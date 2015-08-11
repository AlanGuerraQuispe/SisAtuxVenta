package com.atux.service.qryMapper;

import com.atux.bean.central.Fraccionamiento;
import com.atux.bean.central.FraccionamientoLocal;
import com.atux.bean.central.ProductoFraccionFlt;
import com.atux.bean.central.Unidad;
import com.atux.bean.consulta.UnidadFlt;
import com.atux.bean.consulta.UsuarioFlt;
import com.atux.bean.kardex.Producto;
import com.atux.bean.kardex.ProductoFlt;
import com.atux.bean.precios.Incentivo;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
public interface ProductoQryMapper {

    List<Producto> findProducto(ProductoFlt productoFlt);

    List<Producto> findProductoPrecioLocal(ProductoFlt productoFlt);

    List<Incentivo> findProductoIncentivo(ProductoFlt productoFlt);

    List<Producto> findProductoMinExhibicion(ProductoFlt productoFlt);

    List<Producto> findProductoRotacion(ProductoFlt productoFlt);

    List<Producto> findProductoBase(ProductoFlt productoFlt);
    List<Producto> findProductoFraccionamientoActivo(ProductoFlt productoFlt);

    List<FraccionamientoLocal> findProductoFraccionamiento(ProductoFlt productoFlt);

    List<Unidad> findUnidad(UnidadFlt obj);

    Long findNextNuSecFraccion(ProductoFraccionFlt filter);

    void inactivarProductoFraccion(ProductoFraccionFlt filter);

    void insertProductoFraccion(FraccionamientoLocal fraccionamientoLocal);
}
