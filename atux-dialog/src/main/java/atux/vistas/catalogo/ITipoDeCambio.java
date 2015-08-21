/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IImpuestoIGV.java
 *
 * Created on 22/12/2014, 04:20:23 PM
 */
package atux.vistas.catalogo;
import atux.controllers.CTipoDeCambio;
import atux.modelgui.ModeloTablaTipoDeCambio;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.vistas.buscar.BuscarTipoDeCambio;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Cesar Ruiz  PC
 */
public class ITipoDeCambio extends javax.swing.JInternalFrame {
    private CTipoDeCambio cp;
    private ModeloTablaTipoDeCambio mtp;
    private boolean esActualizacion = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());    

    public ITipoDeCambio() {
        initComponents();
        cp = new CTipoDeCambio();
        mtp = new ModeloTablaTipoDeCambio(inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();

        tblTipoDeCambio.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblTipoDeCambio,ModeloTablaTipoDeCambio.anchoColumnas);
        Helper.ajustarAnchoColumnas(tblTipoDeCambio);
        setFiltroTexto();        
        setEventSelectionModel(tblTipoDeCambio.getSelectionModel());
        DesActivarCasillas();
        rbTodosActionPerformed(null);
    }

    private void setFiltroTexto(){
        Helper.setFiltraEntrada(txtValorTCambio.getDocument(), FiltraEntrada.NUM_LETRAS, 10, false);
    }

    private void setEventSelectionModel(ListSelectionModel lsm){
        ListSelectionListener lsl = new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectionEvent(e);
            }
        };
        lsm.addListSelectionListener(lsl);
    }
    
    private void selectionEvent(ListSelectionEvent e){
        if(tblTipoDeCambio.getSelectedRow() != -1){
            numRegistros = tblTipoDeCambio.getSelectedRow();
            cp.setTipodeCambio(mtp.getFila(tblTipoDeCambio.getSelectedRow()));
            setTipoDeCambio();           
       }
    }
    
    private void setTipoDeCambio(){
        Limpiar();
        this.txtFecha.setDate(AtuxUtility.getStringToDate(cp.getTipodeCambio().getFeTipoCambio(), "dd/MM/yyyy"));
        this.txtValorTCambio.setText(String.valueOf(cp.getTipodeCambio().getVaCambioVentaInka()));

        if("A".equals(cp.getTipodeCambio().getEsTipoCambio())){
            chbSetActivo(true); 
        }else{
            chbSetActivo(false); 
        }
        this.btnModificar.setEnabled(true);
    }
    
    private void Limpiar(){
        this.txtFecha.setDate(null);
        this.txtValorTCambio.setText("");
        this.chbEstado.setSelected(false);
        chbSetActivo(false);
    }

    private void ActivarCasillas(){
        pnlEntradasTDeCambio.setEnabled(false);
        ECampos.setEditableTexto(this.pnlEntradasTDeCambio, true, new Component[]{lblfecha,lblEstado,lblTipoCambio},true,"");        
        this.tblTipoDeCambio.setEnabled(false);
        this.tblTipoDeCambio.clearSelection();        
        this.btnNuevo.setEnabled(false);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(true);
        this.btnCancelar.setEnabled(true);
        this.btnSalir.setEnabled(false);
        this.chbEstado.setEnabled(true);
        
        this.btnPrimero.setEnabled(false);
        this.btnUltimo.setEnabled(false);
        this.btnBuscar.setEnabled(false);
        this.btnSiguiente.setEnabled(false);
        this.btnAnterior.setEnabled(false);
        this.rbTodos.setEnabled(false);
        this.rbAtivos.setEnabled(false);
        this.rbNoActivos.setEnabled(false);
        txtFecha.setEnabled(true);
    }
    private void DesActivarCasillas(){
        this.pnlEntradasTDeCambio.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradasTDeCambio, false, new Component[]{lblfecha,lblEstado,lblTipoCambio},true,"");        
        this.tblTipoDeCambio.setEnabled(true);
        this.tblTipoDeCambio.clearSelection();
        this.btnNuevo.setEnabled(true);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.btnSalir.setEnabled(true);
        this.chbEstado.setEnabled(false);
        
        this.btnPrimero.setEnabled(true);
        this.btnUltimo.setEnabled(true);
        this.btnBuscar.setEnabled(true);
        this.btnSiguiente.setEnabled(true);
        this.btnAnterior.setEnabled(true);
        this.rbTodos.setEnabled(true);
        this.rbAtivos.setEnabled(true);
        this.rbNoActivos.setEnabled(true);        

        esActualizacion = false;
