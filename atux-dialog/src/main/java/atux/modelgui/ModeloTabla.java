package atux.modelgui;

import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public abstract class ModeloTabla<M extends JAbstractModelBD> extends AbstractTableModel{
    protected ArrayList<M> registros;
    protected String[] nombreColumnas;
    protected JAbstractController cc;

    public ModeloTabla(ArrayList<M> registros) {
        this.registros = registros;
    }

    public ModeloTabla() {
    }

    @Override
    public int getRowCount() {
        return registros.size();
    }

    @Override
    public int getColumnCount() {
        return nombreColumnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return nombreColumnas[column];
    }
    
    public void borrarFila(int fila){
        registros.remove(fila);
        cc.borrarRegistro(registros.get(fila));
        fireTableRowsDeleted(fila, fila);
    }
    
    public void quitarFila(int fila)
    {
        registros.remove(fila);
        fireTableRowsDeleted(fila, fila);
    }
    
    public JAbstractModelBD getFila(int fila)
    {
        return registros.get(fila);
    }

    public void setFila(Object fila)
    {
        registros.add((M) fila);
    }

    public ArrayList<M> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<M> registros) {
        this.registros = registros;
    }
}
