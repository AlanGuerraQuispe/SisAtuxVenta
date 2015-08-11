package com.aw.swing.mvp.navigation;

import com.aw.swing.mvp.action.Action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * User: gmc
 * Date: 01-mar-2008
 */
public class Flow implements Serializable {
    public static String ROW_INDEX = "rowIndex";
    public static final String BACK_BEAN_NAME = "backBean";
    public static final String UPLOADED_FILE = "uploadedFile";
    public static String SELECTED_ROW = "selectedRow";
    public static String SELECTED_ROWS = "selectedRows";
    public static String RESULT_ACTION = "resultAction";


    private Class initialPst;
    private Class endPst;
    private Action actionExecuted;
    private Map<String, Object> attributes;
    private Object targetBackBeanAttr;
    private boolean isGoForward = true;

    public Class getInitialPst() {
        return initialPst;
    }

    public void setInitialPst(Class initialPst) {
        this.initialPst = initialPst;
    }

    public Class getEndPst() {
        return endPst;
    }

    public void setEndPst(Class endPst) {
        this.endPst = endPst;
    }

    public Action getActionExecuted() {
        return actionExecuted;
    }

    public void setActionExecuted(Action actionExecuted) {
        this.actionExecuted = actionExecuted;
    }

    public Map getAttributes() {
        if (attributes == null) {
            attributes = new HashMap();
        }
        return attributes;
    }

    public void setAttributes(Map attributes) {
        this.attributes = attributes;
    }

    public void setTargetBackBeanAttribute(Object targetBackBean) {
        this.targetBackBeanAttr = targetBackBean;
    }

    public Object getTargetBackBeanAttr() {
        return targetBackBeanAttr;
    }

    public void setAttribute(String name, Object value) {
        getAttributes().put(name, value);
    }

    public boolean isGoForward() {
        return isGoForward;
    }

    public void setGoForward(boolean goForward) {
        isGoForward = goForward;
    }

    public Object getAttribute(String attName) {
        return getAttributes().get(attName);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flow flow = (Flow) o;
        if (isGoForward != flow.isGoForward()) return false;
        if (endPst != null ? !endPst.equals(flow.endPst) : flow.endPst != null) return false;
        if (initialPst != null ? !initialPst.equals(flow.initialPst) : flow.initialPst != null) return false;
        if (actionExecuted != null ? !actionExecuted.equals(flow.actionExecuted) : flow.actionExecuted != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (initialPst != null ? initialPst.hashCode() : 0);
        result = 31 * result + (endPst != null ? endPst.hashCode() : 0);
        result = 31 * result + (actionExecuted != null ? actionExecuted.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (targetBackBeanAttr != null ? targetBackBeanAttr.hashCode() : 0);
        result = 31 * result + (isGoForward ? 1 : 0);
        return result;
    }

}