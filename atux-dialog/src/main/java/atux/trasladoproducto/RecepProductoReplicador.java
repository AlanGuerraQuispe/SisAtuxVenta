package atux.trasladoproducto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import atux.trasladoproducto.reference.ConstantsTrasladoProducto;
import atux.trasladoproducto.reference.DBTrasladoProducto;

/**
 * Copyright (c) 2010 Eckerd Pero S.A.<br>
 * <br>
 * Funcionalidad : Clase utilitaria usada para facilitar la replicacion de data
 * al momento de recibir los productos <br>
 * <br>
 * Historico de Creacion/Modificacion<br>
 * <br>
 * ID    PROGRAMADOR    FECHA/HORA            TIPO                 OBSERVACIoN
 * 000    GMATEO       10/03/2010 10:00:00   Creacion <br>
 * 001    GMATEO       01/03/2011 10:00:00   Modificacion       Solo se debe replicar si el local destino no es almacon ni recetario magistral<br>
 * <br>
 *
 * @version 1.0<br>
 */
public class RecepProductoReplicador {
    private final Log logger = LogFactory.getLog(getClass());
    private String GRUPO_ACTUALIZAR_SOLITUD_PEDIDO_CABECERA = "RecProdUpdSolPedCab";
    private String GRUPO_ACTUALIZAR_RECEP_PRODUCTO_CABECERA = "RecProdUpdRecProdCab";
    private String GRUPO_ACTUALIZAR_RECEP_PRODUCTO_DETALLE = "RecProdUpdRecProdDet";
    private String GRUPO_ACTUALIZAR_SOLITUD_PEDIDO_DETALLE = "RecProdUpdSolPedDet";

    private String coCompania;
    private String coLocal;
    private String coLocalDestino;
    private String nuRecepcionProducto;
    private String nuSolicitudPedido;

    public RecepProductoReplicador(String coCompania, String coLocal, String coLocalDestino,
                                   String nuRecepcionProducto, String nuSolicitudPedido) {
        this.coCompania = coCompania;
        this.coLocal = coLocal;
        this.coLocalDestino = coLocalDestino;
        this.nuRecepcionProducto = nuRecepcionProducto;
        this.nuSolicitudPedido = nuSolicitudPedido;
    }

