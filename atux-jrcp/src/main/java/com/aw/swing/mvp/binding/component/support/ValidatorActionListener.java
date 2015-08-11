package com.aw.swing.mvp.binding.component.support;

import com.aw.swing.mvp.binding.component.BndIJTextField;
import com.aw.swing.mvp.validation.support.PropertyValidator;
import com.aw.swing.mvp.validation.support.ReflectionValidator;

import java.awt.event.ActionEvent;

/**
 * User: User
 * Date: Dec 11, 2007
 */
public class ValidatorActionListener implements java.awt.event.ActionListener {
    private ReflectionValidator validator;


    public ValidatorActionListener(ReflectionValidator validator) {
        this.validator = validator;

    }
    public void actionPerformed(ActionEvent e) {
        PropertyValidator propertyValidator = validator.getCurrentPropertyValidator();
        if (propertyValidator != null) {
            BndIJTextField bndIJTextField = (BndIJTextField) propertyValidator.getBindingComponent();
            bndIJTextField.initComponent();
//            bndIJTextField.updateComponentRules(); todo revisar esto. RAC
        }
    }
}
