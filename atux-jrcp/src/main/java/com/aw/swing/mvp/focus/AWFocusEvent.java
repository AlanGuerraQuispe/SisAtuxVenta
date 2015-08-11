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
package com.aw.swing.mvp.focus;

import java.awt.*;

/**
 * User: gmc
 * Date: 11-jun-2007
 */
public class AWFocusEvent {
    private boolean consumed;
    private Component from;
    private Component to;

    public AWFocusEvent(Component from, Component to) {
        this.from = from;
        this.to = to;
    }


    public void consume() {
        this.consumed = true;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public Component getFrom() {
        return from;
    }

    public void setFrom(Component from) {
        this.from = from;
    }

    public Component getTo() {
        return to;
    }

    public void setTo(Component to) {
        this.to = to;
    }
}
