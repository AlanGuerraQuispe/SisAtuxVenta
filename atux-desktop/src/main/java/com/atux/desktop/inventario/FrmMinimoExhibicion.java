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
 * Created by JAVA on 24/11/2014.
 */
public class FrmMinimoExhibicion {
    public JPanel pnlMain;
    public JPanel pnlGrid;
    public JPanel pnlForm;
    public JPanel pnlTitGrid;
    public JScrollPane pnlResult;
    public JPanel footerPanel;
    public JCheckBox chkSel;
    public JTextField txtBuscar;
    public JTable tblGrid;
    public JLabel lblTitGrid;
    public JLabel lblF2;
    public JButton btnSearch;
    public JLabel lblBuscar;
    public JLabel lblEsc;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Minimo en Exhibici�n", true);
        pnlGrid = new RoundedPanel(new BorderLayout(), 0);
        pnlGrid.setBorder(new RoundedBorder(0));

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
        pnlMain.setLayout(new FormLayout("fill:d:grow", "fill:38dlu:noGrow,top:4dlu:noGrow,fill:210dlu:noGrow,top:4dlu:noGrow,fill:16dlu:noGrow"));
        pnlMain.setMinimumSize(new Dimension(900, 440));
        pnlMain.setPreferredSize(new Dimension(900, 440));
        pnlGrid.setLayout(new FormLayout("fill:d:grow", "fill:16dlu:noGrow,top:4dlu:noGrow,fill:d:grow"));
        CellConstraints cc = new CellConstraints();
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
        footerPanel.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,right:d:grow", "fill:16dlu:noGrow"));
        pnlMain.add(footerPanel, cc.xy(1, 5));
        lblF2 = new JLabel();
        lblF2.setText("F2 = Modificar");
        footerPanel.add(lblF2, cc.xy(1, 1));
        lblEsc = new JLabel();
        lblEsc.setText("Esc = Salir");
        footerPanel.add(lblEsc, cc.xy(3, 1));
        pnlForm.setLayout(new FormLayout("fill:66dlu:noGrow,left:4dlu:noGrow,fill:100dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow", "fill:12dlu:noGrow"));
        pnlForm.setMinimumSize(new Dimension(336, 65));
        pnlForm.setPreferredSize(new Dimension(336, 65));
        pnlMain.add(pnlForm, cc.xy(1, 1));
        lblBuscar = new JLabel();
        lblBuscar.setText("Buscar Producto");
        pnlForm.add(lblBuscar, cc.xy(1, 1));
        txtBuscar = new JTextField();
        pnlForm.add(txtBuscar, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        btnSearch = new JButton();
        btnSearch.setText("Buscar");
        pnlForm.add(btnSearch, cc.xy(5, 1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }
}
