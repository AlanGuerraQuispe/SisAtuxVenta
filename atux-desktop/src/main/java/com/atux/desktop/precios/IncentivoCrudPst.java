package com.atux.desktop.precios;

import com.atux.bean.precios.Incentivo;
import com.atux.dominio.precios.ProveedorService;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Incentivo")
public class IncentivoCrudPst extends AWFormPresenter<Incentivo> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmIncentivoCrud vsr;

    @Autowired
    ProveedorService proveedorService;


    public IncentivoCrudPst() {
        setAutomaticBinding(true);
        setBackBean(new Incentivo());
        setShowAuditInfo(false);
    }

    protected void registerBinding() {
        bindingMgr.bind(vsr.txtDeLaboratorio, "laboratorio.deLaboratorio").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtFeRegistro, "feCreacion").formatAsDateTime().setUIReadOnly(true);
        bindingMgr.bind(vsr.txtCoProducto, "producto.coProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeProducto, "producto.deProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtUnidad, "producto.unidad").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtPrecioPromedio, "producto.precioPromedio").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtPrecioVenta, "producto.precioVenta").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtVaMargen, "producto.porcentajeMargen").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtPrecioPVP, "producto.precioPVP").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtPrecioPVPFinal, "producto.precioPVPFinal").setUIReadOnly(true);

    }


    protected void registerActions() {
        actionRsr.registerAction("Aceptar", new Action() {
            @Override
            protected Object executeIntern() throws Throwable {
                proveedorService.grabarIncentivo(getBackBean());
                return null;
            }
        }).setKeyTrigger(ActionDialog.KEY_F10).closeViewAtEnd()
                .notNeedVisualComponent()
                .setConfirmMsg("¿Desea confirmar la modificación?");

    }

    public void buscarAction() {

    }

    protected void registerValidations() {
        //validatorMgr.registerValidator("");
    }

    @Override
    protected void registerPicks() {

    }

    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(IncentivoCrudPst.class);
        presenter.init();
    }


}
