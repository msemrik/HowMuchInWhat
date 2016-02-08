package com.util.Helpers;

import com.DAO.DBAccess;
import com.DAO.DBAccessObjects.DBAccessAccount;
import com.DAO.DBAccessObjects.DBAccessDetail;
import com.domain.Database.*;
import com.domain.Report.AccountInformation;
import com.util.Exception.CoreException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by semri on 1/3/2016.
 */
public class AccountHelper {



    public static AccountInformation getAccountInformation(Account account) {

        List<String> labels = new ArrayList<String>();
        labels.add("Id");
        labels.add("Name");
        labels.add("Account type");
        labels.add("Currency");
        labels.add("Account Sadder");

        List<String> data = new ArrayList<String>();
        data.add(String.valueOf(account.getId()));
        data.add(account.getName());
        data.add(account.getTypeOfAccount());
        data.add(account.getCurrency().getSymbol());
        data.add(String.valueOf(account.getAccountSadder()));


        if (account instanceof Person) {
            labels.add("Phone Number:");
            data.add(((Person) account).getPhoneNumber());
        } else if (account instanceof BankAccount) {
            labels.add("Bank");
            labels.add("Type of Account Bank");
            labels.add("Account Number");
            data.add(((BankAccount) account).getBank());
            data.add(account.getTypeOfAccount());
            data.add(((BankAccount) account).getAccountNumber());
        }

        return new AccountInformation(labels, data);
    }


    public static Account getAccountById(String accountId) throws CoreException {
        Account account = (Account) ((DBAccessAccount) DBAccess.getDBAccessObject(Account.class)).getObjectById(Long.parseLong(accountId));
        return account;
    }

    public static void saveAccount(Account account) throws CoreException {
        //Account incomes = (Account) DBAccessAccount.getInstance().getObjectByName("Incomes");
        Detail detail = DBAccessDetail.getInstance().getObjectByName("Initial Movement");
        Movement movement = new Movement(account, account, (long) 0, account.getStartDate(), detail, account.getCurrency(), "Initial Movement");

        DBAccessAccount.getInstance().saveNewAccount(account, movement);



    }
}
