package atux.util.common;

import atux.util.print.AtuxPRNUtility;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;


public class AtuxUtility {
    
    static int rowWithColor = 0;

    public AtuxUtility() {
    }

    public static String formatNumber(double pValue) {
        java.text.DecimalFormat formatDecimal = new java.text.DecimalFormat(",##0.00");
        java.text.DecimalFormatSymbols symbols = new java.text.DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        formatDecimal.setDecimalFormatSymbols(symbols);
        return formatDecimal.format(pValue).toString();
    }

    public static String formatNumber(double pValue, int pDecimales) {
        java.text.DecimalFormat formatDecimal = new java.text.DecimalFormat(",##0." + caracterIzquierda("", pDecimales, "0"));
        java.text.DecimalFormatSymbols symbols = new java.text.DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        formatDecimal.setDecimalFormatSymbols(symbols);
        return formatDecimal.format(pValue).toString();
    }

    public static String formatNumber(double pValue, String pFormatNumber) {
        java.text.DecimalFormat formatDecimal = new java.text.DecimalFormat(pFormatNumber);
        java.text.DecimalFormatSymbols symbols = new java.text.DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        formatDecimal.setDecimalFormatSymbols(symbols);
        return formatDecimal.format(pValue).toString();
    }

    public static double getDecimalNumberRedondeado(double pDecimal) {
        return AtuxUtility.getDecimalNumber(AtuxUtility.formatNumber(pDecimal));
    }

    public static double getDecimalNumber(String pDecimal) {
        double decimalNumber = 0.00;
        try {
            java.text.DecimalFormat formatDecimal = new java.text.DecimalFormat("###,###,##0.00");
            java.text.DecimalFormatSymbols symbols = new java.text.DecimalFormatSymbols();
            symbols.setGroupingSeparator(',');
            symbols.setDecimalSeparator('.');
            formatDecimal.setDecimalFormatSymbols(symbols);
            decimalNumber = formatDecimal.parse(pDecimal).doubleValue();
        } catch (ParseException errParse) {
            errParse.printStackTrace();
        }
        return decimalNumber;
    }

    public static double getDecimalNumber(String pDecimal, int pDecimales) {
        double decimalNumber = 0.00;
        try {
            java.text.DecimalFormat formatDecimal = new java.text.DecimalFormat("###,##0." + caracterIzquierda("", pDecimales, "0"));
            java.text.DecimalFormatSymbols symbols = new java.text.DecimalFormatSymbols();

            symbols.setGroupingSeparator(',');
            symbols.setDecimalSeparator('.');
            formatDecimal.setDecimalFormatSymbols(symbols);
            decimalNumber = formatDecimal.parse(pDecimal).doubleValue();
        } catch (ParseException errParse) {
            errParse.printStackTrace();
        }
        return decimalNumber;
    }

