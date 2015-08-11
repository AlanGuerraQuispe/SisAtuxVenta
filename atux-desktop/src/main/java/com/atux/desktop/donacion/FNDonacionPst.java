package com.atux.desktop.donacion;

import atux.handlers.DonacionService;
import com.atux.bean.donacion.Donacion;
import com.atux.bean.donacion.DonacionDetalle;
import com.atux.bean.donacion.DonacionFlt;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.atux.service.qryMapper.DonacionQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.ViewAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.GridInfoProvider;
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
@AWPresenter(title = "Donaciones")
public class FNDonacionPst extends FindPresenter<DonacionFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmFNDonacion vsr;

    @Autowired
    DonacionService donacionService;

    @Autowired
    DonacionQryMapper donacionQryMapper;

    GridProvider gdpDetalleLista;

    @Autowired
    APDD apdd;

    public FNDonacionPst() {
        setBackBean(new DonacionFlt());
        setShowAuditInfo(false);
        setSearchAtBeginning(true);
    }

    @Override
    protected void registerBinding() {
        bindingMgr.bind(vsr.txtDeInstitución, "deInstitucion");
    }

    @Override
    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new ColumnInfo("Código", "coInstitucion", 45, ColumnInfo.LEFT),
                new ColumnInfo("RUC", "nuRucInstitucion", 60, ColumnInfo.LEFT),
                new ColumnInfo("Institución", "deInstitucion", 300, ColumnInfo.LEFT),
                new ColumnInfo("Dirección", "deDireccion", 150, ColumnInfo.LEFT),
                new ColumnInfo("Teléfono", "nuTelReferencia", 60, ColumnInfo.LEFT),
                new ColumnInfo("Estado", "esInstitucion", 60, ColumnInfo.LEFT).setDropDownFormatter(apdd.ES_TABLA)


        };
        return columns;
    }

    public List getValues(DonacionFlt filtro) {
        filtro.setLocalId(AppCtx.instance().getLocalId());
        return donacionQryMapper.findInstitucionList(filtro);
    }


    protected void registerGridProviders() {
        gdpDetalleLista = gridProviderMgr.registerGridProvider(new IPInstitucionDetalle())
                .dependsOn(getGridProvider());
    }

    protected void registerActions() {

        ViewAction viewAction=new ViewAction(){

            protected Object getObjectToBeViewed(Object obj) {
                Donacion donacion= null;
                if (getGridProvider().getSelectedRow() != null) {
                    donacion = (Donacion) getGridProvider().getSelectedRow();
                    DonacionFlt donacionFlt=new DonacionFlt();
                    donacionFlt.setLocalId(AppCtx.instance().getLocalId());
                    donacionFlt.setCoInstitucion(donacion.getCoInstitucion());
                    donacion.setDetalle(donacionQryMapper.findDonacionDetalleList(donacionFlt));
                }
                return donacion;
            }
        }
                ;

        actionRsr.registerAction("Editar",viewAction , getGridProvider()).notNeedVisualComponent()
                .needSelectedRow()
                .refreshGridAtEnd()
                .setKeyTrigger(ActionDialog.KEY_F5)
                .setTargetPstClass(DonacionCrudPst.class);


    }


    private class IPInstitucionDetalle extends GridInfoProvider<Donacion> {

        public ColumnInfo[] getColumnInfo() {
            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Código", "coLocal", 40, ColumnInfo.LEFT),
                    new ColumnInfo("Local", "deLocal", 200, ColumnInfo.LEFT),
                    new ColumnInfo("Fecha Inicio", "fechaInicio", 45, ColumnInfo.LEFT).formatAsDate(),
                    new ColumnInfo("Fecha Fin", "fechaFin", 45, ColumnInfo.LEFT).formatAsDate(),
                    new ColumnInfo("Estado", "esCobertura", 40, ColumnInfo.LEFT).setDropDownFormatter(apdd.ES_TABLA),
                    new ColumnInfo("Creado Por", "idCreaCoberturaConvenio", 40, ColumnInfo.LEFT),
                    new ColumnInfo("Fecha Creación", "feCreaCoberturaConvenio", 45, ColumnInfo.LEFT).formatAsDate()
            };
            return columns;
        }

        public List<DonacionDetalle> getValues(Donacion donacion) {
            DonacionFlt filtro= new DonacionFlt();
            filtro.setLocalId(AppCtx.instance().getLocalId());
            filtro.setCoInstitucion(donacion.getCoInstitucion());
            return donacionQryMapper.findDonacionDetalleList(filtro);

        }

    }

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "005"));


        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNDonacionPst.class);
        DonacionFlt productoFlt = new DonacionFlt();
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        presenter.setBackBean(productoFlt);
        presenter.init();
    }

}
