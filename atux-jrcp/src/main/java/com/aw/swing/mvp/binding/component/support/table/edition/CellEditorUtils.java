package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.core.domain.AWBusinessException;
import com.aw.swing.mvp.action.AWInternaContext;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Julio C. Macavilca
 * Date: 06/06/2009
 * Time: 07:41:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class CellEditorUtils {
    public final static String AW_CELL_EDITOR = "awCellEditor";

    public static File execFileChooser(Component parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.putClientProperty(AW_CELL_EDITOR, Boolean.TRUE);
        int returnVal = fileChooser.showOpenDialog(parent);
        AWInternaContext.instance().cellEditingEnd();
        File file = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();

            Long size = file.length() / (1024 * 1024);

            if (size > 10) {
                throw new AWBusinessException("El archivo adjunto excede los 10 MB");
            }
        }
        return file;
    }
}
