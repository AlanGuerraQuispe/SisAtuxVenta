package com.aw.core.report.custom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Imprime una seccion de detalle
 * - Primero se carga en un buffer toda la data
 * - Luego se imprime la seccion en 1 o varias llamadas si hay mas lineas del tamaño maximo de la seccion
 *
 * User: JCM
 * Date: 20/11/2007
 */
public abstract class RptSectionBuffered extends RptSection{
    /** Buffer de cabecera */
    LineGroup header = new LineGroup();
    /** Buffer de data */
    LineGroup data = new LineGroup();

    /** Contador interno de lineas impresas */
    int printedLineIdx = 0;
    /** Flag para completar con líneas hasta el tamaño máximo de la seccion */
    protected boolean fillSectionSpace = false;

    public RptSectionBuffered(String name, int type) {
        super(name, type);
    }

    public int getLines(){
        return header.getLines().size()+data.getLines().size();
    }

    public boolean print(int maxLines, RptRenderer renderer) throws Exception{
        int headerLinesPrinted = reallyPrint(header.getLines(), 0, -1, renderer);
        if (maxLines!=-1) maxLines-= headerLinesPrinted;
        printedLineIdx +=reallyPrint(data.getLines(), printedLineIdx, maxLines, renderer);
        return printedLineIdx >=data.getLines().size();
    }

    /**
     * Imprime un bloque de texto (desde el buffer)
     * @param data
     * @param printeLineIdx
     * @param maxLines
     * @param renderer
     * @return
     * @throws Exception
     */
    private int reallyPrint(List<Line> data, int printeLineIdx, int maxLines,  RptRenderer renderer) throws Exception {
        int linesPrinted = 0;

        // imprimir lineas, hasta llegar al maximo de la seccion o se acabe la data 
        for (int i = printeLineIdx; i < data.size() && (linesPrinted < maxLines || maxLines==-1); i++) {
            Line line = data.get(i);
            renderer.writelnX( line.getFieldsArray() );
            linesPrinted++;
        }
        // llenar espacio restante hasta completar tamaño maximo de la seccion
        while( fillSectionSpace && maxLines != -1 && linesPrinted < maxLines ) {
            renderer.writelnX();
            linesPrinted++;
        }

        return linesPrinted;
    }

    /**
     * Llamado antes de iniciar la impresion
     * @param renderer
     */
    public void initialize(RptRenderer renderer) throws Exception {
        loadBuffer(renderer.pg(), header, data);
    }

    protected abstract void loadBuffer(RptRenderer.PgInfo pgInfo, LineGroup header, LineGroup data) throws Exception;



    protected static class LineGroup{
        private List<Line> lines = new ArrayList();
        private Line lineBuff;

        public void writeX(RptField... rptFields) throws Exception {
            getBuffer().add(rptFields);
        }
        public void writelnX(RptField... rptFields) throws Exception {
            getBuffer().add(rptFields);
            commitBuffer();
        }
        public void writeln() throws Exception {
            if (lineBuff==null)
                getBuffer().add(RptField.b("NL","")); //empty
            commitBuffer();
        }

        private void commitBuffer() {
            if (lineBuff!=null)
                lines.add(lineBuff);
            lineBuff = null;
        }

        public Line getBuffer() {
            if (lineBuff==null) lineBuff = new Line();
            return lineBuff;
        }

        public List<Line> getLines() {
            return lines;
        }
    }
    protected static class Line{
        List<RptField> fields = new ArrayList<RptField>(5);

        public void add(RptField field) {
            this.fields.add(field);
        }

        public void add(RptField[] rptFields) {
            fields.addAll(Arrays.asList(rptFields));
        }

        public RptField[] getFieldsArray() {
            return fields.toArray(new RptField[fields.size()]);
        }
    }
}