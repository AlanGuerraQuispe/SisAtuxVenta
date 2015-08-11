package com.aw.swing.mvp.action.types;

import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.grid.GridProvider;

import java.util.List;

/**
 * User: gmc
 * Date: 27/04/2009
 */

public class DeleteItemAction extends Action {

    {
        allowMultiSelectedRows = true;
        useMessageBlocker = false;
        noExecBinding();
        noExecValidation();
    }

    protected Object executeIntern() throws Throwable {
        GridProvider gdp = getGridProvider();
        List selectedRows = gdp.getSelectedRows();
        gdp.getBndSJTable().recalculateDependentDropDowns(gdp.getSelectedRowsIdx());
        for (Object selectedRow : selectedRows) {
            gdp.getBndSJTable().remove(selectedRow);
        }
        gdp.recalcSecuencial();
        gdp.getBndSJTable().cleanSelectedRowsForRefresh();
        return null;
    }
}