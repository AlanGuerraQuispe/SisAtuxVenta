package com.aw.swing.mvp.binding.component.support;

import com.aw.swing.mvp.binding.JTextFieldInputHandler;
import com.aw.swing.mvp.validation.support.PropertyValidator;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * User: gmc
 * Date: 02/06/2009
 */
public class JTextFieldDocumentDecorator {

    public static void decorate(JTextComponent jComponent,  PropertyValidator propertyValidator, PropertyBinding propertyBinding) {
        if(jComponent instanceof JTextPane) return;

        JTextFieldInputHandler document = new JTextFieldInputHandler();
        document.setToUpperCase(propertyBinding.getToUpperCase());

        if (propertyBinding.getConfigureForNumbers() != null) {
            document.configureForNumbers(propertyBinding.getConfigureForNumbers());
        }

        if (propertyValidator.getMaxLength() != -1) {
            document.setLimit(propertyValidator.getMaxLength());
        }
        if (propertyValidator.isOnlyDigits()) {
            document.setOnlyDigits(true);
        }
        if (propertyValidator.isOnlyLetters()) {
            document.setOnlyLetters(true);
        }
        if (propertyBinding.isMinusSymbolAllowed()) {
            document.setMinusSymbolAllowed(true);
        }

        if (propertyValidator.isMoneyFormat() || propertyValidator.isFloatWithGroupFormat()) {
            document.setOnlyDigits(true);
            document.setDecimalSymbolAllowed(true);
            document.setMillarSymbolAllowed(true);
        }
        if (propertyValidator.isDateFormat()) {
            document.setOnlyDigits(true);
            document.setSlashSymbolAllowed(true);
            document.setLimit(propertyBinding.getLimitTextSize());
            document.setAddDateSlash(true);
        }

        if (jComponent instanceof JTextField) {
            if (propertyValidator.isOnlyDigits()) {
                ((JTextField) jComponent).setHorizontalAlignment(JTextField.RIGHT);
            } else {
                ((JTextField) jComponent).setHorizontalAlignment(propertyBinding.getHorizontalAligement());
            }
        }

        jComponent.setDocument(document);
    }


}
