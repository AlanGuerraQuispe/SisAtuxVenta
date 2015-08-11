package com.atux.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 19/11/2014.
 */
public enum KardexGrupoMotivoEnum {
    KARDEX("01","Kardex"),
    AJUSTE_INVENTARIO("04","Ajuste de Inventario");

    private String codigo;
    private String nombre;

    KardexGrupoMotivoEnum(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public static KardexGrupoMotivoEnum findByCode(String codigo){
        KardexGrupoMotivoEnum[] array= KardexGrupoMotivoEnum.values();
        KardexGrupoMotivoEnum result=null;
        for (int i=0;i<array.length;i++){

            if(array[i].getCodigo().equals(codigo)){
                result=array[i];
                break;
            }

        }
        return result;
    }

    public static List<KardexGrupoMotivoEnum> findAll(){
        List<KardexGrupoMotivoEnum> list= new ArrayList<KardexGrupoMotivoEnum>();

        for(KardexGrupoMotivoEnum status: KardexGrupoMotivoEnum.values()){
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
