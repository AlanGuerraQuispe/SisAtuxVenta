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

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.List;

/**
 * User: gmc
 * Date: 21-sep-2007
 */
public class DependentGridManager {

    private GridProvider gridProvider;

    public DependentGridManager(GridProvider gridProvider) {
        this.gridProvider = gridProvider;
    }


    public void onSelectedRow(int selectedIndex, Object selectedRow) {
        List dpdGrids = gridProvider.getDependentGrids();
        for (int i = 0; i < dpdGrids.size(); i++) {
            GridProvider dpdGrid = (GridProvider) dpdGrids.get(i);
//            BeanWrapper dpdGridInfo = new BeanWrapperImpl(dpdGrid.getGridInfoProvider());
//            dpdGridInfo.setPropertyValue("masterBean", selectedRow);
            dpdGrid.refresh(selectedRow);
        }
    }

    public void onClearSelectedRow() {
        List dpdGrids = gridProvider.getDependentGrids();
        for (int i = 0; i < dpdGrids.size(); i++) {
            GridProvider dpdGrid = (GridProvider) dpdGrids.get(i);
            BeanWrapper dpdGridInfo = new BeanWrapperImpl(dpdGrid.getGridInfoProvider());
//            dpdGridInfo.setPropertyValue("masterBean", null);
            dpdGrid.clear();
        }
    }
}
