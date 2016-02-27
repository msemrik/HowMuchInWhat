package DataBase.DAO.Hibernate.HibernateDAO;

import Business.Exceptions.CoreException;
import Business.Exceptions.DBAccessException;
import Business.MessageConstants.LoggingMessageConstants;
import Business.domainObjects.DBObjects.Category;
import Business.domainObjects.DBObjects.Detail;
import DataBase.DAO.DBManager;
import DataBase.DAO.InterfacesDAO.AbstractDAOInterface;
import DataBase.DAO.InterfacesDAO.DetailDAOInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DetailDAO implements DetailDAOInterface, AbstractDAOInterface {

    private static final Logger logger = LogManager.getLogger(DetailDAO.class);
    private DBManager dbManager = null;
    private Class<?> classObject = null;


    private static AbstractDAOInterface DAOObject = null;

    public static AbstractDAOInterface getInstance(DBManager dbManager) {
        if (DAOObject == null)
            DAOObject = new DetailDAO(dbManager);
        return DAOObject;
    }

    private DetailDAO(DBManager dbManager) {
        this.dbManager = dbManager;
        this.classObject = Detail.class;
    }

    @Override
    public Detail getDetailById(long id) throws CoreException {
        logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_BEFORE, "Detail", id));
        Session session = null;
        try {
            session = dbManager.getSession();
            Detail detail = (Detail) session.get(Detail.class, id);
            logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_SUCCESS, "Detail", detail.toPrint()));
            return detail;
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_ERROR, "Detail", id);
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }


    @Override
    public Detail getDetailByName(String name) throws CoreException {
        logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_NAME_BEFORE, "Detail", name));
        Session session = null;
        try {
            session = dbManager.getSession();
            Detail detail = (Detail) session.createCriteria(Detail.class)
                    .add(Restrictions.eq("name", name)).list().get(0);
            logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_NAME_SUCCESS, "Detail", detail.toPrint()));

            return detail;
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.GET_OBJECT_BY_NAME_ERROR, "Detail", name);
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }


    @Override
    public List<Detail> getDetailsForCategory(Category category) throws CoreException {
        logger.info("Loading Details for Category: " + category.toPrint());
        Session session = dbManager.getSession();
        try {
            Criteria cr = session.createCriteria(Detail.class)
                    .add(Restrictions.eq("category", category));
            List<Detail> details = cr.list();
            logger.info("Successfully Loaded Details for Category: " + category.toPrint() + ". Row Count: " + details.size());
            return details;
        } catch (Exception e) {
            String error = "Error Loading Details for Category : " + category.toPrint();
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }

    @Override
    public List<Detail> loadEveryDetail() throws CoreException {
        logger.info(String.format(LoggingMessageConstants.GET_EVERY_OBJECT_BEFORE, "Detail"));
        List<Detail> list = null;
        Session session = dbManager.getSession();
        try {
            Criteria cr = session.createCriteria(Detail.class);
            list = cr.list();
            logger.info(String.format(LoggingMessageConstants.GET_EVERY_OBJECT_SUCCESS, "Detail", list.size()));
            return list;
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.GET_EVERY_OBJECT_ERROR, "Detail");
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }

    @Override
    public void saveDetail(Detail detail) throws CoreException {
        logger.info(String.format(LoggingMessageConstants.SAVE_OBJECT_BEFORE, "Detail", detail.toPrint()));
        Session session = dbManager.getSession();
        try {
            session.getTransaction().begin();
            session.save(detail);
            session.getTransaction().commit();
            logger.info(String.format(LoggingMessageConstants.SAVE_OBJECT_SUCCESS, "Detail", detail.toPrint()));
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.SAVE_OBJECT_ERROR, "Detail", detail.toPrint());
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }


}
