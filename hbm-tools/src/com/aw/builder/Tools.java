package com.aw.builder;

import org.apache.commons.lang.StringUtils;

import java.util.StringTokenizer;

/**
 * User: Kaiser
 * Date: 31/03/2009
 */
public class Tools {


    public static String obtenerFNControllerName(String tableName) {
        return "FN" + StringUtils.capitalize(Tools.generateNameBase(tableName)) + "Ctr";
    }
    public static String obtenerIpConfigName(String tableName) {
        return "IP" + StringUtils.capitalize(Tools.generateNameBase(tableName)) ;
    }
    public static String obtenerFrmName(String tableName) {
        return "Frm" + StringUtils.capitalize(Tools.generateNameBase(tableName)) ;
    }

    public static String obtenerPickName(String tableName) {
        return "Pick" + StringUtils.capitalize(Tools.generateNameBase(tableName)) + "Ctr";
    }

    public static String obtenerControllerName(String tableName) {
        return StringUtils.capitalize(Tools.generateNameBase(tableName)) + "Ctr";
    }

    public static String obtenerEntityBeanName(String tableName) {
        return "BN"+StringUtils.capitalize(Tools.generateNameBase(tableName));
    }

    public static String obtenerRepositoryName(String tableName) {
        return "RP" + StringUtils.capitalize(Tools.generateNameBase(tableName)) ;
    }

    public static String obtenerServiceName(String tableName) {
        return "SV" + StringUtils.capitalize(Tools.generateNameBase(tableName));
    }

    public static String obtenerServiceNameImpl(String tableName) {
        return "SV" + StringUtils.capitalize(Tools.generateNameBase(tableName))+"Impl";
    }

    // CO_ROL -> coRol
    public static String obtenerFieldName(String columnName) {
        StringTokenizer st = new StringTokenizer(columnName.toLowerCase(), "_");
        StringBuffer sb = new StringBuffer();
        boolean toUpper = false;
        while (st.hasMoreTokens()) {
            if (toUpper) {
                sb.append(StringUtils.capitalize(st.nextToken()));
            } else {
                sb.append(st.nextToken());
            }
            toUpper = true;
        }
        return sb.toString();
    }

    public static String generateDirectoryNameBase(String tableName) {
        String nombre = tableName.replaceAll("_TM", "").replaceAll("_TD", "").replaceAll("_TR", "").replaceAll("_TA", "").replaceAll("_TC", "").toLowerCase();
        StringTokenizer st = new StringTokenizer(nombre, "_");
        StringBuffer sb = new StringBuffer();
        boolean toUpper = false;
        while (st.hasMoreTokens()) {

            if (toUpper) {
                sb.append(StringUtils.capitalize(st.nextToken()));
            } else {
                sb.append(st.nextToken());
            }


            toUpper = true;
        }

        return sb.toString().toLowerCase();

    }

    public static String generateNameBase(String tableName) {
        String nombre = tableName.toLowerCase();
        StringTokenizer st = new StringTokenizer(nombre, "_");
        StringBuffer sb = new StringBuffer();
        boolean toUpper = false;
        while (st.hasMoreTokens()) {

            if (toUpper) {
                sb.append(StringUtils.capitalize(st.nextToken()));
            } else {
                sb.append(st.nextToken());
            }


            toUpper = true;
        }

        return sb.toString();

    }

    public static String generateNameImpl(String tableName) {
        String nombre = tableName.toLowerCase();
        StringTokenizer st = new StringTokenizer(nombre, "_");
        StringBuffer sb = new StringBuffer();

        while (st.hasMoreTokens()) {
            sb.append(StringUtils.capitalize(st.nextToken()));
        }
        sb.append("Impl");
        return sb.toString();

    }

    public static String generatePojoName(String tableName) {
        String nombre = tableName.toLowerCase();
        StringTokenizer st = new StringTokenizer(nombre, "_");
        StringBuffer sb = new StringBuffer();

        while (st.hasMoreTokens()) {
            sb.append(StringUtils.capitalize(st.nextToken()));
        }
        return sb.toString();

    }

    public static String getLabel(String columnName) {

        if (columnName.startsWith("CO")) {
            return "Código";
        } else if (columnName.startsWith("NO")) {
            return "Nombre";
        } else {
            return columnName;
        }

    }
}
