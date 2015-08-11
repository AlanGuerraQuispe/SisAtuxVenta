package com.aw.core.report;

import com.aw.core.format.FillerFormat;
import com.aw.core.format.Formatter;

/**
 * User: Orlando RT
 * Date: 25-nov-2007
 */
public class PrintField {
    protected int fieldSize;
    protected Formatter formatter = Formatter.VOID;
    protected boolean alignLeft = true;

    public PrintField(int fieldSize) {
        this.fieldSize = fieldSize;
    }
    public PrintField(int fieldSize, Formatter formatter) {
        this(fieldSize);
        this.formatter = formatter;
    }

    public PrintField alignRight(){
        alignLeft = false;
        return this;
    }

    public String print(Object data){
            if (data == null){
                data = "";
            }
            String dataFormatted = formatter.format(null, null, data).toString();
            dataFormatted = FillerFormat.fill(dataFormatted, ' ', fieldSize,
                    alignLeft?FillerFormat.ALG_LEFT:FillerFormat.ALG_RIGHT);
             return dataFormatted;
    }
}
