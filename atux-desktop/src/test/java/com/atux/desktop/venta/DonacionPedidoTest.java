package com.atux.desktop.venta;

import atux.config.AppConfig;
import atux.handlers.DonacionService;
import atux.modelbd.PedidoVenta;
import atux.util.common.AtuxVariables;
import atux.vistas.MainWindow;
import com.atux.comun.context.AppCtx;
import com.atux.desktop.TestPersistenceBase;

import com.aw.swing.spring.Application;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by MATRIX-JAVA on 15/2/2015.
 */
@TransactionConfiguration(defaultRollback = false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:appCtx/appctx-context.xml",
        "classpath*:appCtx/appctx-datasource.xml",
        "classpath*:appCtx/appctx-dialog.xml",
        "classpath*:appCtx/appctx-mybatis.xml"
})
public class DonacionPedidoTest extends TestPersistenceBase {
    private static final Logger LOG = LoggerFactory.getLogger(DonacionPedidoTest.class);

    @Autowired
    private DonacionService donacionService;

    @Test
    public void testRedondedo1() throws Exception {
        PedidoVenta pedidoVenta= new PedidoVenta();
        pedidoVenta.setVaTotalPrecioVenta(125.30);
        pedidoVenta.setVaSaldoRedondeo(-0.02);
        double donacion= donacionService.calcularMontoSugerido(pedidoVenta,0.1);
        Assert.assertEquals(0.08, donacion);
    }

    @Test
    public void testRedondedo2() throws Exception {
        PedidoVenta pedidoVenta= new PedidoVenta();
        pedidoVenta.setVaTotalPrecioVenta(125.35);
        pedidoVenta.setVaSaldoRedondeo(-0.02);
        double donacion= donacionService.calcularMontoSugerido(pedidoVenta,0.5);
        Assert.assertEquals(0.13, donacion);
    }

    @Test
    public void testRedondedo3() throws Exception {
        PedidoVenta pedidoVenta= new PedidoVenta();
        pedidoVenta.setVaTotalPrecioVenta(125.30);
        pedidoVenta.setVaSaldoRedondeo(0.00);
        double donacion= donacionService.calcularMontoSugerido(pedidoVenta,0.1);
        Assert.assertEquals(0.0, donacion);
    }

    @Test
    public void testRedondedo4() throws Exception {
        PedidoVenta pedidoVenta= new PedidoVenta();
        pedidoVenta.setVaTotalPrecioVenta(125.30);
        pedidoVenta.setVaSaldoRedondeo(0.00);
        double donacion= donacionService.calcularMontoSugerido(pedidoVenta,0.5);
        Assert.assertEquals(0.2, donacion);
    }

   @Test
    public void testAnularDonacion() throws Exception {

        donacionService.anularDonacion("0982193133");

    }

    @Override
    public void init() throws Exception {
        Application.instance().init();
        AppCtx appCtx = AppCtx.instance();
        appCtx.setEnviromentTest(true);
        AtuxVariables.vCodigoCompania = appCtx.getLocalId().getCoCompania();
        AtuxVariables.vCodigoLocal = appCtx.getLocalId().getCoLocal();
        AtuxVariables.vImpresoraTestigo = appCtx.getImpresoraTestigo();
        AppConfig.configUsuario("ADMIN", "atuxpro");

        MainWindow.obtenerInfoLocal();
    }
}
