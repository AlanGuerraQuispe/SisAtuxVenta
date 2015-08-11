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

import java.text.FieldPosition;
import java.text.ParsePosition;


/**
 * Provide Formatting mechanism
 * Knowed implementations
 * Date --> String
 * Code --  Value  (using drop down formmaters)
 *
 * @author jcvergara
 *         15/11/2004
 */
public interface Formatter extends Cloneable {
    public static final Formatter VOID = new Formatter() {
        public Formatter clone() {
            return VOID;
        }

        public Object format(Object bean, String attributeName, Object attributeValue) {
            return attributeValue;
        }

        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            return null;
        }

        public Object format(Object obj) {
            return null;
        }

        public boolean onSortingPreferOriginalValue() {
            return false;
        }

        public Object parseObject(String source) {
            return null;
        }

        public Object parseObject(String source, ParsePosition pos) {
            return null;
        }
    };

    /**
     * Transform the value specified to certain format
     *
     * @param bean
     * @param attributeName
     * @param attributeValue
     * @return
     */
    public Object format(Object bean, String attributeName, Object attributeValue);

    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos);

    public Object format(Object obj);


    public Formatter clone();

    /**
     * Additonal information for Sorting purpose
     * Indicate witch values is prefered to use in sorts
     * TRUE: use original values (unformatted)
     * FALSE: use formatted values
     *
     * @return
     */
    public boolean onSortingPreferOriginalValue();

    public Object parseObject(String source);

    public Object parseObject(String source, ParsePosition pos);

}
