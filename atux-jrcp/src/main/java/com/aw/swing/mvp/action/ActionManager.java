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

import com.aw.core.Identifiable;
import com.aw.core.dao.HbmFailMngr;
import com.aw.core.domain.AWBusinessException;
import com.aw.core.exception.AWException;
import com.aw.core.exception.FlowBreakSilentlyException;
import com.aw.core.view.ViewMode;
import com.aw.support.collection.ListUtils;
import com.aw.swing.exception.AWValidationException;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.types.CancelAction;
import com.aw.swing.mvp.action.types.SortByColumnAction;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.grid.GridProviderManager;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.navigation.Flow;
import com.aw.swing.mvp.navigation.FlowManager;
import com.aw.swing.mvp.ui.common.ProcessMsgBlocker;
import com.aw.swing.mvp.ui.msg.MsgDisplayer;
import com.aw.swing.mvp.ui.painter.PainterMessages;
import com.aw.swing.mvp.validation.support.AWInputVerifier;
import com.aw.system.MemoryInfo;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * User: gmc
 * Date: 21-ago-2007
 * Class used to manage the actions
 */
public class ActionManager {

    protected final Log logger = LogFactory.getLog(getClass());

    public static final Action DEF_CANCEL_ACTION = new CancelAction();

    private Action actionToBeExecuted;
    private String actionExecuted;
    private static ActionManager instance = new ActionManager();

    private ActionManager() {
    }

    public static ActionManager instance() {
        return instance;
    }

    public void sortByColumn(Component component, int keyCode) {
        SortByColumnAction sortByColumnAction = new SortByColumnAction();
//        sortByColumnAction.setGridProvider(SwingContext.getCurrentPst().getGridProviderFor((JTable) component));
        sortByColumnAction.setColSortIndex(keyCode - ActionDialog.KEY_1);
        execute(sortByColumnAction);
    }

