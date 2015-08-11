package atux.util.print;

import atux.util.common.AtuxSearch;
import java.io.*;
import java.util.ArrayList;
import java.sql.SQLException;


public class AtuxPrintService implements AtuxPrint {            
    /**
     * C�digos necesarios para activar Negritas
     */
    private char activateBold[] = {(char) 27, 'E'};
    /**
     * C�digos necesarios para desactivar Negritas
     */
    private char deactivateBold[] = {(char) 27, 'F'};

    /**
     * C�digo necesario para activar Condensando
     */
    private char activateCondensed[] = {(char) 27, (char) 15};//{(char) 15};
    /**
     * C�digo necesario para desactivar Condensando
     */
    private char deactivateCondensed[] = {(char) 27, (char) 18};//{(char) 18};

    /**
     * C�digos necesarios para activar Subrayado
     */
    private char activateUnderline[] = {(char) 27, (char) 45, (char) 49};
    /**
     * C�digos necesarios para desactivar Subrayado
     */
    private char deactivateUnderline[] = {(char) 27, (char) 45, (char) 48};

    /**
     * C�digos necesarios para activar Tama�o Doble de Letra
     */
    private char activateDoubleWidthMode[] = {(char) 27, (char) 87, (char) 49};
    /**
     * C�digos necesarios para desactivar Tama�o Doble de Letra
     */
    private char deactivateDoubleWidthMode[] = {(char) 27, (char) 87, (char) 48};

    /**
     * C�digos necesarios para setear Tama�o de p�gina a 10 l�neas
     */
    private char page10Lines[] = {(char) 27, (char) 67, (char) 10};
    /**
     * C�digos necesarios para setear Tama�o de p�gina a 24 l�neas - BOLETAS
     */
    private char page24Lines[] = {(char) 27, (char) 67, (char) 24};
    /**
     * C�digos necesarios para setear Tama�o de p�gina a 36 l�neas - FACTURAS
     */
    private char page36Lines[] = {(char) 27, (char) 67, (char) 36};
    /**
     * C�digos necesarios para setear Tama�o de p�gina a 66 l�neas - PAPEL CONTINUO
     */
    private char page66Lines[] = {(char) 27, (char) 67, (char) 66};

    /**
     * C�digo necesario para hacer quiebre de p�gina
     */
    private char pageBreak[] = {(char) 12};
    /**
     * C�digos necesarios para hacer retorno de carro
     */
    private char carriageReturn[] = {(char) 13};

    /**
     * Almacena la salida que se enviar� a la Impresora Matricial
     */
    private PrintStream ps;

    /**
     * Almacena el Tama�o del papel expresado en N�mero de l�neas
     */
    private char pageSize[];
    /**
     * Almacena el N�mero de l�neas de la p�gina
     */
    private int linesPerPage = 0;
    /**
     * Almacena el N�mero de l�neas realmente disponibles de la p�gina
     */
    private int printArea = 0;

    /**
     * Almacena el N�mero de l�nea actual de Impresi�n
     */
    private int actualLine = 0;

    /**
     * Almacena el Tama�o de la Cabecera de P�gina expresado en L�neas
     */
    private int headerSize = 0;
    /**
     * Almacena el Tama�o del Pie de P�gina expresado en L�neas
     */
    private int footerSize = 0;

    /**
     * Almacena el Puerto de Salida para la Impresi�n
     */
    private String devicePort = "";

    /**
     * Indicador de Impresi�n de Fecha-Hora y N�mero de P�gina
     */
    private boolean includeDatePage = false;
    /**
     * Almacena la Fecha de Emisi�n del Reporte
     */
    private String reportDate = "";
    /**
     * Almacena el N�mero de P�gina por Imprimir
     */
    private int pageNumber = 1;

    /**
     * Almacena la Cabecera del Reporte
     */
    private ArrayList arrayHeader = new ArrayList();
    /**
     * Indicador de Seteo de Cabecera del Reporte
     */
    private boolean settingHeader = false;
    /**
     * Almacena el Pie de P�gina del Reporte
     */
    private ArrayList arrayFooter = new ArrayList();
    /**
     * Indicador de Seteo del Pie de P�gina del Reporte
     */
    private boolean settingFooter = false;

