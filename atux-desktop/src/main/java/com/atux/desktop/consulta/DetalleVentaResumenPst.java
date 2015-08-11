package com.atux.desktop.consulta;

import com.atux.bean.consulta.ConsultaFlt;
import com.atux.bean.consulta.ReporteVenta;
import com.atux.bean.consulta.ReporteVentaItem;
import com.atux.comun.context.AppCtx;
import com.atux.service.qryMapper.ConsultaQryMapper;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Resumen")
public class DetalleVentaResumenPst extends AWFormPresenter<ReporteVenta> {
    protected final Log LOG = LogFactory.getLog(getClass());

    private FrmDetalleVentaResumen vsr;

    @Autowired
    ConsultaQryMapper consultaQryMapper;

    GridProvider gdpVentas;

    public DetalleVentaResumenPst() {
        setAutomaticBinding(false);
        setBackBean(new ReporteVenta());
        setShowAuditInfo(false);
    }

    @Override
    protected void onWindowsOpened(WindowEvent e) {
        gdpVentas.requestFocus();
    }

    @Override
    protected void registerGridProviders() {
        gdpVentas=gridProviderMgr.registerGridProvider(new IPDetalleVentaResumen());
    }

    @Override
    protected void registerPicks() {


    }

    private class IPDetalleVentaResumen extends GridInfoProvider<ReporteVenta> {

        public ColumnInfo[] getColumnInfo() {
            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Producto", "productoLabel", 250, ColumnInfo.LEFT),
                    new ColumnInfo("Laboratorio", "deLaboratorio", 150, ColumnInfo.LEFT),
                    new ColumnInfo("Cantidad", "caAtendida", 50, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("Precio", "vaVenta", 50, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("Monto", "vaPrecioVenta", 100, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("Val. P. Prom.", "vaPrecioPromedio", 100, ColumnInfo.RIGHT).formatAsMoney(),
                    new ColumnInfo("Margen", "vaMargen", 100, ColumnInfo.RIGHT).formatAsMoney()
            };
            return columns;
        }

        public List<ReporteVentaItem> getValues(ReporteVenta kardex) {
            ConsultaFlt filtro = new ConsultaFlt();
            filtro.setLocalId(AppCtx.instance().getLocalId());
            filtro.setFeInicio(kardex.getFeInicio());
            filtro.setFeFin(kardex.getFeFin());
            List<ReporteVentaItem> result = consultaQryMapper.findDetalleVentaResumen(filtro);
            completarMargen(result);
            Collections.sort(result, new Comparator<ReporteVentaItem>() {
                public int compare(ReporteVentaItem o1, ReporteVentaItem o2) {
                    return o1.getCoProducto().compareTo(o2.getCoProducto());
                }
            });
            return result;
        }

        private void completarMargen(List<ReporteVentaItem> result) {
            for (ReporteVentaItem item : result) {
                BigDecimal vaMargen=null;
                if(item.getVaPrecioVenta()==null || item.getVaPrecioVenta().compareTo(BigDecimal.ZERO)==0) continue;

                vaMargen=((new BigDecimal(1).subtract(item.getVaPrecioPromedio()
                        .divide(item.getVaPrecioVenta(),2,BigDecimal.ROUND_HALF_UP))).multiply(new BigDecimal(100)))
                        .divide((new BigDecimal(1).add(item.getVaImpuesto().divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP))),2,BigDecimal.ROUND_HALF_UP);
                item.setVaMargen(vaMargen);
            }
        }

    }



    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(DetalleVentaResumenPst.class);
        presenter.init();
    }


}
