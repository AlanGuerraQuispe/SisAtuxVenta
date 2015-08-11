package com.aw.core.report.pdf;

import junit.framework.TestCase;


/**
 * User: gmc
 * Date: 01/10/2009
 */
public class TestPdfRenderer extends TestCase {

    public void testCrearPdf() {
        BNIngreso bean = getBNIngreso();
        PdfRenderer pdfRenderer = new PdfIngresoRenderer();
        pdfRenderer.setBackBean(bean);
        pdfRenderer.render();
    }

    public BNIngreso getBNIngreso() {
        BNIngreso bn = new BNIngreso();
        return bn;
    }
}
