package com.aw.builder.templates;

import com.aw.builder.BNTable;
import com.aw.builder.Context;
import com.aw.builder.Tools;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.util.List;

/**
 * User: Kaiser
 * Date: 02/04/2009
 */
public abstract class Template {

    public abstract void process(VelocityEngine ve, BNTable table, List columns) throws Exception;

    public String getPackageName(String directoryName,String tableName){
        return Context.packageName+"."+directoryName+"."+Tools.generateDirectoryNameBase(tableName).toLowerCase();
    }

    protected void generateFile(String fileName,String dir, BNTable table, StringWriter writer) throws IOException {

        String directory = Context.packageDir+"/"+dir+"/"+ Tools.generateDirectoryNameBase(table.getTableName());

        File dirr = new File("./generated/"+directory);
        dirr.mkdir();

        File file = new File("./generated/"+directory+"/"+fileName+".java");
        // Create file if it does not exist
        boolean success = file.createNewFile();
        System.out.println(file.getName());
        FileWriter fstream = new FileWriter(file);
        BufferedWriter out = new BufferedWriter(fstream);
        out.write(writer.toString());
        out.close();

    }
}
