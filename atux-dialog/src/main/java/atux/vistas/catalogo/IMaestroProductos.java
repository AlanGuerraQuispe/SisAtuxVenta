package atux.vistas.catalogo;

import atux.controllers.CG1_LineaComercial;
import atux.controllers.CG2_Division;
import atux.controllers.CG3_SubDivision;
import atux.controllers.CG4_Familia;
import atux.controllers.CG5_SubFamilia;
import atux.controllers.CImpuestoIGV;
import atux.controllers.CLaboratorio;
import atux.controllers.CProducto;
import atux.controllers.CProductoLocal;
import atux.controllers.CMoneda;
import atux.controllers.CMotivoControlDigemid;
import atux.controllers.CPaises;
import atux.modelbd.ImpuestoIGV;
import atux.modelbd.Moneda;
import atux.modelbd.ProductoLocal;
import atux.modelgui.ModeloTablaMaestroProductos;
import atux.modelgui.ModeloTablaSimple;
import atux.util.common.AtuxGridUtils;
import atux.enums.IndicadorSNRegistro;

import javax.swing.*;

import atux.vistas.buscar.BuscarG1_LineaComercial;
import atux.vistas.buscar.BuscarG2_Division;
import atux.vistas.buscar.BuscarG3_SubDivision;
import atux.vistas.buscar.BuscarG4_Familia;
import atux.vistas.buscar.BuscarG5_SubFamilia;
import atux.vistas.buscar.BuscarLaboratorio;
import atux.vistas.buscar.BuscarPaises;
import atux.vistas.buscar.BuscarMaestroProducto;

import java.awt.*;
import java.util.Calendar;
import java.sql.SQLException;

import atux.util.ECampos;
import atux.util.Helper;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxUtility;

import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Cesar Ruiz  PC
 */
public class IMaestroProductos extends javax.swing.JInternalFrame {
    private CProducto cp;
    private CProductoLocal cpl;
    private ModeloTablaMaestroProductos mtp;
    private CMoneda controllerMoneda;
    private CImpuestoIGV controllerIGV;
    private CMotivoControlDigemid controllerControlDigemid;
    private boolean esActualizacion = false;
    private int tipoSeleccion = 1; //-1 todo,1 activos, 0 No activos
    private DefaultComboBoxModel mMoneda;
    private DefaultComboBoxModel mImpuestoIGV;
    private DefaultComboBoxModel mControlDigemid;
    private DefaultComboBoxModel mControlLote;
    private DefaultComboBoxModel mControlado;
    private DefaultComboBoxModel mPMedico;
    private DefaultComboBoxModel mPReceta;
    private DefaultComboBoxModel mIFraccion;

