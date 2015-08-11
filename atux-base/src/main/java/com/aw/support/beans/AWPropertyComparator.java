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

import com.aw.core.format.Formatter;
import org.springframework.beans.support.SortDefinition;


/**
 * @author jcvergara
 *         15/11/2004
 *         Add the support for formatted values to the PropertyComparator
 */
public class AWPropertyComparator extends PropertyComparator {
    protected Formatter formatter;

    public AWPropertyComparator(SortDefinition sortDefinition, Formatter formatter) {
        super(sortDefinition);
        if (sortDefinition == null)
            throw new IllegalArgumentException("SortDefinition cannot be null. Check code");
        this.formatter = formatter;
    }

    /**
     * Get the SortDefinition's property value for the given object.
     *
     * @param obj the object to get the property value for
     * @return the property value
     */
    protected Object getPropertyValue(Object obj) {
        Object convertedPropertyValue = null;
        try {
            if (obj instanceof Object[]) {
                Object[] row = (Object[]) obj;
                convertedPropertyValue = row[Integer.parseInt(sortDefinition.getProperty())];
            } else {
                convertedPropertyValue = super.getPropertyValue(obj);
            }
            convertedPropertyValue = convertToComparableValue(obj, convertedPropertyValue);
        } catch (Throwable e) {
            logger.error("Error getting value for:" +sortDefinition.getProperty() +" value:" + convertedPropertyValue + " formater:" + formatter + " Exception:" + e);
            convertedPropertyValue = "ERR:" + convertedPropertyValue;
        }
        return convertedPropertyValue;
    }

    private Object convertToComparableValue(Object bean, Object propertyValue) {
        if (formatter != null && !formatter.onSortingPreferOriginalValue()) {
            propertyValue = formatter.format(bean, sortDefinition.getProperty(), propertyValue);
        }
        return propertyValue;
    }

}
