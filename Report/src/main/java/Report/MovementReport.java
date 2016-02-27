package Report;

import Business.Exceptions.CoreException;
import Business.ObjectMediators.AccountMediator;
import Business.ObjectMediators.MovementMediator;
import Business.domainObjects.DBObjects.Account;
import Business.domainObjects.ReportElements.MovementRow;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovementReport {

    List<Account> accounts = new ArrayList<Account>();
    List<MovementRow> movements= new ArrayList<MovementRow>();

    public MovementReport(Boolean orderDesc) throws CoreException, IOException {
        accounts = AccountMediator.loadEveryAccountWithSadder();
        movements = MovementMediator.loadEveryMovementOrdered(orderDesc);
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
