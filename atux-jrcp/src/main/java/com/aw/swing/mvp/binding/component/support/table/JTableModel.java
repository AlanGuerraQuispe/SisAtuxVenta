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
package com.aw.swing.mvp.binding.component.support.table;


import com.aw.core.util.DeleteableList;
import com.aw.support.beans.AWPropertyComparator;
import com.aw.swing.mvp.binding.component.support.ButtonColumn;
import com.aw.swing.mvp.binding.component.support.ChangeValueListener;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.EditingRowPolicy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.util.StringUtils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.net.URL;
import java.util.*;

/**
 * @author gmateo
 *         15/10/2004
 */
public class JTableModel extends AbstractTableModel {
    protected final Log logger = LogFactory.getLog(getClass());

    public static ImageIcon COLUMN_UP;
    public static ImageIcon COLUMN_DOWN;
    public static ImageIcon COLUMN;

    /**
     * Values that will be shown in the JTable
     */
    private List values;
    /**
     * Array of column information
     */
    private ColumnInfo[] columnsInfo;

    EditingRowPolicy editingRowPolicy;

    private int indexSortColumn = -1;
    private boolean sortAsc = true;
    private boolean isReadOnly = false;

    public JTableModel(ColumnInfo[] columnsInfo) {
        this.columnsInfo = columnsInfo;
//        COLUMN_UP = createImageIcon("sortUp.png", "Sort UP");
//        COLUMN_DOWN = createImageIcon("sortDown.png", "Sort Down");
        COLUMN_UP = createImageIcon("sort2Up.png", "Sort UP");
        COLUMN_DOWN = createImageIcon("sort2Down.png", "Sort Down");
        COLUMN = createImageIcon("sort2.png", "");

    }

    public JTableModel(ColumnInfo[] columnsInfo, List lista) {
        this(columnsInfo);
        values = lista;
    }


    /**
     * Returns the name of the column at the given column index.
     * This is used to initialize the table's column header name.
     * Note: this name does not need to be unique; two columnNames in a table
     * can have the same name.<p>
     *
     * @param columnIndex the index of the column
     * @return the name of the column
     * @throws NullPointerException if the optional column names array
     *                              has not been set in the constructor. In this case API users
     *                              must override this method.
     * @see #getColumnCount()
     * @see #getRowCount()
     */
    public String getColumnName(int columnIndex) {
        return columnsInfo[columnIndex].getColumnHeader();
    }

    private ColumnInfo getColumnInfo(String colFieldName) {
        for (int i = 0; i < columnsInfo.length; i++) {
            ColumnInfo columnInfo = columnsInfo[i];
            if (colFieldName.equals(columnInfo.getFieldName())){
                return columnInfo;
            }
        }
        throw new IllegalStateException("The column:<"+colFieldName+"> does not exist in the column info array.");
    }

    public ColumnInfo getColumnInfo(int columnIndex) {
        return columnsInfo[columnIndex];
    }

    /**
     * Return the number of columnNames
     *
     * @return
     */
    public int getColumnCount() {
        return columnsInfo.length;
    }

    /**
     * Return the number of rows
     *
     * @return
     */
    public int getRowCount() {
        return values.size();
    }

    /**
     * Get the value set in specific position.
     * If there is a CVLProvider for this column, this method return the value for this key.
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= values.size()) return null;
        Object object = values.get(rowIndex);
        Object value = columnsInfo[columnIndex].getValue(object, columnIndex, rowIndex);
        return value;
    }

    /**
     * Return the row which has the index sent as parameter
     * @param index
     * @return
     */
    public Object getRowAt(int index) {
        if (values.size() > 0) {
            return values.get(index);
        }
        return null;
    }

    /**
     * Add a row to the list that is shown
     *
     * @param row
     */
    public void addRow(Object row) {
        int valuesSize = values.size();
        values.add(row);
        fireTableRowsInserted(valuesSize, valuesSize);
    }
    /**
     * Add a row to the list that is shown
     *
     * @param row
     */
    public void addRow(int idx,Object row) {
        values.add(idx,row);
        fireTableRowsInserted(idx, idx);
    }

    /**
     * Add a list to the list that is shown
     */
    public void addAllRows(List rows) {
        int valuesSize = values.size();
        values.addAll(rows);
        fireTableRowsInserted(valuesSize, valuesSize + rows.size());
    }

    /**
     * Update the value of a row
     *
     * @param index
     * @param row
     */
    public void updateRow(int index, Object row) {
        values.remove(index);
        values.add(index, row);
        fireTableRowsUpdated(index, index);
    }

    /**
     * Remove the selected row
     *
     * @param rowIndex
     */
    public void removeRow(int rowIndex) {
        values.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }


