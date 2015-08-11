package atux.presenter;


import com.atux.bean.donacion.*;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.atux.service.qryMapper.DonacionQryMapper;
import com.aw.core.domain.AWBusinessException;
import com.aw.stereotype.AWPresenter;
import com.aw.support.collection.ListUtils;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.EmptyAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Institución - Donación")
public class IngresarDonacionPst extends Presenter<IngresarDonacion> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmIngresarDonacion vsr;

    GridProvider gdp;

    @Autowired
    DonacionQryMapper donacionQryMapper;

    @Autowired
    APDD apdd;


    Action agregarAction;

    public IngresarDonacionPst() {
        setBackBean(new IngresarDonacion());
        setShowAuditInfo(false);

    }

    @Override
    protected void onWindowsOpened(WindowEvent e) {
        registerListener();
    }

    private void registerListener() {
        vsr.cmbInstitucion.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    vsr.txtMonto.requestFocus();
                }
            }
        });

        vsr.txtMonto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    vsr.btnAgregar.requestFocus();
            }
        });

        vsr.btnAgregar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)

                    vsr.btnAgregar.doClick();

            }
        });
    }

    @Override
    protected void registerActions() {
        agregarAction = actionRsr.registerAction("Agregar", new Action() {
            @Override
            protected Object executeIntern() throws Throwable {
                IngresarDonacion bean = getBackBean();

                DonacionFlt filtro = new DonacionFlt(bean.getCoInstitucion());
                Donacion donacion = (Donacion) donacionQryMapper.findInstitucionList(filtro).get(0);
                IngresarDonacionDetalle ingresarDonacionDetalle = new IngresarDonacionDetalle(bean.getCoInstitucion(), donacion.getDeInstitucion(), donacion.getDeCortaInstitucion(), bean.getMonto());
                bean.getDetalle().add(ingresarDonacionDetalle);
                bean.setCoInstitucion(null);
                bean.setMonto(null);
                setValuesToJComponent();
                gdp.refresh(bean);
                return null;
            }
        });
        actionRsr.registerAction("Guardar", new Action() {
            @Override
            protected Object executeIntern() throws Throwable {
                if (gdp.getValues().size() == 0) {
                    throw new AWBusinessException("Debe ingresar alguna donación");
                }
                return null;
            }
        }).closeViewAtEnd()
                .noExecValidation()
                .notNeedVisualComponent()
                .setKeyTrigger(ActionDialog.KEY_F10);
    }

    @Override
    protected void registerBinding() {
        bindingMgr.bind(vsr.cmbInstitucion, apdd.INSTITUCIONES, "coInstitucion", "R").setLabelToBeUsedOnError("Institución");
    }

    @Override
    protected void registerGridProviders() {
        gdp = gridProviderMgr.registerGridProvider(new IPDetalle()).setShowTotalRegistros(false);
    }

    private class IPDetalle extends GridInfoProvider<IngresarDonacion> {

        public ColumnInfo[] getColumnInfo() {
            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Código", "donacion.coInstitucion", 45, ColumnInfo.LEFT),
                    new ColumnInfo("Institución", "donacion.deInstitucion", 130, ColumnInfo.LEFT),
                    new ColumnInfo("Monto", "monto", 30, ColumnInfo.RIGHT).formatAsMoney()

            };
            return columns;
        }

        public List<IngresarDonacionDetalle> getValues(IngresarDonacion bean) {
            return bean.getDetalle();
        }
    }


    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("001", "005"));


        new LookAndFeelManager().initialize();
        Application.instance().init();

        Presenter presenter = PstMgr.instance().getPst(IngresarDonacionPst.class);
        Application.instance().autowireFields(presenter);
        IngresarDonacion bean = new IngresarDonacion();
        presenter.setBackBean(bean);
        presenter.init();
    }

    public List<IngresarDonacionDetalle> getDonacionList() {
        return gdp.getValues();
    }

}
