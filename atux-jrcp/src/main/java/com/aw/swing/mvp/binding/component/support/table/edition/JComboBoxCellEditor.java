package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.core.cache.dropdown.DropDownRow;

import javax.swing.*;
import java.awt.*;

public class JComboBoxCellEditor extends DefaultCellEditor {
    private int col;

    public JComboBoxCellEditor(JComboBox comboBox) {
        super(comboBox);
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     *
     * @see javax.swing.DefaultCellEditor.EditorDelegate#getCellEditorValue
     */
    public Object getCellEditorValue() {
        DropDownRow ddr = (DropDownRow) delegate.getCellEditorValue();
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
        JComboBoxCellEditorModel model = getModel();
        DropDownRow ddr=model.getDDRFromValue(value);
        if (ddr!=null){
            comboBox.setSelectedItem(ddr);
        }else{
            comboBox.setSelectedIndex(0);
        }
        return editorComponent;
    }

    public JComboBoxCellEditorModel getModel() {
        JComboBox comboBox = (JComboBox) editorComponent;
        JComboBoxCellEditorModel model = (JComboBoxCellEditorModel) comboBox.getModel();
        return model;
    }


    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
