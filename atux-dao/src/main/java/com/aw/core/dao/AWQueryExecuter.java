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

import com.aw.core.cache.storage.CacheEHImpl;
import com.aw.core.domain.AWBusinessException;
import com.aw.support.collection.ListUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * User: JCM
 * Date: 02/10/2007
 */
public class AWQueryExecuter {
    protected final Log logger = LogFactory.getLog(getClass());
    private String sql;
    private Object[] sqlParams;

    /** cache name (null : no cache) */
    private String cacheName;

    // Working variables
    private Connection connection;
    private RowHandler rowHandler;

    public AWQueryExecuter(Connection connection, String sql, Object[] sqlParams) {
        this.connection = connection;
        this.sql = sql;
        this.sqlParams = sqlParams;
    }

    public AWQueryExecuter setCacheable(String cacheName) {
        this.cacheName = cacheName;
        return this;
    }

    public AWQueryExecuter setRowHandler(RowHandler rowHandler) {
        this.rowHandler = rowHandler;
        return this;
    }

    public <Rsl>  Rsl unique(Class<Rsl> bnClass) {
        List<Rsl> results = list(bnClass);
        return results.size()>0?results.get(0):null;
    }

    public <Rsl> List<Rsl> list(Class<Rsl> bnClass) {
        return execute(rowHandler!=null?rowHandler:new BeanRowHandler<Rsl>(bnClass));
    }


    private<Rsl>  List<Rsl> execute(RowHandler<Rsl> rowHandler) {
        List<Rsl>rsl;
        if (cacheName!=null){
            CacheEHImpl cache = new CacheEHImpl();
            cache.setName(cacheName);
            String key = sql + ListUtils.concatenarSepPorComa(sqlParams == null ? Collections.EMPTY_LIST : Arrays.asList(sqlParams));
            if (! cache.exist(key)){
                rsl = executeSQL(rowHandler);;
                cache.store(key, rsl);
            }else{
                rsl = (List<Rsl>) cache.get(key);
            }
        }else{
            rsl = executeSQL(rowHandler);
        }
        return  rsl;
    }

    private <Rsl> List<Rsl> executeSQL(RowHandler<Rsl> rowHandler) {
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            if(sqlParams!=null)
                for (int i = 0; i < sqlParams.length; i++) {
                    Object sqlParam = sqlParams[i];
                    if (sqlParam==null)
                        stmt.setNull(i+1, Types.VARCHAR);
                    else
                        stmt.setObject(i+1,sqlParam);

                }
            List<Rsl> results = new ArrayList<Rsl>(AWQueryAbortable.DEF_LIST_SIZE);
            if(logger.isDebugEnabled())
                logger.debug(DAOSql.buildSQL(sql, sqlParams));
            ResultSet rs=stmt.executeQuery();
            AWQueryAbortable queryAbortable = AWQueryAbortable.instance();
            queryAbortable.resetRowCount();
            while (rs.next()){
                if (queryAbortable.isAborted())break;
                Rsl rsl = rowHandler.handle(rs);
                results.add(rsl);
                queryAbortable.incRowCount();
            }
            logger.debug("Results :"+results.size()+" abortedQuery:"+queryAbortable.isAborted());
            rs.close();
            stmt.close();
            return results;
        }catch (Exception e){
            logger.error("SQL:" + sql);
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }


    public interface RowHandler<Rsl>{
        Rsl handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException;
    }
    private class BeanRowHandler<Rsl> implements RowHandler<Rsl> {
        Class<Rsl> bnClass;
        BeanWrapperImpl wrapper;
        String[] colNames = null;
        public BeanRowHandler(Class<Rsl> bnClass) {
            this.bnClass = bnClass;
            this.wrapper = new BeanWrapperImpl(bnClass);
        }
        @Override
        public Rsl handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
            if (colNames==null)
                colNames = buildColumnNames(rs,wrapper);
            Rsl rsl =  bnClass.newInstance();
            wrapper.setWrappedInstance(rsl);
            for (int i = 0; i < colNames.length; i++) {
                wrapper.setPropertyValue(colNames[i],rs.getObject(i+1));
            }
            return rsl;
        }
    }

    private String[] buildColumnNames(ResultSet rs, BeanWrapper wrapper) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        List<String> colNames = new ArrayList<String>(meta.getColumnCount());

        for (int i = 0; i < meta.getColumnCount(); i++) {
            String colName = meta.getColumnName(i + 1);
            if (wrapper!=null&&wrapper.isWritableProperty(colName))
            colNames.add(colName);
        }
        return colNames.toArray(new String[colNames.size()]);
    }
}