package DataBase.DAO.InterfacesDAO;

import Business.Exceptions.CoreException;
import Business.domainObjects.ReportElements.MovementRow;
import Business.domainObjects.DBObjects.AccountSadder;
import Business.domainObjects.DBObjects.Movement;
import java.util.List;

public interface MovementDAOInterface {
    Movement getMovementById(long id) throws CoreException;

    Movement getLastSavedMovement() throws CoreException;

    void deleteMovement(Movement movement, List<AccountSadder> accountSadderList) throws CoreException;

    void deleteMovement(Movement movement, List<AccountSadder> accountSaddersToDelete, List<AccountSadder> accountOrigSadderToUpdate, List<AccountSadder> accountDestSadderToUpdate) throws CoreException;

    void saveMovement(Movement movement, AccountSadder origAccountSadder, AccountSadder destAccountSadder) throws CoreException;

    void saveMovement(Movement movement, List<AccountSadder> origAccountSadders, List<AccountSadder> destAccountSadders) throws CoreException;

    List<MovementRow> loadEveryMovementOrdered(Boolean orderDesc) throws CoreException;
}
