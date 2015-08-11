package atux.controllers;


import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.ObsCaja;
import atux.modelgui.ModeloTablaObsCierreCaja;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Fecha creación : 01/09/2014  v1.0
 * @author aguerra
 */ 
public class CCajaPago extends JAbstractController{
    private static final long serialVersionUID = 1L; 
    private static ArrayList parametros = new ArrayList();
    private ModeloTablaObsCierreCaja mtped;
    private final Log logger = LogFactory.getLog(getClass());

    public static final String nt = "VTTV_MOVIMIENTO_CAJA";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","NU_SEC_MOVIMIENTO"};
    public static final String COLUMNA_ACTIVO = "ES_CAJA_PAGO";
    
    private String  coCompania;
    private String  coLocal;
    private String  tiMovCaja;
    private Date    feDiaVenta;
    private String  nuSecUsuario;
    private Integer nuCaja;
    private Integer nuTurno;
    private String  idCreaMovimientoCaja;
    private String  apPaternoUsuario;
    private String  apMaternoUsuario;
    private String  noUsuario;
    private Double  vaMontoGene;
    private Integer caBoletasGene;
    private Double  vaMontoBoletasGene;
    private Integer caFacturasGene;
    private Double  vaMontoFacturasGene;
    private Integer caGuiasGene;
    private Double  vaMontoGuiasGene;
    private Integer caBoletasAnul;
    private Double  vaMontoBoletasAnul;
    private Integer caFacturasAnul;
    private Double  vaMontoFacturasAnul;
    private Integer caGuiasAnul;
    private Double  vaMontoGuiasAnul;
    private Double  vaMontoMoneda;
    private Double  vaMontoBoletas;
    private Double  vaMontoFacturas;
    private Double  vaMontoGuias;
    private Double  vaMontoAnul;
    private Double  cjVaMontoMoneda;
    private Integer diTotalMovimiento;
    private Integer caPedidoAnulado;
    private Double  vaPedidoAnulado;
    private String  esCajaPago;
    private Date    feCreaMovimientoCaja;
    private Date    feCierreCaja;
    private String  nuSecMovimiento;
    private String  coMoneda;
    private String  inReplica;
    private Date    feCierreTurno;
    private Integer caBoletasManualGene;
    private Double  vaBoletasManualGene;
    private Integer caFacturasManualGene;
    private Double  vaFacturasManualGene;
    private Double  vaIgvBoletasGene;
    private Double  vaIgvFacturasGene;
    private Double  vaIgvBolManualGene;
    private Double  vaIgvFacManualGene;
    private Double  vaIgvBoletas;
    private Double  vaIgvFacturas;
    private Double  vaBoletasAfecto;
    private Double  vaFacturasAfecto;
    private Double  vaBoletasAfectoManual;
    private Double  vaFacturasAfectoManual;
    private Integer caBoletas;
    private Integer caFacturas;
    private Integer caGuias;
    private Integer caPedidosNoCobrado;
    private Double  vaPedidosNoCobrado;

    public static final String[]
          FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_LOCAL, NU_SEC_MOVIMIENTO, TI_MOV_CAJA, FE_DIA_VENTA, NU_SEC_USUARIO, NU_CAJA, NU_TURNO, ID_CREA_MOVIMIENTO_CAJA, AP_PATERNO_USUARIO, AP_MATERNO_USUARIO, NO_USUARIO, VA_MONTO_GENE, CA_BOLETAS_GENE, VA_MONTO_BOLETAS_GENE, CA_FACTURAS_GENE, VA_MONTO_FACTURAS_GENE, CA_GUIAS_GENE, VA_MONTO_GUIAS_GENE, CA_BOLETAS_ANUL, VA_MONTO_BOLETAS_ANUL, CA_FACTURAS_ANUL, VA_MONTO_FACTURAS_ANUL, CA_GUIAS_ANUL, VA_MONTO_GUIAS_ANUL, VA_MONTO_MONEDA, VA_MONTO_BOLETAS, VA_MONTO_FACTURAS, VA_MONTO_GUIAS, VA_MONTO_ANUL, CJ_VA_MONTO_MONEDA, DI_TOTAL_MOVIMIENTO, CA_PEDIDO_ANULADO, ES_CAJA_PAGO, FE_CREA_MOVIMIENTO_CAJA, FE_CIERRE_CAJA, CO_MONEDA, IN_REPLICA, FE_CIERRE_TURNO, CA_BOLETAS_MANUAL_GENE, VA_BOLETAS_MANUAL_GENE, CA_FACTURAS_MANUAL_GENE, VA_FACTURAS_MANUAL_GENE, VA_IGV_BOLETAS_GENE, VA_IGV_FACTURAS_GENE, VA_IGV_BOL_MANUAL_GENE, VA_IGV_FAC_MANUAL_GENE, VA_IGV_BOLETAS, VA_IGV_FACTURAS, VA_BOLETAS_AFECTO, VA_FACTURAS_AFECTO, VA_BOLETAS_AFECTO_MANUAL,  VA_FACTURAS_AFECTO_MANUAL, CA_BOLETAS, CA_FACTURAS, CA_GUIAS, VA_PEDIDO_ANULADO,CA_PEDIDOS_NO_COBRADO, VA_PEDIDOS_NO_COBRADO"};

