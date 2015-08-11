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

import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.validation.support.PropertyValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Date: Sep 13, 2007
 * Class used to validate each jComponent
 */
public abstract class Validator {

    protected final Log logger = LogFactory.getLog(getClass());

    public static final PropertyValidator EMPTY_VALIDATOR = new PropertyValidator(false, -1, -1, false, false);
    public static final String DEFAULT_PROPERTY_VALIDATOR = "DEFAULT";

    protected Map propertysValidators;

    public List execute() {
        Object result = executeIntern();
        return getAsList(result);
    }

    private List getAsList(Object result) {
        List results = new ArrayList();
        if (result == null) return results;
        if (result instanceof List) {
            results.addAll((List) result);
        } else {
            results.add(result);
        }
        return results;
    }

    protected abstract Object executeIntern();


    public abstract boolean hasValidationRules();


    public abstract PropertyValidator getCurrentPropertyValidator();

    public PropertyValidator getPropertyValidator() {
        return getCurrentPropertyValidator();
    }

    public abstract void registerPropertyValidator(String ruleCase, PropertyValidator propertyValidator);

    public abstract void registerPropertyValidator(String ruleCase, String rulePattern);

    public abstract void registerPropertyValidator(PropertyValidator propertyValidator);

    public abstract void setFocusErrorOn(JComponent jComponent);

    public abstract void setTarget(Object target);

    public abstract Object getTarget();

    public abstract void registerBindingComponent(BindingComponent bindingComponent);


    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("propertysValidators = ").append(propertysValidators.get(DEFAULT_PROPERTY_VALIDATOR));
        return sb.toString();
    }


    public boolean hasDefaultValidator() {
        return propertysValidators.size() == 1 &&  propertysValidators.get(DEFAULT_PROPERTY_VALIDATOR).equals(EMPTY_VALIDATOR);
    }

    public boolean propertyValueIsRequerid(){
        for (Object o : propertysValidators.values()) {
            if (o instanceof PropertyValidator){
                PropertyValidator propertyValidator = (PropertyValidator) o;
                return propertyValidator.isRequerid();
            }
        }
        return false;
    }
}
