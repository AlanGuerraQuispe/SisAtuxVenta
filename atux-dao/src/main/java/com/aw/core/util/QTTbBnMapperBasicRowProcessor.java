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
package com.aw.core.util;

import com.aw.core.dao.AWQueryAbortable;
import com.aw.core.dao.AWQueryExecuter;
import org.apache.commons.dbutils.BasicRowProcessor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * User: Manuel Flores
 * Date: 11/10/2007
 */
public class QTTbBnMapperBasicRowProcessor extends BasicRowProcessor implements AWQueryExecuter.RowHandler {
    private final QTTablaABeanMapper beanMapper;
    private BeanDataRowProvider dataRowProvider = null;
    private Class resultType;

    public QTTbBnMapperBasicRowProcessor(QTTablaABeanMapper beanMapper){
        this.beanMapper = beanMapper;
    }

    public QTTbBnMapperBasicRowProcessor() {
        this(new QTTablaABeanMapper());
    }

    /**
     * Convert a <code>ResultSet</code> row into a JavaBean.  This
     * implementation delegates to a BeanProcessor instance.
     * @see org.apache.commons.dbutils.RowProcessor#toBean(java.sql.ResultSet, java.lang.Class)
     * @see org.apache.commons.dbutils.BeanProcessor#toBean(java.sql.ResultSet, java.lang.Class)
     */
    public Object toBean(ResultSet rs, Class type) throws SQLException {
        initForBean(rs);
        return beanMapper.buildNewBean(type, dataRowProvider);
    }

    public List toBeanList(final ResultSet rs, Class type) throws SQLException {
        initForBean(rs);

        AWQueryAbortable queryAbortable = AWQueryAbortable.instance().resetRowCount();
        List results = new ArrayList();
        do {
            if (queryAbortable.isAborted()) break;
            results.add(beanMapper.buildNewBean(type, dataRowProvider));
            queryAbortable.incRowCount();

        } while (rs.next());

        return results;
    }

    private void initForBean(ResultSet rs) throws SQLException {
        if (!beanMapper.isMetatadaBuilt()){
            ResultSetMetaData metaData = rs.getMetaData();
            List colNames = new ArrayList();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                String colName = metaData.getColumnName(i + 1);
                colNames.add(colName);
            }

            beanMapper.buildMetadata(colNames);
            dataRowProvider = new BeanDataRowProvider(rs);
        }
    }

    public List toBeanList(HashMap[] listOfMaps, Class type) {
        List results = new ArrayList(listOfMaps.length);
        if (listOfMaps.length > 0) {
            beanMapper.buildMetadata(listOfMaps[0].keySet());
        }

        AWQueryAbortable queryAbortable = AWQueryAbortable.instance().resetRowCount();
        MapDataRowProvider dataRowProvider = new MapDataRowProvider();
        for (int i = 0; i < listOfMaps.length; i++) {
            if (queryAbortable.isAborted()) break;
            dataRowProvider.row = listOfMaps[i];
            results.add(beanMapper.buildNewBean(type, dataRowProvider));
            queryAbortable.incRowCount();
        }
        return results;
    }

    public List toBeanList(List listOfArrays, Class type, String[] setterNames) {
        List results = new ArrayList(listOfArrays.size());
        ArrayDataRowProvider dataRowProvider = new ArrayDataRowProvider();
        Map nametoIdx = dataRowProvider.buildNametoIdx(setterNames);
        beanMapper.buildMetadata(nametoIdx.keySet());

        AWQueryAbortable queryAbortable = AWQueryAbortable.instance().resetRowCount();
        for (Iterator iterator = listOfArrays.iterator(); iterator.hasNext();) {
            if (queryAbortable.isAborted()) break;
            dataRowProvider.row = (Object[]) iterator.next();
            results.add(beanMapper.buildNewBean(type, dataRowProvider));
            queryAbortable.incRowCount();
        }
        return results;
    }

    @Override
    public Object handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
        return toBean(rs, resultType);
    }

    public QTTbBnMapperBasicRowProcessor setResultType(Class resultType) {
        this.resultType = resultType;
        return this;
    }

    private static class BeanDataRowProvider implements QTTablaABeanMapper.DataRowProvider {
        ResultSet rs;

        private BeanDataRowProvider(ResultSet rs) {
            this.rs = rs;
        }

        public Object getObject(String colName) throws Exception {
            Object value = rs.getObject(colName);
//            if (value instanceof Date)
//                value = rs.getTimestamp(colName);
            return value;
        }
    }

    private static class MapDataRowProvider implements QTTablaABeanMapper.DataRowProvider {
        Map row;

        public Object getObject(String colName) throws Exception {
            return row.get(colName);
        }
    }

    private static class ArrayDataRowProvider implements QTTablaABeanMapper.DataRowProvider {
        Object[] row;
        Map nameToIdx;

        public Object getObject(String colName) throws Exception {
            Integer idx = (Integer) nameToIdx.get(colName);
            return row[idx.intValue()];
        }

        public Map buildNametoIdx(Object[] row) {
            nameToIdx = new HashMap();
            for (int i = 0; i < row.length; i++) {
                Integer idx = new Integer(i);
                nameToIdx.put(row[i], idx);
            }
            return nameToIdx;
        }
    }
}
