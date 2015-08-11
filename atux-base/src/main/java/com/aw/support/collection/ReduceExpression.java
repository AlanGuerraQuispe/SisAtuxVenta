package com.aw.support.collection;

/**
 * User: Juan Carlos Vergara
 * Date: 25/10/2008
 * Time: 03:23:46 PM
 */
public interface ReduceExpression {
    public Object value(Object element, Object accumulator);
}
