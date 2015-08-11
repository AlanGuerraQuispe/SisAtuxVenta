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

import com.aw.swing.exception.AWValidationException;
import com.aw.swing.mvp.Presenter;

/**
 * User: gmc
 * Date: 30-oct-2007
 */
public class VPGrid {
    public final static String VALIDATE_AT_LEAST_ONE_ROW = "validateAtLeastOneRow";

    private Presenter pst;
    private Object vsr;
    private Object dmn;

    private GridProvider gridProvider;
    private String validationMsg;

    public VPGrid(GridProvider gridProvider, String validationMsg) {
        this.gridProvider = gridProvider;
        this.validationMsg = validationMsg;
    }

    public void validateAtLeastOneRow() {
        if (gridProvider.getValues().size() < 1) {
            throw new AWValidationException(validationMsg);
        }
    }
}
             