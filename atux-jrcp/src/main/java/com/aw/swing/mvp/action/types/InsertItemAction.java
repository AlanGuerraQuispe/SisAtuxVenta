package com.aw.swing.mvp.action.types;

import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.grid.GridProvider;

/**
 * User: gmc
 * Date: 27/04/2009
 */

public class InsertItemAction<GridBean> extends Action {
    private Class<GridBean> dmnClazz;

    public InsertItemAction() {
        useMessageBlocker = false;
        noExecBinding();
        noExecValidation();
    }

    public InsertItemAction(Class<GridBean> dmnClazz) {
        this();
        this.dmnClazz = dmnClazz;
    }

    protected Object executeIntern() throws Throwable {
        GridBean objectToBeInserted = getObjectToBeInserted();
        if (objectToBeInserted != null) {
            GridProvider gdp = getGridProvider();
            gdp.getBndSJTable().getValues().add(objectToBeInserted);
            gdp.recalcSecuencial();
            gdp.getGridManager().refreshValuesCounter();
            int rowIdx = gdp.getBndSJTable().getValues().size()-1;
            gdp.getBndSJTable().setSelectedRow(rowIdx);
            gdp.enableEditionOnFirstCell(rowIdx);
        }
        return null;
    }



    protected GridBean getObjectToBeInserted() throws IllegalAccessException, InstantiationException {
        if (dmnClazz != null) {
            return dmnClazz.newInstance();
        }
        return null;
    }

}