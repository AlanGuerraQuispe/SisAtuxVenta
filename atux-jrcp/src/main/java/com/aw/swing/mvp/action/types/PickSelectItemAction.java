package com.aw.swing.mvp.action.types;

import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.navigation.Flow;

import java.util.HashMap;
import java.util.Map;

/**
 * User: gmc
 * Date: 16/05/2009
 */
public class PickSelectItemAction extends Action {

    public PickSelectItemAction() {
        closeViewAtEnd();
        needSelectedRow = true;
        executeOnDblClick = true;
        allowMultiSelectedRows = false;
        useMessageBlocker = false;
    }

    protected Object executeIntern() throws Throwable {
        return null;
    }

    public Map getAttributesAtCloseView() {
        Object selectedRow = getSelectedRow();
        Map attributes = new HashMap();
        attributes.put(Flow.SELECTED_ROW, selectedRow);
        return attributes;
    }
}

