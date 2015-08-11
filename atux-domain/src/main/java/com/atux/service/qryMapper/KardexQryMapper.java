package com.atux.service.qryMapper;

import com.atux.bean.kardex.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by JAVA on 15/11/2014.
 */
public interface KardexQryMapper {

    List<KardexDetalle> findKardexDetalle(KardexFlt kardexFlt);

    List<KardexDetalle> findKardexAjusteDetalle(KardexFlt kardexFlt);

    List<Producto> findKardexAjuste(ProductoFlt productoFlt);

    Integer sp_invAjusteInv(Map parametros);
}
