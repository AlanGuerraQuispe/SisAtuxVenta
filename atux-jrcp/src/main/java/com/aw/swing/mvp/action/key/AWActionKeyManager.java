package com.aw.swing.mvp.action.key;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.*;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.cmp.pick.Pick;
import com.aw.swing.mvp.cmp.pick.PickAction;
import com.aw.swing.mvp.cmp.pick.PickManager;
import com.aw.swing.mvp.focus.AWFocusManager;
import com.aw.swing.mvp.focus.FocusZone;
import com.aw.swing.mvp.focus.GridFocusZone;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.ui.SplitPanel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * User: gmc
 * Date: 18/06/2009
 */
public class AWActionKeyManager {
    protected final Log logger = LogFactory.getLog(getClass());
    protected boolean isIncharge = false;

    public boolean isIncharge() {
        return isIncharge;
    }

    public AWActionKeyManager() {
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            public void eventDispatched(AWTEvent event) {
                if (event.getID() == MouseEvent.MOUSE_CLICKED) {
                    AWActionTipPainter.instance().hideTipWindow();
                    isIncharge = false;
                }
            }
        }, AWTEvent.MOUSE_EVENT_MASK);
    }

    public void init() {
        isIncharge = true;
        process();
    }

    private void process() {
        Presenter pst = AWWindowsManager.instance().getCurrentPst();
        if (pst == null) {
            return;
        }
        ActionResolver actionResolver = pst.getActionRsr();
        java.util.List<Action> allActions = actionResolver.getAllActions();
        for (Action action : allActions) {
            if (action.isEnabled()) {
                String keyNameForAction = getKeyNameForAction(action);
                if (!StringUtils.hasText(keyNameForAction)) {
                    continue;
                }
                JButton btn = (JButton) action.getJComponent();
                if (btn != null && btn.isShowing()&& btn.isEnabled()) {
                    AWActionTipPainter.instance().showTipWindow(btn, keyNameForAction);
                }
            }
        }
    }

    private String getKeyNameForAction(Action action) {
        Integer keyTrigger = action.getKeyTrigger();
        if (keyTrigger == null || keyTrigger == 0) {
            return null;
        }
        action.setKeyTrigger(keyTrigger);
        if (ActionDialog.isKeyForAction(keyTrigger)) {
            return ActionDialog.getNameForFKey(keyTrigger);
        }
        if (keyTrigger != 0) {
            logger.info("KEY:<" + keyTrigger + "> no currently managed");
        }
        return null;
    }

    public static void initializeKeyTriggersFor(List<Action> actions) {
        for (Action action : actions) {
            initializeKeyTriggersFor(action);
        }
    }

    private static void initializeKeyTriggersFor(Action action) {
        int keyTrigger = action.getKeyTrigger();
        if (keyTrigger == -1) {
            if (ActionNames.ACTION_ADD.equals(action.getId().getActionCmd()) ||
                    ActionNames.ACTION_ADD_ITEM.equals(action.getId().getActionCmd())) {
                keyTrigger = ActionDialog.KEY_F4;
            } else if (ActionNames.ACTION_SHOW_AUDIT_INFO.equals(action.getId().getActionCmd())) {
                keyTrigger = ActionDialog.KEY_F1;
            } else if (ActionNames.ACTION_EDIT.equals(action.getId().getActionCmd())) {
                keyTrigger = ActionDialog.KEY_F5;
            } else if (ActionNames.ACTION_SEARCH.equals(action.getId().getActionCmd())) {
                keyTrigger = ActionDialog.KEY_F2;
            } else if (ActionNames.ACTION_CLEAN_FILTER.equals(action.getId().getActionCmd())) {
                keyTrigger = ActionDialog.KEY_F3;
            } else if (ActionNames.ACTION_VIEW.equals(action.getId().getActionCmd())) {
                keyTrigger = ActionDialog.KEY_F6;
            } else if (ActionNames.ACTION_EXPORT_EXCEL.equals(action.getId().getActionCmd())) {
                keyTrigger = ActionDialog.KEY_F11;
            } else if (ActionNames.ACTION_EXPORT_PDF.equals(action.getId().getActionCmd())) {
                keyTrigger = ActionDialog.KEY_F12;
            } else if (ActionNames.ACTION_CANCEL.equals(action.getId().getActionCmd())) {
                keyTrigger = ActionDialog.KEY_ESC;
            } else if (("grabar".equals(action.getId().getActionCmd().toLowerCase())) ||
                    ("aceptar".equals(action.getId().getActionCmd().toLowerCase())) ||
                    (ActionNames.ACTION_PICK_SELECT.equals(action.getId().getActionCmd())) ||
                    (ActionNames.ACTION_OK.equals(action.getId().getActionCmd()))
                    ) {
                keyTrigger = ActionDialog.KEY_F10;
            } else if (ActionNames.ACTION_DELETE.equals(action.getId().getActionCmd()) ||
                    ActionNames.ACTION_DELETE_ITEM.equals(action.getId().getActionCmd())
                    ) {
                keyTrigger = ActionDialog.KEY_F7;
            }
            if (keyTrigger !=-1) {
                action.setKeyTrigger(keyTrigger);
            }
        }
    }

    public boolean processKeyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
