package com.atux.infraestructura;

import com.aw.core.domain.AWBusinessException;
import com.aw.core.domain.AWDeveloperException;
import com.aw.core.exception.AWException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: PC6
 * Date: 18/05/2009
 * Time: 10:25:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class AWFTPClient extends FTPClient {
    protected final Log logger = LogFactory.getLog(getClass());

    public void connect(String host, int port, String user, String password) {
        try {
            super.connect(host, port);
            logger.debug(getReplyString());
            // After connection attempt, you should check the reply code to verify
            // success.
            int reply = this.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                this.disconnect();
                logger.debug("FTP server refused connection.");
                throw new AWBusinessException("FTP login error");
            }

            super.login(user, password);
            //logger.debug(getReplyString());
            //super.pass(password);
            logger.debug(getReplyString());
            super.setFileType(FTP.BINARY_FILE_TYPE);
            logger.debug(getReplyString());
        } catch (IOException e) {
            throw new AWBusinessException("No se puede manipular archivos adjuntos en este momento. El servidor FTP no esta disponible.\n" +
                    "Por favor reintente, si el problema persiste contacte al area de TI.\n" +
                    "Error interno: "+e.getMessage(), e);
             //.wrapUnhandledException(LogFactory.getLog(getClass()), e)
        }

    }

    public void setWorkingDir(String... dirNames) {
        try {

            for (String dirName : dirNames) {
                makeDirectory(dirName);
                logger.debug(getReplyString());
                changeWorkingDirectory(dirName);
                logger.debug(getReplyString());
            }
        } catch (IOException e) {
            throw new AWException("Change directory Error", e.getCause());
        }

    }

    public void storeFile(String localFilePath, String remoteFilePath) {
        try {
            pasv();
            InputStream istream = new FileInputStream(localFilePath);
            if (!storeFile(remoteFilePath, istream)) {
                throw new AWBusinessException("FTP store error");
            }

//            lo(getReplyString());
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            if (message.indexOf("(")!=-1)
                message = message.substring(0,message.indexOf("(")-1 );
            throw new AWDeveloperException("El archivo no se encuentra, por favor verifique.\n Archivo : "+ message);
        } catch (IOException e) {
            throw new AWBusinessException("FTP store error", e);
        } catch (Exception e) {
            throw new AWBusinessException("FTP store error", e);
        }
    }

    public void storeFileThrowExcep(String localFilePath, String remoteFilePath) throws IOException {
        pasv();
        InputStream istream = new FileInputStream(localFilePath);
        storeFile(remoteFilePath, istream);
    }
}



