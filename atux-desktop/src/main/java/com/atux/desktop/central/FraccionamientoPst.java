package com.atux.desktop.central;

import com.atux.bean.central.Fraccionamiento;
import com.atux.bean.central.FraccionamientoLocal;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.atux.dominio.precios.ProveedorService;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.aw.core.view.ViewMode;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.ShowPstAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.SelectorColumn;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.navigation.Flow;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Fraccionamiento")
public class FraccionamientoPst extends AWFormPresenter<Fraccionamiento> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private Fraccionamiento dmn;
    private FrmFraccionamiento vsr;

    @Autowired
    ProveedorService proveedorService;

    @Autowired
    ProductoQryMapper productoQryMapper;

    @Autowired
    APDD apdd;


    public FraccionamientoPst() {
        setAutomaticBinding(false);
        setBackBean(new Fraccionamiento());
        setShowAuditInfo(false);
    }

    @Override
    protected void afterInitialing() {
    }


    protected void registerBinding() {

        bindingMgr.bind(vsr.txtCoProducto, "producto.coProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeProducto, "producto.deProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtUnidadProducto, "producto.unidadProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeLaboratorio, "producto.deLaboratorio").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtCaEmpaqueMin, "producto.caEmpaqueMin").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtMinFraccion, "producto.minFraccion").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtCaEmpaqueMax, "producto.caEmpaqueMax").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtMaxFraccion, "producto.maxFraccion").setUIReadOnly(true);

    }

    private GridProvider gdpFraccionamientoDetalle;

    protected void registerGridProviders() {
        gdpFraccionamientoDetalle = gridProviderMgr.registerGridProvider(new IPFraccionamientoDetalle());

    }

    protected void registerActions() {

        ShowPstAction editAction = new ShowPstAction() {
            @Override
            protected Object getTargetBackBean() throws IllegalAccessException, InstantiationException {
                setTargetMode(ViewMode.MODE_INSERT);
                FraccionamientoLocal result = new FraccionamientoLocal();

                List itemList = gdpFraccionamientoDetalle.getSelectedRows();
                FraccionamientoLocal select = null;
                if (itemList.size() == 1) {
                    select = (FraccionamientoLocal) itemList.get(0);
                } else if (itemList.size() == 0) {
                    if (gdpFraccionamientoDetalle.getSelectedRow() != null) {
                        select = (FraccionamientoLocal) gdpFraccionamientoDetalle.getSelectedRow();
                    }
                }
                if (select != null) {
                    result.setCoLocal(select.getCoLocal());
                    result.setDeLocal(select.getDeLocal());
                    result.setCoCompania(select.getCoCompania());
                    result.setVaFraccion(select.getVaFraccion());
                    result.setUnidadFraccion(select.getUnidadFraccion());
                    result.setInProdFraccionadoLocal(select.getInProdFraccionadoLocal());
                }
                result.setProducto(getBackBean().getProducto());
                return result;
            }

            @Override
            public Object executeOnReturn(Flow initialFlow, Flow endFlow) {
                FraccionamientoLocal fraccionamientoLocal = (FraccionamientoLocal) endFlow.getAttribute(Flow.BACK_BEAN_NAME);

                List itemList = gdpFraccionamientoDetalle.getSelectedRows();
                FraccionamientoLocal select = null;
                if (itemList.size() == 1) {
                    select = (FraccionamientoLocal) itemList.get(0);
                } else if (itemList.size() == 0) {
                    if (gdpFraccionamientoDetalle.getSelectedRow() != null) {
                        select = (FraccionamientoLocal) gdpFraccionamientoDetalle.getSelectedRow();
                    }
                }else{
                    for (Object anItemList : itemList) {
                        FraccionamientoLocal local = (FraccionamientoLocal) anItemList;
                        local.setInProdFraccionadoNuevo(fraccionamientoLocal.getInActivarFraccion());
                        local.setVaFraccionNuevo(fraccionamientoLocal.getVaFraccionNuevo());
                        local.setUnidadFraccionNuevo(fraccionamientoLocal.getUnidadFraccionNuevo());
                        local.setInActivo(fraccionamientoLocal.getInActivo());
                        local.setEsFraccion("A");
                        local.setFeVigencia(fraccionamientoLocal.getFeVigencia());
                    }
                }
                if(select!=null){
                    select.setInProdFraccionadoNuevo(fraccionamientoLocal.getInActivarFraccion());
                    select.setVaFraccionNuevo(fraccionamientoLocal.getVaFraccionNuevo());
                    select.setUnidadFraccionNuevo(fraccionamientoLocal.getUnidadFraccionNuevo());
                    select.setInActivo(fraccionamientoLocal.getInActivo());
                    select.setEsFraccion("A");
                    select.setFeVigencia(fraccionamientoLocal.getFeVigencia());
                }
                gdpFraccionamientoDetalle.repaint();
                return null;
            }
        };
        actionRsr.registerAction("Fraccionar", editAction, getGridProvider())
                .setKeyTrigger(ActionDialog.KEY_F2)
                .notNeedVisualComponent()
                .setTargetPstClass(FraccionamientoCrudPst.class);

        actionRsr.registerAction("Guardar", new Action(){
            @Override
            protected Object executeIntern() throws Throwable {
                proveedorService.grabarFraccion(getBackBean());
                return null;
            }
        }, getGridProvider())
                .closeViewAtEnd()
                .setKeyTrigger(ActionDialog.KEY_F10)
                .notNeedVisualComponent()
                .setTargetPstClass(FraccionamientoCrudPst.class).setConfirmMsg("¿Desea confirmar el nuevo fraccionamiento?");

   }


    @Override
    protected void registerPicks() {


    }

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "025"));
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FraccionamientoPst.class);
        presenter.init();
    }

    @Override
    protected void afterShowValues() {


    }


    private class IPFraccionamientoDetalle extends GridInfoProvider<Fraccionamiento> {

        public ColumnInfo[] getColumnInfo() {

            ColumnInfo[] columns = new ColumnInfo[]{
                    new SelectorColumn(),
                    new ColumnInfo("CIA", "coCompania", 80, ColumnInfo.LEFT)
                            .setAsSorted()
                            .setBackgroundColor(new Color(79, 129, 189), Color.black),
                    new ColumnInfo("Local", "local", 150, ColumnInfo.LEFT).setAsSorted()
                            .setBackgroundColor(new Color(79, 129, 189), Color.black),
                    new ColumnInfo("Stk", "stkDisponibleFmt", 80, ColumnInfo.LEFT).setAsSorted()
                            .setBackgroundColor(new Color(79, 129, 189), Color.black),
                    new ColumnInfo("Fracción", "inProdFraccionadoLocal", 30, ColumnInfo.LEFT).setAsSorted()
                            .setBackgroundColor(new Color(79, 129, 189), Color.black),
                    new ColumnInfo("Valor Fración", "vaFraccion", 40, ColumnInfo.LEFT).setAsSorted()
                            .setBackgroundColor(new Color(79, 129, 189), Color.black),
                    new ColumnInfo("Unidad Fración", "unidadFraccion", 80, ColumnInfo.LEFT).setAsSorted()
                            .setBackgroundColor(new Color(79, 129, 189), Color.black),

                    new ColumnInfo("Fracción", "inProdFraccionadoNuevo", 30, ColumnInfo.LEFT).setAsSorted(),
                    new ColumnInfo("Valor Fración", "vaFraccionNuevo", 40, ColumnInfo.LEFT).setAsSorted(),
                    new ColumnInfo("Unidad Fración", "unidadFraccionNuevo", 80, ColumnInfo.LEFT).setAsSorted(),

                    new ColumnInfo("Activo", "inActivo", 70, ColumnInfo.RIGHT).setAsSorted(),
                    new ColumnInfo("Vigencia", "feVigencia", 70, ColumnInfo.RIGHT).formatAsDate().setAsSorted()

            };
            return columns;
        }

        public List<FraccionamientoLocal> getValues(Fraccionamiento fraccionamiento) {
            return fraccionamiento.getDetalleList();
        }

    }


}
