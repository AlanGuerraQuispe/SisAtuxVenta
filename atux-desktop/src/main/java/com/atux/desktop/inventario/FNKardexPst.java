package com.atux.desktop.inventario;

import com.atux.bean.kardex.Kardex;
import com.atux.bean.kardex.Producto;
import com.atux.bean.kardex.ProductoFlt;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
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

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Búsqueda de Producto")
public class FNKardexPst extends FindPresenter<ProductoFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmFNKardex vsr;

    @Autowired
    ProductoQryMapper productoQryMapper;


    public FNKardexPst() {
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
                new ColumnInfo("Unidad", "unidadProducto", 110, ColumnInfo.LEFT),
                new ColumnInfo("Unidad Frac.", "unidadFraccion", 90, ColumnInfo.LEFT),
                new ColumnInfo("Cant. Ent.", "stkEntero", 70, ColumnInfo.RIGHT).formatAsNumberWithGroup(),
                new ColumnInfo("Cant. Fracc", "stkFraccion", 60, ColumnInfo.RIGHT).formatAsNumberWithGroup()
        };
        return columns;
    }


    protected void registerBinding() {
        bindingMgr.bind(vsr.txtBuscar, "buscar");
    }

    public List getValues(ProductoFlt productoFlt) {
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        List result= productoQryMapper.findProducto(productoFlt);
        return result;
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

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "005"));
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNKardexPst.class);
        ProductoFlt productoFlt = new ProductoFlt();
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        productoFlt.setBuscar("422366");
        presenter.setBackBean(productoFlt);
        presenter.init();
    }

}
