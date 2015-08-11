package atux.trasladoproducto.reference;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

import atux.common.AtuxLoadCVL;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;


public class SolicitudPedidoInfoProvider {
    String tiSolicitudPedido;
    JDialog jdialog;
    JTextField txtCoLocal;
    JTextField txtDeLocal;
    JTextField txtNuRecepcion;
    JComboBox cmbLocalADevolver;
    JComboBox cmbMotivo;
    JComboBox cmbAlmacen;
    List solicitudOriginalInfo;


    public void inicializar(JDialog jdialog, String tiSolicitudPedido, JTextField txtCoLocal, JTextField txtDeLocal, JTextField txtNuRecepcion,
                            JComboBox cmbLocalADevolver,
                            JComboBox cmbMotivo,
                            JComboBox cmbAlmacen) {
        this.jdialog = jdialog;
        this.tiSolicitudPedido = tiSolicitudPedido;
        this.txtCoLocal = txtCoLocal;
        this.txtDeLocal = txtDeLocal;
        this.txtNuRecepcion = txtNuRecepcion;
        this.cmbLocalADevolver = cmbLocalADevolver;
        this.cmbMotivo = cmbMotivo;
        this.cmbAlmacen = cmbAlmacen;
    }

    public boolean verificarDatosEnBaseATipoPedido() {
        solicitudOriginalInfo = null;
        if (ConstantsTrasladoProducto.esPedidoXSobranteOFaltante(VariablesTrasladoProducto.vTipoPedidoTraslado) ||
                ConstantsTrasladoProducto.esPedidoXSobranteOFaltanteCD(VariablesTrasladoProducto.vTipoPedidoTraslado)) {
            String nuRecepcion = getTxtNuRecepcionText();
            if ("".equals(nuRecepcion)) {
                AtuxUtility.showMessage(jdialog, "Debe ingresar el Nro de Recepcion.", null);
                txtNuRecepcion.requestFocus();
                return false;
            }
            // Verificar que exista el registro
            try {
                if (Integer.parseInt(AtuxDBUtility.getValueAt("LGTC_RECEPCION_PRODUCTO", "count(*)",
                        "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' AND " +
                                "CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "' AND " +
                                "NU_RECEPCION_PRODUCTO = '" + nuRecepcion + "'  ")) == 0) {
                    AtuxUtility.showMessage(jdialog, "No es volido el Nro de Recepcion:" + nuRecepcion + " Verifique!!", null);
                    txtNuRecepcion.requestFocus();
                    return false;
                }
                String estadoRecepcionProd = DBTrasladoProducto.obtenerEstadoDeRecepcion(nuRecepcion);
                if (!ConstantsTrasladoProducto.RECEPCION_PRODUCTO_ES_CERRADO.equals(estadoRecepcionProd)) {
                    AtuxUtility.showMessage(jdialog, "El Número de recepcion:" + nuRecepcion + " no ha pasado por el proceso de Registro de Productos.\n Verifique!!", null);
                    txtNuRecepcion.requestFocus();
                    return false;
                }

                String coLocalOrigenDeRecepcionBase = DBTrasladoProducto.obtenerCoLocalOrigenDeRecepcion(nuRecepcion);
                if (coLocalOrigenDeRecepcionBase == null || "".equals(coLocalOrigenDeRecepcionBase.trim())) {
                    AtuxUtility.showMessage(jdialog, "El Número de recepcion:" + nuRecepcion + " NO TIENE Local Origen. Verifique!!", null);
                    txtNuRecepcion.requestFocus();
                    return false;
                }

                if (ConstantsTrasladoProducto.esPedidoXSobranteOFaltante(tiSolicitudPedido)) {
                    if (ConstantsTrasladoProducto.esLocalCentral(coLocalOrigenDeRecepcionBase)) {
                        AtuxUtility.showMessage(jdialog, "El Número de recepcion:" + nuRecepcion + " TIENE como origen Almacon Central. Verifique!!", null);
                        txtNuRecepcion.requestFocus();
                        return false;
                    }
                    if (AtuxVariables.vCodigoLocal.equals(coLocalOrigenDeRecepcionBase)) {
                        AtuxUtility.showMessage(jdialog, "El Número de recepcion:" + nuRecepcion + " debe tener como origen otro local. Verifique!!", null);
                        txtNuRecepcion.requestFocus();
                        return false;
                    }

                }
                if (ConstantsTrasladoProducto.esPedidoXSobranteOFaltanteCD(tiSolicitudPedido)) {
                    if (!ConstantsTrasladoProducto.esLocalCentral(coLocalOrigenDeRecepcionBase)) {
                        AtuxUtility.showMessage(jdialog, "El Número de recepcion:" + nuRecepcion + " NO TIENE como origen Almacon Central. Verifique!!", null);
                        txtNuRecepcion.requestFocus();
                        return false;
                    }
                }
                solicitudOriginalInfo = DBTrasladoProducto.obtenerSolicitudOriginalInfo(AtuxVariables.vCodigoCompania, AtuxVariables.vCodigoLocal, nuRecepcion);
                txtCoLocal.setText(coLocalOrigenDeRecepcionBase);
                String deLocal = DBTrasladoProducto.obtenerDeLocal(coLocalOrigenDeRecepcionBase);
                txtDeLocal.setText(deLocal);
            } catch (SQLException e) {
                AtuxUtility.showMessage(jdialog, "Problemas al obtener datos del Nro de Recepcion:" + nuRecepcion + " Verifique!!", null);
                txtNuRecepcion.requestFocus();
                e.printStackTrace();
                return false;
            }
        } else if (ConstantsTrasladoProducto.esPedidoDevolucion(VariablesTrasladoProducto.vTipoPedidoTraslado)) {
            if (cmbLocalADevolver.getSelectedIndex() == -1) {
                AtuxUtility.showMessage(jdialog, "Seleccione Local A Devolver...!!!", null);
                cmbLocalADevolver.requestFocus();
                return false;
            }
            if (cmbMotivo.getSelectedIndex() == -1) {
                AtuxUtility.showMessage(jdialog, "Seleccione Motivo...!!!", null);
                cmbMotivo.requestFocus();
                return false;
            }
        } else if (ConstantsTrasladoProducto.esPedidoReposicionManual(VariablesTrasladoProducto.vTipoPedidoTraslado) ||
                ConstantsTrasladoProducto.esPedidoFranquicia(VariablesTrasladoProducto.vTipoPedidoTraslado)) {
            if (cmbAlmacen.getSelectedIndex() == -1) {
                AtuxUtility.showMessage(jdialog, "Seleccione un Almacon...!!!", null);
                cmbAlmacen.requestFocus();
                return false;
            }
        } else {
            if ((getTxtCoLocalText().length() == 0) ||
                    (getTxtDeLocalText().length() == 0)) {
                AtuxUtility.showMessage(jdialog, "Seleccione Local...!!!", null);
                txtCoLocal.requestFocus();
                return false;
            }
            String coLocalTxt = getTxtCoLocalText();
            if (coLocalTxt.equals(AtuxVariables.vCodigoLocal)) {
                AtuxUtility.showMessage(jdialog, "No puede elegir el mismo Local. Seleccione otro Local...!!!", null);
                txtCoLocal.setText("");
                txtDeLocal.setText("");
                txtCoLocal.requestFocus();
                return false;
            }
        }
        return true;
    }

