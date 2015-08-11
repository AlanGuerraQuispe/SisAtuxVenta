package com.aw.builder;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * User: Kaiser
 * Date: 31/03/2009
 */
public class RPRepository {


    public static Connection getConection() throws Exception {

        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        return DriverManager.getConnection
                ("jdbc:oracle:thin:@192.168.1.37:1521:ORCL", "SUMIDESA", "SUMIDESA");
    }

    public static String generateSqlForColumns(String tableName) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT COLUMN_NAME,DATA_LENGTH,DATA_TYPE,DATA_PRECISION,NULLABLE ");
        sql.append(" FROM   dba_tab_columns ");
        sql.append(" WHERE  OWNER     = '" + Context.schema + "' ");
        sql.append("    AND TABLE_NAME='" + tableName + "' ");
        sql.append("    AND COLUMN_NAME NOT LIKE 'PK%' ");
        sql.append("    AND COLUMN_NAME NOT LIKE 'DE%' ");
        sql.append("    AND COLUMN_NAME NOT IN ('USUA_CREA', ");
        sql.append("                            'FECH_CREA', ");
        sql.append("                            'NUIP_CREA', ");
        sql.append("                            'USSO_CREA', ");
        sql.append("                            'NOPC_CREA', ");
        sql.append("                            'USUA_MODI', ");
        sql.append("                            'FECH_MODI', ");
        sql.append("                            'NUIP_MODI', ");
        sql.append("                            'USSO_MODI', ");
        sql.append("                            'NOPC_MODI')");

