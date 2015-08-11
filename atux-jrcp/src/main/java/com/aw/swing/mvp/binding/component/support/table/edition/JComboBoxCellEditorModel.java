package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.core.cache.dropdown.DropDownRow;
import com.aw.core.cache.dropdown.MappableList;
import com.aw.core.cache.loader.MetaLoader;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.List;

/**
 * User: gmc
 * Date: 02/05/2009
 */
public class JComboBoxCellEditorModel implements ComboBoxModel {
    protected MetaLoader metaLoader;
    protected List<DropDownRow> values;
    protected DropDownRow selectedItemValue;


    public MetaLoader getMetaLoader() {
        return metaLoader;
    }

    public void setMetaLoader(MetaLoader metaLoader) {
        this.metaLoader = metaLoader;
    }

    /**
     * @param anItem is a dropDown
     */
    public void setSelectedItem(Object anItem) {
        this.selectedItemValue = (DropDownRow) anItem;
    }

    /**
     * @return a DropDown
     */
    public Object getSelectedItem() {
        return selectedItemValue;
    }

    public int getSize() {
        return getValues().size();
    }

    /**
     * Return a dropdown
     *
     * @param index
     * @return
     */
    public Object getElementAt(int index) {
        return getValues().get(index);
    }


    protected EventListenerList listenerList = new EventListenerList();


    /**
     * Adds a listener to the list that's notified each time a change
     * to the data model occurs.
     *
     * @param l the <code>ListDataListener</code> to be added
     */
    public void addListDataListener(ListDataListener l) {
        listenerList.add(ListDataListener.class, l);
    }


    /**
     * Removes a listener from the list that's notified each time a
     * change to the data model occurs.
     *
     * @param l the <code>ListDataListener</code> to be removed
     */
    public void removeListDataListener(ListDataListener l) {
        listenerList.remove(ListDataListener.class, l);
    }

    /**
     * <code>AbstractListModel</code> subclasses must call this method
     * <b>after</b>
     * one or more elements of the list change.  The changed elements
     * are specified by the closed interval index0, index1 -- the endpoints
     * are included.  Note that
     * index0 need not be less than or equal to index1.
     *
     * @param source the <code>ListModel</code> that changed, typically "this"
     * @param index0 one end of the new interval
     * @param index1 the other end of the new interval
     * @see EventListenerList
     * @see DefaultListModel
     */
    protected void fireContentsChanged(Object source, int index0, int index1) {
        Object[] listeners = listenerList.getListenerList();
        ListDataEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ListDataListener.class) {
                if (e == null) {
                    e = new ListDataEvent(source, ListDataEvent.CONTENTS_CHANGED, index0, index1);
                }
                ((ListDataListener) listeners[i + 1]).contentsChanged(e);
            }
        }
    }

    private List<DropDownRow> getValues() {
        if (!metaLoader.isCacheable() || (values == null)) {
            refreshValues();
        }
        return values;
    }

    public void refreshValues() {
        values = metaLoader.getRows();
        if (values!=null && values.size()>1){
            fireContentsChanged(this,0,values.size()-1);
        }
    }

    public DropDownRow getDDRFromValue(Object value) {
        MappableList values = (MappableList) getValues();
        return values.size() > 1 ? values.mapGet(value) : null;
    }
}
