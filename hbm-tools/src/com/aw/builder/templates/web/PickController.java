package com.aw.builder.templates.web;

import com.aw.builder.BNTable;
import com.aw.builder.Context;
import com.aw.builder.Tools;
import com.aw.builder.templates.Template;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.List;

/**
 * User: Kaiser
 * Date: 02/04/2009
 */
public class PickController extends Template{

    @Override
    public void process(VelocityEngine ve, BNTable table, List columns) throws Exception {
        org.apache.velocity.Template t = ve.getTemplate(Context.pathTemplate+"PickEngine.vm");
        VelocityContext context = generateContextForPick(table,columns);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        String fileName = Tools.obtenerPickName(table.getTableName());
        generateFile(fileName,"web" ,table, writer);
    }

    private VelocityContext generateContextForPick(BNTable table, List columns) throws Exception {
        // rolCajaFonTr
        String nameBase = StringUtils.capitalize(Tools.generateNameBase(table.getTableName()));
        VelocityContext context = new VelocityContext();
        String packageName = getPackageName("web",table.getTableName());
        context.put("packageWeb", packageName);
        context.put("pickName", Tools.obtenerPickName(table.getTableName()));
        context.put("filtroName", "BN" + nameBase + "Flt");
        context.put("serviceName", "SV" + nameBase);
        context.put("serviceName_var", "sv" + nameBase);
        context.put("viewName", nameBase);
        context.put("entityName", nameBase);
        context.put("columns", columns);

        return context;
    }


}
