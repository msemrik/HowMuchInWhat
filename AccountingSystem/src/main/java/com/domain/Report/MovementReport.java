package com.domain.Report;

import com.DAO.DBAccess;
import com.DAO.DBAccessObjects.DBAccessAccount;
import com.DAO.DBAccessObjects.DBAccessMovement;
import com.domain.Database.Account;
import com.domain.Database.Movement;
import com.util.Exception.CoreException;

import java.util.ArrayList;
import java.util.List;

public class MovementReport {

    List<Account> accounts = new ArrayList<Account>();
    List<MovementRow> movements= new ArrayList<MovementRow>();

    public MovementReport(Boolean orderDesc) throws CoreException {
        DBAccessAccount dbAccessAccount = ((DBAccessAccount) DBAccess.getDBAccessObject(Account.class));
        DBAccessMovement dbAccessMovement = ((DBAccessMovement) DBAccess.getDBAccessObject(Movement.class));

        accounts = dbAccessAccount.loadEveryAccountWithSadder();
        movements = dbAccessMovement.loadEveryMovementOrdered(orderDesc);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<MovementRow> getMovements() {
        return movements;
    }

    public void setMovements(List<MovementRow> movements) {
        this.movements = movements;
    }
}
