package com.aw.swing.mvp.binding.component.support;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.net.URL;

public class JButtonLinkA extends JButton {
  private static final String uiString = "LinkButtonUI";

  public static final int ALWAYS_UNDERLINE = 0;

  public static final int HOVER_UNDERLINE = 1;

  public static final int NEVER_UNDERLINE = 2;

  public static final int SYSTEM_DEFAULT = 3;

  private int linkBehavior;

  private Color linkColor;

  private Color colorPressed;

  private Color visitedLinkColor;

  private Color disabledLinkColor;

  private URL buttonURL;

  private Action defaultAction;

  private boolean isLinkVisited;

  public static void main(String[] a) {
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new GridLayout(0,2));
    f.getContentPane().add(new JButtonLinkA("www.java2s.com"));
    f.getContentPane().add(new JButtonLinkA("www.java2s.com/ExampleCode/CatalogExampleCode.htm"));
    f.setSize(600, 200);
    f.setVisible(true);
  }

  public JButtonLinkA() {
    this(null, null, null);
  }

  public JButtonLinkA(Action action) {
    this();
    setAction(action);
  }

  public JButtonLinkA(Icon icon) {
    this(null, icon, null);
  }

  public JButtonLinkA(String s) {
    this(s, null, null);
  }

  public JButtonLinkA(URL url) {
    this(null, null, url);
  }

  public JButtonLinkA(String s, URL url) {
    this(s, null, url);
  }

  public JButtonLinkA(Icon icon, URL url) {
    this(null, icon, url);
  }

  public JButtonLinkA(String text, Icon icon, URL url) {
    super(text, icon);
    linkBehavior = SYSTEM_DEFAULT;
    linkColor = Color.blue;
    colorPressed = Color.red;
    visitedLinkColor = new Color(128, 0, 128);
    if (text == null && url != null)
      setText(url.toExternalForm());
    setLinkURL(url);
    setCursor(Cursor.getPredefinedCursor(12));
    setBorderPainted(false);
    setContentAreaFilled(false);
    setRolloverEnabled(true);
    addActionListener(defaultAction);
  }

  public void updateUI() {
    setUI(BasicLinkButtonUI.createUI(this));
  }

  private void setDefault() {
    UIManager.getDefaults().put("LinkButtonUI", "BasicLinkButtonUI");
  }

  public String getUIClassID() {
    return "LinkButtonUI";
  }

  protected void setupToolTipText() {
    String tip = null;
    if (buttonURL != null)
      tip = buttonURL.toExternalForm();
    setToolTipText(tip);
  }

  public void setLinkBehavior(int bnew) {
    checkLinkBehaviour(bnew);
    int old = linkBehavior;
    linkBehavior = bnew;
    firePropertyChange("linkBehavior", old, bnew);
    repaint();
  }

  private void checkLinkBehaviour(int beha) {
    if (beha != ALWAYS_UNDERLINE && beha != HOVER_UNDERLINE
        && beha != NEVER_UNDERLINE && beha != SYSTEM_DEFAULT)
      throw new IllegalArgumentException("Not a legal LinkBehavior");
    else
      return;
  }

  public int getLinkBehavior() {
    return linkBehavior;
  }

  public void setLinkColor(Color color) {
    Color colorOld = linkColor;
    linkColor = color;
    firePropertyChange("linkColor", colorOld, color);
    repaint();
  }

  public Color getLinkColor() {
    return linkColor;
  }

  public void setActiveLinkColor(Color colorNew) {
    Color colorOld = colorPressed;
    colorPressed = colorNew;
    firePropertyChange("activeLinkColor", colorOld, colorNew);
    repaint();
  }

  public Color getActiveLinkColor() {
    return colorPressed;
  }

  public void setDisabledLinkColor(Color color) {
    Color colorOld = disabledLinkColor;
    disabledLinkColor = color;
    firePropertyChange("disabledLinkColor", colorOld, color);
    if (!isEnabled())
      repaint();
  }

  public Color getDisabledLinkColor() {
    return disabledLinkColor;
  }

  public void setVisitedLinkColor(Color colorNew) {
    Color colorOld = visitedLinkColor;
    visitedLinkColor = colorNew;
    firePropertyChange("visitedLinkColor", colorOld, colorNew);
    repaint();
  }

  public Color getVisitedLinkColor() {
    return visitedLinkColor;
  }

  public URL getLinkURL() {
    return buttonURL;
  }

  public void setLinkURL(URL url) {
    URL urlOld = buttonURL;
    buttonURL = url;
    setupToolTipText();
    firePropertyChange("linkURL", urlOld, url);
    revalidate();
    repaint();
  }

  public void setLinkVisited(boolean flagNew) {
    boolean flagOld = isLinkVisited;
    isLinkVisited = flagNew;
    firePropertyChange("linkVisited", flagOld, flagNew);
    repaint();
  }

  public boolean isLinkVisited() {
    return isLinkVisited;
  }

  public void setDefaultAction(Action actionNew) {
    Action actionOld = defaultAction;
    defaultAction = actionNew;
    firePropertyChange("defaultAction", actionOld, actionNew);
  }

  public Action getDefaultAction() {
    return defaultAction;
  }

  protected String paramString() {
    String str;
    if (linkBehavior == ALWAYS_UNDERLINE)
      str = "ALWAYS_UNDERLINE";
    else if (linkBehavior == HOVER_UNDERLINE)
      str = "HOVER_UNDERLINE";
    else if (linkBehavior == NEVER_UNDERLINE)
      str = "NEVER_UNDERLINE";
    else
      str = "SYSTEM_DEFAULT";
    String colorStr = linkColor == null ? "" : linkColor.toString();
    String colorPressStr = colorPressed == null ? "" : colorPressed
        .toString();
    String disabledLinkColorStr = disabledLinkColor == null ? ""
        : disabledLinkColor.toString();
    String visitedLinkColorStr = visitedLinkColor == null ? ""
        : visitedLinkColor.toString();
    String buttonURLStr = buttonURL == null ? "" : buttonURL.toString();
    String isLinkVisitedStr = isLinkVisited ? "true" : "false";
    return super.paramString() + ",linkBehavior=" + str + ",linkURL="
        + buttonURLStr + ",linkColor=" + colorStr + ",activeLinkColor="
        + colorPressStr + ",disabledLinkColor=" + disabledLinkColorStr
        + ",visitedLinkColor=" + visitedLinkColorStr
        + ",linkvisitedString=" + isLinkVisitedStr;
  }
}

