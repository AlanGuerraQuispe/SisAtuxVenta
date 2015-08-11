package com.aw.core.report;

import com.aw.core.report.custom.RptField;
import com.aw.core.report.custom.RptRenderer;
import com.lowagie.text.Font;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Para escribir imagenes paragraph.add( new Chunk(Image.getInstance("save.gif"),0, 0))
 * <p/>
 * User: JCM
 * Date: 21/11/2007
 */
public class ExcelRenderer extends RptRenderer {
    //public static int DEF_PDF_COL_WIDTH_PORTRAIT = 87;
    //public static int DEF_PDF_COL_WIDTH_LANDSCAPE = 128;

    HSSFWorkbook document;
    HSSFSheet sheet;
    HSSFRow row;
    int rowNumber = 0;
    int colNumber = 0;
    boolean documentManagedByOthers = false;

    HSSFCellStyle boldStyle;
    HSSFCellStyle titleStyle;
    HSSFCellStyle doubleStyle;
    HSSFCellStyle boldDoubleStyle;
    HSSFCellStyle normalStyle;
    private Font fontTitle = new Font(Font.COURIER, 14, Font.BOLD);
    private Font fontNormal = new Font(Font.COURIER, 10, Font.NORMAL);
    //String fullFilePath;
    private File file;

    public ExcelRenderer() {
        allWithLeftAlign = true;
    }

    /**
     * Use this constructor when the opening and closing of the document will be manage by other process
     *
     * @param document documento excel
     */
    public ExcelRenderer(HSSFWorkbook document) {
        this();
        this.document = document;
        documentManagedByOthers = true;
    }

    public String start(String name) throws Exception {
        String fullFilePath = null;
        if (!documentManagedByOthers) {
            file = File.createTempFile("Rpt" + (name==null?"":name), ".xls");
            file.deleteOnExit();
            fullFilePath = file.getAbsolutePath();

            document = new HSSFWorkbook();
        }
        // Init sheet and first row
        sheet = document.createSheet("Sheet N1");
        row = sheet.createRow(rowNumber++);


        pgInfo = new PgInfo();
        pgInfo.setColumnWidth(128); //cualquier numero
        pgInfo.setTitleCharWidhtRatio(fontTitle.getCalculatedSize() / fontNormal.getCalculatedSize());
        pgInfo.setTotalLinesPerPage(Integer.MAX_VALUE);

        return fullFilePath;
    }

    public void end() throws IOException {
        if (!documentManagedByOthers) {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            document.write(fileOutputStream);
            fileOutputStream.close();
            if (!toFileOnly)
                ReportUtils.showReport(file.getAbsolutePath());
        }
    }

    public int writeTitle(String str) throws Exception {
        int retval = super.writeTitle(str);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue(str);
        decorate(cell, getTitleStyle());
        return retval;
    }

    public int writeDoubleWidth(String str) throws Exception {
        int retval = super.writeDoubleWidth(str);
        HSSFCell cell = row.createCell((short) colNumber++);
        cell.setCellValue(Double.parseDouble(str));
        decorate(cell, getDoubleStyle());
        return retval;
    }

    public int writeBoldDoubleWidth(String str) {
        int retval = super.writeBoldDoubleWidth(str);
        HSSFCell cell = row.createCell((short) colNumber++);
        cell.setCellValue(Double.parseDouble(str));
        decorate(cell, getBoldDoubleStyle());
        return retval;
    }

    public int writeBold(String str) throws Exception {
        int retval = super.writeBold(str);
        HSSFCell cell = row.createCell((short) colNumber++);
        cell.setCellValue(str);
        decorate(cell, getBoldStyle());
        return retval;
    }

    public int write(String str) throws Exception {
        int retval = super.write(str);
        HSSFCell cell = row.createCell((short) colNumber++);
        cell.setCellValue(str);
        decorate(cell, getNormalStyle());
        return retval;
    }

    public void writeln() throws Exception {
        super.writeln();
        row = sheet.createRow(rowNumber++);
        colNumber = 0;
    }

    private void decorate(HSSFCell cell, HSSFCellStyle style) {
        cell.setCellStyle(style);
    }

    private HSSFCellStyle getBoldStyle() {
        if (boldStyle == null) {
            boldStyle = document.createCellStyle();
            HSSFFont font = document.createFont();
            font.setFontHeightInPoints((short) 10);
            font.setFontName("Courier");
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            boldStyle.setFont(font);
        }
        return boldStyle;
    }

    private HSSFCellStyle getTitleStyle() {
        if (titleStyle == null) {
            titleStyle = document.createCellStyle();
            HSSFFont font = document.createFont();
            font.setFontHeightInPoints((short) 14);
            font.setFontName("Courier");
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            titleStyle.setFont(font);
        }
        return titleStyle;
    }

    public HSSFCellStyle getDoubleStyle() {
        if (doubleStyle == null) {
            doubleStyle = document.createCellStyle();
            HSSFFont font = document.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setFontName("Courier");
            font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
            titleStyle.setFont(font);
        }
        return doubleStyle;
    }

    public HSSFCellStyle getBoldDoubleStyle() {
        if (boldDoubleStyle == null) {
            boldDoubleStyle = document.createCellStyle();
            HSSFFont font = document.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setFontName("Courier");
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            titleStyle.setFont(font);
        }
        return boldDoubleStyle;
    }

    public HSSFCellStyle getNormalStyle() {
        if (normalStyle == null) {
            normalStyle = document.createCellStyle();
            HSSFFont font = document.createFont();
            font.setFontHeightInPoints((short) 10);
            font.setFontName("Courier");
            font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
            normalStyle.setFont(font);
        }
        return normalStyle;
    }


    public int writeX(RptField... rptFields) throws Exception {
        int retval = 0;
        for (RptField field : rptFields) {
            if (field.isFormatSeparator()) continue;
            String value = field.getStr();
            if (field.getFont() == 'T') retval += writeTitle(value);
            else if (field.getFont() == 'B') retval += writeBold(value);
            else if (field.getFont() == 'W') retval += writeBoldDoubleWidth(value);
            else if (field.getFont() == 'D') retval += writeDoubleWidth(value);
            else if (field.getFont() == 'N') retval += write(value);
            else throw new IllegalArgumentException("Font invalid:" + field.getAlignement());
        }
        return retval;
    }

}