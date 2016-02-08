package com.util.Helpers;

import com.DAO.DBAccess;
import com.DAO.DBAccessObjects.DBAccessAccountSadder;
import com.DAO.DBAccessObjects.DBAccessMovement;
import com.domain.Database.*;
import com.domain.MovementStatus;
import com.domain.VO.MovementVO;
import com.util.Exception.CoreException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by M-Sem on 06/12/2015.
 */
public class MovementHelper {

    final static Logger logger = Logger.getLogger(MovementHelper.class);

    public static void saveMovement(Movement movement) throws CoreException {
        validateMovementForSaving(movement);
        if (isLastMovement(movement)) {
            saveLastMovement(movement);
        } else {
            saveMiddleMovement(movement);
        }
    }




    public static void saveLastMovement(Movement movement) throws CoreException {

        try {
            logger.info("Saving new Movement: " + movement);
            DBAccessMovement dbAccessMovement = ((DBAccessMovement) DBAccess.getDBAccessObject(movement));

            AccountSadder origAccountSadder = new AccountSadder(movement, movement.getOrigAccount());
            AccountSadder destAccountSadder = new AccountSadder(movement, movement.getDestAccount());
            dbAccessMovement.saveMovement(movement, origAccountSadder, destAccountSadder);
            logger.info("Successfully Saved new Movement: " + movement + ". Orig Account Sadder: " + origAccountSadder + ". Dest Account Sadder: " + destAccountSadder);
        } catch (Exception e) {
            logger.error("Error Saving new Movement: " + movement + ". \nException:" + e);
            throw new CoreException("Error Saving new Movement: " + movement + ". \nException:" + e);
        }
    }





    public static void saveMiddleMovement(Movement movement) throws CoreException {

        try {
            logger.info("Saving middle Movement: " + movement);

            DBAccessMovement dbAccessMovement = (DBAccessMovement) DBAccess.getDBAccessObject(Movement.class);

            List<AccountSadder> origAccountSadders = AccountSadderHelper.getAccountSadderAfterDate(movement.getOrigAccount(), movement.getMovementDate());
            Long origLastAccountSadder = AccountSadderHelper.getAccountSadderForAccountByDate(movement.getOrigAccount(), movement.getMovementDate());
            AccountSadder origNewaccountSadder = new AccountSadder(movement, movement.getOrigAccount(), origLastAccountSadder, origLastAccountSadder - movement.getAmount(), MovementStatus.EXECUTED);
            Long expectedOrigSadderAfterCorrection = AccountSadderHelper.getAccountSadderForAccount(movement.getOrigAccount()) - movement.getAmount();

            List<AccountSadder> destAccountSadders = AccountSadderHelper.getAccountSadderAfterDate(movement.getDestAccount(), movement.getMovementDate());
            Long destLastAccountSadder = AccountSadderHelper.getAccountSadderForAccountByDate(movement.getDestAccount(), movement.getMovementDate());
            AccountSadder destNewaccountSadder = new AccountSadder(movement, movement.getDestAccount(), destLastAccountSadder, destLastAccountSadder + movement.getAmount(), MovementStatus.EXECUTED);
            Long expectedDestSadderAfterCorrection = AccountSadderHelper.getAccountSadderForAccount(movement.getDestAccount()) + movement.getAmount();

            Long temporarySadder = origNewaccountSadder.getSadderAfterMovement();
            for (AccountSadder accountSadder : origAccountSadders) {
                accountSadder.setSadderBeforeMovement(temporarySadder);
                accountSadder.setSadderAfterMovement((accountSadder.getMovement().getOrigAccount() == accountSadder.getAccount()) ? accountSadder.getSadderBeforeMovement() - accountSadder.getMovement().getAmount() : accountSadder.getSadderBeforeMovement() + accountSadder.getMovement().getAmount());
                temporarySadder = accountSadder.getSadderAfterMovement();
            }
            if (origAccountSadders.size() > 0 && !(origAccountSadders.get(origAccountSadders.size() - 1).getSadderAfterMovement().equals(expectedOrigSadderAfterCorrection))) {
                new CoreException("There was a problem inserting Movement. Please, try Again.");
            }
            origAccountSadders.add(origNewaccountSadder);


            temporarySadder = destNewaccountSadder.getSadderAfterMovement();
            for (AccountSadder accountSadder : destAccountSadders) {
                accountSadder.setSadderBeforeMovement(temporarySadder);
                accountSadder.setSadderAfterMovement((accountSadder.getMovement().getOrigAccount() == accountSadder.getAccount()) ? accountSadder.getSadderBeforeMovement() - accountSadder.getMovement().getAmount() : accountSadder.getSadderBeforeMovement() + accountSadder.getMovement().getAmount());
                temporarySadder = accountSadder.getSadderAfterMovement();
            }
            if (destAccountSadders.size() > 0 && !destAccountSadders.get(destAccountSadders.size() - 1).getSadderAfterMovement().equals(expectedDestSadderAfterCorrection)) {
                new CoreException("There was a problem inserting Movement. Please, try Again.");
            }
            destAccountSadders.add(destNewaccountSadder);

            dbAccessMovement.saveMovement(movement, origAccountSadders, destAccountSadders);
            logger.info("Successfully Saved Middle Movement: " + movement + ". Orig Account Sadder: " + origNewaccountSadder+ ". Dest Account Sadder: " + destNewaccountSadder);
        } catch (Exception e){
            logger.error("Error Saving Middle Movement: " + movement + ". \nException:" + e);
            throw new CoreException("Error Saving Middle Movement: " + movement + ". \nException:" + e);
        }

    }






