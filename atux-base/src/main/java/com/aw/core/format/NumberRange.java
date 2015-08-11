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
package com.aw.core.format;

//import com.aw.swing.exception.AWValidationException;

import java.text.DecimalFormat;

/**
 * Null Support
 *
 * @author jcvergara
 *         05/11/2004
 */
public class NumberRange {

    private boolean onInvalidRangeAdjust = true;
    private Comparable minimun;
    private Comparable maximun;

    public NumberRange(Comparable minimun, Comparable maximun) {
        this.minimun = minimun;
        this.maximun = maximun;
    }

    public Comparable getMinimun() {
        return minimun;
    }

    public Comparable getMaximun() {
        return maximun;
    }

    public void setOnInvalidRangeAdjust(boolean onInvalidRangeAdjust) {
        this.onInvalidRangeAdjust = onInvalidRangeAdjust;
    }

    /**
     * Enforces the number to be in the range specified by this class
     *
     * @param number
     * @return
     */
    public Comparable enforceInRange(DecimalFormat decimalFormat, Comparable number) {
        checkRange(decimalFormat, number);
        if (number == null) return number;
        // compareTo
        //  return a negative integer, zero, or a positive integer as this object
        //		is less than, equal to, or greater than the specified object.
        if (minimun != null && minimun.compareTo(number) > 0) return minimun;
        if (maximun != null && maximun.compareTo(number) < 0) return maximun;
        return number;
    }

    private void checkRange(DecimalFormat decimalFormat, Comparable number) {
        if (onInvalidRangeAdjust || number == null) return;
//aw-framework: desacoplar AWValidationException de Swing        
//        if (minimun != null && minimun.compareTo(number) > 0)
//            throw new AWValidationException("sw.error.validate.rangoMinInvalido");

//        if (maximun != null && maximun.compareTo(number) < 0)
//            throw new AWValidationException("sw.error.validate.rangoMaxInvalido");

    }

}
