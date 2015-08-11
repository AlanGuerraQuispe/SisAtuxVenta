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

import com.aw.core.exception.AWException;
import com.aw.core.spring.ApplicationBase;
import org.apache.commons.logging.Log;

import java.sql.SQLException;

/**
 * author: jcvergara
 * 07/08/2004
 */
public class AWBusinessException extends AWException {
    /**
     * The exception only has warning messages and no error messages ?
     */
    private boolean onlyContainsWarnings;    
    private boolean forceFailMode = false;

    public AWBusinessException(String message) {
        super(message);
    }

    public AWBusinessException(String message, String visualCmpName) {
        super(message, visualCmpName);
    }

    public AWBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public AWBusinessException setForceFailMode(boolean forceFailMode) {
        this.forceFailMode = forceFailMode;
        return this;
    }

    public boolean isOnlyContainsWarnings() {
        return onlyContainsWarnings;
    }

    public boolean isForceFailMode() {
        return forceFailMode;
    }

    public void setOnlyContainsWarnings(boolean onlyContainsWarnings) {
        this.onlyContainsWarnings = onlyContainsWarnings;
    }


    public static AWBusinessException wrapUnhandledException(Log logger, Throwable e) {
        return wrapUnhandledException(logger, e, "Operation fail");
    }

    public static AWBusinessException wrapUnhandledException(Log logger, SQLException e) {
        return wrapUnhandledException(logger, e, "Database SQL operation fail");
    }


    private static AWBusinessException wrapUnhandledException(Log logger, Throwable e, Object errorMessage) {
        try{
            AWExcepcionInterpreter  excepcionInterpreter = (AWExcepcionInterpreter) ApplicationBase.instance().getBean("exceptionInterpreter", null);
            if (excepcionInterpreter!=null)
                e = excepcionInterpreter.handle(e);
        }catch (Throwable e2){}
        if (e instanceof AWBusinessException)
            return (AWBusinessException) e;
        else {
            logger.error(errorMessage, e);
            AWBusinessException awBusinessException = new AWBusinessException(errorMessage + " : " + e.getMessage());
            awBusinessException.initCause(e);
            return awBusinessException;
        }
    }
}
