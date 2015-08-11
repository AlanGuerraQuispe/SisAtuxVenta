package com.aw.swing.mvp.action.types;

import com.aw.core.exception.AWSystemException;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.table.JTableModel;
import com.aw.swing.mvp.binding.component.support.table.header.TextAndIcon;
import com.aw.swing.mvp.grid.GridProvider;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * User: gmc
 * Date: 21-nov-2007
 */
public class SortByColumnAction extends Action {
    private int colSortIndex = -1;

    public SortByColumnAction() {
        needVisualComponent = false;
        noExecValidation();
    }

    protected Object executeIntern() throws Throwable {
        JTable jTable = getGridProvider().getJTable();
        JTableModel tableModel = (JTableModel) jTable.getModel();
        sortByColumn(tableModel);
        GridProvider gdp =  getGridProvider();
        gdp.cleanSelectedRows();        
        repaintTableHeader(jTable, tableModel);
        return null;
    }

    private void sortByColumn(final JTableModel tableModel) {
        if (SwingUtilities.isEventDispatchThread()){
            tableModel.sortByColumn(colSortIndex);
        }else{
            try {
                SwingUtilities.invokeAndWait(new Runnable(){
                    public void run(){
                        tableModel.sortByColumn(colSortIndex);
                    }
                });
            } catch (Throwable e) {
                throw new AWSystemException("Problems sorting using:"+this,e);
            }
        }


    }

    private void repaintTableHeader(final JTable jTable, JTableModel tableModel) {
        final GridProvider gdp =  getGridProvider();
        final  boolean existFixedColumns= gdp.getBndSJTable().getFixedColumnTableMgr()!=null;
        ColumnInfo[] columnInfos = gdp.getBndSJTable().getColumnsInfo();
        TableColumnModel colModel = jTable.getColumnModel();
        TableColumnModel colModelFixed = !existFixedColumns? null: gdp.getBndSJTable().getFixedColumnTableMgr().getFixedTable().getColumnModel();
        int fixedColumns = gdp.getFixedColumns();
        TableColumn column;
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            if (i < fixedColumns){
                column = colModelFixed.getColumn(i);
            }else{
                column = colModel.getColumn(i-fixedColumns);
            }
            int index = column.getModelIndex();
            column.setHeaderValue(new TextAndIcon(columnInfos[index].getColumnHeader(), tableModel.getColumnIcon(index)));
        }
        if (SwingUtilities.isEventDispatchThread()){
            repaintTables(existFixedColumns, gdp, jTable);
        }else{
            try {
                SwingUtilities.invokeAndWait(new Runnable(){
                    public void run(){
                        repaintTables(existFixedColumns, gdp, jTable);
                    }
                });
            } catch (Throwable e) {
                throw new AWSystemException("Problems repaint the tables in:"+this,e);
            }
        }

    }

    private void repaintTables(boolean existFixedColumns, GridProvider gdp, JTable jTable) {
        jTable.getTableHeader().repaint();
        if(existFixedColumns){
            gdp.getBndSJTable().getFixedColumnTableMgr().getFixedTable().getTableHeader().repaint();
        }
    }

    public int getColSortIndex() {
        return colSortIndex;
    }

    public void setColSortIndex(int colSortIndex) {
        this.colSortIndex = colSortIndex;
    }
}
