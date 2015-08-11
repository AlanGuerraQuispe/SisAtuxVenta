package com.aw.core.report.pdf;

import com.aw.core.format.DateFormatter;
import com.aw.core.report.pdf.support.FormCell;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * User: gmc
 * Date: 05/10/2009
 */
public class PdfHeader {
    protected String imgLocation;
    protected String title;
    protected PgInfo pgInfo;
    protected int pageNumber;
    protected float xPosForTotalPages = -1;
    protected float yPosForTotalPages = -1;


    public PdfHeader() {
    }

    public PdfHeader(String title) {
        this.title = title;
    }

    public PdfPTable getAsPdfPTable() {
        PdfFontInfo pdfFontInfo = pgInfo.getFontInfo();
        FormTable formTable = new FormTable();
        formTable.setPgInfo(pgInfo);
        formTable.setWidthPercentaje(100);
        formTable.setColumnsInfo(pgInfo.isLandscape()? new float[]{10, 82, 10}:new float[]{10, 82, 12});
        FormCell logoFormCell = new FormCell("");
        Image image = getImage();
        if (image!=null){
            logoFormCell = new FormCell(image);
        }
        formTable.add(logoFormCell.noBorder())
                .add(new FormCell(title).alignCenter().noBorder().setFont(pdfFontInfo.title()))
                .add(new FormCell(getDateTimePageStr()).noBorder().setFont(pdfFontInfo.dateTimePage()));
        return formTable.getAsPdfPTable();
    }

    protected String getDateTimePageStr() {
        Date date = new Date();
        StringBuffer sb = new StringBuffer();
        sb.append("Fecha : ");
        sb.append(DateFormatter.DATE_FORMATTER.format(date));
        sb.append("\nHora   : ");
        sb.append(DateFormatter.TIME_SECONDS_FORMATTER.format(date));
        sb.append("\nPág    : ");
        sb.append(pageNumber + " / ");
        return sb.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPgInfo(PgInfo pgInfo) {
        this.pgInfo = pgInfo;
    }

    public void setCurrentPage(int pageNumber) {
        this.pageNumber = pageNumber;
    }


    public float getXPosForTotalPages() {
        if (xPosForTotalPages == -1) {
            xPosForTotalPages = pgInfo.getPgSize().width() - (pgInfo.isLandscape()? 80 : 65);
        }
        return xPosForTotalPages;
    }

    public float getYPosForTotalPages() {
        if (yPosForTotalPages == -1) {
            yPosForTotalPages = pgInfo.getPgSize().height() - 42;
        }
        return yPosForTotalPages;
    }

    public void setImgLocation(String imgLocation) {
        this.imgLocation = imgLocation;
    }

    protected Image getImage() {
        if (!StringUtils.hasText(imgLocation)){
            return null;           
        }
        Image image = null;
        try {
            image = Image.getInstance(getClass().getResource(imgLocation));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return image;
    }
}
