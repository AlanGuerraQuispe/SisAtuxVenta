package com.aw.builder.query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2008 Eckerd Peru S.A.<br>
 * <br>
 * Funcionalidad : <br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * <br>
 * ID   PROGRAMADOR  FECHA/HORA  TIPO          OBSERVACIÓN
 * 001   JCM          28/04/2009 Creación      <br>
 * 002   JCM          06/10/2008  Modificación  <br>
 * <br>
 * <br>
 *
 * @author Julio C. Macavilca - AW<br>
 * @version 1.0<br>
 */
public class ColResultExtractor {
    public List<JavaField> extract(String sql) throws ClassNotFoundException, SQLException {
        //Class.forName("oracle.jdbc.driver.OracleDriver");
        // or you can use:
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.37:1521:ORCL", "sumidesa", "sumidesa");

        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        ResultSetMetaData metadata = rset.getMetaData();
        List<JavaField> columns = new ArrayList<JavaField>();
        for (int i = 1; i <= metadata.getColumnCount(); i++) {
            String colName  =  metadata.getColumnLabel(i);
            int    colType  =  metadata.getColumnType(i);
            Class  colTypeClass = Object.class;
            if (colType==Types.CHAR || colType==Types.VARCHAR )
                colTypeClass = SVGenerarQuery.TYPE_STR;
            else if(colType==Types.INTEGER || colType==Types.DECIMAL || colType==Types.NUMERIC)
                colTypeClass = SVGenerarQuery.TYPE_NUMBER;
            else if (colType==Types.DATE|| colType==Types.TIME || colType==Types.TIMESTAMP)
                colTypeClass = SVGenerarQuery.TYPE_DATE;
            else
                colTypeClass=Object.class;
            columns.add(new JavaField(colName, colTypeClass));
        }

//        while (rset.next())
//            System.out.println(rset.getString(1));
        rset.close();
        stmt.close();
        conn.close();
        return columns;
    }


}
