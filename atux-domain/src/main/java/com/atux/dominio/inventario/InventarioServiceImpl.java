package com.atux.dominio.inventario;

import com.atux.bean.inventario.LaboratorioInventarioFlt;
import com.atux.bean.inventario.TomaInventario;
import com.atux.bean.inventario.TomaInventarioLaboratorio;
import com.atux.bean.inventario.TomaInventarioProducto;
import com.atux.comun.context.AppCtx;
import com.atux.service.qryMapper.InventarioQryMapper;
import com.aw.core.domain.AWBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.*;

/**
 * Created by JAVA on 01/12/2014.
 */
@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    InventarioQryMapper inventarioQryMapper;

    @Autowired
    DataSource dataSource;

    private SimpleJdbcCall simpleJdbcCall;

    public String iniciarToma(LaboratorioInventarioFlt laboratorioInventarioFlt) {

        validarInicioToma(laboratorioInventarioFlt);

        TomaInventario tomaInventario = buildTomaInventario(laboratorioInventarioFlt);
        String nuSecTomaInventario = grabarInicioTomaCabecera(tomaInventario);
        tomaInventario.setNuSecTomaInventario(nuSecTomaInventario);
        tomaInventario.addLaboratorios(laboratorioInventarioFlt.getSeleccionados());
        List<TomaInventarioLaboratorio> laboratorioList = tomaInventario.getLaboratorioList();
        for (TomaInventarioLaboratorio tomaInventarioLaboratorio : laboratorioList) {
            inventarioQryMapper.grabarInicioTomaDetalle(tomaInventarioLaboratorio);
        }
        return nuSecTomaInventario;
    }

    private String grabarInicioTomaCabecera(TomaInventario tomaInventario) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PTOVTA_TOMAINVENTARIO")
                .withFunctionName("GRABA_TOMA_INVENTARIO")
                .declareParameters(new SqlParameter("codigocompania_in", Types.VARCHAR),
                        new SqlParameter("codigolocal_in", Types.VARCHAR),
                        new SqlParameter("tipoinventario_in", Types.VARCHAR),
                        new SqlParameter("idcongelainv_in", Types.VARCHAR)
                );
        simpleJdbcCall.compile();
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("codigocompania_in", tomaInventario.getCoCompania())
                .addValue("codigolocal_in", tomaInventario.getCoLocal())
                .addValue("tipoinventario_in", tomaInventario.getTiTomaInventario())
                .addValue("idcongelainv_in", tomaInventario.getCreadoPor());
        String name = simpleJdbcCall.executeFunction(String.class, in);
        return name;
    }

    public void grabarIngresoCantidad(TomaInventarioProducto tomaInventarioProducto) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PTOVTA_TOMAINVENTARIO")
                .withProcedureName("ACTUALIZA_PROD_LAB")
                .declareParameters(
                        new SqlParameter("codigocompania_in", Types.VARCHAR),
                        new SqlParameter("codigolocal_in", Types.VARCHAR),
                        new SqlParameter("secuenciatomainv_in", Types.VARCHAR),
                        new SqlParameter("codigolaboratorio_in", Types.VARCHAR),
                        new SqlParameter("codigoproducto_in", Types.VARCHAR),
                        new SqlParameter("cantprimeracuenta_in", Types.VARCHAR),
                        new SqlParameter("idtomainventario_in", Types.VARCHAR),
                        new SqlParameter("origen_in", Types.VARCHAR)
                );
        simpleJdbcCall.compile();
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("codigocompania_in", tomaInventarioProducto.getCoCompania())
                .addValue("codigolocal_in", AppCtx.instance().getCoLocal())
                .addValue("secuenciatomainv_in", tomaInventarioProducto.getNuSecTomaInventario())
                .addValue("codigolaboratorio_in", tomaInventarioProducto.getCoLaboratorio())
                .addValue("codigoproducto_in", tomaInventarioProducto.getCoProducto())
                .addValue("cantprimeracuenta_in", tomaInventarioProducto.getCantidad())
                .addValue("idtomainventario_in", AppCtx.instance().getUsuario().getIdUsuario())
                .addValue("origen_in", "D");
        simpleJdbcCall.execute(in);
    }

    private TomaInventario buildTomaInventario(LaboratorioInventarioFlt laboratorioInventarioFlt) {
        TomaInventario result = new TomaInventario();
        result.setCoCompania(AppCtx.instance().getCoCompania());
        result.setCoLocal(AppCtx.instance().getCoLocal());
        result.setTiTomaInventario(laboratorioInventarioFlt.getTiTomaInventario().getCodigo());
        result.setCreadoPor(AppCtx.instance().getUsuario().getIdUsuario());
        return result;
    }

    private void validarInicioToma(LaboratorioInventarioFlt laboratorioInventarioFlt) {

        List<String> laboratorioPedidoPendienteList = inventarioQryMapper.findLaboratorioConPedidoPendiente(laboratorioInventarioFlt);

        if (!laboratorioPedidoPendienteList.isEmpty()) {
            if (laboratorioPedidoPendienteList.size() < 5) {
                throw new AWBusinessException("Los siguientes laboratorios tienen pedidos pendientes:" + laboratorioPedidoPendienteList);
            } else {
                throw new AWBusinessException("Existen " + laboratorioPedidoPendienteList.size() + " con pedidos pendientes");
            }
        }

    }
}
