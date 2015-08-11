package com.table;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChooserEditor extends AbstractCellEditor
        implements TableCellEditor {
    private JButton delegate = new JButton();
    Color savedColor;

    public ColorChooserEditor() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color color = JColorChooser.showDialog(
                        delegate, "Color Chooser", savedColor);
                ColorChooserEditor.this.changeColor(color);
            }
        };
        delegate.addActionListener(actionListener);
    }

    public Object getCellEditorValue() {
        return savedColor;
    }

    private void changeColor(Color color) {
        if (color != null) {
            savedColor = color;
            delegate.setForeground(color);
        }
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        changeColor((Color) value);
        return delegate;
    }
}