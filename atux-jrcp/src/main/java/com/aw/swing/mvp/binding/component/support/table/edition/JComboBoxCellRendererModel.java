package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.core.cache.dropdown.DropDownRow;
import com.aw.core.cache.loader.MetaLoader;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

/**
 * User: gmc
 * Date: 02/05/2009
 */
public class JComboBoxCellRendererModel implements ComboBoxModel {
    protected Object selectedItemValue;
    protected Object selectedItemLabel;
    MetaLoader metaLoader;

    public JComboBoxCellRendererModel(MetaLoader metaLoader) {
        this.metaLoader = metaLoader;
    }

    /**
     * @param anItem is the value of the dropdown
     */
    public void setSelectedItem(Object anItem) {
        this.selectedItemValue = anItem;
    }

    /**
     * Return the label of the dropdown
     *
     * @return
     */
    public Object getSelectedItem() {
        selectedItemLabel = metaLoader.getLabel(selectedItemValue);
        return selectedItemLabel;
    }

    public int getSize() {
        return getValues().size();
    }

    public Object getElementAt(int index) {
        return getValues().get(index).getValue();
    }


    /**
     * Adds a listener to the list that's notified each time a change
     * to the data model occurs.
     *
     * @param l the <code>ListDataListener</code> to be added
     */
    public void addListDataListener(ListDataListener l) {
    }


    /**
     * Removes a listener from the list that's notified each time a
     * change to the data model occurs.
     *
     * @param l the <code>ListDataListener</code> to be removed
     */
    public void removeListDataListener(ListDataListener l) {
    }

    List<DropDownRow> values;


    private List<DropDownRow> getValues() {
        if (!metaLoader.isCacheable() || (values == null)) {
            values = metaLoader.getRows();
        }
        return values;
    }

    public void refreshValues(){
        values = metaLoader.getRows();        
    }
    public MetaLoader getMetaLoader() {
        return metaLoader;
    }
}