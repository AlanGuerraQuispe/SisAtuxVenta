package atux.util.print.builder;

import atux.controllers.repository.AtuxException;
import atux.util.print.AtuxPrint;
import atux.util.print.AtuxPrintService;
import atux.util.print.AtuxPrintTicket;
import com.atux.comun.context.AppCtx;

public class ComprobanteGenerator {
    private ComprobanteBuilder comprobante;
    public String rutaImpresora;
    private int espacios ;
    private boolean ticketera;

    public ComprobanteGenerator(ComprobanteBuilder comprobante, String rutaImpresora, int espacios) {
        this.comprobante = comprobante;
        this.rutaImpresora = rutaImpresora;
        this.espacios = espacios;
    }

    public void generarComprobante() {

        AtuxPrint vPrint = getImpresora();

        if (!vPrint.startPrintService()) throw new AtuxException("Error en Impresora. Verifique !!!");



        comprobante.printTiTle(vPrint);
        comprobante.printHeader(vPrint);
        comprobante.printColumnHeader(vPrint);
        comprobante.printDetail(vPrint);
        comprobante.printPageFooter(vPrint);
        comprobante.printFooter(vPrint);


        vPrint.endPrintService();

    }

    private AtuxPrint getImpresora(){

        if(AppCtx.instance().isEnviromentTest()){
        String tiComprobante="T";
        if(comprobante instanceof BoletaBuilder){
            tiComprobante="B";
        }else if (comprobante instanceof FacturaBuilder){
            tiComprobante="F";
        }
        rutaImpresora="C:\\IMPRESION\\"+tiComprobante+"_"+comprobante.getComprobantePago().getNuPedido()+"_"+comprobante.getComprobantePago().getNuComprobantePago()+".txt";
        }

        AtuxPrint printer=null;
        if(!ticketera)
            printer = new AtuxPrintService(espacios, rutaImpresora, false);
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
