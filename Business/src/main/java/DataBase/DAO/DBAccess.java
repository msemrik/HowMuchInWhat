package DataBase.DAO;

import Business.Exceptions.CoreException;
import Business.Exceptions.ValidationException;
import DataBase.DAO.Hibernate.DBManagerHibernate;
import org.hibernate.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public final class DBAccess {

    private static final Logger logger = LogManager.getLogger(DBAccess.class);
    private final String HIBERNATE_DATABASE = "Hibernate";

    private DBManager dbManager;

    public DBAccess(String dataBaseType) throws IOException, CoreException {
        if (dataBaseType.equals(HIBERNATE_DATABASE)) {
            dbManager = new DBManagerHibernate();
        }else{
            String error = "Not valid dataBaseType Selected";
            logger.error(error);
            throw new ValidationException(error);
        }
    }

    public Session getSession() {
        return dbManager.getSession();
    }

    public void closeSession(Session session) {
        dbManager.closeSession(session);
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }
}
