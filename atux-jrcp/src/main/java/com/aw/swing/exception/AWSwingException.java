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

import com.aw.core.exception.AWException;

import javax.swing.*;

/**
 * User: Kaiser
 */
public class AWSwingException extends AWException {
    /** Contains the jComponent that will gain the focus on error */
    protected JComponent jComponent;

    /**
     * The tab index for the presenter (if apply)
     */
    private int tabIndex = -1;

    /**
     * The warning messages
     */
    private String warningMessages;

    private boolean requestFocus = true;

    public AWSwingException(String errorCode, Object[] errorArgs, JComponent component) {
        super(errorCode, errorArgs);
        this.jComponent = component;
    }

    public AWSwingException(String errorCode, Object[] errorArgs) {
        super(errorCode, errorArgs);
    }

    public AWSwingException(String errorCode) {
        super(errorCode);
    }

    public AWSwingException() {
    }


    public void setJComponent(JComponent jComponent) {
        this.jComponent = jComponent;
    }

    public JComponent getJComponent() {
        return jComponent;
    }

    public boolean isRequestFocus() {
        return requestFocus;
    }

    public void setRequestFocus(boolean requestFocus) {
        this.requestFocus = requestFocus;
    }
}
