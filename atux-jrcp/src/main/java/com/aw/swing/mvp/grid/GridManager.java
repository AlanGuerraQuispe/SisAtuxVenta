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
package com.aw.swing.mvp.grid;

import com.aw.swing.mvp.binding.component.BndSJTable;
import com.aw.swing.mvp.binding.component.support.table.function.GridFunction;
import com.aw.swing.mvp.view.IPView;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;

/**
 * User: gmc
 * Date: 09-may-2007
 * Class used to manage all the componentes related to a grid functionality, it includes
 * the manage of the title, check selector, table and other components related.
 */
public class GridManager {

    protected static final Log logger = LogFactory.getLog(GridManager.class);

    private IPView ipView;
    /**
     * {@link GridProvider#gridIndex}
     */
    private int gridIndex = 0;
    private int lastSelectedRow = -1;

    private BndSJTable bndSJTable;

    private boolean showTotalRegistros = true;
    private boolean resetSelectionOnFocusLost = false;

    public GridManager(IPView ipView, BndSJTable bndSJTable) {
        this.ipView = ipView;
        this.bndSJTable = bndSJTable;
        this.bndSJTable.setGridManager(this);
    }

    public void init() {
        configureLabelFor();
        configureGridTitleColor();
    }

    private void configureLabelFor() {
//        ipView.getLblTitGrid(gridIndex).setLabelFor(ipView.getChkSelector(gridIndex));
        ipView.getLblTitGrid(gridIndex).setLabelFor(ipView.getTblGrid(gridIndex));
        ipView.getLblTitGrid(gridIndex).setForeground(Color.BLACK);
//        ipView.getChkSelector(gridIndex).setVisible(false);
    }


    JLabel numRecords = null;

    private void configureGridTitleColor() {
        JPanel pnlGrid = ipView.getPnlGrid(gridIndex);
        pnlGrid.setBorder(BorderFactory.createLineBorder(new Color(131, 172, 219)));

        JPanel pnlTitGrid = ipView.getPnlTitGrid(gridIndex);
        pnlTitGrid.setBackground(new Color(131, 172, 219));
        pnlTitGrid.setLayout(new FormLayout("fill:16dlu:noGrow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,right:pref:grow", "center:16dlu:noGrow"));
        JLabel lblTitGrid = ipView.getLblTitGrid(gridIndex);
        lblTitGrid.setFont(new Font(lblTitGrid.getFont().getName(), Font.BOLD, 14));
        lblTitGrid.setForeground(Color.black);

        numRecords = new JLabel("");
        numRecords.setFont(new Font(lblTitGrid.getFont().getName(), Font.BOLD, 14));
        numRecords.setForeground(Color.black);
        numRecords.setVisible(showTotalRegistros);

        pnlTitGrid.remove(lblTitGrid);
        CellConstraints cc = new CellConstraints();
        pnlTitGrid.add(lblTitGrid, cc.xy(3, 1));
        pnlTitGrid.add(numRecords, cc.xy(5, 1));

    }

    public void checkChkSelector() {
        ipView.getChkSelector(gridIndex).setSelected(true);
    }

    boolean focusIsMouseTriggered = false;

    /**
     * This method select a especific row in the table
     */
    public void selectRowOnTable() {
        if (focusIsMouseTriggered) {
            return;
        }
        int valuesSize = bndSJTable.getValues().size();
        if (valuesSize == 0) {
            lastSelectedRow = -1;
            return;
        }
        int rowToBeSelected = 0;
        if (!resetSelectionOnFocusLost && lastSelectedRow != -1) {
            rowToBeSelected = lastSelectedRow;
        }
        bndSJTable.setSelectedRow(Math.min(rowToBeSelected, valuesSize - 1));

    }

    public void setFocusIsMouseTriggered(boolean focusIsMouseTriggered) {
        this.focusIsMouseTriggered = focusIsMouseTriggered;
    }

    public void requestFocusOnTable() {
        bndSJTable.getJTable().requestFocusInWindow();
    }


    public void uncheckChkSelector() {
        ipView.getChkSelector(gridIndex).setSelected(false);
    }

    /**
     * Deselect the check selector
     * Clean the selected row
     */
    public void clearTableSelection() {
        ipView.getTblGrid(gridIndex).clearSelection();
    }


    public void setGridIndex(int gridIndex) {
        this.gridIndex = gridIndex;
    }


    public void setLastSelectedRow(int lastSelectedRow) {
        this.lastSelectedRow = lastSelectedRow;
    }

    public void setResetSelectionOnFocusLost(boolean resetSelectionOnFocusLost) {
        this.resetSelectionOnFocusLost = resetSelectionOnFocusLost;
    }

    public void refreshValuesCounter() {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                Object value = "";
                GridFunction gridFunction = bndSJTable.getGridFunction();
                if (gridFunction!=null){
                    value = gridFunction.execute();
                    value = gridFunction.getLabel()+ " " + value + " | ";
                }
                numRecords.setText(value+"Total de Registros: "+bndSJTable.getValues().size() + "  ");
            }
        });

    }

    public IPView getIpView() {
        return ipView;
    }

    public void setShowTotalRegistros(boolean showTotalRegistros) {
        this.showTotalRegistros=showTotalRegistros;
    }
}
