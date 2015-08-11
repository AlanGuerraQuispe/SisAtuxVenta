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
package com.aw.swing.mvp.ui.msg;

import com.aw.swing.context.MessageSourceImpl;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.ui.common.ProcessMsgBlocker;
import com.aw.swing.mvp.view.View;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;


/**
 * @author jcvergara
 *         12/11/2004
 */
public class MessageDisplayerImpl implements MessageDisplayer {
    protected static final Log logger = LogFactory.getLog(MessageDisplayerImpl.class);

    public static final int BUTTON_YES = 0;

    public static final int BUTTON_NO = 1;

    /**
     * The options for the options dialog
     */
    private static Object[] options = {"Yes", "No"};

    private MessageSourceImpl messageSource;

    public MessageDisplayerImpl(MessageSourceImpl messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Show a message used to inform to the user of something that happened
     *
     * @param message
     */
    public void showMessage(String message) {
        showMessage(message, null);
    }

    public void showErrorMessage(String message) {
        showErrorMessage(getParentContainer(), message);
    }

    public void showMessage(String messageKey, Object[] args) {
        showMessage(getParentContainer(), getMessage(messageKey, args));
    }

    private static Component getParentContainer() {
        Presenter pst = AWWindowsManager.instance().getCurrentPst();
        if (pst!=null && !pst.isEmbeddedView()){
            return (Component) ((View)pst.getView()).getParentContainer();           
        }
        return AWWindowsManager.instance().getFrame();
    }

    /**
     * @param messageConfirm
     * @return
     */
    public boolean showConfirmMessage(String messageConfirm) {
        return showConfirmMessage(messageConfirm, null);
    }

    /**
     * Show a message dialog with an YES, NO Button
     *
     * @param confirmMsg
     * @return TRUE, if the user press YES
     *         FALSE if the user pres NO
     */
    public boolean showConfirmMessage(String confirmMsg, Object[] args) {
        return showConfirmMessage(getParentContainer(), getMessage(confirmMsg, args));
    }

    public String getMessage(String message, Object[] args) {
        String msg = message;
        if ((args != null && args.length > 0) || isMessageKey(message)) {
            msg = messageSource.getMessage(message, args);
        }
        return msg;
    }

    /**
     * Return true if the parameter is a messakey otherwise return false.
     * Basically check if there is any space in the message
     *
     * @param message
     * @return
     */
    private boolean isMessageKey(String message) {
        return message.indexOf(" ") == -1;
    }

    /**
     * Show a message used to inform to the user of something that happened
     *
     * @param message
     */
    public static void showMessage(Component parentContainer, String message) {
        ProcessMsgBlocker.instance().removeMessage();
        JOptionPane.showMessageDialog(parentContainer, message, GENERIC_MESSAGE_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

/*
    public static void showMessage(String message) {
        ProcessMsgBlocker.instance().removeMessage();
        JOptionPane.showMessageDialog(getParentContainer(), message, GENERIC_MESSAGE_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }
*/
    /**
     * Show a message used to inform to the user of something that happened
     *
     * @param message
     */
    public static void showErrorMessage(Component parentContainer, String message) {
        ProcessMsgBlocker.instance().removeMessage();
        JOptionPane.showMessageDialog(parentContainer, message, GENERIC_MESSAGE_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    public static boolean showConfirmMessage(Component parentContainer, String messageConfirm) {
        ProcessMsgBlocker.instance().removeMessage();
        int result = JOptionPane.showConfirmDialog(parentContainer, messageConfirm, GENERIC_MESSAGE_TITLE, JOptionPane.YES_NO_OPTION);
        return (result == JOptionPane.YES_OPTION);
    }

    public static boolean showConfirmMessage(Frame frame, String messageConfirm, int defaultButton) {
        return showConfirmMessage(frame, messageConfirm, JOptionPane.WARNING_MESSAGE, defaultButton);
    }

    public static boolean showConfirmMessage(Component parentContainer, String messageConfirm, int icon, int defaultButton) {
        ProcessMsgBlocker.instance().removeMessage();
        int result = JOptionPane.showOptionDialog(parentContainer, messageConfirm, GENERIC_MESSAGE_TITLE, JOptionPane.YES_NO_OPTION,
                icon, null, options, options[defaultButton]);
        return (result == JOptionPane.YES_OPTION);
    }
}
