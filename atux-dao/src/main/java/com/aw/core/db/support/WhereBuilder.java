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

import com.aw.core.dao.bean.DAOSqlColumn;
import com.aw.core.dao.meta.Like;
import com.aw.core.dao.meta.Temporal;
import com.aw.core.dao.meta.Upper;
import com.aw.core.db.RPUtils;
import com.aw.support.ObjectConverter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * User: User
 * Date: Oct 16, 2007
 */
public class WhereBuilder {

    Object bean;
    protected BeanWrapper target;
    private Map dispatchAttribute;
    private String alias;
    List columns = new ArrayList();

    public WhereBuilder(Object bean) {
        this.bean = bean;
        target = new BeanWrapperImpl(bean);
        dispatchAttribute = new HashMap();
    }

    public WhereBuilder(String alias, Object bean) {
        this(bean);
        this.alias = alias;
    }

    public void execute() {

        Object[] fields = bean.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String fieldName = ((Field) (fields[i])).getName();

            if ( ((Field) (fields[i])).isAnnotationPresent(Temporal.class)) {
                continue;
            }
            Object columnValue = target.getPropertyValue(fieldName);

            if (columnValue != null && !StringUtils.isEmpty(columnValue.toString())) {
                if (columnValue instanceof Number) {
                    if (ObjectConverter.convertToint(columnValue) == -1)
                        continue;
                }
                buildColumn((Field) (fields[i]), columnValue);
            }
        }

    }

    public void buildColumn(Field field, Object columnValue) {
        String columnName = "";


        Method method = target.getPropertyDescriptor(field.getName()).getReadMethod();


        if (field.isAnnotationPresent(DAOSqlColumn.class)) {
            DAOSqlColumn column = field.getAnnotation(DAOSqlColumn.class);
            columnName = column.value();
        } else {
            if (dispatchAttribute.containsKey(field.getName())) {
                columnName = (String) dispatchAttribute.get(field.getName());
            } else {
                columnName = field.getName();
            }
        }
        boolean isLike = false;
        if (field.isAnnotationPresent(Like.class)) {
            columnValue = "%" + columnValue + "%";
            isLike = true;
        }
        boolean isUpper = false;
        if (field.isAnnotationPresent(Upper.class)) {
            //columnName = "upper("+columnName+")";
            isUpper = true;
        }
        columns.add(new Column(field.getName(), columnName, columnValue, isLike, isUpper ));
    }

    public StringBuffer getSql() {
        StringBuffer sql = new StringBuffer();
        boolean assignFecha = false;
        for (int i = 0; i < columns.size(); i++) {
            Column column = (Column) columns.get(i);
            String columnName = column.getColumnName();
            Object columnValue = column.getValue();
            if ((columnName.equalsIgnoreCase("feRangoInicial") || columnName.equalsIgnoreCase("feRangoFinal"))) {
                if (!assignFecha) {
                    sql.append(getCustomDate());
                    i++;
                    assignFecha = true;
                }

            } else {
                if (alias != null && alias.length() != 0) {
                    columnName = alias + "." + columnName;
                }
                if (!column.isLike()) {
                    sql.append(" " + columnName + " = ");
                } else {
                    sql.append(" " + columnName + " LIKE ");
                }
                if (columnValue instanceof Number) {
                    sql.append(columnValue);
                } else {
                    sql.append("\'").append(columnValue).append("\'");
                }

            }
            if ((i + 1) < columns.size()) {
                sql.append(" AND ");
            }
        }
        return sql;
    }

    public List getFilter(StringBuffer hql, List params) {
        execute();
        StringBuffer sqlWithArgs=  getSqlWithArgs();
        if (sqlWithArgs.length()>0){
            boolean containsWhere = containsWhere(hql);
            hql.append(containsWhere?" and ":" where ");
            hql.append(sqlWithArgs);
            params.addAll(getParametersList());
        }
        return params;
    }

    public boolean containsWhere(StringBuffer hql) {
        return hql.toString().toLowerCase().indexOf("where")!=-1;
    }

    public StringBuffer getSqlWithAnd() {
        StringBuffer sql = getSql();
        if (sql.length() > 0) {
            return new StringBuffer(" AND ").append(sql);
        } else {
            return new StringBuffer();
        }
    }


    public StringBuffer getSqlWithArgs() {
        StringBuffer sql = new StringBuffer();
        boolean asignFecha = false;
        for (int i = 0; i < columns.size(); i++) {
            Column column = (Column) columns.get(i);
            String columnName = column.getColumnName();
            if ((columnName.equalsIgnoreCase("feRangoInicial") || columnName.equalsIgnoreCase("feRangoFinal"))) {
                if (!asignFecha) {
                    i++;
                    sql.append(getCustomDate());
                    asignFecha = true;
                }

            } else {
                if (alias != null && alias.length() != 0) {
                    columnName = alias + "." + columnName;
                }
                if (column.isUpper())
                    columnName = "upper("+columnName+")";
                
                if (!column.isLike()) {
                    sql.append(" " + columnName + " = ? ");
                } else {
                    sql.append(" " + columnName + " LIKE ? ");
                }

            }
            if ((i + 1) < columns.size()) {
                sql.append(" AND ");
            }
        }
        return sql;
    }

    public Object[] getParameters() {

        List parameters = getParametersList();
        return parameters.isEmpty() ? null : parameters.toArray();
    }

    public List getParametersList() {
        List parameters = new ArrayList();
        for (Iterator iterator = columns.iterator(); iterator.hasNext();) {
            Column column = (Column) iterator.next();
            String columnName = column.getColumnName();
            if ((columnName.equalsIgnoreCase("feRangoInicial") || columnName.equalsIgnoreCase("feRangoFinal"))) {
                continue;
            }
            parameters.add(column.getValue());
        }
        return parameters;
    }

    public void registerMatch(String fieldName, String attribute) {
        dispatchAttribute.put(fieldName, attribute);
    }

    public void registerColumnFecha(String attribute) {
        dispatchAttribute.put("fecha", attribute);
    }


    private StringBuffer getCustomDate() {
        StringBuffer sql = new StringBuffer();
        if (columns.indexOf(new Column("feRangoInicial")) != -1 && columns.indexOf(new Column("feRangoFinal")) != -1) {
            Date fechaInicial = (Date) ((Column) columns.get(columns.indexOf(new Column("feRangoInicial")))).getValue();
            Date fechaFinal = (Date) ((Column) columns.get(columns.indexOf(new Column("feRangoFinal")))).getValue();
            String columnName = (String) dispatchAttribute.get("fecha");
            if (alias != null && alias.length() != 0) {
                columnName = alias + "." + columnName;
            }
            
            RPUtils.agregarFechaRangoFiltro(sql, columnName, fechaInicial, fechaFinal);
        }
        return sql;
    }

    public boolean existsColumn() {
        return columns.size() > 0;
    }


    class Column {
        private Object value;
        private String columnName;
        private String fieldName;
        private boolean like;
        private boolean upper;


        public Column(String fieldName) {
            this.fieldName = fieldName;
        }

        public Column(String fieldName, String columnName, Object value, boolean like, boolean isUpper) {
            this.fieldName = fieldName;
            this.value = value;
            this.columnName = columnName;
            this.like = like;
            this.upper= isUpper;
        }


        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }


        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }


        public boolean isLike() {
            return like;
        }

        public void setLike(boolean like) {
            this.like = like;
        }

        public boolean isUpper() {
            return upper;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Column column = (Column) o;

            if (!fieldName.equals(column.fieldName)) return false;

            return true;
        }

        public int hashCode() {
            return fieldName.hashCode();
        }
    }

}
