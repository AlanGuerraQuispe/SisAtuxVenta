package com.atux.desktop.precios;

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
public class FrmProveedorPrecio {
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
    public JPanel footerPanel;
    public JLabel lblEsc;
    public JTextField txtFeInicio;
    public JTextField txtFeFin;
    public JLabel lblFeInicio;
    public JLabel lblFeFin;
    public JTextField txtCoLista;
    public JTextField txtCoProveedor;
    public JTextField txtNoProveedor;
    public JLabel lblProveedor;
    public JButton btnPickCoProveedor;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Proveedor", true);
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
        pnlMain.setLayout(new FormLayout("fill:d:grow", "fill:70dlu:noGrow,top:4dlu:noGrow,fill:105dlu:grow,top:4dlu:noGrow,fill:16dlu:noGrow"));
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
        pnlForm.setLayout(new FormLayout("fill:70dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:10dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:120dlu:noGrow,left:4dlu:noGrow,fill:20dlu:noGrow", "fill:12dlu:noGrow,top:4dlu:noGrow,center:12dlu:noGrow"));
        pnlMain.add(pnlForm, cc.xy(1, 1));
        lblBuscar = new JLabel();
        lblBuscar.setText("Lista");
        pnlForm.add(lblBuscar, cc.xy(1, 1));
        txtCoLista = new JTextField();
        pnlForm.add(txtCoLista, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblFeInicio = new JLabel();
        lblFeInicio.setText("Fecha de Vigencia");
        pnlForm.add(lblFeInicio, cc.xy(1, 3));
        txtFeInicio = new JTextField();
        pnlForm.add(txtFeInicio, cc.xy(3, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblFeFin = new JLabel();
        lblFeFin.setHorizontalAlignment(0);
        lblFeFin.setText("al");
        pnlForm.add(lblFeFin, cc.xy(5, 3));
        txtFeFin = new JTextField();
        txtFeFin.setText("");
        pnlForm.add(txtFeFin, cc.xy(7, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtCoProveedor = new JTextField();
        pnlForm.add(txtCoProveedor, cc.xy(9, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtNoProveedor = new JTextField();
        pnlForm.add(txtNoProveedor, cc.xy(11, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblProveedor = new JLabel();
        lblProveedor.setText("Proveedor");
        pnlForm.add(lblProveedor, cc.xy(7, 1));
        btnPickCoProveedor = new JButton();
        btnPickCoProveedor.setIcon(new ImageIcon(getClass().getResource("/images/zoom.png")));
        btnPickCoProveedor.setText("");
        pnlForm.add(btnPickCoProveedor, cc.xy(13, 1));
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("right:max(d;4px):grow", "fill:16dlu:noGrow"));
        pnlMain.add(footerPanel, cc.xy(1, 5));
        lblEsc = new JLabel();
        lblEsc.setText("Esc = Salir");
        footerPanel.add(lblEsc, cc.xy(1, 1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }
}
