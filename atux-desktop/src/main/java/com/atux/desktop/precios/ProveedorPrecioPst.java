package com.atux.desktop.precios;

import com.atux.bean.precios.PrecioLista;
import com.atux.bean.precios.PrecioListaDetalle;
import com.atux.config.APDD;
import com.atux.desktop.comun.picks.PickProveedorPst;
import com.atux.dominio.precios.ProveedorService;
import com.atux.service.qryMapper.ProveedorQryMapper;
import com.aw.core.report.ReportUtils;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.DeleteItemAction;
import com.aw.swing.mvp.action.types.InsertAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.cmp.pick.Pick;
import com.aw.swing.mvp.cmp.pick.PickImpl;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Proveedor - Precio")
public class ProveedorPrecioPst extends Presenter<PrecioLista> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmProveedorPrecio vsr;

    @Autowired
    ProveedorQryMapper proveedorQryMapper;

    @Autowired
    ProveedorService proveedorService;

    JFileChooser chooser = new JFileChooser();

    GridProvider gdp;

    @Autowired
    APDD apdd;

    public ProveedorPrecioPst() {
        setBackBean(new PrecioLista());
        setShowAuditInfo(false);

    }

    @Override
    protected void registerBinding() {
        bindingMgr.bind(vsr.txtCoLista, "coLista").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtCoProveedor, "coProveedor");
        bindingMgr.bind(vsr.txtNoProveedor, "noProveedor").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtFeInicio, "fechaInicio");
        bindingMgr.bind(vsr.txtFeFin, "fechaFin");
    }

    @Override
    protected void registerGridProviders() {
        gdp = gridProviderMgr.registerGridProvider(new IPPrecioListaDetalle());
    }


    @Override
    protected void registerPicks() {

        Pick pickProveedor = new PickImpl(PickProveedorPst.class);
        pickProveedor.registerBind("coProveedor", "coProveedor");
        pickProveedor.registerBind("noProveedor", "noProveedor");
        pickProveedor.registerParam("coProveedor", "buscar");
        pickMgr.registerPick("coProveedor", pickProveedor);
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
            return precioLista.getDetalle();
        }
    }

    @Override
    protected void afterInitComponents() {

    }

    @Override
    public void onWindowsOpenedInternalOnlyForAWFW(WindowEvent e) {

    }

    public List getValues(PrecioLista precioLista) {
        return precioLista.getDetalle();
    }

    protected void registerActions() {


    }


}
