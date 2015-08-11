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
package com.aw.swing.mvp.validation.support;

import com.aw.support.IKeyArgs;
import com.aw.swing.mvp.binding.BindingComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jcvergara
 *         07/11/2004
 */
public class PropertyResults implements IKeyArgs {

    protected final Log logger = LogFactory.getLog(getClass());

    private String message;

    private List components;

    private List args;


    public PropertyResults() {
        this.args = new ArrayList();
        this.components = new ArrayList();
    }

    public PropertyResults(String message) {
        this();
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getBindingComponents() {
        return components;
    }

    public void setComponents(List components) {
        this.components = components;
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("PropertyResults");
        sb.append("{message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public void add(BindingComponent bindingComponent) {
        components.add(bindingComponent);
    }

    public void addArgs(Object param) {
        args.add(param);
    }


    public Object[] getArgs() {
        return args.toArray();
    }
}
