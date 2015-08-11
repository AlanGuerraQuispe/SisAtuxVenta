package com.atux.report;


import com.aw.core.domain.AWBusinessException;
import com.aw.core.report.AWPrintService;
import com.aw.core.report.ReportFileGenerator;
import com.aw.core.report.ReportUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Manuel Flores
 * Date: 23/10/2007
 */
public class SPReportGenerator extends ReportFileGenerator {
    private String outPutFullFileName;
    //private String rutaVisorPdf;

    /**
     * Constructor
     *
     * @param reportName report name
     * @param params params
     */
    public SPReportGenerator(File reportName, Map params) {
        super(reportName, params);
    }

    public SPReportGenerator(String reportFileName) {
        super(getReportFullPath(reportFileName), buildParams());
    }

    /**
     * Crea los parametros que necesitan los reportes:
     * - Codigo de Usuario
     *
     * @return params map
     */
    private static Map buildParams() {
        Map params = new HashMap();
        /*ApplicationUser user= SPApplication.getAppUser();

        params.put("codigoUsuario",  user.getUsername());*/
        return params;
    }

    public static InputStream getReportFullPath(String reportFileName) {
        try{
            // intentart con nombre
            //reportFileName = "com/etna/qt/report/"+reportFileName;
//            logger.error("XXX:"+reportFileName );
//            try{logger.error("1:"+SPReportGenerator.class.getClassLoader().getResource(reportFileName));}catch (Throwable e){}
//            try{logger.error("2:"+new ClassPathResource(reportFileName).getURL());}catch (Throwable e){}
//            try{logger.error("3:"+new ClassPathResource(reportFileName).getFile());}catch (Throwable e){}
//            try{logger.error("4:"+new FileSystemResource(reportFileName).getURL());}catch (Throwable e){}
//            try{logger.error("5:"+new FileSystemResource(reportFileName).getFile());}catch (Throwable e){}
//            try{logger.error("5.1:"+new FileSystemResource(reportFileName).getFile().exists());}catch (Throwable e){}
//            try{logger.error("5.1:"+new FileSystemResource(reportFileName).getFile().getAbsolutePath());}catch (Throwable e){}
//            try{logger.error("6:"+new UrlResource(reportFileName).getURL());}catch (Throwable e){}
//            try{logger.error("7:"+new UrlResource(reportFileName).getFile());}catch (Throwable e){}


            //return SPReportGenerator.class.getClassLoader().getResourceAsStream(reportFileName);
            return new ClassPathResource(reportFileName).getInputStream();
        }catch (Throwable e2){
            // intentar con directorio por defecto

            String reportFileNameFull = ReportUtils.formatDirectory("com/etna/qt/report") + reportFileName;
//            logger.error("XXX:"+reportFileNameFull );
//            try{logger.error("1:"+SPReportGenerator.class.getClassLoader().getResource(reportFileNameFull));}catch (Throwable e){}
//            try{logger.error("2:"+new ClassPathResource(reportFileNameFull).getURL());}catch (Throwable e){}
//            try{logger.error("3:"+new ClassPathResource(reportFileNameFull).getFile());}catch (Throwable e){}
//            try{logger.error("4:"+new FileSystemResource(reportFileNameFull).getURL());}catch (Throwable e){}
//            try{logger.error("5:"+new FileSystemResource(reportFileNameFull).getFile());}catch (Throwable e){}
//            try{logger.error("5.1:"+new FileSystemResource(reportFileNameFull).getFile().exists());}catch (Throwable e){}
//            try{logger.error("5.1:"+new FileSystemResource(reportFileNameFull).getFile().getAbsolutePath());}catch (Throwable e){}
//            try{logger.error("6:"+new UrlResource(reportFileNameFull).getURL());}catch (Throwable e){}
//            try{logger.error("7:"+new UrlResource(reportFileNameFull).getFile());}catch (Throwable e){}

            //String reportRootRelativePath = ReportUtils.formatDirectory("com/etna/qt/report") + reportFileName;
            //return SPReportGenerator.class.getClassLoader().getResource(reportRootRelativePath).getFile();
            try {
                return new ClassPathResource(reportFileNameFull).getInputStream();
            } catch (IOException e1) {
                throw AWBusinessException.wrapUnhandledException(logger,e1);
            }
        }

    }


//    public boolean readProperties() throws FileNotFoundException, IOException {
//        Properties properties = null;
//
//        DataInputStream archivo = new DataInputStream(new FileInputStream("qt-test-aw.properties"));
//        if (archivo != null) {
//            properties = new Properties();
//            properties.load(archivo);
//            rutaVisorPdf = properties.getProperty("externo.visorPDF");
//            return true;
//        }
//        return false;
//    }

    public Object execute(Object dataAccesor) {
        outPutFullFileName = generatePdfFile((Connection) dataAccesor);
        return outPutFullFileName;
    }

    public String getOutputFullFileName() {
        return outPutFullFileName;
    }


    public File getOutPutFile() {
        return new File(outPutFullFileName);
    }


    public void printReport() {

    }

    public void printDotMatrix() {
        if (true)
            throw new UnsupportedOperationException("Metodo no implementado para Sider Peru");
        // Quiza la tabla de correlativos (ricky sugiere Sucursal, pero no parece apropiado)
        String impresora = null; // RPImpresora.instance().obtenerImpMatricial();
        //impresora = "d:/prin.txt";
        printDotMatrix(finalReportFile,  impresora , true);
//        printDotMatrix(finalReportFile,  "//berserk/EpsonFX-1170", true);
    }

    public void printDotMatrix(File textFile, String printerPath, boolean trimFile) {
        AWPrintService prnSrv = new AWPrintService(AWPrintService.DEF_LINES_PER_PAGE, printerPath, true);
        try {
            if (trimFile)
                textFile = trimTextFile(textFile, buildFullTmpFilePath(".txt"));

            if (!prnSrv.startPrintService()) {
                throw new AWBusinessException("No se pudo determinar la existencia de la Impresora '"+printerPath+"'. Por favor verifique");
            }

            //prnSrv.activateCondensed();
            BufferedReader reader  = new BufferedReader(new FileReader(textFile));
            String line = null; //not declared within while loop
            while ( (line = reader.readLine()) != null){
                prnSrv.printCondensed(line, true);
            }
            reader.close();
        } catch (Throwable e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }finally {
            prnSrv.endPrintService();
        }
    }

    /**
     * Removes right spaces on each line
     * Removes empty lines at the bottom of the file
     * @param inTextFile
     * @param outTextFile
     * @return
     * @throws Exception
     */
    private File trimTextFile(File inTextFile, String outTextFile) throws Exception {
        BufferedReader reader  = new BufferedReader(new FileReader(inTextFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outTextFile));
        ArrayList<String> lineBuffer = new  ArrayList<String>();
        String line = null; //not declared within while loop
        boolean writeData = false;
        while ( (line = reader.readLine()) != null){
            line = line.replaceAll("\\s+$", "");
            lineBuffer.add(line);

            if (line.length()!=0){
                // finally something usefull. Writte all buffer lines
                for (String aLineBuffer : lineBuffer) {
                    writer.write(aLineBuffer);
                    writer.newLine();
                }
                lineBuffer.clear();
            }
        }
        reader.close();
        writer.close();
        return new File(outTextFile);
    }

    public void showReport() {
        ReportUtils.showReport(finalReportFile.getAbsolutePath());
    }
}
