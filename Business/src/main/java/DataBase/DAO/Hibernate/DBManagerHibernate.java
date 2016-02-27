package DataBase.DAO.Hibernate;

import Business.Exceptions.CoreException;
import Business.Exceptions.DBAccessException;
import Business.Exceptions.ValidationException;
import Business.MessageConstants.ErrorMessagesConstants;
import Business.MessageConstants.LoggingMessageConstants;
import DataBase.DAO.Hibernate.HibernateDAO.*;
import DataBase.DAO.InterfacesDAO.AbstractDAOInterface;
import DataBase.DAO.DBManager;
import Business.domainObjects.DBObjects.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class DBManagerHibernate implements DBManager {
    private static final Logger logger = LogManager.getLogger(DBManagerHibernate.class);
    private SessionFactory sessionFactory;

    public DBManagerHibernate() throws DBAccessException {
        sessionFactory = buildSessionFactory();
            try {
                ((SessionFactoryImpl) sessionFactory).getConnectionProvider().getConnection();
            } catch (Exception e) {
                String error = "Error while initializing DB.";
                throw new DBAccessException(error, error + " Please contact your administrator.", e);
            }
    }

    private SessionFactory buildSessionFactory() throws DBAccessException {
            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).buildServiceRegistry();
            return configuration.buildSessionFactory(serviceRegistry);
    }


    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void closeSession(Session session) {
        session.close();
    }

    public void shutdown() {
        sessionFactory.close();
    }


    public AbstractDAOInterface getDBAccessObject(Class<?> classType) throws CoreException {

        if (classType == Account.class)
            return AccountDAO.getInstance(this);
        else {
            if (classType == Movement.class)
                return MovementDAO.getInstance(this);
            else {
                if (classType == AccountSadder.class)
                    return AccountSadderDAO.getInstance(this);
                else {
                    if (classType == Category.class)
                        return CategoryDAO.getInstance(this);
                    else {
                        if (classType == Currency.class)
                            return CurrencyDAO.getInstance(this);
                        else {
                            if (classType == Detail.class)
                                return DetailDAO.getInstance(this);

                            else {
                                String error = classType + "is not a Valid DAO Object.";
                                logger.error(error);
                                throw new ValidationException(error, ErrorMessagesConstants.GENERIC_INTERNAL_ERROR);
                            }
                        }
                    }
                }
            }
        }
    }

}