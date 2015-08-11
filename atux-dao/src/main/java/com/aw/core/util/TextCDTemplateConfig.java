package com.aw.core.util;

public class TextCDTemplateConfig extends TextTemplateConfig {

    private String detailStart = "{{";
    private String detailEnd = "}}";

    public String getDetailStart() {
        return detailStart;
    }

    public void setDetailStart(String detailStart) {
        this.detailStart = detailStart;
    }

    public String getDetailEnd() {
        return detailEnd;
    }

    public void setDetailEnd(String detailEnd) {
        this.detailEnd = detailEnd;
    }
}