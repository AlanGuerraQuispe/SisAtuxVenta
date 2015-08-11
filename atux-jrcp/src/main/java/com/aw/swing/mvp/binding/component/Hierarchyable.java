package com.aw.swing.mvp.binding.component;

/**
 * User: gmc
 * Date: 02/05/2009
 */
public interface Hierarchyable<E> {

    public E dependsOn(E masterCmp) ;

    public void addDependentCmp(E dependentCmp) ;

}
