package com.aw.swing.report;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.report.custom.RptDocument;
import com.aw.core.report.custom.RptSection;
import com.aw.core.report.custom.RptTitleSectionImpl;
import com.aw.core.view.IColumnInfo;
import com.aw.swing.mvp.JDialogView;
import com.aw.swing.mvp.binding.component.BndSJTable;
import com.aw.swing.mvp.grid.GridProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.List;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public class RptBeanListBuilder {
    protected final Log logger = LogFactory.getLog(getClass());
    //public String K_VIEW_DESCRIPCION = "title";
    public int K_LINE_IMPRECISION = 3;

    private List beanList;
    private List<IColumnInfo> columnInfoList;
    private String title;
    private GridProvider         gridProviderOverride;
    //private RptListBuilder rptListBuilder;


    protected RptSection         pageHeaderSection = null;
    protected RptTitleSectionImpl titleSection = new RptTitleSectionImpl();
    protected RptFilterSectionSwingImpl filterSection = new RptFilterSectionSwingImpl();
    protected RptBeansDataSectionImpl dataSection = new RptBeansDataSectionImpl();
    protected RptSection         pageFooterSection = null;

    public RptBeanListBuilder(String title,List<IColumnInfo> columnInfoList, List beanList) {
        this.beanList = beanList;
        this.columnInfoList= columnInfoList;
        this.title = title;
    }

    public RptDocument build() {
        RptDocument documentSpec = new RptDocument(title);
        if (pageHeaderSection!=null)
            documentSpec.getSections().add(pageHeaderSection);
        documentSpec.getSections().add(titleSection);
        if (filterSection!=null && !filterSection.isEmptySection() )
            documentSpec.getSections().add(filterSection);
        documentSpec.getSections().add(dataSection);
        if (pageFooterSection!=null)
            documentSpec.getSections().add(pageFooterSection);
        return documentSpec;
    }

    public RptBeanListBuilder buildTitle() {
        return buildTitle(null);
    }

    public RptBeanListBuilder buildTitle(String title) {
        if (title ==null){
            title =  this.title;
        }
        titleSection.setTitle(title);
        return this;
    }

    public RptBeanListBuilder buildTitle(String title,int indent) {
        buildTitle(title);
        titleSection.setIndent(indent);
        return this;
    }





    public RptBeanListBuilder buildData() {
           if(beanList.size()==0){
            throw new AWBusinessException("No hay registros para realizar la impresión");
        }
        return buildData(beanList,columnInfoList);
    }



    public RptBeanListBuilder buildData(List beanList,List<IColumnInfo> columnInfoList) {
        dataSection.setColumnInfoList(columnInfoList);
        dataSection.setBeanList(beanList);
        return this;
    }


    public RptFilterSectionSwingImpl getFilterSection() {
        return filterSection;
    }

    public RptTitleSectionImpl getTitleSection() {
        return titleSection;
    }

    public RptBeanListBuilder setTitleSection(RptTitleSectionImpl titleSection) {
        this.titleSection = titleSection;
        return this;
    }

    public RptBeanListBuilder setFilterSection(RptFilterSectionSwingImpl filterSection) {
        this.filterSection = filterSection;
        return this;
    }

    public RptBeanListBuilder setPageFooterSection(RptSection pageFooterSection) {
        this.pageFooterSection = pageFooterSection;
        return this;
    }

    public void setPageHeaderSection(RptSection pageHeaderSection) {
        this.pageHeaderSection = pageHeaderSection;
    }

    public void setGridProvider(GridProvider gridProvider) {
        this.gridProviderOverride = gridProvider;
    }


}
