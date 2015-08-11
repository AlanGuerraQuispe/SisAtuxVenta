package com.aw.core.report.custom;

import com.aw.core.cache.loader.MetaLoader;
import com.aw.core.format.FillerFormat;

/**
 * User: JCM
* Date: 13/12/2007
*/
public class RptField {
    /**
     * Formato  TC20
     * Font
     * Alignement
     * Size
     */
    private String spec;
    private Object value;
    private MetaLoader metaLoaderDD;

    /**
     *
     * T:title
     * B:bold
     * W:doubleWidthBold   
     * D:doubleWidth
     * N:normal
     */
    private char font;
    /**
     * L:left
     * C:center
     * R:right
     */
    private char alignement;
    private int size;  // field size

    /**
     * Indica que el campo es usado solo para formatear
     * Excel debe ignorar este tupo de campos (separador de columna)
     */
    private boolean formatSeparator = false;

    public static RptField fillLine(char fillerChar, RptRenderer renderer) {
        return fillLine(fillerChar, renderer.pg().getColumnWidth()) ;
    }
    public static RptField fillLine(char fillerChar, int size) {
        String str = FillerFormat.fill("", fillerChar, size, FillerFormat.ALG_LEFT);
        return new RptField("NL" + str.length(), str);
    }

    public static RptField b(String spec, Object value){
        return new RptField(spec, value);
    }
    public static RptField b(String spec, Object value, MetaLoader metaLoaderDD) {
        RptField field =  new RptField(spec, value);
        field.metaLoaderDD =metaLoaderDD;
        return field;
    }
    public RptField(String spec, Object str) {
        this.spec = spec;
        this.value = str;

        font = spec.charAt(0);
        alignement = spec.charAt(1);
            String valueStr = this.value==null?"":this.value.toString();
        size = spec.length()>2? Integer.parseInt(spec.substring(2)): valueStr.length();
    }

    public String getStr() {
        String valueStr = this.value==null?"":this.value.toString();
        if (metaLoaderDD !=null && metaLoaderDD.getMap().mapContains(value)){
            valueStr = (String) metaLoaderDD.getMap().mapGet(value).getLabel();
        }            
        return valueStr;
    }

    public char getFont() {
        return font;
    }

    public char getAlignement() {
        return alignement;
    }

    public int getSize() {
        return size;
    }

    public boolean isFormatSeparator() {
        return formatSeparator;
    }

    public RptField formatSep() {
        this.formatSeparator = true;
        return this;
    }
}
