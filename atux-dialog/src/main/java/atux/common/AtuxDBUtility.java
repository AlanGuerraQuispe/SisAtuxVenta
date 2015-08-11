package atux.common;

import atux.managerbd.BaseConexion;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import atux.util.common.AtuxTableModel;
import atux.util.common.AtuxVariables;
import oracle.jdbc.OracleTypes;


public class AtuxDBUtility {

    private static ArrayList parametros = new ArrayList();
    
    public static String getFechaHoraBD(int pTipo) throws SQLException {
        String fechahora = "";
        String query = "SELECT TO_CHAR(SYSDATE,'dd/mm/yyyy hh24:mi:ss') FROM DUAL";
        Statement stmt = ((Connection) BaseConexion.getConexion()).createStatement();
        // Ejecutar el query haciendo uso de la conexion por default
        ResultSet results = stmt.executeQuery(query);
        if (results.next()) fechahora = results.getString(1);
        results.close();
        stmt.close();
        // Determinar formato de fecha_hora para retornar el valor
        // Valores de retorno : dd/mm/yyyy
        //                      dd/mm/yyyy hh24:mi:ss
        if (fechahora.trim().length() > 0 && pTipo == 1) //FORMATO_FECHA dd/mm/yyyy
            fechahora = fechahora.substring(0, 10);
        return fechahora;
    }
    
    public static void getImpresoraTestigo(ArrayList pDatos) throws SQLException {        
        parametros = new ArrayList();
        parametros.add(AtuxVariables.vCodigoCompania);
        parametros.add(AtuxVariables.vCodigoLocal);
        AtuxDBUtility.executeSQLStoredProcedureArrayList(pDatos, "INKVENTA_MANTENIMIENTO.RUTA_TESTIGO_ACTIVO(?,?)", parametros);

    }
     
    /**
     * Retorna la coleccion de datos del Query ejecutado.  Luego de usar el Objeto
     * retornado se debero necesariamente invocar el Motodo closeSQL para evitar
     * congestionar los recursos de la Base de Datos.
     *
     * @param <b>pQuery</b> Query ha ejecutar.
     * @return <b>String</b> Coleccion de datos - resultado del Query.
     */
    public static ResultSet startSQLSelect(String pSQLSelect) throws SQLException {
        // Disparamos una excepcion si el query que llega como parometro es nulo.
        // Esto es para evitar tener problemas con el Statement.
        if (pSQLSelect == null || pSQLSelect.trim().length() == 0)
            throw new SQLException("Expresion del Query no Definido", "EckerdError", 9000);
        Statement stmt = ((Connection) BaseConexion.getConexion()).createStatement();
        // Retornamos el ResultSet resultado de ejecutar el Query.
        return stmt.executeQuery(pSQLSelect);
    }

    /**
     * Cierra el Statement y el ResultSet usados para la ejecucion de un Query.
     *
     * @param <b>pResultSet</b> Coleccion de datos a cerrar.  Por medio de este
     *                          objeto obtenemos el Statement usado el cual también
     *                          es cerrado.
     */
    public static void closeSQL(ResultSet pResultSet) throws SQLException {
        Statement stmt = pResultSet.getStatement();
        pResultSet.close();
        stmt.close();
    }

    public static void executeSQLUpdate(String pSQLUpdate) throws SQLException {
        executeSQLUpdate(pSQLUpdate, true);
    }

    public static void executeSQLUpdate(Connection connection,String pSQLUpdate) throws SQLException {
        // Disparamos una excepcion si el query que llega como parometro es nulo.
        // Esto es para evitar tener problemas con el Statement.
        if (pSQLUpdate == null || pSQLUpdate.trim().length() == 0)
            throw new SQLException("Expresion del Update no Definido", "EckerdError", 9001);
        // Controla el COMMIT automático
        connection.setAutoCommit(true);
        Statement stmt = connection.createStatement();
        //TODO: Quitar este fragmento de codigo
        //System.out.println("QUERY 2; " + pSQLUpdate);
        int registros = stmt.executeUpdate(pSQLUpdate);
        connection.setAutoCommit(false);
        stmt.close();
    }


