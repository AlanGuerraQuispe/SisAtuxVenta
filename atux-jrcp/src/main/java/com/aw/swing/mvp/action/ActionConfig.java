package com.aw.swing.mvp.action;

import com.aw.swing.mvp.Presenter;

import javax.swing.*;

/**
 * User: gmc
 * Date: 23/04/2009
 */
public interface ActionConfig {
    ActionConfig setAsDefaultAction();

    public ActionConfig notNeedVisualComponent();

    ActionConfig closeViewAtEnd();

    ActionConfig closeViewsAtEnd(int numberOfViewsToClose);

    ActionConfig closeAllViewAtEnd();

    ActionConfig refreshGridAtEnd();

    ActionConfig repaintGridAtEnd();

    ActionConfig setResultMsg(String resultMsg);

    ActionConfig setConfirmMsg(String confirmMsg);

    ActionConfig needSelectedRow();

    ActionConfig allowMultiSelectedRows();

    ActionConfig setLabel(String label);

    ActionConfig executeOnDblClick();

    ActionConfig noExecValidation();
    ActionConfig execValidation();

    ActionConfig noExecBinding();

    ActionConfig setSecurityCode(String securityCode);

    ActionConfig setTargetPstClass(Class<? extends Presenter> targetPstClass);

    ActionConfig setTargetPstTitle(String targetPstTitle);

    ActionConfig setJComponent(JComponent jComponent);

    ActionConfig setEnabled(boolean enabled);

    ActionConfig alwaysEnabled();
    
    ActionConfig setSecurityLabel(String securityLabel);

    ActionConfig noUseMessageBlocker();

    ActionConfig setKeyTrigger(int keyTrigger);

}
