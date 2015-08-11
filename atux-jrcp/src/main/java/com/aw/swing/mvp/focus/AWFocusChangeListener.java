package com.aw.swing.mvp.focus;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * User: gmc
 * Date: 16/06/2009
 */
public class AWFocusChangeListener implements PropertyChangeListener {
    protected final Log logger = LogFactory.getLog(getClass());

    public void propertyChange(PropertyChangeEvent evt) {
        Object newValue = evt.getNewValue();
        if ("focusOwner".equals(evt.getPropertyName())) {
            if (newValue == null) {
                Presenter currentPst = getCurrentPst();
                if (currentPst != null) {
                    currentPst.getAWFocusManager().setAsTemporal();
                }
            }
        }
        if ("permanentFocusOwner".equals(evt.getPropertyName())) {
            if (evt.getOldValue() == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Gaining focus:" + newValue);
                }
                if (newValue instanceof JComponent ) {
                    logger.debug("Gaining Calling to Gain focus:" + newValue);

                    Presenter currentPst = getCurrentPst();
                    if (currentPst != null) {
                        currentPst.getAWFocusManager().focusGained((JComponent) newValue);
                    }else{
//                        ((JComponent)newValue).getRootPane().getParent().requestFocus();
                    }
                }
            }
            if (newValue == null) {
                logger.debug("Losing focus:" + evt.getOldValue());
                if (newValue instanceof JComponent) {
                    logger.debug("Losing Calling to lost focus:" + evt.getOldValue());
                    getCurrentPst().getAWFocusManager().focusLost((JComponent) newValue);
                }
            }
        }
    }

    public Presenter getCurrentPst() {
        return AWWindowsManager.instance().getCurrentPst();
    }
}
