package com.atux.desktop.inventario;

import com.atux.bean.inventario.LaboratorioInventarioFlt;
import com.atux.bean.precios.Laboratorio;
import com.atux.bean.precios.LaboratorioFlt;
import com.atux.bean.precios.ProductoExhibicion;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.desktop.precios.FNLaboratorioPst;
import com.atux.desktop.precios.FrmFNLaboratorio;
import com.atux.dominio.inventario.InventarioService;
import com.atux.dominio.modelo.UsuarioDTO;
import com.atux.enums.TipoTomaInventarioEnum;
import com.atux.service.qryMapper.InventarioQryMapper;
import com.aw.core.domain.AWBusinessException;
import com.aw.core.domain.AWBusinessPropException;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EditAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.SelectorColumn;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.mvp.ui.msg.MessageDisplayerImpl;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Búsqueda de Laboratorio")
public class FNLaboratorioInventarioPst extends FindPresenter<LaboratorioInventarioFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());

    protected FrmFNLaboratorio vsr;

    @Autowired
    InventarioQryMapper inventarioQryMapper;

    @Autowired
    InventarioService inventarioService;


    public FNLaboratorioInventarioPst() {
        LaboratorioInventarioFlt bk = new LaboratorioInventarioFlt();
        bk.setTiTomaInventario(TipoTomaInventarioEnum.LABORATORIO);
        setBackBean(bk);
    }

    @Override
    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new SelectorColumn(),
                new ColumnInfo("Código", "coLaboratorio", 40, ColumnInfo.LEFT),
                new ColumnInfo("Descripción", "deLaboratorio", 240, ColumnInfo.LEFT),
                new ColumnInfo("Toma", "nuSecTomaInventario", 45, ColumnInfo.LEFT),
                new ColumnInfo("# Prod", "caProducto", 45, ColumnInfo.RIGHT).formatAsNumberWithGroup()
        };
        return columns;
    }

    public List getValues(LaboratorioInventarioFlt laboratorioFlt) {
        laboratorioFlt.setLocalId(AppCtx.instance().getLocalId());
        return inventarioQryMapper.findLaboratorio(laboratorioFlt);
    }


    protected void registerActions() {
        Action action = new Action() {
            @Override
            protected Object executeIntern() throws Throwable {
                if (getGridProvider().getSelectedRows().isEmpty()) {
                    throw new AWBusinessException("Debe seleccionar uno o mas laboratorios");
                }
                getBackBean().setSeleccionados(getGridProvider().getSelectedRows());
                String nuSectTomaInventario=inventarioService.iniciarToma(getBackBean());
                new MessageDisplayerImpl(null).showMessage("Número de Toma Inventario generado : " + nuSectTomaInventario);
                return null;
            }
        };
        actionRsr.registerAction("iniciarToma", action, getGridProvider())
                .setKeyTrigger(ActionDialog.KEY_F5)
                .notNeedVisualComponent()
                .closeViewAtEnd()
        ;
    }


    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        AppCtx.instance().setUsuario(new UsuarioDTO("ADMIN"));
        AppCtx.instance().setLocalId(new LocalId("001", "005"));
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNLaboratorioInventarioPst.class);
        presenter.init();
    }


    @Override
    public void afterConfiguration() {
        vsr.lblEnter.setText("F5 = Generar Toma ");
    }


}
