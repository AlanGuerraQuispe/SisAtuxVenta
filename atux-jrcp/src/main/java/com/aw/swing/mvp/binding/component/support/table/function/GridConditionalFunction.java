package com.aw.swing.mvp.binding.component.support.table.function;

import org.apache.commons.functor.UnaryPredicate;

/**
 * User: gmc
 * Date: 20/07/2009
 */
public abstract class GridConditionalFunction<E> extends GridFunction<E> {
    protected UnaryPredicate condition;

    protected GridConditionalFunction(UnaryPredicate condition) {
        this.condition = condition;
    }


    public void setCondition(UnaryPredicate condition) {
        this.condition = condition;
    }
}