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
import com.aw.swing.mvp.view.IPView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 07-jun-2007
 * Gather a group of components in order to process the focus gained and lost properly in the
 * members of this group
 */
public class FocusZone {

    protected static final Log logger = LogFactory.getLog(FocusZone.class);
    /**
     * Presenter in which is the zone
     */
    protected Presenter presenter;
    /**
     * Components that belongs to this zone
     */
    protected List components = new ArrayList();

    protected FocusZone masterFocusZone = null;
    protected List dependentFocusZones = new ArrayList();

    /**
     * Process that initialize all the needed things in order tha zone function properly
     */
    public void init() {

    }

    /**
     * Returns true if this FocusZone contains the specified Component.
     *
     * @param component
     * @return
     */
    public boolean contains(Component component) {
        if (logger.isDebugEnabled()) {
            logger.debug("Searching:" + component);
            logger.debug("Searching in :" + components);
        }
        boolean isInFocusZone = components.contains(component);
        if (logger.isDebugEnabled()) {
            logger.debug("Contains:" + isInFocusZone);
        }
        return isInFocusZone;
    }

    /**
     * Invoked when the zone gains the keyboard focus.
     *
     * @param event
     */
    public void focusGained(AWFocusEvent event) {

    }

    /**
     * Invoked when the zone loses the keyboard focus.
     *
     * @param event
     */
    public void focusLost(AWFocusEvent event) {

    }

    public void addComponents(List components) {
        this.components.addAll(components);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public IPView getIpView() {
        return presenter.getIpView();
    }

    public void addDependentFocusZone(FocusZone dependentFocusZone) {
        dependentFocusZones.add(dependentFocusZone);
    }

    /**
     * @param focusZone
     */
    public boolean isMasterOf(FocusZone focusZone) {
        if (focusZone == null) return false;
        FocusZone master = focusZone.getMasterFocusZone();
        return master != null && master == this;
    }

    public boolean isDependentFrom(FocusZone focusZone) {
        if (focusZone == null) return false;
        return masterFocusZone != null && masterFocusZone == focusZone;
    }

    public List getDependentFocusZones() {
        return dependentFocusZones;
    }

    public FocusZone getMasterFocusZone() {
        return masterFocusZone;
    }

    public void setMasterFocusZone(FocusZone masterFocusZone) {
        this.masterFocusZone = masterFocusZone;
    }

    /**
     * Invoked always that a component of the FocusZone receives the focus
     * @param component
     */
    public void focusGainedBy(Component component) {

    }

    protected boolean belongToDependentFocusZones(Component component) {
        for (int i = 0; i < dependentFocusZones.size(); i++) {
            FocusZone focusZone = (FocusZone) dependentFocusZones.get(i);
            if (focusZone.contains(component)){
                return true;
            }
        }
        return false;
    }

    protected boolean isMasterFocusZone() {
        boolean isMaster =masterFocusZone ==null;
        return isMaster;
    }

    /**
     * Called when the component regained the focus after have been temporarily lost
     * @param awFocusEvent
     */
    public void focusReGainedAfterTemporaryLost(AWFocusEvent awFocusEvent) {
        
    }
}
