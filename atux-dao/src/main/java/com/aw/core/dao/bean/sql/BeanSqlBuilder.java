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
package com.aw.core.dao.bean.sql;

import com.aw.core.dao.DAOHbm;
import com.aw.core.dao.DAOSql;
import com.aw.core.dao.bean.DAOBeanInterceptor;
import com.aw.core.dao.bean.meta.BeanMetaInfo;
import com.aw.core.util.QTTbBnMapperBasicRowProcessor;
import com.aw.support.ObjectConverter;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: JCM
 * Date: 15/10/2007
 */
public class BeanSqlBuilder {
    protected final Log logger = LogFactory.getLog(getClass());

    DAOSql daoSQL;
    DAOHbm daoHbm;

    protected BeanMetaInfo metaInfo = null;
    protected BeanWrapper beanWrap = null;
    protected DAOBeanInterceptor interceptor;

    public BeanSqlBuilder(DAOHbm daoHbm, DAOSql daoSQL) {
        this.daoHbm = daoHbm;
        this.daoSQL = daoSQL;
    }

    protected List execSqlQuery(String fullSql, Object[] params) {
        Class beanClass = beanWrap.getWrappedClass();
        return (List) daoSQL.executeQuery(fullSql, params, new BeanListHandler(beanClass, new QTTbBnMapperBasicRowProcessor()));
    }

    protected int execSqlUpdate(String fullSql, Object[] params) {
        return daoSQL.execSqlUpdate(fullSql, params);
    }

    protected void buildColEqValue(BeanMetaInfo.ColumnInfo columnInfo, StringBuffer sqlPart, ArrayList<Object> params) {
        sqlPart.append(buildColName(columnInfo));
        sqlPart.append("=");
        buildColValue(columnInfo, sqlPart, params);
    }

    protected static void buildColEqValue(BeanMetaInfo.ColumnInfo columnInfo, Object colValue, StringBuffer sqlPart, List params) {
        sqlPart.append(buildColName(columnInfo));
        sqlPart.append("=");
        sqlPart.append("?");
        params.add(colValue);
    }

    protected void buildColValue(BeanMetaInfo.ColumnInfo columnInfo, StringBuffer sqlPart, ArrayList<Object> params) {
        Object value = beanWrap.getPropertyValue(columnInfo.getBeanField());
        // El type se podria sacar de Hibernate
        Class type = beanWrap.getPropertyType(columnInfo.getBeanField());
        if (type == Date.class) type = java.sql.Timestamp.class;
        Object convertedValue = ObjectConverter.convert(value, type);
        sqlPart.append("?");
        params.add(convertedValue);
    }

    public static String buildColName(BeanMetaInfo.ColumnInfo columnInfo) {
        return columnInfo.getColumn();
    }


}
