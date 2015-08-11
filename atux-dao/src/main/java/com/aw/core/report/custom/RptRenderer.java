package com.aw.core.report.custom;

import com.aw.core.format.FillerFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public abstract class RptRenderer {
    protected final Log logger = LogFactory.getLog(getClass());

    protected boolean landscape = false;
    protected boolean toFileOnly = false;
    protected boolean allWithLeftAlign = false;
    protected PgInfo pgInfo;

    protected StringBuffer currLine = new StringBuffer();
    protected PrintStatistics statistics = new PrintStatistics();

    public abstract String start(String name) throws Exception;

    public abstract void end() throws Exception;

    public PgInfo pg() {
        return pgInfo;
    }

    public PrintStatistics stat() {
        return statistics;
    }

    public RptRenderer pgLandscape() {
        this.landscape = true;
        return this;
    }

    public RptRenderer pgPortrait() {
        this.landscape = false;
        return this;
    }

    public RptRenderer toFileOnly() {
        this.toFileOnly = true;
        return this;
    }

    public String center(String title) {
        return center(title, pgInfo.getColumnWidth());
    }

    public String left(String text, int width) {
        return FillerFormat.alignLeft(text, width);
    }

    public String center(String title, int size) {
        return FillerFormat.alignCenter(title, size);
    }

    public String fill(int chars) {
        return FillerFormat.alignLeft("", chars);
    }

    public static String fill(char fillerCharacter, int length) {
        return FillerFormat.fill("", fillerCharacter, length, FillerFormat.ALG_LEFT);
    }

    public String right(String text, int width) {
        return FillerFormat.alignRight(text, width);
    }

    public int writeX(RptField... rptFields) throws Exception {
        int retval = 0;
        for (RptField field : rptFields) {
            String value = null;
            if (allWithLeftAlign) {
                value = left(field.getStr(), field.getSize());
            } else {
                if (field.getAlignement() == 'L') value = left(field.getStr(), field.getSize());
                else if (field.getAlignement() == 'C') value = center(field.getStr(), field.getSize());
                else if (field.getAlignement() == 'R') value = right(field.getStr(), field.getSize());
                else throw new IllegalArgumentException("Alignement invalid:" + field.getAlignement());
            }

            if (field.getFont() == 'T') retval += writeTitle(value);
            else if (field.getFont() == 'B') retval += writeBold(value);
            else if (field.getFont() == 'W') retval += writeBoldDoubleWidth(value);
            else if (field.getFont() == 'D') retval += writeDoubleWidth(value);
            else if (field.getFont() == 'N') retval += write(value);
            else throw new IllegalArgumentException("Font invalid:" + field.getAlignement());
        }
        return retval;
    }


    public int writelnX(RptField... rptFields) throws Exception {
        int retval = writeX(rptFields);
        if (retval==0 && isFormatSeparator(rptFields))
            ; // toda la fila es para para poner espacios en blanco para formatear (no aplica)
        else
            writeln();
        return retval;
    }

    private boolean isFormatSeparator(RptField... rptFields) {
        for (RptField field : rptFields)
            if (!field.isFormatSeparator()) return false;
        return true;
    }

    public int writelnTitle(String str) throws Exception {
        int retval = writeTitle(str);
        writeln();
        return retval;
    }

    public int writeTitle(String str) throws Exception {
        currLine.append(str);
        return str.length();
    }

    public int writeBold(String str) throws Exception {
        currLine.append(str);
        return str.length();
    }

    public int writeBoldDoubleWidth(String str) {
        currLine.append(str);
        return str.length();
    }

    public int writeDoubleWidth(String str) throws Exception {
        currLine.append(str);
        return str.length();
    }

    public int write(String str) throws Exception {
        currLine.append(str);
        return str.length();
    }

    public int write(String str, boolean bold) throws Exception {
        if (bold)
            return writeBold(str);
        else
            return write(str);

    }

    public void writeln() throws Exception {
        logger.info(currLine);
        currLine.setLength(0); //clean
        stat().incPrintedLines();
        int linesPrintedInCurrentPage = stat().getPrintedLines() % pg().getTotalLinesPerPage();
        if (linesPrintedInCurrentPage == 0) {
            int printedPages = stat().incPrintedPages();
            pageChanged(printedPages);
        }
    }

    protected void pageChanged(int printedPages) {
    }


    public static class PrintStatistics {
        int printedPages = 0;
        int printedLines = 0;
        int userPrintedLineCount = 0;

        public int getPrintedPages() {
            return printedPages;
        }

        public int incPrintedPages() {
            this.printedPages++;
            return this.printedPages;
        }

        public int getPrintedLines() {
            return printedLines;
        }

        public void incPrintedLines() {
            this.printedLines++;
            this.userPrintedLineCount++;
        }

        public void resetUserPrintedLineCount() {
            userPrintedLineCount = 0;
        }

        public int userPrintedLineCount() {
            return userPrintedLineCount;
        }

        public String toString() {
            return "[printedPages=" + printedPages +
                    ", printedLines=" + printedLines + "]";
        }
    }


    public static class PgInfo {
        protected int columnWidth = 128;
        protected int totalLinesPerPage;
        protected double titleCharWidhtRatio;

        public int translateX(int x, int refXWidth) {
            return x * columnWidth / refXWidth;
        }

        public double getColumnCharWidth(int refXWidth) {
            return 1.0 * refXWidth / columnWidth;
        }

        public int getColumnWidth() {
            return columnWidth;
        }

        public void setColumnWidth(int columnWidth) {
            this.columnWidth = columnWidth;
        }

        public int getTotalLinesPerPage() {
            return totalLinesPerPage;
        }

        public void setTotalLinesPerPage(int totalLinesPerPage) {
            this.totalLinesPerPage = totalLinesPerPage;
        }

        public double getTitleCharWidhtRatio() {
            return titleCharWidhtRatio;
        }

        public void setTitleCharWidhtRatio(double titleCharWidhtRatio) {
            this.titleCharWidhtRatio = titleCharWidhtRatio;
        }
    }

    public boolean isAllWithLeftAlign() {
        return allWithLeftAlign;
    }

    public void setAllWithLeftAlign(boolean allWithLeftAlign) {
        this.allWithLeftAlign = allWithLeftAlign;
    }
}

