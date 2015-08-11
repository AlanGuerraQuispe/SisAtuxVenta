package com.aw.swing.mvp.binding.component.support.table.edition.support;

import com.aw.core.format.DateFormatter;
import com.aw.swing.mvp.binding.component.support.table.edition.JTextFieldDateCellEditor;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.michaelbaranov.microba.calendar.CalendarPane;
import com.michaelbaranov.microba.common.CommitEvent;
import com.michaelbaranov.microba.common.CommitListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.util.Date;

//import com.aw.core.db.DbUtil;

/**
 * User: gmc
 * Date: 21/07/2009
 */
public class DlgJTextFieldDateCellEditor extends JDialog {
    private final static String CANCEL_ACTION = "JTextAreaCellEditor$CancelAction";

    private JTextFieldDateCellEditor cellEditor;
    private CalendarPane calendarPane;
    private JPanel pnlMain;

    public DlgJTextFieldDateCellEditor() {
        super((Dialog) AWWindowsManager.instance().getCurrentPst().getViewMgr().getView().getParentContainer(), true);
        init();
    }

    ComponentListener componentListener;

    private void init() {
        setUndecorated(true);
        pnlMain = new JPanel() {
            @Override
            public void addNotify() {
                super.addNotify();
                calendarPane.requestFocusInWindow();
            }
        };
        calendarPane = new CalendarPane();
        calendarPane.setFocusCycleRoot(true);
        calendarPane.setBorder(BorderFactory.createEmptyBorder(1, 3, 0, 3));
        calendarPane.setStripTime(false);
        calendarPane.setShowTodayButton(false);
        calendarPane.setShowNoneButton(false);

        calendarPane.setStyle(calendarPane.STYLE_CLASSIC);
        calendarPane.requestFocus(false);

        pnlMain.setBorder(new LineBorder(Color.BLACK));
//        pnlMain.setLayout(new BorderLayout());
//        pnlMain.add(calendarPane, BorderLayout.CENTER);
        pnlMain.add(calendarPane);

        add(pnlMain);

        componentListener = new ComponentListener();
        calendarPane.addPropertyChangeListener(componentListener);
        calendarPane.addCommitListener(componentListener);

        installKeyActions();
        addListeners();
    }


    private void installKeyActions() {
        InputMap inputMap = pnlMain.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), CANCEL_ACTION);
        pnlMain.getActionMap().put(CANCEL_ACTION, new CancelAction());
    }

    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                calendarPane.requestFocusInWindow();
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

    public void setValue(Object valueStr) {
        Date value = DateFormatter.DATE_FORMATTER.parse((String) valueStr);
        calendarPane.removePropertyChangeListener(componentListener);
        try {
            calendarPane.setDate(value);
        } catch (PropertyVetoException e) {
            e.printStackTrace();  
        }
        calendarPane.addPropertyChangeListener(componentListener);
    }

    public Object getValue() {
        return calendarPane.getDate();
    }

    private class CancelAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            cancelAction();
        }
    }

    public void setCellEditor(JTextFieldDateCellEditor cellEditor) {
        this.cellEditor = cellEditor;
    }

    protected class ComponentListener implements PropertyChangeListener, CommitListener {

        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getSource() == calendarPane) {
                if (CalendarPane.PROPERTY_NAME_DATE.equals(evt.getPropertyName())) {
                    saveAction();
                }
            }
        }

        public void commit(CommitEvent event) {
            if (!event.isCommit()) {
                cancelAction();
            }
        }
    }


    public static void main(String[] args) {
        DlgJTextFieldDateCellEditor dlg = new DlgJTextFieldDateCellEditor();
//        dlg.setCellEditor(new JTextAreaCellEditor(null, null, null));
        dlg.setSize(200, 150);
        dlg.setVisible(true);
    }

}