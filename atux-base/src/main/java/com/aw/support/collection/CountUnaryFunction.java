package com.aw.support.collection;

import org.apache.commons.functor.UnaryFunction;

/**
 * User: Julio C. Macavilca
 * Date: 06/02/2008
 */
public class CountUnaryFunction implements UnaryFunction {

    int count = 0;
    public Object evaluate(Object o) {
        count++;
        return null;
    }

    public int getCount() {
        return count;
    }
}