    /**
     * Almacena el Nombre del Programa que Ejecuta el Reporte
     */
    private String programName = "";
    
    public static final int FORMATO_FECHA = 1;
    public static final int FORMATO_FECHA_HORA = 2;

    /**
     * Constructor : Inicializa el Tama�o de P�gina, el Puerto de Salida para la
     * Impresi�n e indica si se va a imprimir el N�mero de P�gina y la Fecha de
     * Generaci�n del Reporte.
     * <p/>
     * Ejemplo :
     * AtuxPrintService printService = new AtuxPrintService(65,"LPT1",true);
     * Imprimir� en P�ginas de 66 L�neas a trav�s de LPT1 e Imprimir� N�mero de
     * P�gina y Fecha de Generaci�n.
     * Para acceder a una impresora matricial ubicada dentro de la red de Windows
     * se deber� usar la siguiente sintaxis por ejemplo : //aguerra/okidatam
     * <p/>
     * PROCEDIMIENTO :
     * 1.seg�n sea el caso Setear la Cabecera del Reporte.
     * Caso 1:
     * a.Setear Propiedades de Impresi�n (negrita, condensado, etc).
     * b.Imprimir el Texto.
     * c.Desactivar el Seteo de Propiedades realizado.
     * Ventaja: Setear una sola vez para imprimir varios Textos
     * Caso 2:
     * a.Imprimir el Texto indicando el Seteo de manera directa.
     * Ventaja: Imprimir de manera directa el Texto con la propiedad deseada.
     * 2.seg�n sea el caso Setear el Pie de P�gina del Reporte.
     * Los casos son los mismos que el Procedimiento 1.
     * 3.Imprimir el Texto del Cuerpo del Reporte.
     * Los casos son los mismos que el Procedimiento 1.
     * <p/>
     * OBSERVACION : El Seteo de la Cabecera y Pie de P�gina solo es realizado
     * una (1) vez. El Servicio de Impresi�n detecta cuando y cuantas veces
     * imprimir la Cabecera y/o Pie de P�gina, de igual modo detecta cuando
     * hacer los correspondientes quiebre de p�gina.
     *
     * @param <b>pLinesPerPage</b>    Tama�o del Papel expresado en N�mero de L�neas.
     * @param <b>pDevicePort</b>      Puerto de Salida.
     * @param <b>pIncludeDatePage</b> Mostrar Fecha y Numeraci�n de P�gina.
     */
    public AtuxPrintService(int pLinesPerPage,
                              String pDevicePort,
                              boolean pIncludeDatePage) {
        linesPerPage = pLinesPerPage;
        printArea = linesPerPage - 4;
        devicePort = pDevicePort;
        includeDatePage = pIncludeDatePage;
        switch (linesPerPage) {
            case (10): {
                pageSize = page10Lines;
                break;
            }
            case (24): {
                pageSize = page24Lines;
                break;
            }
            case (36): {
                pageSize = page36Lines;
                break;
            }
            case (66): {
                pageSize = page66Lines;
                break;
            }
            default:
                pageSize = page66Lines;
        }
    }

    /**
     * Setea el Inicio del Trabajo del Servicio de Impresi�n.
     */
    @Override
    public boolean startPrintService() {
        boolean valorRetorno = false;
        try {
            FileOutputStream fos = new FileOutputStream(devicePort);
            ps = new PrintStream(fos);
            ps.print(deactivateCondensed);
            ps.print(pageSize);
            ps.print(carriageReturn);
            ps.println(" ");

            ps.print(carriageReturn);
            if (includeDatePage) {
                printArea -= 3;
                reportDate = AtuxSearch.getFechaHoraBD(FORMATO_FECHA_HORA);
                printPageNumber();
            }
            valorRetorno = true;
        } catch (FileNotFoundException errFileNotFound) {
            errFileNotFound.printStackTrace();
        } catch (IOException errIO) {
            errIO.printStackTrace();
        } catch (SQLException errGetDateTime) {
            errGetDateTime.printStackTrace();
        } catch (Exception errException) {
            errException.printStackTrace();
        }
        return valorRetorno;
    }

