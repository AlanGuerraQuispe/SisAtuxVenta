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

import com.aw.core.dao.meta.HbmUtilFactory;
import com.aw.core.dao.sql.SelectAliasParser;
import com.aw.core.domain.AWBusinessException;
import com.aw.support.ObjectConverter;
import org.hibernate.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * User: Julio C. Macavilca
 * Date: 02/10/2007
 */
public class DAOHbm extends HibernateDaoSupport {
    private DAOHbmInterceptor interceptor;

    public void setInterceptor(DAOHbmInterceptor interceptor) {
        this.interceptor = interceptor;
    }

//    /**
//     * Metodo generico usado para crear una nueva instancia
//     *
//     * @param newInstance POJO culaquiera, si el pojo implementa
//     * @return la instancia creada, util para extraer el PK
//     */
//    public Object create(Object newInstance) {
//        // Grabar usando Hibernate
//        getHibernateTemplate().saveOrUpdate(newInstance);
//        return newInstance;
//    }

//    /**
//     * Metodo generico usado para actualizar instancia
//     *
//     * @param existentInstance POJO cualquiera
//     * @return la instancia actualzada
//     */
//    public Object update(Object existentInstance) {
//        // Grabar usando Hibernate
//        getHibernateTemplate().saveOrUpdate(existentInstance);
//        return existentInstance;
//    }
    public <T> T merge(T instance)  {
        try{
           return  instance = (T) getHibernateTemplate().merge(instance);
        }catch (RuntimeException ee){
            throw ee;
        }
    }

    public <T> T saveOrUpdate(T instance)  {
        T instanceOld = instance;

//        Boolean hasPk = instance instanceof Identifiable ? ((Identifiable)instance).getId()!=null : null;
//        boolean failMode = HbmFailMngr.isFailMode() && (hasPk == null || hasPk);
//        if (failMode)
//                instance = merge(instance);
        // Grabar usando Hibernate
        getHibernateTemplate().saveOrUpdate(instance);
//        if (failMode){
//            ClassMetadata metadata = getSessionFactory().getClassMetadata(instance.getClass());
//            String pkName = metadata.getIdentifierPropertyName();
//            BeanWrapperImpl wrapperNew = new BeanWrapperImpl(instance);
//            BeanWrapperImpl wrapperOld = new BeanWrapperImpl(instanceOld);
//            Object pkValue = wrapperNew.getPropertyValue(pkName);
//            wrapperOld.setPropertyValue(pkName, pkValue);
//            getHSession().evict(instanceOld);
//        }
        return instance;
    }
//    public Object save(Object instance) {
//        // Grabar usando Hibernate
//        getHibernateTemplate().save(instance);
//        return instance;
//    }

    //

    public <T> Collection<T> saveOrUpdate(Collection<T> instanceList) {
        for (Object anInstanceList : instanceList) {
            saveOrUpdate(anInstanceList);
        }
        return instanceList;
    }

    /**
     * Metodo generico, elimina una instancia
     *
     * @param existentInstance instancia a ser eliminada
     */
    public void delete(Object existentInstance) {
        getHibernateTemplate().delete(existentInstance);
    }

    /**
     * Metodo generico, elimina varias instancias
     *
     * @param existentCollection lista de instancias a ser eliminada
     */
    public void deleteAll(Collection existentCollection) {
        try{
            getHibernateTemplate().deleteAll(existentCollection);
        }catch (Throwable e){
            throw AWBusinessException.wrapUnhandledException(logger, e).setForceFailMode(true);
        }
    }

    /**
     * Carga un entity de hibernate
     *
     * @param entityClass POJO de Hibernate
     * @param id          Id
     * @return Un entity de hibernate
     */
    public <T> T load(Class<T> entityClass, Serializable id) {
        logger.debug("Load:" + entityClass + "  id:" + id);
        return (T) getHibernateTemplate().load(entityClass, id);
    }

    /**
     *  Tira una excepcion si no existe
     * @param entityClass
     * @param id
     * @return
     */
    public <T> T loadAndInitialize(Class<T> entityClass, Serializable id) {
        Object entity = load(entityClass, id);
        Hibernate.initialize(entity);
        return (T) entity;
    }


    private <T> T get(Class<T> aClass, Serializable pk) {
        return (T) getHibernateTemplate().get(aClass, pk);
    }

    /**
     * Retorna NULL si no existe
     * @param aClass
     * @param pk
     * @return
     */
    public <T> T getAndInitialize(Class<T> aClass, Serializable pk) {
        T entity = get(aClass, pk);
        if (entity != null)
            Hibernate.initialize(entity);
        return entity;

    }


