package com.aw.swing.mvp.action.types;

import com.aw.core.domain.ICloneable;
import com.aw.core.exception.AWSystemException;
import com.aw.core.view.ViewMode;
import com.aw.swing.mvp.action.RoundTransitionAction;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.navigation.Flow;

import java.util.HashMap;
import java.util.Map;


/**
 * User: gmc
 * Date: 27-feb-2008
 */
public class EditAction<GridBean, EditBean> extends RoundTransitionAction {

    public EditAction() {
        targetMode = ViewMode.MODE_UPDATE;
        needSelectedRow = true;
    }

    protected Map executeTransitionIntern(Flow flow) {
        GridBean selectedRow = (GridBean) getSelectedRow();
        EditBean objectToBeUpdated = getObjectToBeUpdated(selectedRow);
        objectToBeUpdated = (EditBean) cloneObjectIfPossible(objectToBeUpdated);
        flow.setTargetBackBeanAttribute(objectToBeUpdated);
        flow.setAttribute(Flow.ROW_INDEX, getSelectedRowIdx());
        return new HashMap();
    }

    static Object cloneObjectIfPossible(Object  obj) {
        Object  instanceInitial = obj;
        Object  instanceCloned = instanceInitial;
        try {
            if (instanceInitial instanceof ICloneable) {
                instanceCloned = (Object) ((ICloneable) instanceInitial).clone();
            }
        } catch (CloneNotSupportedException e) {
            throw new AWSystemException("Problems cloning:" + obj, e);
        }
        return instanceCloned;
    }

    protected EditBean getObjectToBeUpdated(GridBean obj) {
        return (EditBean) obj;
    }

    public Object executeOnReturn(Flow initialFlow, Flow endFlow) {
        if (refreshGridAtEnd){
            return null;           
        }
        GridProvider gdp = getGridProvider();
        Integer rowIndex = (Integer) initialFlow.getAttribute(Flow.ROW_INDEX);
        if (rowIndex != null) {
            EditBean domainEdited = (EditBean) endFlow.getAttribute(Flow.BACK_BEAN_NAME);
            GridBean domainToBeDisplayed = getRowToBeDisplayed(domainEdited);
            gdp.getBndSJTable().updateRow(rowIndex, domainToBeDisplayed);
        }
        return null;
    }
    protected GridBean getRowToBeDisplayed(EditBean  row) {
        return (GridBean) row;
    }


}
