package com.aw.swing.mvp.binding.component.support.table.edition;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JCheckBoxCellEditor extends DefaultCellEditor {
    private Object valueTrue = Boolean.TRUE;

    public JCheckBoxCellEditor(final JCheckBox checkBox) {
        super(checkBox);
        editorComponent = checkBox;
        checkBox.removeActionListener(delegate);
        checkBox.putClientProperty(CellEditorUtils.AW_CELL_EDITOR,true);
        delegate = new EditorDelegate() {
            public void setValue(Object value) {
                boolean selected = false;
                if (value instanceof Boolean) {
                    selected = ((Boolean) value).booleanValue();
                } else if (value instanceof Object) {
                    selected = value.equals(valueTrue);
                }
                checkBox.setSelected(selected);
            }

            public Object getCellEditorValue() {
                return Boolean.valueOf(checkBox.isSelected());
            }
            public void actionPerformed(ActionEvent e) {
                JCheckBoxCellEditor.this.stopCellEditing();
	        }
        };
        checkBox.addActionListener(delegate);
        checkBox.setRequestFocusEnabled(false);
    }

    public void setValueTrue(Object valueTrue) {
        this.valueTrue = valueTrue;
    }
}
