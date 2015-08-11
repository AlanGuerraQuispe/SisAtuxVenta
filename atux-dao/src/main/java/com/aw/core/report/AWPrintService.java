package com.aw.core.report;

import com.aw.core.db.DbUtil;
import com.aw.core.format.FillerFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class used to print text to a dot-matrix printer.
 * Date: 16/11/2007
 */
public class AWPrintService {

  protected final Log logger = LogFactory.getLog(getClass());


  /** Códigos necesarios para activar Negritas */
  private char activateBold[]   = {(char)27,'E'};
  /** Códigos necesarios para desactivar Negritas */
  private char deactivateBold[] = {(char)27,'F'};

  /** Código necesario para activar Condensando */
  private char activateCondensed[] = {(char)15};
  /** Código necesario para desactivar Condensando */
  private char deactivateCondensed[] = {(char)18};

  /** Códigos necesarios para activar Subrayado */
  private char activateUnderline[] = {(char)27,(char)45,(char)49};
  /** Códigos necesarios para desactivar Subrayado */
  private char deactivateUnderline[] = {(char)27,(char)45,(char)48};

  /** Códigos necesarios para activar Tamaño Doble de Letra */
  private char activateDoubleWidthMode[] = {(char)27,(char)87,(char)49};
  /** Códigos necesarios para desactivar Tamaño Doble de Letra */
  private char deactivateDoubleWidthMode[] = {(char)27,(char)87,(char)48};

  /** Códigos necesarios para setear tamaño de página a 10 líneas */
  private char page10Lines[] = {(char)27,(char)67,(char)10};
  /** Códigos necesarios para setear tamaño de página a 24 líneas - BOLETAS */
  private char page24Lines[] = {(char)27,(char)67,(char)24};
  /** Códigos necesarios para setear tamaño de página a 36 líneas - FACTURAS */
  private char page36Lines[] = {(char)27,(char)67,(char)36};
  /** Códigos necesarios para setear tamaño de página a 51 líneas - GUIAS DE TRANSPORTISTA */
  private char page51Lines[] = {(char)27,(char)67,(char)51};
  /** Códigos necesarios para setear tamaño de página a 66 líneas - PAPEL CONTINUO */
  private char page66Lines[] = {(char)27,(char)67,(char)66};

  /** Código necesario para hacer quiebre de página */
  private char pageBreak[] = {(char)12};
  /** Códigos necesarios para hacer retorno de carro */
  private char carriageReturn[] = {(char)13};

  /** Almacena la salida que se enviará a la Impresora Matricial */
  private PrintStream ps;

  /** Almacena el tamaño del papel expresado en número de líneas */
  private char pageSize[];
  /** Almacena el número de líneas de la página */
  private int linesPerPage = 0;
  /** Almacena el número de líneas realmente disponibles de la página */
  private int printArea = 0;

  /** Almacena el número de línea actual de impresión */
  private int actualLine = 0;

  /** Almacena el tamaño de la Cabecera de Página expresado en Líneas */
  private int headerSize = 0;
  /** Almacena el tamaño del Pie de Página expresado en Líneas */
  private int footerSize = 0;

  /** Almacena el Puerto de Salida para la impresión */
  private String devicePort = "";

  /** Indicador de Impresión de Fecha-Hora y Número de Página */
  private boolean includeDatePage = false;
  /** Almacena la Fecha de Emisión del Reporte */
  private String reportDate = "";
  /** Almacena el Número de Página por Imprimir */
  private int pageNumber = 1;

  /** Almacena la Cabecera del Reporte */
  private ArrayList arrayHeader = new ArrayList();
  /** Indicador de Seteo de Cabecera del Reporte */
  private boolean settingHeader = false;
  /** Almacena el Pie de Página del Reporte */
  private ArrayList arrayFooter = new ArrayList();
  /** Indicador de Seteo del Pie de Página del Reporte */
  private boolean settingFooter = false;

  /** Almacena el Nombre del Programa que Ejecuta el Reporte */
  private String programName = "";

  private boolean isStarPrinter = false;
    public static final int DEF_LINES_PER_PAGE = 66;

