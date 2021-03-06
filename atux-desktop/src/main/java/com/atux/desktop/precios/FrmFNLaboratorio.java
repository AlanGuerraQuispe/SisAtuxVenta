package com.atux.desktop.precios;

import com.aw.swing.mvp.ui.GradientPanel;
import com.aw.swing.mvp.ui.RoundedBorder;
import com.aw.swing.mvp.ui.RoundedPanel;
import com.aw.swing.mvp.ui.ZonePanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by JAVA on 24/11/2014.
 */
public class FrmFNLaboratorio {
    public JPanel pnlMain;
    public JPanel pnlForm;
    public JLabel lblBuscar;
    public JTextField txtBuscar;
    public JButton btnSearch;
    public JPanel pnlGrid;
    public JTable tblGrid;
    public JPanel pnlTitGrid;
    public JCheckBox chkSel;
    public JLabel lblTitGrid;
    public JScrollPane pnlResult;
    public JLabel lblEnter;
    public JPanel footerPanel;
    private JLabel lblEsc;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Lista de Laboratorio", true);
        pnlGrid = new RoundedPanel(new BorderLayout(), 0);
        pnlGrid.setBorder(new RoundedBorder(0));
        pnlTitGrid = new GradientPanel();
        footerPanel = new GradientPanel();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
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
        pnlMain = new JPanel();
        pnlMain.setLayout(new FormLayout("fill:d:grow", "fill:38dlu:noGrow,top:4dlu:noGrow,fill:206dlu:noGrow,top:4dlu:noGrow,fill:16dlu:noGrow"));
        pnlMain.setMinimumSize(new Dimension(400, 430));
        pnlMain.setPreferredSize(new Dimension(500, 430));
        pnlForm.setLayout(new FormLayout("fill:50dlu:noGrow,left:4dlu:noGrow,fill:100dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow", "fill:12dlu:noGrow"));
        CellConstraints cc = new CellConstraints();
        pnlMain.add(pnlForm, cc.xy(1, 1));
        lblBuscar = new JLabel();
        lblBuscar.setText("Buscar ");
        pnlForm.add(lblBuscar, cc.xy(1, 1));
        txtBuscar = new JTextField();
        pnlForm.add(txtBuscar, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        btnSearch = new JButton();
        btnSearch.setText("Buscar");
        pnlForm.add(btnSearch, cc.xy(5, 1));
        pnlGrid.setLayout(new FormLayout("fill:d:grow", "center:max(d;4px):noGrow,top:4dlu:noGrow,fill:d:grow"));
        pnlGrid.setMinimumSize(new Dimension(400, 333));
        pnlGrid.setPreferredSize(new Dimension(500, 333));
        pnlMain.add(pnlGrid, cc.xy(1, 3));
        pnlTitGrid = new JPanel();
        pnlTitGrid.setLayout(new FormLayout("fill:16dlu:noGrow,left:4dlu:noGrow,fill:100dlu:noGrow", "fill:16dlu:noGrow"));
        pnlGrid.add(pnlTitGrid, cc.xy(1, 1));
        chkSel = new JCheckBox();
        chkSel.setText("");
        pnlTitGrid.add(chkSel, cc.xy(1, 1));
        lblTitGrid = new JLabel();
        lblTitGrid.setText("Lista de Laboratorios");
        pnlTitGrid.add(lblTitGrid, cc.xy(3, 1));
        pnlResult = new JScrollPane();
        pnlResult.setMinimumSize(new Dimension(400, 300));
        pnlResult.setPreferredSize(new Dimension(500, 300));
        pnlGrid.add(pnlResult, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
        tblGrid = new JTable();
        pnlResult.setViewportView(tblGrid);
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,right:d:grow", "fill:d:grow"));
        pnlMain.add(footerPanel, cc.xy(1, 5));
        lblEnter = new JLabel();
        lblEnter.setText("F2 = Seleccionar");
        footerPanel.add(lblEnter, cc.xy(1, 1));
        lblEsc = new JLabel();
        lblEsc.setText("Esc = Salir");
        footerPanel.add(lblEsc, cc.xy(3, 1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }
}
