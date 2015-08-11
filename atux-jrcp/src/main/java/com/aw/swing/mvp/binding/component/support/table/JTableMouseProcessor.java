package com.aw.swing.mvp.binding.component.support.table;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.*;
import com.aw.swing.mvp.action.types.SortByColumnAction;
import com.aw.swing.mvp.binding.component.BndSJTable;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.SelectorColumn;
import com.aw.swing.mvp.binding.component.support.table.header.JCheckBoxCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * User: gmc
 * Date: 21-nov-2007
 */
public class JTableMouseProcessor {
    private BndSJTable bndSJTable;
    private Presenter presenter;
    private int gridIndex;
    private JTable jTable;
    private boolean isInFixedTable=false;

    public JTableMouseProcessor(Presenter presenter, BndSJTable bndSJTable, int gridIndex) {
        this.bndSJTable = bndSJTable;
        this.presenter = presenter;
        this.gridIndex = gridIndex;
        this.jTable = bndSJTable.getJTable(); 
    }

    public void processMouseEventsOnHeader(MouseEvent e) {
        TableColumn tableColumn = getTableColumn(e);
        int colIndex = tableColumn.getModelIndex();
        if ((colIndex < 0)||  (tableColumn.getHeaderRenderer() instanceof JCheckBoxCellHeaderRenderer)){
            return;
        }
        SortByColumnAction sortByColumnAction = (SortByColumnAction) presenter.getActionRsr().getAction(ActionIdentifier.getActionIdentifier(ActionNames.ACTION_SORT_BY_COLUMN,gridIndex));
        if (sortByColumnAction!=null){
            sortByColumnAction.setColSortIndex(colIndex);
            ActionManager.instance().executeAction(sortByColumnAction);
        }
    }

    private TableColumn getTableColumn(MouseEvent e){
        TableColumnModel colModel = jTable.getColumnModel();
        int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
        return colModel.getColumn(columnModelIndex);
    }


    public void processMouseEventsOnBody(MouseEvent e) {
        Point origin = e.getPoint();
        int row = jTable.rowAtPoint(origin);
        int column = jTable.columnAtPoint(origin);
        if (row == -1 || column == -1) {
            return; // no cell found
        }
        int currentTableColumn = column;
        if (!isInFixedTable){
            column += bndSJTable.getFixedColumns();
        }
        JTableModel jTableModel = (JTableModel) jTable.getModel();
        ColumnInfo columnInfo = jTableModel.getColumnInfo(column);
        if (jTableModel.isCellEditable(row, column)){
            if (e.getClickCount() == 1) {
                jTable.editCellAt(row, currentTableColumn);
            }
            if (columnInfo instanceof SelectorColumn){
                return;
            }
            Object bean = jTableModel.getRowAt(row);
            Object newValue = jTableModel.getValueAt(row, column);
            columnInfo.onActionPerformed(jTable, e, bean, newValue);
        }
        Object bean = jTableModel.getRowAt(row);
        Object newValue = jTableModel.getValueAt(row, column);
        columnInfo.onClick(jTable, e, bean, newValue);

        if (bndSJTable.isEditable()){
            return;            
        }
        ActionResolver actionResolver = presenter.getActionRsr();
        // Doble click on body
        Action action = null;
        if (e.getClickCount() == 2) {
            action = actionResolver.getDefaultActionFor(presenter.getGridProvider(gridIndex));
            if (action == null) {
//                ActionIdentifier actionIdentifier = ActionIdentifier.getActionIdentifier(ActionDialog.KEY_F3, jTable);
//                action = actionResolver.getAction(actionIdentifier);
            }
        } else if (e.getClickCount() == 1) {
//            if (column == 0) {
//                    action = actionResolver.getActionToBeExecutedOnClickFor(presenter.getGridProviderFor(jTable));
//            }else{
//                GridProvider gdp = presenter.getGridProviderFor(jTable);
//                gdp.getBndSJTable().setSelectedRow(row);
//            }
        }
        if (action != null && action.isEnabled()) {
            // This is in order to avoid that the jtable inner component receives the mouse event.
            TableCellEditor cellEditor = jTable.getCellEditor(row, column);
            if (cellEditor instanceof DefaultCellEditor) {
                ((DefaultCellEditor) cellEditor).setClickCountToStart(1000);
            }
            ActionManager.instance().executeAction(action);
        }
    }

    public void setJTable(JTable jTable) {
        this.jTable = jTable;
    }

    public void setInFixedTable(boolean inFixedTable) {
        isInFixedTable = inFixedTable;
    }
}
