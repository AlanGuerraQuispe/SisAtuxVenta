package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ListaPedidosReposicion extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;    
    public static final String nt = "LGTC_PEDIDO_PRODUCTO";
     
    public static final String[] COLUMNA_PK ={"CO_COMPANIA", "CO_LOCAL", "TI_PEDIDO_PRODUCTO", "NU_PEDIDO_PRODUCTO"};
    public static final String COLUMNA_ACTIVO = "ES_PEDIDO_PRODUCTO";        
                    
    private String  coCompania;
    private String  coLocal;
    private String  tiPedidoProducto;
    private String  nuPedidoProducto;
    private Date    feSolicitaPedido;
    private Integer caTotalProducto;
    private Integer nuDiasRotacion;
    private Integer nuMinDiasReposicion;
    private Integer nuMaxDiasReposicion;
    private Integer nuItem;    
    private String  coProducto;
    private String  deProducto;
    private String  nuRevisionProducto;    
    private String  deUnidad;                
    private Integer caPedida; 
    private Integer caSugerida;     
    
    private ArrayList<ListaPedidosReposicion> detallePedido;
    
    public static final String[]
          FULL_CAMPOS ={"CO_COMPANIA, CO_PRODUCTO, NU_REVISION_PRODUCTO, DE_CORTA_PRODUCTO, DE_PRODUCTO,"+
                        "CO_FACTOR_PRECIO, CO_MONEDA, CO_IMPUESTO_1, CO_IMPUESTO_2, CO_LABORATORIO,"+
                        "CO_LINEA_PRODUCTO CO_GRUPO_PRODUCTO, CO_CATEGORIA_CCR, CO_CLASIFICACION_IMS,"+
                        "CO_CATEGORIA_SB, CO_SUBCATEGORIA_SB, CO_UNIDAD_MEDIDA, VA_UNIDAD_MEDIDA,"+
                        "CO_UNIDAD_COMPRA, CO_UNIDAD_VENTA, CO_UNIDAD_CONTENIDO, VA_UNIDAD_CONTENIDO,"+
                        "IN_PROD_FRACCIONABLE, VA_PRECIO_COMPRA, VA_PRECIO_PROMEDIO, VA_BONO, PC_BONO,"+
                        "DE_UNIDAD_PRODUCTO, IN_RECETA_MEDICA, ES_PRODUCTO, ID_CREA_PRODUCTO,"+
                        "FE_CREA_PRODUCTO, ID_MOD_PRODUCTO, FE_MOD_PRODUCTO, CO_CLASE_VENTA,"+
                        "CO_GRUPO_PROD_ERP, CO_LINEA_PROD_ERP, CO_OTC, CO_GRUPO_TERAPEUTICO, CO_GIRO,"+
                        "CO_GRUPO_ANATOMICO, CO_FORMA_PRESENTACION, CO_ACCION_TERAPEUTICA,"+
                        "CO_ACCION_FARMACEUTICA, PC_DESCUENTO_BASE, CO_VIADM, VA_COSTO_PRODUCTO,"+
                        "DE_DETALLE_PRESENTACION_LARGO, VA_PRECIO_PUBLICO, CO_PRODUCTO_SAP,"+
                        "CO_TIPO_CONSUMO, CO_COMPRADOR, IN_REINTEGRO, IN_DESCONTINUADO, IN_CONTROLADO,"+
                        "IN_CONSIGNADO_SAP, CO_UNIDAD_SAP, TI_MATERIAL_SAP, CO_UNIDAD_FM_SAP,"+
                        "CO_GRUPO_EXT, DE_PARTIDA_ARANCELARIA"};
        
    public static final String[]
          CAMPOS_COMPLETOS ={"CO_COMPANIA,CO_PRODUCTO,NU_REVISION_PRODUCTO,DE_CORTA_PRODUCTO,DE_PRODUCTO,CO_FACTOR_PRECIO,CO_MONEDA,CO_IMPUESTO_1,"
                           + "CO_IMPUESTO_2,CO_LABORATORIO,CO_LINEA_PRODUCTO,CO_GRUPO_PRODUCTO,CO_CATEGORIA_CCR,CO_CLASIFICACION_IMS,CO_CATEGORIA_SB,"
                           + "CO_SUBCATEGORIA_SB,CO_UNIDAD_MEDIDA,VA_UNIDAD_MEDIDA,CO_UNIDAD_COMPRA,CO_UNIDAD_VENTA,CO_UNIDAD_CONTENIDO,VA_UNIDAD_CONTENIDO,"
                           + "IN_PROD_FRACCIONABLE,VA_PRECIO_COMPRA,VA_PRECIO_PROMEDIO,VA_BONO,PC_BONO,DE_UNIDAD_PRODUCTO,IN_RECETA_MEDICA,ES_PRODUCTO,"
                           + "ID_CREA_PRODUCTO,FE_CREA_PRODUCTO,ID_MOD_PRODUCTO,FE_MOD_PRODUCTO,CO_CLASE_VENTA,CO_GRUPO_PROD_ERP,CO_LINEA_PROD_ERP,"
                           + "CO_OTC,CO_GRUPO_TERAPEUTICO,CO_GIRO,CO_GRUPO_ANATOMICO,CO_FORMA_PRESENTACION,CO_ACCION_TERAPEUTICA,CO_ACCION_FARMACEUTICA,"
                           + "PC_DESCUENTO_BASE,CO_VIADM,VA_COSTO_PRODUCTO,DE_DETALLE_PRESENTACION_LARGO,VA_PRECIO_PUBLICO,CO_PRODUCTO_SAP,CO_TIPO_CONSUMO,"
                           + "CO_COMPRADOR,IN_REINTEGRO,IN_DESCONTINUADO,IN_CONTROLADO,IN_CONSIGNADO_SAP,CO_UNIDAD_SAP,TI_MATERIAL_SAP,CO_UNIDAD_FM_SAP,"
                           + "CO_GRUPO_EXT,DE_PARTIDA_ARANCELARIA,CO_PRODUCTO2,CO_PRODUCTO_BK,CO_NIVEL_01,CO_NIVEL_02,CO_NIVEL_03,CO_NIVEL_04,CO_NIVEL_05,"
                           + "CO_PROCEDENCIA,NO_CONTROL_DIGEMID,NO_CONTROL_LOTE,NO_TIPO_PRECIO,FE_VIGENCIA,FE_ULT_VENTA,IN_CONTROL_LOTE, IN_PIDE_MEDICO"};

    public Integer getCaPedida() {
        return caPedida;
    }

    public void setCaPedida(Integer caPedida) {
        this.caPedida = caPedida;
    }

    public Integer getCaSugerida() {
        return caSugerida;
    }

    public void setCaSugerida(Integer caSugerida) {
        this.caSugerida = caSugerida;
    }

    public Integer getCaTotalProducto() {
        return caTotalProducto;
    }

    public void setCaTotalProducto(Integer caTotalProducto) {
        this.caTotalProducto = caTotalProducto;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    public String getDeProducto() {
        return deProducto;
    }

    public void setDeProducto(String deProducto) {
        this.deProducto = deProducto;
    }

    public String getDeUnidad() {
        return deUnidad;
    }

    public void setDeUnidad(String deUnidad) {
        this.deUnidad = deUnidad;
    }

    public Date getFeSolicitaPedido() {
        return feSolicitaPedido;
    }

    public void setFeSolicitaPedido(Date feSolicitaPedido) {
        this.feSolicitaPedido = feSolicitaPedido;
    }

    public Integer getNuDiasRotacion() {
        return nuDiasRotacion;
    }

    public void setNuDiasRotacion(Integer nuDiasRotacion) {
        this.nuDiasRotacion = nuDiasRotacion;
    }

    public Integer getNuItem() {
        return nuItem;
    }

    public void setNuItem(Integer nuItem) {
        this.nuItem = nuItem;
    }

    public Integer getNuMaxDiasReposicion() {
        return nuMaxDiasReposicion;
    }

    public void setNuMaxDiasReposicion(Integer nuMaxDiasReposicion) {
        this.nuMaxDiasReposicion = nuMaxDiasReposicion;
    }

    public Integer getNuMinDiasReposicion() {
        return nuMinDiasReposicion;
    }

    public void setNuMinDiasReposicion(Integer nuMinDiasReposicion) {
        this.nuMinDiasReposicion = nuMinDiasReposicion;
    }

    public String getNuPedidoProducto() {
        return nuPedidoProducto;
    }

    public void setNuPedidoProducto(String nuPedidoProducto) {
        this.nuPedidoProducto = nuPedidoProducto;
    }

    public String getNuRevisionProducto() {
        return nuRevisionProducto;
    }

    public void setNuRevisionProducto(String nuRevisionProducto) {
        this.nuRevisionProducto = nuRevisionProducto;
    }

    public String getTiPedidoProducto() {
        return tiPedidoProducto;
    }

    public void setTiPedidoProducto(String tiPedidoProducto) {
        this.tiPedidoProducto = tiPedidoProducto;
    }

    public ArrayList<ListaPedidosReposicion> getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(ArrayList<ListaPedidosReposicion> detallePedido) {
        this.detallePedido = detallePedido;
    }        
    
    public ListaPedidosReposicion() {
        initBasic();
    }    
    
    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }  
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.getPrimaryKey() != null ? this.getPrimaryKey().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ListaPedidosReposicion other = (ListaPedidosReposicion) obj;
        if (this.getPrimaryKey() != other.getPrimaryKey() && (this.getPrimaryKey() == null || !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        System.out.println("Pks de Producto = "+this.getPrimaryKey() +" = "+other.getPrimaryKey());
        return true;
    }  
        
}
