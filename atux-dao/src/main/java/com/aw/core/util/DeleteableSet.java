package com.aw.core.util;

import java.util.Collection;
import java.util.Set;

/**
 * DeleteableList que implementa la interface Set
 *
 * User: Julio C. Macavilca
 * Date: 28/01/2008
 */
public class DeleteableSet<E> extends DeleteableList<E> implements Set<E>{
    public DeleteableSet() {
        int i = 1;
    }

    public boolean addAll(Collection<? extends E> newCollection){
        newCollection.removeAll(this);
        boolean changed = super.addAll(newCollection);
        return changed;
    }
    public boolean add(E element){
        if (super.contains(element))   return  false;
        else return super.add(element);
    }

}