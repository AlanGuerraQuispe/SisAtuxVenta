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


import com.aw.core.context.AWBaseContext;
import com.aw.core.domain.AWBusinessException;
import com.aw.core.exception.AWSystemException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.text.*;
import java.util.Date;

/**
 * @author jcvergara
 *         15/11/2004
 */
public class DateFormatter<E> implements Formatter {
    protected static Log logger = LogFactory.getLog(DateFormatter.class);

    public static final DateFormatter<Date> DATE_FORMATTER = new DateFormatter(AWBaseContext.instance().getConfigInfoProvider().getDateFormat());
    public static final DateFormatter<Date> TIME_FORMATTER = new DateFormatter(AWBaseContext.instance().getConfigInfoProvider().getTimeFormat());
    public static final DateFormatter<Date> TIME_SECONDS_FORMATTER = new DateFormatter(AWBaseContext.instance().getConfigInfoProvider().getTimeFormatWitchSeconds());
    public static final DateFormatter<Date> DATE_TIME_FORMATTER = new DateFormatter(AWBaseContext.instance().getConfigInfoProvider().getDateTimeFormat());


    protected Format internFormmater;

    public DateFormatter(String dateFormat) {
        internFormmater = new SimpleDateFormat(dateFormat);
    }

    public Formatter clone() {
        try {
            return (Formatter) super.clone();
        } catch (CloneNotSupportedException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    public Object format(Object bean, String attributeName, Object attributeValue) {
        if (attributeValue == null) return null;
        return internFormmater.format(attributeValue);
    }

    public Object format(Object attributeValue) {
        if (attributeValue == null) return null;
        return internFormmater.format(attributeValue);
    }

    public Object parseObject(String source, ParsePosition pos) {
        return null;
    }

    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        return null;
    }

    public Object parseObject(String source) {
        try {
            if (!StringUtils.hasText(source)) return null;
            return internFormmater.parseObject(source);
        } catch (ParseException e) {
            throw new AWSystemException("Error Parsing:" + source, e);
        }
    }

    public E parse(String source) {
        return (E)parseObject(source);
    }

    /**
     * Additonal information for Sorting purpose
     * Indicate witch values is prefered to use in sorts
     * TRUE: use original values (unformatted)
     * FALSE: use formatted values
     *
     * @return always TRUE (use date object instead of String)
     */
    public boolean onSortingPreferOriginalValue() {
        return true;
    }

    public DateFormat getDateFormat() {
        return (DateFormat) internFormmater;
    }

}


