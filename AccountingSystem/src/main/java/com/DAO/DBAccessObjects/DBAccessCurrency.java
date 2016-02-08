package com.DAO.DBAccessObjects;

import com.DAO.DBAccess;
import com.domain.Database.Currency;
import com.domain.Database.DBObject;
import com.util.Exception.CoreException;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Created by M-Sem on 28/11/2015.
 */
public class DBAccessCurrency extends DBAccessObject {

    final static Logger logger = Logger.getLogger(DBAccessCurrency.class);

    private static DBAccessCurrency dbAccessCurrencyInstance = null;

    public static DBAccessCurrency getInstance() {
        if (dbAccessCurrencyInstance == null) {
            dbAccessCurrencyInstance = new DBAccessCurrency();
        }
        return dbAccessCurrencyInstance;
    }

    private DBAccessCurrency (){
        this.classObject = Currency.class;
    }

    @Override
    public DBObject getObjectById(long id) throws CoreException {
        Session session = null;
        try {
            logger.info("Loading: Currency: " + id);
            session = DBAccess.getSession();
            DBObject returnObject = (DBObject) session.get(Currency.class, id);

            logger.info("Successfully Loaded: Currency: " + returnObject);
            return (Currency) returnObject;
        } catch (Exception e) {
            logger.error("Error Loading Currency: " + id + ". Exception:" + e);
            throw new CoreException("Error Loading Currency: " + id + ". Exception:" + e);
        }finally {
            DBAccess.closeSession(session);
        }
    }

}
