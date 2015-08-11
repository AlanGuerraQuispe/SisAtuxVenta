package com.aw.core.util;

import com.aw.core.domain.AWDeveloperException;

import java.util.List;
import java.util.Map;

/**
 * User: Server
 * Date: 09/09/2009
 */
public class TextCDTemplate {

    final TextCDTemplateConfig config = new TextCDTemplateConfig();

    String template;

    public TextCDTemplate(String template) {
        this.template =template;
    }
    public String build(Map cabecera, List<Map> detalle) {
        TextTemplateConfig configDetail = config.clone();
        configDetail.setFieldStart(config.getDetailStart());
        configDetail.setFieldEnd(config.getDetailEnd());
        TextTemplate detailTemplate = new TextTemplate(template){
            protected StrReplacer buildStrReplacer(StringBuffer text, StrPos strPos) {
                throw new AWDeveloperException("No soportado");
//                return new StrReplacer(text, strPos){
//
//
//                };
            }
        };
        detailTemplate.setConfig(configDetail);
        String text = detailTemplate.build(cabecera);

        TextTemplateConfig configMaster = config.clone();
        TextTemplate masterTemplate = new TextTemplate(text);
        text = masterTemplate.build(cabecera);
        return text;

    }

}