package com.aw.swing.mvp.binding.component.support.table;


import com.aw.swing.mvp.binding.component.support.table.header.JCheckBoxCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.Enumeration;


public class FixedColumnTableDecorator{

    public static FixedColumnTableMgr decorate(JTable jtable,int fixedColumns){
        return decorate(jtable,fixedColumns,false);
    }
    public static FixedColumnTableMgr decorate(JTable jtable,int fixedColumns,boolean useColSpan){
        JScrollPane jScrollPane = (JScrollPane) jtable.getParent().getParent();
        FixedColumnTableMgr mgr = new FixedColumnTableMgr(jScrollPane,fixedColumns);
        mgr.setUseColSpan(useColSpan);
        mgr.init();
        TableColumnModel columnModel= mgr.getFixedTable().getColumnModel();
        Enumeration columns =columnModel.getColumns();
        while (columns.hasMoreElements()) {
            TableColumn tableColumn = (TableColumn) columns.nextElement();
            TableCellRenderer tableCellRenderer = tableColumn.getHeaderRenderer();
            if (tableCellRenderer instanceof JCheckBoxCellHeaderRenderer){
                JCheckBoxCellHeaderRenderer headerRenderer = (JCheckBoxCellHeaderRenderer) tableCellRenderer;
                headerRenderer.setJtable(mgr.getFixedTable());
            }
        }
        return mgr;
    }
}

