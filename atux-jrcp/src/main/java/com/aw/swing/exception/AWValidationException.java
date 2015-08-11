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
package com.aw.swing.exception;

import com.aw.support.IKeyArgs;
import com.aw.swing.mvp.binding.BindingComponent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: Sep 19, 2007
 */
public class AWValidationException extends AWSwingException implements IKeyArgs {

    public static final int EXCEPTION_UNIQUE = 0;
    public static final int EXCEPTION_WITH_LIST_OBJECTS = 1;


    private String message = null;
    private Object[] args = null;
    private List bindingComponents = new ArrayList();
    // List de AWValidationException or PropertyResults 
    private List listObject = new ArrayList();
    private int typeException = -1;



    public AWValidationException(String message) {
        this.message = message;
        this.typeException = EXCEPTION_UNIQUE;
    }

    public AWValidationException(String message, JComponent jComponent) {
        this(message);
        this.jComponent = jComponent;

    }

    public AWValidationException(String message, Object[] args, JComponent jComponent) {
        this(message, jComponent);
        this.args = args;
    }

    public AWValidationException(String message, Object[] args) {
        this(message);
        this.args = args;
    }

    public AWValidationException(String message, List bindingComponents, JComponent jComponent) {
        this(message, jComponent);
        this.bindingComponents = bindingComponents;
    }

    public AWValidationException(String message, List bindingComponents) {
        this(message);
        this.bindingComponents = bindingComponents;
        if (bindingComponents != null &&(bindingComponents.size()>0)){
            this.jComponent = ((BindingComponent)bindingComponents.get(0)).getJComponent();
        }
    }

    public AWValidationException(String message, Object[] args, List bindingComponents, JComponent jComponent) {
        this(message, args, jComponent);
        this.bindingComponents = bindingComponents;
    }

    public AWValidationException(String message, Object[] args, List bindingComponents) {
        this(message, args);
        this.bindingComponents = bindingComponents;
    }

    public AWValidationException(List listObject) {
        this.listObject = listObject;
        this.typeException = EXCEPTION_WITH_LIST_OBJECTS;
    }


    public String getMessage() {
        return message;
    }

    public Object[] getArgs() {
        return args;
    }

    public List getBindingComponents() {
        return bindingComponents;
    }

    public List getListObject() {
        return listObject;
    }


    public int getTypeException() {
        return typeException;
    }
}
