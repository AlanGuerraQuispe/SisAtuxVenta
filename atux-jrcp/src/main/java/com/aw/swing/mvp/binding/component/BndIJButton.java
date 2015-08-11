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
package com.aw.swing.mvp.binding.component;

import com.aw.swing.mvp.binding.BindingComponent;

import javax.swing.*;

/**
 * Date: Aug 17, 2007
 */
class BndIJButton extends BindingComponent {
    public BndIJButton(JButton jButton) {
        super(jButton);
    }

    protected void setValueToJComponentInternal(){

    }

    public void cleanJComponentValues() {
    }

    public void initComponent() {
        super.initComponent();
        ((JButton) getJComponent()).setDefaultCapable(false);
        uiReadOnly = true;
    }
}
