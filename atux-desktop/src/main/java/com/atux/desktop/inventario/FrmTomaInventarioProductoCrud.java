package com.atux.desktop.inventario;

import com.aw.swing.mvp.ui.ZonePanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by MATRIX-JAVA on 24/11/2014.
 */
public class FrmTomaInventarioProductoCrud {
    public JPanel pnlMain;
    public JTextField txtCoProducto;
    public JTextField txtDeProducto;
    public JLabel lblCoProducto;
    public JTextField txtUnidad;
    public JLabel lblDeProducto;
    public JLabel lblUnidad;
    public JTextField txtDeLaboratorio;
    public JLabel lblDeLaboratorio;
    public JTextField txtCaEntero;
    public JLabel lblCaEntero;
    public JPanel footerPanel;
    public JLabel lblF10;
    public JPanel pnlForm;
    public JLabel lblEsc;
    public JTextField txtCaFraccion;
    public JLabel lblCaFraccion;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Ingresar Cantidad", true);
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
        pnlMain.setLayout(new FormLayout("fill:d:grow", "fill:d:grow,top:4dlu:noGrow,fill:16dlu:noGrow"));
        pnlMain.setMinimumSize(new Dimension(450, 250));
        pnlMain.setPreferredSize(new Dimension(450, 250));
        pnlMain.setRequestFocusEnabled(true);
        pnlForm.setLayout(new FormLayout("fill:50dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:20dlu:noGrow", "fill:12dlu:noGrow,fill:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow"));
        pnlForm.setMinimumSize(new Dimension(400, 180));
        pnlForm.setPreferredSize(new Dimension(400, 180));
        CellConstraints cc = new CellConstraints();
        pnlMain.add(pnlForm, cc.xy(1, 1));
        lblCoProducto = new JLabel();
        lblCoProducto.setText("C�digo");
        pnlForm.add(lblCoProducto, cc.xy(1, 1));
        lblDeProducto = new JLabel();
        lblDeProducto.setText("Producto");
        pnlForm.add(lblDeProducto, cc.xyw(3, 1, 3));
        txtCoProducto = new JTextField();
        txtCoProducto.setText("");
        pnlForm.add(txtCoProducto, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtDeProducto = new JTextField();
        pnlForm.add(txtDeProducto, cc.xyw(3, 3, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblUnidad = new JLabel();
        lblUnidad.setText("Unidad");
        pnlForm.add(lblUnidad, cc.xy(7, 1));
        txtUnidad = new JTextField();
        pnlForm.add(txtUnidad, cc.xy(7, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblDeLaboratorio = new JLabel();
        lblDeLaboratorio.setText("Laboratorio");
        pnlForm.add(lblDeLaboratorio, cc.xy(1, 5));
        txtDeLaboratorio = new JTextField();
        pnlForm.add(txtDeLaboratorio, cc.xyw(1, 7, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JSeparator separator1 = new JSeparator();
        pnlForm.add(separator1, cc.xyw(1, 9, 7, CellConstraints.FILL, CellConstraints.FILL));
        lblCaEntero = new JLabel();
        lblCaEntero.setText("Cantidad ");
        pnlForm.add(lblCaEntero, cc.xy(1, 11));
        txtCaEntero = new JTextField();
        pnlForm.add(txtCaEntero, cc.xy(3, 11, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblCaFraccion = new JLabel();
        lblCaFraccion.setText("Fracci�n");
        pnlForm.add(lblCaFraccion, cc.xy(5, 11));
        txtCaFraccion = new JTextField();
        pnlForm.add(txtCaFraccion, cc.xy(7, 11, CellConstraints.FILL, CellConstraints.DEFAULT));
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,right:d:grow", "fill:16dlu:noGrow"));
        pnlMain.add(footerPanel, cc.xy(1, 3));
        lblF10 = new JLabel();
        lblF10.setText("F10 = Aceptar");
        footerPanel.add(lblF10, cc.xy(1, 1));
        lblEsc = new JLabel();
        lblEsc.setText("Esc = Cancelar");
        footerPanel.add(lblEsc, cc.xy(3, 1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }
}