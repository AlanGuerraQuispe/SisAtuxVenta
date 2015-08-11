package com.aw.core.report.pdf.support;

/**
 * User: gmc
 * Date: 01/10/2009
 */
public class SeparatorCell extends FormCell{
    public SeparatorCell() {
        text = " ";
    }
    SeparatorCell(int column){
        this();
        this.column = column;
    }

}