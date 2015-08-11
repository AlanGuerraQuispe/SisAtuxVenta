package com.aw.swing.mvp.ui;

import com.aw.swing.mvp.focus.AWKeyboardFocusManagerDecorator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: Juan Carlos Vergara
 * Date: 03/05/2009
 */
public class SplitPanel extends JSplitPane {
    protected final Log logger = LogFactory.getLog(getClass());
    
    private boolean firstExpanded = false;

    private int lastDividerLocation = -1;

    private int defaultDividerLocation = -1;

    public SplitPanel(int orientation) {
        super(orientation);
        setOneTouchExpandable(false);
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, AWKeyboardFocusManagerDecorator.getForwardFocusTraversalKeys());

        BasicSplitPaneUI splitPaneUI = (BasicSplitPaneUI)(this.getUI());
        splitPaneUI.getDivider().addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2){
                    SplitPanel.this.maximizeOrRestore();
                }
            }
        });
    }

    public void maximizeOrRestore() {
        logger.debug("Processing SplitPane for position: " + getDividerLocation());
        if (getDividerLocation() == 1){
            logger.debug("Restoring split pane...");
            resetToPreferredSizes();
            setDividerLocation(defaultDividerLocation);
            return;
        }
        logger.debug("Maximizing split pane...: ");
        setDividerLocation(1);    
    }

    public void setExpanded(boolean expanded) {
        if (expanded != firstExpanded) {

            if (!firstExpanded) {
                lastDividerLocation = getDividerLocation();
            }

            this.firstExpanded = expanded;

            Animator animator = new Animator(500, new PropertySetter(this, "dividerLocation",
                    getDividerLocation(), (expanded ? getHeight() : lastDividerLocation)));

            animator.setStartDelay(10);
            animator.setAcceleration(.2f);
            animator.setDeceleration(.3f);
            animator.start();
        }
    }

    // workaround for bug where the animator blows up without it;
    // possibly a bug in reflection when method is in super class (?)
    public void setDividerLocation(int dividerLocation) {
        logger.debug("Divider location set to: " + dividerLocation);
        if (defaultDividerLocation == -1){
            defaultDividerLocation = dividerLocation;
        }
        super.setDividerLocation(dividerLocation);
    }
}
