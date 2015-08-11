package atux.handlers;

import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.PedidoVenta;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by MATRIX-JAVA on 16/2/2015.
 */
public interface PedidoVentaInterceptor {

    public void procesar(PedidoVenta pedidoVenta, DetallePedidoVenta item,Map result) throws Exception;

    public void quitar(PedidoVenta pedidoVenta, DetallePedidoVenta item,Map result);
}
