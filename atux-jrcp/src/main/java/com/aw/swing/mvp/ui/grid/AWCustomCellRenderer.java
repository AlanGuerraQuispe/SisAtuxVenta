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
package com.aw.swing.mvp.ui.grid;

import com.aw.core.cache.support.DropDownFormatter;
import org.springframework.util.Assert;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Class to custom render a JTable
 * If the value of the list is contained in the values List
 * The foreground color of the cell must changed to the
 * specified color
 *
 * @author jcvergara
 *         Date: Apr 21, 2005
 */
public class AWCustomCellRenderer extends DefaultTableCellRenderer {
    private List values;
    private Color color;
    Color unselectForeground;
    DropDownFormatter dropDownFormatter;

    private boolean allRecords = false;

    public AWCustomCellRenderer(boolean allRecords, Color color) {
        this.allRecords = allRecords;
        this.color = color;
    }

    public AWCustomCellRenderer(String value) {
        this(value, Color.red);
    }


    public AWCustomCellRenderer(String value, Color color) {
        this.values = new ArrayList();
        values.add(value);
        this.color = color;
    }


    public AWCustomCellRenderer(List values, Color color) {
        this.values = values;
        this.color = color;
    }

    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row, int column) {
        if (allRecords) {
            setForeground(color);
        } else {
            Assert.notNull(values, "Please use the AWCustomCellRenderer(String value, Color color) Constructor");
            Assert.notNull(color, "Please use the AWCustomCellRenderer(String value, Color color) Constructor");
            String text = (String) value;
            storePreviousForeground();
            if (textIsContainedInValues(text)) {
                setForeground(color);
            } else {
                setForeground(Color.black);
            }
        }
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        this.setBorder(new EmptyBorder(1, 1, 1, 1));
        return cell;
    }

    /**
     * Return true if the text is contained int the values list
     * If the text no is contained in the list
     * we must convert the values list translating its elements
     * with a CVLFormatter and compare again to that values
     *
     * @return
     */
    private boolean textIsContainedInValues(String text) {
        if (values.contains(text)) {
            return true;
        }
        if (dropDownFormatter != null) {
            for (int i = 0; i < values.size(); i++) {
                String s = (String) values.get(i);
                String formattedValue = (String) dropDownFormatter.format(null, null, s);
                if (formattedValue.equals(text)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void storePreviousForeground() {
        if (unselectForeground == null) {
            unselectForeground = getForeground();
        }
    }

    public void setDropDownFormatter(DropDownFormatter dropDownFormatter) {
        this.dropDownFormatter = dropDownFormatter;
    }

}
