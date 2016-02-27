package com.Controller;


import Business.Exceptions.CoreException;
import Business.Exceptions.NonStackTraceException;
import Business.Helpers.DateHelper;
import Business.Helpers.DetailHelper;
import Business.Helpers.MovementHelper;
import Business.MessageConstants.LoggingMessageConstants;
import Business.ObjectMediators.*;
import Business.domainObjects.DBObjects.*;
import Business.domainObjects.DBObjects.Currency;
import Business.domainObjects.VO.CategoryList;
import Business.domainObjects.VO.CategoryVO;
import Business.domainObjects.VO.MovementVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.Response.ResponseHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import Report.AccountReport;
import Report.MovementReport;

import java.security.Principal;
import java.util.*;


@RestController
public class MainController {

    private static final Logger logger = (Logger) LogManager.getLogger(MainController.class);
    private String logParameters;

    @RequestMapping(value = "/getCategories", method = RequestMethod.POST)
    @ResponseBody
    public String getCategories() throws JsonProcessingException {
        Date startTime = new Date();
        logParameters = "getCategories()";
        logger.info(String.format(LoggingMessageConstants.REST_SERVICE_BEFORE, logParameters));
        String responseEntity = null;
        try {
            List<Category> categories = CategoryMediator.loadEveryCategory();
            responseEntity = ResponseHelper.createResponseEntity(categories, HttpStatus.OK);
            logger.info(String.format(LoggingMessageConstants.REST_SERVICE_SUCCESS, logParameters, DateHelper.getDetailedDifference(startTime)));
        } catch (NonStackTraceException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (CoreException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (Exception e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        }
        return responseEntity;
    }


    @RequestMapping(value = "/getAccounts", method = RequestMethod.POST)
    @ResponseBody
    public String getAccounts() throws JsonProcessingException {
        Date startTime = new Date();
        logParameters = "getAccounts()";
        logger.info(String.format(LoggingMessageConstants.REST_SERVICE_BEFORE, logParameters));
        String responseEntity = null;
        try {
            List<Account> accounts = AccountMediator.loadEveryAccountWithSadder();
            responseEntity = ResponseHelper.createResponseEntity(accounts, HttpStatus.OK);
            logger.info(String.format(LoggingMessageConstants.REST_SERVICE_SUCCESS, logParameters, DateHelper.getDetailedDifference(startTime)));
        } catch (NonStackTraceException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (CoreException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (Exception e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        }
            return responseEntity;
    }

    @RequestMapping(value = "/getCurrencies", method = RequestMethod.POST)
    @ResponseBody
    public String getCurrencies() throws JsonProcessingException {
        Date startTime = new Date();
        logParameters = "getCurrencies()";
        logger.info(String.format(LoggingMessageConstants.REST_SERVICE_BEFORE, logParameters));
        String responseEntity = null;
        try {
            List<Currency> currencies = CurrencyMediator.loadEveryCurrency();
            responseEntity = ResponseHelper.createResponseEntity(currencies, HttpStatus.OK);
            logger.info(String.format(LoggingMessageConstants.REST_SERVICE_SUCCESS, logParameters, DateHelper.getDetailedDifference(startTime)));
        } catch (NonStackTraceException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (CoreException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (Exception e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        }
        return responseEntity;
    }


    @RequestMapping(value = "/getDetails", method = RequestMethod.POST)
    @ResponseBody
    public String getDetails(@RequestBody CategoryVO categoryVO) throws JsonProcessingException {
        Date startTime = new Date();
        logParameters = "getCurrencies()";
        logger.info(String.format(LoggingMessageConstants.REST_SERVICE_BEFORE, logParameters));
        String responseEntity = null;
        try {
            Category category = categoryVO.getCategoryfromVO();
            List<Detail> details = DetailMediator.getDetailsForCategory(category);
            responseEntity = ResponseHelper.createResponseEntity(details, HttpStatus.OK);
            logger.info(String.format(LoggingMessageConstants.REST_SERVICE_SUCCESS, logParameters, DateHelper.getDetailedDifference(startTime)));
        } catch (NonStackTraceException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (CoreException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (Exception e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        }
        return responseEntity;
    }


    @RequestMapping(value = "/getCategoriesList", method = RequestMethod.POST)
    @ResponseBody
    public String getCategoriesList() throws JsonProcessingException {
        Date startTime = new Date();
        logParameters = "getCategoriesList()";
        logger.info(String.format(LoggingMessageConstants.REST_SERVICE_BEFORE, logParameters));
        String responseEntity = null;
        try {
            List<CategoryList> detailList = DetailHelper.getCategoryList();
            responseEntity = ResponseHelper.createResponseEntity(detailList, HttpStatus.OK);
            logger.info(String.format(LoggingMessageConstants.REST_SERVICE_SUCCESS, logParameters, DateHelper.getDetailedDifference(startTime)));
        } catch (NonStackTraceException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (CoreException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (Exception e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        }
        return responseEntity;
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
    public String getAccountReport(@RequestParam(value = "accountId", required = true) String accountId) throws JsonProcessingException {
        Date startTime = new Date();
        logParameters = "getAccountReport(" + accountId + ")";
        logger.info(String.format(LoggingMessageConstants.REST_SERVICE_BEFORE, logParameters));
        String responseEntity = null;
        try {
            Account account = AccountMediator.getAccountById(Long.parseLong(accountId));
            AccountReport report = new AccountReport(account);
            responseEntity = ResponseHelper.createResponseEntity(report, HttpStatus.OK);
            logger.info(String.format(LoggingMessageConstants.REST_SERVICE_SUCCESS, logParameters, DateHelper.getDetailedDifference(startTime)));
        } catch (NonStackTraceException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (CoreException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (Exception e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        }
        return responseEntity;
    }


    @RequestMapping(value = "/getMovementsReport", method = RequestMethod.POST)
    @ResponseBody
    public String getMovementsReport(@RequestParam(value = "orderDesc") boolean orderDesc) throws JsonProcessingException {
        Date startTime = new Date();
        logParameters = "getMovementsReport(" + orderDesc + ")";
        logger.info(String.format(LoggingMessageConstants.REST_SERVICE_BEFORE, logParameters));
        String responseEntity = null;
        try {
            MovementReport report = new MovementReport(orderDesc);
            responseEntity = ResponseHelper.createResponseEntity(report, HttpStatus.OK);
            logger.info(String.format(LoggingMessageConstants.REST_SERVICE_SUCCESS, logParameters, DateHelper.getDetailedDifference(startTime)));
        } catch (NonStackTraceException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (CoreException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (Exception e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        }
        return responseEntity;
    }


    @RequestMapping(value = "/createMovement", method = RequestMethod.POST)
    @ResponseBody
    public String createMovement(@RequestBody MovementVO movementVO) throws JsonProcessingException {
        Date startTime = new Date();
        logParameters = "createMovement(" + movementVO + ")";
        logger.info(String.format(LoggingMessageConstants.REST_SERVICE_BEFORE, logParameters));
        String responseEntity = null;
        try {
            Movement movement = MovementHelper.getMovementfromVO(movementVO);
            MovementHelper.saveMovement(movement);
            responseEntity = ResponseHelper.createResponseEntity(movement.getId(), HttpStatus.OK);
            logger.info(String.format(LoggingMessageConstants.REST_SERVICE_SUCCESS, logParameters, DateHelper.getDetailedDifference(startTime)));
        } catch (NonStackTraceException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (CoreException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (Exception e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        }
        return responseEntity;
    }


    @RequestMapping(value = "/deleteMovement", method = RequestMethod.POST)
    @ResponseBody
    public String deteleMovement(@RequestParam(value = "movId", required = true) String movId) throws JsonProcessingException {
        Date startTime = new Date();
        logParameters = "deteleMovement(" + movId + ")";
        logger.info(String.format(LoggingMessageConstants.REST_SERVICE_BEFORE, logParameters));
        String responseEntity = null;
        try {
            Movement movement = MovementMediator.getMovementById(Long.parseLong(movId));
            MovementHelper.deleteMovement(movement);
            responseEntity = ResponseHelper.createResponseEntity(movement.getId(), HttpStatus.OK);
            logger.info(String.format(LoggingMessageConstants.REST_SERVICE_SUCCESS, logParameters, DateHelper.getDetailedDifference(startTime)));
        } catch (NonStackTraceException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (CoreException e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        } catch (Exception e) {
            responseEntity = createResponseEntityAndLog(logParameters, startTime, e);
        }
        return responseEntity;
    }

    private String createResponseEntityAndLog(String logParameters, Date startTime, NonStackTraceException e) throws JsonProcessingException {
        String error = String.format(LoggingMessageConstants.REST_SERVICE_LOG_ERROR, logParameters, DateHelper.getDetailedDifference(startTime));
        logger.error(error);
        return ResponseHelper.createErrorResponseEntity(e.getExceptionCause().getForPrintingMessage());
    }

    private String createResponseEntityAndLog(String logParameters, Date startTime, CoreException e) throws JsonProcessingException {
        String error = String.format(LoggingMessageConstants.REST_SERVICE_LOG_ERROR, logParameters, DateHelper.getDetailedDifference(startTime));
        logger.error(error, e);
        return ResponseHelper.createErrorResponseEntity(e.getExceptionCause().getForPrintingMessage(), e);
    }

    private String createResponseEntityAndLog(String logParameters, Date startTime, Exception e) throws JsonProcessingException {
        String error = String.format(LoggingMessageConstants.REST_SERVICE_LOG_ERROR, logParameters, DateHelper.getDetailedDifference(startTime));
        logger.error(error, e);
        return ResponseHelper.createErrorResponseEntity("No business error, check error Description.", e);
    }


}