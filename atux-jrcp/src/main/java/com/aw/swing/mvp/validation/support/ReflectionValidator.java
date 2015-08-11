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


import com.aw.support.reflection.MethodInvoker;
import com.aw.swing.exception.AWValidationException;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.validation.Validator;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Date: Sep 13, 2007
 */
public class ReflectionValidator extends Validator {

    private Object target = null;
    private String method = null;
    private Object[] methods = null;
    private JComponent jComponentDiscriminator;
    private JComponent jComponentOnFocus;
    private BeanWrapper discriminator;

    private String discriminatorField;

    public static final String PREFIX_EXECUTE_VALIDATION = "validate";


    public ReflectionValidator() {
        this.propertysValidators = new HashMap();
    }

    /**
     * Used when call only validate methods
     *
     * @param target
     */
    public ReflectionValidator(Object target) {
        this();
        this.target = target;

    }

    /**
     * Used when call instance.method
     *
     * @param target
     * @param method
     */
    public ReflectionValidator(Object target, String method) {
        this(target);
        this.method = method;
    }

    public ReflectionValidator(Object target, String[] methods) {
        this(target);
        this.methods = methods;
    }


    public ReflectionValidator(JComponent discriminator, String discriminatorField) {
        this();
        this.jComponentDiscriminator = discriminator;
        this.discriminator = new BeanWrapperImpl(discriminator);
        this.discriminatorField = discriminatorField;
        this.propertysValidators = new HashMap();
        registerPropertyValidators();
    }

    protected void registerPropertyValidators() {

    }

    public Object executeIntern() {
        try {
            PropertyValidator propertyValidator = getCurrentPropertyValidator();
            if (method != null) {
                return MethodInvoker.invoke(target, method);
            } else if (propertyValidator != null && propertyValidator.getRules().length != 0) {
                methods = propertyValidator.getRules();
                return MethodInvoker.invoke(new AWDefaultRulesSource(), methods, propertyValidator);
            } else if (methods != null) {
                return MethodInvoker.invoke(target, methods);
            } else {
                return MethodInvoker.invokeMethodWithPrefix(target, PREFIX_EXECUTE_VALIDATION);
            }
        } catch (AWValidationException ex) {
            if (ex.getJComponent() == null) {
                ex.setJComponent(jComponentOnFocus);
            }
            throw ex;
        } catch (Throwable throwable) {
            throw new IllegalStateException("Problems executing the action. Check that exists the method:<" + getCurrentPropertyValidator() + "> in:<" + target + "> Exception:" + throwable.getMessage());
        }
    }

    public boolean hasValidationRules() {
        boolean existRules = getCurrentPropertyValidator().getRules().length != 0;
        boolean existMethod = method != null;
        boolean existMethods = methods != null && methods.length != 0;
        return existRules || existMethod || existMethods;
    }


    public PropertyValidator getCurrentPropertyValidator() {
        PropertyValidator propertyValidator = null;

        if (discriminator != null) {

            String discriminatorValue = (String) discriminator.getPropertyValue(discriminatorField);
            propertyValidator = (PropertyValidator) propertysValidators.get(discriminatorValue);
        }
        if (propertyValidator == null) {
            if (propertysValidators.containsKey(Validator.DEFAULT_PROPERTY_VALIDATOR)) {
                propertyValidator = (PropertyValidator) propertysValidators.get(Validator.DEFAULT_PROPERTY_VALIDATOR);
            } else {
                propertyValidator = Validator.EMPTY_VALIDATOR;
            }
        }
        return propertyValidator;
    }

    public void registerPropertyValidator(String ruleCase, PropertyValidator propertyValidator) {
        if (propertysValidators.size() == 0) {
            propertysValidators.put(Validator.DEFAULT_PROPERTY_VALIDATOR, propertyValidator);
        }else{
            propertysValidators.put(ruleCase, propertyValidator);
        }

    }

    public void registerPropertyValidator(String ruleCase, String rulePattern) {
        PropertyValidator propertyValidator = PatternRules.buildPropertyValidator(rulePattern);
        propertyValidator.getRules();
        registerPropertyValidator(ruleCase, propertyValidator);
    }

    public void registerBindingComponent(BindingComponent bindingComponent) {
        for (Iterator iterator = propertysValidators.values().iterator(); iterator.hasNext();) {
            ((PropertyValidator) iterator.next()).setBindingComponent(bindingComponent);
        }
    }


    public void setFocusErrorOn(JComponent jComponent) {
        this.jComponentOnFocus = jComponent;
    }

    public JComponent getJComponent() {
        return jComponentOnFocus;
    }

    public JComponent getJComponentDiscriminator() {
        return jComponentDiscriminator;
    }

    public void registerPropertyValidator(PropertyValidator propertyValidator) {
        registerPropertyValidator(Validator.DEFAULT_PROPERTY_VALIDATOR, propertyValidator);
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public String toString() {
        return "execute all methos in " + target + " propertyValidator =" + getCurrentPropertyValidator();
    }

    public Object getTarget() {
        return target;
    }
}
