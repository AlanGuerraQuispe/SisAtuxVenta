package com.aw.swing.mvp.binding.component.support;

import com.aw.core.format.Formatter;

import javax.swing.*;

/**
 * User: User
 * Date: Jan 7, 2008
 */
public class PropertyBinding {
    private int horizontalAligement= JTextField.LEFT;
    private int limitTextSize =-1;
    private Formatter formatter;
    private boolean onlyDigits;
    private boolean onlyLetters;
    private boolean decimalSymbolAllowed;
    private boolean MillarSymbolAllowed;
    private Boolean toUpperCase= Boolean.TRUE;
    private boolean readOnly;
    private boolean lineWrap;
    private boolean wrapStyleWord;
    private String configureForNumbers;
    private boolean slashSymbolAllowed;
    private boolean minusSymbolAllowed;
    private Boolean trim;

    public int getHorizontalAligement() {
        return horizontalAligement;
    }

    public void setHorizontalAligement(int horizontalAligement) {
        this.horizontalAligement = horizontalAligement;
    }

    public int getLimitTextSize() {
        return limitTextSize;
    }

    public void setLimitTextSize(int limitTextSize) {
        this.limitTextSize = limitTextSize;
    }

    public Formatter getFormatter() {
        return formatter;
    }

    public void setFormatter(Formatter formatter) {
        // soporte multithreading
        formatter = formatter.clone();
        this.formatter = formatter;
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

    public boolean isDecimalSymbolAllowed() {
        return decimalSymbolAllowed;
    }

    public void setDecimalSymbolAllowed(boolean decimalSymbolAllowed) {
        this.decimalSymbolAllowed = decimalSymbolAllowed;
    }

    public boolean isMillarSymbolAllowed() {
        return MillarSymbolAllowed;
    }

    public void setMillarSymbolAllowed(boolean millarSymbolAllowed) {
        MillarSymbolAllowed = millarSymbolAllowed;
    }

    public Boolean getToUpperCase() {
        return toUpperCase;
    }

    public void setToUpperCase(Boolean toUpperCase) {
        this.toUpperCase = toUpperCase;
    }

    public boolean isReadOnly() {
        return readOnly;
    }                                                   

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isLineWrap() {
        return lineWrap;
    }

    public void setLineWrap(boolean lineWrap) {
        this.lineWrap = lineWrap;
    }

    public boolean isWrapStyleWord() {
        return wrapStyleWord;
    }

    public void setWrapStyleWord(boolean wrapStyleWord) {
        this.wrapStyleWord = wrapStyleWord;
    }

    public String getConfigureForNumbers() {
        return configureForNumbers;
    }

    public void setConfigureForNumbers(String configureForNumbers) {
        this.configureForNumbers = configureForNumbers;
    }

    public boolean isSlashSymbolAllowed() {
        return slashSymbolAllowed;
    }

    public void setSlashSymbolAllowed(boolean slashSymbolAllowed) {
        this.slashSymbolAllowed = slashSymbolAllowed;
    }

    public Boolean getTrim() {
        return trim;
    }

    public void setTrim(Boolean trim) {
        this.trim = trim;
    }

    public boolean isMinusSymbolAllowed() {
        return minusSymbolAllowed;
    }

    public void setMinusSymbolAllowed(boolean minusSymbolAllowed) {
        this.minusSymbolAllowed = minusSymbolAllowed;
    }
}
