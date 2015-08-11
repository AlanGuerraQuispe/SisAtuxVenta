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
import com.aw.core.exception.AWSystemException;
import com.aw.support.ObjectConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.*;

/**
 * Null Support
 *
 * @author jcvergara
 *         05/11/2004
 */
public class NullDecimalFormat implements Formatter {
    protected static Log logger = LogFactory.getLog(NullDecimalFormat.class);

    /**
     * Internal atribute used to format the number
     */
    private DecimalFormat decimalFormat;
    private NumberRange numberRangeLimit;
    private Class returnClassType;

    /**
     * In case the input types is blanck (null or "")
     * and this atribute has any value set (not null)
     * then this will be used as if the user typed this value.
     * Common use: set to "0" when no explicity value is set
     *
     * @see #setZeroAsDefaultValue()
     */
    private String defaultValueOnBlank;

    /**
     * Constructor
     *
     * @param pattern
     */
    public NullDecimalFormat(String pattern) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        decimalFormat = new DecimalFormat(pattern, symbols);
    }

    public Formatter clone() {
        try {
            return (Formatter) super.clone();
        } catch (CloneNotSupportedException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }
    /**
     * Constructor
     *
     * @param pattern
     */
    public NullDecimalFormat(String pattern, NumberRange numberRangeLimit) {
        this(pattern);
        this.numberRangeLimit = numberRangeLimit;
        // guess what type should be used
        Class clazz = null;
        if (numberRangeLimit.getMinimun() != null) clazz = numberRangeLimit.getMinimun().getClass();
        else if (numberRangeLimit.getMaximun() != null) clazz = numberRangeLimit.getMaximun().getClass();
        setReturnClassType(clazz);
    }

    public void setNumberRangeLimit(NumberRange numberRangeLimit) {
        this.numberRangeLimit = numberRangeLimit;
    }

    public void setReturnClassType(Class returnClassType) {
        this.returnClassType = returnClassType;
    }

    public void setZeroAsDefaultValue() {
        this.defaultValueOnBlank = "0";
    }

    /**
     * Add null or empty string support
     */
    public Object parseObject(String source) {
        if (source == null || "".equals(source)) {
            // source is empty
            if (defaultValueOnBlank == null) return null;
            source = defaultValueOnBlank;
        }
        Comparable number = null;
        try {
            number = (Comparable) ObjectConverter.transformType((Number) decimalFormat.parseObject(source), returnClassType);
        } catch (ParseException e) {
            throw new AWSystemException("Error Parsing:" + source, e);
        }
        if (numberRangeLimit != null) {
            number = numberRangeLimit.enforceInRange(decimalFormat, number);
        }
        return number;
    }


    /**
     * Overrided method
     */
    public Object parseObject(String source, ParsePosition pos) {
        return decimalFormat.parseObject(source, pos);
    }

    public Object format(Object bean, String attributeName, Object attributeValue) {
        return null;
    }

    /**
     * Overrided method
     */
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        return decimalFormat.format(obj, toAppendTo, pos);
    }

    public Object format(Object obj) {
        if (obj instanceof String) {
            obj = new Long(obj.toString());
        }
        return decimalFormat.format(obj);
    }

    public boolean onSortingPreferOriginalValue() {
        return false;
    }

}
