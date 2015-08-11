package atux.trasladoproducto.reference;

import java.util.ArrayList;


public class VariablesTrasladoProducto {
    /**
     * Almacena el Pedido de la transferencia - relacion de productos
     */
    public static ArrayList arrayTransferencia = new ArrayList();

    /**
     * Almacena los productos seleccionados
     */
    public static ArrayList arrayProductos = new ArrayList();

    /**
     * Almacena los datos del producto seleccionado - transferencia
     */
    public static ArrayList arrayElementos = null;

    /**
     * Almacena el Codigo de Local
     */
    public static String vCodLocal = "";
    /**
     * Almacena el Número de local*
     */
    public static String vNoLocal = "";


    public static String vTipoPedidoTraslado = "";
    /**
     * Almacena el Codigo del Producto que se encuentre seleccionado
     */
    public static String vCodigoProducto = "";

    /**
     * Para la búsqueda de locales se esto asumiendo puntos de venta
     */
    public static String vTipoLocal = "V";
}