//        this.pnlBuscadorTDeCambio.setVisible(true);
        txtFecha.setEnabled(false); 
        logger.info(txtFecha.getDate().toString());
    }

    public boolean verficarCambios(){
        // TODO aguerra verificar     
        
        if(!this.txtFecha.getDate().toString().equals(cp.getTipodeCambio().getFeTipoCambio())){
            return true;
        }else if(!this.txtValorTCambio.getText().equals(String.valueOf(cp.getTipodeCambio().getVaCambioVentaInka()))){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getTipodeCambio().getEsTipoCambio()))){
            return true;
        }
        return false;
    }
    
    private boolean guardarActualizar() throws SQLException{
        cp.getTipodeCambio().setCoCompania(AtuxVariables.vCodigoCompania);
        cp.getTipodeCambio().setCoMoneda(AtuxVariables.MONEDA_DOLARES);
        cp.getTipodeCambio().setCoMonedaPais(AtuxVariables.MONEDA_SOLES);

        cp.getTipodeCambio().setFeTipoCambio(txtFecha.getDate());
        cp.getTipodeCambio().setVaCambioCompraInka(Double.parseDouble(txtValorTCambio.getText()));
        cp.getTipodeCambio().setVaCambioVentaInka(Double.parseDouble(txtValorTCambio.getText()));


        if (chbEstado.isSelected()){
            cp.getTipodeCambio().setEsTipoCambio("A");
        }else{
            cp.getTipodeCambio().setEsTipoCambio("I");
        }
        
        Boolean EstadoGuardar=false;
            
        if(!esActualizacion){
            cp.getTipodeCambio().setIdCreaTipoCambio(AtuxVariables.vIdUsuario);
            cp.getTipodeCambio().setFeCreaTipoCambio(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));

            cp.getTipodeCambio().setNuSecTipoCambio(cp.getNuevoCodigo());
            EstadoGuardar = cp.guardarRegistro(cp.getTipodeCambio());
        }else{
            cp.getTipodeCambio().setIdModTipoCambio(AtuxVariables.vIdUsuario);
            cp.getTipodeCambio().setFeModTipoCambio(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            
            int act = cp.actualizarRegistro(cp.getTipodeCambio());
            if (act ==1) {
                EstadoGuardar = true;
            }
        }
        
        if(EstadoGuardar){
            EstadoGuardar = true; 
        }else{
            EstadoGuardar = false; 
        }
        return EstadoGuardar;
    }
    
    private Date FormatoFecha(String oldFecha){
        Date Fecha=null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = oldFecha;

        try {
            Fecha = formatter.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(ITipoDeCambio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }

    public void chbSetActivo(boolean opcion){
        chbEstado.setSelected(true);
        chbEstado.setText("Activo");
        chbEstado.setBackground(new Color(104,204,0));
        chbEstado.setForeground(Color.BLACK);
        if(!opcion){
            chbEstado.setSelected(false);
            chbEstado.setText("No Activo");
            chbEstado.setBackground(Color.red);
            chbEstado.setForeground(Color.WHITE);
        }
    }

    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        pnlEntradasTDeCambio = new javax.swing.JPanel();
        lblfecha = new javax.swing.JLabel();
        lblTipoCambio = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtValorTCambio = new elaprendiz.gui.textField.TextField();
        chbEstado = new javax.swing.JCheckBox();
        txtFecha = new com.toedter.calendar.JDateChooser();
        pnlBuscadorTDeCambio = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnBuscar = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        pnlAccionesTDeCambio = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTipoDeCambio = new javax.swing.JTable();

        setBorder(null);
        setResizable(true);
        setTitle("Formulario de Tipo de Cambio");

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        pnlEntradasTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos de Tipo de Cambio", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlEntradasTDeCambio.setOpaque(false);

        lblfecha.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblfecha.setText("Fecha:");

        lblTipoCambio.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTipoCambio.setText("Tipo de Cambio:");

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setText("Estado:");

        txtValorTCambio.setEditable(false);
        txtValorTCambio.setDireccionDeSombra(30);
        txtValorTCambio.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtValorTCambio.setFont(new java.awt.Font("Arial", 0, 12));
        txtValorTCambio.setName("pdescrip"); // NOI18N
        txtValorTCambio.setPreferredSize(new java.awt.Dimension(120, 25));
        txtValorTCambio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorTCambioKeyReleased(evt);
            }
        });

        chbEstado.setBackground(new java.awt.Color(51, 153, 255));
        chbEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        chbEstado.setSelected(true);
        chbEstado.setText("Activo");
        chbEstado.setEnabled(false);
        chbEstado.setPreferredSize(new java.awt.Dimension(100, 25));
        chbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbEstadoActionPerformed(evt);
            }
        });
        chbEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chbEstadoKeyReleased(evt);
            }
        });

        txtFecha.setBackground(new java.awt.Color(0, 0, 0));
        txtFecha.setForeground(new java.awt.Color(255, 0, 0));
        txtFecha.setDate(Calendar.getInstance().getTime());
        txtFecha.setFont(new java.awt.Font("Tahoma", 1, 13));
        txtFecha.setPreferredSize(new java.awt.Dimension(120, 22));
        txtFecha.setRequestFocusEnabled(false);
        txtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlEntradasTDeCambioLayout = new javax.swing.GroupLayout(pnlEntradasTDeCambio);
        pnlEntradasTDeCambio.setLayout(pnlEntradasTDeCambioLayout);
        pnlEntradasTDeCambioLayout.setHorizontalGroup(
            pnlEntradasTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasTDeCambioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblfecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTipoCambio)
                .addGap(13, 13, 13)
                .addComponent(txtValorTCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblEstado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnlEntradasTDeCambioLayout.setVerticalGroup(
            pnlEntradasTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasTDeCambioLayout.createSequentialGroup()
                .addGroup(pnlEntradasTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlEntradasTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEstado)
                            .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtValorTCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBuscadorTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlBuscadorTDeCambio.setOpaque(false);

        btnPrimero.setBackground(new java.awt.Color(102, 204, 0));
        btnPrimero.setText("<<");
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });

        btnAnterior.setBackground(new java.awt.Color(102, 204, 0));
        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(102, 204, 0));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnSiguiente.setBackground(new java.awt.Color(102, 204, 0));
        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnUltimo.setBackground(new java.awt.Color(104, 204, 0));
        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });

        rbTodos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbTodos);
        rbTodos.setFont(new java.awt.Font("Arial", 1, 14));
        rbTodos.setForeground(new java.awt.Color(255, 255, 255));
        rbTodos.setSelected(true);
        rbTodos.setText("Todos");
        rbTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTodosActionPerformed(evt);
            }
        });

        rbAtivos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbAtivos);
        rbAtivos.setFont(new java.awt.Font("Arial", 1, 14));
        rbAtivos.setForeground(new java.awt.Color(255, 255, 255));
        rbAtivos.setText("Activos");
        rbAtivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAtivosActionPerformed(evt);
            }
        });

        rbNoActivos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbNoActivos);
        rbNoActivos.setFont(new java.awt.Font("Arial", 1, 14));
        rbNoActivos.setForeground(new java.awt.Color(255, 255, 255));
        rbNoActivos.setText("No Activos");
        rbNoActivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNoActivosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBuscadorTDeCambioLayout = new javax.swing.GroupLayout(pnlBuscadorTDeCambio);
        pnlBuscadorTDeCambio.setLayout(pnlBuscadorTDeCambioLayout);
        pnlBuscadorTDeCambioLayout.setHorizontalGroup(
            pnlBuscadorTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
            .addGroup(pnlBuscadorTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBuscadorTDeCambioLayout.createSequentialGroup()
                    .addGap(0, 4, Short.MAX_VALUE)
                    .addComponent(btnPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(rbTodos)
                    .addGap(5, 5, 5)
                    .addComponent(rbAtivos)
                    .addGap(5, 5, 5)
                    .addComponent(rbNoActivos)
                    .addGap(0, 4, Short.MAX_VALUE)))
        );
        pnlBuscadorTDeCambioLayout.setVerticalGroup(
            pnlBuscadorTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
            .addGroup(pnlBuscadorTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBuscadorTDeCambioLayout.createSequentialGroup()
                    .addGap(0, 2, Short.MAX_VALUE)
                    .addGroup(pnlBuscadorTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbTodos)
                        .addComponent(rbAtivos)
                        .addComponent(rbNoActivos))
                    .addGap(0, 2, Short.MAX_VALUE)))
        );

        pnlAccionesTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlAccionesTDeCambio.setOpaque(false);

        btnNuevo.setBackground(new java.awt.Color(0, 153, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(51, 153, 255));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAccionesTDeCambioLayout = new javax.swing.GroupLayout(pnlAccionesTDeCambio);
        pnlAccionesTDeCambio.setLayout(pnlAccionesTDeCambioLayout);
        pnlAccionesTDeCambioLayout.setHorizontalGroup(
            pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesTDeCambioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAccionesTDeCambioLayout.setVerticalGroup(
            pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tblTipoDeCambio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTipoDeCambio);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                            .addComponent(pnlEntradasTDeCambio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEntradasTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleName("Formulario Tipo de Cambio");
        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblTipoDeCambio.getRowCount()-1;
        numRegistros=0;
        cp.setTipodeCambio(mtp.getFila(numRegistros));
        setTipoDeCambio();           
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblTipoDeCambio.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setTipodeCambio(mtp.getFila(numRegistros));
        setTipoDeCambio();           

        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblTipoDeCambio.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setTipodeCambio(mtp.getFila(numRegistros));
        setTipoDeCambio();           

        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblTipoDeCambio.getRowCount()-1;
        numRegistros=finalPag;
        cp.setTipodeCambio(mtp.getFila(numRegistros));
        setTipoDeCambio();           

        return;
}//GEN-LAST:event_btnUltimoActionPerformed

private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;  
        finalPag = tmpFp;
        mtp = new ModeloTablaTipoDeCambio(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblTipoDeCambio.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblTipoDeCambio,ModeloTablaTipoDeCambio.anchoColumnas);
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbTodosActionPerformed

private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaTipoDeCambio(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblTipoDeCambio.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblTipoDeCambio,ModeloTablaTipoDeCambio.anchoColumnas);
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbAtivosActionPerformed

private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaTipoDeCambio(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblTipoDeCambio.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblTipoDeCambio,ModeloTablaTipoDeCambio.anchoColumnas);
        chbSetActivo(false);
        Limpiar();
}//GEN-LAST:event_rbNoActivosActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        esActualizacion = false;        
        Limpiar();
        cp.getTipodeCambio().setNuSecTipoCambio(cp.getNuevoCodigo());
        txtFecha.requestFocus();
        ActivarCasillas();
}//GEN-LAST:event_btnNuevoActionPerformed

