package DataBase.DAO.Hibernate.HibernateDAO;

import Business.Exceptions.CoreException;
import Business.Exceptions.DBAccessException;
import Business.MessageConstants.LoggingMessageConstants;
import Business.domainObjects.DBObjects.Category;
import DataBase.DAO.DBManager;
import DataBase.DAO.InterfacesDAO.AbstractDAOInterface;
import DataBase.DAO.InterfacesDAO.CategoryDAOInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

public class CategoryDAO implements CategoryDAOInterface, AbstractDAOInterface {

    private static final Logger logger = LogManager.getLogger(CategoryDAO.class);
    private DBManager dbManager = null;
    private Class<?> classObject = null;


    private static AbstractDAOInterface DAOObject = null;

    public static AbstractDAOInterface getInstance(DBManager dbManager) {
        if (DAOObject == null)
            DAOObject = new CategoryDAO(dbManager);
        return DAOObject;
    }

    private CategoryDAO(DBManager dbManager) {
        this.dbManager = dbManager;
        this.classObject = Category.class;
    }


    @Override
    public Category getCategoryById(long id) throws CoreException {
        logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_BEFORE, "Category", id));
        Session session = dbManager.getSession();
        try {
            Category category = (Category) session.get(Category.class, id);
            logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_SUCCESS, "Category", category.toPrint()));
            return category;
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_ERROR, "Category", id);
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }

    @Override
    public List<Category> loadEveryCategory() throws CoreException {
        logger.info(String.format(LoggingMessageConstants.GET_EVERY_OBJECT_BEFORE, "Category"));
        List<Category> list = null;
        Session session = dbManager.getSession();
        try {
            Criteria cr = session.createCriteria(classObject);
            list = (List<Category>) (List<?>) cr.list();
            logger.info(String.format(LoggingMessageConstants.GET_EVERY_OBJECT_SUCCESS, "Category", list.size()));
            return list;
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.GET_EVERY_OBJECT_ERROR, "Category");
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }

    @Override
    public void saveCategory(Category category) throws CoreException {
        logger.info(String.format(LoggingMessageConstants.SAVE_OBJECT_BEFORE, "Category", category.toPrint()));
        Session session = dbManager.getSession();
        try {
            session.getTransaction().begin();
            session.save(category);
            session.getTransaction().commit();
            logger.info(String.format(LoggingMessageConstants.SAVE_OBJECT_SUCCESS, "Category", category.toPrint()));
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.SAVE_OBJECT_SUCCESS, "Category", category.toPrint());
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }


}