package com.aw.core.exception;

/**
 * Use for wrap the exceptions of low level
 * User: gmc
 * Date: 11/10/2008
 */
public class AWSystemException extends AWException{
    public AWSystemException(Throwable cause) {
        super(cause);
    }

    public AWSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
