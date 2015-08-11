package com.atux.dominio.reporte.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Wilmer Segura
 * Date: 01/09/2009
 * Time: 10:44:54 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class PdfReport {
    private Document documento;
    private PdfWriter pdfWriter;
    private PdfHeaderFooter pdfHeaderFooter;
    private Rectangle page = PageSize.A4;
    private Object bnDatos;
    private String nameReport = "reporte";
    private String tipo = "";
    private PdfMargen pdfMargen;

    private Boolean tieneCopia = false;
    private Integer nroCopias = 0;
    private String tmpDir = System.getProperty("java.io.tmpdir");

    public PdfReport(Object bnDatos, String nameFile, String tipo) {
        setBnDatos(bnDatos);
        setNameReport(nameFile);
        setTipo(tipo);
    }

    public File imprimirPdf() {
        crearDocumento();
        File file = crearInstanciaPdfWriter();

        configurarImpresion();

        documento.open();
        try {
            PdfPTable pdfContenido = obtenerContenido();
            documento.add(pdfContenido);
        }
        catch (Exception de) {
            de.printStackTrace();
        }
        documento.close();

        if (getTieneCopia()) {
            imprimirCopias(getNameReport());
        }
        return file;
    }

    private void crearDocumento() {
        documento = new Document();
    }

    private File crearInstanciaPdfWriter() {
        PdfWriter writer = null;
        String fileName = getTmpDir() + getNameReport() + ".pdf";
        try {
            writer = PdfWriter.getInstance(documento, new FileOutputStream(fileName));
        } catch (DocumentException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
//        writer.setPageEvent(new HeaderOrdenCompra());
        pdfWriter = writer;

        return new File(fileName);
    }


    private void configurarImpresion() {
//        validarDatosNulos(bnDatosResumen);
        configurarDocumento();
//        setDocumento();
        configurarEncabezado();
        configurarMargenesPdf();
//        imprimirPdf();
    }

    protected void configurarEncabezado() {
        if (pdfHeaderFooter != null) {
            getPdfWriter().setPageEvent(pdfHeaderFooter);
        }
    }


    protected void configurarMargenesPdf() {
        if (pdfMargen != null) {
            documento.setMargins(pdfMargen.getMargenLeft(), pdfMargen.getMargenRigth(), pdfMargen.getMargenTop(), pdfMargen.getMargenBottom());
        } else {
            documento.setMargins(10, 10, 45, 45);
        }
        documento.leftMargin();
        documento.rightMargin();
    }

    protected abstract PdfPTable obtenerContenido() throws DocumentException;


    public void imprimirCopias(String documentoACopiar) {
        Integer numeroCopias = getNroCopias();
        Integer control = 0;
        while (control < numeroCopias) {
            control++;
            imprimirCopia(documentoACopiar, control.toString());
        }
    }

    private void imprimirCopia(String documentoACopiar, String version) {
        try {
            // we create a reader for a certain document
            PdfReader reader = new PdfReader(getTmpDir() + documentoACopiar + ".pdf");
            int n = reader.getNumberOfPages();
            // we create a stamper that will copy the document to a new file
            PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(getTmpDir() + documentoACopiar + "_copy_" + version + ".pdf"));
            // adding some metadata

            int i = 0;
            PdfContentByte under;
            PdfContentByte over;
            Image img = Image.getInstance(getClass().getResource("/images/mark.gif"));
            img.scalePercent(80.0f);
            img.setAbsolutePosition(0, 250);
            while (i < n) {
                i++;
                // watermark under the existing page
                under = stamp.getUnderContent(i);
                under.addImage(img);

            }
            under = stamp.getUnderContent(1);
            stamp.close();
        } catch (Exception de) {
            de.printStackTrace();
        }
    }


    public void mostrarPdf() {
        mostrarPdf(getTmpDir() + "/" + getNameReport() + ".pdf");
    }

    public void mostrarCopiaPdf() {
        mostrarPdf(getTmpDir() + "/" + getNameReport() + "_copy_1.pdf");
    }

    public static void mostrarPdf(String pdfFileFullName) {
//        logger.debug(pdfFileFullName);
        Desktop desktop = Desktop.getDesktop();
        File file = new File(pdfFileFullName);
        if (desktop.isDesktopSupported()) {
            try {
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public Object getBnDatos() {
        return bnDatos;
    }

    public void setBnDatos(Object bnDatos) {
        this.bnDatos = bnDatos;
    }

    public String getTmpDir() {
        return tmpDir;
    }

    public void setTmpDir(String tmpDir) {
        this.tmpDir = tmpDir;
    }

    public String getNameReport() {

        return nameReport;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {

        return tipo;
    }


    public void setNameReport(String nameReport) {
        this.nameReport = nameReport;
    }

    public Boolean getTieneCopia() {
        return tieneCopia;
    }

    public void setTieneCopia(Boolean tieneCopia) {
        this.tieneCopia = tieneCopia;
    }

    public Integer getNroCopias() {
        return nroCopias;
    }

    public void setNroCopias(Integer nroCopias) {
        setTieneCopia(true);
        this.nroCopias = nroCopias;
    }

    private void configurarDocumento() {
        documento.setPageSize(page);
    }

    public PdfWriter getPdfWriter() {
        return pdfWriter;
    }

    public void setPdfWriter(PdfWriter pdfWriter) {
        this.pdfWriter = pdfWriter;
    }

    public PdfHeaderFooter getPdfHeaderFooter() {
        return pdfHeaderFooter;
    }

    public void setPdfHeaderFooter(PdfHeaderFooter pdfHeaderFooter) {
        this.pdfHeaderFooter = pdfHeaderFooter;
    }

    public Rectangle getPage() {
        return page;
    }

    public void setPage(Rectangle page) {
        this.page = page;
    }

    public void setPdfMargen(PdfMargen pdfMargen) {
        this.pdfMargen = pdfMargen;
    }
}
