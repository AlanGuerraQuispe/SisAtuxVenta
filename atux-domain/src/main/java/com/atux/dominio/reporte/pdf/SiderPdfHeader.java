package com.atux.dominio.reporte.pdf;

import com.aw.core.report.pdf.PdfHeader;
import com.lowagie.text.Image;

/**
 * User: gmc
 * Date: 08/10/2009
 */
public class SiderPdfHeader extends PdfHeader {

    public SiderPdfHeader() {
        setImgLocation("/images/logoS.gif");        
    }

    @Override
    protected Image getImage() {
        Image image = super.getImage();
        if (image==null) return null;
        image.scalePercent(60.0f);
        return image;
    }
}
