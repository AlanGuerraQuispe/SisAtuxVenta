package com.atux.desktop.inventario;

import com.atux.bean.kardex.*;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.atux.desktop.comun.reporte.ExportarFindPstAction;
import com.atux.service.qryMapper.KardexQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.ActionNames;
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

import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Ajuste de Stock")
public class FNAjusteStockPst extends FindPresenter<ProductoFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmFNAjusteStock vsr;

    @Autowired
    KardexQryMapper kardexQryMapper;

    GridProvider gdpAjusteStock;

    @Autowired
    APDD apdd;

    public FNAjusteStockPst() {
        setBackBean(new ProductoFlt());
        setShowAuditInfo(false);
        setSearchAtBeginning(false);
    }

    @Override
    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new ColumnInfo("Código", "coProducto", 60, ColumnInfo.LEFT),
                new ColumnInfo("Descripción", "deProducto", 260, ColumnInfo.LEFT),
                new ColumnInfo("Unidad", "unidad", 110, ColumnInfo.LEFT),
                new ColumnInfo("Laboratorio", "deLaboratorio", 90, ColumnInfo.LEFT),
                new ColumnInfo("Saldo", "cantidad", 70, ColumnInfo.RIGHT),
                new ColumnInfo("Precio \nPromedio", "precioPromedio", 60, ColumnInfo.RIGHT)
        };
        return columns;
    }

    @Override
    protected void afterInitComponents() {
        vsr.lblF2.setText("F2 = Ajustar Stock");
    }

    @Override
    public void onWindowsOpenedInternalOnlyForAWFW(WindowEvent e) {
        DateTime dateTime = new DateTime();
        getBackBean().setFeInicio(dateTime.minusDays(30).toDate());
        getBackBean().setFeFin(new Date());
        setValuesToJComponent();
    }

    public List getValues(ProductoFlt productoFlt) {
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        return kardexQryMapper.findKardexAjuste(productoFlt);
    }


    protected void registerGridProviders() {
       gdpAjusteStock= gridProviderMgr.registerGridProvider(new IPAjusteStock()).dependsOn(getGridProvider());
    }

    protected void registerActions() {
        EditAction editAction = new EditAction() {
            @Override
            protected Object getObjectToBeUpdated(Object obj) {
                Kardex kardex = new Kardex();
                Producto producto = (Producto) obj;
                kardex.setProducto(producto);
                return kardex;
            }

            @Override
            protected Object getRowToBeDisplayed(Object row) {
                Kardex kardex = (Kardex) row;

                Producto producto= kardex.getProducto();
                gdpAjusteStock.refresh(producto);
                return producto;
            }
        };
        actionRsr.registerAction("Ajuste", editAction, getGridProvider())
                .setKeyTrigger(ActionDialog.KEY_F2)
                .notNeedVisualComponent()
                .setTargetPstClass(AjusteStockPst.class);


        actionRsr.registerAction(ActionNames.ACTION_EXPORT_EXCEL, new ExportarFindPstAction(this).xls())
                .setKeyTrigger(ActionDialog.KEY_F7).notNeedVisualComponent();
    }


    private class IPAjusteStock extends GridInfoProvider<Producto> {


        public ColumnInfo[] getColumnInfo() {
            ColumnInfo grupoMotivo = new ColumnInfo("Sistema", "coGrupoMotivo", 100, ColumnInfo.LEFT)
                    .setDropDownFormatter(apdd.KARDEX_GRUPO_MOTIVO)
                    .setAsSorted();
            ColumnInfo motivo = new ColumnInfo("Tipo de Movimiento", "coMotivo", 100, ColumnInfo.LEFT)
                    .setDropDownFormatter(apdd.KARDEX_MOTIVO).dependsOn(grupoMotivo)
                    .setAsSorted();

            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Fech. Mov", "feKardex", 80, ColumnInfo.LEFT).formatAsDate().setAsSorted(),
                    new ColumnInfo("Hor. Mov", "feKardex", 60, ColumnInfo.LEFT).formatAsHour().setAsSorted(),
                    motivo,
                    grupoMotivo,
                    new ColumnInfo("Usuario", "usuario", 80, ColumnInfo.LEFT).setAsSorted(),
                    new ColumnInfo("Justificación", "justificacion", 70, ColumnInfo.LEFT).setAsSorted()
            };
            return columns;
        }

        public List<KardexDetalle> getValues(Producto producto) {
            KardexFlt kardexFlt = new KardexFlt();
            kardexFlt.setLocalId(AppCtx.instance().getLocalId());
            kardexFlt.setCoProducto(producto.getCoProducto());
            kardexFlt.setFeInicio(getBackBean().getFeInicio());
            kardexFlt.setFeFin(getBackBean().getFeFin());
            List result = kardexQryMapper.findKardexAjusteDetalle(kardexFlt);
            Collections.sort(result, new Comparator<KardexDetalle>() {
                public int compare(KardexDetalle o1, KardexDetalle o2) {
                    return o2.getFeKardex().compareTo(o1.getFeKardex());
                }
            });
            return result;
        }

    }

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "005"));


        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNAjusteStockPst.class);
        ProductoFlt productoFlt = new ProductoFlt();
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        productoFlt.setBuscar("422366");
        presenter.setBackBean(productoFlt);
        presenter.init();
    }

}
