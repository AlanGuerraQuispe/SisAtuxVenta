package com.atux.desktop.consulta;

import com.atux.bean.consulta.ConsultaFlt;
import com.atux.bean.consulta.ReporteVenta;
import com.atux.bean.consulta.ReporteVentaItem;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.atux.desktop.comun.reporte.ExportarFindPstAction;
import com.atux.service.qryMapper.ConsultaQryMapper;
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
import com.aw.swing.mvp.grid.CellColorChanger;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Venta x Transacción")
public class VentaTransaccionPst extends AWFormPresenter<ReporteVenta> {
    protected final Log LOG = LogFactory.getLog(getClass());

    private FrmVentaTransaccion vsr;

    @Autowired
    ConsultaQryMapper consultaQryMapper;

    @Autowired
    APDD apdd;

    GridProvider gdpVentas;

    public VentaTransaccionPst() {
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
        bindingMgr.bind(vsr.txtVaPrecioPromedio).setAsUIReadOnly();
        bindingMgr.bind(vsr.txtVaTotal).setAsUIReadOnly();
    }

    protected void registerActions() {
        Action showConsultaAction = new ShowPstAction() {
            protected ReporteVenta getTargetBackBean() throws IllegalAccessException, InstantiationException {
                ReporteVenta reporteVenta = new ReporteVenta();
                reporteVenta.setFeInicio(getBackBean().getFeInicio());
                reporteVenta.setFeFin(getBackBean().getFeFin());
                return reporteVenta;
            }
        }.setTargetPstClass(VentaTransaccionResumenPst.class);
        actionRsr.registerAction("VerResumen", showConsultaAction)
                .setKeyTrigger(ActionDialog.KEY_F5)
                .notNeedVisualComponent();

        actionRsr.registerAction(ActionNames.ACTION_EXPORT_EXCEL, new ExportarFindPstAction(this).xls())
                .setKeyTrigger(ActionDialog.KEY_F7)
                .notNeedVisualComponent();
    }


    protected void registerValidations() {
        //validatorMgr.registerValidator("");
    }

    @Override
    protected void registerGridProviders() {
      gdpVentas=  gridProviderMgr.registerGridProvider(new IPVentaTransaccion()).registerCellColorChanger(new CellColorChanger() {
          @Override
          public Color getForeGround(Object rowObj, int row, String colFieldName) {

              if("N".equals(((ReporteVentaItem)rowObj).getEsPedidoVenta())){
                  return Color.RED;
              }
              return Color.BLACK;
          }
      });
    }

    @Override
    protected void registerPicks() {


    }

    @Override
    protected void afterShowValues() {
        calcularTotales();
    }

    private void calcularTotales() {
        List registros= gdpVentas.getValues();
        BigDecimal totalPrecioVenta= ListUtils.sum(registros, "vaPrecioVenta", null);
        BigDecimal  totalPrecioPromedio=ListUtils.sum(registros, "vaPrecioPromedio", null);
        getBackBean().setVaPrecioPromedio(totalPrecioPromedio);
        getBackBean().setVaTotal(totalPrecioVenta);
        setValuesToJComponent();
    }

    private class IPVentaTransaccion extends GridInfoProvider<ReporteVenta> {

        public ColumnInfo[] getColumnInfo() {
            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Correlativo", "nuPedido", 80, ColumnInfo.LEFT),
                    new ColumnInfo("#SUNAT", "nuComprobante", 90, ColumnInfo.LEFT),
                    new ColumnInfo("Fecha", "fePedido", 70, ColumnInfo.RIGHT).formatAsDate(),
                    new ColumnInfo("Cliente", "noCliente", 300, ColumnInfo.LEFT),
                    new ColumnInfo("Td", "tiComprobante", 100, ColumnInfo.LEFT).setDropDownFormatter(apdd.COMPROBANTE_TIPO),
                    new ColumnInfo("Imp", "imp", 50, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("Vend", "noVendedor", 80, ColumnInfo.LEFT),
                    new ColumnInfo("Monto", "vaPrecioVenta", 80, ColumnInfo.RIGHT).formatAsMoney()
            };
            return columns;
        }

        public List<ReporteVentaItem> getValues(ReporteVenta kardex) {
            ConsultaFlt filtro = new ConsultaFlt();
            filtro.setLocalId(AppCtx.instance().getLocalId());
            filtro.setFeInicio(kardex.getFeInicio());
            filtro.setFeFin(kardex.getFeFin());
            List<ReporteVentaItem> result = consultaQryMapper.findVentaTransaccion(filtro);
            Collections.sort(result, new Comparator<ReporteVentaItem>() {
                public int compare(ReporteVentaItem o1, ReporteVentaItem o2) {
                    return o1.getNuPedido().compareTo(o2.getNuPedido());
                }
            });
            return result;
        }

    }

    public static void main(String[] args) {
        new LookAndFeelManager().initialize();

        Application.instance().init();
        AppCtx.instance().setLocalId(new LocalId("001", "025"));
        Presenter presenter = PstMgr.instance().getPst(VentaTransaccionPst.class);
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
