package com.aw.swing.mvp;

import com.aw.core.domain.model.AuditableImpl;
import com.aw.swing.mvp.ui.AuditablePanel;
import com.aw.swing.mvp.ui.ErrorPanel;
import com.aw.swing.mvp.ui.HeaderPanel;
import com.aw.swing.mvp.view.ViewLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * User: Juan Carlos Vergara
 * Date: 06/05/2009
 */
public class MainFormLayout implements ViewLayout {
    public JPanel mainPanel;
    public JPanel headerPanel;
    public JPanel auditInfo;
    public JPanel contentPanel;
    public JPanel toolbar;
    public ErrorPanel errorPanel;
    public JPanel errorContent;

    String title;
    String description;
    private JLabel lblErrorMessage;

    //L&F Attributes
//    Color mainPanelBkg = new Color(131, 172, 219);//Color.WHITE;
//    Color mainPanelBkg = new Color(34, 78, 136);//Color.WHITE;
    Color mainPanelBkg = new Color(79, 129, 189);//Color.WHITE;

    public MainFormLayout(String title, String description) {
        this.title = title;
        this.description = description;
        setupUI();
//        debugUI();
    }

    private void setupUI() {
        mainPanel = new JPanel();
        mainPanel.setBackground(this.mainPanelBkg);
        GridBagLayout gridbag = new GridBagLayout();
        mainPanel.setLayout(gridbag);
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

//        createHeader();
//        gridbag.addLayoutComponent(headerPanel, c);
//        mainPanel.add(headerPanel);
//        c.gridy++;

        createAuditInfo();
        gridbag.addLayoutComponent(auditInfo, c);
        mainPanel.add(auditInfo);
        c.gridy++;

        createToolbar();
        gridbag.addLayoutComponent(toolbar, c);
        mainPanel.add(toolbar);
        c.gridy++;

        createErrorPanel();
//        gridbag.addLayoutComponent(errorPanel, c);
//        headerPanel.add(errorPanel, BorderLayout.SOUTH);
//        c.gridy++;

        createContent();
        gridbag.addLayoutComponent(contentPanel, c);
        mainPanel.add(contentPanel);
        c.gridy++;

    }

    private void createErrorPanel() {
        errorPanel = new ErrorPanel();
    }

    public void hideErrorMessage() {
        errorPanel.hideErrorMessage();
    }

    public void showErrorMessage(String message) {
        errorPanel.showErrorMessage(message);
    }


    private void createToolbar() {
        toolbar = new JPanel();
        toolbar.setPreferredSize(new Dimension(30, 0));
        toolbar.setMinimumSize(new Dimension(30, 0));

//        ToolbarManager tb = new ToolbarManager();
//        //tb.buildToolbar();
//        toolbar.setLayout(new BorderLayout());
//        toolbar.add(tb.getComponent(), BorderLayout.CENTER);
    }

    private void createAuditInfo() {
        auditInfo = new AuditablePanel();
    }

    public void showAuditableFields(Object audit) {
        AuditableImpl auditImpl = new AuditableImpl(audit);
        ((AuditablePanel) auditInfo).showFields(auditImpl);
    }

    private void createHeader() {
        headerPanel = new HeaderPanel(title, description);
    }

    private void createContent() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
//        contentPanel.setPreferredSize(new Dimension(30, 280));
//        contentPanel.setMinimumSize(new Dimension(30, 200));
        contentPanel.setBorder(new EmptyBorder(2, 10, 10, 10));
    }

    public void debugUI() {
        //headerPanel.setBackground(new Color(-256));
        contentPanel.setBackground(new Color(0, 0, 0));
        auditInfo.setBackground(new Color(219, 229, 236));
        toolbar.setBackground(new Color(-256));
    }

}
