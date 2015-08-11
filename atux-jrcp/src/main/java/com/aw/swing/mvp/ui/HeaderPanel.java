package com.aw.swing.mvp.ui;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * User: Juan Carlos Vergara
 * Date: 05/06/2009
 */
public class HeaderPanel extends JPanel {
    protected final Log logger = LogFactory.getLog(getClass());

    JButton btnClose;
    private ImageIcon image;
    private ImageIcon imageOver;
    private ImageIcon imagePressed;

    JPanel headerContent;

    public HeaderPanel(String title, String description) {
        this.setPreferredSize(new Dimension(30, 0));
        this.setMinimumSize(new Dimension(30, 0));
        this.setLayout(new BorderLayout());
        headerContent = new TitleGradientPanel();
//        JPanel headerContent = new JPanel();
        headerContent.setLayout(new BorderLayout());
        this.add(headerContent, BorderLayout.CENTER);
        headerContent.add(new FormTitle(title, description, "/images/window-icon.png"), BorderLayout.CENTER);

        logger.warn("'" + getClass().getResource("/images/title-close-x.png") + "'");

        image = new ImageIcon(getClass().getResource("/images/title-close-x.png"));
        imagePressed = new ImageIcon(getClass().getResource("/images/title-close-pressed.png"));
        imageOver = new ImageIcon(getClass().getResource("/images/title-close-over.png"));
//        JLabel formIcon = new FormIcon("/images/suminet.png");
//        headerContent.add(formIcon, BorderLayout.EAST);
    }

    public void createCloseButton(AbstractAction aa){
        btnClose = createButton(aa);
        headerContent.setBorder(new EmptyBorder(0,0,0,5));

        headerContent.add(btnClose, BorderLayout.EAST);
    }

    public void setCloseAction(AbstractAction aa){
        btnClose.setAction(aa);
    }

    public JButton createButton(AbstractAction aa){
//        AbstractAction aa = new AbstractAction() {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Esta es una accion...");
//            }
//        };
//        JButton button = new JButton(aa);
        JButton button = new JButton(aa);
//        JButton button = new JButton();
        button.setIcon(imageOver);
        button.setPressedIcon(imagePressed);
        button.setRolloverIcon(image);
//        button.setRolloverEnabled(true);
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(image.getIconWidth(),
                                              image.getIconHeight()));
        return button;
    }
}
