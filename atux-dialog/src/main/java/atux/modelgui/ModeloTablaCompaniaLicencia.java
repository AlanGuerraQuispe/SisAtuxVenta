package atux.modelgui;

import atux.controllers.CCompaniaLicencia;
import atux.modelbd.CompaniaLicencia;
import atux.util.common.AtuxUtility;

public class ModeloTablaCompaniaLicencia extends ModeloTabla{

//    String[] columnas = {"NU_LICENCIA","NU_SERIE_LICENCIA","ES_LICENCIA","FE_CREA_LICENCIA"};
    String[] columnas = {"Orden","Serie Licencia","Estado","Fecha Creación"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {50,600,100,150};
    
    public ModeloTablaCompaniaLicencia() {
        cc = new CCompaniaLicencia();
        this.nombreColumnas = columnas;       
        registros = ((CCompaniaLicencia)cc).getRegistros();
    }
    
    public ModeloTablaCompaniaLicencia(int opcion) {
        cc = new CCompaniaLicencia();
        this.nombreColumnas = columnas;       
        registros = ((CCompaniaLicencia)cc).getRegistros();
    }
    
    public ModeloTablaCompaniaLicencia(String[] campo,Object[] valor) {
        cc = new CCompaniaLicencia();
        this.nombreColumnas = columnas;       
        registros = ((CCompaniaLicencia)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaCompaniaLicencia(int inicio,int finalPag) {
        cc = new CCompaniaLicencia();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CCompaniaLicencia)cc).getRegistros("");
    }

    //    public ModeloTablaCompaniaLicencia(String Filtro) {
//        cc = new CCompaniaLicencia();
//        this.nombreColumnas = columnas;
//        registros = ((CCompaniaLicencia)cc).getBusquedaLocales(Filtro);
//    }        
    public ModeloTablaCompaniaLicencia(int opcion,int inicio,int finalPag) {
        cc = new CCompaniaLicencia();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CCompaniaLicencia)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CCompaniaLicencia)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CCompaniaLicencia)cc).getRegistros("");
        }
        
    }
    
    public int getCantidadRegistros(){
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
            case 0: return ((CompaniaLicencia)registros.get(rowIndex)).getNuLicencia();
            case 1: return ((CompaniaLicencia)registros.get(rowIndex)).getNuSerieLicencia();     
            case 2: return ((CompaniaLicencia)registros.get(rowIndex)).getEsLicencia().equals("A") ? "Activo" : "Inactivo";  
            case 3: return AtuxUtility.getDateToString(((CompaniaLicencia)registros.get(rowIndex)).getFeCreaLicencia(), "dd/MM/yyyy");            
            default: return null;   
        }
    }
    
}
