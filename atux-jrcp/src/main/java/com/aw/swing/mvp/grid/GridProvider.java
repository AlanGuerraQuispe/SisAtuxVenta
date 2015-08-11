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

import com.aw.core.domain.AWBusinessException;
import com.aw.core.format.Formatter;
import com.aw.support.collection.ListUtils;
import com.aw.support.reflection.AttributeAccessor;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.component.BndSJTable;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.MainAuditColumn;
import com.aw.swing.mvp.binding.component.support.table.JTableModel;
import com.aw.swing.mvp.binding.component.support.table.function.GridFunction;
import com.aw.swing.mvp.focus.ConcurrentFocusManager;
import com.aw.swing.mvp.focus.FocusZone;
import com.aw.swing.mvp.focus.GridFocusZone;
import com.aw.swing.mvp.grid.filter.FilterManager;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gmateo
 *         31/01/2005
 */
public class GridProvider {
    protected final Log LOG = LogFactory.getLog(getClass());
    /**
     * Represents the number of the grid in one page.
     * The values could be 0,1,2,.. so on
     */
    private int gridIndex = 0;

    private Presenter presenter;

    /**
     * It is in charge of managing the table
     */
    private GridManager gridManager;
    /**
     * It is in charge of providing all the related info for the table
     */
    private GridInfoProvider gridInfoProvider;

    /**
     * It is in charge of manage the focus related issues
     */
    private GridFocusZone focusZone;

    private GridValidator validator = new GridValidator(this);
    /**
     * Represents the table
     */
    private BndSJTable bndSJTable;
    private JTable jTable = null;
    private EditingRowPolicy editingRowPolicy;
    /**
     * Point out if the grid must be populated at the beggining
     */
    private boolean populateAtBeginning = true;

    private boolean resetSelectionOnFocusLost = false;

    private boolean refreshAbortable = false;

    private boolean showTotalRegistros = false;

    private boolean createAutoSortCols = true;


    /**
     * Dependent grids for this grid provider
     */
    private List dependentGrids = new ArrayList();

    private GridProvider masterGridProvider;

    /**
     * Processor of the dependent grids
     */
    private DependentGridManager dpdGridManager;

    private List cache = new ArrayList();
    private RowColorBkgChanger rowColorBkgChanger;
    private CellColorChanger cellColorChanger;

    private FilterManager filterManager;

    private GridOperatorContainer operatorContainer;

    protected int fixedColumns = 0;

    /**
     * If it is true means that the records are already sorted so it is not needed to sort them again
     */
    private boolean alwaysReloadColumnInfo = false;

    protected GridFunction gridFunction;

    public GridProvider(GridInfoProvider gridInfoProvider) {
        this.gridInfoProvider = gridInfoProvider;
        dpdGridManager = new DependentGridManager(this);
        filterManager = new FilterManager(this);
    }

    /**
     * Init the grid.
     */
    public void init() {
        createGrid();
    }

    public void setValueToJComponentAtBeginning() {
        if (populateAtBeginning) {
            bndSJTable.setValueToJComponent(presenter.getBackBean());
        } else {
            bndSJTable.cleanJComponentValues();
        }
    }

    private void createGrid() {
        createBndSJTable();
        createGridManager();
        addFocusZone();
        focusZone.setGridManager(gridManager);
        if (operatorContainer != null) {
            operatorContainer.setBndSJTable(bndSJTable);
            bndSJTable.getJTable().getModel().addTableModelListener(operatorContainer.getTableModelListener());
            bndSJTable.setTableModelListener(operatorContainer.getTableModelListener());
        }
        bndSJTable.setAlwaysReloadColumnInfo(alwaysReloadColumnInfo);
        if (editingRowPolicy != null) {
            ((JTableModel) bndSJTable.getJTable().getModel()).setEditingRowPolicy(editingRowPolicy);
        }

        if (isEditable()) {
            TableKeyManager.decorate(bndSJTable.getJTable());
            jTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        }
    }


