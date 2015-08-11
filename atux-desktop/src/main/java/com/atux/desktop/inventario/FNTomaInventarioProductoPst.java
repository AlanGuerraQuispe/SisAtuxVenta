package com.atux.desktop.inventario;

import com.atux.bean.inventario.TomaInventarioFlt;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.atux.dominio.inventario.InventarioService;
import com.atux.dominio.modelo.UsuarioDTO;
import com.atux.service.qryMapper.InventarioQryMapper;
import com.aw.core.domain.AWBusinessException;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EditAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Lista de Productos por Toma de Inventario")
public class FNTomaInventarioProductoPst extends FindPresenter<TomaInventarioFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());

    protected FrmFNTomaInventarioProducto vsr;

    @Autowired
    InventarioQryMapper inventarioQryMapper;

    @Autowired
    InventarioService inventarioService;

    @Autowired
    APDD apdd;

    public FNTomaInventarioProductoPst() {
        setSearchAtBeginning(true);
    }

    @Override
    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new ColumnInfo("Código", "coProducto", 40, ColumnInfo.LEFT),
                new ColumnInfo("Descripción", "deProducto", 300, ColumnInfo.LEFT),
                new ColumnInfo("Unidad", "unidadProducto", 60, ColumnInfo.LEFT),
                new ColumnInfo("Unidad Frac.", "unidadFraccion", 60, ColumnInfo.LEFT),
                new ColumnInfo("Cant", "caEntero", 60, ColumnInfo.LEFT),
                new ColumnInfo("Cant Frac.", "caFraccion", 60, ColumnInfo.LEFT)
        };
        return columns;
    }

    public List getValues(TomaInventarioFlt tomaInventarioFlt) {
        tomaInventarioFlt.setLocalId(AppCtx.instance().getLocalId());
        return inventarioQryMapper.findTomaInventarioProductoList(tomaInventarioFlt);
    }


    protected void registerActions() {
        actionRsr.registerAction("iniciarToma", new EditAction(), getGridProvider())
                .setKeyTrigger(ActionDialog.KEY_ENTER)
                .notNeedVisualComponent()
                .setTargetPstClass(TomaInventarioProductoCrudPst.class)
        ;
    }


    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        AppCtx.instance().setUsuario(new UsuarioDTO("ADMIN"));
        AppCtx.instance().setLocalId(new LocalId("001", "005"));
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNTomaInventarioProductoPst.class);
        TomaInventarioFlt bk=new TomaInventarioFlt();
        bk.setNuSecTomaInventario("000032");
        bk.setCoLaboratorio("0014");
        presenter.setBackBean(bk);
        presenter.init();
    }


}
