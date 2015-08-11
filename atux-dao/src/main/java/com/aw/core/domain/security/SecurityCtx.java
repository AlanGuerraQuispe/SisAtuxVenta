package com.aw.core.domain.security;

/**
 * User: gmc
 * Date: 22/05/2009
 */
// todo gmc revisar luego
public class SecurityCtx {
    private static SecurityCtx instance = new SecurityCtx();

//    public ApplicationUser appUser;

    private SecurityCtx() {
    }

    public static SecurityCtx instance() {
        return instance;
    }

//    public ApplicationUser getAppUser() {
//        if (appUser==null)
//            appUser = new ApplicationUser("jcvergara", "Juan Carlos Vergara", "jcver", new Long(2));
//        return appUser;
//    }
//
//    public void setAppUser(ApplicationUser appUser) {
//        this.appUser = appUser;
//    }
}
