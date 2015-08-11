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
package com.aw.swing.mvp.view;

import com.aw.core.util.TimeObserver;
import com.aw.swing.exception.AWValidationException;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.JDialogView;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.ui.common.ProcessMsgBlocker;
import com.aw.swing.mvp.ui.painter.PainterMessages;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fest.swing.fixture.DialogFixture;

import javax.swing.*;
import java.awt.*;

/**
 * User: gmc
 * Date: 07-may-2007
 * Class used to do all the operations related to the view
 */
public class ViewManager {

    protected final Log logger = LogFactory.getLog(getClass());

    private Presenter presenter;
    private Object viewSrc;
    private IPView ipView;
    private View view;
    private boolean useForTest;

    private String title;

    public ViewManager(Presenter presenter) {
        this.presenter = presenter;
    }

    public void setViewSrc(Object vsr) {
        this.viewSrc = vsr;
        view = ViewFactory.createView(presenter, vsr);
        ipView = new IPView(vsr);
    }

    public Object getViewSrc() {
        return viewSrc;
    }

    public ViewLayout getLayout() {
        return view.getLayout();
    }

    public String toString() {
        return viewSrc.toString();
    }

    public void initView() {
        logger.debug("Preparing the View");
        prepareView();
        logger.debug("Initializing the View");
        ProcessMsgBlocker.instance().removeMessage();
        TimeObserver time = new TimeObserver("Initializing the View");
        time.empezar();
        view.setTitle(title);
        view.init();
        ProcessMsgBlocker.instance().removeMessage();
        AWWindowsManager.instance().setPresenterToBeShown(presenter);
//        SwingContext.setCurrentPst(presenter);
        try {
            presenter.getValidatorMgr().validateBasic();
        } catch (AWValidationException ex) {
//            PainterMessages.paintException(ex, false);
              PainterMessages.paintExceptionWithoutRequestingFocus(ex);
        }
        time.terminar();
        view.show();

    }

    /**
     * Setting some default colors to the screens
     */
    private void prepareView() {
        if (!(presenter instanceof FindPresenter)) {
            JPanel pnlToolBar = getIpView().getPnlToolBar();
            if (pnlToolBar != null) {
                pnlToolBar.setBackground(new Color(223, 232, 246));
                pnlToolBar.setBorder(BorderFactory.createLineBorder(new Color(131, 172, 219), 1));
            }
        }
    }

    public View getView() {
        return view;
    }

    /**
     * Close the window and release the resources
     */
    public void closeView() {
        AWWindowsManager.instance().removePst(view.getPst());
        Presenter lastPst =null;
        if(AWWindowsManager.instance().getCurrentFlowMgr()!=null){
            lastPst =AWWindowsManager.instance().getCurrentFlowMgr().getLastPstShown();
        }
        view.close();
        viewSrc = null;
        if (lastPst!=null){
            ((JDialog)((JDialogView)lastPst.getView()).getParentContainer()).setVisible(true);
        }
    }

    public Component[] getJComponents() {
        return ipView.getJComponents();
    }

    public IPView getIpView() {
        return ipView;
    }

    public DialogFixture getFixture() {
        return ((JDialogView) view).dialogFixture;
    }

    public boolean isUseForTest() {
        return useForTest;
    }

    public void setUseForTest(boolean useForTest) {
        this.useForTest = useForTest;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
