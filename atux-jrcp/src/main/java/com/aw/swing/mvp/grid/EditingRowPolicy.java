package com.aw.swing.mvp.grid;

import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.SelectorColumn;

/**
 * User: gmc
 * Date: 30/04/2009       werwerw
 */
public abstract class EditingRowPolicy<E> {
    public static EditingRowPolicy READONLY_POLICY = new EditingRowPolicy(){
        public boolean isEditable(Object row, String colFieldName) {
            return false;
        }
    };
    ColumnInfo currentColInfo;

    protected boolean isSelectorColumn() {
        return currentColInfo instanceof SelectorColumn;
    }

    public abstract boolean isEditable(E row, String colFieldName);

    public void setCurrentColInfo(ColumnInfo currentColInfo) {
        this.currentColInfo = currentColInfo;
    }
}
