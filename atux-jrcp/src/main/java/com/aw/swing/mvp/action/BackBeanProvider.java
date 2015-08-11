package com.aw.swing.mvp.action;


import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.navigation.Flow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Iterator;
import java.util.Map;

/**
 * User: gmc
 * Date: 16/05/2009
 */
public class BackBeanProvider {
    protected Log logger = LogFactory.getLog(getClass());

    public BackBeanProvider() {
    }

    /**
     * Return the back bean that must be used.
     *
     * @return
     */
    public Object getBackBean(Presenter targetPst,Flow flow) {
        Object backBean = null;
        backBean = flow.getTargetBackBeanAttr();
        if (backBean == null) {
            backBean = targetPst.createBackBean();
        }
        decorateBackBeanWithFlowAttributes(backBean, flow);
        return backBean;                                                                  
    }

    /**
     * Decorate the back bean with the attributes sent in the flow
     *
     * @param backBean
     * @param flow
     */
    private void decorateBackBeanWithFlowAttributes(Object backBean, Flow flow) {
        Map flowAttributes = flow.getAttributes();
        BeanWrapper bwBackBean = new BeanWrapperImpl(backBean);
        for (Iterator iterator = flowAttributes.keySet().iterator(); iterator.hasNext();) {
            String flowAttributeName = (String) iterator.next();
            if (bwBackBean.isWritableProperty(flowAttributeName)) {
                bwBackBean.setPropertyValue(flowAttributeName, flowAttributes.get(flowAttributeName));
            }
        }
    }
}

