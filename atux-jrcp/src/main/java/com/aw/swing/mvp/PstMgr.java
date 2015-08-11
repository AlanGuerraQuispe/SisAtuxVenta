package com.aw.swing.mvp;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.exception.AWSystemException;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.security.SecurityChecker;
import com.aw.swing.mvp.security.SecurityCodeManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

import javax.swing.*;

/**
 * User: gmc
 * Date: 22/04/2009
 */
public class PstMgr {
    protected final Log logger = LogFactory.getLog(getClass());

    protected ApplicationContext appCtx;
    protected SecurityCodeManager securityCodeManager;
    protected SecurityChecker securityChecker;

    protected static PstMgr instance = new PstMgr();

    public static PstMgr instance() {
        return instance;
    }

    public <T extends Presenter> T getPst(Class<T> clazz) {
        return getPst(clazz,"");
    }

    public <T extends Presenter> T getPst(Class<T> clazz,String titleSufix) {
        final T pst = getPstInternal(clazz);
        if(SwingUtilities.isEventDispatchThread()){
            pst.getViewMgr().setViewSrc(pst.createView());
        }else{
            try {
                SwingUtilities.invokeAndWait(new Runnable(){
                    public void run() {
                        pst.getViewMgr().setViewSrc(pst.createView());
                    }
                });
            } catch (Throwable e) {
                throw new AWBusinessException("Problems creating the view for:"+ pst,e);
            }
        }

        AWPresenter awPresenter = AnnotationUtils.findAnnotation(pst.getClass(), AWPresenter.class);
        pst.getViewMgr().setTitle(getTitle(awPresenter.title(),titleSufix));
        pst.getSecurityMgr().setEnabledSecurity(awPresenter.secure());
        pst.getSecurityMgr().setSecurityCodeSetter(securityCodeManager);
        pst.getSecurityMgr().setSecurityChecker(securityChecker);
        return pst;
    }

    private String getTitle(String title,String titleSufix) {
        if (StringUtils.hasText(titleSufix)){
            return title + " - " + titleSufix;
        }
        return title;
    }

    private <T extends Presenter> T getPstInternal(Class<T> clazz) {
        try {
            if (appCtx != null) {
                String[] beanNames = appCtx.getBeanNamesForType(clazz);
                int index = getIndexOfTheSpecificPst(beanNames, clazz);
                return (T) appCtx.getBean(beanNames[index]);
            }
            return (T) clazz.newInstance();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new AWSystemException("Problems creating a instance for:<" + clazz + ">", e);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new AWSystemException("Problems creating a instance for:<" + clazz + ">", e);
        }
    }

    private int getIndexOfTheSpecificPst(String[] beanNames, Class clazz) {
        for (int i = 0; i < beanNames.length; i++) {
            String beanName = beanNames[i];
            Object bean = appCtx.getBean(beanName);
            if (bean.getClass().getName().equals(clazz.getName())) {
                return i;
            }
        }
        return -1;
    }

    public void setAppCtx(ApplicationContext appCtx) {
        this.appCtx = appCtx;
    }

    public void setSecurityCodeSetter(SecurityCodeManager securityCodeManager) {
        this.securityCodeManager = securityCodeManager;
    }

    public void setSecurityChecker(SecurityChecker securityChecker) {
        this.securityChecker = securityChecker;
    }
}
