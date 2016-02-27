package Business.Helpers;

import Business.Exceptions.CoreException;
import Business.ObjectMediators.AccountMediator;
import Business.ObjectMediators.DetailMediator;
import Business.domainObjects.DBObjects.Account;
import Business.domainObjects.DBObjects.Detail;
import Business.domainObjects.DBObjects.Movement;
import java.io.IOException;

public class AccountHelper {

    public static void saveAccount(Account account) throws CoreException, IOException {
        Detail detail = DetailMediator.getDetailByName("Initial Movement");
        Movement movement = new Movement(account, account, (long) 0, account.getStartDate(), detail, account.getCurrency(), "Initial Movement");
        AccountMediator.saveNewAccount(account, movement);
    }
}
