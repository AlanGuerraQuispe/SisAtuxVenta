/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.swing.mvp.cmp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Date: Sep 19, 2007
 */
public class RowSelectorMediator {
    protected final Log logger = LogFactory.getLog(getClass());

    private JTextField textField;
    private JTable table;
    private int columnIndex;
    private boolean caretAtBeginning = false;

    DocumentListener documentListener;

    public RowSelectorMediator(JTextField textField, JTable table, int columnIndex) {
        this.textField = textField;
        this.table = table;
        this.columnIndex = columnIndex;

        documentListener = new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                textFieldChanged(e);
            }

            public void insertUpdate(DocumentEvent e) {
                textFieldChanged(e);
            }

            public void removeUpdate(DocumentEvent e) {
                textFieldChanged(e);
            }
        };

        textField.getDocument().addDocumentListener(documentListener);

        //Add the keylistener to process the Up, Down, PgUp and PgDn keys events
        textField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                textFieldKeyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
            }
        });
    }

    /**
     * AWProcess when a user change the text contained for the textfield
     *
     * @param e
     */
    private void textFieldChanged(DocumentEvent e) {
        if (logger.isDebugEnabled()) {
            logger.debug("Processing text field change with column index: " + columnIndex + " changed event " + e + " ");
        }

        String prefix = textField.getText();
        if (prefix.length() == 0) {
            table.clearSelection();
            return;
        }

        boolean found = false;
        TableModel m = table.getModel();

        for (int i = 0; found == false && i < m.getRowCount(); i++) {
            Object o = m.getValueAt(i, columnIndex);
            if (o == null) {
                continue;
            }

            String s = o.toString();
            if (s.toUpperCase().startsWith(prefix.toUpperCase())) {
                table.setRowSelectionInterval(i, i);
                Rectangle rect = table.getCellRect(i, columnIndex, true);
                table.scrollRectToVisible(rect);

                found = true;
            }
        }

        if (!found) {
            table.clearSelection();
        }
    }

    /**
     * AWProcess when the user press the Up, Down, PgUp and PgDn keys
     */
    private void textFieldKeyPressed(KeyEvent e) {
        if (logger.isDebugEnabled()) {
            logger.debug("Processing text field key pressed event ");
        }

        int delta = 0;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                delta = 1;
                break;
            case KeyEvent.VK_UP:
                delta = -1;
                break;
            case KeyEvent.VK_PAGE_DOWN:
                delta = calculateVisibleRect();
                break;
            case KeyEvent.VK_PAGE_UP:
                delta = -calculateVisibleRect();
                break;
            default:
                return;
        }
        //
        int jump = calculateJumpWithBounds(delta);
        if (logger.isDebugEnabled()) {
            logger.debug("Scroll, jump to " + jump);
        }

        selectTableRow(jump);
    }

    /**
     * Calculate the next selected row index based in the key
     * pressed and the table's scroll bounds
     *
     * @param delta
     * @return
     */
    private int calculateJumpWithBounds(int delta) {
        int jump = delta + table.getSelectedRow();

        if (jump < 0) {
            jump = 0;
        } else if (jump >= table.getRowCount()) {
            jump = table.getRowCount() - 1;
        }

        return jump;
    }

    private int calculateVisibleRect() {
        return (int) table.getVisibleRect().getHeight() / (table.getRowHeight() + table.getRowMargin());
    }

    /**
     * Select(highlight) a row in a table
     *
     * @param index
     */
    private void selectTableRow(int index) {
        Rectangle rect = table.getCellRect(index, columnIndex, true);
        table.scrollRectToVisible(rect);
        table.clearSelection();
        table.setRowSelectionInterval(index, index);
        TableModel m = table.getModel();
        String text = (String) m.getValueAt(table.getSelectedRow(), columnIndex);
        textField.getDocument().removeDocumentListener(documentListener);
        textField.setText(text);
        textField.getDocument().addDocumentListener(documentListener);

        int beginPos = 0;
        int endPos = text.length();
        if (caretAtBeginning) {
            textField.setCaretPosition(endPos);
            textField.moveCaretPosition(beginPos);
        } else {
            textField.setCaretPosition(beginPos);
            textField.moveCaretPosition(endPos);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Setting current row at " + index);
        }
        table.setRowSelectionInterval(index, index);
    }

    public void setCaretAtBeginning(boolean caretAtBeginning) {
        this.caretAtBeginning = caretAtBeginning;
    }
}

