package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.table.JTableModel;
import com.aw.swing.mvp.grid.CellColorChanger;
import com.aw.swing.mvp.ui.UIColorConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class JLabelLinkCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

    private CellColorChanger cellColorChanger;
    ColumnInfo columnInfo;
    private boolean tableEditable =false;
    private Color customBackground;


    public JLabelLinkCellRenderer() {
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel  renderer = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        configure(renderer);
        if (!isSelected){
            Color bkgColor = Color.white;
            if (customBackground!=null){
                bkgColor =customBackground;
            }else{
                if (cellColorChanger != null) {
                    cellColorChanger.setDefaultColor(bkgColor);
                    Object obj = ((JTableModel)table.getModel()).getRowAt(row);
                    bkgColor = cellColorChanger.getBackGround(obj, row, columnInfo.getFieldName());
                }
            }
            if (tableEditable){
                bkgColor = UIColorConstants.BKG_CELL_DISABLED;
            }
            setBackground(bkgColor);
        }else{
            if (tableEditable){
                setBackground(UIColorConstants.BKG_CELL_SELECTED_DISABLED);
            }
        }
        return this;
    }

    public static void configure(JLabel renderer) {
        String text = renderer.getText();
        if (text==null) text = "";
        if (text.startsWith("<html><u>"))
            ;
        else
            text = "<html><u>"+text+"</u></html>";
        renderer.setText(text);
        renderer.setForeground(Color.blue);
        renderer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public CellColorChanger getCellColorChanger() {
        return cellColorChanger;
    }

    public void setCellColorChanger(CellColorChanger cellColorChanger) {
        this.cellColorChanger = cellColorChanger;
    }


    public void setColumnInfo(ColumnInfo columnInfo) {
        this.columnInfo = columnInfo;
    }

    public void setTableEditable(boolean tableEditable) {
        this.tableEditable = tableEditable;
    }

    public void setCustomBackground(Color customBackground) {
        this.customBackground = customBackground;
    }
}