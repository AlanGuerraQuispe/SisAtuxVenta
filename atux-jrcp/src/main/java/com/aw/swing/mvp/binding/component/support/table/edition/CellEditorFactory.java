package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.swing.mvp.binding.component.BndSJTable;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.JTextFieldDocumentDecorator;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

/**
 * User: gmc
 * Date: 01/05/2009
 */
public class CellEditorFactory {

    public static TableCellEditor getJCheckBoxEditor(ColumnInfo columnInfo, JTable jTable, BndSJTable.CellEditableFocusListener cellEditableFocusListener) {
        JCheckBox chBox = new JCheckBox();
        chBox.setHorizontalAlignment(JCheckBox.CENTER);
        chBox.setBackground(jTable.getBackground());
        JCheckBoxCellEditor jCheckBoxCellEditor = new JCheckBoxCellEditor(chBox);
        jCheckBoxCellEditor.setValueTrue(columnInfo.getValueTrue());
        return jCheckBoxCellEditor;
    }

    public static TableCellEditor getJTextFielCellEditor(ColumnInfo columInfo, JTable jTable,Font font) {
        final JTextField jTextField = new JTextField();
        if (columInfo.getPropertyValidator() != null) {
            JTextFieldDocumentDecorator.decorate(jTextField, columInfo.getPropertyValidator(), columInfo.getPropertyBinding());
        }
        jTextField.setFont(font);
        jTextField.setBorder(null);
        if (columInfo.getPropertyValidator().isDateFormat()){
            return new JTextFieldDateCellEditor(jTable,jTextField);
        }
        return new JTextFieldCellEditor(jTextField);

    }

}
