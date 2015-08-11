package com.aw.core.config;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/**
 * User: gmc
 * Date: 24-sep-2008
 */
public class ConfigInfoProvider {
    /**
     * Date format used to display and validate dates
     */
    private String dateFormat = "dd/MM/yyyy";

    private String dateFormatLabel = "DD/MM/AAAA";


    /**
     * Date format used to display and validate dates
     */
    private String timeFormat = "kk:mm";
    private String timeFormatWitchSeconds = "kk:mm:ss";
    /**
     * Date format used to display and validate dates
     */
    private String dateTimeFormat = "dd/MM/yyyy kk:mm";
    
    /**
     * Date format used to display and validate dates
     */
    private String dateFormatWithSeconds = "dd/MM/yyyy kk:mm:ss";
    /**
     * Date format used to display and validate dates
     */
    private String dateFormatDayMonthYear = "dd 'de' MMMM 'de' yyyy";
    /**
     * Date format used to display and validate dates
     */
    private String moneyFormat = "#,###,###,###,##0.00";

    private String moneyFormatHundredth = "#,###,###,###,##0.000";
    /**
     * Date format used to display and validate dates
     */
    private String moneyExchangeFormat = "#,###,###,###,##0.0000";
    /**
     * Date format used to display and validate dates
     */
    private String pesoFormat = "#,###,###,###,##0.00";

    private String percentFormat = "##0.00";

    private String floatFormat = "#,###,###,###,##0.00";

    private String integerFormat = "#,###,###,###,##0";

    private String codigoFormat = "#############";

    private String numberFormat = "############0";

    /**
     * Optional Force to UpperCase in all fields
     */
    private boolean defaultTextToUpperCase = false;

    private Object checkTrue = "1";
    private Object checkFalse = "0";

    private List<String> auditingFields = Arrays.asList(new String[]{"usuaCrea", "fechCrea", "usuaModi", "fechModi"});


    public int getDateFormatLength() {
        return dateFormat.length();
    }

    public int getTimeFormatLength() {
        return timeFormat.length();
    }
    public int getDateTimeFormatLength() {
        return dateTimeFormat.length();
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

    public String getMoneyFormat() {
        return moneyFormat;
    }

    public void setMoneyFormat(String moneyFormat) {
        this.moneyFormat = moneyFormat;
    }

    public String getMoneyFormatHundredth() {
        return moneyFormatHundredth;
    }

    public void setMoneyFormatHundredth(String moneyFormatHundredth) {
        this.moneyFormatHundredth = moneyFormatHundredth;
    }

    public String getPesoFormat() {
        return pesoFormat;
    }

    public void setPesoFormat(String pesoFormat) {
        this.pesoFormat = pesoFormat;
    }

    public boolean isDefaultTextToUpperCase() {
        return defaultTextToUpperCase;
    }

    public void setDefaultTextToUpperCase(boolean defaultTextToUpperCase) {
        this.defaultTextToUpperCase = defaultTextToUpperCase;
    }

    public String getPercentFormat() {
        return percentFormat;
    }

    public void setPercentFormat(String percentFormat) {
        this.percentFormat = percentFormat;
    }

    public String getFloatFormat() {
        return floatFormat;
    }

    public void setFloatFormat(String floatFormat) {
        this.floatFormat = floatFormat;
    }

    public String getIntegerFormat() {
        return integerFormat;
    }

    public void setIntegerFormat(String integerFormat) {
        this.integerFormat = integerFormat;
    }

    public String getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(String numberFormat) {
        this.numberFormat = numberFormat;
    }

    public String getCodigoFormat() {
        return codigoFormat;
    }

    public void setCodigoFormat(String codigoFormat) {
        this.codigoFormat = codigoFormat;
    }

    public String getMoneyExchangeFormat() {
        return moneyExchangeFormat;
    }

    public String getTimeFormatWitchSeconds() {
        return timeFormatWitchSeconds;
    }

    public void setTimeFormatWitchSeconds(String timeFormatWitchSeconds) {
        this.timeFormatWitchSeconds = timeFormatWitchSeconds;
    }

    public String getDateFormatWithSeconds() {
        return dateFormatWithSeconds;
    }

    public void setDateFormatWithSeconds(String dateFormatWithSeconds) {
        this.dateFormatWithSeconds = dateFormatWithSeconds;
    }

    public String getDateFormatDayMonthYear() {
        return dateFormatDayMonthYear;
    }

    public void setDateFormatDayMonthYear(String dateFormatDayMonthYear) {
        this.dateFormatDayMonthYear = dateFormatDayMonthYear;
    }

    public Object getCheckTrue() {
        return checkTrue;
    }

    public void setCheckTrue(Object checkTrue) {
        this.checkTrue = checkTrue;
    }

    public Object getCheckFalse() {
        return checkFalse;
    }

    public void setCheckFalse(Object checkFalse) {
        this.checkFalse = checkFalse;
    }

    public String getDateFormatLabel() {
        return dateFormatLabel;
    }

    public void setDateFormatLabel(String dateFormatLabel) {
        this.dateFormatLabel = dateFormatLabel;
    }

    public List<String> getAuditingFields() {
        return auditingFields;
    }

    public void setAuditingFields(List<String> auditingFields) {
        this.auditingFields = auditingFields;
    }

    public static void main(String[] args) {
        DecimalFormat df=new DecimalFormat("#,###,###,###,##0.00");
        System.out.println(df.format(new BigDecimal("-153")));
    }
}
