package atux.managerbd;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Copyright (c) <br>
 * 
 * @version 1.0<br>
 */
public class BaseConexion {               

    /**
     * Almacena la Conexion actualmente vigente
     * */
    public static Connection conn = null;    
    private static ResultSet rs = null;
    private static Statement st = null;
    private static PreparedStatement ps = null;
    
    public static int TODO           = 1;
    public static int SOLO_STATEMENT = 2;
    public static int SOLO_RESULTSET = 3;
    public static int SOLO_PREPAREDSTATEMENT = 4;
    public static int CONNECION      = 5;

    public static String connect_string_thin = "jdbc:oracle:thin:ecventa/venta@192.168.1.111:1521:ABDPV001";
//  public static String connect_string_thin = "jdbc:oracle:thin:atux/atuxpro@192.168.1.10:1521:ABDPV002";
//  public static String connect_string_thin = "jdbc:oracle:thin:ecventa/venta@7.86.89.177:1521:ABDPV001";
//  public static String connect_string_thin = "jdbc:oracle:thin:atux/atuxpro@192.168.1.91:1521:XE";
//  public static String connect_string_thin = "jdbc:oracle:thin:ecventa/venta@25.153.221.86:1521:ABDPV000";
      
    /**
     * String de Conexion modo OCI
     */
    static final String connect_string_oci = "jdbc:oracle:oci:ecventa/venta@edbdes00";

    /**
     * Retorna la actual Conexion de Base de Datos.
     *
     * @return <b>Connection</b> Objeto de Conexion retornado.
     */
    public static Connection getConexion() throws SQLException {
        try {
            if (conn == null || conn.isClosed()) {                              
                DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
                
                conn = DriverManager.getConnection(connect_string_thin);
                    
                conn.setAutoCommit(false);

                return conn;
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return conn;
    }

    /* ************************************************************************ */
    /**
     * Descripcion:Metodos que proveen datos de la Conexion     
     * Fecha modificacion:
     * Observacion:
     */
    public static void setConnectionNull() {
        conn = null;
    }
    
    public static boolean connectionIsNull() {
        return conn == null;
    }    

    public static boolean getConnectionState() throws SQLException {
        return !conn.isClosed();
    }

    /* ************************************************************************ */

    /**
     * Cierra la Conexion de Base de Datos.
     */
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            }
            catch (Exception e) {
            }
        }
        return;
    }

    /**
     * Constructor
     */
    public BaseConexion() {
    }

    public void setTransaction(int tipoTransaccion) throws SQLException{
        conn.commit();
        conn.setTransactionIsolation(tipoTransaccion);
    }

    public void setDefaultTransaction() throws SQLException {
        conn.commit();
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }
    
    public static ResultSet getResultSet(String sql) throws SQLException {
        if(conn == null)
        {
            getConexion();
        }
        try
        {           
           rs = getStatement().executeQuery(sql);            
        }catch(SQLException ex){
               ex.printStackTrace();
        } 

        return rs;
    }

    public static Statement getStatement() throws SQLException {
        if(conn == null)
        {
            getConexion();
        }
        try
        {
//           st = (Statement) conn.createStatement();
            st =   conn.createStatement(
                               ResultSet.TYPE_SCROLL_INSENSITIVE,
                               ResultSet.CONCUR_READ_ONLY);
                
        }catch(SQLException ex){} 
        
        return st;
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        if(conn == null)
        {
             getConexion();
        }
        
        if(ps == null)
        {
            try
            {
               ps = (PreparedStatement) conn.prepareStatement(sql);           
            }catch(SQLException ex){}  
        }else if(ps != null)
        {
             try
            {
                if(ps.isClosed())
                {
                   ps = (PreparedStatement) conn.prepareStatement(sql); 
                }                       
            }catch(SQLException ex){}
        }
        return ps;
    }

    /**
     * 
     * @param tipoCierre - 
     * TODO,SOLO_STATEMENT,SOLO_RESULTSET,SOLO_PREPAREDSTATEMENT,CONNECION
     */
    public static void cerrarEnlacesConexion(int tipoCierre)
    {
        switch(tipoCierre)
        {
            case 1:
                cerrarConexion();
                cerrarResultSet();
                cerrarStatement();
                cerrarPreparedStatement();
              break;
           case 2:
                cerrarStatement();
              break; 
           case 3:
                cerrarResultSet();
              break;
           case 4:
                cerrarPreparedStatement();
              break;    
           case 5:
                cerrarConexion();
              break;     
            default:   
        }
    }
    
    private static void cerrarResultSet()
    {
        if(rs != null)
        {
            try{
                if(!rs.isClosed())
                {
                    rs.close();
                }  
            }catch(SQLException es){}             
        }
    }
    
    private static void cerrarStatement()
    {
        if(st != null)
        {
            try{
                if(!st.isClosed())
                {
                    st.close();
                }  
            }catch(SQLException es){}             
        }
    }
    
    private static void cerrarPreparedStatement()
    {
        if(ps != null)
        {
            try{
                if(!ps.isClosed())
                {
                    ps.close();
                }  
            }catch(SQLException es){}             
        }
    }
    
    private static void cerrarConexion()
    {
        if(conn != null)
        {            
            try{
                if(!conn.isClosed())
                {
                    conn.close();
                }  
            }catch(SQLException es){}             
        }
    }

}

