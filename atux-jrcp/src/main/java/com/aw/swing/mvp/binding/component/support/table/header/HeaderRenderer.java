package com.aw.swing.mvp.binding.component.support.table.header;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * User: gmc
 * Date: 21-nov-2007
 */
public class HeaderRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                           boolean hasFocus, int row, int column) {
        if (table != null) {
            JTableHeader header = table.getTableHeader();
            if (header != null) {
                setForeground(header.getForeground());
                setBackground(header.getBackground());
                setFont(header.getFont());
            }
        }
        Icon icon = null;
        String text = "";
        if (value instanceof TextAndIcon) {
            icon = ((TextAndIcon) value).getIcon();
            text = ((TextAndIcon) value).getText();
        } else {
            text = (value == null) ? "" : value.toString();
        }
        setIcon(icon);
        setText(text);
        setBorder(BorderFactory.createLineBorder(Color.white));
        setHorizontalAlignment(JLabel.CENTER);
        return this;
    }
}
