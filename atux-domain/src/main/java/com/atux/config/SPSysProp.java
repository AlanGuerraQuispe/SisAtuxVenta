package com.atux.config;


/**
 */
public class SPSysProp {

    public static final String AW_EDT_CHECKER_ENABLED = "com.edChecker.enabled";
    public static final String ATUX_ENVIROMENT = "suminet.env";
    public static final String ATUX_AUTOLOGIN_USR = "suminet.autologin.usr";
    public static final String ATUX_AUTOLOGIN_PWD = "suminet.autologin.pwd";
    public static final String ATUX_MULTIPLE_INSTANCES = "suminet.multiple.instances";

    // completa informacion y muestra mas informacion para debug
    private static final String ATUX_DEBUG_ENABLED = "suminet.debug.enabled";

    public static boolean isDeveloperDebugEnabled(){
        return "true".equals(System.getProperty(SPSysProp.ATUX_DEBUG_ENABLED) );
    }

    public static boolean permiteMultiplesInstancias(){
        return "true".equals(System.getProperty(SPSysProp.ATUX_MULTIPLE_INSTANCES) );
    }

}