package com.atux.dominio.converter;

import com.atux.bean.kardex.Producto;
import com.atux.bean.precios.Incentivo;
import com.atux.comun.context.AppCtx;
import com.atux.infraestructura.jpa.pojo.LgtdFarinV2;
import com.atux.infraestructura.jpa.pojo.LgtdFarinV2PK;
import com.atux.infraestructura.jpa.pojo.VttrProductoBono;
import com.atux.infraestructura.jpa.pojo.VttrProductoBonoPK;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by MATRIX-JAVA on 01/12/2014.
 */
public class IncentivoToVtttrProductoBono implements Converter<Incentivo,VttrProductoBono> {

    public VttrProductoBono convert(Incentivo incentivo) {
        VttrProductoBonoPK pk = new VttrProductoBonoPK();
        pk.setCoCompania(AppCtx.instance().getLocalId().getCoCompania());
        pk.setCoProducto(incentivo.getCoProducto());
        pk.setNuSecProdBono(incentivo.getNuSecProdBono());
        pk.setNuRevisionProducto(incentivo.getNuRevision());

        VttrProductoBono result= new VttrProductoBono();
        result.setId(pk);
        result.setVaBono(incentivo.getVaIncentivo());
        result.setFeInicio(incentivo.getFeInicio());
        result.setFeFin(incentivo.getFeFin());
        result.setEsProdBono("A");
        return result;
    }
}
