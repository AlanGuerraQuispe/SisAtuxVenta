package com.aw.swing.report;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.report.custom.RptField;
import com.aw.core.report.custom.RptRenderer;
import com.aw.core.report.custom.RptSection;
import com.aw.core.report.custom.RptSectionBuffered;
import com.aw.core.view.IColumnInfo;
import com.aw.support.collection.ListUtils;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.*;


/**
 * User: JCM
 * Date: 20/11/2007
 */
public class RptDataSection extends RptSectionBuffered {
    protected String titulo;
    protected List<IColumnInfo> customColumnInfo = null;
    protected List<ColFunction> summaryRow = null;

    protected List             customValues = null;
    int              valuesPrinted = 0;

    public RptDataSection() {
        super("DataSection", RptSection.RPT_DATA);
    }

    public void setCustomColumnInfo(List columns){
        this.customColumnInfo= new ArrayList<IColumnInfo>(columns.size());
        //Collections.addAll(columns, this.customColumnInfo);
        for (Object element : columns)
            customColumnInfo.add((IColumnInfo) element);

        //this.customColumnInfo= new ArrayList<IColumnInfo>(columns);
    }
//    public void setCustomColumnInfo(List<IColumnInfo> columns){
//        this.customColumnInfo=columns;
//    }

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
    public void deleteColumnAt(String colName) {
        List<IColumnInfo> columnInfos = getCustomColumnInfo();
        int idx = -1;
        for (int i = 0; i < columnInfos.size(); i++) {
            IColumnInfo iColumnInfo = columnInfos.get(i);
            if (iColumnInfo!=null && iColumnInfo.getFieldName()!=null && iColumnInfo.getFieldName().equals(colName)){
                idx = i;
                break;
            }
        }
        if (idx!=-1){
            deleteColumnAt(idx);
        }
    }

    public RptDataSection addColumnAt(ColumnInfo columnInfo, int index) {
        if (getCustomColumnInfo().size()<index) index=getCustomColumnInfo().size();
        if (index< 0) index = 0;
        getCustomColumnInfo().add(index, columnInfo);
        return this;
    }
    public RptDataSection addColumnAtEnd(ColumnInfo columnInfo) {
        return addColumnAt(columnInfo, getCustomColumnInfo().size()) ;
    }

//    public int getLines() {
//        return getCustomValues().size();
//    }

