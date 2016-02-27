package Business.domainObjects.DBObjects;

import Business.Exceptions.CoreException;
import Business.ObjectMediators.AccountSadderMediator;
import Business.domainObjects.MovementStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "movement")
public class Movement implements DBObject {

    private static final Logger logger = LogManager.getLogger(Movement.class);


    @Id
    @GeneratedValue(generator = "MOVEMENT_SEQ")
    @SequenceGenerator(name = "MOVEMENT_SEQ", sequenceName = "MOVEMENT_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    private Account origAccount;

    @ManyToOne
    private Account destAccount;

    private Long amount;

    @ManyToOne
    private Currency currency;

    private Date movementDate;

    @ManyToOne
    private Detail detail;

    private String commentary;

    private MovementStatus status;

    public Movement() {
    }

    public Movement(Account origAccount, Account destAccount, Long amount, Date movementDate, Detail detail, Currency currency, String commentary) {
        this.origAccount = origAccount;
        this.destAccount = destAccount;
        this.amount = amount;
        this.movementDate = movementDate;
        this.detail = detail;
        this.currency = currency;
        this.commentary = commentary;
        this.status = MovementStatus.EXECUTED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getOrigAccount() {
        return origAccount;
    }

    public void setOrigAccount(Account origAccount) {
        this.origAccount = origAccount;
    }

    public Account getDestAccount() {
        return destAccount;
    }

    public void setDestAccount(Account destAccount) {
        this.destAccount = destAccount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(Date movementDate) {
        this.movementDate = movementDate;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public MovementStatus getStatus() {
        return status;
    }

    public void setStatus(MovementStatus status) {
        this.status = status;
    }

    public String toPrint(){
        return id + "(Date: " + movementDate + ", origAccount: " + origAccount.toPrint() + ", destAccount: " + destAccount.toPrint() + ") ";
    }

    @Override
    public String toString() {
        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String formattedDate = df2.format(movementDate.getTime());
        try {
            return "Movement{" +
                    "id=" + id +
                    ", origAccount=" + origAccount.getName() +
                    ", origAccountSadder=" + origAccount.getCurrency().getSymbol() + AccountSadderMediator.getAccountSadderForAccount(origAccount) +
                    ", destAccount=" + destAccount.getName() +
                    ", destAccountSadder=" + destAccount.getCurrency().getSymbol() + AccountSadderMediator.getAccountSadderForAccount(destAccount) +
                    ", amount=" + currency.getSymbol() + amount +
                    ", movementDate=" + formattedDate +
                    ", detail=" + detail.getName() +
                    ", category=" + detail.getCategory().getName() +
                    ", commentary='" + commentary + '\'' +
                    ", status=" + status +
                    '}';
        } catch (Exception e) {
            logger.error("Error transforming Movement to String. Returning null");
            return "";
        }
    }
}
