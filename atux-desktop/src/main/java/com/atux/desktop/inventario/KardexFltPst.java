package com.atux.desktop.inventario;

import com.atux.bean.kardex.KardexFlt;
import com.atux.config.APDD;
import com.atux.desktop.comun.picks.PickUsuariosPst;
import com.atux.service.qryMapper.KardexQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EmptyAction;
import com.aw.swing.mvp.binding.component.BndIJComboBox;
import com.aw.swing.mvp.cmp.pick.Pick;
import com.aw.swing.mvp.cmp.pick.PickImpl;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.event.WindowEvent;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Kardex - Consulta")
public class KardexFltPst extends AWFormPresenter<KardexFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private KardexFlt dmn;
    private FrmKardexFlt vsr;

    @Autowired
    KardexQryMapper kardexQryMapper;

    @Autowired
    APDD apdd;

    public KardexFltPst() {
        setAutomaticBinding(true);
        setBackBean(new KardexFlt());
        setShowAuditInfo(false);
        setShowCancelMsgConfirmation(false);
    }

    @Override
    protected void onWindowsOpened(WindowEvent e) {
        vsr.txtFeInicio.requestFocus();
    }


    protected void registerBinding() {

        BndIJComboBox bndGrupoMotivo = bindingMgr.bind(vsr.cmbCoGrupoMotivo, apdd.KARDEX_GRUPO_MOTIVO, "coGrupoMotivo");
        bindingMgr.bind(vsr.cmbCoMotivo, apdd.KARDEX_MOTIVO, "coMotivo").dependsOn(bndGrupoMotivo);
    }


    protected void registerActions() {

        actionRsr.registerAction("seleccionar", new EmptyAction())
                .setKeyTrigger(ActionDialog.KEY_F10)
                .closeViewAtEnd()
                .notNeedVisualComponent();

    }

    @Override
    protected void registerPicks() {
        Pick pickUsuario = new PickImpl(PickUsuariosPst.class);
        pickUsuario.registerBind("idUsuario", "idUsuario");
        pickUsuario.registerParam("idUsuario", "idUsuario");
        pickMgr.registerPick("idUsuario", pickUsuario);

    }

    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(KardexFltPst.class);
        presenter.init();
    }

    @Override
    protected void afterShowValues() {


    }


}
