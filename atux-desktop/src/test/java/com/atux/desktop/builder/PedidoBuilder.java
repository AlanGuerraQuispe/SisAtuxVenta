package com.atux.desktop.builder;

import atux.modelbd.PedidoVenta;

import java.util.Date;

/**
 * Created by MATRIX-JAVA on 15/2/2015.
 */
public class PedidoBuilder implements Builder<PedidoVenta>{

    private String coCompania;
    private String coLocal;
    private String nuPedido;
    private String tiPedido;
    private String inCuadreCaja;
    private String tiComprobante;
    private String coMoneda;
    private String coVendedor;
    private String inPedidoAnulado;
    private String esPedidoVenta;
    private String nuPuntoVenta;
    private String nuTurno;
    private String idCreaPedidoVenta;
    private Date feCreaPedidoVenta;
    private String nuPedidoDiario;

    public PedidoVenta build() {
        PedidoVenta pedidoVenta=new PedidoVenta();
        pedidoVenta.setCoCompania(coCompania);
        pedidoVenta.setCoLocal(coLocal);
        pedidoVenta.setNuPedido(nuPedido);
        pedidoVenta.setTiPedido(tiPedido);
        pedidoVenta.setInCuadreCaja(inCuadreCaja);
        pedidoVenta.setTiComprobante(tiComprobante);
        pedidoVenta.setCoMoneda(coMoneda);
        pedidoVenta.setCoVendedor(coVendedor);
        pedidoVenta.setInPedidoAnulado(inPedidoAnulado);
        pedidoVenta.setEsPedidoVenta(esPedidoVenta);
        pedidoVenta.setNuPuntoVenta(nuPuntoVenta);
        pedidoVenta.setNuTurno(nuTurno);
        pedidoVenta.setIdCreaPedidoVenta(idCreaPedidoVenta);
        pedidoVenta.setFeCreaPedidoVenta(feCreaPedidoVenta);
        pedidoVenta.setNuPedidoDiario(nuPedidoDiario);
        return pedidoVenta;
    }

    public PedidoBuilder conCoCompania(String value){
        this.coCompania =value;
        return this;
    }

    public PedidoBuilder conCoLocal(String value){
        this.coLocal =value;
        return this;
    }
    public PedidoBuilder conNuPedido(String value){
        this.nuPedido =value;
        return this;
    }
    public PedidoBuilder conTiPedido(String value){
        this.tiPedido =value;
        return this;
    }
    public PedidoBuilder conInCuadreCaja(String value){
        this.inCuadreCaja =value;
        return this;
    }
    public PedidoBuilder conTiComprobante(String value){
        this.tiComprobante =value;
        return this;
    }
    public PedidoBuilder conCoMoneda(String value){
        this.coMoneda =value;
        return this;
    }
    public PedidoBuilder conCoVendedor(String value){
        this.coVendedor=value;
        return this;
    }
    public PedidoBuilder conInPedidoAnulado(String value){
        this.inPedidoAnulado=value;
        return this;
    }
    public PedidoBuilder conEsPedidoVenta(String value){
        this.esPedidoVenta =value;
        return this;
    }
    public PedidoBuilder conNuPuntoVenta(String value){
        this.nuPuntoVenta =value;
        return this;
    }
    public PedidoBuilder conNuTurno(String value){
        this.nuTurno =value;
        return this;
    }
    public PedidoBuilder conIdCreaPedidoVenta(String value){
        this.idCreaPedidoVenta=value;
        return this;
    }
    public PedidoBuilder conFeCreaPedidoVenta(Date value){
        this.feCreaPedidoVenta =value;
        return this;
    }
    public PedidoBuilder conNuPedidoDiario(String value){
        this.nuPedidoDiario =value;
        return this;
    }
}
