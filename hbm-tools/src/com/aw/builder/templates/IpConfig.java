package com.aw.builder.templates;

import com.aw.builder.BNTable;
import com.aw.builder.Context;
import com.aw.builder.RPRepository;
import com.aw.builder.Tools;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.List;

/**
 * User: Kaiser
 * Date: 07/04/2009
 */
public class IpConfig  extends Template {


    public void process(VelocityEngine ve, BNTable table, List columns)throws Exception  {
        org.apache.velocity.Template t = ve.getTemplate(Context.pathTemplate+"IPEngine.vm");
        VelocityContext context = generateContextForFN(table,columns);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        String fileName = Tools.obtenerIpConfigName(table.getTableName());
        generateFile(fileName,"web", table, writer);
    }

    private VelocityContext generateContextForFN(BNTable table, List columns) throws Exception {
        // rolCajaFonTr
        String nameBase = StringUtils.capitalize(Tools.generateNameBase(table.getTableName()));
        VelocityContext context = new VelocityContext();

        String packageName = getPackageName("web",table.getTableName());
        context.put("packageWeb", packageName);
        context.put("ipNameClass", Tools.obtenerIpConfigName(table.getTableName()));
        context.put("entityImpl", Tools.generateNameImpl(RPRepository.getParentTable(table.getTableName())));
        context.put("columns", columns);
        return context;
    }

}
