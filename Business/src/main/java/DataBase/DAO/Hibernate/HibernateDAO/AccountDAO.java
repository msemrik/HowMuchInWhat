package DataBase.DAO.Hibernate.HibernateDAO;

import Business.Exceptions.DBAccessException;
import Business.Helpers.MovementHelper;
import Business.MessageConstants.LoggingMessageConstants;
import Business.ObjectMediators.AccountSadderMediator;
import DataBase.DAO.DBManager;
import DataBase.DAO.InterfacesDAO.AbstractDAOInterface;
import DataBase.DAO.InterfacesDAO.AccountDAOInterface;
import Business.Exceptions.CoreException;
import Business.domainObjects.DBObjects.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class AccountDAO implements AccountDAOInterface, AbstractDAOInterface {

    private static final Logger logger = LogManager.getLogger(AccountDAO.class);
    private DBManager dbManager = null;
    private Class<?> classObject = null;


    private static AbstractDAOInterface DAOObject = null;

    public static AbstractDAOInterface getInstance(DBManager dbManager) {
        if (DAOObject == null)
            DAOObject = new AccountDAO(dbManager);
        return DAOObject;
    }

    private AccountDAO(DBManager dbManager) {
        this.dbManager = dbManager;
        this.classObject = Account.class;
    }

    @Override
    public Account getAccountById(long id) throws CoreException {
        logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_BEFORE, "Account", id));
        Session session = null;
        try {
            session = dbManager.getSession();
            Account account = (Account) session.get(classObject, id);
            logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_SUCCESS, "Account", account.toPrint()));
            return account;
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_ERROR, "Account", id);
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }

    @Override
    public List<Account> loadEveryAccountWithSadder() throws CoreException {
        logger.info(String.format(LoggingMessageConstants.GET_EVERY_OBJECT_BEFORE, "Account with Sadder"));
        List<Account> list = null;
        Session session = dbManager.getSession();
        try {
            Criteria cr = session.createCriteria(Account.class);
            list = cr.list();
            for (Object object : list) {
                Account account = (Account) object;
                account.setAccountSadder(AccountSadderMediator.getAccountSadderForAccount(account));
            }
            logger.info(String.format(LoggingMessageConstants.GET_EVERY_OBJECT_SUCCESS, "Account, with Sadder", list.size()));
            return list;
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.GET_EVERY_OBJECT_ERROR, "Account, with Sadder");
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }


    @Override
    public Account getAccountByName(String name) throws CoreException {
        logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_NAME_BEFORE, "Account", name));
        Session session = null;
        try {
            session = dbManager.getSession();
            Account account = (Account) session.createCriteria(Account.class)
                    .add(Restrictions.eq("name", name)).list().get(0);
            logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_NAME_SUCCESS, "Account", account.toPrint()));
            return account;
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.GET_OBJECT_BY_NAME_ERROR, "Account", name);
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }


    @Override
    public List<BankAccount> loadEveryBankAccount() throws CoreException {
        logger.info(String.format(LoggingMessageConstants.GET_EVERY_OBJECT_BEFORE, "BackAccount with Sadder"));
        List<BankAccount> list = null;
        Session session = dbManager.getSession();
        try {
            Criteria cr = session.createCriteria(BankAccount.class);
            list = cr.list();
            for (Object object : list) {
                BankAccount account = (BankAccount) object;
                account.setAccountSadder(AccountSadderMediator.getAccountSadderForAccount(account));
            }
            logger.info(String.format(LoggingMessageConstants.GET_EVERY_OBJECT_SUCCESS, "BackAccount with Sadder", list.size()));
            return list;
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.GET_EVERY_OBJECT_ERROR, "BackAccount with Sadder");
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }


    @Override
    public List<Person> loadEveryPerson() throws CoreException {
        logger.info(String.format(LoggingMessageConstants.GET_EVERY_OBJECT_BEFORE, "Person with Sadder"));
        List<Person> list = null;
        Session session = dbManager.getSession();
        try {
            Criteria cr = session.createCriteria(Person.class);
            list = cr.list();
            for (Object object : list) {
                Person person = (Person) object;
                person.setAccountSadder(AccountSadderMediator.getAccountSadderForAccount(person));
            }
            logger.info(String.format(LoggingMessageConstants.GET_EVERY_OBJECT_SUCCESS, "Person with Sadder", list.size()));
            return (List<Person>) (List<?>) list;
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.GET_EVERY_OBJECT_ERROR, "Person with Sadder");
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }


    @Override
    public void saveNewAccount(Account account, Movement movement) throws CoreException {
        logger.info("Saving Account: " + account + ", with InitialMovement: " + movement);
        Session session = dbManager.getSession();
        try {
            session.getTransaction().begin();
            session.save(account);
            MovementHelper.saveLastMovement(movement);
            session.getTransaction().commit();
            logger.info("Successfully Saved Account: " + account.toPrint() + ", with InitialMovement: " + movement.toPrint());
        } catch (Exception e) {
            String error = "Error Saving Account: " + account.toPrint() + ", with InitialMovement: " + movement.toPrint();
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }


}
