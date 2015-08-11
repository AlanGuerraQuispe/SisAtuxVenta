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
package com.aw.swing.mvp.ui.msg;

/**
 * @author jcvergara
 *         12/11/2004
 */
public interface MessageDisplayer {
    public static final String GENERIC_MESSAGE_TITLE = "Mensaje del Sistema";

    public void showMessage(String message);

    public void showErrorMessage(String message);

    public void showMessage(String messageKey, Object[] args);

    public boolean showConfirmMessage(String messageConfirm);

    /**
     * Show a message dialog with an YES, NO Button
     *
     * @param messageKey
     * @return TRUE, if the user press YES
     *         FALSE if the user pres NO
     */
    public boolean showConfirmMessage(String messageKey, Object[] args);

    public String getMessage(String messageKey, Object[] args);

}
