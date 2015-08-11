package com.atux.desktop.comun.picks;

import com.atux.bean.central.Unidad;
import com.atux.bean.consulta.UnidadFlt;
import com.atux.bean.consulta.UsuarioFlt;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.dominio.modelo.UsuarioDTO;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.atux.service.qryMapper.UsuarioQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.cmp.pick.FrmPick;
import com.aw.swing.mvp.ui.ZonePanel;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.mvp.view.ViewManager;
import com.aw.swing.spring.Application;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.event.WindowEvent;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: PC6
 * Date: 19/05/2009
 * Time: 09:57:36 AM
 * To change this template use File | Settings | File Templates.
 */
@AWPresenter(title = "Unidad", secure = false)
public class PickUnidadPst extends FindPresenter<UnidadFlt> {
    private FrmPickUnidad vsr;

    @Autowired
    private ProductoQryMapper productoQryMapper;



    @Override
    protected void registerBinding() {
        bindingMgr.bind(vsr.txtIdUsuario, "unidad");
    }

    private boolean seleccionarVarios = false;

    public PickUnidadPst() {
        setSearchAtBeginning(true);
    }

    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = null;
        columns = new ColumnInfo[]{
                new ColumnInfo("Código", "coUnidad", 100, ColumnInfo.LEFT),
                new ColumnInfo("Nombre", "deUnidad", 300, ColumnInfo.LEFT)

        };
        return columns;
    }


    public List<Unidad> getValues(UnidadFlt obj) {
        return productoQryMapper.findUnidad(obj);
    }


    public static void main(String[] args) {

        AppCtx.instance().setLocalId(new LocalId("001", "025"));

        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(PickUnidadPst.class);
        presenter.setBackBean(new UsuarioFlt());
        presenter.init();

    }
}
