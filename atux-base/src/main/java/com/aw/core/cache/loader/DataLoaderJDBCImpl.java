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
package com.aw.core.cache.loader;

import com.aw.core.cache.dropdown.MappableList;
import com.aw.core.spring.ApplicationBase;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: JCM
 * Date: 09/10/2007
 */
public class DataLoaderJDBCImpl extends DBDataLoader {
    private String sqlQuery;
    private Class rowClass;

    public DataLoaderJDBCImpl(String sqlQuery, Class rowClass) {
        this.sqlQuery = sqlQuery;
        this.rowClass = rowClass;
    }

    public Object load() {
        if (logger.isInfoEnabled())
            logger.info("DataLoaderJDBCImpl[" + loaderName + "] executing sqlQuery:" + sqlQuery);

        int count = StringUtils.countOccurrencesOf(sqlQuery, "?");
        int paramsLength = sqlParams != null ? sqlParams.length : 0;
        if (count != paramsLength) {
            List newSqlParams = new ArrayList();
            if (count > paramsLength) {
                if (sqlParams != null) {
                    newSqlParams = new ArrayList(Arrays.asList(sqlParams));
                } else {
                    newSqlParams = new ArrayList();
                }
                for (int i = 0; i < count - paramsLength; i++) {
                    newSqlParams.add(null);
                }
            } else {
                int indexIni = paramsLength - count;
                for (int i = indexIni; i < paramsLength; i++) {
                    newSqlParams.add(sqlParams[i]);
                }
            }
            sqlParams = newSqlParams.toArray();
        }
        sqlParams = prepareSqlParams(sqlParams);
        List listBeans = new ArrayList();
        if (sqlParams==null || !existNullsIn(sqlParams)){
            DataSource dataSource = ApplicationBase.instance().getBean(DataSource.class);
            JdbcTemplate template = new JdbcTemplate(dataSource);
            listBeans = template.query(sqlQuery, sqlParams, new BeanPropertyRowMapper(rowClass));
        }

        // hacer que el resultado sea compatible con DropDown mappable lists
        MappableList mappableList = new MappableList(loaderName, listBeans.size());
        mappableList.addAll(listBeans);
        if (logger.isDebugEnabled())
            logger.info("DataLoaderJDBCImpl[" + loaderName + "]  Results:" + mappableList);
        return mappableList;
    }

    private boolean existNullsIn(Object[] sqlParams) {
        for (Object sqlParam : sqlParams) {
            if (sqlParam==null){
                return true;
            }
        }
        return false;
    }

    protected Object[] prepareSqlParams(Object[] sqlParams) {
        return sqlParams;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public Class getRowClass() {
        return rowClass;
    }

    public boolean isDataRelated(Object dataSourceReference) {
        if (sqlQuery.toUpperCase().contains(dataSourceReference.toString().toUpperCase()))
            return true;
        return false;
    }

}
                                