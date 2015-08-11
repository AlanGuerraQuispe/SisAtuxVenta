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


import com.aw.core.context.AWBaseContext;
import com.aw.support.ObjectConverter;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.binding.BndInputComponent;
import com.aw.swing.mvp.binding.InputCmpMgr;

import javax.swing.*;

/**
 * User: Kaiser
 */
public class BndICheckBox extends BndInputComponent<JCheckBox> {
    private Object valueTrue = AWBaseContext.instance().getConfigInfoProvider().getCheckTrue();
    private Object valueFalse = AWBaseContext.instance().getConfigInfoProvider().getCheckFalse();

    public BndICheckBox(InputCmpMgr inputComponentManager, JCheckBox jInputComponent, String fieldName) {
        super(inputComponentManager, jInputComponent, fieldName);
    }

    public BndICheckBox(InputCmpMgr inputComponentManager, JCheckBox jInputComponent) {
        super(inputComponentManager, jInputComponent);
    }

    public BndICheckBox(InputCmpMgr inputComponentManager, JCheckBox jInputComponent, Object bean, String fieldName) {
        super(inputComponentManager, jInputComponent, bean, fieldName);
    }

    /**
     * Set the value of the bean to the JInputComponent
     */
    protected void setValueToJComponentInternal(){
        if (logger.isDebugEnabled())
            logger.debug("[" + this.fieldName + "] Bean-->Cmpt:" + getBeanWrapper().getPropertyValue(fieldName));
        JCheckBox jCheckBox = (JCheckBox) jComponent;
        jCheckBox.setSelected(valueTrue.equals(getBeanWrapper().getPropertyValue(fieldName)));
    }

    /**
     * Set the value of the JInputComponent to the bean
     * If the user has not selected any item then the value will be set to null
     */
    public void setValueToBean() {
        Object value = getValue();
        if (logger.isDebugEnabled())
            logger.debug("[" + this.fieldName + "] Cmpt-->Bean:" + value + " class: " + getBeanWrapper().getWrappedInstance());
        Class clazz = getBeanWrapper().getPropertyType(fieldName);
        value = ObjectConverter.convert(value, clazz);
        getBeanWrapper().setPropertyValue(fieldName, value);
    }


    /**
     * This method cleans the value actully set in the JComponent
     * For TextFields it cleans his value
     * For ComboBox, it shows no element selected, but the list remains there
     */
    public void cleanJComponentValues() {
        JCheckBox jCheckBox = (JCheckBox) jComponent;
        jCheckBox.setSelected(false);
    }


    public void setValue(Object value) {
        getBeanWrapper().setPropertyValue(fieldName,value);
    }

    /**
     * Get the value that contains the input jComponent
     *
     * @return
     */
    public Object getValue() {
        if (jComponent.isSelected()) {
            return valueTrue;
        }
        return valueFalse;
    }

    public void setAsChecked() {
        jComponent.setSelected(true);
    }
    public void setAsUnchecked() {
        jComponent.setSelected(false);
    }

    public BindingComponent registerTrueFalse(Object valueTrue, Object valueFalse) {
        this.valueTrue = valueTrue;
        this.valueFalse = valueFalse;
        return this;
    }
}
