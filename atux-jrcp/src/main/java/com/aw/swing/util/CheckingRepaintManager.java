package com.aw.swing.util;

import javax.swing.*;
import java.awt.*;

/**
 * User: gmc
 * Date: 26/06/2009
 */

public class CheckingRepaintManager extends RepaintManager {

    public synchronized void addInvalidComponent(JComponent component) {
        checkEDTRule(component);
        super.addInvalidComponent(component);
    }

    public synchronized void addDirtyRegion(JComponent component,
                                            int x, int y, int w, int h) {
        checkEDTRule(component);
        super.addDirtyRegion(component, x, y, w, h);
    }

    protected void checkEDTRule(Component component) {
        if (violatesEDTRule(component)) {
            EDTRuleViolation violation = new EDTRuleViolation(component);
            StackTraceElement[] stackTrace = violation.getStackTrace();
            try {
                for (int e = stackTrace.length - 1; e >= 0; e--) {
                    if (isLiableToEDTRule(stackTrace[e])) {
                        StackTraceElement[] subStackTrace =
                                new StackTraceElement[stackTrace.length - e];

                        indicate(violation);
                        System.arraycopy(stackTrace, e, subStackTrace, 0,
                                subStackTrace.length);
                        violation.setStackTrace(subStackTrace);
                    }
                }
            } catch (Exception ex) {
// keep stackTrace
            }
            indicate(violation);
        }
    }

    protected boolean violatesEDTRule(Component component) {
        return !SwingUtilities.isEventDispatchThread() &&
                component.isShowing();
    }

    protected boolean isLiableToEDTRule(StackTraceElement element) throws Exception {
        return Component.class.isAssignableFrom(Class.forName(element.getClassName()));
    }

    protected void indicate(EDTRuleViolation violation)
            throws EDTRuleViolation {
        throw violation;
    }
}

