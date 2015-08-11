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
package com.aw.support.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.support.SortDefinition;

import java.util.*;


/**
 * PropertyComparator performs a comparison of two beans,
 * using the specified bean property via a BeanWrapper.
 *
 * @author Juergen Hoeller
 * @author Jean-Pierre Pawlak
 * @note this class is the same a copy of the Spring classs
 * the only change was to made protected :
 * - method {@link #getPropertyValue(java.lang.Object)}
 * - attribute {@link #sortDefinition}
 * - custom comparation for dates {@link #compare(java.lang.Object,java.lang.Object)}
 * @since 19.05.2003
 */
public class PropertyComparator implements Comparator {

    protected final Log logger = LogFactory.getLog(getClass());

    protected final SortDefinition sortDefinition;

    private final Map cachedBeanWrappers = new HashMap();


    public PropertyComparator(SortDefinition sortDefinition) {
        this.sortDefinition = sortDefinition;
    }

    /**
     * @return a negative integer, zero, or a positive integer as the
     *         first argument is less than, equal to, or greater than the
     *         second.
     */
    public int compare(Object o1, Object o2) {
        Object v1 = getPropertyValue(o1);
        Object v2 = getPropertyValue(o2);
        if (this.sortDefinition.isIgnoreCase() && (v1 instanceof String) && (v2 instanceof String)) {
            v1 = ((String) v1).toLowerCase();
            v2 = ((String) v2).toLowerCase();
        }
        int result;

        // Put a null property at the end of the sort.
        try {
            if (v1 != null) {
                if (v2 != null) {
                    if (v1 instanceof Date && v2 instanceof Date)
                        result = (((Date) v1).getTime() < ((Date) v2).getTime() ? -1 :
                                (((Date) v1).getTime() == ((Date) v2).getTime() ? 0 : 1));
                    else
                        result = ((Comparable) v1).compareTo(v2);
                } else {
                    result = -1;
                }
            } else {
                if (v2 != null) {
                    result = 1;
                } else {
                    result = 0;
                }
            }
        }
        catch (RuntimeException ex) {
            if (logger.isWarnEnabled()) {
                logger.warn("Could not sort objects [" + o1 + "] and [" + o2 + "]", ex);
            }
            return 0;
        }
        return (this.sortDefinition.isAscending() ? result : -result);
    }

    /**
     * Get the SortDefinition's property value for the given object.
     *
     * @param obj the object to get the property value for
     * @return the property value
     */
    protected Object getPropertyValue(Object obj) {
        BeanWrapper bw = (BeanWrapper) this.cachedBeanWrappers.get(obj);
        if (bw == null) {
            bw = new BeanWrapperImpl(obj);
            this.cachedBeanWrappers.put(obj, bw);
        }

        // If a nested property cannot be read, simply return null
        // (similar to JSTL EL). If the property doesn't exist in the
        // first place, let the exception through.
        try {
            return bw.getPropertyValue(this.sortDefinition.getProperty());
        }
        catch (BeansException ex) {
            logger.info("PropertyComparator could not access property - treating as null for sorting", ex);
            return null;
        }
    }


    /**
     * Sort the given List according to the given sort definition.
     * <p>Note: Contained objects have to provide the given property
     * in the form of a bean property, i.e. a getXXX method.
     *
     * @param source         the input List
     * @param sortDefinition the parameters to sort by
     * @throws java.lang.IllegalArgumentException
     *          in case of a missing propertyName
     */
    public static void sort(List source, SortDefinition sortDefinition) throws BeansException {
        Collections.sort(source, new org.springframework.beans.support.PropertyComparator(sortDefinition));
    }

    /**
     * Sort the given source according to the given sort definition.
     * <p>Note: Contained objects have to provide the given property
     * in the form of a bean property, i.e. a getXXX method.
     *
     * @param source         input source
     * @param sortDefinition the parameters to sort by
     * @throws java.lang.IllegalArgumentException
     *          in case of a missing propertyName
     */
    public static void sort(Object[] source, SortDefinition sortDefinition) throws BeansException {
        Arrays.sort(source, new org.springframework.beans.support.PropertyComparator(sortDefinition));
    }

    public static boolean safeEquals(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) return true;
        if (obj1 != null) return obj1.equals(obj2);
        else return obj2.equals(obj1);
    }
}
