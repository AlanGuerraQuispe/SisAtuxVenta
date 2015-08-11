package com.aw.core.util;

import com.aw.core.domain.AWDeveloperException;
import com.aw.core.domain.ICloneable;

public class TextTemplateConfig implements ICloneable{
    private String alignLeftName = "LEFT";
    private String alignRightName = "RIGHT";
    private String alignCenterName = "RIGHT";

    private String formatSep = ":";

    private String fieldStart = "{";
    private String fieldEnd = "}";

    public TextTemplateConfig() {
    }

    public TextCDTemplateConfig clone()  {
        try {
            return (TextCDTemplateConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AWDeveloperException("clone error",e);
        }
    }

    public String getAlignLeftName() {
        return alignLeftName;
    }

    public void setAlignLeftName(String alignLeftName) {
        this.alignLeftName = alignLeftName;
    }

    public String getAlignRightName() {
        return alignRightName;
    }

    public void setAlignRightName(String alignRightName) {
        this.alignRightName = alignRightName;
    }

    public String getAlignCenterName() {
        return alignCenterName;
    }

    public void setAlignCenterName(String alignCenterName) {
        this.alignCenterName = alignCenterName;
    }

    public String getFormatSep() {
        return formatSep;
    }

    public void setFormatSep(String formatSep) {
        this.formatSep = formatSep;
    }

    public String getFieldStart() {
        return fieldStart;
    }

    public void setFieldStart(String fieldStart) {
        this.fieldStart = fieldStart;
    }

    public String getFieldEnd() {
        return fieldEnd;
    }

    public void setFieldEnd(String fieldEnd) {
        this.fieldEnd = fieldEnd;
    }
}