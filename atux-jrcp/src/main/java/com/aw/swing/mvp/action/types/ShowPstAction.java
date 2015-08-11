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
public class ShowPstAction extends RoundTransitionAction {

    private Class targetBackBeanClass;
    protected Object targetBackBean;  // precargado

    public ShowPstAction() {
        setTargetMode(ViewMode.MODE_READONLY);
    }

    public ShowPstAction(Class targetBackBeanClass) {
        this.targetBackBeanClass = targetBackBeanClass;
    }


    protected Map executeTransitionIntern(Flow flow) throws IllegalAccessException, InstantiationException {
        Object currentTargetBackBean = targetBackBean;
        if (currentTargetBackBean==null ){ //class is null, siempre recargar target bean
            currentTargetBackBean = getTargetBackBean();
        }
        if (currentTargetBackBean != null) {
            flow.setTargetBackBeanAttribute(currentTargetBackBean);
        }
        return new HashMap();
    }

    protected Object getTargetBackBean() throws IllegalAccessException, InstantiationException {
        if (targetBackBeanClass != null) {
            return targetBackBeanClass.newInstance();
        }
        return null;
    }

    public Object executeOnReturn(Flow initialFlow, Flow endFlow) {
        return null;
    }

    public void setTargetBackBean(Object targetBackBean) {
        this.targetBackBean = targetBackBean;
    }

}