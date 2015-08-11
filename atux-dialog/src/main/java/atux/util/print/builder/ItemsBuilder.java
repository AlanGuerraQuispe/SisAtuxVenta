package atux.util.print.builder;

import atux.modelbd.DetallePedidoVenta;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ItemsBuilder {
    private List itemsPedido;
    private List detalleItemsPendientes;
    private List itemsPorComprobante;
    private int numItemsPorComprobante;
    private boolean isTicketera;
    private final Log logger = LogFactory.getLog(getClass());

    public ItemsBuilder(List itemsPedido) {
        this.itemsPedido = itemsPedido;
        itemsPorComprobante = new ArrayList();
        detalleItemsPendientes = new ArrayList();
    }


    public void ejecutar() {

        List lista = new ArrayList();
        int lineas = 0;
        int auxiliar = 0;

        if(isTicketera){
            lista.addAll(itemsPedido);
            itemsPorComprobante.add(lista);
            return;
        }

        for (int i = 0; i < itemsPedido.size(); i++) {

            DetallePedidoVenta vttdPedidoVenta = (DetallePedidoVenta) itemsPedido.get(i);
            if (vttdPedidoVenta.getDetalleItemVirtual().isEmpty()) {
                lista.add(vttdPedidoVenta);
                lineas++;
            } else {
                auxiliar = 1 + vttdPedidoVenta.getDetalleItemVirtual().size()+vttdPedidoVenta.getLineasmensaje() + lineas;
                if (auxiliar <= numItemsPorComprobante) {
                    lista.add(vttdPedidoVenta);
                    lineas += 1 + vttdPedidoVenta.getDetalleItemVirtual().size()+vttdPedidoVenta.getLineasmensaje() ;
                } else {
                    detalleItemsPendientes.add(vttdPedidoVenta);
                    continue;
                }
            }

            if (lineas == numItemsPorComprobante) {
                lineas = 0;
                itemsPorComprobante.add(lista);
                lista = new ArrayList();
            }
        }
        
        if(!detalleItemsPendientes.isEmpty()){
            procesarItemsPendientes(lista,lineas);
            return ;
        }

        if (lineas!=0 &&lineas <= numItemsPorComprobante ) {
            lineas = 0;
            itemsPorComprobante.add(lista);
            lista = new ArrayList();
        }
    }

    public void  procesarItemsPendientes(List lista, int lineas) {
        int auxiliar = 0;
        boolean continuar=true;
        int indice=0;

        Collections.sort(detalleItemsPendientes);

        while(continuar) {

            DetallePedidoVenta vttdPedidoVenta=(DetallePedidoVenta)detalleItemsPendientes.get(0);
            auxiliar =1+ vttdPedidoVenta.getDetalleItemVirtual().size()+vttdPedidoVenta.getLineasmensaje() + lineas;
            if (auxiliar <= numItemsPorComprobante) {
                lista.add(vttdPedidoVenta);
                detalleItemsPendientes.remove(0);
                lineas += auxiliar;
            }else{
                lineas=0;
                itemsPorComprobante.add(lista);
                lista = new ArrayList();
                lista.add(vttdPedidoVenta);
                lineas += vttdPedidoVenta.getDetalleItemVirtual().size()+vttdPedidoVenta.getLineasmensaje();
                detalleItemsPendientes.remove(0);

            }
            if(detalleItemsPendientes.isEmpty())
                continuar=false;

        }
        if (lineas <= numItemsPorComprobante) {
            itemsPorComprobante.add(lista);
        }

    }    

    public List getItemsPedido() {
        return itemsPedido;
    }

    public void setItemsPedido(List itemsPedido) {
        this.itemsPedido = itemsPedido;
    }

    public List getDetalleItemsPendientes() {
        return detalleItemsPendientes;
    }

    public void setDetalleItemsPendientes(List detalleItemsPendientes) {
        this.detalleItemsPendientes = detalleItemsPendientes;
    }

    public List getItemsPorComprobante() {
        return itemsPorComprobante;
    }

    public void setItemsPorComprobante(List itemsPorComprobante) {
        this.itemsPorComprobante = itemsPorComprobante;
    }

    public int getNumItemsPorComprobante() {
        return numItemsPorComprobante;
    }

    public void setNumItemsPorComprobante(int numItemsPorComprobante) {
        this.numItemsPorComprobante = numItemsPorComprobante;
    }

    public boolean isTicketera() {
        return isTicketera;
    }

    public void setTicketera(boolean ticketera) {
        isTicketera = ticketera;
    }

}
