package com.DAO.DBAccessObjects;

import com.DAO.DBAccess;
import com.domain.Database.AccountSadder;
import com.domain.Database.DBObject;
import com.domain.Database.Movement;
import com.domain.Report.MovementRow;
import com.util.Exception.CoreException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by M-Sem on 28/11/2015.
 */
public class DBAccessMovement extends DBAccessObject {

    final static Logger logger = Logger.getLogger(DBAccessMovement.class);

    private static DBAccessMovement dbAccessMovementInstance = null;

    public static DBAccessMovement getInstance() {
        if (dbAccessMovementInstance == null) {
            dbAccessMovementInstance = new DBAccessMovement();
        }
        return dbAccessMovementInstance;
    }

    private DBAccessMovement() {
        this.classObject = Movement.class;
    }

    @Override
    public Movement getObjectById(long id) throws CoreException {
        Session session = DBAccess.getSession();
        try {
            logger.info("Loading: Movement: " + id);
            DBObject returnObject = (DBObject) session.get(Movement.class, id);
            logger.info("Successfully Loaded: Movement: " + returnObject);
            return (Movement) returnObject;
        } catch (Exception e) {
            logger.error("Error Loading Account: " + id + ". Exception:" + e);
            throw new CoreException("Error Loading Account: " + id + ". Exception:" + e);
        }finally {
            DBAccess.closeSession(session);
        }
    }


