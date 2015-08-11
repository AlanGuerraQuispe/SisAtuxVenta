package atux.util.print;

import atux.util.common.AtuxSearch;
import atux.util.common.AtuxVariables;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;


public class AtuxPrintTicket implements AtuxPrint {
    
   //private char ESC_CUT_PAPER[] = new char[] { 0x1B, '!',(char)1};          
    /**
     * Códigos necesarios para activar Negritas
     */
    //private char activateBold[] = {(char) 27, 'E'};
    
    char[] activateBold = new char[] { 0x1B, 'E',27};
    /**
     * Códigos necesarios para desactivar Negritas
     */
    
    char[] deactivateBold = new char[] { 0x1B, '!',1};    
    //private char deactivateBold[] = {(char) 27, 'F'};

    /**
     * Código necesario para activar Condensando
     */
    private char activateCondensed[] = {(char) 15};
    /**
     * Código necesario para desactivar Condensando
     */
    private char deactivateCondensed[] = {(char) 18};

    /**
     * Códigos necesarios para activar Subrayado
     */
    private char activateUnderline[] = {(char) 27, (char) 45, (char) 49};
    /**
     * Códigos necesarios para desactivar Subrayado
     */
    private char deactivateUnderline[] = {(char) 27, (char) 45, (char) 48};

    /**
     * Códigos necesarios para activar Tamaño Doble de Letra
     */
    private char activateDoubleWidthMode[] = {(char) 27, (char) 87, (char) 49};
    /**
     * Códigos necesarios para desactivar Tamaño Doble de Letra
     */
    private char deactivateDoubleWidthMode[] = {(char) 27, (char) 87, (char) 48};

    /**
     * Códigos necesarios para setear Tamaño de página a 10 líneas
     */
    private char page10Lines[] = {(char) 27, (char) 67, (char) 10};
    /**
     * Códigos necesarios para setear Tamaño de página a 24 líneas - BOLETAS
     */
    private char page24Lines[] = {(char) 27, (char) 67, (char) 24};
    /**
     * Códigos necesarios para setear Tamaño de página a 36 líneas - FACTURAS
     */
    private char page36Lines[] = {(char) 27, (char) 67, (char) 36};
    /**
     * Códigos necesarios para setear Tamaño de página a 51 líneas - GUIAS DE TRANSPORTISTA
     */
    private char page51Lines[] = {(char) 27, (char) 67, (char) 51};
    /**
     * Códigos necesarios para setear Tamaño de página a 66 líneas - PAPEL CONTINUO
     */
    private char page66Lines[] = {(char) 27, (char) 67, (char) 66};

    /**
     * Código necesario para hacer quiebre de página
     */
    private char pageBreak[] = {(char) 12};

    private char pageBreakEPSON[] = {(char) 27, (char) 109};
    //

    /**
     * Códigos necesarios para hacer retorno de carro
     */
    private char carriageReturn[] = {(char) 13};

    /**
     * Almacena la salida que se enviará¡ a la Impresora Matricial
     */
    private PrintStream ps;

    /**
     * Almacena el Tamaño del papel expresado en Número de líneas
     */
    private char pageSize[];
    /**
     * Almacena el Número de líneas de la página
     */
    private int linesPerPage = 0;
    /**
     * Almacena el Número de líneas realmente disponibles de la página
     */
    private int printArea = 0;

    /**
     * Almacena el Número de línea actual de Impresión
     */
    private int actualLine = 0;

    /**
     * Almacena el Tamaño de la Cabecera de Página expresado en Líneas
     */
    private int headerSize = 0;
    /**
     * Almacena el Tamaño del Pie de Página expresado en Líneas
     */
    private int footerSize = 0;

    /**
     * Almacena el Puerto de Salida para la Impresión
     */
    private String devicePort = "";

