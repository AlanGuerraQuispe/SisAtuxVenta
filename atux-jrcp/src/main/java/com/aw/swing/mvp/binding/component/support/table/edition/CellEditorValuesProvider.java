package com.aw.swing.mvp.binding.component.support.table.edition;

/**
 * User: gmc
 * Date: 18/05/2009
 */
public abstract class CellEditorValuesProvider<E> {

    public abstract Object getTableCellEditorValues(E rowObj, int rowIdx);
}