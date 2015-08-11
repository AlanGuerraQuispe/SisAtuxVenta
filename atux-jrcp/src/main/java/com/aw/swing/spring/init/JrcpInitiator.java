package com.aw.swing.spring.init;

import com.aw.core.spring.io.ResourceProvider;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.security.SecurityChecker;
import com.aw.swing.mvp.security.SecurityCodeManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * User: gmc
 * Date: 21/04/2009
 */
@Component
public class JrcpInitiator implements ApplicationContextAware, InitializingBean, ResourceLoaderAware {

    private ApplicationContext applicationContext;
    private ResourceLoader resourceLoader;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }

    public <T> T getBean(Class<T> clazz) {
        String[] names = applicationContext.getBeanNamesForType(clazz);
        if (names == null || names.length == 0) {
            return null;
        }
        return (T) applicationContext.getBean(names[0]);
    }

    public void afterPropertiesSet() throws Exception {
        ResourceProvider.setResourceLoader(resourceLoader);
        PstMgr.instance().setAppCtx(applicationContext);
        SecurityCodeManager securityCodeManager = getBean(SecurityCodeManager.class);
        PstMgr.instance().setSecurityCodeSetter(securityCodeManager);
        SecurityChecker securityChecker = getBean(SecurityChecker.class);
        if ("true".equals( System.getProperty("com.sider.seguridad.disabled") ))
            securityChecker = null;
        PstMgr.instance().setSecurityChecker(securityChecker);
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