private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        esActualizacion = true;
        ActivarCasillas();
}//GEN-LAST:event_btnModificarActionPerformed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Esta Seguro de Guardar los Datos","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
            return;
        }
        if(ECampos.esObligatorio(this.pnlEntradasTDeCambio,true)){
            JOptionPane.showInternalMessageDialog(this, "Los campos en rojo son obligatorios", "Llene los campos obligatorios", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!this.verficarCambios()){
            if (JOptionPane.showConfirmDialog(this, "No se ha realizado ningun Cambio,\n Esta seguro de Continuar","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
                return;
            }            
        }
        
        boolean correcto = false; 

        if(!this.verficarCambios()){
            JOptionPane.showMessageDialog(this, "Debe cambiar por lo menos algun valor", "No hubo cambios", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            correcto = guardarActualizar();
        } catch (SQLException ex) {
            Logger.getLogger(IProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        if(!correcto){
            JOptionPane.showMessageDialog(this, "Error: no se pudo guardar.", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
            ECampos.esObligatorio(this.pnlEntradasTDeCambio,false);
            return;
        }
        
        AtuxUtility.aceptarTransaccion();
        JOptionPane.showMessageDialog(this, "Información Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);

        if(tipoSeleccion == -1){
            this.mtp = new ModeloTablaTipoDeCambio(inicioPag,finalPag);
        }else if(tipoSeleccion == 1){
            this.mtp = new ModeloTablaTipoDeCambio(tipoSeleccion,inicioPag,finalPag);
        }
        tblTipoDeCambio.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblTipoDeCambio,ModeloTablaTipoDeCambio.anchoColumnas);
        this.numRegistros = mtp.getCantidadRegistros();
        Helper.ajustarSoloAnchoColumnas(tblTipoDeCambio,ModeloTablaTipoDeCambio.anchoColumnas);
        DesActivarCasillas();
        tblTipoDeCambio.requestFocus();
}//GEN-LAST:event_btnGuardarActionPerformed

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Se perderan todos los datos ingresados\nEsta Seguro de Cancelar ","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
            return;
        }

        DesActivarCasillas();
}//GEN-LAST:event_btnCancelarActionPerformed

private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
    dispose();
}//GEN-LAST:event_btnSalirActionPerformed

private void txtValorTCambioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorTCambioKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: chbEstado.requestFocus();
             break;
    }
}//GEN-LAST:event_txtValorTCambioKeyReleased

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: btnGuardar.requestFocus();
             break;
    }    
}//GEN-LAST:event_chbEstadoKeyReleased

