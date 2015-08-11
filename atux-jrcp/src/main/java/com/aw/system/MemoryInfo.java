package com.aw.system;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: Juan Carlos Vergara
 * Date: 17/12/2009
 */
public class MemoryInfo {
    protected final Log logger = LogFactory.getLog(MemoryInfo.class);
//    Logger logger = Logger.getLogger("Perf");

    private static MemoryInfo instance = new MemoryInfo();

    public static MemoryInfo getInstance(){
        return instance;
    }

    public void showMemoryInfo(String context){
        long mb = 1000000;
        long maxMemory = Runtime.getRuntime().maxMemory()/mb;
        long freeMemory = Runtime.getRuntime().freeMemory()/mb;
        long totalMemory = Runtime.getRuntime().totalMemory()/mb;

        logger.info(context);
        logger.info("___ Start Memory Info ___");
        logger.info("   Maximum Allowable Memory: " + maxMemory + "MB");
        logger.info("   Total Memory: " + totalMemory + "MB");
        logger.info("   Free Memory: " + freeMemory + "MB");
        logger.info("   Used Memory: " + (maxMemory - totalMemory)  + "MB");
        logger.info("___ End Memory Info ___");
    }

    private MemoryInfo(){};
}
