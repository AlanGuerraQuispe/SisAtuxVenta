package com.atux.desktop.precios;

import com.atux.bean.kardex.Producto;
import com.atux.bean.precios.Proveedor;
import com.atux.bean.precios.ProveedorFlt;
import com.atux.bean.precios.support.PrecioProvider;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.dominio.precios.ProveedorService;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.atux.service.qryMapper.ProveedorQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.event.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Modificar Descuento / Precio Venta")
public class PrecioProductoPst extends AWFormPresenter<Producto> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmPrecioProducto vsr;


    @Autowired
    ProveedorQryMapper proveedorQryMapper;

    @Autowired
    ProductoQryMapper productoQryMapper;

    @Autowired
    ProveedorService proveedorService;


    public PrecioProductoPst() {
        setAutomaticBinding(true);
        setBackBean(new Producto());
        setShowAuditInfo(false);
    }

    protected void registerBinding() {
        bindingMgr.bind(vsr.txtCoProducto, "coProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeProducto, "deProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtUnidad, "unidad").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtMoneda, "moneda").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtPrecioProveedor, "precioProveedor");
        bindingMgr.bind(vsr.txtPorcentajeDescuento, "porcentajeDescuento");
        bindingMgr.bind(vsr.txtPrecioVentaPublico, "precioVentaPublico");
        bindingMgr.bind(vsr.txtPrecioPromedio, "precioPromedio").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtPrecioPVP, "precioPVP").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtPrecioVenta, "precioVenta").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtPrecioPVPFinal, "precioPVPFinal").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtVaMargen, "porcentajeMargen").setUIReadOnly(true);
    }


    @Override
    protected void afterShowValues() {

    }


    @Override
    protected void onWindowsOpened(WindowEvent e) {
        final boolean[] notKey1=new boolean[]{false};
        vsr.txtPrecioProveedor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(!notKey1[0]){
                    notKey1[0]=true;
                    return;
                }
                setValuesToBean();
                new PrecioProvider().calcularPVPPorPrecioProveedor(getBackBean());
                bindingMgr.getBindingComponent(vsr.txtPorcentajeDescuento).setValueToJComponent();
                bindingMgr.getBindingComponent(vsr.txtPrecioVentaPublico).setValueToJComponent();

                bindingMgr.getBindingComponent(vsr.txtPrecioPromedio).setValueToJComponent();
                bindingMgr.getBindingComponent(vsr.txtPrecioPVP).setValueToJComponent();
                bindingMgr.getBindingComponent(vsr.txtPrecioVenta).setValueToJComponent();
                bindingMgr.getBindingComponent(vsr.txtPrecioPVPFinal).setValueToJComponent();
                bindingMgr.getBindingComponent(vsr.txtVaMargen).setValueToJComponent();
            }
        });
        vsr.txtPorcentajeDescuento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                setValuesToBean();
                new PrecioProvider().calcularPVPPorDscto(getBackBean());
                bindingMgr.getBindingComponent(vsr.txtPrecioProveedor).setValueToJComponent();
                bindingMgr.getBindingComponent(vsr.txtPrecioVentaPublico).setValueToJComponent();

                bindingMgr.getBindingComponent(vsr.txtPrecioPromedio).setValueToJComponent();
                bindingMgr.getBindingComponent(vsr.txtPrecioPVP).setValueToJComponent();
                bindingMgr.getBindingComponent(vsr.txtPrecioVenta).setValueToJComponent();
                bindingMgr.getBindingComponent(vsr.txtPrecioPVPFinal).setValueToJComponent();
                bindingMgr.getBindingComponent(vsr.txtVaMargen).setValueToJComponent();
            }
        });
        vsr.txtPrecioVentaPublico.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                setValuesToBean();
                new PrecioProvider().calcularDsctoPorPVP(getBackBean());
                bindingMgr.getBindingComponent(vsr.txtPrecioProveedor).setValueToJComponent();
                bindingMgr.getBindingComponent(vsr.txtPorcentajeDescuento).setValueToJComponent();

                bindingMgr.getBindingComponent(vsr.txtPrecioPromedio).setValueToJComponent();
                bindingMgr.getBindingComponent(vsr.txtPrecioPVP).setValueToJComponent();
                bindingMgr.getBindingComponent(vsr.txtPrecioVenta).setValueToJComponent();
                bindingMgr.getBindingComponent(vsr.txtPrecioPVPFinal).setValueToJComponent();
                bindingMgr.getBindingComponent(vsr.txtVaMargen).setValueToJComponent();
            }
        });
    }

    protected void registerGridProviders() {
        gridProviderMgr.registerGridProvider(new IPOrdenCompra());
    }


    protected void registerActions() {
        actionRsr.registerAction("Aceptar", new Action() {
            @Override
            protected Object executeIntern() throws Throwable {
                proveedorService.modificarPrecioDescuento(getBackBean());
                return null;
            }
        }).setKeyTrigger(ActionDialog.KEY_F10).closeViewAtEnd()
                .notNeedVisualComponent()
                .setConfirmMsg("¿Desea confirmar la modificación?");
    }


    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "025"));

        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(PrecioProductoPst.class);
        Producto producto = new Producto();
        producto.setVaImpuesto(AppCtx.instance().getVaImpuesto());

        producto.setPrecioPromedio(new BigDecimal(10));
        presenter.setBackBean(producto);
        presenter.init();
    }



    private class IPOrdenCompra extends GridInfoProvider<Producto> {


        public ColumnInfo[] getColumnInfo() {

            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Fecha", "feFacturacion", 50, ColumnInfo.LEFT).formatAsDate(),
                    new ColumnInfo("Código", "coProveedor", 50, ColumnInfo.LEFT),
                    new ColumnInfo("Proveedor", "noProveedor", 50, ColumnInfo.LEFT),
                    new ColumnInfo("Cantidad", "caTotal", 50, ColumnInfo.RIGHT)
            };
            return columns;
        }

        public List<Proveedor> getValues(Producto producto) {
            ProveedorFlt proveedorFlt = new ProveedorFlt();
            proveedorFlt.setLocalId(AppCtx.instance().getLocalId());
            proveedorFlt.setCoProducto(producto.getCoProducto());
            return proveedorQryMapper.findProveedorFacturacion(proveedorFlt);
        }

    }
}