    public SolicitudPedidoInfo getPedidoInfo() throws SQLException {
        if (!ConstantsTrasladoProducto.esPedidoXSobranteOFaltante(tiSolicitudPedido) &&
                !ConstantsTrasladoProducto.esPedidoXSobranteOFaltanteCD(tiSolicitudPedido) &&
                !ConstantsTrasladoProducto.esPedidoDevolucion(tiSolicitudPedido) &&
                !ConstantsTrasladoProducto.esPedidoReposicionManual(tiSolicitudPedido)) {
            String coLocal = AtuxVariables.vCodigoLocal;
            SolicitudPedidoInfo solicitudPedidoInfo = new SolicitudPedidoInfo(AtuxVariables.vCodigoCompania, coLocal, getTxtCoLocalText());
            solicitudPedidoInfo.setNuPedidoTraslado(obtenerNuPedidoTraslado(solicitudPedidoInfo.getCoLocal(), false));
            solicitudPedidoInfo.setNuRecepcionProducto(obtenerNuRecepcionProducto(solicitudPedidoInfo.getCoLocal(), false));
            solicitudPedidoInfo.setCoLocalDestinoAReplicar(solicitudPedidoInfo.getCoLocalDestino());
            return solicitudPedidoInfo;
        } else if (ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_SOBRANTE.equals(tiSolicitudPedido)) {
            String nuRecepcionBase = getTxtNuRecepcionText();
            String coLocalOrigenDeRecepcionBase = DBTrasladoProducto.obtenerCoLocalOrigenDeRecepcion(nuRecepcionBase);
            String coLocal = AtuxVariables.vCodigoLocal;
            SolicitudPedidoInfo solicitudPedidoInfo = new SolicitudPedidoInfo(AtuxVariables.vCodigoCompania, coLocal, coLocalOrigenDeRecepcionBase);
            solicitudPedidoInfo.setNuPedidoTraslado(obtenerNuPedidoTraslado(solicitudPedidoInfo.getCoLocal(), false));
            solicitudPedidoInfo.setNuRecepcionProducto(obtenerNuRecepcionProducto(solicitudPedidoInfo.getCoLocal(), false));
            solicitudPedidoInfo.setCoCompaniaBase(solicitudPedidoInfo.getCoCompania());
            solicitudPedidoInfo.setCoLocalBase(solicitudPedidoInfo.getCoLocal());
            solicitudPedidoInfo.setNuRecepcionProductoBase(nuRecepcionBase);
            solicitudPedidoInfo.setCoLocalDestinoAReplicar(solicitudPedidoInfo.getCoLocalDestino());
            setearDatosPedidoOriginal(solicitudPedidoInfo);
            return solicitudPedidoInfo;
        } else if (ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_FALTANTE.equals(tiSolicitudPedido)) {
            String nuRecepcionBase = getTxtNuRecepcionText();
            String coLocalOrigenDeRecepcionBase = DBTrasladoProducto.obtenerCoLocalOrigenDeRecepcion(nuRecepcionBase);
            String coLocal = AtuxVariables.vCodigoLocal;
            SolicitudPedidoInfo solicitudPedidoInfo = new SolicitudPedidoInfo(AtuxVariables.vCodigoCompania, coLocalOrigenDeRecepcionBase, coLocal);
            solicitudPedidoInfo.setNuPedidoTraslado(obtenerNuPedidoTraslado(solicitudPedidoInfo.getCoLocal(), true));
            solicitudPedidoInfo.setNuRecepcionProducto(obtenerNuRecepcionProducto(solicitudPedidoInfo.getCoLocal(), true));
            solicitudPedidoInfo.setCoCompaniaBase(solicitudPedidoInfo.getCoCompania());
            solicitudPedidoInfo.setCoLocalBase(coLocal);
            solicitudPedidoInfo.setNuRecepcionProductoBase(nuRecepcionBase);
            solicitudPedidoInfo.setCoLocalDestinoAReplicar(coLocalOrigenDeRecepcionBase);
            setearDatosPedidoOriginal(solicitudPedidoInfo);
            return solicitudPedidoInfo;
        } else if (ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_SOBRANTE_CD.equals(tiSolicitudPedido) ||
                ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_SOBRANTE_CD_INTERCOMPANIA.equals(tiSolicitudPedido)) {
            String nuRecepcionBase = getTxtNuRecepcionText();
            String coLocalOrigenDeRecepcionBase = DBTrasladoProducto.obtenerCoLocalOrigenDeRecepcion(nuRecepcionBase);
            String coLocal = AtuxVariables.vCodigoLocal;
            SolicitudPedidoInfo solicitudPedidoInfo = new SolicitudPedidoInfo(AtuxVariables.vCodigoCompania, coLocal, coLocalOrigenDeRecepcionBase);
            solicitudPedidoInfo.setNuPedidoTraslado(obtenerNuPedidoTraslado(solicitudPedidoInfo.getCoLocal(), false));
            solicitudPedidoInfo.setNuRecepcionProducto(obtenerNuRecepcionProducto(solicitudPedidoInfo.getCoLocal(), false));
            solicitudPedidoInfo.setCoCompaniaBase(solicitudPedidoInfo.getCoCompania());
            solicitudPedidoInfo.setCoLocalBase(solicitudPedidoInfo.getCoLocal());
            solicitudPedidoInfo.setNuRecepcionProductoBase(nuRecepcionBase);
            solicitudPedidoInfo.setCoLocalDestinoAReplicar("");
            setearDatosPedidoOriginal(solicitudPedidoInfo);
            return solicitudPedidoInfo;
        } else if (ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_FALTANTE_CD.equals(tiSolicitudPedido)) {
            String nuRecepcionBase = getTxtNuRecepcionText();
            String coLocalOrigenDeRecepcionBase = DBTrasladoProducto.obtenerCoLocalOrigenDeRecepcion(nuRecepcionBase);
            String coLocal = AtuxVariables.vCodigoLocal;
            SolicitudPedidoInfo solicitudPedidoInfo = new SolicitudPedidoInfo(AtuxVariables.vCodigoCompania, coLocalOrigenDeRecepcionBase, coLocal);
            solicitudPedidoInfo.setNuPedidoTraslado(obtenerNuPedidoTraslado(solicitudPedidoInfo.getCoLocal(), false));
            solicitudPedidoInfo.setNuRecepcionProducto(obtenerNuRecepcionProducto(solicitudPedidoInfo.getCoLocal(), false));
            solicitudPedidoInfo.setCoCompaniaBase(solicitudPedidoInfo.getCoCompania());
            solicitudPedidoInfo.setCoLocalBase(coLocal);
            solicitudPedidoInfo.setNuRecepcionProductoBase(nuRecepcionBase);
            solicitudPedidoInfo.setCoLocalDestinoAReplicar("");
            setearDatosPedidoOriginal(solicitudPedidoInfo);
            return solicitudPedidoInfo;
        } else if (ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_DEVOLUCION.equals(tiSolicitudPedido)) {
            String coLocalADevolver = obtenerCoLocalADevolver();
            String coLocal = AtuxVariables.vCodigoLocal;
            SolicitudPedidoInfo solicitudPedidoInfo = new SolicitudPedidoInfo(AtuxVariables.vCodigoCompania, coLocalADevolver, coLocal);
            solicitudPedidoInfo.setNuPedidoTraslado(obtenerNuPedidoTraslado(solicitudPedidoInfo.getCoLocal(), false));
            solicitudPedidoInfo.setNuRecepcionProducto(obtenerNuRecepcionProducto(solicitudPedidoInfo.getCoLocal(), false));
            solicitudPedidoInfo.setCoMotivo(obtenerMotivoDevolucion());
            solicitudPedidoInfo.setCoLocalDestinoAReplicar("");
            return solicitudPedidoInfo;
        } else if (ConstantsTrasladoProducto.esPedidoReposicionManual(tiSolicitudPedido)) {
            String coAlmacen = obtenerCoLocalAlmacen();
            String coLocal = AtuxVariables.vCodigoLocal;
            SolicitudPedidoInfo solicitudPedidoInfo;

            if (ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_DEV_REG_DEV_SOBRANTE_CD_FRANQUICIA.equals(tiSolicitudPedido) ||
                    ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_DEV_REG_FALTANTE_TIENDA_FRANQUICIA.equals(tiSolicitudPedido) ||
                    ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_DEVOLUCION_CD_FRANQUICIA.equals(tiSolicitudPedido)) {
                solicitudPedidoInfo = new SolicitudPedidoInfo(AtuxVariables.vCodigoCompania, coAlmacen, coLocal);
                solicitudPedidoInfo.setNuPedidoTraslado(obtenerNuPedidoTraslado(solicitudPedidoInfo.getCoLocal(), false));
                solicitudPedidoInfo.setNuRecepcionProducto(obtenerNuRecepcionProducto(solicitudPedidoInfo.getCoLocal(), false));
                solicitudPedidoInfo.setCoLocalDestinoAReplicar("");
            } else {
                solicitudPedidoInfo = new SolicitudPedidoInfo(AtuxVariables.vCodigoCompania, coLocal, coAlmacen);
                solicitudPedidoInfo.setNuPedidoTraslado(obtenerNuPedidoTraslado(solicitudPedidoInfo.getCoLocal(), false));
                solicitudPedidoInfo.setNuRecepcionProducto(obtenerNuRecepcionProducto(solicitudPedidoInfo.getCoLocal(), false));
                solicitudPedidoInfo.setCoLocalDestinoAReplicar("");
            }

            return solicitudPedidoInfo;
        }
        //Inicio ID: 001
        else if (ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_DEVOLUCION_CD_FRANQUICIA.equals(tiSolicitudPedido)) {
            //String coAlmacen = obtenerCoLocalAlmacen();
            String coLocalADevolver = obtenerCoLocalADevolver();
            String coLocal = AtuxVariables.vCodigoLocal;
            SolicitudPedidoInfo solicitudPedidoInfo = new SolicitudPedidoInfo(AtuxVariables.vCodigoCompania, coLocalADevolver, coLocal);
            solicitudPedidoInfo.setNuPedidoTraslado(obtenerNuPedidoTraslado(solicitudPedidoInfo.getCoLocal(), false));
            solicitudPedidoInfo.setNuRecepcionProducto(obtenerNuRecepcionProducto(solicitudPedidoInfo.getCoLocal(), false));
            solicitudPedidoInfo.setCoMotivo(obtenerMotivoDevolucion());

            return solicitudPedidoInfo;
        }
        //Fin ID: 001

        return null;
    }

