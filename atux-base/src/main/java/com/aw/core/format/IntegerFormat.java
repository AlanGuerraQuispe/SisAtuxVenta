package com.aw.core.format;

import java.text.DecimalFormat;

/**
 * User: Julio C. Macavilca
 * Date: 27/02/2008
 */
public class IntegerFormat extends DecimalFormat {
    static IntegerFormat instance = new IntegerFormat();

    public IntegerFormat() {
        super("#,###,###,###,##0", MoneyFormat.buildSymbols());
    }

    public static String fmt(Number num){
        if (num ==null) return null;
        else return instance.format(num);
    }
    public static String fmtZero(Number num){
        if (num ==null) return instance.format(0);
        else return instance.format(num);
    }


    public static void main(String[] args) {
        //System.out.println(IntegerFormat.fmt(new BigDecimal(5464654.34543)));
    }
}