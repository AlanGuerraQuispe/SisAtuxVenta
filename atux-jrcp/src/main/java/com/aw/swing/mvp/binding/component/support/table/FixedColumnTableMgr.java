package com.aw.swing.mvp.binding.component.support.table;

import com.aw.swing.mvp.binding.component.BndSJTable;
import com.aw.swing.mvp.binding.component.support.table.header.group.GroupableTableColumnModel;
import com.aw.swing.mvp.binding.component.support.table.header.group.GroupableTableHeader;
import com.aw.swing.mvp.binding.component.support.table.span.CSJTable;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/*
 *  Prevent the specified number of columns from scrolling horizontally in
 *  the scroll pane. The table must already exist in the scroll pane.
 *
 *  The functionality is accomplished by creating a second JTable (fixed)
 *  that will share the TableModel and SelectionModel of the main table.
 *  This table will be used as the row header of the scroll pane.
 *
 *  The fixed table created can be accessed by using the getFixedTable()
 *  method. will be returned from this method. It will allow you to:
 *
 *  You can change the model of the main table and the change will be
 *  reflected in the fixed model. However, you cannot change the structure
 *  of the model.
 */
public class FixedColumnTableMgr implements ChangeListener, PropertyChangeListener {
    private JTable main;
    private JTable fixed;
    private JScrollPane scrollPane;
    private int fixedColumns;
    private boolean useColSpan = false;


    /*
      *  Specify the number of columns to be fixed and the scroll pane
      *  containing the table.
      */
    FixedColumnTableMgr(JScrollPane scrollPane,int fixedColumns) {
        this.scrollPane = scrollPane;
        this.fixedColumns = fixedColumns;
    }

    public void init(){
        main = ((JTable) scrollPane.getViewport().getView());
        main.setAutoCreateColumnsFromModel(false);
        main.addPropertyChangeListener(this);

        //  Use the existing table to create a new table sharing
        //  the DataModel and ListSelectionModel

        if (useColSpan){
            fixed = new CSJTable();
        }else{
            fixed = new JTable();
        }
        main.putClientProperty(BndSJTable.CP_FIXED_TABLE,fixed);
        fixed.putClientProperty(BndSJTable.CP_MAIN_TABLE,main);
        fixed.getTableHeader().setReorderingAllowed(false);

        if (main.getColumnModel() instanceof GroupableTableColumnModel) {
            GroupableTableColumnModel mainColModel = (GroupableTableColumnModel) main.getColumnModel();
            GroupableTableColumnModel gmodel = new GroupableTableColumnModel();
            gmodel.setColumnGroups(mainColModel.getColumnGroups());
            GroupableTableHeader gth= new GroupableTableHeader(gmodel);
            fixed.setColumnModel(gmodel);
            fixed.setTableHeader(gth);
        }

        LookAndFeelManager.setLookAndFeel(fixed);
        fixed.setAutoCreateColumnsFromModel(false);
        fixed.setModel(main.getModel());
        fixed.setSelectionModel(main.getSelectionModel());

        //  Remove the fixed columns from the main table
        //  and add them to the fixed table
        TableColumnModel columnModel = main.getColumnModel();
        for (int i = 0; i < fixedColumns; i++) {
            if (columnModel.getColumnCount() <= 0) {
                break;
            }
            TableColumn column = columnModel.getColumn(0);
            columnModel.removeColumn(column);
            fixed.getColumnModel().addColumn(column);
        }

        //  Add the fixed table to the scroll pane
        fixed.setPreferredScrollableViewportSize(fixed.getPreferredSize());
        scrollPane.setRowHeaderView(fixed);
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixed.getTableHeader());
        // Synchronize scrolling of the row header with the main table
        scrollPane.getRowHeader().addChangeListener(this);
    }

    /*
      *  Return the table being used in the row header
      */
    public JTable getFixedTable() {
        return fixed;
    }
//

    //  Implement the ChangeListener

    public void stateChanged(ChangeEvent e) {
        //  Sync the scroll pane scrollbar with the row header
        JViewport viewport = (JViewport) e.getSource();
        scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
    }
//

    //  Implement the PropertyChangeListener

    public void propertyChange(PropertyChangeEvent e) {
        //  Keep the fixed table in sync with the main table
        if ("selectionModel".equals(e.getPropertyName())) {
            fixed.setSelectionModel(main.getSelectionModel());
        }

        if ("model".equals(e.getPropertyName())) {
            fixed.setModel(main.getModel());
        }
    }

    public void setUseColSpan(boolean useColSpan) {
        this.useColSpan = useColSpan;
    }
}