    /**
     * Indicador de Impresión de Fecha-Hora y Número de Página
     */
    private boolean includeDatePage = false;
    /**
     * Almacena la Fecha de Emisión del Reporte
     */
    private String reportDate = "";
    /**
     * Almacena el Número de Página por Imprimir
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
     * Almacena el Pie de Página del Reporte
     */
    private ArrayList arrayFooter = new ArrayList();
    /**
     * Indicador de Seteo del Pie de Página del Reporte
     */
    private boolean settingFooter = false;

    /**
     * Almacena el Nombre del Programa que Ejecuta el Reporte
     */
    private String programName = "";

    private boolean isStarPrinter = false;

    boolean isTSP100 = false;

    /**
     * Constructor : Inicializa el Tamaño de Página, el Puerto de Salida para la
     * Impresión e indica si se va a imprimir el Número de Página y la Fecha de
     * Generación del Reporte.
     * <p/>
     * Ejemplo :
     * EckerdPrintService printService = new EckerdPrintService(65,"LPT1",true);
     * Imprimirá¡ en Páginas de 66 Líneas a través de LPT1 e Imprimirá¡ Número de
     * Página y Fecha de Generación.
     * Para acceder a una impresora matricial ubicada dentro de la red de Windows
     * se deberá¡ usar la siguiente sintaxis por ejemplo : //aguerra/okidatam
     * <p/>
     * PROCEDIMIENTO :
     * 1.según sea el caso Setear la Cabecera del Reporte.
     * Caso 1:
     * a.Setear Propiedades de Impresión (negrita, condensado, etc).
     * b.Imprimir el Texto.
     * c.Desactivar el Seteo de Propiedades realizado.
     * Ventaja: Setear una sola vez para imprimir varios Textos
     * Caso 2:
     * a.Imprimir el Texto indicando el Seteo de manera directa.
     * Ventaja: Imprimir de manera directa el Texto con la propiedad deseada.
     * 2.según sea el caso Setear el Pie de Página del Reporte.
     * Los casos son los mismos que el Procedimiento 1.
     * 3.Imprimir el Texto del Cuerpo del Reporte.
     * Los casos son los mismos que el Procedimiento 1.
     * <p/>
     * OBSERVACION : El Seteo de la Cabecera y Pie de Página solo es realizado
     * una (1) vez. El Servicio de Impresión detecta cuando y cuantas veces
     * imprimir la Cabecera y/o Pie de Página, de igual modo detecta cuando
     * hacer los correspondientes quiebre de página.
     *
     * @param pLinesPerPage    Tamaño del Papel expresado en Número de Líneas.
     * @param pDevicePort      Puerto de Salida.
     * @param pIncludeDatePage Mostrar Fecha y Numeración de Página.
     */
    public AtuxPrintTicket(int pLinesPerPage,
                             String pDevicePort,
                             boolean pIncludeDatePage) {
        isStarPrinter = false;
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
            case (51): {
                pageSize = page51Lines;
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

    // Este constructor lo invocará las clases que impriman sobre la impresora STAR-SP500
    public AtuxPrintTicket(String pDevicePort) {
        System.out.println("Dentro de AtuxPrintTicket");
        isStarPrinter = true;
        devicePort = pDevicePort;
        isTSP100 = pDevicePort.endsWith("TSP100");
    }


    /**
     * Setea el Inicio del Trabajo del Servicio de Impresión.
     */
    @Override
    public boolean startPrintService() {
        boolean valorRetorno = false;
        try {
            FileOutputStream fos = new FileOutputStream(devicePort);
            ps = new PrintStream(fos);
            if (!isStarPrinter) {
                ps.print(deactivateCondensed);
                ps.print(pageSize);
                ps.print(carriageReturn);
                ps.println(" ");
                ps.print(carriageReturn);
                if (includeDatePage) {
                    printArea -= 3;
                    reportDate = AtuxSearch.getFechaHoraBD(AtuxVariables.FORMATO_FECHA_HORA);
                    printPageNumber();
                }
            } 
            valorRetorno = true;
        } catch (FileNotFoundException errFileNotFound) {
            errFileNotFound.printStackTrace();
        } catch (IOException errIO) {
            errIO.printStackTrace();
            //} catch (SQLException errGetDateTime) { errGetDateTime.printStackTrace();
        } catch (Exception errException) {
            errException.printStackTrace();
        }
        return valorRetorno;
    }

    /**
     * Setea el Fin del Trabajo del Servicio Impresión.
     */
    @Override
    public void endPrintService() {

        System.out.println("DevicePort=" + devicePort);
        System.out.println("vTicketera=" + AtuxVariables.vTicketera);
        System.out.println("isTSP100=" + isTSP100);
        System.out.println("isStarPrinter=" + isStarPrinter);
        if (AtuxVariables.vTicketera.equalsIgnoreCase(AtuxVariables.TICKETERA_EPSON)) {
            for (int i = 0; i < 8; i++) ps.println(" ");
            ps.print(pageBreakEPSON);
        } else {
            if (isTSP100) ps.print(pageBreakEPSON);
            else ps.print(pageBreak);
        }
        //}
        ps.flush();
        ps.close();
    }

    /**
     * Setea el Inicio de la Cabecera de Página.
     */
    @Override
    public void setStartHeader() {
        if (!isStarPrinter) settingHeader = true;
    }

    /**
     * Setea el Fin de la Cabecera de Página.
     */
    @Override
    public void setEndHeader() {
        if (!isStarPrinter) {
            settingHeader = false;
            headerSize += actualLine;
            actualLine = 0;
            printArray(arrayHeader);
        }
    }

    /**
     * Setea el Inicio del Pie de Página.
     */
    @Override
    public void setStartFooter() {
        if (!isStarPrinter) settingFooter = true;
    }

    /**
     * Setea el Fin del Pie de Página.
     */
    @Override
    public void setEndFooter() {
        if (!isStarPrinter) {
            settingFooter = false;
            footerSize += actualLine;
            actualLine = 0;
        }
    }

    /**
     * Activa el modo de Impresión en Negrita.
     */
    @Override
    public void activateBold() {
        setProperties(activateBold);
    }

    /**
     * Desactiva el modo de Impresión en Negrita.
     */
    @Override
    public void deactivateBold() {
        setProperties(deactivateBold);
    }

    /**
     * Imprime el texto en modo Negrita.
     *
     * @param pText       Texto a imprimir.
     * @param pChangeLine Indicador de cambio de línea.
     */
    @Override
    public void printBold(String pText, boolean pChangeLine) {
        activateBold();
        printLine(pText, pChangeLine);
        deactivateBold();
    }

    /**
     * Activa el modo de Impresión en Condensado.
     */
    @Override
    public void activateCondensed() {
        if (!isStarPrinter) {
            setProperties(activateCondensed);
        }
    }

    /**
     * Desactiva el modo de Impresión en Condensado.
     */
    @Override
    public void deactivateCondensed() {
        if (!isStarPrinter) {
            setProperties(deactivateCondensed);
        }
    }

    /**
     * Activa el modo de Impresión en Condensado.
     */    
    public void activateUnderline() {
        setProperties(activateUnderline);
    }

    /**
     * Desactiva el modo de Impresión en Condensado.
     */
    public void deactivateUnderline() {
        setProperties(deactivateUnderline);
    }

    /**
     * Imprime el texto en modo Condensado.
     *
     * @param pText       Texto a imprimir.
     * @param pChangeLine Indicador de cambio de línea.
     */
    @Override
    public void printCondensed(String pText, boolean pChangeLine) {
        activateCondensed();
        printLine(pText, pChangeLine);
        deactivateCondensed();
    }

    /**
     * Activa el modo de Impresión Tamaño Doble de Letra.
     */
    @Override
    public void activateDoubleWidthMode() {
        setProperties(activateDoubleWidthMode);
    }

    /**
     * Desactiva el modo de Impresión Tamaño Doble de Letra.
     */
    @Override
    public void deactivateDoubleWidthMode() {
        setProperties(deactivateDoubleWidthMode);
    }

    /**
     * Imprime el texto en modo Tamaño Doble de Letra.
     *
     * @param pText       Texto a imprimir.
     * @param pChangeLine Indicador de cambio de línea.
     */
    @Override
    public void printDoubleWidthMode(String pText, boolean pChangeLine) {
        activateDoubleWidthMode();
        printLine(pText, pChangeLine);
        deactivateDoubleWidthMode();
    }

    /**
     * Método que envia el texto a impresora matricial.
     *
     * @param pText       Texto a imprimir.
     * @param pChangeLine Indicador de cambio de línea.
     */
    @Override
    public void printLine(String pText, boolean pChangeLine) {
        setPrintLine(pText, pChangeLine);
        if (!isStarPrinter) {
            if (pChangeLine) actualLine += 1;
            if (totalLine() > printArea) internalPageBreak(true);
        }
    }

    /**
     * Método que envia el texto a impresora matricial.
     *
     * @param pText       Texto a imprimir.
     * @param pChangeLine Indicador de cambio de línea.
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
     * Retorna el Número de Líneas disponibles para Impresión.
     *
     * @return int Retorna printArea-(headerSize+footerSize+actualLine)
     */
    @Override
    public int getRemainingLines() {
        return printArea - totalLine();
    }

    /**
     * Método que envia una línea en blanco a impresora matricial.
     *
     * @param pLineNumber Número de Líneas en blanco a Imprimir.
     */
    @Override
    public void printBlankLine(int pLineNumber) {
        for (int i = 0; i < pLineNumber; i++) printLine(" ", true);
    }

    /**
     * Realiza un Quiebre de Página.
     */
    @Override
    public void pageBreak() {
        internalPageBreak(false);
    }

    /**
     * Asigna el Nombre del Programa que ejecuta la Impresión.
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
     * Envia Código de seteo a la impresora matricial.
     *
     * @param pProperties Colección de char's para seteo de propiedades.
     */
    private void setProperties(char[] pProperties) {
        if (settingHeader) arrayHeader.add(pProperties);
        else if (settingFooter) arrayFooter.add(pProperties);
        else ps.print(pProperties);
    }

    /**
     * Imprime el contenido del Objeto ArrayList.
     *
     * @param pPrintArray Objeto ArrayList que almacena el Header o el Footer.
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
     * Imprime el Número de Página.
     */
    private void printPageNumber() {
        if (!includeDatePage) return;
        ps.println(AtuxPrintUtility.fillBlanks(74) + "Pag. " + pageNumber);
        //ps.println(FillerFormat.fill("",' ', 74, FillerFormat.ALG_RIGHT)+"Pag. "+pageNumber);
        ps.print(carriageReturn);
        pageNumber += 1;
    }

    /**
     * Imprime la Fecha y Hora de Generación del Reporte.
     */
    private void printDatePage() {
        if (!includeDatePage) return;
        ps.println("");
        ps.print(carriageReturn);
        ps.println((programName.length() > 0 ? programName + " - " : "") + "Emision : " + reportDate);
        ps.print(carriageReturn);
    }

    /**
     * Realiza un Quiebre de Página - Método de uso Interno.
     *
     * @param pIsNormalPageBreak Indica si el quibre de página es natural.
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
     * Retorna el total de líneas consideradas IMPRESAS.
     *
     * @return int Retorna headerSize + footerSize + actualLine
     */
    private int totalLine() {
        return headerSize + footerSize + actualLine;
    }

    /* ***************************************************************************
    *                   P R I N T    S E R V I C E    D E M O                    *
    *****************************************************************************/

    public static void main(String[] args) {
        //EckerdPrintService mainPRN = new EckerdPrintService(36,"LPT1",false);
        AtuxPrintTicket mainPRN = new AtuxPrintTicket("LPT1");
        mainPRN.startPrintService();
        mainPRN.setStartHeader();
        mainPRN.printLine(" ", true);
        mainPRN.printLine("     ECKERD AMAZONIA S.A.C - INKAFARMA   ", true);
        mainPRN.printLine("Central: Av. Guillermo Dansey # 1845-Lima", true);
        mainPRN.printLine("3159000 - 3142020         RUC 20331066703", true);
        mainPRN.printLine("Tienda MAGDALENA                         ", true);
        mainPRN.printLine("JR. CASTILLA # 718 - MAGDALENA           ", true);
        mainPRN.printLine("Serie BOLETACAJA10                       ", true);
        mainPRN.printLine("Ticket Nro 024-99195759                  ", true);
        mainPRN.printLine("CORR. 0002505664             Pedido: 0001", true);
        mainPRN.printLine("FECHA:05/03/2012 12:04:39 CAJA:1 TURNO: 1", true);
        mainPRN.printLine("-----------------------------------------", true);
        mainPRN.printLine("SR(A). CHIROQUE CARNERO JULIO ANTONIO, UD.", true);
        mainPRN.printLine(" GANO 30 PUNO(S), ACUM 438 PUNTO(S) INKAP", true);
        mainPRN.printLine("UNTO(S)                                  ", true);
        mainPRN.printLine("-----------------------------------------", true);
        mainPRN.printLine("                                         ", true);
        mainPRN.printLine("Cant. Descripcion                 Importe", true);
        mainPRN.printLine("-----------------------------------------", true);
        mainPRN.printLine("  1   3-A OFTENO 0.1% SOL GOT.      55.00", true);
        mainPRN.printLine("        FCOx5ML - LAB.OFTALMICOS S.A.C   ", true);
        mainPRN.printLine("-----------------------------------------", true);
        mainPRN.printLine("                       Redo : S/.   -0.00", true);
        mainPRN.printLine("                       Total  S/.   55.00", true);
        mainPRN.printLine("EFECTIVO S/. 30.00 (Soles)               ", true);
        mainPRN.printLine("VISA ELECTRON S/. 25.00 (Soles)          ", true);
        mainPRN.printLine("VUELTO: S/. 0.00                         ", true);
        mainPRN.printLine("CAJERO: VENTAS USUARIO                   ", true);
        mainPRN.printLine("VENDED: VENTAS USUARIO                   ", true);
        mainPRN.printLine("                                         ", true);
        mainPRN.printLine("      NO HAY DEVOLUCION DE DINERO        ", true);
        mainPRN.printLine("TODO CAMBIO DE MERCADERIA SE HARA DENTRO ", true);
        mainPRN.printLine("DE LAS 48 HORAS PREVIA PRESENTACION DEL  ", true);
        mainPRN.printLine("COMPROBANTE Y VERIFICACION POR PARTE DEL ", true);
        mainPRN.printLine("          QUIMICO FARMACEUTICO           ", true);
        mainPRN.printLine("-----------------------------------------", true);
        mainPRN.printLine("  PAGO CON VISA          TOTAL: S/. 25.00", true);
        mainPRN.printLine("4213********7533           VENCE: 1605   ", true);
        mainPRN.printLine("AP:046678 TER:013825655 REF:0056 LOTE:018", true);
        mainPRN.printLine("          ID: 992120609257918            ", true);
        mainPRN.printLine("-----------------------------------------", true);
        mainPRN.setEndHeader();
        mainPRN.endPrintService();
    }


    /**
     * Setea el Inicio del Trabajo del Servicio de Impresión.
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
                reportDate = AtuxSearch.getFechaHoraBD(AtuxVariables.FORMATO_FECHA_HORA); 
                printPageNumber();
            }
            valorRetorno = true;
        } catch (FileNotFoundException errFileNotFound) {
            errFileNotFound.printStackTrace();
        } catch (IOException errIO) {
            errIO.printStackTrace();
            //} catch (SQLException errGetDateTime) { errGetDateTime.printStackTrace();
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
