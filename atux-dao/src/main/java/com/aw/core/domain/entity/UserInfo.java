package com.aw.core.domain.entity;

import com.aw.core.format.DateFormatter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.Date;

/**
 * User: gmc
 * Date: 20/10/2008
 */
public class UserInfo implements Serializable {
    transient protected final Log logger = LogFactory.getLog(getClass());
    private Long userId ;
    private String userName;
    private String sessionIP;
    private Date sessionStart = new Date();
    private String so;
    private String noPc;

    public Long getUserId() {
        return userId;                                      
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSessionIP() {
        return sessionIP;
    }

    public void setSessionIP(String sessionIP) {
        this.sessionIP = sessionIP;
    }

    public Date getSessionStart() {
        return sessionStart;
    }

    public String getSessionStartStr() {
        return (String) DateFormatter.DATE_FORMATTER.format(sessionStart);
    }

    public void setSessionStart(Date sessionStart) {
        this.sessionStart = sessionStart;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public String getNoPc() {
        return noPc;
    }

    public void setNoPc(String noPc) {
        this.noPc = noPc;
    }
}
