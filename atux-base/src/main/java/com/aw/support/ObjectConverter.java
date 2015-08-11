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
package com.aw.support;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Provide convertion utilities to transform any object class to another object class/primitive
 * User: Julio C. Macavilca
 * 29/06/2004
 * Improve check if ObjectConverter can be replaced by spring clasess, until now no class found
 */
public class ObjectConverter {
    static SimpleDateFormat DATE_STD_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Convert an object to a certaint class
     *
     * @param source
     * @param returnType Type of the object that need to be returned,
     *                   internally conversion is made.
     * @return object in the type specified by returnType
     * @throws ObjectConverterException
     */
    public static<E> E convert(Object source, Class<E> returnType) throws ObjectConverterException {
        if (source == null) return null;

        Object destination = null;

        if (returnType==null)
                throw new IllegalArgumentException("Null returnType for "+source);
        // convertion same type. no convertion needed
        if (returnType.isInstance(source))
            destination = source;

        if (returnType == Date.class)
            return (E) convertToDate(source);

        if (returnType == java.sql.Timestamp.class)
            return (E) convertToSqlTimestamp(source);

        if (returnType == BigDecimal.class)
            return (E) convertToBigDecimal(source);


        if (returnType == Double.class)
            return (E) new Double(convertToBigDecimal(source).doubleValue());

        if (returnType == Long.class|| returnType == Long.TYPE)
            return (E) convertToLong(source);

        if (returnType == Integer.class || returnType == Integer.TYPE)
            return source == null ? null : (E) new Integer(convertToint(source));

        if (returnType == String.class)
            return (E) source.toString();

        if(returnType == boolean.class){
            return (E) source;
        }

        if (destination == null)
            // exception if cannot support transformation
            throw new ObjectConverterException(returnType, source.getClass(), null);

        return (E) destination;
    }


    private static Date convertToDate(Object source) {
        if (source instanceof Date)
            return (Date) source;
        else if (source instanceof Calendar)
            return ((Calendar) source).getTime();
        else if (source instanceof String)
            try {
                return DATE_STD_FORMAT.parse((String) source);
            } catch (ParseException e) {
                new ObjectConverterException(source, Integer.TYPE, e.toString());
            }

        // exception if cannot support trasnformation
        throw new ObjectConverterException(source, Integer.TYPE, null);
    }

    private static java.sql.Timestamp convertToSqlTimestamp(Object source) {
//        if (source instanceof java.sql.Timestamp)
//            return (java.sql.Timestamp) source;
        Date date = convertToDate(source);
        return new java.sql.Timestamp(date.getTime());
    }

    public static BigDecimal convertToBigDecimal(Object source) {
        if (source == null)
            return null;
        if (source instanceof BigDecimal)
            return (BigDecimal) source;
        else if (source instanceof Number)
            return new BigDecimal(((Number) source).doubleValue());
        else if (source instanceof String && ((String)source).length()==0)
            return null;
        else if (source instanceof String)
            return new BigDecimal((String) source);

        // exception if cannot support trasnformation
        throw new ObjectConverterException(source, BigDecimal.class, null);
    }

    public static Long convertToLong(Object source) {
        if (source == null )
            return null;
        else if (source instanceof BigDecimal)
            return new Long(((BigDecimal) source).longValue());
        else if (source instanceof Number)
            return new Long(((Number) source).longValue());
        else if (source instanceof String)
            return new Long((String) source);

        // exception if cannot support trasnformation
        throw new ObjectConverterException(source, Long.TYPE, null);
    }
    public static Integer convertToInteger(Object source) {
        if (source instanceof BigDecimal)
            return new Integer(((BigDecimal) source).intValue());
        else if (source instanceof Number)
            return new Integer(((Number) source).intValue());
        else if (source instanceof String)
            return new Integer((String) source);

        // exception if cannot support trasnformation
        throw new ObjectConverterException(source, Integer.TYPE, null);
    }


    /**
     * Converts to primitive int
     */
    public static int convertToint(Object source) throws ObjectConverterException {
        if (source instanceof Number)
            return ((Number) source).intValue();
        else if (source instanceof String)
            return Integer.parseInt((String) source);

        // exception if cannot support trasnformation
        throw new ObjectConverterException(source, Integer.TYPE, null);
    }

    /**
     * Converts to primitive long
     */
    public static long convertTolong(Object source) throws ObjectConverterException {
        if (source instanceof Number)
            return ((Number) source).longValue();
        else if (source instanceof String)
            return Long.parseLong((String) source);

        // exception if cannot support trasnformation
        throw new ObjectConverterException(source, Integer.TYPE, null);
    }

    /**
     * Converts to primitive float
     */
    public static float convertTofloat(Object source) throws ObjectConverterException {
        if (source instanceof Number)
            return ((Number) source).floatValue();
        else if (source instanceof String)
            return Float.parseFloat((String) source);

        // exception if cannot support trasnformation
        throw new ObjectConverterException(source, Integer.TYPE, null);
    }

    public static boolean convertToboolean(Object source) {
        if (source instanceof Boolean)
            return ((Boolean) source).booleanValue();
        else if (source instanceof String) {
            String sourceStr = (String) source;
            return "S".equalsIgnoreCase(sourceStr) ||
                    "SI".equalsIgnoreCase(sourceStr) ||
                    "TRUE".equalsIgnoreCase(sourceStr) ||
                    "VERDADERO".equalsIgnoreCase(sourceStr);
        }

        // exception if cannot support trasnformation
        throw new ObjectConverterException(source, Integer.TYPE, null);
    }

    /**
     * @param number
     * @return numero
     */
    public static Number transformType(Number number, Class returnClassType) {
        if (returnClassType == null)
            throw new IllegalStateException("Return class type not set. Cannot tranform number");
        if (number.getClass() == returnClassType) return number;

        if (returnClassType == Float.class) return new Float(number.floatValue());
        if (returnClassType == Double.class) return new Double(number.doubleValue());
        if (returnClassType == Long.class) return new Long(number.longValue());
        if (returnClassType == Integer.class) return new Integer(number.intValue());
        if (returnClassType == Byte.class) return new Byte(number.byteValue());
        //possible loss of presicion here
        if (returnClassType == BigDecimal.class) return new BigDecimal(number.doubleValue());
        throw new IllegalArgumentException("Class not support implement code here :" + number.getClass() + " to " + returnClassType);
    }

    public static Object convert(Object valueToConvert, Object valueRef) {
        valueToConvert = convert(valueToConvert, valueRef.getClass());
        if (valueRef.getClass() == BigDecimal.class){
            valueToConvert = ((BigDecimal)valueToConvert).setScale(((BigDecimal)valueRef).scale(), BigDecimal.ROUND_HALF_UP);
        }
        return valueToConvert;
    }

    public static boolean equals(Object val1, Object val2){
        if (val1 ==null && val2==null) return true;
        if (val1!=null){
            Object val2Con = convert(val2, val1.getClass());
            return val1.equals(val2Con);
        }else{
            Object val1Con = convert(val1, val2.getClass());
            return val2.equals(val1Con );
        }
    }

}


