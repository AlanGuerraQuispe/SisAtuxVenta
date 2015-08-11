package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.table.JTableModel;
import com.aw.swing.mvp.grid.EditingRowPolicy;
import com.aw.swing.mvp.ui.painter.PainterMessages;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class JComboBoxCellRenderer extends JComboBox implements TableCellRenderer {

    ColumnInfo columnInfo;
    private EditingRowPolicy editingRowPolicy;

    public JComboBoxCellRenderer(ColumnInfo columnInfo,ComboBoxModel aModel ) {
        super(aModel);
        this.columnInfo = columnInfo;
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        setSelectedItem(value);
        if (columnInfo.isRequired()){
            PainterMessages.cleanException(this);
            boolean  editable = true;
            if (editingRowPolicy!=null){
                Object rowObj = ((JTableModel)table.getModel()).getRowAt(row);
                editable = editingRowPolicy.isEditable(rowObj,columnInfo.getFieldName());
            }
            if(editable && isEmpty(value)){
                PainterMessages.paintException(this);
            }
        }
        boolean noIsEditable =!columnInfo.isEditable()||!table.isCellEditable(row,column);
        this.setEnabled(!noIsEditable);
        return this;
    }

    public void setSelectedItem(Object anObject) {
        Object oldSelection = selectedItemReminder;
        Object objectToSelect = anObject;
        if (oldSelection == null || !oldSelection.equals(anObject)) {
            if (anObject != null && !isEditable()) {
                // For non editable combo boxes, an invalid selection
                // will be rejected.
                JComboBoxCellRendererModel model = (JComboBoxCellRendererModel) getModel();
                boolean found = model.getMetaLoader().getMap().mapContains(anObject);
                if (!found) {
                    return;
                }
            }

            // Must toggle the state of this flag since this method
            // call may result in ListDataEvents being fired.
            dataModel.setSelectedItem(objectToSelect);

            if (selectedItemReminder != dataModel.getSelectedItem()) {
                // in case a users implementation of ComboBoxModel
                // doesn't fire a ListDataEvent when the selection
                // changes.
                selectedItemChanged();
            }
        }
        fireActionEvent();
    }

    private boolean isEmpty(Object value) {
        return value == null || ((value instanceof String) && "".equals(value));
    }

    public void setEditingRowPolicy(EditingRowPolicy editingRowPolicy) {
        this.editingRowPolicy = editingRowPolicy;
    }
}
