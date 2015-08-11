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

import com.aw.core.domain.AWBusinessException;
import com.aw.core.exception.AWException;
import com.aw.swing.context.SwingContext;
import com.aw.swing.exception.AWGridValidationException;
import com.aw.swing.exception.AWSwingException;
import com.aw.swing.exception.AWValidationException;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.ui.UIComponentManager;
import com.aw.swing.mvp.ui.grid.Cell;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.mvp.ui.msg.MsgDisplayer;
import com.aw.swing.mvp.ui.support.*;
import com.aw.swing.mvp.view.ViewLayout;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Date: Oct 1, 2007
 */
public class PainterMessages {
    protected static final Log logger = LogFactory.getLog(PainterMessages.class);
    public static List oldComponents = new ArrayList();
    public static int maxMessagesInPanel = 2;

    public static void paintException(Throwable exception) {
        paintException(exception, true);
    }


    public static void paintException(JComponent jComponent) {
        if (jComponent instanceof JComboBox) {
            LookAndFeelManager.assignRequiredPainter((JComboBox) jComponent);
        } else {
            jComponent.setBackground(ColorInfoProvider.COLOR_ERROR);
        }

//        jComponent.setBorder(new LineBorder(ColorInfoProvider.COLOR_BORDER_ERROR));
    }

    /**
     * @Link AWSwingException,AWValidationException,AWBusinessException
     */

    /**
     * @Link AWSwingException,AWValidationException,AWBusinessException
     */

    public static void paintExceptionWithoutRequestingFocus(AWValidationException exception) {
        ExceptionPainter exceptionPainter = getExceptionPainter(exception);
        oldComponents = exceptionPainter.getBindingComponents(exception);
        logger.debug("Component with validation fail <" + oldComponents.size() + "> ");
        ErrorPainter errorPainter = SwingContext.getErrorPainter();
        errorPainter.paintError(oldComponents);
    }


    public static void paintException(Throwable exception, boolean showError) {

//        cleanException();

        ExceptionPainter exceptionPainter = getExceptionPainter(exception);

        JComponent jComponentWithFocus = null;
        if (exception instanceof AWGridValidationException) {
            jComponentWithFocus = ((AWGridValidationException) exception).getJComponent();
        } else if (exception instanceof AWBusinessException) {
            AWBusinessException bExc = (AWBusinessException) exception;
            String visualCmpName = bExc.getVisualCmpName();
            if (StringUtils.hasText(visualCmpName)) {
                jComponentWithFocus = getComponentBasedOn(visualCmpName);
            }
        } else {
            oldComponents = exceptionPainter.getBindingComponents(exception);
            logger.debug("Component with validation fail <" + oldComponents.size() + "> ");
            boolean requestFocus = true;
            if (exception instanceof AWSwingException) {
                requestFocus = ((AWSwingException) exception).isRequestFocus();
            }
            if (requestFocus) {
                jComponentWithFocus = getComponentThatRecivedFocus(exception);
            }
            ErrorPainter errorPainter = SwingContext.getErrorPainter();
            errorPainter.paintError(oldComponents);
        }
        /**
         * Showing the component before showing the message
         */
        if (jComponentWithFocus != null) {
            new UIComponentManager().requestFocus(jComponentWithFocus);
        }

        if (showError) {
            List messagesError = exceptionPainter.getMessagesError(exception);
            String message = buildCustomMessage(messagesError);
            // todo verificar si funcions
//            if (messagesError.size() >= maxMessagesInPanel || message.length()>100) {
            MsgDisplayer.showErrorMessage(message);
//            } else {
//                SwingContext.getCurrentPst().getViewMgr().getLayout().showErrorMessage(message);
//            }
        }

        if (jComponentWithFocus != null) {
            new UIComponentManager().requestFocus(jComponentWithFocus);
            if (exception instanceof AWGridValidationException) {
                AWGridValidationException gridValidationException = (AWGridValidationException) exception;
                JTable jTable = (JTable) gridValidationException.getJComponent();
                Cell cell = gridValidationException.getCell();
                if (cell != null) {
                    new UIComponentManager().setSelectedRow(jTable, cell.getRow());
                    jTable.editCellAt(cell.getRow(), cell.getCol());
                }

            }
        }

    }

