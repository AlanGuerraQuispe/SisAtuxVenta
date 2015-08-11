package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.swing.mvp.action.AWInternaContext;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class JFileChooserCellEditor extends AbstractCellEditor
        implements TableCellEditor {
    private JButton delegate = new JButton();
    JFileChooser fileChooser;
    String fileName;

    {
        fileChooser = new JFileChooser();
        fileChooser.putClientProperty(CellEditorUtils.AW_CELL_EDITOR,Boolean.TRUE);
    }


    public JFileChooserCellEditor() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AWInternaContext.instance().cellEditingStart();
                String fileName = "";
                int returnVal = fileChooser.showOpenDialog(delegate);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    fileName = file.getAbsolutePath();
                    JFileChooserCellEditor.this.changeFileName(fileName);
                }
            }
        };
        delegate.addActionListener(actionListener);
    }

    public Object getCellEditorValue() {
        return fileName;
    }

    private void changeFileName(String fileName) {
        this.fileName = fileName;
        delegate.setText(fileName);
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        changeFileName((String) value);
        return delegate;
    }

    @Override
    public boolean stopCellEditing() {
        AWInternaContext.instance().cellEditingEnd();
        return super.stopCellEditing();
    }

    @Override
    public void cancelCellEditing() {
        AWInternaContext.instance().cellEditingEnd();
        super.cancelCellEditing();
    }

}