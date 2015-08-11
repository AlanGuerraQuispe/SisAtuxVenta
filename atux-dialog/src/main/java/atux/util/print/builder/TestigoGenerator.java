package atux.util.print.builder;

import atux.util.print.AtuxPrint;
import atux.util.print.AtuxPrintService;
import atux.util.print.AtuxPrintTicket;
import javax.swing.*;


public class TestigoGenerator {
    private final String TI_TESTIGO_TICKET ="02";
    private final String TI_TESTIGO_MATRIZ ="01";

    private TestigoBuilder testigo;
    private String rutaImpresora;
    private int espacios ;
    private boolean ticketera;

    public TestigoGenerator(TestigoBuilder testigo, String rutaImpresora, int espacios) {
        this.testigo = testigo;
        this.rutaImpresora = rutaImpresora;
        this.espacios = espacios;
    }

      public TestigoGenerator(TestigoBuilder testigo) {
        this.testigo = testigo;

    }
        public TestigoGenerator(TestigoBuilder testigo, JTable tblProductos) {
        this.testigo = testigo;
        testigo.setTblProductos(tblProductos);

    }
      public TestigoGenerator() {

     }

        public void generarTestigo() {

        AtuxPrint vPrint = getImpresora();

        if (!vPrint.startPrintService()) throw new RuntimeException("Error en Impresora. Verifique !!!");            

        testigo.printTiTle(vPrint);
        testigo.printHeader(vPrint);
        testigo.printColumnHeader(vPrint);
        testigo.printDetail(vPrint);
        testigo.printPageFooter(vPrint);
        testigo.printFooter(vPrint);

        vPrint.endPrintService();

    }
      private AtuxPrint getImpresora(){
        AtuxPrint printer=null;
        if(!ticketera){            
            printer = new AtuxPrintService(espacios, rutaImpresora, false);
        }
        else
            printer = new AtuxPrintTicket(rutaImpresora);

        return printer;
    }

    public boolean isTicketera() {
        return ticketera;
    }

    public void setTicketera(boolean ticketera) {
        this.ticketera = ticketera;
    }
}
