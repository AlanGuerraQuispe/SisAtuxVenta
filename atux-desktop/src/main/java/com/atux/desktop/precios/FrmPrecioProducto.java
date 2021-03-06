package com.atux.desktop.precios;

import com.aw.swing.mvp.ui.GradientPanel;
import com.aw.swing.mvp.ui.ZonePanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by MATRIX-JAVA on 24/11/2014.
 */
public class FrmPrecioProducto {
    public JPanel pnlMain;
    public JTextField txtCoProducto;
    public JTextField txtDeProducto;
    public JTextField txtUnidad;
    public JTextField txtMoneda;
    public JTextField txtPrecioProveedor;
    public JTextField txtPorcentajeDescuento;
    //public JTextField txtVaImpuesto;
    public JTextField txtPrecioVentaPublico;
    public JTextField txtPrecioPromedio;
    public JTextField txtPrecioPVPFinal;
    public JTextField txtVaMargen;
    public JTextField txtPrecioPVP;
    public JTextField txtPrecioVenta;
    public JLabel lblPrecioPromedio;
    public JLabel lblPrecioPVP;
    public JLabel lblPrevioVenta;
    public JLabel lblPrecioPVPFinal;
    public JLabel lblVaMargen;
    public JLabel lblMoneda;
    public JLabel lblPorcentajeDescuento;
    public JLabel lblPrecioVentaPublico;
    public JLabel lblCoProducto;
    public JLabel lblDeProducto;
    public JLabel lblUnidad;
    public JPanel pnlForm;
    public JPanel footerPanel;
    public JTextField txtVaFactor;
    public JLabel lblPrecioProveedor;
    public JLabel lblF10;
    public JLabel lblEsc;
    public JPanel pnlGrid;
    public JTable tblGrid;
    public JPanel pnlTitGrid;
    public JScrollPane pnlResult;
    public JCheckBox chkSel;
    public JLabel lblTitGrid;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Modificar Descuento / Precio Venta", true);
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
        pnlMain.setLayout(new FormLayout("fill:d:noGrow,fill:d:grow,fill:d:noGrow", "fill:120dlu:noGrow,top:4dlu:noGrow,fill:100dlu:noGrow,top:4dlu:noGrow,fill:16dlu:noGrow"));
        pnlMain.setMinimumSize(new Dimension(600, 422));
        pnlMain.setPreferredSize(new Dimension(600, 430));
        pnlForm.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow", "fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow"));
        pnlForm.setMinimumSize(new Dimension(540, 200));
        pnlForm.setPreferredSize(new Dimension(540, 200));
        CellConstraints cc = new CellConstraints();
        pnlMain.add(pnlForm, cc.xyw(1, 1, 3));
        lblCoProducto = new JLabel();
        lblCoProducto.setText("C�digo");
        pnlForm.add(lblCoProducto, cc.xy(1, 1));
        lblDeProducto = new JLabel();
        lblDeProducto.setText("Producto");
        pnlForm.add(lblDeProducto, cc.xyw(3, 1, 3));
        txtCoProducto = new JTextField();
        pnlForm.add(txtCoProducto, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtDeProducto = new JTextField();
        pnlForm.add(txtDeProducto, cc.xyw(3, 3, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblMoneda = new JLabel();
        lblMoneda.setText("Moneda");
        pnlForm.add(lblMoneda, cc.xy(1, 5));
        txtMoneda = new JTextField();
        pnlForm.add(txtMoneda, cc.xy(1, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtPrecioProveedor = new JTextField();
        pnlForm.add(txtPrecioProveedor, cc.xy(3, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblPrecioPromedio = new JLabel();
        lblPrecioPromedio.setText("Precio Promedio");
        pnlForm.add(lblPrecioPromedio, cc.xy(1, 9));
        lblPrecioPVP = new JLabel();
        lblPrecioPVP.setText("PVP. S/ IGV");
        pnlForm.add(lblPrecioPVP, cc.xyw(3, 9, 3));
        lblPrevioVenta = new JLabel();
        lblPrevioVenta.setText("Precio Venta");
        pnlForm.add(lblPrevioVenta, cc.xy(7, 9));
        lblPrecioPVPFinal = new JLabel();
        lblPrecioPVPFinal.setText("PVP Final");
        pnlForm.add(lblPrecioPVPFinal, cc.xy(9, 9));
        lblVaMargen = new JLabel();
        lblVaMargen.setText("% Margen");
        pnlForm.add(lblVaMargen, cc.xy(11, 9));
        txtPrecioPromedio = new JTextField();
        pnlForm.add(txtPrecioPromedio, cc.xy(1, 11, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtPrecioPVP = new JTextField();
        pnlForm.add(txtPrecioPVP, cc.xyw(3, 11, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtPrecioVenta = new JTextField();
        pnlForm.add(txtPrecioVenta, cc.xy(7, 11, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtPrecioPVPFinal = new JTextField();
        pnlForm.add(txtPrecioPVPFinal, cc.xy(9, 11, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtVaMargen = new JTextField();
        pnlForm.add(txtVaMargen, cc.xy(11, 11, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblPrecioProveedor = new JLabel();
        lblPrecioProveedor.setText("Precio");
        pnlForm.add(lblPrecioProveedor, cc.xy(3, 5));
        lblUnidad = new JLabel();
        lblUnidad.setText("Unidad");
        pnlForm.add(lblUnidad, cc.xy(11, 1));
        txtUnidad = new JTextField();
        pnlForm.add(txtUnidad, cc.xy(11, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblPorcentajeDescuento = new JLabel();
        lblPorcentajeDescuento.setText("Dscto.");
        pnlForm.add(lblPorcentajeDescuento, cc.xy(5, 5));
        txtPorcentajeDescuento = new JTextField();
        pnlForm.add(txtPorcentajeDescuento, cc.xy(5, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        //lblImpuesto = new JLabel();
        //lblImpuesto.setText("% Imp ");
        //pnlForm.add(lblImpuesto, cc.xy(7, 5));
        //txtVaImpuesto = new JTextField();
        //txtVaImpuesto.setVisible(false);
        //pnlForm.add(txtVaImpuesto, cc.xy(7, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblPrecioVentaPublico = new JLabel();
        lblPrecioVentaPublico.setText("Precio Vta. Publico");
        pnlForm.add(lblPrecioVentaPublico, cc.xyw(9, 5, 3));
        txtPrecioVentaPublico = new JTextField();
        pnlForm.add(txtPrecioVentaPublico, cc.xy(9, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,right:d:grow", "fill:16dlu:noGrow"));
        pnlMain.add(footerPanel, cc.xy(2, 5));
        lblF10 = new JLabel();
        lblF10.setText("F10 = Aceptar");
        footerPanel.add(lblF10, cc.xy(1, 1));
        lblEsc = new JLabel();
        lblEsc.setText("Esc = Cancelar");
        footerPanel.add(lblEsc, cc.xy(3, 1));
        pnlGrid = new JPanel();
        pnlGrid.setLayout(new FormLayout("fill:d:grow", "fill:16dlu:noGrow,top:4dlu:noGrow,fill:d:grow"));
        pnlMain.add(pnlGrid, cc.xy(2, 3));
        pnlTitGrid = new JPanel();
        pnlTitGrid.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:d:grow", "center:d:grow"));
        pnlGrid.add(pnlTitGrid, cc.xy(1, 1));
        chkSel = new JCheckBox();
        chkSel.setText("");
        pnlTitGrid.add(chkSel, cc.xy(1, 1));
        lblTitGrid = new JLabel();
        lblTitGrid.setText("Ultimas Ordenes de Compra");
        pnlTitGrid.add(lblTitGrid, cc.xy(3, 1));
        pnlResult = new JScrollPane();
        pnlGrid.add(pnlResult, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
        tblGrid = new JTable();
        pnlResult.setViewportView(tblGrid);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }
}
