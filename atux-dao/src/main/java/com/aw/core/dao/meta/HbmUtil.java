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
package com.aw.core.dao.meta;

import org.hibernate.MappingException;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.IdentifierValue;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.tuple.IdentifierProperty;
import org.hibernate.type.Type;
import org.springframework.beans.BeanWrapperImpl;

import java.io.Serializable;
import java.util.Map;

//import org.hibernate.engine.f;

/**
 * User: JCM
 * Date: 15/10/2007
 */
public class HbmUtil implements Serializable{
    protected SessionFactory sessionFactory;

    public HbmUtil() {
    }

    public HbmUtil(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String getTableName(Class entityClass) {
        AbstractEntityPersister entityPersister = (AbstractEntityPersister) sessionFactory.getClassMetadata(entityClass);
        return entityPersister.getTableName();
    }
    public String getPkFieldName(Class entityClass) {
        //getRealClass(Object entity) sss
        AbstractEntityPersister entityPersister = (AbstractEntityPersister) sessionFactory.getClassMetadata(entityClass);
        return entityPersister.getIdentifierPropertyName();
    }

    public boolean isHbmEntity(Class entityClass) {
        AbstractEntityPersister entityPersister = (AbstractEntityPersister) sessionFactory.getClassMetadata(entityClass);
        return entityPersister!=null;
    }

    public String getTableColumnName(Class entityClass, String entityField) {
        AbstractEntityPersister entityPersister = (AbstractEntityPersister) sessionFactory.getClassMetadata(entityClass);
        if (entityPersister==null)
            throw new IllegalArgumentException("Class "+entityClass+ " is not an Hibernate entity class");
        String[] cols = entityPersister.toColumns(entityField);
        return cols != null && cols.length > 0 ? cols[0] : null;
    }

    public EntityPropertyMapper buildPropertyMapper(Class entityClass) {
        AbstractEntityPersister entityPersister = (AbstractEntityPersister) sessionFactory.getClassMetadata(entityClass);
        if (entityPersister==null)
            throw new IllegalArgumentException("Class "+entityClass+ " is not an Hibernate entity class");
        EntityPropertyMapper mapper = new EntityPropertyMapper(entityClass, entityPersister.getTableName());
        String[] propertyNames = entityPersister.getPropertyNames();
        for (String propertyName : propertyNames) {
            String[] columnNames = entityPersister.getPropertyColumnNames(propertyName);
            Type propertyType = entityPersister.getPropertyType(propertyName);
            mapper.put(propertyName, propertyType, columnNames);
        }
        String identifierPropertyName = entityPersister.getIdentifierPropertyName();
        String[] identifierColumnNames = entityPersister.getIdentifierColumnNames();
        Type identifierPropertyType = entityPersister.getIdentifierType();
        mapper.putId(identifierPropertyName, identifierPropertyType, identifierColumnNames);

        return mapper;
    }

    public IdentifierProperty getTableColumnIdentifier(Class entityClass, String entityField) {
        AbstractEntityPersister entityPersister = (AbstractEntityPersister) sessionFactory.getClassMetadata(entityClass);
        if (entityField.equalsIgnoreCase(entityPersister.getIdentifierPropertyName()))
            return entityPersister.getEntityMetamodel().getIdentifierProperty();
        else
            return null;
    }

    public Boolean isUnsaved(Object entity) {
        AbstractEntityPersister entityPersister = (AbstractEntityPersister) sessionFactory.getClassMetadata(entity.getClass());
        String identifierPropertyName = entityPersister.getIdentifierPropertyName();
        //IdentifierProperty identifierProperty = entityPersister.getEntityMetamodel().getIdentifierProperty();
        Serializable entityIdValue = (Serializable) new BeanWrapperImpl(entity).getPropertyValue(identifierPropertyName);
        IdentifierValue unSavedValue = entityPersister.getEntityMetamodel().getIdentifierProperty().getUnsavedValue();
        return unSavedValue.isUnsaved(entityIdValue);
    }


    public boolean existTableColumnName(Class entityClass, String entityField) {
        try {
            getTableColumnName(entityClass, entityField);
            return true;
        } catch (MappingException e) {
            return false;
        }
    }

    public AbstractEntityPersister getClasMetadata(Class entityClass) {
        if (entityClass ==null)throw new IllegalArgumentException("Class is null");
        AbstractEntityPersister entityPersister = (AbstractEntityPersister) sessionFactory.getClassMetadata(entityClass);
        //entityPersister.getPropertyNullability();
        return entityPersister;
    }

    public Map getAllClassMetadata() {
        return sessionFactory.getAllClassMetadata();
    }

    public Class getRealClass(Object entity) {
        if (entity instanceof HibernateProxy) {
            entity= ((HibernateProxy)entity).getHibernateLazyInitializer().getImplementation();
        }            
        return entity.getClass();
    }

}
