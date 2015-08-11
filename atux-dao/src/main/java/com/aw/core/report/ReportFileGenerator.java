/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.core.report;

import com.aw.core.domain.AWBusinessException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import java.awt.print.PrinterJob;
import java.io.*;
import java.sql.Connection;
import java.util.Map;


/**
 * User: Manuel Flores
 * Date: 23/10/2007
 * General interface used to generate reports
 */
public abstract class ReportFileGenerator implements ReportGenerator {
    protected static final Log logger = LogFactory.getLog(ReportFileGenerator .class);


    protected Object compiledReport;
    protected File filledReportFile = null;
    protected File finalReportFile = null;
    protected Map params;
    // force temp direcotory end with "/"
    protected String tempDirectory = ReportUtils.formatDirectory(ReportUtils.TMP_DIR);

    /**
     * Constructor
     *
     * @param compiledReport
     * @param params
     */
    public ReportFileGenerator(Object compiledReport, Map params) {
        this.compiledReport = compiledReport;
        this.params = params;
    }

    /**
     * Main method that will generate the report.
     *
     * @param dataAccesor is the JDBC Connection instance
     * @return
     */
    abstract public Object execute(Object dataAccesor);

    /**
     * Generates the filled independant format report
     * This report file can be converted to any format (pdf, txt, etc)
     * @param conn
     * @return
     * @throws JRException
     */
//    public File generateFile(Connection conn) {
//        if (filledReportFile !=null) return filledReportFile;
//        //if (tempDirectory==null)tempDirectory = TMP_DIR;
//        String fullReportName = buildFullCompiledReportPath();
//
//        //DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd_HH.mm-");
//        String fullOutFileName = buildFullTmpFilePath(".rpt");
//        if (logger.isInfoEnabled())
//            logger.info("Generating report '" + fullReportName + "' on " + tempDirectory);
//
//        try {
//            JasperFillManager.fillReportToFile(fullReportName, fullOutFileName, params, conn);
//        }catch (Exception e) {
//            throw AWBusinessException.wrapUnhandledException(logger, e);
//        }
//        filledReportFile = new File(fullOutFileName);
//        return filledReportFile;
//    }


//    private String buildFullCompiledReportPath() {
//        String fullReportName = null;
//        if (!(compiledReport.exists())) {
//            if (logger.isDebugEnabled())
//                logger.debug("Expanding report:" + compiledReport + " to full path");
//
////            fullReportName = this.getClass().getClassLoader().getResource(compiledReport).getFile();
////            logger.error("SSSSSS:" + fullReportName );
//            try {
//                fullReportName = new ClassPathResource(compiledReport).getFile().getAbsolutePath();
//                logger.debug("fullReportName:" + fullReportName );
//            } catch (IOException e) {
//                throw AWBusinessException.wrapUnhandledException(logger,e );
//            }
//        } else {
//            fullReportName = compiledReport;
//        }
//        return fullReportName;
//    }

//    public void showReport() {
//        Report
//        try {
//            String acrobatExecutable = "rundll32 url.dll,FileProtocolHandler ";
//            String fullCommand = "\"" + finalReportFile + "\"";
//            if (acrobatExecutable!=null && acrobatExecutable.length()>0)
//                fullCommand = acrobatExecutable + " " +fullCommand ;
//            logger.info("Mostrando reporte '" + this.compiledReport + "' con commando:" + fullCommand);
//            Runtime.getRuntime().exec(fullCommand);
//        } catch (IOException e) {
//            //logger.error("Error showing Report", e);
//            throw AWBusinessException.wrapUnhandledException(logger, e);
//        }
//    }
//
    public String generatePdfFile(Connection conn) {
        // force temp directory end with "/"                              
//        tempDirectory = formatDirectory(tempDirectory);
//        String fullOutFileName = generateFile(conn);
//        String fullPdfFileName = fullOutFileName + ".pdf";
//        JasperExportManager.exportReportToPdfFile(fullOutFileName, fullPdfFileName);

        //Report loading and compilation
//        JasperDesign jasperDesign = JRXmlLoader.load(compiledReport);
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        // - Report execution

//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);


        try {
            JasperPrint jasperPrint = buildFilledJasperFile(conn);
            // Report creation in the PDF format
            String fullPdfFileName = buildFullTmpFilePath(".pdf");
            //String fullPdfFileName = "D:\\test.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, fullPdfFileName);
           //JasperPrintManager.printReport(jasperPrint, true );

            finalReportFile = new File(fullPdfFileName);
            return fullPdfFileName;
        } catch (Exception e) {
            throw AWBusinessException.wrapUnhandledException(logger,e);
        }

    }

