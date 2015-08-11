package com.aw.swing.mvp.binding.component.support.table.edition;

import javax.swing.*;
import java.awt.*;

public class JTextFieldCellEditor extends DefaultCellEditor {
    protected JTextField jTextField;
    public JTextFieldCellEditor(final JTextField jTextField) {
        super(jTextField);
        this.jTextField = jTextField;
        jTextField.putClientProperty(CellEditorUtils.AW_CELL_EDITOR,true);
        JPanel jpanel = createPanel();
        jpanel.setLayout(new BorderLayout());
        jpanel.add(jTextField, BorderLayout.CENTER);
        editorComponent = jpanel;
    }

    private JPanel createPanel() {
        return new JPanel() {
            public void addNotify() {
                super.addNotify();
                jTextField.requestFocus();
                jTextField.selectAll();
            }
        };
    }


    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

}