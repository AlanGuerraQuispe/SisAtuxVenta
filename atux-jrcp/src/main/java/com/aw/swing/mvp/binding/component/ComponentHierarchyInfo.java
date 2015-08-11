package com.aw.swing.mvp.binding.component;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 02/05/2009
 */
public class ComponentHierarchyInfo<E> {

    protected Object cmp;

    protected boolean isMasterCmp = true;
    /**
     * Dependents of this component
     */
    protected List<E> dependentCmps = new ArrayList();
    /**
     * Parents of this component
     */
    protected List<E> parentCmps = new ArrayList();

    public ComponentHierarchyInfo(Object cmp) {
        this.cmp = cmp;
    }

    public boolean isMasterCmp() {
        return isMasterCmp;
    }

    public void setMasterCmp(boolean masterCmp) {
        isMasterCmp = masterCmp;
    }

    public List<E> getDependentCmps() {
        return dependentCmps;
    }

    public void setDependentCmps(List<E> dependentCmps) {
        this.dependentCmps = dependentCmps;
    }

    public List<E> getParentCmps() {
        return parentCmps;
    }

    public void setParentCmps(List<E> parentCmps) {
        this.parentCmps = parentCmps;
    }

    public void addParentCmp(E masterCmp) {
        parentCmps.add(masterCmp);
    }

    public void addDependentCmp(E cmp) {
        dependentCmps.add(cmp);
    }


    public boolean hasDependentCmps() {
        return dependentCmps.size() > 0;
    }

    public boolean hasParentCmps() {
        return parentCmps.size() > 0;
    }

    public void dependsOn(E masterCmp) {
        ((Hierarchyable) masterCmp).addDependentCmp(cmp);
        setMasterCmp(false);
        addParentCmp(masterCmp);
    }
}
