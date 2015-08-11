package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.JTextFieldDocumentDecorator;
import com.aw.swing.mvp.binding.component.support.table.edition.support.DlgJTextAreaCellEditor;
import com.aw.swing.mvp.binding.component.support.table.edition.support.ScreenLocationUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * User: gmc
 * Date: 18/07/2009
 */
public class JTextAreaCellEditor extends DefaultCellEditor implements CellEditorWithPopup {
    private ColumnInfo columnInfo;
    private JTable jTable;

    private EditorDelegate oldDelegate;
    private JTextField textField;
    private Font font;
    private ShowDlgAction showDlgAction = new ShowDlgAction(this);


    public JTextAreaCellEditor(JTable jTable, ColumnInfo columnInfo, Font font) {
        super(new JTextField());
        this.font = font;
        this.jTable = jTable;
        this.columnInfo = columnInfo;
        init();
    }

    private void init() {
        decorateJTextField();
        editorComponent = getPanel();
    }

    private void decorateJTextField() {
        textField = (JTextField) editorComponent;
        textField.setFont(font);
        textField.setBorder(null);
        textField.putClientProperty(CellEditorUtils.AW_CELL_EDITOR,true);
        JTextFieldDocumentDecorator.decorate(textField, columnInfo.getPropertyValidator(), columnInfo.getPropertyBinding());
        InputMap inputMap = textField.getInputMap(JComponent.WHEN_FOCUSED);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), SHOW_DLG);
        textField.getActionMap().put(SHOW_DLG, showDlgAction);
    }

    private JPanel getPanel() {
        JPanel jPanel = new JPanel() {
            public void addNotify() {
                super.addNotify();
                textField.requestFocus();
                textField.selectAll();
            }
        };
        jPanel.setRequestFocusEnabled(true);
        jPanel.setLayout(new BorderLayout());
        jPanel.add(textField, BorderLayout.CENTER);
        jPanel.add(getJButton(), BorderLayout.EAST);
        return jPanel;
    }

    private JButton getJButton() {
        JButton btn = new JButton();
        btn.setIcon(new ImageIcon(getClass().getResource("/images/text-area.png")));
        btn.addActionListener(showDlgAction);
        btn.setFocusable(false);
        btn.setFocusPainted(false);
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        //btn.setSize(10, 16);
        return btn;
    }

    public void showPopup() {
        setupDlgJTextArea();
        setValueInPopup(textField.getText());
    }

    // Set the edit placeholder for the cell, and make the delegate the JTextAreaEditorDelegate
    // (the dialog that will display the JTextArea).
    private void setupDlgJTextArea() {
        oldDelegate = delegate;
        delegate = new JTextAreaEditorDelegate(this);
    }

    private void setValueInPopup(Object value) {
        delegate.setValue(value);
    }

    public void dlgCancelAction() {
        cancelCellEditing();
        setUpNormalEditor();
    }

    public void dlgSaveAction() {
        stopCellEditing();// editing is finished, so tell the parent component
        setUpNormalEditor();
    }

    /**
     * Set up the editor as simple JTextField
     */
    private void setUpNormalEditor() {
        delegate = oldDelegate;
        jTable.requestFocusInWindow();
    }


    private class JTextAreaEditorDelegate extends EditorDelegate {
        private JTextAreaCellEditor cellEditor;
        private DlgJTextAreaCellEditor dlg;

        JTextAreaEditorDelegate(JTextAreaCellEditor cellEditor) {
            this.cellEditor = cellEditor;
            createJTextAreaDialog();
        }

        // Do whatever you need to create the date picker here.
        private void createJTextAreaDialog() {
            dlg = new DlgJTextAreaCellEditor(font);
            dlg.setCellEditor(cellEditor);
            dlg.setSize(306, 110);
        }

        // Set the value to be edited into the JTextAreaDlg and display / edit it.
        public void setValue(Object value) {
            dlg.setValue(value);
            showDlg();
        }

        @Override
        public Object getCellEditorValue() {
            return dlg.getValue();
        }

        private void showDlg() {
            Point point = textField.getLocationOnScreen();
            Point newPoint = ScreenLocationUtils.getAdjustLocationToFitScreen(dlg.getPreferredSize(),point);
            dlg.setLocation(newPoint);
            dlg.setVisible(true);
        }

    }

    public ColumnInfo getColumnInfo() {
        return columnInfo;
    }
}