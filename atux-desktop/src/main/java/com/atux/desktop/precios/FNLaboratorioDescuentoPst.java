package com.atux.desktop.precios;

import com.atux.bean.kardex.ProductoFlt;
import com.atux.bean.precios.Laboratorio;
import com.atux.bean.precios.LaboratorioFlt;
import com.atux.bean.precios.ProveedorProducto;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EditAction;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Búsqueda de Laboratorio")
public class FNLaboratorioDescuentoPst extends FNLaboratorioPst {
    protected final Log LOG = LogFactory.getLog(getClass());


    protected void registerActions() {
        EditAction editAction = new EditAction() {
            @Override
            protected Object getObjectToBeUpdated(Object obj) {
                ProductoFlt proveedorProducto = new ProductoFlt();
                Laboratorio labo = (Laboratorio) obj;
                proveedorProducto.setLaboratorio(labo);
                proveedorProducto.setCoLaboratorio(labo.getCoLaboratorio());
                return proveedorProducto ;
            }

            @Override
            protected Object getRowToBeDisplayed(Object row) {
                ProductoFlt proveedorProducto=(ProductoFlt)row;
                return proveedorProducto.getLaboratorio();
            }
        };
        actionRsr.registerAction("Ver", editAction,getGridProvider())
                .needSelectedRow()
                .setKeyTrigger(ActionDialog.KEY_F2)
                .notNeedVisualComponent()
        .setTargetPstClass(FNAjustePrecioPst.class);
    }


    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNLaboratorioDescuentoPst.class);
        presenter.init();
    }





}
