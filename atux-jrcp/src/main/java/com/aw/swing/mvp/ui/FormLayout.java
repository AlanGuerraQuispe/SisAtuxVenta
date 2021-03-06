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

import com.aw.swing.g2d.LinearGradientTypeLoader;
import com.aw.swing.mvp.view.ViewLayout;
import com.jgoodies.forms.layout.CellConstraints;
import org.jdesktop.fuse.ResourceInjector;
import org.jdesktop.fuse.TypeLoaderFactory;
import org.jdesktop.fuse.swing.SwingModule;
import org.jdesktop.swingx.JXPanel;

import javax.swing.*;
import java.awt.*;

//import org.jvnet.substance.skin.SubstanceCremeLookAndFeel;

/**
 * User: Juan Carlos Vergara
 * Date: 02-nov-2007
 * Time: 18:46:54
 * This class define the form layout
 */
public class FormLayout implements ViewLayout {
    public JPanel mainPanel;
    private JPanel titlePanel;

    public JPanel contentPanel;
    private Separator sepCriterios;
    private Separator sepResultados;
    public JPanel resultsActionPanel;
    public FormTitle formTitle;
    public JPanel headerPanel;
    public JPanel windowTitlePanel;
    public JPanel errorPanel;
    private JLabel lblErrorMessage;

    String title;
    String description;

    public FormLayout() {
    }

    public FormLayout(String title, String description) {
        super();
        this.title = title;
        this.description = description;
        $$$setupUI$$$();
    }

    public void hideErrorMessage() {
        lblErrorMessage.setText("");
        errorPanel.setVisible(false);
    }

    @Override
    public void showAuditableFields(Object audit) {
        throw new UnsupportedOperationException();
    }

    public void showErrorMessage(String message) {
        lblErrorMessage.setText(message);
        errorPanel.setVisible(true);
        System.out.println("Mostrando error: " + message);
    }

    public void createErrorLabel() {
        lblErrorMessage = new JLabel();
        lblErrorMessage.setFont(new Font(lblErrorMessage.getFont().getName(), Font.BOLD, lblErrorMessage.getFont().getSize()));
        lblErrorMessage.setForeground(new Color(-3407821));
        lblErrorMessage.setHorizontalAlignment(2);
        lblErrorMessage.setHorizontalTextPosition(11);
        lblErrorMessage.setMinimumSize(new Dimension(128, 18));
        lblErrorMessage.setText("El campo es requerido");
        lblErrorMessage.setName("lblErrorMessage");
    }

    private void createUIComponents() {
        sepCriterios = new Separator("Criterios de B�squeda");
        sepResultados = new Separator("Resultados");
        titlePanel = new GradientPanel();
        windowTitlePanel = new JXPanel();
        ((JXPanel) windowTitlePanel).setAlpha(0.9f);
        errorPanel = new ErrorPanel();
        errorPanel.setVisible(false);
        formTitle = new FormTitle(title, description, "/images/window-icon.png");
        resultsActionPanel = new GradientPanel();
        //footerPanel = new GradientPanel();
        createErrorLabel();
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        //todo jcv mejorar la manera de inicializar el LAF en modo pruebas
        ResourceInjector.get().load(FormLayout.class, "/jrcp-uitheme.properties");
        TypeLoaderFactory.addTypeLoader(new LinearGradientTypeLoader());
        ResourceInjector.addModule(new SwingModule());

//        UIManager.setLookAndFeel(new SubstanceCremeLookAndFeel());

        JDialog dialog = new JDialog();
        Container contentPane = dialog.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new FormLayout().mainPanel, BorderLayout.CENTER);
        dialog.setSize(new Dimension(580, 440));
        dialog.setVisible(true);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setPreferredSize(new Dimension(600, 400));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null));
        contentPanel = new JPanel();
        contentPanel.setLayout(new com.jgoodies.forms.layout.FormLayout("fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:max(d;4px):noGrow"));
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        contentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10), null));
        headerPanel = new JPanel();
        headerPanel.setLayout(new com.jgoodies.forms.layout.FormLayout("fill:d:grow", "center:52px:noGrow,center:max(d;4px):noGrow"));
        headerPanel.setPreferredSize(new Dimension(0, 74));
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        titlePanel.setLayout(new BorderLayout(0, 0));
        titlePanel.setBackground(new Color(-1));
        titlePanel.setMinimumSize(new Dimension(0, 50));
        titlePanel.setPreferredSize(new Dimension(0, 60));
        CellConstraints cc = new CellConstraints();
        headerPanel.add(titlePanel, cc.xy(1, 1));
        titlePanel.add(formTitle, BorderLayout.CENTER);
        errorPanel.setLayout(new com.jgoodies.forms.layout.FormLayout("fill:d:grow", "center:d:grow"));
        errorPanel.setBackground(new Color(-52378));
        errorPanel.setPreferredSize(new Dimension(0, 30));
        headerPanel.add(errorPanel, cc.xy(1, 2));
        lblErrorMessage.setFont(new Font(lblErrorMessage.getFont().getName(), Font.BOLD, lblErrorMessage.getFont().getSize()));
        lblErrorMessage.setForeground(new Color(-1));
        lblErrorMessage.setHorizontalAlignment(2);
        lblErrorMessage.setHorizontalTextPosition(11);
        lblErrorMessage.setMinimumSize(new Dimension(128, 18));
        lblErrorMessage.setText("El campo es requerido");
        errorPanel.add(lblErrorMessage, cc.xy(1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
