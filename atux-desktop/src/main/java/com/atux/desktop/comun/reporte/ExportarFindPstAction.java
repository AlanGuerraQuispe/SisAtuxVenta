package com.atux.desktop.comun.reporte;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.report.custom.RptRenderer;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.report.RptFindPresenterBuilder;
import com.aw.swing.report.RptFindPresenterRenderer;


/**
 * Created by IntelliJ IDEA.
* User: Julio C. Macavilca
* Date: 29/05/2009
* Time: 06:32:02 PM
* To change this template use File | Settings | File Templates.
*/
public class ExportarFindPstAction extends Action {
    private Presenter presenter;

    enum RENDER {pdf, excel};
    private RENDER rendererType;

    public ExportarFindPstAction(Presenter presenter) {
        this.presenter = presenter;
    }

    protected Object executeIntern() throws Throwable {
        if (rendererType ==null)
            throw new AWBusinessException("No se configuro el reender. Favor de configurar el código");
        boolean ignorarAuditCols = rendererType == RENDER.pdf;
        RptFindPresenterBuilder builder = SPRptFindFactory.crear(presenter,getGridProvider(),ignorarAuditCols);
        RptRenderer renderer = rendererType== RENDER.excel
                ? RptFindPresenterRenderer.excel().pgLandscape()
                :RptFindPresenterRenderer.pdf().pgLandscape();
        builder.build().render(renderer);
        return null;
    }

    public ExportarFindPstAction pdf(){
        rendererType = RENDER.pdf;
        return this;
    }
    public ExportarFindPstAction xls(){
        rendererType = RENDER.excel;
        return this;
    }
}