    public String getcoCompania() {
        return coCompania;
    }

    public String getcoLocal() {
        return coLocal;
    }

    public String gettiMovCaja() {
        return tiMovCaja;
    }

    public Date getfeDiaVenta() {
        return feDiaVenta;
    }

    public String getnuSecUsuario() {
        return nuSecUsuario;
    }

    public Integer getnuCaja() {
        return nuCaja;
    }

    public Integer getnuTurno() {
        return nuTurno;
    }

    public String getidCreaMovimientoCaja() {
        return idCreaMovimientoCaja;
    }

    public String getapPaternoUsuario() {
        return apPaternoUsuario;
    }

    public String getapMaternoUsuario() {
        return apMaternoUsuario;
    }

    public String getnoUsuario() {
        return noUsuario;
    }

    public Double  getvaMontoGene() {
        return vaMontoGene;
    }

    public Integer getcaBoletasGene() {
        return caBoletasGene;
    }

    public Double  getvaMontoBoletasGene() {
        return vaMontoBoletasGene;
    }

    public Integer getcaFacturasGene() {
        return caFacturasGene;
    }

    public Double  getvaMontoFacturasGene() {
        return vaMontoFacturasGene;
    }

    public Integer getcaGuiasGene() {
        return caGuiasGene;
    }

    public Double  getvaMontoGuiasGene() {
        return vaMontoGuiasGene;
    }

    public Integer getcaBoletasAnul() {
        return caBoletasAnul;
    }

    public Double  getvaMontoBoletasAnul() {
        return vaMontoBoletasAnul;
    }

    public Integer getcaFacturasAnul() {
        return caFacturasAnul;
    }

    public Double  getvaMontoFacturasAnul() {
        return vaMontoFacturasAnul;
    }

    public Integer getcaGuiasAnul() {
        return caGuiasAnul;
    }

    public Double  getvaMontoGuiasAnul() {
        return vaMontoGuiasAnul;
    }

    public Double getvaMontoMoneda() {
        return vaMontoMoneda;
    }

    public Double  getvaMontoBoletas() {
        return vaMontoBoletas;
    }

    public Double getvaMontoFacturas() {
        return vaMontoFacturas;
    }

    public Double  getvaMontoGuias() {
        return vaMontoGuias;
    }

    public Double  getvaMontoAnul() {
        return vaMontoAnul;
    }

    public Double  getcjVaMontoMoneda() {
        return cjVaMontoMoneda;
    }

    public Integer getdiTotalMovimiento() {
        return diTotalMovimiento;
    }

    public Integer getcaPedidoAnulado() {
        return caPedidoAnulado;
    }

    public String getesCajaPago() {
        return esCajaPago;
    }

    public Date getfeCreaMovimientoCaja() {
        return feCreaMovimientoCaja;
    }

    public Date getfeCierreCaja() {
        return feCierreCaja;
    }

    public String getnuSecMovimiento() {
        return nuSecMovimiento;
    }

    public String getcoMoneda() {
        return coMoneda;
    }

    public String getinReplica() {
        return inReplica;
    }

    public Date getfeCierreTurno() {
        return feCierreTurno;
    }

    public Integer getcaBoletasManualGene() {
        return caBoletasManualGene;
    }

    public Double getvaBoletasManualGene() {
        return vaBoletasManualGene;
    }

    public Integer getcaFacturasManualGene() {
        return caFacturasManualGene;
    }

