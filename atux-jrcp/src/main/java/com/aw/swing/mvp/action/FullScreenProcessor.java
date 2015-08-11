/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.swing.mvp.action;

import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.ui.SwingUtils;
import com.aw.swing.mvp.view.View;

import javax.swing.*;
import java.awt.*;

/**
 * User: gmc
 * Date: 15-nov-2007
 */
public class FullScreenProcessor {
    private boolean isMaximized = false;
    private Dimension lastDimension = null;

    private static FullScreenProcessor instance = new FullScreenProcessor();

    private FullScreenProcessor() {
    }

    public static FullScreenProcessor getInstance() {
        return instance;
    }

    public void process() {
        View view = (View) AWWindowsManager.instance().getCurrentPst().getView();
        if (view.getParentContainer() instanceof JDialog) {
            JDialog jdlg = (JDialog) view.getParentContainer();
            if (isMaximized) {
                jdlg.setSize(lastDimension);
                isMaximized = false;
            } else {
                lastDimension = jdlg.getSize();
                Toolkit tk = Toolkit.getDefaultToolkit();
                jdlg.setSize(tk.getScreenSize());
                isMaximized = true;
            }
            SwingUtils.locateOnScreenCenter(jdlg);
            jdlg.setVisible(true);
        }
    }

    public void reset() {
        isMaximized = false;
        lastDimension = null;
    }
}
