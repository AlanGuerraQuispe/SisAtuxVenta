package com.aw.swing.mvp.navigation;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionEnableable;
import com.aw.swing.mvp.view.View;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 17-nov-2007
 */
public class AWWindowsManager {
    protected final Log logger = LogFactory.getLog(getClass());

    private static AWWindowsManager instance = new AWWindowsManager();

    private JFrame frame;
    private javax.swing.JDesktopPane dp;

    public static AWWindowsManager instance() {
        return instance;
    }

    private boolean isInFrame = false;
    private List<FlowManager> flowManagers = new ArrayList();
    private FlowManager currentFlowManager;

    /**
     * Return true if the application is in the main frame
     *
     * @return
     */
    public boolean isInMainWindow() {
        return isInFrame;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
        resetValues();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                setInFrame(true);
            }
        });
    }

    public void resetValues() {
        flowManagers = new ArrayList();
        currentFlowManager = null;
    }


    public void initFlowManagerFrom(Presenter pst, Object source) {
        if (source instanceof ActionEnableable) {
            ActionEnableable lastMenuItem = (ActionEnableable) source;
            if (lastMenuItem!=null){
                lastMenuItem.setEnabled(false);
            }
            FlowManager flowManager = getFlowManager(pst, lastMenuItem);
            currentFlowManager = flowManager;
        }else{
            throw new IllegalStateException("Action source does not supported yet:"+ source);
        }
    }

    public void setPresenterToBeShown(Presenter pst) {
        if (frame==null && currentFlowManager==null) {
            FlowManager flowManager = getFlowManager(pst);
            currentFlowManager = flowManager;
        }
        if(currentFlowManager==null){
            FlowManager flowManager = getFlowManager(pst);
            currentFlowManager = flowManager;
        }
        currentFlowManager.setLastPstShown(pst);
    }

    public void removePst(Presenter pst) {
        if (currentFlowManager!=null){
            currentFlowManager.removePst(pst);
            if ((currentFlowManager.getRoot() == pst) ){
                ActionEnableable jMenuItem = currentFlowManager.getMenuItem();
                if (jMenuItem!=null){
                    enableMenuItem(jMenuItem);
                }
                flowManagers.remove(currentFlowManager);
                currentFlowManager = null;
            }
        }
    }

    private void enableMenuItem(final ActionEnableable jMenuItem) {
        if (SwingUtilities.isEventDispatchThread()){
            jMenuItem.setEnabled(true);
        }else{
            try {
                SwingUtilities.invokeAndWait(new Runnable(){
                    public void run() {
                        jMenuItem.setEnabled(true);
                    }
                });
            } catch (Throwable e) {
                logger.error("Problems enabling the menu item:"+jMenuItem,e);
            }
        }

    }


    private FlowManager getFlowManager(Presenter pst) {
        FlowManager flowManager = new FlowManager(pst);
        flowManagers.add(flowManager);
        return flowManager;
    }
    private FlowManager getFlowManager(Presenter pst, ActionEnableable jMenuItem) {
        FlowManager flowManager = new FlowManager(pst,jMenuItem);
        flowManagers.add(flowManager);
        return flowManager;
    }


    public void setInFrame(boolean inFrame) {
        isInFrame = inFrame;
//        currentFlowManager = null;
    }

    public <T extends Presenter> T getCurrentPst() {
        Presenter currentPst = null;
        if (currentFlowManager!=null){
            currentPst = currentFlowManager.getLastPstShown();
        }
        return (T) currentPst;
    }

    public void setPresenterActivated(Presenter pst) {
        currentFlowManager = findFlowManager(pst);
        isInFrame =false;
    }

    private FlowManager findFlowManager(Presenter pst) {
        for (FlowManager flowManager : flowManagers) {
            if (flowManager.contains(pst)){
                return flowManager;
            }
        }
        if (System.getProperty("test.mode") != null) return null;
        throw new IllegalStateException("Presenter:"+pst +" is not in the flows");
    }

    public Object getCurrentOwner() {
        Presenter currentPst = getCurrentPst();
        if (currentPst != null) {
            return ((View) currentPst.getView()).getParentContainer();

        }
        return getFrame();
    }

    public FlowManager getCurrentFlowMgr() {
        return currentFlowManager;
    }
    
    public Flow getNewFlow(Class<? extends Presenter> sourcePstClass, Class targetPstClass) {
        return currentFlowManager.getNewFlow(sourcePstClass,targetPstClass);  
    }

    public void remove(Flow flow) {
        currentFlowManager.remove(flow);
    }

    public Flow getNewFlowToCloseView(Action action) {
        FlowManager flowManager = getCurrentFlowMgr();
        Flow flow = flowManager.getNewFlowToCloseView(action.getPst().getClass());
        flow.setActionExecuted(action);
        flow.setAttribute("resultMsg", action.getResultMsg());
        return flow;
    }

    public JDesktopPane getDp() {
        return dp;
    }

    public void setDp(JDesktopPane dp) {
        this.dp = dp;
    }
}
