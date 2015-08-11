package com.aw.swing.report;

import com.aw.core.report.ExcelRenderer;
import com.aw.core.report.PDFRenderer;
import com.aw.core.report.custom.RptRenderer;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public class RptFindPresenterRenderer {
    //IRenderer renderer = null;

    public static RptRenderer pdf() {
        return new PDFRenderer();
    }
    public static RptRenderer excel() {
        return new ExcelRenderer();
    }

//    private RptFindPresenterRenderer() {
//    }
//
//    public String start(String name) throws Exception {
//        return renderer.start(name,landscape, pageCharSize, toFileOnly);
//    }
//
//    public void end() {
//        renderer.close();
//    }
//
//    public double getTitleCharWidhtRatio() {
//        return renderer.getTitleCharWidhtRatio();
//    }
//
//    public int writeTitle(String str) throws Exception {
//        int retval = super.writeTitle(str);
//        renderer.writeTitle(str);
//        return retval;
//    }
//
//    public int writeBold(String str) throws Exception {
//        int retval = super.writeBold(str);
//        renderer.writeBold(str);
//        return retval;
//    }
//
//    public int write(String str) throws Exception {
//        int retval = super.writeBold(str);
//        renderer.write(str);
//        return retval;
//    }
//
//    public void writeln() throws Exception {
//        super.writeln();
//        renderer.writeln();
//    }

}