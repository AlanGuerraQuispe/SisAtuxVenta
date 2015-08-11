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

/**
 * User: gmc
 * Date: 17-sep-2007
 */
public interface RowSelectionListener {
    /**
     * Method call when a row is selected
     *
     * @param selectedIndex
     * @param selectedRow
     */
    public void onSelectedRow(int selectedIndex, Object selectedRow);

    /**
     * Method call when the selected row is clear it means there are any selected row
     */
    public void onClearSelectedRow();

}
