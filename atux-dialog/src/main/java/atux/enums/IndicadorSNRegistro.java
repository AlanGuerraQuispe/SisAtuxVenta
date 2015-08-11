package atux.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aguerra on 06/08/2015.
 */
public enum IndicadorSNRegistro {
    SI("S","SI"),
    NO("N","NO");

    private String codigo;
    private String nombre;

    IndicadorSNRegistro(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public static IndicadorSNRegistro findByCode(String codigo){
        IndicadorSNRegistro[] array= IndicadorSNRegistro.values();
        IndicadorSNRegistro result=null;
        for (int i=0;i<array.length;i++){

            if(array[i].getCodigo().equals(codigo)){
                result=array[i];
                break;
            }

        }
        return result;
    }

    public static List<IndicadorSNRegistro> findAll(){
        List<IndicadorSNRegistro> list= new ArrayList<IndicadorSNRegistro>();

        for(IndicadorSNRegistro status: IndicadorSNRegistro.values()){
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
