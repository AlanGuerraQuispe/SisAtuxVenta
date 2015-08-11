package com.atux.desktop.comun.picks;

import com.atux.bean.precios.Proveedor;
import com.atux.bean.precios.ProveedorFlt;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.service.qryMapper.ProveedorQryMapper;
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
public class PickProveedorPst extends FindPresenter<ProveedorFlt> {
    private FrmPickUsuario vsr;

    @Autowired
    private ProveedorQryMapper proveedorQryMapper;


    private boolean seleccionarVarios = false;

    public PickProveedorPst() {
        setSearchAtBeginning(true);
    }

    @Override
    public void afterConfiguration() {
        ((ZonePanel) vsr.pnlForm).setLabelTitle("Seleccionar Proveedor");
        vsr.lblTitGrid.setText("Lista de Proveedores");
    }

    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = null;
        columns = new ColumnInfo[]{
                new ColumnInfo("Código", "coProveedor", 80, ColumnInfo.LEFT),
                new ColumnInfo("RUC", "nuDocIdentidad", 80, ColumnInfo.LEFT),
                new ColumnInfo("Nombre", "noProveedor", 300, ColumnInfo.LEFT)
        };
        return columns;
    }


    public List<Proveedor> getValues(ProveedorFlt obj) {
        return proveedorQryMapper.findProveedor(obj);
    }


    public static void main(String[] args) {

        AppCtx.instance().setLocalId(new LocalId("001", "005"));

        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(PickProveedorPst.class);
        presenter.setBackBean(new ProveedorFlt());
        presenter.init();

    }
}
