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
package com.aw.swing.mvp.action;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * User: gmc
 * Date: 11-sep-2007
 */
public class ActionDialog {

    public static final int KEY_LEFT = KeyEvent.VK_LEFT;
    public static final int KEY_RIGHT = KeyEvent.VK_RIGHT;
    public static final int KEY_UP = KeyEvent.VK_UP;
    public static final int KEY_DOWN = KeyEvent.VK_DOWN;

    public static final int KEY_ESC = KeyEvent.VK_ESCAPE;
    public static final int KEY_ALT = KeyEvent.VK_ALT;

    public static final int KEY_ENTER = KeyEvent.VK_ENTER;
    public static final int KEY_SPACE = KeyEvent.VK_SPACE;
    public static final int KEY_TAB = KeyEvent.VK_TAB;
    public static final int KEY_CONTROL = KeyEvent.VK_CONTROL;

    public static final int KEY_1 = KeyEvent.VK_1;
    public static final int KEY_9 = KeyEvent.VK_9;

    public static final int KEY_F1 = KeyEvent.VK_F1;
    public static final int KEY_F2 = KeyEvent.VK_F2;
    public static final int KEY_F3 = KeyEvent.VK_F3;
    public static final int KEY_F4 = KeyEvent.VK_F4;
    public static final int KEY_F5 = KeyEvent.VK_F5;
    public static final int KEY_F6 = KeyEvent.VK_F6;
    public static final int KEY_F7 = KeyEvent.VK_F7;
    public static final int KEY_F8 = KeyEvent.VK_F8;
    public static final int KEY_F9 = KeyEvent.VK_F9;
    public static final int KEY_F10 = KeyEvent.VK_F10;
    public static final int KEY_F11 = KeyEvent.VK_F11;
    public static final int KEY_F12 = KeyEvent.VK_F12;


    public static final int KEY_CTRL_F1 = KeyEvent.VK_F1 + KEY_CONTROL;
    public static final int KEY_CTRL_F2 = KeyEvent.VK_F2 + KEY_CONTROL;
    public static final int KEY_CTRL_F3 = KeyEvent.VK_F3 + KEY_CONTROL;
    public static final int KEY_CTRL_F4 = KeyEvent.VK_F4 + KEY_CONTROL;
    public static final int KEY_CTRL_F5 = KeyEvent.VK_F5 + KEY_CONTROL;


//    public static final int KEY_F13 = KEY_F12 + 1;
//    public static final int KEY_F14 = KEY_F13 + 1;
//    public static final int KEY_F15 = KEY_F14 + 1;
//    public static final int KEY_F16 = KEY_F15 + 1;
//    public static final int KEY_F17 = KEY_F16 + 1;
//    public static final int KEY_F18 = KEY_F17 + 1;
//    public static final int KEY_F19 = KEY_F18 + 1;
//    public static final int KEY_F20 = KEY_F19 + 1;
//    public static final int KEY_F21 = KEY_F20 + 1;
//    public static final int KEY_F22 = KEY_F21 + 1;
//    public static final int KEY_F23 = KEY_F22 + 1;
//    public static final int KEY_F24 = KEY_F23 + 1;


    public static boolean isKeyForAction(int keyCode) {
        return (keyCode >= KEY_F1 && keyCode <= KEY_F12)||
                (keyCode >= KEY_CTRL_F1 && keyCode <= KEY_CTRL_F5)||
                (keyCode == KEY_ESC) || (keyCode == KEY_ENTER);
    }

    public static boolean isKeyForAction(KeyEvent e) {
        int keyCode = e.getKeyCode();
        return isKeyForAction(keyCode);
    }


    public static boolean isKeyNotCleanAction(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("Key: " + keyCode);
        return isKeyForAction(keyCode) ||
                (keyCode >= KEY_LEFT && keyCode <= KEY_DOWN) || esCaracter(keyCode) || esNumerico(keyCode) || esTecladoNumerico(keyCode) ||
                (keyCode == KEY_CONTROL) || (keyCode == KeyEvent.VK_SHIFT) || e.isAltDown() ;
    }

    static private boolean esCaracter(int keyCode){
        return (keyCode >= 65 && keyCode <= 90) || keyCode == 32;
    }

    static private boolean esNumerico(int keyCode){
        return keyCode >= 48 && keyCode <= 57;
    }

    static private boolean esTecladoNumerico(int keyCode){
        return keyCode >= 96 && keyCode <= 105;
    }



    public static boolean isKeyForGoingToMainView(KeyEvent e) {
        int keyCode = e.getKeyCode();
        return (keyCode == KEY_TAB) && e.isControlDown();
    }

    public static boolean isKeyForSortingGrids(KeyEvent e) {
        int keyCode = e.getKeyCode();
        return keyCode >= KEY_1 && keyCode <= KEY_9 &&
                e.isControlDown() && e.getComponent() instanceof JTable;
    }

    public static boolean isKeyForShowingAdditionalActions(KeyEvent e) {
        return e.getKeyCode() == KEY_CONTROL && e.isControlDown();
    }


    public static String getNameForFKey(int keyCode) {
        if (keyCode == KEY_ESC) {
            return "Esc";
        }
        String name ="";
        if (keyCode >= ActionDialog.KEY_CTRL_F1){
            name = "Ctr+";
            keyCode -= ActionDialog.KEY_CONTROL;
        }
        return name+ "F" + (keyCode + 1 - KEY_F1);
    }
}
