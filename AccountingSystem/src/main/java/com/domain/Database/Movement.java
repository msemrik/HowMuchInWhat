package com.domain.Database;

import com.DAO.DBAccessObjects.DBAccessAccountSadder;
import com.domain.MovementStatus;
import com.util.Exception.CoreException;
import com.util.Helpers.AccountSadderHelper;
import com.util.Helpers.DateHelper;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "movement")
public class Movement implements DBObject {


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

    @Override
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



    @Override
    public String toString() {
        return "Movement{" +
                "amount=" + amount +
                ", id=" + id +
                ", origAccount=" + origAccount +
                ", destAccount=" + destAccount +
                ", currency=" + currency +
                ", movementDate=" + movementDate +
                ", detail=" + detail +
                ", commentary='" + commentary + '\'' +
                ", status=" + status +
                '}';
    }

    public String toStringShowing() throws CoreException {
        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String formattedDate = df2.format(movementDate.getTime());

        return "Movement{" +
                "id=" + id +
                ", origAccount=" + origAccount.getName() +
                ", origAccountSadder=" + origAccount.getCurrency().getSymbol() + AccountSadderHelper.getAccountSadderForAccount(origAccount) +
                ", destAccount=" + destAccount.getName() +
                ", destAccountSadder=" + destAccount.getCurrency().getSymbol() + AccountSadderHelper.getAccountSadderForAccount(destAccount) +
                ", amount=" + currency.getSymbol() + amount +
                ", movementDate=" + formattedDate +
                ", detail=" + detail.getName() +
                ", category=" + detail.getCategory().getName() +
                ", commentary='" + commentary + '\'' +
                ", status=" + status +
                '}';


    }
}
