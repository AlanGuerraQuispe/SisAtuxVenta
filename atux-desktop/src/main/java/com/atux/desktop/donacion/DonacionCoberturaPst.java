package com.atux.desktop.donacion;


import com.atux.bean.donacion.DonacionDetalle;
import com.atux.config.APDD;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EmptyAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Donación-Cobertura")
public class DonacionCoberturaPst extends Presenter<DonacionDetalle> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmDonacionCobertura vsr;

    @Autowired
    APDD apdd;

    public DonacionCoberturaPst() {
        setBackBean(new DonacionDetalle());
        setShowAuditInfo(false);

    }

    @Override
    protected void registerBinding() {
          bindingMgr.bind(vsr.chkEstado, "esCobertura").registerTrueFalse("A", "I");
    }

    protected void registerActions() {

        actionRsr.registerAction("Guardar", new EmptyAction()).notNeedVisualComponent()
                .setKeyTrigger(ActionDialog.KEY_F10)
                .closeViewAtEnd();

    }


}
