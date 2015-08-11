package com.aw.core.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 26-abr-2008
 */
public class AWValidationException extends AWException{

    private String message = null;
    private Object[] args = null;
    private List components = new ArrayList();

    public AWValidationException(String message) {
        this.message = message;
    }


    public AWValidationException(String message, Object[] args) {
        this(message);
        this.args = args;
    }



    public String getMessage() {
        return message;
    }

    public Object[] getArgs() {
        return args;
    }

    public List getComponents() {
        return components;
    }

}
