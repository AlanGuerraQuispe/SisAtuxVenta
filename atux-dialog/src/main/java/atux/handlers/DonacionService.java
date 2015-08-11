package atux.handlers;

import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.PedidoVenta;
import com.atux.bean.donacion.Donacion;
import com.atux.bean.donacion.IngresarDonacionDetalle;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by MATRIX-JAVA on 16/2/2015.
 */
public interface DonacionService {

    public double calcularMontoSugerido(PedidoVenta pedidoVenta,double montoRedondear) ;


    void grabarDonacion(PedidoVenta pedidoVenta,List<IngresarDonacionDetalle> donacionList);

    void anularDonacion(String nuPedido);
}
