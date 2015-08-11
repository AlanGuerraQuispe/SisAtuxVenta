package com.aw.support.net;

import com.aw.core.domain.AWBusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * User: Julio C. Macavilca
 * Date: 19/04/2008
 */
public class NetUtils {
    protected static final Log logger = LogFactory.getLog(NetUtils.class);

    public static String getOperatingSystem() {
        String nameOS = "os.name";
        return System.getProperty(nameOS);
    }
    
    public static String getLocalHostIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    public static String getLocalHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

}
