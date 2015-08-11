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
package com.aw.core.dao.bean.sql.criterion;

import com.aw.core.dao.bean.sql.BeanSqlCriteria;

import java.util.Arrays;
import java.util.List;

/**
 * User: JCM
 * Date: 18/10/2007
 */
public class BeanSqlRestrictions {
    static public BeanSqlCriterion sqlRestriction(String sql, Object[] params) {
        return new BeanSqlCriterionFullWhere(sql, params);

    }

    public static class BeanSqlCriterionFullWhere implements BeanSqlCriterion {
        String sql;
        Object[] params;

        public BeanSqlCriterionFullWhere(String sql, Object[] params) {
            this.sql = sql;
            this.params = params;
        }

        public String toSqlString(BeanSqlCriteria criteria, List params) {
            if (this.params != null) params.addAll(Arrays.asList(this.params) );
            return sql;
        }
    }
}
