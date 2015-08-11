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
package com.aw.core.format;

import com.aw.core.domain.AWBusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.FieldPosition;
import java.text.ParsePosition;

/**
 * Filler Support
 *
 * @author jcvergara
 *         05/11/2004
 */
public class TrimFormat implements Formatter {
    protected static Log logger = LogFactory.getLog(TrimFormat.class);

    /**
     * Constructor
     */
    public TrimFormat() {
    }

    public Formatter clone() {
        try {
            return (Formatter) super.clone();
        } catch (CloneNotSupportedException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }
    /**
     * Add null or empty string support
     */
    public Object parseObject(String source) {
        return source == null ? null : source.trim();

    }

    /**
     */
    public Object parseObject(String source, ParsePosition pos) {
        return source;
    }

    /**
     */
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        return new StringBuffer(obj.toString());
    }

    public Object format(Object obj) {
        if (obj instanceof String) obj = ((String) obj).trim();
        return obj;

    }

    public Object format(Object bean, String attributeName, Object attributeValue) {
        return attributeValue;
    }

    public boolean onSortingPreferOriginalValue() {
        return false;
    }
}
