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
package com.aw.swing.mvp.validation;


import com.aw.core.util.TimeObserver;
import com.aw.support.reflection.AttributeAccessor;
import com.aw.swing.exception.AWValidationException;
import com.aw.swing.mvp.Constants;
import com.aw.swing.mvp.InputInfoProvider;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.binding.component.BndIJComboBox;
import com.aw.swing.mvp.binding.component.BndIJTextField;
import com.aw.swing.mvp.binding.component.support.ValidatorBuilder;
import com.aw.swing.mvp.cmp.pick.Pick;
import com.aw.swing.mvp.validation.support.PatternRules;
import com.aw.swing.mvp.validation.support.PropertyValidator;
import com.aw.swing.mvp.validation.support.ReflectionValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Date: Sep 13, 2007
 */
public class ValidatorManager {
    protected final Log logger = LogFactory.getLog(getClass());
    protected Presenter presenter;
    protected List basicValidations = new ArrayList();
    protected List extraValidations = new ArrayList();

    protected Object currentTarget;
    protected ValidatorBuilder validatorBuilder;

    public ValidatorManager(Presenter presenter) {
        this.presenter = presenter;
        validatorBuilder = new ValidatorBuilder(presenter);
        currentTarget = presenter;
    }

    /**
     * Register one valid extra
     *
     * @param target
     */
    public Validator registerValidator(Object target) {
        Validator validator = new ReflectionValidator(target);
        settingValuesOnTarget(target);
        registerExtraValidator(validator);
        return validator;
    }

    private void settingValuesOnTarget(Object target) {
        if (!(target instanceof Presenter) && !(target instanceof Pick) && !(target instanceof InputInfoProvider)) {
            AttributeAccessor.set(target, Constants.PST, presenter);
            AttributeAccessor.set(target, Constants.BKN, presenter.getBackBean());
            AttributeAccessor.set(target, Constants.DLG, presenter.getViewMgr().getViewSrc());
        }
    }

    public Validator registerValidator(Object target, String method) {
        Validator validator = new ReflectionValidator(target, method);
        settingValuesOnTarget(target);
        registerExtraValidator(validator);
        return validator;
    }

    public Validator registerValidator(String method) {
        return registerValidator(currentTarget, method);
    }

    /**
     * Register one valid Basic
     *
     * @param inputComponent
     * @param stringRule
     * @return
     */

    public Validator registerBasicRule(BndIJTextField inputComponent, String stringRule) {
        PropertyValidator propertyValidator = PatternRules.buildPropertyValidator(stringRule);
        propertyValidator.setBindingComponent(inputComponent);

        Validator validator = new ReflectionValidator();
        validator.registerPropertyValidator(propertyValidator);
        inputComponent.setValidator(validator);
//        System.out.println("register xxxx " + inputComponent.getFieldName() + " requerid "+propertyValidator.getRules().length);
        registerBasicValidator(validator);
        return validator;
    }

    public Validator registerBasicRule(BndIJComboBox inputComponent, String stringRule) {
        PropertyValidator propertyValidator = PatternRules.buildPropertyValidator(stringRule);
        propertyValidator.setBindingComponent(inputComponent);

        Validator validator = new ReflectionValidator();
        validator.registerPropertyValidator(propertyValidator);
        inputComponent.setValidator(validator);
//        System.out.println("register xxxx " + inputComponent.getFieldName() + " requerid "+propertyValidator.getRules().length);
        registerBasicValidator(validator);
        return validator;
    }

    public Validator registerBasicRule(BndIJTextField inputComponent, PropertyValidator propertyValidator) {
        propertyValidator.setBindingComponent(inputComponent);
        Validator validator = new ReflectionValidator();
        validator.registerPropertyValidator(propertyValidator);
        inputComponent.registerValidator(validator);
        logger.debug("register basic validator 2 for " + inputComponent.getFieldName());
//        System.out.println("register xxxx " + inputComponent.getFieldName()+ " requerid "+propertyValidator.getRules().length);
        registerBasicValidator(validator);
        return validator;
    }

    public void registerExtraValidator(Validator validator) {
        extraValidations.add(validator);
    }

    public void registerBasicValidator(Validator validator) {
        basicValidations.add(validator);
    }

    public void validate(JComponent component) {
        if (!(component instanceof JTextComponent)) {
            return;
        }
        BindingComponent bindingComponent = (BindingComponent) ((JTextComponent) component).getClientProperty(BindingComponent.ATTR_BND);
        validate(bindingComponent);
    }

    public void validate(BindingComponent bindingComponent) {
        Validator validator = null;
        if (bindingComponent != null) {
            validator = bindingComponent.getValidator();
            if (validator != null && validator.hasValidationRules()) {
                validator.execute();
            }
        }
    }

    public void validate() {
        validateBasic();
        validateExtra();
        validateDynamic();
    }

    public void validateBasic() {
        logger.debug("Executing basic validations ");
        TimeObserver time = new TimeObserver("Executing basic validations ");
        time.empezar();
        validate(basicValidations);
        time.terminar();
    }

    public void validateExtra() {
        logger.debug("Executing extra validations ");
        TimeObserver time = new TimeObserver("Executing extra validations");
        time.empezar();

        for (Iterator iterator = extraValidations.iterator(); iterator.hasNext();) {
            Validator validator = (Validator) iterator.next();
            Object target = validator.getTarget();
            if (!(target instanceof Presenter)) {
                AttributeAccessor.set(target, Constants.PST, presenter);
                AttributeAccessor.set(target, Constants.BKN, presenter.getBackBean());
            }
        }
        ValidationUtils.pst = presenter;
        ValidationUtils.dmn = presenter.getBackBean();
        validate(extraValidations);
        time.terminar();
    }

    public void validateDynamic() {
        logger.debug("Executing dynamic validations ");
        validate(presenter.getDynamicValidations());
    }

    private void validate(List validations) {
        List exceptions = new ArrayList();
        for (Iterator iterator = validations.iterator(); iterator.hasNext();) {
            Validator validator = (Validator) iterator.next();
            if (validator != null && validator.hasValidationRules()) {
                try {
                    if (validator.getPropertyValidator() != null)
                        validator.execute();
                } catch (AWValidationException ex) {
                    exceptions.add(ex);
                }
            }
        }
        if (exceptions.size() > 0) {
            throw new AWValidationException(exceptions);
        }
    }

    public void setCurrentTarget(Object currentTarget) {
        this.currentTarget = currentTarget;
    }

    public void buildBasicValidationIfNotDefined() {
        validatorBuilder.buildValidator();
    }

    public void resetValidations() {
        basicValidations = new ArrayList();
        extraValidations = new ArrayList();
    }
}
