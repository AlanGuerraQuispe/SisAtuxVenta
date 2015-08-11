package com.aw.swing.mvp.util;

import atux.util.AdminIFrame;
import com.aw.swing.context.SwingContext;
import com.aw.swing.mvp.navigation.AWWindowsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * User: Juan Carlos Vergara
 * Date: 20/05/2009
 */
public class SwingShowJDialogAction extends AbstractAction {
    Class pstClazz;
    String label;
    boolean useMessageBlocker;

    public SwingShowJDialogAction(Class presenterClass, String label) {
        this.pstClazz = presenterClass;
        putValue(Action.NAME, label);
    }

    private JDesktopPane dp;
    public void actionPerformed(final ActionEvent actionEvent) {
        JDialog internalFrame = null;
        try {
            SwingContext.desactivarActionSupport();
            internalFrame = (JDialog) pstClazz.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension objectSize = internalFrame.getSize();
        if (objectSize.height > screenSize.height) {
            objectSize.height = screenSize.height;
        }
        if (objectSize.width > screenSize.width) {
            objectSize.width = screenSize.width;
        }
        internalFrame.setLocation((((screenSize.width - objectSize.width) / 2) - 40), (screenSize.height - objectSize.height) / 2);

        internalFrame.setVisible(true);
    }
}
