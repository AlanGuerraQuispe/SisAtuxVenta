package com.aw.core.report.custom;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public class RptTitleSectionImpl extends RptSection {
    protected String title;
    private int indent = -1; // por defecto calcular

    public RptTitleSectionImpl() {
        super("titleSection", RptSection.DOC_HEADER);
    }

    public RptTitleSectionImpl setTitle(String title) {
        this.title = title;
        return this;
    }

    public RptTitleSectionImpl setIndent(int indent) {
        this.indent = indent;
        return this;
    }

    public int getLines() {
        return 1;
    }

    public boolean print(int maxLines, RptRenderer renderer) throws Exception {
        if (title!=null){
            int spaces = indent != -1 ? indent : (int) ((renderer.pg().getColumnWidth()-(title.length()*0.8))/2);
            renderer.writeX(RptField.fillLine(' ', spaces).formatSep()) ;
            renderer.writeTitle(title);
            renderer.writeln();
        }
        return true;
    }

    public String getTitle() {
        return title;
    }
}