    private String obtenerCoLocalAlmacen() {
        return AtuxLoadCVL.getCVLCode("ALMACEN_DE_REPOSICION", cmbAlmacen.getSelectedIndex());
    }

    private String obtenerCoLocalADevolver() {
        return AtuxLoadCVL.getCVLCode("LOCAL_A_DEVOLVER", cmbLocalADevolver.getSelectedIndex());
    }

    private String obtenerMotivoDevolucion() {
        return AtuxLoadCVL.getCVLCode("MOTIVO_TRAS_PROD", cmbMotivo.getSelectedIndex());
    }

    private void setearDatosPedidoOriginal(SolicitudPedidoInfo solicitudPedidoInfo) {
        if (solicitudOriginalInfo != null && solicitudOriginalInfo.size() > 0) {
            solicitudPedidoInfo.setTiPedidoOriginal(((String) solicitudOriginalInfo.get(0)).trim());
            solicitudPedidoInfo.setNuPedidoOriginal(((String) solicitudOriginalInfo.get(1)).trim());
        }
    }

    private static String obtenerNuPedidoTraslado(String coLocal, boolean remoto) throws SQLException {
        String nuSecNumeracion = "";
        int nuSecSize = 6;
        boolean esAlmacen = DBTrasladoProducto.esAlmacen(coLocal);

        if (esAlmacen) {
            coLocal = AtuxVariables.vCodigoLocal;
        }

        if (remoto && !esAlmacen) {
            nuSecNumeracion = DBTrasladoProducto.obtenerNumeracionRemotamente(AtuxVariables.vCodigoCompania, coLocal, ConstantsTrasladoProducto.NUMERACION_PEDIDO_TRASLADO);
            nuSecNumeracion = AtuxUtility.completeWithSymbol(nuSecNumeracion, nuSecSize, "0", "I");
        } else {
            nuSecNumeracion = AtuxSearch.getNuSecNumeracion(ConstantsTrasladoProducto.NUMERACION_PEDIDO_TRASLADO, nuSecSize);
            AtuxSearch.setNuSecNumeracion(ConstantsTrasladoProducto.NUMERACION_PEDIDO_TRASLADO);
        }
        String coLocalSap = DBTrasladoProducto.getCoLocalSap(coLocal);
        return coLocalSap + nuSecNumeracion;
    }

