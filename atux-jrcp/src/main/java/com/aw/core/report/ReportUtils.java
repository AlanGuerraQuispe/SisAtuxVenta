package com.aw.core.report;

import com.aw.core.domain.AWBusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * User: JCM
 * Date: 21/11/2007
 */
public class ReportUtils {
    protected static final Log logger = LogFactory.getLog(ReportUtils .class);
    static protected final String TMP_DIR = System.getProperty("java.io.tmpdir");
    static protected DateFormat fileNameDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm-");

    public static String formatDirectory(String tempDirectory) {
        if (tempDirectory == null) tempDirectory = "";
        else if (!tempDirectory.endsWith("/") && !tempDirectory.endsWith("\\")) tempDirectory += "/";
        return tempDirectory;
    }


    public static String buildFullTmpFilePath(String prefix, String fileExt) {
        String outFileName = prefix + fileNameDateFormat.format(new Date()) + String.valueOf(Math.abs(new Random().nextInt(500))) + fileExt;
        String fullOutFileName = formatDirectory(TMP_DIR) + outFileName;
        return fullOutFileName;
    }
    public static void showReport(String finalReportFile) {
        try {
            String acrobatExecutable = "rundll32 url.dll,FileProtocolHandler ";
            String fullCommand = "\"" + finalReportFile + "\"";
            if (acrobatExecutable!=null && acrobatExecutable.length()>0)
                fullCommand = acrobatExecutable + " " +fullCommand ;
            logger.info("Mostrando reporte con commando:" + fullCommand);
            Runtime.getRuntime().exec(fullCommand);
        } catch (IOException e) {
            //logger.error("Error showing Report", e);
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

}
