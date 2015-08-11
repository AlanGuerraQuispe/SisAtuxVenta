package com.aw.core.report.pdf;

import com.aw.core.report.pdf.support.FormCell;
import com.aw.core.report.pdf.support.FormColumnInfo;
import com.aw.core.report.pdf.support.FormTableRow;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 01/10/2009
 */
public class FormTable extends AWPdfTable<FormColumnInfo> {
    List<FormTableRow> rows = new ArrayList();
    FormTableRow currentRow;

    public FormTable add(FormCell formCell) {
        FormTableRow row = null;
        do {
            row = getCurrentRow();
        } while (row.isFull());
        row.add(formCell);
        return this;
    }

    public FormTableRow getCurrentRow() {
        if (currentRow == null || currentRow.isFull()) {
            currentRow = getNewRow();
            currentRow.setRowIdx(rows.size());
            rows.add(currentRow);
        }
        return currentRow;
    }

    public void setColumnsInfo(float[] colWidths) {
        FormColumnInfo[] formColumnInfos = new FormColumnInfo[colWidths.length];
        int i = 0;
        for (float colWidth : colWidths) {
            formColumnInfos[i] = new FormColumnInfo(colWidth);
            i++;
        }
        setColumnsInfo(formColumnInfos);
    }

    public FormTableRow getNewRow() {
        FormTableRow newRow = new FormTableRow(columnsInfo);
        return newRow;
    }

    public int getRowsCount() {
        return rows.size();
    }

    public List<FormTableRow> getRows() {
        return rows;
    }

    public PdfPTable getAsPdfPTable() {
        PdfPTable tabla = new PdfPTable(getColsWidths());
        tabla.setWidthPercentage(getWidthPercentaje());
        List<FormTableRow> rows = getRows();
        for (FormTableRow row : rows) {
            FormCell formCell = row.getNextCell();
            while (formCell != null) {
                PdfPCell pdfPCell = null;
                if (formCell.containsImage()){
                    pdfPCell = new PdfPCell(formCell.getImage());                    
                }else{
                    Paragraph paragraph = new Paragraph(formCell.getFormattedValue(), getFont(formCell));
                    pdfPCell = new PdfPCell(paragraph);
                }
                pdfPCell.setColspan(formCell.getColSpan());
                if (formCell.getHorizontalAlignment() != -1) {
                    pdfPCell.setHorizontalAlignment(formCell.getHorizontalAlignment());
                }else{
                    FormColumnInfo formColumnInfo = columnsInfo[formCell.getColumn()];
                    pdfPCell.setHorizontalAlignment(formColumnInfo.getHorizontalAlignment());
                }
                if (formCell.isPaintBorder() == null) {
                    FormColumnInfo formColumnInfo = columnsInfo[formCell.getColumn()];
                    if (!formColumnInfo.isPaintBorder()) {
                        pdfPCell.setBorder(Rectangle.NO_BORDER);
                    }
                } else if (!formCell.isPaintBorder()) {
                    pdfPCell.setBorder(Rectangle.NO_BORDER);
                }
                tabla.addCell(pdfPCell);
                formCell = row.getNextCell();
            }
        }
        return tabla;
    }

    private Font getFont(FormCell formCell) {
        Font font =pgInfo.getFontInfo().defaultF();
        if (formCell.getFont() != null) {
            font =  formCell.getFont();
        }
        font = new Font(font);
        if (formCell.isBold()){
            font.setStyle(Font.BOLD);
        }else{
            font.setStyle(Font.NORMAL);            
        }
        return font;
    }
}
