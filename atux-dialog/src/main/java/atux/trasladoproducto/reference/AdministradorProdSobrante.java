package atux.trasladoproducto.reference;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 06/08/2010
 */
public class AdministradorProdSobrante {
    private List prodsGuia = new ArrayList();
    private List otrosProds = new ArrayList();

    private boolean usarOtrosProds = false;
    private boolean usarOtrosProdsOld = true;

    public List getProdsGuia() {
        return prodsGuia;
    }

    public void setProdsGuia(List prodsGuia) {
        this.prodsGuia = prodsGuia;
    }

    public List getOtrosProds() {
        return otrosProds;
    }

    public void setOtrosProds(List otrosProds) {
        this.otrosProds = otrosProds;
    }

    public void actualizarProductosASerTrasladados() {
        if (usarOtrosProds){
            otrosProds = VariablesTrasladoProducto.arrayTransferencia;
        }else{
            prodsGuia = VariablesTrasladoProducto.arrayTransferencia;
        }
        actualizarArrayTrasferencia();
    }

    public void actualizarArrayTrasferencia() {
        VariablesTrasladoProducto.arrayTransferencia = new ArrayList();
        VariablesTrasladoProducto.arrayTransferencia.addAll(prodsGuia);
        VariablesTrasladoProducto.arrayTransferencia.addAll(otrosProds);
    }

    public void actualizarProductosSeleccionados() {
        VariablesTrasladoProducto.arrayTransferencia = new ArrayList();
        if (usarOtrosProds) {
            VariablesTrasladoProducto.arrayTransferencia.addAll(otrosProds);
        } else {
            VariablesTrasladoProducto.arrayTransferencia.addAll(prodsGuia);
        }
    }

    public boolean isUsarOtrosProds() {
        return usarOtrosProds;
    }

    public void setUsarOtrosProds(boolean usarOtrosProdsActual) {
        usarOtrosProdsOld = usarOtrosProds;
        usarOtrosProds = usarOtrosProdsActual;
    }

    public boolean seDebeCambiarListaDeProds() {
        return usarOtrosProds != usarOtrosProdsOld;
    }

    public void removeRow(int row) {
        // Se esto asumiendo que primero eston los productos de la Guía
        // y luego los demos productos.
        boolean esUnProdDeGuia =  prodsGuia.size()>row;
        if (esUnProdDeGuia){
            prodsGuia.remove(row);
        }else{
            otrosProds.remove(row - prodsGuia.size());
        }
    }
}
