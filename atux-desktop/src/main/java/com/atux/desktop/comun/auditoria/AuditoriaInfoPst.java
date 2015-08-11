package com.atux.desktop.comun.auditoria;

import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionConfig;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;

import java.util.Date;
import java.util.List;

//import com.atux.desktop.comun.links.VerUsuarioAction;
//import com.atux.dominio.usuario.SVUsuario;

/**
 * User: gmc
 * Date: 07/06/2009
 */
@AWPresenter(title = "Auditoría" ,secure = false)
public class AuditoriaInfoPst extends AWFormPresenter<BNAuditoriaInfo> {

    private ActionConfig verUsuarioAction;
//    @Autowired
//    SVUsuario svUsuario;

    FrmAuditoriaInfo vsr;
    private ActionConfig actionShowUser;
    private ActionConfig actionShowModi;

    public AuditoriaInfoPst() {
        setAutomaticBinding(true);
        setShowAuditInfo(false);
    }

    public List getEnabledActionsOnReadOnly() {
       List enabledActionsOnReadOnly = super.getEnabledActionsOnReadOnly();
      
       if(!(vsr.txtNoUsuModi.getText().equals("")))
           enabledActionsOnReadOnly.add(actionShowModi);
        if(!(vsr.txtNoUsuCrea.getText().equals("")))
           enabledActionsOnReadOnly.add(actionShowUser);
       return enabledActionsOnReadOnly;
    }

    @Override
    protected void registerBinding() {
        bindingMgr.bind(vsr.txtFechCrea,"fechCreaStr");
        bindingMgr.bind(vsr.txtFechModi,"fechModiStr");
    }

    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
//        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(AuditoriaInfoPst.class);
        BNAuditoriaInfo bnAuditoriaInfo = new BNAuditoriaInfo();
        bnAuditoriaInfo.setUsuaCrea("asdfsdfas");
        bnAuditoriaInfo.setFechCrea(new Date());
        bnAuditoriaInfo.setFechModi(new Date());
        presenter.setBackBean(bnAuditoriaInfo);
        presenter.init();

    }

    protected void registerActions() {

//        actionShowUser=actionRsr.registerAction("ShowUserCrea", new VerUsuarioAction().setLogin(getBackBean().getUsuaCrea()));
//        actionShowModi=actionRsr.registerAction("ShowUserModi", new VerUsuarioAction().setLogin(getBackBean().getUsuaModi()));

    }

//    public void mostrarUsuario() {
//        UsuarioTmImpl bnUsuario= svUsuario.getUsuario(getBackBean().getNoUsuCrea());
//        verUsuarioAction = actionRsr.registerAction("ShowUserCrea", );
//    }

}
