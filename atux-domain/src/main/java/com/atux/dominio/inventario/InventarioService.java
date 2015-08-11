package com.atux.dominio.inventario;

import com.atux.bean.inventario.LaboratorioInventarioFlt;
import com.atux.bean.inventario.TomaInventarioProducto;
import com.atux.bean.kardex.Kardex;
import com.atux.bean.precios.LaboratorioFlt;

/**
 * Created by JAVA on 01/12/2014.
 */
public interface InventarioService {


    public String iniciarToma(LaboratorioInventarioFlt laboratorioFlt);

    public void grabarIngresoCantidad(TomaInventarioProducto tomaInventarioProducto);

}