    public int finalPag = 0;
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());

    private int VaFraccionAnt;
    private int CaStockDisponibleAnt;
    private String InProdFraccionadoAnt;
    private String DeFraccionAnt;

    private int BuscarG1(String Codigo) {
        CG1_LineaComercial BG1 = new CG1_LineaComercial();
        BG1.getRegistroPorPk(new String[]{Codigo, "1"});
        if (BG1.getG1_LineaComercial().getDeLineaProdErp() != null) {
            txtLineaComercial.setText(BG1.getG1_LineaComercial().getDeLineaProdErp().trim());
            return 1;
        } else {
            JOptionPane.showMessageDialog(this, "G1 - Linea Comercial no registrado", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    private int BuscarG2(String Codigo) {
        CG2_Division BG2 = new CG2_Division();
        BG2.getRegistroPorPk(new String[]{Codigo, Codigo.substring(0, 1)});
        if (BG2.getG2_Division().getDeGrupoProdErp() != null) {
            txtDivision.setText(BG2.getG2_Division().getDeGrupoProdErp().trim());
            return 1;
        } else {
            JOptionPane.showMessageDialog(this, "G2 - División no registrado", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    private int BuscarG3(String Codigo) {
        CG3_SubDivision BG3 = new CG3_SubDivision();
        BG3.getRegistroPorPk(new String[]{Codigo, Codigo.substring(0, 3)});
        if (BG3.getG3_SubDivision().getDeGrupoAnatomico() != null) {
            txtSub_Division.setText(BG3.getG3_SubDivision().getDeGrupoAnatomico().trim());
            return 1;
        } else {
            JOptionPane.showMessageDialog(this, "G3 - Sub División no registrado", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    private int BuscarG4(String Codigo) {
        CG4_Familia BG4 = new CG4_Familia();
        BG4.getRegistroPorPk(new String[]{Codigo, Codigo.substring(0, 5)});
        if (BG4.getG4_Familia().getDeGrupoTerapeutico() != null) {
            txtFamilia.setText(BG4.getG4_Familia().getDeGrupoTerapeutico().trim());
            return 1;
        } else {
            JOptionPane.showMessageDialog(this, "G4 - Familia no registrado", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    private int BuscarG5(String Codigo) {
        CG5_SubFamilia BG5 = new CG5_SubFamilia();
        BG5.getRegistroPorPk(new String[]{Codigo, Codigo.substring(0, 7)});
        if (BG5.getG5_SubFamilia().getDeAccionTerapeutica() != null) {
            txtSub_Familia.setText(BG5.getG5_SubFamilia().getDeAccionTerapeutica().trim());
            return 1;
        } else {
            JOptionPane.showMessageDialog(this, "G5 - Sub Familia no registrado", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    private int BuscarLab(String Codigo) {
        CLaboratorio BLab = new CLaboratorio();
        BLab.getRegistroPorPk(new String[]{AtuxVariables.vCodigoCompania, Codigo});
        if (BLab.getLaboratorio().getDeLaboratorio() != null) {
            txtDescripLaboratorio.setText(BLab.getLaboratorio().getDeLaboratorio().trim());
            return 1;
        } else {
            JOptionPane.showMessageDialog(this, "Laboratorio no registrado", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    private int BuscarProcedencia(String Codigo) {
        if (Codigo == null || "".equals(Codigo)) {
            return 0;
        }
        CPaises BPais = new CPaises();
        BPais.getRegistroPorPk(new String[]{Codigo});
        if (BPais.getPaises().getDePais() != null) {
            txtDescripProcedencia.setText(BPais.getPaises().getDePais().trim());
            return 1;
        } else {
            JOptionPane.showMessageDialog(this, "Procedencia no registrado", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public IMaestroProductos() {
        initComponents();
        lblMensaje.setVisible(false);
        cp = new CProducto();
        cpl = new CProductoLocal();
        tipoSeleccion = 1; //-1 todo,1 activos, 0 No activos
        CargarGrilla();
        DesActivarCasillas();

        mControlLote = new DefaultComboBoxModel(IndicadorSNRegistro.values());
        this.cbxControlLote.setModel(mControlLote);

        mControlado = new DefaultComboBoxModel(IndicadorSNRegistro.values());
        this.cbxProdControlado.setModel(mControlado);

        mPMedico = new DefaultComboBoxModel(IndicadorSNRegistro.values());
        this.cbxPideMedico.setModel(mPMedico);

        mPReceta = new DefaultComboBoxModel(IndicadorSNRegistro.values());
        this.cbxPideReceta.setModel(mPReceta);

        mIFraccion = new DefaultComboBoxModel(IndicadorSNRegistro.values());
        this.cbxIndicadorFraccion.setModel(mIFraccion);

        controllerMoneda = new CMoneda();
        mMoneda = new DefaultComboBoxModel(controllerMoneda.getRegistros().toArray());
        this.cmbMoneda.setModel(mMoneda);
        this.cmbMoneda.setSelectedIndex(0);

        controllerIGV = new CImpuestoIGV();
        mImpuestoIGV = new DefaultComboBoxModel(controllerIGV.getRegistros(new String[]{"ES_IMPUESTO"}, new Object[]{"A"}).toArray());
        this.cmbImpuesto.setModel(mImpuestoIGV);
        this.cmbImpuesto.setSelectedIndex(0);

        controllerControlDigemid = new CMotivoControlDigemid();
        mControlDigemid = new DefaultComboBoxModel(controllerControlDigemid.getRegistros().toArray());
        this.cbxDescripcionControl.setModel(mControlDigemid);
        this.cbxDescripcionControl.setSelectedIndex(0);

        Limpiar();
        btnPrimeroActionPerformed(null);
        AtuxGridUtils.setearPrimerRegistro(tblListado, txtDescripcion, ModeloTablaSimple.COLUMNA_DESCRIPCION);
        tblListado.requestFocus();
    }

    private void CargarGrilla() {
        if (tipoSeleccion == -1) {
            this.mtp = new ModeloTablaMaestroProductos("T");
        } else if (tipoSeleccion == 0) {
            this.mtp = new ModeloTablaMaestroProductos("I");
        } else {
            this.mtp = new ModeloTablaMaestroProductos("A");
        }

        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado, ModeloTablaMaestroProductos.anchoColumnas);
        setEventSelectionModel(tblListado.getSelectionModel());
    }

    private void setEventSelectionModel(ListSelectionModel lsm) {
        ListSelectionListener lsl = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectionEvent(e);
            }
        };
        lsm.addListSelectionListener(lsl);
    }

    private void selectionEvent(ListSelectionEvent e) {
        if (tblListado.getSelectedRow() != -1) {
            numRegistros = tblListado.getSelectedRow();
            cp.setProducto(mtp.getFila(tblListado.getSelectedRow()));
            setMaestroProductos();
        }
    }

    private void setMaestroProductos() {
        Limpiar();
        // Datos Maestro Producto
        this.txtCodigo.setText(String.valueOf(cp.getProducto().getCoProducto().trim()));
        this.txtDescripcion.setText(String.valueOf(cp.getProducto().getDeProducto().trim()));
        this.txtUnidad.setText(String.valueOf(cp.getProducto().getDeUnidadProducto().trim()));
        this.txtCodigoLaboratorio.setText(String.valueOf(cp.getProducto().getCoLaboratorio().trim()));
        this.txtCosto.setText(String.valueOf(cp.getProducto().getVaPrecioCompra()));
        this.dteFechaVigencia.setDate(cp.getProducto().getFeVigencia());
        this.dteFechaCreacion.setDate(cp.getProducto().getFeCreaProducto());

        // Set Categoria
        this.txtCodigoG1.setText(cp.getProducto().getCoNivel01() == null ? "" : cp.getProducto().getCoNivel01());
        this.txtCodigoG2.setText(cp.getProducto().getCoNivel02() == null ? "" : cp.getProducto().getCoNivel02());
        this.txtCodigoG3.setText(cp.getProducto().getCoNivel03() == null ? "" : cp.getProducto().getCoNivel03());
        this.txtCodigoG4.setText(cp.getProducto().getCoNivel04() == null ? "" : cp.getProducto().getCoNivel04());
        this.txtCodigoG5.setText(cp.getProducto().getCoNivel05() == null ? "" : cp.getProducto().getCoNivel05());
        this.txtCodigoLaboratorio.setText(cp.getProducto().getCoLaboratorio() == null ? "" : cp.getProducto().getCoLaboratorio());

        if (cp.getProducto().getCoNivel01() != null) {
            BuscarG1(cp.getProducto().getCoNivel01());
        }
        if (cp.getProducto().getCoNivel02() != null) {
            BuscarG2(cp.getProducto().getCoNivel02());
        }
        if (cp.getProducto().getCoNivel03() != null) {
            BuscarG3(cp.getProducto().getCoNivel03());
        }
        if (cp.getProducto().getCoNivel04() != null) {
            BuscarG4(cp.getProducto().getCoNivel04());
        }
        if (cp.getProducto().getCoNivel05() != null) {
            BuscarG5(cp.getProducto().getCoNivel05());
        }
        if (cp.getProducto().getCoLaboratorio() != null) {
            BuscarLab(cp.getProducto().getCoLaboratorio());
        }

        //  Control Digemid
        this.txtCodigoProcedencia.setText(String.valueOf(cp.getProducto().getCoProcedencia()));
        //  if (cp.getProducto().getCoLaboratorio() != null){BuscarProcedencia(cp.getProducto().getCoProcedencia());}
        if (cp.getProducto().getCoProcedencia() != null) {
            BuscarProcedencia(cp.getProducto().getCoProcedencia());
        }

        cmbOpcionSN(cbxProdControlado, cp.getProducto().getInControlado());
        cmbOpcionSN(cbxPideMedico, cp.getProducto().getInPideMedico());
        cmbOpcionSN(cbxPideReceta, cp.getProducto().getInRecetaMedica());
        cmbOpcionSN(cbxControlLote, cp.getProducto().getInControlLote());

        if ("A".equals(cp.getProducto().getEsProducto())) {
            chbSetActivo(true);
        } else {
            chbSetActivo(false);
        }
        this.btnModificar.setEnabled(true);

        // Producto Local
        ProductoLocal prodLocal = new ProductoLocal();
        prodLocal = cpl.getRegistroPorPk(new Object[]{AtuxVariables.vCodigoCompania, AtuxVariables.vCodigoLocal, cp.getProducto().getCoProducto(), 0});
        VaFraccionAnt = prodLocal.getVaFraccion();
        InProdFraccionadoAnt = prodLocal.getInProdFraccionado();
        DeFraccionAnt = prodLocal.getDeUnidadFraccion();
        CaStockDisponibleAnt = prodLocal.getCaStockDisponible();

        cmbOpcionSN(cbxIndicadorFraccion, prodLocal.getInProdFraccionado());
        txtCantidadFraccion.setText(prodLocal.getVaFraccion().toString());
        txtUnidadFraccion.setText(prodLocal.getDeUnidadFraccion());
        this.txtPrecio.setText(prodLocal.getVaVenta().toString());
        this.txtDescuento.setText(prodLocal.getPcDescuento1().toString());
        this.txtPreVtaPublico.setText(prodLocal.getCalculoPrecioPublico().toString());

        // Impuesto
        cmbOpcionImpuesto(cmbImpuesto, cp.getProducto().getCoImpuesto1());

        // Procedencia
        txtCodigoProcedencia.setText(cp.getProducto().getCoProcedencia());
        BuscarProcedencia(txtCodigoProcedencia.getText());
    }

    private void cmbOpcionSN(JComboBox cmbOptionSN, String opcion) {
        for (int i=0;i<cmbOptionSN.getItemCount();i++){
            IndicadorSNRegistro tip = (IndicadorSNRegistro) cmbOptionSN.getItemAt(i);
                if (tip.getCodigo().equals(opcion)){
                    cmbOptionSN.setSelectedIndex(i);
                    break;
                }
                else{
                    cmbOptionSN.setSelectedIndex(1);
                    break;
                }
        }
    }

    private void cmbOpcionImpuesto(JComboBox cmbImpuesto, String coImpuesto1) {
        for (int i=0;i<cmbImpuesto.getItemCount();i++){
            ImpuestoIGV impuestoIGV = (ImpuestoIGV) cmbImpuesto.getItemAt(i);
            if (impuestoIGV.getCoImpuesto().equals(coImpuesto1)){
                cmbImpuesto.setSelectedIndex(i);
                break;
            }
        }
    }

    private String cmbRetornaOpcion(JComboBox cmbOptionSN){
        return ((IndicadorSNRegistro)cmbOptionSN.getSelectedItem()).getCodigo();
    }

    public void chbSetActivo(boolean opcion) {
        chbEstado.setSelected(true);
        chbEstado.setText("Activo");
        chbEstado.setBackground(new Color(104, 204, 0));
        chbEstado.setForeground(Color.BLACK);
        if (!opcion) {
            chbEstado.setSelected(false);
            chbEstado.setText("No Activo");
            chbEstado.setBackground(Color.red);
            chbEstado.setForeground(Color.WHITE);
        }
    }

    private void Limpiar() {
        this.txtPideLote2.setVisible(false);
        this.txtCodigo.setText("");
        this.txtDescripcion.setText("");
        this.txtUnidad.setText("");
        this.txtCodigoLaboratorio.setText("");
        this.txtDescripLaboratorio.setText("");

        this.txtCosto.setText("0.00");
        this.txtPrecio.setText("0.00");
        this.txtDescuento.setText("0.00");
        this.txtPreVtaPublico.setText("0.00");
        this.dteFechaVigencia.setDate(null);
        this.dteFechaCreacion.setDate(null);


        this.txtTipoPrecio.setText("");
        //this.cmbImpuesto.setSelectedIndex(0);
        //this.cmbMoneda.setSelectedIndex(0);
        chbSetActivo(false);

        //	Set de Categoria
        this.txtCodigoG1.setText("");
        this.txtCodigoG2.setText("");
        this.txtCodigoG3.setText("");
        this.txtCodigoG4.setText("");
        this.txtCodigoG5.setText("");
        this.txtLineaComercial.setText("");
        this.txtDivision.setText("");
        this.txtSub_Division.setText("");
        this.txtFamilia.setText("");
        this.txtSub_Familia.setText("");

        //	Control Digemid
        this.txtCodigoProcedencia.setText("");
        this.txtDescripProcedencia.setText("");

        this.txtPideLote2.setText("");
        this.txtCantidadFraccion.setText("0");
        this.txtUnidadFraccion.setText("");

        this.cbxProdControlado.setSelectedIndex(1);
        this.cbxPideMedico.setSelectedIndex(1);
        this.cbxPideReceta.setSelectedIndex(1);
        this.cbxControlLote.setSelectedIndex(1);
        this.cbxIndicadorFraccion.setSelectedIndex(1);
        this.cbxDescripcionControl.setSelectedIndex(0);

        this.cmbImpuesto.setEnabled(false);
        this.cmbMoneda.setEnabled(false);
        this.cbxControlLote.setEnabled(false);
        this.cbxDescripcionControl.setEnabled(false);
        this.cbxIndicadorFraccion.setEnabled(false);
        this.cbxPideMedico.setEnabled(false);
        this.cbxPideReceta.setEnabled(false);
        this.cbxProdControlado.setEnabled(false);

    }


    private void ActivarCasillas() {
        pnlDastosIniciales.setEnabled(false);
        pnlSetDeCategoria.setEnabled(false);
        pnlControlDigemid.setEnabled(false);
        pnlDatosPrecio01.setEnabled(false);
        pnlBuscadorCategorias2.setEnabled(false);

        ECampos.setEditableTexto(this.pnlDastosIniciales, true, new Component[]{lblCodigo, lblDescrip, lblUnidad, lblLaboratorio}, false, "");
        ECampos.setEditableTexto(this.pnlSetDeCategoria, true, new Component[]{lblG1, lblG2, lblG3, lblG4, lblG5}, false, "");
        ECampos.setEditableTexto(this.pnlControlDigemid, true, new Component[]{lblPideMedico, lblProcedencia, lblProductoControlado, lblControlDigemid, lblFraccionamiento, lblFraccionamiento, lblControlReceta, lblControlLote, lblIndicadorF, lblCantidadF, lblUnidadF}, false, "");
        ECampos.setEditableTexto(this.pnlDatosPrecio01, true, new Component[]{lblTipoPrecio, lblImpuesto, lblMoneda, lblImpuesto1}, false, "");
        ECampos.setEditableTexto(this.pnlBuscadorCategorias2, true, new Component[]{lblCostoSol, lblPrecioSol, lblDescuento, lblPVP, lblFechaVigencia, lblFechaCreacion}, false, "");

        this.tblListado.setEnabled(false);
        this.tblListado.clearSelection();
        this.btnNuevo.setEnabled(false);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(true);
        this.btnCancelar.setEnabled(true);
        this.btnSalir.setEnabled(false);
        this.chbEstado.setEnabled(true);

        this.btnPrimero.setEnabled(false);
        this.btnUltimo.setEnabled(false);
        this.btnSiguiente.setEnabled(false);
        this.btnAnterior.setEnabled(false);
        this.rbTodos.setEnabled(false);
        this.rbAtivos.setEnabled(false);
        this.rbNoActivos.setEnabled(false);

        this.cmbImpuesto.setEnabled(true);
        this.cmbMoneda.setEnabled(true);
        this.cbxControlLote.setEnabled(true);
        this.cbxDescripcionControl.setEnabled(true);
        this.cbxIndicadorFraccion.setEnabled(true);
        this.cbxPideMedico.setEnabled(true);
        this.cbxPideReceta.setEnabled(true);
        this.cbxProdControlado.setEnabled(true);

        this.dteFechaVigencia.setEnabled(true);
        this.dteFechaCreacion.setEnabled(true);
        this.txtCantidadFraccion.setEnabled(true);
        this.txtUnidadFraccion.setEnabled(true);
        this.txtCantidadFraccion.setEditable(true);
        this.txtUnidadFraccion.setEditable(true);

        this.btnCodigoDeBarras.setEnabled(false);
        this.btnPrincipioActivo.setEnabled(false);
        this.btnParametrosReposicion.setEnabled(false);
        this.btnLote.setEnabled(false);
        this.btnRegistroSanitario.setEnabled(false);

    }

    private void DesActivarCasillas() {
        pnlDastosIniciales.setEnabled(true);
        pnlSetDeCategoria.setEnabled(true);
        pnlControlDigemid.setEnabled(true);
        pnlDatosPrecio01.setEnabled(true);
        pnlBuscadorCategorias2.setEnabled(true);
        ECampos.setEditableTexto(this.pnlDastosIniciales, false, new Component[]{lblCodigo, lblDescrip, lblUnidad, lblLaboratorio}, false, "");
        ECampos.setEditableTexto(this.pnlSetDeCategoria, false, new Component[]{lblG1, lblG2, lblG3, lblG4, lblG5}, false, "");
        ECampos.setEditableTexto(this.pnlControlDigemid, false, new Component[]{lblPideMedico, lblProcedencia, lblProductoControlado, lblControlDigemid, lblFraccionamiento, lblFraccionamiento, lblControlReceta, lblControlLote, lblIndicadorF, lblCantidadF, lblUnidadF}, false, "");
        ECampos.setEditableTexto(this.pnlDatosPrecio01, false, new Component[]{lblTipoPrecio, lblImpuesto, lblMoneda, lblImpuesto1}, false, "");
        ECampos.setEditableTexto(this.pnlBuscadorCategorias2, false, new Component[]{lblCostoSol, lblPrecioSol, lblDescuento, lblPVP, lblFechaVigencia, lblFechaCreacion}, false, "");

        this.tblListado.setEnabled(true);
        this.tblListado.clearSelection();
        this.btnNuevo.setEnabled(true);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.btnSalir.setEnabled(true);
        this.chbEstado.setEnabled(false);

        this.btnPrimero.setEnabled(true);
        this.btnUltimo.setEnabled(true);
        this.btnSiguiente.setEnabled(true);
        this.btnAnterior.setEnabled(true);
        this.rbTodos.setEnabled(true);
        this.rbAtivos.setEnabled(true);
        this.rbNoActivos.setEnabled(true);

        this.cmbImpuesto.setEnabled(false);
        this.cmbMoneda.setEnabled(false);
        this.cbxControlLote.setEnabled(false);
        this.cbxDescripcionControl.setEnabled(false);
        this.cbxIndicadorFraccion.setEnabled(false);
        this.cbxPideMedico.setEnabled(false);
        this.cbxPideReceta.setEnabled(false);
        this.cbxProdControlado.setEnabled(false);

        this.dteFechaVigencia.setEnabled(false);
        this.dteFechaCreacion.setEnabled(false);
        this.txtCantidadFraccion.setEnabled(false);
        this.txtUnidadFraccion.setEnabled(false);
        this.txtCantidadFraccion.setEditable(false);
        this.txtUnidadFraccion.setEditable(false);

        this.btnCodigoDeBarras.setEnabled(true);
        this.btnPrincipioActivo.setEnabled(true);
        this.btnParametrosReposicion.setEnabled(true);
        this.btnLote.setEnabled(true);
        this.btnRegistroSanitario.setEnabled(true);

        esActualizacion = false;
    }

    public boolean verficarCambios() {
        if (!this.txtCodigo.getText().equals(String.valueOf(cp.getProducto().getCoProducto()))) {
            return true;
        } else if (!this.txtDescripcion.getText().equals(String.valueOf(cp.getProducto().getDeProducto()))) {
            return true;
        } else if (!this.txtUnidad.getText().equals(String.valueOf(cp.getProducto().getDeUnidadProducto()))) {
            return true;
        } else if (!this.txtCodigoLaboratorio.getText().equals(String.valueOf(cp.getProducto().getCoLaboratorio()))) {
            return true;
        } else if (!this.txtCodigoG1.getText().equals(String.valueOf(cp.getProducto().getCoNivel01()))) {
            return true;
        } else if (!this.txtCodigoG2.getText().equals(String.valueOf(cp.getProducto().getCoNivel02()))) {
            return true;
        } else if (!this.txtCodigoG3.getText().equals(String.valueOf(cp.getProducto().getCoNivel03()))) {
            return true;
        } else if (!this.txtCodigoG4.getText().equals(String.valueOf(cp.getProducto().getCoNivel04()))) {
            return true;
        } else if (!this.txtCodigoG5.getText().equals(String.valueOf(cp.getProducto().getCoNivel05()))) {
            return true;
        } else if (!this.txtCodigoProcedencia.getText().equals(String.valueOf(cp.getProducto().getCoProcedencia()))) {
            return true;
        } else if (this.cbxProdControlado.getSelectedItem().toString().substring(0, 1).equals(cp.getProducto().getInControlado())) {
            return true;
        } else if (this.cbxPideMedico.getSelectedItem().toString().substring(0, 1).equals(cp.getProducto().getInPideMedico())) {
            return true;
        } else if (this.cbxPideReceta.getSelectedItem().toString().substring(0, 1).equals(cp.getProducto().getInRecetaMedica())) {
            return true;
        } else if (this.cbxControlLote.getSelectedItem().toString().substring(0, 1).equals(cp.getProducto().getInControlLote())) {
            return true;
        } else if (this.cbxIndicadorFraccion.getSelectedItem().toString().substring(0, 1).equals(cpl.getProductoLocal().getInProdFraccionado())) {
            return true;
        } else if (!this.txtCantidadFraccion.getText().equals(String.valueOf(cpl.getProductoLocal().getVaFraccion()))) {
            return true;
        } else if (!this.txtUnidadFraccion.getText().equals(String.valueOf(cpl.getProductoLocal().getDeUnidadFraccion()))) {
            return true;
        } else if (!this.txtTipoPrecio.getText().equals(String.valueOf(cp.getProducto().getNoTipoPrecio()))) {
            return true;
        } else if (this.cmbImpuesto.getSelectedItem().toString().substring(0, 2).equals(cp.getProducto().getCoImpuesto1())) {
            return true;
        } else if (controllerMoneda.getMoneda().getCoMoneda().equals(cp.getProducto().getCoMoneda())) {
            return true;
        } else if (!chbEstado.isSelected() != ("I".equals(cp.getProducto().getEsProducto()))) {
            return true;
        } else if (!this.txtCosto.getText().equals(String.valueOf(cp.getProducto().getVaPrecioCompra()))) {
            return true;
        } else if (!this.txtPrecio.getText().equals(String.valueOf(cpl.getProductoLocal().getVaVenta()))) {
            return true;
        } else if (!this.txtDescuento.getText().equals(String.valueOf(cpl.getProductoLocal().getPcDescuento1()))) {
            return true;
        } else if (!this.txtPreVtaPublico.getText().equals(String.valueOf(cpl.getProductoLocal().getCalculoPrecioPublico()))) {
            return true;
        } else if (this.dteFechaCreacion.getDate() != cp.getProducto().getFeCreaProducto()) {
            return true;
        } else if (this.dteFechaVigencia.getDate() != cp.getProducto().getFeVigencia()) {
            return true;
        }

        return false;
    }

    private boolean guardarActualizar() throws SQLException {
        cp.getProducto().setCoCompania(AtuxVariables.vCodigoCompania);
        cp.getProducto().setCoProducto(this.txtCodigo.getText());
        cp.getProducto().setDeProducto(txtDescripcion.getText().toUpperCase());
        cp.getProducto().setNuRevisionProducto("0");
        cp.getProducto().setDeCortaProducto(null);
        cp.getProducto().setCoFactorPrecio(null);
        cp.getProducto().setCoMoneda(controllerMoneda.getMoneda().getCoMoneda());
        cp.getProducto().setCoImpuesto1(controllerIGV.getImpuesto().getCoImpuesto());
        cp.getProducto().setCoImpuesto2(null);
        cp.getProducto().setCoLaboratorio(this.txtCodigoLaboratorio.getText());
        cp.getProducto().setCoLineaProducto(null);
        cp.getProducto().setCoGrupoProducto(null);
        cp.getProducto().setCoCategoriaCcr(null);
        cp.getProducto().setCoClasificacionIms(null);
        cp.getProducto().setCoCategoriaSb(null);
        cp.getProducto().setCoSubcategoriaSb(null);
        cp.getProducto().setDeUnidadProducto(this.txtUnidad.getText().toUpperCase());
        cp.getProducto().setVaUnidadMedida(0);
        cp.getProducto().setCoUnidadCompra(null);
        cp.getProducto().setCoUnidadVenta(null);
        cp.getProducto().setCoUnidadContenido(null);
        cp.getProducto().setVaUnidadContenido(0);
        cp.getProducto().setInProdFraccionable(cmbRetornaOpcion(cbxIndicadorFraccion));
        cp.getProducto().setVaPrecioCompra(Double.parseDouble(this.txtCosto.getText()));
        cp.getProducto().setPcBono(null);
        cp.getProducto().setInRecetaMedica(cmbRetornaOpcion(cbxPideReceta));

        if (chbEstado.isSelected()) {
            cp.getProducto().setEsProducto("A");
        } else {
            cp.getProducto().setEsProducto("I");
        }

        cp.getProducto().setCoClaseVenta(null);
        cp.getProducto().setCoGrupoProdErp(null);
        cp.getProducto().setCoLineaProdErp(null);
        cp.getProducto().setCoOtc(null);
        cp.getProducto().setCoGrupoTerapeutico(null);
        cp.getProducto().setCoGiro(null);
        cp.getProducto().setCoGrupoAnatomico(null);
        cp.getProducto().setCoFormaPresentacion(null);
        cp.getProducto().setCoAccionTerapeutica(null);
        cp.getProducto().setCoAccionFarmaceutica(null);
        cp.getProducto().setPcDescuentoBase(0);
        cp.getProducto().setCoViadm(null);
        cp.getProducto().setVaCostoProducto(Double.parseDouble(this.txtCosto.getText()));
        cp.getProducto().setDeDetallePresentacionLargo(null);
        cp.getProducto().setVaPrecioPublico(Double.parseDouble(txtPreVtaPublico.getText()));
        cp.getProducto().setCoProductoSap(null);
        cp.getProducto().setCoTipoConsumo(null);
        cp.getProducto().setCoComprador(null);
        cp.getProducto().setInReintegro(null);
        cp.getProducto().setInDescontinuado(null);
        cp.getProducto().setInControlado(cmbRetornaOpcion(cbxProdControlado));
        cp.getProducto().setCaEmpaque(null);
        cp.getProducto().setInConsignadoSap(null);
        cp.getProducto().setCoUnidadSap(null);
        cp.getProducto().setTiMaterialSap(null);
        cp.getProducto().setCoUnidadFmSap(null);
        cp.getProducto().setCoGrupoExt(null);
        cp.getProducto().setDePartidaArancelaria(null);
        cp.getProducto().setCoNivel01(this.txtCodigoG1.getText());
        cp.getProducto().setCoNivel02(this.txtCodigoG2.getText());
        cp.getProducto().setCoNivel03(this.txtCodigoG3.getText());
        cp.getProducto().setCoNivel04(this.txtCodigoG4.getText());
        cp.getProducto().setCoNivel05(this.txtCodigoG5.getText());
        cp.getProducto().setCoProcedencia(this.txtCodigoProcedencia.getText());
        cp.getProducto().setNoControlDigemid(null);
        cp.getProducto().setNoControlLote(null);
        cp.getProducto().setNoTipoPrecio(null);
        cp.getProducto().setFeVigencia(this.dteFechaVigencia.getDate());
        cp.getProducto().setFeUltVenta(null);
        cp.getProducto().setInControlLote(cmbRetornaOpcion(cbxControlLote));
        cp.getProducto().setInPideMedico(cmbRetornaOpcion(cbxPideMedico));

        cpl.getProductoLocal().setCoCompania(AtuxVariables.vCodigoCompania);
        cpl.getProductoLocal().setCoLocal(AtuxVariables.vCodigoLocal);
        cpl.getProductoLocal().setCoProducto(this.txtCodigo.getText());
        cpl.getProductoLocal().setNuRevisionProducto("0");
        cpl.getProductoLocal().setVaVenta(Double.parseDouble(this.txtPrecio.getText()));
        cpl.getProductoLocal().setCoUnidadVentaFraccion(null);

        cpl.getProductoLocal().setCoMoneda(((Moneda) this.cmbMoneda.getSelectedItem()).getCoMoneda());

        cpl.getProductoLocal().setCoUnidadContenido(null);
        cpl.getProductoLocal().setVaContenidoFraccion(null);
        cpl.getProductoLocal().setCoDescuento1(null);
        cpl.getProductoLocal().setPcDescuento1(Double.parseDouble(this.txtDescuento.getText()));
        cpl.getProductoLocal().setCoDescuento2(null);
        cpl.getProductoLocal().setPcDescuento2(null);
        cpl.getProductoLocal().setCoDescuento3(null);
        cpl.getProductoLocal().setPcDescuento3(null);
        cpl.getProductoLocal().setPcDctoVentaEspecial(null);
        cpl.getProductoLocal().setInProdInventario(null);
        cpl.getProductoLocal().setInProductoReponer(null);
        cpl.getProductoLocal().setCaStockReponer(null);
        cpl.getProductoLocal().setCaStockReponerCalcula(null);

        cpl.getProductoLocal().setNuDiasRotacionPromedio(null);
        cpl.getProductoLocal().setNuMinDiasReposicion(null);
        cpl.getProductoLocal().setNuMaxDiasReposicion(null);
        cpl.getProductoLocal().setCaMinProdExhibicion(null);
        cpl.getProductoLocal().setCaProdNoAtendido(null);
        cpl.getProductoLocal().setEsProdLocal(cp.getProducto().getEsProducto());
        cpl.getProductoLocal().setVaRotacion(null);
        cpl.getProductoLocal().setCaUltimoPedidoRep(null);
        cpl.getProductoLocal().setInReplica(null);
        cpl.getProductoLocal().setFeReplica(null);
        cpl.getProductoLocal().setNuSecReplica(null);
        cpl.getProductoLocal().setCaStkDisponiblePedRep(null);
        cpl.getProductoLocal().setCaStockTransitoPedRep(null);
        cpl.getProductoLocal().setCoCentroBeneficioSap(null);
        cpl.getProductoLocal().setVaTotalInventario(null);
        cpl.getProductoLocal().setVaCostoProducto(null);
        cpl.getProductoLocal().setVaPrecioPublico(null);
        cpl.getProductoLocal().setCoUnidadLocalSap(null);
        cpl.getProductoLocal().setInModCambio(null);
        cpl.getProductoLocal().setFeUltModPrecio(null);
        cpl.getProductoLocal().setTsDias(null);
        cpl.getProductoLocal().setTrDias(null);
        cpl.getProductoLocal().setIdCreaProdLocal(cp.getProducto().getIdCreaProducto());
        cpl.getProductoLocal().setFeCreaProdLocal(cp.getProducto().getFeCreaProducto());
        cpl.getProductoLocal().setPrimaryKey(new String[]{cpl.getProductoLocal().getCoCompania(), cpl.getProductoLocal().getCoLocal(), cpl.getProductoLocal().getCoProducto(), cpl.getProductoLocal().getNuRevisionProducto()});

        Boolean EstadoGuardar = false;

        cpl.getProductoLocal().setCaStockDisponible(CaStockDisponibleAnt);
        cpl.getProductoLocal().setCaStockTransito(0);
        cpl.getProductoLocal().setCaStockComprometido(0);
        cpl.getProductoLocal().setCaStockMinimo(0);
        cpl.getProductoLocal().setCaStockMaximo(0);

        if (!esActualizacion) {
            cp.getProducto().setVaPrecioPromedio(0.00);
            cp.getProducto().setVaBono(0.00);
            cpl.getProductoLocal().setCaStockDisponible(0);
            cpl.getProductoLocal().setCaStockTransito(0);
            cpl.getProductoLocal().setCaStockComprometido(0);
            cpl.getProductoLocal().setCaStockMinimo(0);
            cpl.getProductoLocal().setCaStockMaximo(0);
            cpl.getProductoLocal().setInProdFraccionado(cbxIndicadorFraccion.getSelectedItem().toString().substring(0, 1));
            cpl.getProductoLocal().setVaFraccion(Integer.parseInt(txtCantidadFraccion.getText()));
            cpl.getProductoLocal().setDeUnidadFraccion(txtUnidadFraccion.getText().toUpperCase());

            cp.getProducto().setCoProducto(cp.getNuevoCodigo());
            txtCodigo.setText(cp.getProducto().getCoProducto());
            cp.getProducto().setIdCreaProducto(AtuxVariables.vIdUsuario);
            cp.getProducto().setFeCreaProducto(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            cp.getProducto().setCoProducto(cp.getNuevoCodigo());
            EstadoGuardar = cp.guardarRegistro(cp.getProducto());
            if (EstadoGuardar == true) {
                cpl.getProductoLocal().setIdCreaProdLocal(AtuxVariables.vIdUsuario);
                cpl.getProductoLocal().setFeCreaProdLocal(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
                cpl.getProductoLocal().setCoProducto(cp.getProducto().getCoProducto());
                EstadoGuardar = cpl.guardarRegistroNuevo(cpl.getProductoLocal());
            }
        } else {

            cpl.getProductoLocal().setInProdFraccionado(InProdFraccionadoAnt);
            cpl.getProductoLocal().setVaFraccion(VaFraccionAnt);
            cpl.getProductoLocal().setDeUnidadFraccion(DeFraccionAnt);

            cp.getProducto().setIdModProducto(AtuxVariables.vIdUsuario);
            cp.getProducto().setFeModProducto(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));

            int act = cp.actualizarProductos(cp.getProducto());
            if (act == 1) {
                cpl.getProductoLocal().setIdModProdLocal(AtuxVariables.vIdUsuario);
                cpl.getProductoLocal().setFeModProdLocal(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
                act = cpl.actualizarProductosLocal(cpl.getProductoLocal());
                if (act == 1) {
                    String Guardar = cpl.getGuardaFraccion(AtuxVariables.vCodigoCompania, AtuxVariables.vCodigoLocal, cpl.getProductoLocal().getCoProducto(), cbxIndicadorFraccion.getSelectedItem().toString().substring(0, 1), Integer.parseInt(txtCantidadFraccion.getText()), txtUnidadFraccion.getText().toUpperCase());
                    if ("OK".equals(Guardar) || "No Hay Cambios".equals(Guardar)) {
                        EstadoGuardar = true;
                    } else {
                        EstadoGuardar = false;
                    }
                }
            }
        }

        return EstadoGuardar;
    }

    private Date FormatoFecha(String oldFecha) {
        Date Fecha = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = oldFecha;

        try {
            Fecha = formatter.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(IMaestroProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }


    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        pnlEntradasCategorias_G05 = new javax.swing.JPanel();
        pnlDastosIniciales = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblDescrip = new javax.swing.JLabel();
        lblUnidad = new javax.swing.JLabel();
        lblLaboratorio = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        txtDescripcion = new elaprendiz.gui.textField.TextField();
        txtUnidad = new elaprendiz.gui.textField.TextField();
        txtCodigoLaboratorio = new elaprendiz.gui.textField.TextField();
        txtDescripLaboratorio = new elaprendiz.gui.textField.TextField();
        lblLaboratorio1 = new javax.swing.JLabel();
        pnlSetDeCategoria = new javax.swing.JPanel();
        lblG1 = new javax.swing.JLabel();
        lblG2 = new javax.swing.JLabel();
        lblG3 = new javax.swing.JLabel();
        lblG4 = new javax.swing.JLabel();
        lblG5 = new javax.swing.JLabel();
        txtCodigoG1 = new elaprendiz.gui.textField.TextField();
        txtLineaComercial = new elaprendiz.gui.textField.TextField();
        txtCodigoG2 = new elaprendiz.gui.textField.TextField();
        txtDivision = new elaprendiz.gui.textField.TextField();
        txtCodigoG3 = new elaprendiz.gui.textField.TextField();
        txtSub_Division = new elaprendiz.gui.textField.TextField();
        txtCodigoG4 = new elaprendiz.gui.textField.TextField();
        txtFamilia = new elaprendiz.gui.textField.TextField();
        txtCodigoG5 = new elaprendiz.gui.textField.TextField();
        txtSub_Familia = new elaprendiz.gui.textField.TextField();
        pnlControlDigemid = new javax.swing.JPanel();
        lblProcedencia = new javax.swing.JLabel();
        lblProductoControlado = new javax.swing.JLabel();
        lblControlDigemid = new javax.swing.JLabel();
        lblPideMedico = new javax.swing.JLabel();
        lblControlReceta = new javax.swing.JLabel();
        lblControlLote = new javax.swing.JLabel();
        txtCodigoProcedencia = new elaprendiz.gui.textField.TextField();
        txtDescripProcedencia = new elaprendiz.gui.textField.TextField();
        txtPideLote2 = new elaprendiz.gui.textField.TextField();
        lblIndicadorF = new javax.swing.JLabel();
        lblFraccionamiento = new javax.swing.JLabel();
        lblCantidadF = new javax.swing.JLabel();
        txtCantidadFraccion = new elaprendiz.gui.textField.TextField();
        txtUnidadFraccion = new elaprendiz.gui.textField.TextField();
        lblUnidadF = new javax.swing.JLabel();
        cbxDescripcionControl = new javax.swing.JComboBox();
        cbxControlLote = new javax.swing.JComboBox();
        cbxProdControlado = new javax.swing.JComboBox();
        cbxIndicadorFraccion = new javax.swing.JComboBox();
        cbxPideReceta = new javax.swing.JComboBox();
        cbxPideMedico = new javax.swing.JComboBox();
        pnlDatosPrecio01 = new javax.swing.JPanel();
        lblTipoPrecio = new javax.swing.JLabel();
        lblImpuesto = new javax.swing.JLabel();
        lblMoneda = new javax.swing.JLabel();
        txtTipoPrecio = new elaprendiz.gui.textField.TextField();
        chbEstado = new javax.swing.JCheckBox();
        lblImpuesto1 = new javax.swing.JLabel();
        cmbImpuesto = new javax.swing.JComboBox();
        cmbMoneda = new javax.swing.JComboBox();
        pnlBuscadorCategorias2 = new javax.swing.JPanel();
        lblPVP = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();
        txtDescuento = new elaprendiz.gui.textField.TextField();
        txtPreVtaPublico = new elaprendiz.gui.textField.TextField();
        txtPrecio = new elaprendiz.gui.textField.TextField();
        lblCostoSol = new javax.swing.JLabel();
        txtCosto = new elaprendiz.gui.textField.TextField();
        lblPrecioSol = new javax.swing.JLabel();
        lblFechaCreacion = new javax.swing.JLabel();
        lblFechaVigencia = new javax.swing.JLabel();
        dteFechaCreacion = new com.toedter.calendar.JDateChooser();
        dteFechaVigencia = new com.toedter.calendar.JDateChooser();
        lblFechaCreacion1 = new javax.swing.JLabel();
        pnlBuscadorCategorias = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnBuscar = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        pnlAccionesCategorias = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        btnCodigoDeBarras = new elaprendiz.gui.button.ButtonRect();
        btnPrincipioActivo = new elaprendiz.gui.button.ButtonRect();
        btnParametrosReposicion = new elaprendiz.gui.button.ButtonRect();
        lblMensaje = new javax.swing.JLabel();
        btnRegistroSanitario = new elaprendiz.gui.button.ButtonRect();
        btnLote = new elaprendiz.gui.button.ButtonRect();

        setBorder(null);
        setResizable(true);
        setTitle("Maestro de Productos");
        setAlignmentX(0.2F);
        setAlignmentY(0.2F);
        setPreferredSize(new java.awt.Dimension(1100, 620));

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setPreferredSize(new java.awt.Dimension(1200, 500));
        panelImage1.setLayout(null);

        pnlEntradasCategorias_G05.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos de Maestro de Productos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlEntradasCategorias_G05.setAlignmentX(0.2F);
        pnlEntradasCategorias_G05.setAlignmentY(0.2F);
        pnlEntradasCategorias_G05.setEnabled(false);
        pnlEntradasCategorias_G05.setOpaque(false);
        pnlEntradasCategorias_G05.setPreferredSize(new java.awt.Dimension(748, 120));
        pnlEntradasCategorias_G05.setLayout(null);

        pnlDastosIniciales.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlDastosIniciales.setAlignmentX(0.2F);
        pnlDastosIniciales.setAlignmentY(0.2F);
        pnlDastosIniciales.setOpaque(false);
        pnlDastosIniciales.setPreferredSize(new java.awt.Dimension(575, 37));
        pnlDastosIniciales.setLayout(null);

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblCodigo.setText("Codigo:");
        pnlDastosIniciales.add(lblCodigo);
        lblCodigo.setBounds(6, 8, 47, 25);

        lblDescrip.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblDescrip.setText("Descripcion:");
        pnlDastosIniciales.add(lblDescrip);
        lblDescrip.setBounds(142, 8, 73, 25);

        lblUnidad.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblUnidad.setText("Unidad:");
        pnlDastosIniciales.add(lblUnidad);
        lblUnidad.setBounds(509, 8, 46, 25);

        lblLaboratorio.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblLaboratorio.setText("Laboratorio:");
        pnlDastosIniciales.add(lblLaboratorio);
        lblLaboratorio.setBounds(670, 8, 76, 25);

        txtCodigo.setEditable(false);
        txtCodigo.setAlignmentX(0.2F);
        txtCodigo.setAlignmentY(0.2F);
        txtCodigo.setDireccionDeSombra(30);
        txtCodigo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigo.setMinimumSize(new java.awt.Dimension(6, 20));
        txtCodigo.setName("pcodigo"); // NOI18N
        txtCodigo.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });
        pnlDastosIniciales.add(txtCodigo);
        txtCodigo.setBounds(57, 8, 83, 25);

        txtDescripcion.setEditable(false);
        txtDescripcion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDescripcion.setAlignmentX(0.2F);
        txtDescripcion.setAlignmentY(0.2F);
        txtDescripcion.setDireccionDeSombra(30);
        txtDescripcion.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDescripcion.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescripcion.setMinimumSize(new java.awt.Dimension(6, 20));
        txtDescripcion.setName("pdescrip"); // NOI18N
        txtDescripcion.setPreferredSize(new java.awt.Dimension(120, 25));
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });
        pnlDastosIniciales.add(txtDescripcion);
        txtDescripcion.setBounds(219, 8, 280, 25);

        txtUnidad.setEditable(false);
        txtUnidad.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUnidad.setAlignmentX(0.2F);
        txtUnidad.setAlignmentY(0.2F);
        txtUnidad.setDireccionDeSombra(30);
        txtUnidad.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtUnidad.setFont(new java.awt.Font("Arial", 0, 12));
        txtUnidad.setMinimumSize(new java.awt.Dimension(6, 20));
        txtUnidad.setName("pdescrip"); // NOI18N
        txtUnidad.setPreferredSize(new java.awt.Dimension(120, 25));
        txtUnidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUnidadKeyReleased(evt);
            }
        });
        pnlDastosIniciales.add(txtUnidad);
        txtUnidad.setBounds(560, 8, 100, 25);

        txtCodigoLaboratorio.setEditable(false);
        txtCodigoLaboratorio.setAlignmentX(0.2F);
        txtCodigoLaboratorio.setAlignmentY(0.2F);
        txtCodigoLaboratorio.setDireccionDeSombra(30);
        txtCodigoLaboratorio.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoLaboratorio.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoLaboratorio.setMinimumSize(new java.awt.Dimension(6, 20));
        txtCodigoLaboratorio.setName(""); // NOI18N
        txtCodigoLaboratorio.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoLaboratorio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoLaboratorioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoLaboratorioFocusLost(evt);
            }
        });
        txtCodigoLaboratorio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoLaboratorioKeyReleased(evt);
            }
        });
        pnlDastosIniciales.add(txtCodigoLaboratorio);
        txtCodigoLaboratorio.setBounds(748, 8, 59, 25);

        txtDescripLaboratorio.setEditable(false);
        txtDescripLaboratorio.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDescripLaboratorio.setDireccionDeSombra(30);
        txtDescripLaboratorio.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDescripLaboratorio.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescripLaboratorio.setMinimumSize(new java.awt.Dimension(6, 20));
        txtDescripLaboratorio.setName(""); // NOI18N
        txtDescripLaboratorio.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlDastosIniciales.add(txtDescripLaboratorio);
        txtDescripLaboratorio.setBounds(813, 8, 219, 25);

        lblLaboratorio1.setFont(new java.awt.Font("Tahoma", 1, 12));
        pnlDastosIniciales.add(lblLaboratorio1);
        lblLaboratorio1.setBounds(0, 0, 0, 0);

        pnlEntradasCategorias_G05.add(pnlDastosIniciales);
        pnlDastosIniciales.setBounds(16, 20, 1048, 40);

        pnlSetDeCategoria.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Set de Categoria", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlSetDeCategoria.setAlignmentX(0.2F);
        pnlSetDeCategoria.setAlignmentY(0.2F);
        pnlSetDeCategoria.setOpaque(false);
        pnlSetDeCategoria.setPreferredSize(new java.awt.Dimension(575, 37));
        pnlSetDeCategoria.setLayout(null);

        lblG1.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblG1.setText("G1-Linea Comercial:");
        pnlSetDeCategoria.add(lblG1);
        lblG1.setBounds(6, 20, 119, 23);

        lblG2.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblG2.setText("G2-Division:");
        pnlSetDeCategoria.add(lblG2);
        lblG2.setBounds(53, 56, 72, 23);

        lblG3.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblG3.setText("G3-Sub_Division:");
        pnlSetDeCategoria.add(lblG3);
        lblG3.setBounds(21, 92, 104, 23);

        lblG4.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblG4.setText("G4-Familia:");
        pnlSetDeCategoria.add(lblG4);
        lblG4.setBounds(60, 124, 65, 23);

        lblG5.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblG5.setText("G5-Sub_Familia:");
        pnlSetDeCategoria.add(lblG5);
        lblG5.setBounds(28, 160, 97, 23);

        txtCodigoG1.setEditable(false);
        txtCodigoG1.setDireccionDeSombra(30);
        txtCodigoG1.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoG1.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoG1.setMinimumSize(new java.awt.Dimension(6, 20));
        txtCodigoG1.setName(""); // NOI18N
        txtCodigoG1.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoG1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoG1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoG1FocusLost(evt);
            }
        });
        txtCodigoG1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoG1KeyReleased(evt);
            }
        });
        pnlSetDeCategoria.add(txtCodigoG1);
        txtCodigoG1.setBounds(129, 20, 75, 25);

        txtLineaComercial.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtLineaComercial.setDireccionDeSombra(30);
        txtLineaComercial.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtLineaComercial.setMinimumSize(new java.awt.Dimension(6, 20));
        txtLineaComercial.setName(""); // NOI18N
        txtLineaComercial.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlSetDeCategoria.add(txtLineaComercial);
        txtLineaComercial.setBounds(214, 20, 242, 25);

        txtCodigoG2.setEditable(false);
        txtCodigoG2.setDireccionDeSombra(30);
        txtCodigoG2.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoG2.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoG2.setMinimumSize(new java.awt.Dimension(6, 20));
        txtCodigoG2.setName(""); // NOI18N
        txtCodigoG2.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoG2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoG2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoG2FocusLost(evt);
            }
        });
        txtCodigoG2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoG2KeyReleased(evt);
            }
        });
        pnlSetDeCategoria.add(txtCodigoG2);
        txtCodigoG2.setBounds(129, 55, 75, 25);

        txtDivision.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDivision.setDireccionDeSombra(30);
        txtDivision.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDivision.setMinimumSize(new java.awt.Dimension(6, 20));
        txtDivision.setName(""); // NOI18N
        txtDivision.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlSetDeCategoria.add(txtDivision);
        txtDivision.setBounds(214, 55, 242, 25);

        txtCodigoG3.setEditable(false);
        txtCodigoG3.setDireccionDeSombra(30);
        txtCodigoG3.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoG3.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoG3.setMinimumSize(new java.awt.Dimension(6, 20));
        txtCodigoG3.setName(""); // NOI18N
        txtCodigoG3.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoG3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoG3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoG3FocusLost(evt);
            }
        });
        txtCodigoG3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoG3KeyReleased(evt);
            }
        });
        pnlSetDeCategoria.add(txtCodigoG3);
        txtCodigoG3.setBounds(129, 91, 75, 25);

        txtSub_Division.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtSub_Division.setDireccionDeSombra(30);
        txtSub_Division.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtSub_Division.setMinimumSize(new java.awt.Dimension(6, 20));
        txtSub_Division.setName(""); // NOI18N
        txtSub_Division.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlSetDeCategoria.add(txtSub_Division);
        txtSub_Division.setBounds(214, 91, 242, 25);

        txtCodigoG4.setEditable(false);
        txtCodigoG4.setDireccionDeSombra(30);
        txtCodigoG4.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoG4.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoG4.setMinimumSize(new java.awt.Dimension(6, 20));
        txtCodigoG4.setName(""); // NOI18N
        txtCodigoG4.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoG4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoG4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoG4FocusLost(evt);
            }
        });
        txtCodigoG4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoG4KeyReleased(evt);
            }
        });
        pnlSetDeCategoria.add(txtCodigoG4);
        txtCodigoG4.setBounds(129, 123, 75, 25);

        txtFamilia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFamilia.setDireccionDeSombra(30);
        txtFamilia.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtFamilia.setMinimumSize(new java.awt.Dimension(6, 20));
        txtFamilia.setName(""); // NOI18N
        txtFamilia.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlSetDeCategoria.add(txtFamilia);
        txtFamilia.setBounds(214, 123, 242, 25);

        txtCodigoG5.setEditable(false);
        txtCodigoG5.setDireccionDeSombra(30);
        txtCodigoG5.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoG5.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoG5.setMinimumSize(new java.awt.Dimension(6, 20));
        txtCodigoG5.setName(""); // NOI18N
        txtCodigoG5.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoG5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoG5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoG5FocusLost(evt);
            }
        });
        txtCodigoG5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoG5KeyReleased(evt);
            }
        });
        pnlSetDeCategoria.add(txtCodigoG5);
        txtCodigoG5.setBounds(129, 159, 75, 25);

        txtSub_Familia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtSub_Familia.setDireccionDeSombra(30);
        txtSub_Familia.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtSub_Familia.setMinimumSize(new java.awt.Dimension(6, 20));
        txtSub_Familia.setName(""); // NOI18N
        txtSub_Familia.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlSetDeCategoria.add(txtSub_Familia);
        txtSub_Familia.setBounds(214, 159, 242, 25);

        pnlEntradasCategorias_G05.add(pnlSetDeCategoria);
        pnlSetDeCategoria.setBounds(16, 65, 474, 197);

        pnlControlDigemid.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Control Digemid", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlControlDigemid.setOpaque(false);
        pnlControlDigemid.setPreferredSize(new java.awt.Dimension(575, 37));
        pnlControlDigemid.setLayout(null);

        lblProcedencia.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblProcedencia.setText("Procedencia:");
        lblProcedencia.setAlignmentX(0.1F);
        lblProcedencia.setAlignmentY(0.1F);
        pnlControlDigemid.add(lblProcedencia);
        lblProcedencia.setBounds(49, 21, 77, 15);

        lblProductoControlado.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblProductoControlado.setText("Prod. Controlado:");
        lblProductoControlado.setAlignmentX(0.1F);
        lblProductoControlado.setAlignmentY(0.1F);
        pnlControlDigemid.add(lblProductoControlado);
        lblProductoControlado.setBounds(16, 55, 110, 15);

        lblControlDigemid.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblControlDigemid.setText("Control Digemid:");
        lblControlDigemid.setAlignmentX(0.1F);
        lblControlDigemid.setAlignmentY(0.1F);
        pnlControlDigemid.add(lblControlDigemid);
        lblControlDigemid.setBounds(23, 105, 103, 15);

        lblPideMedico.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblPideMedico.setText("Médico");
        lblPideMedico.setAlignmentY(0.0F);
        pnlControlDigemid.add(lblPideMedico);
        lblPideMedico.setBounds(146, 80, 50, 15);

        lblControlReceta.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblControlReceta.setText("Receta");
        lblControlReceta.setAlignmentY(0.0F);
        pnlControlDigemid.add(lblControlReceta);
        lblControlReceta.setBounds(213, 80, 42, 15);

        lblControlLote.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblControlLote.setText("Ctrol Lote");
        lblControlLote.setAlignmentY(0.0F);
        pnlControlDigemid.add(lblControlLote);
        lblControlLote.setBounds(273, 80, 62, 15);

        txtCodigoProcedencia.setEditable(false);
        txtCodigoProcedencia.setAlignmentX(0.2F);
        txtCodigoProcedencia.setAlignmentY(0.2F);
        txtCodigoProcedencia.setDireccionDeSombra(30);
        txtCodigoProcedencia.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoProcedencia.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoProcedencia.setMinimumSize(new java.awt.Dimension(6, 20));
        txtCodigoProcedencia.setName(""); // NOI18N
        txtCodigoProcedencia.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoProcedencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoProcedenciaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoProcedenciaFocusLost(evt);
            }
        });
        txtCodigoProcedencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProcedenciaKeyReleased(evt);
            }
        });
        pnlControlDigemid.add(txtCodigoProcedencia);
        txtCodigoProcedencia.setBounds(137, 16, 70, 25);

        txtDescripProcedencia.setEditable(false);
        txtDescripProcedencia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDescripProcedencia.setAlignmentX(0.2F);
        txtDescripProcedencia.setAlignmentY(0.2F);
        txtDescripProcedencia.setDireccionDeSombra(30);
        txtDescripProcedencia.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDescripProcedencia.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescripProcedencia.setMinimumSize(new java.awt.Dimension(6, 20));
        txtDescripProcedencia.setName(""); // NOI18N
        txtDescripProcedencia.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlControlDigemid.add(txtDescripProcedencia);
        txtDescripProcedencia.setBounds(213, 16, 215, 25);

        txtPideLote2.setEditable(false);
        txtPideLote2.setAlignmentX(0.2F);
        txtPideLote2.setAlignmentY(0.2F);
        txtPideLote2.setDireccionDeSombra(30);
        txtPideLote2.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtPideLote2.setFont(new java.awt.Font("Arial", 0, 12));
        txtPideLote2.setMinimumSize(new java.awt.Dimension(6, 20));
        txtPideLote2.setName(""); // NOI18N
        txtPideLote2.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlControlDigemid.add(txtPideLote2);
        txtPideLote2.setBounds(341, 99, 89, 25);

        lblIndicadorF.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblIndicadorF.setText("Indicador");
        lblIndicadorF.setAlignmentX(0.2F);
        lblIndicadorF.setAlignmentY(0.2F);
        pnlControlDigemid.add(lblIndicadorF);
        lblIndicadorF.setBounds(138, 130, 58, 15);

        lblFraccionamiento.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblFraccionamiento.setText("Fracccionamiento:");
        lblFraccionamiento.setAlignmentX(0.1F);
        lblFraccionamiento.setAlignmentY(0.1F);
        pnlControlDigemid.add(lblFraccionamiento);
        lblFraccionamiento.setBounds(17, 156, 109, 15);

        lblCantidadF.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblCantidadF.setText("Cantidad");
        lblCantidadF.setAlignmentX(0.2F);
        lblCantidadF.setAlignmentY(0.2F);
        pnlControlDigemid.add(lblCantidadF);
        lblCantidadF.setBounds(208, 130, 55, 15);

        txtCantidadFraccion.setEditable(false);
        txtCantidadFraccion.setAlignmentX(0.2F);
        txtCantidadFraccion.setAlignmentY(0.2F);
        txtCantidadFraccion.setDireccionDeSombra(30);
        txtCantidadFraccion.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCantidadFraccion.setFont(new java.awt.Font("Arial", 0, 12));
        txtCantidadFraccion.setMinimumSize(new java.awt.Dimension(6, 20));
        txtCantidadFraccion.setName(""); // NOI18N
        txtCantidadFraccion.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCantidadFraccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadFraccionKeyReleased(evt);
            }
        });
        pnlControlDigemid.add(txtCantidadFraccion);
        txtCantidadFraccion.setBounds(206, 150, 60, 25);

        txtUnidadFraccion.setEditable(false);
        txtUnidadFraccion.setAlignmentX(0.2F);
        txtUnidadFraccion.setAlignmentY(0.2F);
        txtUnidadFraccion.setDireccionDeSombra(30);
        txtUnidadFraccion.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtUnidadFraccion.setFont(new java.awt.Font("Arial", 0, 12));
        txtUnidadFraccion.setMinimumSize(new java.awt.Dimension(6, 20));
        txtUnidadFraccion.setName(""); // NOI18N
        txtUnidadFraccion.setPreferredSize(new java.awt.Dimension(120, 25));
        txtUnidadFraccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUnidadFraccionKeyReleased(evt);
            }
        });
        pnlControlDigemid.add(txtUnidadFraccion);
        txtUnidadFraccion.setBounds(272, 150, 159, 25);

        lblUnidadF.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblUnidadF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUnidadF.setText("Unidad Fracción");
        lblUnidadF.setAlignmentX(0.2F);
        lblUnidadF.setAlignmentY(0.2F);
        pnlControlDigemid.add(lblUnidadF);
        lblUnidadF.setBounds(274, 130, 110, 15);

        cbxDescripcionControl.setFont(new java.awt.Font("Tahoma", 0, 12));
        cbxDescripcionControl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlControlDigemid.add(cbxDescripcionControl);
        cbxDescripcionControl.setBounds(214, 50, 214, 24);

        cbxControlLote.setFont(new java.awt.Font("Tahoma", 1, 12));
        cbxControlLote.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cbxControlLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxControlLoteActionPerformed(evt);
            }
        });
        pnlControlDigemid.add(cbxControlLote);
        cbxControlLote.setBounds(273, 100, 60, 24);

        cbxProdControlado.setFont(new java.awt.Font("Tahoma", 1, 12));
        cbxProdControlado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlControlDigemid.add(cbxProdControlado);
        cbxProdControlado.setBounds(138, 50, 68, 24);

        cbxIndicadorFraccion.setFont(new java.awt.Font("Tahoma", 1, 12));
        cbxIndicadorFraccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cbxIndicadorFraccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbxIndicadorFraccionFocusLost(evt);
            }
        });
        cbxIndicadorFraccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbxIndicadorFraccionKeyReleased(evt);
            }
        });
        pnlControlDigemid.add(cbxIndicadorFraccion);
        cbxIndicadorFraccion.setBounds(138, 151, 60, 24);

        cbxPideReceta.setFont(new java.awt.Font("Tahoma", 1, 12));
        cbxPideReceta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlControlDigemid.add(cbxPideReceta);
        cbxPideReceta.setBounds(207, 100, 56, 24);

        cbxPideMedico.setFont(new java.awt.Font("Tahoma", 1, 12));
        cbxPideMedico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlControlDigemid.add(cbxPideMedico);
        cbxPideMedico.setBounds(138, 100, 60, 24);

        pnlEntradasCategorias_G05.add(pnlControlDigemid);
        pnlControlDigemid.setBounds(496, 65, 447, 197);

        pnlDatosPrecio01.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlDatosPrecio01.setAlignmentX(0.2F);
        pnlDatosPrecio01.setAlignmentY(0.2F);
        pnlDatosPrecio01.setOpaque(false);
        pnlDatosPrecio01.setPreferredSize(new java.awt.Dimension(575, 37));

        lblTipoPrecio.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTipoPrecio.setText("Tp. Precio");
        lblTipoPrecio.setAlignmentX(0.2F);
        lblTipoPrecio.setAlignmentY(0.2F);

        lblImpuesto.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblImpuesto.setText("Imp. IGV");
        lblImpuesto.setAlignmentX(0.2F);
        lblImpuesto.setAlignmentY(0.2F);

        lblMoneda.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblMoneda.setText("Moneda S/.");
        lblMoneda.setAlignmentX(0.2F);
        lblMoneda.setAlignmentY(0.2F);

        txtTipoPrecio.setEditable(false);
        txtTipoPrecio.setAlignmentX(0.2F);
        txtTipoPrecio.setAlignmentY(0.2F);
        txtTipoPrecio.setDireccionDeSombra(30);
        txtTipoPrecio.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtTipoPrecio.setFont(new java.awt.Font("Arial", 0, 12));
        txtTipoPrecio.setMinimumSize(new java.awt.Dimension(6, 20));
        txtTipoPrecio.setName("pcodigo"); // NOI18N
        txtTipoPrecio.setPreferredSize(new java.awt.Dimension(120, 25));
        txtTipoPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTipoPrecioKeyReleased(evt);
            }
        });

        chbEstado.setBackground(new java.awt.Color(51, 153, 255));
        chbEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        chbEstado.setSelected(true);
        chbEstado.setText("Activo");
        chbEstado.setAlignmentX(0.2F);
        chbEstado.setAlignmentY(0.2F);
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

        lblImpuesto1.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblImpuesto1.setText("Estado");
        lblImpuesto1.setAlignmentX(0.2F);
        lblImpuesto1.setAlignmentY(0.2F);

        cmbImpuesto.setFont(new java.awt.Font("Tahoma", 0, 12));
        cmbImpuesto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmbMoneda.setFont(new java.awt.Font("Tahoma", 0, 12));
        cmbMoneda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlDatosPrecio01Layout = new javax.swing.GroupLayout(pnlDatosPrecio01);
        pnlDatosPrecio01.setLayout(pnlDatosPrecio01Layout);
        pnlDatosPrecio01Layout.setHorizontalGroup(
            pnlDatosPrecio01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosPrecio01Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTipoPrecio))
            .addGroup(pnlDatosPrecio01Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtTipoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlDatosPrecio01Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblImpuesto))
            .addGroup(pnlDatosPrecio01Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(cmbImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlDatosPrecio01Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblMoneda))
            .addGroup(pnlDatosPrecio01Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(cmbMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlDatosPrecio01Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblImpuesto1))
            .addGroup(pnlDatosPrecio01Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlDatosPrecio01Layout.setVerticalGroup(
            pnlDatosPrecio01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosPrecio01Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(lblTipoPrecio)
                .addGap(5, 5, 5)
                .addComponent(txtTipoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblImpuesto)
                .addGap(10, 10, 10)
                .addComponent(cmbImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lblMoneda)
                .addGap(12, 12, 12)
                .addComponent(cmbMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblImpuesto1)
                .addGap(2, 2, 2)
                .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlEntradasCategorias_G05.add(pnlDatosPrecio01);
        pnlDatosPrecio01.setBounds(949, 75, 117, 232);

        pnlBuscadorCategorias2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlBuscadorCategorias2.setOpaque(false);
        pnlBuscadorCategorias2.setPreferredSize(new java.awt.Dimension(575, 37));

        lblPVP.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblPVP.setText("PVP S/.");

        lblDescuento.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblDescuento.setText("Desc. %");

        txtDescuento.setEditable(false);
        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDescuento.setDireccionDeSombra(30);
        txtDescuento.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDescuento.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescuento.setMinimumSize(new java.awt.Dimension(6, 20));
        txtDescuento.setName(""); // NOI18N
        txtDescuento.setPreferredSize(new java.awt.Dimension(120, 25));
        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyReleased(evt);
            }
        });

        txtPreVtaPublico.setEditable(false);
        txtPreVtaPublico.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPreVtaPublico.setDireccionDeSombra(30);
        txtPreVtaPublico.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtPreVtaPublico.setFont(new java.awt.Font("Arial", 0, 12));
        txtPreVtaPublico.setMinimumSize(new java.awt.Dimension(6, 20));
        txtPreVtaPublico.setName(""); // NOI18N
        txtPreVtaPublico.setPreferredSize(new java.awt.Dimension(120, 25));
        txtPreVtaPublico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPreVtaPublicoKeyReleased(evt);
            }
        });

        txtPrecio.setEditable(false);
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrecio.setDireccionDeSombra(30);
        txtPrecio.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtPrecio.setFont(new java.awt.Font("Arial", 0, 12));
        txtPrecio.setMinimumSize(new java.awt.Dimension(6, 20));
        txtPrecio.setName(""); // NOI18N
        txtPrecio.setPreferredSize(new java.awt.Dimension(120, 25));
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioKeyReleased(evt);
            }
        });

        lblCostoSol.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblCostoSol.setText("Costo S/.");

        txtCosto.setEditable(false);
        txtCosto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCosto.setDireccionDeSombra(30);
        txtCosto.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCosto.setFont(new java.awt.Font("Arial", 0, 12));
        txtCosto.setMinimumSize(new java.awt.Dimension(6, 20));
        txtCosto.setName(""); // NOI18N
        txtCosto.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoKeyReleased(evt);
            }
        });

        lblPrecioSol.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblPrecioSol.setText("Precio S/.");

        lblFechaCreacion.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblFechaCreacion.setText("Fec.Crea");

        lblFechaVigencia.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblFechaVigencia.setText("Fec.Vigencia");

        dteFechaCreacion.setBackground(new java.awt.Color(0, 0, 0));
        dteFechaCreacion.setForeground(new java.awt.Color(255, 0, 0));
        dteFechaCreacion.setDate(Calendar.getInstance().getTime());
        dteFechaCreacion.setFont(new java.awt.Font("Tahoma", 1, 13));
        dteFechaCreacion.setPreferredSize(new java.awt.Dimension(120, 22));
        dteFechaCreacion.setRequestFocusEnabled(false);
        dteFechaCreacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dteFechaCreacionKeyReleased(evt);
            }
        });

        dteFechaVigencia.setBackground(new java.awt.Color(0, 0, 0));
        dteFechaVigencia.setForeground(new java.awt.Color(255, 0, 0));
        dteFechaVigencia.setDate(Calendar.getInstance().getTime());
        dteFechaVigencia.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dteFechaVigencia.setPreferredSize(new java.awt.Dimension(120, 22));
        dteFechaVigencia.setRequestFocusEnabled(false);

        lblFechaCreacion1.setFont(new java.awt.Font("Tahoma", 1, 12));

        javax.swing.GroupLayout pnlBuscadorCategorias2Layout = new javax.swing.GroupLayout(pnlBuscadorCategorias2);
        pnlBuscadorCategorias2.setLayout(pnlBuscadorCategorias2Layout);
        pnlBuscadorCategorias2Layout.setHorizontalGroup(
            pnlBuscadorCategorias2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscadorCategorias2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblCostoSol)
                .addGap(1, 1, 1)
                .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblPrecioSol)
                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblDescuento)
                .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblPVP)
                .addGap(3, 3, 3)
                .addComponent(txtPreVtaPublico, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblFechaVigencia)
                .addGap(7, 7, 7)
                .addComponent(dteFechaVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblFechaCreacion)
                .addGap(4, 4, 4)
                .addComponent(dteFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlBuscadorCategorias2Layout.setVerticalGroup(
            pnlBuscadorCategorias2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscadorCategorias2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lblCostoSol, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlBuscadorCategorias2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlBuscadorCategorias2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lblPrecioSol, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlBuscadorCategorias2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlBuscadorCategorias2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lblDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlBuscadorCategorias2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlBuscadorCategorias2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lblPVP, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlBuscadorCategorias2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(txtPreVtaPublico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlBuscadorCategorias2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lblFechaVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlBuscadorCategorias2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(dteFechaVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlBuscadorCategorias2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lblFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlBuscadorCategorias2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(dteFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlEntradasCategorias_G05.add(pnlBuscadorCategorias2);
        pnlBuscadorCategorias2.setBounds(16, 270, 927, 39);

        panelImage1.add(pnlEntradasCategorias_G05);
        pnlEntradasCategorias_G05.setBounds(10, 0, 1078, 320);

        pnlBuscadorCategorias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlBuscadorCategorias.setOpaque(false);
        pnlBuscadorCategorias.setPreferredSize(new java.awt.Dimension(575, 37));
        pnlBuscadorCategorias.setLayout(null);

        btnPrimero.setBackground(new java.awt.Color(102, 204, 0));
        btnPrimero.setText("<<");
        btnPrimero.setEnabled(false);
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias.add(btnPrimero);
        btnPrimero.setBounds(6, 7, 48, 25);

        btnAnterior.setBackground(new java.awt.Color(102, 204, 0));
        btnAnterior.setText("<");
        btnAnterior.setEnabled(false);
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias.add(btnAnterior);
        btnAnterior.setBounds(59, 7, 40, 25);

        btnBuscar.setBackground(new java.awt.Color(102, 204, 0));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias.add(btnBuscar);
        btnBuscar.setBounds(104, 7, 81, 25);

        btnSiguiente.setBackground(new java.awt.Color(102, 204, 0));
        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias.add(btnSiguiente);
        btnSiguiente.setBounds(190, 7, 40, 25);

        btnUltimo.setBackground(new java.awt.Color(104, 204, 0));
        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias.add(btnUltimo);
        btnUltimo.setBounds(235, 7, 48, 25);

        rbTodos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbTodos);
        rbTodos.setFont(new java.awt.Font("Arial", 1, 14));
        rbTodos.setForeground(new java.awt.Color(255, 255, 255));
        rbTodos.setText("Todos");
        rbTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTodosActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias.add(rbTodos);
        rbTodos.setBounds(288, 7, 69, 25);

        rbAtivos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbAtivos);
        rbAtivos.setFont(new java.awt.Font("Arial", 1, 14));
        rbAtivos.setForeground(new java.awt.Color(255, 255, 255));
        rbAtivos.setSelected(true);
        rbAtivos.setText("Activos");
        rbAtivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAtivosActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias.add(rbAtivos);
        rbAtivos.setBounds(362, 7, 77, 25);

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
        pnlBuscadorCategorias.add(rbNoActivos);
        rbNoActivos.setBounds(444, 7, 101, 25);

        panelImage1.add(pnlBuscadorCategorias);
        pnlBuscadorCategorias.setBounds(198, 326, 551, 37);

        pnlAccionesCategorias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlAccionesCategorias.setOpaque(false);
        pnlAccionesCategorias.setPreferredSize(new java.awt.Dimension(550, 50));
        pnlAccionesCategorias.setLayout(null);

        btnNuevo.setBackground(new java.awt.Color(0, 153, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        pnlAccionesCategorias.add(btnNuevo);
        btnNuevo.setBounds(6, 6, 78, 25);

        btnModificar.setBackground(new java.awt.Color(51, 153, 255));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        pnlAccionesCategorias.add(btnModificar);
        btnModificar.setBounds(90, 6, 90, 25);

        btnGuardar.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlAccionesCategorias.add(btnGuardar);
        btnGuardar.setBounds(185, 6, 89, 25);

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlAccionesCategorias.add(btnCancelar);
        btnCancelar.setBounds(280, 6, 94, 25);

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnlAccionesCategorias.add(btnSalir);
        btnSalir.setBounds(380, 6, 86, 25);
        pnlAccionesCategorias.add(jLabel2);
        jLabel2.setBounds(0, 0, 0, 0);

        panelImage1.add(pnlAccionesCategorias);
        pnlAccionesCategorias.setBounds(10, 545, 475, 37);

        tblListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CODIGO", "DESCRIPCION", "UNIDAD PRODUCTO", "LABORATORIO"
            }
        ));
        tblListado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblListadoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblListado);

        panelImage1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 369, 1078, 168);

        btnCodigoDeBarras.setBackground(new java.awt.Color(0, 204, 0));
        btnCodigoDeBarras.setText("CODIGO DE BARRA");
        btnCodigoDeBarras.setAlignmentX(0.1F);
        btnCodigoDeBarras.setAlignmentY(0.1F);
        btnCodigoDeBarras.setFont(new java.awt.Font("Arial", 1, 12));
        btnCodigoDeBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCodigoDeBarrasActionPerformed(evt);
            }
        });
        panelImage1.add(btnCodigoDeBarras);
        btnCodigoDeBarras.setBounds(495, 547, 120, 32);

        btnPrincipioActivo.setBackground(new java.awt.Color(0, 204, 0));
        btnPrincipioActivo.setText("INSUMOS");
        btnPrincipioActivo.setAlignmentX(0.1F);
        btnPrincipioActivo.setAlignmentY(0.1F);
        btnPrincipioActivo.setFont(new java.awt.Font("Arial", 1, 12));
        btnPrincipioActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrincipioActivoActionPerformed(evt);
            }
        });
        panelImage1.add(btnPrincipioActivo);
        btnPrincipioActivo.setBounds(622, 547, 120, 32);

        btnParametrosReposicion.setBackground(new java.awt.Color(0, 204, 0));
        btnParametrosReposicion.setText("REPOSICION");
        btnParametrosReposicion.setAlignmentX(0.1F);
        btnParametrosReposicion.setAlignmentY(0.1F);
        btnParametrosReposicion.setFont(new java.awt.Font("Arial", 1, 12));
        btnParametrosReposicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParametrosReposicionActionPerformed(evt);
            }
        });
        panelImage1.add(btnParametrosReposicion);
        btnParametrosReposicion.setBounds(752, 547, 104, 32);

        lblMensaje.setFont(new java.awt.Font("Tahoma", 1, 18));
        lblMensaje.setForeground(new java.awt.Color(255, 255, 255));
        lblMensaje.setText("F1 para ver más datos");
        panelImage1.add(lblMensaje);
        lblMensaje.setBounds(771, 335, 197, 22);

        btnRegistroSanitario.setBackground(new java.awt.Color(0, 204, 0));
        btnRegistroSanitario.setText("REG. SANITARIO");
        btnRegistroSanitario.setAlignmentX(0.1F);
        btnRegistroSanitario.setAlignmentY(0.1F);
        btnRegistroSanitario.setFont(new java.awt.Font("Arial", 1, 12));
        btnRegistroSanitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroSanitarioActionPerformed(evt);
            }
        });
        panelImage1.add(btnRegistroSanitario);
        btnRegistroSanitario.setBounds(980, 547, 110, 32);

        btnLote.setBackground(new java.awt.Color(0, 204, 0));
        btnLote.setText("LOTE");
        btnLote.setAlignmentX(0.1F);
        btnLote.setAlignmentY(0.1F);
        btnLote.setFont(new java.awt.Font("Arial", 1, 12));
        btnLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoteActionPerformed(evt);
            }
        });
        panelImage1.add(btnLote);
        btnLote.setBounds(862, 547, 110, 32);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 1102, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoG1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoG1FocusGained
        if (txtCodigoG1.isEditable()) lblMensaje.setVisible(true);
    }//GEN-LAST:event_txtCodigoG1FocusGained

    private void txtCodigoG1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoG1FocusLost
        lblMensaje.setVisible(false);
    }//GEN-LAST:event_txtCodigoG1FocusLost

    private void txtCodigoG2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoG2FocusGained
        if (txtCodigoG2.isEditable()) lblMensaje.setVisible(true);
    }//GEN-LAST:event_txtCodigoG2FocusGained

    private void txtCodigoG2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoG2FocusLost
        lblMensaje.setVisible(false);
    }//GEN-LAST:event_txtCodigoG2FocusLost

    private void txtCodigoG3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoG3FocusGained
        if (txtCodigoG3.isEditable()) lblMensaje.setVisible(true);
    }//GEN-LAST:event_txtCodigoG3FocusGained

    private void txtCodigoG3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoG3FocusLost
        lblMensaje.setVisible(false);
    }//GEN-LAST:event_txtCodigoG3FocusLost

    private void txtCodigoG4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoG4FocusGained
        if (txtCodigoG4.isEditable()) lblMensaje.setVisible(true);
    }//GEN-LAST:event_txtCodigoG4FocusGained

    private void txtCodigoG4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoG4FocusLost
        lblMensaje.setVisible(false);
    }//GEN-LAST:event_txtCodigoG4FocusLost

    private void txtCodigoG5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoG5FocusGained
        if (txtCodigoG5.isEditable()) lblMensaje.setVisible(true);
    }//GEN-LAST:event_txtCodigoG5FocusGained

    private void txtCodigoG5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoG5FocusLost
        lblMensaje.setVisible(false);
    }//GEN-LAST:event_txtCodigoG5FocusLost

    private void txtCodigoLaboratorioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoLaboratorioFocusGained
        if (txtCodigoLaboratorio.isEditable()) lblMensaje.setVisible(true);
    }//GEN-LAST:event_txtCodigoLaboratorioFocusGained

    private void txtCodigoLaboratorioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoLaboratorioFocusLost
        lblMensaje.setVisible(false);
    }//GEN-LAST:event_txtCodigoLaboratorioFocusLost

    private void txtCodigoProcedenciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoProcedenciaFocusGained
        if (txtCodigoProcedencia.isEditable()) lblMensaje.setVisible(true);
    }//GEN-LAST:event_txtCodigoProcedenciaFocusGained

    private void txtCodigoProcedenciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoProcedenciaFocusLost
        lblMensaje.setVisible(false);
    }//GEN-LAST:event_txtCodigoProcedenciaFocusLost

    private void txtCodigoG1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoG1KeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_F1:
                if (!txtCodigoG1.isEditable()) return;
                txtCodigoG1.setText("");
                txtCodigoG2.setText("");
                txtCodigoG3.setText("");
                txtCodigoG4.setText("");
                txtCodigoG5.setText("");
                txtLineaComercial.setText("");
                txtDivision.setText("");
                txtSub_Division.setText("");
                txtFamilia.setText("");
                txtSub_Familia.setText("");

                int tipoSeleccionG = 1;
                BuscarG1_LineaComercial pvc = new BuscarG1_LineaComercial(tipoSeleccionG);

                JLabel aviso = pvc.getAviso();

                String msj = (tipoSeleccionG == -1 ? "Mostrando Listado de Linea Comercial - G1" : (tipoSeleccionG == 1 ? "Mostrando Listado de Linea Comercial - G1 Activos" : "Mostrando Listado de Linea Comercial - G1 No activos"));
                JOptionPane.showInternalOptionDialog(this, pvc, msj, JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso}, null);

                if (pvc.getG1_LineaComercial() != null) {
                    CG1_LineaComercial cpLC = new CG1_LineaComercial();
                    cpLC.setG1_LineaComercial(pvc.getG1_LineaComercial());
                    this.txtCodigoG1.setText(cpLC.getG1_LineaComercial().getCoNivel01());
                    this.txtLineaComercial.setText(cpLC.getG1_LineaComercial().getDeLineaProdErp());
                }
                break;
            case KeyEvent.VK_ENTER:
                txtCodigoG1.setText(txtCodigoG1.getText().toUpperCase());
                int Ubicado = BuscarG1(txtCodigoG1.getText());
                if (Ubicado == 1) {
                    txtCodigoG2.requestFocus();
                }
                break;
        }
    }//GEN-LAST:event_txtCodigoG1KeyReleased

    private void txtCodigoG2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoG2KeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_F1:
                if (!txtCodigoG2.isEditable()) return;
                txtCodigoG2.setText("");
                txtCodigoG3.setText("");
                txtCodigoG4.setText("");
                txtCodigoG5.setText("");
                txtDivision.setText("");
                txtSub_Division.setText("");
                txtFamilia.setText("");
                txtSub_Familia.setText("");

                int tipoSeleccionG = 1;
                BuscarG2_Division pvc = new BuscarG2_Division(tipoSeleccionG);
                pvc.setCoNivelSuperior(txtCodigoG1.getText());
                JLabel aviso = pvc.getAviso();

                String msj = (tipoSeleccionG == -1 ? "Mostrando Listado de División - G2" : (tipoSeleccionG == 1 ? "Mostrando Listado de División - G2 Activos" : "Mostrando Listado de División - G2 No activos"));
                JOptionPane.showInternalOptionDialog(this, pvc, msj, JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso}, null);

                if (pvc.getG2_Division() != null) {
                    txtDivision.setText("");
                    txtFamilia.setText("");
                    CG2_Division cpDv = new CG2_Division();
                    cpDv.setG2_Division(pvc.getG2_Division());
                    this.txtCodigoG2.setText(cpDv.getG2_Division().getCoNivel02());
                    this.txtDivision.setText(cpDv.getG2_Division().getDeGrupoProdErp());
                }
                break;
            case KeyEvent.VK_ENTER:
                txtCodigoG2.setText(txtCodigoG2.getText().toUpperCase());
                int Ubicado = BuscarG2(txtCodigoG2.getText());
                if (Ubicado == 1) {
                    txtCodigoG3.requestFocus();
                }
                break;
        }
    }//GEN-LAST:event_txtCodigoG2KeyReleased

    private void txtCodigoG3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoG3KeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_F1:
                if (!txtCodigoG3.isEditable()) return;
                txtCodigoG3.setText("");
                txtCodigoG4.setText("");
                txtCodigoG5.setText("");
                txtSub_Division.setText("");
                txtFamilia.setText("");
                txtSub_Familia.setText("");

                int tipoSeleccionG = 1;
                BuscarG3_SubDivision pvc = new BuscarG3_SubDivision(tipoSeleccionG);
                pvc.setCoNivelSuperior(txtCodigoG2.getText());
                JLabel aviso = pvc.getAviso();

                String msj = (tipoSeleccionG == -1 ? "Mostrando Listado de Sub Division - G3" : (tipoSeleccionG == 1 ? "Mostrando Listado de Sub Division - G3 Activos" : "Mostrando Listado de Sub Division - G3 No activos"));
                JOptionPane.showInternalOptionDialog(this, pvc, msj, JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso}, null);

                if (pvc.getG3_SubDivision() != null) {
                    CG3_SubDivision cpSDv = new CG3_SubDivision();
                    cpSDv.setG3_SubDivision(pvc.getG3_SubDivision());
                    this.txtCodigoG3.setText(cpSDv.getG3_SubDivision().getCoNivel03());
                    this.txtSub_Division.setText(cpSDv.getG3_SubDivision().getDeGrupoAnatomico());
                }
                break;
            case KeyEvent.VK_ENTER:
                txtCodigoG3.setText(txtCodigoG2.getText().toUpperCase());
                int Ubicado = BuscarG3(txtCodigoG3.getText());
                if (Ubicado == 1) {
                    txtCodigoG4.requestFocus();
                }

                break;
        }
    }//GEN-LAST:event_txtCodigoG3KeyReleased

    private void txtCodigoG4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoG4KeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_F1:
                if (!txtCodigoG4.isEditable()) return;
                txtCodigoG4.setText("");
                txtCodigoG5.setText("");
                txtFamilia.setText("");
                txtSub_Familia.setText("");

                int tipoSeleccionG = 1;
                BuscarG4_Familia pvc = new BuscarG4_Familia(tipoSeleccionG);
                pvc.setCoNivelSuperior(txtCodigoG3.getText());
                JLabel aviso = pvc.getAviso();

                String msj = (tipoSeleccionG == -1 ? "Mostrando Listado de Familia - G4" : (tipoSeleccionG == 1 ? "Mostrando Listado de Familia - G4 Activos" : "Mostrando Listado de Familia - G4 No activos"));
                JOptionPane.showInternalOptionDialog(this, pvc, msj, JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso}, null);

                if (pvc.getG4_Familia() != null) {
                    CG4_Familia cpF = new CG4_Familia();
                    cpF.setG4_Familia(pvc.getG4_Familia());
                    this.txtCodigoG4.setText(cpF.getG4_Familia().getCoNivel04());
                    this.txtFamilia.setText(cpF.getG4_Familia().getDeGrupoTerapeutico());
                }
                break;
            case KeyEvent.VK_ENTER:
                txtCodigoG4.setText(txtCodigoG4.getText().toUpperCase());
                int Ubicado = BuscarG4(txtCodigoG4.getText());
                if (Ubicado == 1) {
                    txtCodigoG5.requestFocus();
                }
                break;
        }
    }//GEN-LAST:event_txtCodigoG4KeyReleased

    private void txtCodigoG5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoG5KeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_F1:
                if (!txtCodigoG5.isEditable()) return;
                txtCodigoG5.setText("");
                txtSub_Familia.setText("");

                int tipoSeleccionG = 1;
                BuscarG5_SubFamilia pvc = new BuscarG5_SubFamilia(tipoSeleccionG);
                pvc.setCoNivelSuperior(txtCodigoG4.getText());
                JLabel aviso = pvc.getAviso();

                String msj = (tipoSeleccionG == -1 ? "Mostrando Listado de Sub Familia - G5" : (tipoSeleccionG == 1 ? "Mostrando Listado de Sub Familia - G5 Activos" : "Mostrando Listado de Sub Familia - G5 No activos"));
                JOptionPane.showInternalOptionDialog(this, pvc, msj, JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso}, null);

                if (pvc.getG5_SubFamilia() != null) {
                    CG5_SubFamilia cpF = new CG5_SubFamilia();
                    cpF.setG5_SubFamilia(pvc.getG5_SubFamilia());
                    this.txtCodigoG5.setText(cpF.getG5_SubFamilia().getCoNivel05());
                    this.txtSub_Familia.setText(cpF.getG5_SubFamilia().getDeAccionTerapeutica());
                }
                break;
            case KeyEvent.VK_ENTER:
                txtCodigoG5.setText(txtCodigoG5.getText().toUpperCase());
                int Ubicado = BuscarG2(txtCodigoG5.getText());
                if (Ubicado == 1) {
                    txtCodigoProcedencia.requestFocus();
                }
                break;
        }
    }//GEN-LAST:event_txtCodigoG5KeyReleased

    private void txtCodigoLaboratorioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoLaboratorioKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_F1:
                if (!txtCodigoLaboratorio.isEditable()) return;
                int tipoSeleccionG = 1;
                BuscarLaboratorio pvc = new BuscarLaboratorio(tipoSeleccionG);
                JLabel aviso = pvc.getAviso();

                String msj = (tipoSeleccionG == -1 ? "Mostrando Listado de Laboratorios" : (tipoSeleccionG == 1 ? "Mostrando Listado de Laboratorios Activos" : "Mostrando Listado de Laboratorios No activos"));
                JOptionPane.showInternalOptionDialog(this, pvc, msj, JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso}, null);

                if (pvc.getLaboratorio() != null) {
                    CLaboratorio cpF = new CLaboratorio();
                    cpF.setLaboratorio(pvc.getLaboratorio());
                    this.txtCodigoLaboratorio.setText(cpF.getLaboratorio().getCoLaboratorio());
                    this.txtDescripLaboratorio.setText(cpF.getLaboratorio().getDeLaboratorio());
                }
                break;
            case KeyEvent.VK_ENTER:
                txtCodigoLaboratorio.setText(txtCodigoLaboratorio.getText().toUpperCase());
                int Ubicado = BuscarLab(txtCodigoLaboratorio.getText());
                if (Ubicado == 1) {
                    txtCodigoG1.requestFocus();
                }
                break;
        }
    }//GEN-LAST:event_txtCodigoLaboratorioKeyReleased

    private void txtCodigoProcedenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProcedenciaKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_F1:
                if (!txtCodigoProcedencia.isEditable()) return;
                int tipoSeleccionG = 1;
                BuscarPaises pvc = new BuscarPaises();
                //pvc.setSize(new Dimension(500, 170));

                JLabel aviso = pvc.getAviso();

                String msj = (tipoSeleccionG == -1 ? "Mostrando Listado de Procedencias" : (tipoSeleccionG == 1 ? "Mostrando Listado de Procedencias Activos" : "Mostrando Listado de de Procedencias No activos"));
                JOptionPane.showInternalOptionDialog(this, pvc, msj, -1,
                        -1, null, new Object[]{aviso}, null);

                if (pvc.getPais() != null) {
                    CPaises cpF = new CPaises();
                    cpF.setPaises(pvc.getPais());
                    this.txtCodigoProcedencia.setText(cpF.getPaises().getCoPais());
                    this.txtDescripProcedencia.setText(cpF.getPaises().getDePais());
                }
                break;
            case KeyEvent.VK_ENTER:
                txtCodigoProcedencia.setText(txtCodigoProcedencia.getText().toUpperCase());
                txtDescripProcedencia.setText("");
                int Ubicado = BuscarProcedencia(txtCodigoProcedencia.getText());
                if (Ubicado == 1) {
                    cbxControlLote.requestFocus();
                }
                break;
        }
    }//GEN-LAST:event_txtCodigoProcedenciaKeyReleased

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                txtDescripcion.requestFocus();
                break;
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                txtUnidad.requestFocus();
                break;
        }
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void txtUnidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                txtCodigoLaboratorio.requestFocus();
                break;
        }
    }//GEN-LAST:event_txtUnidadKeyReleased

    private void txtCantidadFraccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadFraccionKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                txtUnidadFraccion.requestFocus();
                break;
        }
    }//GEN-LAST:event_txtCantidadFraccionKeyReleased

    private void txtUnidadFraccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadFraccionKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                txtCosto.requestFocus();
                break;
        }
    }//GEN-LAST:event_txtUnidadFraccionKeyReleased

    private void txtCostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                txtPrecio.requestFocus();
                break;
        }
    }//GEN-LAST:event_txtCostoKeyReleased

    private void txtPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                txtDescuento.requestFocus();
                break;
        }
    }//GEN-LAST:event_txtPrecioKeyReleased

    private void txtDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                txtPreVtaPublico.requestFocus();
                break;
        }
    }//GEN-LAST:event_txtDescuentoKeyReleased

    private void txtPreVtaPublicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPreVtaPublicoKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                dteFechaVigencia.requestFocus();
                break;
        }
    }//GEN-LAST:event_txtPreVtaPublicoKeyReleased

    private void txtTipoPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoPrecioKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                cmbImpuesto.requestFocus();
                break;
        }
    }//GEN-LAST:event_txtTipoPrecioKeyReleased

    private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                btnGuardar.requestFocus();
                break;
        }
    }//GEN-LAST:event_chbEstadoKeyReleased

    private void dteFechaCreacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dteFechaCreacionKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                txtTipoPrecio.requestFocus();
                break;
        }
    }//GEN-LAST:event_dteFechaCreacionKeyReleased

    private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = 0;
        AtuxGridUtils.showCell(tblListado,numRegistros,ModeloTablaSimple.COLUMNA_DESCRIPCION);
        cp.setProducto(mtp.getFila(numRegistros));
        setMaestroProductos();
        return;
    }//GEN-LAST:event_btnPrimeroActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1) {
            numRegistros = 0;
        }
        AtuxGridUtils.showCell(tblListado,numRegistros,ModeloTablaSimple.COLUMNA_DESCRIPCION);
        cp.setProducto(mtp.getFila(numRegistros));
        setMaestroProductos();
        return;
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        BuscarMaestroProducto pvc = new BuscarMaestroProducto(tipoSeleccion);
        JLabel aviso = pvc.getAviso();

        String msj = (tipoSeleccion == -1 ? "Mostrando Listado de Productos" : (tipoSeleccion == 1 ? "Mostrando Listado de Productos Activos" : "Mostrando Listado de Productos No activos"));
        JOptionPane.showInternalOptionDialog(this, pvc, msj, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso}, null);

        if (pvc.getMaestroProducto() != null) {
            cp.setProducto(pvc.getMaestroProducto());
            setMaestroProductos();
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros) {
            numRegistros = finalPag;
        }
        AtuxGridUtils.showCell(tblListado,numRegistros,ModeloTablaSimple.COLUMNA_DESCRIPCION);
        cp.setProducto(mtp.getFila(numRegistros));
        setMaestroProductos();
        return;
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = finalPag;
        AtuxGridUtils.showCell(tblListado,numRegistros, ModeloTablaSimple.COLUMNA_DESCRIPCION);
        cp.setProducto(mtp.getFila(numRegistros));
        setMaestroProductos();
        return;
    }//GEN-LAST:event_btnUltimoActionPerformed

    private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;
        finalPag = tmpFp;
        CargarGrilla();
        chbSetActivo(true);
        Limpiar();
    }//GEN-LAST:event_rbTodosActionPerformed

    private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0;
        finalPag = tmpFp;
        CargarGrilla();
        chbSetActivo(true);
        Limpiar();
    }//GEN-LAST:event_rbAtivosActionPerformed

    private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0;
        finalPag = tmpFp;
        CargarGrilla();
        chbSetActivo(false);
        Limpiar();
    }//GEN-LAST:event_rbNoActivosActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        esActualizacion = false;
        Limpiar();
        //cp.getProducto().setCoProducto(cp.getNuevoCodigo()); aguerra se cruza al cancelar con producto de lista
        txtCodigo.requestFocus();
        ActivarCasillas();
        //txtCodigo.setText(cp.getProducto().getCoProducto());aguerra se cruza al cancelar con producto de lista
        txtCodigo.setText(cp.getNuevoCodigo());
        txtCantidadFraccion.setText("0");
        txtUnidadFraccion.setText("");
        cbxIndicadorFraccion.setEnabled(false);
        txtCantidadFraccion.setEnabled(false);
        txtUnidadFraccion.setEnabled(false);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        esActualizacion = true;
        ActivarCasillas();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Esta Seguro de Guardar los Datos", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
            return;
        }

        if (ECampos.esObligatorio(this.pnlDastosIniciales, true)) {
            JOptionPane.showInternalMessageDialog(this, "Los campos en rojo son obligatorios", "Llene los campos obligatorios", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!this.verficarCambios()) {
            if (JOptionPane.showConfirmDialog(this, "No se ha realizado ningun Cambio,\n Esta seguro de Continuar", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return;
            }
        }

        if ("S".equals(cbxIndicadorFraccion.getSelectedItem().toString().substring(0, 1))) {
            if (Integer.parseInt(txtCantidadFraccion.getText()) == 0) {
                JOptionPane.showMessageDialog(this, "El valor de la fracción debe ser mayor de 0", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
                txtCantidadFraccion.requestFocus();
                return;
            }
        }

        boolean correcto = false;

        if (!this.verficarCambios()) {
            JOptionPane.showMessageDialog(this, "Debe cambiar por lo menos algun valor", "No hubo cambios", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        txtCantidadFraccion.setEditable(true);
        txtUnidadFraccion.setEditable(true);

        try {
            correcto = guardarActualizar();
        } catch (SQLException ex) {
            Logger.getLogger(IProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!correcto) {
            JOptionPane.showMessageDialog(this, "Error: no se pudo guardar.", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
            AtuxUtility.liberarTransaccion();
            return;
        }
        AtuxUtility.aceptarTransaccion();
        DesActivarCasillas();
        CargarGrilla();
        JOptionPane.showMessageDialog(this, "Información Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
        tblListado.requestFocus();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Se perderan todos los datos ingresados\nEsta Seguro de Cancelar ", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
            return;
        }
        DesActivarCasillas();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
        chbSetActivo(chbEstado.isSelected());
    }//GEN-LAST:event_chbEstadoActionPerformed

    private void btnCodigoDeBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCodigoDeBarrasActionPerformed
        ICodigoDeBarra pvc = new ICodigoDeBarra(this);
        pvc.setCodigoProducto(txtCodigo.getText());
        pvc.setDescripcionProducto(txtDescripcion.getText());
        pvc.setUnidadProducto(txtUnidad.getText());
        pvc.CargaDatos();
        pvc.setPreferredSize(new Dimension(830, 420));

        String msj = "Mostrando todas los Codigos de Barras del Producto";
        JOptionPane.showInternalOptionDialog(this, pvc, msj, JOptionPane.OK_CANCEL_OPTION,
                -1, null, new Object[]{pvc.getAviso()}, null);
    }//GEN-LAST:event_btnCodigoDeBarrasActionPerformed

    private void btnPrincipioActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrincipioActivoActionPerformed
        IPrincipioActivoProducto pvc = new IPrincipioActivoProducto();
        pvc.setPreferredSize(new Dimension(780, 330));
        pvc.setCodigoProducto(txtCodigo.getText());
        pvc.setDescripcionProducto(txtDescripcion.getText());
        pvc.setUnidadProducto(txtUnidad.getText());
        pvc.CargaDatos();

        String msj = "Mostrando todas los Principios Activos";
        JOptionPane.showInternalOptionDialog(this, pvc, msj, -1,
                -1, null, new Object[]{pvc.getAviso()}, null);

    }//GEN-LAST:event_btnPrincipioActivoActionPerformed

    private void btnParametrosReposicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParametrosReposicionActionPerformed
        IParamXReposicion pvc = new IParamXReposicion();
        pvc.setCodigoProducto(txtCodigo.getText());
        pvc.setDescripcionProducto(txtDescripcion.getText());
        pvc.setTipoParametroReposicion("PD");
        pvc.CargaDatos();
        pvc.setPreferredSize(new Dimension(630, 480));

        //String msj = (tipoSeleccion==-1?"Mostrando todas las Especialidades del Medico":(tipoSeleccion==1?"Mostrando todo los usuarios activos":"Mostrando todo los usuarios No activos"));
        String msj = "Mostrando todas los Parametros de Reposición por Producto";
        JOptionPane.showInternalOptionDialog(this, pvc, msj, JOptionPane.OK_CANCEL_OPTION,
                -1, null, new Object[]{pvc.getAviso()}, null);
    }//GEN-LAST:event_btnParametrosReposicionActionPerformed

    private void btnRegistroSanitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroSanitarioActionPerformed
        IRegistrosSanitarios pvc = new IRegistrosSanitarios(this);
        pvc.setCodigoProducto(txtCodigo.getText());
        pvc.setDescripcionProducto(txtDescripcion.getText());
        pvc.setUnidadProducto(txtUnidad.getText());
        pvc.CargaDatos();
        pvc.setPreferredSize(new Dimension(810, 370));

        //String msj = (tipoSeleccion==-1?"Mostrando todas las Especialidades del Medico":(tipoSeleccion==1?"Mostrando todo los usuarios activos":"Mostrando todo los usuarios No activos"));
        String msj = "Mostrando los Registros Sanitarios";
        JOptionPane.showInternalOptionDialog(this, pvc, msj, JOptionPane.OK_CANCEL_OPTION,
                -1, null, new Object[]{pvc.getAviso()}, null);
    }//GEN-LAST:event_btnRegistroSanitarioActionPerformed

    private void btnLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoteActionPerformed
        IProductoLote pvc = new IProductoLote(this);
        pvc.setCodigoProducto(txtCodigo.getText());
        pvc.setDescripcionProducto(txtDescripcion.getText());
        pvc.setUnidadProducto(txtUnidad.getText());
        pvc.CargaDatos();
        pvc.setPreferredSize(new Dimension(810, 370));

        //String msj = (tipoSeleccion==-1?"Mostrando todas las Especialidades del Medico":(tipoSeleccion==1?"Mostrando todo los usuarios activos":"Mostrando todo los usuarios No activos"));
        String msj = "Mostrando los Lotes";
        JOptionPane.showInternalOptionDialog(this, pvc, msj, JOptionPane.OK_CANCEL_OPTION,
                -1, null, new Object[]{pvc.getAviso()}, null);
    }//GEN-LAST:event_btnLoteActionPerformed

    private void cbxControlLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxControlLoteActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_cbxControlLoteActionPerformed

    private void cbxIndicadorFraccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbxIndicadorFraccionFocusLost
        if ("S".equals(cbxIndicadorFraccion.getSelectedItem().toString().substring(0, 1))) {
            txtCantidadFraccion.setEditable(true);
            txtUnidadFraccion.setEditable(true);
        } else {
            txtCantidadFraccion.setEditable(false);
            txtUnidadFraccion.setEditable(false);
            txtCantidadFraccion.setText("0");
            txtUnidadFraccion.setText("");
        }
    }//GEN-LAST:event_cbxIndicadorFraccionFocusLost

    private void cbxIndicadorFraccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxIndicadorFraccionKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                if ("S".equals(cbxIndicadorFraccion.getSelectedItem().toString().substring(0, 1))) {
                    txtCantidadFraccion.requestFocus();
                    txtCantidadFraccion.setEditable(true);
                    txtUnidadFraccion.setEditable(true);
                } else {
                    txtCantidadFraccion.setEditable(false);
                    txtUnidadFraccion.setEditable(false);
                    txtCantidadFraccion.setText("0");
                    txtUnidadFraccion.setText("");
                    txtTipoPrecio.requestFocus();
                }
                break;
        }
    }//GEN-LAST:event_cbxIndicadorFraccionKeyReleased

    private void tblListadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblListadoKeyReleased

    }//GEN-LAST:event_tblListadoKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect btnAnterior;
    private elaprendiz.gui.button.ButtonRect btnBuscar;
    private elaprendiz.gui.button.ButtonRect btnCancelar;
    private elaprendiz.gui.button.ButtonRect btnCodigoDeBarras;
    private elaprendiz.gui.button.ButtonRect btnGuardar;
    private elaprendiz.gui.button.ButtonRect btnLote;
    private elaprendiz.gui.button.ButtonRect btnModificar;
    private elaprendiz.gui.button.ButtonRect btnNuevo;
    private elaprendiz.gui.button.ButtonRect btnParametrosReposicion;
    private elaprendiz.gui.button.ButtonRect btnPrimero;
    private elaprendiz.gui.button.ButtonRect btnPrincipioActivo;
    private elaprendiz.gui.button.ButtonRect btnRegistroSanitario;
    private elaprendiz.gui.button.ButtonRect btnSalir;
    private elaprendiz.gui.button.ButtonRect btnSiguiente;
    private elaprendiz.gui.button.ButtonRect btnUltimo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbxControlLote;
    private javax.swing.JComboBox cbxDescripcionControl;
    private javax.swing.JComboBox cbxIndicadorFraccion;
    private javax.swing.JComboBox cbxPideMedico;
    private javax.swing.JComboBox cbxPideReceta;
    private javax.swing.JComboBox cbxProdControlado;
    private javax.swing.JCheckBox chbEstado;
    private javax.swing.JComboBox cmbImpuesto;
    private javax.swing.JComboBox cmbMoneda;
    private com.toedter.calendar.JDateChooser dteFechaCreacion;
    private com.toedter.calendar.JDateChooser dteFechaVigencia;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCantidadF;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblControlDigemid;
    private javax.swing.JLabel lblControlLote;
    private javax.swing.JLabel lblControlReceta;
    private javax.swing.JLabel lblCostoSol;
    private javax.swing.JLabel lblDescrip;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblFechaCreacion;
    private javax.swing.JLabel lblFechaCreacion1;
    private javax.swing.JLabel lblFechaVigencia;
    private javax.swing.JLabel lblFraccionamiento;
    private javax.swing.JLabel lblG1;
    private javax.swing.JLabel lblG2;
    private javax.swing.JLabel lblG3;
    private javax.swing.JLabel lblG4;
    private javax.swing.JLabel lblG5;
    private javax.swing.JLabel lblImpuesto;
    private javax.swing.JLabel lblImpuesto1;
    private javax.swing.JLabel lblIndicadorF;
    private javax.swing.JLabel lblLaboratorio;
    private javax.swing.JLabel lblLaboratorio1;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblMoneda;
    private javax.swing.JLabel lblPVP;
    private javax.swing.JLabel lblPideMedico;
    private javax.swing.JLabel lblPrecioSol;
    private javax.swing.JLabel lblProcedencia;
    private javax.swing.JLabel lblProductoControlado;
    private javax.swing.JLabel lblTipoPrecio;
    private javax.swing.JLabel lblUnidad;
    private javax.swing.JLabel lblUnidadF;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAccionesCategorias;
    private javax.swing.JPanel pnlBuscadorCategorias;
    private javax.swing.JPanel pnlBuscadorCategorias2;
    private javax.swing.JPanel pnlControlDigemid;
    private javax.swing.JPanel pnlDastosIniciales;
    private javax.swing.JPanel pnlDatosPrecio01;
    private javax.swing.JPanel pnlEntradasCategorias_G05;
    private javax.swing.JPanel pnlSetDeCategoria;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblListado;
    private elaprendiz.gui.textField.TextField txtCantidadFraccion;
    private elaprendiz.gui.textField.TextField txtCodigo;
    private elaprendiz.gui.textField.TextField txtCodigoG1;
    private elaprendiz.gui.textField.TextField txtCodigoG2;
    private elaprendiz.gui.textField.TextField txtCodigoG3;
    private elaprendiz.gui.textField.TextField txtCodigoG4;
    private elaprendiz.gui.textField.TextField txtCodigoG5;
    private elaprendiz.gui.textField.TextField txtCodigoLaboratorio;
    private elaprendiz.gui.textField.TextField txtCodigoProcedencia;
    private elaprendiz.gui.textField.TextField txtCosto;
    private elaprendiz.gui.textField.TextField txtDescripLaboratorio;
    private elaprendiz.gui.textField.TextField txtDescripProcedencia;
    private elaprendiz.gui.textField.TextField txtDescripcion;
    private elaprendiz.gui.textField.TextField txtDescuento;
    private elaprendiz.gui.textField.TextField txtDivision;
    private elaprendiz.gui.textField.TextField txtFamilia;
    private elaprendiz.gui.textField.TextField txtLineaComercial;
    private elaprendiz.gui.textField.TextField txtPideLote2;
    private elaprendiz.gui.textField.TextField txtPreVtaPublico;
    private elaprendiz.gui.textField.TextField txtPrecio;
    private elaprendiz.gui.textField.TextField txtSub_Division;
    private elaprendiz.gui.textField.TextField txtSub_Familia;
    private elaprendiz.gui.textField.TextField txtTipoPrecio;
    private elaprendiz.gui.textField.TextField txtUnidad;
    private elaprendiz.gui.textField.TextField txtUnidadFraccion;
    // End of variables declaration//GEN-END:variables
}