    protected void loadBuffer(RptRenderer.PgInfo pgInfo, LineGroup header, LineGroup data) throws Exception {
        List<IColumnInfo> colsIntern = getCustomColumnInfo() ;
//        int colsCharsSize[] = new int[cols.size()];

        // Calcular headers (ancho)
        ///////////////////
        ColHeader colHeader = new ColHeader(pgInfo.getColumnWidth(), colsIntern );
//        int colWidthSum = 0;
//        int  fixedSizeColsSum = 0;
//        for (IColumnInfo col : cols) {
//            if (col.getReportCharsSize()!=-1)
//                fixedSizeColsSum += col.getReportCharsSize();
//            else
//                colWidthSum      += col.getWidth();
//        }
//
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
        if (titulo!=null){
            header.writeX(RptField.b("BC"+pgInfo.getColumnWidth(), titulo ));
            header.writeln();
            printedLines++;
        }

        for (int i = 0; i < colHeader.cols.length; i++) {
            if (i>0) header.writeX(RptField.fillLine(' ',1).formatSep()); //separator
            IColumnInfo col = colHeader.cols[i];
            String text = col.getColumnHeader();
            if (text !=null) text = text.replace("\n", " ");
            header.writeX(RptField.b("BC"+colHeader.widths[i].charSize, text ));
        }
        header.writeln();
        printedLines++;
        for (int i = 0; i < colHeader.cols.length; i++) {
            if (i>0) header.writeX(RptField.fillLine(' ',1).formatSep()); //separator
            IColumnInfo col = colHeader.cols[i];
            String text = col.getColumnHeader();
            header.writeX(RptField.fillLine('=', colHeader.widths[i].charSize).formatSep());
            //renderer.writeBold(FillerFormat.fill("", '=', colsCharsSize[i], FillerFormat.ALG_LEFT));

        }
        header.writeln();
        printedLines++;

        List values = getCustomValues();
        for (int i = valuesPrinted; i < values.size() /*&& printedLines < maxLines*/; i++) {
            Object row = values.get(i);
            for (int j = 0; j < colHeader.cols.length; j++) {
                if (j>0) data.writeX(RptField.fillLine(' ',1).formatSep()); //separator
                IColumnInfo col = colHeader.cols[j];
                Object colValue = col.getValueDDRFormated(row, j);
                
                String text = colValue == null ? "" : colValue.toString();

                int width = colHeader.widths[j].charSize;

                char alig= 'L';
                if (col.getAlignment() == ColumnInfo.LEFT)
                    alig= 'L';//text = renderer.left(text, width);
                else if (col.getAlignment() == ColumnInfo.CENTER)
                    alig= 'C';//text = renderer.center(text, width);
                else if (col.getAlignment() == ColumnInfo.RIGHT)
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
            for (int j = 0; j < colHeader.cols.length; j++) {
                if (j>0) data.writeX(RptField.fillLine(' ',1)); //separator
                IColumnInfo col = colHeader.cols[j];
                ColFunction colFunction = summaryRow.get(j);
                Object colValue = colFunction==null?null:colFunction.getValue(colHeader.cols, j, values);
                String text = colValue == null ? "" : colValue.toString();

                int width = colHeader.widths[j].charSize;
                int alignment = colFunction!=null&& colFunction.isAlignmentOverride()
                        ? colFunction.getOverrideAlignment() : col.getAlignment();
//                if (alignment == ColumnInfo.LEFT)
//                    text = renderer.left(text, width);
//                else if (alignment == ColumnInfo.CENTER)
//                    text = renderer.center(text, width);
//                else if (alignment == ColumnInfo.RIGHT)
//                    text = renderer.right(text, width);
                char alig= 'L';
                if (col.getAlignment() == ColumnInfo.LEFT)
                    alig= 'L';//text = renderer.left(text, width);
                else if (col.getAlignment() == ColumnInfo.CENTER)
                    alig= 'C';//text = renderer.center(text, width);
                else if (col.getAlignment() == ColumnInfo.RIGHT)
                    alig= 'R';//text = renderer.right(text, width);
//                renderer.writeBold(text);
                data.writeX( RptField.b("B"+alig+width, text ) );
            }
            data.writeln();
            printedLines++;
            valuesPrinted++;
        }

        //}

        //return rowsPrinted;

    }

    public static class ColHeader{
        int pageCharSize;
        //int pageWidthSize;
        IColumnInfo[] cols;
        ColWidth[] widths;

        public ColHeader(int columnWidth, List<IColumnInfo> iCols) {
            iCols = removeVisualCols(iCols);
            this.pageCharSize = columnWidth;
            this.pageCharSize  -= iCols.size()-1; // quitar espacio entre cols (numCols-1)
            this.cols = iCols.toArray(new IColumnInfo[iCols.size()]);
            this.widths = new ColWidth[iCols.size()];
            int pageWidthSize = 0;
            for (int i = 0; i < cols.length; i++) pageWidthSize += this.cols[i].getWidth();

            // inicializar
            for (int i = 0; i < cols.length; i++) {
                double ratioInit = this.cols[i].getWidth() * 1.0 * pageCharSize / pageWidthSize;
                widths[i] = new ColWidth(this.cols[i].getFieldName(), ratioInit, this.cols[i].getReportCharsSize() );
            }

            int extraChars = sumColWidth()  - pageCharSize;
            List<ColWidth> sortedCols = new ArrayList( Arrays.asList(widths) );
            class ColComparator implements Comparator<ColWidth> {
                int charSizeVariation;
                ColComparator(int charSizeVariation) {
                    this.charSizeVariation = charSizeVariation;
                }

                public int compare(ColWidth o1, ColWidth o2) {
                    double v1 = o1.deltaEffectAfterModif(charSizeVariation);
                    double v2 = o2.deltaEffectAfterModif(charSizeVariation);
                    if (v1 == v2) return 0;
                    else return v1 < v2 ? -1 : +1;
                }
            };

            while (extraChars != 0){
                int variacion = extraChars>0 ? -1:+1;
                Collections.sort(sortedCols, new ColComparator(variacion));
                ColWidth selectedCol = sortedCols.get(0);
                selectedCol.charSize+=variacion; //reducir
                extraChars+=variacion;
            }

            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < cols.length; i++) {
                IColumnInfo col = cols[i];
                buf.append(col.getColumnHeader()+"W:"+widths[i].charSize);
            }
            LogFactory.getLog(getClass()).info(buf);
            
        }

        private List<IColumnInfo> removeVisualCols(List<IColumnInfo> iCols) {
            ArrayList newCols = new ArrayList<IColumnInfo>();
            for (IColumnInfo iCol : iCols) {
                if (iCol.getReportCharsSize()!=0){
                    newCols.add(iCol);
                }
            }
            return newCols;
        }

        public int sumColWidth(){
            int size = 0;
            for (int i = 0; i < widths.length; i++) size += this.widths[i].charSize;
            return size;
        }
        public class ColWidth {
            String id ;
            double charSizeInit;
            //double ratioPref;
            int    charSizeMin;
            int    charSize;

            public ColWidth(String id, double charSizeInit, int charSizeMin) {
                this.id = id;
                this.charSizeInit = charSizeInit;
                this.charSizeMin = charSizeMin;
                setCharSize ((int) charSizeInit);
            }

            public void setCharSize(int charSize) {
                this.charSize = Math.max( charSize, charSizeMin );
            }

            public boolean canReduceSize(){
                return charSize > charSizeMin;
            }
            /**
             * Ratio after reduccion
             * 0..1
             * 0.0 = width will not be affected (ideal)
             * 0.1 = minimal effect
             * 0.9 = greta affected
             * 1.0 = risk to reduce minimum size. should not be reduced
             */
            public double deltaEffectAfterModif(int charSizeVariation){
                double delta ;
                if (charSizeVariation<0 && !canReduceSize())
                    delta = 1.0;
                 else
                     delta= (charSizeInit- (charSize+charSizeVariation))/charSizeInit;
                return Math.abs ( delta );
            }

            @Override
            public String toString() {
                BigDecimal delta = new BigDecimal(charSizeInit - charSize).setScale(3, BigDecimal.ROUND_HALF_UP);
                BigDecimal ratio = new BigDecimal((charSizeInit - charSize)/ charSizeInit).setScale(3, BigDecimal.ROUND_HALF_UP);
                return id+":W:"+charSize +":D:"+ delta+":R:"+ ratio;
            }
        }
    }


    public static abstract class ColFunction {
        protected final Log logger = LogFactory.getLog(getClass());
        public static int NO_OVERRIDE   = -1;
        int overrideAlignment = NO_OVERRIDE ;

        public abstract Object getValue(IColumnInfo[] cols, int j, List values) ;


        public int getOverrideAlignment() {
            return overrideAlignment;
        }

        private boolean isAlignmentOverride() {
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

        public Object getValue(IColumnInfo[] cols, int j, List values) {
            return text;
        }
    }
    public static class ColSum extends ColFunction {

        public Object getValue(IColumnInfo[] cols, int j, List values) {
            IColumnInfo col = cols[j];
            Object colValue = ListUtils.sum(values, col.getFieldName(), null);
            colValue = format(col, colValue);
            return colValue;

        }

    }

}