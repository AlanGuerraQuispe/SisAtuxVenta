package com.aw.core.bean.security.login;

import java.io.Serializable;

/**
 * User: gmc
 * Date: 20/10/2008
 */
public class BNLogin implements Serializable {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
