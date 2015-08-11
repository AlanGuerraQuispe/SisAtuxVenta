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
import com.aw.core.dao.bean.meta.BeanMetaInfoBuilder;
import com.aw.core.domain.AWBusinessException;
import org.springframework.beans.BeanWrapperImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: JCM
 * Date: 15/10/2007
 */
public class BeanSqlBuilderQueryImpl extends BeanSqlBuilder implements Serializable {
    //protected final Log logger = LogFactory.getLog(getClass());

    StringBuffer sqlCols = new StringBuffer();
    StringBuffer sqlWhere = new StringBuffer();
    StringBuffer sqlOrderBy = new StringBuffer();
    List params = new ArrayList();

    public BeanSqlBuilderQueryImpl(Class beanClass, DAOHbm daoHbm, DAOSql daoSQL) {
        super(daoHbm, daoSQL);
        metaInfo = BeanMetaInfoBuilder.instance().build(beanClass);
        beanWrap = new BeanWrapperImpl(beanClass);
    }

    /**
     * Ejecuta SQL Select
     */
    public Object load(Object uniqueId) {
        if (metaInfo.getIdColumns().size() != 1)
            throw new AWBusinessException("El bean " + beanWrap.getWrappedClass() + " debe tener una llave primaria para usar este metodo");

        for (BeanMetaInfo.ColumnInfo columnInfo : metaInfo.getIdColumns()) {
            if (sqlWhere.length() > 0) sqlWhere.append(" AND ");
            BeanSqlBuilderQueryImpl.buildColEqValue(columnInfo, uniqueId, sqlWhere, params);
        }
        List results = queryList();
        Object bean = results.size() == 0 ? null : results.get(0);
        if (interceptor != null) bean = interceptor.onLoad(bean, uniqueId);
        return bean;
    }

    /**
     * Ejecuta SQL Select
     */
    public List queryList() {

        StringBuffer sqlFrom = new StringBuffer().append("FROM ").append(metaInfo.getMainTable());
        buildSqlCols();
        String fullSql = "SELECT " + sqlCols + " " + sqlFrom;
        if (sqlWhere.length() > 0) fullSql += " WHERE " + sqlWhere;

        List list = execSqlQuery(fullSql, params.toArray());
        return list;
    }


    private void buildSqlCols() {
        for (BeanMetaInfo.ColumnInfo columnInfo : metaInfo.getAllColumns()) {
            if (sqlCols.length() > 0) sqlCols.append(",");
            sqlCols.append(BeanSqlBuilderQueryImpl.buildColName(columnInfo));
            sqlCols.append(" as \"").append(columnInfo.getBeanField()).append("\"");
        }
    }


    public void setSqlWhere(StringBuffer sqlWhere, List sqlParams) {
        this.sqlWhere = sqlWhere;
        this.params = sqlParams;
    }

    public void setSqlOrderBy(StringBuffer sqlOrderBy) {
        this.sqlOrderBy = sqlOrderBy;
    }

    public BeanSqlBuilderQueryImpl setInterceptor(DAOBeanInterceptor interceptor) {
        this.interceptor = interceptor;
        return this;

    }
}
