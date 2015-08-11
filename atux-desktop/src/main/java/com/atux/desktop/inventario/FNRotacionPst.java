package com.atux.desktop.inventario;

import com.atux.bean.kardex.*;
import com.atux.bean.precios.Proveedor;
import com.atux.bean.precios.ProveedorFlt;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.atux.service.qryMapper.ProveedorQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EditAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Rotación de Productos")
public class FNRotacionPst extends FindPresenter<ProductoFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmFNRotacion vsr;

    @Autowired
    ProductoQryMapper productoQryMapper;

    @Autowired
    ProveedorQryMapper proveedorQryMapper;

    private GridProvider gdpVenta;

    private GridProvider gdpCantidad;

    private GridProvider gdpMargen;

    public FNRotacionPst() {
        setAutomaticBinding(false);
        setBackBean(new ProductoFlt());
        setShowAuditInfo(false);
        setSearchAtBeginning(false);

    }

    @Override
    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new ColumnInfo("Código", "coProducto", 60, ColumnInfo.LEFT),
                new ColumnInfo("Descripción", "deProducto", 360, ColumnInfo.LEFT),
                new ColumnInfo("Unidad", "unidad", 110, ColumnInfo.LEFT),
                new ColumnInfo("Laboratorio", "deLaboratorio", 250, ColumnInfo.LEFT),
                new ColumnInfo("Cant.", "stkDisponibleFmt", 70, ColumnInfo.RIGHT)
        };
        return columns;
    }

    @Override
    public void afterConfiguration() {
        gdpCantidad.setAsScrollable();
        gdpCantidad.setAsFixedWidth();
        gdpVenta.setAsScrollable();
        gdpVenta.setAsFixedWidth();
        gdpMargen.setAsScrollable();
        gdpMargen.setAsFixedWidth();
    }

    protected void registerBinding() {
        bindingMgr.bind(vsr.txtBuscar, "buscar");
    }

    public List getValues(ProductoFlt productoFlt) {
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        List<Producto> result = productoQryMapper.findProducto(productoFlt);
        Collections.sort(result, new Comparator<Producto>() {
            public int compare(Producto o1, Producto o2) {
                return o1.getCoProducto().compareTo(o2.getCoProducto());
            }
        });
        return result;
    }

    @Override
    protected void registerGridProviders() {
        gdpVenta = gridProviderMgr.registerGridProvider(new IPVentaProducto()).dependsOn(getGridProvider());
        gdpMargen = gridProviderMgr.registerGridProvider(new IPMargenProducto()).dependsOn(getGridProvider());
        gridProviderMgr.registerGridProvider(new IPOrdenCompra()).dependsOn(getGridProvider());
        gdpCantidad = gridProviderMgr.registerGridProvider(new IPCantidadProducto()).dependsOn(getGridProvider());
    }

    protected void registerActions() {
        EditAction editAction = new EditAction() {
            @Override
            protected Object getObjectToBeUpdated(Object obj) {
                Kardex kardex = new Kardex();
                Producto producto = (Producto) obj;
                kardex.setProducto(producto);
                DateTime dt = new DateTime();
                kardex.setFeInicio(dt.minusDays(15).toDate());
                kardex.setFeFin(dt.toDate());
                return kardex;
            }
        };
        actionRsr.registerAction("Kardex", editAction, getGridProvider())
                .setKeyTrigger(ActionDialog.KEY_F2)
                .notNeedVisualComponent()
                .setTargetPstClass(KardexPst.class);

    }


    private class IPVentaProducto extends GridInfoProvider<Producto> {


        public ColumnInfo[] getColumnInfo() {

            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Año", "feAnnio", 50, ColumnInfo.RIGHT).setAsSorted(),
                    new ColumnInfo("01", "vaVta01", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("02", "vaVta02", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("03", "vaVta03", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("04", "vaVta04", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("05", "vaVta05", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("06", "vaVta06", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("07", "vaVta07", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("08", "vaVta08", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("09", "vaVta09", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("10", "vaVta10", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("11", "vaVta11", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("12", "vaVta12", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted()
            };
            return columns;
        }

        public List<Producto> getValues(Producto producto) {
            ProductoFlt productoFlt = new ProductoFlt(producto.getCoProducto());
            productoFlt.setLocalId(AppCtx.instance().getLocalId());
            return productoQryMapper.findProductoRotacion(productoFlt);
        }

    }

    private class IPCantidadProducto extends GridInfoProvider<Producto> {


        public ColumnInfo[] getColumnInfo() {

            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Año", "feAnnio", 50, ColumnInfo.RIGHT).setAsSorted(),
                    new ColumnInfo("01", "vaUni01", 60, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("02", "vaUni02", 60, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("03", "vaUni03", 60, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("04", "vaUni04", 60, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("05", "vaUni05", 60, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("06", "vaUni06", 60, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("07", "vaUni07", 60, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("08", "vaUni08", 60, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("09", "vaUni09", 60, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("10", "vaUni10", 60, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("11", "vaUni11", 60, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("12", "vaUni12", 60, ColumnInfo.RIGHT).formatAsMoney()
            };
            return columns;
        }

        public List<Producto> getValues(Producto producto) {
            ProductoFlt productoFlt = new ProductoFlt(producto.getCoProducto());
            productoFlt.setLocalId(AppCtx.instance().getLocalId());
            return productoQryMapper.findProductoRotacion(productoFlt);

        }

    }

    private class IPMargenProducto extends GridInfoProvider<Producto> {


        public ColumnInfo[] getColumnInfo() {

            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Año", "feAnnio", 50, ColumnInfo.RIGHT).setAsSorted(),
                    new ColumnInfo("01", "vaMg01", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("02", "vaMg02", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("03", "vaMg03", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("04", "vaMg04", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("05", "vaMg05", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("06", "vaMg06", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("07", "vaMg07", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("08", "vaMg08", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("09", "vaMg09", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("10", "vaMg10", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("11", "vaMg11", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted(),
                    new ColumnInfo("12", "vaMg12", 50, ColumnInfo.RIGHT).formatAsMoney().setAsSorted()
            };
            return columns;
        }

        public List<Producto> getValues(Producto producto) {
            ProductoFlt productoFlt = new ProductoFlt(producto.getCoProducto());
            productoFlt.setLocalId(AppCtx.instance().getLocalId());
            return productoQryMapper.findProductoRotacion(productoFlt);

        }

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
//            return null;
            return proveedorQryMapper.findProveedorFacturacion(proveedorFlt);
        }

    }

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "005"));
        new LookAndFeelManager().initialize();

        Application.instance().init();

//        ProcessMsgBlocker.instance().showMessage("Prueba");
        Presenter presenter = PstMgr.instance().getPst(FNRotacionPst.class);
        ProductoFlt productoFlt = new ProductoFlt();
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        productoFlt.setBuscar("168041");
        presenter.setBackBean(productoFlt);
        presenter.init();
    }

}
