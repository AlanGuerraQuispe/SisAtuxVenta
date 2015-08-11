package com.atux.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 19/11/2014.
 */
public enum KardexMotivoEnum {
    KARDEX_STOCK_NEGATIVO("003","Kardex Stock Negativo"),
    KARDEX_STOCK_POSITIVO("052","Kardex Stock Positivo");

    private String codigo;
    private String nombre;

    KardexMotivoEnum(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public static KardexMotivoEnum findByCode(String codigo){
        KardexMotivoEnum[] array= KardexMotivoEnum.values();
        KardexMotivoEnum result=null;
        for (int i=0;i<array.length;i++){

            if(array[i].getCodigo().equals(codigo)){
                result=array[i];
                break;
            }

        }
        return result;
    }

    public static List<KardexMotivoEnum> findAll(){
        List<KardexMotivoEnum> list= new ArrayList<KardexMotivoEnum>();

        for(KardexMotivoEnum status: KardexMotivoEnum.values()){
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
