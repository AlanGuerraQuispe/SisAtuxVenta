package com.aw.core.report.pdf;

import com.lowagie.text.Font;

/**
 * User: gmc
 * Date: 06/10/2009
 */
public class PdfFontInfo {
    private Font title = new Font(Font.HELVETICA, 12) ;
    private Font dateTimePage = new Font(Font.HELVETICA, 6);
    private Font formLabels = new Font(Font.HELVETICA, 7);
    private Font formValues = new Font(Font.HELVETICA, 7);
    private Font gridHeader = new Font(Font.HELVETICA, 7,Font.BOLD);
    private Font gridValues = new Font(Font.HELVETICA, 7);
    private Font defaultF = new Font(Font.HELVETICA, 7);

    public Font title() {
        return title;
    }

    public void setTitle(Font title) {
        this.title = title;
    }

    public Font dateTimePage() {
        return dateTimePage;
    }

    public void setDateTimePage(Font dateTimePage) {
        this.dateTimePage = dateTimePage;
    }

    public Font formLabels() {
        return formLabels;
    }

    public void setFormLabels(Font formLabels) {
        this.formLabels = formLabels;
    }

    public Font formValues() {
        return formValues;
    }

    public void setFormValues(Font formValues) {
        this.formValues = formValues;
    }

    public Font gridHeader() {
        return gridHeader;
    }

    public void setGridHeader(Font gridHeader) {
        this.gridHeader = gridHeader;
    }

    public Font gridValues() {
        return gridValues;
    }

    public Font defaultF() {
        return defaultF;
    }

    public void setGridValues(Font gridValues) {
        this.gridValues = gridValues;
    }

}
