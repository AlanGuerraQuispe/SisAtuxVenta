package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class ListadoPedido extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
   
    public static final String nt = "";
    public static final String[] COLUMNA_PK ={""};
    public static final String COLUMNA_ACTIVO = "";
    
    
    public static final String[]
        FULL_NOM_CAMPOS ={"Nu_Pedido_Diario, Nu_Pedido, Fe_Pedido, Es_Pedido_Venta, Nu_Caja, De_Vendedor, Nu_Comprobante_Pago, Va_Total_Precio_Venta"};

    private String  coCompania;
    private String  coLocal;
    private Integer nuPedidoDiario;
    private String  nuPedido;
    private String  fePedido;
    private String  esPedidoVenta;
    private String  nuCaja;
    private String  deVendedor;
    private String  nuComprobantePago;
    private Double  vaTotalPrecioVenta;
    
     public ListadoPedido() {
        initBasic(); 
    }
    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }
    public ListadoPedido(int nuPedidoDiario, String nuPedido,  String fePedido,           String esPedidoVenta, 
                         String nuCaja,      String deVendedor, String nuComprobantePago, Double vaTotalPrecioVenta){

        this.nuPedidoDiario = nuPedidoDiario;
        this.nuPedido=nuPedido;
        this.fePedido=fePedido;
        this.esPedidoVenta=esPedidoVenta;
        this.nuCaja=nuCaja;
        this.deVendedor=deVendedor;
        this.nuComprobantePago=nuComprobantePago;
        this.vaTotalPrecioVenta=vaTotalPrecioVenta;

    }         

    public String getcoCompania() {
        return coCompania;
    }

    public String getcoLocal() {
        return coLocal;
    }

    public Integer getnuPedidoDiario() {
        return nuPedidoDiario;
    }

    public String getnuPedido() {
        return nuPedido;
    }

    public String getfePedido() {
        return fePedido;
    }

    public String getesPedidoVenta() {
        return esPedidoVenta;
    }

    public String getnuCaja() {
        return nuCaja;
    }

    public String getdeVendedor() {
        return deVendedor;
    }

    public String getnuComprobantePago() {
        return nuComprobantePago;
    }

    public Double getvaTotalPrecioVenta() {
        return vaTotalPrecioVenta;
    }

    public void setcoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public void setcoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public void setnuPedidoDiario(Integer nuPedidoDiario) {
        this.nuPedidoDiario = nuPedidoDiario;
    }

    public void setnuPedido(String nuPedido) {
        this.nuPedido = nuPedido;
    }

    public void setfePedido(String fePedido) {
        this.fePedido = fePedido;
    }

    public void setesPedidoVenta(String esPedidoVenta) {
        this.esPedidoVenta = esPedidoVenta;
    }

    public void setnuCaja(String nuCaja) {
        this.nuCaja = nuCaja;
    }

    public void setdeVendedor(String deVendedor) {
        this.deVendedor = deVendedor;
    }

    public void setnuComprobantePago(String nuComprobantePago) {
        this.nuComprobantePago = nuComprobantePago;
    }

    public void setvaTotalPrecioVenta(Double vaTotalPrecioVenta) {
        this.vaTotalPrecioVenta = vaTotalPrecioVenta;
    }

    @Override
    public int hashCode() {
        int hash = 3;
//        hash = 89 * hash + (this.nuPedido != null ? this.nuPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ListadoPedido other = (ListadoPedido) obj;
//        if (this.nuPedido != other.nuPedido && (this.nuPedido == null || !this.nuPedido.equals(other.nuPedido))) {
//            return false;
//        }
        return true;
    }

}