    /**
  *Constructor : Inicializa el Tamaño de Página, el Puerto de Salida para la
  *Impresión e indica si se va a imprimir el Número de Página y la Fecha de
  *Generación del Reporte.
  *
  *Ejemplo :
  *AWPrintService printService = new AWPrintService(65,"LPT1",true);
  *Imprimirá en Páginas de 66 Líneas a través de LPT1 e imprimirá Número de
  *Página y Fecha de Generación.
  *Para acceder a una impresora matricial ubicada dentro de la red de Windows
  *se deberá usar la siguiente sintaxis por ejemplo : //cramirez/okidatam
  *
  *PROCEDIMIENTO :
  *1.Según sea el caso Setear la Cabecera del Reporte.
  *  Caso 1:
  *  a.Setear Propiedades de Impresión (negrita, condensado, etc).
  *  b.Imprimir el Texto.
  *  c.Desactivar el Seteo de Propiedades realizado.
  *  Ventaja: Setear una sola vez para imprimir varios Textos
  *  Caso 2:
  *  a.Imprimir el Texto indicando el Seteo de manera directa.
  *  Ventaja: Imprimir de manera directa el Texto con la propiedad deseada.
  *2.Según sea el caso Setear el Pie de Página del Reporte.
  *  Los casos son los mismos que el Procedimiento 1.
  *3.Imprimir el Texto del Cuerpo del Reporte.
  *  Los casos son los mismos que el Procedimiento 1.
  *
  *OBSERVACION : El Seteo de la Cabecera y Pie de Página solo es realizado
  *una (1) vez. El Servicio de Impresión detecta cuando y cuantas veces
  *imprimir la Cabecera y/o Pie de Página, de igual modo detecta cuando
  *hacer los correspondientes quiebre de página.
  *@param pLinesPerPage Tamaño del Papel expresado en Número de Líneas.
  *@param pDevicePort Puerto de Salida.
  *@param pIncludeDatePage Mostrar Fecha y Numeración de Página.
  */
  public AWPrintService(int pLinesPerPage,
                            String pDevicePort,
                            boolean pIncludeDatePage) {

    isStarPrinter = false;
    linesPerPage = pLinesPerPage;
    printArea = linesPerPage-4;
    devicePort = pDevicePort;
    includeDatePage = pIncludeDatePage;
    switch (linesPerPage) {
      case (10): { pageSize = page10Lines; break; }
      case (24): { pageSize = page24Lines; break; }
      case (36): { pageSize = page36Lines; break; }
      case (51): { pageSize = page51Lines; break; }
      case (66): { pageSize = page66Lines; break; }
      default: pageSize = page66Lines;
    }
    //logger.info("** Inicializando la impresora hacia el device: " + devicePort);
  }

  // Este constructor lo invocarán las clases que impriman sobre la impresora STAR-SP500
  public AWPrintService(String pDevicePort) {
      isStarPrinter = true;
      devicePort = pDevicePort;
  }

  public String obtenerFechaBaseDatos() {
      Date fechaSistema = DbUtil.instance().getCurrentDate();
      String fechaStr = new SimpleDateFormat("dd/MM/yyyy").format(fechaSistema);
      return fechaStr;
  }


    public String obtenerHoraBaseDatos(){
        Date fechaSistema = DbUtil.instance().getCurrentDate();
        String fechaStr = new SimpleDateFormat("HH:mm").format(fechaSistema);
        return fechaStr;
    }
  /**
  *Setea el Inicio del Trabajo del Servicio de Impresión.
  */
  public boolean startPrintService() {
    boolean valorRetorno = false;
    try {
      logger.info("** Inicializando la impresora hacia el device: " + devicePort);
      FileOutputStream fos = new FileOutputStream(devicePort);
      ps = new PrintStream(fos);
      if (!isStarPrinter) {
          ps.print(deactivateCondensed);
          ps.print(pageSize);
          ps.print(carriageReturn);
          ps.println(" ");
          ps.print(carriageReturn);
          if ( includeDatePage ) {
            printArea -= 3;
            reportDate = obtenerFechaBaseDatos();//EckerdSearch.getFechaHoraBD(EckerdConstants.FORMATO_FECHA_HORA);
            printPageNumber();
          }
      }
      valorRetorno = true;
    } catch (Exception e) {
        logger.error(e);
    }
    return valorRetorno;
  }

  /**
  *Setea el Fin del Trabajo del Servicio Impresión.
  */
  public void endPrintService() {
    logger.info("Cerrando impresora");
    if (!isStarPrinter) {
        for (int i=1; i<getRemainingLines()+2; i++) {
          ps.println(" ");
          ps.print(carriageReturn);
        }
        printArray(arrayFooter);
        if ( includeDatePage ) printDatePage();
    }
    ps.print("\f");
    ps.flush();
    ps.close();
  }

