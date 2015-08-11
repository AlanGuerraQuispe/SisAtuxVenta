package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.managerbd.BaseConexion;
import atux.modelbd.ComprobantePago;
import atux.modelbd.FormaPagoPedidoCredito;
import atux.modelbd.PedidoVenta;
import atux.modelbd.Usuario;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxVariables;
import com.aw.core.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CPedidoVenta extends JAbstractController{

    @Override
    public ArrayList<PedidoVenta> getRegistros() {
        return this.getRegistros(new String[]{}, null,null);
    }

    @Override
    public ArrayList<PedidoVenta> getRegistros(Object[] cvl) {
        return this.getRegistros(new String[]{},new String[]{PedidoVenta.COLUMNA_ACTIVO},cvl);
    }
    
    public ArrayList<PedidoVenta> getRegistros(String[] columnas,Object[] cvl) {
        return this.getRegistros(new String[]{}, columnas,cvl);
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
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        PedidoVenta pedidoCab = (PedidoVenta)mdl;
        int gravado = 0;
        String[] campos = PedidoVenta.CAMPOS_INSERTS;                
        
        Object[] valores = {pedidoCab.getCoCompania(),
                            pedidoCab.getCoLocal(),
                            pedidoCab.getNuPedido(),
                            pedidoCab.getTiPedido(),
                            pedidoCab.getCoLocal(),
                            AtuxSearch.getFechaHoraTimestamp(),//Fecha de Pedido
                            pedidoCab.getCaItem(),                                                       
                            pedidoCab.getVaTotalVenta(),
                            pedidoCab.getVaTotalPrecioVenta(),                            
                            pedidoCab.getNoImpresoCliente(),   
                            pedidoCab.getDeDireccionCliente(), 
                            pedidoCab.getVaTotalDescuento(),
                            pedidoCab.getVaTotalImpuesto(),
                            pedidoCab.getVaSaldoRedondeo(),
                            pedidoCab.getInCuadreCaja(),
                            pedidoCab.getVaTasaCambio(),
                            pedidoCab.getTiComprobante(),                            
                            pedidoCab.getCoMoneda(),
                            pedidoCab.getCoVendedor(),
                            pedidoCab.getNuRucCliente(), 
                            pedidoCab.getNoImpresoComprobante(),
                            pedidoCab.getDeDireccionComprobante(),
                            pedidoCab.getDeObservPedido(),
                            pedidoCab.getInPedidoAnulado(),
                            pedidoCab.getEsPedidoVenta(),
                            pedidoCab.getIdCreaPedidoVenta(),
                            pedidoCab.getFeCreaPedidoVenta(),
                            pedidoCab.getNuPedidoDiario() ,
                            pedidoCab.getNuPuntoVenta(),
                            pedidoCab.getNuTurno()
                            };
       
           gravado = this.agregarRegistroPs(PedidoVenta.nt, campos, valores);
               
        return true;
    }
    
      private Integer getUltimoPk() throws SQLException
    {
        ResultSet rsTmp = this.getUltimoRegistro(PedidoVenta.nt, "idPedidoVenta");
        Integer pk=null;
        try{
            while(rsTmp.next())
            {
               pk =  rsTmp.getInt(1);
            }
        }catch(SQLException ex){ex.printStackTrace();}
        return pk;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        PedidoVenta pedidoCab = (PedidoVenta)mdl;
        int gravado = 0;
        String campos = PedidoVenta.CAMPOS_NO_ID.toString();
        
        Object[] valores = {pedidoCab.getCoCompania(),
                            pedidoCab.getCoLocal(),
                            pedidoCab.getNuPedido(),
                            AtuxVariables.vTipoPedido,
                            pedidoCab.getCoLocal(),                            
                            pedidoCab.getCaItem(),                           
                            pedidoCab.getCoMoneda(),
                            pedidoCab.getVaTotalVenta(),
                            pedidoCab.getVaTotalPrecioVenta(),
                            pedidoCab.getIdUsuario(),
                            pedidoCab.getIdProveedor().getDeProveedor(), 
                            pedidoCab.getIdProveedor().getDeDireccion(), 
                            pedidoCab.getVaTotalDescuento(),
                            pedidoCab.getVaTotalImpuesto(),
                            pedidoCab.getVaSaldoRedondeo(),
                            pedidoCab.getCoVendedor(),                        
                            AtuxVariables.vInComprobanteManual,                            
                            pedidoCab.getPrimaryKey()};
          
           gravado = this.actualizarRegistroPs(PedidoVenta.nt, this.adjuntarSimbolo(campos, ",", "?")+Ex.WHERE+PedidoVenta.COLUMNA_PK+" = ? ", valores);
               
        return gravado;
    }

    public PedidoVenta getRegistroPorPk(Object[] id)
    {
         PedidoVenta pedidoCab = null;
        try {
            rs =  this.selectPorPk(PedidoVenta.nt,PedidoVenta.FULL_CAMPOS,PedidoVenta.COLUMNA_PK, id);
            
            CProveedores cntp = new CProveedores();
            while(rs.next())
            {
                   pedidoCab = new PedidoVenta();
                   pedidoCab.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
                   pedidoCab.setCoCompania(rs.getString(1));
                   pedidoCab.setCoLocal(rs.getString(2));
                   pedidoCab.setNuPedido(rs.getString(3));
                   pedidoCab.setCoVendedor(rs.getString(4));
                   pedidoCab.setTiComprobante(rs.getString(5));
                   pedidoCab.setCoMoneda(rs.getString(6));
                   pedidoCab.setTiPedido(rs.getString(7));
                   pedidoCab.setFePedido(rs.getDate(8));
                   pedidoCab.setCoLocalAtencion(rs.getString(9));
                   pedidoCab.setCaItem(rs.getInt(10));
                   pedidoCab.setNuRucCliente(rs.getString(11));
                   pedidoCab.setNoImpresoCliente(rs.getString(12));
                   pedidoCab.setDeDireccionCliente(rs.getString(13));
                   pedidoCab.setNuPuntoVenta(rs.getString(14));
                   pedidoCab.setNuTurno(rs.getString(15));
                   pedidoCab.setInCuadreCaja(rs.getString(16));
                   pedidoCab.setFeConfirmaCaja(rs.getDate(17));
                   pedidoCab.setVaTotalVenta(rs.getDouble(18));
                   pedidoCab.setVaTotalDescuento(rs.getDouble(19));
                   pedidoCab.setVaTotalCargoTarjeta(rs.getDouble(20));
                   pedidoCab.setVaTotalImpuesto(rs.getDouble(21));
                   pedidoCab.setVaTotalPrecioVenta(rs.getDouble(22));
                   pedidoCab.setVaSaldoRedondeo(rs.getDouble(23));
                   pedidoCab.setPcDctoClienteEspecial(rs.getDouble(24));
                   pedidoCab.setIdImprimePedido(rs.getString(25));
                   pedidoCab.setFeImprimePedido(rs.getDate(26));
                   pedidoCab.setIdAnulaPedido(rs.getString(27));
                   pedidoCab.setFeAnulaPedido(rs.getDate(28));
                   pedidoCab.setCoGrupoMotivo(rs.getString(29));
                   //pedidoCab.setIdProveedor(cntp.getRegistroPorPk(rs.getInt(4)));
                   //pedidoCab.setIdUsuario(new CUsuario().getRegistroPorPk(rs.getInt(5)));
                   //pedidoCab.setIdMoneda(new CMoneda().getRegistroPorPk(rs.getInt(6)));                   
                 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pedidoCab;
    }
    
    
    @Override
    public ArrayList getRegistros(String[] campos, String[] columnaId, Object[] id) {
         ArrayList<PedidoVenta> rgs = new ArrayList<PedidoVenta>();        
        try {
            
            if(id != null)
            { 
               this.numRegistros = this.getNumeroRegistros(PedidoVenta.nt, PedidoVenta.COLUMNA_ACTIVO, PedidoVenta.COLUMNA_ACTIVO, id);
            }else
            {
               this.numRegistros = this.getNumeroRegistros(PedidoVenta.nt, PedidoVenta.COLUMNA_ACTIVO);               
               if(rs.isClosed())
               {
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(PedidoVenta.nt,campos,columnaId,id,null);
            if(this.numRegistros<finalPag)
           {
              finalPag =  this.numRegistros;
           }
            if(finalPag>inicioPag)
            {
                inicioPag -= (finalPag-inicioPag)-1;
            }
            PedidoVenta pedidoCab = null;
            CProveedores cntp = new CProveedores();
            while(rs.next())
            {
                   pedidoCab = new PedidoVenta();                   
                   pedidoCab.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
                   pedidoCab.setCoCompania(rs.getString(1));
                   pedidoCab.setCoLocal(rs.getString(2));
                   pedidoCab.setNuPedido(rs.getString(3));
                   pedidoCab.setCoVendedor(rs.getString(4));
                   pedidoCab.setTiComprobante(rs.getString(5));
                   pedidoCab.setCoMoneda(rs.getString(6));
                   pedidoCab.setTiPedido(rs.getString(7));
                   pedidoCab.setFePedido(rs.getDate(8));
                   pedidoCab.setCoLocalAtencion(rs.getString(9));
                   pedidoCab.setCaItem(rs.getInt(10));
                   pedidoCab.setNuRucCliente(rs.getString(11));
                   pedidoCab.setNoImpresoCliente(rs.getString(12));
                   pedidoCab.setDeDireccionCliente(rs.getString(13));
                   pedidoCab.setNuPuntoVenta(rs.getString(14));
                   pedidoCab.setNuTurno(rs.getString(15));
                   pedidoCab.setInCuadreCaja(rs.getString(16));
                   pedidoCab.setFeConfirmaCaja(rs.getDate(17));
                   pedidoCab.setVaTotalVenta(rs.getDouble(18));
                   pedidoCab.setVaTotalDescuento(rs.getDouble(19));
                   pedidoCab.setVaTotalCargoTarjeta(rs.getDouble(20));
                   pedidoCab.setVaTotalImpuesto(rs.getDouble(21));
                   pedidoCab.setVaTotalPrecioVenta(rs.getDouble(22));
                   pedidoCab.setVaSaldoRedondeo(rs.getDouble(23));
                   pedidoCab.setPcDctoClienteEspecial(rs.getDouble(24));
                   pedidoCab.setIdImprimePedido(rs.getString(25));
                   pedidoCab.setFeImprimePedido(rs.getDate(26));
                   pedidoCab.setIdAnulaPedido(rs.getString(27));
                   pedidoCab.setFeAnulaPedido(rs.getDate(28));
                   pedidoCab.setCoGrupoMotivo(rs.getString(29));
                   //pedidoCab.setIdProveedor(cntp.getRegistroPorPk(rs.getInt(4)));
                   //pedidoCab.setIdUsuario(new CUsuario().getRegistroPorPk(rs.getInt(5)));
                   //pedidoCab.setIdMoneda(new CMoneda().getRegistroPorPk(rs.getInt(6)));
                   
                 rgs.add(pedidoCab);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }
    
    public static void grabarKardexSegunPedido(String pGlosa,
                                               String pGrupoMotivo,
                                               String pMotivo,
                                               String pTipoOperacion) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(AtuxVariables.vNumeroPedido);
        parametros.add(AtuxVariables.TIPO_DOCUMENTO_PEDIDO);     
        parametros.add(pGlosa);
        parametros.add(pGrupoMotivo);
        parametros.add(pMotivo);
        parametros.add(AtuxVariables.vIdUsuario);
        parametros.add(pTipoOperacion);
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_VENTAS.ACTUALIZARKARDEXSEGUNPEDIDO(?,?,?,?,?,?,?,?,?)", parametros, false);
    }
    
    public static void updateStocksProducto(String pCodigoProducto, int pCantidad, boolean pWithCommit) throws SQLException {
        String queryUpdate = "UPDATE LGTR_PRODUCTO_LOCAL " +
                "   SET CA_STOCK_DISPONIBLE = CA_STOCK_DISPONIBLE - " + pCantidad + " ,  "+        
                "       IN_REPLICA = 0," +
                "       FE_MOD_PROD_LOCAL = SYSDATE" +
                " WHERE CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                "   AND CO_LOCAL    = '" + AtuxVariables.vCodigoLocal + "'" +
                "   AND CO_PRODUCTO = '" + pCodigoProducto + "'";
        
        AtuxDBUtility.executeSQLUpdate(queryUpdate, pWithCommit);
    }
    
    public ArrayList getPedidoVenta(String numPedidoDiario,String numCorrelativo,String fechaIni, String fechaFin,String modulo)
    {
        ArrayList<PedidoVenta> rgs = new ArrayList<PedidoVenta>(); 
         
        PedidoVenta pedidoVenta = null;   
        Usuario      vendedor    = null;
        
        StringBuffer query;
        try {                                                    
            query = new StringBuffer();            
            query.append("SELECT   PEDIDO.CO_COMPANIA                        , \n");
            query.append("         PEDIDO.CO_LOCAL                           , \n");
            query.append("         PEDIDO.NU_PEDIDO                          , \n");
            query.append("         PEDIDO.FE_PEDIDO                          , \n");
            query.append("         TO_CHAR (PEDIDO.NU_PEDIDO_DIARIO, '0000') , \n");
            query.append("         PEDIDO.VA_TOTAL_PRECIO_VENTA              , \n");
            query.append("         PEDIDO.NU_RUC_CLIENTE                     , \n");
            query.append("         PEDIDO.NO_IMPRESO_COMPROBANTE             , \n");
            query.append("         VENDEDOR.NU_SEC_USUARIO                   , \n");
            query.append("         TRIM (VENDEDOR.NO_USUARIO)                , \n");
            query.append("         TRIM (VENDEDOR.AP_PATERNO_USUARIO)        , \n");
            query.append("         TRIM (VENDEDOR.AP_MATERNO_USUARIO)        , \n");            
            query.append("         PEDIDO.VA_TASA_CAMBIO                     , \n");
            query.append("         NVL (PEDIDO.TI_COMPROBANTE, ' ')          , \n");            
            query.append("         NVL (PEDIDO.NU_RUC_CLIENTE, ' ')          , \n");
            query.append("         NVL (PEDIDO.NO_IMPRESO_COMPROBANTE, ' ')  , \n");
            query.append("         NVL (PEDIDO.DE_DIRECCION_COMPROBANTE, ' '), \n");
            query.append("         NVL (PEDIDO.CO_FORMA_PAGO_CONVENIO, ' ')  , \n");
            query.append("         PEDIDO.VA_CO_PAGO                         , \n");
            query.append("         PEDIDO.PC_DCTO_CLIENTE_ESPECIAL           , \n");
            query.append("         PEDIDO.TI_PEDIDO                          , \n");
            query.append("         PEDIDO.VA_MONTO_COPAGO_CLIENTE            , \n");
            query.append("         PEDIDO.TI_ORDEN_COPAGO                    , \n");
            query.append("         PEDIDO.VA_TOTAL_DESCUENTO                 , \n");
            query.append("         PEDIDO.VA_TOTAL_IMPUESTO                  , \n");
            query.append("         PEDIDO.VA_SALDO_REDONDEO                  , \n");
            query.append("         PEDIDO.VA_TOTAL_VENTA                     , \n");
            query.append("         PEDIDO.CA_ITEM                            , \n");
            query.append("         DECODE(PEDIDO.ES_PEDIDO_VENTA,'C','COBRADO','N','ANULADO','P','PENDIENTE') , \n");
            query.append("         PEDIDO.CO_VENDEDOR                          \n");
            query.append("FROM     VTTC_PEDIDO_VENTA PEDIDO, \n"); 
            query.append("         CMTS_USUARIO VENDEDOR     \n");            
            query.append("WHERE    PEDIDO.CO_COMPANIA      = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("AND      PEDIDO.CO_LOCAL         = '").append(AtuxVariables.vCodigoLocal).append("'\n");
            if(!numPedidoDiario.isEmpty()){
                query.append("AND      PEDIDO.NU_PEDIDO_DIARIO = '").append(numPedidoDiario).append("'\n");
            }
            if(!numCorrelativo.isEmpty()){
                query.append("AND      PEDIDO.NU_PEDIDO = '").append(numCorrelativo).append("'\n");
            }            
            query.append("AND      PEDIDO.FE_PEDIDO BETWEEN TO_DATE ('").append(fechaIni).append("'\n");
            query.append("                  || ' 00:00:00','dd/MM/yyyy HH24:MI:SS') \n");
            query.append("                         AND      TO_DATE ('").append(fechaFin).append("'\n");
            query.append("                  || ' 23:59:59','dd/MM/yyyy HH24:MI:SS') \n");            
            
            if(modulo.equals("COBRO")){ //Pedidos pendiente de cobro
                query.append("AND      PEDIDO.ES_PEDIDO_VENTA   = 'P' \n");
                query.append("AND      PEDIDO.IN_PEDIDO_ANULADO = 'N' \n");
            }
            
            if(modulo.equals("ANULACION")){ //Pedidos cobrados para anular
                query.append("AND      PEDIDO.ES_PEDIDO_VENTA   = 'C' \n");
                query.append("AND      PEDIDO.IN_PEDIDO_ANULADO = 'N' \n");
            }            
            
            query.append("AND      \n");
            query.append("         (  \n");
            query.append("                  PEDIDO.CO_COMPANIA = VENDEDOR.CO_COMPANIA \n");
            query.append("         AND      PEDIDO.CO_LOCAL    = VENDEDOR.CO_LOCAL \n");
            query.append("         AND      PEDIDO.CO_VENDEDOR = VENDEDOR.NU_SEC_USUARIO  \n");
            query.append("         ) \n");  
            query.append("    ORDER BY PEDIDO.FE_PEDIDO,PEDIDO.NU_PEDIDO_DIARIO");
            
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                   pedidoVenta = new PedidoVenta();
                   pedidoVenta.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
                   pedidoVenta.setCoCompania(rs.getString(1));
                   pedidoVenta.setCoLocal(rs.getString(2));
                   pedidoVenta.setNuPedido(rs.getString(3));
                   pedidoVenta.setDetallePedidoVenta(new CDetallePedidoVenta().getRegistrosPorPedidoVenta(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)}));
                   pedidoVenta.setFePedido(rs.getDate(4));
                   pedidoVenta.setNuPedidoDiario(rs.getString(5));
                   pedidoVenta.setVaTotalPrecioVenta(rs.getDouble(6));
                   pedidoVenta.setNuRucCliente(rs.getString(7));
                   pedidoVenta.setNoImpresoComprobante(rs.getString(8));
                   vendedor = new Usuario(); 
                       vendedor.setPrimaryKey(new String[]{rs.getString(1),rs.getString(14)});
                       vendedor.setCoCompania(rs.getString(1));
                       vendedor.setCoLocal(rs.getString(2));
                       vendedor.setNuSecUsuario(rs.getString(9));
                       vendedor.setNombre(rs.getString(10));
                       vendedor.setApPaterno(rs.getString(11));
                       vendedor.setApMaterno(rs.getString(12));
                   pedidoVenta.setUsuario(vendedor);
                   pedidoVenta.setVaTasaCambio(rs.getDouble(13));
                   pedidoVenta.setTiComprobante(rs.getString(14));
                   pedidoVenta.setNuRucCliente(rs.getString(15));
                   pedidoVenta.setNoImpresoComprobante(rs.getString(16));
                   pedidoVenta.setDeDireccionComprobante(rs.getString(17));
                   pedidoVenta.setCoFormaPagoConvenio(rs.getString(18));
                   pedidoVenta.setVaCoPago(rs.getDouble(19));
                   pedidoVenta.setPcDctoClienteEspecial(rs.getDouble(20));
                   pedidoVenta.setTiPedido(rs.getString(21));
                   pedidoVenta.setVaMontoCopagoCliente(rs.getDouble(22));
                   pedidoVenta.setTiOrdenCopago(rs.getString(23));
                   pedidoVenta.setVaTotalDescuento(rs.getDouble(24));
                   pedidoVenta.setVaTotalImpuesto(rs.getDouble(25));
                   pedidoVenta.setVaSaldoRedondeo(rs.getDouble(26));
                   pedidoVenta.setVaTotalVenta(rs.getDouble(27));
                   pedidoVenta.setCaItem(rs.getInt(28));
                   pedidoVenta.setEsPedidoVenta(rs.getString(29));
                   pedidoVenta.setCoVendedor(rs.getString(30));
                   rgs.add(pedidoVenta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }
    
    public ArrayList getTotalesPedCredito(String coCliente)
    {
        ArrayList<PedidoVenta> rgs = new ArrayList<PedidoVenta>();
         
        PedidoVenta pedidoVenta = null;
        try {                        
                StringBuffer query = new StringBuffer();
                query.append(" SELECT   PEDIDO.CO_COMPANIA                                  , \n");
                query.append("          PEDIDO.CO_LOCAL                                     , \n");
                query.append("          PEDIDO.CO_CLIENTE_LOCAL                             , \n");
                query.append("          PEDIDO.NU_RUC_CLIENTE                               , \n");
                query.append("          PEDIDO.NO_IMPRESO_COMPROBANTE                       , \n");
                query.append("          SUM(PEDIDO.VA_TOTAL_PRECIO_VENTA) AS MONTO_TOTAL    , \n");
                query.append("          SUM(FORMA.IM_TOTAL_PAGO)          AS MONTO_CREDITO  , \n");
                query.append("          SUM(CREDITO.VA_SALDO)             AS SALDO_PENDIENTE, \n");
                query.append("          SUM(PEDIDO.CA_ITEM)               AS NU_ITEMS \n");
                query.append(" FROM     VTTC_PEDIDO_VENTA PEDIDO          , \n");
                query.append("          CMTS_USUARIO VENDEDOR             , \n");
                query.append("          VTTX_FORMA_PAGO_PEDIDO FORMA, \n");
                query.append("          VTTX_FORMA_PAGO_PEDIDO_CREDITO CREDITO \n");
                query.append(" WHERE    PEDIDO.CO_COMPANIA      = '").append(AtuxVariables.vCodigoCompania).append("'\n");
                query.append(" AND      PEDIDO.CO_LOCAL         = '").append(AtuxVariables.vCodigoLocal).append("'\n");
                query.append(" AND      PEDIDO.ES_PEDIDO_VENTA  = 'C' \n");
                query.append(" AND      PEDIDO.IN_PEDIDO_ANULADO= 'N' \n");

                if(!StringUtils.isEmpty(coCliente))
                    query.append(" AND      AND PEDIDO.CO_CLIENTE_LOCAL= '").append(coCliente).append("'\n");

                query.append(" AND  \n");
                query.append("          (  \n");
                query.append("                   CREDITO.CO_COMPANIA,CREDITO.CO_LOCAL,CREDITO.NU_PEDIDO,CREDITO.NU_ITEM_FORMA_PAGO  \n");
                query.append("          )  \n");
                query.append("          IN \n");
                query.append("          (SELECT  PEDCRE.CO_COMPANIA,  \n");
                query.append("                   PEDCRE.CO_LOCAL   ,  \n");
                query.append("                   PEDCRE.NU_PEDIDO  ,  \n");
                query.append("                   MAX(PEDCRE.NU_ITEM_FORMA_PAGO) \n");
                query.append("          FROM     VTTX_FORMA_PAGO_PEDIDO_CREDITO PEDCRE \n");
                query.append("          WHERE    PEDCRE.CO_COMPANIA = PEDIDO.CO_COMPANIA \n");
                query.append("          AND      PEDCRE.CO_LOCAL    = PEDIDO.CO_LOCAL \n");
                query.append("          AND      PEDCRE.NU_PEDIDO   = PEDIDO.NU_PEDIDO \n");
                query.append("          GROUP BY PEDCRE.CO_COMPANIA,  \n");
                query.append("                   PEDCRE.CO_LOCAL   ,  \n");
                query.append("                   PEDCRE.NU_PEDIDO \n");
                query.append("          ) \n");
                query.append(" AND      FORMA.CO_COMPANIA   = PEDIDO.CO_COMPANIA \n");
                query.append(" AND      FORMA.CO_LOCAL      = PEDIDO.CO_LOCAL \n");
                query.append(" AND      FORMA.NU_PEDIDO     = PEDIDO.NU_PEDIDO \n");
                query.append(" AND      FORMA.CO_FORMA_PAGO = '").append(AtuxVariables.FORMA_PAGO_CREDITO).append("'\n");
                query.append(" AND  \n");
                query.append("          ( \n");
                query.append("                   PEDIDO.CO_COMPANIA = VENDEDOR.CO_COMPANIA \n");
                query.append("          AND      PEDIDO.CO_LOCAL    = VENDEDOR.CO_LOCAL \n");
                query.append("          AND      PEDIDO.CO_VENDEDOR = VENDEDOR.NU_SEC_USUARIO \n");
                query.append("          ) \n");
                query.append(" GROUP BY PEDIDO.CO_COMPANIA, \n");
                query.append("          PEDIDO.CO_LOCAL, \n");
                query.append("          PEDIDO.CO_CLIENTE_LOCAL, \n");
                query.append("          PEDIDO.NU_RUC_CLIENTE  , \n");
                query.append("          PEDIDO.NO_IMPRESO_COMPROBANTE \n");
                query.append(" ORDER BY PEDIDO.NO_IMPRESO_COMPROBANTE");
            
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                   pedidoVenta = new PedidoVenta();
                   pedidoVenta.setCoCompania(rs.getString(1));
                   pedidoVenta.setCoLocal(rs.getString(2));
                   pedidoVenta.setCoClienteLocal(rs.getString(3));
                   pedidoVenta.setNuRucCliente(rs.getString(4));
                   pedidoVenta.setNoImpresoComprobante(rs.getString(5));
                   pedidoVenta.setVaTotalPrecioVenta(rs.getDouble(6));
                   pedidoVenta.setVaTotalVenta(rs.getDouble(7));
                   pedidoVenta.setVaSaldoRedondeo(rs.getDouble(8));
                   pedidoVenta.setCaItem(rs.getInt(9));
                   rgs.add(pedidoVenta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }
    
    public ArrayList getPedidosCredito(PedidoVenta pedidoCre)
    {
        ArrayList<PedidoVenta> rgs = new ArrayList<PedidoVenta>();                 
        Usuario      vendedor    = null;
        try {
            StringBuffer query = new StringBuffer();
            query.append(" SELECT   PEDIDO.CO_COMPANIA                                      , \n");
            query.append("          PEDIDO.CO_LOCAL                                         , \n");
            query.append("          PEDIDO.NU_PEDIDO                                        , \n");
            query.append("          PEDIDO.FE_PEDIDO                                        , \n");
            query.append("          TO_CHAR (PEDIDO.NU_PEDIDO_DIARIO, '0000') AS CORRELATIVO, \n");
            query.append("          PEDIDO.VA_TOTAL_PRECIO_VENTA                            , \n");
            query.append("          PEDIDO.NU_RUC_CLIENTE                                   , \n");
            query.append("          PEDIDO.NO_IMPRESO_COMPROBANTE                           , \n");
            query.append("          VENDEDOR.NU_SEC_USUARIO                                 , \n");
            query.append("          TRIM (VENDEDOR.NO_USUARIO)                              , \n");
            query.append("          TRIM (VENDEDOR.AP_PATERNO_USUARIO)                      , \n");
            query.append("          TRIM (VENDEDOR.AP_MATERNO_USUARIO)                      , \n");
            query.append("          PEDIDO.VA_TASA_CAMBIO                                   , \n");
            query.append("          NVL (PEDIDO.TI_COMPROBANTE, ' ')                        , \n");
            query.append("          NVL (PEDIDO.NU_RUC_CLIENTE, ' ')                        , \n");
            query.append("          NVL (PEDIDO.NO_IMPRESO_COMPROBANTE, ' ')                , \n");
            query.append("          NVL (PEDIDO.DE_DIRECCION_COMPROBANTE, ' ')              , \n");
            query.append("          NVL (PEDIDO.CO_FORMA_PAGO_CONVENIO, ' ')                , \n");
            query.append("          PEDIDO.VA_CO_PAGO                                       , \n");
            query.append("          PEDIDO.PC_DCTO_CLIENTE_ESPECIAL                         , \n");
            query.append("          PEDIDO.TI_PEDIDO                                        , \n");
            query.append("          PEDIDO.VA_MONTO_COPAGO_CLIENTE                          , \n");
            query.append("          PEDIDO.TI_ORDEN_COPAGO                                  , \n");
            query.append("          PEDIDO.VA_TOTAL_DESCUENTO                               , \n");
            query.append("          PEDIDO.VA_TOTAL_IMPUESTO                                , \n");
            query.append("          PEDIDO.VA_SALDO_REDONDEO                                , \n");
            query.append("          PEDIDO.VA_TOTAL_VENTA                                   , \n");
            query.append("          PEDIDO.CA_ITEM                                          , \n");
            query.append("          DECODE(PEDIDO.ES_PEDIDO_VENTA,  \n");
            query.append("                 'C','COBRADO',     \n");
            query.append("                 'N','ANULADO',     \n");
            query.append("                 'P','PENDIENTE'),  \n");
            query.append("          PEDIDO.CO_VENDEDOR,       \n");
            query.append("          PEDIDO.CO_CLIENTE_LOCAL                                 , \n");
            query.append("          CREDITO.VA_SALDO    AS SALDO                            , \n");
            query.append("          CREDITO.FE_PAGO     AS FECHA_PAGO_ULTIMA                  \n");
            query.append(" FROM     VTTC_PEDIDO_VENTA PEDIDO          , \n");
            query.append("          CMTS_USUARIO VENDEDOR             , \n");
            query.append("          VTTX_FORMA_PAGO_PEDIDO FORMA, \n");
            query.append("          VTTX_FORMA_PAGO_PEDIDO_CREDITO CREDITO \n");
            query.append(" WHERE    PEDIDO.CO_COMPANIA      = '").append(pedidoCre.getCoCompania()).append("'\n");
            query.append(" AND      PEDIDO.CO_LOCAL         = '").append(pedidoCre.getCoLocal()).append("'\n");
            query.append(" AND      PEDIDO.ES_PEDIDO_VENTA  = 'C' \n");
            query.append(" AND      PEDIDO.IN_PEDIDO_ANULADO= 'N' \n");
            query.append(" AND      PEDIDO.CO_CLIENTE_LOCAL = '").append(pedidoCre.getCoClienteLocal()).append("'\n");
            query.append(" AND  \n");
            query.append("          (  \n");
            query.append("                   CREDITO.CO_COMPANIA,CREDITO.CO_LOCAL,CREDITO.NU_PEDIDO,CREDITO.NU_ITEM_FORMA_PAGO  \n");
            query.append("          )  \n");
            query.append("          IN \n");
            query.append("          (SELECT  PEDCRE.CO_COMPANIA,  \n");
            query.append("                   PEDCRE.CO_LOCAL   ,  \n");
            query.append("                   PEDCRE.NU_PEDIDO  ,  \n");
            query.append("                   MAX(PEDCRE.NU_ITEM_FORMA_PAGO) \n");
            query.append("          FROM     VTTX_FORMA_PAGO_PEDIDO_CREDITO PEDCRE \n");
            query.append("          WHERE    PEDCRE.CO_COMPANIA = PEDIDO.CO_COMPANIA \n");
            query.append("          AND      PEDCRE.CO_LOCAL    = PEDIDO.CO_LOCAL \n");
            query.append("          AND      PEDCRE.NU_PEDIDO   = PEDIDO.NU_PEDIDO \n");
            query.append("          GROUP BY PEDCRE.CO_COMPANIA,  \n");
            query.append("                   PEDCRE.CO_LOCAL   ,  \n");
            query.append("                   PEDCRE.NU_PEDIDO \n");
            query.append("          ) \n");
            query.append(" AND      FORMA.CO_COMPANIA   = PEDIDO.CO_COMPANIA \n");
            query.append(" AND      FORMA.CO_LOCAL      = PEDIDO.CO_LOCAL \n");
            query.append(" AND      FORMA.NU_PEDIDO     = PEDIDO.NU_PEDIDO \n");
            query.append(" AND      FORMA.CO_FORMA_PAGO = '").append(AtuxVariables.FORMA_PAGO_CREDITO).append("'\n");
            query.append(" AND  \n");
            query.append("          ( \n");
            query.append("                   PEDIDO.CO_COMPANIA = VENDEDOR.CO_COMPANIA \n");
            query.append("          AND      PEDIDO.CO_LOCAL    = VENDEDOR.CO_LOCAL \n");
            query.append("          AND      PEDIDO.CO_VENDEDOR = VENDEDOR.NU_SEC_USUARIO \n");
            query.append("          ) \n");
            query.append(" ORDER BY PEDIDO.NU_PEDIDO_DIARIO,  \n");
            query.append("          PEDIDO.FE_PEDIDO");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                PedidoVenta pedido = new PedidoVenta();
                pedido.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
                pedido.setCoCompania(rs.getString(1));
                pedido.setCoLocal(rs.getString(2));
                pedido.setNuPedido(rs.getString(3));
                pedido.setDetallePedidoVenta(new CDetallePedidoVenta().getRegistrosPorPedidoVenta(new String[]{rs.getString(1), rs.getString(2), rs.getString(3)}));
                pedido.setFePedido(rs.getDate(4));
                pedido.setNuPedidoDiario(rs.getString(5));
                pedido.setVaTotalPrecioVenta(rs.getDouble(6));
                pedido.setNuRucCliente(rs.getString(7));
                pedido.setNoImpresoComprobante(rs.getString(8));
                    vendedor = new Usuario();
                    vendedor.setPrimaryKey(new String[]{rs.getString(1),rs.getString(14)});
                    vendedor.setCoCompania(rs.getString(1));
                    vendedor.setCoLocal(rs.getString(2));
                    vendedor.setNuSecUsuario(rs.getString(9));
                    vendedor.setNombre(rs.getString(10));
                    vendedor.setApPaterno(rs.getString(11));
                    vendedor.setApMaterno(rs.getString(12));
                pedido.setUsuario(vendedor);
                pedido.setVaTasaCambio(rs.getDouble(13));
                pedido.setTiComprobante(rs.getString(14));
                pedido.setNuRucCliente(rs.getString(15));
                pedido.setNoImpresoComprobante(rs.getString(16));
                pedido.setDeDireccionComprobante(rs.getString(17));
                pedido.setCoFormaPagoConvenio(rs.getString(18));
                pedido.setVaCoPago(rs.getDouble(19));
                pedido.setPcDctoClienteEspecial(rs.getDouble(20));
                pedido.setTiPedido(rs.getString(21));
                pedido.setVaMontoCopagoCliente(rs.getDouble(22));
                pedido.setTiOrdenCopago(rs.getString(23));
                pedido.setVaTotalDescuento(rs.getDouble(24));
                pedido.setVaTotalImpuesto(rs.getDouble(25));
                pedido.setVaSaldoRedondeo(rs.getDouble(26));
                pedido.setVaTotalVenta(rs.getDouble(27));
                pedido.setCaItem(rs.getInt(28));
                pedido.setEsPedidoVenta(rs.getString(29));
                pedido.setCoVendedor(rs.getString(30));
                pedido.setCoClienteLocal(rs.getString(31));
                pedido.setMontoCredito(rs.getDouble(32));
                rgs.add(pedido);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }
    
    public ArrayList getComprobatesPedido(PedidoVenta pedido)
    {
        ArrayList<ComprobantePago> rgs = new ArrayList<ComprobantePago>(); 
         
        ComprobantePago comprobantePago = null;           
                
        try {                                                    
            
            rs =  this.selectPorPk(ComprobantePago.nt,ComprobantePago.NO_FULL_CAMPOS,new String[]{"CO_COMPANIA","CO_LOCAL","NU_PEDIDO"}, new Object[]{pedido.getCoCompania(),pedido.getCoLocal(),pedido.getNuPedido()});            
            
            while(rs.next())
            {
               comprobantePago = new ComprobantePago();
               comprobantePago.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
               comprobantePago.setCoCompania(rs.getString(1));
               comprobantePago.setCoLocal(rs.getString(2));
               comprobantePago.setTiComprobante(rs.getString(3));
               comprobantePago.setNuComprobantePago(rs.getString(4));               
               comprobantePago.setDetallePedido(new CDetallePedidoVenta().getRegistros(new String[]{"CO_COMPANIA","CO_LOCAL","TI_COMPROBANTE","NU_COMPROBANTE_PAGO"},new String[]{comprobantePago.getCoCompania(),comprobantePago.getCoLocal(),comprobantePago.getTiComprobante(),comprobantePago.getNuComprobantePago()}));
               comprobantePago.setCoMoneda(rs.getString(5));
               comprobantePago.setCoCajero(rs.getString(6));
               comprobantePago.setFeComprobante(rs.getDate(7));
               comprobantePago.setNuPedido(rs.getString(8));
               comprobantePago.setNuCaja(rs.getString(9));
               comprobantePago.setNuTurno(rs.getString(10));
               comprobantePago.setInCuadreCaja(rs.getString(11));
               comprobantePago.setNoImpresoCliente(rs.getString(12));
               comprobantePago.setDeDireccionDliente(rs.getString(13));
               comprobantePago.setCaItem(rs.getInt(14));
               comprobantePago.setVaTotalVenta(rs.getDouble(15));
               comprobantePago.setVaTotalDescuento(rs.getDouble(16));
               comprobantePago.setVaTotalCargoTarjeta(rs.getDouble(17));
               comprobantePago.setVaTotalImpuesto(rs.getDouble(18));
               comprobantePago.setVaTotalPrecioVenta(rs.getDouble(19));
               comprobantePago.setVaSaldoRedondeo(rs.getDouble(20));
               comprobantePago.setEsComprobantePago(rs.getString(21));
               
               rgs.add(comprobantePago);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public boolean guardarPagoCredito(JAbstractModelBD mdl) throws SQLException {
        PedidoVenta pedidoCre = (PedidoVenta)mdl;
        int gravado = 0;
        String[] campos = FormaPagoPedidoCredito.CAMPOS_INSERTS;

        Object[] valores = {pedidoCre.getdPcred().getCoCompania(),
                pedidoCre.getdPcred().getCoLocal(),
                pedidoCre.getdPcred().getNuPedido(),
                pedidoCre.getFePedido(),
                pedidoCre.getdPcred().getCoFormaPagoCredito(),
                pedidoCre.getdPcred().getNuItemFormaPago(),
                pedidoCre.getdPcred().getCoMoneda(),
                pedidoCre.getdPcred().getCoFormaPago(),
                pedidoCre.getdPcred().getFePago(),
                pedidoCre.getdPcred().getVaTasaCambio(),
                pedidoCre.getdPcred().getImPago(),
                pedidoCre.getdPcred().getImTotalPago(),
                pedidoCre.getdPcred().getVaSaldo(),
                pedidoCre.getdPcred().getInPagoDiferido(),
                pedidoCre.getdPcred().getDeVentaCredito(),
                pedidoCre.getdPcred().getVaVuelto(),
                pedidoCre.getdPcred().getEsFormaPagoPedido(),
                pedidoCre.getdPcred().getIdCreaFormaPagoPedido(),
                AtuxSearch.getFechaHoraTimestamp(),
                pedidoCre.getdPcred().getInPagoBono(),
                pedidoCre.getdPcred().getInAnulado()
        };

        gravado = this.agregarRegistroPs(FormaPagoPedidoCredito.nt, campos, valores);

        return true;
    }

    public PedidoVenta getFormaPago(PedidoVenta pedidoVenta) {
        FormaPagoPedidoCredito formaPagoCre = null;
        try {
            StringBuffer query = new StringBuffer();
            query.append("SELECT CO_COMPANIA              , \n");
            query.append("       CO_LOCAL                 , \n");
            query.append("       NU_PEDIDO                , \n");
            query.append("       CO_MONEDA                , \n");
            query.append("       CO_FORMA_PAGO            , \n");
            query.append("       VA_TASA_CAMBIO           , \n");
            query.append("       IM_PAGO                  , \n");
            query.append("       IM_TOTAL_PAGO            , \n");
            query.append("       IN_PAGO_DIFERIDO         , \n");
            query.append("       DE_VENTA_CREDITO         , \n");
            query.append("       VA_VUELTO                , \n");
            query.append("       ES_FORMA_PAGO_PEDIDO     , \n");
            query.append("       ID_CREA_FORMA_PAGO_PEDIDO, \n");
            query.append("       FE_CREA_FORMA_PAGO_PEDIDO, \n");
            query.append("       IN_PAGO_BONO             , \n");
            query.append("       IN_ANULADO \n");
            query.append("FROM   VTTX_FORMA_PAGO_PEDIDO \n");
            query.append("WHERE  CO_COMPANIA   = '").append(pedidoVenta.getCoCompania()).append("'\n");
            query.append("AND    CO_LOCAL      = '").append(pedidoVenta.getCoLocal()).append("'\n");
            query.append("AND    NU_PEDIDO     = '").append(pedidoVenta.getNuPedido()).append("'\n");
            query.append("AND    CO_FORMA_PAGO = '").append(AtuxVariables.FORMA_PAGO_CREDITO).append("'\n");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                formaPagoCre = new FormaPagoPedidoCredito();
                formaPagoCre.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),new String(String.valueOf(1))});
                formaPagoCre.setCoCompania(rs.getString(1));
                formaPagoCre.setCoLocal(rs.getString(2));
                formaPagoCre.setNuPedido(rs.getString(3));
                formaPagoCre.setCoMoneda(rs.getString(4));
                formaPagoCre.setCoFormaPago(rs.getString(5));
                formaPagoCre.setVaTasaCambio(rs.getDouble(6));
                formaPagoCre.setImPago(rs.getDouble(7));
                formaPagoCre.setImTotalPago(rs.getDouble(8));
                formaPagoCre.setInPagoDiferido(rs.getString(9));
                formaPagoCre.setDeVentaCredito(rs.getString(10));
                formaPagoCre.setVaVuelto(rs.getDouble(11));
                formaPagoCre.setEsFormaPagoPedido(rs.getString(12));

                pedidoVenta.setdPcred(formaPagoCre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pedidoVenta;
    }

    public Integer getSecuenciaPedCredito(PedidoVenta pedido) throws SQLException
    {
        ResultSet rsTmp = BaseConexion.getResultSet("SELECT MAX(NU_ITEM_FORMA_PAGO) +1  \n" + "FROM   VTTX_FORMA_PAGO_PEDIDO_CREDITO \n" + "WHERE  CO_COMPANIA= '" + pedido.getCoCompania() + "'\n" + "AND    CO_LOCAL   = '" + pedido.getCoLocal() + "'\n" + "AND    NU_PEDIDO  = '" + pedido.getNuPedido() + "'\n");
        Integer pk=null;
        try{
            while(rsTmp.next())
            {
                pk =  rsTmp.getInt(1);
            }
        }catch(SQLException ex){ex.printStackTrace();}
        return pk;
    }
}
