package com.aw.support.collection;

import java.math.BigDecimal;

/**
 * User: Julio C. Macavilca
 * Date: 06/02/2008
 */
public class SumUnaryFunction extends PropertyUnaryFunction {

    public SumUnaryFunction(String propertyName) {
        super(propertyName);
        this.propertyName = propertyName;
        result = new BigDecimal(0);
    }

    protected Object evaluate(Object bean, Object propertyValue) {
        BigDecimal numBD = null;
        if (propertyValue!=null){
            numBD = getValue(bean,propertyValue);
            if (result == null) result = numBD;
            else result = getTotal().add(numBD);
        }
        return numBD;
    }

    protected BigDecimal getValue(Object bean, Object propertyValue) {
        BigDecimal numBD;
        numBD = new BigDecimal(propertyValue.toString());
        return numBD;
    }

    public BigDecimal getTotal() {
        return (BigDecimal) result;
    }
}
