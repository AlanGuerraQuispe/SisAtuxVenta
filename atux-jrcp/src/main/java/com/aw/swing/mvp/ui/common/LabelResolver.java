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
package com.aw.swing.mvp.ui.common;

import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.ui.msg.MsgDisplayer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;

/**
 * Date: Sep 17, 2007
 */
public class LabelResolver {
    protected static final Log logger = LogFactory.getLog(LabelResolver.class);

    public static String getLabelForMsgs(BindingComponent bindingComponent) {
        if (bindingComponent.getLabelToBeUsedOnError() != null) {
            return bindingComponent.getLabelToBeUsedOnError();
        }
        String labelToBeUsed = null;
        JLabel label = getLabelFromDlg(bindingComponent.getFieldName());

        if (label != null) {
            labelToBeUsed = label.getText();
        }

        if (labelToBeUsed == null) {
            labelToBeUsed = getCodigoFromProperties(bindingComponent.getFieldName());
        }

        bindingComponent.setLabelToBeUsedOnError(labelToBeUsed);
        return labelToBeUsed;
    }

    public static JLabel getLabelFromDlg(String fieldName) {
        JLabel label = null;
        fieldName = fieldName.substring(fieldName.indexOf(".") + 1, fieldName.length());
        Character init = fieldName.charAt(0);
        String labelName = init.toString().toUpperCase() + fieldName.substring(1, fieldName.length());
        try {
            label = AWWindowsManager.instance().getCurrentPst().getIpView().getJLabelForComponent(labelName);
        } catch (IllegalStateException ex) {
            label = null;
        }
        return label;
    }

    /**
     * lblF1gd lblF1gd1 lblF1gd2
     *
     * @param keyCode
     * @param gridIndex
     * @return
     */
    public static String getLabelNameForAction(int keyCode, int gridIndex) {
        StringBuffer sb = new StringBuffer();
        sb.append(getLabelNameForAction(keyCode));
        sb.append("gd");
        if (gridIndex != 0) {
            sb.append(gridIndex);
        }
        return sb.toString();
    }

    /**
     * lblF1 lblF2 lblF3
     *
     * @param keyCode
     * @return
     */
    public static String getLabelNameForAction(int keyCode) {
        StringBuffer sb = new StringBuffer("lblF");
        sb.append(keyCode - (ActionDialog.KEY_F1 - 1));
        return sb.toString();
    }

    public static String getCodigoFromProperties(String fieldName) {
        try {
            return MsgDisplayer.getMessage(fieldName, null);
        } catch (Throwable e) {
            logger.warn(e.getMessage());
        }
        return MsgDisplayer.getMessage("sw.error.field", null);
    }
}
