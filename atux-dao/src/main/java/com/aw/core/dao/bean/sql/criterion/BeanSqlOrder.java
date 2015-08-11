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

import com.aw.core.dao.bean.sql.BeanSqlCriteriaImpl;

/**
 * User: JCM
 * Date: 18/10/2007
 */
public class BeanSqlOrder {
    private String propertyName;
    private boolean ascending;

    public String toString() {
        return propertyName + ' ' + (ascending ? "asc" : "desc");
    }

    /**
     * Constructor for Order.
     */
    protected BeanSqlOrder(String propertyName, boolean ascending) {
        this.propertyName = propertyName;
        this.ascending = ascending;
    }

    /**
     * Render the SQL fragment
     */
    public String toSqlString(BeanSqlCriteriaImpl beanSqlCriteria) {
//        String[] columns = criteriaQuery.getColumnsUsingProjection(criteria, propertyName);
//        Type type = criteriaQuery.getTypeUsingProjection(criteria, propertyName);
//        StringBuffer fragment = new StringBuffer();
//        for ( int i=0; i<columns.length; i++ ) {
//            SessionFactoryImplementor factory = criteriaQuery.getFactory();
//            boolean lower = ignoreCase && type.sqlTypes( factory )[i]== Types.VARCHAR;
//            if (lower) {
//                fragment.append( factory.getDialect().getLowercaseFunction() )
//                    .append('(');
//            }
//            fragment.append( columns[i] );
//            if (lower) fragment.append(')');
//            fragment.append( ascending ? " asc" : " desc" );
//            if ( i<columns.length-1 ) fragment.append(", ");
//        }
//        return fragment.toString();
        return propertyName + (ascending ? " asc" : " desc");
    }

    /**
     * Ascending order
     *
     * @param propertyName
     * @return Order
     */
    public static BeanSqlOrder asc(String propertyName) {
        return new BeanSqlOrder(propertyName, true);
    }

    /**
     * Descending order
     *
     * @param propertyName
     * @return Order
     */
    public static BeanSqlOrder desc(String propertyName) {
        return new BeanSqlOrder(propertyName, false);
    }


}