  /**
  *Setea el Inicio de la Cabecera de Página.
  */
  public void setStartHeader() {
    if (!isStarPrinter)  settingHeader = true;
  }

  /**
  *Setea el Fin de la Cabecera de Página.
  */
  public void setEndHeader() {
    if (!isStarPrinter) {
        settingHeader = false;
        headerSize += actualLine;
        actualLine = 0;
       printArray(arrayHeader);
    }
  }

  /**
  *Setea el Inicio del Pie de Página.
  */
  public void setStartFooter() {
    if (!isStarPrinter)  settingFooter = true;
  }

  /**
  *Setea el Fin del Pie de Página.
  */
  public void setEndFooter() {
    if (!isStarPrinter) {
      settingFooter = false;
      footerSize += actualLine;
      actualLine = 0;
    }
  }

  /**
  *Activa el modo de impresión en Negrita.
  */
  public void activateBold() { setProperties(activateBold); }

  /**
  *Desactiva el modo de impresión en Negrita.
  */
  public void deactivateBold() { setProperties(deactivateBold); }

  /**
  *Imprime el texto en modo Negrita.
  *@param pText Texto a imprimir.
  *@param pChangeLine Indicador de cambio de línea.
  */
  public void printBold(String pText, boolean pChangeLine) {
    activateBold();
    printLine(pText,pChangeLine);
    deactivateBold();
  }

  /**
  *Activa el modo de impresión en Condensado.
  */
  public void activateCondensed() {
    if (!isStarPrinter) {
      setProperties(activateCondensed);
    }
  }

  /**
  *Desactiva el modo de impresión en Condensado.
  */
  public void deactivateCondensed() {
    if (!isStarPrinter) {
      setProperties(deactivateCondensed);
    }
  }

  /**
  *Activa el modo de impresión en Condensado.
  */
  public void activateUnderline() { setProperties(activateUnderline); }

  /**
  *Desactiva el modo de impresión en Condensado.
  */
  public void deactivateUnderline() { setProperties(deactivateUnderline); }

  /**
  *Imprime el texto en modo Condensado.
  *@param pText Texto a imprimir.
  *@param pChangeLine Indicador de cambio de línea.
  */
  public void printCondensed(String pText, boolean pChangeLine) {
    activateCondensed();
    printLine(pText,pChangeLine);
    deactivateCondensed();
  }

  /**
  *Activa el modo de impresión Tamaño Doble de Letra.
  */
  public void activateDoubleWidthMode() { setProperties(activateDoubleWidthMode); }

  /**
  *Desactiva el modo de impresión Tamaño Doble de Letra.
  */
  public void deactivateDoubleWidthMode() { setProperties(deactivateDoubleWidthMode); }

  /**
  *Imprime el texto en modo Tamaño Doble de Letra.
  *@param pText Texto a imprimir.
  *@param pChangeLine Indicador de cambio de línea.
  */
  public void printDoubleWidthMode(String pText, boolean pChangeLine) {
    activateDoubleWidthMode();
    printLine(pText,pChangeLine);
    deactivateDoubleWidthMode();
  }

  /**
  *Método que envia el texto a impresora matricial.
  *@param pText Texto a imprimir.
  *@param pChangeLine Indicador de cambio de línea.
  */
  public void printLine(String pText, boolean pChangeLine) {
    setPrintLine(pText,pChangeLine);
    if (!isStarPrinter) {
        if ( pChangeLine ) actualLine += 1;
        if ( totalLine()>printArea ) internalPageBreak(true);
    }
  }

  /**
  *Método que envia el texto a impresora matricial.
  *@param pText Texto a imprimir.
  *@param pChangeLine Indicador de cambio de línea.
  */
  private void setPrintLine(String pText, boolean pChangeLine) {
    if ( settingHeader && pChangeLine ) arrayHeader.add("1"+pText);
    else if ( settingHeader && !pChangeLine ) arrayHeader.add("0"+pText);
    else if ( settingFooter && pChangeLine ) arrayFooter.add("1"+pText);
    else if ( settingFooter && !pChangeLine ) arrayFooter.add("0"+pText);
    else if ( pChangeLine ) {
      ps.println(pText);
      if (!isStarPrinter)  ps.print(carriageReturn);
    } else ps.print(pText);
  }

