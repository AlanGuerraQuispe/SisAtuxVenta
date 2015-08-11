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

import com.aw.support.reflection.MethodInvoker;
import com.aw.swing.mvp.Constants;
import com.aw.swing.mvp.Presenter;

/**
 * User: gmc
 * Date: 02-nov-2007
 */
public abstract class View {
    protected Presenter pst;
    /*
    The top level container
     */
    protected Object parentContainer;
    protected Object vsr;
    protected String title;

    /**
     *
     * @param pst
     * @param vsr Class which attributes are the visual components that will be part of the view
     * Currently the vsr could be:
     * 1) JDialog
     * 2) Any class that contains the following attributes:
     *      a) public JPanel pnlMain : pnlMain which contains all the components that will be part of the view
     *      b) public String smallTitle : shot title
     *      c) public String title   : title
     *      d) public JXXXX jcomponets: JComponents that will be shown in the view
     */
    public View(Presenter pst, Object vsr) {
        this.pst = pst;
        this.vsr = vsr;
        if (MethodInvoker.existsMethod(vsr, Constants.AFTER_SETUPUI_METHOD_NAME)){
            try {
                MethodInvoker.invoke(vsr,Constants.AFTER_SETUPUI_METHOD_NAME);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                throw new IllegalStateException("Problems calling the method:<afterSetupUI> on :"+vsr);                
            }
        }
    }

    public Object getParentContainer() {
        return parentContainer;
    }

    public abstract void init();
    public abstract void show();
    public abstract void close();
    public abstract ViewLayout getLayout();

    public abstract void hide();
    public abstract void unHide();

    public Object getVsr() {
        return vsr;
    }

    public Presenter getPst() {
        return pst;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
