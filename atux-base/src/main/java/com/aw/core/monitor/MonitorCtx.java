package com.aw.core.monitor;

/**
 * User: gmc
 * Date: 14/12/2009
 */
public class MonitorCtx {
    public static final MonitorCtx instance = new MonitorCtx();
    private TaskInfo taskInfo;

    public static MonitorCtx instance(){
        return instance;
    }

    public TaskInfo getTaskInfo() {
        return taskInfo;
    }

    public void init() {
        taskInfo = new TaskInfo();
    }

}

