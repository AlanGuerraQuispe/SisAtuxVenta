package com.aw.desktop.spring.security;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 10/12/2009
 */
public class SiderAccionesLabelsMgr {
    List<PresenterActionInfo> presenters = new ArrayList();

    public SiderAccionesLabelsMgr() {
    }


    public void add(PresenterActionInfo pstActionInfo) {
        presenters.add(pstActionInfo);
    }

    public void guardarEnArchivo() {
        FileWriter outFile = null;
        try {
            outFile = new FileWriter("AccionesConNombresRepetidos.txtLineaG1");
            PrintWriter out = new PrintWriter(outFile);
            for (PresenterActionInfo presenterActionInfo : presenters) {
                if(presenterActionInfo.tieneAccionesConNombresDuplicados()){
                    out.println("## Ventana:" + presenterActionInfo.getPst().getClass());
                    presenterActionInfo.printAccionesDuplicadas(out);
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
