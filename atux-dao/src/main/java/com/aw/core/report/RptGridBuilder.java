package com.aw.core.report;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.report.custom.RptDocument;
import com.aw.core.report.custom.RptSection;
import com.aw.core.report.custom.RptTitleSectionImpl;
import com.aw.core.view.IGridInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public class RptGridBuilder {
    protected final Log logger = LogFactory.getLog(getClass());
    public int K_LINE_IMPRECISION = 3;

    private IGridInfo gridInfo;

    protected RptSection pageHeaderSection = null;
    protected RptTitleSectionImpl titleSection = new RptTitleSectionImpl();
    protected RptDataSectionImpl dataSection = new RptDataSectionImpl();
    protected RptSection pageFooterSection = null;
    private boolean isExcelReport=false;
    private boolean isPdfReport=false;

    public RptGridBuilder(IGridInfo gridInfo) {
        this.gridInfo = gridInfo;
    }

    public RptDocument build() {
        RptDocument documentSpec = new RptDocument("Title");
        if (pageHeaderSection != null)
            documentSpec.getSections().add(pageHeaderSection);
        documentSpec.getSections().add(titleSection);
        if (isPdfReport){
            dataSection = new RptPdfDataSection();
            dataSection.setGridInfo(gridInfo);
        }
        if (isExcelReport){
            dataSection = new RptExcelDataSection();
            dataSection.setGridInfo(gridInfo);
        }
        documentSpec.getSections().add(dataSection);
        if (pageFooterSection != null)
            documentSpec.getSections().add(pageFooterSection);
        return documentSpec;
    }

    public RptGridBuilder buildTitle() {
        return buildTitle(null);
    }

    public RptGridBuilder buildTitle(String title) {
//        if (title ==null) title =  findPresenter.getViewMgr().guessTitle();
        titleSection.setTitle(title);
        return this;
    }

    public RptGridBuilder buildTitle(String title, int indent) {
//        if (title ==null) title =  findPresenter.getViewMgr().guessTitle();
        titleSection.setTitle(title);
        titleSection.setIndent(indent);
        return this;
    }

    public RptGridBuilder buildData() {
        if (gridInfo != null && gridInfo.getValues().size() == 0) {
            throw new AWBusinessException("No hay registros para realizar la impresión");
        }
        return buildData(gridInfo);
    }

    public RptGridBuilder buildData(IGridInfo gridInfo) {
        dataSection.setGridInfo(gridInfo);
        return this;
    }


    public RptDataSectionImpl getDataSection() {
        return dataSection;
    }

    public RptTitleSectionImpl getTitleSection() {
        return titleSection;
    }

    public RptGridBuilder setTitleSection(RptTitleSectionImpl titleSection) {
        this.titleSection = titleSection;
        return this;
    }

    public RptGridBuilder setPageFooterSection(RptSection pageFooterSection) {
        this.pageFooterSection = pageFooterSection;
        return this;
    }
    public RptGridBuilder setAsExcelReport() {
        isExcelReport =true;
        return this;
    }
    public RptGridBuilder setAsPdfReport() {
        isPdfReport =true;
        return this;
    }

}
