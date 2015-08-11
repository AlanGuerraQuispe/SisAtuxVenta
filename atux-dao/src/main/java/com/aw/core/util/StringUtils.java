package com.aw.core.util;

import java.util.Map;

/**
 * User: Manuel Flores
 * Date: 05/10/2007
 */
public class StringUtils {

    public static boolean isEmpty(String arg) {
        return !hasText(arg);
    }

    public static boolean hasText(String arg) {
        return org.springframework.util.StringUtils.hasText(arg);
    }


    public static String nvl(String arg, String argIfNull) {
        return isEmpty(arg)? argIfNull:arg;
    }
    public static Object nvl(Object arg, String argIfNull) {
        if (arg!=null && !(arg instanceof String)) arg = arg.toString();
        return nvl((String)arg, argIfNull);
    }
    public static String reemplazar(String msg, Map<String, Object> mapVal) {
        for (String key : mapVal.keySet()) {
            Object val =  mapVal.get(key);
            msg = msg.replace(key, String.valueOf(val));
        }
        return msg;
    }


    public static boolean equals(String str1, String str2) {
        if (str1==null && str1==str2) return  true;
        else if (str1!=null && str1.equals(str2)) return true;
        else if (str2!=null && str2.equals(str1)) return true;
        return false;
    }

    public static String trunc(String txt, int size) {
        if (txt==null)
            return txt;
        else if (txt.length() > size) {
            return txt.substring(0, size-1);
        } else {
            return txt;
        }
    }
}