    /**
     * Helper method, usado para buscar un registro unico
     *
     * @param hsql Hibernate SQL
     * @param pk   primary key
     * @return el primer objeto encontrado. La consutla deberia retornar uno solamente
     */
    public Object findUnique(String hsql, Object pk) {
        return findUnique(hsql, new Object[]{pk});
    }


    /**
     * Helper method, usado para buscar un registro unico
     *
     * @param hsql Hibernate SQL
     * @param pks  primary key
     * @return el primer objeto encontrado. La consutla deberia retornar uno solamente
     */
    public Object findUnique(String hsql, Object[] pks) {
        List list = getHibernateTemplate().find(hsql, pks);
        if (list.size() > 0) return list.get(0);
        return null;
    }

    /**
     * Helper method, usado para buscar un registro unico
     *
     * @param hsql Hibernate SQL
     * @param pks  primary key
     * @return el primer objeto encontrado. La consutla deberia retornar uno solamente
     */
    public Object findUniqueEx(String hsql, Object[] pks) {
        Object result = findUnique(hsql, pks);
        if (result == null)
            throw new AWBusinessException("El registro no existe. Pk:" + Arrays.asList(pks).toString());
        return result;
    }

    /**
     * Helper methos used to retrieve a list of objects
     *
     * @param hsql HSQL query
     * @return List of object
     */
    public List findList(String hsql) {
        return getHibernateTemplate().find(hsql);
    }

    /**
     * Helper methos used to retrieve a list of objects
     *
     * @param hsql      HSQL query
     * @param filterKey key used to restrict the search
     * @return List of object
     */
    public List findList(String hsql, Object filterKey) {
        return getHibernateTemplate().find(hsql, filterKey);
    }

    /**
     * Helper methods used to retrieve a list of objects
     *
     * @param hsql      HSQL query
     * @param filterKey key used to restrict the search
     * @return List of object
     */
    public List findList(String hsql, Object[] filterKey) {
        return getHibernateTemplate().find(hsql, filterKey);
    }

    /**
     * Same as {@link #findList}, but the return type of each row is indicated.
     * select  corresp as domain,
     * sa.fechaIniBuscaCorrespDocs as fechaInicio,
     * sa.fechaFinBuscaCorrespDocs as fechaFin
     * from CorrespDocsImpl sa
     * LEFT OUTER JOIN sa.corresp corresp
     *
     * @param hsql       HSQL query
     * @param filterKey  key used to restrict the search
     * @param returnType class used to wrap row results
     *                   Alias of each column is used
     *                   Supported  Map.class wrap as map
     *                   BNXXXX.class bean uses getter/setter
     * @return List of objects of the type indicated
     */
    public List findListEx(String hsql, Object[] filterKey, Class returnType) {
        List listOfArrays = getHibernateTemplate().find(hsql, filterKey);
        SelectAliasParser parser = listOfArrays.size() == 0 ? null : new SelectAliasParser(hsql);
        List listOfBeans = new ArrayList();
        try {
            for (Object rowArray : listOfArrays) {
                String[] aliases = parser.getAliases();
                Object rowObject = returnType.newInstance();
                BeanWrapper wrap = new BeanWrapperImpl(rowObject);
                if (aliases.length==1 && !rowObject.getClass().isArray()  ){
                    //work around "select p from XXImpl p", retorna directamene el objeto en lugar de un array
                    rowArray = new Object[]{rowArray};
                }

                for (int i = 0; i < aliases.length; i++) {
                    String alias = aliases[i];
                    // work arounf for hibernate
                    Object value = aliases.length==1 &&!rowArray.getClass().isArray()
                                ?rowArray:((Object[]) rowArray)[i];
                    
                    Class type = wrap.getPropertyType(alias);
                    if (type==null)
                            throw new IllegalAccessException("El alias '"+alias+"' no es propertyName valido para "+returnType);
//                    logger.info("Erro al setter for " + alias + " en el registro " + i);
                    Object newValue = ObjectConverter.convert(value, type);
                    wrap.setPropertyValue(alias, newValue);
                }
                listOfBeans.add(rowObject);
            }
        } catch (Exception e) {
            throw AWBusinessException.wrapUnhandledException(logger, e).setForceFailMode(true);
        }
        return listOfBeans;
    }

