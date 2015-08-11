package com.aw.swing.mvp.security;

import com.aw.core.exception.AWSystemException;
import com.aw.swing.mvp.Presenter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.util.Enumeration;
import java.util.Properties;

/**
 * User: gmc
 * Date: 04/06/2009
 */
public abstract class SecurityCodeManager {
    protected Log logger = LogFactory.getLog(getClass());

    public abstract void setSecurityCodes(Presenter presenter);

    public Class getPresenterClass(String pstSecurityCode) {
        Properties properties = getSecurityProperties();
        Enumeration enumeration = properties.keys();
        String className = "";
        while (enumeration.hasMoreElements()) {
            String property = (String) enumeration.nextElement();
            if (property.indexOf(".") == -1) {
                String currentPstSecCode = properties.getProperty(property);
                if (currentPstSecCode.equals(pstSecurityCode)) {
                    String propertyNameClass = property + ".Class";
                    className = properties.getProperty(propertyNameClass);
                    break;
                }
            }
        }
        if (!StringUtils.hasText(className)) {
            throw new IllegalStateException("There is not any presenter with security code:<" + pstSecurityCode + ">");
        }
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new AWSystemException("Problems getting class for:<" + className + ">", e);
        }
    }

    public String getSecurityCode(String pstClass) {
        String pstClassName = pstClass.replace("Pst", "");
        Properties properties = getSecurityProperties();
        Enumeration enumeration = properties.keys();
        String currentPstSecCode = "";
        while (enumeration.hasMoreElements()) {
            String property = (String) enumeration.nextElement();
            if (property.equals(pstClassName)) {
                currentPstSecCode = properties.getProperty(property);
                return currentPstSecCode;
            }
        }
        if (!StringUtils.hasText(currentPstSecCode)) {
            throw new IllegalStateException("There is not any presenter with security code:<" + pstClass + ">");
        }
        return currentPstSecCode;
    }

    protected abstract Properties getSecurityProperties();


}