    private static JComponent getComponentBasedOn(String visualCmpName) {
        Presenter currentPst = getCurrentPst();
        if (visualCmpName.startsWith("grid")) {
            String gridIndexStr = visualCmpName.substring(4);
            int gridIndex = 0;
            if (StringUtils.hasText(gridIndexStr)) {
                gridIndex = Integer.parseInt(gridIndexStr);
            }
            GridProvider gdp = currentPst.getGridProvider(gridIndex);
            return gdp.getJTable();
        }
        BindingComponent bnd = currentPst.getBindingMgr().getBindingComponent(visualCmpName);
        if (bnd == null) {
            logger.warn("No exist component for:<" + visualCmpName + "> in <" + currentPst + ">");
            return null;
        }
        return bnd.getJComponent();
    }

    private static JComponent getComponentThatRecivedFocus(Throwable exception) {
        BindingComponent bindingComponent = null;
        if (exception instanceof AWException) {
            if (exception instanceof AWValidationException) {
                if (((AWValidationException) exception).getListObject() != null && ((AWValidationException) exception).getListObject().size() > 0) {
                    List exceptions = ((AWValidationException) exception).getListObject();
                    for (Iterator iterator = exceptions.iterator(); iterator.hasNext();) {
                        AWValidationException o = (AWValidationException) iterator.next();
                        if (o.getJComponent() != null) {
                            bindingComponent = (BindingComponent) (o.getJComponent()).getClientProperty(BindingComponent.ATTR_BND);
                            if (!bindingComponent.isUiReadOnly()) {
                                return bindingComponent.getJComponent();
                            }
                        }
                    }
                }
            }
            if ((exception instanceof AWSwingException) &&
                    ((AWSwingException) exception).getJComponent() != null) {
                JComponent jComponent = ((AWSwingException) exception).getJComponent();
                bindingComponent = (BindingComponent) jComponent.getClientProperty(BindingComponent.ATTR_BND);
                if (!bindingComponent.isUiReadOnly()) {
                    return bindingComponent.getJComponent();
                }
            }

        }

        if (oldComponents.size() > 0) {
            for (Iterator iterator = oldComponents.iterator(); iterator.hasNext();) {
                bindingComponent = (BindingComponent) iterator.next();
                if (!bindingComponent.isUiReadOnly()) {
                    return bindingComponent.getJComponent();
                }
            }
        }

        return null;
    }


    private static String buildCustomMessage(List messages) {

        StringBuffer sb = new StringBuffer();
        for (Iterator iterator = messages.iterator(); iterator.hasNext();) {
            Object obj = iterator.next();
            if (obj == null)
                continue;
            if (obj instanceof String) {
                sb.append(MsgDisplayer.getMessage((String) obj, null));
                sb.append(" ");
            } else if (obj instanceof Message) {
                Message message = (Message) obj;
                sb.append(MsgDisplayer.getMessage(message.getMessage(), message.getArgs()));
                sb.append(" ");
            }
            sb.append("\n");

        }
        return sb.toString();
    }

    public static void cleanException() {
        ViewLayout layout = getCurrentPst().getViewMgr().getLayout();
        if (layout != null) {
            layout.hideErrorMessage();
        }
        ErrorPainter errorPainter = SwingContext.getErrorPainter();
        errorPainter.clearError(oldComponents);
    }


    public static void cleanException(JComponent jComponent) {
        Presenter pst = getCurrentPst();
        if (pst == null) {
            return;
        }
        ViewLayout layout = pst.getViewMgr().getLayout();
        if (layout != null) {
            layout.hideErrorMessage();
        }

        ErrorPainter errorPainter = SwingContext.getErrorPainter();
        if (errorPainter != null) {
            errorPainter.clearError(jComponent);
        }

    }

    private static Presenter getCurrentPst() {
        return AWWindowsManager.instance().getCurrentPst();
    }

    private static ExceptionPainter getExceptionPainter(Throwable exception) {
        ExceptionPainter exceptionPainter = null;
        if (exception instanceof AWValidationException) {
            exceptionPainter = new ValidationExceptionPainter();
        } else if (exception instanceof AWBusinessException) {
            exceptionPainter = new BusinessExceptionPainter();
        } else if (exception instanceof AWSwingException) {
            exceptionPainter = new SwingExceptionPainter();
        } else if (exception instanceof Throwable) {
            exceptionPainter = new ThrowableExceptionPainter();
        }
        return exceptionPainter;
    }

}
