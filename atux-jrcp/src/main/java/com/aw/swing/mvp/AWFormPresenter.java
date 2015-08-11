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
package com.aw.swing.mvp;

/**
 * Class used to manage the communication between the viev and the model.
 * It is used in the simple page.
 *
 * @author gmateo
 *         11/10/2004
 */
public abstract class AWFormPresenter<E> extends Presenter<E> {
    /**
     * Indicate if the Simple Presenter will be used to edition or addition
     */
    protected String mode;


    protected AWFormPresenter() {
    }



}
