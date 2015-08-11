package com.aw.swing.mvp.binding.component.support.table.function;

import com.aw.swing.mvp.grid.GridProvider;

/**
 * User: gmc
 * Date: 20/07/2009
 */
public abstract class GridFunction<E> {
    protected GridProvider gdp;
    public abstract E execute();

    protected String label="";

    protected GridFunction() {

    }

    public void setGdp(GridProvider gdp) {
        this.gdp = gdp;
    }

    public String getLabel() {
        return label;
    }

    public GridFunction setLabel(String label) {
        this.label = label;
        return this;
    }
}
