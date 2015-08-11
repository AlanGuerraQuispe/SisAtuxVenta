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

import com.aw.core.domain.AWBusinessException;
import com.aw.core.exception.AWSystemException;
import com.aw.support.reflection.MethodInvoker;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.navigation.Flow;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: gmc
 * Date: 20-ago-2007
 */
public abstract class Action extends AWBaseAction {
    protected Log logger = LogFactory.getLog(getClass());
    public static final String PREFIX_BEFORE = "before";
    public static final String PREFIX_AFTER = "after";

    protected Presenter pst;
    protected Class targetPstClass;
    protected String targetPstTitle;
    protected JComponent jComponent;

    protected boolean onFailedMode = false;

    /**
     * Used this method if it needed to show other presenters before the execution of the action
     */
    public void beforeExecute() throws Throwable {
        callActionMethod(PREFIX_BEFORE);
    }

    public Object execute() throws Throwable {
        Object returnValue = executeIntern();
        return returnValue;
    }

    /**
     * Used this method if it needed to show other presenters before the execution of the action
     */
    public void afterExecute() {
//        callActionMethod(PREFIX_AFTER);
    }

    protected abstract Object executeIntern() throws Throwable;

    protected Object callActionMethod(String methodName) throws Throwable {
        return callActionMethod(methodName, new Object[]{this});
    }

    protected Object callActionMethod(String methodName, Object[] parameters) throws Throwable {
        if (additionalMethodsTarget == null) {
            return null;
        }
        String realMethodName = methodName + getName();
        boolean isValidMethodName = !PREFIX_AFTER.equals(realMethodName) && !PREFIX_BEFORE.equals(realMethodName);
        if (isValidMethodName && MethodInvoker.existsMethod(additionalMethodsTarget, realMethodName)) {
            return MethodInvoker.invoke(additionalMethodsTarget, realMethodName, parameters);
        }
        return null;
    }

    /**
     * Set this action as default action
     *
     * @return this Action
     */
    public Action setAsDefaultAction() {
        isDefaultAction = true;
        return this;
    }

    public void setPst(Presenter pst) {
        this.pst = pst;
    }

    public Action closeViewAtEnd() {
        hasToCloseView = true;
        return this;
    }

    int numberOfViewsToClose = 1;

    public Action closeViewsAtEnd(int numberOfViewsToClose) {
        hasToCloseView = true;
        this.numberOfViewsToClose = numberOfViewsToClose;
        return this;
    }

    public Action closeAllViewAtEnd() {
        hasToCloseView = false;
        hasToCloseAllView = true;
        return this;
    }

    public Action refreshGridAtEnd() {
        refreshGridAtEnd = true;
        return this;
    }


    public Action repaintGridAtEnd() {
        repaintGridAtEnd = true;
        return this;
    }

    /**
     * Set the confirm message that will be used
     *
     * @param confirmMsg
     * @return this ActionConfig
     */
    public Action setConfirmMsg(String confirmMsg) {
        this.confirmMsg = confirmMsg;
        return this;
    }

    public Action needSelectedRow() {
        needSelectedRow = true;
        return this;
    }

    public Action allowMultiSelectedRows() {
        allowMultiSelectedRows = true;
        return this;
    }

    public Action setLabel(String label) {
        this.label = label;
        return this;
    }

    public Action executeOnDblClick() {
        executeOnDblClick = true;
        return this;
    }

    public Action noExecValidation() {
        execValidation = false;
        return this;
    }

    public Action noExecBinding() {
        execBinding = false;
        return this;
    }

