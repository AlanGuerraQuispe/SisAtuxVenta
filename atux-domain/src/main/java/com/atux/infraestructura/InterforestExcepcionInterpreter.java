package com.atux.infraestructura;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.domain.AWExcepcionInterpreter;
import oracle.jdbc.driver.OracleSQLException;

/**
 * User: jmacavilca
 * Date: 23/02/12
 */
public class InterforestExcepcionInterpreter implements AWExcepcionInterpreter {
    @Override
    public Throwable handle(Throwable e) {
        if (e!=null &&
                e.getCause()!=null &&
                e.getCause().getCause()!=null && e.getCause()!=e.getCause().getCause() &&
                e.getCause().getCause() instanceof OracleSQLException){
            OracleSQLException rootCause= (OracleSQLException ) e.getCause().getCause();
            String message = rootCause.getMessage();
            if (message.contains("FK_EVENTO_PUNTOS_PROMOCION") && message.contains("EVENTO_PUNTOS") )
                message = "La promoción esta siendo usada por Acumulación/Canjes";
            return new AWBusinessException(message, rootCause);
        }
        return e;
    }
}