    public Double getvaFacturasManualGene() {
        return vaFacturasManualGene;
    }

    public Double getvaIgvBoletasGene() {
        return vaIgvBoletasGene;
    }

    public Double getvaIgvFacturasGene() {
        return vaIgvFacturasGene;
    }

    public Double getvaIgvBolManualGene() {
        return vaIgvBolManualGene;
    }

    public Double getvaIgvFacManualGene() {
        return vaIgvFacManualGene;
    }

    public Double getvaIgvBoletas() {
        return vaIgvBoletas;
    }

    public Double getvaIgvFacturas() {
        return vaIgvFacturas;
    }

    public Double getvaBoletasAfecto() {
        return vaBoletasAfecto;
    }

    public Double getvaFacturasAfecto() {
        return vaFacturasAfecto;
    }

    public Double getvaBoletasAfectoManual() {
        return vaBoletasAfectoManual;
    }

    public Double getvaFacturasAfectoManual() {
        return vaFacturasAfectoManual;
    }

    public Double getvaPedidoAnulado() {
        return vaPedidoAnulado;
    }


    public void setcoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public void setcoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public void settiMovCaja(String tiMovCaja) {
        this.tiMovCaja = tiMovCaja;
    }

    public void setfeDiaVenta(Date feDiaVenta) {
        this.feDiaVenta = feDiaVenta;
    }

    public void setnuSecUsuario(String nuSecUsuario) {
        this.nuSecUsuario = nuSecUsuario;
    }

    public void setnuCaja(Integer nuCaja) {
        this.nuCaja = nuCaja;
    }

    public void setnuTurno(Integer nuTurno) {
        this.nuTurno = nuTurno;
    }

    public Integer getcaBoletas() {
        return caBoletas;
    }

    public Integer getcaFacturas() {
        return caFacturas;
    }

    public Integer getcaGuias() {
        return caGuias;
    }

    public Integer getcaPedidosNoCobrado() {
        return caPedidosNoCobrado;
    }

    public Double getvaPedidosNoCobrado() {
        return vaPedidosNoCobrado;
    }

    public void setidCreaMovimientoCaja(String idCreaMovimientoCaja) {
        this.idCreaMovimientoCaja = idCreaMovimientoCaja;
    }

    public void setapPaternoUsuario(String apPaternoUsuario) {
        this.apPaternoUsuario = apPaternoUsuario;
    }

    public void setapMaternoUsuario(String apMaternoUsuario) {
        this.apMaternoUsuario = apMaternoUsuario;
    }

    public void setnoUsuario(String noUsuario) {
        this.noUsuario = noUsuario;
    }

    public void setvaMontoGene(Double  vaMontoGene) {
        this.vaMontoGene = vaMontoGene;
    }

    public void setcaBoletasGene(Integer caBoletasGene) {
        this.caBoletasGene = caBoletasGene;
    }

    public void setvaMontoBoletasGene(Double  vaMontoBoletasGene) {
        this.vaMontoBoletasGene = vaMontoBoletasGene;
    }

    public void setcaFacturasGene(Integer caFacturasGene) {
        this.caFacturasGene = caFacturasGene;
    }

    public void setvaMontoFacturasGene(Double  vaMontoFacturasGene) {
        this.vaMontoFacturasGene = vaMontoFacturasGene;
    }

    public void setcaGuiasGene(Integer caGuiasGene) {
        this.caGuiasGene = caGuiasGene;
    }

    public void setvaMontoGuiasGene(Double  vaMontoGuiasGene) {
        this.vaMontoGuiasGene = vaMontoGuiasGene;
    }

    public void setcaBoletasAnul(Integer caBoletasAnul) {
        this.caBoletasAnul = caBoletasAnul;
    }

    public void setvaMontoBoletasAnul(Double  vaMontoBoletasAnul) {
        this.vaMontoBoletasAnul = vaMontoBoletasAnul;
    }

    public void setcaFacturasAnul(Integer caFacturasAnul) {
        this.caFacturasAnul = caFacturasAnul;
    }

    public void setvaMontoFacturasAnul(Double  vaMontoFacturasAnul) {
        this.vaMontoFacturasAnul = vaMontoFacturasAnul;
    }

    public void setcaGuiasAnul(Integer caGuiasAnul) {
        this.caGuiasAnul = caGuiasAnul;
    }

