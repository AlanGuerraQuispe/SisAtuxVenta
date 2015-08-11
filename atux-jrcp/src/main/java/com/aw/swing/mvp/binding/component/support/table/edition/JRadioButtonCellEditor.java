package com.aw.swing.mvp.binding.component.support.table.edition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class JRadioButtonCellEditor extends DefaultCellEditor implements ItemListener {

    public JRadioButton btn = new JRadioButton();
//    public ButtonGroup group = new ButtonGroup();

    private Object valueTrue = Boolean.TRUE;

    public JRadioButtonCellEditor(JCheckBox checkBox,Object valueTrue) {
        super(checkBox);
        this.valueTrue = valueTrue;
    }

    public void setValueTrue(Object valueTrue) {
        this.valueTrue = valueTrue;
    }


    public Component getTableCellEditorComponent(JTable table, Object
            value, boolean isSelected, int row, int column) {

        if (value == null) return null;
        boolean valuesAsBoolean = false;
        if (value instanceof Boolean) {
            Boolean b = (Boolean) value;
            valuesAsBoolean = b.booleanValue();
        } else if (value instanceof Object) {
            valuesAsBoolean = value.equals(valueTrue);
        }
//        btn = new JRadioButton("");
//        group.add(btn);
        btn.addItemListener(this);
        if (valuesAsBoolean)
            btn.setSelected(true);
        else
            btn.setSelected(false);
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        return btn;
    }

    public Object getCellEditorValue() {
        if (btn.isSelected() == true)
            return new Boolean(true);
        else
            return new Boolean(false);
    }

    public void itemStateChanged(ItemEvent e) {
        super.fireEditingStopped();
    }
}
