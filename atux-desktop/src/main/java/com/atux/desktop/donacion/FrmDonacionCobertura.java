package com.atux.desktop.donacion;

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
public class FrmDonacionCobertura {
    public JPanel pnlMain;
    public JPanel pnlForm;
    public JPanel pnlGrid;
    public JTextField txtBuscar;
    public JPanel footerPanel;
    public JLabel lblF2;
    public JLabel lblEsc;
    public JTextField txtFechaInicio;
    public JTextField txtFechaFin;
    public JLabel lblFeInicio;
    public JLabel lblFeFin;
    public JCheckBox chkEstado;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Cobertura", true);
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
        pnlMain.setLayout(new FormLayout("fill:d:grow", "fill:58dlu:noGrow,top:4dlu:noGrow,fill:16dlu:noGrow"));
        pnlMain.setMinimumSize(new Dimension(400, 145));
        pnlMain.setPreferredSize(new Dimension(400, 145));
        pnlForm.setLayout(new FormLayout("fill:70dlu:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow,left:4dlu:noGrow,fill:10dlu:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow", "fill:12dlu:noGrow,top:4dlu:noGrow,center:12dlu:noGrow"));
        CellConstraints cc = new CellConstraints();
        pnlMain.add(pnlForm, cc.xy(1, 1));
        txtFechaInicio = new JTextField();
        txtFechaInicio.setText("");
        pnlForm.add(txtFechaInicio, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblFeInicio = new JLabel();
        lblFeInicio.setText("Fecha de Cobertura");
        pnlForm.add(lblFeInicio, cc.xy(1, 1));
        lblFeFin = new JLabel();
        lblFeFin.setHorizontalAlignment(0);
        lblFeFin.setText("al");
        pnlForm.add(lblFeFin, cc.xy(5, 1));
        txtFechaFin = new JTextField();
        pnlForm.add(txtFechaFin, cc.xy(7, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JLabel label1 = new JLabel();
        label1.setText("Estado");
        pnlForm.add(label1, cc.xy(1, 3));
        chkEstado = new JCheckBox();
        chkEstado.setText("");
        pnlForm.add(chkEstado, cc.xy(3, 3));
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,right:max(d;4px):grow", "fill:16dlu:noGrow"));
        pnlMain.add(footerPanel, cc.xy(1, 3));
        lblF2 = new JLabel();
        lblF2.setText("F10 = Aceptar");
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
