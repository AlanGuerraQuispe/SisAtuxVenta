package com.aw.core.report.pdf.support;

import com.aw.core.cache.loader.MetaLoader;
import com.aw.core.cache.support.DropDownFormatter;
import com.aw.core.format.DateFormatter;
import com.aw.core.format.Formatter;
import com.aw.core.format.NumberFormatter;
import com.aw.core.view.IColumnInfo;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.NullValueInNestedPathException;

/**
 * User: gmc
 * Date: 01/10/2009
 */
public class FormCell {
    protected int row = -1;
    protected int column = -1;
    protected int colSpan = 1;
    protected String text = "";
    protected Object value;
    protected int horizontalAlignment = -1;
    protected Boolean paintBorder = null;
    protected Font font;
    protected boolean bold = false;
    protected Image image;
    protected Formatter formatter;
    protected Object obj;
    protected String fieldName;
    protected FormCellType formCellType = FormCellType.TEXT;

    public FormCell() {
    }

    public FormCell(Image image) {
        this.image = image;
    }

    public FormCell(String text) {
        this.text = text;
        formCellType = FormCellType.TEXT;
    }

    public FormCell(Object value) {
        this.value = value;
        formCellType = FormCellType.VALUE;
    }

    public FormCell(Object obj, String fieldName) {
        this.obj = obj;
        this.fieldName = fieldName;
        formCellType = FormCellType.BEAN_VALUE;
    }


    public boolean hasPosition() {
        return row != -1 && column != -1;
    }

    public FormCell setColSpan(int colSpan) {
        this.colSpan = colSpan;
        return this;
    }

    public int getColSpan() {
        return colSpan;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFormattedValue() {
        Object valueObj = getValueObj();
        if (formatter != null) {
            return (String) formatter.format(valueObj);
        }
        if (valueObj ==null){
            return "";           
        }
        return valueObj.toString();
    }

    private Object getValueObj() {
        Object valueObj = null;
        try {
            valueObj = formCellType.getValue(this);
        } catch (NullValueInNestedPathException e) {
        }
        return valueObj;
    }

    FormCell setColumn(int column) {
        this.column = column;
        return this;
    }

    public int getColumn() {
        return column;
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

    private FormCell setHorizontalAlignment(int horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        return this;
    }

    public FormCell alignCenter() {
        return setHorizontalAlignment(IColumnInfo.CENTER);
    }

    public FormCell alignLeft() {
        return setHorizontalAlignment(IColumnInfo.LEFT);
    }

    public FormCell alignRigth() {
        return setHorizontalAlignment(IColumnInfo.RIGHT);
    }

    public FormCell noBorder() {
        paintBorder = false;
        return this;
    }

    public Boolean isPaintBorder() {
        return paintBorder;
    }

    public FormCell setFont(Font font) {
        this.font = font;
        return this;
    }

    public Font getFont() {
        return font;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean containsImage() {
        return image != null;
    }

    public Image getImage() {
        return image;
    }

    public FormCell formatAsDate() {
        formatter = DateFormatter.DATE_FORMATTER;
        return this;
    }

    public FormCell formatAsDateTime() {
        formatter = DateFormatter.DATE_TIME_FORMATTER;
        return this;
    }

    public FormCell formatAsMoney() {
        formatter = NumberFormatter.MONEY_FORMATTER;
        return this;
    }

    public FormCell formatUsing(Formatter formatter) {
        this.formatter = formatter;
        return this;
    }

    public FormCell formatUsing(MetaLoader metaLoader) {
        this.formatter = new DropDownFormatter(metaLoader);
        return this;
    }

    public FormCell setAsBold() {
        this.bold = true;
        return this;
    }

    public boolean isBold() {
        return bold;
    }

    private enum FormCellType {
        BEAN_VALUE {Object getValue(FormCell formCell) {
            BeanWrapper bw = new BeanWrapperImpl(formCell.obj);
            return bw.getPropertyValue(formCell.fieldName);
        }},
        VALUE {Object getValue(FormCell formCell) {
            return formCell.value;
        }},
        TEXT {Object getValue(FormCell formCell) {
            return formCell.text;
        }};

        abstract Object getValue(FormCell formCell);
    }
}
