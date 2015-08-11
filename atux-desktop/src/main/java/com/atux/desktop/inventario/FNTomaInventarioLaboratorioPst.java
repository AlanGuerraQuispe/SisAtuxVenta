package com.atux.desktop.inventario;

import com.atux.bean.inventario.TomaInventario;
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
import com.aw.swing.mvp.action.types.ShowPstAction;
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
@AWPresenter(title = "Lista de Laboratorios por Toma de Inventario")
public class FNTomaInventarioLaboratorioPst extends FindPresenter<TomaInventarioFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());

    protected FrmFNTomaInventarioLaboratorio vsr;

    @Autowired
    InventarioQryMapper inventarioQryMapper;

    @Autowired
    InventarioService inventarioService;

    @Autowired
    APDD apdd;

    public FNTomaInventarioLaboratorioPst() {
        setSearchAtBeginning(true);
    }

    @Override
    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new ColumnInfo("Código", "coLaboratorio", 40, ColumnInfo.LEFT),
                new ColumnInfo("Laboratorio", "deLaboratorio", 40, ColumnInfo.LEFT),
                new ColumnInfo("En Proceso", "inLabProceso", 60, ColumnInfo.LEFT),
                new ColumnInfo("Tipo", "inTipoInventario", 60, ColumnInfo.LEFT)
        };
        return columns;
    }

    public List getValues(TomaInventarioFlt tomaInventarioFlt) {
        tomaInventarioFlt.setLocalId(AppCtx.instance().getLocalId());
        return inventarioQryMapper.findTomaInventarioLaboratorioList(tomaInventarioFlt);
    }


    protected void registerActions() {
        Action verDetalleAction = new ShowPstAction(TomaInventarioFlt.class) {
            @Override
            protected Object getTargetBackBean() throws IllegalAccessException, InstantiationException {
                TomaInventarioFlt result=new TomaInventarioFlt();
                result.setNuSecTomaInventario(getBackBean().getNuSecTomaInventario());
                result.setCoLaboratorio(getBackBean().getCoLaboratorio());
                return result;
            }
        }.setTargetPstClass(FNTomaInventarioProductoPst.class);

        actionRsr.registerAction("verDetalle", verDetalleAction, getGridProvider())
                .setKeyTrigger(ActionDialog.KEY_ENTER)
                .notNeedVisualComponent()
        ;

        Action verTodosAction = new ShowPstAction(TomaInventarioFlt.class) {
            @Override
            protected Object getTargetBackBean() throws IllegalAccessException, InstantiationException {
                TomaInventarioFlt result=new TomaInventarioFlt();
                result.setNuSecTomaInventario(getBackBean().getNuSecTomaInventario());
                return result;
            }
        }.setTargetPstClass(FNTomaInventarioProductoPst.class);

        actionRsr.registerAction("verTodos", verTodosAction, getGridProvider())
                .setKeyTrigger(ActionDialog.KEY_F10)
                .notNeedVisualComponent()
        ;
    }


    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        AppCtx.instance().setUsuario(new UsuarioDTO("ADMIN"));
        AppCtx.instance().setLocalId(new LocalId("001", "005"));
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNTomaInventarioLaboratorioPst.class);
        TomaInventarioFlt bk=new TomaInventarioFlt();
        bk.setNuSecTomaInventario("000032");
        presenter.setBackBean(bk);
        presenter.init();
    }


}
