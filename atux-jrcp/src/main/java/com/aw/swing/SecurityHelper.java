package com.aw.swing;

/**
 * User: jvergara
 * Date: 02/05/11
 */
public class SecurityHelper {
    static SecurityHelper instance = new SecurityHelper();

    String username;

    public static SecurityHelper instance(){
        return instance;
    }

    public static SecurityHelper getInstance() {
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private SecurityHelper() {
    }
}
