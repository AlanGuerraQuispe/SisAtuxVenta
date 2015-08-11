package com.atux.desktop.inventario;

import com.atux.bean.inventario.LaboratorioInventarioFlt;
import com.atux.bean.inventario.TomaInventario;
import com.atux.bean.inventario.TomaInventarioFlt;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.atux.desktop.precios.FrmFNLaboratorio;
import com.atux.dominio.inventario.InventarioService;
import com.atux.dominio.modelo.UsuarioDTO;
import com.atux.enums.TipoTomaInventarioEnum;
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
import com.aw.swing.mvp.binding.component.support.SelectorColumn;
import com.aw.swing.mvp.navigation.Flow;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.mvp.ui.msg.MessageDisplayerImpl;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Lista de Toma de Inventario")
public class FNTomaInventarioPst extends FindPresenter<TomaInventarioFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());

    protected FrmFNTomaInventario vsr;

    @Autowired
    InventarioQryMapper inventarioQryMapper;

    @Autowired
    InventarioService inventarioService;

    @Autowired
    APDD apdd;

    public FNTomaInventarioPst() {
        setSearchAtBeginning(true);
    }

    @Override
    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new ColumnInfo("Nro", "nuSecTomaInventario", 40, ColumnInfo.LEFT),
                new ColumnInfo("Tipo", "tiTomaInventario", 40, ColumnInfo.LEFT).setDropDownFormatter(apdd.TOMA_INVENTARIO_TIPO),
                new ColumnInfo("Fecha Inicio", "fechaInicio", 60, ColumnInfo.LEFT).formatAsDateTime(),
                new ColumnInfo("Fecha Fin", "fechaFin", 60, ColumnInfo.LEFT).formatAsDateTime(),
                new ColumnInfo("Estado", "estado", 40, ColumnInfo.LEFT).setDropDownFormatter(apdd.TOMA_INVENTARIO_ESTADO)
        };
        return columns;
    }

    public List getValues(TomaInventarioFlt tomaInventarioFlt) {
        tomaInventarioFlt.setLocalId(AppCtx.instance().getLocalId());
        return inventarioQryMapper.findTomaInventarioList(tomaInventarioFlt);
    }


    protected void registerActions() {
        Action action = new ShowPstAction(TomaInventarioFlt.class) {

            @Override
            protected Object getTargetBackBean() throws IllegalAccessException, InstantiationException {
                TomaInventarioFlt result = new TomaInventarioFlt();
                TomaInventario selectRow= (TomaInventario) getSelectedRow();
                result.setNuSecTomaInventario(selectRow.getNuSecTomaInventario());
                return result;
            }
        }.setTargetPstClass(FNTomaInventarioLaboratorioPst.class);

        actionRsr.registerAction("iniciarToma", action, getGridProvider())
                .setKeyTrigger(ActionDialog.KEY_ENTER)
                .notNeedVisualComponent()
        ;
    }


    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        AppCtx.instance().setUsuario(new UsuarioDTO("ADMIN"));
        AppCtx.instance().setLocalId(new LocalId("001", "005"));
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNTomaInventarioPst.class);
        presenter.setBackBean(new TomaInventarioFlt());
        presenter.init();
    }


}