    /**
     * Ejecuta un SQL encardado de realizar Update a la Base de Datos.
     *
     * @param <b>pSQLUpdate</b> Contiene la sentencia Update a ser ejecutada.
     */
    public static void executeSQLUpdate(String pSQLUpdate, boolean pWithCommit) throws SQLException {
        // Disparamos una excepcion si el query que llega como parometro es nulo.
        // Esto es para evitar tener problemas con el Statement.
        if (pSQLUpdate == null || pSQLUpdate.trim().length() == 0)
            throw new SQLException("Expresion del Update no Definido", "EckerdError", 9001);
        // Controla el COMMIT automático
        ((Connection) BaseConexion.getConexion()).setAutoCommit(pWithCommit);
        Statement stmt = ((Connection) BaseConexion.getConexion()).createStatement();

        int registros = stmt.executeUpdate(pSQLUpdate);
        ((Connection) BaseConexion.getConexion()).setAutoCommit(false);
        //
        stmt.close();
    }
    
    /**
     * Ejecuta un determinado Stored Procedure almacenado en la Base de Datos.
     * Motodo usado en los siguientes casos :
     * 1. Ejecutar Stored Procedures con o sin parometros y estos retornan un objeto
     * del tipo OracleTypes.CURSOR
     * 2. Ejecutar Stored Procedures con o sin parometros que no retornan nada, solo
     * son ejecutados en el Servidor de Base de Datos
     * Se asume que cuando el parometro pTableModel es nulo se trata de un procedimiento
     * que no retornaro nada.
     *
     * @param <b>pTableModel</b>      Table Model usado con el JTable como modelo.
     * @param <b>pStoredProcedure</b> Nombre del Stored Procedure ha ser ejecutado
     * @param <b>pParameters</b>      Arreglo de Objetos (parometros) usados en el Stored
     *                                Procedure.
     * @param <b>pWithCheck</b>       Variable boolean que indica si en el JTable existiro una
     *                                columna de Seleccion.
     */
    public static void executeSQLStoredProcedure(AtuxTableModel pTableModel,
                                                 String pStoredProcedure,
                                                 List pParameters,
                                                 boolean pWithCheck) throws SQLException {
        // Disparamos una excepcion si el query que llega como parometro es nulo.
        // Esto es para evitar tener problemas con el Statement.
        if (pStoredProcedure == null || pStoredProcedure.trim().length() == 0)
            throw new SQLException("Expresion del Stored Procedure no Definido", "EckerdError", 9002);
        int numeroParametro = 2;
        CallableStatement stmt;
        if (pTableModel != null) {
            stmt = ((Connection) BaseConexion.getConexion()).prepareCall("{ call ? := " + pStoredProcedure + " }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
        } else {
            numeroParametro = 1;
            stmt = ((Connection) BaseConexion.getConexion()).prepareCall("{ call " + pStoredProcedure + " }");
        }
        // Setear los parometros segon el tipo de Objecto almacenado en el ArrayList
        // que viene como parometro.
        for (int i = 0; i < pParameters.size(); i++) {
            if (pParameters.get(i) instanceof String) stmt.setString(numeroParametro, (String) pParameters.get(i));
            if (pParameters.get(i) instanceof Integer)
                stmt.setInt(numeroParametro, Integer.parseInt(((Integer) pParameters.get(i)).toString()));
            if (pParameters.get(i) instanceof Double)
                stmt.setDouble(numeroParametro, Double.parseDouble(((Double) pParameters.get(i)).toString()));
            numeroParametro += 1;
        }
        stmt.execute();
        if (pTableModel != null) {
            // Carga la Data en el TableModel para ser usado como modelo para el JTable.
            // El OracleTypes.CURSOR obtenido es "depurado" para obtener cada una de las
            // columnas ... cada registro viene como un String en el que los campos eston
            // concatenados por el character "Ã".
            pTableModel.clearTable();
            ResultSet results = (ResultSet) stmt.getObject(1);
            ArrayList myArray = null;
            String myRow = "";
            StringTokenizer st = null;
            pTableModel.clearTable();
            while (results.next()) {
                myRow = results.getString(1);
                myArray = new ArrayList();
                st = new StringTokenizer(myRow, "Ã");
                if (pWithCheck) myArray.add(new Boolean(false));
                while (st.hasMoreTokens()) {
                    myArray.add(st.nextToken());
                }
                pTableModel.insertRow(myArray);
            }
            results.close();
        }
        stmt.close();
    }

    /**
     * Ejecuta un determinado Stored Procedure almacenado en la Base de Datos.
     * Motodo usado en los siguientes casos :
     * 1. Ejecutar Stored Procedures con o sin parometros y estos retornan un objeto
     * del tipo OracleTypes.CHAR
     *
     * @param <b>pStoredProcedure</b> Nombre del Stored Procedure ha ser ejecutado
     * @param <b>pParameters</b>      Arreglo de Objetos (parometros) usados en el Stored
     *                                Procedure.
     * @return <b>String</b> Objeto String devuelto por la ejecucion del Stored Procedure.
     */
    public static String executeSQLStoredProcedureStr(String pStoredProcedure,
                                                      ArrayList pParameters) throws SQLException {
        // Disparamos una excepcion si el query que llega como parometro es nulo.
        // Esto es para evitar tener problemas con el Statement.
        if (pStoredProcedure == null || pStoredProcedure.trim().length() == 0)
            throw new SQLException("Expresion del Stored Procedure no Definido", "EckerdError", 9002);
        String valorRetorno = null;
        int numeroParametro = 2;
        CallableStatement stmt = ((Connection) BaseConexion.getConexion()).prepareCall("{ call ? := " + pStoredProcedure + " }");
        stmt.registerOutParameter(1, OracleTypes.CHAR);
        // Setear los parometros segon el tipo de Objecto almacenado en el ArrayList
        // que viene como parometro.
        for (int i = 0; i < pParameters.size(); i++) {
            if (pParameters.get(i) instanceof String) stmt.setString(numeroParametro, (String) pParameters.get(i));
            if (pParameters.get(i) instanceof Integer)
                stmt.setInt(numeroParametro, Integer.parseInt(((Integer) pParameters.get(i)).toString()));
            if (pParameters.get(i) instanceof Double)
                stmt.setDouble(numeroParametro, Double.parseDouble(((Double) pParameters.get(i)).toString()));
            numeroParametro += 1;
        }
        stmt.execute();
        valorRetorno = (String) stmt.getObject(1);
        stmt.close();
        return valorRetorno;
    }

    /**
     * Ejecuta un determinado Stored Procedure almacenado en la Base de Datos.
     * Motodo usado en los siguientes casos :
     * 1. Ejecutar Stored Procedures con o sin parometros y estos retornan un objeto
     * del tipo OracleTypes.INTEGER
     *
     * @param <b>pStoredProcedure</b> Nombre del Stored Procedure ha ser ejecutado
     * @param <b>pParameters</b>      Arreglo de Objetos (parometros) usados en el Stored
     *                                Procedure.
     * @return <b>int</b> integer devuelto por la ejecucion del Stored Procedure.
     */
    public static int executeSQLStoredProcedureInt(String pStoredProcedure,
                                                   ArrayList pParameters) throws SQLException {
        // Disparamos una excepcion si el query que llega como parometro es nulo.
        // Esto es para evitar tener problemas con el Statement.
        if (pStoredProcedure == null || pStoredProcedure.trim().length() == 0)
            throw new SQLException("Expresion del Stored Procedure no Definido", "EckerdError", 9002);
        int valorRetorno = 0;
        int numeroParametro = 2;
        CallableStatement stmt = ((Connection) BaseConexion.getConexion()).prepareCall("{ call ? := " + pStoredProcedure + " }");
        stmt.registerOutParameter(1, OracleTypes.INTEGER);
        // Setear los parometros segon el tipo de Objecto almacenado en el ArrayList
        // que viene como parometro.
        for (int i = 0; i < pParameters.size(); i++) {
            if (pParameters.get(i) instanceof String) stmt.setString(numeroParametro, (String) pParameters.get(i));
            if (pParameters.get(i) instanceof Integer)
                stmt.setInt(numeroParametro, Integer.parseInt(((Integer) pParameters.get(i)).toString()));
            if (pParameters.get(i) instanceof Double)
                stmt.setDouble(numeroParametro, Double.parseDouble(((Double) pParameters.get(i)).toString()));
            numeroParametro += 1;
        }
        stmt.execute();
        Object obj = stmt.getObject(1);
        if (obj instanceof BigDecimal) valorRetorno = ((BigDecimal) stmt.getObject(1)).intValue();
        else if (obj instanceof Integer) valorRetorno = ((Integer) stmt.getObject(1)).intValue();
        stmt.close();
        return valorRetorno;
    }

    /**
     * Ejecuta un determinado Stored Procedure almacenado en la Base de Datos.
     * Motodo usado en los siguientes casos :
     * 1. Ejecutar Stored Procedures con o sin parometros y estos retornan un objeto
     * del tipo OracleTypes.DOUBLE
     *
     * @param <b>pStoredProcedure</b> Nombre del Stored Procedure ha ser ejecutado
     * @param <b>pParameters</b>      Arreglo de Objetos (parometros) usados en el Stored
     *                                Procedure.
     * @return <b>double</b> double devuelto por la ejecucion del Stored Procedure.
     */
    public static double executeSQLStoredProcedureDouble(String pStoredProcedure,
                                                         ArrayList pParameters) throws SQLException {
        // Disparamos una excepcion si el query que llega como parometro es nulo.
        // Esto es para evitar tener problemas con el Statement.
        if (pStoredProcedure == null || pStoredProcedure.trim().length() == 0)
            throw new SQLException("Expresion del Stored Procedure no Definido", "EckerdError", 9002);
        double valorRetorno = 0.00;
        int numeroParametro = 2;
        CallableStatement stmt = ((Connection) BaseConexion.getConexion()).prepareCall("{ call ? := " + pStoredProcedure + " }");
        stmt.registerOutParameter(1, OracleTypes.DOUBLE);
        // Setear los parometros segon el tipo de Objecto almacenado en el ArrayList
        // que viene como parometro.
        for (int i = 0; i < pParameters.size(); i++) {
            if (pParameters.get(i) instanceof String) stmt.setString(numeroParametro, (String) pParameters.get(i));
            if (pParameters.get(i) instanceof Integer)
                stmt.setInt(numeroParametro, Integer.parseInt(((Integer) pParameters.get(i)).toString()));
            if (pParameters.get(i) instanceof Double)
                stmt.setDouble(numeroParametro, Double.parseDouble(((Double) pParameters.get(i)).toString()));
            numeroParametro += 1;
        }
        stmt.execute();
        Object obj = stmt.getObject(1);
        if (obj instanceof BigDecimal) valorRetorno = ((BigDecimal) stmt.getObject(1)).doubleValue();
        else if (obj instanceof Double) valorRetorno = ((Double) stmt.getObject(1)).doubleValue();
        stmt.close();
        return valorRetorno;
    }

    public static void executeSQLStoredProcedureArrayList(ArrayList pArrayList,
                                                          String pStoredProcedure,
                                                          ArrayList pParameters) throws SQLException {
        if (pStoredProcedure == null || pStoredProcedure.trim().length() == 0)
            throw new SQLException("Expresion del Stored Procedure no Definido", "EckerdError", 9002);
        int numeroParametro = 2;
        CallableStatement stmt;
        stmt = ((Connection) BaseConexion.getConexion()).prepareCall("{ call ? := " + pStoredProcedure + " }");
        stmt.registerOutParameter(1, OracleTypes.CURSOR);
        for (int i = 0; i < pParameters.size(); i++) {
            if (pParameters.get(i) instanceof String) stmt.setString(numeroParametro, (String) pParameters.get(i));
            else if (pParameters.get(i) instanceof Integer) {
                stmt.setInt(numeroParametro, Integer.parseInt(((Integer) pParameters.get(i)).toString()));
            } else if (pParameters.get(i) instanceof Double) {
                stmt.setDouble(numeroParametro, Double.parseDouble(((Double) pParameters.get(i)).toString()));
            } else
                System.out.println("error " + pParameters.get(i).getClass());

            numeroParametro += 1;
        }
        stmt.execute();
        ResultSet results = (ResultSet) stmt.getObject(1);
        ArrayList myArray = null;
        String myRow = "";
        StringTokenizer st = null;
        while (results.next()) {
            myRow = results.getString(1);
            myArray = new ArrayList();
            st = new StringTokenizer(myRow, "Ã");
            while (st.hasMoreTokens())
                myArray.add(st.nextToken());
            pArrayList.add(myArray);
        }
        results.close();
        stmt.close();
    }

    public static String getValueAt(String tableName, String fieldCode,
                                    String whereCondition) throws SQLException {
        String valor = "";
        String query = "SELECT " + fieldCode + " FROM " + tableName + " WHERE " + whereCondition + "";

        Statement stmt = ((Connection) BaseConexion.getConexion()).createStatement();
        ResultSet results = stmt.executeQuery(query);
        if (results.next()) {
            valor = results.getString(1);
        }
        if (results.next()) throw new SQLException("Existe mos de 1 registro", "EckerdError", 9000);
        results.close();
        stmt.close();
        if (valor == null) valor = "";
        return valor;
    }

    public static void getValueAtInArrayList(String pTableName,
                                             String pFieldCode,
                                             String pWhereCondition,
                                             ArrayList pData) throws SQLException {
        String myRow = getValueAt(pTableName, pFieldCode, pWhereCondition);
        StringTokenizer st = new StringTokenizer(myRow, "Ã");
        while (st.hasMoreTokens())
            pData.add(st.nextToken());
    }

    public static void executeSQLStoredProcedureArrayList(ArrayList pArrayList, String pStoredProcedure,
                                                          ArrayList pParameters, boolean pWithCheck) throws SQLException {
        if (pStoredProcedure == null || pStoredProcedure.trim().length() == 0)
            throw new SQLException("Expresion del Stored Procedure no Definido", "EckerdError", 9002);
        int numeroParametro = 2;
        CallableStatement stmt;
        stmt = BaseConexion.getConexion().prepareCall("{ call ? := " + pStoredProcedure + " }");
        stmt.registerOutParameter(1, OracleTypes.CURSOR);
        for (int i = 0; i < pParameters.size(); i++) {
            if (pParameters.get(i) instanceof String)
                stmt.setString(numeroParametro, (String) pParameters.get(i));
            else if (pParameters.get(i) instanceof Integer) {
                stmt.setInt(numeroParametro, Integer.parseInt(((Integer) pParameters.get(i)).toString()));
            } else if (pParameters.get(i) instanceof Double) {
                stmt.setDouble(numeroParametro, Double.parseDouble(((Double) pParameters.get(i)).toString()));
            } else
                System.out.println("error " + pParameters.get(i).getClass());

            numeroParametro += 1;
        }
        stmt.execute();
        ResultSet results = (ResultSet) stmt.getObject(1);
        ArrayList myArray = null;
        String myRow = "";
        StringTokenizer st = null;
        while (results.next()) {
            myRow = results.getString(1);
            myArray = new ArrayList();
            st = new StringTokenizer(myRow, "Ã");
            if (pWithCheck) {
                myArray.add(new Boolean(false));
            }
            while (st.hasMoreTokens()) {
                myArray.add(st.nextToken());
            }
            pArrayList.add(myArray);
        }
        results.close();
        stmt.close();
    }

    public static void executeSQLArrayList(ArrayList pArrayList, String pSQL) throws SQLException {
        Statement statement = BaseConexion.getConexion().createStatement();
        ResultSet results = statement.executeQuery(pSQL);

        ArrayList myArray = null;
        String myRow = "";
        StringTokenizer st = null;
        while (results.next()) {
            myRow = results.getString(1);
            myArray = new ArrayList();
            st = new StringTokenizer(myRow, "Ã");
            while (st.hasMoreTokens()) {
                myArray.add(st.nextToken());
            }
            pArrayList.add(myArray);
        }
        results.close();
        statement.close();
    }

    public AtuxDBUtility() {
    }

}