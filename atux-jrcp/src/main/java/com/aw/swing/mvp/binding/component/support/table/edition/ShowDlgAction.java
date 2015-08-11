package com.aw.swing.mvp.binding.component.support.table.edition;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: gmc
 * Date: 22/07/2009
 */
public class ShowDlgAction extends AbstractAction {
    CellEditorWithPopup cellEditorWithPopup;

    public ShowDlgAction(CellEditorWithPopup cellEditorWithPopup) {
        this.cellEditorWithPopup = cellEditorWithPopup;
    }

    public void actionPerformed(ActionEvent e) {
        cellEditorWithPopup.showPopup();
    }
}
