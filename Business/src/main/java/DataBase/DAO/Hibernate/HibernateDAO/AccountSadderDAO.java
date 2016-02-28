package DataBase.DAO.Hibernate.HibernateDAO;

import Business.Exceptions.CoreException;
import Business.Exceptions.DBAccessException;
import Business.MessageConstants.LoggingMessageConstants;
import Business.domainObjects.ReportElements.MonthlyAccountSadder;
import Business.domainObjects.DBObjects.Account;
import Business.domainObjects.DBObjects.AccountSadder;
import Business.domainObjects.DBObjects.Movement;
import DataBase.DAO.DBManager;
import DataBase.DAO.InterfacesDAO.AbstractDAOInterface;
import DataBase.DAO.InterfacesDAO.AccountSadderDAOInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AccountSadderDAO implements AccountSadderDAOInterface, AbstractDAOInterface {

    private static final Logger logger = LogManager.getLogger(AccountSadderDAO.class);

    private DBManager dbManager = null;
    private Class<?> classObject = null;


    private static AbstractDAOInterface DAOObject = null;

    public static AbstractDAOInterface getInstance(DBManager dbManager) {
        if (DAOObject == null)
            DAOObject = new AccountSadderDAO(dbManager);
        return DAOObject;
    }

    private AccountSadderDAO(DBManager dbManager) {
        this.dbManager = dbManager;
        this.classObject = AccountSadder.class;
    }


    @Override
    public AccountSadder getAccountSadderById(long id) throws CoreException {
        logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_BEFORE, "AccountSadder", id));
        Session session = dbManager.getSession();
        try {
            AccountSadder accountSadder = (AccountSadder) session.get(AccountSadder.class, id);
            logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_SUCCESS, "AccountSadder", accountSadder.toPrint()));
            return accountSadder;
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_ERROR, "AccountSadder", id);
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);

        }
    }

    @Override
    public Double getAccountSadderForAccount(Account account) throws CoreException {
        logger.info("Loading AccountSadder for Account: " + account);
        Session session = dbManager.getSession();
        try {
            Query q = session.createQuery(
                    "SELECT s from AccountSadder s WHERE s.account= :account order by s.movement.movementDate desc, s.movement.id desc");
            q.setParameter("account", account);
            q.setMaxResults(1);
            List<AccountSadder> accountSadderList = q.list();

            if (accountSadderList.size() < 1) {
                logger.info("No Sadder for Account: " + account.toPrint());
                return Double.valueOf(0);
            }
            logger.info("Successfully Loaded AccountSadder for Account: " + account.toPrint() + ". Row Count; " + accountSadderList.size());
            return accountSadderList.get(0).getSadderAfterMovement();
        } catch (Exception e) {
            String error = "Error Loading AccountSadder for Account: " + account.toPrint();
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }


    @Override
    public Double getAccountSadderForAccountByDate(Account account, Date date) throws CoreException {
        logger.info("Loading AccountSadder value for Account: " + account + ", For Date: " + date);
        Session session = dbManager.getSession();
        try {
            Query q = session.createQuery(
                    "SELECT s from AccountSadder s WHERE s.account= :account AND s.movement.movementDate<=:date order by s.movement.movementDate desc, s.movement.id desc");
            q.setParameter("account", account);
            q.setParameter("date", date);
            q.setMaxResults(1);
            List<AccountSadder> accountSadderList = q.list();

            if (accountSadderList.size() < 1) {
                logger.error("Initial movement for Account: " + account.toPrint() + ", was after requested: " + date);
                return Double.parseDouble(null);
            }
            logger.info("Successfully Loaded AccountSadder value for Account: " + account.toPrint() + ", for Date: " + date + ". Row Count: " + accountSadderList.size());
            return accountSadderList.get(0).getSadderAfterMovement();
        } catch (Exception e) {
            String error = "Error Loading AccountSadder value for Account: " + account.toPrint() + ", for Date: " + date;
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }


    @Override
    public List<MonthlyAccountSadder> getAccountSadderMonthlyHistoryForAccount(Account account) throws DBAccessException {
        logger.info("Loading AccountSadder GroupedMonthly for Account: " + account);
        Session session = dbManager.getSession();
        try {
            String sql = String.format(
                    "  SELECT extract(year from m.movementDate) as Year, extract(month from m.movementDate) as Month, " +
                            " (select a.sadderBeforeMovement from AccountSadder a where a.account_id = s.account_id and a.movement_id = (select m2.id from movement m2 where m2.destAccount_id <> m2.origAccount_id and (m2.destAccount_id = a.account_id or m2.origAccount_id = a.account_id ) and m2.movementDate = min(m.movementDate) order by m2.id asc LIMIT 1 )) as BeforeSadder , " +
                            " (select a.sadderAfterMovement  from AccountSadder a where a.account_id = s.account_id and a.movement_id = (select m2.id from movement m2 where m2.destAccount_id <> m2.origAccount_id and (m2.destAccount_id = a.account_id or m2.origAccount_id = a.account_id ) and m2.movementDate = max(m.movementDate) order by m2.id desc LIMIT 1 )) as AfterSadder , " +
                            " count(*) as MovementCount " +
                            " from AccountSadder s, Movement m WHERE s.account_id = '%s' and s.movement_id = m.id and m.origAccount_id <> m.destAccount_id " +
                            " group by extract(year from m.movementDate), extract(month from m.movementDate) " +
                            " order by extract(year from m.movementDate) asc, extract(month from m.movementDate) asc; ", String.valueOf(account.getId()));
            List<Object> accountSadderList = session.createSQLQuery(sql).list();

            List<MonthlyAccountSadder> monthlyAccountSadders = new ArrayList<MonthlyAccountSadder>();
            for (Object accountSadder : accountSadderList) {
                Object[] object = (Object[]) accountSadder;
                MonthlyAccountSadder monthlyAccountSadder = new MonthlyAccountSadder((Integer) object[0], (Integer) object[1], ((Double) object[2]), ((Double) object[3]), ((BigInteger) object[4]).intValue());
                monthlyAccountSadders.add(monthlyAccountSadder);
            }

            if (accountSadderList.size() < 1) {
                logger.error("No Sadder found for Account: " + account.toPrint());
            } else {
                logger.info("Successfully Loaded AccountSadder GroupedMonthly for Account: " + account.toPrint() + ". Row Count: " + accountSadderList.size());
            }
            return monthlyAccountSadders;
        } catch (Exception e) {
            String error = "Error Loading AccountSadder value for Account: " + account.toPrint();
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }

    }

    @Override
    public List<AccountSadder> getPosteriorDateAccountSadders(Account account, Date date) throws CoreException {
        logger.info("Loading AccountSadders for Account: " + account + ", After: " + date);
        Session session = dbManager.getSession();
        try {
            Query q = session.createQuery(
                    "SELECT s from AccountSadder s WHERE s.account= :account AND s.movement.movementDate>:date order by s.movement.movementDate asc, s.movement.id asc");
            q.setParameter("account", account);
            q.setParameter("date", date);
            List<AccountSadder> accountSadderList = q.list();

            if (accountSadderList.size() < 1) {
                logger.info("No Sadder for Account: " + account.toPrint() + ", after Date:" + date);
                new ArrayList<AccountSadder>();
            }
            logger.info("Successfully Loaded AccountSadder for Account: " + account.toPrint() + ", After: " + date + " .Row Count: " + accountSadderList.size());
            return accountSadderList;
        } catch (Exception e) {
            String error = "Errpr Loading AccountSadder for Account: " + account.toPrint() + ", After: " + date;
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }


    @Override
    public List<AccountSadder> getMovementsAccountSadder(Movement movement) throws CoreException {
        logger.info("Loading AccountSadders For Movement: " + movement);
        Session session = dbManager.getSession();
        try {
            Criteria cr = session.createCriteria(AccountSadder.class)
                    .add(Restrictions.eq("movement", movement));
            List<AccountSadder> accountSadderList = cr.list();
            logger.info("Successfully Loaded AccountSadders for Movement: " + movement.toPrint() + ". Row count: " + accountSadderList.size());
            return accountSadderList;
        } catch (Exception e) {
            String error = "Error Loading AccountSadder for movement: " + movement.toPrint();
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }

    @Override
    public List<AccountSadder> getAccountSaddersForAccount(Account account) throws CoreException {
        logger.info("Loading AccountSadders, Ordered by Desc Date, For Account: " + account);
        List<AccountSadder> list = null;
        Session session = null;
        try {
            session = dbManager.getSession();
            Criteria cr = session.createCriteria(AccountSadder.class)
                    .createAlias("movement", "movement")
                    .add(Restrictions.eq("account", account))
                    .addOrder(Order.desc("movement.movementDate"));
            list = (List<AccountSadder>) cr.list();
            logger.info("Successfully Loaded AccountSadders, Ordered by Desc Date, for Account: " + account.toPrint() + " . Row count: " + list.size());
            return list;
        } catch (Exception e) {
            String error = "Error Loading AccountsSadder, Ordered by Desc Date, for Account: " + account.toPrint();
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }
}