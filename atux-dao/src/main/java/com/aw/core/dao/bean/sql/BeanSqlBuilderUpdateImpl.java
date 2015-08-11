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

import com.aw.core.dao.DAOExecuter;
import com.aw.core.dao.DAOHbm;
import com.aw.core.dao.DAOSql;
import com.aw.core.dao.bean.DAOBeanInterceptor;
import com.aw.core.dao.bean.meta.BeanMetaInfo;
import com.aw.core.dao.bean.meta.BeanMetaInfoBuilder;
import com.aw.core.domain.AWBusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.engine.spi.IdentifierValue;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.BeanWrapperImpl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//import org.hibernate.engine.IdentifierValue;
//import org.hibernate.engine.SessionImplementor;

/**
 * User: JCM
 * Date: 15/10/2007
 */
public class BeanSqlBuilderUpdateImpl extends BeanSqlBuilder {
    protected final Log logger = LogFactory.getLog(getClass());

    private Object bean;

    public BeanSqlBuilderUpdateImpl(DAOSql daoSQL, DAOHbm daoHbm, Object bean) {
        super(daoHbm, daoSQL);
        this.bean = bean;
        metaInfo = BeanMetaInfoBuilder.instance().build(bean.getClass());
        beanWrap = new BeanWrapperImpl(bean);

    }

//    public BeanSqlBuilderUpdateImpl(Object bean) {
//    }


    /**
     * Ejecuta un insert o update
     *
     * @return la misma instancia
     */
    public Object saveOrUpdate() {
        boolean unsaved = isUnsaved();
        if (unsaved) save();
        else update();
        return bean;
    }

    /**
     * Verifica si el bean existe en la BD
     * Actualemente se utiliza la Estrategia "IdAssigned"
     *
     * @return TRUE si existe, FALSE si no existe
     */
    public boolean isUnsaved() {
        for (BeanMetaInfo.ColumnInfo columnInfo : metaInfo.getIdColumns()) {
            IdentifierValue identifierValue = columnInfo.getIdInfo().getUnsavedValue();
            Serializable id = (Serializable) beanWrap.getPropertyValue(columnInfo.getBeanField());
            if (identifierValue.isUnsaved(id)) return true;
        }
        return false;
    }

    private void generateIdentifierIfNeeded() {
        for (BeanMetaInfo.ColumnInfo columnInfo : metaInfo.getIdColumns()) {
            IdentifierValue identifierValue = columnInfo.getIdInfo().getUnsavedValue();
            Serializable id = (Serializable) beanWrap.getPropertyValue(columnInfo.getBeanField());
            if (identifierValue.isUnsaved(id)) {
                IdentifierGenerator identifierGenerator = columnInfo.getIdInfo().getIdentifierGenerator();
                Serializable nuevoId = generateIdentifier(identifierGenerator);
                beanWrap.setPropertyValue(columnInfo.getBeanField(), nuevoId);
            }
        }
    }

    protected Serializable generateIdentifier(final IdentifierGenerator identifierGenerator) {
        Serializable nuevoId = (Serializable) daoHbm.executeQuery(new DAOExecuter() {
            public Object execute(Connection connection) {
                SessionImplementor sessionImplementor = (SessionImplementor) daoHbm.getHSession();
                Serializable idSerializable = identifierGenerator.generate(sessionImplementor, null);
                return idSerializable;
            }
        });
        return nuevoId;
    }

    /**
     * Ejecuta SQL update
     * @return el bean con los atributos modificados
     */
    public Object update() {
        return updateFields(null);
    }

