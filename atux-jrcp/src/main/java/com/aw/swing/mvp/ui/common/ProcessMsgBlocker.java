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
package com.aw.swing.mvp.ui.common;

import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.ui.SwingUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;

/**
 * @author jcmacavilca
 *         15/12/2004
 */
public class ProcessMsgBlocker {
    protected final Log logger = LogFactory.getLog(getClass());

    ////////////////// SINGLETON SUPPORT //////////////////
    private static ProcessMsgBlocker singleton = new ProcessMsgBlocker();

    private static String TITLE = "[Sist. Suministros] Por favor espere.";


    public static ProcessMsgBlocker instance() {
        return singleton;
    }

    public static void initialize(String title) {
        TITLE = title;
    }

    ////////////////// CLASS SUPPORT //////////////////
    //   private DlgMensaje dlgView;
    private ViewDisplayer viewDisplayer = new ViewDisplayer();

    public void showMessage(final String message) {

        if (SwingUtilities.isEventDispatchThread()) {
            internalShowMessage(message);
        } else {

            try {
                SwingUtilities.invokeAndWait(new Runnable() {
                    public void run() {
                        internalShowMessage(message);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    private void internalShowMessage(String message) {
        if (viewDisplayer.running) {
            logger.info("Blockuser input already running");
            viewDisplayer.dlgView.setTitle(message); //JCM agregado
            return;
        }
        logger.info("Block user input:" + message);
        viewDisplayer.init(message);
        new Thread(viewDisplayer).start();
        viewDisplayer.waitUntilWindowShow();
    }

    public void removeMessage() {
        viewDisplayer.stopRun();
        logger.info("UnBlockuser input");
    }

    public boolean isActive() {
        return viewDisplayer.running;
    }

    private class ViewDisplayer implements Runnable {
        public boolean running = false;
        private DlgMensaje dlgView;

        public void init(String message) {
            if (AWWindowsManager.instance().getCurrentOwner() instanceof Window){
                dlgView = new DlgMensaje((Window) AWWindowsManager.instance().getCurrentOwner(), TITLE, true);
            } else {
                dlgView = new DlgMensaje(AWWindowsManager.instance().getFrame(), TITLE, true);
            }
            SwingUtils.locateOnScreenCenter(dlgView);

            dlgView.getLblMessage().setText(message);
            viewDisplayer.running = false;
        }

        public void run() {
            running = true; // not completely thread safe but is the best aproach one can do
            dlgView.setVisible(true);
            logger.info("UnBlockuser input finished");

        }

        public void stopRun() {
            if (dlgView != null) {
                dlgView.setVisible(false);
                dlgView.dispose();
            }
            running = false;
        }

        public void waitUntilWindowShow() {
            // wait to window is show
            while (!running)
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                }
        }

    }

}
