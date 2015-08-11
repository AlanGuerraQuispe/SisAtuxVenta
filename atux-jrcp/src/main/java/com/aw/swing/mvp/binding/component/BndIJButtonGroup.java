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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Kaiser
 */
public abstract class BndIJButtonGroup  {
    protected String fieldName;
    protected ButtonGroup buttonGroup = new ButtonGroup();
    protected List<BndIJRadioButton> bndIJRadioButtons = new ArrayList<BndIJRadioButton>();

    protected BndIJButtonGroup(String fieldName) {
        this.fieldName = fieldName;
    }
    public BndIJButtonGroup addButton(JRadioButton component, Object selectedValue) {
        BndIJRadioButton inputComponent = bindIntern(component, selectedValue);
        bndIJRadioButtons.add(inputComponent );
        return this;
    }

    public void setValuesToBean(){
        for (BndIJRadioButton bndIJRadioButton : bndIJRadioButtons) {
            if(bndIJRadioButton.getJComponent().isSelected()){
                bndIJRadioButton.setValueToBean();
            }
        }
    }

    protected abstract BndIJRadioButton bindIntern(JRadioButton component, Object selectedValue) ;

    public BndIJButtonGroup addActionListener(ActionListenerAdapter actionListenerAdapter) {
        actionListenerAdapter.bnd = this;
        for (BndIJRadioButton bndIJRadioButton : bndIJRadioButtons) {
            bndIJRadioButton.getJComponent().addActionListener(actionListenerAdapter);
        }
        return this;
    }
    public BndIJButtonGroup addActionListenerAndExecute(ActionListenerAdapter actionListenerAdapter) {
        BndIJButtonGroup bndIJButtonGroup = addActionListener(actionListenerAdapter);
        actionListenerAdapter.actionPerformed(new ActionEvent(bndIJButtonGroup, ActionEvent.ACTION_PERFORMED, ""));
        return bndIJButtonGroup;
    }
    public BndIJRadioButton getSelectedBnd() {
        for (BndIJRadioButton bndIJRadioButton : bndIJRadioButtons) {
            if (bndIJRadioButton.getJComponent().isSelected()){
                return bndIJRadioButton;
            }
        }
        return null;
    }

    public static abstract class ActionListenerAdapter implements ActionListener {
        BndIJButtonGroup bnd;

        public void actionPerformed(ActionEvent e) {
            BndIJRadioButton selectedBnd= bnd.getSelectedBnd();
            Object selectedValue = selectedBnd == null ? null : selectedBnd.getSelectedValue();
            doActionPerformed(e, selectedBnd, selectedValue);
        }

        abstract protected void doActionPerformed(ActionEvent e, BndIJRadioButton selectedBnd, Object selectedValue);
    }
}