package com.atux.desktop.promocion;

import com.atux.bean.kardex.Producto;
import com.atux.bean.promocion.PromocionDetalle;
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
import com.aw.swing.mvp.cmp.pick.PickListener;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.util.Map;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Nuevo Producto Promoción")
public class PromocionCrudPst extends Presenter<PromocionDetalle> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmPromocionCrud vsr;

    @Autowired
    ProveedorQryMapper proveedorQryMapper;

    GridProvider gdpDetalleLista;

    @Autowired
    APDD apdd;

    public PromocionCrudPst() {
        setBackBean(new PromocionDetalle());
        setShowAuditInfo(false);
    }

    @Override
    public void onWindowsOpenedInternalOnlyForAWFW(WindowEvent e) {
        vsr.chkIdemtico.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (vsr.chkIdemtico.isSelected()) {
                    if (StringUtils.isNotBlank(getBackBean().getCoProducto())) {
                        getBackBean().setCoProductoP(getBackBean().getCoProducto());
                        getBackBean().setDeProductoP(getBackBean().getDeProducto());
                        getBackBean().setUnidadProductoP(getBackBean().getUnidadProducto());
                        getBackBean().setDeLaboratorioP(getBackBean().getDeLaboratorio());
                        getBackBean().setInProdFraccionadoP(getBackBean().getInProdFraccionado());
                        getBackBean().setVaFraccionP(getBackBean().getVaFraccion());
                        getBackBean().setUnidadFraccionP(getBackBean().getUnidadFraccion());
                        setValuesToJComponent();
                    }
                }
            }
        });
    }

    @Override
    protected void registerBinding() {
        bindingMgr.bind(vsr.txtCoProducto, "coProducto");
        bindingMgr.bind(vsr.txtDeProducto, "deProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeLaboratorio, "deLaboratorio").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtUnidadProducto, "unidadProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtUnidadFraccion, "unidadFraccion").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtVaFraccion, "vaFraccion").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtInProdFraccionado, "inProdFraccionado").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtInProdFraccionadoP, "inProdFraccionadoP").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtCaEntero, "caEntero");
        bindingMgr.bind(vsr.txtCaFraccion, "caFraccion").setUIReadOnly(true);

        bindingMgr.bind(vsr.txtCoProductoP, "coProductoP");
        bindingMgr.bind(vsr.txtDeProductoP, "deProductoP").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeLaboratorioP, "deLaboratorioP").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtUnidadProductoP, "unidadProductoP").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtUnidadFraccionP, "unidadFraccionP").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtVaFraccionP, "vaFraccionP").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtCaEnteroP, "caEnteroP");
        bindingMgr.bind(vsr.txtCaFraccionP, "caFraccionP").setUIReadOnly(true);

    }

    @Override
    protected void registerPicks() {
        Pick pickProveedor = new PickImpl(PickProductoPst.class);
        pickProveedor.registerBind("coProducto", "coProducto");
        pickProveedor.registerBind("deProducto", "deProducto");
        pickProveedor.registerBind("unidadProducto", "unidadProducto");
        pickProveedor.registerBind("deLaboratorio", "deLaboratorio");
        pickProveedor.registerBind("inProdFraccionado", "inProdFraccionado");
        pickProveedor.registerBind("vaFraccion", "vaFraccion");
        pickProveedor.registerBind("unidadFraccion", "unidadFraccion");
        pickProveedor.registerParam("coProducto", "buscar");
        pickProveedor.addListener(new PickListener() {
            public Map valuesSet(Object selectedObj, Map valuesSet) {
                if (vsr.chkIdemtico.isSelected()) {
                    Producto producto = (Producto) selectedObj;
                    getBackBean().setCoProductoP(producto.getCoProducto());
                    getBackBean().setDeProductoP(producto.getDeProducto());
                    getBackBean().setUnidadProductoP(producto.getUnidadProducto());
                    getBackBean().setDeLaboratorioP(producto.getDeLaboratorio());
                    getBackBean().setInProdFraccionadoP(producto.getInProdFraccionado());
                    getBackBean().setVaFraccionP(producto.getVaFraccion());
                    getBackBean().setUnidadFraccionP(producto.getUnidadFraccion());
                    setValuesToJComponent();
                }
                return valuesSet;
            }
        });
        pickMgr.registerPick("coProducto", pickProveedor);


        Pick pickProveedorP = new PickImpl(PickProductoPst.class);
        pickProveedorP.registerBind("coProductoP", "coProducto");
        pickProveedorP.registerBind("deProductoP", "deProducto");
        pickProveedorP.registerBind("unidadProductoP", "unidadProducto");
        pickProveedorP.registerBind("deLaboratorioP", "deLaboratorio");
        pickProveedorP.registerBind("vaFraccionP", "vaFraccion");
        pickProveedorP.registerBind("unidadFraccionP", "unidadFraccion");
        pickProveedorP.registerBind("inProdFraccionadoP", "inProdFraccionado");
        pickProveedorP.registerParam("coProductoP", "buscar");
        pickMgr.registerPick("coProductoP", pickProveedorP);
    }

    protected void registerActions() {

        actionRsr.registerAction("Guardar", new EmptyAction())
                .notNeedVisualComponent()
                .setKeyTrigger(ActionDialog.KEY_F10)
                .closeViewAtEnd();
    }

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "005"));
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(PromocionCrudPst.class);
        PromocionDetalle productoFlt = new PromocionDetalle();
        presenter.setBackBean(productoFlt);
        presenter.init();
    }

}
