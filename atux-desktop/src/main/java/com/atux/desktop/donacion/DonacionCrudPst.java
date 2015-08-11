package com.atux.desktop.donacion;


import atux.handlers.DonacionService;
import com.atux.bean.donacion.Donacion;
import com.atux.bean.donacion.DonacionDetalle;
import com.atux.config.APDD;
import com.atux.service.qryMapper.DonacionQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Donación")
public class DonacionCrudPst extends Presenter<Donacion> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmDonacion vsr;

    @Autowired
    DonacionQryMapper donacionQryMapper;

    @Autowired
    DonacionService donacionService;

    GridProvider gdpLocal;

    @Autowired
    APDD apdd;

    public DonacionCrudPst() {
        setBackBean(new Donacion());
        setShowAuditInfo(false);

    }

    @Override
    protected void registerBinding() {
        bindingMgr.bind(vsr.txtCoInstitucion, "coInstitucion").setUIReadOnly(true);
        bindingMgr.bind(vsr.chkEstado, "esInstitucion").registerTrueFalse("A", "I");
    }

    @Override
    protected void registerGridProviders() {
        gdpLocal = gridProviderMgr.registerGridProvider(new IPLocal());
    }


    private class IPLocal extends GridInfoProvider<Donacion> {

        public ColumnInfo[] getColumnInfo() {
            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Código", "coLocal", 50, ColumnInfo.LEFT),
                    new ColumnInfo("Local", "deLocal", 150, ColumnInfo.LEFT),
                    new ColumnInfo("Fecha Inicio", "fechaInicio", 80, ColumnInfo.LEFT).formatAsDate(),
                    new ColumnInfo("Fecha Fin", "fechaFin", 80, ColumnInfo.LEFT).formatAsDate(),
                    new ColumnInfo("Estado", "esCobertura", 40, ColumnInfo.LEFT).setDropDownFormatter(apdd.ES_TABLA)
            };
            return columns;
        }

        public List<DonacionDetalle> getValues(Donacion donacion) {
            return donacion.getDetalle();
        }
    }

    @Override
    protected void afterInitComponents() {

    }

    protected void registerActions() {



    }


}
