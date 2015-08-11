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

import com.aw.core.context.AWBaseContext;
import com.aw.core.format.FillerFormat;
import com.aw.core.format.Formatter;
import com.aw.core.format.NumberFormatter;
import com.aw.core.util.TimeObserver;
import com.aw.core.validation.annotation.*;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.binding.BndFormattedComponent;
import com.aw.swing.mvp.binding.component.BndIJLabelField;
import com.aw.swing.mvp.binding.component.BndIJTextField;
import com.aw.swing.mvp.validation.Validator;
import com.aw.swing.mvp.validation.support.PatternRules;
import com.aw.swing.mvp.validation.support.PropertyValidator;
import com.aw.swing.mvp.validation.support.Rule;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * User: User
 * Date: Oct 9, 2007
 */
public class ValidatorBuilder {
    protected final Log logger = LogFactory.getLog(getClass());
    Presenter presenter;
    List bindingComponents = new ArrayList();
    DomainBuilder domainBuilder;

    public ValidatorBuilder() {

    }

    public ValidatorBuilder(Presenter presenter) {
        this.presenter = presenter;
        if (presenter.getBackBean() != null) {
            domainBuilder = new DomainBuilder(presenter.getBackBean());
        } else {
            domainBuilder = new DomainBuilder(presenter);
        }
    }

    public void buildValidator() {
        logger.debug("Executing validatorBuilder");
        TimeObserver time = new TimeObserver("Executing validatorBuilder");
        time.empezar();
        List bindingComponents = presenter.getAllBindings();
        for (Iterator iterator = bindingComponents.iterator(); iterator.hasNext();) {
            BindingComponent bindingComponent = (BindingComponent) iterator.next();
            if (bindingComponent instanceof BndIJTextField && bindingComponent.hasDefaultValidator()) {
                if (bindingComponent.getBeanWrapper() != null) {
                    domainBuilder.registerDomain(bindingComponent.getBeanWrapper().getWrappedInstance());
                } else {
                    if (presenter.getBackBean() != null) {
                        domainBuilder.registerDomain(presenter.getBackBean());
                    } else {
                        domainBuilder.registerDomain(presenter);
                    }
                }
                buildValidatorFor(bindingComponent);
            }
        }
        time.terminar();
    }
              




