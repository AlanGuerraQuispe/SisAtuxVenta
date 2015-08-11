package com.aw.core.db.support;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * User: Server
 * Date: 09/11/2009
 */
public class AWBasicDataSource extends BasicDataSource {
    @Override
    public Connection getConnection() throws SQLException {
        Connection con = super.getConnection();
        ConnectionWrap wrap = new ConnectionWrap(con, this);  
        return wrap;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
