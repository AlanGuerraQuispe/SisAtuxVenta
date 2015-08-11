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
package com.aw.swing.mvp.ui.painter;

import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * Date: Aug 16, 2007
 */
public class ErrorBackgroundPainter extends ErrorPainter {

    public void paintError(List components) {
        repaint(components, ColorInfoProvider.COLOR_ERROR);
    }

    public void clearError(JComponent jComponent) {
        if (isNeededClearError(jComponent)){
            repaint(jComponent, ColorInfoProvider.COLOR_DEFAULT);
        }
    }

    private boolean isNeededClearError(JComponent jComponent) {
        if (jComponent instanceof JComboBox){
            return true;
        }
        return ColorInfoProvider.COLOR_ERROR.equals(jComponent.getBackground());
    }

    private void repaint(JComponent jComponent, Color color) {
        if (jComponent instanceof JComboBox){
            if(ColorInfoProvider.COLOR_ERROR.equals(color)){
                LookAndFeelManager.assignRequiredPainter((JComboBox)jComponent);
//                jComponent.setBorder(new LineBorder(ColorInfoProvider.COLOR_BORDER_ERROR));
            }else{
                LookAndFeelManager.clearRequiredPainter((JComboBox)jComponent);
//                jComponent.setBorder(null);
            }
        }else {
            jComponent.setBackground(color);
        }


    }

    private void repaint(List components, Color color) {
        for (Iterator iterator = components.iterator(); iterator.hasNext();) {
            BindingComponent bindingComponent = (BindingComponent) iterator.next();
            logger.debug(" repaint() :" + bindingComponent.getFieldName());
            repaint(bindingComponent.getJComponent(), color);
        }
    }
}
