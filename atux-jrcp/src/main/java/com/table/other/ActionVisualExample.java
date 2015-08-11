package com.table.other;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * User: gmc
 * Date: 23/05/2009
 */
public class ActionVisualExample {
    String toolTipText;
    Point preferredLocation;
    JComponent insideComponent;
    final static ActionVisualExample SHARED_INSTANCE = new ActionVisualExample();
    transient Popup tipWindow;
    /**
     * The Window tip is being displayed in. This will be non-null if
     * the Window tip is in differs from that of insideComponent's Window.
     */
    private Window window;
    JToolTip tip;

    private Rectangle popupRect = null;
    private Rectangle popupFrameRect = null;

    boolean enabled = true;
    private boolean tipShowing = false;

    private KeyStroke postTip, hideTip;
    private Action postTipAction, hideTipAction;

    // PENDING(ges)
    protected boolean lightWeightPopupEnabled = true;
    protected boolean heavyWeightPopupEnabled = false;

    ActionVisualExample() {
        // create accessibility actions
        postTip = KeyStroke.getKeyStroke(KeyEvent.VK_F1, Event.CTRL_MASK);
//        postTipAction = new Actions(Actions.SHOW);
        hideTip = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
//        hideTipAction = new Actions(Actions.HIDE);

    }

    /**
     * Enables or disables the tooltip.
     *
     * @param flag true to enable the tip, false otherwise
     */
    public void setEnabled(boolean flag) {
        enabled = flag;
        if (!flag) {
            hideTipWindow();
        }
    }

    /**
     * Returns true if this object is enabled.
     *
     * @return true if this object is enabled, false otherwise
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * When displaying the <code>JToolTip</code>, the
     * <code>ToolTipManager</code> chooses to use a lightweight
     * <code>JPanel</code> if it fits. This method allows you to
     * disable this feature. You have to do disable it if your
     * application mixes light weight and heavy weights components.
     *
     * @param aFlag true if a lightweight panel is desired, false otherwise
     */
    public void setLightWeightPopupEnabled(boolean aFlag) {
        lightWeightPopupEnabled = aFlag;
    }

    /**
     * Returns true if lightweight (all-Java) <code>Tooltips</code>
     * are in use, or false if heavyweight (native peer)
     * <code>Tooltips</code> are being used.
     *
     * @return true if lightweight <code>ToolTips</code> are in use
     */
    public boolean isLightWeightPopupEnabled() {
        return lightWeightPopupEnabled;
    }

    String msg = "";
    public void showTipWindow() {
//        if (insideComponent == null || !insideComponent.isShowing())
//            return;
        String mode = UIManager.getString("ToolTipManager.enableToolTipMode");
        if ("activeApplication".equals(mode)) {
            KeyboardFocusManager kfm =
                    KeyboardFocusManager.getCurrentKeyboardFocusManager();
            if (kfm.getFocusedWindow() == null) {
                return;
            }
        }
        if (enabled) {
            Dimension size;
            Point screenLocation = insideComponent.getLocationOnScreen();
            Point location = new Point();
            GraphicsConfiguration gc;
            gc = insideComponent.getGraphicsConfiguration();
            Rectangle sBounds = gc.getBounds();
            Insets screenInsets = Toolkit.getDefaultToolkit()
                    .getScreenInsets(gc);
            // Take into account screen insets, decrease viewport
            sBounds.x += screenInsets.left;
            sBounds.y += screenInsets.top;
            sBounds.width -= (screenInsets.left + screenInsets.right);
            sBounds.height -= (screenInsets.top + screenInsets.bottom);
//            boolean leftToRight= SwingUtilities.isLeftToRight(insideComponent);
            boolean leftToRight = true;

            // Just to be paranoid
            hideTipWindow();

            tip = insideComponent.createToolTip();
            tip.setTipText(msg);
            size = tip.getPreferredSize();

            if (preferredLocation != null) {
                location.x = screenLocation.x + preferredLocation.x;
                location.y = screenLocation.y + preferredLocation.y;
                if (!leftToRight) {
                    location.x -= size.width;
                }
            } else {
                location.x = screenLocation.x ;
                location.y = screenLocation.y + 20;
                if (!leftToRight) {
                    if (location.x - size.width >= 0) {
                        location.x -= size.width;
                    }
                }

            }

            // we do not adjust x/y when using awt.Window tips
            if (popupRect == null) {
                popupRect = new Rectangle();
            }
            popupRect.setBounds(location.x, location.y,
                    size.width, size.height);

            // Fit as much of the tooltip on screen as possible
            if (location.x < sBounds.x) {
                location.x = sBounds.x;
            } else if (location.x - sBounds.x + size.width > sBounds.width) {
                location.x = sBounds.x + Math.max(0, sBounds.width - size.width)
                        ;
            }
            if (location.y < sBounds.y) {
                location.y = sBounds.y;
            } else if (location.y - sBounds.y + size.height > sBounds.height) {
                location.y = sBounds.y + Math.max(0, sBounds.height - size.height);
            }

            PopupFactory popupFactory = PopupFactory.getSharedInstance();

            if (lightWeightPopupEnabled) {
                int y = getPopupFitHeight(popupRect, insideComponent);
                int x = getPopupFitWidth(popupRect, insideComponent);
//                if (x > 0 || y > 0) {
//                    popupFactory.setPopupType(PopupFactory.MEDIUM_WEIGHT_POPUP);
//                } else {
//                    popupFactory.setPopupType(PopupFactory.LIGHT_WEIGHT_POPUP);
//                }
            } else {
//                popupFactory.setPopupType(PopupFactory.MEDIUM_WEIGHT_POPUP);
            }
            tipWindow = popupFactory.getPopup(insideComponent, tip,
                    location.x,
                    location.y);
//            popupFactory.setPopupType(PopupFactory.LIGHT_WEIGHT_POPUP);

            tipWindow.show();

            Window componentWindow = SwingUtilities.windowForComponent(insideComponent);

            window = SwingUtilities.windowForComponent(tip);
            if (window != null && window != componentWindow) {
//                window.addMouseListener(this);
            } else {
                window = null;
            }
            tipShowing = true;
        }
    }

