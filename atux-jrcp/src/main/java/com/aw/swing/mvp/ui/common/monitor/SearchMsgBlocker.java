package com.aw.swing.mvp.ui.common.monitor;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * User: gmc
 * Date: 15/12/2009
 */
public class SearchMsgBlocker {
    private static final SearchMsgBlocker instance = new SearchMsgBlocker();
    SearchMsgBlockerDlg dlg;
    MonitorTask monitor;
    private boolean active =false;

    private SearchMsgBlocker() {
    }
    public static final SearchMsgBlocker instance(){
        return instance;
    }

    public void show() {
        active =true;
        monitor = new MonitorTask(this);
        monitor.init();

        Presenter currentPst = AWWindowsManager.instance().getCurrentPst();
        JDialog parent = (JDialog) ((View) currentPst.getView()).getParentContainer();
        showDlg(parent);
    }

    private void showDlg(final JDialog parent) {
        if (SwingUtilities.isEventDispatchThread()){
            showInternal(parent);
        }else{
            try {
                SwingUtilities.invokeAndWait(new Runnable(){
                    public void run() {
                        showInternal(parent);
                    }
                });
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

    }

    private void showInternal(JDialog parent) {
        dlg = new SearchMsgBlockerDlg(parent, "test", Dialog.ModalityType.DOCUMENT_MODAL);
        configureDlg(parent);
        dlg.setVisible(true);
    }

    private void configureDlg(JDialog parent) {
        dlg.setLocationRelativeTo(parent);
        Action close = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                close();
            }
        };
        dlg.getVsr().btnCancel.addActionListener(close);
        dlg.getVsr().pnlMain.registerKeyboardAction(close, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void close() {
        monitor.stop();
        if (dlg!=null){
            closeDlg();
        }
        active = false;
    }

    private void closeDlg() {
        if (SwingUtilities.isEventDispatchThread()){
            dlg.setVisible(false);
            dlg.dispose();
        }else{
            try {
                SwingUtilities.invokeAndWait(new Runnable(){
                    public void run() {
                        dlg.setVisible(false);
                        dlg.dispose();
                    }
                });
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public void setLabel(int numRetrievedRows) {
        dlg.getVsr().lblCounter.setText(String.valueOf(numRetrievedRows));
    }


    public boolean isActive() {
        return active;
    }
}
