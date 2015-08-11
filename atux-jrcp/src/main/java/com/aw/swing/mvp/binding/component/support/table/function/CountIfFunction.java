package com.aw.swing.mvp.binding.component.support.table.function;

import org.apache.commons.functor.UnaryPredicate;

import java.util.List;


/**
 * User: gmc
 * Date: 20/07/2009
 */
public class CountIfFunction extends GridConditionalFunction<Integer> {
    public CountIfFunction(UnaryPredicate condition) {
        super(condition);
    }

    public Integer execute(){
        List values = gdp.getValues();
        int count=0;
        for (Object value : values) {
            if (condition==null|| condition.test(value)){
                count++;
            }
        }
        return count;
    }
}