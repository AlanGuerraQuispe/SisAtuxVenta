package com.aw.swing.mvp.ui.common.monitor;


import com.aw.core.monitor.MonitorCtx;
import com.aw.core.monitor.TaskInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: gmc
 * Date: 14/12/2009
 */
public class MonitorTask {
    SearchMsgBlocker msgBlocker;
    Timer timer;

    public MonitorTask(SearchMsgBlocker msgBlocker) {
        this.msgBlocker = msgBlocker;
    }

    public void init(){
        MonitorCtx.instance().init();
        timer = new Timer(500,new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                TaskInfo taskInfo=  MonitorCtx.instance().getTaskInfo();
                msgBlocker.setLabel(taskInfo.getNumRetrievedRows());
            }
        });
        timer.start();
    }
    public void stop(){
        MonitorCtx.instance().getTaskInfo().setCanceled(true);
        timer.stop();
    }

}

