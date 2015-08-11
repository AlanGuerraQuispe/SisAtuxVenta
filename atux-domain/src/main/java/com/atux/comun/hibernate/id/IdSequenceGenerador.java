package com.atux.comun.hibernate.id;

import com.aw.core.domain.AWBusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class IdSequenceGenerador implements Configurable, IdentifierGenerator {
    transient private Log logger = LogFactory.getLog(getClass());

    int longitud = 8;
    String sequence;

    public Serializable generate(org.hibernate.engine.spi.SessionImplementor sessionImplementor, Object o) throws HibernateException {
        String numero = dbGenerarNumero(sessionImplementor).toString();
        numero = IdNumeracionGenerador.fill(numero, longitud);
        logger.debug("Numero final generado:"+numero);
        return numero;
    }

    public void configure(Type type, Properties params, Dialect dialect) throws MappingException {
        //if (params.contains( "longitud" ))
        longitud = Integer.parseInt(params.getProperty( "longitud" )) ;
        sequence = params.getProperty( "sequence" );
    }



    private Number dbGenerarNumero(org.hibernate.engine.spi.SessionImplementor sessionImplementor) {
        String sql = "SELECT "+sequence+".NEXTVAL FROM DUAL";
        Number returnValue =null;
        try {
            Connection conn = sessionImplementor.connection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
            returnValue = (Number) rs.getObject(1);
            }
            logger.debug("Numero generado:"+returnValue);
            stmt.close();
            return returnValue;
        } catch (SQLException e) {
            logger.error("SQL:" + sql);
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }
}