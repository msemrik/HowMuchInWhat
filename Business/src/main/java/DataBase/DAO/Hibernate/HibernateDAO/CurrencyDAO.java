package DataBase.DAO.Hibernate.HibernateDAO;

import Business.Exceptions.CoreException;
import Business.Exceptions.DBAccessException;
import Business.MessageConstants.LoggingMessageConstants;
import Business.domainObjects.DBObjects.Currency;
import DataBase.DAO.DBManager;
import DataBase.DAO.InterfacesDAO.AbstractDAOInterface;
import DataBase.DAO.InterfacesDAO.CurrencyDAOInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

public class CurrencyDAO implements CurrencyDAOInterface, AbstractDAOInterface {

    private static final Logger logger = LogManager.getLogger(CurrencyDAO.class);
    private DBManager dbManager = null;
    private Class<?> classObject = null;


    private static AbstractDAOInterface DAOObject = null;

    public static AbstractDAOInterface getInstance(DBManager dbManager) {
        if (DAOObject == null)
            DAOObject = new CurrencyDAO(dbManager);
        return DAOObject;
    }

    private CurrencyDAO(DBManager dbManager) {
        this.dbManager = dbManager;
        this.classObject = Currency.class;
    }

    @Override
    public Currency getCurrencyById(long id) throws CoreException {
        logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_BEFORE, "Currency", id));
        Session session = null;
        try {
            session = dbManager.getSession();
            Currency currency = (Currency) session.get(Currency.class, id);
            logger.info(String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_SUCCESS, "Currency", currency.toPrint()));
            return currency;
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.GET_OBJECT_BY_ID_ERROR, "Currency", id);
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }

    @Override
    public List<Currency> loadEveryCurrency() throws DBAccessException {
        logger.info(String.format(LoggingMessageConstants.GET_EVERY_OBJECT_BEFORE, "Currency"));
        List<Currency> list = null;
        Session session = dbManager.getSession();
        try {
            Criteria cr = session.createCriteria(classObject);
            list = (List<Currency>) (List<?>) cr.list();
            logger.info(String.format(LoggingMessageConstants.GET_EVERY_OBJECT_SUCCESS, "Currency", list.size()));
            return list;
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.GET_EVERY_OBJECT_ERROR, "Currency");
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally

        {
            dbManager.closeSession(session);
        }

    }

    @Override
    public void saveCurrency(Currency currency) throws CoreException {
        logger.info(String.format(LoggingMessageConstants.SAVE_OBJECT_BEFORE, "Currency", currency.toPrint()));
        Session session = dbManager.getSession();
        try {
            session.getTransaction().begin();
            session.save(currency);
            session.getTransaction().commit();
            logger.info(String.format(LoggingMessageConstants.SAVE_OBJECT_SUCCESS, "Currency", currency.toPrint()));
        } catch (Exception e) {
            String error = String.format(LoggingMessageConstants.SAVE_OBJECT_ERROR, "Currency", currency.toPrint());
            logger.error(error);
            throw new DBAccessException(error, e);
        } finally {
            dbManager.closeSession(session);
        }
    }
}
