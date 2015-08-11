package com.aw.swing.report;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.report.AWPrintService;
import com.aw.core.report.ReportUtils;
import com.aw.core.report.custom.RptRenderer;

/**
 * User: JCM
 * Date: 21/11/2007
 */
public class DotMatrixRenderer extends RptRenderer {
    protected AWPrintService service = null;
    String textFile = null;
    String rutaImpresora = null;
    int numLinesPerPageOverride = -1;
    int numCharsPerLineOverride = -1;

    public DotMatrixRenderer(String rutaImpresora) {
        this.rutaImpresora = rutaImpresora;
    }

    public String start(String name) throws Exception {
        //textFile = "d://EpsonFX-1170.txt";
        //String printerPath ="\\\\jgonzales\\1170";
        String printerPath =rutaImpresora;
        if (toFileOnly) textFile = printerPath;
        //service = new AWPrintService(AWPrintService.DEF_LINES_PER_PAGE, printerPath, false);
        service = new AWPrintService(printerPath);
        if (!service.startPrintService()) {
            throw new AWBusinessException("No se pudo determinar la existencia de la Impresora. Verifique !!!");
        }
        //service.setStartHeader();

        pgInfo = new PgInfo();
        pgInfo.setTitleCharWidhtRatio(41/12); // segun pruebas en una matricial
        pgInfo.setColumnWidth(numCharsPerLineOverride==-1?132:numCharsPerLineOverride);
        pgInfo.setTotalLinesPerPage(landscape?AWPrintService.DEF_LINES_PER_PAGE:AWPrintService.DEF_LINES_PER_PAGE);
        if (numLinesPerPageOverride!=-1){
            pgInfo.setTotalLinesPerPage(numLinesPerPageOverride);
            //service.setStarPrinter(false);
            service.setLinesPerPage(numLinesPerPageOverride);
            //char pageXXLines[] = {(char)27,(char)67,(char)numLinesPerPageOverride};
            //service.getPs().print(pageXXLines);
        }

        return textFile;
    }

    public void end() throws Exception {
        if (numLinesPerPageOverride!=-1){
            //manually add line changes
            while ( statistics.getPrintedLines() % numLinesPerPageOverride != 0)
                this.writeln();
        }else{
        //service.setEndHeader();
            service.getPs().flush();
            service.getPs().close();
            service.endPrintService();
        }
        if (textFile!=null)
            ReportUtils.showReport(textFile);
    }

    protected void pageChanged(int printedPages) {
        super.pageChanged(printedPages);
        if (numLinesPerPageOverride!=-1){
            service.printLine(" ", true);
            service.printLine(" ", true);
            service.printLine(" ", true);
            service.printLine(" ", true);
            //service.pageBreak();
        }
    }

    public int writeTitle(String str) throws Exception {
        int retval = super.writeTitle(str);
        service.activateDoubleWidthMode();
        service.printBold(str, false);
        service.deactivateDoubleWidthMode();
        //service.printDoubleWidthMode(str, false);
        return retval ;
    }

    public int writeBold(String str) throws Exception {
        int retval = super.writeBold(str);
        //service.activateCondensed();
        service.printBold(str, false);
        //service.deactivateCondensed();
        return retval ;
    }
    public int writeBoldDoubleWidth(String str) {
        int retval = super.writeBoldDoubleWidth(str);
        service.activateDoubleWidthMode();
        service.printBold(str, false);
        service.deactivateDoubleWidthMode();
        return retval ;
    }
    public int writeDoubleWidth(String str) throws Exception {
        int retval = super.writeDoubleWidth(str);
        service.printDoubleWidthMode(str, false);
        return retval ;
    }

    public int write(String str) throws Exception {
        int retval = super.write(str);
        service.printCondensed(str, false);
        return retval ;
    }

    public void writeln() throws Exception {
        super.writeln();
        service.printCondensed(" ", true);
    }

    public void setNumLinesPerPageOverride(int numLinesPerPageOverride) {
        this.numLinesPerPageOverride = numLinesPerPageOverride;
    }

    public void setNumCharsPerLineOverride(int numCharsPerLineOverride) {
        this.numCharsPerLineOverride = numCharsPerLineOverride;
    }

    public static void main(String[] args) throws Exception {
        DotMatrixRenderer  renderer = new DotMatrixRenderer("\\\\jgonzales\\1170");
        renderer.start("ss");
        renderer.writeTitle(" TITLE");
        renderer.writeBold(" BOLD");
        renderer.writeDoubleWidth(" DOBLE ");
        renderer.write(" Normal");
        renderer.writeln();
    }


}
