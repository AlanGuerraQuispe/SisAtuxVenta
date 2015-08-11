package com.aw.swing.mvp.action.types;

import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.navigation.Flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Use to select several rows from a child window.
 * Close the child window at end
 * User: gmc
 * Date: 27-feb-2008
 */
public class SelectRowsAction extends Action {

    public SelectRowsAction() {
        closeViewAtEnd();
        allowMultiSelectedRows = true;
        executeOnDblClick = true;
    }

    protected Object executeIntern() throws Throwable {
        return null;
    }

    public Map getAttributesAtCloseView() {
        GridProvider gdp = getGridProvider();
        List selectedRows = gdp.getSelectedRows();
        if (!allowMultiSelectedRows) {
            Object selectedRow = gdp.getSelectedRow();
            selectedRows = new ArrayList();
            selectedRows.add(selectedRow);
        }
        Map attributes = new HashMap();
        attributes.put(Flow.SELECTED_ROWS, selectedRows);
        return attributes;
    }
}
