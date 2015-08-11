package com.atux.desktop.venta;

import atux.config.AppConfig;
import atux.controllers.CDetallePedidoVenta;
import atux.controllers.CPedidoVenta;
import atux.controllers.CProductoLocal;
import atux.handlers.PedidoVentaInterceptor;
import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.PedidoVenta;
import atux.modelbd.ProductoLocal;
import atux.modelgui.ModeloTomaPedidoVenta;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.vistas.venta.IPedidoVenta;
import com.atux.comun.context.AppCtx;
import com.atux.desktop.DataUtil;
import com.atux.desktop.TestPersistenceBase;
import com.aw.support.collection.ListUtils;
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

import java.util.*;

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
public class PromocionPedidoTest extends TestPersistenceBase {
    private CPedidoVenta cpPedidoVenta = new CPedidoVenta();
    private IPedidoVenta ipPedidoVenta = new IPedidoVenta("Test");
    private CProductoLocal cpProductoLocal = new CProductoLocal();
    private ModeloTomaPedidoVenta modeloTomaPedidoVenta;

    private CDetallePedidoVenta crtlDpv = new CDetallePedidoVenta();

    private static final Logger LOG = LoggerFactory.getLogger(PromocionPedidoTest.class);

    @Autowired
    private PedidoVentaInterceptor pedidoVentaInterceptor;

    @Test
    @Transactional
    public void testGeneraPedidoSinPromocion() throws Exception {
        List<String> coProductoList = Arrays.asList("168041", "170187");
        PedidoVenta pedidoVenta = DataUtil.buildPedidoBase();
        LOG.info("Nro Pedido generado <" + pedidoVenta.getNuPedido() + ">");
        LOG.info("Nro Pedido diario <" + pedidoVenta.getNuPedidoDiario() + ">");
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
        LOG.info("Pedido guardado <" + rp + ">");
        ipPedidoVenta.guardarDetallePedido(pedidoVenta);
        Assert.assertEquals(pedidoVenta.getDetallePedidoVenta().size(), 2);
        AtuxUtility.aceptarTransaccion();
    }

