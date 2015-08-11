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

import com.aw.swing.mvp.JDialogView;
import com.aw.swing.mvp.Presenter;

/**
 * User: gmc
 * Date: 02-nov-2007
 */
public class ViewFactory {

    public static View createView(Presenter pst, Object vsr){
//        if(SwingContext.getSwingConfiguration().isUseTraditionalView()){
            return new JDialogView(pst, vsr);
           // return new JFrameView(pst, vsr);
//        }
//        return new FilthyRichView(pst,vsr);
    }
}
