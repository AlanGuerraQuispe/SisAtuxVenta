package com.aw.core.dao.meta;

import org.hibernate.type.Type;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * User: Julio C. Macavilca
 * Date: 08/01/2008
 */
public class EntityPropertyMapper {
    private Class entityClass;
    private String tableName;

    private Map<String, String[]> propertyToColumnsMap = new HashMap<String, String[]>();
    private Map<String, Type> propertyToTypeMap = new HashMap<String, Type>();
    private Map<String, String> columnToPropertyMap = new HashMap<String, String>();
    private String identifierPropertyName;
    public EntityPropertyMapper(Class entityClass, String tableName) {
        this.entityClass = entityClass;
        this.tableName = tableName;
    }

    public void put(String propertyName, Type propertyType, String[] columnNames) {
        propertyToColumnsMap.put(propertyName, columnNames);
        propertyToTypeMap.put(propertyName, propertyType);
        for (String columnName : columnNames) {
            columnToPropertyMap.put(columnName, propertyName);
        }
    }
    public void putId(String identifierPropertyName, Type identifierPropertyType, String[] identifierColumnNames) {
        this.identifierPropertyName = identifierPropertyName;
        put(identifierPropertyName, identifierPropertyType, identifierColumnNames);
    }


    public String columnToProperty(String columnName) {
        return columnToPropertyMap.get(columnName);
    }

    public Type propertyType(String propertyName) {
        return propertyToTypeMap.get(propertyName);
    }
    public String[] propertyToColumn(String columnName) {
        return propertyToColumnsMap.get(columnName);
    }

    public String getTableName() {
        return tableName;
    }

    public String getIdentifierPropertyName() {
        return identifierPropertyName;
    }

    public PropertyIterator  iterator() {
        return new PropertyIterator();
    }
    public class PropertyIterator implements Iterator {
        Iterator propertyNames = propertyToColumnsMap.keySet().iterator();
        String currentPropertyName;

        public boolean hasNext() {
            return propertyNames.hasNext();
        }

        public Object next() {
            currentPropertyName = (String) propertyNames.next();
            return currentPropertyName;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public String[] getPropertyColumnNames() {
            return propertyToColumnsMap.get(currentPropertyName);
        }

        public Type getPropertyType() {
            return propertyToTypeMap.get(currentPropertyName);
        }
    }


}
