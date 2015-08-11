package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.core.format.DateFormatter;
import com.aw.swing.mvp.binding.component.support.table.edition.support.DlgJTextFieldDateCellEditor;
import com.aw.swing.mvp.binding.component.support.table.edition.support.ScreenLocationUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class JTextFieldDateCellEditor extends JTextFieldCellEditor implements CellEditorWithPopup {
    private ShowDlgAction showDlgAction = new ShowDlgAction(this);
    protected JTable jTable;

    public JTextFieldDateCellEditor(JTable jTable, final JTextField jTextField) {
        super(jTextField);
        this.jTable = jTable;
        init();
    }

    private void init() {
        decorate(jTextField);
        JPanel jpanel = (JPanel) editorComponent;
        JButton jButton = getJButton();
        jpanel.add(jButton, BorderLayout.EAST);
    }

    private void decorate(final JTextField jTextField) {
        InputMap inputMap = jTextField.getInputMap(JComponent.WHEN_FOCUSED);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), SHOW_DLG);
        jTextField.getActionMap().put(SHOW_DLG, showDlgAction);
    }

    private JButton getJButton() {
        JButton btn = new JButton();
//        btn.setIcon(new ImageIcon(getClass().getResource("/images/cal-picker.png")));
        btn.setAction(showDlgAction);
        btn.setFocusable(false);
        btn.setFocusPainted(false);
        btn.setMargin(new Insets(0, 0, 0, 0));

        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
//        btn.setMargin(new Insets(0, 0, 0, 0));

        btn.setIcon(new ImageIcon(getClass().getResource("/images/cal-picker.png")));
        return btn;
    }

    EditorDelegate oldDelegate;

    public void showPopup() {
        setupDlgJTextFieldDate();
        String dateTxt = jTextField.getText();
        try {
            DateFormatter.DATE_FORMATTER.parse(dateTxt);
        } catch (Throwable e) {
            dateTxt = "";
            jTextField.setText("");
        }
        setValueInPopup(dateTxt);
    }

    // Set the edit placeholder for the cell, and make the delegate the JTextAreaEditorDelegate
    // (the dialog that will display the JTextArea).
    private void setupDlgJTextFieldDate() {
        oldDelegate = delegate;
        delegate = new JCalendarDelegate(this);
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

    private class JCalendarDelegate extends EditorDelegate {
        private JTextFieldDateCellEditor cellEditor;
        DlgJTextFieldDateCellEditor dlg;

        JCalendarDelegate(JTextFieldDateCellEditor cellEditor) {
            this.cellEditor = cellEditor;
            createJTextFieldDateDialog();
        }

        // Do whatever you need to create the date picker here.
        private void createJTextFieldDateDialog() {
            dlg = new DlgJTextFieldDateCellEditor();
            dlg.setCellEditor(cellEditor);
            dlg.setSize(200, 160);
        }

        // Set the value to be edited into the JTextAreaDlg and display / edit it.
        public void setValue(Object value) {
            dlg.setValue(value);
            showDlg();
        }

        private void showDlg() {
            Point point = jTextField.getLocationOnScreen();
            Point newPoint = ScreenLocationUtils.getAdjustLocationToFitScreen(dlg.getPreferredSize(),point);
            dlg.setLocation(newPoint);
            dlg.setVisible(true);
        }

        @Override
        public Object getCellEditorValue() {
            return DateFormatter.DATE_FORMATTER.format(dlg.getValue());
        }
    }
}