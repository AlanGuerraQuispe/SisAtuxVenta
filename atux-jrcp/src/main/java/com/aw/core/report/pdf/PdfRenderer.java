package com.aw.core.report.pdf;

import com.aw.core.exception.AWSystemException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: gmc
 * Date: 01/10/2009
 */
public abstract class PdfRenderer<E> {
    protected E backBean;
    protected PdfWriter pdfWriter;
    protected PdfHeader pdfHeader = new PdfHeader();
    protected AWPdfTable pdfFooter;
    protected Document document;
    protected PgInfo pgInfo;
    protected String fileName = "PdfReport";
    protected File file;
    protected boolean addDateTimeToFileName = true;
    protected String fileAbsolutePath = "";

    public PdfRenderer() {
    }

    /**
     * Use this constructor when the opening and closing of the document will be manage by other process
     *
     * @param document
     */
    public PdfRenderer(Document document) {
        this();
        this.document = document;
    }


    public final void render() {
        try {
            init();
            createDocument();
            openDocument();
            renderInternal();
            closeDocument();
        } catch (Throwable e1) {
            throw new AWSystemException("Problems rendering:", e1);
        }
    }

    protected void init() {
        if (pgInfo == null) {
            pgInfo = new PgInfo();
        }
        pdfHeader.setPgInfo(pgInfo);
        if (pdfFooter != null) {
            pdfFooter.setPgInfo(pgInfo);
        }
    }

    public void createDocument() {
        PdfMargin pdfMargin = pgInfo.getPdfMargin();
        document = new Document(pgInfo.getPgSize(), pdfMargin.getLeft(), pdfMargin.getRight(), pdfMargin.getTop(), pdfMargin.getBottom());
        document.setMarginMirroring(true);
    }

    private void openDocument() throws DocumentException, IOException {
        file = File.createTempFile(getFullFileName(), ".pdf");
//        file.deleteOnExit();
        fileAbsolutePath = file.getAbsolutePath();
        pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));
        PdfEventListener pdfEventListener = new PdfEventListener();
        pdfEventListener.setPdfHeader(pdfHeader);
        pdfEventListener.setPdfFooter(pdfFooter);
        pdfEventListener.setPgInfo(pgInfo);
        pdfWriter.setPageEvent(pdfEventListener);
        document.open();
    }

    protected void addToDocument(AWPdfTable awPdfTable) {
        awPdfTable.setPgInfo(pgInfo);
        PdfPTable tabla = awPdfTable.getAsPdfPTable();
        tabla.setWidthPercentage(awPdfTable.getWidthPercentaje());
        tabla.setSpacingAfter(awPdfTable.getSpacingAfter());
        try {
            document.add(tabla);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    protected abstract void renderInternal();

    private void closeDocument() {
        document.close();
    }


    public void end() {
//            document.close();
//            if (!toFileOnly)
//                ReportUtils.showReport(file.getAbsolutePath());
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public E getBackBean() {
        return backBean;
    }

    public void setBackBean(E backBean) {
        this.backBean = backBean;
    }

    public void setTitle(String title) {
        pdfHeader.setTitle(title);
    }

    public void setPgInfo(PgInfo pgInfo) {
        this.pgInfo = pgInfo;
    }

    public String getFullFileName() {
        String fullFileName = fileName;
        if (addDateTimeToFileName) {
            fullFileName += new SimpleDateFormat("ddMMyyyy_kkmm_").format(new Date());
        }
        return fullFileName;
    }

    public File getFile(){
        return  new File(fileAbsolutePath);        
    }

    public void showPdf() {
        Desktop desktop = Desktop.getDesktop();
        File file = new File(fileAbsolutePath);
        if (desktop.isDesktopSupported()) {
            try {
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPdfFooter(AWPdfTable pdfFooter) {
        this.pdfFooter = pdfFooter;
    }
}
