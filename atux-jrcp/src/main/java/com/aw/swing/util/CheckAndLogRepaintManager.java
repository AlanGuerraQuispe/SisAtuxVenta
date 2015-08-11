package com.aw.swing.util;

/**
 * User: gmc
 * Date: 26/06/2009
 */
public class CheckAndLogRepaintManager extends CheckingRepaintManager {
    protected void indicate(EDTRuleViolation violation) throws
            EDTRuleViolation {
        violation.printStackTrace();
    }
}
