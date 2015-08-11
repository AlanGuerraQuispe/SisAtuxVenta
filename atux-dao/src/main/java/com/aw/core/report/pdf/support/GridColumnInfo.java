package com.aw.core.report.pdf.support;

import com.aw.core.cache.loader.MetaLoader;
import com.aw.core.cache.support.DropDownFormatter;
import com.aw.core.format.DateFormatter;
import com.aw.core.format.Formatter;
import com.aw.core.format.NumberFormatter;
import com.aw.core.view.IColumnInfo;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.util.StringUtils;

/**
 * User: gmc
 * Date: 05/10/2009
 */
public class GridColumnInfo extends RptColumnInfo {
    protected String columnHeader;
    protected String fieldName;
    protected Formatter formatter;
    protected int charsSize = -1;

    public GridColumnInfo(String columnHeader, float width){
        this(columnHeader,"",width);
    }

    public GridColumnInfo(String columnHeader, String fieldName, float width) {
        this(columnHeader, fieldName, width, IColumnInfo.LEFT);
    }

    public GridColumnInfo(String columnHeader, String fieldName, float width, int alignment) {
        super(width, alignment);
        this.columnHeader = columnHeader;
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }


    protected Object getValue(Object object) {
        if (!StringUtils.hasText(fieldName)){
            return "";           
        }
        BeanWrapper bw = new BeanWrapperImpl(object);
        Object value = null;
        try {
            value = bw.getPropertyValue(fieldName);
        } catch (NullValueInNestedPathException e)  {
            value = "";
        }
        return value;
    }

    public String getValueAsStr(Object object) {
        Object obj = getValue(object);
        if (formatter != null) {
            return (String) formatter.format(obj);
        }
        String strValue =obj == null ? "" : obj.toString();
        if (charsSize!=-1 && charsSize < strValue.length()){
            strValue = strValue.substring(0,charsSize);
        }
        return strValue;
    }


    public String getColumnHeader() {
        return columnHeader;
    }

    public void setColumnHeader(String columnHeader) {
        this.columnHeader = columnHeader;
    }

    public GridColumnInfo formatAsDate() {
        formatter = DateFormatter.DATE_FORMATTER;
        return this;
    }

    public GridColumnInfo formatAsMoney() {
        formatter = NumberFormatter.MONEY_FORMATTER;
        return this;
    }

    public GridColumnInfo formatAsNumberFloatWithGroup() {
        formatter = NumberFormatter.FLOAT_FORMATTER_W_GRP_SYM;
        return this;
    }

    public GridColumnInfo setChrsSize(int charsSize) {
        this.charsSize = charsSize;
        return this;
    }



    public GridColumnInfo formatUsing(Formatter formatter) {
        this.formatter = formatter;
        return this;
    }

    public GridColumnInfo formatUsing(MetaLoader metaLoader) {
        this.formatter = new DropDownFormatter(metaLoader);
        return this;
    }

}