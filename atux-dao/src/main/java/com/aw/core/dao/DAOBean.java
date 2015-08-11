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
package com.aw.core.dao;

import com.aw.core.dao.bean.DAOBeanInterceptor;
import com.aw.core.dao.bean.sql.BeanSqlBuilderQueryImpl;
import com.aw.core.dao.bean.sql.BeanSqlBuilderUpdateImpl;
import com.aw.core.dao.bean.sql.BeanSqlCriteria;
import com.aw.core.dao.bean.sql.BeanSqlCriteriaImpl;

import java.io.Serializable;

/**
 * Clase utilitaria para extender el uso de hibernate a
 * Beans con anotaciones en sus atributos. Se extiende el soporte en
 * - Beans con anotaciones para mapear a hibernate
 * - Beans con anotaciones para mapear a la BD directamente
 * <p/>
 * El bean debe tener alguna las siguientes anotaciones
 * A Nivel de Clase:
 * - {@link com.aw.core.dao.bean.DAOHbmTable} metadata de hibernate
 * - {@link com.aw.core.dao.bean.DAOSqlTable} metadada en la anotacion
 * <p/>
 * A Nivel de Atributo PUBLICO:                       Clase requiere
 * - {@link com.aw.core.dao.bean.DAOHbmColumn}        DAOHbmTable
 * - {@link com.aw.core.dao.bean.DAOHbmColumnEx}
 * - {@link com.aw.core.dao.bean.DAOSqlColumn}        DAOHbmTable o DAOSqlTable
 * <p/>
 * Ejemplos de formato de los Beans en TestBeanMetaInfo (ver inner beans)
 * <p/>
 * Pendientes
 * INSERT debe autogenerar
 * <p/>
 * <p/>
 * User: Julio C. Macavilca
 * Date: 02/10/2007
 */
public class DAOBean {

    DAOSql daoSQL;
    DAOHbm daoHbm;

    public DAOBean(DAOSql daoSQL, DAOHbm daoHbm) {
        this.daoSQL = daoSQL;
        this.daoHbm = daoHbm;
    }

    //protected final Log logger = LogFactory.getLog(getClass());
    protected DAOBeanInterceptor interceptor;

    public BeanSqlCriteria createCriteria(Class beanClass) {
        return new BeanSqlCriteriaImpl(beanClass, new BeanSqlBuilderQueryImpl(beanClass, daoHbm, daoSQL));
    }

    /**
     * @param beanClass
     * @param id
     * @return el bean desde BD
     */
    public Object load(Class beanClass, Serializable id) {
        Object bean = new BeanSqlBuilderQueryImpl(beanClass, daoHbm, daoSQL)
                .setInterceptor(interceptor)
                .load(id);
        return bean;
    }

    /**
     * Ejecuta inteligentemente un INSERT O UPDATE
     *
     * @param bean
     * @return el mismo bean actualizado
     */
    public Object saveOrUpdate(Object bean) {
        return new BeanSqlBuilderUpdateImpl(daoSQL, daoHbm, bean)
                .setInterceptor(interceptor)
                .saveOrUpdate();
    }

    /**
     * Ejecuta un INSERT
     *
     * @param bean
     * @return el mismo bean actualizado
     */
    public Object save(Object bean) {
        return new BeanSqlBuilderUpdateImpl(daoSQL, daoHbm, bean)
                .setInterceptor(interceptor)
                .save();
    }

    /**
     * Ejecuta un INSERT
     *
     * @param bean
     * @return el mismo bean actualizado
     */
    public Object update(Object bean) {
        return new BeanSqlBuilderUpdateImpl(daoSQL, daoHbm, bean)
                .setInterceptor(interceptor)
                .update();
    }

    public void delete(Object bean) {
        new BeanSqlBuilderUpdateImpl(daoSQL, daoHbm, bean)
                .setInterceptor(interceptor)
                .delete();
    }

    public void setInterceptor(DAOBeanInterceptor interceptor) {
        this.interceptor = interceptor;
    }
}
