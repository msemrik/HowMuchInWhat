package com.domain.Database;


import com.domain.*;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "bankaccount", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "currency_id", "type", "bank"})})
public class BankAccount extends Account {

    private String bank;

    private String type;

    private String accountNumber;

    public BankAccount(String name, Currency currency, String type, Date startDate, Date endDate, String bank, String accountNumber) {
        super(name, currency, startDate, endDate);
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.type = type;
        setTypeOfAccount("Bank Account");
    }

    public BankAccount() {
        setTypeOfAccount("Bank Account");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
