/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.core.format;

import com.aw.core.domain.AWBusinessException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.text.FieldPosition;
import java.text.ParsePosition;

/**
 * Filler Support
 *
 * @author jcvergara
 *         05/11/2004
 */
public class FillerFormat implements Formatter {
    protected static Log logger = LogFactory.getLog(FillerFormat.class);

    // TODO GMC Alguien uso las constantes de swing (esto se usa fuera de swing)
    final public static int ALG_LEFT = SwingConstants.LEFT;
    final public static int ALG_RIGHT = SwingConstants.RIGHT;
    final public static int ALG_CENTER = SwingConstants.CENTER;

    /**
     * Filler string used to fill on left
     */
    private char fillerCharacter;

    private int lenght;
    /**
     * If it is true, then the field will be filled inclusive if it is empty
     * Otherwise the field will not be filled.
     */
    private boolean fillInclusiveEmptyString = true;

    /**
     * Constructor
     *
     * @param fillerCharacter
     */
    public FillerFormat(char fillerCharacter, int lenght) {
        this.fillerCharacter = fillerCharacter;
        this.lenght = lenght;
    }
    public Formatter clone() {
        try {
            return (Formatter) super.clone();
        } catch (CloneNotSupportedException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    /**
     * Add null or empty string support
     */
    public Object parseObject(String source) {
        return fillFormat(source, fillerCharacter, lenght, SwingConstants.RIGHT, fillInclusiveEmptyString);

    }

    public static String fill(String source, char fillerCharacter,
                              int length, int aligment, boolean fillInclusiveEmptyString) {
//        if (StringUtils.isEmpty(source) || length<0) {
        if (length<0) {
            return source;
        }
        if (source==null) source = "";

        if (source.length() > length)
            return source.substring(0, length);
            //throw new AWBusinessException("No se puede llenar '"+source+"' pues tamaño excede "+length);
        source = source.trim();
        if (source.length() == length) return source;
        if (!fillInclusiveEmptyString && source.length() == 0) return source;

        if (source.length() > length) return source.substring(0, length);

        StringBuffer buf = new StringBuffer(length);

        if (aligment == SwingConstants.CENTER) {
            int left = (length - source.length()) / 2;
            int right = length - (source.length() + left);
            fill(buf, fillerCharacter, left);
            buf.append(source);
            fill(buf, fillerCharacter, right);
        } else {
            if (aligment == SwingConstants.LEFT) buf.append(source);
            fill(buf, fillerCharacter, length - source.length());
            if (aligment == SwingConstants.RIGHT) buf.append(source);
        }
        return buf.toString();
    }
    public static String fillFormat(String source, char fillerCharacter,
                              int length, int aligment, boolean fillInclusiveEmptyString) {
        if (StringUtils.isEmpty(source)) return source;

        if (source.length() > length)
            return source.substring(0, length);
            //throw new AWBusinessException("No se puede llenar '"+source+"' pues tamaño excede "+length);
        source = source.trim();
        if (source.length() == length) return source;
        if (!fillInclusiveEmptyString && source.length() == 0) return source;

        if (source.length() > length) return source.substring(0, length);

        StringBuffer buf = new StringBuffer(length);

        if (aligment == SwingConstants.CENTER) {
            int left = (length - source.length()) / 2;
            int right = length - (source.length() + left);
            fill(buf, fillerCharacter, left);
            buf.append(source);
            fill(buf, fillerCharacter, right);
        } else {
            if (aligment == SwingConstants.LEFT) buf.append(source);
            fill(buf, fillerCharacter, length - source.length());
            if (aligment == SwingConstants.RIGHT) buf.append(source);
        }
        return buf.toString();
    }

    public static String fill(String source, char fillerCharacter,
                              int length, int aligment) {
        return fill(source, fillerCharacter, length, aligment, true);
    }

    static private void fill(StringBuffer source, char filler, int numChars) {
        for (int i = 0; i < numChars; i++)
            source.append(filler);
    }

    public static String fillSpaces(int length) {
        return fill("", ' ', length, SwingConstants.LEFT);
    }
    public static String fillNumWZero(String source, int length) {
        return fill(source, '0', length, SwingConstants.RIGHT);
    }

    public static String alignLeft(String source, int length) {
        return fill(source, ' ', length, SwingConstants.LEFT);
    }

    public static String alignCenter(String source, int length) {
        return fill(source, ' ', length, SwingConstants.CENTER);
    }

    public static String alignRight(String source, int length) {
        return fill(source, ' ', length, SwingConstants.RIGHT);
    }

    /**
     */
    public Object parseObject(String source, ParsePosition pos) {
        return source;
    }

    public Object format(Object bean, String attributeName, Object attributeValue) {
        return null;
    }

    /**
     */
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        return toAppendTo.append(obj);
    }

    public Object format(Object obj) {
        return parseObject(obj.toString());
    }

    public boolean onSortingPreferOriginalValue() {
        return false;
    }

    public boolean isFillInclusiveEmptyString() {
        return fillInclusiveEmptyString;
    }

    public void setFillInclusiveEmptyString(boolean fillInclusiveEmptyString) {
        this.fillInclusiveEmptyString = fillInclusiveEmptyString;
    }


    public static void main(String[] args) {
        FillerFormat fillerFormat = new FillerFormat('0', 12);
        //System.out.println("Text->" + fillerFormat.parseObject("23"));
    }


}
