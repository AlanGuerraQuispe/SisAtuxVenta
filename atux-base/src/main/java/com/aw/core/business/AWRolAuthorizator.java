package com.aw.core.business;

/**
 * User: JCM
 * Date: 22/11/2007
 */
public interface AWRolAuthorizator {
    boolean canExecute(Object action);

    void checkActionAuthorized(Object action);

    boolean rolCanExecute(Object rol, Object action);

    void checkActionAuthorized(Object rol, Object action);

    void disableRolActionCheck();

    public boolean isCheckDisabled() ;
}
