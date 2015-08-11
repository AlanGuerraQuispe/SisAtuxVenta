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
package com.aw.core.dao.bean;

import java.util.List;

/**
 * Clase utilitaria para implementar {@link DAOBeanInterceptor}
 * User: JCM
 * Date: 19/10/2007
 */
public class DAOBeanInterceptorEmpty implements DAOBeanInterceptor {
    /**
     * Intercepta la carga del bean desde BD
     *
     * @param bean bean
     * @param id   id que tiene el bean
     * @return el objeto que se retorna al cliente,
     *         (usualmente debe ser el mismo objeto como parametro)
     */
    public Object onLoad(Object bean, Object id) {
        return bean;
    }

    /**
     * Llamado antes de crear y ejecutar el SQL update
     *
     * @param bean
     * @param propertyNamesUpdated
     * @return el nuevo bean a usar para generar la sentencia SQL
     *         (deberia ser el mismo pasado como parametro)
     */
    public Object onUpdate(Object bean, List propertyNamesUpdated) {
        return bean;
    }

    /**
     * Permite ignorar columnas que no deben ser actualizadas
     * @param tableName
     * @param columnName
     * @return TRUE Si la columna debe ser ignorada en la sentencia UPDATE
     */
    public boolean skipColUpdate(String tableName, String columnName) {
        return false;
    }

    /**
     * Llamado antes de crear y ejecutar el SQL delete
     *
     * @param bean
     * @return el nuevo bean a usar para generar la sentencia SQL
     *         (deberia ser el mismo pasado como parametro)
     */
    public Object onDelete(Object bean) {
        return bean;
    }

    /**
     * Llamado antes de crear y ejecutar el SQL insert
     *
     * @param bean
     * @return el nuevo bean a usar para generar la sentencia SQL
     *         (deberia ser el mismo pasado como parametro)
     */
    public Object onSave(Object bean) {
        return bean;
    }
}
