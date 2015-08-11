package com.aw.desktop.spring.security;

import com.aw.swing.mvp.Presenter;

import java.io.PrintWriter;
import java.util.*;

/**
 * User: gmc
 * Date: 10/12/2009
 */
public class PresenterActionInfo {
    private Presenter pst;
    private Map<String, List<AccionTmImplWrapper>> acciones = new HashMap();

    public PresenterActionInfo(Presenter pst) {
        this.pst = pst;
    }

    public void setVentanaAcciones(List<AccionTmImplWrapper> ventanaAcciones) {
        for (AccionTmImplWrapper accionTm : ventanaAcciones) {
            String noAccion = "";
//            String noAccion = accionTm.getNoAccion();
            if (acciones.containsKey(noAccion)){
                acciones.get(noAccion).add(accionTm);
            }else{
                List list = new ArrayList();
                list.add(accionTm);
                acciones.put(noAccion,list);
            }
        }
    }

    public void printAccionesDuplicadas(PrintWriter writer) {
        Set<String> keySet = acciones.keySet();
        for (String key : keySet) {
            List<AccionTmImplWrapper> acRep = acciones.get(key);
            if(acRep.size()>1){
                writer.println(key);
                for (AccionTmImplWrapper accionTmImplWrapper : acRep) {
//                    writer.println(accionTmImplWrapper.getId()+ " "+ accionTmImplWrapper.getCoAccion() +" " +accionTmImplWrapper.getDeAccion()+ " " +accionTmImplWrapper.getNoAccion());
                }
            }
        }
    }
    public boolean tieneAccionesConNombresDuplicados() {
        Set<String> keySet = acciones.keySet();
        for (String key : keySet) {
            if(acciones.get(key).size()>1){
                return true;
            }
        }
        return false;
    }

    public Presenter getPst() {
        return pst;
    }
}
