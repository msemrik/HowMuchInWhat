package Business.ObjectMediators;

import Business.Business;
import Business.Exceptions.CoreException;
import Business.domainObjects.DBObjects.Movement;
import Business.domainObjects.ReportElements.MonthlyAccountSadder;
import Business.domainObjects.DBObjects.Account;
import Business.domainObjects.DBObjects.AccountSadder;
import DataBase.DAO.InterfacesDAO.AccountSadderDAOInterface;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AccountSadderMediator implements MediatorInterface {

    public static List<MonthlyAccountSadder> getAccountSadderMonthlyHistoryForAccount(Account account) throws CoreException, IOException {
        AccountSadderDAOInterface accountSadderDAOInterface = (AccountSadderDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(AccountSadder.class);
        List<MonthlyAccountSadder> accountSadderList = accountSadderDAOInterface.getAccountSadderMonthlyHistoryForAccount(account);
        return accountSadderList;
    }

    public static List<AccountSadder> getAccountSaddersForAccount(Account account) throws CoreException, IOException {
        AccountSadderDAOInterface accountSadderDAOInterface = (AccountSadderDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(AccountSadder.class);
        List<AccountSadder> accountSadderList = accountSadderDAOInterface.getAccountSaddersForAccount(account);
        return accountSadderList;
    }

    public static Double getAccountSadderForAccount(Account account) throws CoreException, IOException {
        AccountSadderDAOInterface accountSaderDAOInterface = (AccountSadderDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(AccountSadder.class);
        Double accountSadder = accountSaderDAOInterface.getAccountSadderForAccount(account);
        return accountSadder;
    }

    public static Double getAccountSadderForAccountByDate(Account account, Date date) throws CoreException, IOException {
        AccountSadderDAOInterface accountSadderDAOInterface = (AccountSadderDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(AccountSadder.class);
        Double accountSadder = accountSadderDAOInterface.getAccountSadderForAccountByDate(account, date);
        return accountSadder;
    }

    public static List<AccountSadder> getAccountSadderAfterDate(Account account, Date date) throws CoreException, IOException {
        AccountSadderDAOInterface accountSadderDAOInterface = (AccountSadderDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(AccountSadder.class);
        List<AccountSadder> accountSadderList = accountSadderDAOInterface.getPosteriorDateAccountSadders(account, date);
        return accountSadderList;
    }

    public static List<AccountSadder> getMovementsAccountSadder(Movement movement) throws CoreException, IOException {
        AccountSadderDAOInterface accountSadderDAOInterface = (AccountSadderDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(AccountSadder.class);
        List<AccountSadder> accountSadderList = accountSadderDAOInterface.getMovementsAccountSadder(movement);
        return accountSadderList;
    }

}
