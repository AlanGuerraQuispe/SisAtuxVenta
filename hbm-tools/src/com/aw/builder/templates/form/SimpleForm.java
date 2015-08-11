package com.aw.builder.templates.form;

import com.aw.builder.BNTable;
import com.aw.builder.Context;
import com.aw.builder.Tools;
import com.aw.builder.templates.Template;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * User: Kaiser
 * Date: 02/04/2009
 */
public class SimpleForm extends Template {

    @Override
    public void process(VelocityEngine ve, BNTable table, List columns) throws Exception {
        org.apache.velocity.Template t = ve.getTemplate(Context.pathTemplate+"EngineForm.vm");
        VelocityContext context = generateContextForView(table,columns);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        String fileName = Tools.obtenerFrmName(table.getTableName());
        String directory = Context.packageDir+"/form/"+Tools.generateNameBase(table.getTableName()).toLowerCase();
        File dirr = new File("./generated/"+directory);
        dirr.mkdir();

        File file = new File("./generated/"+directory+"/"+fileName+".form");
        // Create file if it does not exist
        boolean success = file.createNewFile();
        FileWriter fstream = new FileWriter(file);
        BufferedWriter out = new BufferedWriter(fstream);
        out.write(writer.toString());
        out.close();
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