//        if (keyCode!= ActionDialog.KEY_ALT && keyCode != ActionDialog.KEY_ESC) {
        if (keyCode!= ActionDialog.KEY_ALT) {
            executeActionIfApply(e);
        }
        AWActionTipPainter.instance().hideTipWindow();
        isIncharge = false;
        return true;
    }

    public boolean executeActionIfApply(KeyEvent event) {
        int keyCode =  event.getKeyCode();
        boolean  isCtrlDown =  event.isControlDown();
        if (isCtrlDown){
            if (keyCode != ActionDialog.KEY_ENTER){
                keyCode = keyCode + ActionDialog.KEY_CONTROL;
            }
        }
        Component component =  event.getComponent();
        Presenter pst = AWWindowsManager.instance().getCurrentPst();
        if (pst == null) {

           /* if (keyCode == ActionDialog.KEY_ENTER ) {
                if(event.getComponent() instanceof JTextField){
                    if(((JTextField)event.getComponent()).getKeyListeners().length>0)
                        (((JTextField)event.getComponent()).getKeyListeners()[0]).keyPressed(event);
                }

                if(event.getComponent() instanceof JComboBox){
                    if((event.getComponent()).getKeyListeners().length>0)
                        (event.getComponent()).getKeyListeners()[1].keyPressed(event);
                }

            }*/
            return false;
        }
        Action action = null;
        // check for splitter
        if (keyCode == ActionDialog.KEY_ENTER && event.isAltDown()) {
            executeSpliterAction(pst);
            return true;
        }
        // check for pick
        if (keyCode == ActionDialog.KEY_ENTER) {
            if (component instanceof JTextField) {
                JTextField jTextField = (JTextField) component;
                String pickName = (String) jTextField.getClientProperty(PickManager.PICK_NAME);
                Pick pick = (Pick) jTextField.getClientProperty(PickManager.PICK);
                if (StringUtils.hasText(pickName)) {
                    if (!pick.isPickFilled() || (pick.isPickFilled() && !StringUtils.hasText(jTextField.getText())) ){
                        jTextField.putClientProperty(BindingComponent.ATTR_EXECUTING_PICK_ACTION,Boolean.TRUE);
                        action = pst.getActionRsr().getAction(ActionIdentifier.getActionIdentifier(pickName));
                        ((PickAction)action).setExecFirstChecking(true);
                        ActionManager.instance().executeAction(action);
                    }
                    return false;
                }
            }
        }
        // check for other actions
        if (action==null){
            action = getActionFor(keyCode,component);
        }
        if (action != null) {
            clearPickTextIfNeeded(component);
            isIncharge = false;
            ActionManager.instance().executeAction(action);
            return true;
        }
        return false;
    }

    private void clearPickTextIfNeeded(Component component) {
        if (component instanceof JTextField){
            JTextField jTextField = (JTextField) component;
            Pick pick = (Pick) jTextField.getClientProperty(PickManager.PICK);
            if (pick!=null && !pick.isPickFilled()){
                jTextField.setText("");
            }
        }
    }

    private void executeSpliterAction(Presenter pst) {
        SplitPanel splitPanel = pst.getIpView().getSplitPanel();
        if (splitPanel!=null){
            splitPanel.maximizeOrRestore();            
        }
    }

    private Action getActionFor(int keyCode, Component component) {
        Presenter pst = AWWindowsManager.instance().getCurrentPst();
        if (pst == null) {
            return null;
        }
        ActionResolver actionResolver = pst.getActionRsr();
        AWFocusManager focusManager = pst.getAWFocusManager();
        FocusZone currentFocusZone = focusManager.getCurrentFocusZone();
        Action action = null;
        if (currentFocusZone!=null && currentFocusZone instanceof GridFocusZone) {
            GridProvider gdp = pst.getGridProvider(((GridFocusZone) currentFocusZone).getGridIndex());
            List<Action> gridActions = actionResolver.getActionsFor(gdp);
            action = getActionFor(gridActions, keyCode);
        }
        if (action == null) {
//            List<Action> mainActions = actionResolver.getMainActions();
//            action = getActionFor(mainActions, keyCode);
            List<Action> allActions = actionResolver.getAllActions();
            action = getActionFor(allActions, keyCode);
        }
        return action;
    }

    private Action getActionFor(List<Action> actions, int keyCode) {
        for (Action action : actions) {
            if (action.getKeyTrigger() == keyCode) {
                return action;
            }
        }
        return null;
    }
}
