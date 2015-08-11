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
package com.aw.swing.mvp.ui.painter;

import java.awt.*;

/**
 * User: gmc
 * Date: 01-oct-2007
 */
public class ColorInfoProvider {

    public static final Color COLOR_DEFAULT = new Color(255, 255, 255);
    public static final Color COLOR_TITLE_DEFAULT = new Color(0, 0, 0);
//    public static final Color COLOR_ERROR = new Color(255, 215, 215);
    public static final Color COLOR_ERROR = new Color(254, 204, 205);


    public static final Color COLOR_BORDER_ERROR = new Color(255, 0, 0);

    public static final Color PANEL_ERROR_BACKGROUND = Color.WHITE;
    public static final String PANEL_ERROR = "pnlError";

    public static final String LABEL_ERROR = "lblError";

    public static final String LABEL_OPTIONS = "lblOptions";
    public static final Color LABEL_OPTIONS_FOREGROUND = Color.GREEN;

    public static final String PANEL_TITULO_GRID = "pnlTitGrid";
    public static final Color PANEL_TITULO_GRID_BACKGROUND = Color.RED;

    public static final String CHECKBOX_SEL = "chkSel";
    public static final Color CHECKBOX_SEL_BACKGROUND = Color.RED;
    public static final Color CHECKBOX_SEL_FOREGROUND = Color.GREEN;

    public static final String LABEL_TITULO_GRID = "lblTitGrid";
    public static final Color LABEL_TITULO_GRID_FOREGROUND = Color.WHITE;


//    public static final Color TEXT_BOX_READ_ONLY = Color.lightGray;
//    public static final Color TEXT_BOX_READ_ONLY = new Color(214,217,223);
    public static final Color TEXT_BOX_READ_ONLY = new Color(219, 216,222);

    public static final Color CALENDAR_TODAY_FOREGROUND = new Color(20,56,110);
    public static final Color CALENDAR_TODAY_FOREGROUND_OVER = new Color(1,130,184);


    public static Color getBackGroundFor(String componentName) {
        if (componentName.equals(PANEL_ERROR)) {
            return PANEL_ERROR_BACKGROUND;
        } else if (componentName.equals(PANEL_TITULO_GRID)) {
            return PANEL_TITULO_GRID_BACKGROUND;
        } else if (componentName.equals(CHECKBOX_SEL)) {
            return CHECKBOX_SEL_BACKGROUND;
        }
        return null;
    }

    public static Color getForeGround(String componentName) {
        if (componentName.equals(LABEL_OPTIONS)) {
            return LABEL_OPTIONS_FOREGROUND;
        } else if (componentName.equals(LABEL_TITULO_GRID)) {
            return LABEL_TITULO_GRID_FOREGROUND;
        } else if (componentName.equals(CHECKBOX_SEL)) {
            return CHECKBOX_SEL_FOREGROUND;
        }
        return null;
    }


}
