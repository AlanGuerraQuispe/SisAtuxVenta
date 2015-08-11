package com.atux.service.qryMapper;

import com.atux.bean.promocion.Promocion;
import com.atux.bean.promocion.PromocionDetalle;
import com.atux.bean.promocion.PromocionFlt;
import com.atux.bean.promocion.PromocionLocal;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
public interface PromocionQryMapper {

    List<PromocionDetalle> findPromocionDetalleList(PromocionFlt proveedorFlt);

    List<PromocionDetalle> findPromocionPorProducto(PromocionFlt proveedorFlt);

    List<Promocion> findPromocionList(PromocionFlt proveedorFlt);

    void deletePromocion(String coPromocion);
    void deletePromocionLocal(String coPromocion);

    List<PromocionLocal> findPromocionDetalleLocalList(PromocionFlt proveedorFlt);
}
