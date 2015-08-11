package com.aw.example.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Julio C. Macavilca
 * Date: 17/06/2008
 */
public class BNFactura {
    // Datos encabezado
    Date fechaEmision;
    String empresa;
    String ruc;
    String direccion;
    String nroPedido;

    List<BNFacturaItem> items = new ArrayList<BNFacturaItem>();

    String itemDescAdic1;
    String itemDescAdic2 = "Operacion sujeta al pago de detracciones ";
    String itemDescAdic3 = "por decreto supremo del gobierno central";

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNroPedido() {
        return nroPedido;
    }

    public void setNroPedido(String nroPedido) {
        this.nroPedido = nroPedido;
    }

    public List<BNFacturaItem> getItems() {
        return items;
    }

    public void setItems(List<BNFacturaItem> items) {
        this.items = items;
    }

    public String getItemDescAdic1() {
        return itemDescAdic1;
    }

    public void setItemDescAdic1(String itemDescAdic1) {
        this.itemDescAdic1 = itemDescAdic1;
    }

    public String getItemDescAdic2() {
        return itemDescAdic2;
    }

    public void setItemDescAdic2(String itemDescAdic2) {
        this.itemDescAdic2 = itemDescAdic2;
    }

    public String getItemDescAdic3() {
        return itemDescAdic3;
    }

    public void setItemDescAdic3(String itemDescAdic3) {
        this.itemDescAdic3 = itemDescAdic3;
    }
}
