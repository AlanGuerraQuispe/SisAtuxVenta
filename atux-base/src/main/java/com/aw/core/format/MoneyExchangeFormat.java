package com.aw.core.format;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * User: Julio C. Macavilca
 * Date: 27/02/2008
 */
public class MoneyExchangeFormat extends DecimalFormat {
    static MoneyExchangeFormat instance = new MoneyExchangeFormat();

    public MoneyExchangeFormat() {
        super("#,###,###,###,##0.0000", buildSymbols());
    }

    public static DecimalFormatSymbols buildSymbols() {
        DecimalFormatSymbols symbols =new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        return symbols;
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
        //System.out.println(MoneyFormat.fmt(new BigDecimal(5464654.34543)));
    }
}
