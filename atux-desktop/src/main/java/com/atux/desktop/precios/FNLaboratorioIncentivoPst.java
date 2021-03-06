package com.atux.desktop.precios;

import com.atux.bean.precios.Laboratorio;
import com.atux.bean.precios.LaboratorioFlt;
import com.atux.bean.precios.LaboratorioIncientivo;
import com.atux.bean.precios.ProveedorProducto;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.dominio.modelo.UsuarioDTO;
import com.atux.service.qryMapper.LaboratorioQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
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
@AWPresenter(title = "B�squeda de Laboratorio")
public class FNLaboratorioIncentivoPst extends FNLaboratorioPst {
    protected final Log LOG = LogFactory.getLog(getClass());

    protected void registerActions() {
        EditAction editAction = new EditAction() {
            @Override
            protected Object getObjectToBeUpdated(Object obj) {
                LaboratorioIncientivo result = new LaboratorioIncientivo();
                Laboratorio labo = (Laboratorio) obj;
                result.setLaboratorio(labo);
                return result;
            }

            @Override
            protected Object getRowToBeDisplayed(Object row) {
                LaboratorioIncientivo bean=(LaboratorioIncientivo)row;
                return bean.getLaboratorio();
            }
        };
        actionRsr.registerAction("Ver", editAction,getGridProvider())
                .needSelectedRow()
                .setKeyTrigger(ActionDialog.KEY_F2)
                .notNeedVisualComponent()
        .setTargetPstClass(IncentivoPst.class);
    }


    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        AppCtx.instance().setUsuario(new UsuarioDTO("ADMIN"));
        AppCtx.instance().setLocalId(new LocalId("001", "005"));
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNLaboratorioIncentivoPst.class);
        presenter.init();
    }


    @Override
    public void afterConfiguration() {

    }





}
