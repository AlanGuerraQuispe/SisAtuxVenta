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
package com.aw.core.util;


import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.NullValueInNestedPathException;

/**
 * @author jcmacavilca
 *         16/12/2004
 */
public class EntityMatcher {
    Class classFilter;
    Object[] codeToSearch;
    String[] entityIdNames;

    boolean useLikeMatch = false;

    boolean useNumberComparator = false;

    /**
     * Compares if the value compared is greater than the specified
     */
    boolean useGreaterThanComparator;

    BeanWrapper wrap;

    private boolean ignoreNullsNestedPath = false;

    /**
     * Create a mutable Object overriden the equals(Object) method.
     * This object is used everywhere an object cab bet tested by
     * the equals method.
     * common use: list.indexOf( mutableObject ) where
     * mutableObject is an object created byt this method
     *
     * @param entityIdNames id names used in comparison support dot notation to access inner classes
     */
    public EntityMatcher(String[] entityIdNames) {
        this.entityIdNames = entityIdNames;
    }

    /**
     * Create a mutable Object overriden the equals(Object) method.
     * This object is used everywhere an object cab bet tested by
     * the equals method.
     * common use: list.indexOf( mutableObject ) where
     * mutableObject is an object created byt this method
     *
     * @param entityIdName id names used in comparison support dot notation to access inner classes
     */
    public EntityMatcher(String entityIdName) {
        this(new String[]{entityIdName});
    }


    public boolean equals(Object obj) {
        if (codeToSearch == null) {
            throw new IllegalStateException("Ids no especificados");
        }

        if (wrap == null) {
            wrap = classFilter == null ? new BeanWrapperImpl() : new BeanWrapperImpl(classFilter);
        }
        // check for clas filter
        if (classFilter != null && (obj == null || obj.getClass() != classFilter))
            return false;

        //set current instance
        wrap= new BeanWrapperImpl(obj);
        boolean match = true;
        Object propValue;
        for (int i = 0; i < codeToSearch.length && match; i++) {
            propValue = getPropertyValue(entityIdNames[i]);
            match = compareNumbers(propValue, codeToSearch[i]);
        }

        return match;
    }

    private Object getPropertyValue(String entityIdName) {
        Object propValue;
        try {
            propValue = wrap.getPropertyValue(entityIdName);
        } catch (NullValueInNestedPathException e) {
            if (ignoreNullsNestedPath) propValue = null;
            else throw e;
        }
        return propValue;
    }

    private boolean compareNumbers(Object id, Object valueToCompare) {
        boolean match;
        if (id == null && valueToCompare == null) return true;
        if (useLikeMatch && (id instanceof String) && (valueToCompare instanceof String))
            match = ((String) id).startsWith((String) valueToCompare);
        else if (useNumberComparator && (id instanceof Number) && (valueToCompare instanceof Number))
            match = ((Number) id).doubleValue() == ((Number) valueToCompare).doubleValue();
        else if (useGreaterThanComparator && (id instanceof Number) && (valueToCompare instanceof Number))
            match = ((Number) id).doubleValue() > ((Number) valueToCompare).doubleValue();
        else if (id == null)
            match = valueToCompare == null;
        else
            match = id.equals(valueToCompare);
        return match;

    }

    public void setClassFilter(Class classFilter) {
        this.classFilter = classFilter;
    }

    public void setUseLikeMatch(boolean useLikeMatch) {
        this.useLikeMatch = useLikeMatch;
    }

    public EntityMatcher setUseNumberComparator(boolean useNumberComparator) {
        this.useNumberComparator = useNumberComparator;
        return this;
    }

    public EntityMatcher useGreaterThanComparator() {
        this.useGreaterThanComparator = true;
        return this;
    }


    public EntityMatcher setSearchIds(Object[] codeToSearch) {
        this.codeToSearch = codeToSearch;
        return this;
    }

    public EntityMatcher setSearchId(Object codeToSearch) {
        this.codeToSearch = new Object[]{codeToSearch};
        return this;
    }

    public String[] getEntityIdNames() {
        return entityIdNames;
    }

    public EntityMatcher setIgnoreNullsNestedPath(boolean ignoreNullsNestedPath) {
        this.ignoreNullsNestedPath = ignoreNullsNestedPath;
        return this;
    }
}

