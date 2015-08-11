package com.atux.desktop.consulta;

import com.atux.bean.consulta.ReporteVenta;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.ShowPstAction;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;

import java.util.Collections;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Seleccionar Rango")
public class SeleccionarVentaVendedorPst extends SeleccionarRangoPst {

    protected void registerActions() {

        Action showConsultaAction = new ShowPstAction() {
            protected ReporteVenta getTargetBackBean() throws IllegalAccessException, InstantiationException {
                ReporteVenta bk = new ReporteVenta();
                bk.setFeInicio(getBackBean().getFeInicio());
                bk.setFeFin(getBackBean().getFeFin());
                return bk;
            }
        }.setTargetPstClass(VentaPorVendedorPst.class);

        actionRsr.registerAction("VerReporte", showConsultaAction)
                .setKeyTrigger(ActionDialog.KEY_F10)
                .notNeedVisualComponent()
                .setAsDefaultAction();

    }

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "005"));
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(SeleccionarVentaVendedorPst.class);
        presenter.init();
    }
}
