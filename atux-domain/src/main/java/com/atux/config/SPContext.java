package com.atux.config;

/**
 * User: Kaiser
 * Date: 17/08/2009
 */
public class SPContext {
    static SPContext instance;

    public static SPContext instance() {
        if (instance == null) {
            instance = new SPContext();
        }

        return instance;
    }

}
