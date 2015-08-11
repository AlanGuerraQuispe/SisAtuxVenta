package com.aw.swing.mvp.action.types;

import com.aw.core.view.ViewMode;
import com.aw.swing.mvp.action.RoundTransitionAction;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.navigation.Flow;

import java.util.HashMap;
import java.util.Map;

/**
 * User: gmc
 * Date: 27/04/2009
 */

public class InsertAction<GridBean, EditBean> extends RoundTransitionAction {
    private Class dmnClazz;

    public InsertAction() {
        targetMode = ViewMode.MODE_INSERT;
    }

    public InsertAction(Class dmnClazz) {
        this.dmnClazz = dmnClazz;
    }

    protected Map executeTransitionIntern(Flow flow) throws IllegalAccessException, InstantiationException {
        Object objectToBeInserted = getObjectToBeInserted();
        if (objectToBeInserted != null) {
            flow.setTargetBackBeanAttribute(objectToBeInserted);
        }
        return new HashMap();
    }

    protected EditBean getObjectToBeInserted() throws IllegalAccessException, InstantiationException {
        if (dmnClazz != null) {
            return (EditBean) dmnClazz.newInstance();
        }
        return null;
    }

    public Object executeOnReturn(Flow initialFlow, Flow endFlow) {
        if (refreshGridAtEnd){
            return null;
        }
        Object domainToBeAdded = getRowToBeAdded((EditBean) endFlow.getAttribute(Flow.BACK_BEAN_NAME));
        GridProvider gdp= getGridProvider();
        gdp.getBndSJTable().addRow(0,domainToBeAdded);
        return null;
    }

    protected GridBean getRowToBeAdded(EditBean row) {
        return (GridBean) row;
    }
}