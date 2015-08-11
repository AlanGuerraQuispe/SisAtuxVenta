package com.aw.swing.mvp.grid;

import java.awt.*;

/**
 * User: gmc
 * Date: 04/08/2009
 */
public abstract class CellColorChanger<E> {
    protected Color defaultColor;

    public Color getBackGround(E rowObj, int row, String colFieldName){
        return defaultColor;
    }

    public Color getForeGround(E rowObj, int row, String colFieldName){
        return null;       
    }

    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
    }
}
