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
public class ServiceImpl extends Template {

    @Override
    public void process(VelocityEngine ve, BNTable table, List columns) throws Exception {
        org.apache.velocity.Template t = ve.getTemplate(Context.pathTemplate+"SVEngineImpl.vm");
        VelocityContext context = generateContextForSVImpl(table);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        String fileName = Tools.obtenerServiceNameImpl(table.getTableName());
        generateFile(fileName,"dominio", table, writer);
    }

    private VelocityContext generateContextForSVImpl(BNTable table) throws Exception {
        // rolCajaFonTr
        String nameBase = StringUtils.capitalize(Tools.generateNameBase(table.getTableName()));
        VelocityContext context = new VelocityContext();
        String packageName = getPackageName("domain",table.getTableName());
        context.put("packageDomain", packageName);

        context.put("serviceNameImpl", "SV" + nameBase + "Impl");
        context.put("filtroName", "BN" + nameBase + "Flt");
        context.put("repositoryName", "RP" + nameBase);
        context.put("repositoryName_var", "rp" + nameBase);
        if (table.isUserBN()) {
            context.put("entityImpl", Tools.obtenerEntityBeanName(table.getTableName()));
            context.put("useDomain", "true");
        } else {
            context.put("entityImpl", Tools.generateNameImpl(table.getTableName()));

        }

        return context;
    }    
}
