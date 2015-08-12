package com.atux.config;

import com.atux.comun.context.AppCtx;
import com.atux.config.dropdown.MetaLoaderDDAppImpl;
import com.atux.enums.EstadoTomaInventarioEnum;
import com.atux.enums.KardexGrupoMotivoEnum;
import com.atux.enums.TipoTomaInventarioEnum;
import com.aw.core.cache.dropdown.DropDownProvider;
import com.aw.core.cache.dropdown.DropDownRowImpl;
import com.aw.core.cache.loader.DataLoaderJDBCImpl;
import com.aw.core.cache.loader.DataLoaderMemoryImpl;
import org.springframework.stereotype.Component;

/**
 * Created by MATRIX-JAVA on 27/11/2014.
 */
@Component(value = "apdd")
public class APDD implements DropDownProvider {

    public MetaLoaderDDAppImpl ES_TABLA = new MetaLoaderDDAppImpl("notasalida.tiOrigen",
            new DataLoaderMemoryImpl()
                    .addRow(new DropDownRowImpl("A", "Activo"))
                    .addRow(new DropDownRowImpl("I", "Inactivo"))
    );

    public MetaLoaderDDAppImpl KARDEX_MOTIVO = new MetaLoaderDDAppImpl("comun.kardex.motivo",
            new DataLoaderJDBCImpl(
                    "SELECT " +
                            "CO_MOTIVO AS \"value\"," +
                            " DE_MOTIVO AS \"label\" " +
                            "FROM CMTR_MOTIVO " +
                            "WHERE CO_GRUPO_MOTIVO = ? ORDER BY 2",
                    DropDownRowImpl.class));

    public MetaLoaderDDAppImpl TOMA_INVENTARIO_TIPO = new MetaLoaderDDAppImpl("inventario.tipo",
            new DataLoaderMemoryImpl()
                    .addEnums(TipoTomaInventarioEnum.findAll()));

    public MetaLoaderDDAppImpl TOMA_INVENTARIO_ESTADO = new MetaLoaderDDAppImpl("inventario.estado",
            new DataLoaderMemoryImpl()
                    .addEnums(EstadoTomaInventarioEnum.findAll()));

    public MetaLoaderDDAppImpl KARDEX_MOTIVO_AJUSTE_INVENTARIO = new MetaLoaderDDAppImpl("comun.kardex.motivo.ajuste",
            new DataLoaderJDBCImpl(
                    "SELECT " +
                            "CO_MOTIVO AS \"value\"," +
                            " DE_MOTIVO AS \"label\" " +
                            "FROM CMTR_MOTIVO " +
                            "WHERE CO_GRUPO_MOTIVO = '" + KardexGrupoMotivoEnum.AJUSTE_INVENTARIO.getCodigo() + "' ORDER BY 2",
                    DropDownRowImpl.class));


    public MetaLoaderDDAppImpl KARDEX_GRUPO_MOTIVO = new MetaLoaderDDAppImpl("comun.kardex.grupo.motivo",
            new DataLoaderJDBCImpl(
                    "SELECT " +
                            "CO_GRUPO_MOTIVO AS \"value\"," +
                            " DE_GRUPO_MOTIVO AS \"label\" " +
                            "FROM cmtr_grupo_motivo " +
                            "ORDER BY 2 ",
                    DropDownRowImpl.class));

    public MetaLoaderDDAppImpl COMPROBANTE_TIPO = new MetaLoaderDDAppImpl("comun.comprobante.tipo",
            new DataLoaderJDBCImpl(
                    "SELECT " +
                            " TI_COMPROBANTE AS \"value\"," +
                            " DE_CORTA_TIPO_COMPROBANTE AS \"label\" " +
                            "FROM CMTR_TIPO_COMPROBANTE_PAGO " +
                            "ORDER BY 2 ",
                    DropDownRowImpl.class));

    public MetaLoaderDDAppImpl INSTITUCIONES = new MetaLoaderDDAppImpl("comun.instituciones",
            new DataLoaderJDBCImpl(
                    new StringBuilder().append(" SELECT ")
                            .append(" CI.CO_INSTITUCION AS \"value\",  ")
                            .append(" CI.DE_INSTITUCION As \"label\" ")
                            .append(" FROM ")
                            .append(" CVTR_INSTITUCION_LOCAL CIL, ")
                            .append(" CVTM_INSTITUCION CI ")
                            .append(" WHERE ")
                            .append(" 	 CIL.CO_COMPANIA='" + AppCtx.instance().getCoCompania() + "' ")
                            .append(" AND CIL.CO_LOCAL='" + AppCtx.instance().getCoLocal() + "' ")
                            .append(" AND SYSDATE BETWEEN TO_DATE(TO_CHAR(CIL.FE_INI_COBERTURA, 'dd/MM/yyyy') || ' 00:00:00', 'dd/MM/yyyy HH24:MI:SS') ")
                            .append(" AND TO_DATE(TO_CHAR(CIL.FE_FIN_COBERTURA,'dd/MM/yyyy')||' 23:59:59','dd/MM/yyyy HH24:MI:SS') ")
                            .append(" AND CIL.ES_COBERTURA='A' ")
                            .append(" AND CI.CO_INSTITUCION=CIL.CO_INSTITUCION ").toString(),
                    DropDownRowImpl.class));

}
