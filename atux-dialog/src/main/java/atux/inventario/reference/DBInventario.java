package atux.inventario.reference;

import java.sql.SQLException;
import java.util.ArrayList;

import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxTableModel;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DBInventario {

	private static ArrayList parametros = new ArrayList();

	public DBInventario() {
	}

	public static void loadInvListaProductos(AtuxTableModel pTableModel) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		AtuxDBUtility.executeSQLStoredProcedure(pTableModel, "INKVENTA_INVENTARIO.INV_RELACIONPRODUCTOS(?,?)", parametros, true);
	}

	public static String getDescLaboratorio() throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(VariablesInventario.vCodigoProducto);
		return AtuxDBUtility.executeSQLStoredProcedureStr("INKVENTA_INVENTARIO.DESCRIP_LABORATORIO(?,?)", parametros);
	}

	public static String getIndicProdFraccionado() throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(VariablesInventario.vCodigoProducto);
		return AtuxDBUtility.executeSQLStoredProcedureStr("INKVENTA_INVENTARIO.INDICADOR_PROD_FRACCIONADO(?,?,?)", parametros);
	}

	public static int getValorFraccion() throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(VariablesInventario.vCodigoProducto);
		return AtuxDBUtility.executeSQLStoredProcedureInt("INKVENTA_INVENTARIO.VALOR_FRACCION(?,?,?)", parametros);
	}

	public static String getGrupoProducto() throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(VariablesInventario.vCodigoProducto);
		return AtuxDBUtility.executeSQLStoredProcedureStr("PTOVTA_VENTAS.GRUPOPRODUCTO(?,?)", parametros);
	}

	public static String getLineaProducto() throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(VariablesInventario.vCodigoProducto);
		return AtuxDBUtility.executeSQLStoredProcedureStr("PTOVTA_VENTAS.LINEAPRODUCTO(?,?)", parametros);
	}

	public static int stockDisponibleProducto() throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(VariablesInventario.vCodigoProducto);
		return AtuxDBUtility.executeSQLStoredProcedureInt("PTOVTA_VENTAS.STOCKDISPONIBLEPRODUCTO(?,?,?)", parametros);
	}

	public static void grabarCabeceraGuiaIngreso(String pTipoDocumento, String pNuDocumento, String pNombreProveedor, String pFechaRecepcion, int pCantItems, String pCodLocalOrigen,
			double pValorTotal, String pTipoIngreso) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pTipoDocumento);
		parametros.add(pNuDocumento);
		parametros.add(pNombreProveedor);
		parametros.add(AtuxVariables.vIdUsuario);
		parametros.add(pFechaRecepcion);
		parametros.add(new Integer(pCantItems));
		parametros.add(pCodLocalOrigen);
		parametros.add(new Double(pValorTotal));
		parametros.add(pTipoIngreso);
		parametros.add(new String(""));
		VariablesInventario.vNumeroGuiaIngreso = AtuxDBUtility.executeSQLStoredProcedureStr("INKVENTA_INVENTARIO.INV_GRABA_CABE_GUIA_INGRESO(?,?,?,?,?,?,?,?,?,?,?,?)", parametros);
	}

	public static void grabarDetalleGuiaIngreso(String pNumSecGuiaIngreso, int pNuItem, String pCodProducto, int pCantEntera, int pCantFraccion, String pFechaVencimiento, String pLote,
			String pPrecCompra, String pTotItem) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(VariablesInventario.vNumeroGuiaIngreso);
		parametros.add(new Integer(pNuItem));
		parametros.add(pCodProducto);
		parametros.add(new Integer(pCantEntera));
		parametros.add(new Integer(pCantFraccion));
		parametros.add(pFechaVencimiento);
		parametros.add(pLote);
		parametros.add(AtuxVariables.vIdUsuario);
		parametros.add(new Double(AtuxUtility.getDecimalNumber(pPrecCompra)));
		parametros.add(new Double(AtuxUtility.getDecimalNumber(pTotItem)));
		AtuxDBUtility.executeSQLStoredProcedure(null, "INKVENTA_INVENTARIO.INV_GRABA_DET_GUIA_INGRESO(?,?,?,?,?,?,?,?,?,?,?,?)", parametros, false);
	}

	public static void grabarKardexGuiaIngreso(String pCodProducto, int pCantMovimientos, String pTipoDocumento, String pNumDocumento) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pCodProducto);
		parametros.add(new Integer(pCantMovimientos));
		parametros.add(pTipoDocumento);
		parametros.add(pNumDocumento);
		parametros.add(AtuxVariables.vIdUsuario);
		parametros.add(ConstantsInventario.MOTIVO_KARDEX_GUIA_MANUAL);
		AtuxDBUtility.executeSQLStoredProcedure(null, "INKVENTA_INVENTARIO.INV_GRABA_KARDEX_GUIA_IN(?,?,?,?,?,?,?,?)", parametros, false);
	}

	public static double getPrecioCompraProd(String pTipoUnidad) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(VariablesInventario.vCodigoProducto);
		parametros.add(pTipoUnidad);
		return AtuxDBUtility.executeSQLStoredProcedureDouble("INKVENTA_INVENTARIO.GET_PRECIO_COMPRA_PROD(?,?,?,?)", parametros);
	}

	public static int getStockProd() throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(VariablesInventario.vCodigoProducto);
		return AtuxDBUtility.executeSQLStoredProcedureInt("INKVENTA_INVENTARIO.GET_STOCK_PRODUCTO(?,?,?)", parametros);
	}

	public static int getStockFraccProd() throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(VariablesInventario.vCodigoProducto);
		return AtuxDBUtility.executeSQLStoredProcedureInt("INKVENTA_INVENTARIO.GET_STOCK_FRACC_PRODUCTO(?,?,?)", parametros);
	}

	public static String getNameCompania() throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		return AtuxDBUtility.executeSQLStoredProcedureStr("INKVENTA_INVENTARIO.GET_NAME_COMPANIA(?)", parametros);
	}

	public static void getDetalleIngresoManual(AtuxTableModel pTableModel, String pNumRecepcionProd, String pTipo) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pNumRecepcionProd);
		parametros.add(pTipo);
		AtuxDBUtility.executeSQLStoredProcedure(pTableModel, "INKVENTA_INVENTARIO.GET_DETALLE_INGRESO_MANUAL(?,?,?,?)", parametros, false);
	}

	public static void updateDELSTOCKCOMPROMETIDOPRODUCTO(String pCodigoProducto, int pCantidad, boolean pWithCommit) throws SQLException {
		String queryUpdate = "UPDATE LGTR_PRODUCTO_LOCAL " + "   SET CA_STOCK_COMPROMETIDO = NVL(CA_STOCK_COMPROMETIDO,0) - " + pCantidad + " , " + "       IN_REPLICA = 0,"
				+ "       FE_MOD_PROD_LOCAL = SYSDATE" + " WHERE CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" + "   AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'"
				+ "   AND CO_PRODUCTO = '" + pCodigoProducto + "'";
		System.out.println("7. DBInventario.updateDELSTOCKCOMPROMETIDOPRODUCTO()");
		AtuxDBUtility.executeSQLUpdate(queryUpdate, pWithCommit);
	}

	public static void updateADDSTOCKCOMPROMETIDOPRODUCTO(String pCodigoProducto, int pCantidad, boolean pWithCommit) throws SQLException {
		String queryUpdate = "UPDATE LGTR_PRODUCTO_LOCAL " + "   SET CA_STOCK_COMPROMETIDO = NVL(CA_STOCK_COMPROMETIDO,0) + " + pCantidad + " , " + "       IN_REPLICA = 0,"
				+ "       FE_MOD_PROD_LOCAL = SYSDATE" + " WHERE CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" + "   AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'"
				+ "   AND CO_PRODUCTO = '" + pCodigoProducto + "'";
		System.out.println("8. DBInventario.updateADDSTOCKCOMPROMETIDOPRODUCTO()");
		AtuxDBUtility.executeSQLUpdate(queryUpdate, pWithCommit);
	}

	public static ArrayList obtieneDatosProducto(String pCodigoProducto) throws SQLException {
		ArrayList parameters = new ArrayList();
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pCodigoProducto);
		AtuxDBUtility.executeSQLStoredProcedureArrayList(parameters, "INKVENTA_INVENTARIO.GET_STOCK_PRODUCTO_LOCAL(?,?,?)", parametros);
		return parameters;
	}

	public static String getNombreLocal() throws SQLException {
		return AtuxDBUtility.getValueAt("VTTM_LOCAL", "DE_LOCAL", "CO_COMPANIA='" + AtuxVariables.vCodigoCompania + "' AND CO_LOCAL='" + VariablesInventario.vCodLocal
				+ "' AND ES_LOCAL='A' AND TI_LOCAL = '" + VariablesInventario.vTipoLocal + "'");
	}

	public static void loadRelacionLocales(AtuxTableModel pTableModel, String pTipoLocal) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pTipoLocal);
		AtuxDBUtility.executeSQLStoredProcedure(pTableModel, "INKVENTA_INVENTARIO.RELACION_LOCALES(?, ?, ?)", parametros, false);
	}

	public static int validaNumeroDocumento(String pNumeroDoc, String pCodLocalOrigen, String pTiDocumento) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pNumeroDoc);
		parametros.add(pCodLocalOrigen);
		parametros.add(pTiDocumento);
		return AtuxDBUtility.executeSQLStoredProcedureInt("INKVENTA_INVENTARIO.VALIDA_NUMERO_RECEP_PROD(?,?,?,?,?)", parametros);
	}

	public static String verificacionAnulacionGuia(String pNuRecepProd) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pNuRecepProd);
		return AtuxDBUtility.executeSQLStoredProcedureStr("INKVENTA_INVENTARIO.VERIFICACION_ANULACION_GUIA(?,?,?)", parametros);
	}

	public static void anulaGuiaIngreso(String pNuRecepProd, String pTipoDocumento, String pCodigoMotivo, String pCodigoGrupoMotivo) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pNuRecepProd);
		parametros.add(AtuxVariables.vIdUsuario);
		parametros.add(pTipoDocumento);
		parametros.add(pCodigoMotivo);
		parametros.add(pCodigoGrupoMotivo);
		System.out.println("AtuxVariables.vCodigoCompania: " + AtuxVariables.vCodigoCompania + "\nAtuxVariables.vCodigoLocal: " + AtuxVariables.vCodigoLocal + "\npNuRecepProd: " + pNuRecepProd
				+ "\nAtuxVariables.vIdUsuario: " + AtuxVariables.vIdUsuario + "\npTipoDocumento: " + pTipoDocumento + "\npCodigoMotivo: " + pCodigoMotivo + "\npCodigoGrupoMotivo: "
				+ pCodigoGrupoMotivo);
		AtuxDBUtility.executeSQLStoredProcedure(null, "INKVENTA_INVENTARIO.ANULA_GUIA_INGRESO(?, ?, ?, ?, ?, ?, ?)", parametros, false);
	}

	public static void execRespaldoStock(String tiOperacion, String pcNombre, String coProducto, String nuRevision, String cantidad) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(tiOperacion);
		parametros.add(pcNombre);
		parametros.add(coProducto);
		parametros.add(nuRevision);
		parametros.add(new Integer(cantidad));
		parametros.add(AtuxVariables.vIdUsuario);
		AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_UTILITY.RESPALDO_STOCK(?,?,?,?,?,?,?,?)", parametros, false);
	}

	public static String verificaAnulacionGuiaIngTomaInv(String pNumeroRecepProd) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pNumeroRecepProd);
		return AtuxDBUtility.executeSQLStoredProcedureStr("INKVENTA_INVENTARIO.VERIFICA_ANUL_GUIAING_TOMAINV(?, ?, ?)", parametros);

	}

	public static boolean validaTotGuiaInvRecepGuias() throws SQLException {
		String strWhere = "DET_REC.CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' " + "AND DET_REC.CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "' " + "AND DET_REC.NU_RECEPCION_PRODUCTO = '"
				+ VariablesInventario.vNumeroRecepProd + "' " + "AND DET_REC.IN_AFECTA_PRODUCTO IS NULL AND	PROD_LOC.CO_COMPANIA = DET_REC.CO_COMPANIA AND PROD_LOC.CO_LOCAL = DET_REC.CO_LOCAL "
				+ "AND PROD_LOC.CO_PRODUCTO = DET_REC.CO_PRODUCTO AND PROD_LOC.IN_PROD_INVENTARIO = 'S'";
		String strValTotGuiaInvRecepGuias = AtuxDBUtility.getValueAt("LGTD_RECEPCION_PRODUCTO DET_REC, LGTR_PRODUCTO_LOCAL PROD_LOC", "COUNT(*)", strWhere);
		if (Integer.parseInt(strValTotGuiaInvRecepGuias) > 0)
			return false;
		else
			return true;
	}

	public static boolean validaProductoInvRecepGuias(String pCodProducto) throws SQLException {
		String strWhere = "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' " + "AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "' " + "AND CO_PRODUCTO = '" + pCodProducto + "' "
				+ "AND IN_PROD_INVENTARIO = 'S'";
		String strValProdInvRecepGuias = AtuxDBUtility.getValueAt("LGTR_PRODUCTO_LOCAL", "COUNT(*)", strWhere);
		if (Integer.parseInt(strValProdInvRecepGuias) > 0)
			return false;
		else
			return true;
	}

	public static boolean validaPagGuiaInvRecepGuias(String pNumeroPag) throws SQLException {
		String strWhere = "DET_REC.CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' " + "AND DET_REC.CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "' " + "AND DET_REC.NU_RECEPCION_PRODUCTO = '"
				+ VariablesInventario.vNumeroRecepProd + "' " + "AND DET_REC.NU_PAGINA_GUIA = '" + pNumeroPag + "' "
				+ "AND DET_REC.IN_AFECTA_PRODUCTO IS NULL AND	PROD_LOC.CO_COMPANIA = DET_REC.CO_COMPANIA AND PROD_LOC.CO_LOCAL = DET_REC.CO_LOCAL "
				+ "AND PROD_LOC.CO_PRODUCTO = DET_REC.CO_PRODUCTO AND PROD_LOC.IN_PROD_INVENTARIO = 'S'";
		String strValPagGuiaInvRecepGuias = AtuxDBUtility.getValueAt("LGTD_RECEPCION_PRODUCTO DET_REC, LGTR_PRODUCTO_LOCAL PROD_LOC", "COUNT(*)", strWhere);
		if (Integer.parseInt(strValPagGuiaInvRecepGuias) > 0)
			return false;
		else
			return true;
	}

	// Inicio Id=001
	public static void grabarKardexGuiaIngresoCosteo(String pCodProducto, int pCantMovimientos, String pTipoDocumento, String pNumDocumento, String precioCompra) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pCodProducto);
		parametros.add(new Double(AtuxUtility.getDecimalNumber(precioCompra.trim())));
		parametros.add(new Integer(pCantMovimientos));
		parametros.add(pTipoDocumento);
		parametros.add(pNumDocumento);
		parametros.add(AtuxVariables.vIdUsuario);
		parametros.add(ConstantsInventario.MOTIVO_KARDEX_GUIA_MANUAL);
		AtuxDBUtility.executeSQLStoredProcedure(null, "Inkventa_Inventario.INV_GRABA_KARDEX_GUIA_IN_COST(?,?,?,?,?,?,?,?,?)", parametros, false);
	}

	public static double obtenerMontoConsumido() throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		return AtuxDBUtility.executeSQLStoredProcedureDouble("INKVENTA_INVENTARIO.OBTENER_MONTO_USADO_COTIZACION (?,?)", parametros);
	}

	public static double obtenerMontoMaximoCotizacion() throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		return AtuxUtility.getDecimalNumber(AtuxDBUtility.getValueAt("VTTM_LOCAL", "nvl(va_monto_cotizacion,0)", "CO_COMPANIA='" + AtuxVariables.vCodigoCompania + "' AND CO_LOCAL='"
				+ AtuxVariables.vCodigoLocal + "'"));
	}

	public static String getPrecioProducto(String pCodProducto) throws SQLException {
		String campo = "TO_CHAR((DECODE(VA_VENTA, NULL, 0, VA_VENTA) - (DECODE(VA_VENTA, NULL, 0, VA_VENTA) * " + "(1 - (1 - (DECODE(PC_DESCUENTO_1, NULL, 0, PC_DESCUENTO_1)/100))))), '999,990.00')";

		String where = "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "' " + " AND CO_PRODUCTO = '" + pCodProducto + "'";

		return AtuxDBUtility.getValueAt("LGTR_PRODUCTO_LOCAL", campo, where);
	}

	public static String getProductoClinicoORecetario(String pCodProducto, boolean pIncluyeProdTerminados) throws SQLException {
		String where = "CO_COMPANIA = '001' AND CO_PRODUCTO = '" + pCodProducto + "' AND CO_LABORATORIO IN ('0360', '0386', '0372') ";

		if (pIncluyeProdTerminados) {
			where += " AND TI_MATERIAL_SAP <> 'FERT'";
		}

		return AtuxDBUtility.getValueAt("LGTM_PRODUCTO", "COUNT(1)", where);
	}

	public static String getCantidadReservada(String pTiPedido, String pNuRecepcionBase, String pCodProducto, String pCoLocalAlmacen) throws SQLException {

		String campos = " SUM(NVL(LSPD.CA_SOLICITADA_ENTERA,0)) ";
		String tabla = "  LGTC_SOLICITUD_PEDIDO LSPC, " + "  LGTD_SOLICITUD_PEDIDO LSPD ";
		String where = " LSPC.CO_COMPANIA ='" + AtuxVariables.vCodigoCompania + "'" + "  AND LSPC.CO_LOCAL = '" + pCoLocalAlmacen + "'" + "  AND LSPC.NU_RECEPCION_PRODUCTO_BASE = '"
				+ pNuRecepcionBase + "'" + "  AND LSPC.TI_SOLICITUD_PEDIDO='" + pTiPedido + "' " + "  AND LSPC.ES_SOLICITUD_PEDIDO <> 'N'" + "  AND LSPD.CO_PRODUCTO = '" + pCodProducto + "'"
				+ "  AND LSPD.NU_REVISION_PRODUCTO = '0'" + "  AND LSPD.CO_COMPANIA = LSPC.CO_COMPANIA" + "  AND LSPD.CO_LOCAL = LSPC.CO_LOCAL"
				+ "  AND LSPD.NU_SOLICITUD_PEDIDO = LSPC.NU_SOLICITUD_PEDIDO";

		return AtuxDBUtility.getValueAt(tabla, campos, where);
	}

}
