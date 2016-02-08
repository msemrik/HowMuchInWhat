package com.DAO.DBAccessObjects;

import com.DAO.DBAccess;
import com.domain.Database.Category;
import com.domain.Database.DBObject;
import com.util.Exception.CoreException;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Created by M-Sem on 28/11/2015.
 */
public class DBAccessCategory extends DBAccessObject {

    final static Logger logger = Logger.getLogger(DBAccessCategory.class);

    private static DBAccessCategory dbAccessCategoryInstance = null;

    public static DBAccessCategory getInstance(){
        if (dbAccessCategoryInstance == null){
            dbAccessCategoryInstance =new DBAccessCategory();
        }
        return dbAccessCategoryInstance;
    }

    private DBAccessCategory (){
        this.classObject = Category.class;
    }


    @Override
    public DBObject getObjectById(long id) throws CoreException {
        Session session = DBAccess.getSession();
        try {
            logger.info("Loading: Category: " + id);
            DBObject returnObject = (DBObject) session.get(Category.class, id);
            logger.info("Successfully Loaded: Category: " + returnObject);
            return (Category) returnObject;
        } catch (Exception e) {
            logger.error("Error Loading Category: " + id + ". Exception:" + e);
            throw new CoreException("Error Loading Category: " + id + ". Exception:" + e);
        }
        finally {
            DBAccess.closeSession(session);
        }
    }


}