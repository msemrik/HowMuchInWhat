package Business.ObjectMediators;

import Business.Business;
import Business.domainObjects.DBObjects.Movement;
import DataBase.DAO.InterfacesDAO.AccountDAOInterface;
import Business.Exceptions.CoreException;
import Business.domainObjects.DBObjects.Account;
import java.io.IOException;
import java.util.List;

public class AccountMediator implements MediatorInterface {

    public static Account getAccountById(Long accountId) throws CoreException, IOException {
        AccountDAOInterface accountDAOInterface = (AccountDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Account.class);
        Account account = accountDAOInterface.getAccountById(accountId);
        return account;
    }

    public static List<Account> loadEveryAccountWithSadder() throws CoreException, IOException {
        AccountDAOInterface accountDAOInterface = (AccountDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Account.class);
        List<Account> accounts = accountDAOInterface.loadEveryAccountWithSadder();
        return accounts;
    }


    public static void saveNewAccount(Account account, Movement movement) throws CoreException, IOException {
        AccountDAOInterface accountDAOInterface = (AccountDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Account.class);
        accountDAOInterface.saveNewAccount(account, movement);

    }
}
