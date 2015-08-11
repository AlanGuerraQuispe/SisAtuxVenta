package com.atux.bean.inventario;

import com.atux.bean.precios.LaboratorioFlt;
import com.atux.enums.TipoTomaInventarioEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 24/2/2015.
 */
public class LaboratorioInventarioFlt extends LaboratorioFlt {

    private TipoTomaInventarioEnum tiTomaInventario;

    List<LaboratorioInventario> seleccionados = new ArrayList<LaboratorioInventario>();

    public List<LaboratorioInventario> getSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(List<LaboratorioInventario> seleccionados) {
        this.seleccionados = seleccionados;
    }

    public TipoTomaInventarioEnum getTiTomaInventario() {
        return tiTomaInventario;
    }

    public void setTiTomaInventario(TipoTomaInventarioEnum tiTomaInventario) {
        this.tiTomaInventario = tiTomaInventario;
    }
}