    void hideTipWindow() {
        if (tipWindow != null) {
            if (window != null) {
                window = null;
            }
            tipWindow.hide();
            tipWindow = null;
            tipShowing = false;
            tip = null;
        }
    }

    /**
     * Returns a shared <code>ToolTipManager</code> instance.
     *
     * @return a shared <code>ToolTipManager</code> object
     */
    public static ActionVisualExample sharedInstance() {
        return SHARED_INSTANCE;
    }

    // add keylistener here to trigger tip for access
    /**
     * Registers a component for tooltip management.
     * <p/>
     * This will register key bindings to show and hide the tooltip text
     * only if <code>component</code> has focus bindings. This is done
     * so that components that are not normally focus traversable, such
     * as <code>JLabel</code>, are not made focus traversable as a result
     * of invoking this method.
     *
     * @param component a <code>JComponent</code> object to add
     * @see JComponent#isFocusTraversable
     */
    public void registerComponent(JComponent component) {
//        if (shouldRegisterBindings(component)) {
            // register our accessibility keybindings for this component
            // this will apply globally across L&F
            // Post Tip: Ctrl+F1
            // Unpost Tip: Esc and Ctrl+F1
//            InputMap inputMap = component.getInputMap(JComponent.WHEN_FOCUSED);
//            ActionMap actionMap = component.getActionMap();
//
//            if (inputMap != null && actionMap != null) {
//                inputMap.put(postTip, "postTip");
//                inputMap.put(hideTip, "hideTip");
//                actionMap.put("postTip", postTipAction);
//                actionMap.put("hideTip", hideTipAction);
//            }
//        }
    }

    /**
     * Removes a component from tooltip control.
     *
     * @param component a <code>JComponent</code> object to remove
     */
    public void unregisterComponent(JComponent component) {
//        if (shouldRegisterBindings(component)) {
//            InputMap inputMap = component.getInputMap(JComponent.WHEN_FOCUSED);
//            ActionMap actionMap = component.getActionMap();
//
//            if (inputMap != null && actionMap != null) {
//                inputMap.remove(postTip);
//                inputMap.remove(hideTip);
//                actionMap.remove("postTip");
//                actionMap.remove("hideTip");
//            }
//        }
    }

    /**
     * Returns whether or not bindings should be registered on the given
     * <code>JComponent</code>. This is implemented to return true if the
     * tool tip manager has a binding in any one of the
     * <code>InputMaps</code> registered under the condition
     * <code>WHEN_FOCUSED</code>.
     * <p/>
     * This does not use <code>isFocusTraversable</code> as
     * some components may override <code>isFocusTraversable</code> and
     * base the return value on something other than bindings. For example,
     * <code>JButton</code> bases its return value on its enabled state.
     *
     * @param component the <code>JComponent</code> in question
     */
//    private boolean shouldRegisterBindings(JComponent component) {
//        InputMap inputMap = component.getInputMap(JComponent.WHEN_FOCUSED,false);
//        while (inputMap != null && inputMap.size() == 0) {
//            inputMap = inputMap.getParent();
//        }
//        return (inputMap != null);
//    }

