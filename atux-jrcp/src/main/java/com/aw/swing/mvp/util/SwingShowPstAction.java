package com.aw.swing.mvp.util;

import com.aw.swing.context.SwingContext;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.focus.AWKeyboardFocusManagerDecorator;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.ui.common.ProcessMsgBlocker;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Juan Carlos Vergara
 * Date: 20/05/2009
 */
public class SwingShowPstAction extends AbstractAction {
    Class pstClazz;
    String label;
    boolean useMessageBlocker;

    public SwingShowPstAction(Class presenterClass, String label) {
        this.pstClazz = presenterClass;
        putValue(Action.NAME, label);
    }

    public SwingShowPstAction(Class presenterClass, String label, boolean useMessageBlocker) {
        this.pstClazz = presenterClass;
        this.useMessageBlocker = useMessageBlocker;
        putValue(Action.NAME, label);
    }

    public void actionPerformed(final ActionEvent actionEvent) {
/*
        if (useMessageBlocker) {
            try {
                if (!SwingUtilities.isEventDispatchThread()) {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        public void run() {
                            ProcessMsgBlocker.instance().showMessage("Procesando ...");
                        }
                    });
                } else {
                    ProcessMsgBlocker.instance().showMessage("Procesando ...");
                }
            } catch (Throwable ex) {
                ex.printStackTrace();
            }

            SwingWorker swingWorker = new SwingWorker() {
                protected Object doInBackground() throws Exception {
                    Presenter presenter = PstMgr.instance().getPst(pstClazz);
                    presenter.setBackBean(presenter.createBackBean());
                    AWWindowsManager.instance().initFlowManagerFrom(presenter, actionEvent.getSource());
                    presenter.init();
                    return null;
                }

                protected void done() {
                    ProcessMsgBlocker.instance().removeMessage();

                }
            };
            swingWorker.execute();

        } else {*/
            AWKeyboardFocusManagerDecorator.decorate();
            Presenter presenter = PstMgr.instance().getPst(pstClazz);
            presenter.setBackBean(presenter.createBackBean());
            AWWindowsManager.instance().initFlowManagerFrom(presenter, actionEvent.getSource());
            presenter.init();

//        }
    }
}
