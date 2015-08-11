package com.aw.core.spring.init;

import com.aw.core.dao.DAOIntgr;
import com.aw.core.dao.meta.HbmUtilFactory;
import com.aw.core.db.DbUtil;
import com.aw.core.db.transaction.TransactionDecorator;
import org.aopalliance.aop.Advice;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Class use to initiate all of the needed DAO util objects
 * User: gmc
 * Date: 23-sep-2008
 */
@Component
public class DAOInitiator implements ApplicationContextAware, InitializingBean {
    protected static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public void afterPropertiesSet() throws Exception {
        Advice txAdvice = (Advice) applicationContext.getBean(SpringBeanNames.TX_ADVICE);
        DAOIntgr daoIntgr = (DAOIntgr) applicationContext.getBean(SpringBeanNames.DAO_INTGR);
        DataSource dataSource = (DataSource) applicationContext.getBean(SpringBeanNames.DATA_SOURCE);
        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean(SpringBeanNames.SESSION_FACTORY);
        TransactionDecorator.setDaoIntgr(daoIntgr);
        TransactionDecorator.setTxAdvice(txAdvice);
        DbUtil.instance().setDataSource(dataSource);
        HbmUtilFactory.setFactory(sessionFactory);
    }
}
