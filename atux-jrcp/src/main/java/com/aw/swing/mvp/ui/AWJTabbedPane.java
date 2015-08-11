package com.aw.swing.mvp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * User: gmc
 * Date: 07/07/2009
 */
public class AWJTabbedPane extends JTabbedPane {
    public AWJTabbedPane() {
        configureKeyActions(this);
    }

    public static void configureKeyActions(JTabbedPane jTabbedPane) {
        InputMap im = jTabbedPane.getInputMap(JTabbedPane.WHEN_FOCUSED);
        KeyStroke right = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
        javax.swing.Action rightAction = jTabbedPane.getActionMap().get(im.get(right));
        KeyStroke left = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
        javax.swing.Action leftAction = jTabbedPane.getActionMap().get(im.get(left));

        InputMap whenAncestor = jTabbedPane.getInputMap(JTabbedPane.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        KeyStroke rightAlt = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.ALT_DOWN_MASK);
        KeyStroke leftAlt = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.ALT_DOWN_MASK);
        whenAncestor.put(rightAlt, "moveRight");
        jTabbedPane.getActionMap().put(whenAncestor.get(rightAlt), rightAction);
        whenAncestor.put(leftAlt, "moveLeft");
        jTabbedPane.getActionMap().put(whenAncestor.get(leftAlt), leftAction);
    }

    public void setSelectedIndex(int index) {
        Component comp = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        // if no tabs are selected // -OR- the current focus owner is me
        // -OR- I request focus from another component and get it
        // then proceed with the tab switch
        if (getSelectedIndex() == -1 || comp == this || requestFocus(false)) {
            super.setSelectedIndex(index);
        }
    }
}
