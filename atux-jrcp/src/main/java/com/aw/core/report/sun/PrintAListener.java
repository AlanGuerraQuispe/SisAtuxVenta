package com.aw.core.report.sun; /** PrintAListener.java
  * Date: May 2002
  * Author: Rajesh Ramchandani
  *         Ck Prasad
  */

import javax.print.attribute.Attribute;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.event.PrintServiceAttributeEvent;
import javax.print.event.PrintServiceAttributeListener;

public class PrintAListener implements PrintServiceAttributeListener {

	public void attributeUpdate(PrintServiceAttributeEvent e){

                System.out.println("In attributeUpdate method");
		System.out.println("PS IS  " + e.getPrintService());
		PrintServiceAttributeSet psas = e.getAttributes();
		Attribute[] nattr = psas.toArray();
                System.out.println("New Attributes set:" + nattr.length);
                for (int i = 0; i < nattr.length; i++) {
                	System.out.println("Printer is " + nattr[i]);
                }

        }
}
	
