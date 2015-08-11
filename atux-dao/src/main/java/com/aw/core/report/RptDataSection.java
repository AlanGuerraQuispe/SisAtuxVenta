package com.aw.core.report;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.report.custom.RptField;
import com.aw.core.report.custom.RptRenderer;
import com.aw.core.report.custom.RptSection;
import com.aw.core.report.custom.RptSectionBuffered;
import com.aw.core.view.IColumnInfo;
import com.aw.support.collection.ListUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * User: JCM
 * Date: 20/11/2007
 */
public class RptDataSection extends RptSectionBuffered {
    protected String titulo;
    protected List<IColumnInfo> customColumnInfo = null;
    protected List<ColFunction> summaryRow = null;

    protected List customValues = null;
    protected int  valuesPrinted = 0;

    public RptDataSection() {
        super("DataSection", RptSection.RPT_DATA);
    }

    public void setCustomColumnInfo(List columns){
        this.customColumnInfo= new ArrayList<IColumnInfo>(columns.size());
        for (Object element : columns)
            customColumnInfo.add((IColumnInfo) element);
    }

    public List<IColumnInfo> getCustomColumnInfo() {
        return customColumnInfo;
    }

    public void setCustomValues(List customValues) {
        this.customValues = customValues;
    }

    public void setSummaryRow(List<ColFunction> summaryRow) {
        this.summaryRow = summaryRow;
    }

    protected List getCustomValues() {
        return customValues;
    }

    /**
     *
     * @param index 0 based
     * @return
     */
    public RptDataSection deleteColumnAt(int index) {
        getCustomColumnInfo().remove(index);
        return this;
    }
//    public RptDataSection addColumnAt(ColumnInfo columnInfo, int index) {
//        if (getCustomColumnInfo().size()<index) index=getCustomColumnInfo().size();
//        if (index< 0) index = 0;
//        getCustomColumnInfo().add(index, columnInfo);
//        return this;
//    }
//    public RptDataSection addColumnAtEnd(ColumnInfo columnInfo) {
//        return addColumnAt(columnInfo, getCustomColumnInfo().size()) ;
//    }


