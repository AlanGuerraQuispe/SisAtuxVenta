package com.aw.swing.mvp.binding.component.support.table.edition.support;

import java.awt.*;

/**
 * User: gmc
 * Date: 23/12/2009
 */
public class ScreenLocationUtils {

    public static Point getAdjustLocationToFitScreen(Dimension size,Point point) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Rectangle screenBounds = new Rectangle(toolkit.getScreenSize());

        // Use long variables to prevent overflow
        long pw = (long) point.x + (long) size.width;
        long ph = (long) point.y + (long) size.height;

        if( pw > screenBounds.x + screenBounds.width )
             point.x = screenBounds.x + screenBounds.width - size.width;
        if( ph > screenBounds.y + screenBounds.height-20){
            point.y = screenBounds.y + screenBounds.height - size.height;
            return new Point(point.getLocation().x, point.getLocation().y - 110);
        }else{
            return new Point(point.getLocation().x, point.getLocation().y + 20);
        }
    }

}
