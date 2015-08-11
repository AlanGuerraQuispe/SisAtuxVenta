package atux.vistas.buscar;

import atux.controllers.CSimpleModelo;
import atux.modelgui.ModeloTablaSimple;
import atux.util.Helper;
import java.awt.Component;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class BuscarProductosFaltaCero extends javax.swing.JPanel {

    JOptionPane op;
    int opcion=-1;
    private ModeloTablaSimple mtp;
    private CSimpleModelo cs;         
    DefaultComboBoxModel mFecha;        
        
    public BuscarProductosFaltaCero() {
        initComponents();
        lbAviso.setVisible(false);
        cs  = new CSimpleModelo();
        mFecha = new DefaultComboBoxModel(cs.getFechasProReposicion().toArray());
        this.cmbFechaProceso.setModel(mFecha); 
        this.cmbFechaProceso.setSelectedIndex(0);
        
        mtp = new ModeloTablaSimple(cs.getRelacionProductosFaltaCero(this.cmbFechaProceso.getSelectedItem().toString()), ModeloTablaSimple.FALTA_CERO);
        tblProdFaltaCero.setModel(mtp);
        Helper.ajustarAnchoColumnas(tblProdFaltaCero);        
    }
    
    private void getOptionPane()
    {
        if(op != null)
        {
            return;
        }
        Component pdr =this.getParent(); 
        while(pdr.getParent() != null)
        {
            if(pdr instanceof JOptionPane)
            {
                op = (JOptionPane)pdr;
                break;
            }            
            pdr = pdr.getParent();
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelImageProdNoAtendidos = new elaprendiz.gui.panel.PanelImage();
        panelCurves1 = new elaprendiz.gui.panel.PanelCurves();
        jPanelHead = new javax.swing.JPanel();
        lbAviso = new elaprendiz.gui.label.LabelRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdFaltaCero = new javax.swing.JTable();
        lblFecha = new javax.swing.JLabel();
        cmbFechaProceso = new elaprendiz.gui.comboBox.ComboBoxRect();
        jPanelFoot = new javax.swing.JPanel();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(600, 373));
        setLayout(new java.awt.BorderLayout());

        panelImageProdNoAtendidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImageProdNoAtendidos.setLayout(new java.awt.BorderLayout());

        jPanelHead.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelHead.setOpaque(false);

        lbAviso.setBackground(new java.awt.Color(255, 0, 51));
        lbAviso.setForeground(new java.awt.Color(255, 255, 0));
        lbAviso.setText(".");
        lbAviso.setFont(new java.awt.Font("Arial", 0, 3)); // NOI18N
        lbAviso.setPreferredSize(new java.awt.Dimension(250, 17));

        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(363, 250));

        tblProdFaltaCero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProdFaltaCero.setOpaque(false);
        jScrollPane1.setViewportView(tblProdFaltaCero);

        lblFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFecha.setText("Fecha de proceso :");

        cmbFechaProceso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFechaProcesoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelHeadLayout = new javax.swing.GroupLayout(jPanelHead);
        jPanelHead.setLayout(jPanelHeadLayout);
        jPanelHeadLayout.setHorizontalGroup(
            jPanelHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeadLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(lblFecha)
                .addGap(10, 10, 10)
                .addComponent(cmbFechaProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(382, 382, 382)
                .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelHeadLayout.setVerticalGroup(
            jPanelHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeadLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanelHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha)
                    .addComponent(cmbFechaProceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelCurves1.add(jPanelHead, java.awt.BorderLayout.PAGE_START);

        jPanelFoot.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelFoot.setOpaque(false);

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFootLayout = new javax.swing.GroupLayout(jPanelFoot);
        jPanelFoot.setLayout(jPanelFootLayout);
        jPanelFootLayout.setHorizontalGroup(
            jPanelFootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFootLayout.createSequentialGroup()
                .addContainerGap(284, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(260, 260, 260))
        );
        jPanelFootLayout.setVerticalGroup(
            jPanelFootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFootLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCurves1.add(jPanelFoot, java.awt.BorderLayout.CENTER);

        panelImageProdNoAtendidos.add(panelCurves1, java.awt.BorderLayout.CENTER);

        add(panelImageProdNoAtendidos, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        getOptionPane();
        op.setValue(1);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cmbFechaProcesoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFechaProcesoItemStateChanged
        if(cmbFechaProceso.getSelectedIndex() != -1)
         {     
             if(evt.getStateChange() == ItemEvent.SELECTED)
             {                          
                 String vFechaProceso = this.cmbFechaProceso.getSelectedItem().toString(); 
                                  
                 mtp = new ModeloTablaSimple(cs.getRelacionProductosFaltaCero(this.cmbFechaProceso.getSelectedItem().toString()), ModeloTablaSimple.FALTA_CERO);
                tblProdFaltaCero.setModel(mtp);
                Helper.ajustarAnchoColumnas(tblProdFaltaCero);

             }
         }
        
    }//GEN-LAST:event_cmbFechaProcesoItemStateChanged

    
public JLabel getAviso()
{
   return lbAviso;
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect btnCancelar;
    private javax.swing.ButtonGroup buttonGroup1;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbFechaProceso;
    private javax.swing.JPanel jPanelFoot;
    private javax.swing.JPanel jPanelHead;
    private javax.swing.JScrollPane jScrollPane1;
    private elaprendiz.gui.label.LabelRect lbAviso;
    private javax.swing.JLabel lblFecha;
    private elaprendiz.gui.panel.PanelCurves panelCurves1;
    private elaprendiz.gui.panel.PanelImage panelImageProdNoAtendidos;
    private javax.swing.JTable tblProdFaltaCero;
    // End of variables declaration//GEN-END:variables
     
}
