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
package com.aw.swing.mvp;

import com.aw.swing.mvp.binding.InputCmpMgr;

/**
 * User: gmc
 * Date: 18-oct-2007
 */
public class InputProvider {
    private InputInfoProvider infoProvider;
    private InputCmpMgr inputCmpMgr;

    public InputProvider(InputInfoProvider infoProvider) {
        this.infoProvider = infoProvider;
    }

    public void configureElements() {
        inputCmpMgr = infoProvider.getInputCmpMgr();
        infoProvider.registerBinding();
        infoProvider.registerPicks();
        infoProvider.registerValidations();
    }


    public InputCmpMgr getInputCmpMgr() {
        return inputCmpMgr;
    }

    public void setInputCmpMgr(InputCmpMgr inputCmpMgr) {
        this.inputCmpMgr = inputCmpMgr;
    }

    public void setValuesToBean() {
        inputCmpMgr.setValuesToBean();
    }

    public void setValueToJComponent() {
        inputCmpMgr.setValueToJComponent();
    }
}
