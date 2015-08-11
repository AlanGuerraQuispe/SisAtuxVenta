package com.atux.service.qryMapper;

import com.atux.bean.kardex.Producto;
import com.atux.bean.kardex.ProductoFlt;
import com.atux.bean.precios.Incentivo;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
public interface PrecioQryMapper {

    public Long findNextNuSecProd(ProductoFlt productoFlt);

    public void inactivarProductoBono(Incentivo incentivo);


}