    /**
     * Setea el Fin del Trabajo del Servicio Impresi�n.
     */
    @Override
    public void endPrintService() {
        for (int i = 1; i < getRemainingLines() + 2; i++) {
            ps.println(" ");
            ps.print(carriageReturn);
        }
        printArray(arrayFooter);
        if (includeDatePage) printDatePage();
        ps.print("\f");
        ps.flush();
        ps.close();
    }

    /**
     * Setea el Inicio de la Cabecera de P�gina.
     */
    @Override
    public void setStartHeader() {
        settingHeader = true;
    }

    /**
     * Setea el Fin de la Cabecera de P�gina.
     */
    @Override
    public void setEndHeader() {
        settingHeader = false;
        headerSize += actualLine;
        actualLine = 0;
        printArray(arrayHeader);
    }

    /**
     * Setea el Inicio del Pie de P�gina.
     */
    @Override
    public void setStartFooter() {
        settingFooter = true;
    }

    /**
     * Setea el Fin del Pie de P�gina.
     */
    @Override
    public void setEndFooter() {
        settingFooter = false;
        footerSize += actualLine;
        actualLine = 0;
    }

    /**
     * Activa el modo de Impresi�n en Negrita.
     */
    @Override
    public void activateBold() {
        setProperties(activateBold);
    }

    /**
     * Desactiva el modo de Impresi�n en Negrita.
     */
    @Override
    public void deactivateBold() {
        setProperties(deactivateBold);
    }

    /**
     * Imprime el texto en modo Negrita.
     *
     * @param <b>pText</b>       Texto a imprimir.
     * @param <b>pChangeLine</b> Indicador de cambio de l�nea.
     */
    @Override
    public void printBold(String pText, boolean pChangeLine) {
        activateBold();
        printLine(pText, pChangeLine);
        deactivateBold();
    }

    /**
     * Activa el modo de Impresi�n en Condensado.
     */
    @Override
    public void activateCondensed() {
        setProperties(activateCondensed);
    }

    /**
     * Desactiva el modo de Impresi�n en Condensado.
     */
    @Override
    public void deactivateCondensed() {
        setProperties(deactivateCondensed);
    }

    /**
     * Imprime el texto en modo Condensado.
     *
     * @param <b>pText</b>       Texto a imprimir.
     * @param <b>pChangeLine</b> Indicador de cambio de l�nea.
     */
    @Override
    public void printCondensed(String pText, boolean pChangeLine) {
        activateCondensed();
        printLine(pText, pChangeLine);
        deactivateCondensed();
    }

    /**
     * Activa el modo de Impresi�n Tama�o Doble de Letra.
     */
    @Override
    public void activateDoubleWidthMode() {
        setProperties(activateDoubleWidthMode);
    }

    /**
     * Desactiva el modo de Impresi�n Tama�o Doble de Letra.
     */
    @Override
    public void deactivateDoubleWidthMode() {
        setProperties(deactivateDoubleWidthMode);
    }

    /**
     * Imprime el texto en modo Tama�o Doble de Letra.
     *
     * @param <b>pText</b>       Texto a imprimir.
     * @param <b>pChangeLine</b> Indicador de cambio de l�nea.
     */
    @Override
    public void printDoubleWidthMode(String pText, boolean pChangeLine) {
        activateDoubleWidthMode();
        printLine(pText, pChangeLine);
        deactivateDoubleWidthMode();
    }

    /**
     * M�todo que envia el texto a impresora matricial.
     *
     * @param <b>pText</b>       Texto a imprimir.
     * @param <b>pChangeLine</b> Indicador de cambio de l�nea.
     */
    @Override
    public void printLine(String pText, boolean pChangeLine) {
        setPrintLine(pText, pChangeLine);
        if (pChangeLine) actualLine += 1;

        if (totalLine() > printArea) internalPageBreak(true);
    }

    /**
     * M�todo que envia el texto a impresora matricial.
     *
     * @param <b>pText</b>       Texto a imprimir.
     * @param <b>pChangeLine</b> Indicador de cambio de l�nea.
     */
    private void setPrintLine(String pText, boolean pChangeLine) {
        if (settingHeader && pChangeLine) arrayHeader.add("1" + pText);
        else if (settingHeader && !pChangeLine) arrayHeader.add("0" + pText);
        else if (settingFooter && pChangeLine) arrayFooter.add("1" + pText);
        else if (settingFooter && !pChangeLine) arrayFooter.add("0" + pText);
        else if (pChangeLine) {            
            ps.println(pText);
            ps.print(carriageReturn);            
        } else {            
            ps.print(pText);
        }
    }

