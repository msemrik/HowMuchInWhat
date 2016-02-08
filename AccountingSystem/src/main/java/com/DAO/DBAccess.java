package com.DAO;

import com.DAO.DBAccessObjects.*;
import com.domain.*;
import com.domain.Database.*;
import com.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;


/**
 * Created by x217204 on 11/17/2015.
 */
public final class DBAccess {

    final static Logger logger = Logger.getLogger(DBAccess.class);

    public static Session getSession() {
        HibernateUtil.getSessionFactory().openSession();
        return HibernateUtil.getSessionFactory().openSession();
    }

    public static void closeSession(Session session) {
        session.close();
    }

    private DBAccess() {
    }

    ;


    public static DBAccessObject getDBAccessObject(Class<?> classType) {

        if (classType == Account.class)
            return DBAccessAccount.getInstance();
        if (classType == Movement.class)
            return DBAccessMovement.getInstance();
        if (classType == Category.class)
            return DBAccessCategory.getInstance();
        if (classType == Currency.class)
            return DBAccessCurrency.getInstance();
        if (classType == AccountSadder.class)
            return DBAccessAccountSadder.getInstance();
        if (classType == Detail.class)
            return DBAccessDetail.getInstance();

        return null;
    }


    public static DBAccessObject getDBAccessObject(DBObject dbAccessObject) {
        if (dbAccessObject instanceof Account)
            return DBAccessAccount.getInstance();
        if (dbAccessObject instanceof Movement)
            return DBAccessMovement.getInstance();
        if (dbAccessObject instanceof Category)
            return DBAccessCategory.getInstance();
        if (dbAccessObject instanceof Currency)
            return DBAccessCurrency.getInstance();
        if (dbAccessObject instanceof AccountSadder)
            return DBAccessAccountSadder.getInstance();
        if (dbAccessObject instanceof Detail)
            return DBAccessDetail.getInstance();

        return null;
    }

}
