package com.atux.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 19/11/2014.
 */
public enum TipoDocumentoEnum {
    BOLETA("01","Boleta"),
    FACTURA("02","Factura"),
    REF_MATRIX("03","Ref.Matriz"),
    REF_SALIDA("04","Ref.Salida"),
    PEDIDO("06","Pedido")
    ;

    private String codigo;
    private String nombre;

    TipoDocumentoEnum(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public static TipoDocumentoEnum findByCode(String codigo){
        TipoDocumentoEnum[] array= TipoDocumentoEnum.values();
        TipoDocumentoEnum result=null;
        for (int i=0;i<array.length;i++){

            if(array[i].getCodigo().equals(codigo)){
                result=array[i];
                break;
            }

        }
        return result;
    }

    public static List<TipoDocumentoEnum> findAll(){
        List<TipoDocumentoEnum> list= new ArrayList<TipoDocumentoEnum>();

        for(TipoDocumentoEnum status: TipoDocumentoEnum.values()){
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