  /**
  *Retorna el Número de Líneas disponibles para impresión.
  *@return int Retorna printArea-(headerSize+footerSize+actualLine)
  */
  public int getRemainingLines() {
    return printArea-totalLine();
  }

  /**
  *Método que envia una línea en blanco a impresora matricial.
  *@param pLineNumber Número de Líneas en blanco a Imprimir.
  */
  public void printBlankLine(int pLineNumber) {
    for (int i=0; i<pLineNumber; i++) printLine(" ",true);
  }

  /**
  *Realiza un Quiebre de Página.
  */
  public void pageBreak() {
    internalPageBreak(false);
  }

  /**
  *Asigna el Nombre del Programa que ejecuta la impresión.
  */
  public void setProgramName(String pProgramName) { programName = pProgramName; }

  //public void print

  /* ***************************************************************************
  *                     U T I L I T Y    M E T H O D S                         *
  *****************************************************************************/

  /**
  * Envia código de seteo a la impresora matricial.
  * @param pProperties Colección de char's para seteo de propiedades.
  */
  private void setProperties(char[] pProperties) {
    if ( settingHeader ) arrayHeader.add(pProperties);
    else if ( settingFooter ) arrayFooter.add(pProperties);
    else ps.print(pProperties);
  }

  /**
  *Imprime el contenido del Objeto ArrayList.
  *@param pPrintArray Objeto ArrayList que almacena el Header o el Footer.
  */
  private void printArray(ArrayList pPrintArray) {
    String textToPrint = "";
    for (int i=0; i<pPrintArray.size(); i++) {
      if ( pPrintArray.get(i) instanceof String ) {
        textToPrint = ((String)pPrintArray.get(i)).trim();
        if ( textToPrint.substring(0,1).equalsIgnoreCase("1") ) {
          ps.println(textToPrint.substring(1,textToPrint.length()));
          ps.print(carriageReturn);
        } else
          ps.print(textToPrint.substring(1,textToPrint.length()));
      } else ps.print((char[])pPrintArray.get(i));
    }
  }

  /**
  *Imprime el Número de Página.
  */
  private void printPageNumber() {
    if ( !includeDatePage ) return;
    //ps.println(EckerdPRNUtility.fillBlanks(74)+"Pag. "+pageNumber);
    ps.println(FillerFormat.fill("",' ', 74, FillerFormat.ALG_RIGHT)+"Pag. "+pageNumber);
    ps.print(carriageReturn);
    pageNumber += 1;
  }

  /**
  *Imprime la Fecha y Hora de Generación del Reporte.
  */
  private void printDatePage() {
    if ( !includeDatePage ) return;
    ps.println("");
    ps.print(carriageReturn);
    ps.println((programName.length()>0?programName+" - ":"") + "Emision : " + reportDate);
    ps.print(carriageReturn);
  }

  /**
  *Realiza un Quiebre de Página - Método de uso Interno.
  *@param pIsNormalPageBreak Indica si el quibre de página es natural.
  */
  private void internalPageBreak(boolean pIsNormalPageBreak) {
    if ( !pIsNormalPageBreak )
      { for (int i=1; i<getRemainingLines()+2; i++) ps.println(" "); ps.print(carriageReturn); }
    actualLine = 0;
    printArray(arrayFooter);
    printDatePage();
    setProperties(pageBreak);
    if ( pageNumber==2 ) printArea += 1;
    //printBlankLine(2);
    switch (linesPerPage) {
      case (10): { printBlankLine(1); break; }
      case (24): { printBlankLine(1); break; }
      case (36): { printBlankLine(1); break; }
      case (66): { printBlankLine(1); break; }
      default: printBlankLine(1);
    }
    printPageNumber();
    printArray(arrayHeader);
  }

  /**
  *Retorna el total de líneas consideradas IMPRESAS.
  *@return int Retorna headerSize + footerSize + actualLine
  */
  private int totalLine() {
    return headerSize + footerSize + actualLine;
  }

  /* ***************************************************************************
  *                   P R I N T    S E R V I C E    D E M O                    *
  *****************************************************************************/