    protected void loadBuffer(RptRenderer.PgInfo pgInfo, LineGroup header, LineGroup data) throws Exception {
        List<IColumnInfo> cols = getCustomColumnInfo() ;
        int colsCharsSize[] = new int[cols.size()];

        // Calcular headers
        ///////////////////
        int colWidthSum = 0;
        int  fixedSizeColsSum = 0;
        for (IColumnInfo col : cols) {
            if (col.getReportCharsSize()!=-1)
                fixedSizeColsSum += col.getReportCharsSize();
            else
                colWidthSum      += col.getWidth();
        }

        if (fixedSizeColsSum>0){
            // use fixed cols width
            fixedSizeColsSum += (cols.size() -1);// add separator spaces
            int fixedSizeColsRemaining = pgInfo.getColumnWidth() -fixedSizeColsSum;
            for (int i = 0; i < cols.size(); i++) {
                IColumnInfo col = cols.get(i);
                int colWidth = (int) (1.0* col.getWidth() * fixedSizeColsRemaining / colWidthSum);
                colsCharsSize[i] = col.getReportCharsSize()==-1?colWidth:col.getReportCharsSize();
            }
        }else{
            // additional width used to place single spaces between fields
            double addditionalWidth = (cols.size() -1)* pgInfo.getColumnCharWidth(colWidthSum);
            colWidthSum += addditionalWidth;
            for (int i = 0; i < cols.size(); i++) {
                IColumnInfo col = cols.get(i);
                int colWidth = pgInfo.translateX(col.getWidth(), colWidthSum);
                colsCharsSize[i] = colWidth;
            }
        }


        // Imprimir headers
        ///////////////////
        int printedLines = 0;
        if (titulo!=null){
            header.writeX(RptField.b("BC"+pgInfo.getColumnWidth(), titulo ));
            header.writeln();
            printedLines++;
        }

        for (int i = 0; i < cols.size(); i++) {
            if (i>0) header.writeX(RptField.fillLine(' ',1)); //separator
            IColumnInfo col = cols.get(i);
            String text = col.getColumnHeader();
            header.writeX(RptField.b("BC"+colsCharsSize[i], text ));
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
                if (j>0) data.writeX(RptField.fillLine(' ',1)); //separator
                Object colValue = col.getValueDDRFormated(row, j);

                String text = colValue == null ? "" : colValue.toString();

                int width = colsCharsSize[j];

                char alig= 'L';
                if (col.getAlignment() == IColumnInfo.LEFT)
                    alig= 'L';//text = renderer.left(text, width);
                else if (col.getAlignment() == IColumnInfo.CENTER)
                    alig= 'C';//text = renderer.center(text, width);
                else if (col.getAlignment() == IColumnInfo.RIGHT)
                    alig= 'R';//text = renderer.right(text, width);
                data.writeX( RptField.b("N"+alig+width, text ) );
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

        if (summaryRow!=null){
            for (int j = 0; j < cols.size(); j++) {
                if (j>0) data.writeX(RptField.fillLine(' ',1)); //separator
                IColumnInfo col = cols.get(j);
                ColFunction colFunction = summaryRow.get(j);
                Object colValue = colFunction==null?null:colFunction.getValue(cols, j, values);
                String text = colValue == null ? "" : colValue.toString();

                int width = colsCharsSize[j];
                int alignment = colFunction!=null&& colFunction.isAlignmentOverride()
                        ? colFunction.getOverrideAlignment() : col.getAlignment();
//                if (alignment == ColumnInfo.LEFT)
//                    text = renderer.left(text, width);
//                else if (alignment == ColumnInfo.CENTER)
//                    text = renderer.center(text, width);
//                else if (alignment == ColumnInfo.RIGHT)
//                    text = renderer.right(text, width);
                char alig= 'L';
                if (col.getAlignment() == IColumnInfo.LEFT)
                    alig= 'L';//text = renderer.left(text, width);
                else if (col.getAlignment() == IColumnInfo.CENTER)
                    alig= 'C';//text = renderer.center(text, width);
                else if (col.getAlignment() == IColumnInfo.RIGHT)
                    alig= 'R';//text = renderer.right(text, width);
//                renderer.writeBold(text);
                data.writeX( RptField.b("B"+alig+width, text ) );
            }
            data.writeln();
            printedLines++;
            valuesPrinted++;
        }
    }


    public static abstract class ColFunction {
        protected final Log logger = LogFactory.getLog(getClass());
        public static int NO_OVERRIDE   = -1;
        int overrideAlignment = NO_OVERRIDE ;

        public abstract Object getValue(List<IColumnInfo> cols, int j, List values) ;


        public int getOverrideAlignment() {
            return overrideAlignment;
        }

        protected boolean isAlignmentOverride() {
            return overrideAlignment==NO_OVERRIDE;
        }
        Object format(IColumnInfo col, Object colValue) {
            if (col.getFormatter() != null) {
                try {
                    colValue = col.getFormatter().format(null, null, colValue);
                } catch (Throwable e) {
                    throw AWBusinessException.wrapUnhandledException(logger, e);
                }
            }
            return colValue;
        }
    }
    public static class ColFreeText extends ColFunction {
        String text;

        public ColFreeText(String text) {
            this.text = text;
        }

        public Object getValue(List<IColumnInfo> cols, int j, List values) {
            return text;
        }
    }
    public static class ColSum extends ColFunction {

        public Object getValue(List<IColumnInfo> cols, int j, List values) {
            IColumnInfo col = cols.get(j);
            Object colValue = ListUtils.sum(values, col.getFieldName(), null);
            colValue = format(col, colValue);
            return colValue;
        }
    }

}