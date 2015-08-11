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
package com.aw.swing.mvp.ui;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.ui.common.ProcessMsgBlocker;
import com.aw.swing.mvp.ui.msg.MessageDisplayerImpl;

import java.awt.event.ActionEvent;

/**
 * @author jcvergara
 */
public abstract class ActionListener implements java.awt.event.ActionListener {
    private Object presenter;
    String messageBlocker;

    public ActionListener(Presenter presenter){
        this.presenter = presenter;
    }
    public ActionListener(Object presenter, String messageBlocker){
        this.presenter = presenter;
        this.messageBlocker = messageBlocker;
    }
    public void actionPerformed(ActionEvent e) {
        if (messageBlocker!=null)
            ProcessMsgBlocker.instance().showMessage(messageBlocker);
        Exception error = null;
        try {
            performAction(e);
        } catch (Exception e1) {
            error = e1;
        }finally{
            if (messageBlocker!=null)
                ProcessMsgBlocker.instance().removeMessage();
            if (error!=null){
                if (presenter instanceof com.aw.swing.mvp.Presenter){
//                    (presenter==null?new MessageDisplayerImpl(null):
//                        ((com.aw.swing.mvp.Presenter)presenter).getViewMgr()).
//                            showErrorMessage(error);
                    new MessageDisplayerImpl(null).showMessage(error.getMessage());
                }else{
//                    (presenter==null?new MessageDisplayerImpl(null):
//                        ((com.aw.swing.mvp2.Presenter)presenter).getMessageDisplayer()).
//                            showErrorMessage(error);
                    new MessageDisplayerImpl(null).showMessage(error.getMessage());
                }

            }
        }
    }

    public abstract void performAction(ActionEvent e) throws Exception;
}
