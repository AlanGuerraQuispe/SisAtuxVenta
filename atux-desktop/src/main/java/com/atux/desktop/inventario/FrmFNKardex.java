package com.atux.desktop.inventario;

import com.aw.swing.mvp.ui.GradientPanel;
import com.aw.swing.mvp.ui.RoundedBorder;
import com.aw.swing.mvp.ui.RoundedPanel;
import com.aw.swing.mvp.ui.ZonePanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by JAVA on 16/11/2014.
 */
public class FrmFNKardex {
    public JPanel pnlMain;
    public JPanel pnlForm;
    //    public JTextField txtBuscar;
    public JTable tblGrid;
    public JScrollPane pnlResult;
    public JPanel pnlGrid;
    public JPanel pnlTitGrid;
    public JCheckBox chkSel;
    public JLabel lblTitGrid;
    public JTextField txtBuscar;
    public JLabel lblBuscar;
    public JButton btnSearch;
    public JPanel footerPanel;
    public JLabel lblF2;
    public JLabel lblEsc;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Producto", true);
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
        pnlMain.setLayout(new FormLayout("fill:d:grow", "fill:d:grow,top:4dlu:noGrow,fill:210dlu:noGrow,top:4dlu:noGrow,fill:16dlu:noGrow"));
        pnlMain.setMinimumSize(new Dimension(900, 435));
        pnlMain.setPreferredSize(new Dimension(900, 435));
        pnlGrid.setLayout(new FormLayout("fill:d:grow", "fill:16dlu:noGrow,top:4dlu:noGrow,fill:d:grow"));
        pnlGrid.setPreferredSize(new Dimension(453, 465));
        CellConstraints cc = new CellConstraints();
        pnlMain.add(pnlGrid, cc.xy(1, 3));
        pnlTitGrid = new JPanel();
        pnlTitGrid.setLayout(new FormLayout("fill:16dlu:noGrow,left:4dlu:noGrow,fill:100dlu:noGrow", "fill:16dlu:noGrow"));
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
        tblGrid.setOpaque(true);
        pnlResult.setViewportView(tblGrid);
        pnlForm.setLayout(new FormLayout("fill:60dlu:noGrow,left:4dlu:noGrow,fill:100dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow", "fill:12dlu:noGrow"));
        pnlMain.add(pnlForm, cc.xy(1, 1));
        lblBuscar = new JLabel();
        lblBuscar.setText("Buscar Producto");
        pnlForm.add(lblBuscar, cc.xy(1, 1));
        txtBuscar = new JTextField();
        pnlForm.add(txtBuscar, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        btnSearch = new JButton();
        btnSearch.setText("Buscar");
        pnlForm.add(btnSearch, cc.xy(5, 1));
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,right:max(d;4px):grow", "fill:16dlu:noGrow"));
        pnlMain.add(footerPanel, cc.xy(1, 5));
        lblF2 = new JLabel();
        lblF2.setText("F2 = Kardex");
        footerPanel.add(lblF2, cc.xy(1, 1));
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
