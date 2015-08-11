package com.atux.desktop.inventario;

import atux.config.AppConfig;
import atux.controllers.CDetallePedidoVenta;
import atux.controllers.CPedidoVenta;
import atux.controllers.CProductoLocal;
import atux.handlers.DonacionService;
import atux.handlers.PedidoVentaInterceptor;
import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.PedidoVenta;
import atux.modelbd.ProductoLocal;
import atux.modelgui.ModeloTomaPedidoVenta;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.vistas.MainWindow;
import atux.vistas.venta.IPedidoVenta;
import atux.vistas.venta.caja.IPagoPedido;
import com.atux.bean.donacion.Donacion;
import com.atux.bean.donacion.IngresarDonacionDetalle;
import com.atux.bean.inventario.LaboratorioInventario;
import com.atux.bean.inventario.LaboratorioInventarioFlt;
import com.atux.bean.inventario.TomaInventario;
import com.atux.bean.precios.LaboratorioFlt;
import com.atux.comun.context.AppCtx;
import com.atux.desktop.DataUtil;
import com.atux.desktop.TestPersistenceBase;
import com.atux.dominio.inventario.InventarioService;
import com.atux.enums.TipoTomaInventarioEnum;
import com.aw.core.format.DateFormatter;
import com.aw.swing.spring.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

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
public class InventarioTest extends TestPersistenceBase {

    private static final Logger LOG = LoggerFactory.getLogger(InventarioTest.class);

    @Autowired
    private InventarioService inventarioService;

    @Test
    @Transactional
    public void testGeneraTomaPorLaboratorio() throws Exception {

        LaboratorioInventarioFlt laboratorioInventarioFlt = new LaboratorioInventarioFlt();
        laboratorioInventarioFlt.setTiTomaInventario(TipoTomaInventarioEnum.LABORATORIO);
        LaboratorioInventario laboratorio1 = new LaboratorioInventario();
        laboratorio1.setCoLaboratorio("0266");
        laboratorioInventarioFlt.setSeleccionados(Arrays.asList(laboratorio1));
        inventarioService.iniciarToma(laboratorioInventarioFlt);
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
        AtuxUtility.setCajaTurno();
        AtuxSearch.existeCajaTurnoImpresora(new Frame());
        MainWindow.obtenerInfoLocal();
        AtuxVariables.vInTicketBoleta = "N";
    }
}
