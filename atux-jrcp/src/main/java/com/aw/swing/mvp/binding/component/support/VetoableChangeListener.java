package com.aw.swing.mvp.binding.component.support;

/**
 * User: gmc
 * Date: 24/07/2009
 */
public abstract class VetoableChangeListener<E> {

    public abstract void vetoableChange(E rowObj, Object oldValue, Object newValue);

}
