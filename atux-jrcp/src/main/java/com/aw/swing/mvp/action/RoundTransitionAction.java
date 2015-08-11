package com.aw.swing.mvp.action;

import com.aw.swing.mvp.navigation.Flow;

/**
 * User: gmc
 * Date: 23/04/2009
 */
public class RoundTransitionAction extends TransitionAction {
    /**
     * Method executed when the flow returns to the controller on which was executed the action
     * @return
     */
    public Object executeOnReturn(Flow initialFlow, Flow endFlow) {
        return null;
    }
}