        System.out.println(sql.toString());
        return sql.toString();
    }

    public static String generateSqlForColumnsPks(String tableName) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT COLUMN_NAME,DATA_LENGTH,DATA_TYPE,DATA_PRECISION,NULLABLE ");
        sql.append(" FROM   dba_tab_columns ");
        sql.append(" WHERE  OWNER     = '" + Context.schema + "' ");
        sql.append("    AND TABLE_NAME='" + tableName + "' ");
        sql.append("    AND COLUMN_NAME LIKE 'PK%' ");

        System.out.println(sql.toString());
        return sql.toString();
    }

    public static List obtenerTables(List pks) throws Exception {

        StringBuffer sqlIn = new StringBuffer("(");
        boolean x = false;
        for (Iterator iterator = pks.iterator(); iterator.hasNext();) {
            String s = (String) iterator.next();
            if (x) {
                sqlIn.append(",");
            }
            sqlIn.append("'").append(s).append("'");
            x = true;
        }
        sqlIn.append(")");

        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT TABLE_NAME ,COLUMN_NAME  FROM dba_tab_columns WHERE OWNER = '" + Context.schema + "' and COLUMN_NAME IN ");
        sql.append(sqlIn);

        Connection conn = getConection();

        Statement stmt = conn.createStatement();

        ResultSet rset =
                stmt.executeQuery(sql.toString());

        List tables = new ArrayList();
        while (rset.next()) {
            Map map = new HashMap();
            map.put("tableName", StringUtils.capitalize(Tools.generateNameBase(rset.getString(1))));
            map.put("varName", Tools.generateNameBase(rset.getString(1)));
            map.put("pkFieldName", Tools.obtenerFieldName(rset.getString(2)));
            map.put("fkFieldName", Tools.obtenerFieldName(rset.getString(2).replaceAll("PK", "FK")));
            tables.add(map);
        }
        stmt.close();
        return tables;

    }

    public static List getTableDepends(List pks) throws Exception {
        if (pks.size() == 0) {
            return new ArrayList();
        }

        StringBuffer sqlIn = new StringBuffer("(");
        boolean x = false;
        for (Iterator iterator = pks.iterator(); iterator.hasNext();) {
            String s = (String) iterator.next();
            if (x) {
                sqlIn.append(",");
            }
            sqlIn.append("'").append(s).append("'");
            x = true;
        }
        sqlIn.append(")");

        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT TABLE_NAME ,COLUMN_NAME  FROM dba_tab_columns WHERE OWNER = '" + Context.schema + "' and COLUMN_NAME IN ");
        sql.append(sqlIn);


        Connection conn = getConection();

        Statement stmt = conn.createStatement();

        ResultSet rset =
                stmt.executeQuery(sql.toString());

        List tables = new ArrayList();
        while (rset.next()) {
            Map map = new HashMap();
            map.put("name", "gdp" + StringUtils.capitalize(Tools.generateNameBase(rset.getString(1))));
            map.put("ipNameClass", "IP" + StringUtils.capitalize(Tools.generateNameBase(rset.getString(1))));
            map.put("ipName", "ip" + StringUtils.capitalize(Tools.generateNameBase(rset.getString(1))));
            tables.add(map);
        }
        stmt.close();
        return tables;

    }

    public static List getMasterTable() throws Exception {

        String sql = (" SELECT  TABLE_NAME FROM ALL_TABLES  WHERE OWNER = '" + Context.schema + "' ");

        Connection conn = getConection();

        Statement stmt = conn.createStatement();

        ResultSet rset =
                stmt.executeQuery(sql);

        List tables = new ArrayList();
        while (rset.next()) {
            BNTable table = new BNTable();
            table.setTableName(rset.getString(1));
            table.setMasterTable(rset.getString(1).endsWith("TM"));
            table.setUseDetail(rset.getString(1).endsWith("TC"));
            table.setUserBN(hasFK(rset.getString(1)));
            tables.add(table);
        }
        stmt.close();
        return tables;
    }

    public static boolean hasFK(String tableName) throws Exception {

        String sql = (" SELECT count(*) FROM dba_tab_columns WHERE OWNER = '" + Context.schema + "' and TABLE_NAME='" + tableName + "' AND COLUMN_NAME LIKE 'FK%'  ");

        Connection conn = getConection();

        Statement stmt = conn.createStatement();

        ResultSet rset =
                stmt.executeQuery(sql);

        BigDecimal numero = new BigDecimal(0);
        while (rset.next()) {
            numero = rset.getBigDecimal(1);
        }
        stmt.close();
        return numero.intValue() != 0;
    }

    public static String getParentTable(String tableName) throws Exception {

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT TABLE_NAME ");
        sql.append("FROM   dba_tab_columns ");
        sql.append("WHERE  owner = 'SEGURIDADQA2' ");
        sql.append("   AND table_name LIKE '%TC' ");
        sql.append("   AND column_name IN ");
        sql.append("                       ( SELECT REPLACE(COLUMN_NAME,'FK_','PK_') ");
        sql.append("                       FROM    dba_tab_columns ");
        sql.append("                       WHERE   owner      = 'SEGURIDADQA2' ");
        sql.append("                           AND table_name = 'VENTA_TD' ");
        sql.append("                           AND column_name LIKE 'FK%' ");
        sql.append("                       )");

        Connection conn = getConection();

        Statement stmt = conn.createStatement();

        ResultSet rset =
                stmt.executeQuery(sql.toString());

        String parentTable = "";
        while (rset.next()) {
            parentTable = rset.getString(1);
        }
        stmt.close();

        if (parentTable.equals("")) {
            return "Object";
        }

        return parentTable;
    }

    public static List generateColumns(BNTable table) throws Exception {

        Connection conn = RPRepository.getConection();

        Statement stmt = conn.createStatement();

        ResultSet rset =
                stmt.executeQuery(RPRepository.generateSqlForColumns(table.getTableName()));

        List columnas = new ArrayList();
        while (rset.next()) {
            Map map = new HashMap();
            map.put("label", Tools.getLabel(rset.getString(1)));
            map.put("name", Tools.obtenerFieldName(rset.getString(1)));
            map.put("width", rset.getBigDecimal(2).intValue() * 3);

            map.put("lblName", "lbl" + StringUtils.capitalize(Tools.obtenerFieldName(rset.getString(1))));
            map.put("lblValue", Tools.obtenerFieldName(rset.getString(1)));
            map.put("txtName", "txt" + StringUtils.capitalize(Tools.obtenerFieldName(rset.getString(1))));

            String type = "";
            StringBuffer validation = new StringBuffer();

            if (rset.getString(5).equals("Y")) {
                validation.append("R");
            }

            if (rset.getString(3).startsWith("VARCHAR2")) {
                type = "String";
                validation.append("Y").append(rset.getBigDecimal(2));
            } else if (rset.getString(3).equals("NUMBER")) {
                if (rset.getBigDecimal(4) != null && rset.getBigDecimal(4).intValue() != 0) {
                    type = "Long";
                } else {
                    type = "BigDecimal";
                }

            } else if (rset.getString(3).equals("DATE")) {
                    type = "Date";
            }else if (rset.getString(3).startsWith("CHAR")) {
                    type = "String";
            }
            map.put("type", type);
            map.put("validation", validation.toString());

            map.put("methodName", StringUtils.capitalize(Tools.obtenerFieldName(rset.getString(1))));

            columnas.add(map);
        }
        stmt.close();
        return columnas;
    }

}
