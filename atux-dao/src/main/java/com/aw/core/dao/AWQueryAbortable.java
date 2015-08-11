/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.core.dao;

import com.aw.core.monitor.MonitorCtx;
import com.aw.core.monitor.TaskInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: JCM
 * Date: 02/10/2007
 */
public class AWQueryAbortable {
    protected final Log logger = LogFactory.getLog(getClass());
    public static int DEF_LIST_SIZE = 10000;
    public static AWQueryAbortable instance = new AWQueryAbortable();
    public static AWQueryAbortable DUMMY = new AWQueryAbortable(){
        public void incRowCount() {}
    };

    int rowCount = 0;
    boolean aborted = false;

    public static AWQueryAbortable instance(){
        // TODO integrar con GMC
//        return DUMMY;
        return instance;
    }

    private TaskInfo getTaskInfo(){
        TaskInfo taskInfo = MonitorCtx.instance().getTaskInfo();
        if (taskInfo == null) {
            taskInfo = new TaskInfo();
        }
        return taskInfo;
    }
    public AWQueryAbortable resetRowCount() {
        rowCount= 0;
        aborted = false;
        getTaskInfo().setCanceled(false);
        return this;
    }
    public void incRowCount() {
        rowCount++;
        getTaskInfo().setNumRetrievedRows(rowCount);
    }

    final public boolean isAborted() {
        aborted = getTaskInfo().isCanceled();
        return aborted;
    }

    final public int getRowCount() {
        return rowCount;
    }
}