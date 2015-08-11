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
package com.aw.core.exception;

/**
 * Date: Sep 20, 2007
 */
public class  AWException extends RuntimeException {
    protected String errorCode;
    protected Object errorArgs[];
    protected String visualCmpName;

    public AWException() {
    }

    public AWException(String message, String visualCmpName) {
        super(message);
        this.visualCmpName = visualCmpName;
    }

    public AWException(String message, Throwable cause) {
        super(message, cause);
    }

    public AWException(Throwable cause) {
        super(cause);
    }

    public AWException(String errorCode, Object[] errorArgs) {
        super();
        this.errorCode = errorCode;
        this.errorArgs = errorArgs;
    }


    public AWException(String message) {
        super(message);
    }

    public String getVisualCmpName() {
        return visualCmpName;
    }

    public AWException setVisualCmpName(String visualCmpName) {
        this.visualCmpName = visualCmpName;
        return this;
    }
}
