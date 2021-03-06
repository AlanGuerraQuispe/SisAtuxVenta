package com.atux.desktop.consulta;

import com.aw.swing.mvp.ui.GradientPanel;
import com.aw.swing.mvp.ui.RoundedBorder;
import com.aw.swing.mvp.ui.RoundedPanel;
import com.aw.swing.mvp.ui.ZonePanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by MATRIX-JAVA on 18/11/2014.
 */
public class FrmVentaTransaccion {
    public JPanel pnlMain;
    public JPanel pnlForm;
    public JPanel pnlGrid;
    public JTextField txtFeInicio;
    public JTextField txtFeFin;
    public JTextField txtVaPrecioPromedio;
    public JTextField txtVaTotal;
    public JPanel footerPanel;
    public JPanel pnlTitGrid;
    public JCheckBox chkSel;
    public JLabel lblTitGrid;
    public JLabel lblRangoEvaluacion;
    public JLabel lblVaPrecioPromedio;
    public JLabel lblVaTotal;
    public JLabel lblF7;
    public JLabel lblAl;
    public JLabel lblF5;
    public JTable tblGrid;
    public JScrollPane pnlResult;
    public JLabel lblEsc;


    private void createUIComponents() {
        pnlForm = new ZonePanel("Venta x Transacción", true);
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
        pnlMain.setLayout(new FormLayout("fill:d:grow", "center:d:noGrow,top:4dlu:noGrow,fill:d:grow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        pnlMain.setMinimumSize(new Dimension(900, 430));
        pnlMain.setPreferredSize(new Dimension(900, 430));
        pnlForm.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:45dlu:noGrow,left:4dlu:noGrow,fill:12dlu:noGrow,left:4dlu:noGrow,fill:45dlu:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:45dlu:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:45dlu:noGrow,left:4dlu:noGrow,fill:d:grow", "fill:12dlu:noGrow"));
        CellConstraints cc = new CellConstraints();
        pnlMain.add(pnlForm, cc.xy(1, 1));
        lblRangoEvaluacion = new JLabel();
        lblRangoEvaluacion.setText("Rango Evaluación");
        pnlForm.add(lblRangoEvaluacion, cc.xy(1, 1));
        txtFeInicio = new JTextField();
        pnlForm.add(txtFeInicio, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblAl = new JLabel();
        lblAl.setText("al");
        pnlForm.add(lblAl, cc.xy(5, 1));
        txtFeFin = new JTextField();
        pnlForm.add(txtFeFin, cc.xy(7, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblVaPrecioPromedio = new JLabel();
        lblVaPrecioPromedio.setText("Valor Precio Promedio");
        pnlForm.add(lblVaPrecioPromedio, cc.xy(9, 1));
        txtVaPrecioPromedio = new JTextField();
        txtVaPrecioPromedio.setHorizontalAlignment(4);
        pnlForm.add(txtVaPrecioPromedio, cc.xy(11, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblVaTotal = new JLabel();
        lblVaTotal.setText("Vta. Total");
        pnlForm.add(lblVaTotal, cc.xy(13, 1));
        txtVaTotal = new JTextField();
        pnlForm.add(txtVaTotal, cc.xy(15, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        pnlGrid.setLayout(new FormLayout("fill:d:grow", "fill:16dlu:noGrow,top:4dlu:noGrow,fill:d:grow"));
        pnlGrid.setMinimumSize(new Dimension(453, 336));
        pnlGrid.setPreferredSize(new Dimension(454, 336));
        pnlMain.add(pnlGrid, cc.xy(1, 3));
        pnlTitGrid = new JPanel();
        pnlTitGrid.setLayout(new FormLayout("fill:16dlu:noGrow,left:4dlu:noGrow,fill:d:grow", "center:d:grow,fill:16dlu:noGrow"));
        pnlGrid.add(pnlTitGrid, cc.xy(1, 1));
        chkSel = new JCheckBox();
        chkSel.setText("");
        pnlTitGrid.add(chkSel, cc.xy(1, 2));
        lblTitGrid = new JLabel();
        lblTitGrid.setText("Relación de Transacciones");
        pnlTitGrid.add(lblTitGrid, cc.xy(3, 2));
        pnlResult = new JScrollPane();
        pnlGrid.add(pnlResult, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
        tblGrid = new JTable();
        pnlResult.setViewportView(tblGrid);
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:noGrow,left:4dlu:noGrow,right:d:grow", "fill:16dlu:noGrow"));
        pnlMain.add(footerPanel, cc.xy(1, 5));
        lblF7 = new JLabel();
        lblF7.setText("F7 = Reporte");
        footerPanel.add(lblF7, cc.xy(3, 1));
        lblF5 = new JLabel();
        lblF5.setText("F5 = Ver Resumen");
        footerPanel.add(lblF5, cc.xy(1, 1));
        lblEsc = new JLabel();
        lblEsc.setText("Esc = Salir");
        footerPanel.add(lblEsc, cc.xy(5, 1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }
}
