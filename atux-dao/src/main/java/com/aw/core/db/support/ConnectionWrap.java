package com.aw.core.db.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * User: Server
 * Date: 09/11/2009
 */
public class ConnectionWrap implements Connection{
    protected final Log logger = LogFactory.getLog(getClass());
    public static int instanceCount;
    private static int instanceOpenned;
    private static List<ConnectionWrap> instanceOpennedObjs = new ArrayList();
    AWBasicDataSource awBasicDataSource;
    Throwable ex;
    protected Connection wrap;



    public ConnectionWrap(Connection wrap, AWBasicDataSource awBasicDataSource){
        instanceCount++;
        this.awBasicDataSource = awBasicDataSource;
        if (instanceOpenned> 3)
            printStackTrace();
        logger.error("JDBCWRAP:GET "+buildLog());
        ex = new Throwable(buildLog());
        instanceOpenned++;
        instanceOpennedObjs.add(this);
        this.wrap=wrap;
    }


    public static void printStackTrace() {
        for (ConnectionWrap wrap: instanceOpennedObjs) {
            wrap.ex.printStackTrace();
        }
    }

    private String buildLog() {
        return " open:"+instanceOpenned+
                " tot:"+instanceCount+
                " act:"+awBasicDataSource.getNumActive()+
                " idle:"+awBasicDataSource.getNumIdle();
    }

    protected void finalize() throws Throwable {
        instanceCount--;
        super.finalize();
    }


    public int getHoldability() throws SQLException {
        return wrap.getHoldability();
    }

    public int getTransactionIsolation() throws SQLException {
        return wrap.getTransactionIsolation();
    }

    public void clearWarnings() throws SQLException {
        wrap.clearWarnings();
    }

    public void close() throws SQLException {
        wrap.close();
        instanceOpenned--;
        instanceOpennedObjs.remove(this);
        logger.error("JDBCWRAP:CLS "+buildLog());
        wrap = null;
    }


    public void commit() throws SQLException {
        wrap.commit();
    }

    public void rollback() throws SQLException {
        if (wrap!=null) wrap.rollback();
    }

    public boolean getAutoCommit() throws SQLException {
        return wrap.getAutoCommit();
    }

    public boolean isClosed() throws SQLException {
        return wrap.isClosed();
    }

    public boolean isReadOnly() throws SQLException {
        return wrap.isReadOnly();
    }

    public void setHoldability(int holdability) throws SQLException {
        wrap.setHoldability(holdability);
    }

    public void setTransactionIsolation(int level) throws SQLException {
        wrap.setTransactionIsolation(level);
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        wrap.setAutoCommit(autoCommit);
    }

    public void setReadOnly(boolean readOnly) throws SQLException {
        wrap.setReadOnly(readOnly);
    }

    public String getCatalog() throws SQLException {
        return wrap.getCatalog();
    }

    public void setCatalog(String catalog) throws SQLException {
        wrap.setCatalog(catalog);
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return wrap.getMetaData();
    }

    public SQLWarning getWarnings() throws SQLException {
        return wrap.getWarnings();
    }

    public Savepoint setSavepoint() throws SQLException {
        return wrap.setSavepoint();
    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        wrap.releaseSavepoint(savepoint);
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        wrap.rollback(savepoint);
    }

    public Statement createStatement() throws SQLException {
        return wrap.createStatement();
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return wrap.createStatement(resultSetType, resultSetConcurrency);
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return wrap.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    public Map getTypeMap() throws SQLException {
        return wrap.getTypeMap();
    }


    public void setTypeMap(Map map) throws SQLException {
        wrap.setTypeMap(map);
    }

    public String nativeSQL(String sql) throws SQLException {
        return wrap.nativeSQL(sql);
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        return wrap.prepareCall(sql);
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return wrap.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return wrap.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return wrap.prepareStatement(sql);
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return wrap.prepareStatement(sql, autoGeneratedKeys);
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return wrap.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return wrap.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    public PreparedStatement prepareStatement(String sql, int columnIndexes[]) throws SQLException {
        return wrap.prepareStatement(sql, columnIndexes);
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        return wrap.setSavepoint(name);
    }

    public PreparedStatement prepareStatement(String sql, String columnNames[]) throws SQLException {
        return wrap.prepareStatement(sql, columnNames);
    }

    @Override
    public Clob createClob() throws SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Blob createBlob() throws SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public NClob createNClob() throws SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
