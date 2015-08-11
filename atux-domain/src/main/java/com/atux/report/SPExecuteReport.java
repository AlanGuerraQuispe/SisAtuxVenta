package com.atux.report;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.spring.ApplicationBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.print.PrintService;
import java.awt.print.PrinterJob;
import java.io.File;

/**
 * User: Manuel Flores
 * Date: 24/10/2007
 */
public class SPExecuteReport {
    protected final Log logger = LogFactory.getLog(getClass());


    public static SPExecuteReport instance() {
        SPExecuteReport report = new SPExecuteReport();
        ApplicationBase.instance().autowireFields(report);
        return report;
    }

    public File showPdf(final SPReportGenerator SPReportGenerator) {
        File pdf = generatePdf(SPReportGenerator);
        SPReportGenerator.showReport();
        return pdf;
    }

    public File generatePdf(final SPReportGenerator SPReportGenerator) {
        return SPReportGenerator.getFinalReportFile();
    }

    public void printPdfToDotMatrix(SPReportGenerator SPReportGenerator) {
        File pdf = generatePdf(SPReportGenerator);
        printPdfToDotMatrix(pdf);
    }

    public void printPdfToDotMatrix(File pdf) {
        if (true)
            throw new UnsupportedOperationException("Metodo no implementado para Sider Peru");

        String impresora = null; //RPImpresora.instance().obtenerImpLocal();
        logger.debug("Imprimiendo a "+impresora);
        PrintService[] service= PrinterJob.lookupPrintServices(); //list of printers
        boolean found =false;
        for (PrintService aService : service) {
            if (aService.getName().indexOf(impresora) != -1) {
                found = true;
                break;
            }
        }
        if (!found) throw new AWBusinessException("Impresora local '"+impresora+"' no encontrada");

        // SilentPrint.main(new String[]{pdf.getAbsolutePath(),impresora });
    }


    public File printDotMatrix(final SPReportGenerator SPReportGenerator) {
        File txtFile = generateTxt(SPReportGenerator);
        SPReportGenerator.printDotMatrix();
        return txtFile;
    }

    private File generateTxt(final SPReportGenerator SPReportGenerator) {
        return SPReportGenerator.getFinalReportFile();
    }

//    public  Object ejecutarReport(ReportGenerator reportGenerator) {
//        try {
//            return reportGenerator.execute(DAOIntgr.instance().getSql().getHibernateConnection());
//        } catch (final HibernateException e) {
//            throw new AWBusinessException("Error executing report:" + e.getMessage());
//        }
//    }

}
