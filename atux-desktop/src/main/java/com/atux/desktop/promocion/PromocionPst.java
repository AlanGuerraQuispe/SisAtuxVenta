package com.atux.desktop.promocion;


import com.atux.bean.promocion.Promocion;
import com.atux.bean.promocion.PromocionDetalle;
import com.atux.bean.promocion.PromocionLocal;
import com.atux.config.APDD;
import com.atux.dominio.promocion.PromocionService;
import com.atux.service.qryMapper.ProveedorQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Proveedor - Precio")
public class PromocionPst extends Presenter<Promocion> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmPromocion vsr;

    @Autowired
    ProveedorQryMapper proveedorQryMapper;

    @Autowired
    PromocionService promocionService;

    JFileChooser chooser = new JFileChooser();

    GridProvider gdp;
    GridProvider gdpLocal;

    @Autowired
    APDD apdd;

    public PromocionPst() {
        setBackBean(new Promocion());
        setShowAuditInfo(false);

    }

    @Override
    protected void registerBinding() {
        bindingMgr.bind(vsr.txtCoPromocion, "coPromocion").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtNoPromocion, "noPromocion");
        bindingMgr.bind(vsr.txtMensajeCorto, "mensajeCorto");
        bindingMgr.bind(vsr.txtMensajeLargo, "mensajeLargo");
        bindingMgr.bind(vsr.txtObservacion, "observacion");
        bindingMgr.bind(vsr.txtFeIchanicio, "fechaInicio");
        bindingMgr.bind(vsr.txtFechaFin, "fechaFin");
        bindingMgr.bind(vsr.chkEstado, "esPromocion").registerTrueFalse("A", "I");
    }

    @Override
    protected void registerGridProviders() {
        gdp = gridProviderMgr.registerGridProvider(new IPDetalle());
        gdpLocal = gridProviderMgr.registerGridProvider(new IPLocal());
    }


    private class IPDetalle extends GridInfoProvider<Promocion> {

        public ColumnInfo[] getColumnInfo() {
            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Código", "coProducto", 50, ColumnInfo.LEFT),
                    new ColumnInfo("Producto", "deProducto", 80, ColumnInfo.LEFT),
                    new ColumnInfo("Cant. Ent.", "caProducto", 30, ColumnInfo.RIGHT),
                    new ColumnInfo("Cant. Frac.", "vaFraccion", 30, ColumnInfo.RIGHT),
                    new ColumnInfo("Prom. Código", "coProductoP", 50, ColumnInfo.LEFT),
                    new ColumnInfo("Prom. Producto", "deProductoP", 80, ColumnInfo.LEFT),
                    new ColumnInfo("Prom. Cant. Ent.", "caProductoP", 30, ColumnInfo.RIGHT),
                    new ColumnInfo("Prom. Cant Frac.", "vaFraccionP", 30, ColumnInfo.RIGHT),
                    new ColumnInfo("Estado", "esProductoPlan", 80, ColumnInfo.LEFT).setDropDownFormatter(apdd.ES_TABLA),

            };
            return columns;
        }

        public List<PromocionDetalle> getValues(Promocion precioLista) {
            return precioLista.getDetalle();
        }
    }

    private class IPLocal extends GridInfoProvider<Promocion> {

        public ColumnInfo[] getColumnInfo() {
            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Código", "coLocal", 50, ColumnInfo.LEFT),
                    new ColumnInfo("Local", "deLocal", 80, ColumnInfo.LEFT)
            };
            return columns;
        }

        public List<PromocionLocal> getValues(Promocion precioLista) {
            return precioLista.getDetalleLocal();
        }
    }

    @Override
    protected void afterInitComponents() {

    }

    protected void registerActions() {


    }


}
