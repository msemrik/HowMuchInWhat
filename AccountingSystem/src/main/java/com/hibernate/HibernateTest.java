package com.hibernate;

import com.DAO.DBAccess;
import com.domain.Database.*;
import com.domain.Report.AccountReport;
import com.util.Helpers.AccountHelper;
import com.util.Helpers.AccountSadderHelper;
import org.apache.log4j.Logger;

import java.util.Date;

public class HibernateTest {

    private static Logger logger = Logger.getLogger(HibernateTest.class);

    public static void main2(String[] args) throws Exception {

        Currency peso;
        Currency dolar;
        try {
            peso = new Currency("peso", "$", 1);
            dolar = new Currency("dolar", "u$d", 15);
            DBAccess.getDBAccessObject(Currency.class).saveObject(peso);
            DBAccess.getDBAccessObject(Currency.class).saveObject(dolar);
        } catch (Exception e) {
            peso = (Currency) DBAccess.getDBAccessObject(Currency.class).getObjectById(1);
            dolar = (Currency) DBAccess.getDBAccessObject(Currency.class).getObjectById(2);
        }

        System.out.println(peso);
        System.out.println(dolar);


        BankAccount bankAccount1;
        BankAccount bankAccount2;
        BankAccount bankAccount3;
        BankAccount bankAccount4;
        BankAccount bankAccount5;
        BankAccount bankAccount6;
        BankAccount bankAccount7;
        try {
            bankAccount1 = new BankAccount("Cordoba Debito", peso, "Debito", new Date("11/17/2015"), null, "Banco de Córdoba", "11110002223334445566");
            bankAccount2 = new BankAccount("Frances Debito", peso, "Debito", new Date("11/17/2015"), null, "Banco Frances", "11110002223334445566");
            bankAccount3 = new BankAccount("Frances Credito", peso, "Credito", new Date("11/17/2015"), null, "Banco Frances", "11110002223334445566");
            bankAccount4 = new BankAccount("Santander Debito", peso, "Debito", new Date("11/17/2015"), null, "Santander Rio", "11110002223334445566");
            bankAccount5 = new BankAccount("Santander Debito", dolar, "Debito", new Date("11/17/2015"), null, "Santander Rio", "11110002223334445566");
            bankAccount6 = new BankAccount("Santander Credito Visa", peso, "Credito", new Date("11/17/2015"), null, "Santander Rio", "11110002223334445566");
            bankAccount7 = new BankAccount("Santander Credito Amex", peso, "Credito", new Date("11/17/2015"), null, "Santander Rio", "11110002223334445566");

            DBAccess.getDBAccessObject(Account.class).saveObject(bankAccount1);
            DBAccess.getDBAccessObject(Account.class).saveObject(bankAccount2);
            DBAccess.getDBAccessObject(Account.class).saveObject(bankAccount3);
            DBAccess.getDBAccessObject(Account.class).saveObject(bankAccount4);
            DBAccess.getDBAccessObject(Account.class).saveObject(bankAccount5);
            DBAccess.getDBAccessObject(Account.class).saveObject(bankAccount6);
            DBAccess.getDBAccessObject(Account.class).saveObject(bankAccount7);
        } catch (Exception e) {
            bankAccount1 = (BankAccount) DBAccess.getDBAccessObject(Account.class).getObjectById(1);
            bankAccount2 = (BankAccount) DBAccess.getDBAccessObject(Account.class).getObjectById(2);
            bankAccount3 = (BankAccount) DBAccess.getDBAccessObject(Account.class).getObjectById(3);
            bankAccount4 = (BankAccount) DBAccess.getDBAccessObject(Account.class).getObjectById(4);
            bankAccount5 = (BankAccount) DBAccess.getDBAccessObject(Account.class).getObjectById(5);
            bankAccount6 = (BankAccount) DBAccess.getDBAccessObject(Account.class).getObjectById(6);
            bankAccount7 = (BankAccount) DBAccess.getDBAccessObject(Account.class).getObjectById(7);


        }

        System.out.println(bankAccount1);
        System.out.println(bankAccount2);
        System.out.println(bankAccount3);
        System.out.println(bankAccount4);
        System.out.println(bankAccount5);
        System.out.println(bankAccount6);
        System.out.println(bankAccount7);


        Person person1;
        Person person2;

        try {
            person1 = new Person("Osvaldo Samuel Semrik", dolar,  new Date("11/17/2015"), null, "3513313182");
            person2 = new Person("Matìas Semrik", peso,  new Date("11/17/2015"), null, "3513313182");
            DBAccess.getDBAccessObject(Account.class).saveObject(person1);
            DBAccess.getDBAccessObject(Account.class).saveObject(person2);
        } catch (Exception e) {
            person1 = (Person) DBAccess.getDBAccessObject(Account.class).getObjectById(8);
            person2 = (Person) DBAccess.getDBAccessObject(Account.class).getObjectById(9);
        }

        System.out.println(person1);
        System.out.println(person2);


        Account incomeAccount;
        Account outcomeAccount;

        try {
            incomeAccount = new Account("Incomes", peso,  new Date("11/17/2015"), null);
            outcomeAccount = new Account("Outcomes", peso,  new Date("11/17/2015"), null);
            DBAccess.getDBAccessObject(Account.class).saveObject(incomeAccount);
            DBAccess.getDBAccessObject(Account.class).saveObject(outcomeAccount);
        } catch (Exception e) {
            incomeAccount = (Account) DBAccess.getDBAccessObject(Account.class).getObjectById(10);
            outcomeAccount = (Account) DBAccess.getDBAccessObject(Account.class).getObjectById(11);
        }

        System.out.println(incomeAccount);
        System.out.println(outcomeAccount);


        Category category;
        Category category2;
        Category category3;
        Category category4;
        Category category5;
        Category category6;
        try {
            category = new Category("Department Taxes and Expenses");
            category2 = new Category("Shopping");
            category3 = new Category("Payroll");
            category4 = new Category("Monthly Expenses");
            category5 = new Category("Countable");
            //category5 = new Category("Super");
            //category6 = new Category("Expensas");
            DBAccess.getDBAccessObject(category).saveObject(category);
            DBAccess.getDBAccessObject(category2).saveObject(category2);
            DBAccess.getDBAccessObject(category3).saveObject(category3);
            DBAccess.getDBAccessObject(category4).saveObject(category4);
            DBAccess.getDBAccessObject(category5).saveObject(category5);
            //DBAccess.getDBAccessObject(category6).saveObject(category6);

        } catch (Exception e) {
            category = (Category) DBAccess.getDBAccessObject(Category.class).getObjectById(1);
            category2 = (Category) DBAccess.getDBAccessObject(Category.class).getObjectById(2);
            category3 = (Category) DBAccess.getDBAccessObject(Category.class).getObjectById(3);
            category4 = (Category) DBAccess.getDBAccessObject(Category.class).getObjectById(4);
            category5 = (Category) DBAccess.getDBAccessObject(Category.class).getObjectById(5);
            //category6 = (Category) DBAccess.getDBAccessObject(Category.class).getObjectById(6);


        }

        System.out.println(category);
        System.out.println(category2);
        System.out.println(category3);
        System.out.println(category4);
        System.out.println(category5);
        //System.out.println(category6);


        Detail detail1;
        Detail detail2;
        Detail detail3;
        Detail detail4;
        Detail detail5;
        Detail detail6;
        Detail detail7;
        Detail detail8;
        Detail detail9;
        Detail detail10;
        Detail detail11;
        Detail detail12;
        Detail detail13;
        Detail detail14;
        Detail detail15;
        Detail detail16;
        Detail detail17;

        try {
            detail1 = new Detail(category, "Aguas Cordobesas");
            detail2 = new Detail(category, "EcoGas");
            detail3 = new Detail(category, "Municipalidad");
            detail4 = new Detail(category, "Rentas");
            detail5 = new Detail(category, "EPEC");
            detail6 = new Detail(category, "Cablevision");

            detail7 = new Detail(category2, "SuperMarket");
            detail8 = new Detail(category2, "Lunch");
            detail9 = new Detail(category2, "Clothes");
            detail10 = new Detail(category2, "Others");
            detail11 = new Detail(category2, "Grocery");

            detail12 = new Detail(category3, "Aguinaldo");
            detail13 = new Detail(category3, "Bonus");
            detail14 = new Detail(category3, "Salary");

            detail15 = new Detail(category4, "Swimming");
            detail16 = new Detail(category4, "Football");

            detail17 = new Detail(category5, "Initial Movement");


            DBAccess.getDBAccessObject(detail1).saveObject(detail1);
            DBAccess.getDBAccessObject(detail2).saveObject(detail2);
            DBAccess.getDBAccessObject(detail3).saveObject(detail3);
            DBAccess.getDBAccessObject(detail4).saveObject(detail4);
            DBAccess.getDBAccessObject(detail5).saveObject(detail5);
            DBAccess.getDBAccessObject(detail6).saveObject(detail6);
            DBAccess.getDBAccessObject(detail5).saveObject(detail7);
            DBAccess.getDBAccessObject(detail6).saveObject(detail8);
            DBAccess.getDBAccessObject(detail9).saveObject(detail9);
            DBAccess.getDBAccessObject(detail10).saveObject(detail10);
            DBAccess.getDBAccessObject(detail6).saveObject(detail11);
            DBAccess.getDBAccessObject(detail5).saveObject(detail12);
            DBAccess.getDBAccessObject(detail6).saveObject(detail13);
            DBAccess.getDBAccessObject(detail9).saveObject(detail14);
            DBAccess.getDBAccessObject(detail10).saveObject(detail15);
            DBAccess.getDBAccessObject(detail10).saveObject(detail16);
            DBAccess.getDBAccessObject(detail10).saveObject(detail17);
        } catch (Exception e) {
            detail1 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(1);
            detail2 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(2);
            detail3 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(3);
            detail4 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(4);
            detail5 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(5);
            detail6 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(6);
            detail7 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(7);
            detail8 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(8);
            detail9 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(9);
            detail10 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(10);
            detail11 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(11);
            detail12 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(12);
            detail13 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(13);
            detail14 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(14);
            detail15 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(15);
            detail16 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(16);
            detail17 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(17);

        }

        System.out.println(detail1);
        System.out.println(detail2);
        System.out.println(detail3);
        System.out.println(detail4);
        System.out.println(detail5);
        System.out.println(detail6);
        System.out.println(detail7);
        System.out.println(detail8);
        System.out.println(detail9);
        System.out.println(detail10);
        System.out.println(detail11);
        System.out.println(detail12);
        System.out.println(detail13);
        System.out.println(detail14);
        System.out.println(detail15);
        System.out.println(detail16);
        System.out.println(detail17);

    }

