package com.atux.desktop;

import atux.core.Ex;
import atux.modelbd.PedidoVenta;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxVariables;
import com.atux.desktop.builder.PedidoBuilder;

/**
 * Created by MATRIX-JAVA on 15/2/2015.
 */
public class DataUtil {

    public static PedidoVenta buildPedidoBase() throws Exception{

        String nuPedido = AtuxSearch.getNuSecNumeracion(AtuxVariables.NUMERACION_PEDIDO, 10);
        AtuxSearch.setNuSecNumeracion(AtuxVariables.NUMERACION_PEDIDO);

        String nuPedidoDiario = AtuxSearch.getNuSecNumeracion(AtuxVariables.NUMERACION_PEDIDO_DIARIO, 4);
        AtuxSearch.setNumeroPedidoDiario();

        return new PedidoBuilder()
                .conCoCompania(AtuxVariables.vCodigoCompania)
                .conCoLocal(AtuxVariables.vCodigoLocal)
                .conNuPedido(nuPedido)
                .conTiPedido(AtuxVariables.TIPO_PEDIDO_NORMAL)
                .conInCuadreCaja("N")
                .conTiComprobante(AtuxVariables.TIPO_TICKET_BOLETA)
                .conCoMoneda(AtuxVariables.MONEDA_SOLES)
                .conCoVendedor(AtuxVariables.vNuSecUsuario)
                .conInPedidoAnulado("N")
                .conEsPedidoVenta("P")
                .conNuPuntoVenta(AtuxVariables.vNuCaja)
                .conNuTurno(AtuxVariables.vNuTurno)
                .conIdCreaPedidoVenta(AtuxVariables.vIdUsuario)
                .conFeCreaPedidoVenta(AtuxSearch.getFechaHora())
                .conNuPedidoDiario(AtuxVariables.vNumeroPedidoDiario)
        .build();
    }
}