private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        BuscarTipoDeCambio tCambio = new BuscarTipoDeCambio(tipoSeleccion);
        JLabel aviso = tCambio.getAviso();
        String msj = (tipoSeleccion==-1?"Mostrando todos los Tipos de Cambios existentes":(tipoSeleccion==1?"Mostrando todo los Tipos de Cambios activos":"Mostrando todo los Tipos de Cambios No activos"));
        JOptionPane.showInternalOptionDialog(this, tCambio, msj,JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

        if(tCambio.getTipoDeCambio() != null){
           cp.setTipodeCambio(tCambio.getTipoDeCambio());
           setTipoDeCambio();
        }
}//GEN-LAST:event_btnBuscarActionPerformed

private void txtFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtValorTCambio.requestFocus();
             break;
    }    
}//GEN-LAST:event_txtFechaKeyReleased

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
        chbSetActivo(chbEstado.isSelected());
}//GEN-LAST:event_chbEstadoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect btnAnterior;
    private elaprendiz.gui.button.ButtonRect btnBuscar;
    private elaprendiz.gui.button.ButtonRect btnCancelar;
    private elaprendiz.gui.button.ButtonRect btnGuardar;
    private elaprendiz.gui.button.ButtonRect btnModificar;
    private elaprendiz.gui.button.ButtonRect btnNuevo;
    private elaprendiz.gui.button.ButtonRect btnPrimero;
    private elaprendiz.gui.button.ButtonRect btnSalir;
    private elaprendiz.gui.button.ButtonRect btnSiguiente;
    private elaprendiz.gui.button.ButtonRect btnUltimo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chbEstado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblTipoCambio;
    private javax.swing.JLabel lblfecha;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAccionesTDeCambio;
    private javax.swing.JPanel pnlBuscadorTDeCambio;
    private javax.swing.JPanel pnlEntradasTDeCambio;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblTipoDeCambio;
    private com.toedter.calendar.JDateChooser txtFecha;
    private elaprendiz.gui.textField.TextField txtValorTCambio;
    // End of variables declaration//GEN-END:variables
}
