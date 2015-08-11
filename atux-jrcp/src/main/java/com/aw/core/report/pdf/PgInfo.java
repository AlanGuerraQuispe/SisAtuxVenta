package com.aw.core.report.pdf;

import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;

/**
 * User: gmc
 * Date: 01/10/2009
 */
public class PgInfo {
    private boolean landscape = false;
    private Rectangle pgSize= PageSize.A4;
    private PdfMargin pdfMargin = new PdfMargin();
    private PdfFontInfo pdfFontInfo = new PdfFontInfo();

    public Rectangle getPgSize() {
        if (landscape){
            return pgSize.rotate();           
        }
        return pgSize;
    }

    public PgInfo setAsLandScape() {
        landscape =true;
        return this;
    }

    public PgInfo setPdfMargin(PdfMargin pdfMargin) {
        this.pdfMargin = pdfMargin;
        return this;
    }


    public PdfMargin getPdfMargin() {
        return pdfMargin;
    }

    public PdfFontInfo getFontInfo() {
        return pdfFontInfo;
    }

    public void setFontInfo(PdfFontInfo pdfFontInfo) {
        this.pdfFontInfo = pdfFontInfo;
    }

    //    protected int columnWidth = 128;
//    protected int totalLinesPerPage;
//    protected double titleCharWidhtRatio;
//
//    public int translateX(int x, int refXWidth) {
//        return x * columnWidth / refXWidth;
//    }
//
//    public double getColumnCharWidth(int refXWidth) {
//        return 1.0 * refXWidth / columnWidth;
//    }
//
//    public int getColumnWidth() {
//        return columnWidth;
//    }
//
//    public void setColumnWidth(int columnWidth) {
//        this.columnWidth = columnWidth;
//    }
//
//    public int getTotalLinesPerPage() {
//        return totalLinesPerPage;
//    }
//
//    public void setTotalLinesPerPage(int totalLinesPerPage) {
//        this.totalLinesPerPage = totalLinesPerPage;
//    }
//
//    public double getTitleCharWidhtRatio() {
//        return titleCharWidhtRatio;
//    }
//
//    public void setTitleCharWidhtRatio(double titleCharWidhtRatio) {
//        this.titleCharWidhtRatio = titleCharWidhtRatio;
//    }


    public boolean isLandscape() {
        return landscape;
    }
}

