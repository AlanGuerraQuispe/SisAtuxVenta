package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.core.cache.dropdown.DropDownRow;
import com.aw.core.cache.dropdown.MappableList;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * User: gmc
 * Date: 02/05/2009
 */
public class JComboBoxDependentCellEditorModel implements ComboBoxModel {
    protected DropDownRow selectedItemValue;
    protected CellDropDownProvider cellDropDownProvider;
    int currentRow = -1;

    public JComboBoxDependentCellEditorModel(CellDropDownProvider cellDropDownProvider) {
        this.cellDropDownProvider = cellDropDownProvider;
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
     * @see javax.swing.event.EventListenerList
     * @see javax.swing.DefaultListModel
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

    private MappableList getValues() {
        MappableList values = cellDropDownProvider.getDropDowns(currentRow);
        if (values!=null && values.size()>1){
            fireContentsChanged(this,0,values.size()-1);
        }
        return values;
    }

    public DropDownRow getDDRFromValue(Object value) {
        MappableList values = getValues();
        return values.size() > 1 ? values.mapGet(value) : null;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }
}