    public void executeAction(final Action action) {
        atBeginningOfAction(action);
        AWActionTipPainter.instance().hideTipWindow();
        if (action instanceof RoundTransitionAction) {
            ((RoundTransitionAction) action).setTransitionStoppedWithException(false);
        }

        GridProviderManager gridProviderManager = action.getPst().getGridProviderMgr();
        gridProviderManager.removeEditors();

        try {
            action.checkBasicConditions();
        } catch (FlowBreakSilentlyException ex) {
            logger.info("Exit flow method silently");
            return;
        } catch (AWException ex) {
            logger.error("AW Exception:", ex);
            if (action instanceof RoundTransitionAction) {
                ((RoundTransitionAction) action).setTransitionStoppedWithException(true);
            }
            PainterMessages.paintException(ex);
            return;
        }

        String confirmMsg = action.getConfirmMsg();
        if (StringUtils.hasText(confirmMsg)) {
            boolean isCancelAction = action instanceof CancelAction;
            boolean isFindPst = action.getPst() instanceof FindPresenter;
            boolean isModeReadOnly = ViewMode.MODE_READONLY.equals(action.getPst().getViewMode());
            boolean isShowCancelMsgConfirmation = action.getPst().isShowCancelMsgConfirmation();
            if (!isCancelAction ||
                    (isShowCancelMsgConfirmation && !isModeReadOnly && !isFindPst)
                    ) {
                if (!MsgDisplayer.showConfirmMessage(confirmMsg)) {
                    logger.debug("The action:<" + action.toString() + ">will not be executed because was not confirmed");
                    return;
                }
            }
        }
        try {
            action.checkConditions();
            AWInputVerifier.getInstance().disable();
            Presenter pst = action.getPst();
            if (action.execBinding) {
                pst.setValuesToBean();
            }
            if (action.execValidation) {
                pst.validate();
            }
        } catch (AWException ex) {
            if (action instanceof RoundTransitionAction) {
                ((RoundTransitionAction) action).setTransitionStoppedWithException(true);
            }
            if (ex instanceof FlowBreakSilentlyException) {
                return;
            }
            logger.error("AW Exception:", ex);
            PainterMessages.paintException(ex);
            return;
        } finally {
            AWInputVerifier.getInstance().enable();
        }

        if (action.useMessageBlocker) {
            try {
                if (!SwingUtilities.isEventDispatchThread()) {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        public void run() {
                            ProcessMsgBlocker.instance().showMessage("Procesando ...");
                        }
                    });
                } else {
                    ProcessMsgBlocker.instance().showMessage("Procesando ...");
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }

            SwingWorker swingWorker = new SwingWorker() {
                protected Object doInBackground() throws Exception {
                    executeActionInternal(action);
                    return null;
                }

                protected void done() {
                    ProcessMsgBlocker.instance().removeMessage();
                    action.afterExecute();
                }
            };
            swingWorker.execute();

        } else {
            executeActionInternal(action);
            action.afterExecute();
        }
    }

    private void atBeginningOfAction(Action action) {
        String context = "Executing: <" + action + ">" + ((action.getId() != null) ? "<" + action.getId().asString() + ">" : "");
        MemoryInfo.getInstance().showMemoryInfo(context);
    }

    private void executeActionInternal(Action action) {
        try {
            action.getPst().checkBasicConditionsFor(action);
        } catch (AWException ex) {
            logger.error("AW Exception:", ex);
            PainterMessages.paintException(ex);
            return;
        }

        logger.debug("Before executing action:<" + action.getId() + ">");
        Object actionResult = execute(action);
        logger.debug("After executing action:<" + action.getId() + ">");

        if (actionResult instanceof Throwable) {
            return;
        }
        if (action instanceof RoundTransitionAction) {
            RoundTransitionAction roundTransitionAction = (RoundTransitionAction) action;
            Flow initialFlow =null;
            Flow lastFlow = null;
            FlowManager flowManager = null;
            if ((!roundTransitionAction.isTransitionStopped()) &&
                    (!roundTransitionAction.isTransitionStoppedWithException())) {
                flowManager = AWWindowsManager.instance().getCurrentFlowMgr();
                lastFlow = flowManager.getLastFlow();

                if (lastFlow.getEndPst() != action.getPst().getClass()) {
                    return;
                }

                boolean isCancel = lastFlow.getActionExecuted().getId().getActionCmd().equals(ActionNames.ACTION_CANCEL);
                initialFlow = flowManager.getInitialFlowFor(lastFlow);
                flowManager.remove(lastFlow);
                flowManager.remove(initialFlow);
                if (isCancel) return;
                roundTransitionAction.executeOnReturn(initialFlow, lastFlow);
            }
            if (!roundTransitionAction.isTransitionStoppedWithException()) {
                if (action.isRefreshGridAtEnd()) {
                    processRefreshGridAtEnd(action, initialFlow, lastFlow);
                }
                if (action.isRepaintGridAtEnd()) {
                    action.getGridProvider().repaint();
                }

                String resultMsg = action.getResultMsg();
                if (resultMsg != null) {
                    MsgDisplayer.showMessage(resultMsg);
                }
                ActionManager.instance().setActionExecuted(action.toString());
                logger.info("The action:<" + action.toString() + "> was executed");
                if (action.hasToCloseView()) {
                    closeView(action, actionResult);
                }
            }
        } else {
            if (action.hasToCloseView()) {
                if (action.getNumberOfViewsToClose() == 1) {
                    closeView(action, actionResult);
                } else {
                    closeViews(action, actionResult);
                }

            } else if (action.hasToCloseAllView()) {
                closeAllView(action, actionResult);
            }
        }


    }

    private void processRefreshGridAtEnd(Action action, Flow initialFlow, Flow lastFlow) {
        Presenter pst = action.getPst();
        boolean wasProcesed = false;
        if (pst instanceof FindPresenter) {
            FindPresenter findPst = (FindPresenter) pst;
            if (lastFlow != null) {
                Object backBeanChild = lastFlow.getAttribute(Flow.BACK_BEAN_NAME);
                Object filter = findPst.getFilterFrom(backBeanChild);
                if ((backBeanChild != null) && (filter != null)) {
                    List values = findPst.getValues(filter);
                    if (values.size() > 0) {
                        Object value = values.get(0);
                        Integer index = 0;
                        if (initialFlow!=null){
                            index = (Integer) initialFlow.getAttribute(Flow.ROW_INDEX);
                        }
                        GridProvider gdp = action.getGridProvider();
                        int idxToSelect = 0;
                        if (index != null) {
                            idxToSelect = index;
                            gdp.getBndSJTable().updateRow(index, value);
                        } else {
                            gdp.getBndSJTable().addRow(0, value);
                        }
                        gdp.repaint();
                        gdp.getBndSJTable().setSelectedRow(idxToSelect);
                        wasProcesed = true;
                    }
                }
            }
        }
        if (!wasProcesed) {
            GridProvider gdp = action.getGridProvider();
            gdp.refresh(action.getPst().getBackBean());
            selectingRowIfApply(gdp, lastFlow);
        }
    }


    private void selectingRowIfApply(GridProvider gdp, Flow lastFlow) {
        if (lastFlow != null) {
            List values = gdp.getValues();
            if (values.size() == 1) {
                gdp.getBndSJTable().setSelectedRow(0);
                return;
            }
            final Object backBean = lastFlow.getAttribute(Flow.BACK_BEAN_NAME);
            if (backBean == null) {
                return;
            }
            Object firstCurrentValue = values.get(0);
            if ((firstCurrentValue instanceof Identifiable) && (backBean instanceof Identifiable)) {
                int index = ListUtils.indexOf(values, new UnaryPredicate() {
                    public boolean test(Object o) {
                        Identifiable currentObjIdProv = (Identifiable) o;
                        Identifiable backBeanIdProv = (Identifiable) backBean;
                        return currentObjIdProv.getId().equals(backBeanIdProv.getId());
                    }
                });
                if (index != -1) {
                    gdp.getBndSJTable().setSelectedRow(index);
                }
            } else {
                gdp.getBndSJTable().setSelectedRow(backBean);
            }
        }
    }


    private Object execute(final Action action) {
        return executeInternal(action);
    }


    private Object executeInternal(Action action) {
        actionToBeExecuted = action;
        Object actionResult = null;
        logger.debug("The action:<" + actionToBeExecuted + "> will be executed");
        Throwable actionException = null;
        try {
            AWInputVerifier.getInstance().disable();
            Presenter pst = action.getPst();
            if (action.isOnFailedMode()) {
                HbmFailMngr.seFailMode(true);
            }
            actionResult = action.execute();

            if (!(action instanceof RoundTransitionAction)) {
                if (action.refreshGridAtEnd) {
                    action.getGridProvider().refresh(pst.getBackBean());
                }
                if (action.repaintGridAtEnd) {
                    action.getGridProvider().repaint();
                }
                setActionExecuted(action.toString());
                logger.info("The action:<" + action.toString() + "> was executed");
            }
            String resultMsg = action.getResultMsg();
            if (StringUtils.hasText(resultMsg)) {
                if (resultMsg.indexOf("${") != -1) {
                    if (ViewMode.MODE_INSERT.equals(pst.getViewMode())) {
                        int initVar = resultMsg.indexOf("${");
                        int endVar = resultMsg.indexOf("}", initVar);
                        String fieldName = resultMsg.substring(initVar + 2, endVar);
                        BeanWrapper bw = new BeanWrapperImpl(pst.getBackBean());
                        Object value = bw.getPropertyValue(fieldName);
                        resultMsg = resultMsg.substring(0, initVar) + " " + value + " " + resultMsg.substring(endVar + 1);
                        MsgDisplayer.showMessage(resultMsg);
                    }
                } else {
                    MsgDisplayer.showMessage(resultMsg);
                }
            }
        } catch (FlowBreakSilentlyException ex) {
            logger.info("Exit flow method silently");
            actionException = ex;
            return ex;
        } catch (AWException ex) {
            logger.error("AW Exception:", ex);
            PainterMessages.paintException(ex);
            actionException = ex;
            return ex;
        } catch (DataIntegrityViolationException ex) {
            logger.error("Data Integrity Exception", ex);
            MsgDisplayer.showMessage("Ocurrió un error de integridad en la operación que quizo realizar.");
            actionException = ex;
            return ex;
        } catch (Throwable t) {
            logger.error("General Exception:", t);
            PainterMessages.paintException(t);
            actionException = t;
            return t;
        } finally {
            HbmFailMngr.seFailMode(false);
            action.setOnFailedMode(isExceptionThatChangeFailedMode(actionException));
            AWInputVerifier.getInstance().enable();
        }
        return actionResult;
    }

    private boolean isExceptionThatChangeFailedMode(Throwable actionException) {
        return actionException != null
                && (!(actionException instanceof AWBusinessException) || ((AWBusinessException) actionException).isForceFailMode())
                && !(actionException instanceof AWValidationException);
    }

    public void closeView(Action action, Object actionResult) {
        FlowManager flowManager = AWWindowsManager.instance().getCurrentFlowMgr();
        if (flowManager.getFlows().size() > 0 && flowManager.existInitialFlowFor(action.getPst())) {
            Flow flow = getNewFlowToCloseView(action);
            flow.setAttributes(action.getAttributesAtCloseView());
            flow.setAttribute(Flow.RESULT_ACTION, actionResult);
            flow.setAttribute("resultMsg", action.getResultMsg());
        }
        action.getPst().closeView();
    }

    private void closeViews(Action action, Object actionResult) {
        FlowManager flowManager = AWWindowsManager.instance().getCurrentFlowMgr();
        List<Flow> flows = flowManager.getFlows();
        int nroFlowsToDelete = action.getNumberOfViewsToClose() - 1;
        int nroFlows = flows.size();
        Flow currentFlow = null;
        for (int i = 0; i < nroFlowsToDelete; i++) {
            --nroFlows;
            currentFlow = flows.get(nroFlows);
            flowManager.remove(currentFlow);
        }
        if (flows.size() > 0 && currentFlow != null) {
            Flow lastFlow = flowManager.getLastFlow();
            lastFlow.setAttribute(Flow.ROW_INDEX, null);

            Flow flow = flowManager.getNewFlowToCloseView(currentFlow.getInitialPst());
            flow.setActionExecuted(action);
            flow.setAttribute("resultMsg", action.getResultMsg());
            flow.setAttributes(action.getAttributesAtCloseView());
            flow.setAttribute(Flow.RESULT_ACTION, actionResult);
            flowManager.closeSecondaryPresenters(action.getNumberOfViewsToClose());
        }
    }

    public void closeAllView(Action action, Object actionResult) {
        FlowManager flowManager = AWWindowsManager.instance().getCurrentFlowMgr();
        List<Flow> flows = flowManager.getFlows();
        if (flows.size() == 0) {
            action.getPst().closeView();
            return;
        }
        // workaround para evitar un bug al momento de llamar los rechazarpst 
        boolean forceClosing = false;
        Object cmpAction = action.getJComponent();
        if (cmpAction!=null && cmpAction instanceof JComponent){
            JComponent jcmp = (JComponent) cmpAction;
            forceClosing = jcmp.getClientProperty("forceClosing")!=null;
        }
        if (!forceClosing && flows.size() == 1) {
            closeView(action, actionResult);
            return;
        }

        flowManager.removeAllFlows();
        flowManager.addFlow(flows.get(0));

        Flow flow = getNewFlowToCloseView(action);
        flow.setAttributes(action.getAttributesAtCloseView());
        flow.setAttribute(Flow.RESULT_ACTION, actionResult);
        flow.setAttribute("resultMsg", action.getResultMsg());
        flowManager.closeSecondaryPresenters();
    }

    private Flow getNewFlowToCloseView(Action action) {
        Flow flow = AWWindowsManager.instance().getNewFlowToCloseView(action);
        return flow;
    }

    public Action getActionToBeExecuted() {
        return actionToBeExecuted;
    }

    public String getActionExecuted() {
        return actionExecuted;
    }

    public void setActionExecuted(String actionExecuted) {
        this.actionExecuted = actionExecuted;
    }

    public boolean cancelActionWasExecuted() {
        return DEF_CANCEL_ACTION.toString().equals(actionExecuted);
    }
}


