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

import com.aw.swing.mvp.Presenter;
import com.aw.swing.spring.Application;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


/**
 * User: gmc
 * Date: 17-sep-2007
 */
public class GridProviderManager {
    private Presenter presenter;
    private List<GridProvider> gridProviders = new ArrayList();
    private List<Integer> gridIndexList = new ArrayList();

    public GridProviderManager(Presenter presenter) {
        this.presenter = presenter;
    }

    public void init() {
        initGridProviders();
        settingDependentFocusZones();
    }

    private void settingDependentFocusZones() {
        for (int i = 0; i < gridProviders.size(); i++) {
            GridProvider gdpMaster = (GridProvider) gridProviders.get(i);
            if (gdpMaster.hasDependentGrids()) {
                List dependentGrids = gdpMaster.getDependentGrids();
                for (int j = 0; j < dependentGrids.size(); j++) {
                    GridProvider gdpDependent = (GridProvider) dependentGrids.get(j);
                    gdpMaster.addDependentFocusZone(gdpDependent.getFocusZone());
                }
            }
        }
    }

    private void initGridProviders() {
        for (int i = 0; i < gridProviders.size(); i++) {
            GridProvider gridProvider =  gridProviders.get(i);
            gridProvider.init();
        }
    }

    public GridProvider registerGridProvider(GridInfoProvider gridInfoProvider) {
        return registerGridProvider(gridInfoProvider,gridProviders.size()); 
    }

    public GridProvider registerGridProvider(GridInfoProvider gridInfoProvider,int gridIndex) {
        if (gridIndexList.contains(new Integer(gridIndex))){
            throw new IllegalStateException("The grid index:<"+gridIndex+"> already exist. Current Grid Indexes:"+ gridIndexList.toArray());            
        }

        try {
            Application.instance().autowireFields(gridInfoProvider);
        } catch (Exception e) {
            e.printStackTrace();
        }
        GridProvider gridProvider = new GridProvider(gridInfoProvider);
        gridProvider.setPresenter(presenter);
        gridProvider.setGridIndex(gridIndex);
        gridProviders.add(gridProvider);
        return gridProvider;
    }

//    private void autowireFields(GridInfoProvider gridInfoProvider) {
//        List<Field> fields =  AnnotationUtils.getAnnotatedFieldsFrom(gridInfoProvider, Autowired.class);
//        for (Field field : fields) {
//            Object bean = Application.instance().getBean(field.getType());
//            AttributeAccessor.set(gridInfoProvider,field, bean);
//        }
//    }

    public GridProvider getGridProvider() {
        return getGridProvider(0);
    }

    public GridProvider getGridProvider(int gridIndex) {
        for (GridProvider gridProvider : gridProviders) {
            if (gridProvider.getGridIndex() == gridIndex){
                return gridProvider;
            }
        }
        throw new IllegalStateException("There is not any registered grid with index:"+gridIndex);
    }

    public int getNumGrids() {
        return gridProviders.size();
    }

    public GridProvider getGridProviderFor(JTable jtable) {
        for (int i = 0; i < gridProviders.size(); i++) {
            GridProvider gridProvider = gridProviders.get(i);
            if (gridProvider.getJTable() == jtable) {
                return gridProvider;
            }
        }
        throw new IllegalStateException("The table:<" + jtable + "> does not have any GridProvider linked.");
    }

    public void setValueToJComponentAtBeginning() {
        for (int i = 0; i < gridProviders.size(); i++) {
            GridProvider gridProvider = (GridProvider) gridProviders.get(i);
            gridProvider.setValueToJComponentAtBeginning();
        }
    }

    public void removeEditors() {
        if (!existGridsWithCellEditors()) {
            return;
        }
        for (GridProvider gdp : gridProviders) {
            if (gdp.isEditable() || gdp.hasSelectorColumn()){
                gdp.removeEditor();
            }
        }
    }

    Boolean existEditableGrids = null;

    private boolean existGridsWithCellEditors() {
        if (existEditableGrids == null) {
            existEditableGrids = Boolean.FALSE;
            for (GridProvider gdp : gridProviders) {
                if (gdp.isEditable() || gdp.hasSelectorColumn()){
                    existEditableGrids = Boolean.TRUE;
                    break;
                }
            }
        }
        return existEditableGrids.booleanValue();
    }

    public List<GridProvider> getGridProviders() {
        return gridProviders;
    }

    public void validate() {
        for (GridProvider gridProvider : gridProviders) {
            gridProvider.validate();
        }
    }
}
