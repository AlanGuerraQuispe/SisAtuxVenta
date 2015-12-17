package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.managerbd.BaseConexion;
import atux.modelbd.Producto;
import atux.modelbd.ProductoFull;
import atux.modelbd.ProductoLocal;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class CProducto extends JAbstractController {
    
    private Producto prd;
    private ProductoFull prdFull;
    private InputStream itmp;

    
    public void setProducto(JAbstractModelBD prv) {
        this.prd = (Producto)prv;
    }    
    
    public Producto getProducto() {
        if(prd == null)
        {
            prd = new Producto();
        }
        return prd;
    }

    public void setProductoFull(JAbstractModelBD prv) {
        this.prdFull = (ProductoFull)prv;
    }

    public ProductoFull getProductoFull() {
        if(prdFull == null)
        {
            prdFull = new ProductoFull();
        }
        return prdFull;
    }


    @Override
    public ArrayList getRegistros(Object[] cvl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
   @Override
    public ArrayList getRegistros() {
         throw new UnsupportedOperationException("Not supported yet.");
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

    public Producto getRegistro(int id)
    {
        ArrayList<Producto> registros = this.getRegistros(Producto.COLUMNA_PK,new Object[]{id});
        return registros.get(0);
    }
    
     public Producto getRegistroPorCodigo(String id)
    {
        ArrayList<Producto> registros = this.getRegistros(new String[]{"codigo"},new Object[]{id});
        return registros.get(0);
    }
    
    public ArrayList<Producto> getRegistros(String[] columnas,Object[] cvl) {
        return this.getRegistros(new String[]{}, columnas,cvl);
    }
    
    public Producto getRegistroPorPk(Object[] id)
    {
            Producto prod = null;
        try {
            
            rs =  this.selectPorPk(Producto.nt,Producto.FULL_CAMPOS, Producto.COLUMNA_PK, id);
            
            while(rs.next())
            {
               prod = new Producto();
               prod.setCoCompania(rs.getString(1));
               prod.setCoProducto(rs.getString(2));
               prod.setNuRevisionProducto(rs.getString(3));
               prod.setDeCortaProducto(rs.getString(4));
               prod.setDeProducto(rs.getString(5));
               prod.setCoFactorPrecio(rs.getString(6));
               prod.setCoMoneda(rs.getString(7));
               prod.setCoImpuesto_1(rs.getString(8));
               prod.setCoImpuesto_2(rs.getString(9));
               prod.setCoLaboratorio(rs.getString(10));
               prod.setLaboratorio(new CLaboratorio().getRegistroPorPk(new Object[]{rs.getString(1),rs.getString(10)}));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return prod;
    }               
    
    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        Producto prod = (Producto)mdl;
        int gravado = 0;
        String campos = Producto.CAMPOS_Maestros.toString();

        Object[] valores = {prod.getCoCompania(),
                prod.getCoProducto(),
                prod.getNuRevisionProducto(),
                prod.getDeProducto(),
                prod.getCoMoneda(),
                prod.getCoImpuesto1(),
                prod.getCoLaboratorio(),
                prod.getCoUnidadMedida(),
                prod.getVaUnidadMedida(),
                prod.getVaUnidadContenido(),
                prod.getInProdFraccionable(),
                prod.getVaPrecioCompra(),
                prod.getVaPrecioPromedio(),
                prod.getVaBono(),
                prod.getDeUnidadProducto(),
                prod.getInRecetaMedica(),
                prod.getEsProducto(),
                prod.getIdCreaProducto(),
                prod.getFeCreaProducto(),
                prod.getIdModProducto(),
                prod.getFeModProducto(),
                prod.getPcDescuentoBase(),
                prod.getVaCostoProducto(),
                prod.getVaPrecioPublico(),
                prod.getInControlado(),
                prod.getCoNivel01(),
                prod.getCoNivel02(),
                prod.getCoNivel03(),
                prod.getCoNivel04(),
                prod.getCoNivel05(),
                prod.getCoProcedencia(),
                prod.getFeVigencia(),
                prod.getInControlLote(),
                prod.getInPideMedico()
        };

        gravado = this.agregarRegistroPs(Producto.nt, Producto.CAMPOS_Maestros, valores);

        if(gravado==1)
        {
            return true;
        }


        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        Producto prod = (Producto)mdl;
        int gravado = 0;
        String campos = Producto.CAMPOS_NOID_NOIMAGE;  
        
        Object[] valores = {prod.getCoCompania(),
                            prod.getCoProducto(),
                            prod.getNuRevisionProducto(),
                            prod.getDeCortaProducto(),
                            prod.getDeProducto(),
                            prod.getCoFactorPrecio(),
                            prod.getCoMoneda(),
                            prod.getCoImpuesto_1(),
                            prod.getCoImpuesto_2(),
                            prod.getCoLaboratorio(),
                            prod.getPrimaryKey()
                   };
              
           if(prod.getImagen(null) != null)
            {
                campos = Producto.CAMPOS_NO_ID.toString();
                
                 valores = new Object[]{
                            prod.getImagen(null),
                            prod.getDeCortaProducto(),
                            prod.getDeProducto(),
                            prod.getCoFactorPrecio(),
                            prod.getCoMoneda(),
                            prod.getCoImpuesto_1(),
                            prod.getCoImpuesto_2(),
                            prod.getCoLaboratorio(),
                            prod.getPrimaryKey()
                   };
            }        
           gravado = this.actualizarRegistroPs(Producto.nt, this.adjuntarSimbolo(campos, ",", "?")+Ex.WHERE+Producto.COLUMNA_PK+" = ? ", valores);
       
        return gravado;
    }

    public int actualizarProductos(JAbstractModelBD mdl) {
        Producto prod = (Producto)mdl;
        int gravado = 0;
        String campos = Producto.CAMPOS_NOID_NOIMAGE;
//        prod.setPrimaryKey(new String[]{prod.getCoCompania(),prod.getCoProducto(),prod.getNuRevisionProducto()});
        Object[] valores = {prod.getDeProducto(),
                prod.getCoMoneda(),
                prod.getCoImpuesto1(),
                prod.getCoLaboratorio(),
                prod.getCoUnidadMedida(),
                prod.getVaUnidadMedida(),
                prod.getVaUnidadContenido(),
                prod.getInProdFraccionable(),
                prod.getVaPrecioCompra(),
                prod.getVaPrecioPromedio(),
                prod.getVaBono(),
                prod.getDeUnidadProducto(),
                prod.getInRecetaMedica(),
                prod.getEsProducto(),
                prod.getIdCreaProducto(),
                prod.getFeCreaProducto(),
                prod.getIdModProducto(),
                prod.getFeModProducto(),
                prod.getPcDescuentoBase(),
                prod.getVaCostoProducto(),
                prod.getVaPrecioPublico(),
                prod.getInControlado(),
                prod.getCoNivel01(),
                prod.getCoNivel02(),
                prod.getCoNivel03(),
                prod.getCoNivel04(),
                prod.getCoNivel05(),
                prod.getCoProcedencia(),
                prod.getFeVigencia(),
                prod.getInControlLote(),
                prod.getInPideMedico(),
        };

        gravado = this.actualizarRegistroPs(Producto.nt, this.adjuntarSimbolo(generarArrayAString(Producto.CAMPOS_Maestros_SINPK), ",", "?")+Ex.WHERE+ unirColumnasValores(Producto.COLUMNA_PK, prod.getPrimaryKey()) , valores);

        return gravado;
    }

    @Override
    public ArrayList getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Producto> rgs = new ArrayList<Producto>();        
        try {
                     
            rs = this.getRegistros(Producto.nt,campos,columnaId,id,null);
            
            Producto prod = null;
            while(rs.next())
            {
                   prod = new Producto();
                   prod.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
                   prod.setCoCompania(rs.getString(1));
                   prod.setCoProducto(rs.getString(2));
                   prod.setNuRevisionProducto(rs.getString(3));
                   prod.setDeCortaProducto(rs.getString(4));
                   prod.setDeProducto(rs.getString(5));
                   prod.setCoFactorPrecio(rs.getString(6));
                   prod.setCoMoneda(rs.getString(7));
                   prod.setCoImpuesto_1(rs.getString(8));
                   prod.setCoImpuesto_2(rs.getString(9));
                   prod.setCoLaboratorio(rs.getString(10));
                   prod.setVaPrecioPromedio(rs.getDouble(25));
                   prod.setDeUnidadProducto(rs.getString(28));
                   prod.setVaCostoProducto(rs.getDouble(47));   
                   //compra.setIdProveedor(cntp.getRegistroPorPk(new String[]{rs.getString(1),rs.getString(2)}));
                   //compra.setIdUsuario(new CUsuario().getRegistroPorPk(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)}));
                   //compra.setIdMoneda(new CMoneda().getRegistroPorPk(new String[]{rs.getString(1)}));
                   //prod.setImagen(rs.getBinaryStream(14)); //TODO AGUERRA                               
                 rgs.add(prod);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }
        
    public ImageIcon getFoto(String[] id)
    {
        InputStream archivo = this.getArchivo(Producto.nt, "imagen", Producto.COLUMNA_PK, id); 
        itmp = archivo;
        ImageIcon ii = null;
        if(archivo!= null)
        {
            try {
                 BufferedImage read = ImageIO.read(archivo);                 
                  ii = new ImageIcon(read);
            } catch (IOException ex) {
                Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(NullPointerException ex){
                ii = null;
            }
        }
        return ii;        
    }
     public boolean existeCodigo(String codigo)
    {
        return this.existe(Producto.nt, "codigo", codigo);
    }
    
    public boolean existeCodigoBarras(String codigoDeBarras)
    {
        return this.existe(Producto.nt, "cod_barras", codigoDeBarras);
    }
    
     public boolean existeCodigo(String codigo,String[] pk)
    {   
        System.out.println("Verificar"); //TODO AGUERRA
        return true;
        //return this.existe(Producto.nt, "codigo", codigo,Producto.COLUMNA_PK,pk);
    }
    
    public boolean existeCodigoBarras(String codigoDeBarras,String[] pk)
    {
        System.out.println("Verificar"); //TODO AGUERRA
        return true;
        //return this.existe(Producto.nt, "cod_barras", codigoDeBarras,Producto.COLUMNA_PK,pk);
    }
    
    public int getTotalRegistros()
    {
        System.out.println("Verificar"); //TODO AGUERRA
        return 1;
        //return this.getNumeroRegistros(Producto.nt, Producto.COLUMNA_PK);
    }

    public Double getImpuesto(String coImpuesto) throws SQLException {
        return Double.valueOf(AtuxDBUtility.getValueAt("VTTR_IMPUESTO",
                "PC_IMPUESTO",
                " CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +                
                " AND CO_IMPUESTO= '" + coImpuesto + "'"));                
    }
    
    public ArrayList getProductosConMismoPrincipioActivo(String coProducto)
    {
        ArrayList<Producto> rgs = new ArrayList<Producto>();                
        Producto      prod      = null;        
        StringBuffer  query;
        try {            
            query = new StringBuffer();
            query.append("SELECT Z.CO_COMPANIA , ");
            query.append("       Z.CO_PRODUCTO , ");
            query.append("       Z.NU_REVISION_PRODUCTO , ");
            query.append("       Z.DE_PRODUCTO , ");
            query.append("       Z.DE_UNIDAD , ");
            query.append("       Z.VA_VENTA , ");
            query.append("       Z.VA_PRECIO_PUBLICO , ");
            query.append("       Z.CA_STOCK , ");
            query.append("       Z.VA_BONO , ");
            query.append("       Z.DE_LABORATORIO, ");
            query.append("       Z.PC_DESCUENTO, ");
            query.append("       Z.ORDEN , ");
            query.append("       Z.CLASE , ");
            query.append("       Z.IN_PROD_FRACCIONADO ");
            query.append(" FROM (SELECT A.CO_COMPANIA,A.CO_PRODUCTO CO_PRODUCTO,B.NU_REVISION_PRODUCTO, A.DE_PRODUCTO DE_PRODUCTO, ");
            query.append("              DECODE(B.IN_PROD_FRACCIONADO,'S',NVL(B.DE_UNIDAD_FRACCION,' '), NVL(A.DE_UNIDAD_ENTERO,' ')) DE_UNIDAD, ");            
            query.append("              DECODE(B.VA_VENTA,NULL,0,B.VA_VENTA) VA_VENTA, ");
            query.append("              NVL(B.VA_VENTA,0) - NVL(B.VA_VENTA,0) * (1-(1-(NVL(B.PC_DESCUENTO_1,0)/100))) VA_PRECIO_PUBLICO, ");
            query.append("              NVL(B.CA_STOCK_DISPONIBLE,0) CA_STOCK, ");
            query.append("              DECODE(A.VA_BONO_ENTERO,NULL,0,A.VA_BONO_ENTERO / DECODE(B.VA_FRACCION,NULL, 1, 0, 1, B.VA_FRACCION)) VA_BONO, ");
            query.append("              A.DE_LABORATORIO DE_LABORATORIO, ");
            query.append("              ((1-(1-(DECODE(B.PC_DESCUENTO_1,NULL,0,B.PC_DESCUENTO_1)/100)))* 100) PC_DESCUENTO, ");
            query.append("              A.ORDEN ORDEN, ");
            query.append("              A.CLASE CLASE, ");
            query.append("              B.IN_PROD_FRACCIONADO IN_PROD_FRACCIONADO, ");
            query.append("              A.IN_LAM IN_LAM, ");
            query.append("              CASE ");
            query.append("                  WHEN NVL(B.CA_STOCK_DISPONIBLE,0) - NVL(B.CA_STOCK_COMPROMETIDO,0) > 0 THEN 1 ");
            query.append("              ELSE 0 ");
            query.append("              END AS TIENE_STOCK ");
            query.append("                  FROM LOGISTICA.LGTX_PRODUCTO_PRINCIPIOS A, ");
            query.append("                       LOGISTICA.LGTR_PRODUCTO_LOCAL B ");
            query.append("                     WHERE A.CO_COMPANIA          = '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                       AND A.CO_PRODUCTO_PADRE    = '").append(coProducto).append("'");
            query.append("                       AND B.ES_PROD_LOCAL = 'A' ");
            query.append("                       AND A.CO_COMPANIA = B.CO_COMPANIA ");
            query.append("                       AND A.CO_PRODUCTO = B.CO_PRODUCTO) Z ");
            query.append("             ORDER BY Z.TIENE_STOCK DESC, Z.IN_LAM ASC, Z.VA_BONO DESC");                  
            
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                   prod = new Producto();
                   prod.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
                   prod.setCoCompania(rs.getString(1));
                   prod.setCoProducto(rs.getString(2));
                   prod.setNuRevisionProducto(rs.getString(3)); 
                   prod.setDeProducto(rs.getString(4));
                   prod.setDeUnidadProducto(rs.getString(5));
                   prod.setVaVenta(rs.getDouble(6));
                   prod.setVaPrecioPublico(rs.getDouble(7));
                   prod.setCaStockDisponible(rs.getInt(8));
                   prod.setVaBono(rs.getDouble(9));
                   prod.setDeLaboratorio(rs.getString(10));
                   prod.setPcDescuento(rs.getInt(11));
                   prod.setOrden(rs.getString(12));
                   prod.setClase(rs.getString(13));
                   prod.setInProdFraccionado(rs.getString(14));                  
                   
                 rgs.add(prod);
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

    public ArrayList getProductosLaboratorio(String Filtro){
        ArrayList<Producto> rgs = new ArrayList<Producto>();        
        Producto      prod      = null;        
        StringBuffer  query;
        try {            
            query = new StringBuffer();
            
            query.append("select T1.*, T2.DE_LABORATORIO  ");
            query.append("  from LGTM_PRODUCTO T1, ");
            query.append("       CMTR_LABORATORIO T2 ");
            query.append(" WHERE T1.CO_COMPANIA = T2.CO_COMPANIA ");
            query.append("   AND T1.CO_LABORATORIO = T2.CO_LABORATORIO ");
            query.append("   AND T1.CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            //query.append("   AND T1.CO_LABORATORIO IN ('0014','0100') ");

            if ("A".equals(Filtro) || "I".equals(Filtro)){
                query.append("   AND T1.ES_PRODUCTO = '").append(Filtro).append("' ");
            }else if (!"T".equals(Filtro)){
                query.append(Filtro);
            }
            
            query.append(" ORDER BY DE_PRODUCTO ");
            
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next()){
                    prod = new Producto();
                    prod.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_PRODUCTO"),rs.getString("NU_REVISION_PRODUCTO")});
                    prod.setCoCompania(rs.getString("CO_COMPANIA"));
                    prod.setCoProducto(rs.getString("CO_PRODUCTO"));
                    prod.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
                    prod.setDeCortaProducto(rs.getString("DE_CORTA_PRODUCTO"));
                    prod.setDeProducto(rs.getString("DE_PRODUCTO"));
                    prod.setCoFactorPrecio(rs.getString("CO_FACTOR_PRECIO"));
                    prod.setCoMoneda(rs.getString("CO_MONEDA"));
                    prod.setCoImpuesto1(rs.getString("CO_IMPUESTO_1"));
                    prod.setCoImpuesto2(rs.getString("CO_IMPUESTO_2"));
                    prod.setCoLaboratorio(rs.getString("CO_LABORATORIO"));
                    prod.setCoLineaProducto(rs.getString("CO_LINEA_PRODUCTO"));
                    prod.setCoGrupoProducto(rs.getString("CO_GRUPO_PRODUCTO"));
                    prod.setCoCategoriaCcr(rs.getString("CO_CATEGORIA_CCR"));
                    prod.setCoClasificacionIms(rs.getString("CO_CLASIFICACION_IMS"));
                    prod.setCoCategoriaSb(rs.getString("CO_CATEGORIA_SB"));
                    prod.setCoSubcategoriaSb(rs.getString("CO_SUBCATEGORIA_SB"));
                    prod.setCoUnidadMedida(rs.getString("CO_UNIDAD_MEDIDA"));
                    prod.setVaUnidadMedida(rs.getInt("VA_UNIDAD_MEDIDA"));
                    prod.setCoUnidadCompra(rs.getString("CO_UNIDAD_COMPRA"));
                    prod.setCoUnidadVenta(rs.getString("CO_UNIDAD_VENTA"));
                    prod.setCoUnidadContenido(rs.getString("CO_UNIDAD_CONTENIDO"));
                    prod.setVaUnidadContenido(rs.getInt("VA_UNIDAD_CONTENIDO"));
                    prod.setVaPrecioCompra(rs.getDouble("VA_PRECIO_COMPRA"));
                    prod.setVaPrecioPromedio(rs.getDouble("VA_PRECIO_PROMEDIO"));
                    prod.setVaBono(rs.getDouble("VA_BONO"));
                    prod.setPcBono(rs.getDouble("PC_BONO"));
                    prod.setDeUnidadProducto(rs.getString("DE_UNIDAD_PRODUCTO"));
                    prod.setEsProducto(rs.getString("ES_PRODUCTO"));
                    prod.setIdCreaProducto(rs.getString("ID_CREA_PRODUCTO"));
                    prod.setFeCreaProducto(rs.getDate("FE_CREA_PRODUCTO"));
                    prod.setIdModProducto(rs.getString("ID_MOD_PRODUCTO"));
                    prod.setFeModProducto(rs.getDate("FE_MOD_PRODUCTO"));
                    prod.setCoClaseVenta(rs.getString("CO_CLASE_VENTA"));
                    prod.setCoGrupoProdErp(rs.getString("CO_GRUPO_PROD_ERP"));
                    prod.setCoLineaProdErp(rs.getString("CO_LINEA_PROD_ERP"));
                    prod.setCoOtc(rs.getString("CO_OTC"));
                    prod.setCoGrupoTerapeutico(rs.getString("CO_GRUPO_TERAPEUTICO"));
                    prod.setCoGiro(rs.getString("CO_GIRO"));
                    prod.setCoGrupoAnatomico(rs.getString("CO_GRUPO_ANATOMICO"));
                    prod.setCoFormaPresentacion(rs.getString("CO_FORMA_PRESENTACION"));
                    prod.setCoAccionTerapeutica(rs.getString("CO_ACCION_TERAPEUTICA"));
                    prod.setCoAccionFarmaceutica(rs.getString("CO_ACCION_FARMACEUTICA"));
                    prod.setPcDescuentoBase(rs.getInt("PC_DESCUENTO_BASE"));
                    prod.setCoViadm(rs.getString("CO_VIADM"));
//                    prod.setVaCostoProducto(rs.getInt("VA_COSTO_PRODUCTO"));
                    prod.setDeDetallePresentacionLargo(rs.getString("DE_DETALLE_PRESENTACION_LARGO"));
//                    prod.setVaPrecioPublico(rs.getInt("VA_PRECIO_PUBLICO"));
                    prod.setCoProductoSap(rs.getString("CO_PRODUCTO_SAP"));
                    prod.setCoTipoConsumo(rs.getString("CO_TIPO_CONSUMO"));
                    prod.setCoComprador(rs.getString("CO_COMPRADOR"));
                    prod.setInReintegro(rs.getString("IN_REINTEGRO"));
                    prod.setInDescontinuado(rs.getString("IN_DESCONTINUADO"));
                    prod.setInControlado(rs.getString("IN_CONTROLADO"));
                    prod.setInControlLote(rs.getString("IN_CONTROL_LOTE"));
                    prod.setInPideMedico(rs.getString("IN_PIDE_MEDICO"));
                    prod.setInRecetaMedica(rs.getString("IN_RECETA_MEDICA"));
                    prod.setInProdFraccionable(rs.getString("IN_PROD_FRACCIONABLE"));
//                    prod.setCaEmpaque(rs.getInt("CA_EMPAQUE"));
                    prod.setInConsignadoSap(rs.getString("IN_CONSIGNADO_SAP"));
                    prod.setCoUnidadSap(rs.getString("CO_UNIDAD_SAP"));
                    prod.setTiMaterialSap(rs.getString("TI_MATERIAL_SAP"));
                    prod.setCoUnidadFmSap(rs.getString("CO_UNIDAD_FM_SAP"));
                    prod.setCoGrupoExt(rs.getString("CO_GRUPO_EXT"));
                    prod.setDePartidaArancelaria(rs.getString("DE_PARTIDA_ARANCELARIA"));
                    prod.setDeLaboratorio(rs.getString("DE_LABORATORIO"));
                    prod.setCoNivel01(rs.getString("CO_NIVEL_01"));
                    prod.setCoNivel02(rs.getString("CO_NIVEL_02"));
                    prod.setCoNivel03(rs.getString("CO_NIVEL_03"));
                    prod.setCoNivel04(rs.getString("CO_NIVEL_04"));
                    prod.setCoNivel05(rs.getString("CO_NIVEL_05"));
                    prod.setCoProcedencia(rs.getString("CO_PROCEDENCIA"));
//                    prod.setNoControlDigemid(rs.getString("NO_CONTROL_DIGEMID"));
//                    prod.setNoControlLote(rs.getString("NO_CONTROL_LOTE"));
//                    prod.setNoTipoPrecio(rs.getString("NO_TIPO_PRECIO"));
//                    prod.setFeVigencia(rs.getDate("FE_VIGENCIA"));
//                    prod.setFeUltVenta(rs.getDate("FE_ULT_VENTA"));
                    rgs.add(prod);
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


    public ArrayList getProductosMaestrosFull(String Filtro){
        ArrayList<ProductoFull> rgs = new ArrayList<ProductoFull>();
        ProductoFull prod      = null;
        StringBuffer  query;
        try {
            query = new StringBuffer();
            query.append("SELECT tmp.CO_COMPANIA                   tmp_CO_COMPANIA, ");
            query.append("       tmp.CO_PRODUCTO                   tmp_CO_PRODUCTO,");
            query.append("       tmp.NU_REVISION_PRODUCTO          tmp_NU_REVISION_PRODUCTO, ");
            query.append("       tmp.DE_CORTA_PRODUCTO             tmp_DE_CORTA_PRODUCTO, ");
            query.append("       tmp.DE_PRODUCTO                   tmp_DE_PRODUCTO, ");
            query.append("       tmp.CO_FACTOR_PRECIO              tmp_CO_FACTOR_PRECIO, ");
            query.append("       tmp.CO_MONEDA                     tmp_CO_MONEDA, ");
            query.append("       tmp.CO_IMPUESTO_1                 tmp_CO_IMPUESTO_1, ");
            query.append("       tmp.CO_IMPUESTO_2                 tmp_CO_IMPUESTO_2, ");
            query.append("       tmp.CO_LABORATORIO                tmp_CO_LABORATORIO, ");
            query.append("       tmp.CO_LINEA_PRODUCTO             tmp_CO_LINEA_PRODUCTO, ");
            query.append("       tmp.CO_GRUPO_PRODUCTO             tmp_CO_GRUPO_PRODUCTO, ");
            query.append("       tmp.CO_CATEGORIA_CCR              tmp_CO_CATEGORIA_CCR, ");
            query.append("       tmp.CO_CLASIFICACION_IMS          tmp_CO_CLASIFICACION_IMS, ");
            query.append("       tmp.CO_CATEGORIA_SB               tmp_CO_CATEGORIA_SB, ");
            query.append("       tmp.CO_SUBCATEGORIA_SB            tmp_CO_SUBCATEGORIA_SB, ");
            query.append("       tmp.CO_UNIDAD_MEDIDA              tmp_CO_UNIDAD_MEDIDA, ");
            query.append("       tmp.VA_UNIDAD_MEDIDA              tmp_VA_UNIDAD_MEDIDA, ");
            query.append("       tmp.CO_UNIDAD_COMPRA              tmp_CO_UNIDAD_COMPRA, ");
            query.append("       tmp.CO_UNIDAD_VENTA               tmp_CO_UNIDAD_VENTA, ");
            query.append("       tmp.CO_UNIDAD_CONTENIDO           tmp_CO_UNIDAD_CONTENIDO, ");
            query.append("       tmp.VA_UNIDAD_CONTENIDO           tmp_VA_UNIDAD_CONTENIDO, ");
            query.append("       tmp.IN_PROD_FRACCIONABLE          tmp_IN_PROD_FRACCIONABLE, ");
            query.append("       tmp.VA_PRECIO_COMPRA              tmp_VA_PRECIO_COMPRA, ");
            query.append("       tmp.VA_PRECIO_PROMEDIO            tmp_VA_PRECIO_PROMEDIO, ");
            query.append("       tmp.VA_BONO                       tmp_VA_BONO, ");
            query.append("       tmp.PC_BONO                       tmp_PC_BONO, ");
            query.append("       tmp.DE_UNIDAD_PRODUCTO            tmp_DE_UNIDAD_PRODUCTO, ");
            query.append("       tmp.IN_RECETA_MEDICA              tmp_IN_RECETA_MEDICA, ");
            query.append("       tmp.ES_PRODUCTO                   tmp_ES_PRODUCTO, ");
            query.append("       tmp.ID_CREA_PRODUCTO              tmp_ID_CREA_PRODUCTO, ");
            query.append("       tmp.FE_CREA_PRODUCTO              tmp_FE_CREA_PRODUCTO, ");
            query.append("       tmp.ID_MOD_PRODUCTO               tmp_ID_MOD_PRODUCTO, ");
            query.append("       tmp.FE_MOD_PRODUCTO               tmp_FE_MOD_PRODUCTO, ");
            query.append("       tmp.CO_CLASE_VENTA                tmp_CO_CLASE_VENTA, ");
            query.append("       tmp.CO_GRUPO_PROD_ERP             tmp_CO_GRUPO_PROD_ERP, ");
            query.append("       tmp.CO_LINEA_PROD_ERP             tmp_CO_LINEA_PROD_ERP, ");
            query.append("       tmp.CO_OTC                        tmp_CO_OTC, ");
            query.append("       tmp.CO_GRUPO_TERAPEUTICO          tmp_CO_GRUPO_TERAPEUTICO, ");
            query.append("       tmp.CO_GIRO                       tmp_CO_GIRO, ");
            query.append("       tmp.CO_GRUPO_ANATOMICO            tmp_CO_GRUPO_ANATOMICO, ");
            query.append("       tmp.CO_FORMA_PRESENTACION         tmp_CO_FORMA_PRESENTACION, ");
            query.append("       tmp.CO_ACCION_TERAPEUTICA         tmp_CO_ACCION_TERAPEUTICA, ");
            query.append("       tmp.CO_ACCION_FARMACEUTICA        tmp_CO_ACCION_FARMACEUTICA, ");
            query.append("       tmp.PC_DESCUENTO_BASE             tmp_PC_DESCUENTO_BASE, ");
            query.append("       tmp.CO_VIADM                      tmp_CO_VIADM, ");
            query.append("       tmp.VA_COSTO_PRODUCTO             tmp_VA_COSTO_PRODUCTO, ");
            query.append("       tmp.DE_DETALLE_PRESENTACION_LARGO tmp_DE_DETALLE_PRESENT_LARGO, ");
            query.append("       tmp.VA_PRECIO_PUBLICO             tmp_VA_PRECIO_PUBLICO, ");
            query.append("       tmp.CO_PRODUCTO_SAP               tmp_CO_PRODUCTO_SAP, ");
            query.append("       tmp.CO_TIPO_CONSUMO               tmp_CO_TIPO_CONSUMO, ");
            query.append("       tmp.CO_COMPRADOR                  tmp_CO_COMPRADOR, ");
            query.append("       tmp.IN_REINTEGRO                  tmp_IN_REINTEGRO, ");
            query.append("       tmp.IN_DESCONTINUADO              tmp_IN_DESCONTINUADO, ");
            query.append("       tmp.IN_CONTROLADO                 tmp_IN_CONTROLADO, ");
            query.append("       tmp.CA_EMPAQUE_MIN                tmp_CA_EMPAQUE_MIN, ");
            query.append("       tmp.IN_CONSIGNADO_SAP             tmp_IN_CONSIGNADO_SAP, ");
            query.append("       tmp.CO_UNIDAD_SAP                 tmp_CO_UNIDAD_SAP, ");
            query.append("       tmp.TI_MATERIAL_SAP               tmp_TI_MATERIAL_SAP, ");
            query.append("       tmp.CO_UNIDAD_FM_SAP              tmp_CO_UNIDAD_FM_SAP, ");
            query.append("       tmp.CO_GRUPO_EXT                  tmp_CO_GRUPO_EXT, ");
            query.append("       tmp.DE_PARTIDA_ARANCELARIA        tmp_DE_PARTIDA_ARANCELARIA, ");
            query.append("       tmp.CO_PRODUCTO_BK                tmp_CO_PRODUCTO_BK, ");
            query.append("       tmp.CO_NIVEL_01                   tmp_CO_NIVEL_01, ");
            query.append("       tmp.CO_NIVEL_02                   tmp_CO_NIVEL_02, ");
            query.append("       tmp.CO_NIVEL_03                   tmp_CO_NIVEL_03, ");
            query.append("       tmp.CO_NIVEL_04                   tmp_CO_NIVEL_04, ");
            query.append("       tmp.CO_NIVEL_05                   tmp_CO_NIVEL_05, ");
            query.append("       tmp.CO_PROCEDENCIA                tmp_CO_PROCEDENCIA, ");
            query.append("       tmp.NO_CONTROL_DIGEMID            tmp_NO_CONTROL_DIGEMID, ");
            query.append("       tmp.NO_CONTROL_LOTE               tmp_NO_CONTROL_LOTE, ");
            query.append("       tmp.NO_TIPO_PRECIO                tmp_NO_TIPO_PRECIO, ");
            query.append("       tmp.FE_VIGENCIA                   tmp_FE_VIGENCIA, ");
            query.append("       tmp.FE_ULT_VENTA                  tmp_FE_ULT_VENTA, ");
            query.append("       tmp.IN_CONTROL_LOTE               tmp_IN_CONTROL_LOTE, ");
            query.append("       tmp.MIN_FRACC                     tmp_MIN_FRACC, ");
            query.append("       tmp.MAX_FRACC                     tmp_MAX_FRACC, ");
            query.append("       tmp.IN_PIDE_MEDICO                tmp_IN_PIDE_MEDICO, ");
            query.append("       tmp.CA_EMPAQUE_MAX                tmp_CA_EMPAQUE_MAX, ");
            query.append("       tpl.CO_COMPANIA                   tpl_CO_COMPANIA, ");
            query.append("       tpl.CO_LOCAL                      tpl_CO_LOCAL, ");
            query.append("       tpl.CO_PRODUCTO                   tpl_CO_PRODUCTO, ");
            query.append("       tpl.NU_REVISION_PRODUCTO          tpl_NU_REVISION_PRODUCTO, ");
            query.append("       tpl.VA_VENTA                      tpl_VA_VENTA, ");
            query.append("       tpl.CO_UNIDAD_VENTA_FRACCION      tpl_CO_UNIDAD_VENTA_FRACCION, ");
            query.append("       tpl.CO_MONEDA                     tpl_CO_MONEDA, ");
            query.append("       tpl.VA_FRACCION                   tpl_VA_FRACCION, ");
            query.append("       tpl.IN_PROD_FRACCIONADO           tpl_IN_PROD_FRACCIONADO, ");
            query.append("       tpl.CO_UNIDAD_CONTENIDO           tpl_CO_UNIDAD_CONTENIDO, ");
            query.append("       tpl.VA_CONTENIDO_FRACCION         tpl_VA_CONTENIDO_FRACCION, ");
            query.append("       tpl.CA_STOCK_DISPONIBLE           tpl_CA_STOCK_DISPONIBLE, ");
            query.append("       tpl.CA_STOCK_TRANSITO             tpl_CA_STOCK_TRANSITO, ");
            query.append("       tpl.CA_STOCK_COMPROMETIDO         tpl_CA_STOCK_COMPROMETIDO, ");
            query.append("       tpl.CA_STOCK_MINIMO               tpl_CA_STOCK_MINIMO, ");
            query.append("       tpl.CA_STOCK_MAXIMO               tpl_CA_STOCK_MAXIMO, ");
            query.append("       tpl.CO_DESCUENTO_1                tpl_CO_DESCUENTO_1, ");
            query.append("       tpl.PC_DESCUENTO_1                tpl_PC_DESCUENTO_1, ");
            query.append("       tpl.CO_DESCUENTO_2                tpl_CO_DESCUENTO_2, ");
            query.append("       tpl.PC_DESCUENTO_2                tpl_PC_DESCUENTO_2, ");
            query.append("       tpl.CO_DESCUENTO_3                tpl_CO_DESCUENTO_3, ");
            query.append("       tpl.PC_DESCUENTO_3                tpl_PC_DESCUENTO_3, ");
            query.append("       tpl.PC_DCTO_VENTA_ESPECIAL        tpl_PC_DCTO_VENTA_ESPECIAL, ");
            query.append("       tpl.IN_PROD_INVENTARIO            tpl_IN_PROD_INVENTARIO, ");
            query.append("       tpl.IN_PRODUCTO_REPONER           tpl_IN_PRODUCTO_REPONER, ");
            query.append("       tpl.CA_STOCK_REPONER              tpl_CA_STOCK_REPONER, ");
            query.append("       tpl.CA_STOCK_REPONER_CALCULA      tpl_CA_STOCK_REPONER_CALCULA, ");
            query.append("       tpl.DE_UNIDAD_FRACCION            tpl_DE_UNIDAD_FRACCION, ");
            query.append("       tpl.NU_DIAS_ROTACION_PROMEDIO     tpl_NU_DIAS_ROTACION_PROMEDIO, ");
            query.append("       tpl.NU_MIN_DIAS_REPOSICION        tpl_NU_MIN_DIAS_REPOSICION, ");
            query.append("       tpl.NU_MAX_DIAS_REPOSICION        tpl_NU_MAX_DIAS_REPOSICION, ");
            query.append("       tpl.CA_MIN_PROD_EXHIBICION        tpl_CA_MIN_PROD_EXHIBICION, ");
            query.append("       tpl.CA_PROD_NO_ATENDIDO           tpl_CA_PROD_NO_ATENDIDO, ");
            query.append("       tpl.ES_PROD_LOCAL                 tpl_ES_PROD_LOCAL, ");
            query.append("       tpl.ID_CREA_PROD_LOCAL            tpl_ID_CREA_PROD_LOCAL, ");
            query.append("       tpl.FE_CREA_PROD_LOCAL            tpl_FE_CREA_PROD_LOCAL, ");
            query.append("       tpl.ID_MOD_PROD_LOCAL             tpl_ID_MOD_PROD_LOCAL, ");
            query.append("       tpl.FE_MOD_PROD_LOCAL             tpl_FE_MOD_PROD_LOCAL, ");
            query.append("       tpl.VA_ROTACION                   tpl_VA_ROTACION, ");
            query.append("       tpl.CA_ULTIMO_PEDIDO_REP          tpl_CA_ULTIMO_PEDIDO_REP, ");
            query.append("       tpl.IN_REPLICA                    tpl_IN_REPLICA, ");
            query.append("       tpl.FE_REPLICA                    tpl_FE_REPLICA, ");
            query.append("       tpl.NU_SEC_REPLICA                tpl_NU_SEC_REPLICA, ");
            query.append("       tpl.CA_STK_DISPONIBLE_PED_REP     tpl_CA_STK_DISPONIBLE_PED_REP, ");
            query.append("       tpl.CA_STOCK_TRANSITO_PED_REP     tpl_CA_STOCK_TRANSITO_PED_REP, ");
            query.append("       tpl.CO_CENTRO_BENEFICIO_SAP       tpl_CO_CENTRO_BENEFICIO_SAP, ");
            query.append("       tpl.VA_TOTAL_INVENTARIO           tpl_VA_TOTAL_INVENTARIO, ");
            query.append("       tpl.VA_COSTO_PRODUCTO             tpl_VA_COSTO_PRODUCTO, ");
            query.append("       tpl.VA_PRECIO_PUBLICO             tpl_VA_PRECIO_PUBLICO, ");
            query.append("       tpl.CO_UNIDAD_LOCAL_SAP           tpl_CO_UNIDAD_LOCAL_SAP, ");
            query.append("       tpl.CO_PRODUCTO_BK                tpl_CO_PRODUCTO_BK, ");
            query.append("       tpl.CO_PRODUCTO2                  tpl_CO_PRODUCTO2, ");
            query.append("       tpl.IN_MOD_CAMBIO                 tpl_IN_MOD_CAMBIO, ");
            query.append("       tpl.FE_ULT_MOD_PRECIO             tpl_FE_ULT_MOD_PRECIO, ");
            query.append("       tpl.TS_DIAS                       tpl_TS_DIAS, ");
            query.append("       tpl.TR_DIAS                       tpl_TR_DIAS, ");
            query.append("       tg1.CO_LINEA_PROD_ERP             tg1_CO_LINEA_PROD_ERP, ");
            query.append("       tg1.DE_LINEA_PROD_ERP             tg1_DE_LINEA_PROD_ERP, ");
            query.append("       tg1.ES_LINEA_PROD_ERP             tg1_ES_LINEA_PROD_ERP, ");
            query.append("       tg1.ID_CREA_LINEA_PROD_ERP        tg1_ID_CREA_LINEA_PROD_ERP, ");
            query.append("       tg1.FE_CREA_LINEA_PROD_ERP        tg1_FE_CREA_LINEA_PROD_ERP, ");
            query.append("       tg1.ID_MOD_LINEA_PROD_ERP         tg1_ID_MOD_LINEA_PROD_ERP, ");
            query.append("       tg1.FE_MOD_LINEA_PROD_ERP         tg1_FE_MOD_LINEA_PROD_ERP, ");
            query.append("       tg1.CO_NIVEL_01                   tg1_CO_NIVEL_01, ");
            query.append("       tg1.CO_NIVEL_SUPERIOR             tg1_CO_NIVEL_SUPERIOR, ");
            query.append("       tg2.CO_GRUPO_PROD_ERP             tg2_CO_GRUPO_PROD_ERP, ");
            query.append("       tg2.DE_GRUPO_PROD_ERP             tg2_DE_GRUPO_PROD_ERP, ");
            query.append("       tg2.ES_GRUPO_PROD_ERP             tg2_ES_GRUPO_PROD_ERP, ");
            query.append("       tg2.ID_CREA_GRUPO_PROD_ERP        tg2_ID_CREA_GRUPO_PROD_ERP, ");
            query.append("       tg2.FE_CREA_GRUPO_PROD_ERP        tg2_FE_CREA_GRUPO_PROD_ERP, ");
            query.append("       tg2.ID_MOD_GRUPO_PROD_ERP         tg2_ID_MOD_GRUPO_PROD_ERP, ");
            query.append("       tg2.FE_MOD_GRUPO_PROD_ERP         tg2_FE_MOD_GRUPO_PROD_ERP, ");
            query.append("       tg2.CO_NIVEL_02                   tg2_CO_NIVEL_02, ");
            query.append("       tg2.CO_NIVEL_SUPERIOR             tg2_CO_NIVEL_SUPERIOR, ");
            query.append("       tg3.CO_GRUPO_ANATOMICO            tg3_CO_GRUPO_ANATOMICO, ");
            query.append("       tg3.CO_IMS                        tg3_CO_IMS, ");
            query.append("       tg3.DE_GRUPO_ANATOMICO            tg3_DE_GRUPO_ANATOMICO, ");
            query.append("       tg3.ES_GRUPO_ANATOMICO            tg3_ES_GRUPO_ANATOMICO, ");
            query.append("       tg3.ID_CREA_GRUPO_ANATOMICO       tg3_ID_CREA_GRUPO_ANATOMICO, ");
            query.append("       tg3.FE_CREA_GRUPO_ANATOMICO       tg3_FE_CREA_GRUPO_ANATOMICO, ");
            query.append("       tg3.ID_MOD_GRUPO_ANATOMICO        tg3_ID_MOD_GRUPO_ANATOMICO, ");
            query.append("       tg3.FE_MOD_GRUPO_ANATOMICO        tg3_FE_MOD_GRUPO_ANATOMICO, ");
            query.append("       tg3.CO_NIVEL_03                   tg3_CO_NIVEL_03, ");
            query.append("       tg3.CO_NIVEL_SUPERIOR             tg3_CO_NIVEL_SUPERIOR, ");
            query.append("       tg4.CO_GRUPO_TERAPEUTICO          tg4_CO_GRUPO_TERAPEUTICO, ");
            query.append("       tg4.CO_IMS                        tg4_CO_IMS, ");
            query.append("       tg4.DE_GRUPO_TERAPEUTICO          tg4_DE_GRUPO_TERAPEUTICO, ");
            query.append("       tg4.ES_GRUPO_TERAPEUTICO          tg4_ES_GRUPO_TERAPEUTICO, ");
            query.append("       tg4.ID_CREA_GRUPO_TERAPEUTICO     tg4_ID_CREA_GRUPO_TERAPEUTICO, ");
            query.append("       tg4.FE_CREA_GRUPO_TERAPEUTICO     tg4_FE_CREA_GRUPO_TERAPEUTICO, ");
            query.append("       tg4.ID_MOD_GRUPO_TERAPEUTICO      tg4_ID_MOD_GRUPO_TERAPEUTICO, ");
            query.append("       tg4.FE_MOD_GRUPO_TERAPEUTICO      tg4_FE_MOD_GRUPO_TERAPEUTICO, ");
            query.append("       tg4.CO_NIVEL_04                   tg4_CO_NIVEL_04, ");
            query.append("       tg4.CO_NIVEL_SUPERIOR             tg4_CO_NIVEL_SUPERIOR, ");
            query.append("       tg5.CO_ACCION_TERAPEUTICA         tg5_CO_ACCION_TERAPEUTICA, ");
            query.append("       tg5.CO_IMS                        tg5_CO_IMS, ");
            query.append("       tg5.DE_ACCION_TERAPEUTICA         tg5_DE_ACCION_TERAPEUTICA, ");
            query.append("       tg5.ES_ACCION_TERAPEUTICA         tg5_ES_ACCION_TERAPEUTICA, ");
            query.append("       tg5.ID_CREA_ACCION_TERAPEUTICA    tg5_ID_CREA_ACCION_TERAPEUTICA, ");
            query.append("       tg5.FE_CREA_ACCION_TERAPEUTICA    tg5_FE_CREA_ACCION_TERAPEUTICA, ");
            query.append("       tg5.ID_MOD_ACCION_TERAPEUTICA     tg5_ID_MOD_ACCION_TERAPEUTICA, ");
            query.append("       tg5.FE_MOD_ACCION_TERAPEUTICA     tg5_FE_MOD_ACCION_TERAPEUTICA, ");
            query.append("       tg5.CO_NIVEL_05                   tg5_CO_NIVEL_05, ");
            query.append("       tg5.CO_NIVEL_SUPERIOR             tg5_CO_NIVEL_SUPERIOR, ");
            query.append("       tlb.CO_COMPANIA                   tlb_CO_COMPANIA, ");
            query.append("       tlb.CO_LABORATORIO                tlb_CO_LABORATORIO, ");
            query.append("       tlb.NU_ORDEN_FILA                 tlb_NU_ORDEN_FILA, ");
            query.append("       tlb.DE_LABORATORIO                tlb_DE_LABORATORIO, ");
            query.append("       tlb.IN_LAB_INVENTARIO             tlb_IN_LAB_INVENTARIO, ");
            query.append("       tlb.ES_LABORATORIO                tlb_ES_LABORATORIO, ");
            query.append("       tlb.ID_CREA_LABORATORIO           tlb_ID_CREA_LABORATORIO, ");
            query.append("       tlb.FE_CREA_LABORATORIO           tlb_FE_CREA_LABORATORIO, ");
            query.append("       tlb.ID_MOD_LABORATORIO            tlb_ID_MOD_LABORATORIO, ");
            query.append("       tlb.FE_MOD_LABORATORIO            tlb_FE_MOD_LABORATORIO, ");
            query.append("       tlb.DE_DIREC_LABORATORIO          tlb_DE_DIREC_LABORATORIO, ");
            query.append("       tlb.NU_TELEF_LABORATORIO          tlb_NU_TELEF_LABORATORIO, ");
            query.append("       tlb.IN_LAB_PROPIO                 tlb_IN_LAB_PROPIO, ");
            query.append("       tlb.IN_LAB_TOMA_INV               tlb_IN_LAB_TOMA_INV, ");
            query.append("       tlb.CO_LABORATORIO_SAP            tlb_CO_LABORATORIO_SAP, ");
            query.append("       tlb.DE_ABREV_LAB                  tlb_DE_ABREV_LAB, ");
            query.append("       tlb.NO_EMAIL_LAB                  tlb_NO_EMAIL_LAB, ");
            query.append("       tlb.NO_CONTACTO                   tlb_NO_CONTACTO, ");
            query.append("       tlb.NU_TEL_CONTACTO               tlb_NU_TEL_CONTACTO, ");
            query.append("       tlb.NO_EMAIL_CONTACTO             tlb_NO_EMAIL_CONTACTO, ");
            query.append("       tlb.IN_LAB_DROG                   tlb_IN_LAB_DROG, ");
            query.append("       tlb.DE_CIUDAD                     tlb_DE_CIUDAD, ");
            query.append("       tlb.NU_FAX_LABORATORIO            tlb_NU_FAX_LABORATORIO, ");
            query.append("       tlb.NU_MOVIL_CONTACTO             tlb_NU_MOVIL_CONTACTO, ");
            query.append("       tlb.TS_DIAS                       tlb_TS_DIAS, ");
            query.append("       tlb.TR_DIAS                       tlb_TR_DIAS, ");
            query.append("       tlb.NU_MIN_DIAS_REPOSICION        tlb_NU_MIN_DIAS_REPOSICION, ");
            query.append("       tlb.NU_MAX_DIAS_REPOSICION        tlb_NU_MAX_DIAS_REPOSICION, ");
            query.append("       tlb.NU_DIAS_ROTACION_PROMEDIO     tlb_NU_DIAS_ROTACION_PROMEDIO, ");
            query.append("       tps.CO_PAIS                       tps_CO_PAIS, ");
            query.append("       tps.NU_ORDEN_FILA                 tps_NU_ORDEN_FILA, ");
            query.append("       tps.DE_CORTA_PAIS                 tps_DE_CORTA_PAIS, ");
            query.append("       tps.DE_PAIS                       tps_DE_PAIS, ");
            query.append("       tps.ES_PAIS                       tps_ES_PAIS, ");
            query.append("       tps.ID_CREA_PAIS                  tps_ID_CREA_PAIS, ");
            query.append("       tps.FE_CREA_PAIS                  tps_FE_CREA_PAIS, ");
            query.append("       tps.ID_MOD_PAIS                   tps_ID_MOD_PAIS, ");
            query.append("       tps.FE_MOD_PAIS                   tps_FE_MOD_PAIS, ");
            query.append("       timp.CO_COMPANIA                  timp_CO_COMPANIA, ");
            query.append("       timp.CO_IMPUESTO                  timp_CO_IMPUESTO, ");
            query.append("       timp.NU_ORDEN_FILA                timp_NU_ORDEN_FILA, ");
            query.append("       timp.DE_CORTA_IMPUESTO            timp_DE_CORTA_IMPUESTO, ");
            query.append("       timp.DE_IMPUESTO                  timp_DE_IMPUESTO, ");
            query.append("       timp.PC_IMPUESTO                  timp_PC_IMPUESTO, ");
            query.append("       timp.IN_OPERACION_IMPUESTO        timp_IN_OPERACION_IMPUESTO, ");
            query.append("       timp.ES_IMPUESTO                  timp_ES_IMPUESTO, ");
            query.append("       timp.ID_CREA_IMPUESTO             timp_ID_CREA_IMPUESTO, ");
            query.append("       timp.FE_CREA_IMPUESTO             timp_FE_CREA_IMPUESTO, ");
            query.append("       timp.ID_MOD_IMPUESTO              timp_ID_MOD_IMPUESTO, ");
            query.append("       timp.FE_MOD_IMPUESTO              timp_FE_MOD_IMPUESTO  ");
            query.append("  FROM lgtm_producto tmp ");
            query.append(" INNER JOIN lgtr_producto_local tpl      ON tmp.co_compania = tpl.co_compania ");
            query.append("                                        and tmp.co_producto = tpl.co_producto ");
            query.append("  LEFT JOIN LGTR_LINEA_PROD_ERP tg1      ON tg1.CO_NIVEL_SUPERIOR = 1 ");
            query.append("                                        and tg1.co_nivel_01 = tmp.Co_Nivel_01 ");
            query.append("  LEFT JOIN LGTR_GRUPO_PROD_ERP tg2      ON tg2.CO_NIVEL_SUPERIOR = tmp.Co_Nivel_01 ");
            query.append("                                        and tg2.co_nivel_02 = tmp.Co_Nivel_02 ");
            query.append("  LEFT JOIN LGTR_GRUPO_ANATOMICO tg3     ON tg3.CO_NIVEL_SUPERIOR = tmp.Co_Nivel_02 ");
            query.append("                                        and tg3.co_nivel_03 = tmp.Co_Nivel_03 ");
            query.append("  LEFT JOIN LGTR_GRUPO_TERAPEUTICO tg4   ON tg4.CO_NIVEL_SUPERIOR = tmp.Co_Nivel_03 ");
            query.append("                                        and tg4.co_nivel_04 = tmp.Co_Nivel_04 ");
            query.append("  LEFT JOIN LGTR_ACCION_TERAPEUTICA tg5  ON tg5.CO_NIVEL_SUPERIOR = tmp.Co_Nivel_04 ");
            query.append("                                        and tg5.co_nivel_05 = tmp.Co_Nivel_05 ");
            query.append("  LEFT JOIN CMTR_LABORATORIO tlb         ON tmp.co_compania    = tlb.co_compania ");
            query.append("                                        and tmp.co_laboratorio = tlb.co_laboratorio ");
            query.append("  LEFT JOIN CMTR_PAIS tps                ON tmp.CO_PROCEDENCIA = tps.co_pais ");
            query.append("  LEFT JOIN VTTR_IMPUESTO timp           ON tmp.co_compania    = timp.co_compania ");
            query.append("                                        and tmp.co_impuesto_1  = timp.co_impuesto ");
            query.append(" where tpl.CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("   and tpl.co_local    = '").append(AtuxVariables.vCodigoLocal).append("'\n");

            if ("A".equals(Filtro) || "I".equals(Filtro)){
                query.append("   AND tmp.ES_PRODUCTO = '").append(Filtro).append("' ");
            }

            query.append(" ORDER BY tmp.DE_PRODUCTO ");

//            }else if (!"T".equals(Filtro)){
//                query.append(Filtro);
//            }
//
//
//
//            query.append("select T1.*, T2.DE_LABORATORIO  ");
//            query.append("  from LGTM_PRODUCTO T1, ");
//            query.append("       CMTR_LABORATORIO T2 ");
//            query.append(" WHERE T1.CO_COMPANIA = T2.CO_COMPANIA ");
//            query.append("   AND T1.CO_LABORATORIO = T2.CO_LABORATORIO ");
//            query.append("   AND T1.CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'\n");
//            //query.append("   AND T1.CO_LABORATORIO IN ('0014','0100') ");
//
//            if ("A".equals(Filtro) || "I".equals(Filtro)){
//                query.append("   AND T1.ES_PRODUCTO = '").append(Filtro).append("' ");
//            }else if (!"T".equals(Filtro)){
//                query.append(Filtro);
//            }

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next()){
                prod = new ProductoFull();
                prod.setPrimaryKey(new String[]{rs.getString("tmp_CO_COMPANIA"),rs.getString("tmp_CO_PRODUCTO"),rs.getString("tmp_NU_REVISION_PRODUCTO")});
                prod.setTmpCoCompania(rs.getString("tmp_CO_COMPANIA"));
                prod.setTmpCoProducto(rs.getString("tmp_CO_PRODUCTO"));
                prod.setTmpNuRevisionProducto(rs.getString("tmp_NU_REVISION_PRODUCTO"));
                prod.setTmpDeProducto(rs.getString("tmp_DE_PRODUCTO"));
                prod.setTmpCoMoneda(rs.getString("tmp_CO_MONEDA"));
                prod.setTmpCoImpuesto1(rs.getString("tmp_CO_IMPUESTO_1"));
                prod.setTmpCoLaboratorio(rs.getString("tmp_CO_LABORATORIO"));
                prod.setTmpCoUnidadMedida(rs.getString("tmp_CO_UNIDAD_MEDIDA"));
                prod.setTmpVaUnidadMedida(rs.getInt("tmp_VA_UNIDAD_MEDIDA"));
                prod.setTmpVaUnidadContenido(rs.getInt("tmp_VA_UNIDAD_CONTENIDO"));
                prod.setTmpInProdFraccionable(rs.getString("tmp_IN_PROD_FRACCIONABLE"));
                prod.setTmpVaPrecioCompra(rs.getDouble("tmp_VA_PRECIO_COMPRA"));
                prod.setTmpVaPrecioPromedio(rs.getDouble("tmp_VA_PRECIO_PROMEDIO"));
                prod.setTmpVaBono(rs.getDouble("tmp_VA_BONO"));
                prod.setTmpDeUnidadProducto(rs.getString("tmp_DE_UNIDAD_PRODUCTO"));
                prod.setTmpInRecetaMedica(rs.getString("tmp_IN_RECETA_MEDICA"));
                prod.setTmpEsProducto(rs.getString("tmp_ES_PRODUCTO"));
                prod.setTmpIdCreaProducto(rs.getString("tmp_ID_CREA_PRODUCTO"));
                prod.setTmpFeCreaProducto(rs.getDate("tmp_FE_CREA_PRODUCTO"));
                prod.setTmpIdModProducto(rs.getString("tmp_ID_MOD_PRODUCTO"));
                prod.setTmpFeModProducto(rs.getDate("tmp_FE_MOD_PRODUCTO"));
                prod.setTmpPcDescuentoBase(rs.getDouble("tmp_PC_DESCUENTO_BASE"));
                prod.setTmpVaCostoProducto(rs.getDouble("tmp_VA_COSTO_PRODUCTO"));
                prod.setTmpVaPrecioPublico(rs.getDouble("tmp_VA_PRECIO_PUBLICO"));
                prod.setTmpInControlado(rs.getString("tmp_IN_CONTROLADO"));
                prod.setTmpCoProductoBk(rs.getString("tmp_CO_PRODUCTO_BK"));
                prod.setTmpCoNivel01(rs.getString("tmp_CO_NIVEL_01"));
                prod.setTmpCoNivel02(rs.getString("tmp_CO_NIVEL_02"));
                prod.setTmpCoNivel03(rs.getString("tmp_CO_NIVEL_03"));
                prod.setTmpCoNivel04(rs.getString("tmp_CO_NIVEL_04"));
                prod.setTmpCoNivel05(rs.getString("tmp_CO_NIVEL_05"));
                prod.setTmpCoProcedencia(rs.getString("tmp_CO_PROCEDENCIA"));
                prod.setTmpFeVigencia(rs.getDate("tmp_FE_VIGENCIA"));
                prod.setTmpInControlLote(rs.getString("tmp_IN_CONTROL_LOTE"));
                prod.setTmpMinFracc(rs.getString("tmp_MIN_FRACC"));
                prod.setTmpMaxFracc(rs.getString("tmp_MAX_FRACC"));
                prod.setTmpInPideMedico(rs.getString("tmp_IN_PIDE_MEDICO"));
                prod.setTmpCaEmpaqueMax(rs.getInt("tmp_CA_EMPAQUE_MAX"));
                prod.setTplCoCompania(rs.getString("tpl_CO_COMPANIA"));
                prod.setTplCoLocal(rs.getString("tpl_CO_LOCAL"));
                prod.setTplCoProducto(rs.getString("tpl_CO_PRODUCTO"));
                prod.setTplNuRevisionProducto(rs.getString("tpl_NU_REVISION_PRODUCTO"));
                prod.setTplVaVenta(rs.getDouble("tpl_VA_VENTA"));
                prod.setTplCoMoneda(rs.getString("tpl_CO_MONEDA"));
                prod.setTplVaFraccion(rs.getInt("tpl_VA_FRACCION"));
                prod.setTplInProdFraccionado(rs.getString("tpl_IN_PROD_FRACCIONADO"));
                prod.setTplCaStockDisponible(rs.getInt("tpl_CA_STOCK_DISPONIBLE"));
                prod.setTplCaStockTransito(rs.getInt("tpl_CA_STOCK_TRANSITO"));
                prod.setTplCaStockComprometido(rs.getInt("tpl_CA_STOCK_COMPROMETIDO"));
                prod.setTplCaStockMinimo(rs.getInt("tpl_CA_STOCK_MINIMO"));
                prod.setTplCaStockMaximo(rs.getInt("tpl_CA_STOCK_MAXIMO"));
                prod.setTplPcDescuento1(rs.getDouble("tpl_PC_DESCUENTO_1"));
                prod.setTplDeUnidadFraccion(rs.getString("tpl_DE_UNIDAD_FRACCION"));
                prod.setTplEsProdLocal(rs.getString("tpl_ES_PROD_LOCAL"));
                prod.setTplIdCreaProdLocal(rs.getString("tpl_ID_CREA_PROD_LOCAL"));
                prod.setTplFeCreaProdLocal(rs.getDate("tpl_FE_CREA_PROD_LOCAL"));
                prod.setTplIdModProdLocal(rs.getString("tpl_ID_MOD_PROD_LOCAL"));
                prod.setTplFeModProdLocal(rs.getDate("tpl_FE_MOD_PROD_LOCAL"));
                prod.setTplCoProductoBk(rs.getString("tpl_CO_PRODUCTO_BK"));
                prod.setTplCoProducto2(rs.getString("tpl_CO_PRODUCTO2"));
                prod.setTg1DeLineaProdErp(rs.getString("tg1_DE_LINEA_PROD_ERP"));
                prod.setTg1CoNivel01(rs.getString("tg1_CO_NIVEL_01"));
                prod.setTg1CoNivelSuperior(rs.getString("tg1_CO_NIVEL_SUPERIOR"));
                prod.setTg2DeGrupoProdErp(rs.getString("tg2_DE_GRUPO_PROD_ERP"));
                prod.setTg2CoNivel02(rs.getString("tg2_CO_NIVEL_02"));
                prod.setTg2CoNivelSuperior(rs.getString("tg2_CO_NIVEL_SUPERIOR"));
                prod.setTg3DeGrupoAnatomico(rs.getString("tg3_DE_GRUPO_ANATOMICO"));
                prod.setTg3CoNivel03(rs.getString("tg3_CO_NIVEL_03"));
                prod.setTg3CoNivelSuperior(rs.getString("tg3_CO_NIVEL_SUPERIOR"));
                prod.setTg4DeGrupoTerapeutico(rs.getString("tg4_DE_GRUPO_TERAPEUTICO"));
                prod.setTg4CoNivel04(rs.getString("tg4_CO_NIVEL_04"));
                prod.setTg4CoNivelSuperior(rs.getString("tg4_CO_NIVEL_SUPERIOR"));
                prod.setTg5DeAccionTerapeutica(rs.getString("tg5_DE_ACCION_TERAPEUTICA"));
                prod.setTg5CoNivel05(rs.getString("tg5_CO_NIVEL_05"));
                prod.setTg5CoNivelSuperior(rs.getString("tg5_CO_NIVEL_SUPERIOR"));
                prod.setTlbCoLaboratorio(rs.getString("tlb_CO_LABORATORIO"));
                prod.setTlbDeLaboratorio(rs.getString("tlb_DE_LABORATORIO"));
                prod.setTlbInLabInventario(rs.getString("tlb_IN_LAB_INVENTARIO"));
                prod.setTpsCoPais(rs.getString("tps_CO_PAIS"));
                prod.setTpsDePais(rs.getString("tps_DE_PAIS"));
                prod.setTimpCoImpuesto(rs.getString("timp_CO_IMPUESTO"));
                prod.setTimpDeCortaImpuesto(rs.getString("timp_DE_CORTA_IMPUESTO"));
                prod.setTimpDeImpuesto(rs.getString("timp_DE_IMPUESTO"));
                prod.setTimpPcImpuesto(rs.getDouble("timp_PC_IMPUESTO"));

                rgs.add(prod);
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




    public ArrayList getProductosComplementarios(String coNivel05)
    {
        ArrayList<Producto> rgs = new ArrayList<Producto>();                
        Producto      prod      = null;        
        StringBuffer  query;
        try {            
            query = new StringBuffer();
            query.append("SELECT Z.CO_COMPANIA , ");
            query.append("       Z.CO_PRODUCTO , ");
            query.append("       Z.NU_REVISION_PRODUCTO , ");
            query.append("       Z.DE_PRODUCTO , ");
            query.append("       Z.DE_UNIDAD_PRODUCTO, ");
            query.append("       Z.VA_VENTA, ");
            query.append("       Z.VA_PRECIO_PUBLICO, ");
            query.append("       Z.CA_STOCK, ");
            query.append("       Z.VA_BONO, ");
            query.append("       Z.DE_LABORATORIO, ");
            query.append("       Z.DESCUENTO, ");
            query.append("       Z.ORDEN, ");
            query.append("       Z.CLASE, ");
            query.append("       Z.IN_PROD_FRACCIONADO ");
            query.append("       FROM (SELECT PRODUCTO.CO_COMPANIA, ");
            query.append("                    PRODUCTO.CO_PRODUCTO CO_PRODUCTO, ");
            query.append("                    PRODUCTO.NU_REVISION_PRODUCTO,    ");
            query.append("                    NVL(PRODUCTO.DE_PRODUCTO ,' ') DE_PRODUCTO, ");
            query.append("                    DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,'S',NVL(PRODUCTO_LOCAL.DE_UNIDAD_FRACCION,' '),NVL(PRODUCTO.DE_UNIDAD_PRODUCTO,' ')) DE_UNIDAD_PRODUCTO, ");
            query.append("                    DECODE(PRODUCTO_LOCAL.VA_VENTA,NULL,0,PRODUCTO_LOCAL.VA_VENTA) VA_VENTA, ");
            query.append("                    (DECODE(PRODUCTO_LOCAL.VA_VENTA,NULL,0,PRODUCTO_LOCAL.VA_VENTA) - DECODE(PRODUCTO_LOCAL.VA_VENTA,NULL,0,PRODUCTO_LOCAL.VA_VENTA)*(1-(1-(DECODE(PRODUCTO_LOCAL.PC_DESCUENTO_1,NULL,0,PRODUCTO_LOCAL.PC_DESCUENTO_1)/100))*(1-(DECODE(PRODUCTO_LOCAL.PC_DESCUENTO_2,NULL,0,PRODUCTO_LOCAL.PC_DESCUENTO_2)/100))*(1-(DECODE(PRODUCTO_LOCAL.PC_DESCUENTO_3,NULL,0,PRODUCTO_LOCAL.PC_DESCUENTO_3)/100)))) VA_PRECIO_PUBLICO, ");
            query.append("                    NVL(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,0)-NVL(PRODUCTO_LOCAL.CA_STOCK_COMPROMETIDO,0) CA_STOCK, ");
            query.append("                    DECODE(PRODUCTO.VA_BONO,NULL,0,PRODUCTO.VA_BONO / DECODE(PRODUCTO_LOCAL.VA_FRACCION,NULL,1,0,1,PRODUCTO_LOCAL.VA_FRACCION)) VA_BONO, ");
            query.append("                    NVL(LABORATORIO.DE_LABORATORIO,' ') DE_LABORATORIO, ");
            query.append("                    ((1-(1-(DECODE(PRODUCTO_LOCAL.PC_DESCUENTO_1,NULL,0,PRODUCTO_LOCAL.PC_DESCUENTO_1)/100))*(1-(DECODE(PRODUCTO_LOCAL.PC_DESCUENTO_2,NULL,0,PRODUCTO_LOCAL.PC_DESCUENTO_2)/100))*(1-(DECODE(PRODUCTO_LOCAL.PC_DESCUENTO_3,NULL,0,PRODUCTO_LOCAL.PC_DESCUENTO_3)/100))) * 100) DESCUENTO, ");
            query.append("                    NVL(LABORATORIO.IN_LAB_PROPIO,'N') || ");
            query.append("                    DECODE(PRODUCTO.VA_BONO,NULL,'000.00',TO_CHAR(PRODUCTO.VA_BONO / DECODE(PRODUCTO_LOCAL.VA_FRACCION,NULL,1,0,1,PRODUCTO_LOCAL.VA_FRACCION),'000.00')) || ");
            query.append("                    TO_CHAR(DECODE(PRODUCTO_LOCAL.VA_VENTA,NULL,0,PRODUCTO_LOCAL.VA_VENTA) - DECODE(PRODUCTO_LOCAL.VA_VENTA,NULL,0,PRODUCTO_LOCAL.VA_VENTA)*(1-(1-(DECODE(PRODUCTO_LOCAL.PC_DESCUENTO_1,NULL,0,PRODUCTO_LOCAL.PC_DESCUENTO_1)/100))*(1-(DECODE(PRODUCTO_LOCAL.PC_DESCUENTO_2,NULL,0,PRODUCTO_LOCAL.PC_DESCUENTO_2)/100))*(1-(DECODE(PRODUCTO_LOCAL.PC_DESCUENTO_3,NULL,0,PRODUCTO_LOCAL.PC_DESCUENTO_3)/100))),'000.00') ORDEN, ");
            query.append("                    DECODE(PRODUCTO.VA_BONO,NULL,0,PRODUCTO.VA_BONO) CLASE, ");
            query.append("                    PRODUCTO_LOCAL.IN_PROD_FRACCIONADO IN_PROD_FRACCIONADO, ");
            query.append("                    CASE ");
            query.append("                       WHEN PRODUCTO.CO_GRUPO_EXT = 'LAM-MA' THEN 1 ");
            query.append("                       WHEN PRODUCTO.CO_GRUPO_EXT = 'LAM-MP' THEN 1 ");
            query.append("                       WHEN PRODUCTO.CO_GRUPO_EXT = 'GRUPO A' THEN 2 ");
            query.append("                    ELSE 3 ");
            query.append("                    END AS LAM, ");
            query.append("                    CASE ");
            query.append("                       WHEN NVL(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,0) > 0 THEN 1 ");
            query.append("                    ELSE 0 ");
            query.append("                    END AS TIENE_STOCK ");
            query.append("                  FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL, ");
            query.append("                       LGTM_PRODUCTO PRODUCTO, ");
            query.append("                       CMTR_LABORATORIO LABORATORIO ");
            query.append("                 WHERE PRODUCTO_LOCAL.CO_COMPANIA  = PRODUCTO.CO_COMPANIA ");
            query.append("                   AND PRODUCTO_LOCAL.CO_LOCAL     ='").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                   AND PRODUCTO_LOCAL.CO_PRODUCTO  = PRODUCTO.CO_PRODUCTO ");
            query.append("                   AND PRODUCTO.CO_COMPANIA        = LABORATORIO.CO_COMPANIA(+) ");
            query.append("                   AND PRODUCTO.CO_NIVEL_05        = '").append(coNivel05).append("'");
            query.append("                   AND PRODUCTO.CO_LABORATORIO     = LABORATORIO.CO_LABORATORIO(+)");
            query.append("                   AND PRODUCTO_LOCAL.ES_PROD_LOCAL= 'A' ");
            query.append("                   ) Z ");
            query.append("         ORDER BY Z.TIENE_STOCK DESC, Z.LAM ASC, Z.VA_BONO DESC");               
            
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                   prod = new Producto();
                   prod.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
                   prod.setCoCompania(rs.getString(1));
                   prod.setCoProducto(rs.getString(2));
                   prod.setNuRevisionProducto(rs.getString(3)); 
                   prod.setDeProducto(rs.getString(4));
                   prod.setDeUnidadProducto(rs.getString(5));
                   prod.setVaVenta(rs.getDouble(6));
                   prod.setVaPrecioPublico(rs.getDouble(7));
                   prod.setCaStockDisponible(rs.getInt(8));
                   prod.setVaBono(rs.getDouble(9));
                   prod.setDeLaboratorio(rs.getString(10));
                   prod.setPcDescuento(rs.getInt(11));
                   prod.setOrden(rs.getString(12));
                   prod.setClase(rs.getString(13));
                   prod.setInProdFraccionado(rs.getString(14));                  
                   
                 rgs.add(prod);
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
    
    public String getNuevoCodigo(){
        String codigo="";
        try {
            return AtuxDBUtility.getValueAt(Producto.nt,"rtrim(ltrim(to_char(max(co_producto) + 1,'000000')))"," co_compania = '" + AtuxVariables.vCodigoCompania + "' and nu_revision_producto =0");
        } catch (SQLException ex) {
            Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigo.trim();
    }

    public ArrayList getProductosMaestrosFull(Producto producto, ProductoLocal productoLocal, ProductoFull productoFull) {
        ArrayList<ProductoFull> rgs = new ArrayList<ProductoFull>();
        ProductoFull prod      = null;

        prod = new ProductoFull();

        prod.setPrimaryKey(new String[]{producto.getCoCompania(),producto.getCoProducto(),producto.getNuRevisionProducto()});
        prod.setTmpCoCompania(producto.getCoCompania());
        prod.setTmpCoProducto(producto.getCoProducto());
        prod.setTmpNuRevisionProducto(producto.getNuRevisionProducto());
        prod.setTmpDeProducto(producto.getDeProducto());
        prod.setTmpCoMoneda(producto.getCoMoneda());
        prod.setTmpCoImpuesto1(producto.getCoImpuesto1());
        prod.setTmpCoLaboratorio(producto.getCoLaboratorio());
        prod.setTmpCoUnidadMedida(producto.getCoUnidadMedida());
        prod.setTmpVaUnidadMedida(producto.getVaUnidadMedida());
        prod.setTmpVaUnidadContenido(producto.getVaUnidadContenido());
        prod.setTmpInProdFraccionable(producto.getInProdFraccionable());
        prod.setTmpVaPrecioCompra(producto.getVaPrecioCompra());
        prod.setTmpVaPrecioPromedio(producto.getVaPrecioPromedio());
        prod.setTmpVaBono(producto.getVaBono());
        prod.setTmpDeUnidadProducto(producto.getDeUnidadProducto());
        prod.setTmpInRecetaMedica(producto.getInRecetaMedica());
        prod.setTmpEsProducto(producto.getEsProducto());
        prod.setTmpIdCreaProducto(producto.getIdCreaProducto());
        prod.setTmpFeCreaProducto(producto.getFeCreaProducto());
        prod.setTmpIdModProducto(producto.getIdModProducto());
        prod.setTmpFeModProducto(producto.getFeModProducto());
        prod.setTmpPcDescuentoBase(productoFull.getTmpPcDescuentoBase());
        prod.setTmpVaCostoProducto(producto.getVaCostoProducto());
        prod.setTmpVaPrecioPublico(producto.getVaPrecioPublico());
        prod.setTmpInControlado(producto.getInControlado());
        prod.setTmpCoProductoBk(productoFull.getTmpCoProductoBk());
        prod.setTmpCoNivel01(producto.getCoNivel01());
        prod.setTmpCoNivel02(producto.getCoNivel02());
        prod.setTmpCoNivel03(producto.getCoNivel03());
        prod.setTmpCoNivel04(producto.getCoNivel04());
        prod.setTmpCoNivel05(producto.getCoNivel05());
        prod.setTmpCoProcedencia(producto.getCoProcedencia());
        prod.setTmpFeVigencia(producto.getFeVigencia());
        prod.setTmpInControlLote(producto.getInControlLote());
        prod.setTmpMinFracc(productoFull.getTmpMinFracc());
        prod.setTmpMaxFracc(productoFull.getTmpMaxFracc());
        prod.setTmpInPideMedico(producto.getInPideMedico());
        prod.setTmpCaEmpaqueMax(productoFull.getTmpCaEmpaqueMax());
        prod.setTplCoCompania(productoLocal.getCoCompania());
        prod.setTplCoLocal(productoLocal.getCoLocal());
        prod.setTplCoProducto(productoLocal.getCoProducto());
        prod.setTplNuRevisionProducto(productoLocal.getNuRevisionProducto());
        prod.setTplVaVenta(productoLocal.getVaVenta());
        prod.setTplCoMoneda(productoLocal.getCoMoneda());
        prod.setTplVaFraccion(productoLocal.getVaFraccion());
        prod.setTplInProdFraccionado(productoLocal.getInProdFraccionado());
        prod.setTplCaStockDisponible(productoLocal.getCaStockDisponible());
        prod.setTplCaStockTransito(productoLocal.getCaStockTransito());
        prod.setTplCaStockComprometido(productoLocal.getCaStockComprometido());
        prod.setTplCaStockMinimo(productoLocal.getCaStockMinimo());
        prod.setTplCaStockMaximo(productoLocal.getCaStockMaximo());
        prod.setTplPcDescuento1(productoLocal.getPcDescuento1());
        prod.setTplDeUnidadFraccion(productoLocal.getDeUnidadFraccion());
        prod.setTplEsProdLocal(productoLocal.getEsProdLocal());
        prod.setTplIdCreaProdLocal(productoLocal.getIdCreaProdLocal());
        prod.setTplFeCreaProdLocal(productoLocal.getFeCreaProdLocal());
        prod.setTplIdModProdLocal(productoLocal.getIdModProdLocal());
        prod.setTplFeModProdLocal(productoLocal.getFeModProdLocal());
        prod.setTplCoProductoBk(productoFull.getTplCoProductoBk());
        prod.setTplCoProducto2(productoFull.getTplCoProducto2());

        prod.setTg1DeLineaProdErp(productoFull.getTg1DeLineaProdErp());
        prod.setTg1CoNivel01(productoFull.getTg1CoNivel01());
        prod.setTg1CoNivelSuperior(productoFull.getTg1CoNivelSuperior());
        prod.setTg2DeGrupoProdErp(productoFull.getTg2DeGrupoProdErp());
        prod.setTg2CoNivel02(productoFull.getTg2CoNivel02());
        prod.setTg2CoNivelSuperior(productoFull.getTg2CoNivelSuperior());
        prod.setTg3DeGrupoAnatomico(productoFull.getTg3DeGrupoAnatomico());
        prod.setTg3CoNivel03(productoFull.getTg3CoNivel03());
        prod.setTg3CoNivelSuperior(productoFull.getTg3CoNivelSuperior());
        prod.setTg4DeGrupoTerapeutico(productoFull.getTg4DeGrupoTerapeutico());
        prod.setTg4CoNivel04(productoFull.getTg4CoNivel04());
        prod.setTg4CoNivelSuperior(productoFull.getTg4CoNivelSuperior());
        prod.setTg5DeAccionTerapeutica(productoFull.getTg5DeAccionTerapeutica());
        prod.setTg5CoNivel05(productoFull.getTg5CoNivel05());
        prod.setTg5CoNivelSuperior(productoFull.getTg5CoNivelSuperior());
        prod.setTlbCoLaboratorio(productoFull.getTlbCoLaboratorio());
        prod.setTlbDeLaboratorio(productoFull.getTlbDeLaboratorio());
        prod.setTlbInLabInventario(productoFull.getTlbInLabInventario());
        prod.setTpsCoPais(productoFull.getTpsCoPais());
        prod.setTpsDePais(productoFull.getTpsDePais());
        prod.setTimpCoImpuesto(productoFull.getTimpCoImpuesto());
        prod.setTimpDeCortaImpuesto(productoFull.getTimpDeCortaImpuesto());
        prod.setTimpDeImpuesto(productoFull.getTimpDeImpuesto());
        prod.setTimpPcImpuesto(productoFull.getTimpPcImpuesto());

        rgs.add(prod);
        return rgs;
    }
}