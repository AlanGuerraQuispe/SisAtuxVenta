package com.atux.desktop.seguridad;

import atux.config.AppConfig;
//import atux.inventario.reference.ConstantsInventario;
//import atux.inventario.reference.VariablesInventario;
import atux.controllers.CCliente;
import atux.controllers.CLocal;
import atux.controllers.CProductoLocal;
import atux.inventario.reference.ConstantsInventario;
import atux.inventario.reference.VariablesInventario;
import atux.modelbd.Local;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.dominio.modelo.UsuarioDTO;
import com.aw.core.bean.security.login.BNLogin;
import com.aw.core.domain.AWBusinessException;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.ui.common.ProcessMsgBlocker;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.mvp.ui.msg.MsgDisplayer;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


@AWPresenter(title = "Inicio de Sesión")
public class LoginPst extends AWFormPresenter<BNLogin> {
    private FrmLoginV2 vsr;

    private boolean loginOk = false;

    public LoginPst() {
        setShowCancelMsgConfirmation(false);
        BNLogin model = new BNLogin();
        setBackBean(model);
        setShowAuditInfo(false);
    }


    protected void registerActions() {
        actionRsr.registerAction("Aceptar", "aceptarAction")
                .setAsDefaultAction()
        ;
    }

    public boolean isValidUser() {
        return loginOk;
    }

    @Override
    protected void onWindowsOpened(WindowEvent e) {
        try {
            leerProperties();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Local local = new CLocal().getInfoLocal();

        vsr.txtVersion.setText(" v.1.0 ");
        vsr.txtEmpresa.setText(local.getCompania().getDeCompania());
        vsr.txtDireccion.setText(local.getDeDireccionLocal());
        vsr.txtTelefono.setText(local.getNuTelNormal());
        vsr.txtRUC.setText(local.getCompania().getNuRucCompania());

        AtuxVariables.vDistritoLocal     = local.getDeDistrito();
        AtuxVariables.vProvinciaLocal    = local.getDeProvincia();
        AtuxVariables.vDepartamentoLocal = local.getDeDepartamento();
        vsr.txtPassword.putClientProperty("ExecThisActionOnKeyEnter", "Aceptar");
    }


    public void aceptarAction() {
        BNLogin bnLogin = getBackBean();

        doLogin(bnLogin.getUserName(), bnLogin.getPassword());
        closeView();
    }

    protected void registerBinding() {
        bindingMgr.bind(vsr.txtUsuario, "userName");
        bindingMgr.bind(vsr.txtPassword, "password").setAsNotChangeCase();
    }

    private void doLogin(String username, String passsword) {
            AppCtx.instance().setUsuario(new UsuarioDTO(username));

            String msg = "";

            try {
                leerProperties();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            AppConfig.Estado configUsuario = AppConfig.configUsuario(username, passsword);
            if (configUsuario == AppConfig.Estado.NO_EXISTE) {
                throw new AWBusinessException("Usuario no existe");
            } else if (configUsuario == AppConfig.Estado.ERROR_CLAVE) {
                throw new AWBusinessException("Contraseña incorrecta");
            } else {
                if (configUsuario == AppConfig.Estado.ACCESO_OK) {
                    MsgDisplayer.showMessage("Bienvenido:  " + AppConfig.getUsuario().getNombreCompleto());

                    ProcessMsgBlocker.initialize("[AtuxPro] Por favor espere.");
                    ProcessMsgBlocker.instance().showMessage("Inicializando ...");
                    try {
                        obtenerInfoLocal();
                    } catch (SQLException ex) {
                        logger.error("Error al obtener configuracion del usuario ", ex);
                    }
                    finally {
                        ProcessMsgBlocker.instance().removeMessage();
                    }
                    loginOk = true;
                  }
            }

    }

    private void leerProperties() throws IOException {
        InputStream fis = null;
        Properties properties = null;
        fis = this.getClass().getResourceAsStream("/properties/AtuxServidor.properties");

        if (fis != null) {
            properties = new Properties();
            properties.load(fis);
            AtuxVariables.vImpresoraTestigo = properties.getProperty("ImpresoraTestigo");
            AtuxVariables.vCodigoCompania   = properties.getProperty("CodigoCompania");
            AtuxVariables.vCodigoLocal      = properties.getProperty("CodigoLocal");
            AtuxVariables.vImpresoraTestigo = properties.getProperty("ImpresoraTestigo");
        } else {
            throw new AWBusinessException("Error al leer properties");
        }
    }

    private void obtenerInfoLocal() throws SQLException {
        Local local = AppConfig.getLocal(AtuxVariables.vCodigoLocal);
        AtuxVariables.vDescripcionLocal = local.getDeLocal().trim();
        AtuxVariables.vTipoCaja = local.getTiCaja();
        AtuxVariables.vDescripcionCompania = local.getCompania().getDeCompania();
        AtuxVariables.vCompaniaDireccion = local.getCompania().getDeDireccion();
        AtuxVariables.vCompaniaDireccionWeb = local.getCompania().getDeDireccionWeb();
        AtuxVariables.vCompaniaFono = local.getCompania().getNuTelNormal();
        AtuxVariables.vNuRucCompania = local.getCompania().getNuRucCompania();

        AtuxVariables.vTipoCambio = AtuxSearch.getTipoCambio(AtuxSearch.getFechaHoraBD(AtuxVariables.FORMATO_FECHA));
        AtuxVariables.vDeMensajeTicket = local.getDeMensajeTicket();
        AtuxVariables.vDireccionLocal = local.getDeDireccionLocal();
        AtuxVariables.vInTicketBoleta = local.getInTicketBoleta();
        AtuxVariables.vInTicketFactura = local.getInTicketFactura();

        VariablesInventario.PANT_GUIA_INGRESO_MANUAL = ConstantsInventario.PANT_GUIA_INGRESO_COTIZACION;
        VariablesInventario.TIPO_INGRESO_MANUAL = ConstantsInventario.INGRESO_COTIZACION;
        AtuxVariables.vInComprobanteManual = "N";

        AtuxSearch.setIGV();

        AtuxVariables.arrayClientes = new CCliente().getCliente("A");

        logger.info(AtuxVariables.vDescripcionLocal + " Es caja <<" + AtuxVariables.vTipoCaja + ">> : Tradicional (T) - Multifuncional (M)");
    }

    public static void main(String[] args) {
        File dir = new File("D:\\atux-dist\\lib");
        File[] files = dir.listFiles();
        StringBuilder sb= new StringBuilder();
        for (File file : files) {
            sb.append(file.getAbsolutePath() + ";");
        }
        System.out.println(sb.toString());

    }
}
