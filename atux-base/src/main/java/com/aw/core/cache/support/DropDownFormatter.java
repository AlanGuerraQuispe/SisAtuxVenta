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
package com.aw.core.cache.support;

import com.aw.core.cache.dropdown.DropDownRow;
import com.aw.core.cache.loader.MetaLoader;
import com.aw.core.exception.AWSystemException;
import com.aw.core.format.Formatter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapperImpl;

import java.text.FieldPosition;
import java.text.ParsePosition;

/**
 * @author jcvergara
 *         15/11/2004
 */
public class DropDownFormatter implements Formatter {
    protected static Log logger = LogFactory.getLog(DropDownFormatter.class);

    //private CVLValueProvider valueProvider;
    MetaLoader metaLoader;
    String attributeName;

//    public DropDownFormatter(String dropDownkey) {
//        if (dropDownkey != null)
//            valueProvider = new CVLValueProvider(dropDownkey);
//    }

    public DropDownFormatter(MetaLoader metaLoader) {
        this.metaLoader = metaLoader;
    }

    public Formatter clone() {
        DropDownFormatter  dropDownFormatter = null;
        try {
            dropDownFormatter = (DropDownFormatter) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AWSystemException("Error clonning:"+this,e);
        }
        return dropDownFormatter;
    }

    public Object format(Object bean, String attributeName, Object attributeValue) {
        return metaLoader == null ? attributeValue : formatCVLvalue(attributeValue, metaLoader);
    }



    protected Object formatCVLvalue(Object attributeValue, MetaLoader metaLoader) {
        DropDownRow dropDownRow= metaLoader.getMap().mapGet(attributeValue);
        Object label = null;
        if (dropDownRow==null)
            label =  null;
        else if (attributeName!=null)
            label = new BeanWrapperImpl(dropDownRow).getPropertyValue(attributeName);
        else
            label = dropDownRow.getLabel();
        return label;
    }

    /**
     * Additonal information for Sorting purpose
     * Indicate witch values is prefered to use in sorts
     * TRUE: use original values (unformatted)
     * FALSE: use formatted values
     *
     * @return always FALSE (use formmated value)
     */
    public boolean onSortingPreferOriginalValue() {
        return false;
    }



    ///////////////////// Format Support ///////////////
    /**
     * Parses text from a string to produce an object.
     */
    public Object parseObject(String source, ParsePosition pos) {
        return source;
    }

    /**
     */
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        return toAppendTo.append(formatCVLvalue(obj, metaLoader));
    }

    public Object format(Object obj) {
        return formatCVLvalue( obj,metaLoader);
    }

    public Object parseObject(String source) {
        return source;
    }

    public DropDownFormatter setAttributeName(String attributeName) {
        this.attributeName = attributeName;
        return this;
    }
}
