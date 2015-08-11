package com.atux.desktop.consulta;

import com.atux.bean.consulta.ConsultaFlt;
import com.atux.bean.consulta.ReporteVenta;
import com.atux.bean.consulta.ReporteVentaItem;
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
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.ActionNames;
import com.aw.swing.mvp.action.types.ShowPstAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.navigation.Flow;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Detalle de Venta")
public class DetalleVentaPst extends AWFormPresenter<ReporteVenta> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmDetalleVenta vsr;

    @Autowired
    ConsultaQryMapper consultaQryMapper;

    GridProvider gdpVentas;


    public DetalleVentaPst() {
        setAutomaticBinding(true);
        setBackBean(new ReporteVenta());
        setShowAuditInfo(false);
    }

    protected void registerBinding() {
        bindingMgr.bind(vsr.txtFeInicio).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtFeFin).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtVaPrecioPromedio).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtVaTotal).setAsUIReadOnly();
//        bindingMgr.bind(vsr.txtPorcentajeMargen).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtPorcentajeUtilidad).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtVaUtilidad).formatAsMoney().setAsUIReadOnly();
    }

    @Override
    protected void onWindowsOpened(WindowEvent e) {
        gdpVentas.requestFocus();
    }

    protected void registerActions() {
        Action showConsultaAction = new ShowPstAction() {
            protected ReporteVenta getTargetBackBean() throws IllegalAccessException, InstantiationException {
                return getBackBean();
            }
        }.setTargetPstClass(DetalleVentaResumenPst.class);
        actionRsr.registerAction("VerResumen", showConsultaAction)
                .setKeyTrigger(ActionDialog.KEY_F5)
                .notNeedVisualComponent();

        Action showFiltroAction = new ShowPstAction() {
            @Override
            public Object executeOnReturn(Flow initialFlow, Flow endFlow) {
                gdpVentas.refresh(getBackBean());
                return null;
            }

            protected ReporteVenta getTargetBackBean() throws IllegalAccessException, InstantiationException {
                setTargetMode(ViewMode.MODE_INSERT);
                return getBackBean();
            }

        }.setTargetPstClass(TipoDocumentoFltPst.class);
        actionRsr.registerAction("Filtro", showFiltroAction)
                .setKeyTrigger(ActionDialog.KEY_F6)
                .notNeedVisualComponent().refreshGridAtEnd();

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

        BigDecimal totalPrecioVenta = ListUtils.sum(registros, "vaPrecioVenta", null);

        BigDecimal totalPrecioVentaSinIGV = ListUtils.sum(registros, "vaPrecioVentaSinIgv", null);

        BigDecimal totalPrecioPromedio = BigDecimal.ZERO;
        for (Object next : registros) {
            ReporteVentaItem item = (ReporteVentaItem) next;
            totalPrecioPromedio = totalPrecioPromedio.add(NumberUtils.nvlZero(item.getVaPrecioPromedio())
                    .multiply(NumberUtils.nvlZero(item.getCaAtendida())));
        }


        BigDecimal utilidad = NumberUtils.nvlZero(totalPrecioVentaSinIGV).subtract(NumberUtils.nvlZero(totalPrecioPromedio));
        BigDecimal porcentajeUtilidad = BigDecimal.ZERO;
        if (NumberUtils.nvlZero(totalPrecioVentaSinIGV).intValue() != 0) {
            porcentajeUtilidad = NumberUtils.nvlZero(utilidad).divide(NumberUtils.nvlZero(totalPrecioVentaSinIGV), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
        }
        BigDecimal porcentajeMargen=BigDecimal.ZERO;
        if (NumberUtils.nvlZero(totalPrecioVentaSinIGV).intValue() != 0) {
            porcentajeMargen = NumberUtils.nvlZero(totalPrecioPromedio).divide(NumberUtils.nvlZero(totalPrecioVentaSinIGV), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
        }
        getBackBean().setPorcentajeMargen(porcentajeMargen);
        getBackBean().setPorcentajeUtilidad(porcentajeUtilidad);
        getBackBean().setVaTotal(totalPrecioVenta);
        getBackBean().setVaPrecioPromedio(totalPrecioPromedio);
        getBackBean().setVaUtilidad(utilidad);

        setValuesToJComponent();
    }

    @Override
    protected void registerGridProviders() {
        gdpVentas = gridProviderMgr.registerGridProvider(new IPDetalleVenta());
    }

    private class IPDetalleVenta extends GridInfoProvider<ReporteVenta> {

        public ColumnInfo[] getColumnInfo() {
            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Correlativo", "nuPedido", 80, ColumnInfo.LEFT),
                    new ColumnInfo("Emit.", "emitido", 20, ColumnInfo.LEFT),
                    new ColumnInfo("#SUNAT", "nuComprobanteCustom", 90, ColumnInfo.LEFT),
                    new ColumnInfo("Fecha", "fePedido", 70, ColumnInfo.RIGHT).formatAsDate(),
                    new ColumnInfo("Producto", "productoLabel", 200, ColumnInfo.LEFT),
                    new ColumnInfo("Laboratorio", "deLaboratorio", 160, ColumnInfo.LEFT),
                    new ColumnInfo("Cant.", "caAtendida", 30, ColumnInfo.RIGHT).formatAsNumberWithGroup(),
                    new ColumnInfo("Precio", "vaVenta", 50, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("%dscto", "vaDescuento", 50, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("Monto", "vaPrecioVenta", 80, ColumnInfo.RIGHT).formatAsMoney()
            };
            return columns;
        }

        public List<ReporteVentaItem> getValues(ReporteVenta kardex) {
            ConsultaFlt filtro = new ConsultaFlt();
            filtro.setLocalId(AppCtx.instance().getLocalId());
            filtro.setFeInicio(kardex.getFeInicio());
            filtro.setFeFin(kardex.getFeFin());
            filtro.setTipoDocumento(kardex.getTipoDocumento());
            List<ReporteVentaItem> resultado = consultaQryMapper.findDetalleVenta(filtro);
            Collections.sort(resultado, new Comparator<ReporteVentaItem>() {
                public int compare(ReporteVentaItem o1, ReporteVentaItem o2) {
                    return o1.getNuPedido().compareTo(o2.getNuPedido());
                }
            });
            return resultado;
        }

    }

    public static void main(String[] args) {
        new LookAndFeelManager().initialize();

        Application.instance().init();
        AppCtx.instance().setLocalId(new LocalId("001", "025"));
        Presenter presenter = PstMgr.instance().getPst(DetalleVentaPst.class);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ReporteVenta bk = new ReporteVenta();
        try {
            bk.setFeInicio(sdf.parse("09/11/2004"));
            bk.setFeFin(sdf.parse("09/11/2004"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        presenter.setBackBean(bk);
        presenter.init();
    }


}
