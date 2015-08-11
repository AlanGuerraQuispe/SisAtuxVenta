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

import com.aw.swing.mvp.binding.BindingComponent;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.Severity;
import com.jgoodies.validation.view.ValidationResultViewFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * Date: Aug 16, 2007
 */
public class ErrorBorderPainter extends ErrorPainter {

    public void paintError(List components) {
        clearError(components);
        for (Iterator iterator = components.iterator(); iterator.hasNext();) {
            BindingComponent bindingComponent = (BindingComponent) iterator.next();
            JLabel label = (JLabel) ((JComponent) bindingComponent.getJComponent()).getClientProperty(BindingComponent.ATTR_ICONO);
            if (label != null) {
                return;
            }
            logger.debug("creating icon for " + bindingComponent.getFieldName());
            JComponent feedbackComponent = createFeedbackComponent();
            if (feedbackComponent == null) {
                return;
            }
            Container container = bindingComponent.getJComponent().getParent();
            // GMC Todo  ver q pasa cuando no se envia null ->  Ver como manejar este caso para los diferentes layouts       
//            container.add(feedbackComponent, null);
            CellConstraints cc = new CellConstraints();
            container.add(feedbackComponent, cc.xy(1, 1));
            Point overlayPosition = getFeedbackComponentOrigin(feedbackComponent, bindingComponent.getJComponent());
            overlayPosition.translate(-3, +2);
            feedbackComponent.setLocation(overlayPosition);

            FormLayout layout = ((FormLayout) container.getLayout());
            CellConstraints cc1 = layout.getConstraints(bindingComponent.getJComponent());

            container.add(feedbackComponent, cc1.xy(cc1.gridX-1, cc1.gridY+1, CellConstraints.RIGHT,CellConstraints.BOTTOM));

            bindingComponent.getJComponent().putClientProperty(BindingComponent.ATTR_ICONO, feedbackComponent);
        }
    }

    public void clearError(JComponent jComponent) {
        Container container = jComponent.getParent();
        JLabel label = (JLabel) jComponent.getClientProperty(BindingComponent.ATTR_ICONO);
        if (label != null) {
            container.remove(label);
            container.repaint();
            jComponent.putClientProperty(BindingComponent.ATTR_ICONO, null);
        }
    }

    private Point getFeedbackComponentOrigin(
            JComponent feedbackComponent,
            Component contentComponent) {
        boolean isLTR = contentComponent.getComponentOrientation().isLeftToRight();
        int x = contentComponent.getX()
                + (isLTR ? 0 : contentComponent.getWidth() - 1)
                - feedbackComponent.getWidth() / 2;
        int y = contentComponent.getY()
                + contentComponent.getHeight()
                - feedbackComponent.getHeight();

        return new Point(x, y);
    }

    private JComponent createFeedbackComponent() {
        Icon icon = ValidationResultViewFactory.getSmallIcon(Severity.ERROR);
        JLabel label = new JLabel(icon);
        label.setToolTipText("Fail");
        label.setSize(label.getPreferredSize());
        return label;
    }


}
