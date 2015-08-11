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
package com.aw.swing.mvp;

import com.aw.core.Identifiable;
import com.aw.core.annotation.AnnotationUtils;
import com.aw.core.annotation.Id;
import com.aw.support.reflection.AttributeAccessor;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionIdentifier;
import com.aw.swing.mvp.action.ActionManager;
import com.aw.swing.mvp.action.ActionNames;
import com.aw.swing.mvp.action.types.CleanFilterAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.focus.ConcurrentFocusManager;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.ui.msg.MsgDisplayer;
import org.springframework.beans.BeanUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.util.List;


/**
 * This prst must be used in the find process
 *
 * @author gmateo
 *         20/10/2004
 */
public abstract class FindPresenter<E> extends AWFormPresenter<E> {
    /**
     * If this is true, the search will be executed at the beginnig otherwise not
     */
    private boolean searchAtBeginning = false;
    private boolean refreshAbortable = false;

    protected boolean showMessageIfNoRecords = false;

    protected String messageIfNoRecords = "dt.ui.find.noExistenRegistros";

    /**
     * Default constructor.
     * In this case the child will be only deleted logically
     */
    public FindPresenter() {
        forceBindingAllComponents = true;
        setShowAuditInfo(false);
    }


    public void configureElements() {
        GridProvider gdp = gridProviderMgr.registerGridProvider(getGridInfoProvider()).setPopulateAtBeginning(searchAtBeginning);
//        GridProvider gdp = gridProviderMgr.registerGridProvider(getGridInfoProvider()).setPopulateAtBeginning(!searchAtBeginning);
        if (refreshAbortable){
            gdp.setRefreshAsAbortable();
        }
        super.configureElements();
    }

    public void configureActions() {
        Action searchAction = (Action) actionRsr.registerAction(ActionNames.ACTION_SEARCH, "searchAction")
                .notNeedVisualComponent();
        if (refreshAbortable){
            searchAction.noUseMessageBlocker();            
        }
        super.configureActions();
    }

    /**
     * Execute the search action
     */
    public void searchAction() {
        GridProvider gridProvider = getGridProvider();
        gridProvider.refresh(getBackBean());
        if (gridProvider.getValues().size() > 0) {
            ConcurrentFocusManager.getInstance().invokeLaterRequestFocus("TblGrid", viewMgr.getIpView().getTblGrid());
        }
    }

    protected abstract ColumnInfo[] getColumnInfo();

    public abstract List getValues(E obj);


    /**
     * Method call when a row is selected
     *
     * @param currentRow
     */
    protected void onSelectedRow(Object currentRow) {

    }

    /**
     * Method call when the selected row is clear it means there are any selected row
     */
    protected void onClearSelectedRow() {

    }


    public Boolean onEnterKeyPressed(Component component) {
        Boolean retVal = super.onEnterKeyPressed(component);
        if (component == viewMgr.getIpView().getLastCmpBeforeSearchBtn()) {
            ActionManager.instance().executeAction(actionRsr.getAction(ActionIdentifier.getActionIdentifier(ActionNames.ACTION_SEARCH)));
            return Boolean.FALSE;
        }
        return retVal;
    }


    protected void registerFwActions() {
        super.registerFwActions();
        JComponent jComponent = (JComponent) getIpView().getComponent("btnCleanFilter");
        if (jComponent != null) {
            actionRsr.registerAction(ActionNames.ACTION_CLEAN_FILTER, new CleanFilterAction());
        }
    }


    /**
     * Repaint the grid
     */
    public void repaintGrid() {
        getGridProvider().repaint();
    }

    private GridInfoProvider getGridInfoProvider() {
        final Presenter presenter = this;
        return new GridInfoProvider<E>() {

            public ColumnInfo[] getColumnInfo() {
                return ((FindPresenter) presenter).getColumnInfo();
            }

            public List getValues(Object obj) {
                List valuesToBeShown = ((FindPresenter) presenter).getValues(obj);
                if (showMessageIfNoRecords && (valuesToBeShown == null || valuesToBeShown.size() == 0)) {
                    MsgDisplayer.showMessage(messageIfNoRecords);
                }
                return valuesToBeShown;
            }

            /**
             * Method call when a row is selected
             *
             * @param currentRow
             */
            public void onSelectedRow(Object currentRow) {
                ((FindPresenter) presenter).onSelectedRow(currentRow);
            }

            /**
             * Method call when the selected row is clear it means there are any selected row
             */
            public void onClearSelectedRow() {
                ((FindPresenter) presenter).onClearSelectedRow();
            }

        };
    }

    /**
     * Get the values that are shown in the grid that contains the records with checkBoxes
     *
     * @return
     */
    public Object getSelectedRow() {
        return getGridProvider().getSelectedRow();
    }

    public Object getSelectedRowChecked() {
        return getGridProvider().getSelectedRowChecked();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    public void setShowMessageIfNoRecords(boolean showMessageIfNoRecords) {
        this.showMessageIfNoRecords = showMessageIfNoRecords;
    }

    public boolean isSearchAtBeginning() {
        return searchAtBeginning;
    }

    /**
     * If this is true, the search will be executed at the beginnig otherwise not
     *
     * @param searchAtBeginning
     */
    public void setSearchAtBeginning(boolean searchAtBeginning) {
        this.searchAtBeginning = searchAtBeginning;
    }

    public void setRefreshAsAbortable(){
        refreshAbortable = true;
    }

    @Override
    public void onWindowsOpenedInternalOnlyForAWFW(WindowEvent e) {
        if (searchAtBeginning){
            searchAction();
        }
    }

    public E getFilterFrom(Object obj){
        if (obj instanceof Identifiable){
            Identifiable identifiable = (Identifiable) obj;
            Object filter = BeanUtils.instantiateClass(getBackBean().getClass());
            List<Field> fields = AnnotationUtils.getAnnotatedFieldsFrom(filter, Id.class);
            if (fields.size()>0){
                AttributeAccessor.set(filter,fields.get(0),identifiable.getId());
                return (E) filter;
            }
        }
        return null;
    }
}
