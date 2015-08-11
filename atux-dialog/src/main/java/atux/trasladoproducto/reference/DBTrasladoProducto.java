package atux.trasladoproducto.reference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import atux.inventario.reference.ConstantsInventario;
import atux.managerbd.BaseConexion;
import atux.replicacion.CmtsReplicacionPk;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxTableModel;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.util.print.AtuxPRNUtility;

import com.aw.core.domain.AWBusinessException;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class DBTrasladoProducto {

	private static final Log logger = LogFactory.getLog(DBTrasladoProducto.class);

	private static ArrayList parametros = new ArrayList();

	public static String getDeTipoPedido(String tiPedido) throws SQLException {
		return AtuxDBUtility.getValueAt("CMTR_TIPO_PEDIDO_TRASLADO", "DE_CORTA_TIPO_PEDIDO_TRASLADO", "CO_COMPANIA='" + AtuxVariables.vCodigoCompania + "' AND TI_PEDIDO_TRASLADO='" + tiPedido + "' ");
	}

	public static String getNombreLocal() throws SQLException {
		return AtuxDBUtility.getValueAt("VTTM_LOCAL", "DE_LOCAL", "CO_COMPANIA='" + AtuxVariables.vCodigoCompania + "' AND CO_LOCAL='" + VariablesTrasladoProducto.vCodLocal
				+ "' AND ES_LOCAL='A' AND TI_LOCAL = '" + VariablesTrasladoProducto.vTipoLocal + "'");
	}

	public static boolean esAlmacen(String coLocal) throws SQLException {
		String contador = AtuxDBUtility.getValueAt("VTTM_LOCAL", "COUNT(*)", "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' and " + "CO_LOCAL = '" + coLocal + "' and " + "TI_LOCAL = 'A' ");
		return Integer.parseInt(contador) >= 1;
	}

	// Inicio ID: 001
	public static boolean esAlmacenORecetario(String coLocal) throws SQLException {
		if (ConstantsTrasladoProducto.CO_FORMULARIO_MAGISTRAL.equals(coLocal)) {
			return true;
		}
		return !AtuxDBUtility.getValueAt("VTTM_LOCAL", "count(1)", "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' AND CO_LOCAL = '" + coLocal + "' AND TI_LOCAL = 'A'").equals("0");
	}

	// Fin ID: 001

	public static String getCoLocalSap(String coLocal) throws SQLException {
		return AtuxDBUtility.getValueAt("VTTM_LOCAL", "CO_LOCAL_SAP", "CO_COMPANIA='" + AtuxVariables.vCodigoCompania + "' AND CO_LOCAL='" + coLocal + "' ");
	}

	public static String obtenerCoLocalOrigenDeRecepcion(String nuRecepcion) throws SQLException {
		StringBuffer where = new StringBuffer();
		where.append("  CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' AND ");
		where.append("  CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "' AND ");
		where.append("  NU_RECEPCION_PRODUCTO = '" + nuRecepcion + "'  ");
		return AtuxDBUtility.getValueAt("LGTC_RECEPCION_PRODUCTO", "CO_LOCAL_ORIGEN", where.toString());
	}

	public static String obtenerNoResponsable(String pIdResponsable) throws SQLException {
		StringBuffer where = new StringBuffer();
		where.append("  CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' AND ");
		where.append("  CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "' AND ");
		where.append("  ID_USUARIO = '" + pIdResponsable + "'  ");
		return AtuxDBUtility.getValueAt("CMTS_USUARIO", "NO_USUARIO || ' ' || AP_PATERNO_USUARIO || ' ' || AP_MATERNO_USUARIO", where.toString());
	}

	public static String obtenerTipoSolicitudPedido(String pNuSolicitudPedido) throws SQLException {
		StringBuffer where = new StringBuffer();
		where.append("  CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' AND ");
		where.append("  CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "' AND ");
		where.append("  NU_SOLICITUD_PEDIDO = '" + pNuSolicitudPedido + "'  ");
		return AtuxDBUtility.getValueAt("LGTC_SOLICITUD_PEDIDO", "TI_SOLICITUD_PEDIDO", where.toString());
	}

	public static String obtenerEstadoDeRecepcion(String nuRecepcion) throws SQLException {
		StringBuffer where = new StringBuffer();
		where.append("  CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' AND ");
		where.append("  CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "' AND ");
		where.append("  NU_RECEPCION_PRODUCTO = '" + nuRecepcion + "'  ");
		return AtuxDBUtility.getValueAt("LGTC_RECEPCION_PRODUCTO", "ES_RECEPCION_PRODUCTO", where.toString());
	}

	public static String obtenerDeLocal(String coLocal) throws SQLException {
		StringBuffer where = new StringBuffer();
		where.append("  CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' AND ");
		where.append("  CO_LOCAL = '" + coLocal + "' ");
		return AtuxDBUtility.getValueAt("VTTM_LOCAL", "DE_CORTA_LOCAL", where.toString());
	}

	public static ArrayList obtenerProductoInfo(String pCoCompania, String pCoLocal, String pCoProducto) throws SQLException {

		ArrayList productoLocalInfo = new ArrayList();
		AtuxDBUtility.getValueAtInArrayList("LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL", "NVL(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,'N')|| 'Ã' ||" + "   NVL(PRODUCTO_LOCAL.DE_UNIDAD_FRACCION,' ')|| 'Ã' ||"
				+ "   NVL(PRODUCTO_LOCAL.VA_FRACCION,0)", "PRODUCTO_LOCAL.CO_COMPANIA = '" + pCoCompania + "'" + "           AND PRODUCTO_LOCAL.CO_LOCAL = '" + pCoLocal + "' "
				+ "           AND PRODUCTO_LOCAL.CO_PRODUCTO = '" + pCoProducto + "' ", productoLocalInfo);
		return productoLocalInfo;
	}

	public static ArrayList obtenerSolicitudOriginalInfo(String pCoCompania, String pCoLocal, String pNuRecepcionProducto) throws SQLException {
		ArrayList solicitudOriginalInfo = new ArrayList();
		StringBuffer where = new StringBuffer();
		where.append("      REPRO.CO_COMPANIA = SOLPED.CO_COMPANIA AND");
		where.append("      REPRO.CO_LOCAL = SOLPED.CO_LOCAL AND");
		where.append("      REPRO.NU_SOLICITUD_PEDIDO = SOLPED.NU_SOLICITUD_PEDIDO AND");
		where.append("      REPRO.CO_COMPANIA = '" + pCoCompania + "' AND       ");
		where.append("      REPRO.CO_LOCAL = '" + pCoLocal + "' AND");
		where.append("      REPRO.NU_RECEPCION_PRODUCTO = '" + pNuRecepcionProducto + "' ");
		AtuxDBUtility.getValueAtInArrayList(" LGTC_RECEPCION_PRODUCTO REPRO, LGTC_SOLICITUD_PEDIDO SOLPED ", " SOLPED.TI_SOLICITUD_PEDIDO || 'Ã' || SOLPED.NU_SOLICITUD_PEDIDO ", where.toString(),
				solicitudOriginalInfo);
		return solicitudOriginalInfo;
	}

	public static void updateLgtcSolicitudPedido(String coCompania, String coLocal, String nuSolicitudPedido, String estado) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("    UPDATE LGTC_SOLICITUD_PEDIDO");
		sql.append("    SET   ");
		sql.append("        ES_SOLICITUD_PEDIDO = '" + estado + "' ,  ");
		sql.append("        ID_MOD_SOLICITUD_PEDIDO	= '" + AtuxVariables.vIdUsuario + "' ,");
		sql.append("        FE_MOD_SOLICITUD_PEDIDO	=SYSDATE ");
		sql.append("    WHERE ");
		sql.append("        CO_COMPANIA	= '" + coCompania + "' AND");
		sql.append("        CO_LOCAL	= '" + coLocal + "' AND");
		sql.append("        NU_SOLICITUD_PEDIDO	= '" + nuSolicitudPedido + "'");
		AtuxDBUtility.executeSQLUpdate(sql.toString(), false);
	}

	public static void updateLgtdSolicitudPedidoEstado(String coCompania, String coLocal, String nuSolicitudPedido, String estado) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("    UPDATE LGTD_SOLICITUD_PEDIDO");
		sql.append("    SET   ");
		sql.append("        ES_DET_SOLICITUD_PEDIDO = '" + estado + "' ,  ");
		sql.append("        ID_MOD_DET_SOLICITUD_PEDIDO	= '" + AtuxVariables.vIdUsuario + "' ,");
		sql.append("        FE_MOD_DET_SOLICITUD_PEDIDO	=SYSDATE ");
		sql.append("    WHERE ");
		sql.append("        CO_COMPANIA	= '" + coCompania + "' AND");
		sql.append("        CO_LOCAL	= '" + coLocal + "' AND");
		sql.append("        NU_SOLICITUD_PEDIDO	= '" + nuSolicitudPedido + "'");
		AtuxDBUtility.executeSQLUpdate(sql.toString(), false);
	}

	public static void updateLgtdSolicitudPedido(String coCompania, String coLocal, String nuSolicitudPedido, int nuItemSolicitudPedido, int caAtendidaEntera, String inProdFraccionadoDestino,
			Integer vaFraccionDestino, String deUnidadProductoDestino, String deUnidadFraccionDestino, String estado) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("    UPDATE LGTD_SOLICITUD_PEDIDO");
		sql.append("    SET   ");
		sql.append("        CA_ATENDIDA_ENTERA = " + caAtendidaEntera + ", ");
		sql.append("        IN_PROD_FRACCIONADO_DESTINO = '" + inProdFraccionadoDestino + "' ,");
		sql.append("        VA_FRACCION_DESTINO = " + vaFraccionDestino + " ,");
		sql.append("        DE_UNIDAD_PRODUCTO_DESTINO = '" + deUnidadProductoDestino + "' ,");
		sql.append("        DE_UNIDAD_FRACCION_DESTINO = '" + deUnidadFraccionDestino + "' ,");
		sql.append("        ID_MOD_DET_SOLICITUD_PEDIDO	='" + AtuxVariables.vIdUsuario + "' ,");
		sql.append("        FE_MOD_DET_SOLICITUD_PEDIDO	=SYSDATE,");
		sql.append("        ES_DET_SOLICITUD_PEDIDO	='" + estado + "'");
		sql.append("    WHERE ");
		sql.append("        CO_COMPANIA	= '" + coCompania + "' AND");
		sql.append("        CO_LOCAL	= '" + coLocal + "' AND");
		sql.append("        NU_SOLICITUD_PEDIDO	= '" + nuSolicitudPedido + "' AND");
		sql.append("        NU_ITEM_SOLICITUD_PEDIDO = " + nuItemSolicitudPedido);
		AtuxDBUtility.executeSQLUpdate(sql.toString(), false);
	}

	public static void updateLgtcRecepcionProductoCaProducto(String coCompania, String coLocal, String nuRecepcionProducto, int totalItems, int totalProductos) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("    UPDATE LGTC_RECEPCION_PRODUCTO ");
		sql.append("    SET   ");
		sql.append("        CA_ITEM_RECEPCION = " + totalItems + ",  ");
		sql.append("        CA_PROD_RECEPCION = " + totalProductos + "  ");
		sql.append("    WHERE ");
		sql.append("        CO_COMPANIA	= '" + coCompania + "' AND");
		sql.append("        CO_LOCAL	= '" + coLocal + "' AND");
		sql.append("        NU_RECEPCION_PRODUCTO	= '" + nuRecepcionProducto + "'");
		AtuxDBUtility.executeSQLUpdate(sql.toString(), false);
	}

	public static void cargarListaDeProductos(AtuxTableModel pTableModel, String pNuRecepcionProducto, String coAlmacen, String inUsarOtrosProds) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pNuRecepcionProducto);
		parametros.add(coAlmacen);
		parametros.add(inUsarOtrosProds);
		if (logger.isInfoEnabled()) {
			logger.info("Cargando Prods en base a:" + parametros);
		}

		AtuxDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVTA_TRASLADO_PRODUCTO.OBTENER_RELACION_PRODUCTOS(?,?,?,?,?)", parametros, true);
	}

	public static void grabarCabeceraPedidoTraslado(String pCoCompania, String pColocal, String pNuSolicitudPedido, String pTipoSolicitudPedido, String pCoLocalDestino, String pNuRecepcionProducto,
			int pCantidadProductos, String pEsSolicitudPedido, String pIdResponsable, String pNoResponsable, String pIdCreaSolicitud, String pCodigoCompaniaBase, String pCodigoLocalBase,
			String pNuRecepcionPedidoBase, String pNuSolicitudOriginal, String pTiSolicitudOriginal, String pCoMotivo) throws SQLException {
		parametros = new ArrayList();
		parametros.add(pCoCompania);
		parametros.add(pColocal);
		parametros.add(pNuSolicitudPedido);
		parametros.add(pTipoSolicitudPedido);
		parametros.add(pCoLocalDestino);
		parametros.add(pNuRecepcionProducto);
		parametros.add(new Integer(pCantidadProductos));
		parametros.add(pEsSolicitudPedido);
		parametros.add(pIdResponsable);
		parametros.add(pNoResponsable);
		parametros.add(pIdCreaSolicitud);
		parametros.add(pCodigoCompaniaBase);
		parametros.add(pCodigoLocalBase);
		parametros.add(pNuRecepcionPedidoBase);
		parametros.add(pNuSolicitudOriginal);
		parametros.add(pTiSolicitudOriginal);
		parametros.add(pCoMotivo);
		
		System.out.println("Grabando Cabecera Solicitud Pedido:" + parametros);
		AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_TRASLADO_PRODUCTO.GRABAR_CABECERA_PEDIDO_TRAS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", parametros, false);
	}

	public static void grabarDetallePedidoTraslado(String pCoCompania, String pColocal, String pNuSolicitudPedido, int pNuItemSolicitudPedido, String pCoProducto, String pNuRevisionProducto,
			int pCaSolicitadaEntera, String pInProdFraccionado, Integer pVaFraccion, String pDeUnidadProducto, String pDeUnidadFraccion, String pIdCreaSolicituddet) throws SQLException {
		parametros = new ArrayList();
		parametros.add(pCoCompania);
		parametros.add(pColocal);
		parametros.add(pNuSolicitudPedido);
		parametros.add(new Integer(pNuItemSolicitudPedido));
		parametros.add(pCoProducto);
		parametros.add(pNuRevisionProducto);
		parametros.add(new Integer(pCaSolicitadaEntera));
		parametros.add(pInProdFraccionado);
		parametros.add(pVaFraccion);
		parametros.add(pDeUnidadProducto);
		parametros.add(pDeUnidadFraccion);
		parametros.add(pIdCreaSolicituddet);
		if (logger.isDebugEnabled()) {
			logger.debug("Grabando Detalle Solicitud Pedido:" + parametros);
		}
		AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_TRASLADO_PRODUCTO.GRABAR_DETALLE_PEDIDO_TRAS(?,?,?,?,?,?,?,?,?,?,?,?)", parametros, false);
	}

	public static void grabarCabeceraRecepcion(String pCoCompania, String pColocal, String pNuRecepcionProducto, String pNuSolicitudPedido, String pTiDocumentoRecepcion, String pNuDocumentoRecepcion,
			String pIdCreaSolicitud, String pCoLocalOrigen) throws SQLException {
		parametros = new ArrayList();
		parametros.add(pCoCompania);
		parametros.add(pColocal);
		parametros.add(pNuRecepcionProducto);
		parametros.add(pNuSolicitudPedido);
		parametros.add(pTiDocumentoRecepcion);
		parametros.add(pNuDocumentoRecepcion);
		parametros.add(pIdCreaSolicitud);
		parametros.add(pCoLocalOrigen);
		if (logger.isDebugEnabled()) {
			logger.debug("Grabando Cabecera Recepcion Producto:" + parametros);
		}
		AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_TRASLADO_PRODUCTO.GRABAR_CABECERA_RECEPCION(?,?,?,?,?,?,?,?)", parametros, false);
	}

	public static void grabarDetalleRecepcion(String pCoCompania, String pColocal, String pNuRecepcionProducto, int pNumeroItemRecepcionProducto, int pNumeroItemSolicitudPedido,
			String pCodigoProducto, String pNuRevisionProducto, int pCantidadEntera, String pInProdFraccionado, Integer pVaFraccion, String pIdCreaRecepcion, String pNuGuiaRecepcion, int pNuPagina,
			int pCaItems, String pNuSolicitudPedido, String pTipoPedido) throws SQLException {
		parametros = new ArrayList();
		parametros.add(pCoCompania);
		parametros.add(pColocal);
		parametros.add(pNuRecepcionProducto);
		parametros.add(new Integer(pNumeroItemRecepcionProducto));
		parametros.add(new Integer(pNumeroItemSolicitudPedido));
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pCodigoProducto);
		parametros.add(pNuRevisionProducto);
		parametros.add(new Integer(pCantidadEntera));
		parametros.add(pInProdFraccionado);
		parametros.add(pVaFraccion);
		parametros.add(pIdCreaRecepcion);
		parametros.add(pNuGuiaRecepcion);
		parametros.add(new Integer(pNuPagina));
		parametros.add(new Integer(pCaItems));
		parametros.add(pNuSolicitudPedido);
		parametros.add(pTipoPedido);
		if (logger.isDebugEnabled()) {
			logger.debug("Grabando Detalle Recepcion Producto:" + parametros);
		}
		AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_TRASLADO_PRODUCTO.GRABAR_DETALLE_RECEPCION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", parametros, false);
	}

	public static void actualizarNumSecGuiaSalida(String pNumero) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pNumero);
		AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_TRASLADO_PRODUCTO.ACTUALIZAR_NUM_GUIA_SALIDA(?,?,?)", parametros, false);
	}

	public static CmtsReplicacionPk replicar(String pCoCompania, String pCoLocal, String pNuSolicitudPedido, String pCoLocalDestino) {
		String grupoReplicacion = "PedidoTraslado";
		String idDataReplicar = "SolPed-" + pCoCompania + "-" + pCoLocal + "-" + pNuSolicitudPedido;
		String pkSolicitudPedido = "CO_COMPANIA='" + pCoCompania + "' and CO_LOCAL='" + pCoLocal + "' and  NU_SOLICITUD_PEDIDO='" + pNuSolicitudPedido + "'";
		String deParametros = "TABLA::LGTC_SOLICITUD_PEDIDO,WHERE::<text>" + pkSolicitudPedido + "</text>,USAR_PK::ORIGEN;" + "TABLA::LGTD_SOLICITUD_PEDIDO,WHERE::<text>" + pkSolicitudPedido
				+ "</text>,USAR_PK::ORIGEN'";
		return replicarGenerico(pCoCompania, pCoLocalDestino, grupoReplicacion, idDataReplicar, deParametros);
	}

	public static CmtsReplicacionPk replicarSolitudPedidoUpdate(String pCoCompania, String pCoLocal, String pNuSolicitudPedido, String pCoLocalOrigen, String pCoLocalDestino) {
		String grupoReplicacion = "PedidoTrasladoUpdate";
		String idDataReplicar = "SolPed-" + pCoCompania + "-" + pCoLocal + "-" + pNuSolicitudPedido;
		String pkSolicitudPedido = "CO_COMPANIA='" + pCoCompania + "' and CO_LOCAL='" + pCoLocal + "' and  NU_SOLICITUD_PEDIDO='" + pNuSolicitudPedido + "'";
		String deParametrosCabecera = "TABLA::LGTC_SOLICITUD_PEDIDO,WHERE::<text>" + pkSolicitudPedido
				+ "</text>,USAR_PK::ORIGEN,INCLUIR_COLS::ES_SOLICITUD_PEDIDO:ID_MOD_SOLICITUD_PEDIDO:FE_MOD_SOLICITUD_PEDIDO;";
		String deParametrosDetalle = "TABLA::LGTD_SOLICITUD_PEDIDO,WHERE::<text>"
				+ pkSolicitudPedido
				+ "</text>,USAR_PK::ORIGEN,INCLUIR_COLS::CA_ATENDIDA_ENTERA:IN_PROD_FRACCIONADO_DESTINO:VA_FRACCION_DESTINO:DE_UNIDAD_PRODUCTO_DESTINO:DE_UNIDAD_FRACCION_DESTINO:ID_MOD_DET_SOLICITUD_PEDIDO:FE_MOD_DET_SOLICITUD_PEDIDO:ES_DET_SOLICITUD_PEDIDO";
		String deParametros = deParametrosCabecera + deParametrosDetalle;
		return replicarGenerico(pCoCompania, pCoLocalDestino, grupoReplicacion, idDataReplicar, deParametros);
	}

	// Inicio ID: 002
	public static CmtsReplicacionPk replicarSolitudPedidoUpdateYRecepcionProducto(String pCoCompania, String pCoLocal, String pNuSolicitudPedido, String pNuRecepcionProducto, String pCoLocalOrigen,
			String pCoLocalDestino) {
		String grupoReplicacion = "PedTrasUpdYRecProd";
		String idDataReplicar = "SPRePr-" + pCoCompania + "-" + pCoLocal + "-" + pNuSolicitudPedido;
		String pkSolicitudPedido = "CO_COMPANIA='" + pCoCompania + "' and CO_LOCAL='" + pCoLocal + "' and  NU_SOLICITUD_PEDIDO='" + pNuSolicitudPedido + "'";
		String deParamCabeceraSolPedido = "TABLA::LGTC_SOLICITUD_PEDIDO,WHERE::<text>" + pkSolicitudPedido
				+ "</text>,USAR_PK::ORIGEN,INCLUIR_COLS::ES_SOLICITUD_PEDIDO:ID_MOD_SOLICITUD_PEDIDO:FE_MOD_SOLICITUD_PEDIDO;";
		String deParamDetalleSolPedido = "TABLA::LGTD_SOLICITUD_PEDIDO,WHERE::<text>"
				+ pkSolicitudPedido
				+ "</text>,USAR_PK::ORIGEN,INCLUIR_COLS::CA_ATENDIDA_ENTERA:IN_PROD_FRACCIONADO_DESTINO:VA_FRACCION_DESTINO:DE_UNIDAD_PRODUCTO_DESTINO:DE_UNIDAD_FRACCION_DESTINO:ID_MOD_DET_SOLICITUD_PEDIDO:FE_MOD_DET_SOLICITUD_PEDIDO:ES_DET_SOLICITUD_PEDIDO;";
		String deParamSolPedido = deParamCabeceraSolPedido + deParamDetalleSolPedido;
		String pkRecepcionProd = "CO_COMPANIA='" + pCoCompania + "' and CO_LOCAL='" + pCoLocal + "' and  NU_RECEPCION_PRODUCTO='" + pNuRecepcionProducto + "'";
		String deParamCabeceraRecProducto = "TABLA::LGTC_RECEPCION_PRODUCTO,WHERE::<text>" + pkRecepcionProd + "</text>,USAR_PK::ORIGEN;";
		String deParamDetalleRecProducto = "TABLA::LGTD_RECEPCION_PRODUCTO,WHERE::<text>" + pkRecepcionProd + "</text>,USAR_PK::ORIGEN'";
		String deParamRecProducto = deParamCabeceraRecProducto + deParamDetalleRecProducto;
		String deParam = deParamSolPedido + deParamRecProducto;
		return replicarGenerico(pCoCompania, pCoLocalDestino, grupoReplicacion, idDataReplicar, deParam);
	}

	// Fin ID: 002

	public static CmtsReplicacionPk replicarRecepcionProducto(String pCoCompania, String pCoLocal, String pNuRecepcionProducto, String pCoLocalOrigen, String pCoLocalDestino) {
		String grupoReplicacion = "RecepcionProducto";
		return replicarRecepcionProducto(grupoReplicacion, pCoCompania, pCoLocal, pNuRecepcionProducto, pCoLocalOrigen, pCoLocalDestino);
	}

	public static CmtsReplicacionPk replicarRecepcionProductoUpdate(String pCoCompania, String pCoLocal, String pNuRecepcionProducto, String pCoLocalOrigen, String pCoLocalDestino) {
		String grupoReplicacion = "RecepcionProductoUpdate";
		return replicarRecepcionProducto(grupoReplicacion, pCoCompania, pCoLocal, pNuRecepcionProducto, pCoLocalOrigen, pCoLocalDestino);
	}

	public static CmtsReplicacionPk replicarRecepcionProducto(String grupoReplicacion, String pCoCompania, String pCoLocal, String pNuRecepcionProducto, String pCoLocalOrigen, String pCoLocalDestino) {
		String idDataReplicar = "RecPro-" + pCoCompania + "-" + pCoLocal + "-" + pNuRecepcionProducto;
		String pkRecepcionProd = "CO_COMPANIA='" + pCoCompania + "' and CO_LOCAL='" + pCoLocal + "' and  NU_RECEPCION_PRODUCTO='" + pNuRecepcionProducto + "'";
		String deParametrosCabecera = "TABLA::LGTC_RECEPCION_PRODUCTO,WHERE::<text>" + pkRecepcionProd + "</text>,USAR_PK::ORIGEN;";
		String deParametrosDetalle = "TABLA::LGTD_RECEPCION_PRODUCTO,WHERE::<text>" + pkRecepcionProd + "</text>,USAR_PK::ORIGEN'";
		String deParametros = deParametrosCabecera + deParametrosDetalle;
		return replicarGenerico(pCoCompania, pCoLocalDestino, grupoReplicacion, idDataReplicar, deParametros);
	}

	public static CmtsReplicacionPk replicarGenerico(String pCoCompania, String pCoLocalDestino, String grupoReplicacion, String idDataReplicar, String deParametros) {
		String coReplicador = "";
		String coLocal = AtuxVariables.vCodigoLocal;
		PreparedStatement pre = null;
		try {
			String seqCoReplicador = AtuxDBUtility.getValueAt("dual", "SEQ_CODIGO_REPLICACION.nextval", "1=1");
			coReplicador = AtuxPRNUtility.caracterIzquierda(seqCoReplicador, 12, "0");
			String inEjecutado = "N";
			String sql = "Insert into CMTS_REPLICACION (CO_COMPANIA, CO_LOCAL, CO_REPLICADOR, ID_GRUPO_REPLICACION, ID_DATA_REPLICAR, DE_LOCALES_DESTINO, FE_EJECUCION_PROY, IN_EJECUTADO, DE_PARAMETROS, VA_ORDEN_EJECUCION,ID_CREA_REPLICACION, FE_CREA_REPLICACION) "
					+ " values (?,?,?,?,?,?,sysdate,?,?,?,?,sysdate)";
			pre = ((Connection) BaseConexion.getConexion()).prepareStatement(sql);
			pre.setString(1, pCoCompania);
			pre.setString(2, coLocal);
			pre.setString(3, coReplicador);
			pre.setString(4, grupoReplicacion);
			pre.setString(5, idDataReplicar);
			pre.setString(6, pCoLocalDestino);
			pre.setString(7, inEjecutado);
			pre.setString(8, deParametros);
			pre.setInt(9, 1);
			pre.setString(10, AtuxVariables.vIdUsuario);
			pre.executeUpdate();
			pre.close();
			if (logger.isInfoEnabled()) {
				logger.info("Replicando:" + grupoReplicacion + " RepPK:" + pCoCompania + "-" + coLocal + "-" + coReplicador);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (pre != null) {
					pre.close();
				}
			} catch (Exception e2) {
				// System.out.println("No se cerro la conexion");
			}
			return null;
		}
		return new CmtsReplicacionPk(pCoCompania, coLocal, coReplicador);
	}

	private static String obtenerSelectParaListaPedidosTraslado(boolean faltantesDevolucion, String pTiSolicitudPedido, String pEsSolicitudPedido, String pFeEmisionInicio, String pFeEmisionFinal,
			String pCoLocalOrigen, String pCoLocalDestino) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SOLPEDIDO.FE_EMISION_SOLICITUD_PEDIDO  AS FECHA, " + " TO_CHAR(SOLPEDIDO.FE_EMISION_SOLICITUD_PEDIDO,'DD/MM/YYYY HH24:MI:SS') || 'Ã' ||");
		sql.append("        SOLPEDIDO.TI_SOLICITUD_PEDIDO|| 'Ã' ||");
		sql.append("        SOLPEDIDO.NU_SOLICITUD_PEDIDO|| 'Ã' ||");
		sql.append("        DECODE(ORIGEN.CO_LOCAL,'099', 'CENTRO DIST.', ORIGEN.DE_CORTA_LOCAL)|| 'Ã' ||");
		sql.append("        DECODE(DESTINO.CO_LOCAL, '099', 'CENTRO DIST.', DESTINO.DE_CORTA_LOCAL)|| 'Ã' ||");
		sql.append("        NVL(SOLPEDIDO.NO_RESPONSABLE ,' ' ) || 'Ã' ||");
		sql.append("        DECODE(SOLPEDIDO.ES_SOLICITUD_PEDIDO,'P','Pendiente','A','Atendido','R','Recibido','N','Anulado',' ' ) || 'Ã' ||");
		sql.append("        SOLPEDIDO.co_local|| 'Ã' ||");
		sql.append("        SOLPEDIDO.co_local_destino|| 'Ã' ||");
		sql.append("        SOLPEDIDO.NU_RECEPCION_PRODUCTO|| 'Ã' ||");
		sql.append("        SOLPEDIDO.ES_SOLICITUD_PEDIDO AS RESULTADO ");
		sql.append("   FROM LGTC_SOLICITUD_PEDIDO SOLPEDIDO,");
		sql.append("        VTTM_LOCAL ORIGEN,");
		sql.append("        VTTM_LOCAL DESTINO ");
		sql.append("   WHERE ");
		sql.append("        SOLPEDIDO.CO_COMPANIA = ORIGEN.CO_COMPANIA AND");
		sql.append("        SOLPEDIDO.CO_LOCAL= ORIGEN.CO_LOCAL AND");
		sql.append("        SOLPEDIDO.CO_COMPANIA = DESTINO.CO_COMPANIA AND");
		sql.append("        SOLPEDIDO.CO_LOCAL_DESTINO= DESTINO.CO_LOCAL AND ");
		sql.append("        SOLPEDIDO.TI_SOLICITUD_PEDIDO <> 'ZPPS' AND ");
		if (faltantesDevolucion) {
			sql.append("    SOLPEDIDO.TI_SOLICITUD_PEDIDO IN ('" + ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_FALTANTE + "','" + ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_FALTANTE_CD + "','"
					+ ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_DEVOLUCION + "') ");
		} else {
			sql.append("    SOLPEDIDO.TI_SOLICITUD_PEDIDO NOT IN ('" + ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_FALTANTE + "','" + ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_FALTANTE_CD
					+ "','" + ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_DEVOLUCION + "') ");
		}
		if (!"".equals(pTiSolicitudPedido)) {
			sql.append("    AND    SOLPEDIDO.TI_SOLICITUD_PEDIDO = '" + pTiSolicitudPedido + "' ");
		}
		if (!"".equals(pCoLocalOrigen)) {
			sql.append("    AND    SOLPEDIDO.CO_LOCAL='" + pCoLocalOrigen + "' ");
		}
		if (!"".equals(pCoLocalDestino)) {
			sql.append("    AND    SOLPEDIDO.CO_LOCAL_DESTINO='" + pCoLocalDestino + "' ");
		}
		if (!"".equals(pEsSolicitudPedido)) {
			sql.append("    AND    SOLPEDIDO.ES_SOLICITUD_PEDIDO = '" + pEsSolicitudPedido + "' ");
		}
		if (!"".equals(pFeEmisionInicio) && !"".equals(pFeEmisionFinal)) {
			sql.append("    AND    SOLPEDIDO.FE_EMISION_SOLICITUD_PEDIDO BETWEEN ");
			sql.append("                TO_DATE('" + pFeEmisionInicio + "' || ' 00:00:00', 'dd/MM/yyyy HH24:MI:SS') AND                ");
			sql.append("                TO_DATE('" + pFeEmisionFinal + "' || ' 23:59:59', 'dd/MM/yyyy HH24:MI:SS') ");
		}
		return sql.toString();
	}

	public static void loadPedidosDeTraslado(AtuxTableModel pTableModel, String pTiSolicitudPedido, String pEsSolicitudPedido, String pFeEmisionInicio, String pFeEmisionFinal, String pCoLocalOrigen,
		String pCoLocalDestino) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT resultado ");
		sql.append("FROM ( ");
		sql.append(obtenerSelectParaListaPedidosTraslado(true, pTiSolicitudPedido, pEsSolicitudPedido, pFeEmisionInicio, pFeEmisionFinal, pCoLocalOrigen, pCoLocalDestino));
		sql.append(" UNION ");
		sql.append(obtenerSelectParaListaPedidosTraslado(false, pTiSolicitudPedido, pEsSolicitudPedido, pFeEmisionInicio, pFeEmisionFinal, pCoLocalOrigen, pCoLocalDestino));
		sql.append(" ) ");
		sql.append("    order by fecha desc");
		if (logger.isDebugEnabled()) {
			logger.debug("SQxL para búsquedas de pedidos:" + sql);
		}
		ArrayList resultados = new ArrayList();
		AtuxDBUtility.executeSQLArrayList(resultados, sql.toString());
		pTableModel.data = resultados;
	}

	public static void loadDetallePedidoTraslado(AtuxTableModel pTableModelDetallePedido, String pCoCompania, String pCoLocal, String pNuPedido) throws SQLException {
		parametros = new ArrayList();
		parametros.add(pCoCompania);
		parametros.add(pCoLocal);
		parametros.add(pNuPedido);
		AtuxDBUtility.executeSQLStoredProcedure(pTableModelDetallePedido, "PTOVTA_TRASLADO_PRODUCTO.OBTENER_DET_PEDIDO_TRASLADO(?,?,?)", parametros, false);
	}

	public static void grabarKardexActualizarStocks(String pCoCompania, String pCoLocal, String pCoLocalRecepcionProducto, String pNuRecepcionProd, String pKardexTipoDocumento,
			String pKardexCodGrupoMotivo, String pKardexCodMotivo, boolean debeUsarseUnidadBase, String pTiSolicitud, String pNuSolicitud) throws SQLException {
		parametros = new ArrayList();
		parametros.add(pCoCompania);
		parametros.add(pCoLocal);
		parametros.add(pCoLocalRecepcionProducto);
		parametros.add(pNuRecepcionProd);
		parametros.add(AtuxVariables.vIdUsuario);
		parametros.add(pKardexTipoDocumento);
		parametros.add(pKardexCodGrupoMotivo);
		parametros.add(pKardexCodMotivo);
		parametros.add(debeUsarseUnidadBase ? "S" : "N");
		parametros.add(pTiSolicitud);
		parametros.add(pNuSolicitud);

		if (logger.isInfoEnabled()) {
			logger.info("Grabar Kardex Actualizar Stock:" + parametros);
		}
		AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_TRASLADO_PRODUCTO.GRABAR_KARDEX_ACTUALIZAR_STOCK(?,?,?,?,?,?,?,?,?,?,?)", parametros, false);
	}

	
	public static String obtenerNumeracionRemotamente(String coCompania, String coLocal, String coNumeracion) {
        String sql = "select Eckerd_Utility.OBT_ACT_NUMERACION_AUTONOMO(?, ?, ?) as num from dual";
        ArrayList parametros = new ArrayList();
        parametros.add(coCompania);
        parametros.add(coLocal);
        parametros.add(coNumeracion);
        /*Object[] sqlFilter = new Object[]{coCompania, coLocal, coNumeracion};
        HashMap[] rows = MSEClient.instance().executeQuery(coLocal, sql, sqlFilter);
        if (rows == null || rows.length == 0) {
            throw new AWBusinessException(AWBusinessException.PARAM_MSGE, new Object[]{"No existe conexion con Matriz o con el local " + coLocal + " por favor reintente"});
        }
        String value = rows[0].get("num").toString();
        if ("0".equals(value)) {
            throw new AWBusinessException(AWBusinessException.PARAM_MSGE, new Object[]{"No se puede acceder al local: " + coLocal});
        }*/
        try {
        	System.out.println(parametros);
			return AtuxDBUtility.executeSQLStoredProcedureStr("Eckerd_Utility.OBT_ACT_NUMERACION_AUTONOMO(?, ?, ?)", parametros);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
	
	public static void anularPedidoTraslado(String coCompania, String coLocal, String nuSolicitudPedido) throws SQLException {
		updateLgtcSolicitudPedido(coCompania, coLocal, nuSolicitudPedido, ConstantsTrasladoProducto.PEDIDO_TRASLADO_ES_ANULADO);
		updateLgtdSolicitudPedidoEstado(coCompania, coLocal, nuSolicitudPedido, ConstantsTrasladoProducto.PEDIDO_TRASLADO_ES_ANULADO);
	}

	public static void obtenerGuiasPendientes(AtuxTableModel pTableModel) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		AtuxDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVTA_TRASLADO_PRODUCTO.OBTENER_GUIAS_PENDIENTES(?,?)", parametros, false);
	}

	public static void obtenerProductosDeLaGuia(AtuxTableModel pTableModel, String pNumeroRecepProd) throws SQLException {
		pTableModel.clearTable();
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pNumeroRecepProd);
		AtuxDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVTA_TRASLADO_PRODUCTO.OBTENER_PRODUCTOS_DE_GUIA(?,?,?)", parametros, false);
	}

	public static String verificarNumeracionGuia() throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		return AtuxDBUtility.executeSQLStoredProcedureStr("PTOVTA_TRASLADO_PRODUCTO.VERIFICAR_NUMERACION_GUIA(?,?)", parametros);
	}

	public static String afectaPRODUCTOGUIA(String pNuRecepcionProducto, String pNuSolicitudPedido, String pProducto, String pNumeroPagina, String pIndicAfectaStock, String pNumeroDocumento,
			String pTiSolicitud) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pNuRecepcionProducto);
		parametros.add(pNuSolicitudPedido);
		parametros.add(new Integer(pNumeroPagina));
		parametros.add(pProducto);
		parametros.add(AtuxVariables.vIdUsuario);
		parametros.add(ConstantsInventario.GRUPO_MOTIVO_KARDEX);
		parametros.add(ConstantsInventario.MOTIVO_KARDEX_GUIA_MODEM_MATRIZ);
		parametros.add(ConstantsInventario.TIPO_DOCUMENTO_GUIA_MATRIZ);
		parametros.add(pNumeroDocumento);
		parametros.add(pIndicAfectaStock);
		parametros.add(pTiSolicitud);
		parametros.add(pNuSolicitudPedido);
		if (logger.isDebugEnabled()) {
			logger.debug("Afectar Productos Guia:" + parametros);
		}
		return AtuxDBUtility.executeSQLStoredProcedureStr("PTOVTA_TRASLADO_PRODUCTO.AFECTAR_POR_PRODUCTO(?,?,?,?,?,?,?,?,?,?,?,?,?,?)", parametros);

	}

	public static String afectaPAGINAGUIA(String nuRecepcionProducto, String nuSolicitudPedido, String numeroPag, String pIndicAfectaStock, String pNumeroDocumento, String pTiSolicitud)
			throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(nuRecepcionProducto);
		parametros.add(nuSolicitudPedido);
		parametros.add(new Integer(numeroPag));
		parametros.add(AtuxVariables.vIdUsuario);
		parametros.add(ConstantsInventario.GRUPO_MOTIVO_KARDEX);
		parametros.add(ConstantsInventario.MOTIVO_KARDEX_GUIA_MODEM_MATRIZ);
		parametros.add(ConstantsInventario.TIPO_DOCUMENTO_GUIA_MATRIZ);
		parametros.add(pNumeroDocumento);
		parametros.add(pIndicAfectaStock);
		parametros.add(pTiSolicitud);
		parametros.add(nuSolicitudPedido);
		if (logger.isDebugEnabled()) {
			logger.debug("Afectar Páginas Guia:" + parametros);
		}
		return AtuxDBUtility.executeSQLStoredProcedureStr("PTOVTA_TRASLADO_PRODUCTO.AFECTAR_POR_PAGINA(?,?,?,?,?,?,?,?,?,?,?,?,?)", parametros);
	}

	public static void afectaGUIATOTAL(String pNuRecepcionProducto, String pNuSolicitudPedido, String pIndicAfectaStock, String pNumeroDocumento, String pTiSolicitud) throws SQLException {
		parametros = new ArrayList();
		parametros.add(AtuxVariables.vCodigoCompania);
		parametros.add(AtuxVariables.vCodigoLocal);
		parametros.add(pNuRecepcionProducto);
		parametros.add(pNuSolicitudPedido);
		parametros.add(AtuxVariables.vIdUsuario);
		parametros.add(ConstantsInventario.GRUPO_MOTIVO_KARDEX);
		parametros.add(ConstantsInventario.MOTIVO_KARDEX_GUIA_MODEM_MATRIZ);
		parametros.add(ConstantsInventario.TIPO_DOCUMENTO_GUIA_MATRIZ);
		parametros.add(pNumeroDocumento);
		parametros.add(pIndicAfectaStock);
		parametros.add(pTiSolicitud);
		parametros.add(pNuSolicitudPedido);
		if (logger.isDebugEnabled()) {
			logger.debug("Afectar Guia Completa:" + parametros);
		}

		logger.info("Parametros:");
		logger.info("-----------");
		logger.info("Compania: " + AtuxVariables.vCodigoCompania);
		logger.info("Local: " + AtuxVariables.vCodigoLocal);
		logger.info("Recepcion: " + pNuRecepcionProducto);
		logger.info("Solicitud: " + pNuSolicitudPedido);
		logger.info("ID: " + AtuxVariables.vIdUsuario);
		logger.info("Grupo: " + ConstantsInventario.GRUPO_MOTIVO_KARDEX);
		logger.info("Motivo: " + ConstantsInventario.MOTIVO_KARDEX_GUIA_MODEM_MATRIZ);
		logger.info("Tipo Documento: " + ConstantsInventario.TIPO_DOCUMENTO_GUIA_MATRIZ);
		logger.info("Nro Documento: " + pNumeroDocumento);
		logger.info("Ind. Afectacion: " + pIndicAfectaStock);

		AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_TRASLADO_PRODUCTO.AFECTAR_TOTAL_GUIA(?,?,?,?,?,?,?,?,?,?,?,?)", parametros, false);
	}

	public static void procesarException(JDialog cmp, Exception ex) {
		procesarException(cmp, ex, "");
	}

	public static void procesarException(JDialog jDialog, Exception ex, String msg) {
		AtuxUtility.liberarTransaccion();
		ex.printStackTrace();
		logger.error(msg, ex);
		if (msg == null || "".equals(msg)) {
			return;
		}
		if (ex instanceof SQLException) {
			SQLException sqlException = (SQLException) ex;
			JOptionPane.showMessageDialog(jDialog, msg + "\n" + sqlException.getErrorCode() + " - " + sqlException.getMessage(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
		} else if (ex instanceof AWBusinessException) {
			AtuxUtility.showMessage(jDialog, ex.getMessage(), null);
		} else {
			AtuxUtility.showMessage(jDialog, msg, null);
		}
	}

	public static Map obtenerLocalesParaDevolucion() throws SQLException {

		StringBuffer almacenesSql = new StringBuffer();
		almacenesSql.append("   SELECT ");
		almacenesSql.append("       C.CO_LOCAL  || 'Ã' ||");
		almacenesSql.append("       C.CO_LOCAL || '-' || C.DE_CORTA_LOCAL  ");
		almacenesSql.append("     FROM VTTM_LOCAL A,");
		almacenesSql.append("          CMTM_COMPANIA B,");
		almacenesSql.append("          VTTM_LOCAL C");
		almacenesSql.append("    WHERE ");
		almacenesSql.append("      A.CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'");
		almacenesSql.append("      AND A.CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'");
		almacenesSql.append("      AND C.TI_LOCAL = 'A'");
		almacenesSql.append("      AND C.ES_LOCAL = 'A'");
		almacenesSql.append("      AND C.IN_IMPLEMENTA_SAP = 'S'");
		almacenesSql.append("      AND A.CO_SUCURSAL = B.CO_COMPANIA");
		almacenesSql.append("      AND C.CO_COMPANIA = C.CO_SUCURSAL");
		ArrayList almacenes = new ArrayList();
		AtuxDBUtility.executeSQLArrayList(almacenes, almacenesSql.toString());

		ArrayList localesSapMasAlmacenes = new ArrayList();
		localesSapMasAlmacenes.addAll(almacenes);

		ArrayList localActual = new ArrayList();
		String deLocal = obtenerDeLocal(AtuxVariables.vCodigoLocal);
		localActual.add(AtuxVariables.vCodigoLocal);
		localActual.add(AtuxVariables.vCodigoLocal + "-" + deLocal);

		localesSapMasAlmacenes.add(localActual);

		Set codigosAux = new HashSet();
		ArrayList codigos = new ArrayList();
		ArrayList descripciones = new ArrayList();
		for (Iterator iterator = localesSapMasAlmacenes.iterator(); iterator.hasNext();) {
			ArrayList codigoDescripcion = (ArrayList) iterator.next();
			if (codigosAux.add(codigoDescripcion.get(0))) {
				codigos.add(codigoDescripcion.get(0));
				descripciones.add(codigoDescripcion.get(1));
			}
		}
		String[] codigoArray = convertirAArregloDeStrings(codigos);
		String[] descripcionArray = convertirAArregloDeStrings(descripciones);
		Map valores = new HashMap();
		valores.put("codigo", codigoArray);
		valores.put("descripcion", descripcionArray);
		return valores;
	}

	private static String[] convertirAArregloDeStrings(Collection list) {
		String[] array = new String[list.size()];
		int i = 0;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String s = (String) iterator.next();
			array[i] = s;
			i++;
		}
		return array;
	}

}
