package com.table;

import javax.swing.table.AbstractTableModel;

/**
 * User: gmc
 * Date: 17/04/2009
 */
public class ColorTableModel extends AbstractTableModel {
    Object rowData[][] = {

            {"1", "ichi - \u4E00", Boolean.TRUE, ""},
            {"2", "ni - \u4E8C", Boolean.TRUE, ""},
            {"3", "san - \u4E09", Boolean.FALSE, ""},
            {"4", "shi - \u56DB", Boolean.TRUE, ""},
            {"5", "go - \u4E94", Boolean.FALSE, ""},
    };
    String columnNames[] = {"English", "Japanese", "Boolean", "Color"};

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getRowCount() {
        return rowData.length;
    }

    public Object getValueAt(int row, int column) {
        return rowData[row][column];
    }

    public Class getColumnClass(int column) {
        return (getValueAt(0, column).getClass());
    }

    public void setValueAt(Object value, int row, int column) {
        rowData[row][column] = value;
    }

    public boolean isCellEditable(int row, int column) {
        return (column != 0);
    }
}
