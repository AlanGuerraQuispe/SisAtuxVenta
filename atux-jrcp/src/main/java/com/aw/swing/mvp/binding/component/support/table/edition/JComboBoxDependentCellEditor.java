package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.core.cache.dropdown.DropDownRow;

import javax.swing.*;
import java.awt.*;

public class JComboBoxDependentCellEditor extends DefaultCellEditor {

    public JComboBoxDependentCellEditor(JComboBox comboBox) {
        super(comboBox);
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     *
     * @see javax.swing.DefaultCellEditor.EditorDelegate#getCellEditorValue
     */
    public Object getCellEditorValue() {
        DropDownRow ddr= (DropDownRow) delegate.getCellEditorValue();
        if (ddr ==null){
            return null;
        }
        return ddr.getValue();
    }

    /**
     * Implements the <code>TableCellEditor</code> interface.
     */
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected,
                                                 int row, int column) {
        JComboBox comboBox = (JComboBox) editorComponent;
        JComboBoxDependentCellEditorModel model = (JComboBoxDependentCellEditorModel) comboBox.getModel();
        model.setCurrentRow(row);
        comboBox.setSelectedItem(model.getDDRFromValue(value));
        return editorComponent;
    }



}