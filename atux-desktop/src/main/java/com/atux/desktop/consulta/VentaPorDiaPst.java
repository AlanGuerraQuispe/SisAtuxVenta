package com.atux.desktop.consulta;

import com.atux.bean.consulta.ConsultaFlt;
import com.atux.bean.consulta.ReporteDiaItem;
import com.atux.bean.consulta.ReporteVenta;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.desktop.comun.reporte.ExportarFindPstAction;
import com.atux.service.qryMapper.ConsultaQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.support.collection.ListUtils;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.ActionNames;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Venta x Día")
public class VentaPorDiaPst extends AWFormPresenter<ReporteVenta> {
    protected final Log LOG = LogFactory.getLog(getClass());

    private FrmVentaPorDia vsr;

    @Autowired
    ConsultaQryMapper consultaQryMapper;

    GridProvider gdpVentas;
    public VentaPorDiaPst() {
        setAutomaticBinding(true);
        setBackBean(new ReporteVenta());
        setShowAuditInfo(false);
    }
    @Override
    protected void onWindowsOpened(WindowEvent e) {
        gdpVentas.requestFocus();
    }
    protected void registerBinding() {
        bindingMgr.bind(vsr.txtFeInicio).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtFeFin).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtVaTotal).setAsUIReadOnly();
    }

    protected void registerActions() {
        actionRsr.registerAction(ActionNames.ACTION_EXPORT_EXCEL, new ExportarFindPstAction(this).xls())
                .setKeyTrigger(ActionDialog.KEY_F7)
                .notNeedVisualComponent();
    }


    protected void registerValidations() {

    }

    @Override
    protected void afterShowValues() {

        calcularTotales();
    }

    private void calcularTotales() {
        List registros= gdpVentas.getValues();
        BigDecimal vaTotal= ListUtils.sum(registros, "vaMonto", null);
        getBackBean().setVaTotal(vaTotal);
        setValuesToJComponent();
    }
    @Override
    protected void registerGridProviders() {
        gdpVentas=gridProviderMgr.registerGridProvider(new IPVentaPorDia());
    }

    private class IPVentaPorDia extends GridInfoProvider<ReporteVenta> {

        public ColumnInfo[] getColumnInfo() {
            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Fecha", "fechaStr", 80, ColumnInfo.LEFT),
                    new ColumnInfo("Bol Inicio", "nuBoletaInicio", 90, ColumnInfo.LEFT),
                    new ColumnInfo("Bol Final", "nuBoletaFin", 90, ColumnInfo.RIGHT),
                    new ColumnInfo("Fact. Inicial", "nuFacturaInicio", 90, ColumnInfo.LEFT),
                    new ColumnInfo("Fact. Final", "nuFacturaFin", 90, ColumnInfo.LEFT),
                    new ColumnInfo("Bol. Manual \nInicial", "nuBoletaManualInicio", 90, ColumnInfo.LEFT),
                    new ColumnInfo("Fact. Manual \nInicial", "nuBoletaManualFin", 90, ColumnInfo.LEFT),
                    new ColumnInfo("#Bol \nFinal", "totalBoleta", 70, ColumnInfo.LEFT).formatAsNumberWithoutGroup(),
                    new ColumnInfo("#Fact \nFinal", "totalFactura", 70, ColumnInfo.LEFT).formatAsNumberWithoutGroup(),
                    new ColumnInfo("#Devol \nFinal", "totalDevolucion", 70, ColumnInfo.LEFT).formatAsNumberWithoutGroup(),
                    new ColumnInfo("Monto", "vaMonto", 80, ColumnInfo.RIGHT).formatAsMoney()
            };
            return columns;
        }

        public List<ReporteDiaItem> getValues(ReporteVenta kardex) {
            ConsultaFlt filtro= new ConsultaFlt();
            filtro.setLocalId(AppCtx.instance().getLocalId());
            filtro.setFeInicio(kardex.getFeInicio());
            filtro.setFeFin(kardex.getFeFin());
            return consultaQryMapper.findVentaDia(filtro);
        }

    }

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "025"));
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(VentaPorDiaPst.class);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ReporteVenta bk = new ReporteVenta();
        try {
            bk.setFeInicio(sdf.parse("09/01/2004"));
            bk.setFeFin(sdf.parse("30/01/2004"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        presenter.setBackBean(bk);
        presenter.init();
    }


}
