package com.table.editableTable;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;

public class BooleanTable extends JFrame {
    Object[][] data = {{Boolean.TRUE}, {Boolean.TRUE}, {Boolean.TRUE}, {Boolean.TRUE}, {Boolean.TRUE}, {Boolean.TRUE}};
    String[] header = {"CheckBoxes"};

    public BooleanTable() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        TableModel model = new AbstractTableModel() {
            public String getColumnName(int column) {
                return header[column].toString();
            }

            public int getRowCount() {
                return data.length;
            }

            public int getColumnCount() {
                return header.length;
            }


            /**
             *  Returns <code>Object.class</code> regardless of <code>columnIndex</code>.
             *
             *  @param columnIndex  the column being queried
             *  @return the Object.class
             */
            public Class getColumnClass(int columnIndex) {
                return (data[0][columnIndex].getClass());
            }

            public Object getValueAt(int row, int col) {
                return data[row][col];
            }

            public boolean isCellEditable(int row, int column) {
                return true;
            }

            public void setValueAt(Object value, int row, int col) {
                data[row][col] = value;
                fireTableCellUpdated(row, col);

            }
        };


        JTable table = new JTable(model);
        table.setDefaultRenderer(Boolean.class, new MyCellRenderer());
        table.setDefaultEditor(Boolean.class, new MyCellEditor());

        getContentPane().add(new JScrollPane(table));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] a) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
        }
        new BooleanTable();
    }


    private class MyCellRenderer implements TableCellRenderer {
        protected Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);
        protected Border focusBorder = UIManager.getBorder("Table.focusCellHighlightBorder");
        private JCheckBox checkBox;

        public MyCellRenderer() {
            checkBox = new JCheckBox();
            checkBox.setHorizontalAlignment(SwingConstants.CENTER);
            checkBox.setBackground(Color.white);
        }

        public Component getTableCellRendererComponent(JTable
                table, Object value, boolean isSelected, boolean
                hasFocus, int row, int column) {
            checkBox.setSelected(Boolean.valueOf(value.toString()).booleanValue());
            return checkBox;
        }
    }

    private class MyCellEditor extends AbstractCellEditor implements TableCellEditor {
        JCheckBox checkBox;

        public MyCellEditor() {
            checkBox = new JCheckBox();
            checkBox.setHorizontalAlignment(SwingConstants.CENTER);
            checkBox.setBackground(Color.white);
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            checkBox.setSelected(Boolean.valueOf(value.toString()).booleanValue());
            return checkBox;
        }

        public Object getCellEditorValue() {
            return Boolean.valueOf(checkBox.isSelected());
        }
    }

}
