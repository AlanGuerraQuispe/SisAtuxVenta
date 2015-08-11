package com.aw.core.domain.security;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.domain.entity.UserInfo;

/**
 * User: gmc
 * Date: 01/04/2009
 */
// todo gmc revisar luego
public class SecurityContext {
    static ThreadLocal<UserInfo> usrInfoThreadLocal = null;

    static final SecurityContext instance = new SecurityContext();

    private SecurityContext() {
    }

    public static SecurityContext instance(){
        return instance;
    }

    public UserInfo getUsrInfo() {
        UserInfo usrCfg = getUsrInfoThreadLocal().get();
        return usrCfg;
    }

    private ThreadLocal<UserInfo> getUsrInfoThreadLocal() {
        if (usrInfoThreadLocal == null) {
            usrInfoThreadLocal = new ThreadLocal<UserInfo>();
        }
        return usrInfoThreadLocal;
    }

    public void setUsrInfoThreadLocal(UserInfo usrCfg) {
        getUsrInfoThreadLocal().set(usrCfg);
    }

    public void removeUsrInfoThreadLocal() {
        if (usrInfoThreadLocal == null)
            throw new AWBusinessException("Context has not been set for being used as threadlocal");
        usrInfoThreadLocal.remove();
    }

}
