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
package com.aw.core.domain;

import com.aw.core.business.AWContext;

import java.util.Arrays;

/**
 * author: jcvergara
 * 07/08/2004
 */
public class AWBusinessPropException extends AWBusinessException {


    protected String errorCode;
    protected Object errorArgs[];


    public AWBusinessPropException(String errorCode, Object errorArgs[]) {
        super(null);
        this.errorCode = errorCode;
        this.errorArgs = errorArgs;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getErrorArgs() {
        return errorArgs;

    }


    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String toString() {
        return errorCode + (errorArgs == null ? "" : Arrays.asList(errorArgs).toString());
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this <tt>Throwable</tt> instance
     *         (which may be <tt>null</tt>).
     */
    public String getMessage() {
        return AWContext.getAppCtx().getMessage(errorCode, errorArgs, null);
    }

}
