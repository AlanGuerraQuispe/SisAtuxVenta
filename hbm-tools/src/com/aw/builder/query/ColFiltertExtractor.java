package com.aw.builder.query;

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
public class ColFiltertExtractor {
    String sqlLineOrig;
    String sqlLineExtracted;
    JavaField filter;

    public ColFiltertExtractor(String sqlLine) {
        sqlLineOrig = sqlLine;
    }

    public boolean extract() {
        if (sqlLineOrig.indexOf("/*?")<0) return false;
        int start  = sqlLineOrig.indexOf("/*?");
        int end    = sqlLineOrig.indexOf("*/");
        if (end<0) throw new IllegalArgumentException("Linea no contiene '\\*' :"+sqlLineOrig);
        String filterName = sqlLineOrig.substring(start + 3, end);
        sqlLineExtracted = sqlLineOrig.substring(0,start)+sqlLineOrig.substring(end+"*/".length());
        Class type = Object.class;
        if ( replace("'ABC'") )
            type  = SVGenerarQuery.TYPE_STR;
        else if ( replace("123") )
            type  = SVGenerarQuery.TYPE_NUMBER;
        else if ( replace("sysdate") )
            type  = SVGenerarQuery.TYPE_DATE;
        else
            type  = Object.class;
        filter = new JavaField(filterName, type);
        return true;
    }

    private boolean replace(String param) {
        int before = sqlLineExtracted.length();
        sqlLineExtracted = sqlLineExtracted.replace(param,"?");
        int after = sqlLineExtracted.length();
        return before!=after;
    }

}
