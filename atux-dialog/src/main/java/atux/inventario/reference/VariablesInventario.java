package atux.inventario.reference;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class VariablesInventario {

	/** Almacena el Codigo del Producto que se encuentre seleccionado */
	public static String vCodLab = "";

	/** Almacena el Codigo del Laboratorio que se encuentre seleccionado */
	public static String vDesLab = "";

	/** Almacena el Codigo del Producto que se encuentre seleccionado */
	public static String vCodigoProducto = "";

	/** Almacena la descripcion del Producto que se encuentre seleccionado */
	public static String vDescripProducto = "";

	/** Almacena el Número Pedido actualmente seleccionado */
	public static String vNumeroPedido = "0";

	/** Almacena el Número Recepcion de Producto */
	public static String vNumeroRecepProd = "";

	/** Almacena el Numnero de Transferencia de Productos */
	public static String vNumeroTransferencia = "";

	public static String vNumeroPedidoProd = "0";

	/** Almacena el Codigo del Almacen actualmente seleccionado */
	public static String vCodigoLocalAlmacen = "099";

	/** Almacena el Codigo del Cliente actualmente seleccionado */
	public static String vCodigoCliente = "";

	/** Almacena la Descripcion del Cliente actualmente seleccionado */
	public static String vDescripcionCliente = ConstantsInventario.NO_ESPECIFICADO;

	/** Almacena la Observacion referida al Cliente actualmente seleccionado */
	public static String vObservacionCliente = ConstantsInventario.NO_ESPECIFICADO;

	/** Almacena el Codigo de Local */
	public static String vCodLocal = "";

	/** Almacena la Descripcion del Local */
	public static String vDesLocal = "";

	/** Almacena el Número de local **/
	public static String vNoLocal = "";

	/** Almacena el Tipo de Local */
	public static String vLocal = "";

	public static String vTipoLocal = "";

	/** Almacena el Pedido de la transferencia - relacion de productos */

	public static ArrayList arrayTransferencia = new ArrayList();

	/**
	 * Almacena los productos seleccionados en la ventana
	 * DlgListaPrecioProd_Trans
	 */
	public static ArrayList arrayProductos = new ArrayList();

	/** Almacena los datos del producto seleccionado - transferencia */
	public static ArrayList arrayElementos = null;

	/**
	 * Guia de Ingreso Jorge Ruiz A. 29.09.03
	 */
	/** Almacena el Número de Guia de Ingreso */
	public static String vNumeroGuiaIngreso = "0";

	/** Almacena el valor de fraccion */
	public static int vValorFraccion = 0;

	/** Almacena el tipo de pantalla que voy a mostrar */
	public static int PANT_GUIA_INGRESO_MANUAL = 0;

	/** Almacena el tipo de pantalla que voy a mostrar */
	public static boolean PANT_SHOW_FRACCION = false;

	/** Almacena el tipo de Ingreso Manual */
	/**
	 * L Local de la Cadena P Proveedor C Cotizacion competencia A Almacen
	 * central
	 */
	public static String TIPO_INGRESO_MANUAL = "";

	/** Almacena flag para buscar por columna **/
	public static boolean vAceptaBuscar = false;

	/** Almacena la posicion de la columna que se va a buscar **/
	public static String vPosColumna = "";

	/** Almacena el nombre del InHashtable del combo **/
	public static String vNombreInHashtable = "";

	/** Almacena flag para ordenar columna **/
	public static boolean vAceptaOrdenar = false;

	/** Almacena la fecha inicial **/
	public static String vFechaIni = "";
	/** Almacena la fecha final **/
	public static String vFechaFin = "";

	public VariablesInventario() {

	}

}