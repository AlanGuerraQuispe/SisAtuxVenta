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
package com.aw.swing.mvp.grid;

import org.apache.commons.functor.UnaryPredicate;

import java.awt.*;

/**
 * User: gmc
 * Date: 30-oct-2007
 */
public class RowColorBkgChanger {
    UnaryPredicate condition;
    Object lastRow;
    private Color lastColor;

    public RowColorBkgChanger(UnaryPredicate condition) {
        this.condition = condition;
    }

    public void process(Object row, Component cellCmp) {
        Color color = getColor(row);
        cellCmp.setBackground(color);
    }

    private Color getColor(Object row) {
        if (row == lastRow) {
            return lastColor;
        }
        Color color = Color.WHITE;
        if (condition.test(row)) {
            color = Color.RED;
        }
        lastColor = color;
        lastRow = row;
        return color;
    }
}