    /**
     * Retorna el N�mero de L�neas disponibles para Impresi�n.
     *
     * @return <b>int</b> Retorna printArea-(headerSize+footerSize+actualLine)
     */
    @Override
    public int getRemainingLines() {
        return printArea - totalLine();
    }

    /**
     * M�todo que envia una l�nea en blanco a impresora matricial.
     *
     * @param <b>pLineNumber</b> N�mero de L�neas en blanco a Imprimir.
     */
    @Override
    public void printBlankLine(int pLineNumber) {
        for (int i = 0; i < pLineNumber; i++) printLine(" ", true);
    }

    /**
     * Realiza un Quiebre de P�gina.
     */
    @Override
    public void pageBreak() {
        internalPageBreak(false);
    }

    /**
     * Asigna el Nombre del Programa que ejecuta la Impresi�n.
     */    
    @Override
    public void setProgramName(String pProgramName) {
        programName = pProgramName;
    }

    //public void print

    /* ***************************************************************************
    *                     U T I L I T Y    M E T H O D S                         *
    *****************************************************************************/

    /**
     * Envia C�digo de seteo a la impresora matricial.
     *
     * @param <b>pProperties</b> Colecci�n de char's para seteo de propiedades.
     */
    private void setProperties(char[] pProperties) {
        if (settingHeader) arrayHeader.add(pProperties);
        else if (settingFooter) arrayFooter.add(pProperties);
        else ps.print(pProperties);
    }

    /**
     * Imprime el contenido del Objeto ArrayList.
     *
     * @param <b>pPrintArray</b> Objeto ArrayList que almacena el Header o el Footer.
     */
    private void printArray(ArrayList pPrintArray) {
        String textToPrint = "";
        for (int i = 0; i < pPrintArray.size(); i++) {
            if (pPrintArray.get(i) instanceof String) {
                textToPrint = ((String) pPrintArray.get(i)).trim();
                if (textToPrint.substring(0, 1).equalsIgnoreCase("1")) {
                    ps.println(textToPrint.substring(1, textToPrint.length()));
                    ps.print(carriageReturn);
                } else
                    ps.print(textToPrint.substring(1, textToPrint.length()));
            } else ps.print((char[]) pPrintArray.get(i));
        }
    }

    /**
     * Imprime el N�mero de P�gina.
     */
    private void printPageNumber() {
        if (!includeDatePage) return;
        ps.println(AtuxPrintUtility.fillBlanks(74) + "Pag. " + pageNumber);
        ps.print(carriageReturn);
        pageNumber += 1;
    }

    /**
     * Imprime la Fecha y Hora de Generaci�n del Reporte.
     */
    private void printDatePage() {
        if (!includeDatePage) return;
        ps.println("");
        ps.print(carriageReturn);
        ps.println((programName.length() > 0 ? programName + " - " : "") + "Emision : " + reportDate);
        ps.print(carriageReturn);
    }

    /**
     * Realiza un Quiebre de P�gina - M�todo de uso Interno.
     *
     * @param <b>pIsNormalPageBreak</b> Indica si el quibre de p�gina es natural.
     */
    private void internalPageBreak(boolean pIsNormalPageBreak) {
        if (!pIsNormalPageBreak) {
            for (int i = 1; i < getRemainingLines() + 2; i++) ps.println(" ");
            ps.print(carriageReturn);
        }
        actualLine = 0;
        printArray(arrayFooter);
        printDatePage();
        setProperties(pageBreak);
        if (pageNumber == 2) printArea += 1;
        //printBlankLine(2);
        switch (linesPerPage) {
            case (10): {
                printBlankLine(1);
                break;
            }
            case (24): {
                printBlankLine(1);
                break;
            }
            case (36): {
                printBlankLine(1);
                break;
            }
            case (66): {
                printBlankLine(1);
                break;
            }
            default:
                printBlankLine(1);
        }
        printPageNumber();
        printArray(arrayHeader);
    }

