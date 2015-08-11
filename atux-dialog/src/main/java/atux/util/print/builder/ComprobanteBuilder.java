package atux.util.print.builder;

import atux.controllers.CComprobantePago;
import atux.util.print.AtuxPrint;

import javax.swing.*;
import java.util.HashSet;

public abstract class ComprobanteBuilder {

    protected CComprobantePago comprobantePago;
    protected int numeroLineas;
    protected int numeroMaximoItems;
    protected JTable tblPagos;
    protected String vuelto;
    protected HashSet mensajesAux;
    protected int lineasMensaje;
    protected int espacioLineas;

    protected String tipoComprobante;

    protected boolean  isPedidoCanje;
    protected boolean  isAcumulaCanje;
    protected String[] documentoAsociadoCanje;
    protected String dePromocionCanje;
    protected String dePromocionAcumula;        

    public void limpiarVariables() {
        numeroLineas = 0;
        mensajesAux = new HashSet();
        lineasMensaje = 0;
    }

    public abstract void printTiTle(AtuxPrint vPrint);

    public abstract void printHeader(AtuxPrint vPrint);

    public abstract void printColumnHeader(AtuxPrint vPrint);

    public abstract void printDetail(AtuxPrint vPrint);

    public abstract void printPageFooter(AtuxPrint vPrint);

    public abstract void printFooter(AtuxPrint vPrint);

    public void setNumeroMaximoItems(int numeroMaximoItems) {
        this.numeroMaximoItems = numeroMaximoItems;
    }

    public void setTblPagos(JTable tblPagos) {
        this.tblPagos = tblPagos;
    }

    public void setVuelto(String vuelto) {
        this.vuelto = vuelto;
    }

    public int getEspacioLineas() {
        return espacioLineas;
    }

    public void setEspacioLineas(int espacioLineas) {
        this.espacioLineas = espacioLineas;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public boolean isPedidoCanje() {
        return isPedidoCanje;
    }

    public void setPedidoCanje(boolean pedidoCanje) {
        isPedidoCanje = pedidoCanje;
    }

    public CComprobantePago getComprobantePago() {
        return comprobantePago;
    }

    public void setComprobantePago(CComprobantePago comprobantePago) {
        this.comprobantePago = comprobantePago;
    }    
    
}
