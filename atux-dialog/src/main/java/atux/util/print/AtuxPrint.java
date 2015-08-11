package atux.util.print;


public interface AtuxPrint {

    boolean startPrintService();

    void endPrintService();

    void setStartHeader();

    void setEndHeader();

    void setStartFooter();

    void setEndFooter();

    void activateBold();

    void deactivateBold();

    void printBold(String pText, boolean pChangeLine);

    void activateCondensed();

    void deactivateCondensed();

    void printCondensed(String pText, boolean pChangeLine);

    void activateDoubleWidthMode();

    void deactivateDoubleWidthMode();

    void printDoubleWidthMode(String pText, boolean pChangeLine);

    void printLine(String pText, boolean pChangeLine);

    int getRemainingLines();

    void printBlankLine(int pLineNumber);

    void pageBreak();

    void setProgramName(String pProgramName);

    boolean startPrintServiceArchivoTexto();

    void endPrintServiceArchivoTexto();

    void printLineSinEspacio(String pText, boolean pChangeLine);

    int getActualLine();

    void pageBreakArchivo();

    String printPageNumberArchivo();

    int getPageNumber();
    
}
