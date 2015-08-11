package com.aw.swing.mvp.binding.component.support;

import com.aw.core.domain.AWBusinessException;
import com.aw.swing.mvp.binding.component.support.table.JTableModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 29/04/2009
 */
public class SelectorColumn extends ColumnInfo {

    /**
     * Maintain idx of selected rows
     */
    public List<Integer> selectedRows = new ArrayList<Integer>();

    private boolean selectAll = false;

    public static String COL_NAME = "selector";

    public SelectorColumn() {
        super("", COL_NAME, 20, LEFT);
        isPrintable = false;
        setAsEditable(CHECK_BOX);
        setRptChrs(0);
    }

    @Override
    public Object getValue(Object object, int index, int row) {
        if (selectedRows.indexOf(row) != -1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public void setValue(Object object, Object value, int row) {
        if (vetoableChangeListener != null) {
            Object oldValue = selectedRows.contains(row);
            try {
                vetoableChangeListener.vetoableChange(object, oldValue, value);
            } catch (AWBusinessException e) {
                if (!selectAll) {
                    showErrorMsg(e);
                }
                return;
            }
        }
        Boolean oldValue = Boolean.FALSE;
        if (Boolean.FALSE.equals(value)) {
            if (selectedRows.contains(row)) {
                selectedRows.remove(new Integer(row));
                oldValue = Boolean.TRUE;
                if (existChangeValueListener()) {
                    onChangeValue(object, oldValue, value);
                }
            }
        } else {
            if (!selectedRows.contains(row)) {
                selectedRows.add(row);
                if (existChangeValueListener()) {
                    onChangeValue(object, oldValue, value);
                }
            }
        }
    }

    public void setSelectedRows(List<Integer> selectedRows) {
        this.selectedRows = selectedRows;
    }

    public List<Integer> getSelectedRowsIdx() {
        return selectedRows;
    }

    public void removeSelectedRow(Integer idx) {
        selectedRows.remove(idx);
    }

    public void clearForRefreshGrid() {
        selectedRows = new ArrayList();
    }
    public void clear(JTable jTable) {
        boolean existEditingRowPolicy = existEditingRowPolicy(jTable);
        if (!existChangeValueListener() && !existEditingRowPolicy) {
            selectedRows = new ArrayList();
        } else {
            int tableSize = jTable.getRowCount();
            if (jTable.getModel() instanceof JTableModel) {
                JTableModel jTableModel = (JTableModel) jTable.getModel();
                if (existEditingRowPolicy) {
                    for (int i = 0; i < tableSize; i++) {
                        if (jTableModel.isCellEditable(i, idx)) {
                            setValue(jTableModel.getRowAt(i), Boolean.FALSE, i);
                        }
                    }
                } else {
                    selectAll = true;
                    for (int i = 0; i < tableSize; i++) {
                        setValue(jTableModel.getRowAt(i), Boolean.FALSE, i);
                    }
                    selectAll = false;
                }
            }
        }
    }


    public void selectAll(JTable jTable) {
        int tableSize = jTable.getRowCount();
        boolean existEditingRowPolicy = existEditingRowPolicy(jTable);

        if (vetoableChangeListener == null && !existEditingRowPolicy && !existChangeValueListener()) {
            selectedRows = new ArrayList();
            for (int i = 0; i < tableSize; i++) {
                selectedRows.add(i);
            }
        } else {
            if (jTable.getModel() instanceof JTableModel) {
                JTableModel jTableModel = (JTableModel) jTable.getModel();
                if (existEditingRowPolicy) {
                    for (int i = 0; i < tableSize; i++) {
                        if (jTableModel.isCellEditable(i, idx)) {
                            setValue(jTableModel.getRowAt(i), Boolean.TRUE, i);
                        }
                    }
                } else {
                    selectAll = true;
                    for (int i = 0; i < tableSize; i++) {
                        setValue(jTableModel.getRowAt(i), Boolean.TRUE, i);
                    }
                    selectAll = false;
                }
            }
        }
    }

    private boolean existEditingRowPolicy(JTable jTable) {
        boolean existEditingRowPolicy = false;
        if (jTable.getModel() instanceof JTableModel) {
            JTableModel jTableModel = (JTableModel) jTable.getModel();
            existEditingRowPolicy = jTableModel.hasEditingRowPolicy();
        }
        return existEditingRowPolicy;
    }

}
