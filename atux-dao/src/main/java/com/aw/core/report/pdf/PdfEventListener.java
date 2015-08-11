package com.aw.core.report.pdf;

import com.aw.core.exception.AWSystemException;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;


/**
 * User: gmc
 * Date: 05/10/2009
 */
public class PdfEventListener extends PdfPageEventHelper {
    PdfHeader pdfHeader;
    AWPdfTable pdfFooter;
    PgInfo pgInfo;
    PdfTemplate totalPagesTemplate;
    BaseFont totalPagesFont;

    @Override
    public void onOpenDocument(PdfWriter pdfWriter, Document document) {
        createTemplateForTotalPages(pdfWriter);
    }

    private void createTemplateForTotalPages(PdfWriter pdfWriter) {
        try {
            totalPagesTemplate = pdfWriter.getDirectContent().createTemplate(100, 100);
            BaseFont bf = FontFactory.getFont(FontFactory.HELVETICA_BOLD).getCalculatedBaseFont(true);
            totalPagesFont = BaseFont.createFont("Helvetica", bf.getEncoding(), false);
        } catch (Throwable e) {
            throw new AWSystemException("Problems creating font for total pages template",e);
        }
    }

    public void onEndPage(PdfWriter writer, Document document) {
        paintTotalPagesTemplate(writer);
        Rectangle page = document.getPageSize();
        PdfContentByte cb = writer.getDirectContent();
        pdfHeader.setCurrentPage(writer.getPageNumber());
        PdfPTable header = pdfHeader.getAsPdfPTable();
        header.setTotalWidth(page.width() - document.leftMargin() - document.rightMargin());
        header.writeSelectedRows(0, -1, document.leftMargin(), page.height() - document.topMargin() + header.getTotalHeight(), cb);

        if(pdfFooter!=null){
            PdfPTable footer = getFooter();
            footer.setTotalWidth(page.width() - document.leftMargin() - document.rightMargin());
            footer.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin()-footer.getTotalHeight(),cb);
        }
    }

    PdfPTable footer;
    private PdfPTable getFooter() {
        if (footer==null){
            footer =pdfFooter.getAsPdfPTable();
        }
        return footer; 
    }

    private void paintTotalPagesTemplate(PdfWriter writer) {
        PdfContentByte cb = writer.getDirectContent();
        cb.saveState();
        cb.beginText();
        cb.setFontAndSize(totalPagesFont, pgInfo.getFontInfo().dateTimePage().getCalculatedSize());
        cb.showText("");
        cb.endText();
        cb.addTemplate(totalPagesTemplate, pdfHeader.getXPosForTotalPages(), pdfHeader.getYPosForTotalPages());
        cb.restoreState();
    }

    @Override
    public void onCloseDocument(PdfWriter pdfWriter, Document document) {
        totalPagesTemplate.beginText();
        totalPagesTemplate.setFontAndSize(totalPagesFont, pgInfo.getFontInfo().dateTimePage().getCalculatedSize());
        totalPagesTemplate.setTextMatrix(0, 0);
        totalPagesTemplate.showText(Integer.toString(pdfWriter.getPageNumber() - 1));
        totalPagesTemplate.endText();
    }

    public void setPdfHeader(PdfHeader pdfHeader) {
        this.pdfHeader = pdfHeader;
    }

    public void setPgInfo(PgInfo pgInfo) {
        this.pgInfo = pgInfo;
    }

    public void setPdfFooter(AWPdfTable pdfFooter) {
        this.pdfFooter = pdfFooter;
    }
}
