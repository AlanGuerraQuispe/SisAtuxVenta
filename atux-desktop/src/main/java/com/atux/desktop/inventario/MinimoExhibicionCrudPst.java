package com.atux.desktop.inventario;

import com.atux.bean.kardex.Producto;
import com.atux.bean.kardex.ProductoFlt;
import com.atux.bean.precios.ProductoExhibicion;
import com.atux.comun.context.AppCtx;
import com.atux.desktop.precios.FrmMinExhibicionCrud;
import com.atux.dominio.precios.ProveedorService;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Ingresar o Modificar Min. Exhibición")
public class MinimoExhibicionCrudPst extends AWFormPresenter<ProductoExhibicion> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmMinExhibicionCrud vsr;

    @Autowired
    ProductoQryMapper productoQryMapper;

    @Autowired
    ProveedorService proveedorService;


    public MinimoExhibicionCrudPst() {
        setAutomaticBinding(false);
        setBackBean(new ProductoExhibicion());
        setShowAuditInfo(false);
    }


    protected void registerBinding() {

        bindingMgr.bind(vsr.txtCoProducto, "producto.coProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeProducto, "producto.deProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtUnidad, "producto.unidad").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeLaboratorio, "laboratorio.deLaboratorio").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtPrecioPromedio, "producto.precioPromedio").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtPrecioVenta, "producto.precioVenta").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtVaMargen, "producto.porcentajeMargen").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtPrecioPVP, "producto.precioPVP").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtPrecioPVPFinal, "producto.precioPVPFinal").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtVaExhibicion, "producto.vaExhibicion");


    }

    private GridProvider gdpKardexDetalle;

    protected void registerGridProviders() {


    }

    protected void registerActions() {
        actionRsr.registerAction("Aceptar", new Action() {
            @Override
            protected Object executeIntern() throws Throwable {
                proveedorService.modificarVaExhibicion(getBackBean().getProducto());
                return null;
            }
        }).setKeyTrigger(ActionDialog.KEY_F10).closeViewAtEnd()
                .notNeedVisualComponent()
                .setConfirmMsg("¿Desea confirmar la modificación?");
    }


    @Override
    protected void registerPicks() {

    }

    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(MinimoExhibicionCrudPst.class);
        presenter.init();
    }


}
