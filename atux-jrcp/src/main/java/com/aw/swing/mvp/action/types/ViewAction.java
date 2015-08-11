package com.aw.swing.mvp.action.types;

import com.aw.core.view.ViewMode;
import com.aw.swing.mvp.action.RoundTransitionAction;
import com.aw.swing.mvp.navigation.Flow;

import java.util.HashMap;
import java.util.Map;

/**
 * User: gmc
 * Date: 21/05/2009
 */
public class ViewAction extends RoundTransitionAction {

    EditAction editAction;

    {
        targetMode = ViewMode.MODE_READONLY;
        needSelectedRow();
    }

    public ViewAction(EditAction editAction) {
        this.editAction = editAction;
        setTargetPstClass(editAction.getTargetPstClass());
    }

    public ViewAction() {

    }

    protected Map executeTransitionIntern(Flow flow) {
        Object selectedRow = getSelectedRow();
        Object objectToBeViewed = getObjectToBeViewed(selectedRow);
        flow.setTargetBackBeanAttribute(objectToBeViewed);
        flow.setAttribute(Flow.ROW_INDEX, getSelectedRowIdx());
        return new HashMap();
    }

    protected Object getObjectToBeViewed(Object obj) {
        if (editAction != null) {
            return editAction.getObjectToBeUpdated(obj);
        }
        return getObjectToBeViewedInternal(obj);
    }

    protected Object getObjectToBeViewedInternal(Object obj) {
        return obj;
    }

}
