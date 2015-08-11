package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.swing.mvp.action.AWInternaContext;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.table.JTableModel;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Julio C. Macavilca
 * Date: 07/06/2009
 * Time: 04:38:17 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AWAbstractCellEditor<Cmp extends Component> extends AbstractCellEditor implements TableCellEditor {// Information for editing cell
    protected JTable jTable;
    protected ColumnInfo columnInfo;
    protected Object row;
    protected Object oldValue;
    protected Cmp delegate;
    protected int rowIdx;
    protected int colIdx;

    protected abstract Object getDelegateValue();
    protected abstract void setDelegateValue(Object value);

    protected AWAbstractCellEditor(ColumnInfo columnInfo) {
        this.columnInfo = columnInfo;
    }

    protected void changeValue(AWTEvent actionEvent){
        Object newValue = getDelegateValue();
        if (jTable.isCellEditable(rowIdx, colIdx)){
            columnInfo.executeChangeValueListener(row, newValue, oldValue);
            columnInfo.onActionPerformed(jTable, actionEvent,row, newValue);
        }
        oldValue = newValue;
    }
    public Object getCellEditorValue() {
        return getDelegateValue();
    }
    protected  void onCellEditingEnd() {
        AWInternaContext.instance().cellEditingEnd();
    }
    protected  void onCellEditingStart() {
        AWInternaContext.instance().cellEditingStart();
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        this.jTable = table;
        JTableModel jTableModel = (JTableModel) table.getModel();
        this.row = jTableModel.getRowAt(row);
        this.oldValue = value;
        this.rowIdx = row;
        this.colIdx = column;
        setDelegateValue(value);
        onCellEditingStart();
        return delegate;
    }


    @Override
    public boolean stopCellEditing() {
        onCellEditingEnd();
        jTable=null;
//        columnInfo = null;
        row = null;
        oldValue = null;
        this.rowIdx = -1;
        this.colIdx = -1;
        return super.stopCellEditing();
    }


    @Override
    public void cancelCellEditing() {
        onCellEditingEnd();
        jTable=null;
//        columnInfo = null;
        row = null;
        oldValue = null;
        this.rowIdx = -1;
        this.colIdx = -1;
        super.cancelCellEditing();
    }
}
