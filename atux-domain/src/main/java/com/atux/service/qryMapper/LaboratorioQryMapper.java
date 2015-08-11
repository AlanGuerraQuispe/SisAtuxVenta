package com.atux.service.qryMapper;

import com.atux.bean.kardex.Producto;
import com.atux.bean.kardex.ProductoFlt;
import com.atux.bean.precios.Laboratorio;
import com.atux.bean.precios.LaboratorioFlt;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
public interface LaboratorioQryMapper {

    List<Laboratorio> findLaboratorio(LaboratorioFlt laboratorioFlt);


}