    protected void buildValidatorFor(BindingComponent bindingComponent) {
        String attribute = bindingComponent.getFieldName();
        logger.debug("Attribute <" + attribute + ">");
        Method method = domainBuilder.getMethodFor(attribute);
        if (method == null) {
            return;
        }

        boolean isDate = method.getReturnType().equals(java.util.Date.class);
        if (isDate) {
            if (bindingComponent instanceof BndIJTextField) {
                BndIJTextField bndIJTextField = (BndIJTextField) bindingComponent;
                if (bndIJTextField.getPropertyBinding().getFormatter()==null){
                    bndIJTextField.formatAsDate();
                }
            } else if (bindingComponent instanceof BndIJLabelField) {
                ((BndIJLabelField) bindingComponent).registerAsDateFormatted();
            }
        }

        PropertyValidator propertyValidator = buildPropertyValidator(method);

        if (method.isAnnotationPresent(Label.class)) {
            Label label = method.getAnnotation(Label.class);
            bindingComponent.setLabelToBeUsedOnError(label.value());
        }

        if (method.isAnnotationPresent(FillFormat.class)) {
            FillFormat fillFormat = method.getAnnotation(FillFormat.class);
            Formatter fillerFormatter = new FillerFormat(fillFormat.character(), fillFormat.lenght());
            ((BndIJTextField) bindingComponent).formatUsingCustomFormatter(fillerFormatter);
            ((BndIJTextField) bindingComponent).setLimitTextSize(fillFormat.lenght());
        }
        if (method.isAnnotationPresent(NotChangeCase.class)) {
            if (bindingComponent instanceof BndIJTextField) {
                ((BndIJTextField) bindingComponent).setAsNotChangeCase();
            }
        }
        if (method.isAnnotationPresent(NumberFormat.class)) {
            NumberFormat numberFormat = method.getAnnotation(NumberFormat.class);
            if (bindingComponent instanceof BndFormattedComponent) {
                if (numberFormat.value().length() != 0) {
                    ((BndIJTextField) bindingComponent).formatUsingCustomFormatter(new NumberFormatter(numberFormat.value()));
                } else {
                    ((BndIJTextField) bindingComponent).formatAsNumber();
                }
            }
        }

        if (method.isAnnotationPresent(MoneyFormat.class)) {
            MoneyFormat numberFormat = method.getAnnotation(MoneyFormat.class);
            if (bindingComponent instanceof BndFormattedComponent) {
                if (numberFormat.value().length() != 0) {
                    ((BndIJTextField) bindingComponent).formatUsingCustomFormatter(new NumberFormatter(numberFormat.value()));
                } else {
                    ((BndIJTextField) bindingComponent).formatAsMoney();
                }
                propertyValidator.setMoneyFormat(true);
            }
        }

        if (method.isAnnotationPresent(PercentFormat.class)) {
            if (bindingComponent instanceof BndFormattedComponent) {
                ((BndIJTextField) bindingComponent).formatAsPercentage();
            }
        }
        propertyValidator.setDateFormat(isDate);

        if (propertyValidator == null || propertyValidator.equals(Validator.EMPTY_VALIDATOR)) return;


        presenter.getValidatorMgr().registerBasicRule((BndIJTextField) bindingComponent, propertyValidator);
    }

/*    public String convertPropertyNameToGetMethodName(String property) {
        if (property == null) {
            throw new IllegalArgumentException("Property name was null.");
        }
        StringBuilder builder = new StringBuilder(property.length() + 3);
        String firstLetter = property.substring(0, 1);
        String wordRemainder = property.substring(1);
        firstLetter = firstLetter.toUpperCase();
        builder.append("get").append(firstLetter).append(wordRemainder);
        return builder.toString();
    }*/

    public PropertyValidator buildPropertyValidator(Method method) {
        PropertyValidator propertyValidator = new PropertyValidator();
        if (method.isAnnotationPresent(Validation.class)) {
            Validation validation = method.getAnnotation(Validation.class);
            String patterValidator = validation.value();
            logger.debug("Patter Validator = " + patterValidator);
            propertyValidator = PatternRules.buildPropertyValidator(patterValidator);

        }

        if (method.isAnnotationPresent(Email.class)) {
            propertyValidator.add(Rule.VALIDATE_EMAIL);
        }
        if (method.isAnnotationPresent(RangeNumber.class)) {
            RangeNumber range = method.getAnnotation(RangeNumber.class);
            propertyValidator.add(Rule.VALIDATE_RANGE);
            propertyValidator.setMinValue(range.min());
            propertyValidator.setMaxValue(range.max());
        }
        if (method.isAnnotationPresent(RangeDate.class)) {
            SimpleDateFormat sdf = new SimpleDateFormat(AWBaseContext.instance().getConfigInfoProvider().getDateFormat());
            RangeDate range = method.getAnnotation(RangeDate.class);
            propertyValidator.add(Rule.VALIDATE_RANGE);
            Date from = null;
            Date to = null;
            try {
                if (range.from().equals("today")) {
                    from = new Date();
                } else {
                    from = sdf.parse(range.from());
                }
                if (range.to().equals("today")) {
                    to = new Date();
                } else {
                    to = sdf.parse(range.to());
                }

                propertyValidator.setMinValue(from);
                propertyValidator.setMaxValue(to);
            } catch (ParseException e) {
                logger.error("Error execute parse from <" + range.from() + "> to <" + range.to() + ">");
            }
        }
        if (method.isAnnotationPresent(Past.class)) {
            propertyValidator.add(Rule.VALIDATE_LESS_THAN);
            propertyValidator.setMaxValue(new Date());
        }
        if (method.isAnnotationPresent(Future.class)) {
            propertyValidator.add(Rule.VALIDATE_GREATER_THAN);
            propertyValidator.setMinValue(new Date());
        }
        return propertyValidator;
    }
}

