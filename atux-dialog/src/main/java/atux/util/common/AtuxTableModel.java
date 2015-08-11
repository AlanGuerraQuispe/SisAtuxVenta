package atux.util.common;

import javax.swing.table.AbstractTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AtuxTableModel extends AbstractTableModel {

  /** Almacena la data para la Tabla */
  public ArrayList data;

  /** Almacena los nombres de las columnas de la Tabla */
  private String[]   columnNames;
  private Boolean[]  columnsEditable;
  private Object[]   dataType;

  /**
  *Constructor : Inicializa la estructura de tabla, incluyendo n�mero de
  *columnas y cabecera de columnas.  Tambi�n inicializa la data de la tabla
  *con valores por defecto.
  *@param <b>columns[]</b> Arreglo de t�tulos de columnas.
  *@param <b>defaultv</b> Arreglo de data por default, para cada columna.
  *@param <b>rows</b> N�mero de registros inicial.
  */
  public AtuxTableModel(String columns[], Object defaultv[], int rows) {
    // Inicializa el n�mero de columnas y las cabeceras de columnas.
    columnNames = new String[columns.length];
    for( int i = 0; i < columns.length; i++ )
      columnNames[i] = new String(columns[i]);

    // Instancia Data Array List y popula con la data por default.
    data = new ArrayList();
    for( int i = 0; i < rows; i++ ) {
      ArrayList cols = new ArrayList();
      for( int j = 0; j < columns.length; j++ )
        cols.add(defaultv[j]);
    	data.add(cols);
    }

  }
  /**
  *Constructor : Inicializa la estructura de tabla, incluyendo n�mero de
  *columnas y cabecera de columnas.  Tambi�n inicializa la data de la tabla
  *con valores por defecto.  Inicializa ancho y alineaci�n de las columnas.
  *@param <b>columnData[]</b> Arreglo de objetos del tipo ColumnData.
  *@param <b>defaultv</b> Arreglo de data por default, para cada columna.
  *@param <b>rows</b> N�mero de registros inicial.
  *@param <b>columnsEditable</b> Arreglo si es editable o no la columna.
  *@param <b>dataType</b> Arreglo si es editable o no la columna.
  */
  public AtuxTableModel(AtuxColumnData columnData[], Object defaultv[], int rows, Boolean columnsEditable[], Object dataType[]) {
     this.columnsEditable = columnsEditable;
     this.dataType = dataType;

    // Inicializa el n�mero de columnas y las cabeceras de columnas.
    columnNames = new String[columnData.length];
    for( int i = 0; i < columnData.length; i++ )
      columnNames[i] = new String(columnData[i].m_title);

    // Instancia Data Array List y popula con la data por default.
    data = new ArrayList();
    for( int i = 0; i < rows; i++ ) {
      ArrayList cols = new ArrayList();
      for( int j = 0; j < columnData.length; j++ )
        cols.add(defaultv[j]);
    	data.add(cols);
    }

  }

  /**
  *Constructor : Inicializa la estructura de tabla, incluyendo n�mero de
  *columnas y cabecera de columnas.  Tambi�n inicializa la data de la tabla
  *con valores por defecto.  Inicializa ancho y alineaci�n de las columnas.
  *@param <b>columnData[]</b> Arreglo de objetos del tipo ColumnData.
  *@param <b>defaultv</b> Arreglo de data por default, para cada columna.
  *@param <b>rows</b> N�mero de registros inicial.
  */
  public AtuxTableModel(AtuxColumnData columnData[], Object defaultv[], int rows) {

    // Inicializa el n�mero de columnas y las cabeceras de columnas.
    columnNames = new String[columnData.length];
    for( int i = 0; i < columnData.length; i++ )
      columnNames[i] = new String(columnData[i].m_title);

    // Instancia Data Array List y popula con la data por default.
    data = new ArrayList();
    for( int i = 0; i < rows; i++ ) {
      ArrayList cols = new ArrayList();
      for( int j = 0; j < columnData.length; j++ )
        cols.add(defaultv[j]);
    	data.add(cols);
    }

  }

  /**
  *Sobreescribe el m�todo del AbstracTableModel.  Retorna el n�mero de
  *columnas de la tabla.
  *@return <b>int</b> N�mero de columnas en la tabla.
  */
  public int getColumnCount() {
    return columnNames.length;
  }

  /**
  *Sobreescribe el m�todo del AbstracTableModel.  Retorna el n�mero de
  *registros de la tabla.
  *@return <b>int</b> N�mero de registros de la tabla.
  */
  public int getRowCount() {
    return data.size();
  }

  /**
  *Sobreescribe el m�todo del AbstracTableModel.
  *@param <b>column number</b> N�mero de Columna.
  *@return <b>String</b> Nombre de la Columna.
  */
  public String getColumnName(int col){
    return columnNames[col];
  }

  /**
  *Sobreescribe el m�todo del AbstractModel.
  *@param <b>row</b> N�mero de registro.
  *@param <b>col</b> N�mero de columna.
  *@return <b>Object</b> Valor de una celda espec�fica.
  */
  public Object getValueAt(int row, int col) {
    ArrayList colArrayList = (ArrayList) data.get(row);
    return colArrayList.get(col);
  }

  /**
  *Sobreescribe el m�todo del AbstractModel.  Retorna la clase de una columna
  *espec�fica.
  *@param <b>col</b> N�mero de columna.
  *@return <b>Class</b> Clase de la columna especificada.
  */
  public Class getColumnClass(int col) {
    return getValueAt( 0, col).getClass();
  }

  /**
  *Sobreescribe el m�todo del AbstractModel.  Setea el valor de una celda
  *espec�fica.
  *@param <b>Object</b> Objeto a setear en la celda.
  *@param <b>row</b> N�mero de registro.
  *@param <b>col</b> N�mero de columna.
  */
  public void setValueAt( Object obj, int row, int col ) {
    if (dataType== null){
       ArrayList colArrayList = (ArrayList)data.get(row);
       colArrayList.set( col, obj);
    }
    else {
       String svalue = obj.toString();
       ArrayList colArrayList = (ArrayList)data.get(row);
       if (dataType[col] instanceof Integer){
          try {
              colArrayList.set( col, new Integer(svalue));
          }
          catch (NumberFormatException e) { 
          }
       }
       else if (dataType[col] instanceof Date){
          try {
              if (svalue.equals(""))
                  colArrayList.set(col, "");
              else {
                  SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
                  formatter.setLenient(false);
                  String dateString = formatter.format(formatter.parse(svalue));
                  colArrayList.set( col, dateString);
               }
          }
          catch (ParseException e) { 
          }
       }
       else {
          colArrayList.set( col, obj);
       }
    }
  }

  /**
  *Inserta un registro en la Tabla.
  *@param <b>newrow</b> Nuevo registro a insertar.
  */
  public void insertRow( ArrayList newrow ) {
    data.add(newrow);
    super.fireTableDataChanged();
  }

  /**
  *Elimina un registro de la Tabla.
  *@param <b>row</b> N�mero de registro a eliminar.
  */
  public void deleteRow(int row) {
    data.remove(row);
    super.fireTableDataChanged();
  }

  /**
  *Elimina todos los registros existentes despu�s del registro seleccionado.
  *@param <b>row</b> N�mero de registro a partir del cual se elimina el resto.
  */
  public void deleteAfterSelectedRow(int row){

    // Obtiene el tama�o inicial de la tabla antes de la eliminaci�n.
    int size = this.getRowCount();

    // El n�mero de registro a eliminar se obtiene incrementando en 1 el actual
    // registro seleccionado (enviado por par�metro).
    // Siempre se elimina el registro siguiente debido a que cada vez que se
    // elimina un registro todo lo restante sube una posici�n.  Esta operaci�n
    // se repite "n" veces hasta eliminar todos los registros restantes.
    int n = size-(row+1);
    for(int i=1;i<=n;i++){
      data.remove(row+1);
    }
    super.fireTableDataChanged();
  }

  /**
  *Retorna los valores de un n�mero de registro espec�fico.
  *@param <b>row</b> N�mero de registro.
  *@return <b>ArrayList</b> Arreglo de datos del registro.
  */
  public ArrayList getRow(int row) {
    return (ArrayList) data.get(row);
  }

  /**
  *Actualiza un registro espec�fico de la Tabla.  Se reemplaza el registro
  *del ArrayList por un nuevo registro.
  *@param <b>ArrayList</b> Arreglo de datos del nuevo registro.
  *@param <b>row</b> N�mero de registro.
  */
  public void updateRow( ArrayList updatedRow, int row ) {
    data.set( row, updatedRow);
    super.fireTableDataChanged();
  }

  /**
  *Inicializa la Tabla.
  */
  public void clearTable() {
    //data.clear();
    data = new ArrayList();
    super.fireTableDataChanged();
  }

  /**
  *Verifica si una celda es editable.
  */
  public boolean isCellEditable(int nRow, int nCol) {
    if (columnsEditable!=null)
       return columnsEditable[nCol].booleanValue();
    return false;
  }

  /**
  *Retorna la data de las columnas del Objeto AtuxTableModel.  Obtenemos
  *ciertos campos de un Objeto AtuxTableModel creado previamente.
  *
  *Ejemplo : 
  *Supongamos que ya existe el Objeto AtuxTableModel: tableModelProductos y
  *de este objeto necesitamos obtener la misma relaci�n pero solamente con algunas
  *columnas.  Para no realizar nuevamente una consulta a Base de Datos usamos :
  *
  * // queremos obtener la data de las columnas 1 y 2 la data obtenida la asignamos
  * //  a nuestro Objeto AtuxTableModel
  * int[] myColumns  = {1,2}; 
  *  
  * myTableModel.data = tableModelProductos.getDataWithSelectedColumn(myColumns);
  *
  *@param <b>pColumns[]</b> Contiene los n�meros de columnas a obtener.
  *@return <b>ArrayList</b> Arreglo de datos con las columnas solicitadas.
  */
  public ArrayList getDataWithSelectedColumn(int pColumns[]) {
    ArrayList myArray = getDataWithSelectedColumn(pColumns, false, 0);
    return myArray;
  }

  /**
  *Retorna la data de las columnas del Objeto AtuxTableModel.
  *@param <b>pColumns[]</b> Contiene los n�meros de columnas a obtener.
  *@param <b>pWithCheck</b> Indica si se incluir� un campo para CheckBox (al inicio).
  *@return <b>ArrayList</b> Arreglo de datos con las columnas solicitadas.
  */
  public ArrayList getDataWithSelectedColumn(int pColumns[],
                                             boolean pWithCheck) {
    return getDataWithSelectedColumn(pColumns, pWithCheck, 0);
  }

  /**
  *Retorna la data de las columnas del Objeto AtuxTableModel.
  *@param <b>pColumns[]</b> Contiene los n�meros de columnas a obtener.
  *@param <b>pWithCheck</b> Indica si se incluir� un campo para CheckBox (al inicio).
  *@param <b>pNumNewColumns</b> N�meros de columnas adicionales en blanco (al final).
  *@return <b>ArrayList</b> Arreglo de datos con las columnas solicitadas.
  */  
  public ArrayList getDataWithSelectedColumn(int pColumns[],
                                             boolean pWithCheck,
                                             int pNumNewColumns) {
    // Arreglo que ser� retornado con la data seleccionada
    ArrayList returnArray = new ArrayList();
    // Arreglo temporal para asignar nueva data al arreglo de retorno
    ArrayList tmpArray;
    // Arreglo temporal para almacenar el elemento actual de data
    ArrayList dataElement = new ArrayList();
    for (int i=0; i<data.size(); i++) {
      tmpArray = new ArrayList();
      dataElement = (ArrayList)data.get(i);
      if ( pWithCheck )  tmpArray.add(new Boolean(false));
      for (int col=0; col<pColumns.length; col++)
        tmpArray.add((String)dataElement.get(pColumns[col]));
      for (int newcol=0; newcol<pNumNewColumns; newcol++)
        tmpArray.add(" ");
      returnArray.add(tmpArray);
    }
    return returnArray;
  }

}
