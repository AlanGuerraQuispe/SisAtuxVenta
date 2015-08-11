package com.atux.dominio.converter;

import com.atux.bean.kardex.Producto;
import com.atux.comun.context.AppCtx;
import com.atux.infraestructura.jpa.pojo.LgtdFarinV2;
import com.atux.infraestructura.jpa.pojo.LgtdFarinV2PK;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by MATRIX-JAVA on 01/12/2014.
 */
public class ProductoToLgtdFarinV2 implements Converter<Producto,LgtdFarinV2> {

    public LgtdFarinV2 convert(Producto producto) {
        LgtdFarinV2PK pk = new LgtdFarinV2PK();
        pk.setCoCompania(AppCtx.instance().getLocalId().getCoCompania());
        pk.setCoProducto(producto.getCoProducto());
        pk.setCoProveedor(producto.getCoProveedor());
        pk.setNuGuia(producto.getNuGuia());
        LgtdFarinV2 result= new LgtdFarinV2();
        result.setId(pk);
        result.setVaPrecio(producto.getPrecioProveedor());
        result.setPcDcto1(producto.getPorcentajeDescuento());
        return result;
    }
}
