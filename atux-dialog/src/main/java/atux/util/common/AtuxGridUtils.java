package atux.util.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class AtuxGridUtils {

  public AtuxGridUtils() {
  }

  /**
  *Permite controlar el avance o retroceso de la barra que ilumina la selección
  *de un registro.
  *@param <b>e</b> Objeto KeyEvent que almacena caracterÃ­sticas de la tecla presionada.
  *@param <b>pTabla</b> Objeto JTable sobre el cual se realizarÃ¡ el desplazamiento
  *                     de la barra que ilumina la selección del registro.
  *@param <b>pTextoDeBusqueda</b> Objeto JTextField que refleja la data del registro.
  *@param <b>pColumna</b> Variable int que almacena el valor de la columna cuyo valor será¡
  *                     asignado (mostrado) en el JTextField pTextoDeBusqueda.
  *
  *Ejemplo : Si queremos que conforme avanza o retrocede la barra de selección, el valor de
  *          determinado campo se muestre en un JTextField.
  *
  * void txtBusqueda_keyPressed(KeyEvent e) {
  *   GridUtils.aceptarTeclaPresionada(e, tblProductos, txtBusqueda, 2)
  * }
  *
  * ==> la tecla que se presione será¡ capturada por este Método (_keyPressed o _keyReleased)
  * ==> el valor que exista en la columna 2 del JTable "tblProductos" será¡ mostrado en el
  *     JTextField "txtProducto" ... "e" controla la tecla presionada (UP, DOWN, PAGEUP, 
  *     PAGEDOWN).
  * ==> si no se desea que el valor del Objeto JTextField sea actualizado ponemos :
  *     parámetro pTextoDeBusqueda = null
  *     parámetro pColumna =0
  */
  public static void aceptarTeclaPresionada(KeyEvent e, 
                                            JTable pTabla, 
                                            JTextField pTextoDeBusqueda, 
                                            int pColumna ) {
    if ( pTabla.getRowCount()<=0 )  return;
    
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      if ( pTabla.getRowCount()-1 > pTabla.getSelectedRow()) {
        showCell(pTabla, pTabla.getSelectedRow()+1,0);
        if ( pTextoDeBusqueda != null )
          setearTextoDeBusqueda(pTabla, pTextoDeBusqueda, pColumna);
      }      
    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
      if ( pTabla.getSelectedRow()>0 ) {
        showCell(pTabla, pTabla.getSelectedRow()-1,0);
        if ( pTextoDeBusqueda != null )
          setearTextoDeBusqueda(pTabla, pTextoDeBusqueda, pColumna);
      }      
    } else if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
      int vNum = (int)java.lang.Math.round(pTabla.getVisibleRect().getHeight() / (pTabla.getRowHeight() + pTabla.getRowMargin()));
      if ( ( pTabla.getRowCount() > pTabla.getSelectedRow() ) && ( (pTabla.getSelectedRow()+vNum) <= pTabla.getRowCount() ) )
        showCell(pTabla, pTabla.getSelectedRow()+vNum,0);
      else
        showCell(pTabla, pTabla.getRowCount()-1,0);
      if ( pTextoDeBusqueda != null )
        setearTextoDeBusqueda(pTabla, pTextoDeBusqueda, pColumna);    
    } else if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
      int vNum = (int)java.lang.Math.round(pTabla.getVisibleRect().getHeight() / (pTabla.getRowHeight() + pTabla.getRowMargin()));
      if ( ( pTabla.getSelectedRow()>0 ) && ( (pTabla.getSelectedRow()-vNum) >= 0 ) ) 
        showCell(pTabla, pTabla.getSelectedRow()-vNum,0);
      else
        showCell(pTabla, 0,0);
      if ( pTextoDeBusqueda != null )
        setearTextoDeBusqueda(pTabla, pTextoDeBusqueda, pColumna);      
    }    
  }
  
  public static void setearTextoDeBusqueda(JTable pTabla, JTextField pTextoDeBusqueda, int pColumna) {
        if (pTabla.getSelectedRow() >= 0) {
            showCell(pTabla, pTabla.getSelectedRow(), 0);
            pTextoDeBusqueda.setText(((String) pTabla.getValueAt(pTabla.getSelectedRow(), pColumna)).trim().toUpperCase());
            pTextoDeBusqueda.selectAll();
        }
    }

    public static void setearPrimerRegistro(JTable pTabla, JTextField pTextoDeBusqueda, int pColumna) {
        if (pTabla.getRowCount() > 0) {
            showCell(pTabla, 0, 0);
            if (pTextoDeBusqueda != null)
                setearTextoDeBusqueda(pTabla, pTextoDeBusqueda, pColumna);
        }
    }

  public static boolean buscarDescripcion (KeyEvent e, 
                                           JTable pTabla, 
                                           JTextField pTextoDeBusqueda, 
                                           int pColumna) {
    boolean findRecord = false;
    if ( (e.getKeyChar()!=KeyEvent.CHAR_UNDEFINED) && (e.getKeyCode()!=KeyEvent.VK_ENTER) ) {
      String vFindText = pTextoDeBusqueda.getText().toUpperCase();
      String vDescrip  = "";
      for (int k = 0; k < pTabla.getRowCount(); k++) {
        vDescrip = ((String)pTabla.getValueAt(k,pColumna)).toUpperCase().trim();
        if ( vDescrip.length()>=vFindText.length() ) {
          vDescrip = vDescrip.substring(0,vFindText.length());
          if (vFindText.equalsIgnoreCase(vDescrip)) {
            showCell(pTabla,k,0);
            findRecord = true;
            break;
          }
        }
      }
    }
    return findRecord;
  }

  public static void moveRowSelection(JTable pJTable, int pRow) {
    Rectangle rect = pJTable.getCellRect(pRow, 0, true);
    pJTable.scrollRectToVisible(rect);
    pJTable.clearSelection();
    pJTable.setRowSelectionInterval(pRow, pRow);
  }
  
    public static void showCell(JTable table, int row, int column) {
    if ( row>=table.getRowCount() ) row = table.getRowCount() - 1;
    Rectangle rect = table.getCellRect(row, column, true);
    table.scrollRectToVisible(rect);
    table.clearSelection();
    table.setRowSelectionInterval(row, row);
  }

}

 