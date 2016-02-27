package DataBase.DAO.InterfacesDAO;

import Business.Exceptions.CoreException;
import Business.domainObjects.DBObjects.*;
import java.util.List;

public interface AccountDAOInterface {
    Account getAccountById(long id) throws CoreException;

    List<Account> loadEveryAccountWithSadder() throws CoreException;

    Account getAccountByName(String name) throws CoreException;

    List<BankAccount> loadEveryBankAccount() throws CoreException;

    List<Person> loadEveryPerson() throws CoreException;

    void saveNewAccount(Account account, Movement movement) throws CoreException;
}
