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

import com.aw.swing.exception.AWValidationException;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.binding.component.BndIJTextField;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.ui.painter.PainterMessages;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;

/**
 * Date: Sep 18, 2007
 */
public class AWInputVerifier extends InputVerifier {

    protected final Log logger = LogFactory.getLog(getClass());
    public static AWInputVerifier instance;
    public static boolean enable = true;

    public static AWInputVerifier getInstance() {
        if (instance == null) {
            instance = new AWInputVerifier();
        }
        return instance;
    }

    public boolean verify(JComponent input) {
        BndIJTextField bindingComponent = (BndIJTextField) input.getClientProperty(BindingComponent.ATTR_BND);
        try {
            if (enable) {
                logger.debug("validate <" + bindingComponent.getFieldName() + ">");
                Presenter currentPst =AWWindowsManager.instance().getCurrentPst();
                if (currentPst!=null){
                    currentPst.getValidatorMgr().validate(input);
                }
            }
        } catch (AWValidationException ex) {
            logger.info("validate - paint <" + bindingComponent.getFieldName() + ">");
            ex.setRequestFocus(false);
            PainterMessages.paintException(ex,false);
//            PainterMessages.paintException(ex);
//            return false;
            return true;
        }
        if (enable) {
            logger.debug("validate - clean <" + bindingComponent.getFieldName() + ">");
            if (!bindingComponent.isUiReadOnly()) {
                PainterMessages.cleanException(input);
            }
        }
        return true;
    }

    public void enable() {
        enable = true;
    }

    public void disable() {
        enable = false;
    }
}
