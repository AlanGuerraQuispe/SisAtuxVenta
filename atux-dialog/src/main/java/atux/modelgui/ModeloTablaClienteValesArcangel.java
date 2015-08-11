package atux.modelgui;

import atux.controllers.CCliente;
import atux.modelbd.Cliente;

public class ModeloTablaClienteValesArcangel extends ModeloTabla{

    String[] columnas = {"CODIGO", "TIPO DOCUMENTO", "RAZON SOCIAL", "N° DOCUMENTO","Nº Comprobante"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {200,150,600,300,300};
    
    public ModeloTablaClienteValesArcangel() {
        cc = new CCliente();
        this.nombreColumnas = columnas;       
        registros = ((CCliente)cc).getRegistros();
    }

    public ModeloTablaClienteValesArcangel(String Estado) {
        cc = new CCliente();
        this.nombreColumnas = columnas;
        if (Estado.length()==1){
            registros = ((CCliente)cc).getClienteValesArcangel(Estado);
        }else{
            registros = ((CCliente)cc).getBuscarClienteValesArcangel(Estado);
        }
    }

    public ModeloTablaClienteValesArcangel(int opcion) {
        cc = new CCliente();
        this.nombreColumnas = columnas;       
        registros = ((CCliente)cc).getRegistros();
    }
    
    public ModeloTablaClienteValesArcangel(String[] campo,Object[] valor) {
        cc = new CCliente();
        this.nombreColumnas = columnas;       
        registros = ((CCliente)cc).getRegistros(campo,valor);
    }

    public ModeloTablaClienteValesArcangel(int inicio,int finalPag) {
        cc = new CCliente();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CCliente)cc).getRegistros(null);
    }



    public ModeloTablaClienteValesArcangel(int opcion,int inicio,int finalPag) {
        cc = new CCliente();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CCliente)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CCliente)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CCliente)cc).getRegistros(null);
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
            case 0: return ((Cliente)registros.get(rowIndex)).getCoClienteLocal();
            case 1: return ((Cliente)registros.get(rowIndex)).getDeTipoDocumento();
            case 2: return ((Cliente)registros.get(rowIndex)).getDeRazonSocial();
            case 3: return ((Cliente)registros.get(rowIndex)).getNuDocIdentidad();
            case 4: return ((Cliente)registros.get(rowIndex)).getNuComprobante();
            default: return null;
        }
    }
}