package com.DAO.DBAccessObjects;

import com.DAO.DBAccess;
import com.domain.Database.*;
import com.domain.Report.AccountSadderGraph;
import com.domain.Report.MonthlyAccountSadder;
import com.util.Exception.CoreException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by M-Sem on 01/12/2015.
 */
public class DBAccessAccountSadder extends DBAccessObject {

    final static Logger logger = Logger.getLogger(DBAccessAccountSadder.class);

    private static DBAccessAccountSadder dbAccessAccountSadderInstance = null;

    public static DBAccessAccountSadder getInstance() {
        if (dbAccessAccountSadderInstance == null) {
            dbAccessAccountSadderInstance = new DBAccessAccountSadder();
        }
        return dbAccessAccountSadderInstance;
    }

    private DBAccessAccountSadder() {
        this.classObject = AccountSadder.class;
    }

    @Override
    public DBObject getObjectById(long id) throws CoreException {
        Session session = DBAccess.getSession();
        try {
            logger.info("Loading: AccountSadder: " + id);
            DBObject returnObject = (DBObject) session.get(AccountSadder.class, id);
            logger.info("Successfully Loaded: AccountSadder: " + returnObject);
            return (AccountSadder) returnObject;
        } catch (Exception e) {
            logger.error("Error Loading AccountSadder: " + id + ". Exception:" + e);
            throw new CoreException("Error Loading AccountSadder: " + id + ". Exception:" + e);
        } finally {
            DBAccess.closeSession(session);

        }
    }

