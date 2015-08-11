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


import com.aw.core.dao.bean.sql.criterion.BeanSqlCriterion;
import com.aw.core.dao.bean.sql.criterion.BeanSqlOrder;

import java.util.List;

/**
 * User: JCM
 * Date: 18/10/2007
 */
public interface BeanSqlCriteria {
    /**
     * Add a {@link BeanSqlCriterion restriction} to constrain the results to be
     * retrieved.
     *
     * @param criterion The {@link BeanSqlCriterion criterion} object representing the
     *                  restriction to be applied.
     * @return this (for method chaining)
     */
    public BeanSqlCriteria add(BeanSqlCriterion criterion);

    /**
     * Add an {@link BeanSqlOrder ordering} to the result set.
     *
     * @param order The {@link BeanSqlOrder order} object representing an ordering
     *              to be applied to the results.
     * @return this (for method chaining)
     */
    public BeanSqlCriteria addOrder(BeanSqlOrder order);

    /**
     * Get the results.
     *
     * @return The list of matched query results.
     */
    public List list();

    /**
     * Convenience method to return a single instance that matches
     * the query, or null if the query returns no results.
     *
     * @return the single result or <tt>null</tt>
     */
    public Object uniqueResult();
}
