package com.aw.swing.mvp.binding.component.support.table.edition;

/**
 * User: gmc
 * Date: 22/07/2009
 */
public interface CellEditorWithPopup {
    public static final String SHOW_DLG = "ShowDlg";
    public void showPopup();
    public void dlgSaveAction();
    public void dlgCancelAction();


}