    public Class getColumnClass(int c) {
        if (getValueAt(0, c) == null) return String.class;
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        if (isReadOnly) return false;
        boolean rowIsReadOnly = false;
        if (editingRowPolicy != null) {
            editingRowPolicy.setCurrentColInfo(columnsInfo[col]);
            Object rowObject = values.get(row);
            rowIsReadOnly = !editingRowPolicy.isEditable(rowObject, columnsInfo[col].getFieldName());
        }
        return !rowIsReadOnly && (columnsInfo[col].isEditable()|| columnsInfo[col] instanceof ButtonColumn);
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        if (values ==null || values.size()==0){
            return;
        }
        boolean editable = isCellEditable(row, col);
        if (logger.isDebugEnabled()) {
            logger.debug("Setting value at Row:" + row + " Col:" + col + " editable:" + editable + " Value:" + value);
        }
        if (editable) {
            Object object = values.get(row);
            columnsInfo[col].setValue(object, value, row);
            if (values instanceof DeleteableList) {
                ((DeleteableList) values).addModifiedRow(object);
            }
            fireTableCellUpdated(row, col);
            List<ChangeValueListener> changeValueListenerList = columnsInfo[col].getChangeValueListenerList();
            for (ChangeValueListener changeValueListener : changeValueListenerList) {
                if (changeValueListener != null) {
                    String[] modifiedColFieldNames = changeValueListener.getModifiedCols();
                    int[] modifiedCols = getColIdxFor(modifiedColFieldNames);
                    for (Integer modifiedCol : modifiedCols) {
                        fireTableCellUpdated(row, modifiedCol.intValue());
                    }
                }
            }
            if(columnsInfo[col].isFillEmptyWithLastValue() && !settingAuto){
                settingAuto =true;
                columnsInfo[col].setFillingEmptyWithLastValueInProcess(true);
                for (int i=0;i< getValues().size();i++){
                    if (i!=row){
                        Object val = getValueAt(i,col);
                        if (val==null || ((val instanceof String) && !StringUtils.hasText((String) val) ) ){
                            setValueAt(value,i,col);
                        }
                    }
                }
                settingAuto = false;
                columnsInfo[col].setFillingEmptyWithLastValueInProcess(false);
            }
        }
    }
    boolean settingAuto = false;

    private int[] getColIdxFor(String[] modifiedColFieldNames) {
        int[] modifiedCols = new int[modifiedColFieldNames.length];
        for (int i = 0; i < modifiedColFieldNames.length; i++) {
            String colFieldName = modifiedColFieldNames[i];
            ColumnInfo columnInfo = getColumnInfo(colFieldName);
            modifiedCols[i] = columnInfo.getIdx();
        }
        return modifiedCols;  
    }

    /**
     * Sort the shown values
     */
    public void sort() {
        Collections.sort(values);
        fireTableDataChanged();
    }

    /**
     * Sort the shown values using the comparator sent as a parameter
     *
     * @param comparator
     */
    public void sort(Comparator comparator) {
        if (comparator == null) {
            return;
        }
        Collections.sort(values, comparator);
        fireTableDataChanged();
    }

    /**
     * Sort the shown values using the comparator sent as a parameter
     */
    public void sortByColumn(int indexColumn) {
        if (indexSortColumn == indexColumn) {
            sortAsc = !sortAsc;
        } else {
            indexSortColumn = indexColumn;
        }
        ColumnInfo colInfo = columnsInfo[indexColumn];
        final String fieldName = colInfo.getFieldName();
        MutableSortDefinition mutableSortDefinition = new MutableSortDefinition(fieldName, true, sortAsc);
        AWPropertyComparator propertyComparator = new AWPropertyComparator(
                mutableSortDefinition, colInfo.getFormatter());
        Collections.sort(values, propertyComparator);
        fireTableDataChanged();
    }


    public List getValues() {
        if (values != null) {
            return values;
        }
        return new ArrayList();
    }

    public void setValues(List values) {
        this.values = values;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                fireTableDataChanged();
            }
        });
    }

    public Icon getColumnIcon(int index) {
        if (indexSortColumn == index) {
            return sortAsc ? COLUMN_UP : COLUMN_DOWN;
        }
        return COLUMN;
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     *
     * @param path
     * @param description
     * @return
     */
    protected ImageIcon createImageIcon(String path,
                                        String description) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void setEditingRowPolicy(EditingRowPolicy editingRowPolicy) {
        this.editingRowPolicy = editingRowPolicy;
    }

    public void removeAllRows() {
        Iterator iterator = values.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            iterator.remove();
        }
        fireTableDataChanged();
    }

    public void setReadOnly(boolean readOnly) {
        isReadOnly = readOnly;
    }
    
    public boolean hasEditingRowPolicy(){
        return editingRowPolicy!=null;
    }
}

