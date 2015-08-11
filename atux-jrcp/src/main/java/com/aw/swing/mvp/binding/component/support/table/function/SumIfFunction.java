package com.aw.swing.mvp.binding.component.support.table.function;

import com.aw.core.format.NumberFormatter;
import org.apache.commons.functor.UnaryPredicate;
import org.springframework.beans.BeanWrapperImpl;

import java.util.List;


/**
 * User: gmc
 * Date: 20/07/2009
 */
public class SumIfFunction extends GridConditionalFunction {
    private String fieldName;
    private NumberFormatter formatter;

    public SumIfFunction(UnaryPredicate condition, String fieldName) {
        super(condition);
        this.fieldName = fieldName;
    }

    public Object execute() {
        List values = gdp.getValues();
        Number sum = 0;
        for (Object value : values) {
            if (condition==null || condition.test(value)) {
                Number valueToSum = (Number) new BeanWrapperImpl(value).getPropertyValue(fieldName);
                sum = sum.doubleValue() + (valueToSum==null?0: valueToSum.doubleValue());
            }
        }
        if (formatter!=null) return formatter.format(sum);
        else return sum;
    }

    public SumIfFunction setFormatter(NumberFormatter formatter) {
        this.formatter = formatter;
        return this;
    }
}