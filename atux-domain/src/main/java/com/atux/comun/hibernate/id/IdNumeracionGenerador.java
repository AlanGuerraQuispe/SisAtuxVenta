package com.atux.comun.hibernate.id;


import com.aw.core.domain.AWBusinessException;
import com.aw.core.domain.AWDeveloperException;
import oracle.jdbc.OracleTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 */
public class IdNumeracionGenerador implements IdentifierGenerator {

    public Serializable generate(org.hibernate.engine.spi.SessionImplementor sessionImplementor, Object o) throws HibernateException {
        String numero = dbGenerarNumero(sessionImplementor.connection(), pkModulo, 6).toString();
        //numero = IdNumeracionGenerador.fill(numero, 6);
        numero = afterGenerate(numero);
        logger.debug("Modulo:"+pkModulo+" numero final generado:"+numero);
        return numero;
    }

    transient private static Log logger = LogFactory.getLog(IdNumeracionGenerador.class);
    Long pkModulo;

    public void configure(Type type, Properties params, Dialect dialect) throws MappingException {
        pkModulo = Long.parseLong(params.getProperty( "pkModulo" )) ;
    }


    protected String afterGenerate(String numero) {
        return numero;
    }

    public static String fill(String numero, int size) {
        if (numero.length()>size)
            throw new IllegalArgumentException("Numero sobrepaso los "+size+" digitos:"+numero);
        while (numero.length()<size) numero="0"+numero;
        return numero;
    }

    public static String dbGenerarNumero(Connection conn, Long pkModulo, Integer padSize ) {
        String sql = "{ call ? := Pk_SUMINISTROS_UTIL.FN_MODULO_NEXT_NUM( ?, ?, ?, ?, ?, ? ) }";
        try {
            //String sql = "{ call ? := PF_SUMINISTROS_UTIL.FN_OBTENER_NUMERACION( CO_NUMERACION, usua_modi, nuip_modi, usso_modi, nopc_modi ) }";
//            ApplicationUser appUser = SPApplication.getAppUser();
            //Connection conn = sessionImplementor.connection();
            CallableStatement stmt = conn.prepareCall(sql);

            stmt.registerOutParameter(1, OracleTypes.VARCHAR);
            stmt.setObject(2, pkModulo);
            stmt.setObject(3, padSize);//padSize
//            stmt.setObject(4, appUser.getUsername());//"USUMODI"
//            stmt.setObject(5, appUser.getIpAddress()); //"IPMODI"
//            stmt.setObject(6, appUser.getOS());  //"SOMODI"
//            stmt.setObject(7, appUser.getHostName()); //"PCMODI"
            logger.info(" Pk_SUMINISTROS_UTIL.FN_MODULO_NEXT_NUM:"+ pkModulo);
            stmt.execute();
            String returnValue = (String) stmt.getObject(1);
            logger.debug("Numero generado:"+returnValue);
            stmt.close();
            return returnValue;
        } catch (SQLException e) {
            logger.error("SQL:" + sql);
            throw new AWDeveloperException(AWBusinessException.wrapUnhandledException(logger, e)) ;
        }
    }
}
