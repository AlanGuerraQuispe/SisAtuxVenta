package com.atux.desktop.consulta;

import com.atux.bean.consulta.ReporteVenta;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.service.qryMapper.ConsultaQryMapper;
import com.aw.core.cache.dropdown.DropDownRowImpl;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EmptyAction;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Seleccione el Tipo Documento")
public class TipoDocumentoFltPst extends AWFormPresenter<ReporteVenta> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmTipoDocumentoFlt vsr;

    @Autowired
    ConsultaQryMapper consultaQryMapper;


    public TipoDocumentoFltPst() {
        setAutomaticBinding(true);
        setBackBean(new ReporteVenta());
        setShowAuditInfo(false);
        setShowCancelMsgConfirmation(false);
    }

    protected void registerBinding() {
        List<DropDownRowImpl> tipoDocumentoDD = new ArrayList<DropDownRowImpl>();
        tipoDocumentoDD.add(0, new DropDownRowImpl(null, "Todos"));
        tipoDocumentoDD.add(1, new DropDownRowImpl("01", "Boleta"));
        tipoDocumentoDD.add(1, new DropDownRowImpl("02", "Factura"));
        bindingMgr.bind(vsr.cmbTipoDocumento, tipoDocumentoDD,"tipoDocumento");
    }

    protected void registerActions() {
        actionRsr.registerAction("VerResumen",new EmptyAction())
                .setKeyTrigger(ActionDialog.KEY_F10)
                .notNeedVisualComponent().closeViewAtEnd();
    }


    public static void main(String[] args) {
        new LookAndFeelManager().initialize();

        Application.instance().init();
        AppCtx.instance().setLocalId(new LocalId("001","025"));
        Presenter presenter = PstMgr.instance().getPst(TipoDocumentoFltPst.class);
        presenter.init();
    }


}
