package Business.ObjectMediators;

import Business.Business;
import Business.Exceptions.CoreException;
import Business.domainObjects.DBObjects.AccountSadder;
import Business.domainObjects.ReportElements.MovementRow;
import Business.domainObjects.DBObjects.Movement;
import DataBase.DAO.InterfacesDAO.MovementDAOInterface;
import java.io.IOException;
import java.util.List;

public class MovementMediator implements MediatorInterface {

    public static List<MovementRow> loadEveryMovementOrdered(Boolean orderDesc) throws CoreException, IOException {
        MovementDAOInterface movementDAOInterface = (MovementDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Movement.class);
        List<MovementRow> movementRows = movementDAOInterface.loadEveryMovementOrdered(orderDesc);
        return movementRows;
    }

    public static Movement getLastSavedMovement() throws CoreException, IOException {
        MovementDAOInterface movementDAOInterface = (MovementDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Movement.class);
        Movement movement = movementDAOInterface.getLastSavedMovement();
        return movement;
    }

    public static Movement getMovementById(Long id) throws CoreException, IOException {
        MovementDAOInterface movementDAOInterface = (MovementDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Movement.class);
        Movement movement = movementDAOInterface.getMovementById(id);
        return movement;
    }

    public static void deleteMovement(Movement movement, List<AccountSadder> accountSadderList) throws CoreException, IOException {
        MovementDAOInterface movementDAOInterface = (MovementDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Movement.class);
        movementDAOInterface.deleteMovement(movement, accountSadderList);
    }

    public static void deleteMovement(Movement movement, List<AccountSadder> accountSaddersToDelete, List<AccountSadder> accountOrigSadderToUpdate, List<AccountSadder> accountDestSadderToUpdate) throws CoreException, IOException {
        MovementDAOInterface movementDAOInterface = (MovementDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Movement.class);
        movementDAOInterface.deleteMovement(movement, accountSaddersToDelete, accountOrigSadderToUpdate, accountDestSadderToUpdate);
    }

    public static void saveMovement(Movement movement, AccountSadder origAccountSadder, AccountSadder destAccountSadder) throws CoreException, IOException {
        MovementDAOInterface movementDAOInterface = (MovementDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Movement.class);
        movementDAOInterface.saveMovement(movement,origAccountSadder,destAccountSadder);
    }

    public static void saveMovement(Movement movement, List<AccountSadder> origAccountSadder, List<AccountSadder> destAccountSadder) throws CoreException, IOException {
        MovementDAOInterface movementDAOInterface = (MovementDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Movement.class);
        movementDAOInterface.saveMovement(movement,origAccountSadder,destAccountSadder);
    }
}