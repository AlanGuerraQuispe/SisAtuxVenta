package com.atux.desktop.comun.picks;

import com.atux.bean.consulta.UsuarioFlt;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.dominio.modelo.UsuarioDTO;
import com.atux.service.qryMapper.UsuarioQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
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
public class PickUsuariosPst extends FindPresenter<UsuarioFlt> {
    private FrmPickUsuario vsr;

    @Autowired
    private UsuarioQryMapper usuarioQryMapper;


    private boolean seleccionarVarios = false;

    public PickUsuariosPst() {
        setSearchAtBeginning(true);
    }

    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = null;
        columns = new ColumnInfo[]{
                new ColumnInfo("ID", "idUsuario", 100, ColumnInfo.LEFT),
                new ColumnInfo("Nombre", "noCompleto", 300, ColumnInfo.LEFT)

        };
        return columns;
    }


    public List<UsuarioDTO> getValues(UsuarioFlt obj) {
        return usuarioQryMapper.findUsuarios(obj);
    }


    public static void main(String[] args) {

        AppCtx.instance().setLocalId(new LocalId("001", "025"));

        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(PickUsuariosPst.class);
        presenter.setBackBean(new UsuarioFlt());
        presenter.init();

    }
}
