package com.aw.core.db.transaction;

import com.aw.core.dao.DAOIntgr;
import com.aw.core.db.DBExecuter;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * User: GMC
 * Date: 02/10/2007
 */
public class TransactionDecorator {
    protected static Map cache = new HashMap();
    protected static DAOIntgr daoIntgr;
    protected static Advice txAdvice;

    /**
     * Crea una nueva instancia y la wrapea con un proxy transaccional
     *
     * @param clazz tipo de objeto
     * @return specific
     */
    public static Object getTxProxy(Class clazz) {
        if (cache != null && cache.containsKey(clazz.getName())) {
            return cache.get(clazz.getName());
        }
        Object object = null;
        try {
            object = clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("Class:<" + clazz + "> cannot be intantied.");
        }
        Object txProxy = getTxProxy(object);
        cache.put(clazz.getName(), txProxy);
        return txProxy;
    }

    /**
     * Wrap el objeto con un proxy transaccional
     *
     * @return el proxy
     */
    public static Object getTxProxy(Object object) {
        ProxyFactory p = new ProxyFactory(object);
        p.addAdvice(txAdvice);
        return p.getProxy();
    }


    public static Object executeUpdate(DBExecuter executer) {
        executer.setDaoIntgr(daoIntgr);
        UpdateExecuter updateExecuter = (UpdateExecuter) getTxProxy(UpdateExecuter.class);
        return updateExecuter.executeUpdate(executer);
    }

    public static Object executeQuery(DBExecuter executer) {
        executer.setDaoIntgr(daoIntgr);
        QueryExecuter queryExecuter = (QueryExecuter) getTxProxy(QueryExecuter.class);
        return queryExecuter.executeQuery(executer);
    }

    public static class UpdateExecuter {
        public Object executeUpdate(DBExecuter executer) {
            return executer.execute();
        }
    }

    public static class QueryExecuter {
        public Object executeQuery(DBExecuter executer) {
            return executer.execute();
        }
    }

    public static void setTxAdvice(Advice txAdvice) {
        TransactionDecorator.txAdvice = txAdvice;
    }

    public static void setDaoIntgr(DAOIntgr daoIntgr) {
        TransactionDecorator.daoIntgr = daoIntgr;
    }
}
