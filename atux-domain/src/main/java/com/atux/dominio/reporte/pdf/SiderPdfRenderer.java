package com.atux.dominio.reporte.pdf;

import com.aw.core.report.pdf.PdfRenderer;

/**
 * User: gmc
 * Date: 08/10/2009
 */
public abstract class SiderPdfRenderer<E> extends PdfRenderer<E> {

    public SiderPdfRenderer() {
        pdfHeader = new SiderPdfHeader();
    }

    @Override
    protected void init() {
        super.init();
    }
}
