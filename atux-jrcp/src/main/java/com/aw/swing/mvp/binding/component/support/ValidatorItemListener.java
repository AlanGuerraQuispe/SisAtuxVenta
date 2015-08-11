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
package com.aw.swing.mvp.binding.component.support;

import com.aw.swing.mvp.binding.component.BndIJTextField;
import com.aw.swing.mvp.validation.support.PropertyValidator;
import com.aw.swing.mvp.validation.support.ReflectionValidator;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Date: Sep 28, 2007
 */
public class ValidatorItemListener implements ItemListener {
    private ReflectionValidator validator;


    public ValidatorItemListener(ReflectionValidator validator) {
        this.validator = validator;

    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) return;
        
        PropertyValidator propertyValidator = validator.getCurrentPropertyValidator();
        if (propertyValidator != null) {
            BndIJTextField bndIJTextField = (BndIJTextField) propertyValidator.getBindingComponent();
            bndIJTextField.initComponent();
        }
    }
}

