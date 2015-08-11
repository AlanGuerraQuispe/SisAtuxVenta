package com.aw.core.util;

import com.aw.support.ObjectConverter;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * User: Manuel Flores
 * Date: 05/10/2007
 */
public class NumberUtils {

    public static boolean equals(Number num1, Number num2) {
        if (num1 == num2) return true;
        if (num1 != null)
            num2 = (Number) ObjectConverter.convert(num2, num1.getClass());
        return num1 != null && num2 != null && num1.equals(num2);
    }

    public static BigDecimal nvlZero(BigDecimal num) {
        return num == null ? BigDecimal.ZERO : num;
    }

    public static BigDecimal setPresicion(BigDecimal number,int presicion) {
        if (number == null) return null;

        return number.setScale(presicion, BigDecimal.ROUND_HALF_UP);
    }

    public static boolean maxValue(BigDecimal origen, BigDecimal montoMaximo) {
        if (origen == null) return false;

        return origen.compareTo(montoMaximo) > 0;
    }

    public static Long nvlZero(Long num) {
        return num==null? new Long(0):num;
    }

    public static boolean isNumeric(String str) {
        try{
            new BigInteger(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}