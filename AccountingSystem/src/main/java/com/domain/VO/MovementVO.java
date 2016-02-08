package com.domain.VO;

import com.DAO.DBAccess;
import com.domain.Database.Account;
import com.domain.Database.Currency;
import com.domain.Database.Detail;
import com.domain.Database.Movement;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.util.Exception.CoreException;
import com.util.Helpers.DateHelper;
import java.util.Date;

public class MovementVO {

    private String origAccount;
    private String destAccount;
    private Long amount;
    private String currency;
    private Date movementDate;
    @JsonProperty
    private boolean isActualDate;
    private String detail;
    private String comment;

    public MovementVO() {
    }

    public MovementVO(String origAccount, String destAccount, Long amount, String currency, Date movementDate, String detail, String comment) {
        this.origAccount = origAccount;
        this.destAccount = destAccount;
        this.amount = amount;
        this.currency = currency;
        this.movementDate = movementDate;
        this.detail = detail;
        this.comment = comment;
    }

    public String getOrigAccount() {
        return origAccount;
    }

    public void setOrigAccount(String origAccount) {
        this.origAccount = origAccount;
    }

    public String getDestAccount() {
        return destAccount;
    }

    public void setDestAccount(String destAccount) {
        this.destAccount = destAccount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(Date movementDate) {
        this.movementDate = movementDate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String reason) {
        this.detail = reason;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isActualDate() {
        return isActualDate;
    }

    public void setActualDate(boolean actualDate) {
        isActualDate = actualDate;
    }

    @Override
    public String toString() {
        return "MovementVO{" +
                "origAccount='" + origAccount + '\'' +
                ", destAccount='" + destAccount + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", movementDate=" + movementDate +
                ", detail='" + detail + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
