package atux.modelbd;

import atux.core.DatoArchivo;
import atux.core.JAbstractModelBD;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class Producto extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;   
    public static final String nt = "LGTM_PRODUCTO";
    
    public static final String[] COLUMNA_PK ={"CO_COMPANIA", "CO_PRODUCTO", "NU_REVISION_PRODUCTO"};
    public static final String COLUMNA_ACTIVO = "ES_PRODUCTO";
    
    public static final String[] tipoProducto = {"Producto Terminado","Componente","Insumo"};
    public static final String[] UNIDADES = {"CAJA","UNIDAD","MILLAR","CARTUCHO","PAQUETE"};
    
    private FileInputStream imagen; 
    private DatoArchivo dat;   
    private Laboratorio laboratorio;
    
    private String coCompania;
    private String coProducto;
    private String nuRevisionProducto;
    private String deCortaProducto;
    private String deProducto;
    private String coFactorPrecio;
    private String coMoneda;
    private String coImpuesto_1;
    private String coImpuesto_2;
    private String coLaboratorio;
    private String coLineaProducto;
    private String coGrupoProducto;
    private String coCategoriaCcr;
    private String coClasificacionIms;
    private String coCategoriaSb;
    private String coSubcategoriaSb;
    private String coUnidadMedida;
    private Integer vaUnidadMedida;
    private String coUnidadCompra;
    private String coUnidadVenta;
    private String coUnidadContenido;
    private Integer vaUnidadContenido;
    private String inProdFraccionable;
    private Double vaPrecioCompra;
    private Double vaPrecioPromedio;
    private Double vaBono;
    private Double pcBono;
    private String deUnidadProducto;
    private String inRecetaMedica;
    private String esProducto;
    private String idCreaProducto;
    private Date   feCreaProducto;
    private String idModProducto;
    private Date   feModProducto;
    private String coClaseVenta;
    private String coGrupoProdErp;
    private String coLineaProdErp;
    private String coOtc;
    private String coGrupoTerapeutico;
    private String coGiro;
    private String coGrupoAnatomico;
    private String coFormaPresentacion;
    private String coAccionTerapeutica;
    private String coAccionFarmaceutica;
    private Integer pcDescuentoBase;
    private String coViadm;
    private Double vaCostoProducto;
    private String deDetallePresentacionLargo;
    private Double vaPrecioPublico;
    private String coProductoSap;
    private String coTipoConsumo;
    private String coComprador;
    private String inReintegro;
    private String inDescontinuado;
    private String inControlado;
    private String caEmpaque;
    private String inConsignadoSap;
    private String coUnidadSap;
    private String tiMaterialSap;
    private String coUnidadFmSap;
    private String coGrupoExt;
    private String dePartidaArancelaria;
    private String  inPideMedico;

    // Nuevos    
    private String  coImpuesto1;
    private String  coImpuesto2;
    private String  coNivel01;
    private String  coNivel02;
    private String  coNivel03;
    private String  coNivel04;
    private String  coNivel05;
    private String  coProcedencia;
    private String  noControlDigemid;
    private String  noControlLote;
    private String  inControlLote;
    private String  noTipoPrecio;
    private Date    feVigencia;
    private Date    feUltVenta;
            
    //Variables para cargar Lista de productos complementarios
    private Double  vaVenta;
    private Integer caStockDisponible;
    private String  deLaboratorio;
    private Integer pcDescuento;
    private String  orden;
    private String  clase;
    private String  inProdFraccionado;

    //Datos de producto principio
    private String coPrincipioActivo;
    private String dePrincipioActivo;
    private String inPrincipioActivoPrincipal;

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
                CAMPOS_Maestros = {"CO_COMPANIA, CO_PRODUCTO, NU_REVISION_PRODUCTO, DE_PRODUCTO, CO_MONEDA, CO_IMPUESTO_1, CO_LABORATORIO, "
                + "CO_UNIDAD_MEDIDA, VA_UNIDAD_MEDIDA, VA_UNIDAD_CONTENIDO, IN_PROD_FRACCIONABLE, VA_PRECIO_COMPRA, "
                + "VA_PRECIO_PROMEDIO, VA_BONO, DE_UNIDAD_PRODUCTO, IN_RECETA_MEDICA, ES_PRODUCTO, ID_CREA_PRODUCTO, "
                + "FE_CREA_PRODUCTO, ID_MOD_PRODUCTO, FE_MOD_PRODUCTO, PC_DESCUENTO_BASE, VA_COSTO_PRODUCTO, "
                + "VA_PRECIO_PUBLICO, IN_CONTROLADO, CO_NIVEL_01, CO_NIVEL_02, CO_NIVEL_03, CO_NIVEL_04, CO_NIVEL_05, "
                + "CO_PROCEDENCIA, FE_VIGENCIA, IN_CONTROL_LOTE, IN_PIDE_MEDICO"};

        public static final String[]
                CAMPOS_Maestros_SINPK = {"DE_PRODUCTO, CO_MONEDA, CO_IMPUESTO_1, CO_LABORATORIO, "
                + "CO_UNIDAD_MEDIDA, VA_UNIDAD_MEDIDA, VA_UNIDAD_CONTENIDO, IN_PROD_FRACCIONABLE, VA_PRECIO_COMPRA, "
                + "VA_PRECIO_PROMEDIO, VA_BONO, DE_UNIDAD_PRODUCTO, IN_RECETA_MEDICA, ES_PRODUCTO, ID_CREA_PRODUCTO, "
                + "FE_CREA_PRODUCTO, ID_MOD_PRODUCTO, FE_MOD_PRODUCTO, PC_DESCUENTO_BASE, VA_COSTO_PRODUCTO, "
                + "VA_PRECIO_PUBLICO, IN_CONTROLADO, CO_NIVEL_01, CO_NIVEL_02, CO_NIVEL_03, CO_NIVEL_04, CO_NIVEL_05, "
                + "CO_PROCEDENCIA, FE_VIGENCIA, IN_CONTROL_LOTE, IN_PIDE_MEDICO"};


    public static final String CAMPOS_NOID_NOIMAGE = "cod_barras, codigo, cod_del_fabricante, nombre, costo,"+ 
                                             "precioalmayor, precioalmenor, utilidad, aplica_igv, stock_min, tipo,"+
                                             "idmoneda, idclase, idmarca, idmodelo, ubicacion,unidad_principal,peso, activo";
        
    public static final String FULL_CAMPOS_NOIMAGEN = "idproducto, cod_barras, codigo, cod_del_fabricante, nombre, costo,"+ 
                                             "precioalmayor, precioalmenor, utilidad, aplica_igv, stock_min, tipo,"+
                                             "idmoneda,idclase, idmarca, idmodelo, ubicacion,unidad_principal,peso, activo";
    
    public static final String []
        CAMPOS_NO_ID = {"DE_CORTA_PRODUCTO, DE_PRODUCTO,"+
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
    
    public static final String []
        CAMPOS_SIN_ID = {"DE_CORTA_PRODUCTO, DE_PRODUCTO, CO_FACTOR_PRECIO, CO_MONEDA, CO_IMPUESTO_1, "
                      +  "CO_IMPUESTO_2, CO_LABORATORIO, CO_LINEA_PRODUCTO, CO_GRUPO_PRODUCTO, CO_CATEGORIA_CCR, CO_CLASIFICACION_IMS, CO_CATEGORIA_SB, "
                      +  "CO_SUBCATEGORIA_SB, CO_UNIDAD_MEDIDA, VA_UNIDAD_MEDIDA, CO_UNIDAD_COMPRA, CO_UNIDAD_VENTA, CO_UNIDAD_CONTENIDO, VA_UNIDAD_CONTENIDO, "
                      +  "IN_PROD_FRACCIONABLE, VA_PRECIO_COMPRA, VA_PRECIO_PROMEDIO, VA_BONO, PC_BONO, DE_UNIDAD_PRODUCTO, IN_RECETA_MEDICA, ES_PRODUCTO, "
                      +  "ID_CREA_PRODUCTO, FE_CREA_PRODUCTO, ID_MOD_PRODUCTO, FE_MOD_PRODUCTO, CO_CLASE_VENTA, CO_GRUPO_PROD_ERP, CO_LINEA_PROD_ERP, CO_OTC, "
                      +  "CO_GRUPO_TERAPEUTICO, CO_GIRO, CO_GRUPO_ANATOMICO, CO_FORMA_PRESENTACION, CO_ACCION_TERAPEUTICA, CO_ACCION_FARMACEUTICA, PC_DESCUENTO_BASE, "
                      +  "CO_VIADM, VA_COSTO_PRODUCTO, DE_DETALLE_PRESENTACION_LARGO, VA_PRECIO_PUBLICO, CO_PRODUCTO_SAP, CO_TIPO_CONSUMO, CO_COMPRADOR, "
                      +  "IN_REINTEGRO, IN_DESCONTINUADO, IN_CONTROLADO, IN_CONSIGNADO_SAP, CO_UNIDAD_SAP, TI_MATERIAL_SAP, CO_UNIDAD_FM_SAP, "
                      +  "CO_GRUPO_EXT, DE_PARTIDA_ARANCELARIA, CO_NIVEL_01, CO_NIVEL_02, CO_NIVEL_03, CO_NIVEL_04, CO_NIVEL_05, "
                      +  "CO_PROCEDENCIA, NO_CONTROL_DIGEMID, NO_CONTROL_LOTE, NO_TIPO_PRECIO, FE_VIGENCIA, FE_ULT_VENTA, IN_CONTROL_LOTE, IN_PIDE_MEDICO"};
    
    
    
    
    public static final String CAMPOS_OBLIGATORIOS = "idproducto,	cod_barras,	codigo,nombre, costo,"+ 
                                                     "precioalmayor, precioalmenor, utilidad, stock_min, tipo,idmoneda";
    
    public Producto() {
        initBasic();
    }

    public Producto(String coCompania, String coProducto, String nuRevisionProducto, String deCortaProducto, String deProducto, String coFactorPrecio, String coMoneda, String coImpuesto_1, String coImpuesto_2, String coLaboratorio, String coLineaProducto, String coGrupoProducto, String coCategoriaCcr, String coClasificacionIms, String coCategoriaSb, String coSubcategoriaSb, String coUnidadMedida, Integer vaUnidadMedida, String coUnidadCompra, String coUnidadVenta, String coUnidadContenido, Integer vaUnidadContenido, String inProdFraccionable, Double vaPrecioCompra, Double vaPrecioPromedio, Double vaBono, Double pcBono, String deUnidadProducto, String inRecetaMedica, String esProducto, String coClaseVenta, String coGrupoProdErp, String coLineaProdErp, String coOtc, String coGrupoTerapeutico, String coGiro, String coGrupoAnatomico, String coFormaPresentacion, String coAccionTerapeutica, String coAccionFarmaceutica, Integer pcDescuentoBase, String coViadm, Double vaCostoProducto, String deDetallePresentacionLargo, Double vaPrecioPublico, String coProductoSap, String coTipoConsumo, String coComprador, String inReintegro, String inDescontinuado, String inControlado, String inConsignadoSap, String coUnidadSap, String tiMaterialSap, String coUnidadFmSap, String coGrupoExt, String dePartidaArancelaria, FileInputStream imagen) {
        this.coCompania = coCompania;
        this.coProducto = coProducto;
        this.nuRevisionProducto = nuRevisionProducto;
        this.deCortaProducto = deCortaProducto;
        this.deProducto = deProducto;
        this.coFactorPrecio = coFactorPrecio;
        this.coMoneda = coMoneda;
        this.coImpuesto_1 = coImpuesto_1;
        this.coImpuesto_2 = coImpuesto_2;
        this.coLaboratorio = coLaboratorio;
        this.coLineaProducto = coLineaProducto;
        this.coGrupoProducto = coGrupoProducto;
        this.coCategoriaCcr = coCategoriaCcr;
        this.coClasificacionIms = coClasificacionIms;
        this.coCategoriaSb = coCategoriaSb;
        this.coSubcategoriaSb = coSubcategoriaSb;
        this.coUnidadMedida = coUnidadMedida;
        this.vaUnidadMedida = vaUnidadMedida;
        this.coUnidadCompra = coUnidadCompra;
        this.coUnidadVenta = coUnidadVenta;
        this.coUnidadContenido = coUnidadContenido;
        this.vaUnidadContenido = vaUnidadContenido;
        this.inProdFraccionable = inProdFraccionable;
        this.vaPrecioCompra = vaPrecioCompra;
        this.vaPrecioPromedio = vaPrecioPromedio;
        this.vaBono = vaBono;
        this.pcBono = pcBono;
        this.deUnidadProducto = deUnidadProducto;
        this.inRecetaMedica = inRecetaMedica;
        this.esProducto = esProducto;
        this.coClaseVenta = coClaseVenta;
        this.coGrupoProdErp = coGrupoProdErp;
        this.coLineaProdErp = coLineaProdErp;
        this.coOtc = coOtc;
        this.coGrupoTerapeutico = coGrupoTerapeutico;
        this.coGiro = coGiro;
        this.coGrupoAnatomico = coGrupoAnatomico;
        this.coFormaPresentacion = coFormaPresentacion;
        this.coAccionTerapeutica = coAccionTerapeutica;
        this.coAccionFarmaceutica = coAccionFarmaceutica;
        this.pcDescuentoBase = pcDescuentoBase;
        this.coViadm = coViadm;
        this.vaCostoProducto = vaCostoProducto;
        this.deDetallePresentacionLargo = deDetallePresentacionLargo;
        this.vaPrecioPublico = vaPrecioPublico;
        this.coProductoSap = coProductoSap;
        this.coTipoConsumo = coTipoConsumo;
        this.coComprador = coComprador;
        this.inReintegro = inReintegro;
        this.inDescontinuado = inDescontinuado;
        this.inControlado = inControlado;
//        this.caEmpaque = caEmpaque;
        this.inConsignadoSap = inConsignadoSap;
        this.coUnidadSap = coUnidadSap;
        this.tiMaterialSap = tiMaterialSap;
        this.coUnidadFmSap = coUnidadFmSap;
        this.coGrupoExt = coGrupoExt;
        this.dePartidaArancelaria = dePartidaArancelaria;
        this.imagen = imagen;
        initBasic();
    }        
    
    public String getCaEmpaque() {
        return caEmpaque;
    }

    public void setCaEmpaque(String caEmpaque) {
        this.caEmpaque = caEmpaque;
    }

    public String getCoAccionFarmaceutica() {
        return coAccionFarmaceutica;
    }

    public void setCoAccionFarmaceutica(String coAccionFarmaceutica) {
        this.coAccionFarmaceutica = coAccionFarmaceutica;
    }

    public String getCoAccionTerapeutica() {
        return coAccionTerapeutica;
    }

    public void setCoAccionTerapeutica(String coAccionTerapeutica) {
        this.coAccionTerapeutica = coAccionTerapeutica;
    }

    public String getCoCategoriaCcr() {
        return coCategoriaCcr;
    }

    public void setCoCategoriaCcr(String coCategoriaCcr) {
        this.coCategoriaCcr = coCategoriaCcr;
    }

    public String getCoCategoriaSb() {
        return coCategoriaSb;
    }

    public void setCoCategoriaSb(String coCategoriaSb) {
        this.coCategoriaSb = coCategoriaSb;
    }

    public String getCoClaseVenta() {
        return coClaseVenta;
    }

    public void setCoClaseVenta(String coClaseVenta) {
        this.coClaseVenta = coClaseVenta;
    }

    public String getCoClasificacionIms() {
        return coClasificacionIms;
    }

    public void setCoClasificacionIms(String coClasificacionIms) {
        this.coClasificacionIms = coClasificacionIms;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoComprador() {
        return coComprador;
    }

    public void setCoComprador(String coComprador) {
        this.coComprador = coComprador;
    }

    public String getCoFactorPrecio() {
        return coFactorPrecio;
    }

    public void setCoFactorPrecio(String coFactorPrecio) {
        this.coFactorPrecio = coFactorPrecio;
    }

    public String getCoFormaPresentacion() {
        return coFormaPresentacion;
    }

    public void setCoFormaPresentacion(String coFormaPresentacion) {
        this.coFormaPresentacion = coFormaPresentacion;
    }

    public String getCoGiro() {
        return coGiro;
    }

    public void setCoGiro(String coGiro) {
        this.coGiro = coGiro;
    }

    public String getCoGrupoAnatomico() {
        return coGrupoAnatomico;
    }

    public void setCoGrupoAnatomico(String coGrupoAnatomico) {
        this.coGrupoAnatomico = coGrupoAnatomico;
    }

    public String getCoGrupoExt() {
        return coGrupoExt;
    }

    public void setCoGrupoExt(String coGrupoExt) {
        this.coGrupoExt = coGrupoExt;
    }

    public String getCoGrupoProdErp() {
        return coGrupoProdErp;
    }

    public void setCoGrupoProdErp(String coGrupoProdErp) {
        this.coGrupoProdErp = coGrupoProdErp;
    }

    public String getCoGrupoProducto() {
        return coGrupoProducto;
    }

    public void setCoGrupoProducto(String coGrupoProducto) {
        this.coGrupoProducto = coGrupoProducto;
    }

    public String getCoGrupoTerapeutico() {
        return coGrupoTerapeutico;
    }

    public void setCoGrupoTerapeutico(String coGrupoTerapeutico) {
        this.coGrupoTerapeutico = coGrupoTerapeutico;
    }

    public String getCoImpuesto_1() {
        return coImpuesto_1;
    }

    public void setCoImpuesto_1(String coImpuesto_1) {
        this.coImpuesto_1 = coImpuesto_1;
    }

    public String getCoImpuesto_2() {
        return coImpuesto_2;
    }

    public void setCoImpuesto_2(String coImpuesto_2) {
        this.coImpuesto_2 = coImpuesto_2;
    }

    public String getCoLaboratorio() {
        return coLaboratorio;
    }

    public void setCoLaboratorio(String coLaboratorio) {
        this.coLaboratorio = coLaboratorio;
    }

    public String getCoLineaProdErp() {
        return coLineaProdErp;
    }

    public void setCoLineaProdErp(String coLineaProdErp) {
        this.coLineaProdErp = coLineaProdErp;
    }

    public String getCoLineaProducto() {
        return coLineaProducto;
    }

    public void setCoLineaProducto(String coLineaProducto) {
        this.coLineaProducto = coLineaProducto;
    }

    public String getCoMoneda() {
        return coMoneda;
    }

    public void setCoMoneda(String coMoneda) {
        this.coMoneda = coMoneda;
    }

    public String getCoOtc() {
        return coOtc;
    }

    public void setCoOtc(String coOtc) {
        this.coOtc = coOtc;
    }

    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    public String getCoProductoSap() {
        return coProductoSap;
    }

    public void setCoProductoSap(String coProductoSap) {
        this.coProductoSap = coProductoSap;
    }

    public String getCoSubcategoriaSb() {
        return coSubcategoriaSb;
    }

    public void setCoSubcategoriaSb(String coSubcategoriaSb) {
        this.coSubcategoriaSb = coSubcategoriaSb;
    }

    public String getCoTipoConsumo() {
        return coTipoConsumo;
    }

    public void setCoTipoConsumo(String coTipoConsumo) {
        this.coTipoConsumo = coTipoConsumo;
    }

    public String getCoUnidadCompra() {
        return coUnidadCompra;
    }

    public void setCoUnidadCompra(String coUnidadCompra) {
        this.coUnidadCompra = coUnidadCompra;
    }

    public String getCoUnidadContenido() {
        return coUnidadContenido;
    }

    public void setCoUnidadContenido(String coUnidadContenido) {
        this.coUnidadContenido = coUnidadContenido;
    }

    public String getCoUnidadFmSap() {
        return coUnidadFmSap;
    }

    public void setCoUnidadFmSap(String coUnidadFmSap) {
        this.coUnidadFmSap = coUnidadFmSap;
    }

    public String getCoUnidadMedida() {
        return coUnidadMedida;
    }

    public void setCoUnidadMedida(String coUnidadMedida) {
        this.coUnidadMedida = coUnidadMedida;
    }

    public String getCoUnidadSap() {
        return coUnidadSap;
    }

    public void setCoUnidadSap(String coUnidadSap) {
        this.coUnidadSap = coUnidadSap;
    }

    public String getCoUnidadVenta() {
        return coUnidadVenta;
    }

    public void setCoUnidadVenta(String coUnidadVenta) {
        this.coUnidadVenta = coUnidadVenta;
    }

    public String getCoViadm() {
        return coViadm;
    }

    public void setCoViadm(String coViadm) {
        this.coViadm = coViadm;
    }

    public DatoArchivo getDat() {
        return dat;
    }

    public void setDat(DatoArchivo dat) {
        this.dat = dat;
    }

    public String getDeCortaProducto() {
        return deCortaProducto;
    }

    public void setDeCortaProducto(String deCortaProducto) {
        this.deCortaProducto = deCortaProducto;
    }

    public String getDeDetallePresentacionLargo() {
        return deDetallePresentacionLargo;
    }

    public void setDeDetallePresentacionLargo(String deDetallePresentacionLargo) {
        this.deDetallePresentacionLargo = deDetallePresentacionLargo;
    }

    public String getDePartidaArancelaria() {
        return dePartidaArancelaria;
    }

    public void setDePartidaArancelaria(String dePartidaArancelaria) {
        this.dePartidaArancelaria = dePartidaArancelaria;
    }

    public String getDeProducto() {
        return deProducto;
    }

    public void setDeProducto(String deProducto) {
        this.deProducto = deProducto;
    }

    public String getDeUnidadProducto() {
        return deUnidadProducto;
    }

    public void setDeUnidadProducto(String deUnidadProducto) {
        this.deUnidadProducto = deUnidadProducto;
    }

    public String getEsProducto() {
        return esProducto;
    }

    public void setEsProducto(String esProducto) {
        this.esProducto = esProducto;
    }

    public Date getFeCreaProducto() {
        return feCreaProducto;
    }

    public void setFeCreaProducto(Date feCreaProducto) {
        this.feCreaProducto = feCreaProducto;
    }

    public Date getFeModProducto() {
        return feModProducto;
    }

    public void setFeModProducto(Date feModProducto) {
        this.feModProducto = feModProducto;
    }

    public String getIdCreaProducto() {
        return idCreaProducto;
    }

    public void setIdCreaProducto(String idCreaProducto) {
        this.idCreaProducto = idCreaProducto;
    }

    public String getIdModProducto() {
        return idModProducto;
    }

    public void setIdModProducto(String idModProducto) {
        this.idModProducto = idModProducto;
    }

    public String getInConsignadoSap() {
        return inConsignadoSap;
    }

    public void setInConsignadoSap(String inConsignadoSap) {
        this.inConsignadoSap = inConsignadoSap;
    }

    public String getInControlado() {
        return inControlado;
    }

    public void setInControlado(String inControlado) {
        this.inControlado = inControlado;
    }

    public String getInDescontinuado() {
        return inDescontinuado;
    }

    public void setInDescontinuado(String inDescontinuado) {
        this.inDescontinuado = inDescontinuado;
    }

    public String getInProdFraccionable() {
        return inProdFraccionable;
    }

    public void setInProdFraccionable(String inProdFraccionable) {
        this.inProdFraccionable = inProdFraccionable;
    }

    public String getInRecetaMedica() {
        return inRecetaMedica;
    }

    public void setInRecetaMedica(String inRecetaMedica) {
        this.inRecetaMedica = inRecetaMedica;
    }

    public String getInReintegro() {
        return inReintegro;
    }

    public void setInReintegro(String inReintegro) {
        this.inReintegro = inReintegro;
    }

    public String getNuRevisionProducto() {
        return nuRevisionProducto;
    }

    public void setNuRevisionProducto(String nuRevisionProducto) {
        this.nuRevisionProducto = nuRevisionProducto;
    }

    public Double getPcBono() {
        return pcBono;
    }

    public void setPcBono(Double pcBono) {
        this.pcBono = pcBono;
    }

    public int getPcDescuentoBase() {
        return pcDescuentoBase;
    }

    public void setPcDescuentoBase(Integer pcDescuentoBase) {
        this.pcDescuentoBase = pcDescuentoBase;
    }

    public String getTiMaterialSap() {
        return tiMaterialSap;
    }

    public void setTiMaterialSap(String tiMaterialSap) {
        this.tiMaterialSap = tiMaterialSap;
    }

    public Double getVaBono() {
        return vaBono;
    }

    public void setVaBono(Double vaBono) {
        this.vaBono = vaBono;
    }

    public Double getVaCostoProducto() {
        return vaCostoProducto;
    }

    public void setVaCostoProducto(Double vaCostoProducto) {
        this.vaCostoProducto = vaCostoProducto;
    }

    public Double getVaPrecioCompra() {
        return vaPrecioCompra;
    }

    public void setVaPrecioCompra(Double vaPrecioCompra) {
        this.vaPrecioCompra = vaPrecioCompra;
    }

    public Double getVaPrecioPromedio() {
        return vaPrecioPromedio;
    }

    public void setVaPrecioPromedio(Double vaPrecioPromedio) {
        this.vaPrecioPromedio = vaPrecioPromedio;
    }

    public Double getVaPrecioPublico() {
        return vaPrecioPublico;
    }

    public void setVaPrecioPublico(Double vaPrecioPublico) {
        this.vaPrecioPublico = vaPrecioPublico;
    }

    public int getVaUnidadContenido() {
        return vaUnidadContenido;
    }

    public void setVaUnidadContenido(Integer vaUnidadContenido) {
        this.vaUnidadContenido = vaUnidadContenido;
    }

    public int getVaUnidadMedida() {
        return vaUnidadMedida;
    }

    public void setVaUnidadMedida(Integer vaUnidadMedida) {
        this.vaUnidadMedida = vaUnidadMedida;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Integer getCaStockDisponible() {
        return caStockDisponible;
    }

    public void setCaStockDisponible(Integer caStockDisponible) {
        this.caStockDisponible = caStockDisponible;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getDeLaboratorio() {
        return deLaboratorio;
    }

    public void setDeLaboratorio(String deLaboratorio) {
        this.deLaboratorio = deLaboratorio;
    }

    public String getInProdFraccionado() {
        return inProdFraccionado;
    }

    public void setInProdFraccionado(String inProdFraccionado) {
        this.inProdFraccionado = inProdFraccionado;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public Integer getPcDescuento() {
        return pcDescuento;
    }

    public void setPcDescuento(Integer pcDescuento) {
        this.pcDescuento = pcDescuento;
    }

    public Double getVaVenta() {
        return vaVenta;
    }

    public void setVaVenta(Double vaVenta) {
        this.vaVenta = vaVenta;
    }

    public String getCoImpuesto1() {
        return coImpuesto1;
    }

    public void setCoImpuesto1(String coImpuesto1) {
        this.coImpuesto1 = coImpuesto1;
    }

    public String getCoImpuesto2() {
        return coImpuesto2;
    }

    public void setCoImpuesto2(String coImpuesto2) {
        this.coImpuesto2 = coImpuesto2;
    }

    public String getCoNivel01() {
        return coNivel01;
    }

    public void setCoNivel01(String coNivel01) {
        this.coNivel01 = coNivel01;
    }

    public String getCoNivel02() {
        return coNivel02;
    }

    public void setCoNivel02(String coNivel02) {
        this.coNivel02 = coNivel02;
    }

    public String getCoNivel03() {
        return coNivel03;
    }

    public void setCoNivel03(String coNivel03) {
        this.coNivel03 = coNivel03;
    }

    public String getCoNivel04() {
        return coNivel04;
    }

    public void setCoNivel04(String coNivel04) {
        this.coNivel04 = coNivel04;
    }

    public String getCoNivel05() {
        return coNivel05;
    }

    public void setCoNivel05(String coNivel05) {
        this.coNivel05 = coNivel05;
    }

    public String getCoProcedencia() {
        return coProcedencia;
    }

    public void setCoProcedencia(String coProcedencia) {
        this.coProcedencia = coProcedencia;
    }

    public Date getFeUltVenta() {
        return feUltVenta;
    }

    public void setFeUltVenta(Date feUltVenta) {
        this.feUltVenta = feUltVenta;
    }

    public Date getFeVigencia() {
        return feVigencia;
    }

    public void setFeVigencia(Date feVigencia) {
        this.feVigencia = feVigencia;
    }

    public String getNoControlDigemid() {
        return noControlDigemid;
    }

    public void setNoControlDigemid(String noControlDigemid) {
        this.noControlDigemid = noControlDigemid;
    }

    public String getNoControlLote() {
        return noControlLote;
    }

    public void setNoControlLote(String noControlLote) {
        this.noControlLote = noControlLote;
    }

    public String getNoTipoPrecio() {
        return noTipoPrecio;
    }

    public void setNoTipoPrecio(String noTipoPrecio) {
        this.noTipoPrecio = noTipoPrecio;
    }
    
    public String getInControlLote() {
        return inControlLote;
    }
    
    public void setInControlLote(String inControlLote) {
        this.inControlLote = inControlLote;
    }

    public String getInPideMedico() {
        return inPideMedico;
    }
    
    public void setInPideMedico(String inPideMedico) {
        this.inPideMedico = inPideMedico;
    }

    public String getCoPrincipioActivo() {
        return coPrincipioActivo;
    }

    public void setCoPrincipioActivo(String coPrincipioActivo) {
        this.coPrincipioActivo = coPrincipioActivo;
    }

    public String getDePrincipioActivo() {
        return dePrincipioActivo;
    }

    public void setDePrincipioActivo(String dePrincipioActivo) {
        this.dePrincipioActivo = dePrincipioActivo;
    }

    public String getInPrincipioActivoPrincipal() {
        return inPrincipioActivoPrincipal;
    }

    public void setInPrincipioActivoPrincipal(String inPrincipioActivoPrincipal) {
        this.inPrincipioActivoPrincipal = inPrincipioActivoPrincipal;
    }

    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }  

    public FileInputStream getImagen() {
        return imagen;
    }

    public void setImagen(FileInputStream imagen) {
        this.imagen = imagen;
    }
    
    public void setImagenDA(FileInputStream foto,int longitud)
    {
        dat = new DatoArchivo(foto,longitud);
        this.imagen = foto;
    }
    
     public void setImagenDA(DatoArchivo dat)
    {
        this.dat = dat;
        this.imagen = dat.getFis();
    }
     
     public DatoArchivo getImagen(String s)
    {
        return dat;
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
        final Producto other = (Producto) obj;
        if (this.getPrimaryKey() != other.getPrimaryKey() && (this.getPrimaryKey() == null || !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        System.out.println("Pks de Producto = "+this.getPrimaryKey() +" = "+other.getPrimaryKey());
        return true;
    }  
    
    
}
