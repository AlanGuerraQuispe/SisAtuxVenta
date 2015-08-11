package com.atux.service.qryMapper;

import com.atux.bean.consulta.*;

import java.util.List;
import java.util.Map;

/**
 * Created by JAVA on 15/11/2014.
 */
public interface ConsultaQryMapper {

    List<ReporteVentaItem> findDetalleVenta(ConsultaFlt consultaFlt);

    List<ReporteVentaItem> findDetalleVentaResumen(ConsultaFlt consultaFlt);

    List<ReporteVentaItem> findVentaTransaccion(ConsultaFlt filtro);

    List<ReporteDiaItem> findVentaDia(ConsultaFlt filtro);

    List<ReporteVentaItem> findDetalleVentaPorVendedor(ConsultaFlt filtro);

    List<ReporteVendedorItem> findVentaPorVendedor(ConsultaFlt filtro);

    List<ReporteVentaFormaPagoItem> findResumenFormaPago(ConsultaFlt filtro);

    List<Map> findResumenVentasMap(ConsultaFlt filtro);
    
    void calculaVentasPorVendedor(Map parametros);
}
