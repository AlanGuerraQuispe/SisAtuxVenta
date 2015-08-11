package com.aw.core.report.pdf;

import com.aw.core.report.pdf.support.RptColumnInfo;
import com.lowagie.text.pdf.PdfPTable;

/**
 * User: gmc
 * Date: 05/10/2009
 */
public abstract class AWPdfTable <E extends RptColumnInfo>{
    protected float widthPercentaje = 100;
    protected E[] columnsInfo;
    protected float[] colsWidths;
    protected PgInfo pgInfo;

    protected float spacingAfter = 20f;

    public float getWidthPercentaje() {
        return widthPercentaje;
    }

    public void setWidthPercentaje(float widthPercentaje) {
        this.widthPercentaje = widthPercentaje;
    }

    public E[] getColumnsInfo() {
        return columnsInfo;
    }

    public void setColumnsInfo(E[] columnsInfo) {
        this.columnsInfo = columnsInfo;
    }


    public void setColsWidths(float[] colsWidths) {
        this.colsWidths = colsWidths;
    }

    public abstract int getRowsCount();

    public int getColsCount() {
        return columnsInfo.length;
    }

    public float[] getColsWidths() {
        if (colsWidths == null){
            colsWidths = new float[columnsInfo.length];
            for (int i = 0; i < columnsInfo.length; i++) {
                colsWidths[i] = columnsInfo[i].getWidth();
            }
        }
        return colsWidths;
    }

    public abstract PdfPTable getAsPdfPTable();

    public PgInfo getPgInfo() {
        return pgInfo;
    }

    public void setPgInfo(PgInfo pgInfo) {
        this.pgInfo = pgInfo;
    }

    public float getSpacingAfter() {
        return spacingAfter;
    }

    public void setSpacingAfter(float spacingAfter) {
        this.spacingAfter = spacingAfter;
    }
}