    static Frame frameForComponent(Component component) {
        while (!(component instanceof Frame)) {
            component = component.getParent();
        }
        return (Frame) component;
    }


    // Returns: 0 no adjust
    //         -1 can't fit
    //         >0 adjust value by amount returned
    private int getPopupFitWidth(Rectangle popupRectInScreen, Component invoker) {
        if (invoker != null) {
            Container parent;
            for (parent = invoker.getParent(); parent != null; parent = parent.getParent()) {
                // fix internal frame size bug: 4139087 - 4159012
                if (parent instanceof JFrame || parent instanceof JDialog ||
                        parent instanceof JWindow) { // no check for awt.Frame since we use Heavy tips
                    return getWidthAdjust(parent.getBounds(), popupRectInScreen);
                } else if (parent instanceof JApplet || parent instanceof JInternalFrame) {
                    if (popupFrameRect == null) {
                        popupFrameRect = new Rectangle();
                    }
                    Point p = parent.getLocationOnScreen();
                    popupFrameRect.setBounds(p.x, p.y,
                            parent.getBounds().width,
                            parent.getBounds().height);
                    return getWidthAdjust(popupFrameRect, popupRectInScreen);
                }
            }
        }
        return 0;
    }

    // Returns:  0 no adjust
    //          >0 adjust by value return
    private int getPopupFitHeight(Rectangle popupRectInScreen, Component invoker) {
        if (invoker != null) {
            Container parent;
            for (parent = invoker.getParent(); parent != null; parent = parent.getParent()) {
                if (parent instanceof JFrame || parent instanceof JDialog ||
                        parent instanceof JWindow) {
                    return getHeightAdjust(parent.getBounds(), popupRectInScreen);
                } else if (parent instanceof JApplet || parent instanceof JInternalFrame) {
                    if (popupFrameRect == null) {
                        popupFrameRect = new Rectangle();
                    }
                    Point p = parent.getLocationOnScreen();
                    popupFrameRect.setBounds(p.x, p.y,
                            parent.getBounds().width,
                            parent.getBounds().height);
                    return getHeightAdjust(popupFrameRect, popupRectInScreen);
                }
            }
        }
        return 0;
    }

    private int getHeightAdjust(Rectangle a, Rectangle b) {
        if (b.y >= a.y && (b.y + b.height) <= (a.y + a.height))
            return 0;
        else
            return (((b.y + b.height) - (a.y + a.height)) + 5);
    }

    // Return the number of pixels over the edge we are extending.
    // If we are over the edge the ToolTipManager can adjust.
    // REMIND: what if the Tooltip is just too big to fit at all - we currently will just clip
    private int getWidthAdjust(Rectangle a, Rectangle b) {
        //    System.out.println("width b.x/b.width: " + b.x + "/" + b.width +
        //		       "a.x/a.width: " + a.x + "/" + a.width);
        if (b.x >= a.x && (b.x + b.width) <= (a.x + a.width)) {
            return 0;
        } else {
            return (((b.x + b.width) - (a.x + a.width)) + 5);
        }
    }


    //
    // Actions
    private void show(JComponent source) {
        if (tipWindow != null) { // showing we unshow
            hideTipWindow();
            insideComponent = null;
        } else {
            hideTipWindow(); // be safe
            insideComponent = source;
            if (insideComponent != null) {
                toolTipText = insideComponent.getToolTipText();
                preferredLocation = new Point(10, insideComponent.getHeight() +
                        10);  // manual set
                showTipWindow();
            }
        }
    }

    private void hide(JComponent source) {
        hideTipWindow();
        preferredLocation = null;
        insideComponent = null;
    }


//    private static class Actions extends UIAction {
//        private static String SHOW = "SHOW";
//        private static String HIDE = "HIDE";
//
//        Actions(String key) {
//            super(key);
//        }
//
//        public void actionPerformed(ActionEvent e) {
//            String key = getName();
//            JComponent source = (JComponent) e.getSource();
//            if (key == SHOW) {
//                ToolTipManager.SHARED_INSTANCE().show(source);
//            } else if (key == HIDE) {
//                ToolTipManager.SHARED_INSTANCE().hide(source);
//            }
//        }
//
//        public boolean isEnabled(Object sender) {
//            if (getName() == SHOW) {
//                return true;
//            }
//            return ToolTipManager.SHARED_INSTANCE().tipShowing;
//        }
//    }

    public void setInsideComponent(JComponent insideComponent) {
        this.insideComponent = insideComponent;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
