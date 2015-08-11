package com.atux.desktop.consulta;

import com.atux.bean.consulta.ConsultaFlt;
import com.atux.bean.consulta.ReporteVenta;
import com.atux.bean.consulta.ReporteVentaFormaPagoItem;
import com.atux.comun.context.AppCtx;
import com.atux.service.qryMapper.ConsultaQryMapper;
import com.aw.core.util.NumberUtils;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Venta x Transacción")
public class VentaTransaccionResumenPst extends AWFormPresenter<ReporteVenta> {
    protected final Log LOG = LogFactory.getLog(getClass());

    private FrmVentaTransaccionResumen vsr;

    @Autowired
    ConsultaQryMapper consultaQryMapper;


    GridProvider gdpFormaPago;

    public VentaTransaccionResumenPst() {
        setAutomaticBinding(true);
        setBackBean(new ReporteVenta());
        setShowAuditInfo(false);
    }

    @Override
    protected void onWindowsOpened(WindowEvent e) {
        gdpFormaPago.requestFocus();
    }

    protected void registerBinding() {
        bindingMgr.bind(vsr.txtFeInicio).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtFeFin).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtVaEmitido).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtVaTicket).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtVaFactura).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtVaDevolucion).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtVaOtro).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtVaTotal).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtDocumentoTotal).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtFormaPagoTotal).setAsUIReadOnly();
    }

    @Override
    public void afterConfiguration() {
        ConsultaFlt consultaFlt = new ConsultaFlt();
        consultaFlt.setLocalId(AppCtx.instance().getLocalId());
        consultaFlt.setFeInicio(getBackBean().getFeInicio());
        consultaFlt.setFeFin(getBackBean().getFeFin());
        List<Map> result = consultaQryMapper.findResumenVentasMap(consultaFlt);
        if (result.size() > 0) {
            getBackBean().setVaEmitido((BigDecimal) result.get(0).get("vaEmitido"));
            getBackBean().setVaTicket((BigDecimal) result.get(0).get("vaTicket"));
            getBackBean().setVaFactura((BigDecimal) result.get(0).get("vaFactura"));
            getBackBean().setVaDevolucion((BigDecimal) result.get(0).get("vaDevolucion"));
            getBackBean().sumDocumentoTotal();
        }
    }

    protected void registerActions() {

    }

    @Override
    protected void registerGridProviders() {
        gdpFormaPago = gridProviderMgr.registerGridProvider(new IPFormaPago());
    }

    @Override
    protected void afterShowValues() {
        List<ReporteVentaFormaPagoItem> result = gdpFormaPago.getValues();
        BigDecimal total = BigDecimal.ZERO;
        for (ReporteVentaFormaPagoItem item : result) {
            total = total.add(NumberUtils.nvlZero(item.getVaFormaPago()));
        }
        getBackBean().setFormaPagoTotal(total);
        setValuesToJComponent();
    }

    private class IPFormaPago extends GridInfoProvider<ReporteVenta> {
        public ColumnInfo[] getColumnInfo() {
            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("CD", "coFormaPago", 20, ColumnInfo.LEFT),
                    new ColumnInfo("Forma de Pago", "deFormaPago", 70, ColumnInfo.LEFT),
                    new ColumnInfo("Monto", "vaFormaPago", 60, ColumnInfo.RIGHT).formatAsMoney()
            };
            return columns;
        }

        public List<ReporteVentaFormaPagoItem> getValues(ReporteVenta kardex) {
            ConsultaFlt consultaFlt = new ConsultaFlt();
            consultaFlt.setLocalId(AppCtx.instance().getLocalId());
            consultaFlt.setFeInicio(kardex.getFeInicio());
            consultaFlt.setFeFin(kardex.getFeInicio());
            return consultaQryMapper.findResumenFormaPago(consultaFlt);
        }

    }

    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(VentaTransaccionResumenPst.class);
        presenter.init();
    }


}
