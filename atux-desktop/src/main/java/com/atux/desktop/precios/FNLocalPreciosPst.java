package com.atux.desktop.precios;

import com.atux.bean.kardex.Kardex;
import com.atux.bean.kardex.Producto;
import com.atux.bean.kardex.ProductoFlt;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.atux.desktop.comun.reporte.ExportarFindPstAction;
import com.atux.desktop.inventario.AjusteStockPst;
import com.atux.desktop.inventario.FrmFNKardex;
import com.atux.desktop.precios.FrmFNLocalPrecios;
import com.atux.service.qryMapper.KardexQryMapper;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.ActionNames;
import com.aw.swing.mvp.action.types.EditAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Lista de Precios - Local")
public class FNLocalPreciosPst extends FindPresenter<ProductoFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmFNLocalPrecios vsr;

    @Autowired
    ProductoQryMapper productoQryMapper;

    @Autowired
    APDD apdd;


    public FNLocalPreciosPst() {
        setAutomaticBinding(true);
        setBackBean(new ProductoFlt());
        setShowAuditInfo(false);
        setSearchAtBeginning(false);

    }

    @Override
    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new ColumnInfo("Código", "coProducto", 80, ColumnInfo.LEFT).setAsSorted(),
                new ColumnInfo("Descripción", "deProducto", 300, ColumnInfo.LEFT).setAsSorted(),
                new ColumnInfo("Unidad", "unidad", 80, ColumnInfo.LEFT).setAsSorted(),
                new ColumnInfo("Laboratorio", "deLaboratorio", 150, ColumnInfo.LEFT).setAsSorted(),
                new ColumnInfo("Estado", "esProductoLocal", 60, ColumnInfo.LEFT).setDropDownFormatter(apdd.ES_TABLA),
                new ColumnInfo("Ult. Fecha", "feModificacion", 70, ColumnInfo.LEFT).formatAsDateTime().setAsSorted(),
                new ColumnInfo("#Dias \nUlt. Precio", "nroDiasSinCambioPrecio", 70, ColumnInfo.LEFT).formatAsNumberWithGroup(),
                new ColumnInfo("Stock Disponible", "stkDisponibleFmt", 70, ColumnInfo.LEFT),
                new ColumnInfo("Precio Vta", "precioVenta", 70, ColumnInfo.RIGHT).formatAsMoney().setAsSorted()
        };
        return columns;
    }


    @Override
    protected void afterInitialing() {
        vsr.lblTitGrid.setText("Lista de Productos");
        vsr.lblF2.setText("F7 = Exportar");
    }

    public List getValues(ProductoFlt productoFlt) {
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        return productoQryMapper.findProductoPrecioLocal(productoFlt);
    }


    protected void registerActions() {

        actionRsr.registerAction(ActionNames.ACTION_EXPORT_EXCEL, new ExportarFindPstAction(this).xls())
        .setKeyTrigger(ActionDialog.KEY_F7).notNeedVisualComponent();
    }

    public void buscarAction(){

    }

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "005"));


        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNLocalPreciosPst.class);
        ProductoFlt productoFlt = new ProductoFlt();
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        productoFlt.setBuscar("422366");
        presenter.setBackBean(productoFlt);
        presenter.init();
    }


    @Override
    public void afterConfiguration() {

    }





}
