package com.atux.desktop.comun.reporte;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.report.RptFindPresenterBuilder;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public class SPRptFindFactory {
    /**
     * Crea una instancia en blanco del reporte
     * @param findPresenter presenter en el que se basa el reporte
     * @param gridProvider
     * @param ignorarAuditCols
     * @return
     */
    public static RptFindPresenterBuilder crear(Presenter findPresenter, GridProvider gridProvider, boolean ignorarAuditCols) {
        RptFindPresenterBuilder builder = null;
        builder = new RptFindPresenterBuilder(findPresenter);
        builder.setGridProvider(gridProvider);
        //builder.setPageHeaderSection( new SPRptHeaderSectionImpl() );
        //builder.setTitleSection( new SPRRptTitleSectionImpl(findPresenter.getClass()) );
        builder.buildTitle();
        //builder.buildFilter();  // GMC pidio desabilitar esto y que solo pinte la data del grid
        builder.buildData();
        if (ignorarAuditCols){
            builder.getDataSection().deleteColumnAt("usuaCrea");
            builder.getDataSection().deleteColumnAt("fechCrea");
            builder.getDataSection().deleteColumnAt("usuaModi");
            builder.getDataSection().deleteColumnAt("fechModi");
        }

        //K_VIEW_DESCRIPCION = "descripcion";
        //filterSection.getEmptyFilterValues().add(QTConstants.ITEM_TODOS);
        //builder.setTitleSection( new SPRptTitleSectionImpl() );
        return builder;
    }

}
