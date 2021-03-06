package com.table;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

public class ChooserTableSample {
    public static void main(String args[]) {
        Runnable runner = new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Editable Color Table");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                TableModel model = new ColorTableModel();
                JTable table = new JTable(model);
                TableColumn column = table.getColumnModel().getColumn(3);
                ComboTableCellRenderer renderer = new ComboTableCellRenderer();
                column.setCellRenderer(renderer);
                TableCellEditor editor = new ColorChooserEditor();
//                TableCellEditor editor = new JFileChooserCellEditor();
                column.setCellEditor(editor);
                JScrollPane scrollPane = new JScrollPane(table);
                frame.add(scrollPane, BorderLayout.CENTER);
                frame.setSize(400, 150);
                frame.setVisible(true);
            }
        };
        EventQueue.invokeLater(runner);
    }
}