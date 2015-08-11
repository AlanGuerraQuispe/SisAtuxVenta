package atux.util;

import atux.handlers.PedidoVentaInterceptor;
import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.PedidoVenta;
import atux.modelbd.ProductoLocal;
import atux.modelgui.ModeloTomaPedidoVenta;
import atux.util.common.AtuxUtility;
import atux.vistas.venta.ICompletarPedidoVenta;
import atux.vistas.venta.IPedidoVentaInsumo;
import atux.vistas.venta.IPedidoVenta;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.Component;
import java.awt.Font;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;


public class CellEditorSpinnerPedidoVenta extends AbstractCellEditor implements TableCellEditor {


    protected final Log LOG = LogFactory.getLog(getClass());
    private JSpinner spinner;
    private JTable tbl;
    private Object valorInicial;
    private Object valorActual;
    private IPedidoVentaInsumo pedidoVentaInsumo;
    private IPedidoVenta pedidoVenta;
    private ICompletarPedidoVenta iCompPedidoVenta;


    private int fila;
    private int columna;

    public CellEditorSpinnerPedidoVenta(int sizeDes, final IPedidoVentaInsumo pedidoVenta) {
        this.pedidoVentaInsumo = pedidoVenta;
        spinner = new JSpinner();
        spinner.setFont(new Font("Tahoma", 1, 14));
        spinner.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        spinner.setModel(new SpinnerNumberModel(0, 0, 500, sizeDes));

        ChangeListener listener = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner temp = (JSpinner) e.getSource();
                ((DefaultEditor) temp.getEditor()).getTextField().setHorizontalAlignment(JTextField.RIGHT);
                Integer vi = (Integer) valorInicial;
                if (vi != null) {
                    valorActual = temp.getValue();
                    ModeloTomaPedidoVenta mt = ((ModeloTomaPedidoVenta) tbl.getModel());
                    int stock = ((DetallePedidoVenta) mt.getFila(fila)).getProdLocal().getCaStockDisponible();

                    if (Double.parseDouble(valorActual.toString()) > stock) {
                        valorActual = stock;
                        AtuxUtility.showMessage(null, "El Stock disponible es " + stock + " " + ((DetallePedidoVenta) mt.getFila(fila)).getProdLocal().getDeUnidadFraccion(), null);
                    } else {
                        Double pr = ((DetallePedidoVenta) mt.getFila(fila)).getProdLocal().getVaPrecioPublico();
                        mt.setValueAt(getPedidoVentaInsumo(), Double.parseDouble(valorActual.toString()) * pr, getFila(), 4);
                        ((DetallePedidoVenta) mt.getFila(fila)).setCaAtendida(Integer.parseInt(valorActual.toString()));
                        mt.contarItems();
                        getPedidoVentaInsumo().mostrarInsumos(((DetallePedidoVenta) mt.getFila(fila)));
//                        PedidoVentaInterceptor pedidoVentaInterceptor = Application.instance().getBean(PedidoVentaInterceptor.class);
//                        Map result = new HashMap();
//                        try {
//                            PedidoVenta pedidoVenta=new PedidoVenta();
//                            pedidoVenta.setDetallePedidoVenta(mt.getRegistros());
//                            pedidoVentaInterceptor.procesar(pedidoVenta, (DetallePedidoVenta) mt.getFila(fila), result);
//                            mt.fireTableRowsInserted();
//                            if (result.get("mensajeVentana") != null) {
//                                AtuxUtility.showMessage(null, (String) result.get("mensajeVentana"), null);
//
//                            }
//                        } catch (Exception e1) {
//                            LOG.error("Error al verificar promociones ",e1);
//                            AtuxUtility.showMessage(null, "Error al verificar promociones del producto", null);
//                        }
                    }

                }
                ((DefaultEditor) temp.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
                fireEditingStopped();
            }

        };

