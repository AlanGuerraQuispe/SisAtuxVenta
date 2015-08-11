package com.aw.swing.mvp.binding.component.support;

import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.ui.msg.MsgDisplayer;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * User: Julio C. Macavilca
 * Date: 14/02/2008
 */
public class BndFocusAdapter implements FocusListener {
    protected BindingComponent bndCmp;
                                      
    public void setBndCmp(BindingComponent bndCmp) {
        this.bndCmp = bndCmp;
    }

    public void focusGained(FocusEvent e) {
        try{
            focusGaineSafe(e);
        }catch (Throwable ex){
            MsgDisplayer.showMessage(ex.getMessage());
        }
    }

    public void focusLost(FocusEvent e) {
        try{
            focusLostSafe(e);
        }catch (Throwable ex){
            MsgDisplayer.showMessage(ex.getMessage());
        }
    }

    protected void focusLostSafe(FocusEvent e) throws Exception{

    }

    protected void focusGaineSafe(FocusEvent e)throws Exception {

    }

}
