package atux.modelgui;

import atux.controllers.CProductoRegSanitario;
import atux.modelbd.ProductoRegSanitario;
import atux.util.common.AtuxUtility;

public class ModeloTablaProductoRegSanitario extends ModeloTabla{

    String[] columnas = {"Reg Sanitario","Fecha Vencimiento","Estado","Fecha Registro"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;
    public static final Integer[] anchoColumnas  = {400,200,100,200};

    public ModeloTablaProductoRegSanitario() {
        cc = new CProductoRegSanitario();
        this.nombreColumnas = columnas;
        registros = ((CProductoRegSanitario)cc).getRegistros();
    }

    public ModeloTablaProductoRegSanitario(int opcion) {
        cc = new CProductoRegSanitario();
        this.nombreColumnas = columnas;
        registros = ((CProductoRegSanitario)cc).getRegistros();
    }

    public ModeloTablaProductoRegSanitario(String[] campo,Object[] valor) {
        cc = new CProductoRegSanitario();
        this.nombreColumnas = columnas;
        registros = ((CProductoRegSanitario)cc).getRegistros(campo,valor);
    }

//    public ModeloTablaProductoRegSanitario(String Filtro) {
//        cc = new CProductoRegSanitario();
//        this.nombreColumnas = columnas;
//        registros = ((CProductoRegSanitario)cc).getRegistros(Filtro);
//    }

    public ModeloTablaProductoRegSanitario(int inicio,int finalPag) {
        cc = new CProductoRegSanitario();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CProductoRegSanitario)cc).getRegistros();
    }

    public ModeloTablaProductoRegSanitario(int opcion,int inicio,int finalPag) {
        cc = new CProductoRegSanitario();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0:
                registros = ((CProductoRegSanitario)cc).getRegistros(new Object[]{"I"});
                break;
            case 1:
                registros = ((CProductoRegSanitario)cc).getRegistros(new Object[]{"A"});
                break;
            default:
                registros = ((CProductoRegSanitario)cc).getRegistros();
        }

    }

    public int getCantidadRegistros()
    {
        return cc.getCantidadRegistros();
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)
        {
            case 0: return ((ProductoRegSanitario)registros.get(rowIndex)).getNuRegSanitario();
            case 1: return AtuxUtility.getDateToString(((ProductoRegSanitario)registros.get(rowIndex)).getFeVenRegSanitario(), "dd/MM/yyyy");
            case 2: return ((ProductoRegSanitario)registros.get(rowIndex)).getEsRegSanitario();
            case 3: return AtuxUtility.getDateToString(((ProductoRegSanitario)registros.get(rowIndex)).getFeCreaRegSanitario(), "dd/MM/yyyy");
            default: return null;

        }
    }
}