    public void setvaMontoGuiasAnul(Double  vaMontoGuiasAnul) {
        this.vaMontoGuiasAnul = vaMontoGuiasAnul;
    }

    public void setvaMontoMoneda(Double vaMontoMoneda) {
        this.vaMontoMoneda = vaMontoMoneda;
    }

    public void setvaMontoBoletas(Double  vaMontoBoletas) {
        this.vaMontoBoletas = vaMontoBoletas;
    }

    public void setvaMontoFacturas(Double vaMontoFacturas) {
        this.vaMontoFacturas = vaMontoFacturas;
    }

    public void setvaMontoGuias(Double  vaMontoGuias) {
        this.vaMontoGuias = vaMontoGuias;
    }

    public void setvaMontoAnul(Double  vaMontoAnul) {
        this.vaMontoAnul = vaMontoAnul;
    }

    public void setcjVaMontoMoneda(Double  cjVaMontoMoneda) {
        this.cjVaMontoMoneda = cjVaMontoMoneda;
    }

    public void setdiTotalMovimiento(Integer diTotalMovimiento) {
        this.diTotalMovimiento = diTotalMovimiento;
    }

    public void setcaPedidoAnulado(Integer caPedidoAnulado) {
        this.caPedidoAnulado = caPedidoAnulado;
    }

    public void setesCajaPago(String esCajaPago) {
        this.esCajaPago = esCajaPago;
    }

    public void setfeCreaMovimientoCaja(Date feCreaMovimientoCaja) {
        this.feCreaMovimientoCaja = feCreaMovimientoCaja;
    }

    public void setfeCierreCaja(Date feCierreCaja) {
        this.feCierreCaja = feCierreCaja;
    }

    public void setnuSecMovimiento(String nuSecMovimiento) {
        this.nuSecMovimiento = nuSecMovimiento;
    }

    public void setcoMoneda(String coMoneda) {
        this.coMoneda = coMoneda;
    }

    public void setinReplica(String inReplica) {
        this.inReplica = inReplica;
    }

    public void setfeCierreTurno(Date feCierreTurno) {
        this.feCierreTurno = feCierreTurno;
    }

    public void setcaBoletasManualGene(Integer caBoletasManualGene) {
        this.caBoletasManualGene = caBoletasManualGene;
    }

    public void setvaBoletasManualGene(Double vaBoletasManualGene) {
        this.vaBoletasManualGene = vaBoletasManualGene;
    }

    public void setcaFacturasManualGene(Integer caFacturasManualGene) {
        this.caFacturasManualGene = caFacturasManualGene;
    }

    public void setvaFacturasManualGene(Double vaFacturasManualGene) {
        this.vaFacturasManualGene = vaFacturasManualGene;
    }

    public void setvaIgvBoletasGene(Double vaIgvBoletasGene) {
        this.vaIgvBoletasGene = vaIgvBoletasGene;
    }

    public void setvaIgvFacturasGene(Double vaIgvFacturasGene) {
        this.vaIgvFacturasGene = vaIgvFacturasGene;
    }

    public void setvaIgvBolManualGene(Double vaIgvBolManualGene) {
        this.vaIgvBolManualGene = vaIgvBolManualGene;
    }

    public void setvaIgvFacManualGene(Double vaIgvFacManualGene) {
        this.vaIgvFacManualGene = vaIgvFacManualGene;
    }
    
    public void setvaIgvBoletas(Double vaIgvBoletas) {
        this.vaIgvBoletas = vaIgvBoletas;
    }

    public void setvaIgvFacturas(Double vaIgvFacturas) {
        this.vaIgvFacturas = vaIgvFacturas;
    }

    public void setvaBoletasAfecto(Double caBoletasAfecto) {
        this.vaBoletasAfecto = caBoletasAfecto;
    }

    public void setvaFacturasAfecto(Double caFacturasAfecto) {
        this.vaFacturasAfecto = caFacturasAfecto;
    }

    public void setvaBoletasAfectoManual(Double caBoletasAfectoManual) {
        this.vaBoletasAfectoManual = caBoletasAfectoManual;
    }

    public void setvaFacturasAfectoManual(Double caFacturasAfectoManual) {
        this.vaFacturasAfectoManual = caFacturasAfectoManual;
    }

    public void setcaBoletas(Integer caBoletas) {
        this.caBoletas = caBoletas;
    }

    public void setcaFacturas(Integer caFacturas) {
        this.caFacturas = caFacturas;
    }

