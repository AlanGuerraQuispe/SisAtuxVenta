package com.aw.swing.mvp.binding.component.support;

import java.awt.*;

/**
 * User: gmc
 * Date: 19/05/2009
 */
public abstract class ColumnActionListener<E> {
    /**
     * @param actionEvent
     * @param rowObj
     * @param newValue
     * @return TRUE si la tabla requiere ser pintada de nuevo
     */
    public abstract boolean actionPerformed(AWTEvent actionEvent, E rowObj, Object newValue);

}