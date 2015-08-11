package atux.trasladoproducto.reference;

/**
 * Copyright (c) 2010 Eckerd Pero S.A.<br>
 * <br>
 * Funcionalidad : Almacena todos los valores de la solicitud de pedido de traslado<br>
 * <br>
 * Historico de Creacion/Modificacion<br>
 * <br>
 * ID    PROGRAMADOR    FECHA/HORA            TIPO                 OBSERVACIoN
 * 000    GMATEO       10/03/2010 10:00:00   Creacion <br>
 * <br>
 *
 * @version 1.0<br>
 */
public class SolicitudPedidoInfo {
    String coCompania;
    String coLocal;
    String coLocalDestino;
    String coLocalDestinoAReplicar;
    String nuPedidoTraslado;
    String nuRecepcionProducto;
    String coCompaniaBase="";
    String coLocalBase="";
    String nuRecepcionProductoBase="";
    String tiPedidoOriginal="";
    String nuPedidoOriginal="";
    String coMotivo="";

    public SolicitudPedidoInfo(String coCompania, String coLocal, String coLocalDestino) {
        this.coCompania = coCompania;
        this.coLocal = coLocal;
        this.coLocalDestino = coLocalDestino;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getCoLocalDestino() {
        return coLocalDestino;
    }

    public void setCoLocalDestino(String coLocalDestino) {
        this.coLocalDestino = coLocalDestino;
    }

    public String getNuPedidoTraslado() {
        return nuPedidoTraslado;
    }

    public void setNuPedidoTraslado(String nuPedidoTraslado) {
        this.nuPedidoTraslado = nuPedidoTraslado;
    }

    public String getNuRecepcionProducto() {
        return nuRecepcionProducto;
    }

    public void setNuRecepcionProducto(String nuRecepcionProducto) {
        this.nuRecepcionProducto = nuRecepcionProducto;
    }

    public String getCoLocalDestinoAReplicar() {
        return coLocalDestinoAReplicar;
    }

    public void setCoLocalDestinoAReplicar(String coLocalDestinoAReplicar) {
        this.coLocalDestinoAReplicar = coLocalDestinoAReplicar;
    }

    public String getCoCompaniaBase() {
        return coCompaniaBase;
    }

    public void setCoCompaniaBase(String coCompaniaBase) {
        this.coCompaniaBase = coCompaniaBase;
    }

    public String getCoLocalBase() {
        return coLocalBase;
    }

    public void setCoLocalBase(String coLocalBase) {
        this.coLocalBase = coLocalBase;
    }

    public String getNuRecepcionProductoBase() {
        return nuRecepcionProductoBase;
    }

    public void setNuRecepcionProductoBase(String nuRecepcionProductoBase) {
        this.nuRecepcionProductoBase = nuRecepcionProductoBase;
    }

    public String getTiPedidoOriginal() {
        return tiPedidoOriginal;
    }

    public void setTiPedidoOriginal(String tiPedidoOriginal) {
        this.tiPedidoOriginal = tiPedidoOriginal;
    }

    public String getNuPedidoOriginal() {
        return nuPedidoOriginal;
    }

    public void setNuPedidoOriginal(String nuPedidoOriginal) {
        this.nuPedidoOriginal = nuPedidoOriginal;
    }

    public String getCoMotivo() {
        return coMotivo;
    }

    public void setCoMotivo(String coMotivo) {
        this.coMotivo = coMotivo;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Compaoia: " + coCompania + "\n");
        buffer.append("Local: " + coLocal + "\n");
        buffer.append("Local Destino: " + coLocalDestino + "\n");
        buffer.append("Local Destino a Replicar: " + coLocalDestinoAReplicar + "\n");
        buffer.append("Num Ped Traslado: " + nuPedidoTraslado + "\n");
        buffer.append("Num Rec Producto: " + nuRecepcionProducto + "\n");
        buffer.append("Compaoia Base: " + coCompaniaBase + "\n");
        buffer.append("Local Base: " + coLocalBase + "\n");
        buffer.append("Num Rec Producto Base: " + nuRecepcionProductoBase + "\n");
        buffer.append("Tipo Ped Original: " + tiPedidoOriginal + "\n");
        buffer.append("Num Ped Original: " + nuPedidoOriginal + "\n");
        buffer.append("Motivo: " + coMotivo + "\n");

        return buffer.toString();
    }

}
