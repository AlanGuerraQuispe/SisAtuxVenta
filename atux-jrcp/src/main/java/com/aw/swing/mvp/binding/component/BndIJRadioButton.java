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
import com.aw.swing.mvp.binding.ComponentManager;

import javax.swing.*;

/**
 * User: Kaiser
 */
public class BndIJRadioButton extends BindingComponent<JRadioButton> {
    private Object selectedValue;

    public BndIJRadioButton(JRadioButton jComponent) {
        super(jComponent);
    }

    public BndIJRadioButton(ComponentManager componentManager, JRadioButton jComponent, String fieldName, Object selectedValue) {
        super(componentManager, jComponent, fieldName);
        this.selectedValue = selectedValue;
    }

    public BndIJRadioButton(ComponentManager inputComponentManager, JRadioButton jComponent, Object bean, String fieldName, Object selectedValue) {
        super(inputComponentManager, jComponent, bean, fieldName);
        this.selectedValue = selectedValue;
    }

    protected void setValueToJComponentInternal(){
        if (logger.isDebugEnabled())
            logger.debug("[" + this.fieldName + "] Bean-->Cmpt:" + getBeanWrapper().getPropertyValue(fieldName));
        JRadioButton jRadioButton = (JRadioButton) jComponent;
        Boolean select = (selectedValue).equals((String) getBeanWrapper().getPropertyValue(fieldName));
        jRadioButton.setSelected(select.booleanValue());
    }


    /**
     * Set the value of the JInputComponent to the bean
     * If the user has not selected any item then the value will be set to null
     */
    public void setValueToBean() {
        Boolean value = (Boolean) getValue();
        if (logger.isDebugEnabled())
            logger.debug("[" + this.fieldName + "] Cmpt-->Bean:" + value + " class: " + getBeanWrapper().getWrappedInstance());
        if (value.booleanValue()) {
            getBeanWrapper().setPropertyValue(fieldName, selectedValue);
        }else{
            // JCM no sobreescribir valor para radios
            //getBeanWrapper().setPropertyValue(fieldName, "0");
        }
    }


    /**
     * This method cleans the value actully set in the JComponent
     * For TextFields it cleans his value
     * For ComboBox, it shows no element selected, but the list remains there
     */
    public void cleanJComponentValues() {
    }

    public Object getValue() {
        Boolean selected = ((JRadioButton) jComponent).isSelected();
        return selected;
    }

    public Object getSelectedValue() {
        return selectedValue;
    }
}
