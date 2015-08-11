package com.aw.example.report;

import com.aw.core.format.DateFormatter;
import com.aw.core.format.Mnt;
import com.aw.core.format.NumberFormatter;
import com.aw.core.format.NumeroEnTextoFormatter;
import com.aw.core.report.custom.*;
import com.aw.swing.report.DotMatrixRenderer;
import com.lowagie.text.DocumentException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: JCM
 * Date: 13/12/2007
 */
public class ReporteFacturaAW {
    private BNFactura dmn;
    DateFormatter dtFmt = DateFormatter.DATE_FORMATTER;
    DateFormatter hrFmt = DateFormatter.TIME_FORMATTER;
    NumberFormatter numFmt = NumberFormatter.MONEY_FORMATTER;

    public ReporteFacturaAW(BNFactura documento) {
        this.dmn = documento;
    }

    public RptDocument build() {
        RptDocument documentSpec = new RptDocument("ReporteMatricial");
        documentSpec.getSections().add(new CabeceraSection());
        documentSpec.getSections().add(new ItemsSection());
        documentSpec.getSections().add(new TotalSection());
        return documentSpec;
    }

    private class CabeceraSection extends RptSection {
        public CabeceraSection() {
            super("DatosPedido", RptSection.PAGE_HEADER);
        }

        public int getLines() {
            return 13;
        }

        public boolean print(int maxLines, RptRenderer renderer) throws Exception {
            for (int i = 0; i < 10; i++) {
                renderer.writelnX();
            }
            renderer.writelnX(RptField.b("WL5", ""), RptField.b("WL35", dtFmt.format(dmn.getFechaEmision())));
            renderer.writelnX(RptField.b("WL5", ""), RptField.b("WL35", dmn.getEmpresa()), RptField.b("WL10", ""), RptField.b("WL10", dmn.getRuc()));
            renderer.writelnX(RptField.b("WL5", ""), RptField.b("WL35", dmn.getDireccion()), RptField.b("WL10", ""));
            return true;
        }

    }

    private class ItemsSection extends RptSectionBuffered {
        private int SCOL1 = 12;
        private int SCOL2 = 12;
        private int SCOL3 = 85;
        private int SCOL4 = 12;
        private int SCOL5 = 12;
        public ItemsSection() {
            super("ItemsSection", RptSection.RPT_DATA);
        }

        protected void loadBuffer(RptRenderer.PgInfo pgInfo, LineGroup header, LineGroup data) throws Exception {
            data.writelnX();
            data.writelnX();
            for (BNFacturaItem item : dmn.getItems()) {
                data.writelnX(RptField.b("NC"+SCOL1, item.getCantidad()),
                        RptField.b("NC"+SCOL2, item.getCodigo()),
                        RptField.b("NL"+SCOL3, item.getDescs().get(0)),
                        RptField.b("NL"+SCOL4, ""),
                        RptField.b("NR"+SCOL5, numFmt.formatAsString(item.getTotal()))
                );
                for (int i = 1; i < item.getDescs().size(); i++) {
                    String desc = item.getDescs().get(i);
                    data.writelnX(RptField.b("NC"+SCOL1, ""),
                            RptField.b("NC"+SCOL2, ""),
                            RptField.b("NL"+SCOL3, desc),
                            RptField.b("NL"+SCOL4, ""),
                            RptField.b("NR"+SCOL5, "")
                    );
                }

            }
            for (int i = 0; i< 3; i++)                data.writeln();

            data.writelnX(RptField.b("NC"+SCOL1, ""),
                    RptField.b("NC"+SCOL2, ""),
                    RptField.b("NL"+SCOL3, dmn.getItemDescAdic1()),
                    RptField.b("NL"+SCOL4, ""),
                    RptField.b("NR"+SCOL5, "")
            );
            data.writelnX(RptField.b("NC"+SCOL1, ""),
                    RptField.b("NC"+SCOL2, ""),
                    RptField.b("NL"+SCOL3, dmn.getItemDescAdic2()),
                    RptField.b("NL"+SCOL4, ""),
                    RptField.b("NR"+SCOL5, "")
            );
            data.writelnX(RptField.b("NC"+SCOL1, ""),
                    RptField.b("NC"+SCOL2, ""),
                    RptField.b("NL"+SCOL3, dmn.getItemDescAdic3()),
                    RptField.b("NL"+SCOL4, ""),
                    RptField.b("NR"+SCOL5, "")
            );
            for (int i = 0; i< 2; i++)                data.writeln();
        }
    }

    private class TotalSection extends RptSectionBuffered {
        public TotalSection() {
            super("TotalSection", RptSection.RPT_DATA);
        }

        public int getLines() {
            return -1;
        }

        protected void loadBuffer(RptRenderer.PgInfo pgInfo, LineGroup header, LineGroup data) throws Exception {
            BigDecimal neto = new BigDecimal(0) ;
            for (BNFacturaItem item: dmn.getItems()) {
                neto.add(item.getTotal());
            }
            BigDecimal igv = Mnt.fix(neto.multiply(new BigDecimal(0.19)) );
            BigDecimal total = neto.add(igv);

            data.writelnX(RptField.b("NC15", ""),
                    RptField.b("NC100", new NumeroEnTextoFormatter().format(total)));
            data.writeln();
            data.writeln();
            data.writeln();
            data.writelnX(RptField.b("NC60", ""),
                    RptField.b("NR20", numFmt.formatAsString(neto)),
                    RptField.b("NR20", numFmt.formatAsString(igv)),
                    RptField.b("NR20", numFmt.formatAsString(total))
                    );
        }
    }
//    public static DotMatrixRenderer buildDotMatrixRenderer(String rutaImpresora) {
//        DotMatrixRenderer renderer = new DotMatrixRenderer(rutaImpresora);
//        renderer.setNumCharsPerLineOverride(71);
//        renderer.setNumLinesPerPageOverride(47);
//        return renderer;
//    }
//    public static PDFRenderer buildPdfRenderer() {
//        PDFRenderer renderer = new PDFRenderer(){
//            public String start(String name) throws Exception {
//                String retVal = super.start(name);
//                pgInfo.setTotalLinesPerPage(51);
//                return retVal;
//            }
//        };
//        return renderer;

    //    }
    public static void main(String[] args) throws IOException, DocumentException {

//        //rutaImpresora = "d:/impresora.txt";

//        DotMatrixRenderer renderer = buildDotMatrixRenderer(rutaImpresora);
//       new ReportePedidoVentaHbm(documento, true).build().render(new PDFRenderer().pgPortrait());

//        PDFRenderer rendererpdf = buildPdfRenderer();
//        new ReportePedidoVentaHbm(documento,false).build().render(rendererpdf);

        BNFactura factura = new BNFactura();
        factura.setFechaEmision(new Date());
        factura.setEmpresa("Eckerd Peru S.A.");
        factura.setRuc("20331066703");
        factura.setDireccion("Jr. Guillermo Dansey 1846-Lima");
        BNFacturaItem bnFacturaItem = new BNFacturaItem();
        bnFacturaItem.getDescs().add("Fidelización Clientes y Puntos v6");
        bnFacturaItem.setTotal(new BigDecimal(7553.11));
        factura.getItems().add(bnFacturaItem);

        //String rutaImpresora = "\\\\192.168.1.73\\EpsonFX-";
        String rutaImpresora = "d:/impresora.txt";
        new ReporteFacturaAW(factura).build().render(new DotMatrixRenderer(rutaImpresora));
        //new ReporteFacturaAW(factura).build().render( new PDFRenderer());
    }

}