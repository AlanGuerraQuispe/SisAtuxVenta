package com.aw.swing.mvp.grid;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * User: gmc
 * Date: 23/05/2009
 */
public class TableKeyManager {

    public static void decorate(JTable jTable) {
        InputMap im = jTable.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        KeyStroke left = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
        final Action oldLeftAction = jTable.getActionMap().get(im.get(left));

        Action newLeftAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                oldLeftAction.actionPerformed(e);
                JTable table = (JTable) e.getSource();
                int rowCount = table.getRowCount();
                int columnCount = table.getColumnCount();
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();
                while (!table.isCellEditable(row, column)) {
                    column -= 1;
                    if (column <= 0) {
                        column = columnCount - 1;
                        row -= 1;
                    }
                    if (row < 0) {
                        row = rowCount - 1;
                    }
                    //  Back to where we started, get out.
                    if (row == table.getSelectedRow() && column == table.getSelectedColumn()) {
                        break;
                    }
                }
                table.changeSelection(row, column, false, false);
                table.editCellAt(row, column);
            }
        };
        jTable.getActionMap().put(im.get(left), newLeftAction);

        //  Have the enter key work the same as the tab key
        KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        KeyStroke right = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
        im.put(enter, im.get(tab));
        im.put(right, im.get(tab));


        //  Override the default tab behaviour
        //  Tab to the next editable cell. When no editable cells goto next cell.
        final Action oldTabAction = jTable.getActionMap().get(im.get(tab));
        Action tabAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                oldTabAction.actionPerformed(e);
                JTable table = (JTable) e.getSource();
                int rowCount = table.getRowCount();
                int columnCount = table.getColumnCount();
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();
                while (!table.isCellEditable(row, column) && column + 1 != columnCount) {
                    column += 1;
                    if (column == columnCount) {
                        column = 0;
                        row += 1;
                    }

                    if (row == rowCount) {
                        row = 0;
                    }

                    //  Back to where we started, get out.
                    if (row == table.getSelectedRow() && column == table.getSelectedColumn()) {
                        break;
                    }
                }

                table.changeSelection(row, column, false, false);
                table.editCellAt(row, column);
            }
        };
        jTable.getActionMap().put(im.get(tab), tabAction);

    }
}
