package com.atux.desktop.central;

import com.atux.bean.central.Fraccionamiento;
import com.atux.bean.central.FraccionamientoLocal;
import com.atux.bean.kardex.Kardex;
import com.atux.bean.kardex.Producto;
import com.atux.bean.kardex.ProductoFlt;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.desktop.inventario.FrmFNKardex;
import com.atux.desktop.inventario.KardexPst;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EditAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Búsqueda de Producto")
public class FNFraccionamientoPst extends FindPresenter<ProductoFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmFNFraccionamiento vsr;

    @Autowired
    ProductoQryMapper productoQryMapper;


    public FNFraccionamientoPst() {
        setAutomaticBinding(false);
        setBackBean(new ProductoFlt());
        setShowAuditInfo(false);
        setSearchAtBeginning(false);

    }

    @Override
    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new ColumnInfo("Código", "coProducto", 60, ColumnInfo.LEFT),
                new ColumnInfo("Descripción", "deProducto", 340, ColumnInfo.LEFT),
                new ColumnInfo("Unidad", "unidadProducto", 110, ColumnInfo.LEFT),
                new ColumnInfo("Laboratorio", "deLaboratorio", 90, ColumnInfo.LEFT),
                new ColumnInfo("Fecha Vigencia", "feVigencia", 60, ColumnInfo.RIGHT).formatAsDate(),
                new ColumnInfo("Fecha Ult. Cambio", "feUltCambio", 60, ColumnInfo.RIGHT).formatAsDate(),
                new ColumnInfo("Fecha Creación", "feCreacion", 60, ColumnInfo.RIGHT).formatAsDate()
        };
        return columns;
    }


    protected void registerBinding() {
        bindingMgr.bind(vsr.txtBuscar, "buscar");
    }

    public List getValues(ProductoFlt productoFlt) {
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        List<Producto> productoBaseList = productoQryMapper.findProductoBase(productoFlt);
        List<Producto> productoFraccionamientoList = productoQryMapper.findProductoFraccionamientoActivo(productoFlt);

        for (Producto producto : productoFraccionamientoList) {
            Producto productoBase = productoBaseList.get(productoBaseList.indexOf(producto));
            productoBase.setFeCreacion(producto.getFeCreacion());
            productoBase.setFeUltCambio(producto.getFeUltCambio());
            productoBase.setFeVigencia(producto.getFeVigencia());
        }
        return productoBaseList;
    }


    protected void registerActions() {
        EditAction editAction = new EditAction() {
            @Override
            protected Object getObjectToBeUpdated(Object obj) {
                Fraccionamiento fraccionamiento = new Fraccionamiento();
                Producto producto = (Producto) obj;
                fraccionamiento.setProducto(producto);
                ProductoFlt productoFlt=new ProductoFlt();
                productoFlt.setLocalId(AppCtx.instance().getLocalId());
                productoFlt.setCoProducto(producto.getCoProducto());
                List<FraccionamientoLocal> detalleList=productoQryMapper.findProductoFraccionamiento(productoFlt);
                fraccionamiento.setDetalleList(detalleList);
                return fraccionamiento;
            }

            @Override
            protected Object getRowToBeDisplayed(Object row) {
                Fraccionamiento fraccionamiento=(Fraccionamiento)row;
                ProductoFlt productoFlt=new ProductoFlt();
                productoFlt.setCoProducto(fraccionamiento.getProducto().getCoProducto());
                Producto producto=(Producto)getValues(productoFlt).get(0);
                return producto;
            }
        };
        actionRsr.registerAction("Fraccionar", editAction, getGridProvider())
                .setKeyTrigger(ActionDialog.KEY_F3)
                .notNeedVisualComponent()
                .setTargetPstClass(FraccionamientoPst.class);

    }

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "025"));
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNFraccionamientoPst.class);
        ProductoFlt productoFlt = new ProductoFlt();
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        productoFlt.setBuscar("005869");
        presenter.setBackBean(productoFlt);
        presenter.init();
    }

}