        spinner.addChangeListener(listener);

    }

    public CellEditorSpinnerPedidoVenta(int sizeDes, final IPedidoVenta pedidoVenta) {
        this.pedidoVenta = pedidoVenta;
        spinner = new JSpinner();
        spinner.setFont(new Font("Tahoma", 1, 14));
        spinner.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        spinner.setModel(new SpinnerNumberModel(0, 0, 500, sizeDes));

        ChangeListener listener = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner temp = (JSpinner) e.getSource();
                ((DefaultEditor) temp.getEditor()).getTextField().setHorizontalAlignment(JTextField.RIGHT);
                Integer vi = (Integer) valorInicial;
                if (vi != null) {
                    valorActual = temp.getValue();
                    ModeloTomaPedidoVenta mt = ((ModeloTomaPedidoVenta) tbl.getModel());
                    int stock = ((DetallePedidoVenta) mt.getFila(fila)).getProdLocal().getCaStockDisponible();

                    if (Double.parseDouble(valorActual.toString()) > stock) {
                        valorActual = stock;
                        AtuxUtility.showMessage(null, "El Stock disponible es " + stock + " " + ((DetallePedidoVenta) mt.getFila(fila)).getProdLocal().getDeUnidadFraccion(), null);
                    } else {
                        Double pr = ((DetallePedidoVenta) mt.getFila(fila)).getProdLocal().getVaPrecioPublico();
                        mt.setValueAt(getPedidoVenta(), Double.parseDouble(valorActual.toString()) * pr, getFila(), 4);
                        ((DetallePedidoVenta) mt.getFila(fila)).setCaAtendida(Integer.parseInt(valorActual.toString()));
                        mt.contarItems();
                    }

                }
                ((DefaultEditor) temp.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
                fireEditingStopped();
            }

        };

        spinner.addChangeListener(listener);

    }

    public CellEditorSpinnerPedidoVenta(int sizeDes, ICompletarPedidoVenta iCompPedidoVenta) {
        this.iCompPedidoVenta = iCompPedidoVenta;
        spinner = new JSpinner();
        spinner.setFont(new Font("Tahoma", 1, 14));
        spinner.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        spinner.setModel(new SpinnerNumberModel(0, 0, 500, sizeDes));

        ChangeListener listener = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner temp = (JSpinner) e.getSource();
                ((DefaultEditor) temp.getEditor()).getTextField().setHorizontalAlignment(JTextField.RIGHT);
                Integer vi = (Integer) valorInicial;
                if (vi != null) {
                    valorActual = temp.getValue();
                    ModeloTomaPedidoVenta mt = ((ModeloTomaPedidoVenta) tbl.getModel());
                    int stock = ((DetallePedidoVenta) mt.getFila(fila)).getProdLocal().getCaStockDisponible();

                    if (Double.parseDouble(valorActual.toString()) > stock) {
                        valorActual = stock;
                        AtuxUtility.showMessage(null, "El Stock disponible es " + stock + " " + ((DetallePedidoVenta) mt.getFila(fila)).getProdLocal().getDeUnidadFraccion(), null);
                    } else {
                        Double pr = ((DetallePedidoVenta) mt.getFila(fila)).getProdLocal().getVaPrecioPublico();
                        mt.setValueAt(getCompletarPedidoVenta(), Double.parseDouble(valorActual.toString()) * pr, getFila(), 4);
                        mt.contarItems();
                    }

                }
                ((DefaultEditor) temp.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
                fireEditingStopped();
            }

        };

        spinner.addChangeListener(listener);
    }

    @Override
    public Object getCellEditorValue() {
        return valorActual;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        tbl = table;
        fila = row;
        columna = column;
        valorActual = value;
        spinner.setValue(value);
        if (valorInicial == null) {
            valorInicial = ((ModeloTomaPedidoVenta) table.getModel()).getValueAt(row, column);
        }
        return spinner;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public IPedidoVentaInsumo getPedidoVentaInsumo() {
        return pedidoVentaInsumo;
    }

    public void setPedidoVentaInsumo(IPedidoVentaInsumo pedidoVenta) {
        this.pedidoVentaInsumo = pedidoVenta;
    }

    public IPedidoVenta getPedidoVenta() {
        return pedidoVenta;
    }

    public void setPedidoVenta(IPedidoVenta pedidoVenta) {
        this.pedidoVenta = pedidoVenta;
    }

    public ICompletarPedidoVenta getCompletarPedidoVenta() {
        return iCompPedidoVenta;
    }

    public JSpinner getSpinner() {
        return spinner;
    }

}
