package com.aw.swing.mvp.binding.component.support.table.function;

import org.apache.commons.functor.UnaryPredicate;
import org.springframework.beans.BeanWrapperImpl;

import java.math.BigDecimal;
import java.util.List;


/**
 * User: gmc
 * Date: 20/07/2009
 */
public class AvgIfFunction extends GridConditionalFunction {
    private String fieldName;

    public AvgIfFunction(UnaryPredicate condition, String fieldName) {
        super(condition);
        this.fieldName = fieldName;
    }

    public Number execute() {
        List values = gdp.getValues();
        Number sum = 0;
        int count=0;
        for (Object value : values) {
            if (condition==null || condition.test(value)) {
                Number valueToSum = (Number) new BeanWrapperImpl(value).getPropertyValue(fieldName);
                sum = sum.doubleValue() + valueToSum.doubleValue();
                count++;
            }
        }
        if(count==0) return 0;

        return new BigDecimal(sum.doubleValue()/count);
    }
}