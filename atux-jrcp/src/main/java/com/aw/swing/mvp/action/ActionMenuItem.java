package com.aw.swing.mvp.action;

import javax.swing.Action;
import javax.swing.*;

/**
 * User: Juan Carlos Vergara
 * Date: 14/12/2009
 */
public class ActionMenuItem extends JMenuItem implements ActionEnableable {
    public ActionMenuItem(Action a) {
        super(a);
    }
}
