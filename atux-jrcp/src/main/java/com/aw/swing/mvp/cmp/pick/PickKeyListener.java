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
package com.aw.swing.mvp.cmp.pick;

import com.aw.swing.mvp.action.ActionDialog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Date: Sep 21, 2007
 */
public class PickKeyListener implements KeyListener {
    protected final Log logger = LogFactory.getLog(getClass());
    private Pick pick;


    public PickKeyListener(Pick pick) {
        this.pick = pick;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        System.out.println("KEY PRESSED::: "+ActionDialog.isKeyNotCleanAction(e));
        if (!ActionDialog.isKeyNotCleanAction(e)){
            pick.cleanPickedValues();
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}