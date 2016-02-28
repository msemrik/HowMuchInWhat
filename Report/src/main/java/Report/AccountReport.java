package Report;

import Business.Exceptions.CoreException;
import Business.ObjectMediators.AccountSadderMediator;
import Business.domainObjects.DBObjects.Account;
import ReportElements.AccountInformation;
import ReportElements.AccountSadderGraph;
import java.io.IOException;

public class AccountReport {

    private Account account;
    private AccountSadderGraph accountSadderGraph;
    private AccountInformation accountInformation;
    private Double accountSadder;


    public AccountReport(Account account) throws CoreException, IOException {
        this.account = account;
        accountSadderGraph = new AccountSadderGraph(account);
        accountSadder = AccountSadderMediator.getAccountSadderForAccount(account);
        accountInformation = new AccountInformation(account);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountSadderGraph getAccountSadderGraph() {
        return accountSadderGraph;
    }

    public void setAccountSadderGraph(AccountSadderGraph accountSadderGraph) {
        this.accountSadderGraph = accountSadderGraph;
    }

    public Double getAccountSadder() {
        return accountSadder;
    }

    public void setAccountSadder(Double accountSadder) {
        this.accountSadder = accountSadder;
    }

    public AccountInformation getAccountInformation() {
        return accountInformation;
    }

    public void setAccountInformation(AccountInformation accountInformation) {
        this.accountInformation = accountInformation;
    }
}
