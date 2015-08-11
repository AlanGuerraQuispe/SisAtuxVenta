package com.aw.core.dao;

import org.apache.commons.logging.LogFactory;

/**
 * User: Server
 * Date: 14/10/2009
 */
public class HbmFailMngr {
    private static final ThreadLocal < Boolean > failMode =
          new ThreadLocal < Boolean > () {
              @Override
              protected Boolean initialValue() {
                  return Boolean.FALSE;
                  }
      };

    public static boolean isFailMode(){
        return failMode.get();
    }
    public static void seFailMode(boolean enableFailMode){
        failMode.set(enableFailMode);
        if (enableFailMode){
            LogFactory.getLog(HbmFailMngr.class).debug("Fail mode enabled");
        }
    }
}
