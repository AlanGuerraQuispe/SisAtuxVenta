package com.aw.builder.templates;

import com.aw.builder.BNTable;
import com.aw.builder.Context;
import com.aw.builder.Tools;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.List;

/**
 * User: Kaiser
 * Date: 02/04/2009
 */
public class Bean extends Template{

    @Override
    public void process(VelocityEngine ve, BNTable table, List columns) throws Exception {
        org.apache.velocity.Template t = ve.getTemplate(Context.pathTemplate+"BNEngine.vm");
        VelocityContext context = generateContextForEntityBean(table);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        String fileName = Tools.obtenerEntityBeanName(table.getTableName());
        generateFile(fileName,"bean", table, writer);

    }


    private VelocityContext generateContextForEntityBean(BNTable table) throws Exception {
        // rolCajaFonTr
        String nameBase = StringUtils.capitalize(Tools.generateNameBase(table.getTableName()));
        VelocityContext context = new VelocityContext();

        String packageName = getPackageName("bean",table.getTableName());
        context.put("packageBean", packageName);
        context.put("entityBean", Tools.obtenerEntityBeanName(table.getTableName()));
        context.put("entityImpl", Tools.generateNameImpl(table.getTableName()));
        return context;
    }    
}