class BasicLinkButtonUI extends MetalButtonUI {
  private static final BasicLinkButtonUI ui = new BasicLinkButtonUI();

  public BasicLinkButtonUI() {
  }

  public static ComponentUI createUI(JComponent jcomponent) {
    return ui;
  }

  protected void paintText(Graphics g, JComponent com, Rectangle rect,
      String s) {
    JButtonLinkA bn = (JButtonLinkA) com;
    ButtonModel bnModel = bn.getModel();
    Color color = bn.getForeground();
    Object obj = null;
    if (bnModel.isEnabled()) {
      if (bnModel.isPressed())
        bn.setForeground(bn.getActiveLinkColor());
      else if (bn.isLinkVisited())
        bn.setForeground(bn.getVisitedLinkColor());

      else
        bn.setForeground(bn.getLinkColor());
    } else {
      if (bn.getDisabledLinkColor() != null)
        bn.setForeground(bn.getDisabledLinkColor());
    }
    super.paintText(g, com, rect, s);
    int behaviour = bn.getLinkBehavior();
    boolean drawLine = false;
    if (behaviour == JButtonLinkA.HOVER_UNDERLINE) {
      if (bnModel.isRollover())
        drawLine = true;
    } else if (behaviour == JButtonLinkA.ALWAYS_UNDERLINE || behaviour == JButtonLinkA.SYSTEM_DEFAULT)
      drawLine = true;
    if (!drawLine)
      return;
    FontMetrics fm = g.getFontMetrics();
    int x = rect.x + getTextShiftOffset();
    int y = (rect.y + fm.getAscent() + fm.getDescent() + getTextShiftOffset()) - 1;
    if (bnModel.isEnabled()) {
      g.setColor(bn.getForeground());
      g.drawLine(x, y, (x + rect.width) - 1, y);
    } else {
      g.setColor(bn.getBackground().brighter());
      g.drawLine(x, y, (x + rect.width) - 1, y);
    }
  }
}