package com.DAO.DBAccessObjects;

import com.DAO.DBAccess;
import com.domain.Database.Category;
import com.domain.Database.DBObject;
import com.domain.Database.Detail;
import com.util.Exception.CoreException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by M-Sem on 28/11/2015.
 */
@Component
public class DBAccessDetail extends DBAccessObject {

    final static Logger logger = Logger.getLogger(DBAccessDetail.class);

    private static DBAccessDetail dbAccessDetailInstance = null;

    public static DBAccessDetail getInstance() {
        if (dbAccessDetailInstance == null) {
            dbAccessDetailInstance = new DBAccessDetail();
        }
        return dbAccessDetailInstance;
    }

    private DBAccessDetail (){
        this.classObject = Detail.class;
    }

    @Override
    public DBObject getObjectById(long id) throws CoreException {
        Session session = null;
        try {
            logger.info("Loading: Detail: " + id);
            session = DBAccess.getSession();
            DBObject returnObject = (DBObject) session.get(Detail.class, id);
            logger.info("Successfully Loaded: Detail: " + returnObject);
            return (Detail) returnObject;
        } catch (Exception e) {
            logger.error("Error Loading Detail: " + id + ". Exception:" + e);
            throw new CoreException("Error Loading Detail: " + id + ". Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }


    public Detail getObjectByName(String name) throws CoreException {
        Session session = null;
        try {
            logger.info("Loading: Detail: " + name);
            session = DBAccess.getSession();
            Detail detail = (Detail) session.createCriteria(Detail.class)
                    .add(Restrictions.eq("name", name)).list().get(0);
            logger.info("Successfully Loaded: Detail: " + detail);
            return detail;
        } catch (Exception e) {
            logger.error("Error Loading Detail: " + name + ". Exception:" + e);
            throw new CoreException("Error Loading Detail: " + name + ". Exception:" + e);
        } finally {
            DBAccess.closeSession(session);
        }
    }


    public List<Detail> getDetailsForCategory(Category category) throws CoreException {
        Session session = DBAccess.getSession();
        try {
            logger.info("Loading: Detail for category: " + category);
            Criteria cr = session.createCriteria(Detail.class)
                    .add(Restrictions.eq("category", category));
            List <Detail> details = cr.list();
            logger.info("Successfully Loaded: Details for " + category +". Row Count: "+details.size());
            return details;
        } catch (Exception e) {
            logger.error("Error Loading Details for: " + category+ ". Exception:" + e);
            throw new CoreException("Error Loading Details for: " + category+ ". Exception:" + e);
        }finally {
            DBAccess.closeSession(session);
        }
    }


}
