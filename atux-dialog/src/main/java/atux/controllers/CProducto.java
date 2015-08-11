package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.managerbd.BaseConexion;
import atux.modelbd.Laboratorio;
import atux.modelbd.Producto;
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
            query.append("   AND T1.CO_LABORATORIO IN ('0014','0100') ");

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
}