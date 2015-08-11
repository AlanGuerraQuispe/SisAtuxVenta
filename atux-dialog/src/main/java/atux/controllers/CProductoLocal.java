package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.managerbd.BaseConexion;
import atux.modelbd.Laboratorio;
import atux.modelbd.PrincipioActivoProducto;
import atux.modelbd.Producto;
import atux.modelbd.ProductoLocal;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import com.atux.bean.promocion.PromocionDetalle;
import com.atux.comun.context.AppCtx;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;


public class CProductoLocal extends JAbstractController implements Serializable{

    private static ArrayList parametros = new ArrayList();
    private ProductoLocal prdLc;

    @Override
    public ArrayList getRegistros() {
        return this.getRegistros(new String[]{},null, null);
    }

    @Override
    public ArrayList getRegistros(Object[] cvl) {
        return this.getRegistros(new String[]{},new String[]{ProductoLocal.COLUMNA_ACTIVO}, cvl);
    }

    @Override
    public JAbstractModelBD getRegistro() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JAbstractModelBD getRegistro(JAbstractModelBD mdl, boolean opcion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JAbstractModelBD buscarRegistro(Object cvl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ProductoLocal getProductoLocal() {
        if(prdLc == null)
        {
            prdLc = new ProductoLocal();
        }
        return prdLc;
    }


    public ProductoLocal getRegistro(String[] idalm,String[] idprd)
    {
        ArrayList<ProductoLocal> registros = this.getRegistros(new String[]{"idalmacen","idproducto"},new Object[]{idalm,idprd});
        return registros.size()>0?registros.get(0):null;
    }

    public ProductoLocal getRegistroPorPk(Object[] id){
        ProductoLocal prodLocal = null;
        try {
            rs = selectPorPk(ProductoLocal.nt, ProductoLocal.FULL_NOM_CAMPOS, ProductoLocal.COLUMNA_PK , id);
            while(rs.next())            {
                prodLocal = new ProductoLocal();
                prodLocal.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("co_local"),rs.getString("co_producto"),rs.getString("NU_REVISION_PRODUCTO")});
                prodLocal.setCoCompania(rs.getString("CO_COMPANIA"));
                prodLocal.setCoLocal(rs.getString("CO_LOCAL"));
                prodLocal.setCoProducto(rs.getString("CO_PRODUCTO"));
                prodLocal.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
                prodLocal.setVaVenta(rs.getDouble("VA_VENTA"));
                prodLocal.setCoUnidadVentaFraccion(rs.getString("CO_UNIDAD_VENTA_FRACCION"));
                prodLocal.setCoMoneda(rs.getString("CO_MONEDA"));
                prodLocal.setVaFraccion(rs.getInt("VA_FRACCION"));
                prodLocal.setInProdFraccionado(rs.getString("IN_PROD_FRACCIONADO"));
                prodLocal.setCoUnidadContenido(rs.getString("CO_UNIDAD_CONTENIDO"));
                prodLocal.setVaContenidoFraccion(rs.getInt("VA_CONTENIDO_FRACCION"));
                prodLocal.setCaStockDisponible(rs.getInt("CA_STOCK_DISPONIBLE"));
                prodLocal.setCaStockTransito(rs.getInt("CA_STOCK_TRANSITO"));
                prodLocal.setCaStockComprometido(rs.getInt("CA_STOCK_COMPROMETIDO"));
                prodLocal.setCaStockMinimo(rs.getInt("CA_STOCK_MINIMO"));
                prodLocal.setCaStockMaximo(rs.getInt("CA_STOCK_MAXIMO"));
                prodLocal.setCoDescuento1(rs.getString("CO_DESCUENTO_1"));
                prodLocal.setPcDescuento1(rs.getDouble("PC_DESCUENTO_1"));
                prodLocal.setCoDescuento2(rs.getString("CO_DESCUENTO_2"));
                prodLocal.setPcDescuento2(rs.getDouble("PC_DESCUENTO_2"));
                prodLocal.setCoDescuento3(rs.getString("CO_DESCUENTO_3"));
                prodLocal.setPcDescuento3(rs.getDouble("PC_DESCUENTO_3"));
                prodLocal.setPcDctoVentaEspecial(rs.getDouble("PC_DCTO_VENTA_ESPECIAL"));
                prodLocal.setInProdInventario(rs.getString("IN_PROD_INVENTARIO"));
                prodLocal.setInProductoReponer(rs.getString("IN_PRODUCTO_REPONER"));
                prodLocal.setCaStockReponer(rs.getInt("CA_STOCK_REPONER"));
                prodLocal.setCaStockReponerCalcula(rs.getInt("CA_STOCK_REPONER_CALCULA"));
                prodLocal.setDeUnidadFraccion(rs.getString("DE_UNIDAD_FRACCION"));
                prodLocal.setNuDiasRotacionPromedio(rs.getInt("NU_DIAS_ROTACION_PROMEDIO"));
                prodLocal.setNuMinDiasReposicion(rs.getInt("NU_MIN_DIAS_REPOSICION"));
                prodLocal.setNuMaxDiasReposicion(rs.getInt("NU_MAX_DIAS_REPOSICION"));
                prodLocal.setCaMinProdExhibicion(rs.getInt("CA_MIN_PROD_EXHIBICION"));
                prodLocal.setCaProdNoAtendido(rs.getInt("CA_PROD_NO_ATENDIDO"));
                prodLocal.setEsProdLocal(rs.getString("ES_PROD_LOCAL"));
                prodLocal.setIdCreaProdLocal(rs.getString("ID_CREA_PROD_LOCAL"));
                prodLocal.setFeCreaProdLocal(rs.getDate("FE_CREA_PROD_LOCAL"));
                prodLocal.setIdModProdLocal(rs.getString("ID_MOD_PROD_LOCAL"));
                prodLocal.setFeModProdLocal(rs.getDate("FE_MOD_PROD_LOCAL"));
                prodLocal.setVaRotacion(rs.getDouble("VA_ROTACION"));
                prodLocal.setCaUltimoPedidoRep(rs.getInt("CA_ULTIMO_PEDIDO_REP"));
                prodLocal.setInReplica(rs.getString("IN_REPLICA"));
                prodLocal.setFeReplica(rs.getDate("FE_REPLICA"));
                prodLocal.setNuSecReplica(rs.getInt("NU_SEC_REPLICA"));
                prodLocal.setCaStkDisponiblePedRep(rs.getInt("CA_STK_DISPONIBLE_PED_REP"));
                prodLocal.setCaStockTransitoPedRep(rs.getInt("CA_STOCK_TRANSITO_PED_REP"));
                prodLocal.setCoCentroBeneficioSap(rs.getString("CO_CENTRO_BENEFICIO_SAP"));
                prodLocal.setVaTotalInventario(rs.getDouble("VA_TOTAL_INVENTARIO"));
                prodLocal.setVaCostoProducto(rs.getDouble("VA_COSTO_PRODUCTO"));
                prodLocal.setVaPrecioPublico(rs.getDouble("VA_PRECIO_PUBLICO"));
                prodLocal.setCoUnidadLocalSap(rs.getString("CO_UNIDAD_LOCAL_SAP"));
                prodLocal.setInModCambio(rs.getString("IN_MOD_CAMBIO"));
                prodLocal.setFeUltModPrecio(rs.getDate("FE_ULT_MOD_PRECIO"));
                prodLocal.setTsDias(rs.getDouble("TS_DIAS"));
                prodLocal.setTrDias(rs.getDouble("TR_DIAS"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return prodLocal;
    }


    public ProductoLocal getRegistro(Object[] pk)
    {
        ArrayList<ProductoLocal> registros = this.getRegistros(ProductoLocal.COLUMNA_PK,pk);
        return registros.size()>0?registros.get(0):null;
    }

    public ArrayList<ProductoLocal> getRegistros(String[] columnas,Object[] cvl) {
        return this.getRegistros(new String[]{}, columnas,cvl);
    }

    public int getCantidadProducto(String[] idp)
    {
        Object suma =this.getSuma(ProductoLocal.nt, "cantidad_actual", "idproducto", idp);
        int cnt = Integer.parseInt(suma.toString());
        return cnt;
    }

    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException{
        ProductoLocal ap = (ProductoLocal)mdl;
        int gravado = 0;
        String campos = ProductoLocal.NO_FULL_CAMPOS.toString();

        Object[] valores = {
            ap.getProducto().getPrimaryKey(),
            ap.getCaStockDisponible(),
            ap.getVaVenta(),
            ap.getPcDescuento_1(),
            ap.getVaFraccion()
            //Helper.getFechaFormateada(ap.getFecha(), Helper.ANIO_MES_DIA),
        };

         gravado = this.agregarRegistroPs(ProductoLocal.nt, this.stringToArray(campos, ","), valores);

        if(gravado==1)
            return true;

        return false;
    }

    public boolean guardarRegistroNuevo(JAbstractModelBD mdl) throws SQLException{
        ProductoLocal ap = (ProductoLocal)mdl;
        int gravado = 0;
        String campos = ProductoLocal.FULL_MAESTROS.toString();

        Object[] valores = {ap.getCoCompania(),
                ap.getCoLocal(),
                ap.getCoProducto(),
                ap.getNuRevisionProducto(),
                ap.getVaVenta(),
                ap.getCoMoneda(),
                ap.getVaFraccion(),
                ap.getInProdFraccionado(),
                ap.getCaStockDisponible(),
                ap.getCaStockTransito(),
                ap.getCaStockComprometido(),
                ap.getCaStockMinimo(),
                ap.getCaStockMaximo(),
                ap.getPcDescuento1(),
                ap.getDeUnidadFraccion(),
                ap.getEsProdLocal(),
                ap.getIdCreaProdLocal(),
                ap.getFeCreaProdLocal()
        };

        gravado = this.agregarRegistroPs(ProductoLocal.nt, ProductoLocal.FULL_MAESTROS, valores);

        if(gravado==1)
            return true;

        return false;
    }

    public int actualizarProductosLocal(JAbstractModelBD mdl) {
        ProductoLocal ap = (ProductoLocal)mdl;
        int gravado = 0;
//        String campos = ProductoLocal.CAMPOS_MAESTROS_SINPK;

        Object[] valores = {ap.getVaVenta(),
                ap.getCoMoneda(),
                ap.getVaFraccion(),
                ap.getInProdFraccionado(),
                ap.getCaStockDisponible(),
                ap.getCaStockTransito(),
                ap.getCaStockComprometido(),
                ap.getCaStockMinimo(),
                ap.getCaStockMaximo(),
                ap.getPcDescuento1(),
                ap.getDeUnidadFraccion(),
                ap.getEsProdLocal(),
                ap.getIdModProdLocal(),
                ap.getFeModProdLocal()
        };

        gravado = this.actualizarRegistroPs(ProductoLocal.nt, this.adjuntarSimbolo(generarArrayAString(ProductoLocal.CAMPOS_MAESTROS_SINPK), ",", "?")+Ex.WHERE+ unirColumnasValores(ProductoLocal.COLUMNA_PK, ap.getPrimaryKey()) , valores);

        return gravado;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        ProductoLocal ap = (ProductoLocal)mdl;
        int gravado = 0;
        String campos = ProductoLocal.NO_FULL_CAMPOS.toString();

        Object[] valores = {ap.getProducto().getPrimaryKey(),
            ap.getVaVenta(),
            ap.getVaFraccion(),
            //Helper.getFechaFormateada(ap.getFecha(), Helper.ANIO_MES_DIA),
            ap.getCaStockDisponible(),
            ap.getPrimaryKey()
        };
        gravado = this.actualizarRegistroPs(ProductoLocal.nt, this.adjuntarSimbolo(campos, ",", "?")+Ex.WHERE+ProductoLocal.COLUMNA_PK+" = ? ", valores);


        return gravado;
    }

    public int actualizarRegistroNuevo(JAbstractModelBD mdl) {
        ProductoLocal ap = (ProductoLocal)mdl;
        int gravado = 0;

        Object[] valores = {ap.getProducto().getPrimaryKey(),
            ap.getVaVenta(),
            ap.getVaFraccion(),
            //Helper.getFechaFormateada(ap.getFecha(), Helper.ANIO_MES_DIA),
            ap.getCaStockDisponible(),
            ap.getPrimaryKey()
        };
        gravado = this.actualizarRegistroPs(ProductoLocal.nt, this.adjuntarSimbolo(generarArrayAString(ProductoLocal.FULL_NOM_CAMPOS_Update), ",", "?")+Ex.WHERE+ unirColumnasValores(ProductoLocal.COLUMNA_PK,  ap.getPrimaryKey()) , valores);
        return gravado;
    }

    @Override
    public ArrayList getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<ProductoLocal> rgs = new ArrayList();
        try {

            rs = this.getRegistros(ProductoLocal.nt,campos,columnaId,id,null);

            ProductoLocal sm = null;
            CProducto cProducto = new CProducto();

            while(rs.next())
            {
                 sm = new ProductoLocal();
                 sm.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
                 sm.setProducto(cProducto.getRegistroPorPk(new Object[]{rs.getString(1),rs.getString(3),rs.getString(4)}));
                 sm.setCoCompania(rs.getString(4));
                 sm.setCoProducto(rs.getString(5));
                 sm.setNuRevisionProducto(rs.getString(6));
                 sm.setCaStockDisponible(rs.getInt(7));
                 rgs.add(sm);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public ArrayList getProductosPedidoVenta(){
        return getProductosPedidoVenta(null);
    }

    public ArrayList getProductosPedidoVenta(String coProducto){
        ArrayList<ProductoLocal> rgs = new ArrayList<ProductoLocal>();
        ProductoLocal prodLocal = null;
        Producto      prod      = null;
        Laboratorio   lab       = null;
        StringBuffer  query;
        try {
            query = new StringBuffer();
            query.append("SELECT PRDLOCAL.CO_COMPANIA, ");
            query.append("       PRDLOCAL.CO_LOCAL, ");
            query.append("       PRDLOCAL.CO_PRODUCTO, ");
            query.append("       PRDLOCAL.NU_REVISION_PRODUCTO, ");
            query.append("       TRIM(T1.DE_PRODUCTO) DE_PRODUCTO, ");
            query.append("       TRIM(T1.DE_UNIDAD_PRODUCTO) DE_UNIDAD, ");
            query.append("       TRIM(DECODE(PRDLOCAL.IN_PROD_FRACCIONADO,'S',NVL(PRDLOCAL.DE_UNIDAD_FRACCION,' '),NVL(T1.DE_UNIDAD_PRODUCTO,' '))) DE_UNIDAD_VENTA, ");
            query.append("       T1.CO_GRUPO_EXT  GRUPO_EXT, ");
            query.append("       DECODE(T1.VA_BONO,NULL,0,T1.VA_BONO / DECODE(PRDLOCAL.VA_FRACCION,NULL, 1, 0, 1, PRDLOCAL.VA_FRACCION)) VA_BONO, ");
            query.append("       DECODE(PRDLOCAL.VA_VENTA,NULL,0, PRDLOCAL.VA_VENTA)  VA_VENTA, ");
            query.append("       ((1-(1-(DECODE(PRDLOCAL.PC_DESCUENTO_1,NULL,0,PRDLOCAL.PC_DESCUENTO_1)/100))) * 100)  DESCUENTO, ");
            query.append("       ROUND(DECODE(PRDLOCAL.VA_VENTA,NULL,0,PRDLOCAL.VA_VENTA) - (DECODE(PRDLOCAL.VA_VENTA,NULL,0,PRDLOCAL.VA_VENTA) * (1-(1-(DECODE(PRDLOCAL.PC_DESCUENTO_1,NULL,0,PRDLOCAL.PC_DESCUENTO_1)/100)))),2) PRECIO_PUB, ");
            query.append("       NVL(PRDLOCAL.CA_STOCK_DISPONIBLE,0)  STOCK, ");
            query.append("       T1.CO_LABORATORIO AS CO_LAB, ");
            query.append("       TRIM(T2.DE_LABORATORIO) AS DE_LABORATORIO, ");
            query.append("       T3.CO_NIVEL_05 AS CO_J5, T3.DE_ACCION_TERAPEUTICA AS DE_J5, ");
            query.append("       T4.CO_NIVEL_04 AS CO_J4, T4.DE_GRUPO_TERAPEUTICO  AS DE_J4, ");
            query.append("       T5.CO_NIVEL_03 AS CO_J3, T5.DE_GRUPO_ANATOMICO    AS DE_J3, ");
            query.append("       T6.CO_NIVEL_02 AS CO_J2, T6.DE_GRUPO_PROD_ERP     AS DE_J2, ");
            query.append("       T7.CO_NIVEL_01 AS CO_J1, T7.DE_LINEA_PROD_ERP     AS DE_J1, ");
            query.append("       PRDLOCAL.IN_PROD_FRACCIONADO INFRACCION, ");
            query.append("       PRDLOCAL.VA_FRACCION         VA_FRACCION, ");
            query.append("       T1.CO_IMPUESTO_1             CO_IMPUESTO_1, ");
            query.append("       PRDLOCAL.VA_COSTO_PRODUCTO   VA_COSTO, ");
            query.append("       T8.CO_PRINCIPIO_ACTIVO, ");
            query.append("       T9.DE_PRINCIPIO_ACTIVO, ");
            query.append("       T8.IN_PRINCIPIO_ACTIVO_PRINCIPAL ");
            query.append("  FROM LGTR_PRODUCTO_LOCAL          PRDLOCAL ");
            query.append("       LEFT JOIN   LGTM_PRODUCTO         T1 ");
            query.append("        ON  PRDLOCAL.CO_COMPANIA  = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("        AND PRDLOCAL.CO_LOCAL     = '").append(AtuxVariables.vCodigoLocal).append("'\n");
            query.append("        AND T1.ES_PRODUCTO        = 'A' ");
            query.append("        AND T1.CO_LABORATORIO     IN('0014','0100') ");
            query.append("        AND PRDLOCAL.CO_COMPANIA  = T1.CO_COMPANIA ");
            query.append("        AND  PRDLOCAL.CO_PRODUCTO  = T1.CO_PRODUCTO ");
            query.append("        AND  PRDLOCAL.NU_REVISION_PRODUCTO = T1.NU_REVISION_PRODUCTO ");
            query.append("       LEFT JOIN  CMTR_LABORATORIO    T2 ");
            query.append("        ON T1.CO_COMPANIA      = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("        AND T2.CO_COMPANIA     = T1.CO_COMPANIA ");
            query.append("        AND T2.CO_LABORATORIO=T1.CO_LABORATORIO ");
            query.append("       LEFT JOIN  LGTR_ACCION_TERAPEUTICA  T3 ");
            query.append("        ON T1.CO_COMPANIA    = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("        AND T3.CO_ACCION_TERAPEUTICA=T1.CO_ACCION_TERAPEUTICA   "); // J5 jerarquia 5
            query.append("       LEFT JOIN  LGTR_GRUPO_TERAPEUTICO T4 ");
            query.append("        ON T1.CO_COMPANIA    = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("        AND T4.CO_GRUPO_TERAPEUTICO=T1.CO_GRUPO_TERAPEUTICO      ");// J4 jerarquia 4
            query.append("       LEFT JOIN  LGTR_GRUPO_ANATOMICO   T5 ");
            query.append("        ON T5.CO_GRUPO_ANATOMICO=T1.CO_GRUPO_ANATOMICO           ");// J3 jerarquia 3
            query.append("       LEFT JOIN  LGTR_GRUPO_PROD_ERP      T6 ");
            query.append("        ON T6.CO_GRUPO_PROD_ERP=T1.CO_GRUPO_PROD_ERP             ");// J2 jerarquia 2
            query.append("       LEFT JOIN  LGTR_LINEA_PROD_ERP        T7 ");
            query.append("        ON T7.CO_LINEA_PROD_ERP=T1.CO_LINEA_PROD_ERP             ");// J1 jerarquia 1
            query.append("       LEFT JOIN LGTR_PRODUCTO_PRINCIPIO T8 ");
            query.append("        ON T8.CO_COMPANIA = T1.CO_COMPANIA  ");
            query.append("        AND T8.CO_PRODUCTO = T1.CO_PRODUCTO ");
            query.append("        AND T8.ES_PRODUCTO_PRINCIPIO = 'A' ");
            query.append("        AND T8.IN_PRINCIPIO_ACTIVO_PRINCIPAL='S'  ");
            query.append("       LEFT JOIN LGTM_PRINCIPIO_ACTIVO T9 ");
            query.append("         ON T9.CO_PRINCIPIO_ACTIVO = T8.CO_PRINCIPIO_ACTIVO  ");// J1 jerarquia 1
            query.append("        AND T9.ES_PRINCIPIO_ACTIVO = 'A' ");// J1 jerarquia 1

            query.append("WHERE T1.CO_COMPANIA= '").append(AtuxVariables.vCodigoCompania).append("'\n");;
            if(StringUtils.isNotBlank(coProducto)){
                query.append("  AND T1.CO_PRODUCTO= '").append(coProducto).append("'\n");;

            }
            if(AppCtx.instance().isEnviromentTest()){
                query.append("  AND T1.CO_PRODUCTO in ( '902429','134207','112090') \n");;
            }

            query.append("  AND T1.ES_PRODUCTO = 'A' ");
            query.append("  AND T1.CO_PRODUCTO_SAP IS NOT NULL");

            if(AtuxVariables.vMuestraListaPrincipiosActivos){
                query.append("  ORDER BY T9.DE_PRINCIPIO_ACTIVO, T1.DE_PRODUCTO,PRDLOCAL.CA_STOCK_DISPONIBLE DESC");
            }
            else
                query.append("  ORDER BY T1.DE_PRODUCTO,PRDLOCAL.CA_STOCK_DISPONIBLE DESC");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                   prodLocal = new ProductoLocal();
                   prodLocal.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
                   prodLocal.setCoCompania(rs.getString(1));
                   prodLocal.setCoLocal(rs.getString(2));
                   prodLocal.setCoProducto(rs.getString(3));
                   prodLocal.setNuRevisionProducto(rs.getString(4));
                       prod = new Producto();
                       prod.setPrimaryKey(new String[]{rs.getString(1),rs.getString(3),rs.getString(4)});
                       prod.setCoCompania(rs.getString(1));
                       prod.setCoProducto(rs.getString(3));
                       prod.setNuRevisionProducto(rs.getString(4));
                       prod.setDeProducto(rs.getString(5));
                       prod.setDeUnidadProducto(rs.getString(6));
                       prod.setCoGrupoExt(rs.getString(8));
                       prod.setVaBono(rs.getDouble(9));
                       prod.setCoImpuesto_1(rs.getString(28));
                       prod.setCoPrincipioActivo(rs.getString(30));
                       prod.setDePrincipioActivo(rs.getString(31));
                       prod.setInPrincipioActivoPrincipal(rs.getString(32));
                   prodLocal.setProducto(prod);
                   prodLocal.setDeUnidadFraccion(rs.getString(7));
                   prodLocal.setVaVenta(rs.getDouble(10));
                   prodLocal.setPcDescuento_1(rs.getDouble(11));
                   prodLocal.setVaPrecioPublico(rs.getDouble(12));
                   prodLocal.setCaStockDisponible(rs.getInt(13));
                       lab = new Laboratorio();
                       lab.setPrimaryKey(new String[]{rs.getString(1),rs.getString(14)});
                       lab.setCoCompania(rs.getString(1));
                       lab.setCoLaboratorio(rs.getString(14));
                       lab.setDeLaboratorio(rs.getString(15));
                   prodLocal.getProducto().setLaboratorio(lab);
                   prodLocal.setCoJ5(rs.getString(16));
                   prodLocal.setDeJ5(rs.getString(17));
                   prodLocal.setCoJ4(rs.getString(18));
                   prodLocal.setDeJ4(rs.getString(19));
                   prodLocal.setCoJ3(rs.getString(20));
                   prodLocal.setDeJ3(rs.getString(21));
                   prodLocal.setCoJ2(rs.getString(22));
                   prodLocal.setDeJ2(rs.getString(23));
                   prodLocal.setCoJ1(rs.getString(24));
                   prodLocal.setDeJ1(rs.getString(25));
                   prodLocal.setInProdFraccionado(rs.getString(26));
                   prodLocal.setVaFraccion(rs.getInt(27));
                   prodLocal.setVaCostoProducto(rs.getDouble(29));
                   prodLocal.setInInsumos(false);
                 rgs.add(prodLocal);
            }
            rs.close();
            BaseConexion.closeConnection();
            BaseConexion.setConnectionNull();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
        // Se cierran los recursos de base de datos.
        try {
        if (rs != null) {
            rs.close();
            }
        } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
        }
        return rgs;
    }

    public ArrayList getProductosConMismoPrincipioActivo(String coProducto){
        ArrayList<ProductoLocal> rgs = new ArrayList<ProductoLocal>();
        ProductoLocal prodLocal = null;
        Producto      prod      = null;
        Laboratorio   lab       = null;
        StringBuffer  query;
        try {
            query = new StringBuffer();
            query.append("SELECT Z.CO_COMPANIA , ");
            query.append("       Z.CO_LOCAL    , ");
            query.append("       Z.CO_PRODUCTO , ");
            query.append("       Z.NU_REVISION_PRODUCTO , ");
            query.append("       Z.DE_PRODUCTO , ");
            query.append("       Z.DE_UNIDAD   , ");
            query.append("       Z.DE_UNIDAD_VENTA , ");
            query.append("       Z.VA_VENTA , ");
            query.append("       Z.VA_PRECIO_PUBLICO , ");
            query.append("       Z.CA_STOCK , ");
            query.append("       Z.VA_BONO , ");        //Bono calculado a la unidad de venta
            query.append("       Z.CO_LABORATORIO, ");
            query.append("       Z.DE_LABORATORIO, ");
            query.append("       Z.DESCUENTO, ");
            query.append("       Z.ORDEN , ");
            query.append("       Z.CLASE , ");
            query.append("       Z.IN_PROD_FRACCIONADO, ");
            query.append("       Z.CO_IMPUESTO_1, ");
            query.append("       Z.VA_FRACCION, ");
            query.append("       Z.VA_COSTO, ");
            query.append("       Z.DE_J5, ");
            query.append("       Z.DE_J4, ");
            query.append("       Z.DE_J3, ");
            query.append("       Z.DE_J2, ");
            query.append("       Z.DE_J1 ");
            query.append(" FROM (SELECT A.CO_COMPANIA,B.CO_LOCAL,A.CO_PRODUCTO CO_PRODUCTO,B.NU_REVISION_PRODUCTO, A.DE_PRODUCTO DE_PRODUCTO,A.DE_UNIDAD_ENTERO DE_UNIDAD, ");
            query.append("              DECODE(B.IN_PROD_FRACCIONADO,'S',NVL(B.DE_UNIDAD_FRACCION,' '), NVL(A.DE_UNIDAD_ENTERO,' ')) DE_UNIDAD_VENTA, ");
            query.append("              DECODE(B.VA_VENTA,NULL,0,B.VA_VENTA) VA_VENTA, ");
            query.append("              NVL(B.VA_VENTA,0) - NVL(B.VA_VENTA,0) * (1-(1-(NVL(B.PC_DESCUENTO_1,0)/100))) VA_PRECIO_PUBLICO, ");
            query.append("              NVL(B.CA_STOCK_DISPONIBLE,0) CA_STOCK, ");
            query.append("              DECODE(A.VA_BONO_ENTERO,NULL,0,A.VA_BONO_ENTERO / DECODE(B.VA_FRACCION,NULL, 1, 0, 1, B.VA_FRACCION)) VA_BONO, ");
            query.append("              PRO.CO_LABORATORIO CO_LABORATORIO, ");
            query.append("              TRIM(A.DE_LABORATORIO) DE_LABORATORIO, ");
            query.append("              B.VA_FRACCION  VA_FRACCION, ");
            query.append("              ((1-(1-(DECODE(B.PC_DESCUENTO_1,NULL,0,B.PC_DESCUENTO_1)/100)))* 100) DESCUENTO, ");
            query.append("              A.ORDEN ORDEN, ");
            query.append("              A.CLASE CLASE, ");
            query.append("              B.IN_PROD_FRACCIONADO IN_PROD_FRACCIONADO, ");
            query.append("              A.IN_LAM IN_LAM, ");
            query.append("              CASE ");
            query.append("                  WHEN NVL(B.CA_STOCK_DISPONIBLE,0) > 0 THEN 1 ");
            query.append("              ELSE 0 ");
            query.append("              END AS TIENE_STOCK, ");
            query.append("              PRO.CO_IMPUESTO_1     CO_IMPUESTO_1, ");
            query.append("              B.VA_COSTO_PRODUCTO   VA_COSTO, ");
            query.append("              T3.CO_NIVEL_05 AS CO_J5, T3.DE_ACCION_TERAPEUTICA AS DE_J5, ");
            query.append("              T4.CO_NIVEL_04 AS CO_J4, T4.DE_GRUPO_TERAPEUTICO  AS DE_J4, ");
            query.append("              T5.CO_NIVEL_03 AS CO_J3, T5.DE_GRUPO_ANATOMICO    AS DE_J3, ");
            query.append("              T6.CO_NIVEL_02 AS CO_J2, T6.DE_GRUPO_PROD_ERP     AS DE_J2, ");
            query.append("              T7.CO_NIVEL_01 AS CO_J1, T7.DE_LINEA_PROD_ERP     AS DE_J1  ");
            query.append("                  FROM LGTX_PRODUCTO_PRINCIPIOS A, ");
            query.append("                       LGTR_PRODUCTO_LOCAL      B, ");
            query.append("                       LGTM_PRODUCTO            PRO ");
            query.append("            LEFT JOIN  LGTR_ACCION_TERAPEUTICA  T3 ");
            query.append("              ON PRO.CO_COMPANIA    = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("              AND T3.CO_ACCION_TERAPEUTICA=PRO.CO_ACCION_TERAPEUTICA   "); // J5 jerarquia 5
            query.append("            LEFT JOIN  LGTR_GRUPO_TERAPEUTICO T4 ");
            query.append("              ON  PRO.CO_COMPANIA    = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("              AND T4.CO_GRUPO_TERAPEUTICO=PRO.CO_GRUPO_TERAPEUTICO      ");// J4 jerarquia 4
            query.append("            LEFT JOIN  LGTR_GRUPO_ANATOMICO   T5 ");
            query.append("              ON T5.CO_GRUPO_ANATOMICO=PRO.CO_GRUPO_ANATOMICO           ");// J3 jerarquia 3
            query.append("            LEFT JOIN  LGTR_GRUPO_PROD_ERP      T6 ");
            query.append("              ON T6.CO_GRUPO_PROD_ERP=PRO.CO_GRUPO_PROD_ERP             ");// J2 jerarquia 2
            query.append("            LEFT JOIN  LGTR_LINEA_PROD_ERP        T7 ");
            query.append("              ON T7.CO_LINEA_PROD_ERP=PRO.CO_LINEA_PROD_ERP             ");// J1 jerarquia 1
            query.append("                     WHERE A.CO_COMPANIA          = '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                       AND A.CO_PRODUCTO_PADRE    = '").append(coProducto).append("'");
            query.append("                       AND B.CO_COMPANIA  = '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                       AND B.CO_LOCAL     = '").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                       AND B.ES_PROD_LOCAL = 'A' ");
            query.append("                       AND A.CO_COMPANIA = B.CO_COMPANIA ");
            query.append("                       AND A.CO_PRODUCTO = B.CO_PRODUCTO ");
            query.append("                       AND B.CO_COMPANIA  = PRO.CO_COMPANIA ");
            query.append("                       AND B.CO_PRODUCTO  = PRO.CO_PRODUCTO ");
            query.append("                       AND B.NU_REVISION_PRODUCTO = PRO.NU_REVISION_PRODUCTO ");
            query.append("                       ) Z ");
            query.append("             ORDER BY Z.TIENE_STOCK DESC, Z.IN_LAM ASC, Z.VA_BONO DESC");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                   prodLocal = new ProductoLocal();
                   prodLocal.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
                   prodLocal.setCoCompania(rs.getString(1));
                   prodLocal.setCoLocal(rs.getString(2));
                   prodLocal.setCoProducto(rs.getString(3));
                   prodLocal.setNuRevisionProducto(rs.getString(4));
                       prod = new Producto();
                       prod.setPrimaryKey(new String[]{rs.getString(1),rs.getString(3),rs.getString(4)});
                       prod.setCoCompania(rs.getString(1));
                       prod.setCoProducto(rs.getString(3));
                       prod.setNuRevisionProducto(rs.getString(4));
                       prod.setDeProducto(rs.getString(5));
                       prod.setDeUnidadProducto(rs.getString(6));
                       prod.setVaBono(rs.getDouble(11));
                       prod.setCoImpuesto_1(rs.getString(18));
                   prodLocal.setProducto(prod);
                   prodLocal.setDeUnidadFraccion(rs.getString(7));
                   prodLocal.setVaVenta(rs.getDouble(8));
                   prodLocal.setPcDescuento_1(rs.getDouble(14));
                   prodLocal.setVaPrecioPublico(rs.getDouble(9));
                   prodLocal.setCaStockDisponible(rs.getInt(10));
                       lab = new Laboratorio();
                       lab.setPrimaryKey(new String[]{rs.getString(1),rs.getString(12)});
                       lab.setCoCompania(rs.getString(1));
                       lab.setCoLaboratorio(rs.getString(12));
                       lab.setDeLaboratorio(rs.getString(13));
                   prodLocal.getProducto().setLaboratorio(lab);
                   prodLocal.setInProdFraccionado(rs.getString(17));
                   prodLocal.setVaFraccion(rs.getInt(19));
                   prodLocal.setVaCostoProducto(rs.getDouble(20));
                   prodLocal.setDeJ5(rs.getString(21));
                   prodLocal.setDeJ4(rs.getString(22));
                   prodLocal.setDeJ3(rs.getString(23));
                   prodLocal.setDeJ2(rs.getString(24));
                   prodLocal.setDeJ1(rs.getString(25));

                 rgs.add(prodLocal);
            }
            rs.close();
            BaseConexion.closeConnection();
            BaseConexion.setConnectionNull();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
        // Se cierran los recursos de base de datos.
        try {
        if (rs != null) {
            rs.close();
            }
        } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
        }
        return rgs;
    }

    public ArrayList getProductosComplementarios(String coNivel05){
        ArrayList<ProductoLocal> rgs = new ArrayList<ProductoLocal>();
        ProductoLocal prodLocal = null;
        Producto      prod      = null;
        Laboratorio   lab       = null;
        StringBuffer  query;
        try {
            query = new StringBuffer();
            query.append("SELECT Z.CO_COMPANIA , ");
            query.append("       Z.CO_LOCAL    , ");
            query.append("       Z.CO_PRODUCTO , ");
            query.append("       Z.NU_REVISION_PRODUCTO , ");
            query.append("       Z.DE_PRODUCTO , ");
            query.append("       Z.DE_UNIDAD   , ");
            query.append("       Z.DE_UNIDAD_VENTA , ");
            query.append("       Z.VA_VENTA , ");
            query.append("       Z.VA_PRECIO_PUBLICO , ");
            query.append("       Z.CA_STOCK , ");
            query.append("       Z.VA_BONO , ");        //Bono calculado a la unidad de venta
            query.append("       Z.CO_LABORATORIO, ");
            query.append("       Z.DE_LABORATORIO, ");
            query.append("       Z.DESCUENTO, ");
            query.append("       Z.ORDEN , ");
            query.append("       Z.CLASE , ");
            query.append("       Z.IN_PROD_FRACCIONADO, ");
            query.append("       Z.CO_IMPUESTO_1, ");
            query.append("       Z.VA_FRACCION, ");
            query.append("       Z.VA_COSTO, ");
            query.append("       Z.DE_J5, ");
            query.append("       Z.DE_J4, ");
            query.append("       Z.DE_J3, ");
            query.append("       Z.DE_J2, ");
            query.append("       Z.DE_J1 ");
            query.append(" FROM (SELECT PRODUCTO_LOCAL.CO_COMPANIA,PRODUCTO_LOCAL.CO_LOCAL,PRODUCTO_LOCAL.CO_PRODUCTO CO_PRODUCTO,PRODUCTO_LOCAL.NU_REVISION_PRODUCTO, PRODUCTO.DE_PRODUCTO DE_PRODUCTO,PRODUCTO.DE_UNIDAD_PRODUCTO DE_UNIDAD, ");
            query.append("              DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,'S',NVL(PRODUCTO_LOCAL.DE_UNIDAD_FRACCION,' '), NVL(PRODUCTO.DE_UNIDAD_PRODUCTO,' ')) DE_UNIDAD_VENTA, ");
            query.append("              DECODE(PRODUCTO_LOCAL.VA_VENTA,NULL,0,PRODUCTO_LOCAL.VA_VENTA) VA_VENTA, ");
            query.append("              NVL(PRODUCTO_LOCAL.VA_VENTA,0) - NVL(PRODUCTO_LOCAL.VA_VENTA,0) * (1-(1-(NVL(PRODUCTO_LOCAL.PC_DESCUENTO_1,0)/100))) VA_PRECIO_PUBLICO, ");
            query.append("              NVL(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,0) CA_STOCK, ");
            query.append("              DECODE(PRODUCTO.VA_BONO,NULL,0,PRODUCTO.VA_BONO / DECODE(PRODUCTO_LOCAL.VA_FRACCION,NULL, 1, 0, 1, PRODUCTO_LOCAL.VA_FRACCION)) VA_BONO, ");
            query.append("              PRODUCTO.CO_LABORATORIO    CO_LABORATORIO, ");
            query.append("              TRIM(LABORATORIO.DE_LABORATORIO) DE_LABORATORIO, ");
            query.append("              PRODUCTO_LOCAL.VA_FRACCION  VA_FRACCION, ");
            query.append("              ((1-(1-(DECODE(PRODUCTO_LOCAL.PC_DESCUENTO_1,NULL,0,PRODUCTO_LOCAL.PC_DESCUENTO_1)/100)))* 100) DESCUENTO, ");
            query.append("               NVL(LABORATORIO.IN_LAB_PROPIO,'N') || ");
            query.append("               DECODE(PRODUCTO.VA_BONO,NULL,'000.00',TO_CHAR(PRODUCTO.VA_BONO / DECODE(PRODUCTO_LOCAL.VA_FRACCION,NULL,1,0,1,PRODUCTO_LOCAL.VA_FRACCION),'000.00')) || ");
            query.append("               TO_CHAR(DECODE(PRODUCTO_LOCAL.VA_VENTA,NULL,0,PRODUCTO_LOCAL.VA_VENTA) - DECODE(PRODUCTO_LOCAL.VA_VENTA,NULL,0,PRODUCTO_LOCAL.VA_VENTA)*(1-(1-(DECODE(PRODUCTO_LOCAL.PC_DESCUENTO_1,NULL,0,PRODUCTO_LOCAL.PC_DESCUENTO_1)/100))*(1-(DECODE(PRODUCTO_LOCAL.PC_DESCUENTO_2,NULL,0,PRODUCTO_LOCAL.PC_DESCUENTO_2)/100))*(1-(DECODE(PRODUCTO_LOCAL.PC_DESCUENTO_3,NULL,0,PRODUCTO_LOCAL.PC_DESCUENTO_3)/100))),'000.00') ORDEN, ");
            query.append("               DECODE(PRODUCTO.VA_BONO,NULL,0,PRODUCTO.VA_BONO) CLASE, ");
            query.append("               PRODUCTO_LOCAL.IN_PROD_FRACCIONADO IN_PROD_FRACCIONADO, ");
            query.append("                 CASE ");
            query.append("                   WHEN PRODUCTO.CO_GRUPO_EXT = 'LAM-MA' THEN 1 ");
            query.append("                   WHEN PRODUCTO.CO_GRUPO_EXT = 'LAM-MP' THEN 1 ");
            query.append("                   WHEN PRODUCTO.CO_GRUPO_EXT = 'GRUPO A' THEN 2 ");
            query.append("                 ELSE 3 ");
            query.append("                 END AS LAM, ");
            query.append("                 CASE ");
            query.append("                    WHEN NVL(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,0) > 0 THEN 1 ");
            query.append("                 ELSE 0 ");
            query.append("                 END                   TIENE_STOCK, ");
            query.append("                PRODUCTO.CO_IMPUESTO_1 CO_IMPUESTO_1, ");
            query.append("                PRODUCTO_LOCAL.VA_COSTO_PRODUCTO    VA_COSTO, ");
            query.append("                T3.CO_NIVEL_05 AS CO_J5, T3.DE_ACCION_TERAPEUTICA AS DE_J5, ");
            query.append("                T4.CO_NIVEL_04 AS CO_J4, T4.DE_GRUPO_TERAPEUTICO  AS DE_J4, ");
            query.append("                T5.CO_NIVEL_03 AS CO_J3, T5.DE_GRUPO_ANATOMICO    AS DE_J3, ");
            query.append("                T6.CO_NIVEL_02 AS CO_J2, T6.DE_GRUPO_PROD_ERP     AS DE_J2, ");
            query.append("                T7.CO_NIVEL_01 AS CO_J1, T7.DE_LINEA_PROD_ERP     AS DE_J1  ");
            query.append("                FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL, ");
            query.append("                     CMTR_LABORATORIO    LABORATORIO, ");
            query.append("                     LGTM_PRODUCTO       PRODUCTO ");
            query.append("            LEFT JOIN LGTR_ACCION_TERAPEUTICA  T3 ");
            query.append("              ON PRODUCTO.CO_COMPANIA    = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("              AND T3.CO_ACCION_TERAPEUTICA=PRODUCTO.CO_ACCION_TERAPEUTICA   "); // J5 jerarquia 5
            query.append("            LEFT JOIN LGTR_GRUPO_TERAPEUTICO T4 ");
            query.append("              ON  PRODUCTO.CO_COMPANIA    = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("              AND T4.CO_GRUPO_TERAPEUTICO=PRODUCTO.CO_GRUPO_TERAPEUTICO      ");// J4 jerarquia 4
            query.append("            LEFT JOIN LGTR_GRUPO_ANATOMICO   T5 ");
            query.append("              ON T5.CO_GRUPO_ANATOMICO=PRODUCTO.CO_GRUPO_ANATOMICO           ");// J3 jerarquia 3
            query.append("            LEFT JOIN LGTR_GRUPO_PROD_ERP      T6 ");
            query.append("              ON T6.CO_GRUPO_PROD_ERP=PRODUCTO.CO_GRUPO_PROD_ERP             ");// J2 jerarquia 2
            query.append("            LEFT JOIN LGTR_LINEA_PROD_ERP        T7 ");
            query.append("              ON T7.CO_LINEA_PROD_ERP=PRODUCTO.CO_LINEA_PROD_ERP             ");// J1 jerarquia 1
            query.append("               WHERE PRODUCTO_LOCAL.CO_COMPANIA  = PRODUCTO.CO_COMPANIA ");
            query.append("                 AND PRODUCTO_LOCAL.CO_COMPANIA  ='").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                 AND PRODUCTO_LOCAL.CO_LOCAL     ='").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                 AND PRODUCTO_LOCAL.CO_PRODUCTO  = PRODUCTO.CO_PRODUCTO ");
            query.append("                 AND PRODUCTO.CO_COMPANIA        = LABORATORIO.CO_COMPANIA ");
            query.append("                 AND PRODUCTO.CO_NIVEL_05        = '").append(coNivel05).append("'");
            query.append("                 AND PRODUCTO.CO_LABORATORIO     = LABORATORIO.CO_LABORATORIO");
            query.append("                 AND PRODUCTO_LOCAL.ES_PROD_LOCAL= 'A' ");
            query.append("                 ) Z ");
            query.append("       ORDER BY Z.TIENE_STOCK DESC, Z.LAM ASC, Z.VA_BONO DESC");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
               prodLocal = new ProductoLocal();
                   prodLocal.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
                   prodLocal.setCoCompania(rs.getString(1));
                   prodLocal.setCoLocal(rs.getString(2));
                   prodLocal.setCoProducto(rs.getString(3));
                   prodLocal.setNuRevisionProducto(rs.getString(4));
                       prod = new Producto();
                       prod.setPrimaryKey(new String[]{rs.getString(1),rs.getString(3),rs.getString(4)});
                       prod.setCoCompania(rs.getString(1));
                       prod.setCoProducto(rs.getString(3));
                       prod.setNuRevisionProducto(rs.getString(4));
                       prod.setDeProducto(rs.getString(5));
                       prod.setDeUnidadProducto(rs.getString(6));
                       prod.setVaBono(rs.getDouble(11));
                       prod.setCoImpuesto_1(rs.getString(18));
                   prodLocal.setProducto(prod);
                   prodLocal.setDeUnidadFraccion(rs.getString(7));
                   prodLocal.setVaVenta(rs.getDouble(8));
                   prodLocal.setPcDescuento_1(rs.getDouble(14));
                   prodLocal.setVaPrecioPublico(rs.getDouble(9));
                   prodLocal.setCaStockDisponible(rs.getInt(10));
                       lab = new Laboratorio();
                       lab.setPrimaryKey(new String[]{rs.getString(1),rs.getString(12)});
                       lab.setCoCompania(rs.getString(1));
                       lab.setCoLaboratorio(rs.getString(12));
                       lab.setDeLaboratorio(rs.getString(13));
                   prodLocal.getProducto().setLaboratorio(lab);
                   prodLocal.setInProdFraccionado(rs.getString(17));
                   prodLocal.setVaFraccion(rs.getInt(19));
                   prodLocal.setVaCostoProducto(rs.getDouble(20));
                   prodLocal.setDeJ5(rs.getString(21));
                   prodLocal.setDeJ4(rs.getString(22));
                   prodLocal.setDeJ3(rs.getString(23));
                   prodLocal.setDeJ2(rs.getString(24));
                   prodLocal.setDeJ1(rs.getString(25));
                   rgs.add(prodLocal);
            }
            rs.close();
            BaseConexion.closeConnection();
            BaseConexion.setConnectionNull();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
        // Se cierran los recursos de base de datos.
        try {
        if (rs != null) {
            rs.close();
            }
        } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
        }
        return rgs;
    }

    public ArrayList getInsumosProductos(ProductoLocal prodLocal){
        ArrayList<ProductoLocal> rgs = (ArrayList<ProductoLocal>) prodLocal.getInsumosProducto();
        ProductoLocal prodLocalAux;
        Laboratorio   lab  = null;
        Producto      prod = null;
        StringBuffer  query;

        if (!prodLocal.getInInsumos()){
            try {
                query = new StringBuffer();
                query.append("SELECT INS.CO_COMPANIA,  ");
                query.append("       INS.CO_PRODUCTO,  ");
                query.append("       INS.NU_REVISION_PRODUCTO,  ");
                query.append("       INS.CO_PRODUCTO_INSUMO,  ");
                query.append("       INS.IN_PRODUCTO_INSUMO_PRINCIPAL,  ");
                query.append("       INS.ES_PRODUCTO_INSUMO,  ");
                query.append("       PRO.DE_PRODUCTO, ");
                query.append("       INS.DE_UNIDAD_INSUMO, ");
                query.append("       INS.CA_INSUMO, ");
                query.append("       INS.VA_COSTO, ");
                query.append("       INS.VA_PRECIO_PROMEDIO, ");
                query.append("       INS.IN_IMPRESION, ");
                query.append("       PRO.CO_LABORATORIO, ");
                query.append("       PRO.VA_BONO,");
                query.append("       PRO.CO_IMPUESTO_1, ");
                query.append("       LAB.DE_LABORATORIO ");
                query.append("  FROM CMTR_LABORATORIO LAB, ");
                query.append("       LGTM_PRODUCTO    PRO, ");
                query.append("       LGTR_PRODUCTO_INSUMO INS ");
                query.append(" WHERE LAB.CO_COMPANIA  = PRO.CO_COMPANIA ");
                query.append(" AND LAB.CO_LABORATORIO = PRO.CO_LABORATORIO ");
                query.append(" AND PRO.CO_COMPANIA    = INS.CO_COMPANIA ");
                query.append(" AND PRO.CO_PRODUCTO    = INS.CO_PRODUCTO_INSUMO ");
                query.append("   and INS.CO_COMPANIA = '").append(prodLocal.getCoCompania()).append("'");
                query.append("   and INS.CO_PRODUCTO = '").append(prodLocal.getCoProducto()).append("'");

                rs =  this.getRegistrosSinFiltro(query);

                while(rs.next()){
                    prodLocalAux= new ProductoLocal();
                    prodLocalAux.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),AtuxVariables.vCodigoLocal, rs.getString("NU_REVISION_PRODUCTO"),rs.getString("CO_PRODUCTO_INSUMO")});
                    prodLocalAux.setCoCompania(rs.getString("CO_COMPANIA"));
                    prodLocalAux.setCoLocal(AtuxVariables.vCodigoLocal);
                    prodLocalAux.setCoProducto(rs.getString("CO_PRODUCTO_INSUMO"));
                    prodLocalAux.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
                    prodLocalAux.setDeProducto(rs.getString("DE_PRODUCTO"));
                        prod = new Producto();
                        prod.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_PRODUCTO_INSUMO"),rs.getString("NU_REVISION_PRODUCTO")});
                        prod.setCoCompania(rs.getString("CO_COMPANIA"));
                        prod.setCoProducto(rs.getString("CO_PRODUCTO_INSUMO"));
                        prod.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
                        prod.setDeProducto(rs.getString("DE_PRODUCTO"));
                        prod.setVaBono(rs.getDouble("VA_BONO"));
                        prod.setCoImpuesto_1(rs.getString("CO_IMPUESTO_1"));
                    prodLocalAux.setProducto(prod);
                        lab = new Laboratorio();
                        lab.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LABORATORIO")});
                        lab.setCoCompania(rs.getString("CO_COMPANIA"));
                        lab.setCoLaboratorio(rs.getString("CO_LABORATORIO"));
                        lab.setDeLaboratorio(rs.getString("DE_LABORATORIO"));
                    prodLocalAux.getProducto().setLaboratorio(lab);
                    prodLocalAux.setDeUnidadFraccion(rs.getString("DE_UNIDAD_INSUMO"));
                    prodLocalAux.setCantidad(rs.getInt("CA_INSUMO"));
                    prodLocalAux.setVaCostoProducto(rs.getDouble("VA_COSTO"));
                    prodLocalAux.setVaPrecioPublico(rs.getDouble("VA_PRECIO_PROMEDIO"));
                    prodLocalAux.setVaVenta(rs.getDouble("VA_PRECIO_PROMEDIO"));
                    prodLocalAux.setPcDescuento_1(0d);
                    prodLocalAux.setCaStockDisponible(0);
                    prodLocal.setInProdFraccionado("N");
                    prodLocal.setVaFraccion(0);
                    prodLocal.setInInsumos(true);
                    rgs.add(prodLocalAux);
                }
                prodLocal.setInsumosProducto(rgs);
                rs.close();
                BaseConexion.closeConnection();
                BaseConexion.setConnectionNull();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {
                // Se cierran los recursos de base de datos.
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException e) {
                    System.out.println("No ha podido cerrar ResultSet.");
                }
             }
        }
        return (ArrayList<ProductoLocal>) prodLocal.getInsumosProducto();
    }

    public ArrayList getProductosPromocion(String coProducto){
        ArrayList<PromocionDetalle> rgs = new ArrayList<PromocionDetalle>();
        PromocionDetalle promocion = null;
        StringBuffer  query;
        try {
            query = new StringBuffer();
            query.append("SELECT 'X COMPRA DE ' \n");
            query.append("  || PPPP.CA_PRODUCTO \n");
            query.append("  || ' ' \n");
            query.append("  || TRIM(NVL(LPLO.DE_UNIDAD_FRACCION,LPO.DE_UNIDAD_PRODUCTO)) \n");
            query.append("  || '(S)' \n");
            query.append("  || DECODE(DECODE(PPPP.CA_PRODUCTO_P, 0, NULL, PPPP.CA_PRODUCTO_P), NULL, ' DAMOS ', ' REGALAMOS ') \n");
            query.append("  || PPPP.CA_PRODUCTO_P \n");
            query.append("  || ' ' \n");
            query.append("  || TRIM(NVL(LPLP.DE_UNIDAD_FRACCION,LPP.DE_UNIDAD_PRODUCTO)) \n");
            query.append("  || '(S)' \n");
            query.append("  || DECODE(PPPP.CO_PRODUCTO_P, PPPP.CO_PRODUCTO, ' MÁS DEL MISMO PRODUCTO', ' DE ' \n");
            query.append("  || LPO.DE_PRODUCTO) promo, \n");
            query.append("  CL.DE_LABORATORIO deLaboratorioP, \n");
            query.append("  PPPP.CO_COMPANIA coCompania, \n");
            query.append("  PPPP.CO_PROMOCION coPromocion, \n");
            query.append("  PPPP.CO_PRODUCTO coProducto, \n");
            query.append("  LPO.DE_PRODUCTO deProducto, \n");
            query.append("  PPPP.CA_PRODUCTO caProducto, \n");
            query.append("  PPPP.VA_FRACCION vaFraccion, \n");
            query.append("  PPPP.CO_PRODUCTO_P coProductoP, \n");
            query.append("  LPP.DE_PRODUCTO deProductoP, \n");
            query.append("  LPP.DE_UNIDAD_PRODUCTO unidadProductoP, \n");
            query.append("  LPO.DE_UNIDAD_PRODUCTO unidadProducto, \n");
            query.append("  PPPP.CA_PRODUCTO_P caProductoP, \n");
            query.append("  PPPP.VA_FRACCION_P vaFraccionP, \n");
            query.append("  PPPP.ES_PRODUCTO_PLAN esProductoPlan \n");

            query.append("FROM PRTM_PROMO PP \n");
            query.append("INNER JOIN PRTD_PRODUCTO_PLAN_PROMO PPPP \n");
            query.append("ON PPPP.CO_COMPANIA   = PP.CO_COMPANIA \n");
            query.append("AND PPPP.CO_PROMOCION = PP.CO_PROMOCION \n");
            query.append("INNER JOIN PRTR_LOCAL_PROMO PLP   ON PLP.CO_COMPANIA= PP.CO_COMPANIA        AND PLP.CO_PROMOCION= PP.CO_PROMOCION \n");
            query.append("INNER JOIN LGTM_PRODUCTO LPO \n");
            query.append("ON LPO.CO_COMPANIA = PPPP.CO_COMPANIA \n");
            query.append("AND LPO.CO_PRODUCTO=PPPP.CO_PRODUCTO \n");
            query.append("INNER JOIN LGTM_PRODUCTO LPP \n");
            query.append("ON LPP.CO_COMPANIA = PPPP.CO_COMPANIA \n");
            query.append("AND LPP.CO_PRODUCTO=PPPP.CO_PRODUCTO_P \n");
            query.append("INNER JOIN LGTR_PRODUCTO_LOCAL LPLO \n");
            query.append("ON LPLO.CO_COMPANIA = LPO.CO_COMPANIA \n");
            query.append("AND LPLO.CO_PRODUCTO= LPO.CO_PRODUCTO \n");
            query.append("INNER JOIN LGTR_PRODUCTO_LOCAL LPLP \n");
            query.append("ON LPLP.CO_COMPANIA = LPP.CO_COMPANIA \n");
            query.append("AND LPLP.CO_PRODUCTO= LPP.CO_PRODUCTO \n");
            query.append("INNER JOIN CMTR_LABORATORIO CL \n");
            query.append("ON CL.CO_COMPANIA    = LPP.CO_COMPANIA \n");
            query.append("AND CL.CO_LABORATORIO= LPP.CO_LABORATORIO \n");
            query.append("WHERE PP.CO_COMPANIA ='"+AtuxVariables.vCodigoCompania+"' \n");
            query.append("AND  PLP.CO_LOCAL ='"+AtuxVariables.vCodigoLocal+"' \n");
            query.append("AND  PPPP.CO_PRODUCTO ='"+coProducto+"' \n");
            query.append("AND LPLO.CO_COMPANIA    ='"+AtuxVariables.vCodigoCompania+"' \n");
            query.append("AND LPLO.CO_LOCAL    ='"+AtuxVariables.vCodigoLocal+"' \n");
            query.append("AND LPLP.CO_COMPANIA    ='"+AtuxVariables.vCodigoCompania+"' \n");
            query.append("AND LPLP.CO_LOCAL    ='"+AtuxVariables.vCodigoLocal+"' \n");
            query.append("AND SYSDATE BETWEEN TO_DATE(TO_CHAR(PP.FE_INI_PROMOCION,'dd/MM/yyyy') \n");
            query.append("  ||' 00:00:00','dd/MM/yyyy HH24:MI:SS') \n");
            query.append("AND TO_DATE(TO_CHAR(PP.FE_FIN_PROMOCION,'dd/MM/yyyy') \n");
            query.append("  ||' 23:59:59','dd/MM/yyyy HH24:MI:SS') \n");
            query.append("ORDER BY PPPP.CA_PRODUCTO DESC, \n");
            query.append("  PPPP.CA_PRODUCTO_P DESC");
            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
               promocion = new PromocionDetalle();

                   promocion.setPromocion(rs.getString(1));
                   promocion.setDeLaboratorioP(rs.getString(2));
                   rgs.add(promocion);
            }
            rs.close();
            BaseConexion.closeConnection();
            BaseConexion.setConnectionNull();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
        // Se cierran los recursos de base de datos.
        try {
        if (rs != null) {
            rs.close();
            }
        } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
        }
        return rgs;
    }

    public String getGuardaFraccion(String CoCompania, String CoLocal, String CoProducto, String InProdFraccion, int VaFraccion, String DeFraccion) throws SQLException{
        parametros = new ArrayList();
        parametros.add(CoCompania);
        parametros.add(CoLocal);
        parametros.add(CoProducto);
        parametros.add(InProdFraccion);
        parametros.add(VaFraccion);
        parametros.add(DeFraccion);
        parametros.add(AtuxVariables.vIdUsuario);
        return AtuxDBUtility.executeSQLStoredProcedureStr("INKVENTA_INVENTARIO.INV_GRABA_CAMBIO_FRACCION(?,?,?,?,?,?,?)", parametros);
    }
}
