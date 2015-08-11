package com.aw.swing.mvp.action.key;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.AWInternaContext;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.ui.common.ProcessMsgBlocker;
import com.aw.swing.mvp.ui.common.monitor.SearchMsgBlocker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * User: gmc
 * Date: 18/06/2009
 */
public class AWKeyEventDispatcher implements KeyEventDispatcher {
    private static String OPTION_PANE_IDENTIFIER = "OptionPane.button";
    boolean altWasPressed = false;
    AWActionKeyManager actionKeyManager = new AWActionKeyManager();


    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            return processKeyPresssed(e);
        } else if (e.getID() == KeyEvent.KEY_RELEASED) {
            return processKeyReleased(e);
        }
        return false;
    }

    private boolean processKeyReleased(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (altWasPressed && keyCode == KeyEvent.VK_ALT) {
            actionKeyManager.init();
            return true;
        }
        if (keyCode == KeyEvent.VK_ENTER) {
            Presenter pst = AWWindowsManager.instance().getCurrentPst();
            if (pst == null) {

  /*              if (event.getComponent() instanceof JTextField) {
                    if (((JTextField) event.getComponent()).getKeyListeners().length > 0)
                        (((JTextField) event.getComponent()).getKeyListeners()[0]).keyReleased(event);
                }

                if (event.getComponent() instanceof JComboBox) {
                    if ((event.getComponent()).getKeyListeners().length > 0)
                        (event.getComponent()).getKeyListeners()[1].keyReleased(event);
                }*/


            }

        }
        return false;
    }


    private boolean processKeyPresssed(KeyEvent e) {
        if (ProcessMsgBlocker.instance().isActive()) {
            return true;
        }
        if (SearchMsgBlocker.instance().isActive()) {
            SearchMsgBlocker.instance().close();
            return true;
        }

        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ALT) {
            altWasPressed = true;
        } else if (altWasPressed) {
            altWasPressed = false;
        }
        if (actionKeyManager.isIncharge()) {
            boolean result = actionKeyManager.processKeyPressed(e);
            if (result) {
                return true;
            }
        }

        Component component = e.getComponent();

        if (keyCode == ActionDialog.KEY_F1) {
            if (component instanceof JTable) {
                JTable jTable = (JTable) component;
                if (jTable.isEditing()) {
                    return false;
                }
            }
        }

        // used to avoid execute the cancel action in jcomponents used as cellEditors
        if ((keyCode == ActionDialog.KEY_ESC) || (keyCode == ActionDialog.KEY_F10)) {
            if (component instanceof JComponent) {
                JComponent jComponent = (JComponent) component;
                Object clientProperty = jComponent.getClientProperty("awCellEditor");
                if (Boolean.TRUE.equals(clientProperty) || jComponent.getClass().getSimpleName().equals("CalendarGridPanel")) {
                    return false;
                }
            }
            if (component instanceof JTable) {
                JTable jTable = (JTable) component;
                if (jTable.isEditing()) {
                    return false;
                }
            }
            if (AWInternaContext.instance().isCellEditing()) {
                return false;
            }
        }
        if (keyCode == KeyEvent.VK_S && e.isControlDown()) {
            AWWindowsManager.instance().getFrame().toFront();
            return true;
        }

        // Used to manage OptionPane buttons
        if (OPTION_PANE_IDENTIFIER.equals(component.getName())) {
            if (keyCode == ActionDialog.KEY_ENTER) {
                JButton jButton = (JButton) component;
                jButton.doClick();
                return true;
            }
            return false;
        }

//        if (ActionDialog.isKeyForSortingGrids(e)) {
//            ActionManager.instance().sortByColumn(component, keyCode);
//            return true;
//        }

        return actionKeyManager.executeActionIfApply(e);
    }


}
