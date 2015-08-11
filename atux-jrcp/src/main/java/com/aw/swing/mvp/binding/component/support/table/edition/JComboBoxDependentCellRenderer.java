package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.ui.painter.PainterMessages;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class JComboBoxDependentCellRenderer extends JComboBox implements TableCellRenderer {
    protected final Log logger = LogFactory.getLog(getClass());
    ColumnInfo columnInfo;

    public JComboBoxDependentCellRenderer(ColumnInfo columnInfo,ComboBoxModel aModel) {
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
        JComboBoxDependentCellRendererModel jmodel = (JComboBoxDependentCellRendererModel) getModel();
        jmodel.setCurrentRow(row);
        setSelectedItem(value);
        if (columnInfo.isRequired() && isEmpty(value)){
            PainterMessages.paintException(this);
        }else{
            PainterMessages.cleanException(this);
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
                boolean found = false;
                for (int i = 0; i < dataModel.getSize(); i++) {
                    Object element = dataModel.getElementAt(i);
                    if ((anObject instanceof Number) && element instanceof Number){
                        if (anObject.getClass() != element.getClass()){
                            logger.error("no soportado ----");
//                            anObject = NumberUtils.convertNumberToTargetClass((Number) anObject, element.getClass());
                        }
                    }
                    if (anObject.equals(element)) {
                        found = true;
                        objectToSelect = element;
                        break;
                    }
                }
                if (!found) {
                    return;
                }
            }

            // Must toggle the state of this flag since this method
            // call may result in ListDataEvents being fired.
//            selectingItem = true;
            dataModel.setSelectedItem(objectToSelect);
//            selectingItem = false;

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



}