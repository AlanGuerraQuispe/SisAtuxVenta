package com.aw.core.report.pdf;

import com.aw.core.report.pdf.support.*;
import com.aw.core.view.IColumnInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * User: gmc
 * Date: 01/10/2009
 */
public class PdfIngresoRenderer extends PdfRenderer<BNIngreso> {


    public PdfIngresoRenderer() {
        setPgInfo(new PgInfo().setAsLandScape());
        setTitle("TÍTULO");
        setPdfFooter(getFooter());
    }

    @Override
    protected void renderInternal(){
        createBody();
    }

    public void createBody() {
        FormTable formTable = new FormTable();
        formTable.setColumnsInfo(getFormColumnsInfo());
        formTable.add(new FormCell("Moneda"));
        formTable.add(new FormCell("mon"));
        formTable.add(new EmptyCell().setColSpan(3).noBorder());
        formTable.add(new FormCell(""));
        formTable.add(new FormCell("RUC").noBorder().alignCenter());
        formTable.add(new FormCell("RAZÓN SOCIAL").noBorder().setColSpan(2).alignCenter());
        formTable.add(new FormCell("GUÍA").noBorder().alignCenter());
        formTable.add(new FormCell("Proveedor"));
        formTable.add(new FormCell(""));
        formTable.add(new FormCell("").setColSpan(2));
        formTable.add(new FormCell(""));
        formTable.add(new FormCell("Transportista"));
        formTable.add(new FormCell(""));
        formTable.add(new FormCell("").setColSpan(2));
        formTable.add(new FormCell(""));
        formTable.add(new FormCell("Age Imp."));
        formTable.add(new FormCell(""));
        formTable.add(new FormCell("País"));
        formTable.add(new FormCell("").setColSpan(2));
        addToDocument(formTable);
        GridTable gridTable = new GridTable(getColumnInfo(), getValues());
        gridTable.setSpacingAfter(0f);
        addToDocument(gridTable);
        formTable = new FormTable();
        formTable.setColumnsInfo(new float[]{90,10});
        formTable.add(new FormCell("Total").alignRigth());
        formTable.add(new FormCell("12").alignLeft());
        addToDocument(formTable);
    }


    public FormColumnInfo[] getFormColumnsInfo() {
        FormColumnInfo[] formColumnsInfo = new FormColumnInfo[]{
                (FormColumnInfo) new FormColumnInfo(8).noBorder(),
                (FormColumnInfo) new SeparatorFormColumnInfo(2).noBorder(),
                new FormColumnInfo(10),
                new FormColumnInfo(10),
                new FormColumnInfo(10),
                new FormColumnInfo(10)
        };
        return formColumnsInfo;
    }

    public GridColumnInfo[] getColumnInfo() {
        return new GridColumnInfo[]{
                new GridColumnInfo("Ítem OC", "itemOc", 10, IColumnInfo.RIGHT),
                new GridColumnInfo("RC", "rc", 10, IColumnInfo.CENTER),
                new GridColumnInfo("Código", "codigo", 10, IColumnInfo.CENTER),
                new GridColumnInfo("Descripción", "descripcion", 10, IColumnInfo.CENTER),
                new GridColumnInfo("UM", "unidadMedida", 10, IColumnInfo.CENTER),
                new GridColumnInfo("Cantidad\nComprada", "caComprada", 10, IColumnInfo.RIGHT),
                new GridColumnInfo("Cantidad\nLlegada", "caLlegada", 10, IColumnInfo.RIGHT),
                new GridColumnInfo("Cantidad\nPendiente", "caPendiente", 10, IColumnInfo.RIGHT),
                new GridColumnInfo("Ubicación", "ubicacion", 10, IColumnInfo.RIGHT),
                new GridColumnInfo("Stock", "stock", 10, IColumnInfo.RIGHT)
        };
    }

    public List getValues() {
        List values = new ArrayList();
        for (int i = 0; i < 80; i++) {
            BNIngresoRst bn = new BNIngresoRst(i + 1, "0200177" + i + "-1", "0906001041", "Corte de Grass", "", null, null, null, "ubic", null);
            values.add(bn);
        }
        return values;
    }

    public AWPdfTable getFooter() {
        FormTable footer = new FormTable();
        footer.setColumnsInfo(new float[]{50,50});
        footer.add(new FormCell("Footer").alignRigth());
        footer.add(new FormCell("XX").alignLeft());
        return footer;
    }
}
