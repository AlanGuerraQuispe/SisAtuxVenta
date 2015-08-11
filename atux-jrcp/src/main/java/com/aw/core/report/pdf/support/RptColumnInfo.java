package com.aw.core.report.pdf.support;

import com.aw.core.view.IColumnInfo;
import com.lowagie.text.pdf.PdfPTable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: gmc
 * Date: 05/10/2009
 */
public class RptColumnInfo {
    protected Log logger = LogFactory.getLog(getClass());
    protected int horizontalAlignment = IColumnInfo.LEFT;
    protected float width = -1;
    protected Boolean paintBorder =true;

    public RptColumnInfo() {
    }

    public RptColumnInfo(float width, int horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        this.width = width;
    }


    public int getHorizontalAlignment() {
        switch (horizontalAlignment) {
            case IColumnInfo.RIGHT:
                return PdfPTable.ALIGN_RIGHT;
            case IColumnInfo.LEFT:
                return PdfPTable.ALIGN_LEFT;
            case IColumnInfo.CENTER:
                return PdfPTable.ALIGN_CENTER;
        }
        return horizontalAlignment;
    }

    public RptColumnInfo setHorizontalAlignment(int horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        return this;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public RptColumnInfo alignCenter() {
        setHorizontalAlignment(IColumnInfo.CENTER);
        return this;
    }

    public RptColumnInfo alignLeft() {
        setHorizontalAlignment(IColumnInfo.LEFT);
        return this;
    }

    public RptColumnInfo alignRigth() {
        setHorizontalAlignment(IColumnInfo.RIGHT);
        return this;
    }
    
    public RptColumnInfo noBorder(){
        paintBorder = false;
        return this;
    }

    public Boolean isPaintBorder() {
        return paintBorder;
    }

}
