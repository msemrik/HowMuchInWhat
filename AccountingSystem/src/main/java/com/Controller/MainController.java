package com.Controller;


import com.DAO.DBAccess;
import com.DAO.DBAccessObjects.*;
import com.domain.Database.Currency;
import com.domain.Report.AccountReport;
import com.domain.Database.*;
import com.domain.Report.MovementReport;
import com.domain.VO.CategoryVO;
import com.domain.VO.CategoryList;
import com.domain.VO.MovementVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.Exception.CoreException;
import com.util.Helpers.AccountHelper;
import com.util.Helpers.DetailHelper;
import com.util.Helpers.MovementHelper;
import com.util.Helpers.ResponseHelper;
import com.util.RESTResponse;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;


@RestController
public class MainController {

    final static Logger logger = Logger.getLogger(MainController.class);

    @RequestMapping(value = "/getCategories", method = RequestMethod.POST)
    @ResponseBody
    public List<Object> getCategories() throws CoreException {
        return ((DBAccessCategory) DBAccess.getDBAccessObject(Category.class)).loadEveryRow();
    }

    @RequestMapping(value = "/getCategoriesList", method = RequestMethod.POST)
    @ResponseBody
    public List<CategoryList> getCategoriesList() throws CoreException {
        List<CategoryList> detailList = DetailHelper.getCategoryList();
        return detailList;
    }


    @RequestMapping(value = "/getAccounts", method = RequestMethod.POST)
    @ResponseBody
    public List<Account> getAccounts() throws CoreException {
        return ((DBAccessAccount) DBAccess.getDBAccessObject(Account.class)).loadEveryAccountWithSadder();

    }

    @RequestMapping(value = "/getCurrencies", method = RequestMethod.POST)
    @ResponseBody
    public List<Object> getCurrencies() throws CoreException {
        return ((DBAccessCurrency) DBAccess.getDBAccessObject(Currency.class)).loadEveryRow();
    }

    @RequestMapping(value = "/getMovements", method = RequestMethod.POST)
    @ResponseBody
    public List<Object> getMovements() throws CoreException {
        return ((DBAccessMovement) DBAccess.getDBAccessObject(Movement.class)).loadEveryRow();

    }

    @RequestMapping(value = "/getDetails", method = RequestMethod.POST)
    @ResponseBody
    public List<Detail> getDetails(@RequestBody CategoryVO categoryVO) throws CoreException {
        Category category = categoryVO.getCategoryfromVO();
        return ((DBAccessDetail) DBAccess.getDBAccessObject(Detail.class)).getDetailsForCategory(category);
    }




    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public Map<String, Object> home() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Principal user(Principal user) {
        return user;
    }








    @RequestMapping(value = "/getAccountReport", method = RequestMethod.POST)
    @ResponseBody
    public String getAccountReport(@RequestParam(value = "accountId", required = true) String accountId) throws CoreException, JsonProcessingException {
        String responseEntity = null;
        try {
            logger.info("REST Service Request: getAccountReport for Account: " + accountId + ".");

            Account account = AccountHelper.getAccountById(accountId);
            AccountReport report = new AccountReport(account);
            responseEntity = ResponseHelper.createResponseEntity(report, HttpStatus.OK);

            logger.info("REST Service Request: Successfully created Report for Account : " + accountId);
        } catch (Exception e) {
            logger.error("REST Service Request: Error while creating Report for Account: " + accountId + ". Exception: " + e.getMessage());
            responseEntity = ResponseHelper.createResponseEntity("Error while creating Report for Account: " + accountId + ". Exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }


       return responseEntity;

    }



    @RequestMapping(value = "/getMovementsReport", method = RequestMethod.POST)
    @ResponseBody
    public String getMovementsReport(@RequestParam(value = "orderDesc") boolean orderDesc) throws CoreException, JsonProcessingException {
        String responseEntity = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.info("REST Service Request: getMovementReport, order desc: " + orderDesc + ".");

            MovementReport report = new MovementReport(orderDesc);
            responseEntity = ResponseHelper.createResponseEntity(report, HttpStatus.OK);

            logger.info("REST Service Request: Successfully created Movements Report.");
        } catch (Exception e) {
            logger.error("REST Service Request: Error while creating Movements Report. Exception: " + e.getMessage());
            responseEntity = ResponseHelper.createResponseEntity("Error while creating Movement Report. Exception: " + e.getMessage(), HttpStatus.OK);
        }

        return responseEntity;
    }







    @RequestMapping(value = "/createMovement", method = RequestMethod.POST)
    @ResponseBody
    public String createMovement(@RequestBody MovementVO movementVO) throws JsonProcessingException {
        String responseEntity = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.info("REST Service Request: createMovement(" + movementVO + ").");
            Movement movement = MovementHelper.getMovementfromVO(movementVO);
            MovementHelper.saveMovement(movement);
            responseEntity = ResponseHelper.createResponseEntity(movement.getId(), "Succesfully saved: " + movement.toStringShowing(), HttpStatus.OK);
            logger.info("REST Service Request: createMovement(" + movementVO + "). Successfully created Movement: " + movement);
        } catch (CoreException e) {
            logger.error("REST Service Request: createMovement(" + movementVO + "). Error while creating Movement: " + movementVO + ". Exception: " + e.getMessage());
            responseEntity = ResponseHelper.createResponseEntity("Error while creating movement: " + movementVO + ".\n Exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return responseEntity;
    }





    @RequestMapping(value = "/deleteMovement", method = RequestMethod.POST)
    @ResponseBody
    public String deteleMovement(@RequestParam(value = "movId", required = true) String movId) throws JsonProcessingException {
        String responseEntity = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.info("REST Service Request: deleteMovement(" + movId + "). Deleting Movement: " + movId);
            Movement movement = MovementHelper.getMovementById(movId);
            MovementHelper.deleteMovement(movement);
            responseEntity = ResponseHelper.createResponseEntity(movement.getId(), "Succesfully saved: " + movement.toStringShowing(), HttpStatus.OK);
            logger.info("REST Service Request: deleteMovement(" + movId + "). Successfully deleted Movement: " + movement);
        } catch (CoreException e) {
            logger.error("REST Service Request: deleteMovement((" + movId + ")). Error while deleting movement: " + movId + ". Exception: " + e.getMessage());
            responseEntity = ResponseHelper.createResponseEntity("Error while undoing Moving: " + movId + ".\n Exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }


}