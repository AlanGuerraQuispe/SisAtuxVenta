package com.atux.desktop.precios;

import com.atux.bean.kardex.KardexFlt;
import com.atux.bean.kardex.Producto;
import com.atux.bean.kardex.ProductoFlt;
import com.atux.bean.precios.Incentivo;
import com.atux.bean.precios.LaboratorioIncientivo;
import com.atux.comun.context.AppCtx;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EditAction;
import com.aw.swing.mvp.action.types.ViewAction;
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
@AWPresenter(title = "Incentivo x Laboratorio")
public class IncentivoPst extends FindPresenter<LaboratorioIncientivo> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmIncentivo vsr;

    @Autowired
    ProductoQryMapper productoQryMapper;


    public IncentivoPst() {
        setAutomaticBinding(true);
        setBackBean(new LaboratorioIncientivo());
        setSearchAtBeginning(true);
        setShowAuditInfo(false);
    }

    protected void registerBinding() {
        bindingMgr.bind(vsr.txtCoLaboratorio, "laboratorio.coLaboratorio").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeLaboratorio, "laboratorio.deLaboratorio").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtBuscar, "buscar");
    }



    @Override
    public ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new ColumnInfo("Código", "producto.coProducto", 60, ColumnInfo.LEFT).setAsSorted(),
                new ColumnInfo("Descripción", "producto.deProducto", 280, ColumnInfo.LEFT).setAsSorted(),
                new ColumnInfo("Unidad", "producto.unidad", 80, ColumnInfo.LEFT).setAsSorted(),
                new ColumnInfo("Fecha Creación", "feCreacion", 110, ColumnInfo.LEFT).formatAsDateTime().setAsSorted(),
                new ColumnInfo("Fecha Inicio", "feInicio", 80, ColumnInfo.LEFT).formatAsDate().setAsSorted(),
                new ColumnInfo("Fecha Fin", "feFin", 80, ColumnInfo.LEFT).formatAsDate().setAsSorted(),
                new ColumnInfo("S/. Incentivo", "vaIncentivo", 70, ColumnInfo.RIGHT).setAsSorted(),
                new ColumnInfo("Precio Vta", "precioVenta", 70, ColumnInfo.RIGHT).setAsSorted(),
                new ColumnInfo("%Margen", "porcentajeMargen", 70, ColumnInfo.RIGHT).setAsSorted()
        };
        return columns;
    }

    @Override
    public List<Incentivo> getValues(LaboratorioIncientivo value) {
        ProductoFlt productoFlt= new ProductoFlt();
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        productoFlt.setCoLaboratorio(value.getLaboratorio().getCoLaboratorio());
        productoFlt.setBuscar(value.getBuscar());
        return productoQryMapper.findProductoIncentivo(productoFlt);
    }

    protected void registerActions() {

        EditAction editAction  = new EditAction(){
            @Override
            protected Object getObjectToBeUpdated(Object obj) {
                Incentivo incentivo=(Incentivo)obj;
                incentivo.setLaboratorio(getBackBean().getLaboratorio());
                return incentivo;
            }
        };
        actionRsr.registerAction("Crear", editAction,getGridProvider())
                .needSelectedRow()
                .setKeyTrigger(ActionDialog.KEY_F2)
                .notNeedVisualComponent()
                .setTargetPstClass(IncentivoCrudPst.class);

        actionRsr.registerAction("Ver", new ViewAction(editAction),getGridProvider())
                .needSelectedRow()
                .setKeyTrigger(ActionDialog.KEY_F3)
                .notNeedVisualComponent()
                .setTargetPstClass(IncentivoCrudPst.class);
    }

    @Override
    protected void afterInitComponents() {
        viewMgr.setTitle("Incentivo - Laboratorio :"+getBackBean().getLaboratorio().toFormat());
    }

    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(IncentivoPst.class);
        presenter.init();
    }


    @Override
    public void afterConfiguration() {

    }

}
