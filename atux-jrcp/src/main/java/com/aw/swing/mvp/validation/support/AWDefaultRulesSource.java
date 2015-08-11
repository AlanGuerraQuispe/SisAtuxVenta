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

import com.aw.support.date.AWDate;
import com.aw.swing.exception.AWValidationException;
import com.aw.swing.mvp.binding.BindingComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.GenericValidator;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Contains all the validation rules
 */
public class AWDefaultRulesSource {
    protected static final Log logger = LogFactory.getLog(AWDefaultRulesSource.class);

    /**
     * Build the rule object for the required rule
     *
     * @return
     */
    public void isRequired(Object propertyValidator) {
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        Object value = inputComponent.getValue();
//        if (value== null || !ValidationUtils.isNotEmpty(value.toString().trim())) {
        if (!inputComponent.isUiReadOnly() && (value== null || !StringUtils.hasText(value.toString().trim()))) {
            if (logger.isInfoEnabled()) {
                logger.info("isRequired validation " + inputComponent.getFieldName() + " [FAIL]");
            }
            throw new AWValidationException("sw.error.isrequerid", Arrays.asList(new Object[]{inputComponent}));
        }
    }

    public void validateTextMin(Object propertyValidator) {
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        String value = inputComponent.getValue().toString();
        if (value.length() == 0) {
            return;
        }
        int min = ((PropertyValidator) propertyValidator).getMinLength();
        logger.info("Validation " + inputComponent.getFieldName() + ": min length is " + min + "?");
        int length = value.length();
        if (Math.min(min,length)!=min){
            logger.info("validation [FAIL]");
            throw new AWValidationException("sw.error.validate.textMin", new Object[]{new BigDecimal(min)}, Arrays.asList(new Object[]{inputComponent}));
        }
//        if (!ValidationUtils.hasMinimumLength(value, min)) {
//            logger.info("validation [FAIL]");
//            throw new AWValidationException("sw.error.validate.textMin", new Object[]{new BigDecimal(min)}, Arrays.asList(new Object[]{inputComponent}));
//        }
    }

    public void validateEmail(Object propertyValidator) {
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        logger.info("validation " + inputComponent.getFieldName() + ": is email");
        String valorCampo = (String) inputComponent.getValue();
        if (valorCampo != null) {
            StringTokenizer st = new StringTokenizer(valorCampo, ",");
            while (st.hasMoreTokens()) {
                String email = st.nextToken();
                if (!GenericValidator.isEmail(email)) {
                    logger.info("validation [FAIL]");
                    throw new AWValidationException("sw.error.validate.email", Arrays.asList(new Object[]{inputComponent}));
                }
            }
        }
    }

    public void validateRUC(Object propertyValidator) {
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        String ruc = (String) inputComponent.getValue();
        if (ruc == null || ruc.equals("")) return; // nothing to validate
        ruc = ruc.trim();

        // longitud
        if (ruc.length() != 11) {
            logger.info("RUC[" + ruc + "] no tiene 11 digitos");
            throw new AWValidationException("RUC[" + ruc + "] no tiene 11 digitos");
        }

        // digitos
        Character noDigito = findNonDigit(ruc);
        if (noDigito != null) {
            logger.info("RUC[" + ruc + "] tiene caracter no valido:'" + noDigito + "'");
            throw new AWValidationException("RUC[" + ruc + "] tiene caracter no valido:'" + noDigito + "'");
        }

        String rucPrefix = ruc.substring(0, 2);
        List rucSerieList = Arrays.asList(new String[]{"10", "15", "20", "17"});
        if (rucSerieList.indexOf(rucPrefix) < 0) {
            // ruc NO pertenece a la serie
        }
        int suma = 0, x = 6;
        for (int i = 0; i < 10; i++) {
            if (i == 4) x = 8;
            int digito = ruc.charAt(i) - '0';
            x--;

            //Nota esto hace lo mismo, pero esta asi en el SP, confirmar si hay error
            if (i == 0) suma += digito * x;
            else suma += digito * x;
        }
        int resto = suma % 11;
        resto = 11 - resto;
        if (resto >= 10) resto -= 10;

        int ultDigito = ruc.charAt(10) - '0';
        boolean rucOk = resto == ultDigito;
        if (!rucOk) {
            logger.info("RUC[" + ruc + "] digito de verificacion incorrecto calculado:'" + resto + "'");
            throw new AWValidationException("RUC[" + ruc + "] digito de verificacion incorrecto calculado:'" + resto + "'");
        }
    }

