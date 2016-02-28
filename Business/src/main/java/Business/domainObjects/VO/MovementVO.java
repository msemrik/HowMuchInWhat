package Business.domainObjects.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class MovementVO {

    private String origAccount;
    private String destAccount;
    private Double amount;
    private String currency;
    private Double currencyCotization;
    private Date movementDate;
    @JsonProperty
    private boolean isActualDate;
    private String detail;
    private String comment;

    public MovementVO() {
    }

    public MovementVO(String origAccount, String destAccount, Double amount, String currency, Double currencyCotization, Date movementDate, String detail, String comment) {
        this.origAccount = origAccount;
        this.destAccount = destAccount;
        this.amount = amount;
        this.currency = currency;
        this.currencyCotization = currencyCotization;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    public Double getCurrencyCotization() {
        return currencyCotization;
    }

    public void setCurrencyCotization(Double currencyCotization) {
        this.currencyCotization = currencyCotization;
    }

    @Override
    public String toString() {
        String returnString = "MovementVO{" +
                "origAccount='" + origAccount + '\'' +
                ", destAccount='" + destAccount + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'';

        if (currencyCotization != null)
            returnString = returnString + ", currencyCotization='" + currencyCotization + '\'';

        returnString = returnString + ", movementDate=" + movementDate +
                ", detail='" + detail + '\'' +
                ", comment='" + comment + '\'' +
                '}';

        return returnString;
    }
}
