package com.atux.desktop.consulta;

import com.atux.bean.consulta.ConsultaFlt;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.atux.desktop.comun.reporte.ExportarFindPstAction;
import com.atux.service.qryMapper.ConsultaQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.ActionNames;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Ventas por  Vendedor")
public class FNVentaVendedorPst extends FindPresenter<ConsultaFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmFNVentaVendedor vsr;

    @Autowired
    APDD apdd;

    @Autowired
    ConsultaQryMapper consultaQryMapper;

    public FNVentaVendedorPst() {
        setSearchAtBeginning(false);
        setAutomaticBinding(true);
        setBackBean(new ConsultaFlt());
        setShowAuditInfo(false);
    }

    @Override
    protected void registerBinding() {
        bindingMgr.bind(vsr.txtVendedor).setUIReadOnly(true);
    }

    @Override
    public ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new ColumnInfo("Correlativo", "nuPedido", 80, ColumnInfo.LEFT),
                new ColumnInfo("Código", "coProducto", 90, ColumnInfo.LEFT),
                new ColumnInfo("Descripción", "deProducto", 90, ColumnInfo.LEFT),
                new ColumnInfo("Unidad", "unidadProducto", 90, ColumnInfo.LEFT),
                new ColumnInfo("Cantidad", "caAtendida", 90, ColumnInfo.RIGHT).formatAsNumberWithGroup(),
                new ColumnInfo("Venta", "vaPrecioVenta", 90, ColumnInfo.RIGHT).formatAsMoney(),
                new ColumnInfo("Clase", "vaBono", 90, ColumnInfo.RIGHT).formatAsMoney()
        };
        return columns;
    }

    @Override
    protected void afterInitComponents() {

    }

    public List getValues(ConsultaFlt consultaFlt) {
        consultaFlt.setLocalId(AppCtx.instance().getLocalId());
        return consultaQryMapper.findDetalleVentaPorVendedor(consultaFlt);
    }


    protected void registerActions() {
        actionRsr.registerAction(ActionNames.ACTION_EXPORT_EXCEL, new ExportarFindPstAction(this).xls())
                .setKeyTrigger(ActionDialog.KEY_F7)
                .notNeedVisualComponent();

    }

    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNVentaVendedorPst.class);
        presenter.init();
    }


    @Override
    public void afterConfiguration() {
        super.afterConfiguration();

    }


}
