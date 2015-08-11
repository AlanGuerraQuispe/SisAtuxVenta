package atux.trasladoproducto;

import javax.swing.UIManager;

import atux.managerbd.BaseConexion;
import atux.util.common.AtuxVariables;

/**
 * User: gmc
 * Date: 01/03/2010
 */
public class TrasladoProductosInterno {
    public static void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            BaseConexion.connect_string_thin = "jdbc:oracle:thin:ecventa/xp9087@192.169.0.196:1521:EDBPV021";
            BaseConexion.getConexion();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        AtuxVariables.vCodigoCompania = "001";
        AtuxVariables.vCodigoLocal = "021";
        AtuxVariables.vIdUsuario = "AW0001";
        AtuxVariables.vCoSucursal = "001";
        inicializarDatos();
        System.out.println("XXXX Fin de la inicializacion XXX");
    }


    private static void inicializarDatos(){
       /* try {
        	BaseConexion.getConexion();
            AtuxVariables.vTipoCambio = AtuxSearch.getTipoCambio(AtuxSearch.getFechaHoraBD(EckerdConstants.FORMATO_FECHA));
            VariablesVentas.tableModelListaGlobalProductos = new AtuxTableModel(ConstantsVentas.columnsPrecioProductos, ConstantsVentas.defaultValuesPrecioProductos, 0);

            DBVentas.loadListaPrecioProductos(VariablesVentas.tableModelListaGlobalProductos);
            Collections.sort(VariablesVentas.tableModelListaGlobalProductos.data, new AtuxTableComparator(2, true));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }*/
    }
}