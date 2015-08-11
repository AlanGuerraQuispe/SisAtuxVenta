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

import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;

/**
 * User: gmc
 * Date: 20-ago-2007
 */
public class CancelAction extends Action {

    public CancelAction() {
//        confirmMsg = "sw.common.conf.exit";
        confirmMsg = "¿Está seguro que desea salir?";
        execBinding = false;
        execValidation = false;
        hasToCloseView = true;
        keyTrigger = ActionDialog.KEY_ESC;
        needVisualComponent = false;
    }
    public CancelAction setDefaultConfirmMsg(){
        confirmMsg = "¿Está seguro que desea salir?";
        return this;
    }

    protected Object executeIntern() {
        return null;
    }

    public String toString() {
        return "CancelAction";
    }
}
