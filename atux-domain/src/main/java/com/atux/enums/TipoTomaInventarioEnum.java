package com.atux.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 1/3/2015.
 */
public enum TipoTomaInventarioEnum  {
    LABORATORIO("L","Laboratorio"),
    PRODUCTO("P","Producto");


    private String codigo;
    private String nombre;

    TipoTomaInventarioEnum(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public static TipoTomaInventarioEnum findByCode(String codigo){
        TipoTomaInventarioEnum[] array= TipoTomaInventarioEnum.values();
        TipoTomaInventarioEnum result=null;
        for (int i=0;i<array.length;i++){

            if(array[i].getCodigo().equals(codigo)){
                result=array[i];
                break;
            }

        }
        return result;
    }

    public static List<TipoTomaInventarioEnum> findAll(){
        List<TipoTomaInventarioEnum> list= new ArrayList<TipoTomaInventarioEnum>();

        for(TipoTomaInventarioEnum status: TipoTomaInventarioEnum.values()){
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
