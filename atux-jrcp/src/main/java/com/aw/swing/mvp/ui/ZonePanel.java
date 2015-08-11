package com.aw.swing.mvp.ui;

import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.mvp.ui.painter.ColorInfoProvider;
import com.jgoodies.forms.layout.CellConstraints;
import org.jdesktop.swingx.JXPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * User: Juan Carlos Vergara
 * Date: 01/05/2009
 */
public class ZonePanel extends JXPanel {
    private static final int TITLE_HEIGHT = 26;
    JPanel zoneContent;
    JPanel titlePanel;
    private JLabel labelTitle;
    private JLabel labelRight;
    private JButton btnAction1;
    private JButton btnAction2;
    boolean useBorderLayoutInTitle = true;
    boolean useRedColor = false;


    public ZonePanel(String title, boolean useBorderLayoutInTitle) {
        setBorder(new LineBorder(LookAndFeelManager.COLOR_BKG_TITLE));
        this.setLayout(new BorderLayout());
        this.useBorderLayoutInTitle = useBorderLayoutInTitle;

        configureTitle(title);
        configureContent();

    }

    public ZonePanel(String title, boolean useBorderLayoutInTitle, boolean useRedColor) {
        setBorder(new LineBorder(LookAndFeelManager.COLOR_BKG_TITLE));
        this.setLayout(new BorderLayout());
        this.useBorderLayoutInTitle = useBorderLayoutInTitle;
        this.useRedColor = useRedColor;

        configureTitle(title);
        configureContent();

    }

    public ZonePanel(String title) {
        this(title, true);
    }

    private void configureContent() {
        zoneContent = new JPanel();
        zoneContent.setBorder(new EmptyBorder(5, 5, 5, 5));
        if (useRedColor) {
            zoneContent.setForeground(new Color(255, 255, 255));
            zoneContent.setBackground(new Color(199, 60, 57));
        } else {
            zoneContent.setBackground(new Color(223, 232, 246));
        }
        this.add(zoneContent, BorderLayout.CENTER);
    }

    private void configureTitle(String title) {
        titlePanel = new JPanel();
        LayoutManager layoutManager = new BorderLayout();
        if (!useBorderLayoutInTitle) {
            layoutManager = new com.jgoodies.forms.layout.FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:d:noGrow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:noGrow,left:5dlu:noGrow,fill:d:noGrow", "center:12dlu:noGrow");
        }
        titlePanel.setLayout(layoutManager);
        titlePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        titlePanel.setPreferredSize(new Dimension(0, TITLE_HEIGHT));
        titlePanel.setBackground(new Color(197, 217, 255));

        labelTitle = new JLabel(title);
        labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.BOLD, 14));
        labelTitle.setForeground(ColorInfoProvider.COLOR_TITLE_DEFAULT);

        if (useBorderLayoutInTitle) {
            titlePanel.add(labelTitle, BorderLayout.WEST);
            labelRight = new JLabel();
            labelRight.setFont(new Font(labelRight.getFont().getName(), Font.BOLD, 14));
            labelRight.setForeground(ColorInfoProvider.COLOR_DEFAULT);
            titlePanel.add(labelRight, BorderLayout.EAST);
        } else {
            CellConstraints cc = new CellConstraints();
            titlePanel.add(labelTitle, cc.xy(1, 1));
        }
        this.add(titlePanel, BorderLayout.NORTH);
    }

    @Override
    public void setLayout(LayoutManager mgr) {
        if (mgr instanceof com.jgoodies.forms.layout.FormLayout) {
            zoneContent.setLayout(mgr);
        } else {
            super.setLayout(mgr);
        }
    }

    @Override
    public void add(Component comp, Object constraints) {
        if (comp == zoneContent || comp == titlePanel) {
            super.add(comp, constraints);
            return;
        }

        if (useRedColor) {
            comp.setForeground(new Color(255, 255, 255));
            if(comp instanceof JTextField){
                comp.setBackground(new Color(199, 60, 57));

            }
        }

        zoneContent.add(comp, constraints);
    }


    public void setLabelTitle(String labelTitleStr) {
        this.labelTitle.setText(labelTitleStr);
    }

    public JLabel getLabelRight() {
        return labelRight;
    }

    public void setBtnAction1(JButton btnAction1) {
        this.btnAction1 = btnAction1;
        CellConstraints cc = new CellConstraints();
        titlePanel.add(btnAction1, cc.xy(7, 1));


    }

    public void setBtnAction2(JButton btnAction2) {
        this.btnAction2 = btnAction2;
        CellConstraints cc = new CellConstraints();
        titlePanel.add(btnAction2, cc.xy(9, 1));
    }
}