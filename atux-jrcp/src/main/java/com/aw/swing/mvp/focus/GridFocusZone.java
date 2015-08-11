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
import com.aw.swing.mvp.grid.GridManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

/**
 * User: gmc
 * Date: 12-jun-2007
 * Gather the information needed for the tables. This consist of the title, the checkbox and the table
 * that represent a common table
 */

public class GridFocusZone extends FocusZone {
    /**
     * Represents the number of the grid in one page.
     * The values could be 0,1,2,.. so on
     */
    protected int gridIndex = 0;

    protected GridManager gridManager;

    /**
     * Last selected row
     */
    protected int lastSelectedRow = -1;

    public GridFocusZone(Presenter presenter, int gridIndex) {
        this.presenter = presenter;
        this.gridIndex = gridIndex;
    }

    public boolean contains(Component component) {
        boolean isInFocusZone = super.contains(component);
        if (!isInFocusZone && (component instanceof JComponent)){
            JComponent jComponent = (JComponent) component;
            Boolean value = (Boolean) jComponent.getClientProperty(CellEditorUtils.AW_CELL_EDITOR);
            if (value!=null && getJTable().isEditing()){
                isInFocusZone = value;
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Contains:" + isInFocusZone);
        }
        return isInFocusZone;
    }


    public void init() {
        components = getIpView().getGridCmps(gridIndex);
    }

    /**
     * Invoked when the zone gains the keyboard focus.
     *
     * @param event
     */
    public void focusGained(AWFocusEvent event) {
        if (event.getTo() instanceof JButton){
            if (logger.isDebugEnabled()){
                logger.debug("Gainging focus from:" +event.getFrom());    
            }
            return;
        }
        // Check if the focus is comming from cell editor
        Component cmpSource = event.getFrom();
        if (cmpSource instanceof JComponent){
            Component cmpNextFocus = (Component) ((JComponent)cmpSource).getClientProperty("nextFocus");
            if (cmpNextFocus!=null && cmpNextFocus == getJTable()){
                logger.debug("Focus is comming from cell editor.");
                return;
            }
        }
        gridManager.checkChkSelector();
        gridManager.selectRowOnTable();
        gridManager.requestFocusOnTable();
    }

    /**
     * Invoked when the zone loses the keyboard focus.
     *
     * @param event
     */
    public void focusLost(AWFocusEvent event) {
        Component from = event.getFrom();
        Component to = event.getTo();
        if (from instanceof JComponent){
            JComponent jComponent = (JComponent) from;
            Boolean value = (Boolean) jComponent.getClientProperty(CellEditorUtils.AW_CELL_EDITOR);
            if (value!=null && getJTable().isEditing()){
                getJTable().editingStopped(new ChangeEvent(from));
            }
        }

        if ((from instanceof JTable)&&(to!=null && to.getParent()== from)){
            return;
        }
        gridManager.uncheckChkSelector();
        if (isMasterFocusZone() || hasDependents()) {
            if (!belongToDependentFocusZones(event.getTo())) {
                 gridManager.clearTableSelection();
            }
        } else {
            gridManager.clearTableSelection();
            if (masterFocusZone!=null && !masterFocusZone.belongToDependentFocusZones(event.getTo())) {
                masterFocusZone.focusLost(new AWFocusEvent(event.getFrom(), event.getTo()));
            }
        }
    }

    private boolean hasDependents() {
        return dependentFocusZones!=null && dependentFocusZones.size()>0;
    }

    public void focusGainedBy(Component component) {
//        JCheckBox jCheckBox = presenter.getIpView().getChkSelector(gridIndex);
//        if (jCheckBox == component) {
//            gridManager.requestFocusOnTable();
//        }
        JTable jtable = getJTable();
        if (jtable == component) {
            gridManager.selectRowOnTable();
//            gridManager.requestFocusOnTable();
        }
    }

    private JTable getJTable() {
        return presenter.getIpView().getTblGrid(gridIndex);
    }


    public void setGridManager(GridManager gridManager) {
        this.gridManager = gridManager;
    }


    @Override
    public void focusReGainedAfterTemporaryLost(AWFocusEvent awFocusEvent) {
        if (awFocusEvent.getTo() instanceof JButton){
            gridManager.selectRowOnTable();
            gridManager.requestFocusOnTable();
        }
    }

    public int getGridIndex() {
        return gridIndex;
    }
}
