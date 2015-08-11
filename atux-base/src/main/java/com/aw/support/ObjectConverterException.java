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
package com.aw.support;

public class ObjectConverterException
        extends RuntimeException {
    private Object source;
    private Class targetType;
    private String addInfo;

    ObjectConverterException(Object source, Class targetType, String addInfo) {
        this.source = source;
        this.targetType = targetType;
        this.addInfo = addInfo;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer(100);
        buf.append("Object '").append(source).append("' cannot be converted '");
        buf.append(source == null ? null : source.getClass()).append("' --> '").append(targetType).append("'");
        buf.append(addInfo == null ? "" : "(" + addInfo + ")");
        return buf.toString();
    }
}