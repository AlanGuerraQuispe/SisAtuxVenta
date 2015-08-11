package com.aw.swing.mvp.grid.filter;

/**
 * User: gmc
 * Date: 26-nov-2007
 */
public class CriteriaAttribute {
    protected String name;
    protected Object value;

    public CriteriaAttribute(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
