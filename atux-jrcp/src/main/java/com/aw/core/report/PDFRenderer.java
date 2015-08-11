package com.aw.core.report;

import com.aw.core.report.custom.RptRenderer;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Para escribir imagenes paragraph.add( new Chunk(Image.getInstance("save.gif"),0, 0))
 * <p/>
 * User: JCM
 * Date: 21/11/2007
 */
public class PDFRenderer extends RptRenderer {
    public static int DEF_PDF_COL_WIDTH_PORTRAIT = 87;
    public static int DEF_PDF_COL_WIDTH_LANDSCAPE = 128;

    Document document;
    boolean documentManagedByOthers = false;
    Paragraph paragraph = new Paragraph();
    private Font fontBoldDoubleWidth = new Font(Font.COURIER, 12, Font.BOLD);
    private Font fontDoubleWidth = new Font(Font.COURIER, 12, Font.NORMAL);
    private Font fontBold = new Font(Font.COURIER, 10, Font.BOLD);
    private Font fontNormal = new Font(Font.COURIER, 10, Font.NORMAL);
    private Font fontTitle = new Font(Font.COURIER, 14, Font.BOLD);
    File file;

    public PDFRenderer() {
        allWithLeftAlign = true;
    }

    /**
     * Use this constructor when the opening and closing of the document will be manage by other process
     *
     * @param document
     */
    public PDFRenderer(Document document) {
        this();
        this.document = document;
        documentManagedByOthers = true;
    }

    public String start(String name) throws Exception {
             
//        if (document == null) {      //todo revisar si no afecta en la exportacion de PDF en los grids para WEB y SWING
            file = File.createTempFile("Rpt" + (name==null?"":name), ".pdf");
            file.deleteOnExit();
            //fullFilePath = ReportUtils.buildFullTmpFilePath("Rpt" + name, ".pdf");
            if (document == null)
                document = new Document(landscape ? PageSize.A4.rotate() : PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
//        }
        pgInfo = new PgInfo();
        pgInfo.setColumnWidth(landscape ? DEF_PDF_COL_WIDTH_LANDSCAPE : DEF_PDF_COL_WIDTH_PORTRAIT);
        pgInfo.setTitleCharWidhtRatio(fontTitle.getCalculatedSize() / fontNormal.getCalculatedSize());
        //pgInfo.setTotalLinesPerPage(landscape?31  :50 );
        //pescate 25/08/2008
        pgInfo.setTotalLinesPerPage(landscape ? 31 : 47);
        //==================

        return file.getAbsolutePath();
    }

    public void end() {
        if (!documentManagedByOthers) {
            document.close();
            if (!toFileOnly)
                ReportUtils.showReport(file.getAbsolutePath());
        }
    }

    public int writeTitle(String str) throws Exception {
        int retval = super.writeTitle(str);
        paragraph.add(new Chunk(str, fontTitle));
        return retval;
    }

    public int writeDoubleWidth(String str) throws Exception {
        int retval = super.writeDoubleWidth(str);
        paragraph.add(new Chunk(str, fontDoubleWidth));
        return retval;
    }

    public int writeBoldDoubleWidth(String str) {
        int retval = super.writeBoldDoubleWidth(str);
        paragraph.add(new Chunk(str, fontBoldDoubleWidth));
        return retval;
    }

    public int writeBold(String str) throws Exception {
        int retval = super.writeBold(str);
        paragraph.add(new Chunk(str, fontBold));
        return retval;
    }

    public int write(String str) throws Exception {
        int retval = super.write(str);
        paragraph.add(new Chunk(str, fontNormal));
        return retval;
    }

    public void writeln() throws Exception {
        super.writeln();
        if (paragraph.getChunks().size() == 0)
            paragraph.add(new Chunk(" ", fontNormal));
        document.add(paragraph);
        paragraph = new Paragraph();
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    public Document getDocument() {
        return document;
    }
}
