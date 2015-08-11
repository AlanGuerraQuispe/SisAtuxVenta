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

import com.aw.core.domain.AWBusinessException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * User: JCM
 * Date: 02/10/2007
 */
public class AWQueryRunner extends QueryRunner {
    protected final Log logger = LogFactory.getLog(getClass());

    public int findCountRows(Connection con, String sqlFromWhere, Object[] filterKeys) {
        List results = findListOfMaps(con, "select count(*) as numRows " + sqlFromWhere, filterKeys);
        Number num = results.size() > 0 ? (Number) ((Map) results.get(0)).get("numRows") : null;
        return num == null ? 0 : num.intValue();
    }

    public Object executeQuery(Connection connOrigen, String sqlAllCols, Object[] filter, MapListHandler resultSetHandler) {
        try {
            if (logger.isDebugEnabled())
                logger.debug("Executing:" + buildSQLLog(sqlAllCols, filter));
            return super.query(connOrigen, sqlAllCols, filter, resultSetHandler);
        } catch (SQLException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    public List findListOfMaps(Connection con, String sql, Object[] filterKeys) {
        try {
            if (logger.isDebugEnabled())
                logger.debug("Executing:" + buildSQLLog(sql, filterKeys));
            return (List) super.query(con, sql, filterKeys, new MapListHandler());
        } catch (SQLException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    public List findListOfArrays(Connection con, String sql, Object[] filterKeys) {
        try {
            if (logger.isDebugEnabled())
                logger.debug("Executing:" + buildSQLLog(sql, filterKeys));
            return (List) super.query(con, sql, filterKeys, new ArrayListHandler());
        } catch (SQLException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    public Map findUniqueMap(Connection con, String sql, Object[] filterKeys) {
        List maps = findListOfMaps(con, sql, filterKeys);
        return (Map) (maps.size() == 0 ? null : maps.get(0));
    }

    public Object findUniqueValue(Connection con, String sql, Object[] filterKeys) {
        Map map = findUniqueMap(con, sql, filterKeys);
        Iterator iter = map.values().iterator();
        return iter.hasNext() ? iter.next() : null;
    }


    public int execSqlUpdate(Connection connOrigen, String sqlAllCols, Object[] filter) {
        try {
            if (logger.isDebugEnabled())
                logger.debug("Executing:" + buildSQLLog(sqlAllCols, filter));
            return super.update(connOrigen, sqlAllCols, filter);
        } catch (SQLException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    public static String buildSQLLog(String sql, Object[] filterKeys) {
        StringBuffer buf = new StringBuffer();
        buf.append(sql);
        int idx = 0;
        while(buf.indexOf("?")>=0){
            int pos = buf.indexOf("?");
            buf.deleteCharAt(pos);
            Object filterKey = filterKeys[idx];
            if (filterKey instanceof String )
                filterKey = "'"+filterKey+"'"; 
            buf.insert(pos, filterKey);
            idx++;
        }
//        if (filterKeys != null) {
//            buf.append(" params:");
//            buf.append(Arrays.asList(filterKeys).toString());
//        }
        return buf.toString();
    }

}
