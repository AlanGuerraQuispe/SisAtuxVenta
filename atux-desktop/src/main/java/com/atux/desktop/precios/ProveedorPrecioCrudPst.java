package com.atux.desktop.precios;

import com.atux.bean.precios.PrecioListaDetalle;
import com.atux.bean.precios.ProveedorFlt;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.atux.desktop.comun.picks.PickProductoPst;
import com.atux.service.qryMapper.ProveedorQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EmptyAction;
import com.aw.swing.mvp.cmp.pick.Pick;
import com.aw.swing.mvp.cmp.pick.PickImpl;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Proveedor - Precio")
public class ProveedorPrecioCrudPst extends Presenter<PrecioListaDetalle> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmProveedorPrecioCrud vsr;

    @Autowired
    ProveedorQryMapper proveedorQryMapper;

    GridProvider gdpDetalleLista;

    @Autowired
    APDD apdd;

    public ProveedorPrecioCrudPst() {
        setBackBean(new PrecioListaDetalle());
        setShowAuditInfo(false);
    }

    @Override
    protected void registerBinding() {
        bindingMgr.bind(vsr.txtCoProducto, "coProducto");
        bindingMgr.bind(vsr.txtDeProducto, "deProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeLaboratorio, "deLaboratorio").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtUnidad, "unidadProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtCoProductoProv, "coProductoProv");
        bindingMgr.bind(vsr.txtCantidad, "cantidad");
        bindingMgr.bind(vsr.txtVaPrecioVenta, "vaPrecioVenta");
    }

    @Override
    protected void registerPicks() {
        Pick pickProveedor = new PickImpl(PickProductoPst.class);
        pickProveedor.registerBind("coProducto", "coProducto");
        pickProveedor.registerBind("deProducto", "deProducto");
        pickProveedor.registerBind("unidadProducto", "unidadProducto");
        pickProveedor.registerBind("deLaboratorio", "deLaboratorio");
        pickProveedor.registerParam("coProducto", "buscar");
        pickMgr.registerPick("coProducto", pickProveedor);
    }

    protected void registerActions() {

        actionRsr.registerAction("Guardar", new EmptyAction())
                .notNeedVisualComponent()
                .setKeyTrigger(ActionDialog.KEY_F10)
                .closeViewAtEnd();
    }

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "025"));
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(ProveedorPrecioCrudPst.class);
        ProveedorFlt productoFlt = new ProveedorFlt();
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        productoFlt.setBuscar("422366");
        presenter.setBackBean(productoFlt);
        presenter.init();
    }

}