    /**
     * Flush Hibernate
     */
    public void flush() {
        try{
            getHibernateTemplate().flush();
        }catch (Throwable e){
            throw AWBusinessException.wrapUnhandledException(logger, e).setForceFailMode(true);
        }
    }


    public Connection getHibernateConnection() {
        try {
            return null;
        } catch (HibernateException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e).setForceFailMode(true);
        }
    }

    public Session getHSession() {
        //aw-framework
        return super.getSession();
    }

    public SessionFactory getHSessionFactory() {
        return super.getSessionFactory();
    }

    public Object executeQuery(DAOExecuter daoExecuter) {
        try {
            return daoExecuter.execute(getHibernateConnection());
        } catch (SQLException e) {
            throw AWBusinessException.wrapUnhandledException(logger,e).setForceFailMode(true);
        }
    }

    public int executeUpdate(Object entity, List<String> fieldNames, List fieldValues,
                             List<String> pkNames, List pkValues) {

        getSession().evict(entity);
        boolean setPkCols = pkNames == null || pkValues == null;
        if (pkNames == null) pkNames = new ArrayList<String>();
        if (pkValues == null) pkValues = new ArrayList();
        if (setPkCols) {
            String pkFiledName = HbmUtilFactory.newInstance().getPkFieldName(HbmUtilFactory.newInstance().getRealClass(entity));
            BeanWrapper wrapper = new BeanWrapperImpl(entity);
            pkNames.add(pkFiledName);
            pkValues.add(wrapper.getPropertyValue(pkFiledName));
        }


        if (interceptor != null){
            fieldNames = new ArrayList(fieldNames);   // reemplazar listas actuales, por listas modificables
            fieldValues = new ArrayList(fieldValues);
            interceptor.onUpdateFields(entity.getClass(), entity, fieldNames, fieldValues, pkNames, pkValues);
        }
        String hqlUpdate = "update " + HbmUtilFactory.newInstance().getRealClass( entity ).getName() + " p ";
        StringBuffer hqlSet = new StringBuffer();
        for (String fieldName : fieldNames) {
            if (hqlSet.length() > 0) hqlSet.append(",");
            hqlSet.append("p.").append(fieldName).append(" = ?");
        }
        StringBuffer hqlWhere = new StringBuffer();
        for (String pkName : pkNames) {
            if (hqlWhere.length() > 0) hqlWhere.append(" AND ");
            hqlWhere.append("p.").append(pkName).append(" = ?");
        }
        String hql = hqlUpdate + " set " + hqlSet + " where " + hqlWhere;
        logger.debug("SQL UpdateFields1:" + hql);
        Query query = getHSession().createQuery(hql);

        List<String> allFields = new ArrayList<String>();
        allFields.addAll(fieldNames);
        allFields.addAll(pkNames);

        List allValues = new ArrayList();
        allValues.addAll(fieldValues);
        allValues.addAll(pkValues);

        for (int i = 0; i < allValues.size(); i++) {
            String fieldName =  allFields.get(i);
            Object fieldValue = allValues.get(i);
            logger.debug("SQL UpdateFields setting :" + i  + " "+fieldName+"=" + fieldValue);
            query.setParameter(i , fieldValue);
        }
        logger.debug("SQL UpdateFields:" + hql + " :: " + allValues);

        return query.executeUpdate();
    }

    public int executeUpdate(Object entity, String[] fields) {
        BeanWrapper wrapper = new BeanWrapperImpl(entity);
        List fieldValues = new ArrayList();
        for (String field : fields)
            fieldValues.add(wrapper.getPropertyValue(field));
        List<String> fieldNames = Arrays.asList(fields);
        return executeUpdate(entity, fieldNames, fieldValues, null, null);
    }

    public Serializable save(Object entity){
        return getHibernateTemplate().save(entity);        
    }

    public List findByNamedQueryAndNamedParam(String nameQuery, String namedParam, String value){
        return getHibernateTemplate().findByNamedQueryAndNamedParam(nameQuery, namedParam, value);
    }

    public List find(String query) {
        return getHibernateTemplate().find(query);
    }

    public List listAbortable(SQLQuery sqlQuery) {
        ScrollableResults ss = sqlQuery.scroll(ScrollMode.FORWARD_ONLY);
        AWQueryAbortable queryAbortable = AWQueryAbortable.instance();
        List results = new ArrayList(AWQueryAbortable.DEF_LIST_SIZE);
        queryAbortable.resetRowCount();
        while (ss.next()){
            if (queryAbortable.isAborted()) break;
            results.add(ss.get());
            queryAbortable.incRowCount();
        }
        return results;
    }
}
