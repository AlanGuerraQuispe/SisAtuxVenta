package com.atux.dominio.inventario;

import com.atux.bean.kardex.Kardex;
import com.atux.bean.kardex.Producto;
import com.atux.comun.context.AppCtx;
import com.atux.enums.KardexGrupoMotivoEnum;
import com.atux.enums.KardexMotivoEnum;
import com.atux.service.qryMapper.KardexQryMapper;
import com.aw.core.domain.AWBusinessException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JAVA on 01/12/2014.
 */
@Service
public class KardexServiceImpl implements KardexService {


    @Autowired
    KardexQryMapper kardexQryMapper;

    public int ajustarStock(Kardex kardex) {

        if(StringUtils.isBlank(kardex.getCoMotivoAjuste())){
            throw new AWBusinessException("Ingrese el motivo del ajuste");
        }

        Producto producto = kardex.getProducto();
        boolean inFraccionado = "S".equals(producto.getInProdFraccionado());
        KardexMotivoEnum kardexMotivo = null;
        double stock = 0;
        if (inFraccionado) {
            double stkEnteroActual = producto.getStkEnteroActual().doubleValue();
            double stkEnteroModificado = producto.getStkEntero().doubleValue();
            double vaFraccion=producto.getVaFraccion().doubleValue();
            double stkFraccionActual = producto.getStkFraccionActual().doubleValue();
            double stkFraccionModificado = producto.getStkFraccion().doubleValue();
            double stkActual=0;
            double stkModificado=0;
            if (stkEnteroModificado < 0) {
                throw new AWBusinessException("Ingrese una cantidad positiva");
            }
            if (stkFraccionModificado < 0) {
                throw new AWBusinessException("Ingrese una fracción positiva");
            }
            if (stkFraccionModificado > vaFraccion) {
                throw new AWBusinessException("Ingrese una fracción correcta");
            }
            stkActual = ((stkEnteroActual * vaFraccion) + stkFraccionActual);
            stkModificado = ((stkEnteroModificado * vaFraccion) + stkFraccionModificado);
            stock = (stkModificado - stkActual);
        } else {
            double stkEnteroActual = producto.getStkEnteroActual().doubleValue();
            double stkEnteroModificado = producto.getStkEntero().doubleValue();

            if (stkEnteroModificado < 0) {
                throw new AWBusinessException("Ingrese una cantidad positiva.");
            }
            stock = stkEnteroModificado - stkEnteroActual;
        }
        if (stock < 0) {
            kardexMotivo = KardexMotivoEnum.KARDEX_STOCK_NEGATIVO;
        } else if (stock >= 0) {
            kardexMotivo = KardexMotivoEnum.KARDEX_STOCK_POSITIVO;
        }
        Map parametros = new HashMap();
        parametros.put("caMovimiento", "");
        parametros.put("coCompania", AppCtx.instance().getLocalId().getCoCompania());
        parametros.put("coLocal", AppCtx.instance().getLocalId().getCoLocal());
        parametros.put("coProducto", producto.getCoProducto());
        parametros.put("stkEntero", producto.getStkEntero());
        parametros.put("stkFraccion", producto.getStkFraccion());
        parametros.put("tiDocumento", "");
        parametros.put("nuDocumento", "");
        parametros.put("justificacion", kardex.getJustificacion());
        parametros.put("coGrupoMotivoKardex", KardexGrupoMotivoEnum.KARDEX.getCodigo());
        parametros.put("coMotivoKardex", kardexMotivo != null ? kardexMotivo.getCodigo() : "");
        parametros.put("coGrupoMotivoAjuste", KardexGrupoMotivoEnum.AJUSTE_INVENTARIO.getCodigo());
        parametros.put("coMotivoAjuste", kardex.getCoMotivoAjuste());
        parametros.put("idUsuario", AppCtx.instance().getUsuario().getIdUsuario());

        Integer result = kardexQryMapper.sp_invAjusteInv(parametros);

        if(result==0){
            throw new AWBusinessException("No hay movimiento de productos");
        }
        return 0;
    }
}
