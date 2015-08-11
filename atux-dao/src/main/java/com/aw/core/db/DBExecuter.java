package com.aw.core.db;

import com.aw.core.dao.DAOIntgr;

/**
 * User: gmc
 * Date: 23-sep-2008
 */
public abstract class DBExecuter {
    protected DAOIntgr daoIntgr;

    public abstract Object execute();

    public void setDaoIntgr(DAOIntgr daoIntgr) {
        this.daoIntgr = daoIntgr;
    }
}