    private static Character findNonDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)))
                return new Character(str.charAt(i));
        }
        return null;
    }

    public void validateRange(Object propertyValidator) {
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        Object valorCampo = inputComponent.getValue();

        logger.info("Field Value:" + valorCampo);
        if (valorCampo instanceof Date) {
            logger.info(" is Date");
            validateRangeDate(propertyValidator);
        } else if (valorCampo instanceof Number) {
            logger.info(" is Number");
            validateRangeNumber(propertyValidator);
        }
    }

    private void validateRangeDate(Object propertyValidator) {
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        AWDate df = new AWDate((Date) ((PropertyValidator) propertyValidator).getMinValue());
        AWDate dt = new AWDate((Date) ((PropertyValidator) propertyValidator).getMaxValue());
        logger.info("validation " + inputComponent.getFieldName() + ": Range");
        AWDate valorCampo = new AWDate((Date) inputComponent.getValue());
        if (valorCampo != null) {
            if (!(valorCampo.compareTo(df) >= 0 && dt.compareTo(valorCampo) >= 0)) {
                throw new AWValidationException("sw.error.validate.range", new Object[]{sdf.format(df.getTime()), sdf.format(dt.getTime())}, Arrays.asList(new Object[]{inputComponent}));
            }
        }
    }

    private void validateRangeNumber(Object propertyValidator) {
        Number df = (Number) ((PropertyValidator) propertyValidator).getMinValue();
        Number dt = (Number) ((PropertyValidator) propertyValidator).getMaxValue();
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        logger.info("validation " + inputComponent.getFieldName() + ": Range");
        Number valorCampo = (Number) inputComponent.getValue();
        if (valorCampo != null) {
            if (!(df.doubleValue() <= valorCampo.doubleValue() && dt.doubleValue() >= valorCampo.doubleValue())) {
                throw new AWValidationException("sw.error.validate.range", new Object[]{df.toString(), dt.toString()}, Arrays.asList(new Object[]{inputComponent}));
            }
        }
    }

    private void validateGreaterThanDate(Object propertyValidator) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        AWDate df = new AWDate((Date) ((PropertyValidator) propertyValidator).getMinValue());
        logger.info("validation " + inputComponent.getFieldName() + ": is greaterThan");
        AWDate valorCampo = new AWDate((Date) inputComponent.getValue());
        if (valorCampo != null) {
            if (!(valorCampo.compareTo(df) > 0)) {
                throw new AWValidationException("sw.error.validate.greaterThan", new Object[]{sdf.format(df.getTime())}, Arrays.asList(new Object[]{inputComponent}));
            }
        }
    }

    private void validateGreaterThanEqualDate(Object propertyValidator) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        AWDate df = new AWDate((Date) ((PropertyValidator) propertyValidator).getMinValue());
        logger.info("validation " + inputComponent.getFieldName() + ": is greaterThan");
        AWDate valorCampo = new AWDate((Date) inputComponent.getValue());
        if (valorCampo != null) {
            if (!(valorCampo.compareTo(df) >= 0)) {
                throw new AWValidationException("sw.error.validate.greaterThan", new Object[]{sdf.format(df.getTime())}, Arrays.asList(new Object[]{inputComponent}));
            }
        }
    }

    private void validateGreaterThanNumber(Object propertyValidator) {
        Number minValue = (Number) ((PropertyValidator) propertyValidator).getMinValue();
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        logger.info("validation " + inputComponent.getFieldName() + ": is greaterThan");
        Number valorCampo = (Number) inputComponent.getValue();
        if (valorCampo != null) {
            if (!(minValue.doubleValue()< valorCampo.doubleValue())) {
                throw new AWValidationException("sw.error.validate.greaterThan", new Object[]{minValue.toString()}, Arrays.asList(new Object[]{inputComponent}));
            }
        }
    }

    private void validateGreaterThanEqualsNumber(Object propertyValidator) {
        Number minValue = (Number) ((PropertyValidator) propertyValidator).getMinValue();
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        logger.info("validation " + inputComponent.getFieldName() + ": is greaterThan");
        Number valorCampo = (Number) inputComponent.getValue();
        if (valorCampo != null) {
            if (!(minValue.doubleValue()<= valorCampo.doubleValue())) {
                throw new AWValidationException("sw.error.validate.greaterThan", new Object[]{minValue.toString()}, Arrays.asList(new Object[]{inputComponent}));
            }
        }
    }


    public void validateGreaterThan(Object propertyValidator) {
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        Object valorCampo = inputComponent.getValue();
        if (valorCampo instanceof Date) {
            validateGreaterThanDate(propertyValidator);
        } else if (valorCampo instanceof Number) {
            validateGreaterThanNumber(propertyValidator);
        }
    }
    
    public void validateGreaterThanOrEqual(Object propertyValidator) {
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        Object valorCampo = inputComponent.getValue();
        if (valorCampo instanceof Date) {
            validateGreaterThanEqualDate(propertyValidator);
        } else if (valorCampo instanceof Number) {
            validateGreaterThanEqualsNumber(propertyValidator);
        }

    }

    public void validateLessThan(Object propertyValidator) {
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        Object valorCampo = inputComponent.getValue();
        if (valorCampo instanceof Date) {
            validateLessThanDate(propertyValidator);
        } else if (valorCampo instanceof Number) {
            validateLessThanNumber(propertyValidator);
        }
    }

    public void validateLessThanDate(Object propertyValidator) {
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        AWDate dt = new AWDate((Date) ((PropertyValidator) propertyValidator).getMaxValue());
        logger.info("validation " + inputComponent.getFieldName() + ": is lessThan");
        AWDate valorCampo = new AWDate((Date) inputComponent.getValue());
        if (valorCampo != null) {
            if (!(dt.compareTo(valorCampo) >= 0)) {
                throw new AWValidationException("sw.error.validate.lessThan", new Object[]{sdf.format(dt.getTime())}, Arrays.asList(new Object[]{inputComponent}));
            }
        }
    }

    public void validateLessThanNumber(Object propertyValidator) {
        BindingComponent inputComponent = ((PropertyValidator) propertyValidator).getBindingComponent();
        Number max = (Number) ((PropertyValidator) propertyValidator).getMaxValue();
        PropertyResults propertyResults = null;
        logger.info("validation " + inputComponent.getFieldName() + ": is lessThan");
        Number valorCampo = (Number) inputComponent.getValue();
        if (valorCampo != null) {
            if (!(max.doubleValue() >= valorCampo.doubleValue())) {
                throw new AWValidationException("sw.error.validate.lessThan", new Object[]{max.toString()}, Arrays.asList(new Object[]{inputComponent}));
            }
        }
    }
}