package com.aw.swing.report;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.report.custom.RptRenderer;
import com.aw.core.report.custom.RptSection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public abstract class RptFilterSection extends RptSection {

    protected int indentPercentage = 10;

    private List emptyFilterValues = new ArrayList(Arrays.asList(null, ""));


    public RptFilterSection() {
        super("filterSection", RptSection.DOC_HEADER);
    }


    public void setIndentPercentage(int indentPercentage) {
        this.indentPercentage = indentPercentage;
    }

    public int getLines() {
        return -1;
    }

    public boolean print(int maxLines, RptRenderer renderer) {
        try {
            printShrink(renderer);
        } catch (Exception e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
        return true;
    }

    public int printShrink(RptRenderer renderer) throws Exception {
        List<RptJCmpsGroup> groups = getGroups();

        // remove empty filters ()
        for (int i = groups.size()-1; i>=0 ; i--) {
            RptJCmpsGroup rptJCmpsGroup =  groups.get(i);
            if (rptJCmpsGroup.emptyFilter(emptyFilterValues))
                groups.remove(i);
        }

        // print groups
        int indentSpaces = (int) (1.0*renderer.pg().getColumnWidth() * indentPercentage/100);
        int charsWidth = renderer.pg().getColumnWidth() -2*indentSpaces;

        //boolean fullLastLine = true;

        int colIndex = 0;
        //boolean justCreatedLine = false;
        int charsGroupSeparator = 5;
        int lineCount = 0;
        for (int i = 0; i < groups.size(); i++) {
            RptJCmpsGroup rptJCmpsGroup = groups.get(i);

            int charsGroupSize = rptJCmpsGroup.getCharsSize();
            boolean needNewLine = colIndex + charsGroupSeparator+ charsGroupSize + indentSpaces > renderer.pg().getColumnWidth();
            if (needNewLine || i==0) {
                if (i>0) {
                    renderer.writeln(); //change line
                    lineCount ++;
                }
                renderer.write(renderer.fill(indentSpaces));
                colIndex =  indentSpaces;
                //justCreatedLine = true;
            }else{
                // place separator indentation
                renderer.write(renderer.fill(charsGroupSeparator));
                colIndex +=  charsGroupSeparator;
            }
            int charsPrinted = rptJCmpsGroup.print(renderer);
            colIndex+= charsPrinted;
        }
        if (colIndex>0){
            renderer.writeln(); //change line at line's end
            lineCount ++;
        }

        return lineCount;
    }

    protected abstract List<RptJCmpsGroup> getGroups() ;

    public List getEmptyFilterValues() {
        return emptyFilterValues;
    }

    public abstract boolean isEmptySection() ;


}