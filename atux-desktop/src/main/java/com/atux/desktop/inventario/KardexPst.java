package com.atux.desktop.inventario;

import com.atux.bean.kardex.Kardex;
import com.atux.bean.kardex.KardexDetalle;
import com.atux.bean.kardex.KardexFlt;
import com.atux.bean.kardex.Producto;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.atux.desktop.comun.reporte.ExportarFindPstAction;
import com.atux.service.qryMapper.KardexQryMapper;
import com.aw.core.format.DateFormatter;
import com.aw.stereotype.AWPresenter;
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
import org.apache.tools.ant.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Kardex")
public class KardexPst extends AWFormPresenter<Kardex> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private Kardex dmn;
    private FrmKardex vsr;

    @Autowired
    KardexQryMapper kardexQryMapper;

    @Autowired
    APDD apdd;


    public KardexPst() {
        setAutomaticBinding(false);
        setBackBean(new Kardex());
        setShowAuditInfo(false);
    }

    @Override
    protected void afterInitialing() {
        populateLblTitGrid();

    }

    private void populateLblTitGrid() {
        String result="Kardex - #feInicio#  al #feFin#";
        result=StringUtils.replace(result,"#feInicio#", (String)DateFormatter.DATE_FORMATTER.format(getBackBean().getFeInicio()));
        result=StringUtils.replace(result,"#feFin#", (String)DateFormatter.DATE_FORMATTER.format(getBackBean().getFeFin()));
        vsr.lblTitGrid.setText(result);
    }

    protected void registerBinding() {

        bindingMgr.bind(vsr.txtCoProducto, "producto.coProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeProducto, "producto.deProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtUnidadProducto, "producto.unidadProducto").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDeLaboratorio, "producto.deLaboratorio").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtVaFraccion, "producto.vaFraccion").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtStkEntero, "producto.stkEntero").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtStkFraccion, "producto.stkFraccion").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtPrecioPromedio, "producto.precioPromedio").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtPrecioValorizado, "producto.precioValorizado").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtLineaG1, "producto.lineaG1").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtDivisionG2, "producto.divisionG2").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtSubdivisionG3, "producto.subdivisionG3").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtFamiliaG4, "producto.familiaG4").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtSubFamiliaG5, "producto.subFamiliaG5").setUIReadOnly(true);

    }

    private GridProvider gdpKardexDetalle;

    protected void registerGridProviders() {
        gdpKardexDetalle = gridProviderMgr.registerGridProvider(new IPKardexDetalle());

    }

    protected void registerActions() {

        Action showConsultaAction = new ShowPstAction(KardexFlt.class) {

            @Override
            protected Object getTargetBackBean() throws IllegalAccessException, InstantiationException {

                KardexFlt result = new KardexFlt();
                Kardex bk=getBackBean();
                result.setFeInicio(bk.getFeInicio());
                result.setFeFin(bk.getFeFin());
                result.setCoGrupoMotivo(bk.getCoGrupoMotivo());
                result.setCoMotivo(bk.getCoMotivo());
                result.setIdUsuario(bk.getIdUsuario());
                return result;
            }

            @Override
            public Object executeOnReturn(Flow initialFlow, Flow endFlow) {
                KardexFlt kardexFlt = (KardexFlt) endFlow.getAttribute(Flow.BACK_BEAN_NAME);
                getBackBean().setCoGrupoMotivo(kardexFlt.getCoGrupoMotivo());
                getBackBean().setCoMotivo(kardexFlt.getCoMotivo());
                getBackBean().setIdUsuario(kardexFlt.getIdUsuario());
                getBackBean().setFeInicio(kardexFlt.getFeInicio());
                getBackBean().setFeFin(kardexFlt.getFeFin());
                populateLblTitGrid();
                gdpKardexDetalle.refresh(getBackBean());
                return null;
            }
        }.setTargetPstClass(KardexFltPst.class);

        actionRsr.registerAction("consulta", showConsultaAction)
                .setKeyTrigger(ActionDialog.KEY_F3)
                .notNeedVisualComponent();


        actionRsr.registerAction(ActionNames.ACTION_EXPORT_EXCEL, new ExportarFindPstAction(this).xls())
                .setKeyTrigger(ActionDialog.KEY_F7)
                .notNeedVisualComponent();
    }

    @Override
    protected void onWindowsOpened(WindowEvent e) {
        System.out.println("xx");
    }

    @Override
    protected void registerPicks() {


    }

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "025"));
        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(KardexPst.class);
        Kardex bk= new Kardex();
        bk.setFeInicio(new Date());
        bk.setFeFin(new Date());
        Producto producto=new Producto();
        producto.setCoProducto("900317");
        bk.setProducto(producto);
        presenter.setBackBean(bk);
        presenter.init();
    }

    @Override
    protected void afterShowValues() {


    }

    @Override
    public void afterConfiguration() {
        getGridProvider().setAsScrollable();
        getGridProvider().setAsFixedWidth();
        super.afterConfiguration();
    }

    private class IPKardexDetalle extends GridInfoProvider<Kardex> {


        public ColumnInfo[] getColumnInfo() {
            ColumnInfo grupoMotivo = new ColumnInfo("Sistema", "coGrupoMotivo", 100, ColumnInfo.LEFT)
                    .setDropDownFormatter(apdd.KARDEX_GRUPO_MOTIVO)
                    .setAsSorted();
            ColumnInfo motivo = new ColumnInfo("Tipo de Movimiento", "coMotivo", 100, ColumnInfo.LEFT)
                    .setDropDownFormatter(apdd.KARDEX_MOTIVO).dependsOn(grupoMotivo)
                    .setAsSorted();

            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Fech. Mov", "feKardex", 80, ColumnInfo.LEFT).formatAsDate().setAsSorted(),
                    new ColumnInfo("Hor. Mov", "feKardex", 60, ColumnInfo.LEFT).formatAsHour().setAsSorted(),
                    motivo,
                    new ColumnInfo("Stk. Ant", "caInicial", 70, ColumnInfo.RIGHT).setAsSorted(),
                    new ColumnInfo("Stk. Mov", "caMovimiento", 70, ColumnInfo.RIGHT).setAsSorted(),
                    new ColumnInfo("Stk. Act", "caFinal", 70, ColumnInfo.RIGHT).setAsSorted(),
                    new ColumnInfo("Referencia", "nuReferencia", 100, ColumnInfo.LEFT).setAsSorted(),
                    new ColumnInfo("Lote", "lote", 50, ColumnInfo.LEFT).setAsSorted(),
                    new ColumnInfo("Fech. Venc.", "feVencimiento", 100, ColumnInfo.LEFT).setAsSorted(),
                    new ColumnInfo("Documento", "nuDocumento", 100, ColumnInfo.LEFT).setAsSorted(),
                    new ColumnInfo("Fracc.", "vaFraccion", 70, ColumnInfo.RIGHT).setAsSorted(),
                    grupoMotivo,
                    new ColumnInfo("Usuario", "usuario", 80, ColumnInfo.LEFT).setAsSorted(),
                    new ColumnInfo("Costo. Prom.", "costoPromedio", 70, ColumnInfo.RIGHT).setAsSorted()
            };
            return columns;
        }

        public List<KardexDetalle> getValues(Kardex kardex) {
            KardexFlt kardexFlt = new KardexFlt();
            kardexFlt.setLocalId(AppCtx.instance().getLocalId());
            kardexFlt.setCoProducto(kardex.getProducto().getCoProducto());
            kardexFlt.setCoMotivo(kardex.getCoMotivo());
            kardexFlt.setCoGrupoMotivo(kardex.getCoGrupoMotivo());
            kardexFlt.setIdUsuario(kardex.getIdUsuario());
            kardexFlt.setFeInicio(kardex.getFeInicio());
            kardexFlt.setFeFin(kardex.getFeFin());

            List<KardexDetalle> result = kardexQryMapper.findKardexDetalle(kardexFlt);
            Collections.sort(result, new Comparator<KardexDetalle>() {
                public int compare(KardexDetalle o1, KardexDetalle o2) {
                    return o1.getFeKardex().compareTo(o2.getFeKardex());
                }
            });
            return result;
        }

    }


}
