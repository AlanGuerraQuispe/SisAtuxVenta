package com.aw.swing.report;


//import com.aw.core.db.DbUtil;
//import com.aw.core.domain.entity.ApplicationUser;
//import com.aw.core.domain.security.SecurityCtx;

import com.aw.core.report.custom.RptTitleSectionImpl;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.Presenter;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * User: JCM
 * Date: 18/12/2007
 */
public class SPRRptTitleSectionImpl extends RptTitleSectionImpl {
    public SPRRptTitleSectionImpl(Class<? extends Presenter> presenterClass) {
        AWPresenter awPresenter = AnnotationUtils.findAnnotation(presenterClass, AWPresenter.class);
        setTitle( awPresenter.title());
    }

}
