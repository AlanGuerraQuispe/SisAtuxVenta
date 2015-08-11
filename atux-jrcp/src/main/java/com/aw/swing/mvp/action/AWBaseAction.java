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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: gmc
 * Date: 20-ago-2007
 */
public abstract class AWBaseAction implements ActionConfig {
    protected Log logger = LogFactory.getLog(getClass());

    public static final String PREFIX_BEFORE = "before";
    public static final String PREFIX_AFTER = "after";

    /**
     * Action id
     */
    protected ActionIdentifier id;

    /**
     * Name that identified this action
     */
    protected String name;
    protected String label;

    protected Object additionalMethodsTarget;
    protected boolean isDefaultAction = false;

    protected boolean hasToCloseView = false;
    protected boolean hasToCloseAllView = false;
    protected boolean execBinding = true;
    protected boolean execValidation = true;
    protected boolean needSelectedRow = false;
    protected boolean allowMultiSelectedRows = false;

    protected String confirmMsg;
    protected String resultMsg;

    protected boolean refreshGridAtEnd = false;
    protected boolean repaintGridAtEnd = false;
    protected boolean showViewByItself = false;
    protected boolean executeOnDblClick = false;

    protected boolean useMessageBlocker = true;
    protected boolean enabled = true;
    protected boolean alwaysEnabled = false;

    protected boolean hasToBeExecutedOnClick = false;

    protected boolean needVisualComponent = true;

    protected String securityCode = "";
    protected String securityLabel = "";

    /**
     * Key that triggers the execution of this action
     */
    protected int keyTrigger = -1;


    public String getName() {
        if (name == null) {
            name = id.getActionCmd() + ((id.getGridIndex() != null && id.getGridIndex() > 0) ? id.getGridIndex() : "");
        }
        return name;
    }
    

    public boolean hasToBeExecutedOnClick() {
        return hasToBeExecutedOnClick;
    }

    public boolean isDefaultAction() {
        return isDefaultAction;
    }

    public boolean hasToCloseView() {
        return hasToCloseView;
    }
    public boolean hasToCloseAllView() {
        return hasToCloseAllView;
    }

    public boolean isEnabled() {
        boolean canExecute = canExecute();
        return canExecute && enabled;
    }

    private boolean canExecute() {
        return true;
    }

    public String getConfirmMsg() {
        return confirmMsg;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public Object getAdditionalMethodsTarget() {
        return additionalMethodsTarget;
    }

    public void setAdditionalMethodsTarget(Object additionalMethodsTarget) {
        this.additionalMethodsTarget = additionalMethodsTarget;
    }


    public int getKeyTrigger() {
        return keyTrigger;
    }


    public boolean isGridAction() {
        return id!=null && id.getGridIndex() != null;
    }

    public ActionIdentifier getId() {
        return id;
    }

    public void setId(ActionIdentifier id) {
        this.id = id;
    }

    public boolean isRefreshGridAtEnd() {
        return refreshGridAtEnd;
    }

    public boolean isRepaintGridAtEnd() {
        return repaintGridAtEnd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNeedVisualComponent() {
        return needVisualComponent;
    }


    public ActionConfig notNeedVisualComponent() {
        this.needVisualComponent = false;
        return this;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public String getSecurityLabel() {
        return securityLabel;
    }


}