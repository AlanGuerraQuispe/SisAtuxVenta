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
package com.aw.swing.mvp.focus;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;

/**
 * @author jcvergara
 *         12/11/2004
 */
public class ConcurrentFocusManager {
    protected final Log logger = LogFactory.getLog(this.getClass());

    private static ConcurrentFocusManager instance = new ConcurrentFocusManager();

    public static ConcurrentFocusManager getInstance() {
        return instance;
    }

    private ConcurrentFocusManager() {
    }


    public void requestFocus(String description, Component target) {
        target.requestFocus();
    }

    public void invokeLaterRequestFocus(final String description, final JComponent component) {
        if (component == null){
            return;            
        }
        invokeLater(description, new Runnable() {
            public void run() {
                logger.debug("Requesting focus in :" + component);
                component.requestFocus();
            }
        });
    }

    public void invokeLater(final String description, final Runnable runnable) {
        logger.debug("FocusConcur enqueued:" + description);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                logger.debug("FocusConcur dequeued:" + description);
                runnable.run();
            }
        });
    }

    public void invokeAndWait(final JComponent component) {
        final Runnable requestFocusRunner = new Runnable() {
            public void run() {
                logger.debug("Calling request focus in " + Thread.currentThread());
                component.requestFocus();
            }
        };

        Thread requestFocusThread = new Thread() {
            public void run() {
                try {
                    SwingUtilities.invokeAndWait(requestFocusRunner);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                logger.debug("Finished on invoke and wait in " + Thread.currentThread());
            }
        };
        requestFocusThread.start();

    }
}
