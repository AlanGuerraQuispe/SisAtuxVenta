package com.atux.desktop.comun.picks;

import com.atux.bean.kardex.Producto;
import com.atux.bean.kardex.ProductoFlt;
import com.atux.bean.precios.ProveedorFlt;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.ui.ZonePanel;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: PC6
 * Date: 19/05/2009
 * Time: 09:57:36 AM
 * To change this template use File | Settings | File Templates.
 */
@AWPresenter(title = "Usuarios", secure = false)
public class PickProductoPst extends FindPresenter<ProductoFlt> {
    private FrmPickUsuario vsr;

    @Autowired
    private ProductoQryMapper productoQryMapper;


    private boolean seleccionarVarios = false;


    @Override
    protected void registerBinding() {
        bindingMgr.bind(vsr.txtIdUsuario,"buscar");
    }

    public PickProductoPst() {
        setSearchAtBeginning(false);
    }

    @Override
    public void afterConfiguration() {
        ((ZonePanel) vsr.pnlForm).setLabelTitle("Seleccionar Producto");
        vsr.lblTitGrid.setText("Lista de Productos");
        vsr.lblIdUsuario.setText("Producto");
    }

    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = null;
        columns = new ColumnInfo[]{
                new ColumnInfo("Código", "coProducto", 80, ColumnInfo.LEFT),
                new ColumnInfo("Descripción", "deProducto", 300, ColumnInfo.LEFT),
                new ColumnInfo("Unidad", "unidadProducto", 80, ColumnInfo.LEFT),
                new ColumnInfo("Laboratorio", "deLaboratorio", 300, ColumnInfo.LEFT)
        };
        return columns;
    }


    public List<Producto> getValues(ProductoFlt obj) {

        return productoQryMapper.findProductoBase(obj);
    }


    public static void main(String[] args) {

        AppCtx.instance().setLocalId(new LocalId("001", "005"));

        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(PickProductoPst.class);
        presenter.setBackBean(new ProveedorFlt());
        presenter.init();

    }
}
