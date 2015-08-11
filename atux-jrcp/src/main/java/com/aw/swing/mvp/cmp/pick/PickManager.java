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
package com.aw.swing.mvp.cmp.pick;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.ActionNames;
import com.aw.swing.mvp.binding.BindingComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;


/**
 * Date: Sep 19, 2007
 */
public class PickManager {
    protected final Log logger = LogFactory.getLog(getClass());

    protected List<PickInfo> picksInfo;

    Presenter presenter;
    //    /**
    //     * It is the list of components that has picks
    //     */
    //    private java.util.List components;
    /**
     * It is the list of picks that are used
     */
    private List picks;
    public static final String PICK = "pick";
    public static final String PICK_NAME = "pickName";

    public PickManager(Presenter presenter) {
        picks = new ArrayList();
        this.presenter = presenter;
    }

    /**
     * @param attrName La caja de texto txt<attrName> debe existir
     * @param pick
     * @return
     */
    public Pick registerPick(String attrName, final Pick pick) {
        final String pickName = getPickName(attrName);
        getPicksInfo().add(new PickInfo(pick, attrName, pickName));
        picks.add(pick);
        if (pick instanceof PickImpl) {
            ((PickImpl) pick).setPresenter(presenter);
            pick.setMainAttribute(attrName);
            final JTextComponent jTextComponent = (JTextComponent) presenter.getIpView().getComponent(getTxtPick(attrName));
            jTextComponent.addFocusListener(new FocusAdapter(){
                public void focusLost(FocusEvent e) {

                    if (e.isTemporary()){
                        return;
                    }

                    // if the pick action is currently executing
                    Boolean executingPick = (Boolean) jTextComponent.getClientProperty(BindingComponent.ATTR_EXECUTING_PICK_ACTION);
                    if ((executingPick != null) && (executingPick) ){
                        jTextComponent.putClientProperty(BindingComponent.ATTR_EXECUTING_PICK_ACTION,null);
//                        return;
                    }
                    System.out.println("XXX    Focus LOST   2");
                    // if the focus will be directed to the pick Button
                    Component cmp= e.getOppositeComponent();
                    if (cmp instanceof JComponent){
                        JComponent jComponent = (JComponent) cmp;
                        String actionName = (String) jComponent.getClientProperty(BindingComponent.ATTR_ACTION);
                        if (pickName.equals(actionName)){
                            return;
                        }
                    }

                    if(!pick.isPickFilled()){

                        jTextComponent.setText("");                        
                    }
                }
            });
            jTextComponent.addKeyListener(new PickKeyListener(pick));
            jTextComponent.putClientProperty(PICK_NAME, pickName);
            jTextComponent.putClientProperty(PICK, pick);
//            JButton jButton = (JButton) presenter.getIpView().getComponent(getBtnPick(attrName));
        }
        return pick;
    }


    private static String getPickName(String attrName) {
        String pickName = ActionNames.ACTION_PICK + StringUtils.capitalize(attrName);
        return pickName;
    }

    public List<PickInfo> getPicksInfo() {
        if (picksInfo == null) {
            picksInfo = new ArrayList();
        }
        return picksInfo;
    }


//        } else if (pick instanceof PickGroup) {
//            ((PickGroup) pick).assignListenerToDiscriminator(component);
//        }
//            pick.setPickComponent(component);


    private static String getTxtPick(String attrName) {
        String txtPickName = "txt" + StringUtils.capitalize(attrName);
        return txtPickName;
    }

    private static String getBtnPick(String attrName) {
        String txtPickName = "btn" + StringUtils.capitalize(attrName);
        return txtPickName;
    }

}
