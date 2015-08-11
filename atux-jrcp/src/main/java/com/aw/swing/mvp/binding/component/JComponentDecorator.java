package com.aw.swing.mvp.binding.component;

import com.aw.swing.mvp.binding.component.support.table.JTableModel;
import com.aw.swing.mvp.ui.painter.ColorInfoProvider;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * User: gmc
 * Date: 06/06/2009
 */
public class JComponentDecorator {

    public static JComponent setUIReadOnly(JComponent jComponent, boolean uiReadOnly) {
        return setUIReadOnly(jComponent,uiReadOnly,true);
    }

    public static JComponent setUIReadOnly(JComponent jComponent, boolean uiReadOnly,boolean uiReadOnlyChangeColor) {
        boolean isTable = jComponent instanceof JTable;
        if (jComponent instanceof JTextComponent) {
            ((JTextComponent) jComponent).setEditable(!uiReadOnly);
        } else if (!isTable) {
            jComponent.setEnabled(!uiReadOnly);
        }
        if (isTable) {
            JTable jTable = (JTable) jComponent;
            Object model = jTable.getModel();
            if (model instanceof JTableModel) {
                ((JTableModel) model).setReadOnly(uiReadOnly);
            }
        } else {
            jComponent.setFocusable(!uiReadOnly);
        }

        Color background = ColorInfoProvider.TEXT_BOX_READ_ONLY;
        if (!uiReadOnly) {
            background = ColorInfoProvider.COLOR_DEFAULT;
        }
        if (!(jComponent instanceof JComboBox) && uiReadOnlyChangeColor) {
            jComponent.setBackground(background);
        }
        return jComponent;
    }

    public static void setUIReadOnly(JPanel jPanel) {
        Component[] components = jPanel.getComponents();
        setUIReadOnly(components);
    }

    public static void setUINoReadOnly(JPanel jPanel) {
        Component[] components = jPanel.getComponents();
        setUINoReadOnly(components);
    }

    public static void setUINoReadOnly(Component[] components) {
        for (Component component : components) {
            if (!(component instanceof JComponent)) continue;
            JComponent jcmp = (JComponent) component;
            if ((jcmp instanceof JPanel)
                    || (jcmp instanceof JScrollPane)
                    || (jcmp instanceof JLabel)) {
            } else if (jcmp instanceof JButton) {
                jcmp.setEnabled(true);
            } else if ((jcmp instanceof JTextComponent)
                    || (jcmp instanceof JCheckBox)
                    || (jcmp instanceof JComboBox)
                    || (jcmp instanceof JRadioButton)
                    || (jcmp instanceof JTable)) {
                setUIReadOnly(jcmp, false);
            }
            Component[] childComponents = jcmp.getComponents();
            if (childComponents != null && childComponents.length > 0) {
                setUINoReadOnly(childComponents);
            }
        }
    }


    public static void setUIReadOnly(Component[] components) {
        for (Component component : components) {
            if (!(component instanceof JComponent) ||
                    (component instanceof JScrollBar)) {
                continue;
            }
            JComponent jcmp = (JComponent) component;
            if ((jcmp instanceof JPanel)
                    || (jcmp instanceof JScrollPane)
                    || (jcmp instanceof JLabel)) {
            } else if (jcmp instanceof JButton) {
                jcmp.setEnabled(false);
            } else if ((jcmp instanceof JTextComponent)
                    || (jcmp instanceof JCheckBox)
                    || (jcmp instanceof JComboBox)
                    || (jcmp instanceof JRadioButton)
                    || (jcmp instanceof JTable)) {
                setUIReadOnly(jcmp, true);
            }
            Component[] childComponents = jcmp.getComponents();
            if (childComponents != null && childComponents.length > 0) {
                setUIReadOnly(childComponents);
            }
        }
    }
}
