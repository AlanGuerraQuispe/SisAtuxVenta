package atux.modelgui;

import atux.controllers.CCliente;
import atux.modelbd.Cliente;

import java.util.ArrayList;

public class ModeloTablaCliente extends ModeloTabla{

    String[] columnas = {"CODIGO", "TIPO DOCUMENTO", "RAZON SOCIAL", "N° DOCUMENTO"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;
    public static final Integer[] anchoColumnas  = {100,120,600,300};

    public ModeloTablaCliente() {
        cc = new CCliente();
        this.nombreColumnas = columnas;
        registros = ((CCliente)cc).getRegistros();
    }

    public ModeloTablaCliente(String Estado) {
        cc = new CCliente();
        this.nombreColumnas = columnas;
        if (Estado.length()==1){
            registros = ((CCliente)cc).getCliente(Estado);
        }else{
            registros = ((CCliente)cc).getBuscarCliente(Estado);
        }
    }

    public ModeloTablaCliente(ArrayList<Cliente> lstClientes) {
        this.nombreColumnas = columnas;
        registros = lstClientes;
    }

    public ModeloTablaCliente(int opcion) {
        cc = new CCliente();
        this.nombreColumnas = columnas;
        registros = ((CCliente)cc).getRegistros();
    }

    public ModeloTablaCliente(String[] campo,Object[] valor) {
        cc = new CCliente();
        this.nombreColumnas = columnas;
        registros = ((CCliente)cc).getRegistros(campo,valor);
    }

    public ModeloTablaCliente(int inicio,int finalPag) {
        cc = new CCliente();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CCliente)cc).getRegistros(null);
    }



    public ModeloTablaCliente(int opcion,int inicio,int finalPag) {
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
            default: return null;
        }
    }
}