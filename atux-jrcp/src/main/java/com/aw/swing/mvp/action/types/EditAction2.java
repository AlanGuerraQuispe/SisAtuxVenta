package com.aw.swing.mvp.action.types;

import com.aw.core.view.ViewMode;
import com.aw.swing.mvp.action.RoundTransitionAction;
import com.aw.swing.mvp.navigation.Flow;

import java.util.HashMap;
import java.util.Map;


/**
 * User: gmc
 * Date: 27-feb-2008
 */
public abstract class EditAction2<EditBean> extends RoundTransitionAction {

    public EditAction2() {
        targetMode = ViewMode.MODE_UPDATE;
        needSelectedRow = false;
    }

    protected Map executeTransitionIntern(Flow flow) {
        EditBean objectToBeUpdated = getObjectToBeUpdated();
        objectToBeUpdated = (EditBean) EditAction.cloneObjectIfPossible(objectToBeUpdated);
        flow.setTargetBackBeanAttribute(objectToBeUpdated);
        return new HashMap();
    }


    protected abstract  EditBean getObjectToBeUpdated();
    public EditBean executeOnReturn(Flow initialFlow, Flow endFlow) {
        return null;
    }
    protected EditBean getEditedBean(Flow endFlow){
        EditBean domainEdited = (EditBean) endFlow.getAttribute(Flow.BACK_BEAN_NAME);
        return domainEdited;
    }


}