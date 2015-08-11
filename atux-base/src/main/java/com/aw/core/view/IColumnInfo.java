package com.aw.core.view;

import com.aw.core.format.Formatter;

import javax.swing.*;


/**
 * User: Julio C. Macavilca
 * Date: 09/06/2008
 */
public interface IColumnInfo {
    public final static int LEFT = SwingConstants.LEFT;
    public final static int RIGHT = SwingConstants.RIGHT;
    public final static int CENTER = SwingConstants.CENTER;

    boolean isPrintable();
    
    String getColumnHeader();
    public String getPdfColumnHeader();

    String getFieldName();

    int getWidth();
    void setWidth(int width);

    int getAlignment();

    Formatter getFormatter();

    int getReportCharsSize();

    /**
     * Get the value that will be shown in a specific column
     *
     * @param object
     * @param index  Only used in the case that the source of the rows is an array of objects
     * @return
     */
    Object getValue(Object object, int index);

    /**
     * Value formatted with DDRow if any
     * @param object
     * @param index
     * @return
     */
    Object getValueDDRFormated(Object object, int index) ;

}
