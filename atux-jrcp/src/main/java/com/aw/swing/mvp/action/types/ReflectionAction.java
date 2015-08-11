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
package com.aw.swing.mvp.action.types;

import com.aw.support.reflection.MethodInvoker;
import com.aw.swing.mvp.action.Action;

/**
 * User: gmc
 * Date: 11-sep-2007
 */
public class ReflectionAction extends Action {
    private Object target;
    private String methodName;

    public ReflectionAction(Object target, String methodName) {
        this.target = target;
        this.methodName = methodName;
        additionalMethodsTarget = target;
    }


    protected Object executeIntern() throws Throwable {
        return MethodInvoker.invoke(target, methodName);
    }

    public String toString() {
        return methodName;
    }

    protected String getActionName() {
        return ((Character) methodName.charAt(0)).toString().toUpperCase() + methodName.substring(1, methodName.length());
    }

}
