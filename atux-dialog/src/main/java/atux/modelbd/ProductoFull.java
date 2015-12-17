package atux.modelbd;

import atux.core.DatoArchivo;
import atux.core.JAbstractModelBD;

import java.io.FileInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;


public class ProductoFull extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    public static final String nt = "LGTM_PRODUCTO";

    public static final String[] COLUMNA_PK ={"CO_COMPANIA", "CO_PRODUCTO", "NU_REVISION_PRODUCTO"};
    public static final String COLUMNA_ACTIVO = "ES_PRODUCTO";

    private String  tmpCoCompania;
    private String  tmpCoProducto;
    private String  tmpCoProductoAnt;
    private String  tmpNuRevisionProducto;
    private String  tmpDeProducto;
    private String  tmpCoMoneda;
    private String  tmpCoImpuesto1;
    private String  tmpCoLaboratorio;
    private String  tmpCoUnidadMedida;
    private Integer tmpVaUnidadMedida;
    private Integer tmpVaUnidadContenido;
    private String  tmpInProdFraccionable;
    private Double  tmpVaPrecioCompra;
    private Double  tmpVaPrecioPromedio;
    private Double  tmpVaBono;
    private String  tmpDeUnidadProducto;
    private String  tmpInRecetaMedica;
    private String  tmpEsProducto;
    private String  tmpIdCreaProducto;
    private Date    tmpFeCreaProducto;
    private String  tmpIdModProducto;
    private Date    tmpFeModProducto;
    private Double  tmpPcDescuentoBase;
    private Double  tmpVaCostoProducto;
    private Double  tmpVaPrecioPublico;
    private String  tmpInControlado;
    private String  tmpCoProductoBk;
    private String  tmpCoNivel01;
    private String  tmpCoNivel02;
    private String  tmpCoNivel03;
    private String  tmpCoNivel04;
    private String  tmpCoNivel05;
    private String  tmpCoProcedencia;
    private Date    tmpFeVigencia;
    private String  tmpInControlLote;
    private String  tmpMinFracc;
    private String  tmpMaxFracc;
    private String  tmpInPideMedico;
    private Integer tmpCaEmpaqueMax;
    private String  tplCoCompania;
    private String  tplCoLocal;
    private String  tplCoProducto;
    private String  tplNuRevisionProducto;
    private Double  tplVaVenta;
    private String  tplCoMoneda;
    private Integer tplVaFraccion;
    private String  tplInProdFraccionado;
    private Integer tplCaStockDisponible;
    private Integer tplCaStockTransito;
    private Integer tplCaStockComprometido;
    private Integer tplCaStockMinimo;
    private Integer tplCaStockMaximo;
    private Double  tplPcDescuento1;
    private String  tplDeUnidadFraccion;
    private String  tplEsProdLocal;
    private String  tplIdCreaProdLocal;
    private Date    tplFeCreaProdLocal;
    private String  tplIdModProdLocal;
    private Date    tplFeModProdLocal;
    private String  tplCoProductoBk;
    private String  tplCoProducto2;
    private String  tg1DeLineaProdErp;
    private String  tg1CoNivel01;
    private String  tg1CoNivelSuperior;
    private String  tg2DeGrupoProdErp;
    private String  tg2CoNivel02;
    private String  tg2CoNivelSuperior;
    private String  tg3DeGrupoAnatomico;
    private String  tg3CoNivel03;
    private String  tg3CoNivelSuperior;
    private String  tg4DeGrupoTerapeutico;
    private String  tg4CoNivel04;
    private String  tg4CoNivelSuperior;
    private String  tg5DeAccionTerapeutica;
    private String  tg5CoNivel05;
    private String  tg5CoNivelSuperior;
    private String  tlbCoLaboratorio;
    private String  tlbDeLaboratorio;
    private String  tlbInLabInventario;
    private String  tpsCoPais;
    private String  tpsDePais;
    private String  timpCoImpuesto;
    private String  timpDeCortaImpuesto;
    private String  timpDeImpuesto;
    private Double  timpPcImpuesto;

    private Double  CalculoPrecioPublico;

    public String getTmpCoCompania() {
        return tmpCoCompania;
    }

    public void setTmpCoCompania(String tmpCoCompania) {
        this.tmpCoCompania = tmpCoCompania;
    }

    public String getTmpCoProducto() {
        return tmpCoProducto;
    }

    public void setTmpCoProducto(String tmpCoProducto) {
        this.tmpCoProducto = tmpCoProducto;
    }

    public String getTmpCoProductoAnt() {
        return tmpCoProductoAnt;
    }

    public void setTmpCoProductoAnt(String tmpCoProductoAnt) {
        this.tmpCoProductoAnt = tmpCoProductoAnt;
    }

    public String getTmpNuRevisionProducto() {
        return tmpNuRevisionProducto;
    }

    public void setTmpNuRevisionProducto(String tmpNuRevisionProducto) {
        this.tmpNuRevisionProducto = tmpNuRevisionProducto;
    }

    public String getTmpDeProducto() {
        return tmpDeProducto;
    }

    public void setTmpDeProducto(String tmpDeProducto) {
        this.tmpDeProducto = tmpDeProducto;
    }

    public String getTmpCoMoneda() {
        return tmpCoMoneda;
    }

    public void setTmpCoMoneda(String tmpCoMoneda) {
        this.tmpCoMoneda = tmpCoMoneda;
    }

    public String getTmpCoImpuesto1() {
        return tmpCoImpuesto1;
    }

    public void setTmpCoImpuesto1(String tmpCoImpuesto1) {
        this.tmpCoImpuesto1 = tmpCoImpuesto1;
    }

    public String getTmpCoLaboratorio() {
        return tmpCoLaboratorio;
    }

    public void setTmpCoLaboratorio(String tmpCoLaboratorio) {
        this.tmpCoLaboratorio = tmpCoLaboratorio;
    }

    public String getTmpCoUnidadMedida() {
        return tmpCoUnidadMedida;
    }

    public void setTmpCoUnidadMedida(String tmpCoUnidadMedida) {
        this.tmpCoUnidadMedida = tmpCoUnidadMedida;
    }

    public Integer getTmpVaUnidadMedida() {
        return tmpVaUnidadMedida;
    }

    public void setTmpVaUnidadMedida(Integer tmpVaUnidadMedida) {
        this.tmpVaUnidadMedida = tmpVaUnidadMedida;
    }

    public Integer getTmpVaUnidadContenido() {
        return tmpVaUnidadContenido;
    }

    public void setTmpVaUnidadContenido(Integer tmpVaUnidadContenido) {
        this.tmpVaUnidadContenido = tmpVaUnidadContenido;
    }

    public String getTmpInProdFraccionable() {
        return tmpInProdFraccionable;
    }

    public void setTmpInProdFraccionable(String tmpInProdFraccionable) {
        this.tmpInProdFraccionable = tmpInProdFraccionable;
    }

    public Double getTmpVaPrecioCompra() {
        return tmpVaPrecioCompra;
    }

    public void setTmpVaPrecioCompra(Double tmpVaPrecioCompra) {
        this.tmpVaPrecioCompra = tmpVaPrecioCompra;
    }

    public Double getTmpVaPrecioPromedio() {
        return tmpVaPrecioPromedio;
    }

    public void setTmpVaPrecioPromedio(Double tmpVaPrecioPromedio) {
        this.tmpVaPrecioPromedio = tmpVaPrecioPromedio;
    }

    public Double getTmpVaBono() {
        return tmpVaBono;
    }

    public void setTmpVaBono(Double tmpVaBono) {
        this.tmpVaBono = tmpVaBono;
    }

    public String getTmpDeUnidadProducto() {
        return tmpDeUnidadProducto;
    }

    public void setTmpDeUnidadProducto(String tmpDeUnidadProducto) {
        this.tmpDeUnidadProducto = tmpDeUnidadProducto;
    }

    public String getTmpInRecetaMedica() {
        return tmpInRecetaMedica;
    }

    public void setTmpInRecetaMedica(String tmpInRecetaMedica) {
        this.tmpInRecetaMedica = tmpInRecetaMedica;
    }

    public String getTmpEsProducto() {
        return tmpEsProducto;
    }

    public void setTmpEsProducto(String tmpEsProducto) {
        this.tmpEsProducto = tmpEsProducto;
    }

    public String getTmpIdCreaProducto() {
        return tmpIdCreaProducto;
    }

    public void setTmpIdCreaProducto(String tmpIdCreaProducto) {
        this.tmpIdCreaProducto = tmpIdCreaProducto;
    }

    public Date getTmpFeCreaProducto() {
        return tmpFeCreaProducto;
    }

    public void setTmpFeCreaProducto(Date tmpFeCreaProducto) {
        this.tmpFeCreaProducto = tmpFeCreaProducto;
    }

    public String getTmpIdModProducto() {
        return tmpIdModProducto;
    }

    public void setTmpIdModProducto(String tmpIdModProducto) {
        this.tmpIdModProducto = tmpIdModProducto;
    }

    public Date getTmpFeModProducto() {
        return tmpFeModProducto;
    }

    public void setTmpFeModProducto(Date tmpFeModProducto) {
        this.tmpFeModProducto = tmpFeModProducto;
    }

    public Double getTmpPcDescuentoBase() {
        return tmpPcDescuentoBase;
    }

    public void setTmpPcDescuentoBase(Double tmpPcDescuentoBase) {
        this.tmpPcDescuentoBase = tmpPcDescuentoBase;
    }

    public Double getTmpVaCostoProducto() {
        return tmpVaCostoProducto;
    }

    public void setTmpVaCostoProducto(Double tmpVaCostoProducto) {
        this.tmpVaCostoProducto = tmpVaCostoProducto;
    }

    public Double getTmpVaPrecioPublico() {
        return tmpVaPrecioPublico;
    }

    public void setTmpVaPrecioPublico(Double tmpVaPrecioPublico) {
        this.tmpVaPrecioPublico = tmpVaPrecioPublico;
    }

    public String getTmpInControlado() {
        return tmpInControlado;
    }

    public void setTmpInControlado(String tmpInControlado) {
        this.tmpInControlado = tmpInControlado;
    }

    public String getTmpCoProductoBk() {
        return tmpCoProductoBk;
    }

    public void setTmpCoProductoBk(String tmpCoProductoBk) {
        this.tmpCoProductoBk = tmpCoProductoBk;
    }

    public String getTmpCoNivel01() {
        return tmpCoNivel01;
    }

    public void setTmpCoNivel01(String tmpCoNivel01) {
        this.tmpCoNivel01 = tmpCoNivel01;
    }

    public String getTmpCoNivel02() {
        return tmpCoNivel02;
    }

    public void setTmpCoNivel02(String tmpCoNivel02) {
        this.tmpCoNivel02 = tmpCoNivel02;
    }

    public String getTmpCoNivel03() {
        return tmpCoNivel03;
    }

    public void setTmpCoNivel03(String tmpCoNivel03) {
        this.tmpCoNivel03 = tmpCoNivel03;
    }

    public String getTmpCoNivel04() {
        return tmpCoNivel04;
    }

    public void setTmpCoNivel04(String tmpCoNivel04) {
        this.tmpCoNivel04 = tmpCoNivel04;
    }

    public String getTmpCoNivel05() {
        return tmpCoNivel05;
    }

    public void setTmpCoNivel05(String tmpCoNivel05) {
        this.tmpCoNivel05 = tmpCoNivel05;
    }

    public String getTmpCoProcedencia() {
        return tmpCoProcedencia;
    }

    public void setTmpCoProcedencia(String tmpCoProcedencia) {
        this.tmpCoProcedencia = tmpCoProcedencia;
    }

    public Date getTmpFeVigencia() {
        return tmpFeVigencia;
    }

    public void setTmpFeVigencia(Date tmpFeVigencia) {
        this.tmpFeVigencia = tmpFeVigencia;
    }

    public String getTmpInControlLote() {
        return tmpInControlLote;
    }

    public void setTmpInControlLote(String tmpInControlLote) {
        this.tmpInControlLote = tmpInControlLote;
    }

    public String getTmpMinFracc() {
        return tmpMinFracc;
    }

    public void setTmpMinFracc(String tmpMinFracc) {
        this.tmpMinFracc = tmpMinFracc;
    }

    public String getTmpMaxFracc() {
        return tmpMaxFracc;
    }

    public void setTmpMaxFracc(String tmpMaxFracc) {
        this.tmpMaxFracc = tmpMaxFracc;
    }

    public String getTmpInPideMedico() {
        return tmpInPideMedico;
    }

    public void setTmpInPideMedico(String tmpInPideMedico) {
        this.tmpInPideMedico = tmpInPideMedico;
    }

    public Integer getTmpCaEmpaqueMax() {
        return tmpCaEmpaqueMax;
    }

    public void setTmpCaEmpaqueMax(Integer tmpCaEmpaqueMax) {
        this.tmpCaEmpaqueMax = tmpCaEmpaqueMax;
    }

    public String getTplCoCompania() {
        return tplCoCompania;
    }

    public void setTplCoCompania(String tplCoCompania) {
        this.tplCoCompania = tplCoCompania;
    }

    public String getTplCoLocal() {
        return tplCoLocal;
    }

    public void setTplCoLocal(String tplCoLocal) {
        this.tplCoLocal = tplCoLocal;
    }

    public String getTplCoProducto() {
        return tplCoProducto;
    }

    public void setTplCoProducto(String tplCoProducto) {
        this.tplCoProducto = tplCoProducto;
    }

    public String getTplNuRevisionProducto() {
        return tplNuRevisionProducto;
    }

    public void setTplNuRevisionProducto(String tplNuRevisionProducto) {
        this.tplNuRevisionProducto = tplNuRevisionProducto;
    }

    public Double getTplVaVenta() {
        return tplVaVenta;
    }

    public void setTplVaVenta(Double tplVaVenta) {
        this.tplVaVenta = tplVaVenta;
    }

    public String getTplCoMoneda() {
        return tplCoMoneda;
    }

    public void setTplCoMoneda(String tplCoMoneda) {
        this.tplCoMoneda = tplCoMoneda;
    }

    public Integer getTplVaFraccion() {
        return tplVaFraccion;
    }

    public void setTplVaFraccion(Integer tplVaFraccion) {
        this.tplVaFraccion = tplVaFraccion;
    }

    public String getTplInProdFraccionado() {
        return tplInProdFraccionado;
    }

    public void setTplInProdFraccionado(String tplInProdFraccionado) {
        this.tplInProdFraccionado = tplInProdFraccionado;
    }

    public Integer getTplCaStockDisponible() {
        return tplCaStockDisponible;
    }

    public void setTplCaStockDisponible(Integer tplCaStockDisponible) {
        this.tplCaStockDisponible = tplCaStockDisponible;
    }

    public Integer getTplCaStockTransito() {
        return tplCaStockTransito;
    }

    public void setTplCaStockTransito(Integer tplCaStockTransito) {
        this.tplCaStockTransito = tplCaStockTransito;
    }

    public Integer getTplCaStockComprometido() {
        return tplCaStockComprometido;
    }

    public void setTplCaStockComprometido(Integer tplCaStockComprometido) {
        this.tplCaStockComprometido = tplCaStockComprometido;
    }

    public Integer getTplCaStockMinimo() {
        return tplCaStockMinimo;
    }

    public void setTplCaStockMinimo(Integer tplCaStockMinimo) {
        this.tplCaStockMinimo = tplCaStockMinimo;
    }

    public Integer getTplCaStockMaximo() {
        return tplCaStockMaximo;
    }

    public void setTplCaStockMaximo(Integer tplCaStockMaximo) {
        this.tplCaStockMaximo = tplCaStockMaximo;
    }

    public Double getTplPcDescuento1() {
        return tplPcDescuento1;
    }

    public void setTplPcDescuento1(Double tplPcDescuento1) {
        this.tplPcDescuento1 = tplPcDescuento1;
    }

    public String getTplDeUnidadFraccion() {
        return tplDeUnidadFraccion;
    }

    public void setTplDeUnidadFraccion(String tplDeUnidadFraccion) {
        this.tplDeUnidadFraccion = tplDeUnidadFraccion;
    }

    public String getTplEsProdLocal() {
        return tplEsProdLocal;
    }

    public void setTplEsProdLocal(String tplEsProdLocal) {
        this.tplEsProdLocal = tplEsProdLocal;
    }

    public String getTplIdCreaProdLocal() {
        return tplIdCreaProdLocal;
    }

    public void setTplIdCreaProdLocal(String tplIdCreaProdLocal) {
        this.tplIdCreaProdLocal = tplIdCreaProdLocal;
    }

    public Date getTplFeCreaProdLocal() {
        return tplFeCreaProdLocal;
    }

    public void setTplFeCreaProdLocal(Date tplFeCreaProdLocal) {
        this.tplFeCreaProdLocal = tplFeCreaProdLocal;
    }

    public String getTplIdModProdLocal() {
        return tplIdModProdLocal;
    }

    public void setTplIdModProdLocal(String tplIdModProdLocal) {
        this.tplIdModProdLocal = tplIdModProdLocal;
    }

    public Date getTplFeModProdLocal() {
        return tplFeModProdLocal;
    }

    public void setTplFeModProdLocal(Date tplFeModProdLocal) {
        this.tplFeModProdLocal = tplFeModProdLocal;
    }

    public String getTplCoProductoBk() {
        return tplCoProductoBk;
    }

    public void setTplCoProductoBk(String tplCoProductoBk) {
        this.tplCoProductoBk = tplCoProductoBk;
    }

    public String getTplCoProducto2() {
        return tplCoProducto2;
    }

    public void setTplCoProducto2(String tplCoProducto2) {
        this.tplCoProducto2 = tplCoProducto2;
    }

    public String getTg1DeLineaProdErp() {
        return tg1DeLineaProdErp;
    }

    public void setTg1DeLineaProdErp(String tg1DeLineaProdErp) {
        this.tg1DeLineaProdErp = tg1DeLineaProdErp;
    }

    public String getTg1CoNivel01() {
        return tg1CoNivel01;
    }

    public void setTg1CoNivel01(String tg1CoNivel01) {
        this.tg1CoNivel01 = tg1CoNivel01;
    }

    public String getTg1CoNivelSuperior() {
        return tg1CoNivelSuperior;
    }

    public void setTg1CoNivelSuperior(String tg1CoNivelSuperior) {
        this.tg1CoNivelSuperior = tg1CoNivelSuperior;
    }

    public String getTg2DeGrupoProdErp() {
        return tg2DeGrupoProdErp;
    }

    public void setTg2DeGrupoProdErp(String tg2DeGrupoProdErp) {
        this.tg2DeGrupoProdErp = tg2DeGrupoProdErp;
    }

    public String getTg2CoNivel02() {
        return tg2CoNivel02;
    }

    public void setTg2CoNivel02(String tg2CoNivel02) {
        this.tg2CoNivel02 = tg2CoNivel02;
    }

    public String getTg2CoNivelSuperior() {
        return tg2CoNivelSuperior;
    }

    public void setTg2CoNivelSuperior(String tg2CoNivelSuperior) {
        this.tg2CoNivelSuperior = tg2CoNivelSuperior;
    }

    public String getTg3DeGrupoAnatomico() {
        return tg3DeGrupoAnatomico;
    }

    public void setTg3DeGrupoAnatomico(String tg3DeGrupoAnatomico) {
        this.tg3DeGrupoAnatomico = tg3DeGrupoAnatomico;
    }

    public String getTg3CoNivel03() {
        return tg3CoNivel03;
    }

    public void setTg3CoNivel03(String tg3CoNivel03) {
        this.tg3CoNivel03 = tg3CoNivel03;
    }

    public String getTg3CoNivelSuperior() {
        return tg3CoNivelSuperior;
    }

    public void setTg3CoNivelSuperior(String tg3CoNivelSuperior) {
        this.tg3CoNivelSuperior = tg3CoNivelSuperior;
    }

    public String getTg4DeGrupoTerapeutico() {
        return tg4DeGrupoTerapeutico;
    }

    public void setTg4DeGrupoTerapeutico(String tg4DeGrupoTerapeutico) {
        this.tg4DeGrupoTerapeutico = tg4DeGrupoTerapeutico;
    }

    public String getTg4CoNivel04() {
        return tg4CoNivel04;
    }

    public void setTg4CoNivel04(String tg4CoNivel04) {
        this.tg4CoNivel04 = tg4CoNivel04;
    }

    public String getTg4CoNivelSuperior() {
        return tg4CoNivelSuperior;
    }

    public void setTg4CoNivelSuperior(String tg4CoNivelSuperior) {
        this.tg4CoNivelSuperior = tg4CoNivelSuperior;
    }

    public String getTg5DeAccionTerapeutica() {
        return tg5DeAccionTerapeutica;
    }

    public void setTg5DeAccionTerapeutica(String tg5DeAccionTerapeutica) {
        this.tg5DeAccionTerapeutica = tg5DeAccionTerapeutica;
    }

    public String getTg5CoNivel05() {
        return tg5CoNivel05;
    }

    public void setTg5CoNivel05(String tg5CoNivel05) {
        this.tg5CoNivel05 = tg5CoNivel05;
    }

    public String getTg5CoNivelSuperior() {
        return tg5CoNivelSuperior;
    }

    public void setTg5CoNivelSuperior(String tg5CoNivelSuperior) {
        this.tg5CoNivelSuperior = tg5CoNivelSuperior;
    }

    public String getTlbCoLaboratorio() {
        return tlbCoLaboratorio;
    }

    public void setTlbCoLaboratorio(String tlbCoLaboratorio) {
        this.tlbCoLaboratorio = tlbCoLaboratorio;
    }

    public String getTlbDeLaboratorio() {
        return tlbDeLaboratorio;
    }

    public void setTlbDeLaboratorio(String tlbDeLaboratorio) {
        this.tlbDeLaboratorio = tlbDeLaboratorio;
    }

    public String getTlbInLabInventario() {
        return tlbInLabInventario;
    }

    public void setTlbInLabInventario(String tlbInLabInventario) {
        this.tlbInLabInventario = tlbInLabInventario;
    }

    public String getTpsCoPais() {
        return tpsCoPais;
    }

    public void setTpsCoPais(String tpsCoPais) {
        this.tpsCoPais = tpsCoPais;
    }

    public String getTpsDePais() {
        return tpsDePais;
    }

    public void setTpsDePais(String tpsDePais) {
        this.tpsDePais = tpsDePais;
    }

    public String getTimpCoImpuesto() {
        return timpCoImpuesto;
    }

    public void setTimpCoImpuesto(String timpCoImpuesto) {
        this.timpCoImpuesto = timpCoImpuesto;
    }

    public String getTimpDeCortaImpuesto() {
        return timpDeCortaImpuesto;
    }

    public void setTimpDeCortaImpuesto(String timpDeCortaImpuesto) {
        this.timpDeCortaImpuesto = timpDeCortaImpuesto;
    }

    public String getTimpDeImpuesto() {
        return timpDeImpuesto;
    }

    public void setTimpDeImpuesto(String timpDeImpuesto) {
        this.timpDeImpuesto = timpDeImpuesto;
    }

    public Double getTimpPcImpuesto() {
        return timpPcImpuesto;
    }

    public void setTimpPcImpuesto(Double timpPcImpuesto) {
        this.timpPcImpuesto = timpPcImpuesto;
    }

    public Double getCalculoPrecioPublico() {
        double CPP= tplVaVenta - (tplVaVenta * (1-(1-(tplPcDescuento1/100))));
        BigDecimal bd = new BigDecimal(CPP);
        BigDecimal CPPD = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
        CalculoPrecioPublico = CPPD.doubleValue();

        return CalculoPrecioPublico;
    }

    public ProductoFull() {
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
        final ProductoFull other = (ProductoFull) obj;
        if (this.getPrimaryKey() != other.getPrimaryKey() && (this.getPrimaryKey() == null || !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        System.out.println("Pks de Producto = "+this.getPrimaryKey() +" = "+other.getPrimaryKey());
        return true;
    }  
    
    
}
