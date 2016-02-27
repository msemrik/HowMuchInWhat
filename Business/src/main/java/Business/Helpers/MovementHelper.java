package Business.Helpers;

import Business.Business;
import Business.Exceptions.BusinessProcessException;
import Business.Exceptions.CoreException;
import Business.Exceptions.ValidationException;
import Business.MessageConstants.ErrorMessagesConstants;
import Business.ObjectMediators.*;
import Business.domainObjects.DBObjects.*;
import Business.domainObjects.MovementStatus;
import Business.domainObjects.VO.MovementVO;
import DataBase.DAO.InterfacesDAO.MovementDAOInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class MovementHelper {

    private static final Logger logger = (Logger) LogManager.getLogger(MovementHelper.class);

    public static void saveMovement(Movement movement) throws CoreException, IOException {
        validateMovementForSaving(movement);
        if (isLastMovement(movement)) {
            saveLastMovement(movement);
        } else {
            saveMiddleMovement(movement);
        }
    }

    public static void saveLastMovement(Movement movement) throws CoreException {

        try {
            logger.info("Saving Last Movement: " + movement);
            AccountSadder origAccountSadder = new AccountSadder(movement, movement.getOrigAccount());
            AccountSadder destAccountSadder = new AccountSadder(movement, movement.getDestAccount());
            MovementMediator.saveMovement(movement, origAccountSadder, destAccountSadder);
            logger.info("Successfully Saved Last Movement: " + movement + ". Orig Account Sadder: " + origAccountSadder + ". Dest Account Sadder: " + destAccountSadder);
        } catch (Exception e) {
            String error = "Error Saving Last Movement: " + movement + ". \nException:" + e;
            logger.error(error);
            throw new CoreException(error, e);
        }
    }

    public static void saveMiddleMovement(Movement movement) throws CoreException {
        try {
            logger.info("Saving middle Movement: " + movement);

            List<AccountSadder> origAccountSadders = AccountSadderMediator.getAccountSadderAfterDate(movement.getOrigAccount(), movement.getMovementDate());
            Long origLastAccountSadder = AccountSadderMediator.getAccountSadderForAccountByDate(movement.getOrigAccount(), movement.getMovementDate());
            AccountSadder origNewaccountSadder = new AccountSadder(movement, movement.getOrigAccount(), origLastAccountSadder, origLastAccountSadder - movement.getAmount(), MovementStatus.EXECUTED);
            Long expectedOrigSadderAfterCorrection = AccountSadderMediator.getAccountSadderForAccount(movement.getOrigAccount()) - movement.getAmount();

            List<AccountSadder> destAccountSadders = AccountSadderMediator.getAccountSadderAfterDate(movement.getDestAccount(), movement.getMovementDate());
            Long destLastAccountSadder = AccountSadderMediator.getAccountSadderForAccountByDate(movement.getDestAccount(), movement.getMovementDate());
            AccountSadder destNewaccountSadder = new AccountSadder(movement, movement.getDestAccount(), destLastAccountSadder, destLastAccountSadder + movement.getAmount(), MovementStatus.EXECUTED);
            Long expectedDestSadderAfterCorrection = AccountSadderMediator.getAccountSadderForAccount(movement.getDestAccount()) + movement.getAmount();

            Long temporarySadder = origNewaccountSadder.getSadderAfterMovement();
            for (AccountSadder accountSadder : origAccountSadders) {
                accountSadder.setSadderBeforeMovement(temporarySadder);
                accountSadder.setSadderAfterMovement((accountSadder.getMovement().getOrigAccount() == accountSadder.getAccount()) ? accountSadder.getSadderBeforeMovement() - accountSadder.getMovement().getAmount() : accountSadder.getSadderBeforeMovement() + accountSadder.getMovement().getAmount());
                temporarySadder = accountSadder.getSadderAfterMovement();
            }
            if (origAccountSadders.size() > 0 && !(origAccountSadders.get(origAccountSadders.size() - 1).getSadderAfterMovement().equals(expectedOrigSadderAfterCorrection))) {
                new BusinessProcessException(String.format(ErrorMessagesConstants.ERROR_MOVEMENT_RECALCULATING_SADDERS, movement.getOrigAccount().toPrint(), origAccountSadders.size(), expectedOrigSadderAfterCorrection,origAccountSadders.get(origAccountSadders.size() - 1).getSadderAfterMovement()), "Error while recalculating account sadders. Movement is not being saved");
            }
            origAccountSadders.add(origNewaccountSadder);


            temporarySadder = destNewaccountSadder.getSadderAfterMovement();
            for (AccountSadder accountSadder : destAccountSadders) {
                accountSadder.setSadderBeforeMovement(temporarySadder);
                accountSadder.setSadderAfterMovement((accountSadder.getMovement().getOrigAccount() == accountSadder.getAccount()) ? accountSadder.getSadderBeforeMovement() - accountSadder.getMovement().getAmount() : accountSadder.getSadderBeforeMovement() + accountSadder.getMovement().getAmount());
                temporarySadder = accountSadder.getSadderAfterMovement();
            }
            if (destAccountSadders.size() > 0 && !destAccountSadders.get(destAccountSadders.size() - 1).getSadderAfterMovement().equals(expectedDestSadderAfterCorrection)) {
                new BusinessProcessException(String.format(ErrorMessagesConstants.ERROR_MOVEMENT_RECALCULATING_SADDERS, movement.getDestAccount().toPrint(), destAccountSadders.size(), expectedDestSadderAfterCorrection,destAccountSadders.get(destAccountSadders.size() - 1).getSadderAfterMovement()), "Error while recalculating account sadders. Movement is not being saved");
            }
            destAccountSadders.add(destNewaccountSadder);

            MovementMediator.saveMovement(movement, origAccountSadders, destAccountSadders);
            logger.info("Successfully Saved Middle Movement: " + movement + ". Orig Account Sadder: " + origNewaccountSadder + ". Dest Account Sadder: " + destNewaccountSadder);
        } catch (Exception e) {
            String error = "Error Saving Middle Movement: " + movement;
            logger.error(error);
            throw new CoreException(error, e);
        }

    }

    public static void deleteMovement(Movement movement) throws CoreException {

        try {
            logger.info("Deleting Movement: " + movement);
            List<AccountSadder> accountSadderList = AccountSadderMediator.getMovementsAccountSadder(movement);
            MovementMediator.deleteMovement(movement, accountSadderList);
            logger.info("Successfully Deleted Movement: " + movement);
        } catch (Exception e) {
            logger.error("Error Delete Movement: " + movement + ". Exception:" + e);
            throw new CoreException("Error Deleting Movement: " + movement, e);
        }
    }

    public static void validateMovementForSaving(Movement movement) throws CoreException, IOException {
        hasMissingParameters(movement);

        if (isTheSameMovement(movement, MovementMediator.getLastSavedMovement()))
            throw new ValidationException(ErrorMessagesConstants.ERROR_MOVEMENT_THE_SAME_AS_THE_LAST_SAVED);

        List<AccountSadder> origAccountSadders = AccountSadderMediator.getAccountSaddersForAccount(movement.getOrigAccount());
        List<AccountSadder> destAccountSadders = AccountSadderMediator.getAccountSaddersForAccount(movement.getDestAccount());

        if (origAccountSadders.size() < 1 ||
                DateHelper.isAfter(origAccountSadders.get(origAccountSadders.size() - 1).getMovement().getMovementDate(), movement.getMovementDate())) {

            throw new ValidationException(String.format(ErrorMessagesConstants.ERROR_MOVEMENT_INITIAL_ACCOUNT_DATE_IS_AFTER, movement.getOrigAccount().toPrint(), DateHelper.getDayMonthYear(movement.getMovementDate())));
        }
        if (destAccountSadders.size() < 1 ||
                DateHelper.isAfter(origAccountSadders.get(origAccountSadders.size() - 1).getMovement().getMovementDate(), movement.getMovementDate())) {
            throw new ValidationException(String.format(ErrorMessagesConstants.ERROR_MOVEMENT_INITIAL_ACCOUNT_DATE_IS_AFTER, movement.getOrigAccount().toPrint(), DateHelper.getDayMonthYear(movement.getMovementDate())));
        }
    }

    public static boolean isLastMovement(Movement movement) throws CoreException, IOException {
        if (DateHelper.isToday(movement.getMovementDate()))
            return true;
        List<AccountSadder> origAccountSadders = AccountSadderMediator.getAccountSadderAfterDate(movement.getOrigAccount(), movement.getMovementDate());
        List<AccountSadder> destAccountSadders = AccountSadderMediator.getAccountSadderAfterDate(movement.getDestAccount(), movement.getMovementDate());
        if (origAccountSadders.size() > 0 || destAccountSadders.size() > 0) {
            return false;
        }
        return true;
    }

    public static String hasMissingParameters(Movement movement) throws CoreException {
        String attrMissing = "";
        if (movement.getOrigAccount() == null)
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
            throw new ValidationException("Missing Properties for Movement: " + attrMissing, "Movement is not ready to save, there are some missing params: " + attrMissing);

        return attrMissing;
    }

    public static boolean isTheSameMovement(Movement movementOne, Movement movementTwo) {
        if (movementOne == null || movementTwo == null) {
            return false;
        }
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

    public static Movement getMovementfromVO(MovementVO movementVO) throws CoreException, IOException {
        Account origAccount = AccountMediator.getAccountById(Long.parseLong(movementVO.getOrigAccount()));
        Account destAccount = AccountMediator.getAccountById(Long.parseLong(movementVO.getDestAccount()));
        Detail detail = DetailMediator.getDetailById(Long.parseLong(movementVO.getDetail()));
        Currency currency = CurrencyMediator.getCurrencyById(Long.parseLong(movementVO.getCurrency()));
        if (!movementVO.isActualDate()) {
            if (DateHelper.isToday(movementVO.getMovementDate()))
                throw new ValidationException(ErrorMessagesConstants.ERROR_MOVEMENT_TODAY_AND_NOT_USE_ACTUAL_DATE);
            else {
                movementVO.setMovementDate(DateHelper.setHourAndMinute(movementVO.getMovementDate(), 23, 59));
            }
        }
        return new Movement(origAccount, destAccount, movementVO.getAmount(), movementVO.getMovementDate(), detail, currency, movementVO.getComment());
    }

}