    public Movement getLastSavedMovement() throws CoreException {
        Session session = null;
        List<Object> list = null;
        try {
            logger.info("Loading: Last Saved Movement.");
            session = DBAccess.getSession();
            Criteria cr = session.createCriteria(Movement.class)
                    .addOrder(Order.desc("id"));
            list = cr.list();
            if(list.size() > 0) {
                logger.info("Successfully Loaded: LastMovement: " + list.get(0));
                return (Movement) list.get(0);
            }
            logger.error("No Last Movement Found.");
            return null;

        } catch (Exception e) {
            logger.error("Error Loading LastMovement. Exception:" + e);
            throw new CoreException("Error Loading LastMovement. Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }


    public void saveObject(DBObject dbObject) throws CoreException {
        try {
            Movement movement = (Movement) dbObject;
            checkNotNull(getObjectById(movement.getId()));

        } catch (NullPointerException e) {
            logger.error("Error saving Movment: " + dbObject + ". to Save a new Movement you need to create AccountSadder before, invoking other method. Exception:" + e);
            throw new CoreException("Error saving Movment: " + dbObject + ". to Save a new Movement you need to create AccountSadder before, invoking other method. Exception:" + e);
        } catch (Exception e) {
            logger.error("Error Loading Movement: " + dbObject + ". Exception:" + e);
            throw new CoreException("Error Loading Movement: " + dbObject + ". Exception:" + e);
        }

        Session session = DBAccess.getSession();
        try {
            logger.info("Updating: Movement" + dbObject.getClass().getSimpleName() + " " + dbObject);
            session.getTransaction().begin();
            session.save(dbObject);
            session.getTransaction().commit();
            logger.info("Successfully Saved: " + dbObject.getClass().getSimpleName() + " " + dbObject);
        } catch (Exception e) {
            logger.error("Error Saving: " + dbObject.getClass().getSimpleName() + " " + dbObject + ". Exception:" + e);
            throw new CoreException("Error Saving: " + dbObject.getClass().getSimpleName() + " " + dbObject + ". Exception:" + e);
        }finally {
            DBAccess.closeSession(session);
        }
    }


    public void deleteMovement(Movement movement, List<AccountSadder> accountSadderList) throws CoreException {
        Session session = DBAccess.getSession();
        try {
            logger.info("Deleting new Movement: " + movement);
            session.getTransaction().begin();
            for (AccountSadder accountSadder : accountSadderList) {
                session.delete(accountSadder);
            }
            session.delete(movement);
            session.getTransaction().commit();
            DBAccess.closeSession(session);
            logger.info("Successfully Deleted Movement: " + movement + ". AccountSadder count: " + accountSadderList.size());
        } catch (Exception e) {
            logger.error("Error Deleting Movement: " + movement + ". Exception:" + e);
            throw new CoreException("Error Deleting Movement: " + movement + ". Exception:" + e);
        }finally {
            DBAccess.closeSession(session);
        }
    }


    public void saveMovement(Movement movement, AccountSadder origAccountSadder, AccountSadder destAccountSadder) throws CoreException {
        Session session = null;
        try {
            logger.info("Saving new Movement: " + movement);
            session = DBAccess.getSession();
            session.getTransaction().begin();
            session.save(movement);
            session.save(origAccountSadder);
            session.save(destAccountSadder);
            session.getTransaction().commit();
            logger.info("Successfully Saved new Movement: " + movement + ". Orig Account Sadder: " + origAccountSadder + ". Dest Account Sadder: " + destAccountSadder);
        } catch (Exception e) {
            logger.error("Error Saving new Movement: " + movement + ". Exception:" + e);
            throw new CoreException("Error Saving new Movement: " + movement + ". Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }

    public void saveMovement(Movement movement, List<AccountSadder> origAccountSadders, List<AccountSadder> destAccountSadders) throws CoreException {

        Session session = DBAccess.getSession();
        try {
            logger.info("Saving old Movement: " + movement);
            session.getTransaction().begin();
            session.save(movement);
            for (AccountSadder accountSadder : origAccountSadders) {
                session.save(session.merge(accountSadder));
            }
            for (AccountSadder accountSadder : destAccountSadders) {
                session.save(session.merge(accountSadder));
            }
            session.getTransaction().commit();
            logger.info("Successfully Saved new Movement: " + movement + ". Orig Account Sadder: " + origAccountSadders + ". Dest Account Sadder: " + destAccountSadders);
        } catch (Exception e) {
            logger.error("Error Saving new Movement: " + movement + ". Exception:" + e);
            throw new CoreException("Error Saving new Movement: " + movement + ". Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }

    public List<MovementRow> loadEveryMovementOrdered(Boolean orderDesc) throws CoreException {

        Session session = DBAccess.getSession();
        try {
            logger.info("Loading every Movement. Order Desc: " + orderDesc);

            String order = orderDesc ? "desc" : "asc";
            String query = " SELECT m,a,a2  " +
                    " from Movement m, AccountSadder a, AccountSadder a2 " +
                    " where m = a.movement and m = a2.movement " +
                    " and m.origAccount = a.account and m.destAccount = a2.account " +
                    " and m.origAccount <> m.destAccount " +
                    " order by m.movementDate %s, m.id %s ";

            query = String.format(query, order, order);
            Query q = session.createQuery(query).setFlushMode(FlushMode.ALWAYS);
            List<Object[]> resultList= (List<Object[]>)q.list();

            List<MovementRow> movementRows = new ArrayList<MovementRow>();
            for(Object[] resultSet : resultList){
                MovementRow movementRow = new MovementRow();
                movementRow.setId(((Movement)resultSet[0]).getId());
                movementRow.setMovementDate(((Movement)resultSet[0]).getMovementDate());
                movementRow.setOrigAccount(((Movement)resultSet[0]).getOrigAccount());
                movementRow.setOrigSadder(((AccountSadder)resultSet[1]).getSadderAfterMovement());
                movementRow.setDestAccount(((Movement)resultSet[0]).getDestAccount());
                movementRow.setDestSadder(((AccountSadder)resultSet[2]).getSadderAfterMovement());
                movementRow.setCurrency(((Movement)resultSet[0]).getCurrency());
                movementRow.setAmount(((Movement)resultSet[0]).getAmount());
                movementRow.setDetail(((Movement)resultSet[0]).getDetail());
                movementRow.setCommentary(((Movement)resultSet[0]).getCommentary());

                movementRows.add(movementRow);
            }

            logger.info("Successfully uploaded all movements. Row count: " + movementRows.size());
            return movementRows;
        } catch (Exception e) {
            logger.error("Error uploading all Movements. Exception:" + e);
            throw new CoreException("Error uploading all Movements. Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }

}
