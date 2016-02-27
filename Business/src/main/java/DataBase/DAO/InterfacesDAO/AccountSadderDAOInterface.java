package DataBase.DAO.InterfacesDAO;

import Business.Exceptions.CoreException;
import Business.domainObjects.ReportElements.MonthlyAccountSadder;
import Business.domainObjects.DBObjects.Account;
import Business.domainObjects.DBObjects.AccountSadder;
import Business.domainObjects.DBObjects.Movement;
import java.util.Date;
import java.util.List;

public interface AccountSadderDAOInterface {
    AccountSadder getAccountSadderById(long id) throws CoreException;

    Long getAccountSadderForAccount(Account account) throws CoreException;

    long getAccountSadderForAccountByDate(Account account, Date date) throws CoreException;

    List<MonthlyAccountSadder> getAccountSadderMonthlyHistoryForAccount(Account account) throws CoreException;

    List<AccountSadder> getPosteriorDateAccountSadders(Account account, Date date) throws CoreException;

    List<AccountSadder> getMovementsAccountSadder(Movement movement) throws CoreException;

    List<AccountSadder> getAccountSaddersForAccount(Account account) throws CoreException;
}
