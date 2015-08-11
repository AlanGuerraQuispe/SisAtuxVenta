package com.aw.swing.report;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.report.custom.RptRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public class RptFilterSectionSwingImpl extends RptFilterSection {
    private int yLimit = -1;
    private int panelWidth = -1;
    private boolean shrink = true;

    List<RptJCmpsLine> cmpLines = new ArrayList<RptJCmpsLine>();
    public List<RptJCmpsLine> getCmpLines() {
        return cmpLines;
    }
    public int getLines() {
        return cmpLines.size();
    }

    public boolean sameLine(int impresicionAllowed, int cmpY, int lineY) {
        return Math.abs(cmpY - lineY) <= impresicionAllowed;
    }

    public void addJCmp(int impresicionAllowed, JComponent jComponent) {
        //int cmpY = jComponent.getY();
        int cmpY = getY(jComponent);
        RptJCmpsLine line = null;
        for (RptJCmpsLine rptJCmpsLine : cmpLines) {
            int lineY = rptJCmpsLine.getY();
            if (sameLine(impresicionAllowed, cmpY, lineY)) {
                line = rptJCmpsLine;
                break;
            }
        }
        if (line == null) {
            line = new RptJCmpsLine(cmpY);
            cmpLines.add(line);
        }
        line.addSorted(jComponent);

        String text = JCmpHelper.getText(jComponent);
        logger.debug("Agregando JCmp:" + text + " X:" + jComponent.getX() + " Y:" + jComponent.getY());
    }

    private int getY(Component jComponent) {
        return (int) jComponent.getLocationOnScreen().getY();
    }

    public void setYLimit(Component jComponent) {
        yLimit = getY(jComponent);
    }

    public void excludeCompAfter(Component jComponent) {
        setYLimit(jComponent);
        rebuild();
    }

    public void rebuild() {
        if (yLimit > 0)
            for (int i = cmpLines.size() - 1; i >= 0; i--) {
                if (yLimit < cmpLines.get(i).getY())
                    cmpLines.remove(i);
            }

        Collections.sort(cmpLines, new Comparator<RptJCmpsLine>() {
            public int compare(RptJCmpsLine o1, RptJCmpsLine o2) {
                return o1.getY() - o2.getY();
            }
        });
        for (RptJCmpsLine rptJCmpsLine : cmpLines)
            rptJCmpsLine.sort();

        //lines = cmpLines.size();
    }

    public void setPanelWidth(int sectionWidth) {
        panelWidth = sectionWidth;
    }

    public void setShrink(boolean shrink) {
        this.shrink = shrink;
    }
    public boolean isEmptySection() {
            return getCmpLines().size() == 0;
    }

    public boolean print(int maxLines, RptRenderer renderer) {

        try {
            // shrink, ignore null filters and try to put filter params
            // using less lines
            if (shrink) {
                printShrink(renderer);
            }else{
                for (RptJCmpsLine rptJCmpsLine : cmpLines) {
                    rptJCmpsLine.print(renderer, panelWidth, indentPercentage);
                    renderer.writeln();
                }
            }
            return true;
        } catch (Exception e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    protected List<RptJCmpsGroup> getGroups() {
        List<RptJCmpsGroup> groups  = new ArrayList<RptJCmpsGroup>();
        for (RptJCmpsLine cmpLine : cmpLines) {
            groups.addAll(cmpLine.buildGroups());
        }
        return groups;
    }
}
