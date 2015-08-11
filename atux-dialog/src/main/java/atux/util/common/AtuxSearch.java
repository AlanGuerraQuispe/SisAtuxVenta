package atux.util.common;

import atux.managerbd.BaseConexion;
import atux.modelbd.CajaPago;
import atux.modelbd.Local;
import atux.modelbd.PedidoVenta;
import atux.modelbd.ProductoLocal;
import atux.vistas.venta.caja.IPagoPedido;
import com.aw.core.util.StringUtils;

import java.awt.Window;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class AtuxSearch {

    /**
     * Almacena los parámetros necesarios para la ejecución de un SP
     */
    private static ArrayList parametros = new ArrayList();

    /**
     * Constructor.
     */
    public AtuxSearch() {
    }

    /**
     * Retorna la fecha o fecha_hora de la base de datos en un formato pre-determinado.
     * 1=FORMATO_FECHA
     * 2=FORMATO_FECHA_HORA
     *
     * @param <b>pTipo</b> Tipo de formato (fecha o fecha_hora).
     * @return <b>String</b> Fecha de la base de datos en el formato establecido.
     */
    public static String getFechaHoraBD(int pTipo) throws SQLException {
        String fechahora = "";
        String query = "SELECT TO_CHAR(SYSDATE,'dd/mm/yyyy hh24:mi:ss') FROM DUAL";
        Statement stmt = ((Connection) BaseConexion.getConexion()).createStatement();
        // Ejecutar el query haciendo uso de la conexión por default
        ResultSet results = stmt.executeQuery(query);
        if (results.next()) fechahora = results.getString(1);
        results.close();
        stmt.close();
        // Determinar formato de fecha_hora para retornar el valor
        // Valores de retorno : dd/mm/yyyy
        //                      dd/mm/yyyy hh24:mi:ss
        if (fechahora.trim().length() > 0 && pTipo == AtuxVariables.FORMATO_FECHA)
            fechahora = fechahora.substring(0, 10);
        return fechahora;
    }

    /**
     * Retorna la fecha o fecha_hora de la base de datos en un formato pre-determinado.
     * 1=FORMATO_FECHA
     * 2=FORMATO_FECHA_HORA
     *
     * @param <b>pTipo</b> Tipo de formato (fecha o fecha_hora).
     * @return <b>String</b> Fecha de la base de datos en el formato establecido.
     */
    public static String getFechaHoraMinutoBD(int pTipo) throws SQLException {
        String fechahora = "";
        String query = "SELECT TO_CHAR(SYSDATE,'dd/mm/yyyy hh24:mi') FROM DUAL";
        Statement stmt = ((Connection) BaseConexion.getConexion()).createStatement();
        // Ejecutar el query haciendo uso de la conexión por default
        ResultSet results = stmt.executeQuery(query);
        if (results.next()) fechahora = results.getString(1);
        results.close();
        stmt.close();
        // Determinar formato de fecha_hora para retornar el valor
        // Valores de retorno : dd/mm/yyyy
        //                      dd/mm/yyyy hh24:mi:ss
        if (fechahora.trim().length() > 0 && pTipo == AtuxVariables.FORMATO_FECHA)
            fechahora = fechahora.substring(0, 10);
        return fechahora;
    }

    public static java.sql.Date getFechaHora() throws SQLException {
        java.sql.Date fechahora = null;
        String query = "SELECT SYSDATE FROM DUAL";
        Statement stmt = ((Connection) BaseConexion.getConexion()).createStatement();
        // Ejecutar el query haciendo uso de la conexión por default
        ResultSet results = stmt.executeQuery(query);
        if (results.next()) fechahora = results.getDate(1);
        results.close();
        stmt.close();
        return fechahora;
    }

    public static int getDiferencia(String pFechaAnulacion) throws SQLException {
        int diasDiferencia = 0;
        String query = "SELECT SYSDATE - TO_DATE('"+ pFechaAnulacion +"','dd/MM/yyyy')  FROM DUAL";
        Statement stmt = ((Connection) BaseConexion.getConexion()).createStatement();
        // Ejecutar el query haciendo uso de la conexión por default
        ResultSet results = stmt.executeQuery(query);
        if (results.next()) diasDiferencia = results.getInt(1);
        results.close();
        stmt.close();

        return diasDiferencia;
    }

    public static java.sql.Timestamp getFechaHoraTimestamp() throws SQLException {
        java.sql.Timestamp fechahora = null;
        String query = "SELECT SYSDATE FROM DUAL";
        Statement stmt = ((Connection) BaseConexion.getConexion()).createStatement();
        // Ejecutar el query haciendo uso de la conexión por default
        ResultSet results = stmt.executeQuery(query);
        if (results.next()) fechahora = results.getTimestamp(1);
        results.close();
        stmt.close();
        return fechahora;
    }

    public static String getFechaHoraParametroBD(int pTipo, int pVariacion) throws SQLException {
        String fechahora = "";
        String query = "SELECT TO_CHAR(SYSDATE+" + String.valueOf(pVariacion) + ",'dd/mm/yyyy hh24:mi:ss') FROM DUAL";
        Statement stmt = ((Connection) BaseConexion.getConexion()).createStatement();
        // Ejecutar el query haciendo uso de la conexión por default
        ResultSet results = stmt.executeQuery(query);
        if (results.next()) fechahora = results.getString(1);
        results.close();
        stmt.close();
        // Determinar formato de fecha_hora para retornar el valor
        // Valores de retorno : dd/mm/yyyy
        //                      dd/mm/yyyy hh24:mi:ss
        if (fechahora.trim().length() > 0 && pTipo == AtuxVariables.FORMATO_FECHA)
            fechahora = fechahora.substring(0, 10);
        return fechahora;
    }

    public static boolean setNumeroPedidoDiario() throws SQLException {

        String feModNumeracion = getFeModNumeracionNuPedido();
        String feHoyDia = "";

        if (!(feModNumeracion.trim().length() > 0))
            throw new SQLException("Ultima Fecha Modificación de Numeración Diaria del Pedido NO ES VALIDA !!!", "EckerdError", 9001);
        else {
            feHoyDia = AtuxSearch.getFechaHoraBD(AtuxVariables.FORMATO_FECHA);
            //
            feHoyDia = feHoyDia.trim().substring(0, 2);
            feModNumeracion = feModNumeracion.trim().substring(0, 2);
            //
            if (Integer.parseInt(feHoyDia) != Integer.parseInt(feModNumeracion)) {
                AtuxSearch.inicializaNumeracionNoCommit(AtuxVariables.NUMERACION_PEDIDO_DIARIO);
                AtuxVariables.vNumeroPedidoDiario = "0001";
            } else {
                // Obtiene el Numero de Atencion del Día y hace SELECT FOR UPDATE.
                AtuxVariables.vNumeroPedidoDiario = AtuxSearch.getNuSecNumeracion(AtuxVariables.NUMERACION_PEDIDO_DIARIO, 4);
            }
        }
        return true;
    }

    public static String getFeModNumeracionNuPedido() throws SQLException {
        return AtuxDBUtility.getValueAt("CMTR_NUMERACION",
                "TO_CHAR(FE_MOD_NUMERACION,'dd/MM/yyyy')",
                "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                        " AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'" +
                        " AND CO_NUMERACION = '" + AtuxVariables.NUMERACION_PEDIDO_DIARIO + "' FOR UPDATE");
    }

    /**
     * Setea el Tipo de Cambio usado por toda la aplicación.
     *
     * @param <b>pDiaVenta</b> Fecha de Venta en el formato dd/MM/yyyy.
     */
    public static double getTipoCambio(String pDiaVenta) throws SQLException {
        ArrayList parameters = new ArrayList();
        parameters.add(AtuxVariables.vCodigoCompania);
        parameters.add(AtuxVariables.MONEDA_DOLARES);
        parameters.add(AtuxVariables.MONEDA_SOLES);
        parameters.add(pDiaVenta);
        return AtuxDBUtility.executeSQLStoredProcedureDouble("PTOVTA_UTILITY.GET_TIPO_CAMBIO(?,?,?,?)", parameters);
    }

    /**
     * Retorna un arreglo con los datos del local.
     *
     * @param <b>pCodigoLocal</b> Código del Local.
     * @return <b>ArrayList</b> Contiene los datos del Local.
     */
    public static ArrayList obtieneDatoLocal(String pCodigoLocal) throws SQLException {
        ArrayList pOutParams = new ArrayList();
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(pCodigoLocal);
        AtuxDBUtility.executeSQLStoredProcedureArrayList(pOutParams, "ECKERD_SECURITY.OBTIENE_DATO_LOCAL(?,?)", parametros);
        return pOutParams;
    }

    /**
     * Retorna el Secuencial requerido alineado y con ceros a la izquierda.
     *
     * @param <b>pCoNumeracion</b> Código de la Numeración del Secuencial.
     * @param <b>pLength</b>       Longitud del Secuencial.
     * @return <b>String</b> Secuencial.
     */
    public static String getNuSecNumeracion(String pCoNumeracion, int pLength) throws SQLException {
        // Obtener Secuencial de la tabla CMTR_NUMERACION
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pCoNumeracion);
        String nuSecMovimiento = String.valueOf(AtuxDBUtility.executeSQLStoredProcedureInt("PTOVTA_UTILITY.OBTENER_NUMERACION(?,?,?)", parametros));
        return AtuxUtility.completeWithSymbol(nuSecMovimiento, pLength, "0", "I");
    }

    public static String getNuSecNumeracionStr(String pCoNumeracion, int pLength) throws SQLException {
        // Obtener Secuencial de la tabla CMTR_NUMERACION
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pCoNumeracion);
        String nuSecMovimiento = AtuxDBUtility.executeSQLStoredProcedureStr("PTOVTA_UTILITY.OBTENER_NUMERACION(?,?,?)", parametros);
        return AtuxUtility.completeWithSymbol(nuSecMovimiento, pLength, "0", "I");
    }

    /**
     * Setea Secuencial en Base de Datos.
     *
     * @param <b>pCoNumeracion</b> Código de la Numeración del Secuencial.
     */
    public static void setNuSecNumeracion(String pCoNumeracion) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pCoNumeracion);
        parametros.add(AtuxVariables.vIdUsuario);
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_UTILITY.ACTUALIZAR_NUMERACION(?,?,?,?)", parametros, false);
    }

    /**
     * Setea Secuencial en Base de Datos. No realiza COMMIT
     *
     * @param <b>pCoNumeracion</b> Código de la Numeración del Secuencial.
     */
    public static void setNuSecNumeracionNoCommit(String pCoNumeracion) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pCoNumeracion);
        parametros.add(AtuxVariables.vIdUsuario);
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_UTILITY.ACTUALIZAR_NUMERACION_NOCOMMIT(?,?,?,?)", parametros, false);
    }

    public static void inicializaNumeracionNoCommit(String pCoNumeracion) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pCoNumeracion);
        parametros.add(AtuxVariables.vIdUsuario);
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_UTILITY.INICIALIZA_NUMERACION_NOCOMMIT(?,?,?,?)", parametros, false);
    }

    /**
     * Libera la ejecución de la transacción. Culmina el FOR UPDATE en Base de Datos.
     */
    public static void liberarTransaccion() throws SQLException {
        parametros = new ArrayList();
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_UTILITY.LIBERAR_TRANSACCION", parametros, false);
    }

    /**
     * Acepta la ejecución de la transacción. Hace COMMIT en Base de Datos.
     */
    public static void aceptarTransaccion() throws SQLException {
        parametros = new ArrayList();
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_UTILITY.ACEPTAR_TRANSACCION", parametros, false);
    }

    /**
     * Retorna el valor del Impuesto General a las Ventas - IGV
     *
     * @return <b>double</b> Valor del IGV.
     */
    public static double getIGV() throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        return AtuxDBUtility.executeSQLStoredProcedureDouble("PTOVTA_VENTAS.MONTOIGV(?,?)", parametros);
    }

    /**
     * Setea el valor del Impuesto General a las Ventas tanto en porcentaje
     * como en indicador para Cálculo
     */
    public static void setIGV() throws SQLException {
        AtuxVariables.vIgvPorcentaje = getIGV();
        AtuxVariables.vIgvCalculo    = 1 + (AtuxVariables.vIgvPorcentaje / 100);
    }

    public static String getNumeracionComprobante(String pNuImpresora) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(new Integer(pNuImpresora));
        return AtuxDBUtility.executeSQLStoredProcedureStr("PTOVTA_UTILITY.GET_NUMERACION_COMPROBANTE(?,?,?)", parametros);
    }

    public static void setNumeracionComprobante(String pNuImpresora) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pNuImpresora);
        parametros.add(AtuxVariables.vIdUsuario);
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_UTILITY.SET_NUMERACION_COMPROBANTE(?,?,?,?)", parametros, false);
    }

    public static void executeSentenceForMaintenance(String pSQL) throws SQLException {
        AtuxDBUtility.executeSQLUpdate(pSQL);
    }

    public static String getCodigoProductoBarra(String pCodProducto) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(pCodProducto);
        return AtuxDBUtility.executeSQLStoredProcedureStr("PTOVTA_VENTAS.CODIGOPRODUCTOBARRA(?,?)", parametros);
    }

    public static void setNuSecNumeracionVirtual(String pCoNumeracion, int pNumeracion) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pCoNumeracion);
        parametros.add(AtuxVariables.vIdUsuario);
        parametros.add(new Integer(pNumeracion));
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_UTILITY.ACTUALIZAR_NUMERACION2(?, ?,?,?,?)", parametros, false);
    }

    public static boolean esPedidoDiaAnterior(String fePedido) throws SQLException {
        parametros = new ArrayList();
        parametros.add(fePedido);
        int rpta = AtuxDBUtility.executeSQLStoredProcedureInt("PTOVTA_CAJA.GET_PEDIDO_DIA(?)", parametros);

        return (rpta == 0);
    }

    public static ArrayList verificaComprobanteInsertarIn(String pComprobanteConsul, String ptiComprobante, ArrayList pArray) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pComprobanteConsul);
        parametros.add(ptiComprobante);
        AtuxDBUtility.executeSQLStoredProcedureArrayList(pArray, "PTOVTA_CAJA.VERIFICA_COMPROBANTE_IN(?,?,?,?)", parametros);
        return pArray;
    }

    public static String getInCajaTurnoAbierta(String pNuCaja,
                                               String pNuTurno) throws SQLException {
        return AtuxDBUtility.getValueAt("VTTR_CAJA_PAGO",
                "IN_CAJA_ABIERTA",
                "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                        " AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'" +
                        " AND NU_CAJA = " + pNuCaja +
                        " AND NU_TURNO = " + pNuTurno +
                        " AND ES_CAJA = 'A' FOR UPDATE");
    }

    public static String getFeModCajaPago(String pNuCaja,
                                          String pNuTurno) throws SQLException {
        return AtuxDBUtility.getValueAt("VTTR_CAJA_PAGO",
                "NVL(TO_CHAR(FE_MOD_CAJA,'dd/MM/yyyy'),' ')",
                "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                        " AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'" +
                        " AND NU_CAJA = " + pNuCaja +
                        " AND NU_TURNO = " + pNuTurno +
                        " AND ES_CAJA = 'A'");
    }


    public static void insertaPagoPedido(int pItemFormaPago,
                                         String pCodigoFormaPago,
                                         String pImPago,
                                         String pCoMoneda,
                                         String pImTotalPago,
                                         String pVuelto,
                                         String pNuTarjeta,
                                         String pFeVenceTarjeta,
                                         String pPaternoTitular,
                                         String pMaternoTitular,
                                         String pNombreTitular,
                                         String pTiDocIdentidad,
                                         String pNuDocIdentidad,
                                         String pCaCuotaPagoTarjeta,
                                         String pInPagoDiferido,
                                         String pIdTransaccion,
                                         String pCodComercio,
                                         String pNuTerminal,
                                         String pNuLote,
                                         String pNuReferencia,
                                         String pAprobacion
    ) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(AtuxVariables.vNumeroPedido);
        parametros.add(new Integer(pItemFormaPago));
        parametros.add(pCodigoFormaPago);
        parametros.add(new Double(pImPago));
        parametros.add(pCoMoneda);
        parametros.add(new Double(AtuxVariables.vTipoCambio));
        parametros.add(new Double(pImTotalPago));
        parametros.add(new Double(pVuelto));
        parametros.add(pNuTarjeta);
        if (pNuTarjeta.trim().length() > 0) {
            parametros.add("01/" + pFeVenceTarjeta.substring(0, 3) + "20" + pFeVenceTarjeta.substring(3, 5));
        } else {
            parametros.add(pFeVenceTarjeta);
        }
        parametros.add(pPaternoTitular);
        parametros.add(pMaternoTitular);
        parametros.add(pNombreTitular);
        parametros.add(pTiDocIdentidad);
        parametros.add(pNuDocIdentidad);
        parametros.add(pCaCuotaPagoTarjeta);
        parametros.add(pInPagoDiferido);
        parametros.add(AtuxVariables.vIdUsuario);
        parametros.add(pIdTransaccion);
        parametros.add(pCodComercio);
        parametros.add(pNuTerminal);
        parametros.add(pNuLote);
        parametros.add(pNuReferencia);
        parametros.add(pAprobacion);
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_CAJA.GRABAR_PAGO_PEDIDO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", parametros, false);
    }

    public static void insertaPagoPedidoCredito(int pItemFormaPago,
                                                String pCodigoFormaPago,
                                                String pImPago,
                                                String pCoMoneda,
                                                String pImTotalPago,
                                                String pVuelto,
                                                String pNuTarjeta,
                                                String pFeVenceTarjeta,
                                                String pPaternoTitular,
                                                String pMaternoTitular,
                                                String pNombreTitular,
                                                String pTiDocIdentidad,
                                                String pNuDocIdentidad,
                                                String pCaCuotaPagoTarjeta,
                                                String pInPagoDiferido,
                                                String pIdTransaccion,
                                                String pCodComercio,
                                                String pNuTerminal,
                                                String pNuLote,
                                                String pNuReferencia,
                                                String pAprobacion
    ) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(AtuxVariables.vNumeroPedido);
        parametros.add(new Integer(pItemFormaPago));
        parametros.add(pCodigoFormaPago);
        parametros.add(new Double(pImPago));
        parametros.add(pCoMoneda);
        parametros.add(new Double(AtuxVariables.vTipoCambio));
        parametros.add(new Double(pImTotalPago));
        parametros.add(new Double(pVuelto));
        parametros.add(pNuTarjeta);
        if (pNuTarjeta.trim().length() > 0) {
            parametros.add("01/" + pFeVenceTarjeta.substring(0, 3) + "20" + pFeVenceTarjeta.substring(3, 5));
        } else {
            parametros.add(pFeVenceTarjeta);
        }
        parametros.add(pPaternoTitular);
        parametros.add(pMaternoTitular);
        parametros.add(pNombreTitular);
        parametros.add(pTiDocIdentidad);
        parametros.add(pNuDocIdentidad);
        parametros.add(pCaCuotaPagoTarjeta);
        parametros.add(pInPagoDiferido);
        parametros.add(AtuxVariables.vIdUsuario);
        parametros.add(pIdTransaccion);
        parametros.add(pCodComercio);
        parametros.add(pNuTerminal);
        parametros.add(pNuLote);
        parametros.add(pNuReferencia);
        parametros.add(pAprobacion);
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_CAJA.GRABAR_PAGO_PEDIDO_CREDITO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", parametros, false);
    }

    public static String getInPedidoAnulado(String pNumeroPedido) throws SQLException {
        return AtuxDBUtility.getValueAt("VTTC_PEDIDO_VENTA",
                "IN_PEDIDO_ANULADO",
                "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                        " AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'" +
                        " AND NU_PEDIDO = '" + pNumeroPedido + "'");
    }

    public static String getNuCaja(String pNuSecUsuario) throws SQLException {
        return AtuxDBUtility.getValueAt("VTTR_USUARIO_CAJA",
                "NU_CAJA",
                "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                        " AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'" +
                        " AND NU_SEC_USUARIO = '" + pNuSecUsuario + "'" +
                        " AND ES_USUARIO_CAJA = 'A'");
    }

    public static String getInCajaAbierta(String pNuCaja) throws SQLException {
        return AtuxDBUtility.getValueAt("VTTR_CAJA_PAGO",
                "IN_CAJA_ABIERTA",
                "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                        " AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'" +
                        " AND NU_CAJA = " + pNuCaja +
                        " AND ES_CAJA = 'A'");
    }

    public static String getNuTurno(String pNuCaja) throws SQLException {
        return AtuxDBUtility.getValueAt("VTTR_CAJA_PAGO",
                "NU_TURNO",
                " CO_COMPANIA  = '" + AtuxVariables.vCodigoCompania + "'" +
                        " AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'" +
                        " AND NU_CAJA  = " + pNuCaja +
                        " AND ES_CAJA  = 'A'");
    }

    public static void updateCobranzaPedido(boolean pWithCommit) throws SQLException {
        String query = "UPDATE VTTC_PEDIDO_VENTA" +
                "      SET CO_CAJERO  ='"  + AtuxVariables.vNuSecUsuario + "'," +
                "          IN_REPLICA = 0," +
                "          FE_MOD_PEDIDO_VENTA = SYSDATE" +
                "    WHERE CO_COMPANIA= '" + AtuxVariables.vCodigoCompania + "'" +
                "    AND CO_LOCAL     = '" + AtuxVariables.vCodigoLocal + "'" +
                "    AND NU_PEDIDO    = '" + AtuxVariables.vNumeroPedido + "'";

        AtuxDBUtility.executeSQLUpdate(query, pWithCommit);
    }

    public static void updatePedido(String nuPedido) throws SQLException {
        String query = "UPDATE VTTC_PEDIDO_VENTA " +
                "    SET ES_PEDIDO_VENTA = 'C', " +
                "        IN_REPLICA = 0," +
                "        FE_MOD_PEDIDO_VENTA = SYSDATE" +
                "  WHERE CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                "    AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'" +
                "    AND NU_PEDIDO = '" + nuPedido + "'";

        AtuxDBUtility.executeSQLUpdate(query, false);
    }

    public static String getNombreVendedor() throws SQLException {
        return AtuxDBUtility.getValueAt("VTTC_PEDIDO_VENTA PEDIDO, CMTS_USUARIO USUARIO",
                "TRIM(USUARIO.NO_USUARIO) || ' ' || TRIM(USUARIO.AP_PATERNO_USUARIO)",
                "PEDIDO.CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                        " AND PEDIDO.CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'" +
                        " AND PEDIDO.NU_PEDIDO = '" + AtuxVariables.vNumeroPedido + "'" +
                        " AND PEDIDO.CO_COMPANIA = USUARIO.CO_COMPANIA" +
                        " AND PEDIDO.CO_LOCAL = USUARIO.CO_LOCAL" +
                        " AND PEDIDO.CO_VENDEDOR = USUARIO.NU_SEC_USUARIO");
    }

    public static void getNuCajaInCajaAbierta(ArrayList pDatos) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(AtuxVariables.vNuSecUsuario);
        AtuxDBUtility.executeSQLStoredProcedureArrayList(pDatos, "PTOVTA_CAJA.NUCAJA_INCAJAABIERTA(?,?,?)", parametros);
    }

    public static boolean getInLectorTarjeta() throws SQLException {

        return AtuxDBUtility.getValueAt("VTTR_CAJA_PAGO", "nvl(IN_TIENE_LECTOR_TARJETAS,'N')", "CO_COMPANIA='" + AtuxVariables.vCodigoCompania + "' AND CO_LOCAL='" + AtuxVariables.vCodigoLocal + "' AND NU_CAJA='" + AtuxVariables.vNuCaja + "'").trim().equals("S");
    }

    public static void getNumeroImpresoraColaImpresion(ArrayList pDatos,
                                                       String pTiComprobante) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(AtuxVariables.vNuCaja);
        parametros.add(pTiComprobante);
        AtuxDBUtility.executeSQLStoredProcedureArrayList(pDatos, "PTOVTA_CAJA.NUMEROIMPRESORA_COLAIMPRESION(?,?,?,?)", parametros);
    }

    public static void getImpresorasCaja(ArrayList pValores) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(new Integer(AtuxVariables.vNuCaja));

        AtuxDBUtility.executeSQLStoredProcedureArrayList(pValores, "PTOVTA_CAJA.RELACION_IMPRESORAS_CAJA(?,?,?)", parametros);
    }

    public static boolean setImpresorasCaja(IPagoPedido aThis) throws SQLException {
        ArrayList datos = new ArrayList();
        getImpresorasCaja(datos);
        for (int i = 0; i < datos.size(); i++) {

            if (AtuxVariables.vInTicketBoleta.equalsIgnoreCase("S") && ((String) ((ArrayList) datos.get(i)).get(0)).trim().equalsIgnoreCase(AtuxVariables.TIPO_TICKET_BOLETA)) {
                AtuxVariables.vImpresoraTicketBoleta = ((String) ((ArrayList) datos.get(i)).get(1)).trim();
                AtuxVariables.vDeImpresoraBoleta = ((String) ((ArrayList) datos.get(i)).get(2)).trim();
            }
            else
            if (AtuxVariables.vInTicketBoleta.equalsIgnoreCase("N") && ((String) ((ArrayList) datos.get(i)).get(0)).trim().equalsIgnoreCase(AtuxVariables.TIPO_BOLETA)) {
                AtuxVariables.vImpresoraBoleta = ((String) ((ArrayList) datos.get(i)).get(1)).trim();
                AtuxVariables.vDeImpresoraBoleta = ((String) ((ArrayList) datos.get(i)).get(2)).trim();
            }
            else
            if (AtuxVariables.vInTicketFactura.equalsIgnoreCase("S") && ((String) ((ArrayList) datos.get(i)).get(0)).trim().equalsIgnoreCase(AtuxVariables.TIPO_TICKET_FACTURA)) {
                AtuxVariables.vImpresoraFactura = ((String) ((ArrayList) datos.get(i)).get(1)).trim();
            }
            else
            if (AtuxVariables.vInTicketFactura.equalsIgnoreCase("N") && ((String) ((ArrayList) datos.get(i)).get(0)).trim().equalsIgnoreCase(AtuxVariables.TIPO_FACTURA)) {
                AtuxVariables.vImpresoraFactura = ((String) ((ArrayList) datos.get(i)).get(1)).trim();
            }
            else
            if (((String) ((ArrayList) datos.get(i)).get(0)).trim().equalsIgnoreCase(AtuxVariables.TIPO_GUIA)) {
                AtuxVariables.vImpresoraGuia = ((String) ((ArrayList) datos.get(i)).get(1)).trim();
            }
        }


        if (AtuxVariables.vImpresoraBoleta.trim().length() == 0 && AtuxVariables.vImpresoraTicketBoleta.trim().length() == 0) {
            JOptionPane.showMessageDialog(aThis, "La Cola de Impresión de Boletas o Ticket Boletas es INCORRECTO. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (AtuxVariables.vImpresoraFactura.trim().length() == 0) {
            JOptionPane.showMessageDialog(aThis, "La Cola de Impresión de Facturas es INCORRECTO. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (AtuxVariables.vImpresoraGuia.trim().length() == 0) {
            JOptionPane.showMessageDialog(aThis, "La Cola de Impresión de Guias es INCORRECTO. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;

    }

    public static void loadDetallePedido(ArrayList pData, String pNumeroPedido) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pNumeroPedido);
        AtuxDBUtility.executeSQLStoredProcedureArrayList(pData, "PTOVTA_CAJA.DETALLE_PEDIDO(?,?,?)", parametros);
    }

    public static void loadDetallePedido(AtuxTableModel pTableModel,
                                         String pNumeroPedido) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pNumeroPedido);
        AtuxDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVTA_CAJA.DETALLE_PEDIDO(?,?,?)", parametros, false);
    }

    public static String productoVirtualReceta(String pCoProducto) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(pCoProducto);
        parametros.add("0");
        return AtuxDBUtility.executeSQLStoredProcedureStr("PTOVTA_CAJA.VERIFICA_PROD_VIRT_RECETAMAGIS(?,?,?)", parametros);
    }

    public static String doCuentaRegistrosAnula(String pPedido) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pPedido);
        return AtuxDBUtility.executeSQLStoredProcedureStr("PTOVTA_VENTAS.CUENTA_UNION_PEDIDO(?,?,?)", parametros);
    }

    public static void grabarPedidoCabeceraUnido(String pNuPedido,
                                                 String pNuPedidoNuevo,
                                                 String pFechaPedido) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pNuPedido);
        parametros.add(pNuPedidoNuevo);
        parametros.add(pFechaPedido);
        parametros.add(AtuxVariables.vIdUsuario);
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_CAJA.GRABAR_PEDIDO_CABECERA_UNIDO(?,?,?,?,?,?)", parametros, false);
    }

    public static void anularPedido(String pPedido,
                                    String pMotivoAnulacion,
                                    boolean pWithCommit) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(AtuxVariables.GRUPO_MOTIVO_ANULACION_PEDIDO);
        parametros.add(pMotivoAnulacion);
        parametros.add(AtuxVariables.vIdUsuario);
        parametros.add(pPedido);
        parametros.add(AtuxVariables.vNuSecUsuario);
        parametros.add(AtuxVariables.vNuCaja);
        parametros.add(AtuxVariables.vNuTurno);
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_VENTAS.ANULA_PEDIDO(?,?,?,?,?,?,?,?,?)", parametros, false);
    }

    public static void setNuPedidoUnido(String pPedido,
                                        String pNuPedidoUnido,
                                        boolean pWithCommit) throws SQLException {
        String queryUpdate = "UPDATE VTTC_PEDIDO_VENTA " +
                "   SET NU_PEDIDO_UNIDO     = '" + pNuPedidoUnido + "'," +
                "       ID_MOD_PEDIDO_VENTA = '" + AtuxVariables.vIdUsuario + "'," +
                "       IN_REPLICA = 0," +
                "       FE_MOD_PEDIDO_VENTA = SYSDATE" +
                " WHERE CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                "   AND CO_LOCAL    = '" + AtuxVariables.vCodigoLocal + "'" +
                "   AND NU_PEDIDO   = '" + pPedido + "'";

        AtuxDBUtility.executeSQLUpdate(queryUpdate, pWithCommit);
    }

    public static void grabarPedidoDetalleUnido(String pNuPedido,
                                                String pNuPedidoNuevo) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pNuPedido);
        parametros.add(pNuPedidoNuevo);
        AtuxDBUtility.executeSQLStoredProcedure(null,
                "PTOVTA_CAJA.GRABAR_PEDIDO_DETALLE_UNIDO(?,?,?,?)",
                parametros,
                false);
    }

    public static void grabarKardexSegunPedido(String pNumPedido,
                                               String pGlosa,
                                               String pGrupoMotivo,
                                               String pMotivo,
                                               String pTipoOperacion) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pNumPedido);
        parametros.add(AtuxVariables.TIPO_DOCUMENTO_PEDIDO);
        parametros.add(pGlosa);
        parametros.add(pGrupoMotivo);
        parametros.add(pMotivo);
        parametros.add(AtuxVariables.vIdUsuario);
        parametros.add(pTipoOperacion);
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_VENTAS.ACTUALIZARKARDEXSEGUNPEDIDO(?,?,?,?,?,?,?,?,?)", parametros, false);
    }

    public static void anularCantidadesCambio(String pNumPedido) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pNumPedido);
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_VENTAS.ANULA_PEDIDO_CAMBIO(?,?,?)", parametros, false);
    }

    public static void updateADDStockProductoDisp(String pCodProducto,
                                                  int pCantidad,
                                                  boolean pWithCommit) throws SQLException {
        String queryUpdate = "UPDATE LGTR_PRODUCTO_LOCAL " +
                "   SET CA_STOCK_DISPONIBLE = NVL(CA_STOCK_DISPONIBLE,0) + " + pCantidad + " , " +
                "       IN_REPLICA = 0," +
                "       FE_MOD_PROD_LOCAL = SYSDATE" +
                " WHERE CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                "   AND CO_LOCAL    = '" + AtuxVariables.vCodigoLocal + "'" +
                "   AND CO_PRODUCTO = '" + pCodProducto + "'";

        AtuxDBUtility.executeSQLUpdate(queryUpdate, pWithCommit);
    }

    public static void updateNuPedidoDiario(boolean pWithCommit) throws SQLException {
        String queryUpdate = "UPDATE VTTC_PEDIDO_VENTA " +
                "   SET NU_PEDIDO_DIARIO = " + AtuxVariables.vNumeroPedidoDiario + " , " +
                "       IN_REPLICA       = 0," +
                "       FE_MOD_PEDIDO_VENTA = SYSDATE" +
                " WHERE CO_COMPANIA     = '" + AtuxVariables.vCodigoCompania + "'" +
                "   AND CO_LOCAL        = '" + AtuxVariables.vCodigoLocal + "'" +
                "   AND NU_PEDIDO       = '" + AtuxVariables.vNumeroPedido + "'";

        AtuxDBUtility.executeSQLUpdate(queryUpdate, pWithCommit);
    }

    public static void actualizarTotalesPedido(String pVaTotalVenta,
                                               String pVaTotalDescuento,
                                               String pVaTotalImpuesto,
                                               String pVaTotalPrecioVenta,
                                               String pSaldoRedondeo,
                                               int pCaItem,
                                               double pTotalInkClub,
                                               double pRedondeoInkClub
    ) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(AtuxVariables.vNumeroPedido);
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaTotalVenta)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaTotalDescuento)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaTotalImpuesto)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaTotalPrecioVenta)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pSaldoRedondeo)));
        parametros.add(new Integer(pCaItem));
        parametros.add(new Double(pTotalInkClub));
        parametros.add(new Double(pRedondeoInkClub));
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_CAJA.ACTUALIZAR_TOTALES_PEDIDO(?,?,?,?,?,?,?,?,?,?,?)", parametros, false);
    }

    public static boolean esPedidoCambioProducto(String vNumeroPedidoDiario, String fecha) throws SQLException {
        StringBuilder SQL = new StringBuilder();
        SQL.append(" CAB.CO_COMPANIA            ='").append(AtuxVariables.vCodigoCompania).append("' ");
        SQL.append("AND    CAB.CO_LOCAL         ='").append(AtuxVariables.vCodigoLocal).append("' ");
        SQL.append("AND    CAB.NU_PEDIDO_DIARIO = ").append(vNumeroPedidoDiario).append(" ");
        SQL.append("AND    CAB.FE_PEDIDO BETWEEN TO_DATE('").append(fecha).append("' ");
        SQL.append("              ||' 00:00:00','dd/MM/yyyy HH24:MI:SS') ");
        SQL.append("AND    TO_DATE('").append(fecha).append("' ");
        SQL.append("              ||' 23:59:59','dd/MM/yyyy HH24:MI:SS') ");
        SQL.append("AND    DET.CO_COMPANIA = CAB.CO_COMPANIA ");
        SQL.append("AND    DET.CO_LOCAL    = CAB.CO_LOCAL ");
        SQL.append("AND    DET.NU_PEDIDO   = CAB.NU_PEDIDO ");
        SQL.append("AND    DET.CA_ATENDIDA < 0");
        return !AtuxDBUtility.getValueAt(" VTTT_CAB_PEDIDO_VENTA CAB, VTTD_PEDIDO_VENTA DET ", "COUNT(*)", SQL.toString()).equals("0");
    }

    public static void loadPedidoPendiente(AtuxTableModel pTableModel,
                                           String pNuPedidoDiario,
                                           String pFechaPedido) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pNuPedidoDiario);
        parametros.add(pFechaPedido);
        AtuxDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVTA_CAJA.PEDIDO_PENDIENTE(?,?,?,?)", parametros, false);
    }

    public static void getPedidoPendiente(ArrayList pData,
                                          String pNuPedidoDiario,
                                          String pFechaPedido) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pNuPedidoDiario);
        parametros.add(pFechaPedido);
        AtuxDBUtility.executeSQLStoredProcedureArrayList(pData, "PTOVTA_CAJA.PEDIDO_PENDIENTE(?,?,?,?)", parametros);
    }

    public static void deleteFormaPago(String pNuItem) throws SQLException {
        String queryUpdate = "DELETE FROM VTTX_FORMA_PAGO_PEDIDO " +
                " WHERE CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                "   AND CO_LOCAL    = '" + AtuxVariables.vCodigoLocal + "'" +
                "   AND NU_PEDIDO   = '" + AtuxVariables.vNumeroPedido + "'" +
                "   AND NU_ITEM_FORMA_PAGO = '" + pNuItem + "'";
        AtuxDBUtility.executeSQLUpdate(queryUpdate);
    }

    public static boolean existeCajaTurnoImpresora(Window pDialog) {
        try {
            boolean existeCajaImpresora = true;
            ArrayList datos = new ArrayList();
            AtuxSearch.getNuCajaInCajaAbierta(datos);

            if (datos.size() == 1) {
                if (((String) ((ArrayList) datos.get(0)).get(1)).trim().equalsIgnoreCase("N")) {
                    existeCajaImpresora = false;
                    JOptionPane.showMessageDialog(pDialog, "La Caja relacionada al Usuario no ha sido Aperturada. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                } else {
                    AtuxVariables.vNuCaja = ((String) ((ArrayList) datos.get(0)).get(0)).trim();
                    AtuxVariables.vNuTurno = ((String) ((ArrayList) datos.get(0)).get(2)).trim();

                    AtuxVariables.vTieneLectorTarjetas = AtuxSearch.getInLectorTarjeta();
                    if (!existeImpresoraBoletaFactura(pDialog)) existeCajaImpresora = false;
                }
            } else {
                existeCajaImpresora = false;
                JOptionPane.showMessageDialog(pDialog, "No se pudo determinar la existencia de una Caja relacionada al Usuario. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            }

            return existeCajaImpresora;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(pDialog, "Error al obtener Datos de la Caja / Impresora !!! - " + sqlException.getErrorCode(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    static boolean existeImpresoraBoletaFactura(Window pDialog) throws SQLException {
        boolean existeImpresoraBoletaFactura = true;
        // Obteniendo Impresora y Cola de Impresion para BOLETAS
        ArrayList datos = new ArrayList();
        if (AtuxVariables.vInTicketBoleta.equalsIgnoreCase("S"))
            AtuxSearch.getNumeroImpresoraColaImpresion(datos, AtuxVariables.TIPO_TICKET_BOLETA);
        else
            AtuxSearch.getNumeroImpresoraColaImpresion(datos, AtuxVariables.TIPO_BOLETA);
        if (datos.size() == 1) {
            AtuxVariables.vNuImpresoraBoleta = ((String) ((ArrayList) datos.get(0)).get(0)).trim();
            AtuxVariables.vDeColaImpresoraBoleta = ((String) ((ArrayList) datos.get(0)).get(1)).trim();
        } else {
            existeImpresoraBoletaFactura = false;
            JOptionPane.showMessageDialog(pDialog, "No se pudo determinar la existencia de la Impresora para Boletas. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        }
        // Obteniendo Impresora y Cola de Impresion para FACTURAS
        datos.clear();
        if (AtuxVariables.vInTicketFactura.equalsIgnoreCase("S"))
            AtuxSearch.getNumeroImpresoraColaImpresion(datos, AtuxVariables.TIPO_TICKET_FACTURA);
        else
            AtuxSearch.getNumeroImpresoraColaImpresion(datos, AtuxVariables.TIPO_FACTURA);
        if (datos.size() == 1) {
            AtuxVariables.vNuImpresoraFactura = ((String) ((ArrayList) datos.get(0)).get(0)).trim();
            AtuxVariables.vDeColaImpresoraFactura = ((String) ((ArrayList) datos.get(0)).get(1)).trim();
        } else {
            existeImpresoraBoletaFactura = false;
            JOptionPane.showMessageDialog(pDialog, "No se pudo determinar la existencia de la Impresora para Facturas. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        }
        // Obteniendo Impresora y Cola de Impresion para GUIAS
        datos.clear();
        AtuxSearch.getNumeroImpresoraColaImpresion(datos, AtuxVariables.TIPO_GUIA);
        if (datos.size() == 1) {
            AtuxVariables.vNuImpresoraGuia = ((String) ((ArrayList) datos.get(0)).get(0)).trim();
            AtuxVariables.vDeColaImpresoraGuia = ((String) ((ArrayList) datos.get(0)).get(1)).trim();
        } else {
            existeImpresoraBoletaFactura = false;
            JOptionPane.showMessageDialog(pDialog, "No se pudo determinar la existencia de la Impresora para Guias. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        }
        return existeImpresoraBoletaFactura;
    }

    public static boolean getExisteCierreCaja(String fecha) throws SQLException {
        String numRegistros = AtuxDBUtility.getValueAt("VTTV_MOVIMIENTO_CAJA",
                "COUNT(*)",
                "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                        " AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'" +
                        " AND FE_DIA_VENTA BETWEEN TO_DATE('" + fecha + " 00:00:00','dd/MM/yyyy HH24:MI:SS') AND TO_DATE('" + fecha + " 23:59:59','dd/MM/yyyy HH24:MI:SS')" +
                        " AND TI_MOV_CAJA = 'C'" +
                        " AND NU_CAJA =  " + AtuxVariables.vNuCaja +
                        " AND NU_TURNO = " + AtuxVariables.vNuTurno);
        if (Integer.parseInt(numRegistros) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public static String getUsuarioAsignado(Integer pNuCaja) throws SQLException {
        return AtuxDBUtility.getValueAt("VTTR_USUARIO_CAJA USUARIO_CAJA, CMTS_USUARIO USUARIO",
                "USUARIO.ID_USUARIO ||' : '||TRIM(AP_PATERNO_USUARIO||' '|| AP_MATERNO_USUARIO||' '||NO_USUARIO) ",
                "USUARIO_CAJA.CO_COMPANIA           = '" + AtuxVariables.vCodigoCompania + "'" +
                        " AND USUARIO_CAJA.CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'" +
                        " AND USUARIO_CAJA.NU_CAJA  =  " + pNuCaja + "" +
                        " AND USUARIO_CAJA.NU_SEC_USUARIO = USUARIO.NU_SEC_USUARIO" +
                        " AND USUARIO_CAJA.CO_LOCAL       = USUARIO.CO_LOCAL" +
                        " AND USUARIO_CAJA.CO_COMPANIA    = USUARIO.CO_COMPANIA");
    }

    /**
     * todo  los montos de las boletas , facturas ya incluyen las donaciones.
     * se agregó los montos y cantidades de transacciones bancarias
     */
    public static void insertaMovimientoCaja(String nuSecMov,String fechaApertura,String pTiMovCaja, String pCoCajero, String pPaternoCajero, String pMaternoCajero,
                                             String pNombreCajero, String pCaBoletasGeneradas, String pVaMontoBoletasGeneradas,
                                             String pVaMontoBoletaDonaciones, String pCaFacturasGeneradas, String pVaMontoFacturasGeneradas,
                                             String pVaMontoFacturaDonaciones, String pCaGuiasGeneradas, String pVaMontoGuiasGeneradas,
                                             String pVaMontoGeneradas, String pCaBoletasAnul, String pVaMontoBoletasAnul, String pVaMontoBoletaAnulDonaciones,
                                             String pCaFacturasAnul, String pVaMontoFacturasAnul, String pVaMontoFacturaAnulDonaciones,
                                             String pCaGuiasAnul, String pVaMontoGuiasAnul, String pVaMontoAnul, String pVaMontoBoletas,
                                             String pVaMontoFacturas, String pVaMontoGuias, String pVaMontoDonaciones, String pVaMonto,
                                             String pCjVaMontoMoneda, String pVaMontoIngresos, String pVaMontoEgresos, String pTotalIgresos,
                                             String pTotalEgresos, String pVaMontoIngresosAnulados, String pVaMontoEgresosAnulados,
                                             String pTotalIgresosAnulados, String pTotalEgresosAnulados, String pMontoNC,
                                             String pCaNCAnuladas, String pMontoNCAnuladas, String pCaNCGeneradas, String pMontoNCGeneradas,
                                             String pVaMontoNCDonacion, String pVaMontoNCDonacionAnulada) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(nuSecMov);
        parametros.add(pTiMovCaja);
        parametros.add(fechaApertura);
        parametros.add(pCoCajero);
        parametros.add(pPaternoCajero);
        parametros.add(pMaternoCajero);
        parametros.add(pNombreCajero);
        parametros.add(new Integer(AtuxVariables.vNuCaja));
        parametros.add(new Integer(AtuxVariables.vNuTurno));
        parametros.add(AtuxVariables.MONEDA_SOLES);
        parametros.add(new Integer(AtuxUtility.trunc(pCaBoletasGeneradas)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoBoletasGeneradas)));
        parametros.add(new Integer(AtuxUtility.trunc(pCaFacturasGeneradas)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoFacturasGeneradas)));
        parametros.add(new Integer(AtuxUtility.trunc(pCaGuiasGeneradas)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoGuiasGeneradas)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoGeneradas)));
        parametros.add(new Integer(AtuxUtility.trunc(pCaBoletasAnul)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoBoletasAnul)));
        parametros.add(new Integer(AtuxUtility.trunc(pCaFacturasAnul)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoFacturasAnul)));
        parametros.add(new Integer(AtuxUtility.trunc(pCaGuiasAnul)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoGuiasAnul)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoAnul)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoBoletas)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoFacturas)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoGuias)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMonto)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pCjVaMontoMoneda)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pCjVaMontoMoneda) - AtuxUtility.getDecimalNumber(pVaMonto)));
        parametros.add(new Integer("0"));
        parametros.add(new String(""));
        parametros.add(AtuxVariables.vIdUsuario);
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoBoletaDonaciones)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoFacturaDonaciones)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoBoletaAnulDonaciones)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoFacturaAnulDonaciones)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoDonaciones)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoIngresos)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoEgresos)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pTotalIgresos)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pTotalEgresos)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoIngresosAnulados)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoEgresosAnulados)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pTotalIgresosAnulados)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pTotalEgresosAnulados)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pCaNCGeneradas)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pMontoNCGeneradas)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pCaNCAnuladas)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pMontoNCAnuladas)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoNCDonacion)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pVaMontoNCDonacionAnulada)));
        parametros.add(new Double(AtuxUtility.getDecimalNumber(pMontoNC)));

        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_CAJA.INSERTA_MOVIMIENTO_CAJA_NEW(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", parametros, false);
    }

    public static void grabarPedidoNegativoDevolucion(String pNuPedido,
                                                      String pNuPedidoNuevo,
                                                      String pCoGrupoMotivo,
                                                      String pCoMotivoAnulacion,
                                                      String pPagoSoles) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pNuPedido);
        parametros.add(pNuPedidoNuevo);
        parametros.add(pCoGrupoMotivo);
        parametros.add(pCoMotivoAnulacion);
        parametros.add(AtuxVariables.vNuSecUsuarioCajero);
        parametros.add(AtuxVariables.vNuCaja);
        parametros.add(AtuxVariables.vNuTurno);
        parametros.add(pPagoSoles);
        parametros.add(AtuxVariables.vIdUsuario);
        AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_CAJA.GRABAR_NEGATIVO_DEVOLUCION(?,?,?,?,?,?,?,?,?,?,?)", parametros, false);
    }

    public static String devuelveEstadoIndicador() throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        return AtuxDBUtility.executeSQLStoredProcedureStr("INKVENTA_INVENTARIO.DEVUELVE_INDICADOR_PED_LOCAL(?,?)", parametros);
    }

    public static void recalculaMAXMIN() throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        AtuxDBUtility.executeSQLStoredProcedure(null,
                "INKVENTA_INVENTARIO.INV_CALCULA_MAX_MIN(?,?)",
                parametros, false);
    }

    public static int obtienePRODUCTOTRANSITO(String pCoProducto) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pCoProducto);
        return AtuxDBUtility.executeSQLStoredProcedureInt("INKVENTA_INVENTARIO.INV_OBTIENE_PROD_TRANSITO(?, ?, ?)",
                parametros);
    }

    public static double obtieneROTACIONPRODUCTO(int pDiasRotacion, String pCoProducto) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pCoProducto);
        parametros.add(new Integer(pDiasRotacion));
        return AtuxDBUtility.executeSQLStoredProcedureDouble("INKVENTA_INVENTARIO.INV_OBTIENE_PROD_ROTACION(?,?,?,?)", parametros);
    }

    public static ArrayList obtieneROTACIONPRODUCTOMES(String pCoProducto) throws SQLException {
        ArrayList pOutParams = new ArrayList();
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pCoProducto);
        AtuxDBUtility.executeSQLStoredProcedureArrayList(pOutParams,
                "INKVENTA_INVENTARIO.OBTIENE_PROD_ROTACION_4_MESES(?, ?, ?)",
                parametros);
        return pOutParams;
    }

    public static int getCantidadMaximaReponer(String pCoProducto) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add(pCoProducto);
        return AtuxDBUtility.executeSQLStoredProcedureInt("INKVENTA_INVENTARIO.CANTIDAD_MAXIMA_REPONER(?,?,?)",
                parametros);
    }

    public static void grabarCabeceraPedidoReposicionNoCommit(int nuSemanasRot,
                                                              int nuMinimoDias,
                                                              int nuMaximoDias) throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        parametros.add("RP");
        parametros.add("099");
        parametros.add(AtuxVariables.vIdUsuario);
        parametros.add(new Integer(nuSemanasRot));
        parametros.add(new Integer(nuMinimoDias));
        parametros.add(new Integer(nuMaximoDias));
        AtuxVariables.vNumeroPedidoProd = AtuxDBUtility.executeSQLStoredProcedureStr("INKVENTA_INVENTARIO.INV_GRABA_PEDIDO_REP_NO_COMMIT(?,?,?,?,?,?,?,?)",
                parametros);
    }

    public static void actualizaIndicadorPedLocalNoCommit() throws SQLException {
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        AtuxDBUtility.executeSQLStoredProcedure(null, "INKVENTA_INVENTARIO.ACTUALIZA_IND_PED_NO_COMMIT(?,?)", parametros, false);
    }

    public static void updateCANTIDADPEDIDOREP(String codProducto, String cantSolicitada) throws SQLException {
        String query;
        query = " UPDATE LGTR_PRODUCTO_LOCAL SET CA_STOCK_REPONER = " + cantSolicitada + " , " +
                "       IN_REPLICA = 0," +
                "       FE_MOD_PROD_LOCAL = SYSDATE" +
                " WHERE LGTR_PRODUCTO_LOCAL.CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' AND " +
                "       LGTR_PRODUCTO_LOCAL.CO_LOCAL    = '" + AtuxVariables.vCodigoLocal    + "' AND " +
                "       LGTR_PRODUCTO_LOCAL.CO_PRODUCTO = '" + codProducto + "' ";
        AtuxDBUtility.executeSQLUpdate(query);
    }

    public static void updateParametrosPedidoRep(Local local) throws SQLException {
        String query = "UPDATE VTTM_LOCAL " +
                "SET NU_DIAS_ROTACION_PROMEDIO  ="+local.getNuDiasRotacionPromedio()+","+
                "    NU_MIN_DIAS_REPOSICION     ="+local.getNuMinDiasReposicion()+","+
                "    NU_MAX_DIAS_REPOSICION     ="+local.getNuMaxDiasReposicion()+","+
                "    IN_IGNORAR_PROD_SIN_SALDO  ='"+local.getInIgnorarProdSinSaldo()+"',"+
                "    IN_SUMAR_TIEMPO_SUMINISTRO ='"+local.getInSumarTiempoSuministro()+"',"+
                "    IN_SUMAR_TRANSITO          ='"+local.getInSumarTransito()+"',"+
                "    IN_SUMAR_MIN_EXHIBICION    ='"+local.getInSumarMinExhibicion()+"',"+
                "    IN_SUMAR_COMPRAS_PENDIENTES='"+local.getInSumarComprasPendientes()+"',"+
                "    IN_TIPO_OPERACION          ='"+local.getInTipoOperacion()+"',"+
                "    IN_ORIGEN_PRODUCTOS        ='"+local.getInOrigenProductos()+"',"+
                "    IN_SOLO_PROD_ACTIVOS       ='"+local.getInSoloProdActivos()+"',"+
                "    IN_PRODUCTOS_FRACCIONADOS  ='"+local.getInProductosFraccionados()+"',"+
                "    IN_FALTA_CERO              ='"+local.getInFaltaCero()+"',"+
                "    ID_MOD_LOCAL               ='"+AtuxVariables.vIdUsuario+"',"+
                "    FE_MOD_LOCAL               = SYSDATE" +
                "  WHERE CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'"+
                "    AND CO_LOCAL    = '" + AtuxVariables.vCodigoLocal    + "'";

        AtuxDBUtility.executeSQLUpdate(query, true);
    }

    public static void updatePersonaJuridicaAPedido(String pNuRUC,String pNoCliente,String pTiComp,
                                                    String pDireccion,String pNuPedido) throws SQLException {
        String query = "UPDATE VTTC_PEDIDO_VENTA " +
                "    SET NU_RUC_CLIENTE           = '" + pNuRUC     + "'," +
                "        NO_IMPRESO_CLIENTE       = '" + pNoCliente + "'," +
                "        DE_DIRECCION_CLIENTE     = '" + pDireccion + "'," +
                "        NO_IMPRESO_COMPROBANTE   = '" + pNoCliente + "'," +
                "        DE_DIRECCION_COMPROBANTE = '" + pDireccion + "'," +
                "        TI_COMPROBANTE           = '" + pTiComp    + "'"  +
                "  WHERE CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                "    AND CO_LOCAL    = '" + AtuxVariables.vCodigoLocal + "'" +
                "    AND NU_PEDIDO   = '" + pNuPedido + "'";

        AtuxDBUtility.executeSQLUpdate(query, false);
    }

    public static void updatePersonaNaturalAPedido(String pCoCliente,String pNoCliente,
                                                   String pDireccion,String pNuPedido) throws SQLException {
        String query = "UPDATE VTTC_PEDIDO_VENTA " +
                "    SET CO_CLIENTE_LOCAL         = '" + pCoCliente     + "'," +
                "        NO_IMPRESO_CLIENTE       = '" + pNoCliente + "'," +
                "        DE_DIRECCION_CLIENTE     = '" + pDireccion + "'," +
                "        NO_IMPRESO_COMPROBANTE   = '" + pNoCliente + "'," +
                "        DE_DIRECCION_COMPROBANTE = '" + pDireccion + "'" +
                "  WHERE CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                "    AND CO_LOCAL    = '" + AtuxVariables.vCodigoLocal + "'" +
                "    AND NU_PEDIDO   = '" + pNuPedido + "'";

        AtuxDBUtility.executeSQLUpdate(query, false);
    }

    public static void updateTipoPedidoCredito(String pTiPedido, String pNuPedido) throws SQLException {
        String query = "UPDATE VTTC_PEDIDO_VENTA " +
                "    SET TI_PEDIDO                = '" + pTiPedido     + "'" +
                "  WHERE CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                "    AND CO_LOCAL    = '" + AtuxVariables.vCodigoLocal + "'" +
                "    AND NU_PEDIDO   = '" + pNuPedido + "'";

        AtuxDBUtility.executeSQLUpdate(query, false);
    }

    public static void updateColaTestigo(String pDeColaTestigo) throws SQLException {
        String query = "UPDATE CMTR_IMPRESORA_TESTIGO " +
                "    SET DE_COLA_IMPRESION      = '" + pDeColaTestigo + "'," +
                "        FE_MOD_IMPRESORA_LOCAL = SYSDATE," +
                "        ID_MOD_IMPRESORA_LOCAL = '" + AtuxVariables.vIdUsuario + "'" +
                "  WHERE CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                "    AND CO_LOCAL    = '" + AtuxVariables.vCodigoLocal + "'";

        AtuxDBUtility.executeSQLUpdate(query, true);
    }

    public static void updateProdNoAtendido(ProductoLocal prodLocal,int cant) throws SQLException {
        String query = "UPDATE LGTR_PRODUCTO_LOCAL " +
                "    SET CA_PROD_NO_ATENDIDO=NVL(CA_PROD_NO_ATENDIDO,0)+"+cant + ","+
                "        FE_MOD_PROD_LOCAL = SYSDATE," +
                "        ID_MOD_PROD_LOCAL = '" + AtuxVariables.vIdUsuario + "'" +
                "        WHERE CO_COMPANIA = '" + prodLocal.getCoCompania() + "'" +
                "          AND CO_LOCAL    = '" + prodLocal.getCoLocal()    + "'"+
                "          AND CO_PRODUCTO = '" + prodLocal.getCoProducto() + "'"+
                "          AND NU_REVISION_PRODUCTO = '" + prodLocal.getNuRevisionProducto() + "'";

        AtuxDBUtility.executeSQLUpdate(query, true);
    }

    public static void actualizarCorrelativo(CajaPago cajaPago) throws SQLException {
        String query = "UPDATE VTTR_IMPRESORA_LOCAL " +
                "    SET NU_SERIE               = '" + cajaPago.getNuSerie() + "'," +
                "        NU_COMPROBANTE_PAGO    = " + cajaPago.getNuComprobante() + "," +
                "        ID_MOD_IMPRESORA_LOCAL = '" + AtuxVariables.vIdUsuario + "'," +
                "        FE_MOD_IMPRESORA_LOCAL = SYSDATE " +
                "  WHERE CO_COMPANIA    = '" + AtuxVariables.vCodigoCompania + "'" +
                "    AND CO_LOCAL       = '" + AtuxVariables.vCodigoLocal    + "'"+
                "    AND TI_COMPROBANTE = '" + cajaPago.getTiComprobante() + "'";

        AtuxDBUtility.executeSQLUpdate(query, true);
    }

    public static ArrayList validaFraccionDiferentePedido(String pNuPedido) throws Exception {
        ArrayList productos = new ArrayList();
        String sql = "SELECT A.CO_PRODUCTO || 'Ã' || " +
                "       C.DE_PRODUCTO || 'Ã' || " +
                "       DECODE(A.VA_FRACCION, NULL, 1, 0, 1, A.VA_FRACCION) || 'Ã' || " +
                "       DECODE(B.VA_FRACCION, NULL, 1, 0, 1, B.VA_FRACCION) " +
                "  FROM VTTD_PEDIDO_VENTA A,   " +
                "       LGTR_PRODUCTO_LOCAL B, " +
                "       LGTM_PRODUCTO C " +
                " WHERE A.CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' " +
                "   AND A.CO_LOCAL    = '" + AtuxVariables.vCodigoLocal + "' " +
                "   AND A.NU_PEDIDO   = '" + pNuPedido + "' " +
                "   AND A.CO_COMPANIA = B.CO_COMPANIA " +
                "   AND A.CO_LOCAL    = B.CO_LOCAL " +
                "   AND A.CO_PRODUCTO = B.CO_PRODUCTO " +
                "   AND A.NU_REVISION_PRODUCTO = B.NU_REVISION_PRODUCTO " +
                "   AND DECODE(A.VA_FRACCION, NULL, 1, 0, 1, A.VA_FRACCION) <> DECODE(B.VA_FRACCION, NULL, 1, 0, 1, B.VA_FRACCION) " +
                "   AND B.CO_COMPANIA = C.CO_COMPANIA " +
                "   AND B.CO_PRODUCTO = C.CO_PRODUCTO " +
                "   AND B.NU_REVISION_PRODUCTO = C.NU_REVISION_PRODUCTO";

        AtuxDBUtility.executeSQLArrayList(productos, sql);

        return productos;
    }

    public static String getValorDonacion(PedidoVenta pedido) throws SQLException {
        String val = AtuxDBUtility.getValueAt("VTTR_PEDIDO_DONACION",
                "VA_DONACION",
                " CO_COMPANIA  = '" + pedido.getCoCompania() + "'" +
                        " AND CO_LOCAL = '" + pedido.getCoLocal() + "'" +
                        " AND NU_PEDIDO= "  + pedido.getNuPedido());

        try{
            if(!StringUtils.isEmpty(val)){
                if (Double.parseDouble(val) >= 0)
                    return AtuxUtility.formatNumber(Double.parseDouble(val),2);
            }
        }catch (Exception e) {
            return "0.00";
        }

        return "0.00";
    }

}