    @Test
    public void testGeneraPedidoConPromocion() throws Exception {
        List<String> coProductoList = Arrays.asList("134207");
        PedidoVenta pedidoVenta = DataUtil.buildPedidoBase();
        LOG.info("Nro Pedido generado <" + pedidoVenta.getNuPedido() + ">");
        LOG.info("Nro Pedido diario <" + pedidoVenta.getNuPedidoDiario() + ">");
        pedidoVenta.setDetallePedidoVenta(new ArrayList<DetallePedidoVenta>());
        int i = 0;
        for (String coProducto : coProductoList) {
            ProductoLocal productoLocal = (ProductoLocal) cpProductoLocal.getProductosPedidoVenta(coProducto).get(0);
            DetallePedidoVenta detallePedidoVenta = new DetallePedidoVenta();
            detallePedidoVenta.setNuItemPedido(i);
            detallePedidoVenta.agregarItem(productoLocal);
            detallePedidoVenta.setCaAtendida(15);
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
        LOG.info("Pedido guardado <" + rp + ">");
        ipPedidoVenta.guardarDetallePedido(pedidoVenta);
        Assert.assertEquals(pedidoVenta.getDetallePedidoVenta().size(), 2);
        AtuxUtility.aceptarTransaccion();
    }

    @Test
    public void testGeneraPedidoQuitarProductoPromocion() throws Exception {
        List<String> coProductoList = Arrays.asList("134207");
        PedidoVenta pedidoVenta = DataUtil.buildPedidoBase();
        LOG.info("Nro Pedido generado <" + pedidoVenta.getNuPedido() + ">");
        LOG.info("Nro Pedido diario <" + pedidoVenta.getNuPedidoDiario() + ">");
        pedidoVenta.setDetallePedidoVenta(new ArrayList<DetallePedidoVenta>());
        int i = 0;
        for (String coProducto : coProductoList) {
            ProductoLocal productoLocal = (ProductoLocal) cpProductoLocal.getProductosPedidoVenta(coProducto).get(0);
            DetallePedidoVenta detallePedidoVenta = new DetallePedidoVenta();
            detallePedidoVenta.setNuItemPedido(i);
            detallePedidoVenta.agregarItem(productoLocal);
            detallePedidoVenta.setCaAtendida(15);
            pedidoVenta.getDetallePedidoVenta().add(detallePedidoVenta);
            Map result = new HashMap();
            pedidoVentaInterceptor.procesar(pedidoVenta, detallePedidoVenta, result);
            LOG.info("mensajeImpresora " + (result.get("mensajeImpresora") == null ? "sin mensaje" : result.get("mensajeImpresora")));
            LOG.info("mensajeVentana" + (result.get("mensajeVentana") == null ? "sin mensaje" : result.get("mensajeVentana")));
            i++;
        }
        DetallePedidoVenta detallePedidoVenta = pedidoVenta.getDetallePedidoVenta().get(0);
        pedidoVenta.getDetallePedidoVenta().remove(detallePedidoVenta);
        Map result = new HashMap();
        pedidoVentaInterceptor.quitar(pedidoVenta, detallePedidoVenta, result);
        List<DetallePedidoVenta> itemsEliminados = (List<DetallePedidoVenta>) result.get("itemsEliminados");
        if (itemsEliminados.size() > 0) {
            pedidoVenta.getDetallePedidoVenta().removeAll(itemsEliminados);
        }
        Assert.assertEquals(pedidoVenta.getDetallePedidoVenta().size(), 1);
        AtuxUtility.aceptarTransaccion();
    }

    @Test
    public void testPromocionAumentoCaAtendida() throws Exception {
        List<String> coProductoList = Arrays.asList("134207");
        PedidoVenta pedidoVenta = DataUtil.buildPedidoBase();
        LOG.info("Nro Pedido generado <" + pedidoVenta.getNuPedido() + ">");
        LOG.info("Nro Pedido diario <" + pedidoVenta.getNuPedidoDiario() + ">");
        pedidoVenta.setDetallePedidoVenta(new ArrayList<DetallePedidoVenta>());
        int i = 0;
        for (String coProducto : coProductoList) {
            ProductoLocal productoLocal = (ProductoLocal) cpProductoLocal.getProductosPedidoVenta(coProducto).get(0);
            DetallePedidoVenta detallePedidoVenta = new DetallePedidoVenta();
            detallePedidoVenta.setNuItemPedido(i);
            detallePedidoVenta.agregarItem(productoLocal);
            detallePedidoVenta.setCaAtendida(15);
            pedidoVenta.getDetallePedidoVenta().add(detallePedidoVenta);
            Map result = new HashMap();
            pedidoVentaInterceptor.procesar(pedidoVenta, detallePedidoVenta, result);
            LOG.info("mensajeImpresora " + (result.get("mensajeImpresora") == null ? "sin mensaje" : result.get("mensajeImpresora")));
            LOG.info("mensajeVentana" + (result.get("mensajeVentana") == null ? "sin mensaje" : result.get("mensajeVentana")));
            i++;
        }
        pedidoVenta.getDetallePedidoVenta().get(0).setCaAtendida(18);
        Map result = new HashMap();
        pedidoVentaInterceptor.procesar(pedidoVenta, pedidoVenta.getDetallePedidoVenta().get(0), result);
        LOG.info("mensajeImpresora " + (result.get("mensajeImpresora") == null ? "sin mensaje" : result.get("mensajeImpresora")));
        LOG.info("mensajeVentana" + (result.get("mensajeVentana") == null ? "sin mensaje" : result.get("mensajeVentana")));

        modeloTomaPedidoVenta = new ModeloTomaPedidoVenta(pedidoVenta.getDetallePedidoVenta());
        pedidoVenta.setCaItem((Integer) modeloTomaPedidoVenta.getNumItems());
        pedidoVenta.setVaTotalVenta((Double) modeloTomaPedidoVenta.getBruto());
        pedidoVenta.setVaTotalPrecioVenta((Double) modeloTomaPedidoVenta.getTotalPrecioVenta());
        pedidoVenta.setVaTotalDescuento((Double) modeloTomaPedidoVenta.getTotalDescuento());
        pedidoVenta.setVaTotalImpuesto((Double) modeloTomaPedidoVenta.getTotalImpuesto());
        pedidoVenta.setVaSaldoRedondeo((Double) modeloTomaPedidoVenta.getRedondeo());

        ListUtils.setSequentialInColumn(pedidoVenta.getDetallePedidoVenta(), "nuItemPedido");
        boolean rp = cpPedidoVenta.guardarRegistro(pedidoVenta);
        LOG.info("Pedido guardado <" + rp + ">");
        ipPedidoVenta.guardarDetallePedido(pedidoVenta);
        Assert.assertEquals(pedidoVenta.getDetallePedidoVenta().size(), 2);
        AtuxUtility.aceptarTransaccion();
    }

    @Test
    public void testGeneraPedidoSinPromocionPorCaAtendida() throws Exception {
        List<String> coProductoList = Arrays.asList("134207");
        PedidoVenta pedidoVenta = DataUtil.buildPedidoBase();
        LOG.info("Nro Pedido generado <" + pedidoVenta.getNuPedido() + ">");
        LOG.info("Nro Pedido diario <" + pedidoVenta.getNuPedidoDiario() + ">");
        pedidoVenta.setDetallePedidoVenta(new ArrayList<DetallePedidoVenta>());
        int i = 0;
        for (String coProducto : coProductoList) {
            ProductoLocal productoLocal = (ProductoLocal) cpProductoLocal.getProductosPedidoVenta(coProducto).get(0);
            DetallePedidoVenta detallePedidoVenta = new DetallePedidoVenta();
            detallePedidoVenta.setNuItemPedido(i);
            detallePedidoVenta.agregarItem(productoLocal);
            detallePedidoVenta.setCaAtendida(8);
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
        LOG.info("Pedido guardado <" + rp + ">");
        ipPedidoVenta.guardarDetallePedido(pedidoVenta);
        Assert.assertEquals(pedidoVenta.getDetallePedidoVenta().size(), 1);
        AtuxUtility.aceptarTransaccion();
    }


    @Override
    public void init() throws Exception {
        AppCtx appCtx = AppCtx.instance();
        AtuxVariables.vCodigoCompania = appCtx.getLocalId().getCoCompania();
        AtuxVariables.vCodigoLocal = appCtx.getLocalId().getCoLocal();
        AtuxVariables.vImpresoraTestigo = appCtx.getImpresoraTestigo();
        AppConfig.configUsuario("ADMIN", "atuxpro");
    }
}
