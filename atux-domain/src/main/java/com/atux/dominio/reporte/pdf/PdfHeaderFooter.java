package com.atux.dominio.reporte.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Wilmer Segura
 * Date: 30/08/2009
 * Time: 06:51:22 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class PdfHeaderFooter extends PdfPageEventHelper {

    private PdfTemplate pdfTemplate;
    private Object data;
    private String tipo;
    private BaseFont font;
    private Integer numberPage;
    private float posXTotalnumberPage=0;
    private float posYTotalnumberPage=0;


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(Integer numberPage) {
        this.numberPage = numberPage;
    }

    public Object getData() {
        return data;
    }

    public void setPosXTotalnumberPage(float posXTotalnumberPage) {
        this.posXTotalnumberPage = posXTotalnumberPage;
    }

    public void setPosYTotalnumberPage(float posYTotalnumberPage) {
        this.posYTotalnumberPage = posYTotalnumberPage;
    }

    protected PdfHeaderFooter(Object obj) {
        data = obj;
    }

    protected PdfHeaderFooter() {

    }

    public abstract PdfPTable createHeader();

    public abstract PdfPTable createFooter();

    @Override
    public void onEndPage(PdfWriter pdfWriter, Document document) {
        try {
            PdfContentByte contentByte;
            if ((posXTotalnumberPage != 0) && (posYTotalnumberPage != 0)) {
                contentByte = registerPaginaTotal(pdfWriter);
            } else {
                contentByte = pdfWriter.getDirectContent();
            }


            Rectangle page = document.getPageSize();
            Integer numeroPagina = document.getPageNumber();
            setNumberPage(numeroPagina);
            PdfPTable head = createHeader(); // creamos el encabezado
            head.setTotalWidth(page.width() - document.leftMargin() - document.rightMargin());
            head.writeSelectedRows(0, -1, document.leftMargin(), page.height() - document.topMargin() + head.getTotalHeight(),
                    contentByte);

            PdfPTable foot = createFooter();  // creamos el pie pagina
            foot.setTotalWidth(page.width() - document.leftMargin() - document.rightMargin());
            foot.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(),
                    contentByte);
        }
        catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    @Override
    public void onOpenDocument(PdfWriter pdfWriter, Document document) {

        try {
            pdfTemplate = pdfWriter.getDirectContent().createTemplate(100, 100);
               BaseFont bf = FontFactory.getFont(FontFactory.HELVETICA_BOLD).getCalculatedBaseFont(true);
            font = BaseFont.createFont("Helvetica", bf.getEncoding(), false);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

      @Override
    public void onCloseDocument(PdfWriter pdfWriter, Document document) {
        pdfTemplate.beginText();
        pdfTemplate.setFontAndSize(font, 7);
        pdfTemplate.showText(Integer.toString(pdfWriter.getPageNumber() - 1));
        pdfTemplate.endText();
    }

    private PdfContentByte registerPaginaTotal(PdfWriter pdfWriter) {

        PdfContentByte cb = pdfWriter.getDirectContent();
        cb.saveState();
        String text = "";
        float textSize = font.getWidthPoint(text, 8);
        float textBase = posYTotalnumberPage;
        cb.beginText();
        cb.setFontAndSize(font, 8);
        cb.showText(text);
        cb.endText();
        cb.addTemplate(pdfTemplate, posXTotalnumberPage + textSize, textBase);
        cb.saveState();
        return cb;
    }


}
