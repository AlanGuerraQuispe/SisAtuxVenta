package com.aw.swing.mvp.cmp.pick;

import com.aw.core.view.ViewMode;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.RoundTransitionAction;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.binding.BindingManager;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.navigation.Flow;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * User: gmc
 * Date: 15/05/2009
 */
public class PickAction extends RoundTransitionAction {

    private Pick pick;
    private boolean execFirstChecking = false;

    public PickAction() {
        targetMode = ViewMode.MODE_PICK;
        execValidation = false;
    }

    protected Object executeTransitionIntern(Flow flow) throws Exception {
        Map attributes = getAttributes();
        if (execFirstChecking){
            execFirstChecking = false;
            List values = getResultValues(attributes);
            if (values.size() == 1) {
                Object selectedRow = values.get(0);
                Map copiedValues = copyValuesToBackBean(selectedRow);
                updateJComponents(copiedValues);
                Map additionalModel = getAdditionalModelForExecuteOnReturn(selectedRow, copiedValues);
                return STOP_TRANSITION;
            }
        }
        flow.setAttributes(attributes);
        return new HashMap();
    }

    private void updateJComponents(Map<String,Object> copiedValues) {
        Set<String> fieldNames = copiedValues.keySet();
        BindingManager bindingManager= getPst().getBindingMgr();
        for (String fieldName : fieldNames) {
            BindingComponent bnd = bindingManager.getBindingComponent(fieldName);
            if (bnd != null){
                bnd.setValueToJComponent();
            }
        }
//        getPst().setValuesToJComponent();
    }

    /**
     * Get the attributes (name-value pairs) that will be sent to the target controller
     *
     * @return
     */
    protected Map getAttributes() {
        Map<String, String> parameters = pick.getParameters();
        BeanWrapper backBean = new BeanWrapperImpl(getPst().getBackBean());
        Map attributes = new HashMap();
        for (Iterator<String> iterator = parameters.keySet().iterator(); iterator.hasNext();) {
            String paramName = iterator.next();
            Object paramValue = backBean.getPropertyValue(paramName);
            String paramToName = parameters.get(paramName);
            attributes.put(paramToName, paramValue);
        }
        Map<String, PickParamProvider> paramProviders = pick.getParameterProviders();
        for (Iterator<String> iterator = paramProviders.keySet().iterator(); iterator.hasNext();) {
            String paramName = iterator.next();
            Object paramValue = paramProviders.get(paramName).getParameterValue();
            attributes.put(paramName, paramValue);
        }
        attributes.putAll(pick.getParameterValues());
        return attributes;
    }

    /**
     * Get the values that return the target controller.
     * This is used in order to get the value if the result has only one record avoiding the
     * normal pick flow.
     *
     * @param attributes
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private List getResultValues(Map attributes) throws IllegalAccessException, InvocationTargetException {
        Presenter targetPst = PstMgr.instance().getPst(getTargetPstClass());
        targetPst.configure();
        GridProvider gdp = targetPst.getGridProvider(0);
        Object targetBackBean = targetPst.createBackBean();
        BeanUtils.populate(targetBackBean, attributes);
        return gdp.getGridInfoProvider().getValues(targetBackBean);
    }

    public Object executeOnReturn(Flow initialFlow, Flow endFlow) {
        Object selectedRow = endFlow.getAttribute(Flow.SELECTED_ROW);
        Map copiedValues = copyValuesToBackBean(selectedRow);
        updateJComponents(copiedValues);
        Map additionalModel = getAdditionalModelForExecuteOnReturn(selectedRow, copiedValues);
        return null;
    }

    protected Map getAdditionalModelForExecuteOnReturn(Object selectedRow, Map copiedValues) {
        Map additionalModel = new HashMap();
        PickListener listener = pick.getListener();
        if (listener != null) {
            additionalModel = listener.valuesSet(selectedRow, copiedValues);
        }
        return additionalModel != null ? additionalModel : new HashMap();
    }

    /**
     * Copy values from the selected row to the domain
     *
     * @param selectedRow
     */
    protected Map copyValuesToBackBean(Object selectedRow) {
        pick.setPickFilled(true);
        Map<String, String> binding = pick.getBinding();
        Map<String, Object> values = new HashMap();
        BeanWrapper backBean = new BeanWrapperImpl(getPst().getBackBean());
        BeanWrapper bwSelectedRow = new BeanWrapperImpl(selectedRow);
        for (Iterator<String> iterator = binding.keySet().iterator(); iterator.hasNext();) {
            String toFieldName = iterator.next();
            String fromFieldName = binding.get(toFieldName);
            Object value = bwSelectedRow.getPropertyValue(fromFieldName);
            backBean.setPropertyValue(toFieldName, value);
            values.put(toFieldName, value);
        }
        return values;
    }

    public void setPick(Pick pick) {
        this.pick = pick;
        if (pick.isRefreshGridAtEnd()) {
            refreshGridAtEnd();
        }
    }

    public void setExecFirstChecking(boolean execFirstChecking) {
        this.execFirstChecking = execFirstChecking;
    }
}
