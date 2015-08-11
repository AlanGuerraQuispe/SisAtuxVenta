/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atux.controllers;

import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.ListadoPedido;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Omar Davila
 */


public class CListadoPedido extends JAbstractController{
    private static final long serialVersionUID = 1L; 
    private static ArrayList parametros = new ArrayList();
//    private final Log logger = LogFactory.getLog(getClass());

    public static final String nt = "VTTV_MOVIMIENTO_CAJA_OBS";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","FE_DIA_VENTA","NU_SEC_OBS","NU_CAJA","NU_TURNO"};
    
    private String coCompania;
    private String coLocal;
    private Integer nuPedidoDiario;
    private Integer nuPedido;
    private String fePedido;
    private String esPedidoVenta;
    private String nuCaja;
    private String deVendedor;
    private String nuComprobantePago;
    private Double vaTotalPrecioVenta;


    public static final String[]
          FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_LOCAL, FE_DIA_VENTA, NU_CAJA, NU_TURNO, NU_SEC_OBS, DE_OBS, ID_CREA_OBS, FE_CREA_OBS"};

    public Integer getnuPedidoDiario() {
        return nuPedidoDiario;
    }

    
    public String getcoCompania() {
        return coCompania;
    }

    public String getcoLocal() {
        return coLocal;
    }

    public Integer getnuPedido() {
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

    public void setnuPedido(Integer nuPedido) {
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

    public ArrayList LeerPedidos(String vCodigoCompania, String vCodigoLocal, String vFecha){
        ArrayList<ListadoPedido> rgs = new ArrayList<ListadoPedido>();
        ListadoPedido      Listado      = null;
        StringBuffer  query;
        
        query = new StringBuffer();
        query.append("select tc.nu_pedido_diario Nu_Pedido_Diario, tc.nu_pedido Nu_Pedido, to_char(tc.fe_pedido,'dd/mm/yyyy hh24:mi:ss') Fe_Pedido,");
        query.append("        CASE tc.es_pedido_venta");
        query.append("            WHEN 'P' THEN  'Pendiente de Cobro'");
        query.append("            WHEN 'N' THEN  'Pedido Anulado'");
        query.append("            WHEN 'C' THEN");
        query.append("                    CASE TP.ES_COMPROBANTE_PAGO");
        query.append("                        WHEN 'A' THEN  'Cobrado'");
        query.append("                        WHEN 'N' THEN  'Devoluciòn de Venta'");
        query.append("                     END");
        query.append("        END  AS Es_Pedido_Venta,");
        query.append("        tc.NU_PUNTO_VENTA Nu_Caja, trim(tu.ap_paterno_usuario) || ' ' || trim(tu.ap_materno_usuario) || ' ' || trim(tu.no_usuario) De_Vendedor,");
        query.append("        nvl(tp.nu_comprobante_pago,' ') Nu_Comprobante_Pago, nvl(TP.VA_TOTAL_PRECIO_VENTA,0) Va_Total_Precio_Venta");
        query.append("  from vttc_pedido_venta tc left join vttv_comprobante_pago tp");
        query.append("                                        on tc.co_compania = tp.co_compania");
        query.append("                                       and tc.co_local    = tp.co_local");
        query.append("                                       and tc.nu_pedido   = tp.nu_pedido");
        query.append("                            INNER JOIN CMTS_USUARIO TU");
        query.append("                                        on tc.co_compania = tu.co_compania");
        query.append("                                       and tc.co_local    = tu.co_local");
        query.append("                                       and tc.CO_VENDEDOR = tu.nu_sec_usuario");
        query.append(" where tc.co_compania = '").append(vCodigoCompania).append("' ");
        query.append("   and tc.co_local    = '").append(vCodigoLocal).append("' ");
        query.append("   and tc.fe_pedido between to_date('").append(vFecha).append(" 00:00:00','dd/mm/yyyy hh24:mi:ss')");
        query.append("                        AND to_date('").append(vFecha).append(" 23:59:59','dd/mm/yyyy hh24:mi:ss')");
        query.append(" order by tc.nu_pedido_diario");

        try {            
            rs =  this.getRegistrosSinFiltro(query);
            while(rs.next())
            {
                Listado = new ListadoPedido();
                Listado.setnuPedidoDiario(rs.getInt("Nu_Pedido_Diario"));
                Listado.setnuPedido(rs.getString("Nu_Pedido"));
                Listado.setfePedido(rs.getString("Fe_Pedido"));
                Listado.setesPedidoVenta(rs.getString("Es_Pedido_Venta"));
                Listado.setnuCaja(rs.getString("Nu_Caja"));
                Listado.setdeVendedor(rs.getString("De_Vendedor"));
                Listado.setnuComprobantePago(rs.getString("Nu_Comprobante_Pago"));
                Listado.setvaTotalPrecioVenta(rs.getDouble("Va_Total_Precio_Venta"));

                rgs.add(Listado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CListadoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rgs;
    }
    

    @Override
    public String[] stringToArray(String cad,String separador){
        return cad.split(separador);
    }

    @Override
    public String generarArrayAString(String[] campos)
    {
        String cad = "* ";
        if(campos != null)
        {
            if(campos.length > 0)
            {
                cad = ""; 
                if(campos.length == 1)
                {
                    return cad+campos[0];
                }
                for(int i=0;i<campos.length;i++)
                {
                    cad +=(i==0?" ":", ")+campos[i];
                }           
            }
        }
        return cad;
    }

    @Override
    public ArrayList getRegistros() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList getRegistros(Object[] cvl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JAbstractModelBD getRegistro() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JAbstractModelBD getRegistro(JAbstractModelBD mdl, boolean opcion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JAbstractModelBD buscarRegistro(Object cvl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList getRegistros(String[] campos, String[] columnaId, Object[] id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
