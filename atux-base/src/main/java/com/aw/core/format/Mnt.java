package com.aw.core.format;

import java.math.BigDecimal;

/**
 * User: Julio C. Macavilca
 * Date: 20-mar-2008
 */
public class Mnt {
    public static BigDecimal fix(BigDecimal monto) {
        return monto==null?null:monto.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    public static BigDecimal fix(double total) {
        return fix(new BigDecimal(total) );
    }

    public static BigDecimal nvlZero(BigDecimal monto) {
        return monto!=null?monto: BigDecimal.ZERO;
    }

}
