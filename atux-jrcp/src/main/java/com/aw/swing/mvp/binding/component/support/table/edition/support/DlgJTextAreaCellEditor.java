package com.aw.swing.mvp.binding.component.support.table.edition.support;

import com.aw.swing.mvp.binding.component.support.JTextFieldDocumentDecorator;
import com.aw.swing.mvp.binding.component.support.table.edition.JTextAreaCellEditor;
import com.aw.swing.mvp.navigation.AWWindowsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * User: gmc
 * Date: 21/07/2009
 */
public class DlgJTextAreaCellEditor extends JDialog {
    private final static String SAVE_ACTION = "JTextAreaCellEditor$SaveAction";
    private final static String CANCEL_ACTION = "JTextAreaCellEditor$CancelAction";

    private FrmJTextArea frm;
    private JTextArea textArea;
    private JTextAreaCellEditor cellEditor;
    private Font font;

    public DlgJTextAreaCellEditor(Font font) {
        super((Dialog) AWWindowsManager.instance().getCurrentPst().getViewMgr().getView().getParentContainer(),true);
        this.font = font;
        frm = new FrmJTextArea();
        setLayout(new BorderLayout());
        init();
    }

    private void init() {
        setUndecorated(true);
        add(frm.pnlMain, BorderLayout.CENTER);
        textArea = frm.txtData;
        textArea.setFont(font);
        textArea.putClientProperty("awCellEditor",true);
        frm.btnAceptar.setAction(new SaveAction());
        frm.btnAceptar.setText("Aceptar");
        frm.btnAceptar.putClientProperty("awCellEditor",true);
        frm.btnCancel.setAction(new CancelAction());
        frm.btnCancel.setText("Cancelar");
        frm.btnCancel.putClientProperty("awCellEditor",true);
        installKeyActions();
        addListeners();
    }


    private void installKeyActions() {
        InputMap inputMap = frm.pnlMain.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), SAVE_ACTION);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), CANCEL_ACTION);
        frm.pnlMain.getActionMap().put(SAVE_ACTION, new SaveAction());
        frm.pnlMain.getActionMap().put(CANCEL_ACTION, new CancelAction());
    }

    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                textArea.requestFocusInWindow();
            }

            public void windowDeactivated(WindowEvent e) {
                cancelAction();
            }
        });
    }

    private void cancelAction() {
        setVisible(false);
        dispose();
        cellEditor.dlgCancelAction();
    }

    private void saveAction() {
        setVisible(false);
        dispose();
        cellEditor.dlgSaveAction();
    }

    public void setValue(Object value) {
        textArea.setText((value != null) ? value.toString() : "");
    }

    public Object getValue() {
        return textArea.getText();
    }

    private class SaveAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            saveAction();
        }
    }

    private class CancelAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            cancelAction();
        }
    }

    public void setCellEditor(JTextAreaCellEditor cellEditor) {
        this.cellEditor = cellEditor;
        JTextFieldDocumentDecorator.decorate(textArea, cellEditor.getColumnInfo().getPropertyValidator(), cellEditor.getColumnInfo().getPropertyBinding());
    }

    public static void main(String[] args) {
        DlgJTextAreaCellEditor dlg = new DlgJTextAreaCellEditor(Font.getFont(Font.MONOSPACED));
        dlg.setCellEditor(new JTextAreaCellEditor(null,null,null));
        dlg.setSize(320,300);
        dlg.setVisible(true);
    }
}
