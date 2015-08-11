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
public class JavaField {
    String dbName;
    String javaName;
    Class type;

    public JavaField(String name, Class type) {
        this.dbName= name;
        this.javaName= toCamelCase(name);
        this.type = type;
    }

    private String toCamelCase(String name) {
        String[] parts = name.split("_");
        String nameCamelCase = "";
        if (parts.length == 1 ){
            nameCamelCase = parts[0];
        }else if (parts.length >0 ){
            nameCamelCase = parts[0].toLowerCase();
        }
        for (int i = 1; i < parts.length; i++) {
            String part = parts[i];
            nameCamelCase+=midleName(part);
        }
        return nameCamelCase;  
    }

    private String midleName(String part) {
        part = part.toLowerCase();
        return part.substring(0,1).toUpperCase()+part.substring(1);
    }

    public String toDeclarationStr() {
        return type.getSimpleName() +" "+javaName+";";
    }

    public String getDbName() {
        return dbName;
    }

    public String getJavaName() {
        return javaName;
    }

    public Class getType() {
        return type;
    }
    public String getFilterNameGetter() {
        return "get"+javaName.substring(0,1).toUpperCase()+ javaName.substring(1);
    }
}