  public static void main(String[] args) {
    //AWPrintService mainPRN = new AWPrintService(36,"\\\\jgonzales\\1170",false);
    AWPrintService mainPRN = new AWPrintService("\\\\jgonzales\\1170");
    // Asignando el Nombre del Programa que genera la impresión.
    //mainPRN.setProgramName("Prueba.class");
    // Iniciando Servicio de Impresión
    mainPRN.startPrintService();

    // Seteando el inicio de la Cabecera del Reporte.
    mainPRN.setStartHeader();
    // Caso 1:
    mainPRN.activateCondensed();
    mainPRN.printLine("PRUEBA DE HEADER CONDENSADO",true);
    mainPRN.deactivateCondensed();

    mainPRN.printLine("PRUEBA DE HEADER NORMAL ",true);
    // Caso 2:
    mainPRN.printCondensed("HEADER CONDENSADO",true);
    mainPRN.printDoubleWidthMode("TEXTO DOBLE misma linea",false);
    mainPRN.printDoubleWidthMode("TEXTO DOBLE",true);
    // Seteando el final de la Cabecera del Reporte.
    mainPRN.setEndHeader();

    mainPRN.printDoubleWidthMode("NORMAL TEXTO DOBLE misma linea",false);
    mainPRN.printCondensed("TEXTO CONDENSED misma linea",false);
    mainPRN.printBold("TEXTO BOLD misma linea",false);
    mainPRN.printCondensed("",true);

    mainPRN.setStartFooter();
    mainPRN.printLine("PIE DE PAGINA NORMAL",true);
    mainPRN.setEndFooter();

    // Imprimiendo el Cuerpo del Reporte.
    mainPRN.activateBold();
    for (int i=0; i<2; i++) {
      /*
      if ( mainPRN.getRemainingLines()<10 ) {
        mainPRN.printLine("*QUEDABAN MENOS DE 10 LINEAS",true);
        mainPRN.pageBreak();
      }
      */
      //mainPRN.printLine("            " + String.valueOf(i),true);
      mainPRN.printLine(FillerFormat.fill(String.valueOf(i),'0',2,FillerFormat.ALG_RIGHT)+" 456789012345678901234567890123456789012",true);
      //mainPRN.printLine("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890",true);
    }
    mainPRN.deactivateBold();
    // Finalizando Servicio de Impresión.
    mainPRN.endPrintService();
  }


  /**
  *Setea el Inicio del Trabajo del Servicio de Impresión.
  */
  public boolean startPrintServiceArchivoTexto() {
    boolean valorRetorno = false;
    try {
      FileOutputStream fos = new FileOutputStream(devicePort);

      ps = new PrintStream(fos);
      ps.print(deactivateCondensed);

      if ( includeDatePage ) {
        printArea -= 3;
        reportDate = obtenerFechaBaseDatos();///EckerdSearch.getFechaHoraBD(EckerdConstants.FORMATO_FECHA_HORA);
        printPageNumber();
      }
      valorRetorno = true;
    } catch (Exception e) {
        logger.error(e);
    }
    return valorRetorno;
  }

  public void endPrintServiceArchivoTexto() {
    if ( includeDatePage ) printDatePage();
    ps.print("\f");
    ps.flush();
    ps.close();
  }


  public void printLineSinEspacio(String pText, boolean pChangeLine) {
    setPrintLineSinEspacio(pText,pChangeLine);
    if ( pChangeLine ) actualLine += 1;
    if ( totalLine()>printArea ) internalPageBreak(true);
  }

  private void setPrintLineSinEspacio(String pText, boolean pChangeLine) {
    if ( settingHeader && pChangeLine ) arrayHeader.add("1"+pText);
    else if ( settingHeader && !pChangeLine ) arrayHeader.add("0"+pText);
    else if ( settingFooter && pChangeLine ) arrayFooter.add("1"+pText);
    else if ( settingFooter && !pChangeLine ) arrayFooter.add("0"+pText);
    else if ( pChangeLine ) {
      ps.print(pText);
      ps.print(carriageReturn);
    } else ps.print(pText);
  }

  public int getActualLine()
  {
    return actualLine;
  }

  public void pageBreakArchivo() {
    internalPageBreak(true);
  }

  public String printPageNumberArchivo() {
    return "Folio: " + (pageNumber++);
  }

  public int getPageNumber() {
      return pageNumber;
  }

    public void setLinesPerPage(int linesPerPage) {
        this.linesPerPage = linesPerPage;
    }

    public PrintStream getPs() {
        return ps;
    }

    public void setStarPrinter(boolean starPrinter) {
        isStarPrinter = starPrinter;
    }
}
