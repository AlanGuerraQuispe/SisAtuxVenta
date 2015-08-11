package atux.controllers.repository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: razanero
 * Date: Aug 1, 2006
 * Time: 11:01:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SQLExecuter {
    Object execute(Connection con) throws SQLException;
}