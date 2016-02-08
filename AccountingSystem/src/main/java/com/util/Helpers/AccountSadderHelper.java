package com.util.Helpers;

import com.DAO.DBAccess;
import com.DAO.DBAccessObjects.DBAccessAccountSadder;
import com.domain.Database.Account;
import com.domain.Database.AccountSadder;
import com.domain.Report.AccountSadderGraph;
import com.domain.Report.MonthlyAccountSadder;
import com.util.Exception.CoreException;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

/**
 * Created by semri on 1/31/2016.
 */
public class AccountSadderHelper {

    public static List<MonthlyAccountSadder> getAccountSadderMonthlyHistoryForAccount(Account account) throws CoreException {

        List<MonthlyAccountSadder> accountSadderList =((DBAccessAccountSadder) DBAccess.getDBAccessObject(AccountSadder.class)).getAccountSadderMonthlyHistoryForAccount(account);
        return accountSadderList;
    }

    public static List<AccountSadder> getAccountSaddersForAccount(Account account) throws CoreException {

        List<AccountSadder> accountSadderList =((DBAccessAccountSadder) DBAccess.getDBAccessObject(AccountSadder.class)).getAccountSaddersForAccount(account);
        return accountSadderList;
    }

    public static Long getAccountSadderForAccount(Account account) throws CoreException {
        Long accountSadder = ((DBAccessAccountSadder) DBAccess.getDBAccessObject(AccountSadder.class)).getAccountSadderForAccount(account);
        return accountSadder;
    }

    public static long getAccountSadderForAccountByDate(Account account, Date date) throws CoreException {
        DBAccessAccountSadder dbAccessAccountSadder = (DBAccessAccountSadder) DBAccess.getDBAccessObject(AccountSadder.class);
        return dbAccessAccountSadder.getAccountSadderForAccountByDate(account, date);
    }

    public static List<AccountSadder> getAccountSadderAfterDate(Account account, Date date) throws CoreException {
        DBAccessAccountSadder dbAccessAccountSadder = (DBAccessAccountSadder) DBAccess.getDBAccessObject(AccountSadder.class);
        return dbAccessAccountSadder.getPosteriorDateAccountSadders(account, date);
    }

}