    public static void centrarVentana(JDialog pDialog) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension objectSize = pDialog.getSize();
        if (objectSize.height > screenSize.height) {
            objectSize.height = screenSize.height;
        }
        if (objectSize.width > screenSize.width) {
            objectSize.width = screenSize.width;
        }
        pDialog.setLocation((((screenSize.width - objectSize.width) / 2)-40) , (screenSize.height - objectSize.height) / 2 );
    }

    public static void setearTextoDeBusqueda(JTable pTabla, JTextField pTextoDeBusqueda, int pColumna) {
        if (pTabla.getSelectedRow() >= 0) {
            AtuxGridUtils.showCell(pTabla, pTabla.getSelectedRow(), 0);
            pTextoDeBusqueda.setText(((String) pTabla.getValueAt(pTabla.getSelectedRow(), pColumna)).trim().toUpperCase());
            pTextoDeBusqueda.selectAll();
        }
    }

    public static void setearPrimerRegistro(JTable pTabla, JTextField pTextoDeBusqueda, int pColumna) {
        if (pTabla.getRowCount() > 0) {
            AtuxGridUtils.showCell(pTabla, 0, 0);
            if (pTextoDeBusqueda != null)
                setearTextoDeBusqueda(pTabla, pTextoDeBusqueda, pColumna);
        }
    }

    public static void setearActualRegistro(JTable pTabla, JTextField pTextoDeBusqueda, int pColumna) {
        if (pTabla.getRowCount() > 0) {
            AtuxGridUtils.showCell(pTabla, pTabla.getSelectedRow(), 0);
            if (pTextoDeBusqueda != null)
                setearTextoDeBusqueda(pTabla, pTextoDeBusqueda, pColumna);
        }
    }

    public static void setearRegistro(JTable pTabla, int pRowSelected, JTextField pTextoDeBusqueda, int pColumna) {
        if (pTabla.getRowCount() > 0) {
            AtuxGridUtils.showCell(pTabla, pRowSelected, 0);
            if (pTextoDeBusqueda != null)
                setearTextoDeBusqueda(pTabla, pTextoDeBusqueda, pColumna);
        }
    }

    /**
     * Permite restringir la digitación a sólo dígitos.
     */
    public static void admitirSoloDigitos(KeyEvent e, JTextField textField, int longitudTexto, JDialog dialog) {
        // Comparamos la longitud del texto antes y despues de presionar una tecla
        // El objetivo es controlar los caracteres no válidos.  Para las teclas Bloq.Mayús,
        // Insert, Supr, Inicio, Fin, etc ... son teclas que no generan un caracter pero sí
        // un objeto KeyEvent ...
        if (longitudTexto < textField.getText().length()) {
            char keyChar = e.getKeyChar();
            if (!Character.isDigit(keyChar)) {
                e.consume();
                admitirMensaje(keyChar, textField, "La tecla presionada no es válida !!!.  Solo se aceptan Números.", dialog);
            }
        }
    }

    public static boolean esDigito(KeyEvent e, JTextField textField, int longitudTexto, JDialog dialog) {
        boolean digitoValido = true;
        if (longitudTexto < textField.getText().length()) {
            char keyChar = e.getKeyChar();
            if (!Character.isDigit(keyChar)) {
                digitoValido = false;
                e.consume();
                admitirMensaje(keyChar, textField, "La tecla presionada no es válida !!!.  Solo se aceptan Números.", dialog);
            }
        }
        return digitoValido;
    }

    /**
     * Permite restringir la digitación a sólo dígitos (decimales).
     */
    public static void admitirSoloDecimales(KeyEvent e, JTextField textField, int longitudTexto, JDialog dialog) {
        // Comparamos la longitud del texto antes y despues de presionar una tecla
        // El objetivo es controlar los caracteres no válidos.  Para las teclas Bloq.Mayús,
        // Insert, Supr, Inicio, Fin, etc ... son teclas que no generan un caracter pero sí
        // un objeto KeyEvent ...
        if (longitudTexto < textField.getText().length()) {
            char keyChar = e.getKeyChar();
            //if (!Character.isDigit(keyChar) && (e.getKeyCode()!=KeyEvent.VK_DECIMAL)) {
            if (!Character.isDigit(keyChar) && (e.getKeyCode() != KeyEvent.VK_DECIMAL) && (e.getKeyCode() != 46)) {
                e.consume();
                admitirMensaje(keyChar, textField, "La tecla presionada no es válida !!!.  Solo se aceptan Números.", dialog);
            } else {
                int posPunto = textField.getText().indexOf(".") + 1;
                int lenTexto = textField.getText().length();
                if ((posPunto > 0) && ((lenTexto - posPunto) > 2)) {
                    admitirMensaje(keyChar, textField, "Solo se aceptan dos (2) decimales !!!", dialog);
                }
            }
        }
    }

    /**
     * Permite restringir la digitación a sólo dígitos (decimales).
     */
    public static void admitirSoloDecimales(KeyEvent e, JTextField textField, int longitudTexto, JDialog dialog, int pCantDecimales) {
        // Comparamos la longitud del texto antes y despues de presionar una tecla
        // El objetivo es controlar los caracteres no válidos.  Para las teclas Bloq.Mayús,
        // Insert, Supr, Inicio, Fin, etc ... son teclas que no generan un caracter pero sí
        // un objeto KeyEvent ...
        if (longitudTexto < textField.getText().length()) {
            char keyChar = e.getKeyChar();
            if (!Character.isDigit(keyChar) && (e.getKeyCode() != KeyEvent.VK_DECIMAL) && (e.getKeyCode() != 46)) {
                e.consume();
                admitirMensaje(keyChar, textField, "La tecla presionada no es válida !!!.  Solo se aceptan Números.", dialog);
            } else {
                int posPunto = textField.getText().indexOf(".") + 1;
                int lenTexto = textField.getText().length();
                if ((posPunto > 0) && ((lenTexto - posPunto) > pCantDecimales)) {
                    admitirMensaje(keyChar, textField, "Solo se aceptan dos (" + pCantDecimales + ") decimales !!!", dialog);
                }
            }
        }
    }

    /**
     * Permite restringir la digitación del Ãºltimo digito de la parte decimal.
     *
     * @return <b>boolean</b> Si es valido el digito devuelve true caso contrario devuelve false.
     */
    public static boolean validaCentimos(JTextField pMonto) {
        boolean valorRetorno = true;
        if (pMonto.getText().indexOf(".") >= 0) {
            if ((pMonto.getText().indexOf(".") + 3) > pMonto.getText().length())
                pMonto.setText(pMonto.getText() + "0");
            
            if (
                    (Integer.parseInt(pMonto.getText().trim().substring(pMonto.getText().trim().length() - 1, pMonto.getText().trim().length())) != 0)
                            &&
                            (Integer.parseInt(pMonto.getText().trim().substring(pMonto.getText().trim().length() - 1, pMonto.getText().trim().length())) != 5)
                    )
                valorRetorno = false;

        }
        return valorRetorno;
    }

    /**
     * Permite restringir la digitación a sólo letras.
     */
    public static void admitirSoloLetras(KeyEvent e, JTextField textField, int longitudTexto, JDialog dialog) {
        // Comparamos la longitud del texto antes y despues de presionar una tecla
        // El objetivo es controlar los caracteres no válidos.  Para las teclas Bloq.Mayús,
        // Insert, Supr, Inicio, Fin, etc ... son teclas que no generan un caracter pero sí
        // un objeto KeyEvent ...
        if (!(e.getKeyCode() == KeyEvent.VK_SHIFT) && !(e.getKeyCode() == KeyEvent.VK_ALT)) {
            if (longitudTexto < textField.getText().length()) {
                char keyChar = e.getKeyChar();
                if (!Character.isLetter(keyChar) && !Character.isSpace(keyChar)) {
                    e.consume();
                    admitirMensaje(keyChar, textField, "La tecla presionada no es válida !!!.  Solo se aceptan letras y espacios en blanco.", dialog);
                }
            }
        }
    }

    public static void ponerMayuscula(KeyEvent e, JTextField textField) {
        char keyChar = e.getKeyChar();
        if (Character.isLetter(keyChar)) textField.setText(textField.getText().trim().toUpperCase());
    }

    /**
     * Permite mostrar un mensaje de error en caso el tipo de dato digitado no sea el
     * que corresponda.
     */
    private static void admitirMensaje(char keyChar, JTextField textField, String mensaje, JDialog dialog) {
        if (!Character.isISOControl(keyChar)) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(dialog, mensaje, "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            // en caso de haber digitado un caracter no válido, este serï¿½ borrado luego de aceptar
            // el mensaje del sistema
            textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
            textField.requestFocus();
        }
    }

    public static boolean validateNumber(JDialog pJDialog,
                                         JTextField pJTextField,
                                         String pMessage,
                                         boolean pIsMandatory) {
        boolean returnValue = false;
        String tempNumber = pJTextField.getText().trim();
        if (!isLong(tempNumber)) showMessage(pJDialog, pMessage, pJTextField);
        else {
            if (pIsMandatory) {
                if (Long.parseLong(tempNumber) <= 0) showMessage(pJDialog, pMessage, pJTextField);
                else returnValue = true;
            } else returnValue = true;
        }
        return returnValue;
    }

    public static boolean validateDecimal(JDialog pJDialog,
                                          JTextField pJTextField,
                                          String pMessage,
                                          boolean pIsMandatory) {
        boolean returnValue = false;
        String tempNumber = String.valueOf(getDecimalNumber(pJTextField.getText().trim()));
        if (!isDouble(tempNumber)) showMessage(pJDialog, pMessage, pJTextField);
        else {
            if (pIsMandatory) {
                if (Double.parseDouble(tempNumber) <= 0.00) showMessage(pJDialog, pMessage, pJTextField);
                else returnValue = true;
            } else returnValue = true;
        }
        return returnValue;
    }

    public static boolean isLong(String pValue) {
        try {
            if (!pValue.equals("")) {
                long valor = Long.parseLong(pValue);
                return true;
            } else return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDouble(String pValue) {
        try {
            if (!pValue.equals("")) {
                double valor = Double.parseDouble(pValue);
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isDoubleWhitFormat(String pValue) {
        try {
            if (!pValue.equals("")) {
                java.text.DecimalFormat formatDecimal = new java.text.DecimalFormat("###,##0.00");
                java.text.DecimalFormatSymbols symbols = new java.text.DecimalFormatSymbols();
                symbols.setGroupingSeparator(',');
                symbols.setDecimalSeparator('.');
                formatDecimal.setDecimalFormatSymbols(symbols);
                double valor = formatDecimal.parse(pValue).doubleValue();
//        double valor = Double.parseDouble(pValue);
                return true;
            } else return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static void showMessageComponent(Component pComponent, String pMessage, Object pObject) {
        JOptionPane.showMessageDialog(pComponent, pMessage, "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        if (pObject != null) moveFocus(pObject);
    }
    
    public static void showMessage(JDialog pJDialog, String pMessage, Object pObject) {
        JOptionPane.showMessageDialog(pJDialog, pMessage, "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        if (pObject != null) moveFocus(pObject);
    }

    public static void showErrorMessage(JDialog pJDialog, String pMessage, Object pObject) {
        JOptionPane.showMessageDialog(pJDialog, pMessage, "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
        if (pObject != null) moveFocus(pObject);
    }

    public static void showInformationMessage(JDialog pJDialog, String pMessage, Object pObject) {
        JOptionPane.showMessageDialog(pJDialog, pMessage, "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
        if (pObject != null) moveFocus(pObject);
    }

    public static void showInformationMessage(Frame pFrame, String pMessage, Object pObject) {
        JOptionPane.showMessageDialog(pFrame, pMessage, "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
        if (pObject != null) moveFocus(pObject);
    }

    public static void showWarningMessage(JDialog pJDialog, String pMessage, Object pObject) {
        JOptionPane.showMessageDialog(pJDialog, pMessage, "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        if (pObject != null) moveFocus(pObject);
    }

    public static void showQuestionErrorMessage(JDialog pJDialog, String pMessage, Object pObject) {
        JOptionPane.showMessageDialog(pJDialog, pMessage, "Mensaje del Sistema", JOptionPane.QUESTION_MESSAGE);
        if (pObject != null) moveFocus(pObject);
    }

    public static void showPlainMessage(JDialog pJDialog, String pMessage, Object pObject) {
        JOptionPane.showMessageDialog(pJDialog, pMessage, "Mensaje del Sistema", JOptionPane.PLAIN_MESSAGE);
        if (pObject != null) moveFocus(pObject);
    }  

    /**
     * Permite seleccionar o deseleccionar una celda del tipo JCheckBox.
     * <p/>
     * permitirï¿½ seleccionar el actual registro, es decir,
     * un registro a la vez (multiselecciï¿½n o no ...);
     */
    public static void setCheckValue(JTable pTable, boolean pOnlyCurrentRow) {
        // Asignaciï¿½n de valores antes de proceder o no con la inicializaciï¿½n
        // de la celda JCheckBox de cada uno de los registros del JTable.
        int selectedRow = pTable.getSelectedRow();
        Boolean valor = (Boolean) pTable.getValueAt(selectedRow, 0);
        if (!valor.booleanValue()) {
            if (pOnlyCurrentRow) {
                // Si no es multiselecciï¿½n se eliminan todos los check.
                for (int i = 0; i < pTable.getRowCount(); i++)
                    pTable.setValueAt(new Boolean(false), i, 0);
            }
        }
        pTable.setValueAt(new Boolean(!valor.booleanValue()), selectedRow, 0);
        // Es necesario pintar nuevamente este objeto para poder mostrar los
        // cambios efectuados.
        pTable.repaint();
    }             
    
    public static void initSimpleList(JTable pTable, AtuxTableModel pTableModel, AtuxColumnData pColumnsList[]) {
        initSimpleList(pTable, pTableModel, pColumnsList, new ArrayList(), null, null, false);
    }
    
    public static void initSimpleList(JTable table,
                                      AtuxTableModel tableModel,
                                      AtuxColumnData columnsList[],
                                      ArrayList pRowsWithOtherColor,
                                      Color pBackgroundColor,
                                      Color pForegroundColor,
                                      boolean pBold) {        
        table.setAutoCreateColumnsFromModel(false);
        table.setModel(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        for (int k = 0; k < tableModel.getColumnCount(); k++) {
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(columnsList[k].m_alignment);

            TableCellEditor editor = new AtuxCustomCellEditor();
            TableColumn column = new TableColumn(k, columnsList[k].m_width, renderer, editor);
            table.addColumn(column);
        }
    }

    public static void initSelectList(JTable pTable, AtuxTableModel pTableModel, AtuxColumnData pColumnsList[]) {
        initSelectList(pTable, pTableModel, pColumnsList, new ArrayList(), null, null, false);
    }
     
     /**
     * Permite inicializar un JTable con una columna de Selección (JCheckBox).
     */
     public static void initSelectList(JTable pTable,
                                       AtuxTableModel pTableModel,
                                       AtuxColumnData pColumnsList[],
                                       ArrayList pRowsWithOtherColor,
                                       Color pBackgroundColor,
                                       Color pForegroundColor,
                                       boolean pBold) {
         pTable.setAutoCreateColumnsFromModel(false);
         pTable.setModel(pTableModel);
         pTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         pTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         for (int k = 0; k < pTableModel.getColumnCount(); k++) {
             TableCellRenderer renderer;
             if (k == 0) {
                 TableCellRenderer rendererCheckBox = new AtuxCheckCellRenderer();
                 TableCellEditor editorCheckBox = new DefaultCellEditor(new JCheckBox());
                 TableColumn columnCheckBox = new TableColumn(k, pColumnsList[k].m_width, rendererCheckBox, editorCheckBox);
                 pTable.addColumn(columnCheckBox);
             } else {
                 DefaultTableCellRenderer textRenderer = new DefaultTableCellRenderer();
                /*
                AttributiveCellRenderer rendererText = new AttributiveCellRenderer(pRowsWithOtherColor,
                                                                                   pBackgroundColor,
                                                                                   pForegroundColor,
                                                                                   pBold,
                                                                                   true);
                */
                 textRenderer.setHorizontalAlignment(pColumnsList[k].m_alignment);
//          TableCellEditor editorText = new EckerdCustomCellEditor();
                 TableCellEditor editorText = null;
                 TableColumn columnText = new TableColumn(k, pColumnsList[k].m_width, textRenderer, editorText);
                 pTable.addColumn(columnText);
             }
         }
     }

    /**
     * Permite seleccionar o deseleccionar una celda del tipo JCheckBox y a la vez establecer
     * o no el modo multiselecciï¿½n.  Adicionalmente permite realizar una bï¿½squeda antes de la
     * selecciï¿½n, sino estï¿½ activo el flag de bï¿½squeda entonces todo el proceso se realizarï¿½
     * sobre el registro actualmente seleccionado del JTable.
     * <p/>
     * para poder determinar la columna con la cual se deberï¿½ comparar
     * el pValorABuscar.
     */
    public static void setearCheckInRow(JTable pJTable, Boolean pValor, boolean pIsMultiSelect, boolean pFindData, String pValorABuscar, int numeroColumna) {
        // Almacenar el actual registro antes de procesar
        int actualRow = pJTable.getSelectedRow();
        // debe existir el elemento actualmente seleccionado
        if (actualRow >= 0) {
            String myValor = "";
            // Si no es multiselecciï¿½n se eliminan todos los check.
            if (!pIsMultiSelect) {
                for (int i = 0; i < pJTable.getRowCount(); i++)
                    pJTable.setValueAt(new Boolean(false), i, 0);
            }
            // Si se debe buscar data son necesarios los parametros :
            // - pValorABuscar
            // - numeroColumna
            if (pFindData) {
                if ((pValorABuscar != null) && (numeroColumna >= 0)) {
                    for (int i = 0; i < pJTable.getRowCount(); i++) {
                        myValor = (String) pJTable.getValueAt(i, numeroColumna);
                        if (myValor.equalsIgnoreCase(pValorABuscar)) {
                            pJTable.setValueAt(new Boolean(!pValor.booleanValue()), i, 0);
                            break;
                        }
                    }
                }
            } else {
                pJTable.setValueAt(new Boolean(!pValor.booleanValue()), actualRow, 0);
            }
            // Es necesario pintar nuevamente este objeto para poder mostrar los
            // cambios efectuados.
            pJTable.repaint();
            // Iluminar el registro que estaba seleccionado antes de realizar
            // todas estas operaciones
            AtuxGridUtils.showCell(pJTable, actualRow, 0);
        }
    }

    public static void setearCheckInRowCambioProducto(JTable pJTable, Boolean pValor, boolean pIsMultiSelect, boolean pFindData, String pValorABuscar, int numeroColumna) {
        // Almacenar el actual registro antes de procesar
        int actualRow = pJTable.getSelectedRow();
        // debe existir el elemento actualmente seleccionado
        if (actualRow >= 0) {
            String myValor = "";
            // Si no es multiselección se eliminan todos los check.
            if (!pIsMultiSelect) {
                for (int i = 0; i < pJTable.getRowCount(); i++)
                    pJTable.setValueAt(new Boolean(false), i, 0);
            }
            // Si se debe buscar data son necesarios los parametros :
            // - pValorABuscar
            // - numeroColumna
            if (pFindData) {
                if ((pValorABuscar != null) && (numeroColumna >= 0)) {
                    for (int i = 0; i < pJTable.getRowCount(); i++) {
                        myValor = (String) pJTable.getValueAt(i, numeroColumna);
                        String cantidad = (String) pJTable.getValueAt(i, 6);
                        if (myValor.equalsIgnoreCase(pValorABuscar) && AtuxUtility.getDecimalNumber(cantidad.trim()) > 0) {
                            pJTable.setValueAt(new Boolean(!pValor.booleanValue()), i, 0);
                            break;
                        }
                    }
                }
            } else {
                pJTable.setValueAt(new Boolean(!pValor.booleanValue()), actualRow, 0);
            }
            // Es necesario pintar nuevamente este objeto para poder mostrar los
            // cambios efectuados.
            pJTable.repaint();
            // Iluminar el registro que estaba seleccionado antes de realizar
            // todas estas operaciones
            AtuxGridUtils.showCell(pJTable, actualRow, 0);
        }
    }

    public static boolean rptaConfirmDialog(JDialog pJDialog, String pMensaje) {
        int rptaDialogo = JOptionPane.showConfirmDialog(pJDialog,
                pMensaje,
                "Mensaje del Sistema",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (rptaDialogo == JOptionPane.YES_OPTION) return true;
        else return false;
    }
    
        public static boolean eliminarRegistroEnJTable(JDialog pJDialog, AtuxTableModel pEckerdTableModel, JTable pJTable) {
        boolean eliminarRegistro = false;
        int row = pJTable.getSelectedRow();
        System.out.println("Se va a eliminar la fila " + row);
        if (row >= 0) {
            if (rptaConfirmDialog(pJDialog, "Seguro de eliminar el Registro ?")) {
                pEckerdTableModel.deleteRow(row);
                pJTable.repaint();
                if (pEckerdTableModel.getRowCount() > 0) {
                    if (row > 0) row--;
                    AtuxGridUtils.showCell(pJTable, row, 0);
                }
                eliminarRegistro = true;
            }
        } else {
            JOptionPane.showMessageDialog(pJDialog, "No existe Registro seleccionado !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        }
        return eliminarRegistro;
    }

    public static void dateCompleteOnlyFormat(JTextField pJTextField, KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (Character.isDigit(keyChar)) {
            if ((pJTextField.getText().trim().length() == 2) || (pJTextField.getText().trim().length() == 5))
                pJTextField.setText(pJTextField.getText().trim() + "/");
        }
    }

    public static void ponerCheckJTable(JTable pJTable, int pCampoEnJTable, ArrayList pArrayList, int pCampoEnArrayList) {
        String myCodigo = "";
        String myCodigoTmp = "";
        for (int i = 0; i < pJTable.getRowCount(); i++) {
            myCodigo = (String) pJTable.getValueAt(i, pCampoEnJTable);
            for (int j = 0; j < pArrayList.size(); j++) {
                myCodigoTmp = (String) (((ArrayList) pArrayList.get(j)).get(pCampoEnArrayList));
                if (myCodigo.equalsIgnoreCase(myCodigoTmp)) pJTable.setValueAt(new Boolean(true), i, 0);
            }
        }
        pJTable.repaint();
    }

    public static void ponerCheckJTable(JTable pJTable, int pCampoEnJTable, ArrayList pArrayList, int pCampoEnArrayList, String pNoEsigualA, int pCampoEnArrayDeIgualdad) {
        String myCodigo = "";
        String myCodigoTmp = "";
        String igualdadParaAgregar = "";
        for (int i = 0; i < pJTable.getRowCount(); i++) {
            myCodigo = (String) pJTable.getValueAt(i, pCampoEnJTable);
            for (int j = 0; j < pArrayList.size(); j++) {
                myCodigoTmp = (String) (((ArrayList) pArrayList.get(j)).get(pCampoEnArrayList));
                igualdadParaAgregar = ((String) (((ArrayList) pArrayList.get(j)).get(pCampoEnArrayDeIgualdad))).trim();

                if (myCodigo.equalsIgnoreCase(myCodigoTmp) && !igualdadParaAgregar.equalsIgnoreCase(pNoEsigualA)) {
                    pJTable.setValueAt(new Boolean(true), i, 0);
                }
            }
        }
        pJTable.repaint();
    }

    public static void ponerCheckJTableCambio(JTable pJTable, int pCampoEnJTable, ArrayList pArrayList, int pCampoEnArrayList, String pNoEsigualA, int pCampoEnArrayDeIgualdad) {
        String myCodigo = "";
        String myCodigoTmp = "";
        String igualdadParaAgregar = "";
        for (int i = 0; i < pJTable.getRowCount(); i++) {
            myCodigo = (String) pJTable.getValueAt(i, pCampoEnJTable);
            for (int j = 0; j < pArrayList.size(); j++) {
                myCodigoTmp = (String) (((ArrayList) pArrayList.get(j)).get(pCampoEnArrayList));
                igualdadParaAgregar = ((String) (((ArrayList) pArrayList.get(j)).get(pCampoEnArrayDeIgualdad))).trim();
                String cantidad = (String) pJTable.getValueAt(i, 7);
                if (myCodigo.equalsIgnoreCase(myCodigoTmp) & AtuxUtility.getDecimalNumber(cantidad.trim()) > 0) {
                    pJTable.setValueAt(new Boolean(true), i, 0);
                }
            }
        }
        pJTable.repaint();
    }
    
    public static double getRedondeo(double pTotal) {
        if (pTotal > 0.00) {
            String myTotal = formatNumber(pTotal);

            int myRedondeo = Integer.parseInt(myTotal.substring(myTotal.length() - 1, myTotal.length()));

            if (myRedondeo >= 5) return (0.01 * (AtuxVariables.vCifraRedondeo - myRedondeo));
            else return (-1 * 0.01 * myRedondeo);

        } else {
            return 0.00;
        }
    }
    
    public static double getRedondeoHaciaArriba(double pTotal) {
        if (pTotal > 0.00) {
            String myTotal = formatNumber(pTotal);

            int myRedondeo = Integer.parseInt(myTotal.substring(myTotal.length() - 1, myTotal.length()));

            if (myRedondeo > 5) {
                return (0.01 * (10 - myRedondeo));
            } else if (myRedondeo > 0) {
                return (0.01 * (5 - myRedondeo));
            } else {
                return 0.00;
            }
        } else {
            return 0.00;
        }
    }   

    public static double getMontoDolarRedondeado(double pTotal) {
        if (pTotal > 0.00) {
            String myTotal = formatNumber(pTotal);
            pTotal = Double.parseDouble(myTotal);
            int myRedondeo = Integer.parseInt(myTotal.substring(myTotal.length() - 1, myTotal.length()));
            if (myRedondeo < 5) return (pTotal + 0.01 * (5 - myRedondeo));
            else if (myRedondeo > 5) return (pTotal + 0.01 * (10 - myRedondeo));
            else return pTotal;
        } else {
            return 0.00;
        }
    }

    public static void moveFocus(Object pObject) {
        if (pObject instanceof JTextField) {
            ((JTextField) pObject).selectAll();
            ((JTextField) pObject).requestFocus();
        } else if (pObject instanceof JComboBox) ((JComboBox) pObject).requestFocus();
        else if (pObject instanceof com.toedter.calendar.JDateChooser) ((com.toedter.calendar.JDateChooser) pObject).requestFocus();
        else if (pObject instanceof JList) ((JList) pObject).requestFocus();
        else if (pObject instanceof JRadioButton) ((JRadioButton) pObject).requestFocus();
        else if (pObject instanceof JTable) ((JTable) pObject).requestFocus();
        else if (pObject instanceof JRadioButton) ((JRadioButton) pObject).requestFocus();
        else if (pObject instanceof JTextArea) ((JTextArea) pObject).requestFocus();
        else if (pObject instanceof JCheckBox) ((JCheckBox) pObject).requestFocus();
        else if (pObject instanceof JButton) ((JButton) pObject).requestFocus();
    }

    public static boolean validaFecha(String pValue, String pDateFormat) {
        try {
            if (!pValue.equals("")) {
                int contaSlash = 0;
                char[] fecha = pValue.toCharArray();
                for (int i = 0; i < fecha.length; i++) {
                    if (fecha[i] == '/') contaSlash += 1;
                }
                if (contaSlash > 2) return false;
                else {
                    SimpleDateFormat formatter = new SimpleDateFormat(pDateFormat);
                    formatter.setLenient(false);
                    String dateString = formatter.format(formatter.parse(pValue));
                    return true;
                }
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    // Verifica si una cadena es un entero
    public static boolean isInteger(String pValue) {
        try {
            if (!pValue.equals("")) {
                int valor = Integer.parseInt(pValue);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    //Metodo que verifica si la cadena de busqueda
    public static boolean findTextInJTable(JTable pTable, String pFindText, int positionCode, int positionDescription) {
        String vCodigo;
        for (int k = 0; k < pTable.getRowCount(); k++) {
            vCodigo = (String) (pTable.getValueAt(k, positionCode));
            if (vCodigo.trim().equalsIgnoreCase(pFindText)) {
                AtuxGridUtils.showCell(pTable, k, 0);
                break;
            }
        }
        String currentCodigo = (String) (pTable.getValueAt(pTable.getSelectedRow(), positionCode));
        String currentDescription = ((String) (pTable.getValueAt(pTable.getSelectedRow(), positionDescription))).toUpperCase();
        int i = 0;
        int longitud = (pFindText.length() >= currentDescription.length()) ? currentDescription.length() : pFindText.length();
        // Comparando codigo y descripc0ion del currentrow con el txtBusqueda
        if ((currentCodigo.trim().equalsIgnoreCase(pFindText) ||
                currentDescription.substring(0, longitud).equalsIgnoreCase(pFindText))) {
            return true;
        }
        return false;
    }

    public static boolean isDouble(String pValue, boolean pIsMandatory) {
        try {
            if (!pValue.equals("")) {
                double valor = Double.parseDouble(pValue);
                return true;
            } else {
                if (pIsMandatory) return false;
                else return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static Date getStringToDate(String pValue, String pDateFormat) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pDateFormat);
            formatter.setLenient(false);
            return formatter.parse(pValue);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getDateToString(Date pValue, String pDateFormat) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pDateFormat);
            formatter.setLenient(false);
            return formatter.format(pValue);
        } catch (Exception e) {
            return null;
        }
    }

    public static void showInDecimalFormat(JTextField pJTextField) {
        pJTextField.setText(formatNumber(getDecimalNumber(pJTextField.getText().trim())));
    }

    /**
     * Completa una cadena con un simbolo para una longitud dada
     *
     * @return <b>String</b> Cadena de blancos.
     */
    public static String completeWithSymbol(String pValue, int pLength, String pSymbol, String pAlign) {
        String tempString = pValue;
        for (int i = pValue.length(); i < pLength; i++) {
            if (pAlign.trim().equalsIgnoreCase("I"))
                tempString = pSymbol + tempString;
            else
                tempString += pSymbol;
        }
        return tempString;
    }

    /**
     * Parte una cadena en un arreglo dada la longitud de las subcadenas
     *
     * @return <b>ArrayList</b> Arreglo con las subcadenas
     */
    public static ArrayList splitString(String pValue, int pLength) {
        ArrayList pList = new ArrayList();
        String line = new String("");
        String word = new String("");
        StringTokenizer st = new StringTokenizer(pValue, " \n");
        while (st.hasMoreTokens()) {
            word = st.nextToken();
            if (line.length() + word.length() <= pLength) {
                line += word + " ";
            } else {
                pList.add(line.trim());
                line = word + " ";
            }
        }
        if (line.length() > 0) pList.add(line.trim());
        return pList;
    }

    /**
     * Alinea la variable int hacia la derecha colocando CARACTERES a la izquierda
     * según la longitud que se establezca y el CARACTER dado.
     *
     * @return <b>String</b> String alineado a la derecha con el CARACTER.
     */
    public static String caracterIzquierda(int parmint, int parmLen, String parmCaracter) {
        return caracterIzquierda(String.valueOf(parmint), parmLen, parmCaracter);
    }

    /**
     * Alinea la variable long hacia la derecha colocando CARACTERES a la izquierda
     * según la longitud que se establezca y el CARACTER dado.
     *
     * @return <b>String</b> String alineado a la derecha con el CARACTER.
     */
    public static String caracterIzquierda(long parmint, int parmLen, String parmCaracter) {
        return caracterIzquierda(String.valueOf(parmint), parmLen, parmCaracter);
    }

    /**
     * Alinea la variable double hacia la derecha colocando CARACTERES a la izquierda
     * según la longitud que se establezca y el CARACTER dado.
     *
     * @return <b>String</b> String alineado a la derecha con el CARACTER.
     */
    public static String caracterIzquierda(double parmint, int parmLen, String parmCaracter) {
        return caracterIzquierda(String.valueOf(parmint), parmLen, parmCaracter);
    }

    /**
     * Alinea la variable String hacia la derecha colocando CARACTERES a la izquierda
     * según la longitud que se establezca y el CARACTER dado.
     *
     * @return <b>String</b> String alineado a la derecha con el CARACTER.
     */
    public static String caracterIzquierda(String parmString, int parmLen, String parmCaracter) {

        String tempString = parmString;

        if (tempString.length() > parmLen)
            tempString = tempString.substring(tempString.length() - parmLen,
                    tempString.length());
        else {
            while (tempString.length() < parmLen)
                tempString = parmCaracter + tempString;
        }

        return tempString;

    }

    public static void operaListaProd(ArrayList lista, ArrayList elemento, Boolean valor, int campo) {
        if (!valor.booleanValue()) {
            String valorCampo = "";
            String valorCampoTmp = (String) (elemento.get(campo));
            for (int i = 0; i < lista.size(); i++) {
                valorCampo = (String) (((ArrayList) lista.get(i)).get(campo));
                if (valorCampo.equalsIgnoreCase(valorCampoTmp)) {
                    lista.remove(i);
                }
            }
        } else {
            lista.add(elemento);
        }
    }

    public static void operaListaProd(ArrayList lista, ArrayList elemento, Boolean valor, int campo1, int campo2) {
        if (!valor.booleanValue()) {
            String valorCampo1 = "";
            String valorCampo2 = "";
            String valorCampoTmp1 = (String) (elemento.get(campo1));
            String valorCampoTmp2 = (String) (elemento.get(campo2));
            for (int i = 0; i < lista.size(); i++) {
                valorCampo1 = (String) (((ArrayList) lista.get(i)).get(campo1));
                valorCampo2 = (String) (((ArrayList) lista.get(i)).get(campo2));
                if (valorCampo1.equalsIgnoreCase(valorCampoTmp1) && valorCampo2.equalsIgnoreCase(valorCampoTmp2)) {
                    lista.remove(i);
                }
            }
        } else {
            lista.add(elemento);
        }
    }

    public static void operaListaProdProducto(ArrayList lista, ArrayList elemento, Boolean valor, int campo1, int campo2) {
        if (!valor.booleanValue()) {
            String valorCampo1 = "";
            String valorCampo2 = "";
            String valorCampoTmp1 = (String) (elemento.get(campo1));
            String valorCampoTmp2 = (String) (elemento.get(campo2));
            for (int i = 0; i < lista.size(); i++) {
                valorCampo1 = (String) (((ArrayList) lista.get(i)).get(campo1));
                valorCampo2 = (String) (((ArrayList) lista.get(i)).get(campo2));
                if (valorCampo1.equalsIgnoreCase(valorCampoTmp1) && valorCampo2.equalsIgnoreCase(valorCampoTmp2)) {
                    lista.remove(i);
                }
            }
        } else {
            lista.add(elemento);
        }
    }

    public static String getStringInFormatDate(String pValue, String pDateFormat) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pDateFormat);
            formatter.setLenient(false);
            return formatter.format(formatter.parse(pValue));
        }
        catch (Exception e) {
            return null;
        }
    }

    public static void removeAllListaProd(ArrayList lista) {
        lista = new ArrayList();
    }

    public static void completarVencimiento(JTextField pJTextField, KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (Character.isDigit(keyChar)) {
            if ((pJTextField.getText().trim().length() == 2))
                pJTextField.setText(pJTextField.getText().trim() + "/");
        }
    }

    public static void completarHora(JTextField pJTextField, KeyEvent e, boolean withSeconds) {
        char keyChar = e.getKeyChar();
        if (e.getKeyCode() != KeyEvent.VK_ENTER) {
            if (Character.isDigit(keyChar)) {
                if (pJTextField.getText().trim().length() == 2 ||
                        (withSeconds && (pJTextField.getText().trim().length() == 5)))
                    pJTextField.setText(pJTextField.getText().trim() + ":");
            }
        }
    }

    public static boolean isHoraMinutoValida(Object pJDialog,
                                             String pHoraMinuto,
                                             String pMensaje) {
        if (pHoraMinuto.equalsIgnoreCase("") || (pHoraMinuto.trim().length() < 5) || !validaTiempo(pHoraMinuto)) {
            JOptionPane.showMessageDialog((JDialog) pJDialog, pMensaje, "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean isHoraMinutoSegundoValida(Object pJDialog,
                                                    String pHoraMinutoSegundo,
                                                    String pMensaje) {
        if (pHoraMinutoSegundo.equalsIgnoreCase("") ||
                (pHoraMinutoSegundo.trim().length() < 8) ||
                !validaTiempo(pHoraMinutoSegundo)) {
            JOptionPane.showMessageDialog((JDialog) pJDialog, pMensaje, "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean validaTiempo(String pHora) {
        if (!pHora.substring(2, 3).equalsIgnoreCase(":")) return false;
        if (pHora.trim().length() == 3) pHora = pHora + "00";
        if (pHora.trim().length() == 4) pHora = pHora + "0";
        String hora = pHora.substring(0, 2);
        String minuto = pHora.substring(3, 5);
        if (!isInteger(hora) || Integer.parseInt(hora) > 23) return false;
        if (!isInteger(minuto) || Integer.parseInt(minuto) > 59) return false;        
        if (pHora.length() > 5) {
            String segundo = pHora.substring(6, 8);
            if (!isInteger(segundo) || Integer.parseInt(segundo) > 59) return false;
        }
        return true;
    }

    public static long diferenciaEnDias(Calendar cal1,
                                        Calendar cal2) {
        long diferencia = 0;
        diferencia = cal1.getTime().getTime() - cal2.getTime().getTime();
        diferencia /= 86400000;
        return diferencia;
    }

    public static void timeComplete(JTextField pJTextField, KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (Character.isDigit(keyChar)) {
            if ((pJTextField.getText().trim().length() == 2)) {
                pJTextField.setText(pJTextField.getText().trim() + ":");
            }
        }
    }
    
    public static void liberarTransaccion() {
        try {
            System.out.println("*** LIBERANDO TRANSACCION !!! ***");
            AtuxSearch.liberarTransaccion();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static void aceptarTransaccion() {
        try {
            System.out.println("*** ACEPTANDO TRANSACCION !!! ***");
            AtuxSearch.aceptarTransaccion();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
   
    public static int trunc(double pDato) {
        String dato = String.valueOf(pDato);
        if (dato.lastIndexOf(".") != -1)
            return Integer.parseInt(dato.substring(0, dato.lastIndexOf(".")));
        else
            return Integer.parseInt(dato);
    }

    public static int trunc(String pDato) {
        return trunc(AtuxUtility.getDecimalNumber(pDato));
    }

    public static void admitirDigitos(JTextField jtext, KeyEvent e) {
        char c = e.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) ||
                (c == KeyEvent.VK_ENTER))) {
            //jtext.getToolkit().beep();
            e.consume();
        }
    }

    public static void admitirDigitosDecimales(JTextField jtext, KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) &&
                !(c == KeyEvent.VK_BACK_SPACE) &&
                !(c == KeyEvent.VK_DELETE) &&
                !(c == KeyEvent.VK_ENTER) &&
                !(c == '.') &&
                !(c == KeyEvent.VK_ALT)) {
            e.consume();
        }
    }

    public static void admitirLetras(JTextField jtext, KeyEvent e) {
        char c = e.getKeyChar();
        if (!(Character.isLetter(c) || (Character.isSpaceChar(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) ||
                (c == KeyEvent.VK_ENTER))) {            
            e.consume();
        }
   }
    
    public static String devuelveMesEnLetras(int mes) {
        String mesletras = "";
        switch (mes) {
            case 1:
                mesletras = "Enero";
                break;
            case 2:
                mesletras = "Febrero";
                break;
            case 3:
                mesletras = "Marzo";
                break;
            case 4:
                mesletras = "Abril";
                break;
            case 5:
                mesletras = "Mayo";
                break;
            case 6:
                mesletras = "Junio";
                break;
            case 7:
                mesletras = "Julio";
                break;
            case 8:
                mesletras = "Agosto";
                break;
            case 9:
                mesletras = "Septiembre";
                break;
            case 10:
                mesletras = "Octubre";
                break;
            case 11:
                mesletras = "Noviembre";
                break;
            case 12:
                mesletras = "Diciembre";
                break;
        }
        return mesletras;
    }

    public static int devuelveDiasMes(int mes, int anio) {
        int dias = 0;
        switch (mes) {
            case 1:
                dias = 31;
                break;
            case 2:
                dias = 28;
                break;
            case 3:
                dias = 31;
                break;
            case 4:
                dias = 30;
                break;
            case 5:
                dias = 31;
                break;
            case 6:
                dias = 30;
                break;
            case 7:
                dias = 31;
                break;
            case 8:
                dias = 31;
                break;
            case 9:
                dias = 30;
                break;
            case 10:
                dias = 31;
                break;
            case 11:
                dias = 30;
                break;
            case 12:
                dias = 31;
                break;
        }
        if (mes == 2 && anio % 4 == 0)
            dias = 29;
        return dias;
    } 

    public static boolean isFechaMesValida(Object pJDialog,
                                           String pFecha,
                                           String pMensaje) {
        pFecha = pFecha.trim();
        boolean fechaCorrecta = true;

        if ((pFecha.trim().length() != 7) || !validaFechaMes(pFecha, "MM/yyyy")) {
            JOptionPane.showMessageDialog((JDialog) pJDialog, pMensaje, "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            fechaCorrecta = false;
        }
        return fechaCorrecta;
    }

    public static boolean validaFechaMes(String pValue, String pDateFormat) {
        try {
            if (!(pValue.length() != 7)) {
                SimpleDateFormat formatter = new SimpleDateFormat(pDateFormat);
                formatter.setLenient(false);
                String dateString = formatter.format(formatter.parse(pValue));
                return true;
            } else {
                return false;
            }
        }
        catch (ParseException e) {
            return false;
        }
    }

    public static String replaceString(String p_strMain, String p_strSearch, String p_strReplace) {
        if (p_strMain == null || p_strMain.trim().length() == 0) return "";
        int i;
        int v_posstartsearch;
        int v_posendsearch;
        int v_posini = 0;
        StringBuilder v_strBuff = new StringBuilder();
        int len = p_strMain.length();
        boolean v_end = false;
        while (!v_end) {
            v_posstartsearch = p_strMain.indexOf(p_strSearch, v_posini);
            if (v_posstartsearch >= 0) {
                v_posendsearch = v_posstartsearch + p_strSearch.length();
                v_strBuff.append(p_strMain.substring(v_posini, v_posstartsearch));
                v_strBuff.append(p_strReplace);
                v_posini = v_posendsearch;
            } else {
                v_strBuff.append(p_strMain.substring(v_posini));
                v_end = true;
            }
        }
        return v_strBuff.toString();
    }

    /**
     * Realiza la Suma de toda una Colunma de un Jtable y lo retorna como String con formato
     */

    public static double sumColumTable(JTable pTable, int pColumna) {
        double suma = 0.00;
        for (int p = 0; p < pTable.getRowCount(); p++) {
            suma += getDecimalNumber(((String) pTable.getValueAt(p, pColumna)).trim());
            suma = getDecimalNumber(formatNumber(suma));
        }
        return suma;
    }

    public static String getHostAddress() {
        String strIP = "";
        try {
            InetAddress thisIp = InetAddress.getLocalHost();
            strIP = thisIp.getHostAddress();
        }
        catch (Exception e) {
            e.printStackTrace();
            strIP = "";
        }
        return strIP;
    }

    public static String getHostName() {
        String hostName = "";
        try {
            InetAddress thisIp = InetAddress.getLocalHost();
            hostName = thisIp.getHostName();
            System.out.println("Host Name = " + hostName);
        }
        catch (Exception e) {
            e.printStackTrace();
            hostName = "";
        }
        return hostName;
    }


    public static boolean esDireccionEMailValida(String vi_strDireccionEMail) {

        //asegura que no empieze o termine con un espacio en blanco
        vi_strDireccionEMail = vi_strDireccionEMail.trim();

        //valido si tiene @ y que no este en la primero posicion
        if (vi_strDireccionEMail.indexOf("@") < 1) {
            return false;
        }
        //valido q  solo encuentre caracteres validos
        String vl_strCaracteresValidos = "aeioubcdfghjklmnpqrstvwxyzAEIOUBCDEFGHJKLMNPQRSTVWXYZ.@_-1234567890";
        for (int i = 0; i < vi_strDireccionEMail.length(); i++) {
            if (vl_strCaracteresValidos.indexOf(vi_strDireccionEMail.charAt(i) + "") == -1) {
                return false;
            }
        }
        //valido que se encuentre un punto (.) despues de la arroba y que no este seguida de esta
        if (vi_strDireccionEMail.indexOf(".", vi_strDireccionEMail.indexOf("@") + 2) == -1) {
            return false;
        }
        return true;
    }   

    public static void closeProcessWindow(Frame pFrame) {
        pFrame.setVisible(false);
        pFrame.dispose();
    }

    public static boolean findString(String pTexto, String pSeparador, String pTextoABuscar, boolean pIgnoreCase) {
        String word = new String("");
        StringTokenizer st = new StringTokenizer(pTexto, pSeparador);
        boolean existe = false;

        System.out.println("Texto a Buscar " + pTexto);
        while (st.hasMoreTokens()) {
            word = st.nextToken();
            word = word.trim();

            System.out.println(word + "" + pTextoABuscar);
            if (pIgnoreCase) {
                if (word.equalsIgnoreCase(pTextoABuscar)) {
                    existe = true;
                    break;
                }
            } else {
                if (word.equals(pTextoABuscar)) {
                    existe = true;
                    break;
                }
            }
        }

        return existe;
    }
   
    // Inicio ID : 002
    
    public static String convertToNegative(String pDecimal) {
        return String.valueOf(AtuxUtility.getDecimalNumber(pDecimal.trim()) * (-1));
    } 

    public static boolean isPcDelQuimico(String ipLocal) {
        int ultimoNumeroIPLocal = getLastNumberOfIpAddress(ipLocal);
        return ultimoNumeroIPLocal == 11;
    }

    public static int getLastNumberOfIpAddress(String ipAddress) {
        String ipPart = "";
        for (StringTokenizer st = new StringTokenizer(ipAddress, "."); st.hasMoreTokens();) {
            ipPart = st.nextToken();
        }

        return Integer.parseInt(ipPart);
    }   

    public static String getIpOfHostName(String pHostName) {
        String ipPtoVenta = "";

        try {
            InetAddress ipLocal = InetAddress.getByName(pHostName);
            ipPtoVenta = ipLocal.getHostAddress();
        } catch (UnknownHostException ex) {
            ipPtoVenta = "";
        }

        return ipPtoVenta;
    }
    
    public static Frame openProcessWindow() {
        Frame frame = new FrmProceso();
        //ImageIcon imageIcono = new ImageIcon(JVentas.class.getResource("resources/2793.ico"));
        //frame.setIconImage(imageIcono.getImage());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
        return frame;
    }

    public static Frame openProcessWindow(String pTitulo) {
        Frame frame = new FrmProceso(pTitulo);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
        return frame;
    }
    
    public static void setCajaTurno() throws SQLException {
        //Determinando Número de Caja asignada al Usuario (CAJERO)
        AtuxVariables.vNuCaja = AtuxSearch.getNuCaja(AtuxVariables.vNuSecUsuario).trim();
        //Determinando si la Caja se encuentra Abierta
        if (AtuxVariables.vNuCaja.trim().length() > 0 && Integer.parseInt(AtuxVariables.vNuCaja.trim()) > 0) {
            AtuxVariables.vInCajaAbierta = AtuxSearch.getInCajaAbierta(AtuxVariables.vNuCaja);
            if (AtuxVariables.vInCajaAbierta.equalsIgnoreCase("S"))
                AtuxVariables.vNuTurno = AtuxSearch.getNuTurno(AtuxVariables.vNuCaja);
        }
    }

    public static String getIPLocal() {
        String ip = "";
        int codLocal = Integer.parseInt(AtuxVariables.vCodigoLocal);
        int contador = 0;

        while (codLocal > 254) {
            contador += 2;
            codLocal -= 254;
        }

        ip = "192." + (170 + contador) + "." + codLocal + ".10";

        return ip;
    }

    public static void dateComplete(JTextField pJTextField, KeyEvent e) {
        try {
            String anhoBD = AtuxSearch.getFechaHoraBD(1).trim().substring(6, 10);
            char keyChar = e.getKeyChar();
            if (Character.isDigit(keyChar)) {
                if ((pJTextField.getText().trim().length() == 2) || (pJTextField.getText().trim().length() == 5)) {
                    pJTextField.setText(pJTextField.getText().trim() + "/");
                    if (pJTextField.getText().trim().length() == 6)
                        pJTextField.setText(pJTextField.getText().trim() + anhoBD);
                }
            }
        } catch (SQLException errAnhoBD) {
            errAnhoBD.printStackTrace();
        }
    }

    public static void saveFile(Frame pParentFrame, AtuxColumnData pColumns[], JTable pTable, int pAnchoTexto[]) {
        File lfFile = new File("C:\\");
        JFileChooser filechooser = new JFileChooser(lfFile);
        if (filechooser.showSaveDialog(pParentFrame) != JFileChooser.APPROVE_OPTION)
            return;
        File fileChoosen = filechooser.getSelectedFile();
        try {
            FileWriter outFile = new FileWriter(fileChoosen);
            String linea = "";
            for (int i = 0; i < pTable.getColumnCount(); i++) {
                String lsCabecera = ((AtuxColumnData) pColumns[i]).m_title;
                lsCabecera = AtuxPRNUtility.alinearIzquierda(lsCabecera, pAnchoTexto[i]);
                linea += lsCabecera + "\t";
            }
            outFile.write(linea + "\n");
            for (int i = 0; i < pTable.getRowCount(); i++) {
                linea = "";
                for (int j = 0; j < pTable.getColumnCount(); j++) {
                    Object loValor = (Object) pTable.getValueAt(i, j);
                    String lscadena = "";
                    if (loValor instanceof String)
                        lscadena = AtuxPRNUtility.alinearIzquierda(((String) loValor).trim(), pAnchoTexto[j]);
                    else
                        lscadena = AtuxPRNUtility.alinearDerecha(((String) loValor).trim(), pAnchoTexto[j]);
                    linea += lscadena + "\t";
                }
                outFile.write(linea + "\n");
            }
            outFile.close();
        }
        catch (IOException ioerr) {
            ioerr.printStackTrace();
        }
    }

    public static boolean isFechaValida(Object pJDialog,
                                        String pFecha,
                                        String pMensaje) {
        pFecha = pFecha.trim();
        boolean fechaCorrecta = true;
        try {
            if (pFecha.equalsIgnoreCase("") || (pFecha.trim().length() < 10) || !validaFecha(pFecha, "dd/MM/yyyy")) {
                JOptionPane.showMessageDialog((JDialog) pJDialog, pMensaje, "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                fechaCorrecta = false;
            } else {
                Date fecha = getStringToDate(pFecha, "dd/MM/yyyy");
                Date fechaActual = getStringToDate(AtuxSearch.getFechaHoraBD(AtuxVariables.FORMATO_FECHA), "dd/MM/yyyy");
                if (fecha.after(fechaActual)) {
                    JOptionPane.showMessageDialog((JDialog) pJDialog,
                            pMensaje + " - " + "Fecha es mayor que la Fecha de hoy",
                            "Mensaje del Sistema",
                            JOptionPane.WARNING_MESSAGE);
                    fechaCorrecta = false;
                }
            }
        } catch (SQLException errValidarFecha) {
            JOptionPane.showMessageDialog((JDialog) pJDialog, "Error en ejecución del Comando !!! - " + errValidarFecha.getErrorCode(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            errValidarFecha.printStackTrace();
        }
        return fechaCorrecta;
    }

    public static void dateCompleteMes(JTextField pJTextField, KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (Character.isDigit(keyChar)) {
            if ((pJTextField.getText().trim().length() == 2)) {
                pJTextField.setText(pJTextField.getText().trim() + "/");
            }
        }
    }

    public static void convertirMayuscula(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if(Character.isLowerCase(keyChar)){
            String cad = ("" + keyChar).toUpperCase();
            keyChar=cad.charAt(0);
            e.setKeyChar(keyChar);
        }
    }

    public static void admitirSoloCaracterFecha(KeyEvent e, JTextField textField, int longitudTexto, JDialog dialog) {
        // Comparamos la longitud del texto antes y despues de presionar una tecla
        // El objetivo es controlar los caracteres no vï¿½lidos.  Para las teclas Bloq.Mayï¿½s,
        // Insert, Supr, Inicio, Fin, etc ... son teclas que no generan un caracter pero sï¿½
        // un objeto KeyEvent ...
        char keyChar = e.getKeyChar();
        if (textField.getText().length() <= longitudTexto ) {
            if (!Character.isDigit(e.getKeyChar()) &&  e.getKeyCode() <=105 && e.getKeyCode() != 111){

                e.consume();
                admitirMensaje(keyChar, textField, "La tecla presionada no es vï¿½lida !!!.", dialog);
            }
        }else{
            admitirMensaje(keyChar, textField, "La tecla presionada no es vï¿½lida !!!. Longitud maximo alcanzado.", dialog);
        }
    }


    public static boolean validaPCName() {
        AtuxVariables.vNombrePC = getHostName();
        AtuxVariables.vIP_PC  = getHostAddress();
        if (AtuxVariables.vNombrePC.trim().length() == 0 || AtuxVariables.vIP_PC.trim().length() == 0)
            return false;
        return true;
    }

}
