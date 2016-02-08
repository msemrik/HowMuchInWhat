package com.domain.Database;

import com.DAO.DBAccessObjects.DBAccessAccountSadder;
import com.util.Exception.CoreException;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Account implements DBObject {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ACCOUNT_SEQ")
    @SequenceGenerator(name = "ACCOUNT_SEQ", sequenceName = "ACCOUNT_SEQ", allocationSize = 1)
    private Long id;

    private String name;

    @ManyToOne
    private Currency currency;

    private Date startDate;
    private Date endDate;

    @Transient
    private long accountSadder;

    @Transient
    private String typeOfAccount;

    public Account(String name, Currency currency, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.typeOfAccount = "Countable Account";
    }

    public Account() {
        this.typeOfAccount = "Countable Account";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAccountSadder() {
        return accountSadder;
    }

    public void setAccountSadder(long accountSadder) {
        this.accountSadder = accountSadder;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "currency=" + currency +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }


}