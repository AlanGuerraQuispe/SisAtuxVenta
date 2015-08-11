package com.aw.swing.mvp.binding.component.support.table.edition;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * User: gmc
 * Date: 04/05/2009
 * clase usada para renderizar....  
 */
public class JRadioButtonCellRenderer implements TableCellRenderer {
    public JRadioButton btn = new JRadioButton();

    Object valueTrue;

    public JRadioButtonCellRenderer(Object valueTrue) {
        this.valueTrue = valueTrue;
    }

    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        if (value == null) return null;
        boolean valuesAsBoolean = false;
        if (value instanceof Boolean) {
            Boolean b = (Boolean) value;
            valuesAsBoolean = b.booleanValue();
        } else if (value instanceof Object) {
            valuesAsBoolean = value.equals(valueTrue);
        }

        if (valuesAsBoolean)
            btn.setSelected(true);
        else
            btn.setSelected(false);

        if (isSelected) {
            btn.setForeground(table.getSelectionForeground());
            btn.setBackground(table.getSelectionBackground());
        } else {
            btn.setForeground(table.getForeground());
            btn.setBackground(table.getBackground());
        }
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        return btn;
    }
}
