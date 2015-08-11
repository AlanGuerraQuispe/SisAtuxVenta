package com.atux.desktop.precios;

import com.atux.bean.kardex.Kardex;
import com.atux.bean.kardex.Producto;
import com.atux.bean.precios.PrecioLista;
import com.atux.bean.precios.PrecioListaDetalle;
import com.atux.bean.precios.ProveedorFlt;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.atux.dominio.precios.ProveedorService;
import com.atux.service.qryMapper.ProveedorQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.DeleteItemAction;
import com.aw.swing.mvp.action.types.EditAction;
import com.aw.swing.mvp.action.types.InsertAction;
import com.aw.swing.mvp.action.types.ViewAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.event.WindowEvent;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Proveedor - Precio")
public class FNProveedorPrecioPst extends FindPresenter<ProveedorFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmFNProveedorPrecio vsr;

    @Autowired
    ProveedorQryMapper proveedorQryMapper;

    @Autowired
    ProveedorService proveedorService;

    GridProvider gdpDetalleLista;

    @Autowired
    APDD apdd;

    public FNProveedorPrecioPst() {
        setBackBean(new ProveedorFlt());
        setShowAuditInfo(false);
        setSearchAtBeginning(false);
    }

    @Override
    protected void registerBinding() {
        bindingMgr.bind(vsr.txtBuscar, "buscar");
    }

    @Override
    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new ColumnInfo("Lista", "coLista", 60, ColumnInfo.LEFT),
                new ColumnInfo("Cod. Prov", "coProveedor", 60, ColumnInfo.LEFT),
                new ColumnInfo("Proveedor", "noProveedor", 350, ColumnInfo.LEFT),
                new ColumnInfo("Fecha Inicio", "fechaInicio", 60, ColumnInfo.LEFT).formatAsDate(),
                new ColumnInfo("Fecha Fin", "fechaFin", 60, ColumnInfo.LEFT).formatAsDate()

        };
        return columns;
    }

    @Override
    protected void afterInitComponents() {

    }

    @Override
    public void onWindowsOpenedInternalOnlyForAWFW(WindowEvent e) {
        DateTime dateTime = new DateTime();
        setValuesToJComponent();
    }

    public List getValues(ProveedorFlt prove) {
        prove.setLocalId(AppCtx.instance().getLocalId());
        return proveedorQryMapper.findListaPrecioList(prove);
    }


    protected void registerGridProviders() {
        gdpDetalleLista = gridProviderMgr.registerGridProvider(new IPPrecioListaDetalle()).dependsOn(getGridProvider());
    }

    protected void registerActions() {

        actionRsr.registerAction("Ver", new ViewAction(){
            @Override
            protected Object getObjectToBeViewed(Object obj) {
                PrecioLista precioLista = null;
                if (getGridProvider().getSelectedRow() != null) {
                    precioLista = (PrecioLista) getGridProvider().getSelectedRow();
                    ProveedorFlt proveedorFlt = new ProveedorFlt();
                    proveedorFlt.setLocalId(AppCtx.instance().getLocalId());
                    proveedorFlt.setCoLista(precioLista.getCoLista());
                    precioLista.setDetalle(proveedorQryMapper.findListaPrecioDetalleList(proveedorFlt));
                }
                return precioLista;
            }
        },getGridProvider())
                .needSelectedRow()
                .notNeedVisualComponent()
                .setKeyTrigger(ActionDialog.KEY_F5)
                .setTargetPstClass(ProveedorPrecioPst.class)
                .refreshGridAtEnd();
    }


    private class IPPrecioListaDetalle extends GridInfoProvider<PrecioLista> {

        public ColumnInfo[] getColumnInfo() {
            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Código", "coProducto", 80, ColumnInfo.LEFT),
                    new ColumnInfo("Código Prov.", "coProductoProv", 80, ColumnInfo.LEFT),
                    new ColumnInfo("Descripción", "deProducto", 80, ColumnInfo.LEFT),
                    new ColumnInfo("Unidad", "unidadProducto", 80, ColumnInfo.LEFT),
                    new ColumnInfo("Laboratorio", "deLaboratorio", 80, ColumnInfo.LEFT),
                    new ColumnInfo("Cantidad", "cantidad", 60, ColumnInfo.RIGHT).formatAsNumberWithGroup(),
                    new ColumnInfo("Precio", "vaPrecioVenta", 60, ColumnInfo.RIGHT).formatAsMoney()
            };
            return columns;
        }

        public List<PrecioListaDetalle> getValues(PrecioLista precioLista) {
            ProveedorFlt proveedorFlt = new ProveedorFlt();
            proveedorFlt.setLocalId(AppCtx.instance().getLocalId());
            proveedorFlt.setCoLista(precioLista.getCoLista());
            return proveedorQryMapper.findListaPrecioDetalleList(proveedorFlt);

        }

    }

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "025"));


        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNProveedorPrecioPst.class);
        ProveedorFlt productoFlt = new ProveedorFlt();
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        presenter.setBackBean(productoFlt);
        presenter.init();
    }

}
