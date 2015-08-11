package com.atux.desktop.central;

import com.atux.bean.central.Fraccionamiento;
import com.atux.bean.central.FraccionamientoLocal;
import com.atux.bean.central.Unidad;
import com.atux.bean.consulta.UnidadFlt;
import com.atux.bean.kardex.Kardex;
import com.atux.bean.kardex.Producto;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.atux.desktop.comun.picks.PickUnidadPst;
import com.atux.desktop.comun.picks.PickUsuariosPst;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.aw.core.domain.AWBusinessException;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.AWFormPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EmptyAction;
import com.aw.swing.mvp.binding.component.BndIJTextField;
import com.aw.swing.mvp.binding.component.support.BndFocusAdapter;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.SelectorColumn;
import com.aw.swing.mvp.cmp.pick.Pick;
import com.aw.swing.mvp.cmp.pick.PickImpl;
import com.aw.swing.mvp.cmp.pick.PickListener;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Ingresar o Modificar Fracción")
public class FraccionamientoCrudPst extends AWFormPresenter<FraccionamientoLocal> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FraccionamientoLocal dmn;
    private FrmFraccionamientoCrud vsr;

    @Autowired
    ProductoQryMapper productoQryMapper;

    @Autowired
    APDD apdd;


    public FraccionamientoCrudPst() {
        setAutomaticBinding(false);
        setBackBean(new FraccionamientoLocal());
        setShowAuditInfo(false);
    }

    @Override
    protected void afterInitialing() {
    }

    @Override
    public void onWindowsOpenedInternalOnlyForAWFW(WindowEvent e) {
        vsr.chkActivarFraccion.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(vsr.chkActivarFraccion.isSelected()){
                    vaFraccionNuevo.setUIReadOnly(false);
                    vsr.btnPickMinFraccion.setEnabled(true);
                    vsr.btnPickMaxFraccion.setEnabled(true);
                }else{
                    vaFraccionNuevo.setUIReadOnly(true);
                    vsr.btnPickMinFraccion.setEnabled(false);
                    vsr.btnPickMaxFraccion.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void onWindowsOpened(WindowEvent e) {
        vsr.txtVaFraccionNuevo.addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent e) {
                vaFraccionNuevo.setValueToBean();
                if(StringUtils.isBlank(getBackBean().getMaxFraccion())) return;
                Unidad unidadBean = productoQryMapper.findUnidad(new UnidadFlt(getBackBean().getMaxFraccion())).get(0);
                getBackBean().buildUnidadFraccionNueva(unidadBean.getDeUnidadCorta());
                unidadFraccionNuevo.setValueToJComponent();
            }
        });
    }

    private BndIJTextField unidadFraccionNuevo;
    private BndIJTextField vaFraccionNuevo;

    protected void registerBinding() {

        bindingMgr.bind(vsr.txtCoProducto, "producto.coProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeProducto, "producto.deProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtLocal, "local").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeLaboratorio, "producto.deLaboratorio").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtUnidad, "unidadFraccion").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtVaFraccionActual, "vaFraccion").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtMinFraccion, "minFraccionLabel").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtMaxFraccion, "maxFraccionLabel").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtFeVigencia, "feVigencia");
        bindingMgr.bind(vsr.chkInFraccionMatriz, "producto.inProdFraccionado").registerTrueFalse("S", "N").setUIReadOnly(true);
        bindingMgr.bind(vsr.chkFraccion, "inProdFraccionadoLocal").registerTrueFalse("S", "N").setUIReadOnly(true);
        bindingMgr.bind(vsr.chkActivarFraccion, "inActivarFraccion").registerTrueFalse("S", "N");
        bindingMgr.bind(vsr.chkActivo, "inActivo").registerTrueFalse("S", "N");

        vaFraccionNuevo=bindingMgr.bind(vsr.txtVaFraccionNuevo, "vaFraccionNuevo");
        unidadFraccionNuevo = bindingMgr.bind(vsr.txtUnidadFraccionNuevo, "unidadFraccionNuevo");
        unidadFraccionNuevo.setUIReadOnly(true);
   }



    protected void registerActions() {


        actionRsr.registerAction("Guardar",new EmptyAction(){
            @Override
            protected Object executeIntern() throws Throwable {
               getBackBean().validarNuevaFraccion();

                return super.executeIntern();
            }
        })
                .notNeedVisualComponent().setKeyTrigger(ActionDialog.KEY_F10).closeViewAtEnd();


    }


    @Override
    protected void registerPicks() {

        Pick pickMinUnidad = new PickImpl(PickUnidadPst.class);
        pickMinUnidad.registerBind("minFraccionLabel", "deUnidad");
        pickMinUnidad.registerBind("minFraccion", "coUnidad");
        pickMinUnidad.registerParam("minFraccion", "unidad");
        pickMinUnidad.addListener(new PickListener() {
            public Map valuesSet(Object selectedObj, Map valuesSet) {
                if (StringUtils.isNotBlank(getBackBean().getMaxFraccion())) {
                    Unidad unidadBean = productoQryMapper.findUnidad(new UnidadFlt(getBackBean().getMaxFraccion())).get(0);
                    getBackBean().buildUnidadFraccionNueva(unidadBean.getDeUnidadCorta());
                    unidadFraccionNuevo.setValueToJComponent();
                }
                return valuesSet;
            }
        });
        pickMgr.registerPick("minFraccion", pickMinUnidad);

        Pick pickMaxUnidad = new PickImpl(PickUnidadPst.class);
        pickMaxUnidad.registerBind("maxFraccion", "coUnidad");
        pickMaxUnidad.registerBind("maxFraccionLabel", "deUnidad");
        pickMaxUnidad.registerParam("maxFraccion", "unidad");
        pickMaxUnidad.addListener(new PickListener() {
            public Map valuesSet(Object selectedObj, Map valuesSet) {
                Unidad unidad = (Unidad) selectedObj;
                Unidad unidadBean = productoQryMapper.findUnidad(new UnidadFlt(unidad.getCoUnidad())).get(0);
                getBackBean().buildUnidadFraccionNueva(unidadBean.getDeUnidadCorta());
                unidadFraccionNuevo.setValueToJComponent();
                return valuesSet;
            }
        });
        pickMgr.registerPick("maxFraccion", pickMaxUnidad);

    }

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "025"));
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FraccionamientoCrudPst.class);
        presenter.init();
    }

    @Override
    protected void afterShowValues() {


    }


}
