package com.atux.dominio.precios;

import com.atux.bean.central.Fraccionamiento;
import com.atux.bean.kardex.Producto;
import com.atux.bean.precios.Incentivo;

/**
 * Created by MATRIX-JAVA on 01/12/2014.
 */
public interface ProveedorService {

    public void modificarPrecioDescuento(Producto producto);
    public void modificarVaExhibicion(Producto producto);

    void grabarIncentivo(Incentivo backBean);

    void grabarFraccion(Fraccionamiento backBean);
}
