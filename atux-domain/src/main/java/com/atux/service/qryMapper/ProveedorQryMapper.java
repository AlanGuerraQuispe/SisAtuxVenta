package com.atux.service.qryMapper;

import com.atux.bean.kardex.KardexDetalle;
import com.atux.bean.kardex.KardexFlt;
import com.atux.bean.kardex.Producto;
import com.atux.bean.kardex.ProductoFlt;
import com.atux.bean.precios.PrecioLista;
import com.atux.bean.precios.PrecioListaDetalle;
import com.atux.bean.precios.Proveedor;
import com.atux.bean.precios.ProveedorFlt;

import java.util.List;
import java.util.Map;

/**
 * Created by JAVA on 15/11/2014.
 */
public interface ProveedorQryMapper {

    public List<Proveedor> findProveedorFacturacion(ProveedorFlt proveedorFlt);

    List<PrecioLista> findListaPrecioList(ProveedorFlt productoFlt);

    List<PrecioListaDetalle> findListaPrecioDetalleList(ProveedorFlt productoFlt);

    List<Proveedor> findProveedor(ProveedorFlt obj);

    void deleteListaDetalle(String coLista);

}
