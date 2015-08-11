package com.aw.core.report.custom;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * User: JCM
 * Date: 13/12/2007
 */
public class RptLayout {
    protected final Log logger = LogFactory.getLog(getClass());
//            public static int DOC_HEADER = 1;
//            public static int PAGE_HEADER = 2;
//            public static int RPT_DATA = 4;
//            public static int PAGE_FOOTER = 8;
//            public static int DOC_FOOTER  = 16;

    public void print(RptRenderer renderer, List<RptSection> sections) throws Exception {
        RptSectionGroup rptSectionDocHeader = new RptSectionGroup(sections, RptSection.DOC_HEADER);
        RptSectionGroup rptSectionPageHeader = new RptSectionGroup(sections, RptSection.PAGE_HEADER);
        RptSectionGroup rptSectionRptData = new RptSectionGroup(sections, RptSection.RPT_DATA);
        RptSectionGroup rptSectionPageFooter = new RptSectionGroup(sections, RptSection.PAGE_FOOTER);
        RptSectionGroup rptSectionDocFooter = new RptSectionGroup(sections, RptSection.DOC_FOOTER);

        List<RptSectionGroup> sectionsData = new ArrayList<RptSectionGroup>();
        sectionsData.add(rptSectionDocHeader);
        sectionsData.add(rptSectionRptData);
        sectionsData.add(rptSectionDocFooter);


        boolean printPageHeader = true;


        int pageLinePrinted = 0;
        while (sectionsData.size() > 0) {
            // Header
            if (printPageHeader) {
                rptSectionPageHeader.clearPrintedSections(); // force reprint
                pageLinePrinted = rptSectionPageHeader.print(-1, renderer);
                printPageHeader = false;
            }

            // total - page footer
            int totalLinesPerPage = renderer.pg().getTotalLinesPerPage() - rptSectionPageFooter.getLineCount();

            // Print Detail
            RptSectionGroup currentGroup = sectionsData.get(0);
            pageLinePrinted += currentGroup.print(totalLinesPerPage - pageLinePrinted, renderer);

            // remove group if finished
            if (currentGroup.printedAllSecions()) sectionsData.remove(currentGroup);

            // Footer
            boolean printPageFooter = pageLinePrinted >= totalLinesPerPage;
            if (printPageFooter || sectionsData.size() == 0) {
                rptSectionPageFooter.clearPrintedSections(); // force reprint
                pageLinePrinted = rptSectionPageFooter.print(-1, renderer);
                printPageHeader = true;
                // remove footer if printed
                if (sectionsData.size() == 1)
                    sectionsData.remove(sectionsData.get(sectionsData.size() - 1));
            }
        }
    }

    private static class RptSectionGroup {
        protected final Log logger = LogFactory.getLog(getClass());
        private List<RptSection> sections;
        private List<RptSection> printedSections = new ArrayList<RptSection>();
        private int lineCount;

        private RptSectionGroup(List<RptSection> sections, int type) {
            this.sections = new ArrayList<RptSection>();
            lineCount = 0;
            for (RptSection rptSection : sections) {
                if (rptSection.isType(type)) {
                    this.sections.add(rptSection);
                    lineCount += rptSection.getLines() < 0 ? 0 : rptSection.getLines();
                }
            }
        }


        public void setLineCount(int lineCount) {
            this.lineCount = lineCount;
        }

        public int getLineCount() {
            return lineCount;
        }

        public void clearPrintedSections() {
            this.printedSections.clear();
        }

        public int print(int maxLinesToPrint, RptRenderer renderer) throws Exception {
            renderer.stat().resetUserPrintedLineCount();
            ArrayList<RptSection> pendingSections = new ArrayList<RptSection>(sections);
            pendingSections.removeAll(printedSections);
            for (RptSection rptSection : pendingSections) {
                int currentPrintedLines = renderer.stat().userPrintedLineCount();
                int sectionMaxLinesToPrint = maxLinesToPrint == -1 ? -1 : maxLinesToPrint - currentPrintedLines;
                // verificar si existe espacio para imprimir 
                if (maxLinesToPrint != -1 && sectionMaxLinesToPrint <= 0) break;
                boolean sectionFinished = rptSection.print(sectionMaxLinesToPrint, renderer);
                int sectionLinesPrinted = renderer.stat().userPrintedLineCount() - currentPrintedLines;
                if (sectionFinished) {
                    if (sectionLinesPrinted > 0) renderer.writeln();  // agregar separador de seccion
                    printedSections.add(rptSection);
                }
                logger.info("LAYOUT:section:" + rptSection.getName() + " maxLnPrint:" + sectionMaxLinesToPrint +
                        " printed:" + sectionLinesPrinted + " sectionFinished:" + sectionFinished + " stat:" + renderer.stat());
            }
            int printedLineCount = renderer.stat().userPrintedLineCount();
            return printedLineCount;
        }

        public boolean printedAllSecions() {
            return sections.size() == printedSections.size();
        }

    }
}
