package com.aw.swing.mvp.focus;

import com.aw.swing.mvp.action.key.AWKeyEventDispatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;

/**
 * User: gmc
 * Date: 17/06/2009
 */
public class AWKeyboardFocusManagerDecorator {
    static final Log LOG = LogFactory.getLog(AWKeyboardFocusManagerDecorator.class);
    public static void init() {
        LOG.info("configurar decorate");
        KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyboardFocusManager.addKeyEventDispatcher(new AWKeyEventDispatcher());
        keyboardFocusManager.setDefaultFocusTraversalPolicy(new AWFocusTraversalPolicy());
        keyboardFocusManager.addPropertyChangeListener(new AWFocusChangeListener());
        // Change the forward focus traversal keys for the application
    }

    public static void decorate() {
        LOG.info("configurar decorate");
        KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        // Change the forward focus traversal keys for the application
        keyboardFocusManager.getCurrentKeyboardFocusManager().setDefaultFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, getForwardFocusTraversalKeys());
    }

    public static void unDecorate() {
        LOG.info("liberar decorate");
        KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyboardFocusManager.setDefaultFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,new HashSet<AWTKeyStroke>());


    }

    public static Set getForwardFocusTraversalKeys() {
        KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        Set forwardFocusTraversalKeys = new HashSet(
                keyboardFocusManager.getCurrentKeyboardFocusManager().getDefaultFocusTraversalKeys(
                        KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        forwardFocusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0, false));
        return forwardFocusTraversalKeys;
    }
}
