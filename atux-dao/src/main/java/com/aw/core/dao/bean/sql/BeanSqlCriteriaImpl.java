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

import com.aw.core.dao.bean.meta.BeanMetaInfo;
import com.aw.core.dao.bean.meta.BeanMetaInfoBuilder;
import com.aw.core.dao.bean.sql.criterion.BeanSqlCriterion;
import com.aw.core.dao.bean.sql.criterion.BeanSqlOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: JCM
 * Date: 18/10/2007
 */
public class BeanSqlCriteriaImpl implements BeanSqlCriteria, Serializable {
    protected Class beanClass;
    private List<BeanSqlCriterion> criterions = new ArrayList<BeanSqlCriterion>();
    private List<BeanSqlOrder> orders = new ArrayList<BeanSqlOrder>();
    protected BeanSqlBuilderQueryImpl sqlBuilderQuery;

    public BeanSqlCriteriaImpl(Class beanClass, BeanSqlBuilderQueryImpl sqlBuilderQuery) {
        this.beanClass = beanClass;
        this.sqlBuilderQuery = sqlBuilderQuery;
    }

    /**
     * Add a {@link com.aw.core.dao.bean.sql.criterion.BeanSqlCriterion restriction} to constrain the results to be
     * retrieved.
     *
     * @param criterion The {@link com.aw.core.dao.bean.sql.criterion.BeanSqlCriterion criterion} object representing the
     *                  restriction to be applied.
     * @return this (for method chaining)
     */
    public BeanSqlCriteria add(BeanSqlCriterion criterion) {
        if (criterion == null)
            throw new IllegalArgumentException("Criterion is null");
        criterions.add(criterion);
        return this;
    }

    /**
     * Add an {@link com.aw.core.dao.bean.sql.criterion.BeanSqlOrder ordering} to the result set.
     *
     * @param order The {@link com.aw.core.dao.bean.sql.criterion.BeanSqlOrder order} object representing an ordering
     *              to be applied to the results.
     * @return this (for method chaining)
     */
    public BeanSqlCriteria addOrder(BeanSqlOrder order) {
        orders.add(order);
        return this;
    }

    /**
     * Get the results.
     *
     * @return The list of matched query results.
     */
    public List list() {
        StringBuffer sqlWhere = new StringBuffer();
        List sqlParams = new ArrayList();
        for (BeanSqlCriterion criterion : criterions) {
            if (sqlWhere.length() > 0) sqlWhere.append(" AND ");
            String filter = criterion.toSqlString(this, sqlParams);
            filter = replaceFieldsByCols(filter);
            sqlWhere.append(filter);
        }

        StringBuffer sqlOrderBy = new StringBuffer();
        for (BeanSqlOrder order : orders) {
            if (sqlOrderBy.length() > 0) sqlWhere.append(", ");
            String sqlOrder = order.toSqlString(this);
            sqlOrder = replaceFieldsByCols(sqlOrder);
            sqlOrderBy.append(sqlOrder);
        }

        sqlBuilderQuery.setSqlWhere(sqlWhere, sqlParams);
        sqlBuilderQuery.setSqlOrderBy(sqlOrderBy);
        return sqlBuilderQuery.queryList();
    }

    /**
     * Convenience method to return a single instance that matches
     * the query, or null if the query returns no results.
     *
     * @return the single result or <tt>null</tt>
     */
    public Object uniqueResult() {
        List listResult = list();
        return listResult.size() == 0 ? null : listResult.get(0);
    }

    public String replaceFieldsByCols(String beanQL) {
        BeanMetaInfo metaInfo = BeanMetaInfoBuilder.instance().build(beanClass);
        //StringBuffer sql = new StringBuffer(beanQL);
        for (BeanMetaInfo.ColumnInfo columnInfo : metaInfo.getAllColumns()) {
            beanQL = replaceFieldByCol(beanQL, columnInfo);
        }
        return beanQL;
    }

    private String replaceFieldByCol(String sql, BeanMetaInfo.ColumnInfo columnInfo) {
        String fieldName = columnInfo.getBeanField();
        String colName = BeanSqlBuilder.buildColName(columnInfo);
        sql = sql.replaceAll(fieldName, colName);
        return sql;
    }


}
