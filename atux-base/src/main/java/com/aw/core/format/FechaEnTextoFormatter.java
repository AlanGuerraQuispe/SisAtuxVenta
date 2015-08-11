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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.*;


/**
 * @author jcvergara
 *         15/11/2004
 */
public class FechaEnTextoFormatter implements Formatter {

    protected final Log logger = LogFactory.getLog(getClass());

    public  static FechaEnTextoFormatter instance = new FechaEnTextoFormatter();


    public FechaEnTextoFormatter() {
    }

    public Formatter clone() {
        return new FechaEnTextoFormatter();
    }
    public Object format(Object bean, String attributeName, Object attributeValue) {
        if (attributeValue == null) return null;
        return format(attributeValue);
    }

    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        return null;
    }

    public Object format(Object attributeValue) {
        Date date = (Date) attributeValue;
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        Map meses = buildMesesMap();
        String valor = dia+ " de "+ meses.get(mes)+" del "+anio;
        return valor;
    }

    private Map buildMesesMap() {
        HashMap meses = new HashMap();
        meses.put(0, "ENERO");
        meses.put(1, "FEBRERO");
        meses.put(2, "MARZO");
        meses.put(3, "ABRIL");
        meses.put(4, "MAYO");
        meses.put(5, "JUNIO");
        meses.put(6, "JULIO");
        meses.put(7, "AGOSTO");
        meses.put(8, "SEPTIEMBRE");
        meses.put(9, "OCTUBRE");
        meses.put(10, "NOVIEMBRE");
        meses.put(11, "DICIEMBRE");

        return meses;
    }


    public boolean onSortingPreferOriginalValue() {
        return true;
    }

    public Object parseObject(String source)  {
        throw new UnsupportedOperationException();
    }

    public Object parseObject(String source, ParsePosition pos) {
        throw new UnsupportedOperationException();
    }


    public static void main(String a[]) throws ParseException {
        //System.out.println("VAlue "+ new NumberFormatter( "#,###,###,###,##0.00").parseObject("12,543.32"));
//        System.out.println(new FechaEnTextoFormatter().format(new Date()));
    }

}