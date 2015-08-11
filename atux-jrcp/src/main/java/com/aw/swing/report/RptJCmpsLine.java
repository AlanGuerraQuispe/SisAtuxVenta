package com.aw.swing.report;

import com.aw.core.format.FillerFormat;
import com.aw.core.report.custom.RptRenderer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public class RptJCmpsLine {
    private int y;
    private List<JComponent> jCmps = new ArrayList<JComponent>();

    public RptJCmpsLine(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public List<JComponent> getJCmps() {
        return jCmps;
    }

    public void addSorted(JComponent jCmp) {
        jCmps.add(jCmp);
//        int idx = jCmps.size();
//        for (int i = 0; i < jCmps.size(); i++) {
//            JComponent cmpCurr=  jCmps.get(i);
//            if (jCmp.getX() > cmpCurr.getX()){
//                idx = i-1;
//                break;
//
//            }
//        }
//        if (idx < 0) idx =  0;
//        jCmps.add(idx, jCmp);
    }


    public void sort() {
        Collections.sort(jCmps, new Comparator<JComponent>() {
            public int compare(JComponent o1, JComponent o2) {
                return o1.getX() - o2.getX();
            }
        });
    }

    public void print(RptRenderer renderer, int pixelWidth, int indentPercentage) throws Exception {
        //new width considering indentation
        pixelWidth = pixelWidth + 2 * (indentPercentage * pixelWidth / 100);

        //boolean lastWasData = false;
        int indentChars = renderer.pg().translateX(indentPercentage * pixelWidth / 100, pixelWidth);

        int lastCharPos = 0;
        renderer.write(renderer.fill(indentChars));
        for (JComponent jCmp : jCmps) {

            int currCharPos = renderer.pg().translateX(jCmp.getX(), pixelWidth);
            renderer.write(renderer.fill(currCharPos - lastCharPos));

            int size = renderer.pg().translateX(jCmp.getWidth(), pixelWidth);
            String text = getText(jCmp);
            int alignement = JCmpHelper.getAlignment(jCmp);

            text = FillerFormat.fill(text, ' ', size, alignement);
            if (jCmp instanceof JLabel) {
                renderer.writeBold(text);
            } else {
                renderer.write(text);
            }
            lastCharPos += size;
        }
    }

    private String getText(JComponent jCmp) {
        String text = JCmpHelper.getText(jCmp);
        if (jCmp instanceof JLabel && text.indexOf(':') == -1)
            text = text + ":";
        return text;
    }


    public List<RptJCmpsGroup> buildGroups() {
        List<RptJCmpsGroup>  groups = new ArrayList<RptJCmpsGroup>();
        RptJCmpsGroup currentGroup = null;
        //JComponent lastCmp  = null; 
        for (JComponent jCmp : jCmps) {
            boolean isLabel = (jCmp instanceof JLabel);
            String text = getText(jCmp);
            boolean startGroup =  isLabel  && text.length()>0 &&
                    Character.isUpperCase(text.charAt(0));
            if (startGroup){
                if (currentGroup!=null) groups.add(currentGroup);
                currentGroup= null;
            }

            if (currentGroup==null) currentGroup = new RptJCmpsGroup();
            currentGroup.getJCmps().add(jCmp);
        }
        if (currentGroup!=null) groups.add(currentGroup);
        return groups;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("Y:").append(y).append(" ");
        JCmpHelper.toStr(buf, jCmps);
        return buf.toString();
    }


}
