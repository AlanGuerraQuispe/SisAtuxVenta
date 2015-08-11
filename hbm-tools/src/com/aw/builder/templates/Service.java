package com.aw.builder.templates;

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
public class Service extends Template {

    @Override
    public void process(VelocityEngine ve, BNTable table, List columns) throws Exception {
        org.apache.velocity.Template t = ve.getTemplate(Context.pathTemplate+"SVEngine.vm");
        VelocityContext context = generateContextForSV(table);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        String fileName = Tools.obtenerServiceName(table.getTableName());
        generateFile(fileName,"dominio", table, writer);
    }


    private VelocityContext generateContextForSV(BNTable table) throws Exception {
        // rolCajaFonTr
        String nameBase = StringUtils.capitalize(Tools.generateNameBase(table.getTableName()));
        VelocityContext context = new VelocityContext();
        String packageName = getPackageName("domain",table.getTableName());
        context.put("packageDomain", packageName);
        context.put("filtroName", "BN" + nameBase + "Flt");
        context.put("serviceName", "SV" + nameBase);
        if (table.isUserBN()) {
            context.put("entityImpl", Tools.obtenerEntityBeanName(table.getTableName()));
        } else {
            context.put("entityImpl", Tools.generateNameImpl(table.getTableName()));

        }

        return context;
    }    
}
