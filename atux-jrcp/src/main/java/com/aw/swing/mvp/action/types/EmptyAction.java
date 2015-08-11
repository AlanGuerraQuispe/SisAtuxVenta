package com.aw.swing.mvp.action.types;

import com.aw.swing.mvp.action.Action;

/**
 * User: gmc
 * Date: 27-nov-2007
 */
public class EmptyAction extends Action {
    protected Object executeIntern() throws Throwable {
        return null;
    }
}