    public static void maina(String[] args) throws Exception {
/*
        Account incomeAccount = (Account) DBAccess.getDBAccessObject(Account.class).getObjectById(10);
        AccountReport report = new AccountReport(incomeAccount);
*/
    /*    Currency peso = (Currency) DBAccess.getDBAccessObject(Currency.class).getObjectById(1);
        Account outcomeAccount = new Account("MostNewAcount", peso,  new Date("01/01/2016"), null);
        AccountHelper.saveAccount(outcomeAccount);
*/
        Account account = AccountHelper.getAccountById("15");
        AccountReport accountReport = new AccountReport(account);
        //AccountSadderHelper.getAccountSadderMonthlyHistoryForAccount(account);


        //MainController mainController = new MainController();
        //mainController.getCategoriesList();

        /*        Session session = DBAccess.getSession();
        DBAccessMovement dbAccessMovement = ((DBAccessMovement) DBAccess.getDBAccessObject(Movement.class));

        List<MovementRow> movements = dbAccessMovement.loadEveryMovementOrdered(true);
*/
        /*
        Account incomeAccount = (Account) DBAccess.getDBAccessObject(Account.class).getObjectById(10);
        Account outcomeAccount = (Account) DBAccess.getDBAccessObject(Account.class).getObjectById(11);
        Detail detail1 = (Detail) DBAccess.getDBAccessObject(Detail.class).getObjectById(1);
        Currency currency1 = (Currency) DBAccess.getDBAccessObject(Currency.class).getObjectById(1);

        Movement movement = new Movement(incomeAccount, outcomeAccount, Long.parseLong("50"), new Date(), detail1, currency1, "comment");
        movement.setMovementDate(DateHelper.setDayMonthYearHourMinute(movement.getMovementDate(), 31, 12, 2015, 23, 59));

        DBAccessAccountSadder dbAccessAccountSadder = (DBAccessAccountSadder) DBAccess.getDBAccessObject(AccountSadder.class);
        DBAccessMovement dbAccessMovement = (DBAccessMovement) DBAccess.getDBAccessObject(Movement.class);

        List<AccountSadder> origAccountSadders = dbAccessAccountSadder.getPosteriorDateAccountSadders(movement.getOrigAccount(), movement.getMovementDate());
        Long origLastAccountSadder = dbAccessAccountSadder.getAccountSadderForAccountByDate(incomeAccount, movement.getMovementDate());
        AccountSadder origNewaccountSadder = new AccountSadder(movement, movement.getOrigAccount(), origLastAccountSadder, origLastAccountSadder - movement.getAmount(), MovementStatus.EXECUTED);
        Long expectedOrigSadderAfterCorrection = dbAccessAccountSadder.getAccountSadderForAccount(movement.getOrigAccount()) - movement.getAmount();

        List<AccountSadder> destAccountSadders = dbAccessAccountSadder.getPosteriorDateAccountSadders(movement.getDestAccount(), movement.getMovementDate());
        Long destLastAccountSadder = dbAccessAccountSadder.getAccountSadderForAccountByDate(outcomeAccount, movement.getMovementDate());
        AccountSadder destNewaccountSadder = new AccountSadder(movement, movement.getDestAccount(), destLastAccountSadder, destLastAccountSadder + movement.getAmount(), MovementStatus.EXECUTED);
        Long expectedDestSadderAfterCorrection = dbAccessAccountSadder.getAccountSadderForAccount(movement.getDestAccount()) + movement.getAmount();


        if (origAccountSadders.size() > 0 || destAccountSadders.size() > 0) {
            if (origAccountSadders.size() > 0) {
                Long temporarySadder = origNewaccountSadder.getSadderAfterMovement();
                for (AccountSadder accountSadder : origAccountSadders) {
                    accountSadder.setSadderBeforeMovement(temporarySadder);
                    accountSadder.setSadderAfterMovement((accountSadder.getMovement().getOrigAccount() == accountSadder.getAccount()) ? accountSadder.getSadderBeforeMovement() - accountSadder.getMovement().getAmount() : accountSadder.getSadderBeforeMovement() + accountSadder.getMovement().getAmount());
                    temporarySadder = accountSadder.getSadderAfterMovement();
                }
                if (!origAccountSadders.get(origAccountSadders.size() - 1).getSadderAfterMovement().equals(expectedOrigSadderAfterCorrection)) {
                    new CoreException("Puta Madre Orig");
                }
            }
            origAccountSadders.add(origNewaccountSadder);


            if (destAccountSadders.size() > 0) {
                Long temporarySadder = destNewaccountSadder.getSadderAfterMovement();
                for (AccountSadder accountSadder : destAccountSadders) {
                    accountSadder.setSadderBeforeMovement(temporarySadder);
                    accountSadder.setSadderAfterMovement((accountSadder.getMovement().getOrigAccount() == accountSadder.getAccount()) ? accountSadder.getSadderBeforeMovement() - accountSadder.getMovement().getAmount() : accountSadder.getSadderBeforeMovement() + accountSadder.getMovement().getAmount());
                    temporarySadder = accountSadder.getSadderAfterMovement();
                }
                if (!destAccountSadders.get(destAccountSadders.size() - 1).getSadderAfterMovement().equals(expectedDestSadderAfterCorrection)) {
                    new CoreException("Puta Madre Dest");
                }
            }
            destAccountSadders.add(destNewaccountSadder);

            dbAccessMovement.saveMovement(movement, origAccountSadders, destAccountSadders);
        }
        else throw new CoreException("It's a new movement.");
*/

    }

}