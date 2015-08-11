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
package com.aw.swing.mvp.binding;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

/**
 * Codigo extraido de internet, usado para limitar los caracteres
 * ingresados en un JTextFieldInputHandler
 *
 * @author jcvergara
 *         09/10/2004
 */
public class JTextFieldInputHandler extends PlainDocument {
    public static char DECIMAL_SYMBOL = '.';
    public static char MILLAR_SYMBOL = ',';
    public static char MINUS_SYMBOL = '-';
    public static char DATE_SLASH_SYMBOL = '/';

    private int limit = -1;
    // optional uppercase conversion
    private Boolean toUppercase = Boolean.FALSE;
    /**
     * Point out if this text field only will accept digits
     */
    private boolean onlyDigits = false;
    /**
     * Indicate if input accept date Slash Symbol.
     * Valid if {@link #onlyDigits} is TRUE
     */
    private boolean slashSymbolAllowed = false;
    /**
     * Indicate if input accept decimal Symbol.
     * Valid if {@link #onlyDigits} is TRUE
     */
    private boolean decimalSymbolAllowed = false;
    /**
     * Indicate if input accept Millar Symbol separator.
     * Valid if {@link #onlyDigits} is TRUE
     */
    private boolean millarSymbolAllowed = false;

    /**
     * Indicate if input accept - Symbol.
     * Valid if {@link #onlyDigits} is TRUE
     */
    private boolean minusSymbolAllowed = false;
    /**
     * Point out if this text field only will accept letters
     */
    private boolean onlyLetters = false;

    private JTextFieldChangeListener textChangeListener;

    private boolean addDateSlash = false;

    public JTextFieldInputHandler() {
    }

    public JTextFieldInputHandler(int limit) {
        this();
        setLimit(limit);
    }

    public void insertString(int offset, String str, AttributeSet attr)
            throws BadLocationException {
        if (str != null) {
            if (onlyLetters || onlyDigits) {
                str = getNumberOrLettersFromString(str, onlyDigits);
            }
            if (toUppercase != null) {
                if (toUppercase.booleanValue())
                    str = str.toUpperCase();
                else if (!toUppercase.booleanValue())
                    str = str.toLowerCase();
            }
        }

        if (limit < 0) {
            super.insertString(offset, str, attr);
            return;
        }

        int excessCharCount = (getLength() + (str == null ? 0 : str.length())) - limit;
        if (excessCharCount > 0 && str != null)
            str = str.substring(0, str.length() - excessCharCount);
        if (getLength() < limit) {
//            System.out.println("YYYYYY");
            super.insertString(offset, str, attr);

            if (addDateSlash) {
                int lenght = getLength();
                if ((lenght == 2) || (lenght == 5))
                    super.insertString(lenght, "/", attr);
            }
        } else {
//            System.out.println("ZZZZZZZ");
            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * Support to {@link #textChangeListener}
     *
     * @param chng
     * @param attr
     */
    protected void insertUpdate(DefaultDocumentEvent chng, AttributeSet attr) {
        try {
            String oldStr = getText(0, getLength());
            super.insertUpdate(chng, attr);
            processTextChange(oldStr);
        } catch (BadLocationException e) {

        }
    }

    private void processTextChange(String oldStr) throws BadLocationException {
        if (textChangeListener != null) {
            String newStr = getText(0, getLength());
            textChangeListener.onTextChange(newStr);
        }
    }

    /**
     * Support to {@link #textChangeListener}
     *
     * @param chng
     */
    protected void postRemoveUpdate(DefaultDocumentEvent chng) {
        try {
            String oldStr = getText(0, getLength());
            super.postRemoveUpdate(chng);
            processTextChange(oldStr);
        } catch (BadLocationException e) {
        }
    }

    /**
     * Return the first numbers of the source.
     *
     * @param source
     * @return
     */
    private String getNumberOrLettersFromString(String source, boolean isNumber) {
        StringBuffer sb = new StringBuffer();
        boolean beepFired = false;
        for (int i = 0; i < source.length(); i++) {
            char currentChar = source.charAt(i);
            if (isNumber &&
                    (Character.isDigit(currentChar) || currentChar=='-' ||
                            (slashSymbolAllowed && currentChar == DATE_SLASH_SYMBOL) ||
                            (!slashSymbolAllowed &&
                                    ((decimalSymbolAllowed && currentChar == DECIMAL_SYMBOL) ||
                                            (millarSymbolAllowed && currentChar == MILLAR_SYMBOL) ||
                                            (minusSymbolAllowed && currentChar == MINUS_SYMBOL)
                                    )
                            )
                    )
                    ) {
                sb.append(currentChar);
            } else if (!isNumber && (currentChar != 186) &&
                    (Character.isLetter(currentChar) || Character.isWhitespace(currentChar))) {
                sb.append(currentChar);
            } else {
                if (!beepFired)
                    Toolkit.getDefaultToolkit().beep();
                beepFired = true;
                break;
            }
        }
        return sb.toString();
    }

    public void setLimit(final int limit) {
        this.limit = limit;
    }

    public void setToUpperCase(Boolean toUppercase) {
//        this.toUppercase = toUppercase ? Boolean.TRUE : Boolean.FALSE;
        this.toUppercase = toUppercase;
    }

    public void doNotChangeCase() {
        this.toUppercase = null;
    }

    public void setOnlyDigits(boolean onlyDigits) {
        this.onlyDigits = onlyDigits;
    }

    public void configureForNumbers(String format) {
        this.setOnlyDigits(true);
        if (format != null) {
            decimalSymbolAllowed = format.indexOf(DECIMAL_SYMBOL) >= 0;
            millarSymbolAllowed = format.indexOf(MILLAR_SYMBOL) >= 0;
        }
    }

    public void setOnlyLetters(boolean onlyLetters) {
        this.onlyLetters = onlyLetters;
    }

    public void setTextChangeListener(JTextFieldChangeListener textChangeListener) {
        this.textChangeListener = textChangeListener;
    }

    public void setAddDateSlash(boolean addDateSlash) {
        this.addDateSlash = addDateSlash;
    }


    public boolean isDecimalSymbolAllowed() {
        return decimalSymbolAllowed;
    }

    public void setDecimalSymbolAllowed(boolean decimalSymbolAllowed) {
        this.decimalSymbolAllowed = decimalSymbolAllowed;
    }

    public boolean isMillarSymbolAllowed() {
        return millarSymbolAllowed;
    }

    public void setMillarSymbolAllowed(boolean millarSymbolAllowed) {
        this.millarSymbolAllowed = millarSymbolAllowed;
    }

    public void setSlashSymbolAllowed(boolean slashSymbolAllowed) {
        this.slashSymbolAllowed = slashSymbolAllowed;
    }

    public void setMinusSymbolAllowed(boolean minusSymbolAllowed) {
        this.minusSymbolAllowed = minusSymbolAllowed;
    }
}
