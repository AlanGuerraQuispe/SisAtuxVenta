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
package com.aw.core.db.support;

import com.aw.support.collection.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: User
 * Date: Oct 16, 2007
 */
public class WhereBuilder2 {
    StringBuffer sql = new StringBuffer();
    List params = new ArrayList();


    public WhereBuilder2(StringBuffer sql) {
        this.sql = sql;
    }

    public void filterForce(String sqlFiltro) {
        filter(BigDecimal.ONE, sqlFiltro, null);
    }
    public void filter(Object valor, String sqlFiltro) {
        filter(valor, sqlFiltro, new Object[]{valor} );
    }
    public void filterLike(Object valor, String sqlFiltro) {
        filter(valor, sqlFiltro, new Object[]{"%"+valor+"%"} );
    }

    public void filter(Object valor, String sqlFiltro, Object  filtro) {
        filter(valor, sqlFiltro, new Object[]{filtro} );
    }
    public void filter(Object valor, String sqlFiltro, Object[] filters) {
        if (valor== null ||
            (valor instanceof String && StringUtils.isEmpty((String) valor))){
            return;
        }
        boolean filterStartWithAND = sqlFiltro.toString().toLowerCase().trim().startsWith("and");
        if (!filterStartWithAND){
            boolean containsWhere = sql.toString().toLowerCase().indexOf(" where ") != -1;
            sql.append(containsWhere ?" and ":" where ");
        }

        sql.append(" ").append(sqlFiltro).append(" ");
        if (filters!=null)
            for (Object filter : filters) {
                if (filter instanceof Date)
                    filter = new java.sql.Date(((Date)filter).getTime());
                params.add(filter);
            }
    }

    public StringBuffer getSql() {
        return sql;
    }

    public List getParams() {
        return params;
    }

    public void setParams(SQLQuery sqlQuery) {
        for (int i = 0; i < params.size(); i++) {
            Object param = params.get(i);
            if      (param instanceof Long)                sqlQuery.setLong(i, (Long) param);
            else if (param instanceof Integer)             sqlQuery.setInteger(i, (Integer) param);
            else if (param instanceof Date)                sqlQuery.setDate(i, (Date) param);
            else if (param instanceof String)              sqlQuery.setString(i, (String) param);
            else if (param instanceof BigDecimal)          sqlQuery.setBigDecimal(i, (BigDecimal) param);
            else if (param == null)                         sqlQuery.setParameter(i, null);
            else
                throw new IllegalArgumentException("Implementar codigo param:"+param.getClass());
        }
        //To change body of created methods use File | Settings | File Templates.
    }

    public static String buildIn(Collection idList, boolean removeNulls) {
        if (removeNulls){
            List idListTrim = new ArrayList(idList.size());
            for (Object id : idList) {
                if (id!=null)
                    idListTrim.add(id);
            }
            idList = idListTrim;
        }
        if (idList.size()==0) return  null;

        boolean isNumber = idList.iterator().next() instanceof Number;
        StringBuffer result = isNumber
                ? ListUtils.concatenarSepPorComa(idList)
                :ListUtils.concatenarSepPorComaYApostrofe(idList);
        return result.toString();
    }
}