    private static String obtenerNuRecepcionProducto(String coLocal, boolean remoto) throws SQLException {
        String nuRecepcionProducto = "";
        boolean esAlmacen = DBTrasladoProducto.esAlmacen(coLocal);

        if (esAlmacen) {
            coLocal = AtuxVariables.vCodigoLocal;
        }

        String coLocalSap = DBTrasladoProducto.getCoLocalSap(coLocal);
        if (remoto && !esAlmacen) {
            nuRecepcionProducto = DBTrasladoProducto.obtenerNumeracionRemotamente(AtuxVariables.vCodigoCompania, coLocal, ConstantsTrasladoProducto.NUMERACION_RECEPCION_PRODUCTO);
            nuRecepcionProducto = coLocalSap + AtuxUtility.completeWithSymbol(nuRecepcionProducto, 6, "0", "I");
        } else {
            nuRecepcionProducto = coLocalSap + AtuxSearch.getNuSecNumeracion(ConstantsTrasladoProducto.NUMERACION_RECEPCION_PRODUCTO, 6);
            AtuxSearch.setNuSecNumeracion(ConstantsTrasladoProducto.NUMERACION_RECEPCION_PRODUCTO);
        }

        return nuRecepcionProducto;
    }

    public String getTxtCoLocalText() {
        return txtCoLocal.getText().trim();
    }


    private String getTxtDeLocalText() {
        return txtDeLocal.getText().trim();
    }

    public String getTxtNuRecepcionText() {
        return txtNuRecepcion.getText().trim();
    }
}