    public Action setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
        return this;
    }

    /**
     * Set the result message that will be used at the end of this action
     *
     * @param resultMsg
     * @return this Action
     */
    public Action setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
        return this;
    }


    public Presenter getPst() {
        return pst;
    }

    /**
     * Check the condition that has to be met in order to execute the action
     */
    public void checkBasicConditions() {
        if (needSelectedRow && allowMultiSelectedRows) {
            throw new IllegalStateException("La action:<" + getId() + "> tiene seteado los 2 valores: needSelectedRow, allowMultiSelectedRows. Sólo setee 1");
        }
        if (isGridAction()) {
            GridProvider gdp = getGridProvider();
            if (gdp.hasMasterGrid()) {
                if (gdp.getMasterGridProvider().getSelectedRow() == null) {
                    throw new AWBusinessException("Debe seleccionar un Registro en el Grid Principal.");
                }
            }
        }
        if (needSelectedRow) {
            Object obj = getGridProvider().getSelectedRow();
            if (obj == null) {
                throw new AWBusinessException("Debe seleccionar un Registro");
            }
        }
        if (allowMultiSelectedRows) {
            List selectedRows = getGridProvider().getSelectedRows();
            if (selectedRows == null || selectedRows.size() == 0) {
                throw new AWBusinessException("Debe seleccionar registros usando el Check.");
            }
        }
    }

    public void checkConditions() {

    }


    public Action setKeyTrigger(int keyTrigger) {
        this.keyTrigger = keyTrigger;
        return this;
    }

    public Class getTargetPstClass() {
        return targetPstClass;
    }

    public Action setTargetPstClass(Class<? extends Presenter> targetPstClass) {
        this.targetPstClass = targetPstClass;
        return this;
    }
    public Action setTargetPstTitle(String targetPstTitle) {
        this.targetPstTitle = targetPstTitle;
        return this;
    }

    /**
     * Return the attributes that will be sent to the controller that called this controller at closing this page
     *
     * @return
     */
    public Map getAttributesAtCloseView() {
        Map attributes = new HashMap();
        attributes.put(Flow.BACK_BEAN_NAME, pst.getBackBean());
        return attributes;
    }

    public GridProvider getGridProvider() {
        if (id.getGridIndex() != null) {
            return pst.getGridProvider(id.getGridIndex());
        }
        return null;
    }

    public Object getSelectedRow() {
        return getGridProvider().getSelectedRow();
    }

    public int getSelectedRowIdx() {
        return getGridProvider().getSelectedRowIdx();
    }

    public List getSelectedRows() {
        return getGridProvider().getSelectedRows();
    }

    public List<Integer> getSelectedRowsIdx() {
        return getGridProvider().getSelectedRowsIdx();
    }

    public JComponent getJComponent() {
        if (jComponent == null && getId()!=null) {
            String bndName = "btn" + getId().asStringWithoutSeparator();
            jComponent = (JButton) pst.getViewMgr().getIpView().getComponentFullScan(bndName);
        }
        return jComponent;
    }

    public Action setJComponent(JComponent jComponent) {
        this.jComponent = jComponent;
        return this;
    }

    boolean enabled = true;

    public void enable() {
        setEnabled(true);
    }

    public void disable() {
        setEnabled(false);
    }

    public Action setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (jComponent != null) {
            updateUIStatus();
        }
        return this;
    }

    public boolean isEnabled() {
        if (enabled) {
            if (getPst().getSecurityMgr().getSecurityChecker() != null) {
                enabled = getPst().getSecurityMgr().getSecurityChecker().canExecute(this);
                if (!enabled) {
                    logger.info("Action:<" + this + "> is disabled for Security Configuration.");
                }
            }
        }
        return enabled;
    }

    public void updateUIStatus() {
        if (needVisualComponent) {
            if (SwingUtilities.isEventDispatchThread()) {
                jComponent.setEnabled(isEnabled());
            } else {
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        public void run() {
                            jComponent.setEnabled(isEnabled());
                        }
                    });
                } catch (Throwable e) {
                    throw new AWSystemException("Problems updating UI:" + this, e);
                }
            }

        }
    }

    public Action setSecurityLabel(String securityLabel) {
        this.securityLabel = securityLabel;
        return this;
    }

    public Action execValidation() {
        this.execValidation = true;
        return this;
    }

    public Action noUseMessageBlocker() {
        useMessageBlocker = false;
        return this;
    }

    public void setVisible(boolean visible) {
        JComponent jComponent = getJComponent();
        if (jComponent != null) {
            jComponent.setVisible(visible);
        }
    }

    protected <T> T getService(Class<T> svProductoClass) {
        return Application.instance().getBean(svProductoClass);
    }

    public int getNumberOfViewsToClose() {
        return numberOfViewsToClose;
    }

    public Action alwaysEnabled() {
        alwaysEnabled = true;
        return this;
    }

    public boolean isAlwaysEnabled() {
        return alwaysEnabled;
    }

    public void setOnFailedMode(boolean onFailedMode) {
        this.onFailedMode = onFailedMode;
    }

    public boolean isOnFailedMode() {
        return onFailedMode;
    }

    public String getTargetPstTitle() {
        return targetPstTitle;
    }
}