    public static void deleteMovement(Movement movement) throws CoreException {

        try {
            logger.info("Deleting Movement: " + movement);
            List<AccountSadder> accountSadderList = ((DBAccessAccountSadder) DBAccess.getDBAccessObject(AccountSadder.class)).getMovementsAccountSadder(movement);
            ((DBAccessMovement) DBAccess.getDBAccessObject(movement)).deleteMovement(movement, accountSadderList);
            logger.info("Successfully Deleted Movement: " + movement);
        } catch (Exception e) {
            logger.error("Error Delete Movement: " + movement + ". Exception:" + e);
            throw new CoreException("Error Delete Movement: " + movement + ". Exception:" + e);
        }
    }




    public static void validateMovementForSaving(Movement movement) throws CoreException {

        hasMissingParameters(movement);

        DBAccessMovement dbAccessMovement = (DBAccessMovement) DBAccess.getDBAccessObject(Movement.class);
        if (isTheSameMovement(movement, dbAccessMovement.getLastSavedMovement()))
            throw new CoreException("This is exactly the same movement you had just saved. If you still want to continue, at least change Commentary.");

        List<AccountSadder> origAccountSadders = AccountSadderHelper.getAccountSaddersForAccount(movement.getOrigAccount());
        List<AccountSadder> destAccountSadders = AccountSadderHelper.getAccountSaddersForAccount(movement.getDestAccount());

        if(origAccountSadders.size() < 1 ||
                DateHelper.isAfter(origAccountSadders.get(origAccountSadders.size() - 1).getMovement().getMovementDate(),movement.getMovementDate())) {

            throw new CoreException("Error, initial movement for Account: " + movement.getOrigAccount() + ", was after: " + movement.getMovementDate());
        }
        if(destAccountSadders.size() < 1 ||
                DateHelper.isAfter(origAccountSadders.get(origAccountSadders.size() - 1).getMovement().getMovementDate(),movement.getMovementDate())) {

            throw new CoreException("Error, initial movement for Account: " + movement.getDestAccount()+", was after: " + movement.getMovementDate());
        }
    }





    public static boolean isLastMovement(Movement movement) throws CoreException {

        if (DateHelper.isToday(movement.getMovementDate()))
            return true;

        DBAccessAccountSadder dbAccessAccountSadder = (DBAccessAccountSadder) DBAccess.getDBAccessObject(AccountSadder.class);

        List<AccountSadder> origAccountSadders = AccountSadderHelper.getAccountSadderAfterDate(movement.getOrigAccount(), movement.getMovementDate());
        List<AccountSadder> destAccountSadders = AccountSadderHelper.getAccountSadderAfterDate(movement.getDestAccount(), movement.getMovementDate());

        if (origAccountSadders.size() > 0 || destAccountSadders.size() > 0) {
            return false;
        }


        return true;
    }





    public static String hasMissingParameters(Movement movement) throws CoreException {

        String attrMissing = "";

        if (movement.getOrigAccount()== null)
            attrMissing += " Origin Account.";
        if (movement.getDestAccount() == null)
            attrMissing += " Destination Account.";
        if (movement.getAmount() == null)
            attrMissing += " Amount.";
        if (movement.getCurrency() == null)
            attrMissing += " Currency.";
        if (movement.getMovementDate() == null)
            attrMissing += " Date.";
        if (movement.getDetail() == null)
            attrMissing += " Category.";
        if (movement.getCommentary() == null)
            attrMissing += " Comment.";

        if (!attrMissing.isEmpty())
            throw new CoreException("Missing Properties for Movement: " + attrMissing);

        return attrMissing;
    }



    public static boolean isTheSameMovement(Movement movementOne, Movement movementTwo) {
        if(movementOne == null || movementTwo == null) {return false;}
        if ((movementOne.getOrigAccount().getId().equals(movementTwo.getOrigAccount().getId()))
                && (movementOne.getDestAccount().getId().equals(movementTwo.getDestAccount().getId()))
                && (movementOne.getAmount().equals(movementTwo.getAmount()))
                && (movementOne.getCurrency().getId().equals(movementTwo.getCurrency().getId()))
                && (movementOne.getCommentary().equals(movementTwo.getCommentary()))
                && (movementOne.getDetail().getId().equals(movementTwo.getDetail().getId()))
                && (movementOne.getStatus().getCode() == movementTwo.getStatus().getCode())
                && DateHelper.areDayMonthYearEqual(movementOne.getMovementDate(), movementTwo.getMovementDate())) {
            return true;
        }
        return false;
    }


    public static Movement getMovementfromVO(MovementVO movementVO) throws CoreException {

        Account origAccount = (Account) DBAccess.getDBAccessObject(Account.class).getObjectById(Long.parseLong(movementVO.getOrigAccount()));
        Account destAccount = (Account) DBAccess.getDBAccessObject(Account.class).getObjectById(Long.parseLong(movementVO.getDestAccount()));
        Detail detail = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(Long.parseLong(movementVO.getDetail()));
        Currency currency = (Currency) DBAccess.getDBAccessObject(Currency.class).getObjectById(Long.parseLong(movementVO.getCurrency()));
        if(!movementVO.isActualDate()){
            if(DateHelper.isToday(movementVO.getMovementDate()))
                throw  new CoreException("If its a todays movement, please check 'Use Actual Date' option.");
            else {
                movementVO.setMovementDate(DateHelper.setHourAndMinute(movementVO.getMovementDate(), 23,59));
            }
        }
        return new Movement(origAccount, destAccount, movementVO.getAmount(), movementVO.getMovementDate(), detail, currency, movementVO.getComment());
    }

    public static Movement getMovementById(String movId) throws CoreException {
        DBAccessMovement dbAccessMovement = (DBAccessMovement) DBAccess.getDBAccessObject(Movement.class);
        return dbAccessMovement.getObjectById(Long.parseLong(movId));

    }

}
