package com.domain.Report;

import com.DAO.DBAccess;
import com.DAO.DBAccessObjects.DBAccessAccountSadder;
import com.domain.Database.Account;
import com.domain.Database.AccountSadder;
import com.util.Helpers.AccountHelper;
import com.util.Exception.CoreException;
import com.util.Helpers.AccountSadderHelper;

import java.util.List;

public class AccountReport {

    private Account account;
    private AccountSadderGraph accountSadderGraph;
    private AccountInformation accountInformation;
    private Long accountSadder;


    public AccountReport(Account account) throws CoreException {
        this.account = account;
        accountSadderGraph = new AccountSadderGraph(account);
        accountSadder = AccountSadderHelper.getAccountSadderForAccount(account);
        accountInformation = AccountHelper.getAccountInformation(account);
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

    public Long getAccountSadder() {
        return accountSadder;
    }

    public void setAccountSadder(Long accountSadder) {
        this.accountSadder = accountSadder;
    }

    public AccountInformation getAccountInformation() {
        return accountInformation;
    }

    public void setAccountInformation(AccountInformation accountInformation) {
        this.accountInformation = accountInformation;
    }
}
