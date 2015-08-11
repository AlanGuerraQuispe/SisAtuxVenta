package com.atux.service.qryMapper;

import com.atux.bean.inventario.TomaInventario;
import com.atux.bean.inventario.TomaInventarioFlt;
import com.atux.bean.inventario.TomaInventarioLaboratorio;
import com.atux.bean.inventario.TomaInventarioProducto;
import com.atux.bean.precios.Laboratorio;
import com.atux.bean.precios.LaboratorioFlt;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
public interface InventarioQryMapper {

    String grabarInicioTomaCabecera(TomaInventario tomaInventario);

    List<String> findLaboratorioConPedidoPendiente(LaboratorioFlt laboratorioFlt);

    List<Laboratorio> findLaboratorio(LaboratorioFlt laboratorioFlt);


    void grabarInicioTomaDetalle(TomaInventarioLaboratorio tomaInventarioLaboratorio);

    List<TomaInventario> findTomaInventarioList(TomaInventarioFlt tomaInventarioFlt);

    List<TomaInventarioLaboratorio> findTomaInventarioLaboratorioList(TomaInventarioFlt tomaInventarioFlt);

    List<TomaInventarioProducto> findTomaInventarioProductoList(TomaInventarioFlt tomaInventarioFlt);
}
