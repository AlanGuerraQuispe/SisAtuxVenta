package atux.controllers.repository;

import atux.managerbd.BaseConexion;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: razanero
 * Date: Aug 1, 2006
 * Time: 10:56:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class EckerdManagerRepository implements Repository {

    protected final Log logger = LogFactory.getLog(getClass());

    ////////////////// Simple Query Support ////////////////////
    /**
     * Helper method used to retrieve a list of objects using standard SQL sintaxis
     * The query must return columns that match with setter/getter on the beanClass
     * @param sql SQL query
     * @param filterKeys key used to restrict the search
     * @param beanClass class used to store each row info
     * @return List of object of type <beanClass>
     */
    public List findListOfBeans(String sql, Object[] filterKeys, Class beanClass) {
        return (List) executeQuery(sql, filterKeys, new BeanListHandler(beanClass));
    }

    /**
     * Helper method used to retrieve a list of Arrays using standard SQL sintaxis
     * The query must return columns maintaining the ordinal position on the query
     *
     * @param sql        SQL query
     * @param filterKeys key used to restrict the search
     * @return
     */
    public List findListOfArrays(String sql, Object[] filterKeys) {
        return (List) executeQuery(sql, filterKeys, new ArrayListHandler());
    }

    /**
     * Helper method used to retrieve a list of objects. The object element of the list
     * is determined by the resultSetHandler
     *
     * @param sql              SQL query
     * @param filterKeys       key used to restrict the search
     * @param resultSetHandler maps a resultset row to a object (array, bean, etc)
     * @return List of objects
     */
    public Object executeQuery(String sql, Object[] filterKeys, ResultSetHandler resultSetHandler) {
        QueryRunner queryRunner = new QueryRunner();
        try {
            if (logger.isDebugEnabled())
                logger.debug("Executing:" + sql);

            Connection con = BaseConexion.getConexion();
            return queryRunner.query(con,
                    sql, filterKeys, resultSetHandler);
        } catch (Exception e) {
            logger.error("Error executing query", e);
            throw new AtuxException("Error executing query:" + e);
        }
    }

    /**
     * Runs the {@link SQLExecuter#execute(java.sql.Connection)}
     * method under the curretn transacction.
     * If not trannsaction exists, it create a readonly one
     *
     * @param executer
     * @return
     */
    public Object executeQuery(SQLExecuter executer) {
        try {
            return executer.execute(BaseConexion.getConexion());
        } catch (SQLException e) {
            logger.info(e);
            throw new AtuxException("Error executing query:" + e);
        }
    }

    /**
     * Runs the {@link SQLExecuter#execute(java.sql.Connection)}
     * method under the curretn transacction.
     * If not trannsaction exists, it create a traansactional one
     *
     * @param executer
     * @return
     */
    public Object executeUpdate(SQLExecuter executer) {
        // transactional support given by method declarative on PROXY
        return this.executeQuery(executer);
    }


    /**
     * Helper method used to retrieve a list of objects using standard SQL sintaxis
     * The query must return columns that match with setter/getter on the beanClass
     *
     * @param sql        SQL query
     * @param filterKeys key used to restrict the search
     * @param beanClass  class used to store each row info
     * @return List of object of type <beanClass>
     */
    public Object findObject(String sql, Object[] filterKeys, Class beanClass) {
        List list = findListOfBeans(sql, filterKeys, beanClass);
        if (list.size() == 0) return null;
        else return list.get(0);
    }

    /**
     * Helper method used to retrieve a COLUMN using standard SQL sintaxis
     * The query must return a single column
     *
     * @param sql        SQL query
     * @param filterKeys key used to restrict the search
     * @return First Row First column , NULL if no row
     */
    public Object findObjectGetColumn(String sql, Object[] filterKeys) {
        try {
            Object retval = new QueryRunner().query(
                    BaseConexion.getConexion(), sql, filterKeys, new ScalarHandler());
            return retval;
        } catch (SQLException e) {
            logger.info(e);
            throw new AtuxException("Error executing query:" + e);
        }
    }

    /**
     * Helper method used to retrieve the select count(*)
     *
     * @param sqlFromWhereClause SQL query without the SELECT section: "FROM x where X=y"
     * @param filterKeys         key used to restrict the search
     * @return number of rows that return the query
     */
    public int findCountRows(String sqlFromWhereClause, Object[] filterKeys) {
        String sql = "SELECT count(*) " + sqlFromWhereClause;
        logger.info(sql.toString());
        try {
            Number num = (Number) new QueryRunner().query(
                    BaseConexion.getConexion(), sql, filterKeys, new ScalarHandler());
            return num.intValue();
        } catch (SQLException e) {
            logger.info(e);
            throw new AtuxException("Error executing query:" + e);
        }
    }

    /**
     * Executes a simple SQL update (INSERT/DETELE/UPDATE) operation.
     * Implemtantion uses JdbcTemplate
     *
     * @param sql       SQL expresion, params with ?
     * @param sqlParams array of parameters to replace ? in order that appear
     * @return the numer of rows affected by the SQL operation
     */
    public int execSqlUpdate(String sql, Object[] sqlParams) {
        try {
            return new QueryRunner().update(BaseConexion.getConexion(), sql, sqlParams);
        } catch (SQLException e) {
            logger.info(e);
            throw new AtuxException("Error executing query:" + e);
        }
    }

}
