package com.aw.swing.report;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.report.custom.RptDocument;
import com.aw.core.report.custom.RptSection;
import com.aw.core.report.custom.RptTitleSectionImpl;
import com.aw.swing.mvp.JDialogView;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.component.BndSJTable;
import com.aw.swing.mvp.grid.GridProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public class RptFindPresenterBuilder {
    protected final Log logger = LogFactory.getLog(getClass());
    //public String K_VIEW_DESCRIPCION = "title";
    public int K_LINE_IMPRECISION = 3;

    private Presenter findPresenter;
    private GridProvider         gridProviderOverride;
    //private RptListBuilder rptListBuilder;


    protected RptSection         pageHeaderSection = null;
    protected RptTitleSectionImpl titleSection = new RptTitleSectionImpl();
    protected RptFilterSectionSwingImpl filterSection = new RptFilterSectionSwingImpl();
    protected RptDataSectionImpl dataSection = new RptDataSectionImpl();
    protected RptSection         pageFooterSection = null;

    public RptFindPresenterBuilder(Presenter findPresenter) {
        this.findPresenter = findPresenter;
    }

    public RptDocument build() {
        RptDocument documentSpec = new RptDocument(findPresenter.getViewMgr().getTitle());
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

    public RptFindPresenterBuilder buildTitle() {
        return buildTitle(null);
    }

    public RptFindPresenterBuilder buildTitle(String title) {
        if (title ==null){
            GridProvider gridProvider = buildGridProvider();
            title =  gridProvider.getGridManager().getIpView().getLblTitGrid(gridProvider.getGridIndex()).getText();

            if (title==null) title =  findPresenter.getViewMgr().getTitle();
        }
        titleSection.setTitle(title);
        return this;
    }

    public RptFindPresenterBuilder buildTitle(String title,int indent) {
        buildTitle(title);
        titleSection.setIndent(indent);
        return this;
    }



    public RptFindPresenterBuilder buildFilter() {
        return buildFilter((JPanel)null);
    }

    public RptFindPresenterBuilder buildFilter(JPanel pnlMain) {
        Component lastCmpBeforeSearchBtn = null;
        Component[] components = null;
        int sectionWidth;

        try {
            if (pnlMain != null) {
                // use given panel enclosing
                components = pnlMain.getComponents();
                sectionWidth = pnlMain.getWidth();
            } else if ((lastCmpBeforeSearchBtn = getLastCmpBeforeSearh()) != null) {
                // try to use lastCmpBeforeSearchBtn and panel enclosing
                components = lastCmpBeforeSearchBtn.getParent().getComponents();
                sectionWidth = lastCmpBeforeSearchBtn.getParent().getWidth();
            } else {
                // try to use all fields (this may need a enclosing panel
                // to avoid all components being added.
                // Note Y is relative to the enclosing panel
                sectionWidth = -1;
                Field[] fields = getFrm().getClass().getFields();
                components = new Component[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    //Class fieldType = field.getType();
                    Object fieldValue = field.get(getFrm());
                    if (fieldValue instanceof Component)
                        components[i] = (Component) fieldValue;
                    if (lastCmpBeforeSearchBtn == null && fieldValue instanceof JButton)
                        lastCmpBeforeSearchBtn = (Component) fieldValue;
                }
            }

            //Field[] fields = getFrm().getClass().getFields();
            for (Component fieldValue : components) {
                //Class fieldType = field.getType();
                //Object fieldValue = field.get(getFrm());
                if (fieldValue instanceof JTextField ||
                        fieldValue instanceof JLabel ||
                        fieldValue instanceof JComboBox) {

                    JComponent jComponent = (JComponent) fieldValue;
                    filterSection.addJCmp(K_LINE_IMPRECISION, jComponent);
                }
            }

            if (lastCmpBeforeSearchBtn != null)
                filterSection.setYLimit(lastCmpBeforeSearchBtn);

            filterSection.setPanelWidth(sectionWidth);
            filterSection.rebuild();
        } catch (Exception e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }

        return this;
    }
    public RptFindPresenterBuilder buildFilter(JComponent[] jComponents) {
        for (JComponent jComponent : jComponents) {
                filterSection.addJCmp(K_LINE_IMPRECISION, jComponent);
        }
        filterSection.setPanelWidth(-1);
        filterSection.rebuild();
        return this;
    }

    public RptFindPresenterBuilder buildData() {
        GridProvider gridProvider = buildGridProvider();

        if(gridProvider.getValues()!=null && gridProvider.getValues().size()==0){
            throw new AWBusinessException("No hay registros para realizar la impresión");
        }
        return buildData(gridProvider.getBndSJTable());
    }

    private GridProvider buildGridProvider() {
        GridProvider gridProvider = gridProviderOverride;
        if (gridProvider==null ) gridProvider = findPresenter.getGridProvider();
        return gridProvider;
    }

    public RptFindPresenterBuilder buildData(BndSJTable bndTable) {
        dataSection.setBndTable(bndTable);
        return this;
    }

    private Component getLastCmpBeforeSearh() {
        try {
            return findPresenter.getIpView().getLastCmpBeforeSearchBtn();
        } catch (Throwable e) {
            return null;
        }
    }

    private Object getFrm() {
        JDialogView jDialogView = (JDialogView) findPresenter.getView();
        return jDialogView.getVsr();
    }

    public RptDataSectionImpl getDataSection() {
        return dataSection;
    }

    public RptFilterSectionSwingImpl getFilterSection() {
        return filterSection;
    }

    public RptTitleSectionImpl getTitleSection() {
        return titleSection;
    }

    public RptFindPresenterBuilder setTitleSection(RptTitleSectionImpl titleSection) {
        this.titleSection = titleSection;
        return this;
    }

    public RptFindPresenterBuilder setFilterSection(RptFilterSectionSwingImpl filterSection) {
        this.filterSection = filterSection;
        return this;
    }

    public RptFindPresenterBuilder setPageFooterSection(RptSection pageFooterSection) {
        this.pageFooterSection = pageFooterSection;
        return this;
    }

    public void setPageHeaderSection(RptSection pageHeaderSection) {
        this.pageHeaderSection = pageHeaderSection;
    }

    public void setDataSection(RptDataSectionImpl dataSection) {
        this.dataSection = dataSection;
    }

    public void setGridProvider(GridProvider gridProvider) {
        this.gridProviderOverride = gridProvider;
    }
}
