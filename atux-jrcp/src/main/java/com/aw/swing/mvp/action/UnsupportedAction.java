package com.aw.swing.mvp.action;

/**
 * User: gmc
 * Date: 23/04/2009
 */
public class UnsupportedAction extends Action {
    String message = "Action not supported yet";


    public UnsupportedAction(String message) {
        this.message = message;
    }

    public UnsupportedAction() {
    }

    protected Object executeIntern() throws Throwable {
        throw new UnsupportedOperationException(message);
    }
}