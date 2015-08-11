package com.aw.core.report.pdf;

import com.aw.core.report.pdf.support.GridColumnInfo;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 05/10/2009
 */
public class GridTable extends AWPdfTable<GridColumnInfo> {
    protected List values;

    public GridTable(GridColumnInfo[] columnInfos, List values) {
        this.columnsInfo = columnInfos;
        this.values = values;
    }

    public List getValues() {
        return values;
    }

    public void setValues(List values) {
        this.values = values;
    }

    public List<String> getHeader() {
        List<String> header = new ArrayList();
        for (GridColumnInfo columnInfo : columnsInfo) {
            header.add(columnInfo.getColumnHeader());
        }
        return header;
    }

    public int getRowsCount() {
        return values.size();
    }


    public String getValueAsStr(int row, int col) {
        GridColumnInfo columnInfo = columnsInfo[col];
        Object obj = getValues().get(row);
        return columnInfo.getValueAsStr(obj);
    }

    public PdfPTable getAsPdfPTable() {
        PdfPTable pdfPTable = new PdfPTable(getColsWidths());
        pdfPTable.setWidthPercentage(100);
        pdfPTable.setHeaderRows(1);
        List<String> headerList = getHeader();
        int colIndex =0;
        for (String header : headerList) {
            Paragraph paragraph = new Paragraph(header, pgInfo.getFontInfo().gridHeader());
            PdfPCell pdfPCell = new PdfPCell(paragraph);
            pdfPCell.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
            if (!columnsInfo[colIndex].isPaintBorder()){
                pdfPCell.setBorder(Rectangle.NO_BORDER);
            }
            pdfPTable.addCell(pdfPCell);
            colIndex++;
        }
        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = 0; j < getColsCount(); j++) {
                Paragraph paragraph = new Paragraph(getValueAsStr(i, j), pgInfo.getFontInfo().gridValues());
                PdfPCell pdfPCell = new PdfPCell(paragraph);
                pdfPCell.setHorizontalAlignment(columnsInfo[j].getHorizontalAlignment());
                if (!columnsInfo[j].isPaintBorder()){
                    pdfPCell.setBorder(Rectangle.NO_BORDER);                    
                }
                pdfPTable.addCell(pdfPCell);
            }
        }
        return pdfPTable;
    }

}
