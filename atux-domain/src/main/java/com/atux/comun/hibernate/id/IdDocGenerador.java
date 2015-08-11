package com.atux.comun.hibernate.id;

import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.id.Configurable;
import org.hibernate.type.Type;

import java.util.Properties;

/**
 * <br>
 * Funcionalidad : <br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * <br>
 * ID   PROGRAMADOR  FECHA/HORA  TIPO          OBSERVACIÓN
 * 001   JCM          01/05/2009 Creación      <br>
 * 002   JCM          06/10/2008  Modificación  <br>
 * <br>
 * <br>
 *
 * @author Julio C. Macavilca - AW<br>
 * @version 1.0<br>
 */
public class IdDocGenerador extends IdNumeracionGenerador implements Configurable {
    

    public void configure(Type type, Properties params, Dialect dialect) throws MappingException {

        String clazz =  params.getProperty( "entity_name" );
//        Long pkModuloConfig =  ModuloTmImpl.entityNameToModuloPkMap.get(clazz);
//        if (pkModuloConfig==null)
//            throw new AWDeveloperException("Entity "+clazz+ " no ha sido configurado en la clase "+ModuloTmImpl.class.getName());
//        pkModulo = pkModuloConfig;
    }

//    String coTipoDocumento;
//    public void configure(Type type, Properties params, Dialect dialect) throws MappingException {
//        super.configure(type, params, dialect) ;
//        coTipoDocumento = params.getProperty( "coTipoDocumento" );
//    }
//
//    protected String afterGenerate(String numero) {
//        numero = coTipoDocumento+numero;
//        return numero;
//    }

}
