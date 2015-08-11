package com.aw.swing.mvp.action;

import com.aw.core.view.ViewMode;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.navigation.Flow;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.util.HashMap;

/**
 * User: gmc
 * Date: 23/04/2009
 */
public class TransitionAction extends Action {
    protected String targetMode = ViewMode.MODE_INSERT;
    protected String STOP_TRANSITION = "StopTransition";
    protected boolean transitionStopped = false;
    protected boolean transitionStoppedWithException = false;
    protected boolean targetClassDynamic = false;

    BackBeanProvider backBeanProvider = new BackBeanProvider();

    protected Flow getNewFlow() {
        Flow flow = AWWindowsManager.instance().getNewFlow(pst.getClass(), getTargetPstClass());
        flow.setActionExecuted(this);
        return flow;
    }

    protected Object executeIntern() throws Throwable {
        transitionStopped = false;
        final Flow flow = getNewFlow();
        Object result = null;
        try {
            result = executeTransitionIntern(flow);
        } catch (Exception e) {
            throw e;
        }
        if (STOP_TRANSITION.equals(result)) {
            AWWindowsManager.instance().remove(flow);
            transitionStopped = true;
            return null;
        }
        if (targetClassDynamic){
            flow.setEndPst(targetPstClass);
        }
        String titleSuffix ="";
        if (getJComponent() !=null && getJComponent() instanceof JButton){
            titleSuffix = ((JButton)getJComponent()).getText();
        }
        Presenter pst = PstMgr.instance().getPst(targetPstClass,titleSuffix);
        pst.setViewMode(targetMode);
        if (StringUtils.hasText(getTargetPstTitle())){
            pst.getViewMgr().setTitle(getTargetPstTitle());    
        }
        initializeTargetPst(pst);

        Object backBean = backBeanProvider.getBackBean(pst, flow);
        pst.setBackBean(backBean);
        pst.init();
        return null;
    }

    protected void initializeTargetPst(Presenter targetPst) {

    }


    protected Object executeTransitionIntern(Flow flow) throws Exception {
        return new HashMap();
    }

    public boolean isTransitionStopped() {
        return transitionStopped;
    }

    public boolean isTransitionStoppedWithException() {
        return transitionStoppedWithException;
    }

    public void setTransitionStoppedWithException(boolean transitionStoppedWithException) {
        this.transitionStoppedWithException = transitionStoppedWithException;
    }

    public TransitionAction setTargetMode(String targetMode) {
        this.targetMode = targetMode;
        return this;
    }

    public TransitionAction setTargetClassDynamic(boolean targetClassDynamic) {
        this.targetClassDynamic = targetClassDynamic;
        return this;
    }
}
