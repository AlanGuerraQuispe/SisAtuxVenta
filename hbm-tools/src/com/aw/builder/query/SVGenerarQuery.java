package com.aw.builder.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Copyright (c) 2008 AW.<br>
 * <br>
 * Funcionalidad : <br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * <br>
 * ID   PROGRAMADOR  FECHA/HORA  TIPO          OBSERVACIÓN
 * 001   JCM          28/04/2009 Creación      <br>
 * 002   JCM          06/10/2008 Modificación  <br>
 * <br>
 * <br>
 *
 * @author Julio C. Macavilca - AW<br>
 * @version 1.0<br>
 */
public class SVGenerarQuery {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String sql = "    select prod.PK_PRODUCTO as \"pkProd\", prod.DE_CORTA as \"deCortaProd\",\n" +
                "           prod.FK_VALOR_UM_COMPRA as \"pkUnidMedida\",\n" +
                "           almProd.CA_STOCK_DISPONIBLE as \"caDisponible\",\n" +
                "           alm.NO_ALMACEN as \"noAlmacen\"\n" +
                "    from PRODUCTO_TM prod, ALMACEN_PRODUCTO_TR almProd, ALMACEN_TM alm\n" +
                "    where prod.PK_PRODUCTO = almProd.FK_PRODUCTO\n" +
                "      and alm.PK_ALMACEN   = almProd.FK_ALMACEN\n" +
                "      and prod.PK_PRODUCTO = 123      /*?prodPk*/\n" +
                "      and prod.DE_CORTA like 'ABC'    /*?deCortaProd*/\n" +
                "      and prod.FK_FAMILIA  = 123      /*?pkFamilia*/\n" +
                "      and prod.IN_STOCKABLE          = 'ABC'      /*?inStockable*/\n" +
                "      and prod.IN_ENCONSIGNACION     = 'ABC'      /*?inEnConsignacion*/\n" +
                "      and prod.IN_PLANO              = 'ABC'      /*?inPlano*/\n" +
                "      and prod.IN_ENACUERDO          = 'ABC'      /*?inEnAcuerdo*/\n" +
                "      and prod.IN_PELIGRO            = 'ABC'      /*?inPeligro*/\n" +
                "      and prod.IN_IQF                = 'ABC'      /*?inIQF*/\n" +
                "      and prod.IN_CERT_CALI          = 'ABC'      /*?inCertCalidad*/\n" +
                "      and prod.IN_CONF_TECN          = 'ABC'      /*?inConforTecnica*/\n" +
                "      and prod.FK_VALOR_TIPO         = 123        /*?pkTipoProd*/\n" +
                "      and prod.FK_VALOR_CLASE        = 123        /*?pkClaseProd*/\n" +
                "      and prod.FK_VALOR_CLASE        = 123        /*?pkPlanificador*/--PLANIFICADOR\n" +
                "      and prod.PK_PRODUCTO in (select PK_PRODUCTO from PRODUCTO_MARCA_TR    where  DE_MODELO like 'ABC') /*?deMarcaProd*/\n" +
                "      and prod.PK_PRODUCTO in (select PK_PRODUCTO from PRODUCTO_MARCA_TR    where  NU_SERIE like 'ABC') /*?nuSerieProd*/\n" +
                "      and prod.PK_PRODUCTO in (select PK_PRODUCTO from PRODUCTO_SINONIMO_TR where  DE_SINONIMO like 'ABC') /*?deSinonimoProd*/\n" +
                "      and prod.DE_LARGA like 'ABC'    /*?deLargaProd*/\n" +
                "      and prod.DE_LARGA = 'ABC'    /*?pkPlanta*/ --PLANTA\n" +
                "      and prod.PK_PRODUCTO in (select PK_PRODUCTO from PRODUCTO_EQUIPO_TR WHERE PK_EQUIPO = 123) /*?pkEquipo*/ ";
        new SVGenerarQuery().execute(null);
    }
    public static Class TYPE_NUMBER = Long.class;
    public static Class TYPE_DATE = Date.class;
    public static Class TYPE_STR = String.class;

    List<String> sqlLines;
    List<JavaField> sqlFieldFltr;
    List<JavaField> sqlFieldRslt;
    private void execute(String sql) throws ClassNotFoundException, SQLException {
        if (sql==null) sql = obtenerQuery();
        if (sql==null||sql.trim().length()==0) return;

        sqlFieldRslt = new ColResultExtractor().extract(sql);

        sqlLines = Arrays.asList(sql.split("\n"));

        StringBuffer queryBuf = generateQuery();

        System.out.println("\n FILTER");
        for (JavaField field : sqlFieldFltr) {
            System.out.println(field.toDeclarationStr() );
        }
        
        System.out.println("\n RESULT");
        for (JavaField field : sqlFieldRslt) {
            System.out.println(field.toDeclarationStr() );
        }

        System.out.println("\n QUERY\n"+queryBuf);
    }

    private StringBuffer generateQuery() {
        StringBuffer buf = new StringBuffer();
        buf.append("StringBuffer sql = new StringBuffer();\n");
        buf.append("WhereBuilder2 builder = new WhereBuilder2(sql); \n");
        sqlFieldFltr = new ArrayList<JavaField>();
        for (String sqlLine : sqlLines) {
            sqlLine = sqlLine.replaceAll("\\\"", "\\\\\\\""); //  "  --> \"
            ColFiltertExtractor extractor = new ColFiltertExtractor(sqlLine);
            boolean filterExtracted = extractor.extract();
            if (filterExtracted){
                buf.append("builder.filter(flt."+extractor.filter.getFilterNameGetter()+"(), \" "+extractor.sqlLineExtracted+" \\n\"); \n");
                sqlFieldFltr.add(extractor.filter);
            }else
                buf.append("sql.append(\""+sqlLine+"\\n\"); \n");
        }
        buf.append("return getSQL().findListOfBeans(builder.getSql().toString(),builder.getParams().toArray(), XXXRst.class ); \n");

        return buf;
    }

    private static String obtenerQuery() {
        QueryText dialog = new QueryText();
        dialog.pack();
        dialog.setVisible(true);
        String sql = dialog.getSQL();
        return sql;
    }

}
