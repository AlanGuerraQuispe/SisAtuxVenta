package com.aw.support.beans;

import java.math.BigDecimal;

/**
 * User: User
 * Date: Dec 7, 2007
 */
public class RoundUtils {

    public static BigDecimal round(BigDecimal value) {
        return value.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal validarNull(BigDecimal value){
        return value==null? BigDecimal.ZERO:value; 
    }
    public static Integer validarNull(Integer value){
        return value==null? new Integer(0):value;  
    }
}
