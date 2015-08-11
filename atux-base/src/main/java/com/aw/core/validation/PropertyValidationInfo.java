package com.aw.core.validation;

import org.apache.commons.lang.StringUtils;

/**
 * Hold all of the property validation information
 * User: gmc
 * Date: 07-ago-2008
 */
public class PropertyValidationInfo {
    private static final String REQUERID = "R";
    private static final String MAXLENGTH = "Y";
    private static final String MINLENGTH = "X";
    private static final String DIGITS = "D";
    private static final String LETTERS = "L";

    private String propertyName;
    /**
     * Label to be used to show the user messages
     */
    private String label;
    private boolean requerid = false;
    private int minLength = -1;
    private int maxLength = -1;
    private boolean onlyDigits = false;
    private boolean onlyLetters = false;
    private boolean moneyFormat = false;
    private boolean dateFormat = false;
    private boolean isEmail = false;
    private boolean isUrl = false;
    private Object minValue = new Integer(-1);
    private Object maxValue = new Integer(-1);

    public PropertyValidationInfo() {
    }

    public PropertyValidationInfo(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getLabel() {
        if (StringUtils.isEmpty(label)) {
            return propertyName;
        }
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public boolean isMoneyFormat() {
        return moneyFormat;
    }

    public void setMoneyFormat(boolean moneyFormat) {
        this.moneyFormat = moneyFormat;
    }

    public boolean isDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(boolean dateFormat) {
        this.dateFormat = dateFormat;
    }

    public boolean isEmail() {
        return isEmail;
    }

    public void setEmail(boolean email) {
        isEmail = email;
    }

    public boolean isUrl() {
        return isUrl;
    }

    public void setUrl(boolean url) {
        isUrl = url;
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

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropertyValidationInfo)) return false;

        PropertyValidationInfo that = (PropertyValidationInfo) o;

        if (!propertyName.equals(that.propertyName)) return false;

        return true;
    }

    public int hashCode() {
        return propertyName.hashCode();
    }

    /**
     * RM2W6D
     *
     * @param validationPattern
     * @return
     */
    public void populate(String validationPattern) {
        setRequerid(validationPattern.startsWith(REQUERID));
        int minLength = validationPattern.indexOf(MINLENGTH);
        int maxLength = validationPattern.indexOf(MAXLENGTH);
        int length = validationPattern.length();
        if (minLength != -1) {
            if (StringUtils.isNumeric(String.valueOf(validationPattern.charAt(minLength + 1)))) {
                StringBuffer min = new StringBuffer();
                while (((minLength + 1) < length) && StringUtils.isNumeric(String.valueOf(validationPattern.charAt(minLength + 1)))) {
                    min.append(validationPattern.charAt(minLength + 1));
                    minLength++;
                }
                setMinLength(Integer.parseInt(min.toString()));
            }
        }
        if (maxLength != -1) {
            if (StringUtils.isNumeric(String.valueOf(validationPattern.charAt(maxLength + 1)))) {
                StringBuffer max = new StringBuffer();
                while (((maxLength + 1) < length) && StringUtils.isNumeric(String.valueOf(validationPattern.charAt(maxLength + 1)))) {
                    max.append(validationPattern.charAt(maxLength + 1));
                    maxLength++;
                }
                setMaxLength(Integer.parseInt(max.toString()));
            }
        }

        if (getMaxLength() != -1 && getMaxLength() < getMinLength()) {
            throw new RuntimeException("Incorrect value for min:" + getMinLength() + " max:" + getMaxLength());
        }
        boolean onlyDigits = validationPattern.endsWith(DIGITS);
        boolean onlyLetters = validationPattern.endsWith(LETTERS);
        setOnlyDigits(onlyDigits);
        setOnlyLetters(onlyLetters);
    }
}
