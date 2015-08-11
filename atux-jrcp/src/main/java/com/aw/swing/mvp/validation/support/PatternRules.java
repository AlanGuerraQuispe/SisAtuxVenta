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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Date: Sep 13, 2007
 */
public class PatternRules {

    private static final Log logger = LogFactory.getLog(PatternRules.class);

    private static final String REQUERID = "R";
    private static final String MAXLENGTH = "Y";
    private static final String MINLENGTH = "X";
    private static final String DIGITS = "D";
    private static final String LETTERS = "L";

    /**
     * RM2W6D
     *
     * @param pattern
     * @return
     */
    public static PropertyValidator buildPropertyValidator(String pattern) {

        // filter requerid
        PropertyValidator validator = new PropertyValidator();

        validator.setRequerid(pattern.startsWith(REQUERID));

        int minLength = pattern.indexOf(MINLENGTH);
        int maxLength = pattern.indexOf(MAXLENGTH);
        int length = pattern.length();
        if (minLength != -1) {
            if (StringUtils.isNumeric(String.valueOf(pattern.charAt(minLength + 1)))) {
                StringBuffer min = new StringBuffer();
                while (((minLength + 1) < length) && StringUtils.isNumeric(String.valueOf(pattern.charAt(minLength + 1)))) {
                    min.append(pattern.charAt(minLength + 1));
                    minLength++;
                }
                validator.setMinLength(Integer.parseInt(min.toString()));
            }
        }
        if (maxLength != -1) {
            if (StringUtils.isNumeric(String.valueOf(pattern.charAt(maxLength + 1)))) {
                StringBuffer max = new StringBuffer();
                while (((maxLength + 1) < length) && StringUtils.isNumeric(String.valueOf(pattern.charAt(maxLength + 1)))) {
                    max.append(pattern.charAt(maxLength + 1));
                    maxLength++;
                }
                validator.setMaxLength(Integer.parseInt(max.toString()));
            }
        }

        if (validator.getMaxLength() != -1 && validator.getMaxLength() < validator.getMinLength()) {
            throw new RuntimeException("Incorrect asignacion min:" + validator.getMinLength() + " max:" + validator.getMaxLength());
        }
        boolean onlyDigits = pattern.endsWith(DIGITS);
        boolean onlyLetters = pattern.endsWith(LETTERS);

        validator.setOnlyDigits(onlyDigits);
        validator.setOnlyLetters(onlyLetters);
        return validator;
    }
}

