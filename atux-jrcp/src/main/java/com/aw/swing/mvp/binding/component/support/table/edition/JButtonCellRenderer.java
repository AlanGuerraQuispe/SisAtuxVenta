package com.aw.swing.mvp.binding.component.support.table.edition;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class JButtonCellRenderer extends JButton implements TableCellRenderer {


    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        this.setText((String) value);
        return this;
    }
}