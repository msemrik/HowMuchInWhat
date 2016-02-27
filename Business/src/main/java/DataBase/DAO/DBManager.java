package DataBase.DAO;

import Business.Exceptions.CoreException;
import DataBase.DAO.InterfacesDAO.AbstractDAOInterface;
import org.hibernate.Session;

public interface DBManager {

    public Session getSession();

    public void closeSession(Session session);

    public void shutdown();

    public AbstractDAOInterface getDBAccessObject(Class<?> classType) throws CoreException;
}