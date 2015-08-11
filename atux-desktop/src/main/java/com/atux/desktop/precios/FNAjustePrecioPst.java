package com.atux.desktop.precios;

import com.atux.bean.kardex.Producto;
import com.atux.bean.kardex.ProductoFlt;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.atux.desktop.inventario.FrmFNKardex;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.atux.service.qryMapper.ProveedorQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EditAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.ui.ZonePanel;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Actualización de Precios x Descuento General")
public class FNAjustePrecioPst extends FindPresenter<ProductoFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmFNKardex vsr;

    @Autowired
    APDD apdd;

    @Autowired
    ProveedorQryMapper proveedorQryMapper;

    @Autowired
    ProductoQryMapper productoQryMapper;


    public FNAjustePrecioPst() {
        setSearchAtBeginning(true);
        setAutomaticBinding(true);
        setBackBean(new ProductoFlt());
        setShowAuditInfo(false);
    }




    @Override
    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new ColumnInfo("Código", "coProducto", 60, ColumnInfo.LEFT),
                new ColumnInfo("Descripción", "deProducto", 340, ColumnInfo.LEFT),
                new ColumnInfo("Unidad", "unidad", 110, ColumnInfo.LEFT),
                new ColumnInfo("Estado", "esProductoLocal", 60, ColumnInfo.LEFT).setDropDownFormatter(apdd.ES_TABLA),
                new ColumnInfo("Cant. Ent.", "stkEntero", 70, ColumnInfo.RIGHT).formatAsNumberWithGroup(),
                new ColumnInfo("Cant. Fracc", "stkFraccion", 60, ColumnInfo.RIGHT).formatAsNumberWithGroup(),
                new ColumnInfo("PV", "precioVenta", 60, ColumnInfo.RIGHT).formatAsMoney(),
                new ColumnInfo("Precio Promedio", "precioPromedio", 60, ColumnInfo.RIGHT).formatAsMoney(),
                new ColumnInfo("% Dscto", "porcentajeDescuento", 60, ColumnInfo.RIGHT).formatAsMoney(),
                new ColumnInfo("PVP", "precioPVPFinal", 60, ColumnInfo.RIGHT).formatAsMoney(),
                new ColumnInfo("Margen", "porcentajeMargen", 60, ColumnInfo.RIGHT).formatAsMoney()
        };
        return columns;
    }

    @Override
    protected void afterInitComponents() {
        vsr.lblF2.setText("F2 = Modificar Precio/Descuento");

    }

    public List getValues(ProductoFlt productoFlt) {
        if(AppCtx.instance().isEnviromentTest()){
            productoFlt.setBuscar("101168");
        }
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        return productoQryMapper.findProducto(productoFlt);
    }



    protected void registerActions() {
        EditAction editAction = new EditAction() {
            @Override
            protected Object getObjectToBeUpdated(Object obj) {
                Producto producto = (Producto) obj;
                producto.setVaImpuesto(AppCtx.instance().getVaImpuesto());
                return producto;
            }
        };

        actionRsr.registerAction("Modificar", editAction, getGridProvider())
                .setKeyTrigger(ActionDialog.KEY_F2)
                .notNeedVisualComponent()
                .setTargetPstClass(PrecioProductoPst.class);
    }

    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNAjustePrecioPst.class);
        presenter.init();
    }


    @Override
    public void afterConfiguration() {
        if(getBackBean().getLaboratorio()!=null){
            ((ZonePanel)vsr.pnlForm).setLabelTitle("Laboratorio: " + getBackBean().getLaboratorio().getDeLaboratorio());
        }
        getGridProvider().setAsScrollable();
        getGridProvider().setAsFixedWidth();
        super.afterConfiguration();

    }


}
