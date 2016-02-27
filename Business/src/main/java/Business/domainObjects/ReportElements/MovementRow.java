package Business.domainObjects.ReportElements;

import Business.domainObjects.DBObjects.Account;
import Business.domainObjects.DBObjects.Currency;
import Business.domainObjects.DBObjects.Detail;

import java.util.Date;

/**
 * Created by semri on 1/10/2016.
 */
public class MovementRow {

    Long id;
    Date movementDate;

    Account origAccount;
    Long origSadder;

    Account destAccount;
    Long destSadder;

    Currency currency;
    Long amount;

    Detail detail;

    String commentary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(Date movementDate) {
        this.movementDate = movementDate;
    }

    public Account getOrigAccount() {
        return origAccount;
    }

    public void setOrigAccount(Account origAccount) {
        this.origAccount = origAccount;
    }

    public Long getOrigSadder() {
        return origSadder;
    }

    public void setOrigSadder(Long origSadder) {
        this.origSadder = origSadder;
    }

    public Account getDestAccount() {
        return destAccount;
    }

    public void setDestAccount(Account destAccount) {
        this.destAccount = destAccount;
    }

    public Long getDestSadder() {
        return destSadder;
    }

    public void setDestSadder(Long destSadder) {
        this.destSadder = destSadder;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
