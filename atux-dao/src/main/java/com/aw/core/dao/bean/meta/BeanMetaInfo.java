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
package com.aw.core.dao.bean.meta;

import com.aw.core.dao.bean.*;
import com.aw.core.dao.meta.HbmUtil;
import com.aw.core.dao.meta.HbmUtilFactory;
import com.aw.core.domain.AWBusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.tuple.IdentifierProperty;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * User: JCM
 * Date: 15/10/2007
 */
public class BeanMetaInfo {
    protected final Log logger = LogFactory.getLog(getClass());

    public static boolean isBean(Object bean) {
        if (bean == null)
            throw new IllegalArgumentException("Bean is null");
        return bean.getClass().getAnnotation(DAOSqlTable.class) != null ||
                bean.getClass().getAnnotation(DAOHbmTable.class) != null ||
                bean.getClass().getAnnotation(DAOHbmTableEx.class) != null;
    }

    //protected Object bean;
    protected Class beanClass;

    protected HbmUtil hbmUtil;


    protected Class mainHbmEntity;
    protected String mainTable;
    protected String mainSufijoCorto;
    protected String mainSufijoLargo;

    protected Map<String, ColumnInfo> nonIdColumnsMap = new HashMap<String, ColumnInfo>();

    protected Map<String, ColumnInfo> idColumnsMap = new HashMap<String, ColumnInfo>();

    /**
     * Intentionally marked as datosAdicionales level
     *
     * @param beanClass
     */
    BeanMetaInfo(Class beanClass) {
        if (beanClass == null)
            throw new IllegalArgumentException("BeanClass is NULL");
        this.beanClass = beanClass;
        build();
    }

    private void build() {
        //beanClass = bean.getClass();
        hbmUtil = HbmUtilFactory.newInstance();

        // datos a nivel de clase
        DAOSqlTable daoSqlTable = (DAOSqlTable) beanClass.getAnnotation(DAOSqlTable.class);
        DAOHbmTable daoHbmTable = (DAOHbmTable) beanClass.getAnnotation(DAOHbmTable.class);
        DAOHbmTableEx daoHbmTableEx = (DAOHbmTableEx) beanClass.getAnnotation(DAOHbmTableEx.class);
        if (daoSqlTable != null) {
            mainTable = daoSqlTable.value();
        } else if (daoHbmTable != null) {
            mainHbmEntity = daoHbmTable.value();
            mainTable = hbmUtil.getTableName(mainHbmEntity);
        } else if (daoHbmTableEx != null) {
            mainHbmEntity = daoHbmTableEx.tabla();
            mainTable = hbmUtil.getTableName(mainHbmEntity);
            mainSufijoCorto = daoHbmTableEx.sufijoCorto();
            mainSufijoLargo = daoHbmTableEx.sufijoLargo();
        }

        // datos a nivel de campo/atributo
        Field[] fields = beanClass.getFields();
        if (fields.length == 0)
            throw new IllegalArgumentException("Class " + beanClass + " need to have public atributes");
        for (Field field : fields) {
            ColumnInfo columnInfo = null;
            DAOSqlColumn daoSqlColumn = field.getAnnotation(DAOSqlColumn.class);
            DAOHbmColumn daoHbmColumn = field.getAnnotation(DAOHbmColumn.class);
            DAOHbmColumnEx daoHbmColumnEx = field.getAnnotation(DAOHbmColumnEx.class);
            if (daoSqlColumn != null) {
                columnInfo = buildSqlColumn(field.getName(), daoSqlColumn.value());
            } else if (daoHbmColumn != null) {
                columnInfo = buildHbmColumn(field.getName(), mainHbmEntity, daoHbmColumn.value());
            } else if (daoHbmColumnEx != null) {
                columnInfo = buildHbmColumn(field.getName(), daoHbmColumnEx.table(), daoHbmColumnEx.column());
            } else {
                // tratar de hacer match con atributos de hibernate
                columnInfo = buildHbmColumn(field.getName(), mainHbmEntity, field.getName());
            }

            if (columnInfo.isPk() && columnInfo.getTable().equals(mainTable))
                idColumnsMap.put(columnInfo.getBeanField(), columnInfo);
            else
                nonIdColumnsMap.put(columnInfo.getBeanField(), columnInfo);

        }


    }


