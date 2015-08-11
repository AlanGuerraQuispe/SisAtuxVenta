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

/**
 * User: gmc
 * Date: 02-nov-2007
 *
 * Representa el esquema de vista de una vista.
 * Generalmente una vista esta formada por:
 * 
 */
public interface ViewLayout {
    public void showErrorMessage(String message);
    public void hideErrorMessage();
    public void showAuditableFields(Object audit) ;

}