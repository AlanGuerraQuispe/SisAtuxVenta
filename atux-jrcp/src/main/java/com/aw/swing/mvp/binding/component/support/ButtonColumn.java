package com.aw.swing.mvp.binding.component.support;

import com.aw.core.cache.loader.MetaLoader;

/**
 * User: gmc
 * Date: 29/04/2009
 */
public class ButtonColumn extends ColumnInfo {
    String text = null;


    public ButtonColumn(String columnHeader, String fieldName, int width, int alignment) {
        super(columnHeader, fieldName, width, alignment);
        isPrintable = false;
        setAsEditable(BUTTON);
        setRptChrs(0);
    }

    public ButtonColumn setAsEditable(int editorType) {
        super.setAsEditable(editorType);
        return this;
    }

    public ButtonColumn setText(String text) {
        this.text = text;
        return this;
    }

    public ButtonColumn setTextFromFieldName(String fieldName) {
        this.fieldName = fieldName;
        this.text = null;
        return this;
    }

    @Override
    public Object getValue(Object object, int index, int row) {
        if (metaLoader!=null){
            return super.getValue(object, index, row);                
        }
        return text != null ? text : super.getValue(object, index, row);
    }

    @Override
    public void setValue(Object object, Object value, int row) {
        // no es necesario llamar a este metodo pues se hace dinamicamente en el editor
        //executeChangeValueListener(object, value, value);
    }

    @Override
    public ColumnInfo setDropDownFormatter(MetaLoader metaLoader) {
        this.metaLoader = metaLoader;
        return this;
    }

    public boolean isEditable() {
        return false;
    }

}