package com.aw.swing.mvp.action.types;

//import com.aw.core.db.InClauseConstructor;2.0.1

import com.aw.core.view.ViewMode;
import com.aw.support.beans.ValueProvider;
import com.aw.swing.mvp.action.RoundTransitionAction;
import com.aw.swing.mvp.navigation.Flow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * User: gmc
 * Date: 27-feb-2008
 */
public class InsertFromFindAction extends RoundTransitionAction {

    protected ValueProvider rowIdProvider = null;

    protected String inClauseForCurrentIdsLabel = "idsToAvoid";

    protected Map properties = new HashMap();

    public InsertFromFindAction() {
        targetMode = ViewMode.MODE_PICK;
    }

    protected Object executeTransitionIntern(Flow flow) {
        List values = getGridProvider().getValues();
//        String inClause = rowIdProvider == null ? InClauseConstructor.getInClause(values) : InClauseConstructor.getInClause(values, rowIdProvider);2.0.1
//        flow.setAttribute(inClauseForCurrentIdsLabel, inClause);2.0.1
        Map propertiesToSend = getProperties();
        Set<String> propertyKeys = propertiesToSend.keySet();
        for (String prop : propertyKeys) {
            flow.setAttribute(prop, propertiesToSend.get(prop));
        }
        return new HashMap();
    }


    public Object executeOnReturn(Flow initialFlow, Flow endFlow) {
        List selectedRows = (List) endFlow.getAttribute(Flow.SELECTED_ROWS);
        List rowsToBeAdded = getRowsToBeAdded(selectedRows);
        getGridProvider().addAllRows(rowsToBeAdded);
        return null;
    }

    /**
     * Return the rows that will be added
     *
     * @param selectedRows
     * @return
     */
    protected List getRowsToBeAdded(List selectedRows) {
        return selectedRows;
    }

    public String getInClauseForCurrentIdsLabel() {
        return inClauseForCurrentIdsLabel;
    }

    public InsertFromFindAction setInClauseForCurrentIdsLabel(String inClauseForCurrentIdsLabel) {
        this.inClauseForCurrentIdsLabel = inClauseForCurrentIdsLabel;
        return this;
    }

    public ValueProvider getRowIdProvider() {
        return rowIdProvider;
    }

    public void setRowIdProvider(ValueProvider rowIdProvider) {
        this.rowIdProvider = rowIdProvider;
    }


    public Map getProperties() {
        return properties;
    }

    public void setProperties(Map properties) {
        this.properties = properties;
    }
}
