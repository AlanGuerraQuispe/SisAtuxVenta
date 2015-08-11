package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.core.cache.dropdown.DropDownRow;
import com.aw.core.cache.dropdown.MappableList;

import javax.swing.*;
import javax.swing.event.ListDataListener;

/**
 * User: gmc
 * Date: 02/05/2009
 */
public class JComboBoxDependentCellRendererModel implements ComboBoxModel {
    protected Object selectedItemValue;
    protected Object selectedItemLabel;
    protected CellDropDownProvider cellDropDownProvider;
    int currentRow = -1;

    public JComboBoxDependentCellRendererModel(CellDropDownProvider cellDropDownProvider) {
        this.cellDropDownProvider = cellDropDownProvider;
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

        DropDownRow ddr= getDropDownRowFor(selectedItemValue);
        if (ddr !=null){
            selectedItemLabel = ddr.getLabel();
        }else{
            selectedItemLabel = "";            
        }
        return selectedItemLabel;
    }

    private DropDownRow getDropDownRowFor(Object value){
        for (Object obj: getValues()) {
             DropDownRow dropDownRow = (DropDownRow) obj;
            if (( dropDownRow.getValue() == null && value== null) || (dropDownRow.getValue()!=null && dropDownRow.getValue().equals(value))){
                return dropDownRow;
            }
        }
        return null;
    }

    public int getSize() {
        return getValues().size();
    }

    /**
     * @param index
     * @return values of the dropdown
     */
    public Object getElementAt(int index) {
        return ((DropDownRow) getValues().get(index)).getValue();
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


    private MappableList getValues() {
        return cellDropDownProvider.getDropDowns(currentRow);
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

}