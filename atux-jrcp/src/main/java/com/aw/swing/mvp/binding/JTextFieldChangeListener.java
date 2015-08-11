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
package com.aw.swing.mvp.binding;

/**
 * Class used to propagate edited text changed events for a JTextField
 * and also for the JFormattedtestField
 *
 * @author jcvergara
 *         09/10/2004
 */
public interface JTextFieldChangeListener {
    public void onTextChange(String newText);
}