    public void setcaGuias(Integer caGuias) {
        this.caGuias = caGuias;
    }

    public void setvaPedidoAnulado(Double vaPedidoAnulado) {
        this.vaPedidoAnulado = vaPedidoAnulado;
    }

    public void setcaPedidosNoCobrado(Integer caPedidosNoCobrado) {
        this.caPedidosNoCobrado = caPedidosNoCobrado;
    }

    public void setvaPedidosNoCobrado(Double vaPedidosNoCobrado) {
        this.vaPedidosNoCobrado = vaPedidosNoCobrado;
    }

    public CCajaPago getRegistroPorPk(Object[] id){
        CCajaPago usr = null;
        try {
            rs =  this.selectPorPk(CCajaPago.nt,CCajaPago.FULL_NOM_CAMPOS, CCajaPago.COLUMNA_PK, id);
            while(rs.next())
            {
                usr = new CCajaPago();
                usr.setcoCompania(rs.getString(1));
                usr.setcoLocal(rs.getString(2));
                usr.setnuSecMovimiento(rs.getString(3));
                usr.settiMovCaja(rs.getString(4));
                usr.setfeDiaVenta(rs.getDate(5));
                usr.setnuSecUsuario(rs.getString(6));
                usr.setnuCaja(rs.getInt(7));
                usr.setnuTurno(rs.getInt(8));
                usr.setidCreaMovimientoCaja(rs.getString(9));
                usr.setapPaternoUsuario(rs.getString(10));
                usr.setapMaternoUsuario(rs.getString(11));
                usr.setnoUsuario(rs.getString(12));
                usr.setvaMontoGene(rs.getDouble(13));
                usr.setcaBoletasGene(rs.getInt(14));
                usr.setvaMontoBoletasGene(rs.getDouble(15));
                usr.setcaFacturasGene(rs.getInt(16));
                usr.setvaMontoFacturasGene(rs.getDouble(17));
                usr.setcaGuiasGene(rs.getInt(18));
                usr.setvaMontoGuiasGene(rs.getDouble(19));
                usr.setcaBoletasAnul(rs.getInt(20));
                usr.setvaMontoBoletasAnul(rs.getDouble(21));
                usr.setcaFacturasAnul(rs.getInt(22));
                usr.setvaMontoFacturasAnul(rs.getDouble(23));
                usr.setcaGuiasAnul(rs.getInt(24));
                usr.setvaMontoGuiasAnul(rs.getDouble(25));
                usr.setvaMontoMoneda(rs.getDouble(26));
                usr.setvaMontoBoletas(rs.getDouble(27));
                usr.setvaMontoFacturas(rs.getDouble(28));
                usr.setvaMontoGuias(rs.getDouble(29));
                usr.setvaMontoAnul(rs.getDouble(30));
                usr.setcjVaMontoMoneda(rs.getDouble(31));
                usr.setdiTotalMovimiento(rs.getInt(32));
                usr.setcaPedidoAnulado(rs.getInt(33));
                usr.setesCajaPago(rs.getString(34));
                usr.setfeCreaMovimientoCaja(rs.getDate(35));
                usr.setfeCierreCaja(rs.getDate(36));
                usr.setcoMoneda(rs.getString(37));
                usr.setinReplica(rs.getString(38));
                usr.setfeCierreTurno(rs.getDate(39));
                usr.setcaBoletasManualGene(rs.getInt(40));
                usr.setvaBoletasManualGene(rs.getDouble(41));
                usr.setcaFacturasManualGene(rs.getInt(42));
                usr.setvaFacturasManualGene(rs.getDouble(43));
                usr.setvaIgvBoletasGene(rs.getDouble(44));
                usr.setvaIgvFacturasGene(rs.getDouble(45));
                usr.setvaIgvBolManualGene(rs.getDouble(46));
                usr.setvaIgvFacManualGene(rs.getDouble(47));
                usr.setvaIgvBoletas(rs.getDouble(48));
                usr.setvaIgvFacturas(rs.getDouble(49));
                usr.setvaBoletasAfecto(rs.getDouble(50));
                usr.setvaFacturasAfecto(rs.getDouble(51));
                usr.setvaBoletasAfectoManual(rs.getDouble(52));
                usr.setvaFacturasAfectoManual(rs.getDouble(53));
                usr.setcaBoletas(rs.getInt(54));
                usr.setcaFacturas(rs.getInt(55));
                usr.setcaGuias(rs.getInt(56));
                usr.setvaPedidoAnulado(rs.getDouble(57));
                usr.setcaPedidosNoCobrado(rs.getInt(58));
                usr.setvaPedidosNoCobrado(rs.getDouble(59));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usr;
    }

    public String ImpresionWincha(String NuMovimientoCaja){
        DateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
        CCajaPago movimientoCaja = null;
        movimientoCaja = new CCajaPago().getRegistroPorPk(new Object[]{AtuxVariables.vCodigoCompania,AtuxVariables.vCodigoLocal,NuMovimientoCaja});
        String rutaImpresora;
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter pw = null;            

        String  TipoCambio="";
        
        ArrayList datos = new ArrayList();

        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        try {
            AtuxDBUtility.executeSQLStoredProcedureArrayList(datos, "INKVENTA_MANTENIMIENTO.RUTA_REPORTE_ACTIVO(?,?)", parametros);
        } catch (SQLException ex) {
            logger.error(ex);
        }

        if (datos.isEmpty())
            return "Error al obtener ruta de Impresora";

        rutaImpresora = ((String) ((ArrayList) datos.get(0)).get(1)).trim();
        System.out.println(rutaImpresora);

        //*** Obtiene Tipo de Cambio **
        ArrayList datos2 = new ArrayList();

        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(NuMovimientoCaja);
        try {
            AtuxDBUtility.executeSQLStoredProcedureArrayList(datos2, "ATUX_Movimiento_Caja.Detalle_Forma_Pago(?,?,?)", parametros);
        } catch (SQLException ex) {
            logger.error(ex);
        }

        if (datos.isEmpty())
            return "Error al obtener detalle de Forma de Pago";

        TipoCambio =((String) ((ArrayList) datos2.get(0)).get(6));
        
        try{
            fw = new FileWriter(rutaImpresora);
            bw = new BufferedWriter (fw);
            pw = new PrintWriter (bw);
        }catch(Exception e){
            logger.error(e);
        }

        Calendar fecha = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
        String fechaActual = dateFormat.format(fecha.getTime());
        
        Double SubTotal;

        pw.println("******** DETALLE DE CIERRE DE CAJA ********");
        pw.println("  Dia de Venta : " + Fecha.format(movimientoCaja.getfeDiaVenta()) );
        pw.println("  Cajero : " + movimientoCaja.getapMaternoUsuario().trim() + movimientoCaja.getapPaternoUsuario().trim() + movimientoCaja.getnoUsuario().trim());
        pw.println("  Caja : " + movimientoCaja.getnuCaja() + "        " + "Turno : " + movimientoCaja.getnuTurno());
        pw.println("  Fecha Hora : " + fechaActual );
        pw.println("  Tipo de Cambio : " + TipoCambio );
        pw.println(" ");
        pw.println("********  COMPROBANTES  GENERADOS  ********");
        pw.println(CompletaCeros(movimientoCaja.getcaBoletas().toString(),4)  + "  Boletas  " + CompletaCeros(movimientoCaja.getvaMontoBoletas().toString(),20));
        pw.println(CompletaCeros(movimientoCaja.getcaFacturas().toString(),4) + "  Facturas " + CompletaCeros(movimientoCaja.getvaMontoFacturas().toString(),20));
        pw.println(CompletaCeros(movimientoCaja.getcaGuias().toString(),4)    + "  Guias    " + CompletaCeros(movimientoCaja.getvaMontoGuias().toString(),20));
        SubTotal = (movimientoCaja.getvaMontoBoletas() + movimientoCaja.getvaMontoFacturas() + movimientoCaja.getvaMontoGuias());
        pw.println("  Total Generado : " + CompletaCeros(SubTotal.toString(),20));

        pw.println(" ");
        pw.println("********  COMPROBANTES   ANULADOS  ********");
        pw.println(CompletaCeros(movimientoCaja.getcaBoletasAnul().toString(),4)  + "  Boletas  " + CompletaCeros(movimientoCaja.getvaMontoBoletasAnul().toString(),20));
        pw.println(CompletaCeros(movimientoCaja.getcaFacturasAnul().toString(),4) + "  Facturas " + CompletaCeros(movimientoCaja.getvaMontoFacturasAnul().toString(),20));
        pw.println(CompletaCeros(movimientoCaja.getcaGuiasAnul().toString(),4)    + "  Guias    " + CompletaCeros(movimientoCaja.getvaMontoGuiasAnul().toString(),20));
        SubTotal = (movimientoCaja.getvaMontoBoletasAnul() + movimientoCaja.getvaMontoFacturasAnul() + movimientoCaja.getvaMontoGuiasAnul());
        pw.println("  Total Anulados : " + CompletaCeros(SubTotal.toString(),20));

        pw.println(" ");
        pw.println("***************  T O T A L  ***************");
        Double TotBoletas  = movimientoCaja.getvaMontoBoletas()  - movimientoCaja.getvaMontoBoletasAnul();
        Double TotFacturas = movimientoCaja.getvaMontoFacturas() - movimientoCaja.getvaMontoFacturasAnul();
        Double TotGuias    = movimientoCaja.getvaMontoGuias()    - movimientoCaja.getvaMontoGuiasAnul();
        pw.println("  Boletas  " + CompletaCeros(TotBoletas.toString(),20));
        pw.println("  Facturas " + CompletaCeros(TotFacturas.toString(),20));
        pw.println("  Guias    " + CompletaCeros(TotGuias.toString(),20));
        SubTotal = (TotBoletas + TotFacturas + TotGuias);
        pw.println("  Total          : " + CompletaCeros(SubTotal.toString(),20));
        pw.println(" ");
        pw.println(" ");
        pw.println(" ");
        pw.println("*******  DETALLE DE FORMA DE PAGO  ********");

        int f;
        String FormaPago;
        String CoFormaPago;
        String Monto;
        for(f=0;f<datos2.size();f++) {
            CoFormaPago = ((String) ((ArrayList) datos2.get(f)).get(0)).trim();
            FormaPago = ((String) ((ArrayList) datos2.get(f)).get(1)).trim();
            Monto    = ((String) ((ArrayList) datos2.get(f)).get(5));
            pw.println(CoFormaPago + "  " + FormaPago +  CompletaCeros(Monto,(36-FormaPago.length())));
        }
        pw.println("************** Observaciones **************");
        
        logger.info(movimientoCaja.getfeDiaVenta().toString());        
        int NuCaja=movimientoCaja.getnuCaja();
        int NuTurno=movimientoCaja.getnuTurno();
        
        CObsCaja ObservacionCaja = new CObsCaja();
        ArrayList Data01 = ObservacionCaja.LeerObservaciones(AtuxVariables.vCodigoCompania, 
                                                             AtuxVariables.vCodigoLocal, 
                                                             Fecha.format(movimientoCaja.getfeDiaVenta()), 
                                                             NuCaja, 
                                                             NuTurno);

        ObsCaja obs;
        for (int j = 0; j<Data01.size();  j++){
                obs =(ObsCaja)Data01.get(j);
                 pw.println("  " + obs.getnuSecObs() + "  -  " + obs.getdeObs());
          }
        pw.println("*******************************************");
        
        try{
            pw.close();
        }catch(Exception e){
            logger.error("error al cerrar el PrintWriter", e);
        }

        
        
        return "Impresión Generada Correctamente ";
    }
    
    public String getRealizaMovimientoCaja(String pMovCaja, String pFecha, String pCajero, int pNumeroCaja, int pNumeroTurno) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pMovCaja);
        parametros.add(pFecha);
        parametros.add(pCajero);
        parametros.add(pNumeroCaja);
        parametros.add(pNumeroTurno);
        parametros.add(AtuxVariables.vIdUsuario);
        return AtuxDBUtility.executeSQLStoredProcedureStr("Atux_Movimiento_Caja.Movimiento_Caja_Arqueo_Cierre(?,?,?,?,?,?,?,?)", parametros);
    }
    
    public String getRealizaArqueoGeneral(String pFecha) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pFecha);
        parametros.add(AtuxVariables.vIdUsuario);
        return AtuxDBUtility.executeSQLStoredProcedureStr("Atux_Movimiento_Caja.Movimiento_Arqueo_General(?,?,?,?)", parametros);
    }
    
    
    private static String CompletaCeros(String Valor, int Cantidad ){
        String Espacios= "                                                   ";
        String Dato;
        Dato = (Espacios + Valor );
        int Inicio = Dato.length()-Cantidad;
        Dato = Dato.substring(Inicio);
        return Dato;
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
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
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
}