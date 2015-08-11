package com.aw.swing.mvp.ui;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.*;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.ObjectView;
import java.awt.*;
import java.util.EventListener;

/**
 * User: Juan Carlos Vergara
 * Date: 01/05/2009
 */
public class HTMLPanel extends JEditorPane {
    /**
     * Callback interface for getting notification when components specified in the
     * <object> tag are created in the editor pane.
     */
    public static interface ComponentCreationListener extends EventListener {
        public void componentCreated(HTMLPanel htmlPanel, Component component);
    }

    private static HyperlinkHandler hyperlinkHandler;

    /**
     * Creates a new instance of HTMLPanel with a default content type of &quot;text/html&quot;
     */
    public HTMLPanel() {
        setContentType("text/html");
        setEditorKit(new ComponentEditorKit()); // brute force
        setEditable(false); // VERY IMPORTANT!
        if (hyperlinkHandler == null) {
            hyperlinkHandler = new HyperlinkHandler();
        }
        addHyperlinkListener(hyperlinkHandler);
    }

    public void addComponentCreationListener(HTMLPanel.ComponentCreationListener l) {
        listenerList.add(ComponentCreationListener.class, l);
    }

    public void removeComponentCreationListener(HTMLPanel.ComponentCreationListener l) {
        listenerList.remove(HTMLPanel.ComponentCreationListener.class, l);
    }

    private class ComponentEditorKit extends HTMLEditorKit {
        @Override
        public ViewFactory getViewFactory() {
            return new ComponentFactory();
        }
    }

    protected class ComponentFactory extends HTMLEditorKit.HTMLFactory {
        public ComponentFactory() {
            super();
        }

        @Override
        public View create(Element element) {
            AttributeSet attrs = element.getAttributes();
            Object elementName =
                    attrs.getAttribute(AbstractDocument.ElementNameAttribute);
            Object o = (elementName != null) ?
                    null : attrs.getAttribute(StyleConstants.NameAttribute);
            if (o instanceof HTML.Tag) {
                if (o == HTML.Tag.OBJECT) {
                    return new ComponentView(element);
                }
            }
            return super.create(element);
        }
    }

    protected class ComponentView extends ObjectView {
        public ComponentView(Element element) {
            super(element);
        }

        @Override
        protected Component createComponent() {
            final Component component = super.createComponent();

            Runnable notifier = new Runnable() {
                public void run() {
                    final ComponentCreationListener listeners[] =
                            HTMLPanel.this.listenerList.getListeners(ComponentCreationListener.class);
                    for (ComponentCreationListener l : listeners) {
                        l.componentCreated(HTMLPanel.this, component);
                    }
                }
            };
            // just in case document is being loaded in separate thread. (?)
            if (EventQueue.isDispatchThread()) {
                notifier.run();
            } else {
                EventQueue.invokeLater(notifier);
            }
            return component;
        }
    }

    // single instance of handler is shared for ALL DemoPanel instances
    public static class HyperlinkHandler implements HyperlinkListener {
        Cursor defaultCursor;

        public void hyperlinkUpdate(HyperlinkEvent event) {
            JEditorPane descriptionPane = (JEditorPane) event.getSource();
            HyperlinkEvent.EventType type = event.getEventType();
            if (type == HyperlinkEvent.EventType.ACTIVATED) {
                try {
                    //DemoUtilities.browse(event.getURL().toURI());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println(e);
                }

            } else if (type == HyperlinkEvent.EventType.ENTERED) {
                defaultCursor = descriptionPane.getCursor();
                descriptionPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            } else if (type == HyperlinkEvent.EventType.EXITED) {
                descriptionPane.setCursor(defaultCursor);
            }
        }
    }
}
