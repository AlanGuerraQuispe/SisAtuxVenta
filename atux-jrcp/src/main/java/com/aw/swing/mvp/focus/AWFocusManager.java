/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.swing.mvp.focus;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.component.support.table.edition.CellEditorUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 07-jun-2007
 * It will be in charge to manage the focus in a view
 */
public class AWFocusManager {
    protected final Log logger = LogFactory.getLog(getClass());

    protected Presenter presenter;
    protected List focusZones = new ArrayList();
    protected FocusZone currentFocusZone;
    private Stack lastCmpWithFocus = new Stack();
    private boolean temporal = false;


    public AWFocusManager(Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Request the focus on the last jComponent that had the focus
     */
    private void requestFocusOnPreviousCmp() {
        Object lastCmp = lastCmpWithFocus.getLast();
        if (lastCmp instanceof JComponent){
            ConcurrentFocusManager.getInstance().invokeLaterRequestFocus("", (JComponent) lastCmp);    
        }
        
    }


    private FocusZone getZoneOwnerFor(Component component) {
        for (int i = 0; i < focusZones.size(); i++) {
            FocusZone focusZone = (FocusZone) focusZones.get(i);
            if (focusZone.contains(component)) {
                return focusZone;
            }
        }
        return null;
    }

    public void addFocusZone(FocusZone focusZone) {
        focusZones.add(focusZone);
    }


    Component lastComponent = null;


    public void focusGained(JComponent component) {
        if (component == lastComponent && !temporal) {
            return;
        }

        if (component == lastComponent && temporal) {
            logger.debug("Regaining focus after a losing temporal focus");
            temporal = false;
            if (currentFocusZone != null) {
                currentFocusZone.focusReGainedAfterTemporaryLost(new AWFocusEvent(component, lastComponent));
            }
            return;
        }

        Component oldLastComponent = lastComponent;
        lastComponent = component;
        logger.debug("Focus Gained for:" + component);
        FocusZone focusZoneOwner = getZoneOwnerFor(component);
        if(focusZoneOwner==null){
            return;            
        }
        if (currentFocusZone != focusZoneOwner) {
            logger.debug("Focus Zone Owner:" + focusZoneOwner);
            if (currentFocusZone != null) {
                AWFocusEvent event = new AWFocusEvent(oldLastComponent, lastComponent);
                try {
                    currentFocusZone.focusLost(event);
// Todo Cuando se tenga claro como manejar los AWSwingExcecption volver a esta parte
//                    } catch (AWSwingException e1) {
//                        lastCmpWithFocus.push(e1.getComponent());
//                        presenter.getMessageDisplayer().showErrorMessage(e1,true);
//                        event.consume();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    event.consume();
                }
                if (event.isConsumed()) {
                    requestFocusOnPreviousCmp();
                    return;
                }
            } else {
                // In order to avoid that the focus got over a dependent zone without passing from the master zone
                if (focusZoneOwner != null && focusZoneOwner.getMasterFocusZone() != null) {
                    requestFocusOnPreviousCmp();
                    return;
                }
            }

            currentFocusZone = focusZoneOwner;
            if (currentFocusZone != null) {
                currentFocusZone.focusGained(new AWFocusEvent(oldLastComponent, lastComponent));
            }
        } else {
                if (currentFocusZone != null) {
                    Boolean lastCmpWasCellEditor = false;
                    if (oldLastComponent!=null){
                        lastCmpWasCellEditor = (Boolean) ((JComponent)oldLastComponent).getClientProperty(CellEditorUtils.AW_CELL_EDITOR);
                    }
                    if (lastCmpWasCellEditor==null || !lastCmpWasCellEditor || !(component instanceof JTable)){
                        currentFocusZone.focusGainedBy(component);
                    }
            }
        }
        if (component instanceof JComponent){
            lastCmpWithFocus.push(component);
        }
    }


    public void focusLost(JComponent component) {
        logger.debug("Focus Lost for:" + component);
        lastCmpWithFocus.push(component);
    }

    public void setAsTemporal() {
        temporal = true;          
    }

    public FocusZone getCurrentFocusZone() {
        return currentFocusZone;
    }

    private class Stack {
        // This list will have always 2 elements
        private List components = new ArrayList(2);

        public Stack() {
            // Initializing the stack with two objects
            components.add(new Object());
            components.add(new Object());
        }

        public void push(Object component) {
            if (components.size() >= 1) {
                components.set(0, components.get(1));
            }
            components.set(1, component);
        }

        public Object get(int index) {
            return components.get(index);
        }

        public Object getLast() {
            return get(1);
        }
    }
}
