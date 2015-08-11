package com.aw.swing.report;

import com.aw.core.format.FillerFormat;

import javax.swing.*;
import java.util.List;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public class JCmpHelper {
    public static String getText(JComponent jComponent) {
        String text = null;
        if (jComponent instanceof JTextField)
            text = ((JTextField) jComponent).getText();
        else if (jComponent instanceof JLabel)
            text = ((JLabel) jComponent).getText();
        else if (jComponent instanceof JComboBox && ((JComboBox) jComponent).getSelectedItem() != null)
            text = ((JComboBox) jComponent).getSelectedItem().toString();
        return text;
    }

    public static int getAlignment(JComponent jCmp) {
        int alig = SwingConstants.LEFT;
        if (jCmp instanceof JLabel)
            alig = ((JLabel) jCmp).getHorizontalAlignment();
        else if (jCmp instanceof JTextField)
            alig = ((JTextField) jCmp).getHorizontalAlignment();
        else if (jCmp instanceof JComboBox)
            alig = SwingConstants.LEFT;

        int retVal = FillerFormat.ALG_LEFT;
        if (alig == SwingConstants.LEFT)
            retVal = FillerFormat.ALG_CENTER;
        else if (alig == SwingConstants.CENTER)
            retVal = FillerFormat.ALG_RIGHT;
        else if (alig == SwingConstants.RIGHT)
            retVal = FillerFormat.ALG_RIGHT;
        return retVal;
    }
    public static StringBuffer toStr(StringBuffer buf, List<JComponent> jCmps) {
        if (buf==null) buf = new StringBuffer();
        for (JComponent jCmp : jCmps) {
            buf.append("X:").append(jCmp.getX()).append(" ").append(getText(jCmp)).append(",");
        }
        return buf;
    }

}
