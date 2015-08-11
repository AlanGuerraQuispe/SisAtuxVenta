package com.atux.desktop.consulta;

import com.atux.bean.consulta.ConsultaFlt;
import com.atux.bean.consulta.ReporteVendedorItem;
import com.atux.bean.consulta.ReporteVenta;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.desktop.comun.reporte.ExportarFindPstAction;
import com.atux.service.qryMapper.ConsultaQryMapper;
import com.aw.core.util.NumberUtils;
import com.aw.core.view.ViewMode;
import com.aw.stereotype.AWPresenter;
import com.aw.support.collection.ListUtils;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.ActionNames;
import com.aw.swing.mvp.action.types.ShowPstAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.mapping.Collection;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Venta x Vendedor")
public class VentaPorVendedorPst extends AWFormPresenter<ReporteVenta> {
    protected final Log LOG = LogFactory.getLog(getClass());

    private FrmVentaPorVendedor vsr;

    @Autowired
    ConsultaQryMapper consultaQryMapper;

    GridProvider gdpVentas;

    public VentaPorVendedorPst() {
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
        bindingMgr.bind(vsr.txtGrupoATotal).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtMargenGrupoA).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtTotalPP).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtMargenPP).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtNuPedidos).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtTotalBono).setAsUIReadOnly();
    }

    protected void registerActions() {

        ShowPstAction showPstAction = new ShowPstAction() {
            @Override
            protected Object getTargetBackBean() throws IllegalAccessException, InstantiationException {
                setTargetMode(ViewMode.MODE_INSERT);
                ReporteVendedorItem item = (ReporteVendedorItem) gdpVentas.getSelectedRow();
                ConsultaFlt consultaFlt = new ConsultaFlt();
                consultaFlt.setLocalId(AppCtx.instance().getLocalId());
                consultaFlt.setCoVendedor(item.getNuSecUsuario());
                consultaFlt.setNoVendedor(item.getEmpleado());
                return consultaFlt;
            }
        };
        actionRsr.registerAction("Ver", showPstAction, getGridProvider())
                .needSelectedRow()
                .setKeyTrigger(ActionDialog.KEY_F5)
                .notNeedVisualComponent()
                .setTargetPstClass(FNVentaVendedorPst.class);


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
        List registros = gdpVentas.getValues();
        BigDecimal vaTotal = NumberUtils.nvlZero(ListUtils.sum(registros, "totalVenta", null));
        BigDecimal grupoATotal = ListUtils.sum(registros, "totalGrupoA", null);
        BigDecimal totalPP = ListUtils.sum(registros, "totalPP", null);
        BigDecimal totalPedidos = ListUtils.sum(registros, "totalPedidos", null);
        BigDecimal totalBono = ListUtils.sum(registros, "totalBono", null);
        getBackBean().setVaTotal(vaTotal);
        getBackBean().setGrupoATotal(grupoATotal);
        getBackBean().setTotalPP(totalPP);
        getBackBean().setTotalBono(totalBono);


        if (vaTotal.intValue() != 0) {
            BigDecimal margenGrupoA = grupoATotal.divide(vaTotal, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
            BigDecimal margenPP = totalPP.divide(vaTotal, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
            getBackBean().setMargenGrupoA(margenGrupoA);
            getBackBean().setMargenPP(margenPP);
        } else {
            getBackBean().setMargenGrupoA(BigDecimal.ZERO);
            getBackBean().setMargenPP(BigDecimal.ZERO);
        }
        getBackBean().setNuPedidos(totalPedidos);
        setValuesToJComponent();
    }

    @Override
    protected void registerGridProviders() {
        gdpVentas = gridProviderMgr.registerGridProvider(new IPVentaPorVendedor());
    }

    private class IPVentaPorVendedor extends GridInfoProvider<ReporteVenta> {

        public ColumnInfo[] getColumnInfo() {
            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("No.Empl.", "nuEmpleado", 100, ColumnInfo.LEFT),
                    new ColumnInfo("Vendedor", "empleado", 150, ColumnInfo.LEFT),
                    new ColumnInfo("Tipo", "tiRol", 40, ColumnInfo.RIGHT),
                    new ColumnInfo("Total Venta", "totalVenta", 90, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("Total Bono", "totalBono", 90, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("V. Grupo A", "totalGrupoA", 90, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("%GrupoA", "margenGrupoA", 90, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("Venta PP", "totalPP", 70, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("% PP", "margenPP", 70, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("# Pedidos", "totalPedidos", 70, ColumnInfo.RIGHT).formatAsNumberWithoutGroup(),
                    new ColumnInfo("# Unidades", "totalUnidades", 80, ColumnInfo.RIGHT).formatAsNumberWithoutGroup(),
                    new ColumnInfo("Vale Prom.", "valePromocion", 80, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("Unid.xVale", "unidadesVale", 80, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("Prom.xUnid.", "promedioUnidades", 80, ColumnInfo.RIGHT).formatAsMoney()
            };
            return columns;
        }

        public List<ReporteVendedorItem> getValues(ReporteVenta kardex) {
            ConsultaFlt filtro = new ConsultaFlt();
            filtro.setLocalId(AppCtx.instance().getLocalId());
            filtro.setFeInicio(kardex.getFeInicio());
            filtro.setFeFin(kardex.getFeFin());
            Date fechaActual = DateUtils.truncate(new Date(), Calendar.DATE);

            if (fechaActual.compareTo(filtro.getFeInicio()) >= 0 &&
                    fechaActual.compareTo(filtro.getFeFin()) <= 0) {
                logger.info("calculando ventas del dia de hoy ");
                Map parametros = new HashMap();
                parametros.put("coCompania", AppCtx.instance().getLocalId().getCoCompania());
                parametros.put("coLocal", AppCtx.instance().getLocalId().getCoLocal());
                parametros.put("fecha",new SimpleDateFormat("dd/MM/yyyy").format(fechaActual) );
                consultaQryMapper.calculaVentasPorVendedor(parametros);
            }
            return consultaQryMapper.findVentaPorVendedor(filtro);
        }

    }

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "025"));
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(VentaPorVendedorPst.class);
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
