package com.aw.builder.templates.form;

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
public class SimpleFormClass extends Template {

    @Override
    public void process(VelocityEngine ve, BNTable table, List columns) throws Exception {

        org.apache.velocity.Template t = ve.getTemplate(Context.pathTemplate+"EngineFormClass.vm");
        VelocityContext context = generateContextForView(table,columns);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        String fileName = Tools.obtenerFrmName(table.getTableName());
        generateFile(fileName,"form", table, writer);

    }

    private VelocityContext generateContextForView(BNTable table, List columns) throws Exception {
        // rolCajaFonTr
        String nameBase = StringUtils.capitalize(Tools.generateNameBase(table.getTableName()));
        VelocityContext context = new VelocityContext();

        String packageName = getPackageName("form",table.getTableName());
        context.put("packageForm", packageName);
        context.put("entityFrm", Tools.obtenerFrmName(table.getTableName()));
        context.put("columns", columns);

        return context;
    }
    
}
