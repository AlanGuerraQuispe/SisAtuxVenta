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

import com.aw.swing.mvp.binding.BindingComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: Sep 13, 2007
 */
public class PropertyValidator {

    /**
     * Label to be used to show the user messages
     */
    private String label;
    private BindingComponent bindingComponent;
    private boolean requerid;
    private int minLength = -1;
    private int maxLength = -1;
    private boolean onlyDigits = false;
    private boolean onlyLetters = false;
    private boolean moneyFormat = false;
    private boolean floatWithGroupFormat = false;
    private boolean dateFormat = false;
    private Object minValue;
    private Object maxValue;
    private boolean lowerCase=false;
    private boolean upperCase=false;

    /**
     * List of strings. Each string has to be a name of the methods of {@link AWDefaultRulesSource}
     */
    private List rules = new ArrayList();

    private boolean loadDefaultRules = false;

    public PropertyValidator() {

    }

    public PropertyValidator(boolean requerid, int minLength, int maxLength, boolean onlyDigits, boolean onlyLetters) {
        this.requerid = requerid;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.onlyDigits = onlyDigits;
        this.onlyLetters = onlyLetters;
    }

    public void add(String ruleName) {
        if (ruleName != null) {
            rules.add(ruleName);
        }
    }

    private void buildDefault() {
        if (isRequerid()) {
            rules.add(Rule.REQUIRED);
        }
        if (getMinLength() != -1) {
            rules.add(Rule.VALIDATE_LIMIT_TEXT_MINIMUN);
        }
        loadDefaultRules = true;
    }


    public Object[] getRules() {
        if (!loadDefaultRules)
            buildDefault();
        return rules.toArray();
    }

    public boolean isRequerid() {
        return requerid;
    }

    public void setRequerid(boolean requerid) {
        this.requerid = requerid;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public boolean isOnlyDigits() {
        return onlyDigits;
    }

    public void setOnlyDigits(boolean onlyDigits) {
        this.onlyDigits = onlyDigits;
    }

    public boolean isOnlyLetters() {
        return onlyLetters;
    }

    public void setOnlyLetters(boolean onlyLetters) {
        this.onlyLetters = onlyLetters;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public BindingComponent getBindingComponent() {
        return bindingComponent;
    }

    public void setBindingComponent(BindingComponent bindingComponent) {
        this.bindingComponent = bindingComponent;
    }


    public Object getMinValue() {
        return minValue;
    }

    public void setMinValue(Object minValue) {
        this.minValue = minValue;
    }

    public Object getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Object maxValue) {
        this.maxValue = maxValue;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropertyValidator that = (PropertyValidator) o;

        if (maxLength != that.maxLength) return false;
        if (minLength != that.minLength) return false;
        if (moneyFormat != that.moneyFormat) return false;
        if (onlyDigits != that.onlyDigits) return false;
        if (onlyLetters != that.onlyLetters) return false;
        if (requerid != that.requerid) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (label != null ? label.hashCode() : 0);
        result = 31 * result + (requerid ? 1 : 0);
        result = 31 * result + minLength;
        result = 31 * result + maxLength;
        result = 31 * result + (onlyDigits ? 1 : 0);
        result = 31 * result + (onlyLetters ? 1 : 0);
        result = 31 * result + (moneyFormat ? 1 : 0);

        return result;
    }


    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(" PropertyValidator");
        sb.append("{label='").append(label).append('\'');
        sb.append(", requerid=").append(requerid);
        sb.append(", minLength=").append(minLength);
        sb.append(", maxLength=").append(maxLength);
        sb.append(", onlyDigits=").append(onlyDigits);
        sb.append(", onlyLetters=").append(onlyLetters);
        sb.append(", isRequerid=").append(isRequerid());
        sb.append(", isOnlyDigits=").append(isOnlyDigits());
        sb.append(", isOnlyLetters=").append(isOnlyLetters());
        sb.append(", ruleList=").append(rules);
        sb.append('}');
        return sb.toString();
    }

    public boolean isMoneyFormat() {
        return moneyFormat;
    }

    public void setMoneyFormat(boolean moneyFormat) {
        this.moneyFormat = moneyFormat;
    }

    public void setFloatWithGroupFormat(boolean floatWithGroupFormat) {
        this.floatWithGroupFormat = floatWithGroupFormat;
    }

    public boolean isFloatWithGroupFormat() {
        return floatWithGroupFormat;
    }

    public boolean isDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(boolean dateFormat) {
        this.dateFormat = dateFormat;
    }

}