    /*public CmtsReplicacionPk[] replicarTodo(String tiSolicitudPedido) throws SQLException {
        if (!seDebeReplicar(tiSolicitudPedido)) {
            return null;
        }
        logger.info("Se va a replicar toda los datos de Recepcion Prod" + nuRecepcionProducto);
        CmtsReplicacionPk pk = DBTrasladoProducto.replicarRecepcionProductoUpdate(coCompania, coLocal, nuRecepcionProducto, coLocal, coLocalDestino);
        CmtsReplicacionPk pk2 = DBTrasladoProducto.replicarSolitudPedidoUpdate(coCompania, coLocal, nuSolicitudPedido, coLocal, coLocalDestino);
        return new CmtsReplicacionPk[]{pk, pk2};
    }


    public CmtsReplicacionPk[] replicarXProducto(boolean guiaFueCerrada, String tiSolicitudPedido, String coProducto, String nuPagina) throws SQLException{
        if (!seDebeReplicar(tiSolicitudPedido)) {
            return null;
        }
        logger.info("Se va a replicar Recepcion Prod " + nuRecepcionProducto + " para el Prod:" + coProducto);
        List list = new ArrayList();
        if (guiaFueCerrada) {
            CmtsReplicacionPk[] pks = replicarCabeceras();
            list.addAll(Arrays.asList(pks));
        }
        CmtsReplicacionPk[] pks = replicarRecProdDetalle(coProducto, nuPagina);
        list.addAll(Arrays.asList(pks));
        return convertirAArreglo(list);
    }


    private CmtsReplicacionPk[] replicarRecProdDetalle(String coProducto, String nuPagina) {
        StringBuffer where = new StringBuffer();
        where.append(getRecepcionProductoPkAsWhere());
        where.append(" and CO_PRODUCTO = '" + coProducto + "' ");
        where.append(" and NU_PAGINA_GUIA = " + nuPagina);
        String deParametrosDetalle = "TABLA::LGTD_RECEPCION_PRODUCTO,WHERE::<text>" + where.toString() + "</text>,USAR_PK::ORIGEN;";
        CmtsReplicacionPk pk1 = replicar(GRUPO_ACTUALIZAR_RECEP_PRODUCTO_DETALLE, (getLgtcRecepcionProductoPKAsStr() + "-" + coProducto + "-" + nuPagina),
                deParametrosDetalle);
        where = new StringBuffer();
        where.append(getSolicitudPedidoPkAsWhere());
        where.append(" and CO_PRODUCTO = '" + coProducto + "' ");
        deParametrosDetalle = "TABLA::LGTD_SOLICITUD_PEDIDO,WHERE::<text>" + where.toString() + "</text>,USAR_PK::ORIGEN;";
        CmtsReplicacionPk pk2 = replicar(GRUPO_ACTUALIZAR_SOLITUD_PEDIDO_DETALLE, (getLgtcSolicitudPedidoPKAsStr() + "-" + coProducto),
                deParametrosDetalle);
        return new CmtsReplicacionPk[]{pk1, pk2};
    }

    private CmtsReplicacionPk[] replicarRecProdDetalle(String nuPagina) {
        StringBuffer whereRecepcionProd = new StringBuffer();
        whereRecepcionProd.append(getRecepcionProductoPkAsWhere());
        whereRecepcionProd.append(" and NU_PAGINA_GUIA = " + nuPagina);
        String deParametrosDetalle = "TABLA::LGTD_RECEPCION_PRODUCTO,WHERE::<text>" + whereRecepcionProd.toString() + "</text>,USAR_PK::ORIGEN;";
        CmtsReplicacionPk pk1 = replicar(GRUPO_ACTUALIZAR_RECEP_PRODUCTO_DETALLE, (getLgtcRecepcionProductoPKAsStr() + "-" + nuPagina),
                deParametrosDetalle);
        StringBuffer where = new StringBuffer();
        where.append(getSolicitudPedidoPkAsWhere());
        where.append(" and NU_ITEM_SOLICITUD_PEDIDO in  ");
        where.append(" (SELECT NU_ITEM_SOLICITUD_PEDIDO FROM LGTD_RECEPCION_PRODUCTO " +
                " WHERE " + whereRecepcionProd + " ) ");
        deParametrosDetalle = "TABLA::LGTD_SOLICITUD_PEDIDO,WHERE::<text>" + where.toString() + "</text>,USAR_PK::ORIGEN;";
        CmtsReplicacionPk pk2 = replicar(GRUPO_ACTUALIZAR_SOLITUD_PEDIDO_DETALLE, (getLgtcSolicitudPedidoPKAsStr()),
                deParametrosDetalle);
        return new CmtsReplicacionPk[]{pk1, pk2};
    }


    private CmtsReplicacionPk[] replicarCabeceras() {
        logger.info("Se va a replicar las cabeceras de Recepcion Prod/Sol Pedido" + nuRecepcionProducto + "/" + nuSolicitudPedido);
        CmtsReplicacionPk pk1 = replicar(GRUPO_ACTUALIZAR_SOLITUD_PEDIDO_CABECERA, getLgtcSolicitudPedidoPKAsStr(), getDeParamsParaActSolPedidoCabecera());
        CmtsReplicacionPk pk2 = replicar(GRUPO_ACTUALIZAR_RECEP_PRODUCTO_CABECERA, getLgtcRecepcionProductoPKAsStr(), getDeParamsParaActRecProdCabecera());
        return new CmtsReplicacionPk[]{pk1, pk2};
    }

    public CmtsReplicacionPk[] replicarXPagina(boolean guiaFueCerrada, String tiSolicitudPedido, String nuPagina) throws SQLException{
        if (!seDebeReplicar(tiSolicitudPedido)) {
            return null;
        }
        logger.info("Se va a replicar Recepcion Prod" + nuRecepcionProducto + " Página:" + nuPagina);
        List list = new ArrayList();
        if (guiaFueCerrada) {
            CmtsReplicacionPk[] pks = replicarCabeceras();
            list.addAll(Arrays.asList(pks));
        }
        CmtsReplicacionPk[] pks = replicarRecProdDetalle(nuPagina);
        list.addAll(Arrays.asList(pks));
        return convertirAArreglo(list);
    }

    private CmtsReplicacionPk replicar(String grupoReplicacion, String idDataReplicar, String deParametros) {
        return DBTrasladoProducto.replicarGenerico(coCompania, coLocalDestino, grupoReplicacion, idDataReplicar, deParametros);
    }

    private boolean seDebeReplicar(String tiSolicitudPedido) throws SQLException {
        return tiSolicitudPedido != null &&
               !"".equals(tiSolicitudPedido) &&
               !ConstantsTrasladoProducto.esPedidoXSobranteOFaltanteCD(tiSolicitudPedido)&&
               !DBTrasladoProducto.esAlmacenORecetario(coLocalDestino);
    }*/

    private String getLgtcSolicitudPedidoPKAsStr() {
        return coCompania + "-" + coLocal + "-" + nuSolicitudPedido;
    }

    private String getLgtcRecepcionProductoPKAsStr() {
        return coCompania + "-" + coLocal + "-" + nuRecepcionProducto;
    }


    private String getDeParamsParaActSolPedidoCabecera() {
        String deParametrosCabecera = "TABLA::LGTC_SOLICITUD_PEDIDO,WHERE::<text>" + getSolicitudPedidoPkAsWhere() + "</text>,USAR_PK::ORIGEN;";
        return deParametrosCabecera;
    }

    private String getDeParamsParaActRecProdCabecera() {
        String deParametrosCabecera = "TABLA::LGTC_RECEPCION_PRODUCTO,WHERE::<text>" + getRecepcionProductoPkAsWhere() + "</text>,USAR_PK::ORIGEN;";
        return deParametrosCabecera;
    }


    private String getSolicitudPedidoPkAsWhere() {
        return "CO_COMPANIA='" + coCompania + "' and CO_LOCAL='" + coLocal + "' and  NU_SOLICITUD_PEDIDO='" + nuSolicitudPedido + "'";
    }

    private String getRecepcionProductoPkAsWhere() {
        return "CO_COMPANIA='" + coCompania + "' and CO_LOCAL='" + coLocal + "' and  NU_RECEPCION_PRODUCTO='" + nuRecepcionProducto + "'";
    }

    /*private CmtsReplicacionPk[] convertirAArreglo(List list) {
        CmtsReplicacionPk[] arreglo = new CmtsReplicacionPk[list.size()];
        for (int i = 0; i < list.size(); i++) {
            CmtsReplicacionPk o = (CmtsReplicacionPk) list.get(i);
            arreglo[i] = o;
        }
        return arreglo;
    }*/

}
