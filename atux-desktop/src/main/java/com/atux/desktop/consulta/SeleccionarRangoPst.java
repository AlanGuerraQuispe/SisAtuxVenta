package com.atux.desktop.consulta;

import com.atux.bean.consulta.ConsultaFlt;
import com.atux.bean.consulta.ReporteVenta;
import com.atux.service.qryMapper.ConsultaQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.ShowPstAction;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Seleccionar Rango")
public class SeleccionarRangoPst extends AWFormPresenter<ConsultaFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());

    private FrmSeleccionarRango vsr;

    @Autowired
    ConsultaQryMapper consultaQryMapper;


    public SeleccionarRangoPst() {
        setAutomaticBinding(true);
        setBackBean(new ConsultaFlt());
        setShowAuditInfo(false);
    }

    protected void registerBinding() {

    }

    protected void registerActions() {
        Action showConsultaAction = new ShowPstAction() {
            protected ReporteVenta getTargetBackBean() throws IllegalAccessException, InstantiationException {
                ReporteVenta bk=new ReporteVenta();
                bk.setFeInicio(getBackBean().getFeInicio());
                bk.setFeFin(getBackBean().getFeFin());
                return bk;
            }
        }.setTargetPstClass(DetalleVentaPst.class);
        actionRsr.registerAction("Buscar", showConsultaAction)
                .setKeyTrigger(ActionDialog.KEY_F10)
    .setAsDefaultAction();

    }



    protected void registerValidations() {
        //validatorMgr.registerValidator("");
    }

    @Override
    protected void registerPicks() {


    }

    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(SeleccionarRangoPst.class);
        presenter.init();
    }


}
