package com.atux.desktop.precios;

import com.atux.bean.consulta.ReporteVenta;
import com.atux.bean.kardex.Kardex;
import com.atux.bean.kardex.Producto;
import com.atux.bean.kardex.ProductoFlt;
import com.atux.bean.precios.Laboratorio;
import com.atux.bean.precios.LaboratorioFlt;
import com.atux.bean.precios.ProveedorProducto;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.desktop.consulta.VentaTransaccionPst;
import com.atux.service.qryMapper.LaboratorioQryMapper;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EditAction;
import com.aw.swing.mvp.action.types.ShowPstAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Búsqueda de Laboratorio")
public class FNLaboratorioPst extends FindPresenter<LaboratorioFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());
    protected FrmFNLaboratorio vsr;

    @Autowired
    LaboratorioQryMapper laboratorioQryMapper;


    public FNLaboratorioPst() {
        setBackBean(new LaboratorioFlt());
        setShowAuditInfo(false);
        setSearchAtBeginning(false);
    }

    @Override
    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new ColumnInfo("Código", "coLaboratorio", 30, ColumnInfo.LEFT),
                new ColumnInfo("Descripción", "deLaboratorio", 260, ColumnInfo.LEFT)
        };
        return columns;
    }





    public List getValues(LaboratorioFlt laboratorioFlt) {
        laboratorioFlt.setLocalId(AppCtx.instance().getLocalId());
        return laboratorioQryMapper.findLaboratorio(laboratorioFlt);
    }


    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNLaboratorioPst.class);
        presenter.init();
    }


    @Override
    public void afterConfiguration() {

    }





}