    private ColumnInfo buildSqlColumn(String beanField, String columnName) {
        ColumnInfo columnInfo = new ColumnInfo(beanField);
        if (columnName.indexOf('.') != -1) {
            int idx = columnName.indexOf('.');
            columnInfo.table = columnName.substring(0, idx - 1);
            columnName = columnName.substring(idx + 1);
        } else {
            if (mainTable == null)
                throw new AWBusinessException("Missing MainTable config for Bean " + beanClass.getName() + " field:" + beanField + " column:" + columnName);
        }
        columnInfo.column = columnName;
        columnInfo.idInfo = null;
        return columnInfo;
    }

    private ColumnInfo buildHbmColumn(String beanField, Class entityClass, String entityField) {
        if (entityClass == null)
            throw new AWBusinessException("Missing MainEntity config for Bean " + beanClass.getName() + " field:" + beanField + " hbm field:" + entityField);
        ColumnInfo columnInfo = new ColumnInfo(beanField);
        columnInfo.table = mainTable != null ? mainTable : hbmUtil.getTableName(entityClass);
        boolean exist = false;
        if (exist = hbmUtil.existTableColumnName(entityClass, entityField)) {
            //ok
        } else if (mainSufijoCorto != null && entityField.endsWith(mainSufijoCorto)) {
            // verificar logica de sufijos
            String newEntityField = entityField.substring(0, entityField.length() - mainSufijoCorto.length()) + mainSufijoLargo;
            if (exist = hbmUtil.existTableColumnName(entityClass, newEntityField))
                entityField = newEntityField;
        }
        if (!exist)
            throw new AWBusinessException("Attribute " + entityField + " on entity " + entityClass + " does not exist");

        columnInfo.column = hbmUtil.getTableColumnName(entityClass, entityField);
        columnInfo.idInfo = hbmUtil.getTableColumnIdentifier(entityClass, entityField);
        return columnInfo;
    }


    public String getMainTable() {
        return mainTable;
    }

    public ColumnInfo getColumn(String beanField) {
        ColumnInfo columnInfo = nonIdColumnsMap.get(beanField);
        if (columnInfo == null) columnInfo = idColumnsMap.get(beanField);
        return columnInfo;
    }

    public Map<String, ColumnInfo> getIdColumnsMap() {
        return idColumnsMap;
    }

    public Collection<ColumnInfo> getIdColumns() {
        return idColumnsMap.values();
    }

    public Map<String, ColumnInfo> getNonIdColumnsMap() {
        return nonIdColumnsMap;
    }

    public Collection<ColumnInfo> getNonIdColumns() {
        return nonIdColumnsMap.values();
    }

    public Class getMainHbmEntity() {
        return mainHbmEntity;
    }

    public Collection<ColumnInfo> getAllColumns() {
        Collection<ColumnInfo> collection = new ArrayList<ColumnInfo>();
        collection.addAll(idColumnsMap.values());
        collection.addAll(nonIdColumnsMap.values());
        return collection;
    }


    public class ColumnInfo {
        private String beanField;
        private String table;
        private String column;
        private IdentifierProperty idInfo;

        public ColumnInfo(String beanField) {
            this.beanField = beanField;
        }

        public String getBeanField() {
            return beanField;
        }

        public String getTable() {
            return table;
        }

        public String getColumn() {
            return column;
        }

        public boolean isPk() {
            return idInfo != null;
        }

        public IdentifierProperty getIdInfo() {
            return idInfo;
        }

    }

}
