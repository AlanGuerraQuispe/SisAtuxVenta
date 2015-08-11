package atux.controllers.repository;
import atux.util.common.AtuxUtility;
import org.apache.commons.logging.Log;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Kaiser
 * Date: Jun 29, 2006
 * Time: 11:16:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class AtuxException extends RuntimeException {
    public static final String PARAM_MSGE = "aw.core.FullMsgInParam";

    protected String errorCode;
    protected Object errorArgs[];

    private boolean onlyContainsWarnings;

    public AtuxException(JDialog dialog,String errorCode){
        super();
        this.errorCode=errorCode;
        AtuxUtility.showMessage(dialog,errorCode,null);
    }
    public AtuxException(String errorCode){
        super();
        this.errorCode=errorCode;
    }

    public AtuxException(String errorCode, Object errorArgs[]){
        super();
        this.errorCode=errorCode;
        this.errorArgs=errorArgs;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getErrorArgs() {
        return errorArgs;

    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this <tt>Throwable</tt> instance
     *         (which may be <tt>null</tt>).
     */
    public String getMessage() {
         return errorCode;
    }

/*    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }
    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }
*/

    public boolean isOnlyContainsWarnings() {
        return onlyContainsWarnings;
    }

    public void setOnlyContainsWarnings(boolean onlyContainsWarnings) {
        this.onlyContainsWarnings = onlyContainsWarnings;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String toString(){
        return errorCode+(errorArgs==null?"": Arrays.asList(errorArgs).toString());
    }

    public static AtuxException wrapUnhandledException(Log logger, Throwable e) {
        return wrapUnhandledException(logger, e, "Operation fail");
    }
    public static AtuxException wrapUnhandledException(Log logger, SQLException e) {
        return wrapUnhandledException(logger, e, "Database SQL operation fail");
    }
    private  static AtuxException wrapUnhandledException(Log logger, Throwable e, Object errorMessage) {
        if (e instanceof AtuxException)
            return (AtuxException) e;
        else{
            logger.error(errorMessage,e);
            return new AtuxException(AtuxException.PARAM_MSGE,new Object[]{errorMessage+" : "+e.getMessage()});
        }
    }

}

