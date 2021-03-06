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
 * Created by MATRIX-JAVA on 24/11/2014.
 */
public class FrmIncentivo {
    public JPanel pnlMain;
    public JPanel pnlForm;
    public JPanel pnlGrid;
    public JPanel pnlTitGrid;
    public JCheckBox chkSel;
    public JTextField txtCoLaboratorio;
    public JTextField txtDeLaboratorio;
    public JLabel lblLaboratorio;
    public JLabel lblTitGrid;
    public JPanel footerPanel;
    public JLabel lblF2;
    public JTable tblGrid;
    public JScrollPane pnlResult;
    public JLabel lblEsc;
    public JButton btnSearch;
    public JLabel lblF3;
    public JTextField txtBuscar;
    public JLabel lblProducto;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Incentivo", true);
        pnlGrid = new RoundedPanel(new BorderLayout(), 0);
        pnlGrid.setBorder(new RoundedBorder(0));
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
        pnlMain.setLayout(new FormLayout("fill:d:grow", "fill:38dlu:noGrow,top:4dlu:noGrow,fill:202dlu:noGrow,top:4dlu:noGrow,fill:16dlu:noGrow"));
        pnlMain.setMinimumSize(new Dimension(800, 430));
        pnlMain.setPreferredSize(new Dimension(1000, 430));
        pnlForm.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:d:noGrow,left:4dlu:noGrow,fill:100dlu:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:80dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow", "fill:12dlu:noGrow"));
        CellConstraints cc = new CellConstraints();
        pnlMain.add(pnlForm, cc.xy(1, 1));
        lblLaboratorio = new JLabel();
        lblLaboratorio.setText("Laboratorio");
        pnlForm.add(lblLaboratorio, cc.xy(1, 1));
        txtCoLaboratorio = new JTextField();
        pnlForm.add(txtCoLaboratorio, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtDeLaboratorio = new JTextField();
        pnlForm.add(txtDeLaboratorio, cc.xyw(5, 1, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        btnSearch = new JButton();
        btnSearch.setFocusable(false);
        btnSearch.setText("Buscar");
        btnSearch.setVisible(true);
        pnlForm.add(btnSearch, cc.xy(13, 1));
        lblProducto = new JLabel();
        lblProducto.setText("Producto");
        pnlForm.add(lblProducto, cc.xy(9, 1));
        txtBuscar = new JTextField();
        pnlForm.add(txtBuscar, cc.xy(11, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        pnlGrid.setLayout(new FormLayout("fill:d:grow", "fill:16dlu:noGrow,top:4dlu:noGrow,fill:d:noGrow"));
        pnlMain.add(pnlGrid, cc.xy(1, 3));
        pnlTitGrid = new JPanel();
        pnlTitGrid.setLayout(new FormLayout("fill:16dlu:noGrow,left:4dlu:noGrow,fill:d:grow", "fill:16dlu:noGrow"));
        pnlGrid.add(pnlTitGrid, cc.xy(1, 1));
        chkSel = new JCheckBox();
        chkSel.setText("");
        pnlTitGrid.add(chkSel, cc.xy(1, 1));
        lblTitGrid = new JLabel();
        lblTitGrid.setText("Lista de Productos");
        pnlTitGrid.add(lblTitGrid, cc.xy(3, 1));
        pnlResult = new JScrollPane();
        pnlGrid.add(pnlResult, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
        tblGrid = new JTable();
        pnlResult.setViewportView(tblGrid);
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,right:d:grow", "fill:16dlu:noGrow"));
        pnlMain.add(footerPanel, cc.xy(1, 5));
        lblF2 = new JLabel();
        lblF2.setText("F2 = Crear");
        footerPanel.add(lblF2, cc.xy(1, 1));
        lblEsc = new JLabel();
        lblEsc.setText("Esc = Salir");
        footerPanel.add(lblEsc, cc.xy(5, 1));
        lblF3 = new JLabel();
        lblF3.setText("F3 = Ver");
        footerPanel.add(lblF3, cc.xy(3, 1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }
}
