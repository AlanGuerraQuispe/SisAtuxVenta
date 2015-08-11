/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.core.cache.dropdown;


/**
 * @author Julio C. Macavilca
 *         06/07/2004
 */
public class DropDownRowImpl implements DropDownRow {
    protected Object value;
    protected Object label;
    protected Object inActive;
    protected Object other;

    public DropDownRowImpl() {
    }

    public DropDownRowImpl(Object value, Object label) {
        this.value = value;
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getLabel() {
        return label;
    }

    public boolean isActive() {
        return inActive == null ||
            (inActive instanceof Boolean && ((Boolean)inActive).booleanValue())  ||
            (inActive instanceof String &&  "1".equals(inActive) )  ;
    }
    public DropDownRowImpl active() {
        inActive = Boolean.TRUE;
        return this;
    }
    public DropDownRowImpl inactive() {
        inActive = Boolean.FALSE;
        return this;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public Object getInActive() {
        return inActive;
    }

    public void setInActive(Object inActive) {
        this.inActive = inActive;
    }

    public String toString() {
        return label==null?"<null>":label.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DropDownRowImpl)) return false;

        DropDownRowImpl that = (DropDownRowImpl) o;


        if (value==null && that.value!=null) return false;
        if (value!=null && that.value==null) return false;

        if (value!=null &&(!value.equals(that.value))) return false;

        return true;
    }

    public int hashCode() {
        return value.hashCode();
    }

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }
}
