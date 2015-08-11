package com.aw.swing.mvp.util;

import atux.util.AdminIFrame;
import com.aw.swing.context.SwingContext;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.navigation.AWWindowsManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Juan Carlos Vergara
 * Date: 20/05/2009
 */
public class SwingShowDialogAction extends AbstractAction {
    Class pstClazz;
    String label;
    boolean useMessageBlocker;

    public SwingShowDialogAction(Class presenterClass, String label) {
        this.pstClazz = presenterClass;
        putValue(Action.NAME, label);
    }

    private javax.swing.JDesktopPane dp;
    public void actionPerformed(final ActionEvent actionEvent) {
        JInternalFrame internalFrame = null;
        try {
            SwingContext.desactivarActionSupport();
            internalFrame = (JInternalFrame) pstClazz.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }
        AdminIFrame.mostrarVentanaCentrada(AWWindowsManager.instance().getDp(), internalFrame);

    }
}