    public Long getAccountSadderForAccount(Account account) throws CoreException {
        Session session = DBAccess.getSession();
        try {
            logger.info("Loading: AccountSadder: " + account);
            Query q = session.createQuery(
                    "SELECT s from AccountSadder s WHERE s.account= :account order by s.movement.movementDate desc, s.movement.id desc");
            q.setParameter("account", account);
            q.setMaxResults(1);
            List<AccountSadder> accountSadderList = q.list();

            if (accountSadderList.size() < 1) {
                logger.info("Error, no Sadder for Account: " + account);
                return Long.valueOf(0);
            }
            logger.info("Successfully Loaded: AccountSadder: " + account);
            return accountSadderList.get(0).getSadderAfterMovement();
        } catch (Exception e) {
            logger.error("Error Loading AccountSadder: " + account + ". Exception:" + e);
            throw new CoreException("Error Loading AccountSadder: " + account + ". Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }


    public long getAccountSadderForAccountByDate(Account account, Date date) throws CoreException {
        Session session = DBAccess.getSession();
        try {
            logger.info("Loading: AccountSadder for Account: " + account + ", For: " + date);
            Query q = session.createQuery(
                    "SELECT s from AccountSadder s WHERE s.account= :account AND s.movement.movementDate<=:date order by s.movement.movementDate desc, s.movement.id desc");
            q.setParameter("account", account);
            q.setParameter("date", date);
            q.setMaxResults(1);
            List<AccountSadder> accountSadder = q.list();

            if (accountSadder.size() < 1) {
                logger.error("Error, initial movement for Account: " + account + ", was after: " + date);
                return Long.parseLong(null);
            }
            logger.info("Successfully Loaded: AccountSadder: " + account);
            return accountSadder.get(0).getSadderAfterMovement();
        } catch (Exception e) {
            logger.error("Error Loading AccountSadder: " + account + ". Exception:" + e);
            throw new CoreException("Error Loading AccountSadder: " + account + ". Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }


    public List<MonthlyAccountSadder> getAccountSadderMonthlyHistoryForAccount(Account account) throws CoreException {
        Session session = DBAccess.getSession();
        try {
            logger.info("Loading: AccountSaddersHistory for Account: " + account);
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
                MonthlyAccountSadder monthlyAccountSadder = new MonthlyAccountSadder((Integer) object[0], (Integer) object[1], ((BigInteger) object[2]).longValue(), ((BigInteger) object[3]).longValue(), ((BigInteger) object[4]).intValue());
                monthlyAccountSadders.add(monthlyAccountSadder);
            }

            if (accountSadderList.size() < 1) {
                logger.error("Error, no Sadder for Account: " + account);
            } else {
                logger.info("Successfully Loaded: AccountSadder: " + account);
            }

            return monthlyAccountSadders;
        } catch (Exception e) {
            logger.error("Error Loading AccountSadderHistory For Account: " + account + ". Exception:" + e);
            throw new CoreException("Error Loading AccountSadderHistory For Account: " + account + ". Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }

    public List<AccountSadder> getPosteriorDateAccountSadders(Account account, Date date) throws CoreException {
        Session session = DBAccess.getSession();
        try {
            logger.info("Loading: AccountSadder for Account: " + account + ", For: " + date);
            Query q = session.createQuery(
                    "SELECT s from AccountSadder s WHERE s.account= :account AND s.movement.movementDate>:date order by s.movement.movementDate asc, s.movement.id asc");
            q.setParameter("account", account);
            q.setParameter("date", date);
            List<AccountSadder> accountSadder = q.list();

            if (accountSadder.size() < 1) {
                logger.info("Error, no Sadder for Account: " + account + ", after Date:" + date);
                new ArrayList<AccountSadder>();
            }
            logger.info("Successfully Loaded: AccountSadder: " + account);
            return accountSadder;
        } catch (Exception e) {
            logger.error("Error Loading AccountSadder: " + account + ". Exception:" + e);
            throw new CoreException("Error Loading AccountSadder: " + account + ". Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }


    public List<AccountSadder> getMovementsAccountSadder(Movement movement) throws CoreException {
        Session session = DBAccess.getSession();
        try {
            logger.info("Loading: AccountSadders for movement: " + movement);
            Criteria cr = session.createCriteria(AccountSadder.class)
                    .add(Restrictions.eq("movement", movement));
            List<AccountSadder> accountSadderList = cr.list();
            logger.info("Successfully Loaded: AccountSadders for movement: " + movement + ". Row count: " + accountSadderList.size());
            return accountSadderList;
        } catch (Exception e) {
            logger.error("Error Loading AccountSadder for movement: " + movement + ". Exception:" + e);
            throw new CoreException("Error Loading AccountSadder for movement: " + movement + ". Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }

    public List<AccountSadder> getAccountSaddersForAccount(Account account) throws CoreException {
        List<AccountSadder> list = null;
        Session session = null;
        try {
            logger.info("Loading AccountSadders, For Account, Ordered by Desc Date");
            session = DBAccess.getSession();
            Criteria cr = session.createCriteria(AccountSadder.class)
                    .createAlias("movement", "movement")
                    .add(Restrictions.eq("account", account))
                    .addOrder(Order.desc("movement.movementDate"));
            list = (List<AccountSadder>) cr.list();
            logger.info("Successfully Loaded AccountSadders, Ordered by Desc Date. Row count: " + list.size());
            return list;
        } catch (Exception e) {
            logger.error("Error Loading AccountsSadder, Ordered by Desc Date. Exception:" + e);
            throw new CoreException("Error Loading AccountsSadder, Ordered by Desc Date. Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }
/*
    public List<AccountSadder> loadAccountSaddersOrderedByDescDate() throws CoreException {
        List<AccountSadder> list = null;
        Session session = null;
        try {
            logger.info("Loading AccountSadders, Ordered by Desc Date");
            session = DBAccess.getSession();
            Criteria cr = session.createCriteria(AccountSadder.class)
                    .addOrder(Order.desc("movement.movementDate"));
            list = (List<AccountSadder>) cr.list();
            logger.info("Successfully Loaded AccountSadders, Ordered by Desc Date. Row count: " + list.size());
            return list;
        } catch (Exception e) {
            logger.error("Error Loading AccountsSadder, Ordered by Desc Date. Exception:" + e);
            throw new CoreException("Error Loading AccountsSadder, Ordered by Desc Date. Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }

    public List<AccountSadder> loadEveryAccountSadderOrderedByMovement(Boolean orderDesc) throws CoreException {
        Session session = DBAccess.getSession();
        try {
            logger.info("Loading: every Account Sadder Ordered by Movement.");
            String order = orderDesc ? "desc" : "asc";
            String query = "SELECT s from AccountSadder s order by s.movement.movementDate %s, s.movement.id %s";
            String.format(query, order, order);
            Query q = session.createQuery(query);
            List<AccountSadder> accountSadderList= q.list();


            if (accountSadderList.size() < 1) {
                logger.error("Error, no Account Sadder Found.");
            } else {
                logger.info("Successfully Loaded every Account Sadder Ordered by Movement.");
            }

            return accountSadderList;
        } catch (Exception e) {
            logger.error("Error Loading every Account Sadder Ordered by Movement. Exception:" + e);
            throw new CoreException("Error Loading every Account Sadder Ordered by Movement. Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }
 */
}