/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.core.dao;

import com.aw.core.dao.meta.HbmUtilFactory;
import com.aw.core.dao.sql.SelectEntityParser;
import com.aw.core.domain.AWBusinessException;
import com.aw.core.util.QTTbBnMapperBasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.type.Type;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: Julio C. Macavilca
 * Date: 02/10/2007
 */
public class DAOSql {
    protected final Log logger = LogFactory.getLog(getClass());

    DAOHbm daoHbm;

    public DAOSql(DAOHbm daoHbm) {
        this.daoHbm = daoHbm;
    }

    ////////////////// Simple Query Support ////////////////////
    public Map findUniqueMap(String sql, Object[] filtro) {
        List list = findListOfMaps(sql, filtro);
        return (Map) (list.size() > 0 ? list.get(0) : null);
    }

    /**
     * Helper method used to retrieve a list of objects using standard SQL sintaxis
     * The query must return columns that match with setter/getter on the beanClass
     *
     * @param sql        SQL query
     * @param filterKeys key used to restrict the search
     * @param beanClass  class used to store each row info
     * @return List of object of type <beanClass>
     */
    public <T> List<T> findListOfBeans(String sql, Object[] filterKeys, Class<T> beanClass) {
        return buildQueryExecuter(sql, filterKeys).setRowHandler(new QTTbBnMapperBasicRowProcessor().setResultType(beanClass))
                .list(beanClass);
//        return (List) executeQuery(sql, filterKeys, new BeanListHandler(beanClass, new QTTbBnMapperBasicRowProcessor()));
    }
//    public <T> List<T> findListOfBeans(String sql, Object[] filterKeys, Class<T> beanClass, String cacheName) {
//        String key = sql+ ListUtils.concatenarSepPorStr(Arrays.asList(filterKeys) ,"-").toString();
//        List<T>  rsl = null;
//        CacheEHImpl cache = new CacheEHImpl();
//        cache.setName(cacheName);
//        if (! cache.exist(key)){
//            rsl = findListOfBeans(sql, filterKeys, beanClass);
//            cache.store(key, rsl);
//        }else{
//            rsl = (List<T>) cache.get(key);
//        }
//        return rsl;
//    }

//    public List findListOfBeans(String sql, Object[] filterKeys, Class beanClass, final EckerdTablaABeanMapper beanMapper) {
//        //beanMapper
//        BasicRowProcessor basicRowProcessor = new EckerdTbBnMapperBasicRowProcessor(beanMapper);
//        return (List) executeQuery(sql, filterKeys,new BeanListHandler(beanClass, basicRowProcessor)  );
//    }

    /**
     * Helper method used to retrieve a list of Arrays using standard SQL sintaxis
     * The query must return columns maintaining the ordinal position on the query
     *
     * @param sql        SQL query
     * @param filterKeys key used to restrict the search
     * @return list of arrays
     */
    public List<Object[]> findListOfArrays(String sql, Object[] filterKeys) {
        return (List<Object[]>) executeQuery(sql, filterKeys, new ArrayListHandler());
    }

    /**
     * Helper method used to retrieve a list of Maps using standard SQL sintaxis
     *
     * @param sql        SQL query
     * @param filterKeys key used to restrict the search
     * @return list of maps
     */
    public List<Map> findListOfMaps(String sql, Object[] filterKeys) {
        return (List<Map>) executeQuery(sql, filterKeys, new MapListHandler());
    }

