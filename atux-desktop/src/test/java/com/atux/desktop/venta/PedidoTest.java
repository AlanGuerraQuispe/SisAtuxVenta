package com.atux.desktop.venta;

import atux.config.AppConfig;
import atux.controllers.CDetallePedidoVenta;
import atux.controllers.CPedidoVenta;
import atux.controllers.CProductoLocal;
import atux.handlers.DonacionService;
import atux.handlers.PedidoVentaInterceptor;
import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.Moneda;
import atux.modelbd.PedidoVenta;
import atux.modelbd.ProductoLocal;
import atux.modelgui.ModeloTomaPedidoVenta;
import atux.util.common.*;
import atux.vistas.MainWindow;
import atux.vistas.venta.IPedidoVenta;
import atux.vistas.venta.caja.IPagoPedido;
import com.atux.bean.donacion.Donacion;
import com.atux.bean.donacion.DonacionDetalle;
import com.atux.bean.donacion.IngresarDonacionDetalle;
import com.atux.comun.context.AppCtx;
import com.atux.desktop.DataUtil;
import com.atux.desktop.TestPersistenceBase;
import com.aw.core.format.DateFormatter;
import com.aw.core.spring.ApplicationBase;
import com.aw.support.collection.ListUtils;
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
public class PedidoTest extends TestPersistenceBase {
    private CPedidoVenta cpPedidoVenta = new CPedidoVenta();
    private IPedidoVenta ipPedidoVenta = new IPedidoVenta("Test");
    private CProductoLocal cpProductoLocal = new CProductoLocal();
    private ModeloTomaPedidoVenta modeloTomaPedidoVenta;

    private CDetallePedidoVenta crtlDpv = new CDetallePedidoVenta();

    private static final Logger LOG = LoggerFactory.getLogger(PedidoTest.class);

    @Autowired
    private PedidoVentaInterceptor pedidoVentaInterceptor;

    @Autowired
    private DonacionService donacionProvider;

    @Test
    @Transactional
    public void testGeneraPedido() throws Exception {
        List<String> coProductoList = Arrays.asList("168041", "170187");
        PedidoVenta pedidoVenta = DataUtil.buildPedidoBase();
        pedidoVenta.setDetallePedidoVenta(new ArrayList<DetallePedidoVenta>());
        int i = 0;
        for (String coProducto : coProductoList) {
            ProductoLocal productoLocal = (ProductoLocal) cpProductoLocal.getProductosPedidoVenta(coProducto).get(0);
            DetallePedidoVenta detallePedidoVenta = new DetallePedidoVenta();
            detallePedidoVenta.setNuItemPedido(i);
            detallePedidoVenta.agregarItem(productoLocal);
            pedidoVenta.getDetallePedidoVenta().add(detallePedidoVenta);
            Map result = new HashMap();
            pedidoVentaInterceptor.procesar(pedidoVenta, detallePedidoVenta, result);
            LOG.info("mensajeImpresora " + (result.get("mensajeImpresora") == null ? "sin mensaje" : result.get("mensajeImpresora")));
            LOG.info("mensajeVentana" + (result.get("mensajeVentana") == null ? "sin mensaje" : result.get("mensajeVentana")));
            i++;
        }

        modeloTomaPedidoVenta = new ModeloTomaPedidoVenta(pedidoVenta.getDetallePedidoVenta());
        pedidoVenta.setCaItem((Integer) modeloTomaPedidoVenta.getNumItems());
        pedidoVenta.setVaTotalVenta((Double) modeloTomaPedidoVenta.getBruto());
        pedidoVenta.setVaTotalPrecioVenta((Double) modeloTomaPedidoVenta.getTotalPrecioVenta());
        pedidoVenta.setVaTotalDescuento((Double) modeloTomaPedidoVenta.getTotalDescuento());
        pedidoVenta.setVaTotalImpuesto((Double) modeloTomaPedidoVenta.getTotalImpuesto());
        pedidoVenta.setVaSaldoRedondeo((Double) modeloTomaPedidoVenta.getRedondeo());

        boolean rp = cpPedidoVenta.guardarRegistro(pedidoVenta);
        ipPedidoVenta.guardarDetallePedido(pedidoVenta);
        Assert.assertEquals(pedidoVenta.getDetallePedidoVenta().size(), 2);
        LOG.info("Pedido enviado a bd " + pedidoVenta);


        AtuxUtility.aceptarTransaccion();

    }

