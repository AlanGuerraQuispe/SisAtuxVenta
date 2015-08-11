package com.aw.core.util;

import com.aw.core.format.FillerFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Server
 * Date: 09/09/2009
 * Time: 08:15:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextTemplate {
    public static int INTERN_ID_COUNT = 0;
    TextTemplateConfig config = new TextTemplateConfig();

    enum Orientation {LEFT, CENTER, RIGHT}

    String template;


    public TextTemplate(String template) {
        this.template = template;
    }

    public String build(Map cabecera) {
        StringBuffer text =  new StringBuffer(template);
        List<StrReplacer> replacers = new ArrayList<StrReplacer>();
        compile(text, replacers);
        replace(text, replacers, cabecera);
        return text.toString();
    }

    private StringBuffer compile(StringBuffer text, List<StrReplacer> replacers) {
        int lastPos = 0;
        while(true){
            StrPos strPos = new StrPos(text.indexOf(config.getFieldStart(), lastPos),
                                       text.indexOf(config.getFieldEnd(),lastPos)==-1?-1:text.indexOf(config.getFieldEnd(),lastPos)+1);
            if (strPos.validInstance()==null) break;
            StrReplacer strReplacer = buildStrReplacer(text, strPos);
            if (strReplacer.validInstance()!=null){
                replacers.add(strReplacer);
            }
            lastPos = strPos.end;
        }
        return text;
    }

    protected StrReplacer buildStrReplacer(StringBuffer text, StrPos strPos) {
        return new StrReplacer(text, strPos);
    }

    private void replace(StringBuffer text, List<StrReplacer> replacers, Map cabecera) {
        for (int i = 0; i < replacers.size(); i++) {
            StrReplacer strReplacer = replacers.get(i);
            strReplacer.replace(text, cabecera);
        }
    }

    public TextTemplateConfig getConfig() {
        return config;
    }

    public void setConfig(TextTemplateConfig config) {
        this.config = config;
    }

    public class StrReplacer{
        String id;
        boolean isValid;

        String exprOriginal;
        StrPos strPos;
        String name;
        Orientation orientation;
        Integer size;

        public StrReplacer(StringBuffer text, StrPos strPos) {
            id = "$$$StrReplacer"+(INTERN_ID_COUNT++)+"$$$";
            this.strPos = strPos;
            exprOriginal = text.substring(strPos.start, strPos.end+config.getFieldEnd().length()-1);

            isValid = false;
            if (!exprOriginal.startsWith(config.getFieldStart()) || !exprOriginal.endsWith(config.getFieldEnd()))
                return;

            String[] sections = exprOriginal.substring(
                        config.getFieldStart().length(),
                        exprOriginal.length() - config.getFieldEnd().length())
                    .split(config.getFormatSep());
            if (sections.length<1)
                return;

            name = sections[0];

            String orient = sections.length>=2? sections[1] : config.getAlignLeftName();
            if (config.getAlignLeftName().equals(orient)) orientation = Orientation.LEFT;
            else if (config.getAlignCenterName().equals(orient)) orientation = Orientation.CENTER;
            else if (config.getAlignRightName().equals(orient)) orientation = Orientation.RIGHT;
            else  orientation = Orientation.LEFT;

            size = null;
            if (sections.length>=3)
                try{
                    size = Integer.parseInt(sections[2]);
                }catch (NumberFormatException e){}

            // efectuar reemplazo
            text.replace(strPos.start, strPos.end, id);

            isValid = true;
        }
        public void replace(StringBuffer text, Map cabecera) {
            Object valor = cabecera.get(name);
            String valorFmt = String.valueOf(valor);
            if (size!=null){
                if (orientation == Orientation.LEFT)
                   valorFmt =  FillerFormat.alignLeft(valorFmt, size);
                else if (orientation == Orientation.RIGHT)
                   valorFmt =  FillerFormat.alignRight(valorFmt, size);
                else if (orientation == Orientation.CENTER)
                   valorFmt =  FillerFormat.alignCenter(valorFmt, size);
            }

            while(true){
                int idx =text.indexOf(id);
                if (idx == -1)
                    break;
                text.replace(idx, idx+id.length(), valorFmt);
            }
        }

        public StrReplacer validInstance() {
            return isValid? this:null;
        }

    }
    public class StrPos{
        int start;
        int end;

        private StrPos() {
        }

        private StrPos(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public StrPos validInstance() {
            return start>=0 & end>=0 ? this: null;
        }
    }


}
