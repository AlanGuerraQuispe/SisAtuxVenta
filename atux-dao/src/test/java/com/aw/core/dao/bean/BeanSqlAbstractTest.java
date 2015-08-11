package com.aw.core.dao.bean;

import com.aw.core.dao.meta.HbmUtilFactory;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * User: JCM
 * Date: 18/10/2007
 */
public abstract class BeanSqlAbstractTest extends TestCase {
    protected void setUp() throws Exception {
        super.setUp();
        loadHibernateTestConfig();
    }
    public static void loadHibernateTestConfig() {
        // Load hibernate configuration
        Configuration cfg = new Configuration()
            .addClass(DAOHbmTableImpl.class)
            .setProperty("hibernate.show_sql", "true")
            .setProperty("hibernate.format_sql", "true")
            .setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect")
            .setProperty("hibernate.connection.driver_class", "oracle.jdbc.OracleDriver")
            .setProperty("hibernate.connection.url", "jdbc:oracle:thin:@192.168.1.37:1521:ORCL")
            .setProperty("hibernate.connection.username", "qtcobraapt")
            .setProperty("hibernate.connection.password", "etna2007")
                ;
        //.setProperty("hibernate.connection.datasource", "java:comp/env/jdbc/test")
        HbmUtilFactory.factory = cfg.buildSessionFactory();
    }
    public Object doUnderSession(SessionUnitOfWork unitOfWork){
        Session session = HbmUtilFactory.factory.openSession();
        Transaction tx = session.beginTransaction();
        try{
            return unitOfWork.execute(session);
        }finally {
            tx.commit();
            session.close();
        }
    }


    public interface SessionUnitOfWork{
        public Object execute(Session session);
    }
}
