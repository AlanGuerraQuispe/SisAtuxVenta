/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.core.hibernate.reveng;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * User: Julio C. Macavilca   
 */
public class EntityImplGenerador {
    public static void main(String[] args) throws IOException {
        if (args.length<3)
                throw new IllegalArgumentException("Usage EntityImplGenerador generatedRootDir pojoPackage entityPackage ");
        String generatedRootDir = args[0];
        String pojoPackage = args[1];
        String entityPackage = args[2];

//        File dirOrigen = new File("D:\\aw\\etna2\\qt\\main\\qt-commons\\src\\main\\java\\com\\etna\\qt\\hibernate\\pojo");
        //File dirOrigen = new File("D:\\$AW\\proyectos\\etna\\qt\\utils\\hbmtools\\generated2\\.final\\com\\etna\\qt\\hibernate\\pojo");
        File dirOrigen = new File(generatedRootDir+"\\"+pojoPackage.replace('.','\\'));
//        File dirDestino = new File("D:\\aw\\etna2\\qt\\main\\qt-commons\\src\\main\\java\\com\\etna\\qt\\dominio\\entity");
        //File dirDestino = new File("D:\\$AW\\proyectos\\etna\\qt\\utils\\hbmtools\\generated2\\.final\\com\\etna\\qt\\dominio\\entity");
        File dirDestino = new File(generatedRootDir+"\\"+entityPackage.replace('.','\\'));
        new EntityImplGenerador().generar(dirOrigen, dirDestino);
    }

    private void generar(File dirOrigen, File dirDestino) throws IOException {
        File[] files = dirOrigen.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) continue;
            String pojoName = file.getName();
            pojoName = pojoName.substring(0, pojoName.length() - 5);
            System.out.println(pojoName);
            if (pojoName.endsWith("Pk")) continue;
            String implName = pojoName + "Impl";
            String contenido = "package com.atux.dominio.entity;\n";
            contenido += "\n";       
            contenido += "import com.atux.hibernate.pojo." + pojoName + ";";
            contenido += "\n";
            contenido += "public class " + pojoName + "Impl extends " + pojoName + " {\n";
            contenido += "}\n";
            File fileDestino = new File(dirDestino + "/" + implName + ".java");
            if (!fileDestino.exists()) {
                BufferedWriter out = new BufferedWriter(new FileWriter(fileDestino));
                out.write(contenido);
                out.close();
            }
        }
    }
}
