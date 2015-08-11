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

import com.aw.swing.mvp.Presenter;

/**
 * User: gmc
 * Date: 02-nov-2007
 */
public class FilthyRichView extends View {
    public Object viewPanel;
    //private final BackgroundTitle backgroundTitle;

    /**
     * @param pst
     * @param vsr Class which attributes are the visual components that will be part of the view
     *                Currently the vsr could be:
     *                1) JDialog
     *                2) Any class that contains the following attributes:
     *                a) public JPanel pnlMain : pnlMain which contains all the components that will be part of the view
     *                b) public String smallTitle : shot title
     *                c) public String title   : title
     *                d) public JXXXX jcomponets: JComponents that will be shown in the view
     */
    public FilthyRichView(Presenter pst, Object vsr) {
        super(pst, vsr);

//        backgroundTitle = new BackgroundTitle(
//                    Bundles.getMessage(getClass(), "TXT_PersonaPresenter", ""));

//        backgroundTitle = new BackgroundTitle("Persona Form");
    }

    public void init() {
        createPanel();
        parentContainer = viewPanel;
    }

    public void createPanel(){
//        if (vsr instanceof BaseOverlayPanel || vsr instanceof PrincipalPanel){
//            viewPanel = vsr;
//        } else if (!(vsr instanceof MainDialog)) {
//            viewPanel = new JXPanel();
//            ((JXPanel)viewPanel).setAlpha(0.9f);
//
//            ((JXPanel)viewPanel).setOpaque(false);
//            ((JXPanel)viewPanel).setLayout(new BorderLayout());
//
//            ((JXPanel)viewPanel).add(BorderLayout.NORTH, backgroundTitle);
//            ((JXPanel)viewPanel).add(BorderLayout.WEST, Box.createHorizontalStrut(60));
//
//            JPanel pnlMain = (JPanel) AttributeAccessor.get(vsr, "pnlMain");
//
//            ((JXPanel)viewPanel).add(BorderLayout.CENTER, pnlMain);
//            ((JXPanel)viewPanel).add(BorderLayout.SOUTH, Box.createVerticalStrut(18));
//            ((JXPanel)viewPanel).add(BorderLayout.EAST, Box.createHorizontalStrut(60));
//        }
    }

    public void show() {
//        if (vsr instanceof BaseOverlayPanel){
//            TransitionManager.getMainFrame().showOverlay(pst);
//        } else if (vsr instanceof MainDialog){
//            SwingUtils.locateOnScreenCenter((MainDialog)vsr);
//            ((MainDialog)vsr).setVisible(true);
//        } else if (!(vsr instanceof PrincipalPanel)) {
//            if (TransitionManager.getMainFrame() != null){//for standalone testing
//                //todo jcv agregar titulo a la pagina
//                TransitionManager.getNavPanel().addLink("Persona");
//                TransitionManager.getTransPanel().startTransition(pst);
//            }
//        }
    }

    public void close() {
        //todo llamar al transition manager
    }

    public ViewLayout getLayout() {
//        return TransitionManager.getMainFrame();
        return null;
    }

    public void hide() {

    }

    public void unHide() {

    }
}
