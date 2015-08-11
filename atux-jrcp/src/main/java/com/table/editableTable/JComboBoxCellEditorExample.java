package com.table.editableTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JComboBoxCellEditorExample extends JFrame {

    public JComboBoxCellEditorExample() {
        super("JCombo Box cell editorTable");

        JTable table = new JTable();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Add some columns
        model.addColumn("A", new Object[]{"luis"});
        model.addColumn("B", new Object[]{"juan"});

        // These are the combobox values
        String[] values = new String[]{"luis", "lorca", "juan","juana"};

        // Set the combobox editor on the 1st visible column
        int vColIndex = 0;
        TableColumn col = table.getColumnModel().getColumn(vColIndex);
        col.setCellEditor(new MyComboBoxEditor(values));

        // If the cell should appear like a combobox in its
        // non-editing state, also set the combobox renderer
        col.setCellRenderer(new MyComboBoxRenderer(values));
        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll);
        setSize(400, 160);
        setVisible(true);
    }

    public static void main(String[] args) {
        JComboBoxCellEditorExample frame = new JComboBoxCellEditorExample();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {
        public MyComboBoxRenderer(String[] items) {
            super(items);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                super.setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }

            // Select the current value
            setSelectedItem(value);
            return this;
        }
    }

    public class MyComboBoxEditor extends DefaultCellEditor {
        public MyComboBoxEditor(String[] items) {
            super(new JComboBox(items));
        }
    }

}


