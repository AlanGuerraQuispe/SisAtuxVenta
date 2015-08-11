package com.atux.infraestructura;

import com.aw.core.dao.DAOIntgr;
import com.aw.core.db.DBExecuter;
import com.aw.core.db.transaction.TransactionDecorator;
import com.aw.core.domain.AWDeveloperException;
import com.aw.core.domain.AWDeveloperExceptionLogger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * User: Julio C. Macavilca
 * Date: 22/06/2009
 */
public class SPDeveloperExceptionLogger implements AWDeveloperExceptionLogger {
    @Override
    public void log(final AWDeveloperException ex) {
        TransactionDecorator.executeUpdate(new DBExecuter() {
            @Override
            public Object execute() {
                grabarEnBD(this.daoIntgr, ex);
                return null;
            }
        });

    }

    private void grabarEnBD(DAOIntgr daoIntgr, AWDeveloperException ex) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(os));
        String error = ex.getMessage();
        if (error!=null&&error.length()>200) error=error.substring(0,200);
        String stackTrace = os.toString();
        if (stackTrace!=null&&stackTrace.length()>4000) stackTrace=stackTrace.substring(0,4000);

//        ApplicationUser user = SPApplication.getAppUser();

   /*     StringBuffer sql = new StringBuffer();
        sql.append("insert into ERROR_TR(PK_ERROR, TX_ERROR, TX_TRACE, USUA_CREA, FECH_CREA, NUIP_CREA, USSO_CREA, NOPC_CREA)");
        sql.append("values(SEQ_ERROR_PK.nextval, ?, ?, ?, SYSDATE, ?, ?, ?)");
        daoIntgr.getSql().execSqlUpdate(sql.toString(), 
                new Object[]{error, stackTrace, user.getUsername(), user.getIpAddress(),user.getHostName(),user.getHostName()}
            );*/
    }

}