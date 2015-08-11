package com.aw.builder.templates.web;

import com.aw.builder.BNTable;
import com.aw.builder.Context;
import com.aw.builder.RPRepository;
import com.aw.builder.Tools;
import com.aw.builder.templates.Template;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: Kaiser
 * Date: 02/04/2009
 */
public class SimpleController extends Template {

    @Override
    public void process(VelocityEngine ve, BNTable table,List columns) throws Exception {
        org.apache.velocity.Template t = ve.getTemplate(Context.pathTemplate + "EngineCtr.vm");
        VelocityContext context = generateContext(table);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        String fileName = Tools.obtenerControllerName(table.getTableName());
        generateFile(fileName, "web", table, writer);
    }


    private VelocityContext generateContext(BNTable table) throws Exception {
        String nameBase = StringUtils.capitalize(Tools.generateNameBase(table.getTableName()));
        VelocityContext context = new VelocityContext();
        String packageName = getPackageName("web", table.getTableName());
        context.put("packageWeb", packageName);
        context.put("templateRoot", Tools.generateDirectoryNameBase(table.getTableName()));
        context.put("controllerName", Tools.obtenerControllerName(table.getTableName()));
        context.put("filtroName", "BN" + nameBase + "Flt");
        context.put("serviceName", "SV" + nameBase);
        context.put("serviceName_var", "sv" + nameBase);
        context.put("entityName", nameBase);
        if (table.isUserBN()) {
            context.put("entityImpl", Tools.obtenerEntityBeanName(table.getTableName()));
            context.put("picksInfo", generatePickInfo(table));
        } else {
            context.put("entityImpl", Tools.generateNameImpl(table.getTableName()));
        }

        if (table.isMasterTable()) {
            context.put("gridsInfo", generateGridsInfo(table));
        }else {
            context.put("gridsInfo", new ArrayList());
        }


        return context;
    }

    private List generatePickInfo(BNTable table) throws Exception {
        List columns = generateAllColumns(table,false); // FK_APLICACION
        List fks = new ArrayList();
        for (Iterator iterator = columns.iterator(); iterator.hasNext();) {
            String column = (String) iterator.next();
            if (column.startsWith("FK")) {
                fks.add(column.replaceAll("FK", "PK"));
            }
        }
        return RPRepository.obtenerTables(fks);
    }

    private List generateGridsInfo(BNTable table) throws Exception {
        List columns = generateAllColumns(table,true); // FK_APLICACION
        List fks = new ArrayList();
        for (Iterator iterator = columns.iterator(); iterator.hasNext();) {
            String column = (String) iterator.next();
            if (column.startsWith("PK")) {
                fks.add(column.replaceAll("PK", "FK"));
            }
        }
        return RPRepository.getTableDepends(fks);
    }

    private List generateAllColumns(BNTable table,boolean onlyPks) throws Exception {

        Connection conn = RPRepository.getConection();

        Statement stmt = conn.createStatement();


        String sql = "";

        if(!onlyPks){
            sql=RPRepository.generateSqlForColumns(table.getTableName());
        }else{
            sql=RPRepository.generateSqlForColumnsPks(table.getTableName());
        }


        ResultSet rset =
                stmt.executeQuery(sql);

        List columnas = new ArrayList();
        while (rset.next()) {
            columnas.add(rset.getString(1));
        }
        stmt.close();
        return columnas;
    }

}
