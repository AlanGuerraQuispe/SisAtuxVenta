package com.aw.core.util;

import com.aw.core.domain.ICloneable;

import java.util.*;

/**
 * Clase usada para manejar una lista de Entitys que
 * son eliminables.
 * La clase provee el siguiente metodo para obtener la lista de elementos
 * eliminados.
 *
 * User: Julio C. Macavilca
 * Date: 28/01/2008
 */
public class DeleteableList<E> extends ArrayList<E> implements ICloneable{
    Set<E> deletedItems = new HashSet<E>(2);
    Set<E> modifiedItems = new HashSet<E>(2);
    List<E> initialItems = new ArrayList<E>(2);

    public DeleteableList() {
    }

    public DeleteablePersister<E> buildPersister() {
        return new DeleteablePersister<E>(this);
    }

    public DeleteableList<E> cloneDeep() throws CloneNotSupportedException {
        // clonar todos los items (solo una vez)
        Set<E> fullListOfItems = new HashSet<E>();
        fullListOfItems.addAll(this);
        fullListOfItems.addAll(deletedItems);
        fullListOfItems.addAll(modifiedItems);
        fullListOfItems.addAll(initialItems);  // initialItems es de tipo List (se puede repetir??)
        Map<E,E> clonedItems = new HashMap<E,E> ();
        for (E item: fullListOfItems) {
            clonedItems.put(item, (E) ((ICloneable)item).clone());
        }
        DeleteableList<E> clone = (DeleteableList<E>) super.clone();
        clone.deletedItems = new HashSet<E>(2);
        clone.modifiedItems = new HashSet<E>(2);
        clone.initialItems = new ArrayList<E>(2);
        for (int i = 0; i < this.size(); i++)
            clone.set(i, clonedItems.get(this.get(i)) )   ;
        for (E deletedItem : this.deletedItems)
            clone.deletedItems.add(  clonedItems.get(deletedItem) );
        for (E deletedItem : this.modifiedItems)
            clone.modifiedItems.add(  clonedItems.get(deletedItem) );
        for (int i = 0; i < this.initialItems.size(); i++)
            clone.initialItems.add(i, clonedItems.get(this.initialItems.get(i)) )   ;
        return  clone;
    }

    public void init() {
        List<E> tmp = new ArrayList<E>();
        tmp.addAll(this);
        init(tmp);
    }
    public DeleteableList<E> init(Collection<E> list){
        super.clear();
        deletedItems.clear();
        initialItems.clear();
        modifiedItems.clear();
        if (list!=null){
            this.addAll(list);
            initialItems.addAll(list);
        }
        return this;
    }

    public DeleteableList<E> update(Collection<E> updatedList){
        deletedItems.clear();
        deletedItems.addAll(initialItems);
        deletedItems.removeAll(updatedList);
        if (this != updatedList){
            super.clear();
            this.addAll(updatedList);
        }
        return this;
    }

    /**
     * Elements to be updated or inserted
     * @return List of entitys
     */
    public List<E> toPersist(){
        return this;
    }

    /**
     * List of deleted elements
     * @param onlyInitialItems
     *    TRUE only elements loaded on {@link #init(java.util.Collection)} method
     *    FALSE all deleted elements
     * @return list List of deleted elements
     */
    public List<E> toDelete(boolean onlyInitialItems){
        List<E> result = new ArrayList<E>(deletedItems);
        if (onlyInitialItems) result.retainAll(initialItems);
        result.removeAll(this); // algunas veces hacen remove(e);add(e)
        return  result;
    }

    public List<E> toUpdate(){
        return new ArrayList<E>(modifiedItems);
    }

    public List<E> getInitialItems() {
        return initialItems;
    }
    public List<E> getNewItems() {
        List<E> result = new ArrayList<E>(this);
        result.removeAll(initialItems);
        return  result;
    }

    //////////////  ArrayList overrided methods //////////////////////////
    public E remove(int index) {
        E deleted =  super.remove(index);
        deletedItems.add(deleted);
        return deleted;
    }

    public boolean remove(Object o) {
        boolean existed = super.remove(o);
        if (existed) deletedItems.add( (E) o);
        return existed;
    }

    protected void removeRange(int fromIndex, int toIndex) {
        List<E> toDeleteList = this.subList(fromIndex, toIndex);
        super.removeRange(fromIndex, toIndex);
        deletedItems.addAll(toDeleteList);
    }

    public boolean removeAll(Collection<?> c) {
        boolean modified = super.removeAll(c);
        deletedItems.addAll((Collection<? extends E>) c);
        return modified;
    }
    public void clear() {
        deletedItems.addAll(this);
        super.clear();
    }

    public void addModifiedRow(E object) {
        modifiedItems.add(object);
    }
}
