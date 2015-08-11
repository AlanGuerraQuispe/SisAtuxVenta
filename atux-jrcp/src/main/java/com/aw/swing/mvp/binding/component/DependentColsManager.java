package com.aw.swing.mvp.binding.component;

import com.aw.core.cache.dropdown.DropDownRow;
import com.aw.core.cache.dropdown.DropDownRowImpl;
import com.aw.swing.mvp.binding.component.support.ChangeValueListener;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.table.edition.JComboBoxCellEditor;
import com.aw.swing.mvp.binding.component.support.table.edition.JComboBoxDependentCellEditor;
import org.springframework.util.StringUtils;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 02/05/2009
 */
public class DependentColsManager {
    BndSJTable bndSJTable;

    public DependentColsManager(BndSJTable bndSJTable) {
        this.bndSJTable = bndSJTable;
    }

    public void init() {
        ColumnInfo[] colsInfo = bndSJTable.getColumnsInfo();
        final TableColumnModel columnModel = bndSJTable.getJTable().getColumnModel();
        for (int i = 0; i < colsInfo.length; i++) {
            final ColumnInfo columnInfo = colsInfo[i];
            if (columnInfo.hasDependentCmps()) {
                final TableColumn col = columnModel.getColumn(i);
                TableCellEditor cellEditor = col.getCellEditor();
                if (cellEditor == null) {
                    continue;
                }
                if ((cellEditor instanceof JComboBoxCellEditor) ||
                        (cellEditor instanceof JComboBoxDependentCellEditor)) {

                    columnInfo.addChangeValueListener(new ChangeValueListener() {
                        public String[] onChangeValue(Object rowObj, Object oldwValue, Object newValue) {
                            System.out.println(columnInfo.getFieldName() + " Se modificó la columna:<o,n>:<" + oldwValue + "," + newValue + ">");
                            int rowIdx = bndSJTable.getJTable().getSelectedRow();
                            final JComponent jComponent = (JComponent) col.getCellEditor().getTableCellEditorComponent(bndSJTable.getJTable(), newValue, false, rowIdx, columnInfo.getIdx());
                            if (rowIdx == -1) {
                                return new String[]{};
                            }
                            JComboBox masterCmb = (JComboBox) jComponent;
                            DropDownRow dropDownRow = (DropDownRow) masterCmb.getSelectedItem();
                            if (dropDownRow == null) return null;
                            int colIdx = 0;
                            List<String> dependentColsFieldName = new ArrayList();
                            for (ColumnInfo deptColInfo : columnInfo.getDependentCmps()) {
                                TableColumn col = columnModel.getColumn(deptColInfo.getIdx());
                                CellEditor cellEditor = col.getCellEditor();
                                dependentColsFieldName.add(deptColInfo.getFieldName());
                                Object row = bndSJTable.getValues().get(rowIdx);
                                if (cellEditor instanceof JComboBoxDependentCellEditor) {
                                    clearDependentDropDown(rowIdx, row, deptColInfo);
                                } else if (dropDownRow instanceof DropDownRowImpl) {
                                    DropDownRowImpl dd = (DropDownRowImpl) dropDownRow;
                                    Object other = dd.getOther();
                                    Object objValue = other;
                                    if (other instanceof String) {
                                        String otherValues = (String) other;
                                        String[] valuesList = StringUtils.delimitedListToStringArray(otherValues, "*");
                                        if (valuesList != null && valuesList.length > 0) {
                                            objValue = valuesList[colIdx];
                                        }
                                    }
                                    deptColInfo.setValue(row, objValue, 0);
                                    colIdx++;
                                } else {
                                    System.out.println("no se ejecuto codigo en columnas dependientes");
                                }
                            }
                            System.out.println(columnInfo.getFieldName() + " Terminó");
                            String[] arrayCols = new String[dependentColsFieldName.size()];
                            for (int j = 0; j < arrayCols.length; j++) {
                                arrayCols[j] = dependentColsFieldName.get(j);
                            }
                            return arrayCols;
                        }
                    });
                } else {
//                    throw new IllegalStateException("Component not supported in dependencies");
                }
            }
        }
    }

    private void clearDependentDropDown(int rowIdx, Object row, ColumnInfo deptColInfo) {
        deptColInfo.setValue(row, null, rowIdx);
        deptColInfo.clearCellDropDownsAt(rowIdx);
        if (deptColInfo.hasDependentCmps()) {
            List<ColumnInfo> dependentColList = deptColInfo.getDependentCmps();
            for (ColumnInfo columnInfo : dependentColList) {
                clearDependentDropDown(rowIdx, row, columnInfo);
            }
        }
        bndSJTable.getJTable().repaint();
    }

}
