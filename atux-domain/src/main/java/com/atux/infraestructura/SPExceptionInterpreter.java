package com.atux.infraestructura;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.domain.AWExcepcionInterpreter;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.jdbc.UncategorizedSQLException;

import java.sql.BatchUpdateException;
import java.sql.SQLException;

/**
 * User: Julio C. Macavilca
 * Date: 22/06/2009
 */
public class SPExceptionInterpreter  implements AWExcepcionInterpreter {
    @Override
    public Throwable handle(Throwable e) {
        if (e.getCause() instanceof ConstraintViolationException){
            ConstraintViolationException exception = (ConstraintViolationException) e.getCause();
            String bdErrorFull =  exception.getCause() instanceof BatchUpdateException ?
                    ((BatchUpdateException)exception.getCause()).getMessage() : "";
            if (exception.getErrorCode()==2292){ //ORA-02292: integrity constraint <constraint name> violated - child record found
                String msg = "No se puede eliminar el registro, pues existen registros que dependen de él.\n"
                        +"Detalle técnico:\n"
                        +" - sentencia SQL:"+exception.getSQL()+"\n" 
                        +" - BD error:"+bdErrorFull;
                return new AWBusinessException(msg, e);
            }
            return exception;
        }else if (e instanceof UncategorizedSQLException && e.getCause() instanceof SQLException){
            SQLException exception = (SQLException) e.getCause();
            String bdErrorFull =  exception.getMessage() ;
            if (exception.getErrorCode()==1407 ){   //ORA-01407: cannot update ("SCHEMA"."TABLE_NAME"."COLUMN_NAME") to NULL (se agrego pues eliminaciones tratan de poner el fk a NULL)
                String msg = "No se puede eliminar o actualizar el registro.\n"
                        +"Detalle técnico:\n"
                        +" - sentencia SQL:"+((UncategorizedSQLException)e).getSql()+"\n"
                        +" - BD error:"+bdErrorFull;
                return new AWBusinessException(msg, e);
            }
            return exception;
        }
        return e;
    }
}