    /**
     * Retorna el total de l�neas consideradas IMPRESAS.
     *
     * @return <b>int</b> Retorna headerSize + footerSize + actualLine
     */
    private int totalLine() {
        return headerSize + footerSize + actualLine;
    }

    /* ***************************************************************************
    *                   P R I N T    S E R V I C E    D E M O                    *
    *****************************************************************************/

    public static void main(String[] args) {
        AtuxPrintService mainPRN = new AtuxPrintService(36, "\\\\192.168.0.227\\reportes", false);
        // Asignando el Nombre del Programa que genera la Impresi�n.
        //mainPRN.setProgramName("Prueba.class");
        // Iniciando Servicio de Impresi�n
        mainPRN.startPrintService();
        /*
        // Seteando el inicio de la Cabecera del Reporte.
        mainPRN.setStartHeader();
        // Caso 1:
        mainPRN.activateCondensed();
        mainPRN.printLine("PRUEBA DE HEADER CONDENSADO",true);
        mainPRN.deactivateCondensed();
        // Caso 2:
        mainPRN.printCondensed("HEADER CONDENSADO",true);
        mainPRN.printDoubleWidthMode("TEXTO DOBLE",true);
        // Seteando el final de la Cabecera del Reporte.
        mainPRN.setEndHeader();
        mainPRN.setStartFooter();
        mainPRN.printLine("PIE DE PAGINA NORMAL",true);
        mainPRN.setEndFooter();
        */
        // Imprimiendo el Cuerpo del Reporte.
        mainPRN.activateCondensed();
        for (int i = 1; i < 90; i++) {
            /*
            if ( mainPRN.getRemainingLines()<10 ) {
              mainPRN.printLine("*QUEDABAN MENOS DE 10 LINEAS",true);
              mainPRN.pageBreak();
            }
            */
            mainPRN.printLine("            " + String.valueOf(i), true);
            //mainPRN.printLine("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890",true);
        }
        mainPRN.deactivateCondensed();
        // Finalizando Servicio de Impresi�n.
        mainPRN.endPrintService();
    }


    /**
     * Setea el Inicio del Trabajo del Servicio de Impresi�n.
     */
    @Override
    public boolean startPrintServiceArchivoTexto() {
        boolean valorRetorno = false;
        try {
            FileOutputStream fos = new FileOutputStream(devicePort);

            ps = new PrintStream(fos);
            ps.print(deactivateCondensed);

            if (includeDatePage) {
                printArea -= 3;
                reportDate = AtuxSearch.getFechaHoraBD(FORMATO_FECHA_HORA);
                printPageNumber();
            }
            valorRetorno = true;
        } catch (FileNotFoundException errFileNotFound) {
            errFileNotFound.printStackTrace();
        } catch (IOException errIO) {
            errIO.printStackTrace();
        } catch (SQLException errGetDateTime) {
            errGetDateTime.printStackTrace();
        } catch (Exception errException) {
            errException.printStackTrace();
        }
        return valorRetorno;
    }
    
    @Override
    public void endPrintServiceArchivoTexto() {
        if (includeDatePage) printDatePage();
        ps.print("\f");
        ps.flush();
        ps.close();
    }


    @Override
    public void printLineSinEspacio(String pText, boolean pChangeLine) {
        setPrintLineSinEspacio(pText, pChangeLine);
        if (pChangeLine) actualLine += 1;
        if (totalLine() > printArea) internalPageBreak(true);
    }

    private void setPrintLineSinEspacio(String pText, boolean pChangeLine) {
        if (settingHeader && pChangeLine) arrayHeader.add("1" + pText);
        else if (settingHeader && !pChangeLine) arrayHeader.add("0" + pText);
        else if (settingFooter && pChangeLine) arrayFooter.add("1" + pText);
        else if (settingFooter && !pChangeLine) arrayFooter.add("0" + pText);
        else if (pChangeLine) {
            ps.print(pText);
            ps.print(carriageReturn);
        } else ps.print(pText);
    }
    
    @Override
    public int getActualLine() {
        return actualLine;
    }
    
    @Override
    public void pageBreakArchivo() {
        internalPageBreak(true);
    }
    
    @Override
    public String printPageNumberArchivo() {
        return "Folio: " + (pageNumber++);
    }
    
    @Override
    public int getPageNumber() {
        return pageNumber;
    }
 
}
