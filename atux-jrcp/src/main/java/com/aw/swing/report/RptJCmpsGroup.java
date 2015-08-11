package com.aw.swing.report;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.report.custom.RptRenderer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public class RptJCmpsGroup {
    private List jCmps = new ArrayList();

    public List getJCmps() {
        return jCmps;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        JCmpHelper.toStr(buf, jCmps);
        return buf.toString();
    }

    public boolean emptyFilter(List emptyFilterValues) {
        for (Object jCmp: jCmps) {
            if (isLabel(jCmp)) continue;

            String text = getText(jCmp);

            if (!emptyFilterValues.contains(text)) return false;
        }
        return true;
    }

    private String getText(Object jCmp) {
        String text = null;
        if (jCmp instanceof JComponent) text = JCmpHelper.getText((JComponent )jCmp);
        else if (jCmp instanceof RptFilterField) text = ((RptFilterField)jCmp).getValue();
        else throw new AWBusinessException("Object no es del tipo correcto:"+jCmp);
        return text;
    }

    public int print(RptRenderer renderer) throws Exception {
        return print(renderer, true);
    }

    private int print(RptRenderer renderer, boolean reallyPrint) throws Exception {
        int charSizeCount = 0;
        for (int i = 0; i < jCmps.size(); i++) {
            if (i>0) {
                if (reallyPrint) renderer.write(" "); //separator
                charSizeCount += 1;
            }
            Object jCmp =  jCmps.get(i);
            String text = getText(jCmp);
            if (text==null) text = "";
            if (reallyPrint) renderer.write(text, isLabel(jCmp));
            charSizeCount += text.length();
        }
        return charSizeCount;
    }

    private boolean isLabel(Object jCmp) {
        return (jCmp instanceof JLabel) || (jCmp instanceof RptFilterField.Label);
    }

    public int getCharsSize() throws Exception {
        // only calculate how much characters it  will take
        return print(null, false);
    }
}