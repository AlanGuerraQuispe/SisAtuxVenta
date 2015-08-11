package com.atux.desktop.inventario;

import com.atux.bean.inventario.TomaInventarioProducto;
import com.atux.bean.precios.ProductoExhibicion;
import com.atux.desktop.precios.FrmMinExhibicionCrud;
import com.atux.dominio.inventario.InventarioService;
import com.atux.dominio.precios.ProveedorService;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EmptyAction;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Ingreso de Cantidad")
public class TomaInventarioProductoCrudPst extends AWFormPresenter<TomaInventarioProducto> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmTomaInventarioProductoCrud vsr;

    @Autowired
    ProductoQryMapper productoQryMapper;

    @Autowired
    InventarioService inventarioService;


    public TomaInventarioProductoCrudPst() {
        setAutomaticBinding(false);
        setBackBean(new TomaInventarioProducto());
        setShowAuditInfo(false);
    }


    protected void registerBinding() {

        bindingMgr.bind(vsr.txtCoProducto, "coProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeProducto, "deProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtUnidad, "unidad").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeLaboratorio, "deLaboratorio").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtCaEntero, "caEntero");
        bindingMgr.bind(vsr.txtCaFraccion, "caFraccion");


    }

    protected void registerGridProviders() {


    }

    protected void registerActions() {
        actionRsr.registerAction("Aceptar", new EmptyAction() {
            @Override
            protected Object executeIntern() throws Throwable {
                inventarioService.grabarIngresoCantidad(getBackBean());
                return null;
            }
        }).setKeyTrigger(ActionDialog.KEY_F10)
                .closeViewAtEnd()
                .notNeedVisualComponent()
        ;
    }


    @Override
    protected void registerPicks() {

    }

    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(TomaInventarioProductoCrudPst.class);
        presenter.init();
    }


}
