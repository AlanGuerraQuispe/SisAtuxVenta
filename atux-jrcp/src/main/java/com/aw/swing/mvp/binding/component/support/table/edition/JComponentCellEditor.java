package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.core.cache.dropdown.DropDownRow;
import com.aw.core.cache.loader.MetaLoader;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.JTextFieldDocumentDecorator;
import com.aw.swing.mvp.binding.component.support.table.JTableModel;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class JComponentCellEditor extends DefaultCellEditor {
    private CellEditorValuesProvider cellEditorValuesProvider;
    private JComponentCellRenderer cellRenderer;
    private Map<String, JComponent> components = new HashMap();
    private static final String JCOMBOBOX = "jcombobox";
    private static final String JTEXTFIELD = "jtextfield";
    ColumnInfo columnInfo;
    Font font;

    public JComponentCellEditor(ColumnInfo columnInfo,Font font) {
        super(new JTextField());
        this.columnInfo = columnInfo;
        this.font = font;
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     *
     * @see javax.swing.DefaultCellEditor.EditorDelegate#getCellEditorValue
     */
    public Object getCellEditorValue() {
        Object label = delegate.getCellEditorValue();
        Object valueCmp = null;
        if (component instanceof JTextField) {
            valueCmp = ((JTextField) component).getText();
            label = valueCmp;
        } else if (component instanceof JComboBox) {
            valueCmp = ((JComboBox) component).getSelectedItem();
            if (valueCmp != null) {
                label = ((DropDownRow) valueCmp).getValue();
            } else {
                label = "";
            }
        }
        return label;
    }

    Component component;

    /**
     * Implements the <code>TableCellEditor</code> interface.
     */
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected,
                                                 int row, int column) {
        JTableModel tableModel = (JTableModel) table.getModel();
        Object objRow = tableModel.getRowAt(row);
        Object editorValues = cellEditorValuesProvider.getTableCellEditorValues(objRow, row);
        getComponentBasedOn(editorValues);

        if (component instanceof JComboBox) {
            JComboBoxCellEditorModel comboBoxModel = (JComboBoxCellEditorModel) ((JComboBox) component).getModel();
            cellRenderer.putMetaLoader(row, comboBoxModel.getMetaLoader());
            JComboBox comboBox = (JComboBox) component;
            DropDownRow ddr = comboBoxModel.getDDRFromValue(value);
            comboBox.setSelectedItem(ddr);
        } else if (component instanceof JTextField) {
            JTextField textField = (JTextField) component;
            textField.setText((String) value);
            cellRenderer.removeMetaLoader(row);
        }
        return component;
    }

    private void getComponentBasedOn(Object editorValues) {
        if (editorValues instanceof MetaLoader) {
            JComboBoxCellEditorModel comboBoxModel = new JComboBoxCellEditorModel();
            comboBoxModel.setMetaLoader((MetaLoader) editorValues);
            JComboBox jComboBox = (JComboBox) getComponentOfType(JCOMBOBOX);
            jComboBox.setModel(comboBoxModel);
            component = jComboBox;
        } else {
            component = getComponentOfType(JTEXTFIELD);
        }

    }

    private JComponent getComponentOfType(String componentType) {
        if (components.containsKey(componentType)) {
            return components.get(componentType);
        }
        JComponent jComponent = null;
        if (JCOMBOBOX.equals(componentType)) {
            final JComboBox jComboBox = new JComboBox();
            jComboBox.setRequestFocusEnabled(true);
            jComboBox.putClientProperty(CellEditorUtils.AW_CELL_EDITOR, Boolean.TRUE);
            jComboBox.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
            jComboBox.addAncestorListener(new AncestorListener() {
                public void ancestorAdded(AncestorEvent event) {
                    jComboBox.requestFocusInWindow();
                }

                public void ancestorMoved(AncestorEvent event) {
                }

                public void ancestorRemoved(AncestorEvent event) {
                }
            });
            jComponent = jComboBox;
        } else if (JTEXTFIELD.equals(componentType)) {
            jComponent = new JTextField();
            jComponent.setFont(font);
            jComponent.setBorder(null);

            if (columnInfo.getPropertyValidator() != null) {
                JTextFieldDocumentDecorator.decorate((JTextComponent) jComponent, columnInfo.getPropertyValidator(), columnInfo.getPropertyBinding());
            }
        }
        components.put(componentType, jComponent);
        return jComponent;
    }

    public void setCellEditorProvider(CellEditorValuesProvider cellEditorValuesProvider) {
        this.cellEditorValuesProvider = cellEditorValuesProvider;
    }

    public void setCellRenderer(JComponentCellRenderer cellRenderer) {
        this.cellRenderer = cellRenderer;
    }

}