    private void createBndSJTable() {
        JTable jTable = getJTable();
        bndSJTable = new BndSJTable(jTable, gridInfoProvider.getColumnInfo(), gridInfoProvider.getListProvider(presenter));
        bndSJTable.setReadOnly(presenter.isReadOnly());
        bndSJTable.setScrollable(isScrollable);
        bndSJTable.setFixedWidth(fixedWidth);
        bndSJTable.setFixedColumns(fixedColumns);
        bndSJTable.setPresenter(presenter);
        bndSJTable.setGridIndex(gridIndex);
        bndSJTable.setGridFunction(gridFunction);
        bndSJTable.setRefreshAbortable(refreshAbortable);
        bndSJTable.setCreateAutoSortCols(createAutoSortCols);
        if (rowColorBkgChanger != null) {
            bndSJTable.setRowColorBkgChanger(rowColorBkgChanger);
        }
        if (cellColorChanger != null) {
            bndSJTable.setCellColorChanger(cellColorChanger);
        }
        bndSJTable.setEditingRowPolicy(editingRowPolicy);

        registerRowSelecionListener();

        bndSJTable.init();
    }

    private void registerRowSelecionListener() {
        RowSelectionListener rowSelectionListener = new RowSelectionListener() {
            public void onSelectedRow(int selectedIndex, Object selectedRow) {
                gridManager.setLastSelectedRow(selectedIndex);
                gridInfoProvider.onSelectedRow(selectedRow);
                dpdGridManager.onSelectedRow(selectedIndex, selectedRow);
            }

            public void onClearSelectedRow() {
                gridInfoProvider.onClearSelectedRow();
                dpdGridManager.onClearSelectedRow();
            }

        };
        bndSJTable.registerRowSelectionListener(rowSelectionListener);
    }

    private void createGridManager() {
        gridManager = new GridManager(presenter.getViewMgr().getIpView(), bndSJTable);
        gridManager.setShowTotalRegistros(showTotalRegistros);
        gridManager.setGridIndex(gridIndex);
        gridManager.setResetSelectionOnFocusLost(resetSelectionOnFocusLost);
        gridManager.init();

    }

    /**
     * Add the Grid focus zone to the presenter
     */
    private void addFocusZone() {
        focusZone = new GridFocusZone(presenter, gridIndex);
        presenter.addFocusZone(focusZone);
    }

    public void addDependentFocusZone(FocusZone dependentFocusZone) {
        focusZone.addDependentFocusZone(dependentFocusZone);
        dependentFocusZone.setMasterFocusZone(focusZone);
    }

    /**
     * Refresh the content of the grid
     */
    public void refresh(Object param) {
        if (alwaysReloadColumnInfo) {
            bndSJTable.setColumnsInfo(gridInfoProvider.getColumnInfo());
        }
        bndSJTable.refresh(param);
    }

    public void refreshInMemory(List values) {
        if (alwaysReloadColumnInfo) {
            bndSJTable.setColumnsInfo(gridInfoProvider.getColumnInfo());
        }
        bndSJTable.refreshInMemory(values);
    }


    /**
     * Clear the grid
     */
    public void clear() {
        if (bndSJTable != null) {
            bndSJTable.cleanJComponentValues();
        }
    }

    /**
     * Add listeners to the grid
     */
    public BndSJTable getBndSJTable() {
        return bndSJTable;
    }

    /**
     * Return the table values
     *
     * @return
     */
    public List getValues() {
        return bndSJTable.getValues();
    }

    /**
     * Return a current selected row
     *
     * @return
     */
    public Object getSelectedRow() {
        return bndSJTable.getSelectedRow();
    }

    /**
     * Return a current selected row
     *
     * @return
     */
    public int getSelectedRowIdx() {
        return bndSJTable.getSelectedRowIdx();
    }

    public List getSelectedRows() {
        return bndSJTable.getSelectedRows();
    }

    public List<Integer> getSelectedRowsIdx() {
        return bndSJTable.getSelectedRowsIdx();
    }

    public void setSelectedRowsIdx(List<Integer> rowsIdx) {
        bndSJTable.setSelectedRowsIdx(rowsIdx);
    }

    public void setSelectedRowsObj(List items) {
        List<Integer> seleccionadosIdxList = new ArrayList<Integer>();
        List valuesList = getValues();
        for (int i = 0; i < valuesList.size(); i++) {
            Object row = valuesList.get(i);
            if (items.contains(row))
                seleccionadosIdxList.add(i);
        }
        setSelectedRowsIdx(seleccionadosIdxList);
    }


    /**
     * Return a current selected row
     *
     * @return
     */
    public Object getSelectedRowChecked() {
        Object obj = bndSJTable.getSelectedRow();
        if (obj == null) {
            throw new AWBusinessException("No existe ningún registro seleccionado.");
        }
        return obj;
    }

