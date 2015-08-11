package com.aw.swing.mvp.security;

import com.aw.core.domain.AWBusinessException;
import com.aw.swing.mvp.Presenter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: gmc
 * Date: 04/06/2009
 */
public class SecurityManager {
    protected Log logger = LogFactory.getLog(getClass());
    protected SecurityCodeManager securityCodeManager;
    protected SecurityChecker securityChecker;
    protected Presenter presenter;
    private boolean enabledSecurity = true;
    private Object applicationUser;

    public SecurityManager(Presenter presenter) {
        this.presenter = presenter;
    }

    public void init() {
        if (securityChecker!=null){
            applicationUser = securityChecker.getApplicationUser();
        }

        if (!enabledSecurity) {
            securityChecker = null;
            return;
        }
        if (securityCodeManager == null || securityChecker == null || "true".equals(System.getProperty("com.sider.seguridad.disabled"))) {
            logger.info("SECURITY IS DISABLED.");
            return;
        }
        securityCodeManager.setSecurityCodes(presenter);
        securityChecker.setPstForwarded(presenter.getSecurityCode());
        if (!securityChecker.canAccess(presenter.getSecurityCode())) {
            throw new AWBusinessException("No tiene permisos para acceder a esta pantalla");
        }
        if (securityChecker.isReanOnly()) {
            presenter.setReadOnly(true);
            return;
        }
    }

    public void setSecurityCodeSetter(SecurityCodeManager securityCodeManager) {
        this.securityCodeManager = securityCodeManager;
    }

    public void setSecurityChecker(SecurityChecker securityChecker) {
        this.securityChecker = securityChecker;
        if (securityChecker != null) {
            securityChecker.setPresenter(presenter);
        }
    }

    public SecurityChecker getSecurityChecker() {
        return securityChecker;
    }

    public void setEnabledSecurity(boolean enabledSecurity) {
        this.enabledSecurity = enabledSecurity;
    }

    public Object getApplicationUser() {
        return applicationUser;
    }
}
