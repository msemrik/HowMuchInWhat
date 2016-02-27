package ReportMain;

import Business.Exceptions.CoreException;
import Business.ObjectMediators.AccountMediator;
import Business.domainObjects.DBObjects.Account;

import Report.AccountReport;
import java.io.IOException;

/**
 * Created by semri on 2/16/2016.
 */
public class main {
    public static void main(String[] args) throws IOException, CoreException {
        Account account = AccountMediator.getAccountById(Long.parseLong("1"));
        AccountReport accountReport = new AccountReport(account);

    }
}
