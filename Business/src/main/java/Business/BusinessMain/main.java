package Business.BusinessMain;

import Business.Helpers.DateHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class main {

    private static final Logger logger = LogManager.getLogger(main.class);


    public static void main(String[] args) throws Exception {
/*
        Date date1 = new Date();
        date1.setHours(date1.getHours()-1);
        //Thread.sleep(1000);
        System.out.println(DateHelper.getDetailedDifference(date1));

        Currency peso;
        Currency dolar;
        try {
            peso = new Currency("peso", "$", 1);
            dolar = new Currency("dolar", "u$d", 15);
            CurrencyMediator.saveCurrency(peso);
            CurrencyMediator.saveCurrency(dolar);
        } catch (Exception e) {
            peso = CurrencyMediator.getCurrencyById(1);
            dolar = CurrencyMediator.getCurrencyById(2);
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

            AccountHelper.saveAccount(bankAccount1);
            AccountHelper.saveAccount(bankAccount2);
            AccountHelper.saveAccount(bankAccount3);
            AccountHelper.saveAccount(bankAccount4);
            AccountHelper.saveAccount(bankAccount5);
            AccountHelper.saveAccount(bankAccount6);
            AccountHelper.saveAccount(bankAccount7);
        } catch (Exception e) {
            bankAccount1 = (BankAccount) AccountMediator.getAccountById(Long.parseLong("1"));
            bankAccount2 = (BankAccount) AccountMediator.getAccountById(Long.parseLong("2"));
            bankAccount3 = (BankAccount) AccountMediator.getAccountById(Long.parseLong("3"));
            bankAccount4 = (BankAccount) AccountMediator.getAccountById(Long.parseLong("4"));
            bankAccount5 = (BankAccount) AccountMediator.getAccountById(Long.parseLong("5"));
            bankAccount6 = (BankAccount) AccountMediator.getAccountById(Long.parseLong("6"));
            bankAccount7 = (BankAccount) AccountMediator.getAccountById(Long.parseLong("7"));


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
            AccountHelper.saveAccount(person1);
            AccountHelper.saveAccount(person2);
        } catch (Exception e) {
            person1 = (Person) AccountMediator.getAccountById(Long.parseLong("8"));
            person2 = (Person) AccountMediator.getAccountById(Long.parseLong("9"));
        }

        System.out.println(person1);
        System.out.println(person2);


        Account incomeAccount;
        Account outcomeAccount;

        try {
            incomeAccount = new Account("Incomes", peso,  new Date("11/17/2015"), null);
            outcomeAccount = new Account("Outcomes", peso,  new Date("11/17/2015"), null);
            AccountHelper.saveAccount(incomeAccount);
            AccountHelper.saveAccount(outcomeAccount);
        } catch (Exception e) {
            incomeAccount = AccountMediator.getAccountById(Long.parseLong("10"));
            outcomeAccount = AccountMediator.getAccountById(Long.parseLong("11"));
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

            CategoryMediator.saveCategory(category);
            CategoryMediator.saveCategory(category2);
            CategoryMediator.saveCategory(category3);
            CategoryMediator.saveCategory(category4);
            CategoryMediator.saveCategory(category5);

        } catch (Exception e) {
            category = CategoryMediator.getCategoryById(1);
            category2 = CategoryMediator.getCategoryById(2);
            category3 = CategoryMediator.getCategoryById(3);
            category4 = CategoryMediator.getCategoryById(4);
            category5 = CategoryMediator.getCategoryById(5);
        }

        System.out.println(category);
        System.out.println(category2);
        System.out.println(category3);
        System.out.println(category4);
        System.out.println(category5);


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


            DetailMediator.saveDetail(detail1);
            DetailMediator.saveDetail(detail2);
            DetailMediator.saveDetail(detail3);
            DetailMediator.saveDetail(detail4);
            DetailMediator.saveDetail(detail5);
            DetailMediator.saveDetail(detail6);
            DetailMediator.saveDetail(detail7);
            DetailMediator.saveDetail(detail8);
            DetailMediator.saveDetail(detail9);
            DetailMediator.saveDetail(detail10);
            DetailMediator.saveDetail(detail11);
            DetailMediator.saveDetail(detail12);
            DetailMediator.saveDetail(detail13);
            DetailMediator.saveDetail(detail14);
            DetailMediator.saveDetail(detail15);
            DetailMediator.saveDetail(detail16);
            DetailMediator.saveDetail(detail17);
        } catch (Exception e) {
            detail1 = DetailMediator.getDetailById(1);
            detail2 = DetailMediator.getDetailById(2);
            detail3 = DetailMediator.getDetailById(3);
            detail4 = DetailMediator.getDetailById(4);
            detail5 = DetailMediator.getDetailById(5);
            detail6 = DetailMediator.getDetailById(6);
            detail7 = DetailMediator.getDetailById(7);
            detail8 = DetailMediator.getDetailById(8);
            detail9 = DetailMediator.getDetailById(9);
            detail10 =DetailMediator.getDetailById(10);
            detail11 =DetailMediator.getDetailById(11);
            detail12 =DetailMediator.getDetailById(12);
            detail13 =DetailMediator.getDetailById(13);
            detail14 =DetailMediator.getDetailById(14);
            detail15 =DetailMediator.getDetailById(15);
            detail16 =DetailMediator.getDetailById(16);
            detail17 =DetailMediator.getDetailById(17);

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
*/
    }

    public static void maina(String[] args) throws Exception {
/*
        Account incomeAccount = (Account) DBAccess.getDBAccessObject(Account.class).getAccountById(10);
        AccountReport report = new AccountReport(incomeAccount);
*/
    /*    Currency peso = (Currency) DBAccess.getDBAccessObject(Currency.class).getAccountById(1);
        Account outcomeAccount = new Account("MostNewAcount", peso,  new Date("01/01/2016"), null);
        AccountHelper.saveAccount(outcomeAccount);
*/
     /*   Account account = AccountHelper.getAccountById("15");
        AccountReport accountReport = new AccountReport(account);*/
        //AccountSadderHelper.getAccountSadderMonthlyHistoryForAccount(account);


        //MainController mainController = new MainController();
        //mainController.getCategoriesList();

        /*        Session session = DBAccess.getSession();
        MovementDAO dbAccessMovement = ((MovementDAO) DBAccess.getDBAccessObject(Movement.class));

        List<MovementRow> movements = dbAccessMovement.loadEveryMovementOrdered(true);
*/
        /*
        Account incomeAccount = (Account) DBAccess.getDBAccessObject(Account.class).getAccountById(10);
        Account outcomeAccount = (Account) DBAccess.getDBAccessObject(Account.class).getAccountById(11);
        Detail detail1 = (Detail) DBAccess.getDBAccessObject(Detail.class).getAccountById(1);
        Currency currency1 = (Currency) DBAccess.getDBAccessObject(Currency.class).getAccountById(1);

        Movement movement = new Movement(incomeAccount, outcomeAccount, Long.parseLong("50"), new Date(), detail1, currency1, "comment");
        movement.setMovementDate(DateHelper.setDayMonthYearHourMinute(movement.getMovementDate(), 31, 12, 2015, 23, 59));

        AccountSadderDAO dbAccessAccountSadder = (AccountSadderDAO) DBAccess.getDBAccessObject(AccountSadder.class);
        MovementDAO dbAccessMovement = (MovementDAO) DBAccess.getDBAccessObject(Movement.class);

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