package DataBase.DAO.Hibernate.HibernateDAO;

import Business.Exceptions.CoreException;
import Business.Exceptions.DBAccessException;
import Business.MessageConstants.LoggingMessageConstants;
import Business.domainObjects.ReportElements.MovementRow;
import Business.domainObjects.DBObjects.AccountSadder;
import Business.domainObjects.DBObjects.Movement;
import DataBase.DAO.DBManager;
import DataBase.DAO.InterfacesDAO.AbstractDAOInterface;
import DataBase.DAO.InterfacesDAO.MovementDAOInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.List;

public class MovementDAO implements MovementDAOInterface, AbstractDAOInterface {

    private static final Logger logger = LogManager.getLogger(MovementDAO.class);
    private DBManager dbManager = null;
    private Class<?> classObject = null;


    private static AbstractDAOInterface DAOObject = null;

    public static AbstractDAOInterface getInstance(DBManager dbManager) {
        if (DAOObject == null)
            DAOObject = new MovementDAO(dbManager);
        return DAOObject;
    }

    private MovementDAO(DBManager dbManager) {
        this.dbManager = dbManager;
        this.classObject = Movement.class;
    }

    @Override
    public Movement getMovementById(long id) throws CoreException {
        logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_BEFORE, "Movement", id));
        Session session = dbManager.getSession();
        try {
            Movement movement = (Movement) session.get(Movement.class, id);
            logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_SUCCESS, "Movement", movement.toPrint()));
            return movement;
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_ERROR, "Movement", id);
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }


    @Override
    public Movement getLastSavedMovement() throws CoreException {
        logger.info("Loading Last Saved Movement.");
        Session session = null;
        List<Movement> list = null;
        try {
            session = dbManager.getSession();
            Criteria cr = session.createCriteria(Movement.class)
                    .addOrder(Order.desc("id"));
            list = cr.list();
            if (list.size() > 0) {
                logger.info("Successfully Loaded LastMovement: " + list.get(0).toPrint());
                return list.get(0);
            }
            logger.error("No Last Movement Found.");
            return null;

        } catch (Exception e) {
            String error = "Error Loading LastMovement.";
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }

    @Override
    public void deleteMovement(Movement movement, List<AccountSadder> accountSadderList) throws CoreException {
        logger.info("Deleting new Movement: " + movement.toPrint());
        Session session = dbManager.getSession();
        try {
            session.getTransaction().begin();
            for (AccountSadder accountSadder : accountSadderList) {
                session.delete(accountSadder);
            }
            session.delete(movement);
            session.getTransaction().commit();
            logger.info("Successfully Deleted Movement: " + movement.toPrint() + ". AccountSadder count: " + accountSadderList.size());
        } catch (Exception e) {
            String error = "Error Deleting Movement: " + movement.toPrint();
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }

    @Override
    public void deleteMovement(Movement movement, List<AccountSadder> accountSaddersToDelete, List<AccountSadder> accountOrigSadderToUpdate, List<AccountSadder> accountDestSadderToUpdate) throws CoreException{
        logger.info("Deleting Middle Movement: " + movement.toPrint());
        Session session = dbManager.getSession();
        try {
            session.getTransaction().begin();
            for (AccountSadder accountSadder : accountSaddersToDelete) {
                session.delete(accountSadder);
            }
            for (AccountSadder accountSadder : accountOrigSadderToUpdate) {
                session.save(session.merge(accountSadder));
            }
            for (AccountSadder accountSadder : accountDestSadderToUpdate) {
                session.save(session.merge(accountSadder));
            }
            session.delete(movement);
            session.getTransaction().commit();
            logger.info("Successfully Deleted Middle Movement: " + movement.toPrint() + ". AccountSadder Updated: " + accountOrigSadderToUpdate.size());
        } catch (Exception e) {
            String error = "Error Deleting Movement: " + movement.toPrint();
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }

    }



    @Override
    public void saveMovement(Movement movement, AccountSadder origAccountSadder, AccountSadder destAccountSadder) throws CoreException {
        logger.info("Saving Last Movement: " + movement.toPrint());
        Session session = null;
        try {
            session = dbManager.getSession();
            session.getTransaction().begin();
            session.save(movement);
            session.save(origAccountSadder);
            session.save(destAccountSadder);
            session.getTransaction().commit();
            logger.info("Successfully Saved Last Movement: " + movement.toPrint());
        } catch (Exception e) {
            String error = "Error Saving Last Movement: " + movement.toPrint();
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }

    @Override
    public void saveMovement(Movement movement, List<AccountSadder> origAccountSadders, List<AccountSadder> destAccountSadders) throws CoreException {
        logger.info("Saving Middle Movement: " + movement.toPrint());
        Session session = dbManager.getSession();
        try {
            session.getTransaction().begin();
            session.save(movement);
            for (AccountSadder accountSadder : origAccountSadders) {
                session.save(session.merge(accountSadder));
            }
            for (AccountSadder accountSadder : destAccountSadders) {
                session.save(session.merge(accountSadder));
            }
            session.getTransaction().commit();
            logger.info("Successfully Saved Middle Movement: " + movement.toPrint());
        } catch (Exception e) {
            String error = "Error Saving Middle Movement: " + movement.toPrint();
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }

    @Override
    public List<MovementRow> loadEveryMovementOrdered(Boolean orderDesc) throws CoreException {
        logger.info("Loading every Movement. Order Desc: " + orderDesc);
        Session session = dbManager.getSession();
        try {
            String order = orderDesc ? "desc" : "asc";
            String query = " SELECT m,a,a2  " +
                    " from Movement m, AccountSadder a, AccountSadder a2 " +
                    " where m = a.movement and m = a2.movement " +
                    " and m.origAccount = a.account and m.destAccount = a2.account " +
                    " and m.origAccount <> m.destAccount " +
                    " order by m.movementDate %s, m.id %s ";

            query = String.format(query, order, order);
            Query q = session.createQuery(query).setFlushMode(FlushMode.ALWAYS);
            List<Object[]> resultList = (List<Object[]>) q.list();

            List<MovementRow> movementRows = new ArrayList<MovementRow>();
            for (Object[] resultSet : resultList) {
                MovementRow movementRow = new MovementRow();
                movementRow.setId(((Movement) resultSet[0]).getId());
                movementRow.setMovementDate(((Movement) resultSet[0]).getMovementDate());
                movementRow.setOrigAccount(((Movement) resultSet[0]).getOrigAccount());
                movementRow.setOrigSadder(((AccountSadder) resultSet[1]).getSadderAfterMovement());
                movementRow.setDestAccount(((Movement) resultSet[0]).getDestAccount());
                movementRow.setDestSadder(((AccountSadder) resultSet[2]).getSadderAfterMovement());
                movementRow.setCurrency(((Movement) resultSet[0]).getCurrency());
                movementRow.setAmount(((Movement) resultSet[0]).getAmount());
                movementRow.setDetail(((Movement) resultSet[0]).getDetail());
                movementRow.setCommentary(((Movement) resultSet[0]).getCommentary());

                movementRows.add(movementRow);
            }

            logger.info("Successfully loaded every Movement. Order Desc: " + orderDesc + ". Row count: " + movementRows.size());
            return movementRows;
        } catch (Exception e) {
            String error = "Error loading every Movement";
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }

}