    public GridInfoProvider getGridInfoProvider() {
        return gridInfoProvider;
    }

    /**
     * if true the grid will be populate at beginning
     *
     * @param populateAtBeginning
     * @return
     */
    public GridProvider setPopulateAtBeginning(boolean populateAtBeginning) {
        this.populateAtBeginning = populateAtBeginning;
        return this;
    }


    public GridManager getGridManager() {
        return gridManager;
    }

    public void setGridIndex(int gridIndex) {
        this.gridIndex = gridIndex;
    }

    public GridProvider resetSelectionOnFocusLost() {
        this.resetSelectionOnFocusLost = true;
        return this;
    }


    public JTable getJTable() {
        if (jTable == null) {

            jTable = presenter.getViewMgr().getIpView().getTblGrid(gridIndex);
            if (jTable == null) {
                LOG.debug("Tabla no encontrada para el gridIndex " + gridIndex);
            }

        }

        return jTable;
    }

    public void repaint() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                getJTable().repaint();
                if (hasFixedColumns()) {
                    bndSJTable.getFixedColumnTableMgr().getFixedTable().repaint();
                }
            }
        });
    }

    private boolean hasFixedColumns() {
        return bndSJTable.getFixedColumnTableMgr() != null;
    }

    /**
     * Set a master grid
     *
     * @param masterGdp
     * @return
     */
    public GridProvider dependsOn(GridProvider masterGdp) {
        masterGdp.addDependentGrid(this);
        masterGridProvider = masterGdp;
        setPopulateAtBeginning(false);
        resetSelectionOnFocusLost();
        return this;
    }

    /**
     * Add Dependent grids
     *
     * @param dependentGrid
     */
    private void addDependentGrid(GridProvider dependentGrid) {
        dependentGrids.add(dependentGrid);
    }

    public List getDependentGrids() {
        return dependentGrids;
    }

    public GridFocusZone getFocusZone() {
        return focusZone;
    }


    public int getGridIndex() {
        return gridIndex;
    }

    public boolean hasDependentGrids() {
        return dependentGrids.size() > 0;
    }


    public void setGridInfoProvider(GridInfoProvider gridInfoProvider) {
        this.gridInfoProvider = gridInfoProvider;
    }

    public void removeSelectedRow() {
        bndSJTable.removeSelectedRow();
    }

    public void remove(Object row) {
        bndSJTable.remove(row);
    }

    public void removeAllRows() {
        bndSJTable.removeAllRows();
    }

    public void addAllRows(List rows) {
        bndSJTable.addAllRows(rows);
    }

    public List getCache() {
        return cache;
    }

    public void removeFromCache(Object row) {
        cache.remove(row);
    }

    public void addToCache(Object row) {
        cache.add(row);
    }

    public GridProvider registerCellColorChanger(CellColorChanger cellColorChanger) {
        this.cellColorChanger = cellColorChanger;
        return this;
    }


    public GridProvider registerRowColorChanger(UnaryPredicate condition) {
        rowColorBkgChanger = new RowColorBkgChanger(condition);
        return this;
    }

    public GridProvider registerRowColorChanger(final String attribute, final Object value) {
        UnaryPredicate predicate = new UnaryPredicate() {
            BeanWrapper beanWrapper;

            public boolean test(Object row) {

                beanWrapper = new BeanWrapperImpl(row);

                return value.equals(beanWrapper.getPropertyValue(attribute));
            }
        };
        rowColorBkgChanger = new RowColorBkgChanger(predicate);
        return this;
    }

    public GridProvider validateAtLeastOneRow(String validationMsg) {
        VPGrid vpGrid = new VPGrid(this, validationMsg);
        presenter.getValidatorMgr().registerValidator(vpGrid, VPGrid.VALIDATE_AT_LEAST_ONE_ROW);
        return this;
    }

    public void filter(List criteriaAttrs, String operator) {
        filterManager.filter(criteriaAttrs, operator);
    }

    public void clearFilter() {
        filterManager.clearFilter();
    }

    public void clearCache() {
        cache = new ArrayList();
    }


    public void setValues(List values) {
        JTableModel jTableModel = (JTableModel) getJTable().getModel();
        jTableModel.setValues(values);
    }

    public void requestFocus() {
        ConcurrentFocusManager.getInstance().invokeLaterRequestFocus("Grid Request Focus:", bndSJTable.getJTable());
    }

    public GridOperatorContainer getOperatorContainer() {
        if (operatorContainer == null)
            operatorContainer = new GridOperatorContainer();
        return operatorContainer;
    }

    /**
     * Set the configuration of the column info setting
     *
     * @param alwaysReloadColumnInfo : if it is true then always the column info will be reloaded
     *                               otherwise not. Default value is false.
     */
    public void setAlwaysReloadColumnInfo(boolean alwaysReloadColumnInfo) {
        this.alwaysReloadColumnInfo = alwaysReloadColumnInfo;
    }


    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public boolean isEditable() {
        return bndSJTable.isEditable();
    }

    public void removeEditor() {
        JTable jTable = bndSJTable.getJTable();
        if (jTable.isEditing()) {
            jTable.editingStopped(new ChangeEvent(jTable));
        }
        if (hasFixedColumns()) {
            JTable fixedTable = bndSJTable.getFixedColumnTableMgr().getFixedTable();
            if (fixedTable != null && fixedTable.isEditing()) {
                fixedTable.editingStopped(new ChangeEvent(fixedTable));
            }
        }
    }

    public GridProvider setEditingRowPolicy(EditingRowPolicy editingRowPolicy) {
        this.editingRowPolicy = editingRowPolicy;
        return this;
    }

    public void recalcSecuencial() {
        if (gridInfoProvider.existSequentialColumn()) {
            List values = getValues();
            if (values != null && values.size() > 0) {
                GridInfoProvider gridInfoProvider = getGridInfoProvider();
                ColumnInfo sequentialCol = gridInfoProvider.getSequentialColumnInfo();
                String seqFieldName = sequentialCol.getFieldName();
                try {
                    Formatter formatter = sequentialCol.getSequentialFormatter();
                    if (formatter != null) {
                        ListUtils.setSequentialInColumn(values, seqFieldName, formatter);
                        return;
                    }
                    Class clazz = AttributeAccessor.getField(values.get(0).getClass(), seqFieldName).getType();
                    if (clazz == String.class) {
                        ListUtils.setSequentialInColumn(values, seqFieldName, true);
                    } else {
                        ListUtils.setSequentialInColumn(values, seqFieldName);
                    }
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }

    private boolean isScrollable = false;

    public GridProvider setAsScrollable() {
        isScrollable = true;
        return this;
    }


    private boolean fixedWidth = false;

    public GridProvider setAsFixedWidth() {
        fixedWidth = true;
        return this;
    }


    public GridProvider enforceRowUniqueness(String msg) {
        validator.enforceRowUniqueness(msg);
        return this;
    }

    public GridProvider setFixedColumns(int fixedColumns) {
        this.fixedColumns = fixedColumns;
        return this;
    }

    public void validate() {
        validator.validate();
    }


    public MainAuditColumn getMainAuditColumn() {
        return bndSJTable.getMainAuditColumn();
    }

    public void enableEditionOnFirstCell(int rowIdx) {
        bndSJTable.setSelectedRow(rowIdx);
        int column = bndSJTable.getFirstEditableColumn();
        getJTable().requestFocusInWindow();
        getJTable().editCellAt(rowIdx, column);
    }

    public boolean hasSelectorColumn() {
        return bndSJTable.getSelectorColumn() != null;
    }

    public int getFixedColumns() {
        return fixedColumns;
    }

    public EditingRowPolicy getEditingRowPolicy() {
        return editingRowPolicy;
    }

    public void cleanSelectedRows() {
        bndSJTable.cleanSelectedRows();
    }

    public void setGridFunction(GridFunction gridFunction) {
        this.gridFunction = gridFunction;
        this.gridFunction.setGdp(this);
    }

    public boolean hasMasterGrid() {
        return masterGridProvider != null;
    }

    public GridProvider getMasterGridProvider() {
        return masterGridProvider;
    }

    public GridProvider setRefreshAsAbortable() {
        refreshAbortable = true;
        return this;
    }

    public boolean isCreateAutoSortCols() {
        return createAutoSortCols;
    }

    public void setCreateAutoSortCols(boolean createAutoSortCols) {
        this.createAutoSortCols = createAutoSortCols;
    }

    public GridProvider setShowTotalRegistros(boolean value) {
        showTotalRegistros=value;
        return this;
    }
}