    @Test
    @Transactional
    public void testGeneraPedidoYCobro() throws Exception {
        List<String> coProductoList = Arrays.asList("168041", "170187");
        PedidoVenta pedidoVenta = DataUtil.buildPedidoBase();
        pedidoVenta.setDetallePedidoVenta(new ArrayList<DetallePedidoVenta>());
        int i = 0;
        for (String coProducto : coProductoList) {
            ProductoLocal productoLocal = (ProductoLocal) cpProductoLocal.getProductosPedidoVenta(coProducto).get(0);
            DetallePedidoVenta detallePedidoVenta = new DetallePedidoVenta();
            detallePedidoVenta.setNuItemPedido(i);
            detallePedidoVenta.agregarItem(productoLocal);
            pedidoVenta.getDetallePedidoVenta().add(detallePedidoVenta);
            Map result = new HashMap();
            pedidoVentaInterceptor.procesar(pedidoVenta, detallePedidoVenta, result);
            LOG.info("mensajeImpresora " + (result.get("mensajeImpresora") == null ? "sin mensaje" : result.get("mensajeImpresora")));
            LOG.info("mensajeVentana" + (result.get("mensajeVentana") == null ? "sin mensaje" : result.get("mensajeVentana")));
            i++;
        }

        modeloTomaPedidoVenta = new ModeloTomaPedidoVenta(pedidoVenta.getDetallePedidoVenta());
        pedidoVenta.setCaItem((Integer) modeloTomaPedidoVenta.getNumItems());
        pedidoVenta.setVaTotalVenta((Double) modeloTomaPedidoVenta.getBruto());
        pedidoVenta.setVaTotalPrecioVenta((Double) modeloTomaPedidoVenta.getTotalPrecioVenta());
        pedidoVenta.setVaTotalDescuento((Double) modeloTomaPedidoVenta.getTotalDescuento());
        pedidoVenta.setVaTotalImpuesto((Double) modeloTomaPedidoVenta.getTotalImpuesto());
        pedidoVenta.setVaSaldoRedondeo((Double) modeloTomaPedidoVenta.getRedondeo());

        boolean rp = cpPedidoVenta.guardarRegistro(pedidoVenta);
        ipPedidoVenta.guardarDetallePedido(pedidoVenta);
        Assert.assertEquals(pedidoVenta.getDetallePedidoVenta().size(), 2);
        LOG.info("Pedido enviado a bd " + pedidoVenta);
        AtuxUtility.aceptarTransaccion();

        String feIni = (String) DateFormatter.DATE_FORMATTER.format(new Date());
        String feFin = (String) DateFormatter.DATE_FORMATTER.format(new Date());
        ArrayList ArrayPedidos = cpPedidoVenta.getPedidoVenta(pedidoVenta.getNuPedidoDiario(), "", feIni, feFin, "");
        pedidoVenta = (PedidoVenta) ArrayPedidos.get(0);
        LOG.info("Pedido cargado de bd  " + pedidoVenta);
        AtuxVariables.vNumeroPedido = pedidoVenta.getNuPedido();
        IPagoPedido iPagoPedido = new IPagoPedido(new JFrame(), "", true);
        iPagoPedido.setPedido(pedidoVenta);
        LOG.info("Realizando pago de <" + pedidoVenta.getVaTotalPrecioVenta() + ">");
        List arrayPagos = new ArrayList();
        arrayPagos.add(AtuxVariables.FORMA_PAGO_EFECTIVO);
        arrayPagos.add("EFECTIVO");
        arrayPagos.add("SOLES");
        arrayPagos.add(AtuxUtility.formatNumber(pedidoVenta.getVaTotalPrecioVenta()));
        arrayPagos.add(AtuxUtility.formatNumber(pedidoVenta.getVaTotalPrecioVenta()));
        arrayPagos.add(AtuxVariables.MONEDA_SOLES);
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("0");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        iPagoPedido.getTableModelPagos().insertRow((ArrayList) arrayPagos);
        iPagoPedido.windowOpenedIntern();
        iPagoPedido.doEnterCmbFormaPago();
        iPagoPedido.grabar();

        AtuxUtility.aceptarTransaccion();
    }

