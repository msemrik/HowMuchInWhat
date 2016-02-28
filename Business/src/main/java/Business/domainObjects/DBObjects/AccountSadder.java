package Business.domainObjects.DBObjects;

import Business.Exceptions.CoreException;
import Business.Helpers.CurrencyHelper;
import Business.ObjectMediators.AccountSadderMediator;
import Business.domainObjects.MovementStatus;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(name = "AccountSadder")
public class AccountSadder/* implements DBObject */{

    @Id
    @GeneratedValue(generator = "ACCOUNTSADDER_SEQ")
    @SequenceGenerator(name = "ACCOUNTSADDER_SEQ", sequenceName = "ACCOUNTSADDER_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    private Movement movement;

    @ManyToOne
    private Account account;

    private Double sadderBeforeMovement;
    private Double sadderAfterMovement;

    private MovementStatus movementStatus;

    public AccountSadder() {
    }

    public AccountSadder(Movement movement, Account account, Double sadderBeforeMovement, Double sadderAfterMovement, MovementStatus movementStatus) {
        this.movement = movement;
        this.account = account;
        this.sadderBeforeMovement = sadderBeforeMovement;
        this.sadderAfterMovement = sadderAfterMovement;
        this.movementStatus = movementStatus;
    }


    public AccountSadder(Movement movement, Account account) throws CoreException, IOException {

        this.movement = movement;
        this.account = account;
        this.sadderBeforeMovement = AccountSadderMediator.getAccountSadderForAccount(account);

        int signChanger = 1;

        if (movement.getOrigAccount() == account)
            signChanger = signChanger * -1;

        if(movement.getStatus()== MovementStatus.REVERTED)
            signChanger = signChanger * -1;

        Double amount = (movement.getCurrency().getId() == account.getCurrency().getId()) ? movement.getAmount() : (movement.getAmount() * movement.getCurrencyCotization());
        this.sadderAfterMovement = this.sadderBeforeMovement + ( amount * signChanger);

        this.movementStatus = movement.getStatus();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getSadderBeforeMovement() {
        return sadderBeforeMovement;
    }

    public void setSadderBeforeMovement(Double sadderBeforeMovement) {
        this.sadderBeforeMovement = sadderBeforeMovement;
    }

    public Double getSadderAfterMovement() {
        return sadderAfterMovement;
    }

    public void setSadderAfterMovement(Double sadderAfterMovement) {
        this.sadderAfterMovement = sadderAfterMovement;
    }

    public MovementStatus getMovementStatus() {
        return movementStatus;
    }

    public void setMovementStatus(MovementStatus movementStatus) {
        this.movementStatus = movementStatus;
    }

    public String toPrint(){
        return id + "(Movement: " + movement.getId() + ", Account: " + account.toPrint() +") ";
    }

    @Override
    public String toString() {
        return "AccountSadder{" +
                "account=" + account.getName() + account.getId() +
                ", id=" + id +
                ", movement=" + movement.getId() +
                ", sadderBeforeMovement=" + sadderBeforeMovement +
                ", sadderAfterMovement=" + sadderAfterMovement +
                ", movementStatus=" + movementStatus +
                '}';
    }
}

