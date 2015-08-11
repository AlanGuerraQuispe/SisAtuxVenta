package com.atux.desktop.inventario;

import com.atux.bean.kardex.Kardex;
import com.atux.config.APDD;
import com.atux.dominio.inventario.KardexService;
import com.atux.service.qryMapper.KardexQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EmptyAction;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.event.WindowEvent;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Ingresar Cantidades - Ajuste de Stock")
public class AjusteStockPst extends AWFormPresenter<Kardex> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private Kardex dmn;
    private FrmAjusteStock vsr;

    @Autowired
    KardexQryMapper kardexQryMapper;

    @Autowired
    KardexService kardexService;

    @Autowired
    APDD apdd;

    public AjusteStockPst() {
        setAutomaticBinding(true);
        setBackBean(new Kardex());
        setShowAuditInfo(false);
        setShowCancelMsgConfirmation(false);
    }

    @Override
    protected void onWindowsOpened(WindowEvent e) {
        vsr.txtStkEntero.requestFocus();

    }


    protected void registerBinding() {
        bindingMgr.bind(vsr.cmbCoMotivo, apdd.KARDEX_MOTIVO_AJUSTE_INVENTARIO, "coMotivoAjuste");
        bindingMgr.bind(vsr.txtCoProducto, "producto.coProducto").setAsUIReadOnly();
        bindingMgr.bind(vsr.txtDeProducto, "producto.deProducto").setAsUIReadOnly();
        bindingMgr.bind(vsr.txtUnidad, "producto.unidad").setAsUIReadOnly();
        bindingMgr.bind(vsr.txtDeLaboratorio, "producto.deLaboratorio").setAsUIReadOnly();
        bindingMgr.bind(vsr.txtStkEntero, "producto.stkEntero");
        if ("S".equals(backBean.getProducto().getInProdFraccionado())) {
            bindingMgr.bind(vsr.txtStkFraccion, "producto.stkFraccion");
        } else {
            bindingMgr.bind(vsr.txtStkFraccion, "producto.stkFraccion").setAsUIReadOnly();
        }

        bindingMgr.bind(vsr.txtPrecioPromedio, "producto.precioPromedio").setAsUIReadOnly();
        bindingMgr.bind(vsr.txtJustificacion, "justificacion");
    }


    protected void registerActions() {

        actionRsr.registerAction("Aceptar", new Action() {
            @Override
            protected Object executeIntern() throws Throwable {
                kardexService.ajustarStock(getBackBean());
                return null;
            }
        }).setKeyTrigger(ActionDialog.KEY_F10)
                .closeViewAtEnd()
                .notNeedVisualComponent().setConfirmMsg("¿Desea confirmar el ajuste de inventario?");

    }

    @Override
    protected void registerPicks() {

    }

    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(AjusteStockPst.class);
        presenter.init();
    }

    @Override
    protected void afterShowValues() {


    }


}