    @Test
    @Transactional
    public void testGeneraPedidoYCobroConDonacion() throws Exception {
        List<String> coProductoList = Arrays.asList("168041", "170187");
        PedidoVenta pedidoVenta = DataUtil.buildPedidoBase();
        pedidoVenta.setDetallePedidoVenta(new ArrayList<DetallePedidoVenta>());
        int i = 0;
        for (String coProducto : coProductoList) {
            ProductoLocal productoLocal = (ProductoLocal) cpProductoLocal.getProductosPedidoVenta(coProducto).get(0);
            DetallePedidoVenta detallePedidoVenta = new DetallePedidoVenta();
            detallePedidoVenta.setNuItemPedido(i);
            detallePedidoVenta.agregarItem(productoLocal);
            pedidoVenta.getDetallePedidoVenta().add(detallePedidoVenta);
            Map result = new HashMap();
            pedidoVentaInterceptor.procesar(pedidoVenta, detallePedidoVenta, result);
            LOG.info("mensajeImpresora " + (result.get("mensajeImpresora") == null ? "sin mensaje" : result.get("mensajeImpresora")));
            LOG.info("mensajeVentana" + (result.get("mensajeVentana") == null ? "sin mensaje" : result.get("mensajeVentana")));
            i++;
        }

        modeloTomaPedidoVenta = new ModeloTomaPedidoVenta(pedidoVenta.getDetallePedidoVenta());
        pedidoVenta.setCaItem((Integer) modeloTomaPedidoVenta.getNumItems());
        pedidoVenta.setVaTotalVenta((Double) modeloTomaPedidoVenta.getBruto());
        pedidoVenta.setVaTotalPrecioVenta((Double) modeloTomaPedidoVenta.getTotalPrecioVenta());
        pedidoVenta.setVaTotalDescuento((Double) modeloTomaPedidoVenta.getTotalDescuento());
        pedidoVenta.setVaTotalImpuesto((Double) modeloTomaPedidoVenta.getTotalImpuesto());
        pedidoVenta.setVaSaldoRedondeo((Double) modeloTomaPedidoVenta.getRedondeo());
        pedidoVenta.setTiComprobante(AtuxVariables.TIPO_BOLETA);

        boolean rp = cpPedidoVenta.guardarRegistro(pedidoVenta);
        ipPedidoVenta.guardarDetallePedido(pedidoVenta);
        Assert.assertEquals(pedidoVenta.getDetallePedidoVenta().size(), 2);
        LOG.info("Pedido enviado a bd " + pedidoVenta);
        AtuxUtility.aceptarTransaccion();

        String feIni = (String) DateFormatter.DATE_FORMATTER.format(new Date());
        String feFin = (String) DateFormatter.DATE_FORMATTER.format(new Date());
        ArrayList ArrayPedidos = cpPedidoVenta.getPedidoVenta(pedidoVenta.getNuPedidoDiario(), "", feIni, feFin, "");
        pedidoVenta = (PedidoVenta) ArrayPedidos.get(0);
        LOG.info("Pedido cargado de bd  " + pedidoVenta);
        AtuxVariables.vNumeroPedido = pedidoVenta.getNuPedido();
        IPagoPedido iPagoPedido = new IPagoPedido(new JFrame(), "", true);
        iPagoPedido.setPedido(pedidoVenta);
        LOG.info("Realizando pago de <" + pedidoVenta.getVaTotalPrecioVenta() + ">");
        List arrayPagos = new ArrayList();
        arrayPagos.add(AtuxVariables.FORMA_PAGO_EFECTIVO);
        arrayPagos.add("EFECTIVO");
        arrayPagos.add("SOLES");
        arrayPagos.add(AtuxUtility.formatNumber(pedidoVenta.getVaTotalPrecioVenta()));
        arrayPagos.add(AtuxUtility.formatNumber(pedidoVenta.getVaTotalPrecioVenta()));
        arrayPagos.add(AtuxVariables.MONEDA_SOLES);
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("0");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");
        arrayPagos.add("");

        IngresarDonacionDetalle donacion = new IngresarDonacionDetalle();
        donacion.setDonacion(new Donacion("001", "ALAN","ALAN C"));
        donacion.setMonto(new BigDecimal(donacionProvider.calcularMontoSugerido(pedidoVenta, AtuxVariables.vRedondearTo)));
        iPagoPedido.setDonacionList(Arrays.asList(donacion));
        iPagoPedido.windowOpenedIntern();
        iPagoPedido.doEnterCmbFormaPago();
        arrayPagos.set(3, iPagoPedido.getLblSoles().getText());
        arrayPagos.set(4,iPagoPedido.getLblSoles().getText());
        iPagoPedido.getTableModelPagos().insertRow((ArrayList) arrayPagos);
        iPagoPedido.grabar();
        AtuxUtility.aceptarTransaccion();
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

        AtuxVariables.vInTicketBoleta="N";
    }
}