    /**
     * Ejecuta un SQL Update 
     * @param propertyNamesToUpdate Lista de columnas a actualizar. Si es NULO se actualizan todas
     * @return el bean con los atributos modificados
     */
    public Object updateFields(Collection propertyNamesToUpdate) {
        List propertyNamesUpdatedXIntercetor = new ArrayList();
        if (interceptor != null) bean = interceptor.onUpdate(bean, propertyNamesUpdatedXIntercetor);
        ArrayList<Object> params = new ArrayList<Object>();
        // UPDATE
        StringBuffer sqlUpdate = new StringBuffer().append("UPDATE ").append(metaInfo.getMainTable());
        StringBuffer sqlSet = new StringBuffer();

        // SET
        // SET Armar subset de columnas si se indica
        Collection<BeanMetaInfo.ColumnInfo> nonIdColumnInfoList =
                new ArrayList<BeanMetaInfo.ColumnInfo>(metaInfo.getNonIdColumns());
        if (propertyNamesToUpdate!=null){
            for (BeanMetaInfo.ColumnInfo columnInfo : metaInfo.getNonIdColumns()) {
                if (propertyNamesToUpdate.contains(columnInfo.getBeanField()) ||
                    propertyNamesUpdatedXIntercetor.contains(columnInfo.getBeanField())){
                    // skip, columna debe ser actualizada
                }else{
                    // remover de colleccion
                    nonIdColumnInfoList.remove(columnInfo);
                }
            }
        }

        // SET armar sentencia SET
        for (BeanMetaInfo.ColumnInfo columnInfo : nonIdColumnInfoList) {
            if (interceptor != null && interceptor.skipColUpdate(columnInfo.getTable(), columnInfo.getColumn()))
                continue;
            if (sqlSet.length() > 0) sqlSet.append(", ");
            buildColEqValue(columnInfo, sqlSet, params);
        }

        // WHERE
        StringBuffer sqlWhere = new StringBuffer();
        for (BeanMetaInfo.ColumnInfo columnInfo : metaInfo.getIdColumns()) {
            if (sqlWhere.length() > 0) sqlWhere.append(" AND ");
            buildColEqValue(columnInfo, sqlWhere, params);
        }
        // Unir
        String fullSql = sqlUpdate + " SET " + sqlSet + " WHERE " + sqlWhere;
        int rows = execSqlUpdate(fullSql, params.toArray());
        if (rows != 1)
            throw new AWBusinessException("Se esperaba actualizar un registro. Registros actualizados:" + rows);
        return bean;
    }

    public void delete() {
        if (interceptor != null) bean = interceptor.onDelete(bean);
        StringBuffer sqlUpdate = new StringBuffer().append("DELETE FROM ").append(metaInfo.getMainTable());
        ArrayList<Object> params = new ArrayList<Object>();
        StringBuffer sqlWhere = new StringBuffer();
        for (BeanMetaInfo.ColumnInfo columnInfo : metaInfo.getIdColumns()) {
            if (sqlWhere.length() > 0) sqlWhere.append(" AND ");
            buildColEqValue(columnInfo, sqlWhere, params);
        }
        String fullSql = sqlUpdate + " WHERE " + sqlWhere;
        int rows = execSqlUpdate(fullSql, params.toArray());
        if (rows != 1)
            throw new AWBusinessException("Se esperaba actualizar un registro. Registros actualizados:" + rows);
    }

    /**
     * Ejecuta SQL insert
     */
    public Object save() {
        if (interceptor != null) bean = interceptor.onSave(bean);
        generateIdentifierIfNeeded();

        StringBuffer sqlInsert = new StringBuffer().append("INSERT INTO ").append(metaInfo.getMainTable());
        StringBuffer sqlCols = new StringBuffer();
        StringBuffer sqlValues = new StringBuffer();
        ArrayList<Object> params = new ArrayList<Object>();
        for (BeanMetaInfo.ColumnInfo columnInfo : metaInfo.getAllColumns()) {
            if (sqlCols.length() > 0) {
                sqlCols.append(",");
                sqlValues.append(",");
            }
            sqlCols.append(BeanSqlBuilderUpdateImpl.buildColName(columnInfo));
            buildColValue(columnInfo, sqlValues, params);
        }
        String fullSql = sqlInsert + " (" + sqlCols + ") VALUES(" + sqlValues + ")";
        execSqlUpdate(fullSql, params.toArray());
        return bean;
    }


    public BeanSqlBuilderUpdateImpl setInterceptor(DAOBeanInterceptor interceptor) {
        this.interceptor = interceptor;
        return this;
    }
}
