package com.aw.swing.util;

import java.awt.*;

/**
 * User: gmc
 * Date: 26/06/2009
 */
public class EDTRuleViolation extends RuntimeException
{
  /**
   * Comment for <code>serialVersionUID</code>
   */
  private static final long serialVersionUID = 3833460721496633652L;

  private Component component;

  public EDTRuleViolation(Component component)
  {
    this.component = component;
  }

  public Component getComponent()
  {
    return component;
  }
}
