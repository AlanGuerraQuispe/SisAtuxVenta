package com.aw.swing.mvp.security;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.support.Zone;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * User: gmc
 * Date: 04/06/2009
 */
public abstract class SecurityChecker {
    protected Log logger = LogFactory.getLog(getClass());

    protected Presenter presenter;

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public abstract boolean isReanOnly();

    public abstract boolean canExecute(Action action);

    public abstract boolean canAccess(String view);

    public abstract boolean isReadOnly(Zone zone);

    public abstract boolean canAccess(Zone zone);

    public abstract List getMenuBuilder();

    public abstract Object getApplicationUser();

    public abstract void setPstForwarded(String pstSecurityCode);

}