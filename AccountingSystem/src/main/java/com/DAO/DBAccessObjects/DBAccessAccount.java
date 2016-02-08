package com.DAO.DBAccessObjects;

import com.DAO.DBAccess;
import com.domain.Database.*;
import com.util.Exception.CoreException;
import com.util.Helpers.MovementHelper;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBAccessAccount extends DBAccessObject {

    final static Logger logger = Logger.getLogger(DBAccessAccount.class);

    private static DBAccessAccount dbAccessAccountInstance = null;

    public static DBAccessAccount getInstance() {
        if (dbAccessAccountInstance == null) {
            dbAccessAccountInstance = new DBAccessAccount();
        }
        return dbAccessAccountInstance;
    }

    private DBAccessAccount() {
        this.classObject = Account.class;
    }


    @Override
    public DBObject getObjectById(long id) throws CoreException {
        Session session = null;
        try {
            logger.info("Loading:" + classObject.getSimpleName() + "Id: " + id);
            session = DBAccess.getSession();
            DBObject returnObject = (DBObject) session.get(classObject, id);
            logger.info("Successfully Loaded: " + classObject.getSimpleName() + ": " + returnObject);
            return returnObject;
        } catch (Exception e) {
            logger.error("Error Loading: " + classObject.getSimpleName() + ". Id: " + id + ". Exception:" + e);
            throw new CoreException("Error Loading: " + classObject.getSimpleName() + ". Id: " + id + ". Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }

    public List<Account> loadEveryAccountWithSadder() throws CoreException {
        List<Object> list = null;
        Session session = DBAccess.getSession();
        try {
            logger.info("Loading Every Account with Sadder.");
            Criteria cr = session.createCriteria(Account.class);
            list = cr.list();
            for (Object object : list) {
                Account account = (Account) object;
                account.setAccountSadder(((DBAccessAccountSadder) DBAccess.getDBAccessObject(AccountSadder.class)).getAccountSadderForAccount(account));
            }
            logger.info("Successfully Loaded Accounts with Sadder. Row count: " + list.size());
            return (List<Account>) (Object) list;
        } catch (Exception e) {
            logger.error("Error Loading Acounts. Exception:" + e);
            throw new CoreException("Error Loading Accounts with Sadder. Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }


    public Account getObjectByName(String name) throws CoreException {
        Session session = null;
        try {
            logger.info("Loading Account:" + "Name: " + name);
            session = DBAccess.getSession();
            Account account = (Account) session.createCriteria(Account.class)
                    .add(Restrictions.eq("name", name)).list().get(0);
            logger.info("Successfully Loaded Account: " + account);
            return account;
        } catch (Exception e) {
            logger.error("Error Loading Account: " + ". Name: " + name + ". Exception:" + e);
            throw new CoreException("Error Loading Account: " + ". Name: " + name + ". Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }



    public List<Object> loadEveryBankAccount() throws CoreException {
        List<Object> list = null;
        Session session = DBAccess.getSession();
        try {
            logger.info("Loading Every Account with Sadder.");
            Criteria cr = session.createCriteria(BankAccount.class);
            list = cr.list();
            for (Object object : list) {
                BankAccount account = (BankAccount) object;
                account.setAccountSadder(((DBAccessAccountSadder) DBAccess.getDBAccessObject(AccountSadder.class)).getAccountSadderForAccount(account));
            }
            logger.info("Successfully Loaded Accounts with Sadder. Row count: " + list.size());
            return list;
        } catch (Exception e) {
            logger.error("Error Loading Acounts. Exception:" + e);
            throw new CoreException("Error Loading Accounts with Sadder. Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }


    public List<Object> loadEveryPerson() throws CoreException {
        List<Object> list = null;
        Session session = DBAccess.getSession();
        try {
            logger.info("Loading Every Account with Sadder.");
            Criteria cr = session.createCriteria(Person.class);
            list = cr.list();
            for (Object object : list) {
                Person person = (Person) object;
                person.setAccountSadder(((DBAccessAccountSadder) DBAccess.getDBAccessObject(AccountSadder.class)).getAccountSadderForAccount(person));
            }
            logger.info("Successfully Loaded Accounts with Sadder. Row count: " + list.size());
            return list;
        } catch (Exception e) {
            logger.error("Error Loading Acounts. Exception:" + e);
            throw new CoreException("Error Loading Accounts with Sadder. Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }

    public List<Account> loadEveryAccount() throws CoreException {
        List<Account> list = null;
        Session session = DBAccess.getSession();
        try {
            logger.info("Loading Every Account with Sadder.");
            Criteria cr = session.createCriteria(Account.class).setFlushMode(FlushMode.ALWAYS);
            list = cr.list();
            for (Object object : list) {
                Account account = (Account) object;
                account.setAccountSadder(((DBAccessAccountSadder) DBAccess.getDBAccessObject(AccountSadder.class)).getAccountSadderForAccount(account));
            }
            logger.info("Successfully Loaded Accounts with Sadder. Row count: " + list.size());
            return list;
        } catch (Exception e) {
            logger.error("Error Loading Acounts. Exception:" + e);
            throw new CoreException("Error Loading Accounts with Sadder. Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }


    public void saveNewAccount(Account account, Movement movement) throws CoreException {
        Session session = DBAccess.getSession();
        try {
            logger.info("Saving Account: " + account);
            session.getTransaction().begin();
            session.save(account);
            MovementHelper.saveLastMovement(movement);
            session.getTransaction().commit();
            logger.info("Successfully Saved Account: " + account);
        } catch (Exception e) {
            logger.error("Error Saving Account: " + account+ ". Exception:" + e);
            throw new CoreException("Error Saving Account: " + account + ". Exception:" + e);
        }finally {
            DBAccess.closeSession(session);
        }
    }




}
