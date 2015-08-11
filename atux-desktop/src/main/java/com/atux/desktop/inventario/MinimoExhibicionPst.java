package com.atux.desktop.inventario;

import com.atux.bean.kardex.Producto;
import com.atux.bean.kardex.ProductoFlt;
import com.atux.bean.precios.ProductoExhibicion;
import com.atux.comun.context.AppCtx;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EditAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
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
@AWPresenter(title = "Minimo de Exhibición")
public class MinimoExhibicionPst extends FindPresenter<ProductoExhibicion> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmMinimoExhibicion vsr;

    @Autowired
    ProductoQryMapper productoQryMapper;

    public MinimoExhibicionPst() {
        setAutomaticBinding(false);
        setBackBean(new ProductoExhibicion());
        setShowAuditInfo(false);
    }

    @Override
    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new ColumnInfo("Código", "coProducto", 60, ColumnInfo.LEFT),
                new ColumnInfo("Descripción", "deProducto", 260, ColumnInfo.LEFT),
                new ColumnInfo("Unidad", "unidad", 110, ColumnInfo.LEFT),
                new ColumnInfo("Min. Exhibición", "vaExhibicion", 90, ColumnInfo.RIGHT).formatAsNumberWithGroup(),
                new ColumnInfo("Stock", "stkDisponible", 70, ColumnInfo.RIGHT),
                new ColumnInfo("Precio Venta", "precioVenta", 60, ColumnInfo.RIGHT),
                new ColumnInfo("%Margen", "porcentajeMargen", 60, ColumnInfo.RIGHT)
        };
        return columns;
    }

    @Override
    public List getValues(ProductoExhibicion obj) {
        ProductoFlt productoFlt = new ProductoFlt();
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        productoFlt.setCoLaboratorio(obj.getLaboratorio().getCoLaboratorio());
        productoFlt.setCoProducto(obj.getBuscar());
        return productoQryMapper.findProductoMinExhibicion(productoFlt);
    }

    protected void registerBinding() {
        bindingMgr.bind(vsr.txtBuscar, "buscar");
    }

    private GridProvider gdpProducto;

    protected void registerGridProviders() {
        gdpProducto = getGridProvider();

    }

    protected void registerActions() {
        EditAction editAction = new EditAction() {
            @Override
            protected Object getObjectToBeUpdated(Object obj) {
                ProductoExhibicion result = new ProductoExhibicion();
                Producto producto = (Producto) obj;
                result.setProducto(producto);
                result.setLaboratorio(getBackBean().getLaboratorio());
                return result;
            }

            @Override
            protected Object getRowToBeDisplayed(Object row) {
                ProductoExhibicion bean = (ProductoExhibicion) row;
                return bean.getProducto();
            }
        };
        actionRsr.registerAction("Ver", editAction, gdpProducto)
                .needSelectedRow()
                .setKeyTrigger(ActionDialog.KEY_F2)
                .notNeedVisualComponent()
                .setTargetPstClass(MinimoExhibicionCrudPst.class);
    }


    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(MinimoExhibicionPst.class);
        presenter.init();
    }





}
