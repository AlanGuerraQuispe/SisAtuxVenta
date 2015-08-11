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
 * Interceptor usado por {@link com.aw.core.dao.DAOBean}
 * Permite modificar los objetos
 * - antes de ser enviados a la BD
 * - despues de load()
 * Se recomienda usar {@link DAOBeanInterceptorEmpty}
 * User: JCM
 * Date: 19/10/2007
 */
public interface DAOBeanInterceptor {
    /**
     * Intercepta la carga del bean desde BD
     *
     * @param bean bean
     * @param id   id que tiene el bean
     * @return el objeto que se retorna al cliente,
     *         (usualmente debe ser el mismo objeto como parametro)
     */
    Object onLoad(Object bean, Object id);

    /**
     * Llamado antes de crear y ejecutar el SQL update
     *
     * @param bean
     * @param propertyNamesUpdated llena los atributos cambiados
     * @return el nuevo bean a usar para generar la sentencia SQL
     *         (deberia ser el mismo pasado como parametro)
     */
    Object onUpdate(Object bean, List propertyNamesUpdated);

    /**
     * Permite ignorar columnas que no deben ser actualizadas
     * @param tableName
     * @param columnName
     * @return TRUE Si la columna debe ser ignorada en la sentencia UPDATE
     */
    boolean skipColUpdate(String tableName, String columnName);

    /**
     * Llamado antes de crear y ejecutar el SQL delete
     *
     * @param bean
     * @return el nuevo bean a usar para generar la sentencia SQL
     *         (deberia ser el mismo pasado como parametro)
     */
    Object onDelete(Object bean);

    /**
     * Llamado antes de crear y ejecutar el SQL insert
     *
     * @param bean
     * @return el nuevo bean a usar para generar la sentencia SQL
     *         (deberia ser el mismo pasado como parametro)
     */
    Object onSave(Object bean);

}
