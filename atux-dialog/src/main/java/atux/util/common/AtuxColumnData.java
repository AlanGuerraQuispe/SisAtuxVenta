package atux.util.common;


public class AtuxColumnData {

  public String  m_title;
  public int m_width;
  public int m_alignment;

  /**
  *Constructor : Setea ancho y alineaci�n
  *@param <b>columns[]</b> Arreglo de t�tulos de columnas.
  *@param <b>defaultv</b> Arreglo de data por default, para cada columna.
  *@param <b>rows</b> N�mero de registros inicial.
  */
  public AtuxColumnData(String title, int width, int alignment) {
    m_title = title;
    m_width = width;
    m_alignment = alignment;
  }

}

