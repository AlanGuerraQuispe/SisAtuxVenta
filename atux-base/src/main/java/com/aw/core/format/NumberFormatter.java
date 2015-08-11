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

import com.aw.core.context.AWBaseContext;
import com.aw.core.domain.AWBusinessException;
import com.aw.core.exception.AWSystemException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.text.*;


/**
 * @author jcvergara
 *         15/11/2004
 */
public class NumberFormatter implements Formatter, Cloneable {

    protected final Log logger = LogFactory.getLog(getClass());

//aw-framework quitar referencia SwinfContext    
//    public static final NumberFormatter PESO_FORMATTER = new NumberFormatter(SwingContext.getSwingConfiguration().getPesoFormat());
//    public static final NumberFormatter MONEY_FORMATTER = new NumberFormatter(SwingContext.getSwingConfiguration().getMoneyFormat());
//    public static final NumberFormatter MONEYEXCHANGE_FORMATTER = new NumberFormatter(SwingContext.getSwingConfiguration().getMoneyExchangeFormat());
//    public static final NumberFormatter PERCENT_FORMATTER = new NumberFormatter(SwingContext.getSwingConfiguration().getPercentFormat());
//
//    public static final NumberFormatter FLOAT_FORMATTER_W_GRP_SYM = new NumberFormatter(SwingContext.getSwingConfiguration().getFloatFormat());
//    public static final NumberFormatter INTEGER_FORMATTER_W_GRP_SYM = new NumberFormatter(SwingContext.getSwingConfiguration().getIntegerFormat());
//    public static final NumberFormatter CODIGO_FORMATTER = new NumberFormatter(SwingContext.getSwingConfiguration().getCodigoFormat());
//    public static final NumberFormatter NUMBER_FORMATTER_WOUT_GRP_SYM = new NumberFormatter(SwingContext.getSwingConfiguration().getNumberFormat());

    public static final NumberFormatter PESO_FORMATTER = null;
    public static final NumberFormatter MONEY_FORMATTER = new NumberFormatter(AWBaseContext.instance().getConfigInfoProvider().getMoneyFormat());
    public static final NumberFormatter MONEY_FORMATTER_HUNDREDTH = new NumberFormatter(AWBaseContext.instance().getConfigInfoProvider().getMoneyFormatHundredth());
    public static final NumberFormatter MONEYEXCHANGE_FORMATTER = new NumberFormatter(AWBaseContext.instance().getConfigInfoProvider().getMoneyExchangeFormat());;
    public static final NumberFormatter PERCENT_FORMATTER = null;

    public static final NumberFormatter FLOAT_FORMATTER_W_GRP_SYM = new NumberFormatter(AWBaseContext.instance().getConfigInfoProvider().getFloatFormat());
    public static final NumberFormatter INTEGER_FORMATTER_W_GRP_SYM = new NumberFormatter(AWBaseContext.instance().getConfigInfoProvider().getIntegerFormat());
    public static final NumberFormatter CODIGO_FORMATTER = null;
    public static final NumberFormatter NUMBER_FORMATTER_WOUT_GRP_SYM = new NumberFormatter(AWBaseContext.instance().getConfigInfoProvider().getNumberFormat());

    private DecimalFormat format = null;

    public NumberFormatter(String formatPattern) {
        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        unusualSymbols.setDecimalSeparator('.');
        unusualSymbols.setGroupingSeparator(',');
        this.format = new DecimalFormat(formatPattern, unusualSymbols);
    }

    public NumberFormatter clone() {
        try {
            NumberFormatter numberFormatter = (NumberFormatter) super.clone();
            numberFormatter.format = (DecimalFormat) this.format.clone();
            return numberFormatter;
        } catch (CloneNotSupportedException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    public Object format(Object bean, String attributeName, Object attributeValue) {
        if (attributeValue == null) return null;
        return format.format(attributeValue);
    }

    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        return null;
    }

    public Object format(Object attributeValue) {
        if (attributeValue == null) return null;

        if (attributeValue instanceof String) {
            if (StringUtils.isEmpty((String) attributeValue)) {
                return null;
            }
            logger.info(" attributeValue is String <" + attributeValue + "> convert to new BigDecimal()");
            attributeValue = new BigDecimal(attributeValue.toString());
        }
        return format.format(attributeValue);
    }

    public String formatAsString(Object attributeValue) {
        return (String) format(null, null, attributeValue);
    }

    public static String formatIntegerAsString(Object attributeValue) {
        if (attributeValue == null) return "0";

        return (String) NUMBER_FORMATTER_WOUT_GRP_SYM.format(null, null, attributeValue);
    }


    /**
     * Additonal information for Sorting purpose
     * Indicate witch values is prefered to use in sorts
     * TRUE: use original values (unformatted)
     * FALSE: use formatted values
     *
     * @return always TRUE (use date object instead of String)
     */
    public boolean onSortingPreferOriginalValue() {
        return true;
    }

    public Object parseObject(String source) {
        try {
            if (StringUtils.isEmpty(source)) {
                return "";
            }
            return format.parseObject(source);
        } catch (ParseException e) {
            throw new AWSystemException("Error Parsing:<" + source + ">", e);
        }
    }

    public DecimalFormat getFormat() {
        return format;
    }

    public Object parseObject(String source, ParsePosition pos) {
        return null;
    }

    public static void main(String a[]) throws ParseException {

//        NumberFormatter simpleFormat = new NumberFormatter("##########");
//        System.out.println(simpleFormat.format(new Long(21212121)));
        //System.out.println("VAlue " + new NumberFormatter("#,###,###,###,##0.00").parseObject("12,543.32"));


    }

}


