package com.DAO.DBAccessObjects;

import com.DAO.DBAccess;
import com.domain.Database.DBObject;
import com.util.Exception.CoreException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by M-Sem on 28/11/2015.
 */
public abstract class DBAccessObject extends Object{

    final static Logger logger = Logger.getLogger(DBAccessObject.class);

    public DBObject getObjectById(long id) throws CoreException {return null;};

    public DBObject getObjectById(long id, Class<?> classType) throws CoreException {return null;};

    protected Class<?> classObject;

    public void saveObject(DBObject dbObject) throws CoreException {
        Session session = DBAccess.getSession();
        try {
            logger.info("Saving: " + dbObject.getClass().getSimpleName() + " " + dbObject);
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

    public List<Object> loadEveryRow() throws CoreException {
        List<Object> list = null;
        Session session = DBAccess.getSession();
        try {
            logger.info("Loading Table: " + classObject.getSimpleName());
            Criteria cr = session.createCriteria(classObject);
            list = cr.list();
            logger.info("Successfully Loaded: " + classObject.getSimpleName() + ". Row count: " + list.size());
            return list;
        } catch (Exception e) {
            logger.error("Error Loading Table: " + classObject.getSimpleName() + " " + classObject.getSimpleName() + ". Exception:" + e);
            throw new CoreException("Error Loading Table: " + classObject.getSimpleName() + " " + classObject.getSimpleName() + ". Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }


}