    private JasperPrint buildFilledJasperFile(Connection conn) throws FileNotFoundException, JRException {
        //String fullCompiledReportPath = buildFullCompiledReportPath();
        //InputStream jasperReport = new FileInputStream("C:/ProgFile/iReport-2.0.2/CorrelativoDocumento.jasper");
        InputStream jasperReport = null;
        if( compiledReport instanceof InputStream) jasperReport  = (InputStream) compiledReport;
        else if( compiledReport instanceof File) jasperReport  = new FileInputStream((File)compiledReport);
        else if( compiledReport instanceof String) jasperReport  = new FileInputStream((String)compiledReport);
        else if( compiledReport instanceof String) jasperReport  = new FileInputStream((String)compiledReport);
        else throw new IllegalArgumentException("compiledReport no es un tipo valido: "+compiledReport);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
        return jasperPrint;
    }

    public String generateTxtFile(Connection conn) {
        try {
            JasperPrint jasperPrint = buildFilledJasperFile(conn);

            //FileOutputStream outputStream = new FileOutputStream("D:\\test.txt");
            String fullTxtOutputFilePath = buildFullTmpFilePath(".txt");
            FileOutputStream outputStream = new FileOutputStream(fullTxtOutputFilePath);
            Integer pageHeight= jasperPrint.getPageHeight();
            
            JRTextExporter exporter= new JRTextExporter();
            exporter.setParameter(JRTextExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRTextExporterParameter.OUTPUT_STREAM, outputStream);
            //exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Integer(10));
            exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Integer(10)); // originalmente era 10
//            exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, Integer.valueOf("5"));
            exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, Integer.valueOf("132"));
            //exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT,pageHeight); //comment?
            exporter.exportReport();

            finalReportFile = new File(fullTxtOutputFilePath);
            return fullTxtOutputFilePath;
        } catch (Exception e) {
            throw AWBusinessException.wrapUnhandledException(logger,e);
        }

    }
    public String generateAndPrint(String reportName, Map params, String tempDirectory, Connection conn) throws JRException {
        // force temp directory end with "/"
//        tempDirectory = formatDirectory(tempDirectory);
//        String fullOutFileName = generateFile(conn);
//        String fullPdfFileName = fullOutFileName + ".pdf";
//        JasperExportManager.exportReportToPdfFile(fullOutFileName, fullPdfFileName);

        //Report loading and compilation
//        JasperDesign jasperDesign = JRXmlLoader.load(compiledReport);
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        // - Report execution

//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);


        try {
            // - Report creation to printer
            PrinterJob job = PrinterJob.getPrinterJob();
            DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;  // This is the Flavour JasperReports uses

            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(MediaSizeName.ISO_A4); // ..or whatever the document size is

            PrintService service = null;
            if (job.printDialog(aset))
		        service = job.getPrintService();

            InputStream jasperReport = new FileInputStream("C:/ProgFile/iReport-2.0.2/CorrelativoDocumento.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            
            // Export the report using the JasperPrint instance
            JRExporter exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, service.getAttributes());
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);

                exporter.exportReport();

//            String fullPdfFileName = "D:\\test.pdf";
//            JasperExportManager.exportReportToPdfFile(jasperPrint, fullPdfFileName);

            return null;
        } catch (FileNotFoundException e) {
            throw AWBusinessException.wrapUnhandledException(logger,e);
        }

    }


    public Map getParams() {
        return params;
    }

    public Object getCompiledReport() {
        return compiledReport;
    }
    protected String buildFullTmpFilePath(String fileExt) {
        return ReportUtils.buildFullTmpFilePath("Rpt", fileExt);
    }

    public File getFinalReportFile() {
        return finalReportFile;
    }

    public static void main(String[] a) {
        System.out.println("" + System.getProperties());
    }
}
