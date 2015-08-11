package com.atux.desktop.main;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Juan Carlos Vergara
 * Date: 20/05/2009
 */
public class PstInvokerAction extends AbstractAction {
    Class pst;
    String label;
    
    public PstInvokerAction(Class presenterClass, String label) {
        this.pst = presenterClass; 
        putValue(Action.NAME, label);
    }

    public void actionPerformed(ActionEvent e) {
        Presenter presenter = PstMgr.instance().getPst(pst);
        presenter.setBackBean(presenter.createBackBean());
        presenter.init();
    }
}