    /**
     * Helper method used to retrieve a list of objects. The object element of the list
     * is determined by the resultSetHandler
     *
     * @param sql              SQL query
     * @param filterKeys       key used to restrict the search
     * @param resultSetHandler maps a resultset row to a object (array, bean, etc)
     * @return List of objects
     */
    public Object executeQuery(String sql, Object[] filterKeys, ResultSetHandler resultSetHandler) {
        QueryRunner queryRunner = new QueryRunner();
        try {
            if (logger.isDebugEnabled())
                logger.debug("Executing:" + AWQueryRunner.buildSQLLog(sql, filterKeys));

            Connection con = getHibernateConnection();
            return queryRunner.query(con, sql, filterKeys, resultSetHandler);
        } catch (Exception e) {
            //logger.error("Error executing query", e);
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    public Connection getHibernateConnection() {
        return null;
//        return daoHbm.getHSession().connection();
//        return DAOIntgr.instance().getHbm().getHSession().connection();
    }

//    /**
//     * Runs the {@link SQLExecuter#execute(java.sql.Connection)}
//     * method under the curretn transacction.
//     * If not trannsaction exists, it create a readonly one
//     *
//     * @param executer
//     * @return
//     */
//    public Object executeQuery(SQLExecuter executer) {
//        try {
//            return executer.execute(getHibernateConnection());
//        } catch (SQLException e) {
//            throw AWDataAccessException.wrapUnhandledException(logger, e);
//        } catch (HibernateException e) {
//            throw AWDataAccessException.wrapUnhandledException(logger, e);
//        }
//    }

//    /**
//     * Runs the {@link SQLExecuter#execute(java.sql.Connection)}
//     * method under the curretn transacction.
//     * If not trannsaction exists, it create a traansactional one
//     *
//     * @param executer
//     * @return
//     */
//    public Object executeUpdate(SQLExecuter executer) {
//        // transactional support given by method declarative on PROXY
//        return this.executeQuery(executer);
//    }


    /**
     * Helper method used to retrieve a list of objects using standard SQL sintaxis
     * The query must return columns that match with setter/getter on the beanClass
     *
     * @param sql        SQL query
     * @param filterKeys key used to restrict the search
     * @param beanClass  class used to store each row info
     * @return List of object of type <beanClass>
     */
    public <T> T findObject(String sql, Object[] filterKeys, Class<T> beanClass) {
        List list = findListOfBeans(sql, filterKeys, beanClass);
        if (list.size() == 0)
            return null;
        else
            return (T) list.get(0);
    }

    /**
     * Helper method used to retrieve a COLUMN using standard SQL sintaxis
     * The query must return a single column
     *
     * @param sql        SQL query
     * @param filterKeys key used to restrict the search
     * @return First Row First column , NULL if no row
     */
    public Object findObjectGetColumn(String sql, Object[] filterKeys) {
        try {
            return new QueryRunner().query(getHibernateConnection(), sql, filterKeys, new ScalarHandler());
        } catch (SQLException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    /**
     * Helper method used to retrieve the select count(*)
     *
     * @param sqlFromWhereClause SQL query without the SELECT section: "FROM x where X=y"
     * @param filterKeys         key used to restrict the search
     * @return number of rows that return the query
     */
    public int findCountRows(String sqlFromWhereClause, Object[] filterKeys) {
        String sql = "SELECT count(*) " + sqlFromWhereClause;
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Executing:" + AWQueryRunner.buildSQLLog(sql, filterKeys));
            }
            Number num = (Number) new QueryRunner().query(getHibernateConnection(), sql, filterKeys, new ScalarHandler());
            return num.intValue();
        } catch (SQLException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    /**
     * Executes a simple SQL update (INSERT/DETELE/UPDATE) operation.
     * Implemtantion uses JdbcTemplate
     *
     * @param sql       SQL expresion, params with ?
     * @param sqlParams array of parameters to replace ? in order that appear
     * @return the numer of rows affected by the SQL operation
     */
    public int execSqlUpdate(String sql, Object[] sqlParams) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Executing:" + AWQueryRunner.buildSQLLog(sql, sqlParams));
            }
            return new QueryRunner().update(getHibernateConnection(), sql, sqlParams);
        } catch (SQLException e) {
            try{
                logger.error("SQL:" + buildSQL(sql, sqlParams));
            }catch (Throwable e2){
                logger.error("SQL:" + sql+"-"+ sqlParams);
            }
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    /**
     * Call example
     * execSqlFunction(sqlUpdSqldoActor, Types.NUMERIC, new Object[]{"dss"})
     */
    public Object execSqlFunction(String sql, int returnSqlType, Object[] sqlParams) {
        try {
            CallableStatement cstmt = getHibernateConnection().prepareCall(sql);
            cstmt.registerOutParameter(1, returnSqlType);
            if (sqlParams != null)
                for (int i = 0; i < sqlParams.length; i++) {
                    cstmt.setObject(i + 2, sqlParams[i]);
                }
            logger.debug("SQL Exec:" + buildSQL(sql, sqlParams));
            cstmt.execute();
            Object returnValue = cstmt.getObject(1);
            cstmt.close();
            return returnValue;
        } catch (SQLException e) {
            logger.error("SQL:" + buildSQL(sql, sqlParams), e);
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }
    public Object execSqlFunctionDebug(String sql, int returnSqlType, Object[] sqlParams) {
        try{
            dbmsOutputEnable(true);
            return execSqlFunction(sql, returnSqlType, sqlParams);
        }finally {
            dbmsOutputPrint();
            dbmsOutputEnable(false);
        }
    }

    /**
     * Call example
     * execSqlFunction(sqlUpdSqldoActor, Types.NUMERIC, new Object[]{"dss"})
     */
    public boolean execSqlProcedure(String sql, Object[] sqlParams) {
        try {
            CallableStatement cstmt = getHibernateConnection().prepareCall(sql);
            if (sqlParams != null)
                for (int i = 0; i < sqlParams.length; i++) {
                    cstmt.setObject(i + 1, sqlParams[i]);
                }
            logger.debug("SQL Exec:" + buildSQL(sql, sqlParams));
            boolean result= cstmt.execute();
            cstmt.close();
            return result;
        } catch (SQLException e) {
            logger.error("SQL:" + buildSQL(sql, sqlParams), e);
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }
    public boolean execSqlProcedureDebug(String sql, Object[] sqlParams) {
        try{
            dbmsOutputEnable(true);
            return execSqlProcedure(sql, sqlParams);
        }finally {
            dbmsOutputPrint();
            dbmsOutputEnable(false);
        }
    }

    public static String buildSQL(String sql, Object[] sqlParams) {
        if (sqlParams == null) return sql;

        for (Object sqlParam : sqlParams) {
            if (sqlParam instanceof String) sqlParam = "'"+sqlParam+"'";
            sql = sql.replaceFirst("\\?", sqlParam == null ? "NULL" : sqlParam.toString());
        }
        return sql;
    }

    public List findListEx(String sql, Object[] filterKeys, Class beanClass, SelectEntityParser entityParser) {
        StringBuffer sqlBuf = entityParser.paserXX(sql, beanClass);


        List listOfBeans = (List) executeQuery(sqlBuf.toString(), filterKeys,
                new BeanListHandler(beanClass, new QTTbBnMapperBasicRowProcessor(entityParser.buildBeanMapper())));
        return listOfBeans;

    }

    public <T> List<T> findListEx(String sql, Object[] filterKeys, Class<T> beanClass) {
        if (HbmUtilFactory.newInstance().isHbmEntity(beanClass)) {
            return findListUsingHIBSQLQuery(sql, filterKeys, beanClass);
        }
        SelectEntityParser entityParser = new SelectEntityParser();
        return findListEx(sql, filterKeys, beanClass, entityParser);
    }


    private List findListUsingHIBSQLQuery(String sql, Object[] filterKeys, Class beanClass) {
        List<Type> types = new ArrayList();
        for (Object param : filterKeys) {
            if (param instanceof String) {
//                types.add(Hibernate.STRING);
            } else if (param instanceof Integer) {
//                types.add(Hibernate.BIG_INTEGER);
            } else if (param instanceof BigDecimal) {
//                types.add(Hibernate.BIG_DECIMAL);
            }else {
                throw new IllegalStateException("Type currently not added. Please add this to equivalent Hibernate type:"+param);
            }
        }
        Type[] hibType = new Type[types.size()];
        return daoHbm.getHSession().createSQLQuery(sql.toString()).addEntity("domain", beanClass).setParameters(filterKeys, types.toArray(hibType)).list();
    }

    public List<Object> findListOfFirstColumn(String sql, Object[] filterKeys) {
        List<Object[]> listofArrays = findListOfArrays(sql, filterKeys);
        ArrayList listOfFirstColumn = new ArrayList();
        for (Object[] listofArray : listofArrays) {
           listOfFirstColumn.add(listofArray[0]);
        }
        return listOfFirstColumn; 
    }
    protected void dbmsOutputEnable(boolean enable){
        String sql = enable
                ?"BEGIN\n" +
                "    DBMS_OUTPUT.ENABLE;\n" +
                //"    DBMS_OUTPUT.SERVEROUTPUT(FALSE);\n" +
                "END; "
                :"BEGIN\n" +
                "    DBMS_OUTPUT.DISABLE;\n" +
                "END; ";
        execSqlProcedure(sql, null);
    }

    protected void dbmsOutputPrint() {
        try {
            StringBuffer buffer = new StringBuffer();
            dbmsOutputPrint(getHibernateConnection(), buffer);
            logger.debug("DBMS_OUTPUT\n"+buffer);
        } catch (SQLException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }
    protected void dbmsOutputPrint(Connection conn, StringBuffer buf)throws java.sql.SQLException {
          String getLineSql = "begin dbms_output.get_line(?,?); end;";
          CallableStatement stmt = conn.prepareCall(getLineSql);
          boolean hasMore = true;
          stmt.registerOutParameter(1, Types.VARCHAR);
          stmt.registerOutParameter(2, Types.INTEGER);
            while (hasMore) {
                    boolean status = stmt.execute();
                    hasMore = (stmt.getInt(2) == 0);
                    if (hasMore) {
                           buf.append(stmt.getString(1)).append("\n");
                    }
                }
          stmt.close();
    }


    public AWQueryExecuter buildQueryExecuter(String query, Object[] params) {
        return new AWQueryExecuter(getHibernateConnection(), query, params);
    }
}
