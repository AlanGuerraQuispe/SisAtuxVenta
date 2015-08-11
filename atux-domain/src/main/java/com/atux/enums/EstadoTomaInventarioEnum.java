package com.atux.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 1/3/2015.
 */
public enum EstadoTomaInventarioEnum {
    PROCESO("P","Proceso"),
    CERRADO("C","Cerrado"),
    ANULADO("A","Anulado");


    private String codigo;
    private String nombre;

    EstadoTomaInventarioEnum(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public static EstadoTomaInventarioEnum findByCode(String codigo){
        EstadoTomaInventarioEnum[] array= EstadoTomaInventarioEnum.values();
        EstadoTomaInventarioEnum result=null;
        for (int i=0;i<array.length;i++){

            if(array[i].getCodigo().equals(codigo)){
                result=array[i];
                break;
            }

        }
        return result;
    }

    public static List<EstadoTomaInventarioEnum> findAll(){
        List<EstadoTomaInventarioEnum> list= new ArrayList<EstadoTomaInventarioEnum>();

        for(EstadoTomaInventarioEnum status: EstadoTomaInventarioEnum.values()){
            list.add(status);
        }

        return list;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
