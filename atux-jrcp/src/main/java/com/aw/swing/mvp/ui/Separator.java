/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.swing.mvp.ui;

import com.jgoodies.forms.layout.Sizes;
import com.jgoodies.forms.util.Utilities;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Juan Carlos Vergara
 * Date: 02-nov-2007
 * Time: 17:20:32
 * To change this template use File | Settings | File Templates.
 */
public class Separator extends JPanel {
    /**
     * The character used to indicate the mnemonic position for labels.
     */
    private static final char MNEMONIC_MARKER = '&';

    String separatorTitle = "<Assign Title>";

    /*public Separator() {
        this("<Assign Title>");
    }*/

    public Separator(String separatorTitle) {
        super(new TitledSeparatorLayout(!Utilities.isLafAqua()));

        this.separatorTitle = separatorTitle;

        JLabel title = createTitle(separatorTitle);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD , 11));

        int horizontalAlignment = title.getHorizontalAlignment();
        if ((horizontalAlignment != SwingConstants.LEFT)
                && (horizontalAlignment != SwingConstants.CENTER)
                && (horizontalAlignment != SwingConstants.RIGHT))
            throw new IllegalArgumentException("The label's horizontal alignment"
                    + " must be one of: LEFT, CENTER, RIGHT.");


        setOpaque(false);
        add(title);
        add(new JSeparator());
        if (horizontalAlignment == SwingConstants.CENTER) {
            add(new JSeparator());
        }
    }

    public String getSeparatorTitle() {
        return separatorTitle;
    }

    public void setSeparatorTitle(String separatorTitle) {
        this.separatorTitle = separatorTitle;
    }

    /**
     * A layout for the title label and separator(s) in titled separators.
     */
    private static final class TitledSeparatorLayout implements LayoutManager {

        private final boolean centerSeparators;

        /**
         * Constructs a TitledSeparatorLayout that either centers the separators
         * or aligns them along the font baseline of the title label.
         *
         * @param centerSeparators true to center, false to align along
         *                         the font baseline of the title label
         */
        private TitledSeparatorLayout(boolean centerSeparators) {
            this.centerSeparators = centerSeparators;
        }

        /**
         * Does nothing. This layout manager looks up the components
         * from the layout container and used the component's index
         * in the child array to identify the label and separators.
         *
         * @param name the string to be associated with the component
         * @param comp the component to be added
         */
        public void addLayoutComponent(String name, Component comp) {
            // Does nothing.
        }

        /**
         * Does nothing. This layout manager looks up the components
         * from the layout container and used the component's index
         * in the child array to identify the label and separators.
         *
         * @param comp the component to be removed
         */
        public void removeLayoutComponent(Component comp) {
            // Does nothing.
        }

        /**
         * Computes and returns the minimum size dimensions
         * for the specified container. Forwards this request
         * to <code>#preferredLayoutSize</code>.
         *
         * @param parent the component to be laid out
         * @return the container's minimum size.
         * @see #preferredLayoutSize(Container)
         */
        public Dimension minimumLayoutSize(Container parent) {
            return preferredLayoutSize(parent);
        }

        /**
         * Computes and returns the preferred size dimensions
         * for the specified container. Returns the title label's
         * preferred size.
         *
         * @param parent the component to be laid out
         * @return the container's preferred size.
         * @see #minimumLayoutSize(Container)
         */
        public Dimension preferredLayoutSize(Container parent) {
            Component label = getLabel(parent);
            Dimension labelSize = label.getPreferredSize();
            Insets insets = parent.getInsets();
            int width = labelSize.width + insets.left + insets.right;
            int height = labelSize.height + insets.top + insets.bottom;
            return new Dimension(width, height);
        }

        /**
         * Lays out the specified container.
         *
         * @param parent the container to be laid out
         */
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                // Look up the parent size and insets
                Dimension size = parent.getSize();
                Insets insets = parent.getInsets();
                int width = size.width - insets.left - insets.right;

                // Look up components and their sizes
                JLabel label = getLabel(parent);
                Dimension labelSize = label.getPreferredSize();
                int labelWidth = labelSize.width;
                int labelHeight = labelSize.height;
                Component separator1 = parent.getComponent(1);
                int separatorHeight = separator1.getPreferredSize().height;

                FontMetrics metrics = label.getFontMetrics(label.getFont());
                int ascent = metrics.getMaxAscent();
                int hGapDlu = centerSeparators ? 3 : 1;
                int hGap = Sizes.dialogUnitXAsPixel(hGapDlu, label);
                int vOffset = centerSeparators
                        ? 1 + (labelHeight - separatorHeight) / 2
                        : ascent - separatorHeight / 2;

                int alignment = label.getHorizontalAlignment();
                int y = insets.top;
                if (alignment == JLabel.LEFT) {
                    int x = insets.left;
                    label.setBounds(x, y, labelWidth, labelHeight);
                    x += labelWidth;
                    x += hGap;
                    int separatorWidth = size.width - insets.right - x;
                    separator1.setBounds(x, y + vOffset, separatorWidth, separatorHeight);
                } else if (alignment == JLabel.RIGHT) {
                    int x = insets.left + width - labelWidth;
                    label.setBounds(x, y, labelWidth, labelHeight);
                    x -= hGap;
                    x--;
                    int separatorWidth = x - insets.left;
                    separator1.setBounds(insets.left, y + vOffset, separatorWidth, separatorHeight);
                } else {
                    int xOffset = (width - labelWidth - 2 * hGap) / 2;
                    int x = insets.left;
                    separator1.setBounds(x, y + vOffset, xOffset - 1, separatorHeight);
                    x += xOffset;
                    x += hGap;
                    label.setBounds(x, y, labelWidth, labelHeight);
                    x += labelWidth;
                    x += hGap;
                    Component separator2 = parent.getComponent(2);
                    int separatorWidth = size.width - insets.right - x;
                    separator2.setBounds(x, y + vOffset, separatorWidth, separatorHeight);
                }
            }
        }

        private JLabel getLabel(Container parent) {
            return (JLabel) parent.getComponent(0);
        }
    }

    /**
     * Creates and returns a title label that uses the foreground color
     * and font of a <code>TitledBorder</code>.<p>
     * <p/>
     * <pre>
     * createTitle("Name");       // No mnemonic
     * createTitle("N&ame");      // Mnemonic is 'a'
     * createTitle("Save &as");   // Mnemonic is the second 'a'
     * createTitle("Look&&Feel"); // No mnemonic, text is Look&Feel
     * </pre>
     *
     * @param textWithMnemonic the label's text -
     *                         may contain an ampersand (<tt>&amp;</tt>) to mark a mnemonic
     * @return an emphasized title label
     */
    public JLabel createTitle(String textWithMnemonic) {
        JLabel label = new TitleLabel();
        setTextAndMnemonic(label, textWithMnemonic);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }

    /**
     * Sets the text of the given label and optionally a mnemonic.
     * The given text may contain an ampersand (<tt>&amp;</tt>)
     * to mark a mnemonic and its position. Such a marker indicates
     * that the character that follows the ampersand shall be the mnemonic.
     * If you want to use the ampersand itself duplicate it, for example
     * <tt>&quot;Look&amp;&amp;Feel&quot</tt>.
     *
     * @param label            the label that gets a mnemonic
     * @param textWithMnemonic the text with optional mnemonic marker
     */
    private static void setTextAndMnemonic(
            JLabel label,
            String textWithMnemonic) {
        int markerIndex = textWithMnemonic.indexOf(MNEMONIC_MARKER);
        // No marker at all
        if (markerIndex == -1) {
            label.setText(textWithMnemonic);
            return;
        }
        int mnemonicIndex = -1;
        int begin = 0;
        int end;
        int length = textWithMnemonic.length();
        int quotedMarkers = 0;
        StringBuffer buffer = new StringBuffer();
        do {
            // Check whether the next index has a mnemonic marker, too
            if ((markerIndex + 1 < length)
                    && (textWithMnemonic.charAt(markerIndex + 1) == MNEMONIC_MARKER)) {
                end = markerIndex + 1;
                quotedMarkers++;
            } else {
                end = markerIndex;
                if (mnemonicIndex == -1) {
                    mnemonicIndex = markerIndex - quotedMarkers;
                }
            }
            buffer.append(textWithMnemonic.substring(begin, end));
            begin = end + 1;
            markerIndex = begin < length
                    ? textWithMnemonic.indexOf(MNEMONIC_MARKER, begin)
                    : -1;
        } while (markerIndex != -1);
        buffer.append(textWithMnemonic.substring(begin));

        String text = buffer.toString();
        label.setText(text);
        if ((mnemonicIndex != -1) && (mnemonicIndex < text.length())) {
            label.setDisplayedMnemonic(
                    text.charAt(mnemonicIndex));
            label.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }


    /**
     * A label that uses the TitleBorder font and color.
     */
    private static final class TitleLabel extends JLabel {

        private TitleLabel() {
            // Just invoke the super constructor.
        }

        /**
         * TODO: For the Synth-based L&amp;f we should consider asking
         * a <code>TitledBorder</code> instance for its font and color using
         * <code>#getTitleFont</code> and <code>#getTitleColor</code> resp.
         */
        public void updateUI() {
            super.updateUI();
            Color foreground = getTitleColor();
            if (foreground != null) {
                setForeground(foreground);
            }
            setFont(getTitleFont());
        }

        private Color getTitleColor() {
            return UIManager.getColor("TitledBorder.titleColor");
        }

        /**
         * Looks up and returns the font used for title labels.
         * Since Mac Aqua uses an inappropriate titled border font,
         * we use a bold label font instead. Actually if the title
         * is used in a titled separator, the bold weight is questionable.
         * It seems that most native Aqua tools use a plain label in
         * titled separators.
         *
         * @return the font used for title labels
         */
        private Font getTitleFont() {
            return Utilities.isLafAqua()
                    ? UIManager.getFont("Label.font").deriveFont(Font.BOLD)
                    : UIManager.getFont("TitledBorder.font");
        }

    }
}
