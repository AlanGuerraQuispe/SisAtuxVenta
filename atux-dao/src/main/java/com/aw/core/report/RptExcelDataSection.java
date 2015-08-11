package com.aw.core.report;

import com.aw.core.report.custom.RptField;
import com.aw.core.report.custom.RptRenderer;
import com.aw.core.view.IColumnInfo;

import java.util.List;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public class RptExcelDataSection extends RptDataSectionImpl {

    protected void loadBuffer(RptRenderer.PgInfo pgInfo, LineGroup header, LineGroup data) throws Exception {
        List<IColumnInfo> cols = getCustomColumnInfo();
        int colsCharsSize[] = new int[cols.size()];

        // Calcular headers
        ///////////////////
        int colWidthSum = 0;
        int fixedSizeColsSum = 0;
        for (IColumnInfo col : cols) {
            if (col.getReportCharsSize() != -1)
                fixedSizeColsSum += col.getReportCharsSize();
            else
                colWidthSum += col.getWidth();
        }

//        if (fixedSizeColsSum>0){
//            // use fixed cols width
//            fixedSizeColsSum += (cols.size() -1);// add separator spaces
//            int fixedSizeColsRemaining = pgInfo.getColumnWidth() -fixedSizeColsSum;
//            for (int i = 0; i < cols.size(); i++) {
//                IColumnInfo col = cols.get(i);
//                int colWidth = (int) (1.0* col.getWidth() * fixedSizeColsRemaining / colWidthSum);
//                colsCharsSize[i] = col.getReportCharsSize()==-1?colWidth:col.getReportCharsSize();
//            }
//        }else{
//            // additional width used to place single spaces between fields
//            double addditionalWidth = (cols.size() -1)* pgInfo.getColumnCharWidth(colWidthSum);
//            colWidthSum += addditionalWidth;
//            for (int i = 0; i < cols.size(); i++) {
//                IColumnInfo col = cols.get(i);
//                int colWidth = pgInfo.translateX(col.getWidth(), colWidthSum);
//                colsCharsSize[i] = colWidth;
//            }
//        }


        // Imprimir headers
        ///////////////////
        int printedLines = 0;
        if (titulo != null) {
            header.writeX(RptField.b("BC" + pgInfo.getColumnWidth(), titulo));
            header.writeln();
            printedLines++;
        }

        for (int i = 0; i < cols.size(); i++) {
//            if (i>0) header.writeX(RptField.fillLine(' ',1)); //separator
            IColumnInfo col = cols.get(i);
            String text = col.getColumnHeader();
            header.writeX(RptField.b("BC" + colsCharsSize[i], text));
        }
        header.writeln();
        printedLines++;
//        for (int i = 0; i < cols.size(); i++) {
//            if (i>0) header.writeX(RptField.fillLine(' ',1)); //separator
//            IColumnInfo col = cols.get(i);
//            header.writeX(RptField.fillLine('=', colsCharsSize[i]));
//            //renderer.writeBold(FillerFormat.fill("", '=', colsCharsSize[i], FillerFormat.ALG_LEFT));
//        }
//        header.writeln();
//        printedLines++;

        List values = getCustomValues();
        for (int i = valuesPrinted; i < values.size() /*&& printedLines < maxLines*/; i++) {
            Object row = values.get(i);
            for (int j = 0; j < cols.size(); j++) {
                IColumnInfo col = cols.get(j);
//                if (j>0) data.writeX(RptField.fillLine(' ',1)); //separator
                Object colValue = col.getValueDDRFormated(row, j);

                String text = colValue == null ? "" : colValue.toString();

                int width = colsCharsSize[j];

                char alig = 'L';
                if (col.getAlignment() == IColumnInfo.LEFT)
                    alig = 'L';//text = renderer.left(text, width);
                else if (col.getAlignment() == IColumnInfo.CENTER)
                    alig = 'C';//text = renderer.center(text, width);
                else if (col.getAlignment() == IColumnInfo.RIGHT)
                    alig = 'R';//text = renderer.right(text, width);
                data.writeX(RptField.b("N" + alig + width, text));
            }
            data.writeln();
            printedLines++;
            valuesPrinted++;
        }
        //boolean rowsPrinted = valuesPrinted >= values.size();

        // include summary
        //boolean summaryPrintedIfNecessary = summaryRow==null;
        //if (rowsPrinted && printedLines < maxLines && summaryRow!=null){
        //   summaryPrintedIfNecessary = true;

        if (summaryRow != null) {
            for (int j = 0; j < cols.size(); j++) {
                if (j > 0) data.writeX(RptField.fillLine(' ', 1)); //separator
                IColumnInfo col = cols.get(j);
                ColFunction colFunction = summaryRow.get(j);
                Object colValue = colFunction == null ? null : colFunction.getValue(cols, j, values);
                String text = colValue == null ? "" : colValue.toString();

                int width = colsCharsSize[j];
                int alignment = colFunction != null && colFunction.isAlignmentOverride()
                        ? colFunction.getOverrideAlignment() : col.getAlignment();
                char alig = 'L';
                if (col.getAlignment() == IColumnInfo.LEFT)
                    alig = 'L';//text = renderer.left(text, width);
                else if (col.getAlignment() == IColumnInfo.CENTER)
                    alig = 'C';//text = renderer.center(text, width);
                else if (col.getAlignment() == IColumnInfo.RIGHT)
                    alig = 'R';//text = renderer.right(text, width);
                data.writeX(RptField.b("B" + alig + width, text));
            }
            data.writeln();
            printedLines++;
            valuesPrinted++;
        }
    }

}