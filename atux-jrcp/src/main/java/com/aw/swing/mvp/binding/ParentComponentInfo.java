package com.aw.swing.mvp.binding;

/**
 * User: gmc
 * Date: 24/11/2009
 */
public class ParentComponentInfo {
    private String[] paths;
    private boolean allCmpsReadOnly = false;

    public ParentComponentInfo(String[] paths) {
        this.paths = paths;
    }

    public ParentComponentInfo setAllCmpsAsReadOnly(){
        allCmpsReadOnly = true;
        return this;
    }

    public String[] getPaths() {
        return paths;
    }

    public boolean isAllCmpsReadOnly() {
        return allCmpsReadOnly;
    }
}
