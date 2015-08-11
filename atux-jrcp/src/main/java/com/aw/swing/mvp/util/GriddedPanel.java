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
package com.aw.swing.mvp.util;

/**
 *  Copyright 1999-2002 Matthew Robinson and Pavel Vorobiev. 
 *  All Rights Reserved. 
 * 
 *  =================================================== 
 *  This program contains code from the book "Swing" 
 *  2nd Edition by Matthew Robinson and Pavel Vorobiev 
 *  http://www.spindoczine.com/sbe 
 *  =================================================== 
 * 
 *  The above paragraph must be included in full, unmodified 
 *  and completely intact in the beginning of any source code 
 *  file that references, copies or uses (in any way, shape 
 *  or form) code contained in this file. 
 */

import com.aw.swing.g2d.LinearGradientTypeLoader;
import org.jdesktop.fuse.ResourceInjector;
import org.jdesktop.fuse.TypeLoaderFactory;
import org.jdesktop.fuse.swing.SwingModule;

import javax.swing.*;
import java.awt.*;

/**
* A simple helper class that provides various easy to understand methods
* that lays out the components using the GridBagLayout.
*
* @author James Tan
*/
public class GriddedPanel
{
    private JPanel panel;

    private GridBagConstraints constraints;
    // define some default constraints values
    private static final int C_HORZ = GridBagConstraints.HORIZONTAL;
    private static final int C_NONE = GridBagConstraints.NONE;
    private static final int C_WEST = GridBagConstraints.WEST;
    private static final int C_WIDTH = 1;
    private static final int C_HEIGHT = 1;

    protected static Dimension SHORT_FIELD = new Dimension(40, 20);
    protected static Dimension MEDIUM_FIELD = new Dimension(120, 20);
    protected static Dimension LONG_FIELD = new Dimension(240, 20);
    protected static Dimension HUGE_FIELD = new Dimension(240, 80);

    /**
    * Creates a grid bag layout pnlMain using a default insets constraints.
    */
    public GriddedPanel(JPanel panel)
    {
        this( panel, new Insets( 2, 2, 2, 2 ) );

        //this( new Insets( 2, 2, 2, 2 ) );
    }

    /**
    * Creates a grid bag layout pnlMain using the specified insets
    * constraints.
    */
    public GriddedPanel( JPanel panel, Insets insets )
    {
        this.panel = panel;
        panel.setLayout( new GridBagLayout() );
        // creates the constraints object and set the desired
        // default values
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = insets;
    }

    /**
    * Adds the component to the specified row and col.
    */
    public void addComponent( JComponent component, int row, int col )
    {
        addComponent( component, row, col, C_WIDTH,
                      C_HEIGHT, C_WEST, C_NONE );
    }

    /**
    * Adds the component to the specified row and col that spans across
    * a specified number of columns and rows.
    */
    public void addComponent( JComponent component, int row, int col,
                              int width, int height )
    {
       addComponent( component, row, col, width, height, C_WEST, C_NONE );
    }

    /**
    * Adds the component to the specified row and col that anchors at
    * the specified position.
    */
    public void addAnchoredComponent( JComponent component, int row,
                                      int col, int anchor )
    {
       addComponent( component, row, col, C_WIDTH, C_HEIGHT, anchor, C_NONE );
    }

    /**
    * Adds the component to the specified row and col that spans across
    * a specified number of columns and rows that anchors at the specified
    * position.
    */
    public void addAnchoredComponent( JComponent component, int row, int col,
                                      int width, int height, int anchor )
    {
       addComponent( component, row, col, width, height, anchor, C_NONE );
    }

    /**
    * Adds the component to the specified row and col filling the column
    * horizontally.
    */
    public void addFilledComponent( JComponent component, int row, int col )
    {
        addComponent( component, row, col, C_WIDTH, C_HEIGHT, C_WEST, C_HORZ );
    }

    /**
    * Adds the component to the specified row and col with the specified
    * filling direction.
    */
    public void addFilledComponent( JComponent component, int row, int col,
                                    int fill )
    {
       addComponent( component, row, col, C_WIDTH, C_HEIGHT, C_WEST, fill );
    }

    /**
    * Adds the component to the specified row and col that spans across
    * a specified number of columns and rows with the specified filling
    * direction.
    */
    public void addFilledComponent( JComponent component, int row, int col,
                                    int width, int height, int fill )
    {
       addComponent( component, row, col, width, height, C_WEST, fill );
    }
    
    /**
    * Adds the component to the specified row and col that spans across
    * a specified number of columns and rows with the specified filling
    * direction and an anchoring position.
    */
    public void addComponent( JComponent component, int row, int col,
                              int width, int height, int anchor, int fill )
    {
       // sets the constraints object
       constraints.gridx = col;
       constraints.gridy = row;
       constraints.gridwidth = width;
       constraints.gridheight = height;
       constraints.anchor = anchor;
       double weightx = 0.0;
       double weighty = 0.0;
       
       // only use the extra horizontal or vertical spaces if the component
       // spans more than one column or/and row.
       if( width > 1 )
       {
          weightx = 1.0;
       }
       if( height > 1 )
       {   
          weighty = 1.0;
       }

       switch( fill )
       {
           case GridBagConstraints.HORIZONTAL:
               constraints.weightx = weightx;
               constraints.weighty = 0.0;
               break;
           case GridBagConstraints.VERTICAL:
               constraints.weighty = weighty;
               constraints.weightx = 0.0;
               break;
           case GridBagConstraints.BOTH:
               constraints.weightx = weightx;
               constraints.weighty = weighty;
               break;
           case GridBagConstraints.NONE:
               constraints.weightx = 0.0;
               constraints.weighty = 0.0;
               break;
           default:
               break;
       }
       constraints.fill = fill;
       panel.add( component, constraints );
    }

    //TODO JCV REMOVE-DUP
    public void init(){
        ResourceInjector.addModule(new SwingModule());
        ResourceInjector.get().load(GriddedPanel.class, "/jrcp-uitheme.properties");
        TypeLoaderFactory.addTypeLoader(new LinearGradientTypeLoader());
    }
}
