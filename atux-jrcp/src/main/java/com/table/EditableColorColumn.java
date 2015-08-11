package com.table;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * User: gmc
 * Date: 17/04/2009
 */
public class EditableColorColumn {

    public static void main(String args[]) {
        Runnable runner = new Runnable() {
            public void run() {
                Color choices[] = {Color.RED, Color.ORANGE, Color.YELLOW,
                        Color.GREEN, Color.BLUE, Color.MAGENTA};
                ComboTableCellRenderer renderer = new ComboTableCellRenderer();
                JComboBox comboBox = new JComboBox(choices);
                comboBox.setRenderer(renderer);
                TableCellEditor editor = new DefaultCellEditor(comboBox);
                JFrame frame = new JFrame("Editable Color Table");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                TableModel model = new ColorTableModel();
                JTable table = new JTable(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                JTableHeader header = table.getTableHeader();
                header.setReorderingAllowed(false);
                table.setSurrendersFocusOnKeystroke(true);



                TableColumn column = table.getColumnModel().getColumn(2);
                column.setCellRenderer(renderer);
                column.setCellEditor(editor);
                    UIManager.put("Button.background", Color.YELLOW);
                UIManager.put("Button.foreground", Color.BLACK);
                JButton test = new JButton("test");
                JButton test1 = new JButton("test1");
                JScrollPane scrollPane = new JScrollPane(table);
                frame.add(scrollPane, BorderLayout.CENTER);
                frame.add(test, BorderLayout.NORTH);
                frame.add(test1, BorderLayout.NORTH);
                frame.setSize(200, 150);
                frame.setVisible(true);
            }
        };
        EventQueue.invokeLater(runner);
    }
}