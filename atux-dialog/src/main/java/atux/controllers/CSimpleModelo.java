
package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.*;
import atux.util.common.AtuxVariables;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;


public class CSimpleModelo extends JAbstractController implements Serializable{

    public SimpleModelo sm;
    public static final int OCLASE = SimpleModelo.OCLASE;
    public static final int OMARCA= SimpleModelo.OMARCA;
    public static final int OMODELO= SimpleModelo.OMODELO;
    private int tipoModelo;
    private FormaPago     formaPago;
    private TipoPersona   tipoPersona;
    private CajaPago      cajaPago;
    private FormaPagoPedidoCredito pedCredito;
    private ProductoLocal prodLocal;
    private ListaPedidosReposicion pedRep;

    public CSimpleModelo(int tipoModelo) {
        this.tipoModelo = tipoModelo;
        sm = new SimpleModelo(tipoModelo);

    }

    public CSimpleModelo() {
    }

    @Override
    public ArrayList<SimpleModelo> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<SimpleModelo> rgs = new ArrayList<SimpleModelo>();
        try {

            if(id != null)
            {
                this.numRegistros = this.getNumeroRegistros(sm.getNombreTabla(), sm.getCampoExistencial(), sm.getCampoExistencial(), id);
            }else
            {
                this.numRegistros = this.getNumeroRegistros(sm.getNombreTabla(), sm.getCampoExistencial());
                if(rs.isClosed())
                {
                    System.out.println("resultset esta cerrado...");
                }
            }
            rs = this.getRegistros(sm.getNombreTabla(),campos,columnaId,id,null);
            if(this.numRegistros<finalPag)
            {
                finalPag =  this.numRegistros;
            }
            if(finalPag>inicioPag)
            {
                inicioPag -= (finalPag-inicioPag)-1;
            }
            while(rs.next())
            {
                sm = new SimpleModelo(tipoModelo);
                sm.setPrimaryKey(new String[]{rs.getString(1)});
                sm.setNombre(rs.getString(2));
                sm.setActivo(rs.getInt(3));
                rgs.add(sm);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;

    }

    public ArrayList<SimpleModelo> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }

    @Override
    public ArrayList getRegistros(Object[] cvl) {
        return this.getRegistros(new String[]{}, cvl!=null?new String[]{SimpleModelo.ACTIVO}:null,cvl);
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

    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        sm = (SimpleModelo)mdl;
        int gravado = 0;
        String campos = "nombre, activo";
        Object[] valores = {sm.getNombre(),sm.getActivo()};

        gravado = this.agregarRegistroPs(sm.getNombreTabla(), this.stringToArray(campos, ","), valores);

        if(gravado==1)
            return true;

        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        sm = (SimpleModelo)mdl;
        int gravado = 0;
        String campos = "nombre, activo";
        Object[] valores = {sm.getNombre(),sm.getActivo(),sm.getPrimaryKey()};

        gravado = this.actualizarRegistroPs(sm.getNombreTabla(), this.adjuntarSimbolo(campos, ",", "?")+Ex.WHERE+mdl.getCampoClavePrimaria()+" = ? ", valores);
        System.out.println("grabadoo "+gravado);
        return gravado;
    }

    @Override
    public ArrayList<FormaPago> getRegistros() {
        ArrayList<FormaPago> rgs = new ArrayList<FormaPago>();
        try {
            rs = this.getRegistros(FormaPago.nt,FormaPago.FULL_NOM_CAMPOS);
            while(rs.next())
            {
                formaPago = new FormaPago();
                formaPago.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2)});
                formaPago.setCoCompania(rs.getString(1));
                formaPago.setCoFormaPago(rs.getString(2));
                formaPago.setNuOrdenFila(rs.getInt(3));
                formaPago.setDeCortaFormaPago(rs.getString(4));
                formaPago.setDeFormaPago(rs.getString(5));
                rgs.add(formaPago);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public ArrayList getFormasPago()
    {
        ArrayList<FormaPago> rgs = new ArrayList<FormaPago>();
        try {
            StringBuffer  query = new StringBuffer();
            query.append("SELECT CO_COMPANIA             , ");
            query.append("       CO_FORMA_PAGO           , ");
            query.append("       DE_CORTA_FORMA_PAGO       ");
            query.append("  FROM (SELECT FORMA.CO_COMPANIA, ");
            query.append("               NVL(FORMA_LOCAL.CO_FORMA_PAGO,' ')  CO_FORMA_PAGO, ");
            query.append("               NVL(FORMA.DE_CORTA_FORMA_PAGO,' ')  DE_CORTA_FORMA_PAGO, ");
            query.append("               NU_ORDEN_FILA ");
            query.append("          FROM VTTR_FORMA_PAGO FORMA, ");
            query.append("                    VTTR_FORMA_PAGO_LOCAL FORMA_LOCAL ");
            query.append("         WHERE FORMA_LOCAL.CO_COMPANIA  = '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("           AND FORMA_LOCAL.CO_LOCAL     = '").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("           AND FORMA_LOCAL.ES_FORMA_PAGO= 'A' ");
            query.append("           AND FORMA_LOCAL.CO_COMPANIA = FORMA.CO_COMPANIA ");
            query.append("           AND FORMA_LOCAL.CO_FORMA_PAGO = FORMA.CO_FORMA_PAGO ");
            query.append("           AND FORMA.PC_DESCUENTO_ESPECIAL = 0.00 ");
            query.append("           AND FORMA.ES_FORMA_PAGO = 'A' ");
            //query.append("           AND FORMA.CO_FORMA_PAGO NOT IN (SELECT CO_FORMA_PAGO ");
            //query.append("                                             FROM CVTM_CONVENIO ");
            //query.append("                                            WHERE CO_FORMA_PAGO IS NOT NULL) ");
            query.append("           AND FORMA.CO_FORMA_PAGO_PADRE IS NULL ");
//            query.append("        UNION ");
//            query.append("        SELECT FORMA.CO_COMPANIA, ");
//            query.append("                    NVL(FORMA_LOCAL.CO_FORMA_PAGO,' ') CO_FORMA_PAGO, ");
//            query.append("                    NVL(FORMA.DE_CORTA_FORMA_PAGO,' ') DE_CORTA_FORMA_PAGO, ");
//            query.append("                    NU_ORDEN_FILA ");
//            query.append("          FROM VTTR_FORMA_PAGO FORMA, ");
//            query.append("               VTTR_FORMA_PAGO_LOCAL FORMA_LOCAL ");
//            query.append("         WHERE FORMA_LOCAL.CO_COMPANIA   = '").append(AtuxVariables.vCodigoCompania).append("'");
//            query.append("           AND FORMA_LOCAL.CO_LOCAL      = '").append(AtuxVariables.vCodigoLocal).append("'");
//            query.append("           AND FORMA_LOCAL.ES_FORMA_PAGO = 'A' ");
//            query.append("           AND FORMA_LOCAL.CO_COMPANIA = FORMA.CO_COMPANIA ");
//            query.append("           AND FORMA_LOCAL.CO_FORMA_PAGO = FORMA.CO_FORMA_PAGO ");
//            query.append("           AND FORMA.ES_FORMA_PAGO = 'A' ");            
//            query.append("           AND FORMA.CO_FORMA_PAGO = FORMA.CO_FORMA_PAGO_PADRE");            
            query.append("           ) ");
            query.append(" ORDER BY NU_ORDEN_FILA");
            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                formaPago = new FormaPago();
                formaPago.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2)});
                formaPago.setCoCompania(rs.getString(1));
                formaPago.setCoFormaPago(rs.getString(2));
                formaPago.setDeCortaFormaPago(rs.getString(3));

                rgs.add(formaPago);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public ArrayList getTipoPersona() {
        ArrayList<TipoPersona> rgs = new ArrayList<TipoPersona>();
        try {

            rs =  this.getRegistros(TipoPersona.nt,TipoPersona.FULL_NOM_CAMPOS);

            while(rs.next())
            {
                tipoPersona = new TipoPersona();
                tipoPersona.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2)});
                tipoPersona.setCoCompania(rs.getString(1));
                tipoPersona.setTiCliente(rs.getString(2));
                tipoPersona.setDeCortaTipoCliente(rs.getString(3));
                tipoPersona.setDeTipoCliente(rs.getString(3));
                tipoPersona.setTiPantalla(rs.getString(3));
                tipoPersona.setEsTipoCliente(rs.getString(3));

                rgs.add(tipoPersona);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public ArrayList getCajaPago() {
        ArrayList<CajaPago> rgs = new ArrayList<CajaPago>();
        try {

            rs =  this.getRegistros(CajaPago.nt,CajaPago.FULL_NOM_CAMPOS,new String[]{CajaPago.COLUMNA_ACTIVO},new Object[]{"A"},new String[]{"NU_CAJA"});

            while(rs.next())
            {
                cajaPago = new CajaPago();
                cajaPago.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
                cajaPago.setCoCompania(rs.getString(1));
                cajaPago.setCoLocal(rs.getString(2));
                cajaPago.setNuCaja(rs.getInt(3));
                cajaPago.setNuOrdenFila(rs.getInt(4));
                cajaPago.setDeCorta(rs.getString(5));
                cajaPago.setDeCaja(rs.getString(6));
                cajaPago.setInCajaAbierta(rs.getString(7));
                cajaPago.setInTipoCaja(rs.getString(8));
                cajaPago.setNuTurno(rs.getString(9));
                rgs.add(cajaPago);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public ArrayList getRelacionDeImpresoraCaja(int NumCaja)
    {
        ArrayList<CajaPago> rgs = new ArrayList<CajaPago>();
        try {
            StringBuffer  query = new StringBuffer();
            query.append("SELECT CAJA_IMPRESORA.CO_COMPANIA                    COMPANIA, ");
            query.append("            CAJA_IMPRESORA.CO_LOCAL                  LOCAL, ");
            query.append("            NVL(CAJA_IMPRESORA.NU_CAJA ,0 )          NU_CAJA, ");
            query.append("            NVL(CAJA_PAGO.DE_CAJA,0 )                CAJA , ");
            query.append("            NVL(TIPO_COMPROBANTE.TI_COMPROBANTE,'')  TIPO, ");
            query.append("            NVL(IMPRESORA_LOCAL.DE_IMPRESORA ,0)        COMPROBANTE, ");
            query.append("            NVL(IMPRESORA_LOCAL.NU_IMPRESORA ,0)         NU_IMPRESORA, ");
            query.append("            NVL(IMPRESORA_LOCAL.DE_COLA_IMPRESION,'') COLA_IMPRESION, ");
            query.append("            DECODE(CAJA_IMPRESORA.ES_CAJA_IMPRESORA ,'A','ACTIVO','I','INACTIVO',' ')  ESTADO ");
            query.append("            FROM  VTTR_CAJA_IMPRESORA   CAJA_IMPRESORA, ");
            query.append("                      VTTR_IMPRESORA_LOCAL  IMPRESORA_LOCAL, ");
            query.append("                      CMTR_TIPO_COMPROBANTE_PAGO TIPO_COMPROBANTE, ");
            query.append("                      VTTR_CAJA_PAGO CAJA_PAGO ");
            query.append("            WHERE ");
            query.append("                    CAJA_IMPRESORA.CO_COMPANIA  ='").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("            AND  CAJA_IMPRESORA.CO_LOCAL        ='").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("            AND  CAJA_IMPRESORA.NU_CAJA         =").append(NumCaja);
            query.append("            AND  IMPRESORA_LOCAL.ES_IMPRESORA_LOCAL ='A'");
            query.append("            AND  CAJA_IMPRESORA.CO_COMPANIA =IMPRESORA_LOCAL.CO_COMPANIA ");
            query.append("            AND  CAJA_IMPRESORA.CO_LOCAL        =IMPRESORA_LOCAL.CO_LOCAL ");
            query.append("            AND  CAJA_IMPRESORA.NU_IMPRESORA=IMPRESORA_LOCAL.NU_IMPRESORA ");
            query.append("            AND  CAJA_IMPRESORA.CO_COMPANIA =CAJA_PAGO.CO_COMPANIA ");
            query.append("            AND  CAJA_IMPRESORA.CO_LOCAL       =CAJA_PAGO.CO_LOCAL ");
            query.append("            AND  CAJA_IMPRESORA.NU_CAJA         =CAJA_PAGO.NU_CAJA ");
            query.append("            AND  CAJA_IMPRESORA.CO_COMPANIA =TIPO_COMPROBANTE.CO_COMPANIA ");
            query.append("            AND  CAJA_IMPRESORA.TI_COMPROBANTE =TIPO_COMPROBANTE.TI_COMPROBANTE ");
            query.append("            ORDER BY  CAJA_PAGO.NU_CAJA ,IMPRESORA_LOCAL.NU_IMPRESORA");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                cajaPago = new CajaPago();
                cajaPago.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
                cajaPago.setCoCompania(rs.getString(1));
                cajaPago.setCoLocal(rs.getString(2));
                cajaPago.setNuCaja(rs.getInt(3));
                cajaPago.setDeCaja(rs.getString(4));
                cajaPago.setTiComprobante(rs.getString(5));
                cajaPago.setDeComprobante(rs.getString(6));
                cajaPago.setNuImpresora(rs.getInt(7));
                cajaPago.setDeColaImpresion(rs.getString(8));
                cajaPago.setEstado(rs.getString(9));

                rgs.add(cajaPago);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public ArrayList getRelacionDeCorrelativos()
    {
        ArrayList<CajaPago> rgs = new ArrayList<CajaPago>();
        try {
            StringBuffer  query = new StringBuffer();
            query.append("SELECT   IMPRE_LOCAL.CO_COMPANIA   , \n");
            query.append("         IMPRE_LOCAL.CO_LOCAL      , \n");
            query.append("         IMPRE_LOCAL.TI_COMPROBANTE, \n");
            query.append("         UPPER(TIP_COM.DE_TIPO_COMPROBANTE) AS COMPROBANTE, \n");
            query.append("         IMPRE_LOCAL.NU_SERIE                              SERIE      , \n");
            query.append("         IMPRE_LOCAL.NU_COMPROBANTE_PAGO                   CORRELATIVO \n");
            query.append("FROM     VTTR_IMPRESORA_LOCAL IMPRE_LOCAL, \n");
            query.append("         CMTR_TIPO_COMPROBANTE_PAGO TIP_COM \n");
            query.append("WHERE    IMPRE_LOCAL.CO_COMPANIA       ='").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("AND      IMPRE_LOCAL.CO_LOCAL          ='").append(AtuxVariables.vCodigoLocal).append("'\n");
            query.append("AND      IMPRE_LOCAL.ES_IMPRESORA_LOCAL='A' \n");
            query.append("AND      IMPRE_LOCAL.CO_COMPANIA       = TIP_COM.CO_COMPANIA \n");
            query.append("AND      IMPRE_LOCAL.TI_COMPROBANTE    = TIP_COM.TI_COMPROBANTE \n");
            query.append("ORDER BY IMPRE_LOCAL.TI_COMPROBANTE");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                cajaPago = new CajaPago();
                cajaPago.setCoCompania(rs.getString(1));
                cajaPago.setCoLocal(rs.getString(2));
                cajaPago.setTiComprobante(rs.getString(3));
                cajaPago.setDeComprobante(rs.getString(4));
                cajaPago.setNuSerie(rs.getString(5));
                cajaPago.setNuComprobante(rs.getInt(6));

                rgs.add(cajaPago);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public String getNuCorrelativo(String tiComprobante)
    {
        String nuPedido="";

        try {
            StringBuffer query = new StringBuffer();
            query.append("SELECT MAX(NU_PEDIDO)  \n");
            query.append("FROM   VTTV_COMPROBANTE_PAGO \n");
            query.append("WHERE  CO_COMPANIA   ='").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("AND    CO_LOCAL      ='").append(AtuxVariables.vCodigoLocal).append("'\n");
            query.append("AND    TI_COMPROBANTE='").append(tiComprobante).append("'\n");
            query.append("AND    FE_COMPROBANTE>SYSDATE - 30");
            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                nuPedido = new String();
                nuPedido = rs.getString(1);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nuPedido;
    }

    public ArrayList<Usuario> getCajero()
    {
        ArrayList<Usuario> rgs = new ArrayList<Usuario>();
        Usuario usuario = null;
        try {
            StringBuffer query = new StringBuffer();
            query.append("SELECT USUARIO.CO_COMPANIA,     \n");
            query.append("       USUARIO.CO_LOCAL,        \n");
            query.append("       USUARIO.NU_SEC_USUARIO,  \n");
            query.append("       CAJA.NU_CAJA          ,  \n");
            query.append("       CAJA.NU_TURNO         ,  \n");
            query.append("       NVL(TRIM(USUARIO.AP_PATERNO_USUARIO),' ')  \n");
            query.append("       || ' '  \n");
            query.append("       || NVL(TRIM(USUARIO.AP_MATERNO_USUARIO),' ')  \n");
            query.append("       || ' '  \n");
            query.append("       || NVL(TRIM(USUARIO.NO_USUARIO),' ') \n");
            query.append("FROM     atux.CMTS_USUARIO USUARIO        , \n");
            query.append("         atux.CMTS_USUARIO_ROL USUARIO_ROL, \n");
            query.append("         atux.VTTR_CAJA_PAGO CAJA         , \n");
            query.append("         atux.VTTR_USUARIO_CAJA USUARIO_CAJA \n");
            query.append("WHERE    USUARIO_ROL.CO_COMPANIA     ='").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("AND      USUARIO_ROL.CO_LOCAL        ='").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("AND      USUARIO_ROL.CO_ROL          ='").append(AtuxVariables.ROL_CAJERO).append("'");
            query.append("AND      USUARIO_ROL.ES_USUARIO_ROL  = 'A' \n");
            query.append("AND      USUARIO_ROL.CO_COMPANIA     = USUARIO.CO_COMPANIA \n");
            query.append("AND      USUARIO_ROL.CO_LOCAL        = USUARIO.CO_LOCAL \n");
            query.append("AND      USUARIO_ROL.NU_SEC_USUARIO  = USUARIO.NU_SEC_USUARIO \n");
            query.append("AND      CAJA.CO_COMPANIA            = USUARIO.CO_COMPANIA \n");
            query.append("AND      CAJA.CO_LOCAL               = USUARIO.CO_LOCAL \n");
            query.append("AND      CAJA.IN_CAJA_ABIERTA        = 'S' \n");
            query.append("AND      USUARIO_CAJA.CO_COMPANIA    = CAJA.CO_COMPANIA \n");
            query.append("AND      USUARIO_CAJA.CO_LOCAL       = CAJA.CO_LOCAL \n");
            query.append("AND      USUARIO_CAJA.NU_CAJA        = CAJA.NU_CAJA \n");
            query.append("AND      USUARIO_CAJA.NU_SEC_USUARIO = USUARIO.NU_SEC_USUARIO \n");
            query.append("ORDER BY USUARIO.NO_USUARIO        ,  \n");
            query.append("         USUARIO.AP_PATERNO_USUARIO,  \n");
            query.append("         USUARIO.AP_MATERNO_USUARIO");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())            {
                usuario = new Usuario();
                usuario.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
                usuario.setCoCompania(rs.getString(1));
                usuario.setCoLocal(rs.getString(2));
                usuario.setNuSecUsuario(rs.getString(3));
                usuario.setNuCaja(rs.getString(4));
                usuario.setNuTurno(rs.getString(5));
                usuario.setNombre(rs.getString(6));
                usuario.setApMaterno("");
                usuario.setApPaterno("");
                rgs.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public ArrayList<CajaPago> getRelacionCajas()
    {
        ArrayList<CajaPago> rgs = new ArrayList<CajaPago>();
        try {
            StringBuffer query = new StringBuffer();
            query.append("SELECT   CAJA.CO_COMPANIA , \n");
            query.append("         CAJA.CO_LOCAL    , \n");
            query.append("         NVL(CAJA.DE_CORTA_CAJA,' ') , \n");
            query.append("         NVL(CAJA.NU_ORDEN_FILA,'0') , \n");
            query.append("         DECODE(CAJA.IN_TIPO_CAJA,'T','Tradicional','M','Multifuncional',' ') ,  \n");
            query.append("         DECODE(CAJA.ES_CAJA,  \n");
            query.append("                'A','Activo',  \n");
            query.append("                'I','Inactivo',  \n");
            query.append("                ' ')          , \n");
            query.append("         NVL(CAJA.NU_CAJA,'0'), \n");
            query.append("         NVL(CAJA.DE_CAJA,' '), \n");
            query.append("         NVL(CAJA.IN_TIENE_LECTOR_TARJETAS,'N') \n");
            query.append("FROM     VTTR_CAJA_PAGO CAJA \n");
            query.append("WHERE    CAJA.CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("AND      CAJA.CO_LOCAL    = '").append(AtuxVariables.vCodigoLocal).append("'\n");
            query.append("AND      CAJA.NU_CAJA     > 0 \n");
            query.append("ORDER BY CAJA.NU_CAJA");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                cajaPago = new CajaPago();
                cajaPago.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2)});
                cajaPago.setCoCompania(rs.getString(1));
                cajaPago.setCoLocal(rs.getString(2));
                cajaPago.setDeCorta(rs.getString(3));
                cajaPago.setNuOrdenFila(rs.getInt(4));
                cajaPago.setInTipoCaja(rs.getString(5));
                cajaPago.setEstado(rs.getString(6));
                cajaPago.setNuCaja(rs.getInt(7));
                cajaPago.setDeCaja(rs.getString(8));

                rgs.add(cajaPago);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public CajaPago getTestigo()
    {
        try {
            StringBuffer query = new StringBuffer();
            query.append("SELECT CO_COMPANIA ,  \n");
            query.append("       CO_LOCAL    ,  \n");
            query.append("       NU_IMPRESORA,  \n");
            query.append("       DE_IMPRESORA,  \n");
            query.append("       DE_COLA_IMPRESION  \n");
            query.append("FROM   CMTR_IMPRESORA_TESTIGO \n");
            query.append("WHERE  ES_IMPRESORA_LOCAL='A'");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                cajaPago = new CajaPago();
                cajaPago.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2)});
                cajaPago.setCoCompania(rs.getString(1));
                cajaPago.setCoLocal(rs.getString(2));
                cajaPago.setDeCaja(rs.getString(4));
                cajaPago.setDeColaImpresion(rs.getString(5));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cajaPago;
    }

    public ArrayList<ProductoLocal> getRelacionProductosReposicion()
    {
        ArrayList<ProductoLocal> rgs = new ArrayList<ProductoLocal>();
        try {
            StringBuffer query = new StringBuffer();
            query.append("SELECT V2.CO_COMPANIA                         COMPANIA       , \n");
            query.append("       V2.CO_LOCAL                            LOCAL          , \n");
            query.append("       V2.CO_PRODUCTO                         PROD           , \n");
            query.append("       NVL(RTRIM(V2.DE_PRODUCTO), ' ')        DE_PRODUCTO    , \n");
            query.append("       NVL(RTRIM(V2.DE_UNIDAD_PRODUCTO), ' ') DE_UNIDAD      , \n");
            query.append("       V2.CA_STOCK_MINIMO                     CA_STOCK_MINIMO, \n");
            query.append("       V2.CA_STOCK_MAXIMO                     CA_STOCK_MAXIMO, \n");
            query.append("       V2.STOCK                               STOCK          , \n");
            query.append("       DECODE(NVL(V2.IN_PRODUCTO_REPONER, 'S'),                \n");
            query.append("              'S', ' ', \n");
            query.append("              'N')                 IN_PRODUCTO_REPONER    , \n");
            query.append("       V2.CA_STOCK_REPONER_CALCULA STOCK_REPONER_CALCULADO, \n");
            query.append("       NVL(V2.CA_STOCK_REPONER,0)  STOCK_REPONER          , \n");
            query.append("       V1.ULTIMO                   ULTIMO                 , \n");
            query.append("       V2.CA_MIN_PROD_EXHIBICION   MIN_PROD_EXHIBICION    , \n");
            query.append("       V2.CA_PROD_NO_ATENDIDO      CA_PROD_NO_ATENDIDO    , \n");
            query.append("       V1.NU_MIN_DIAS_REPOSICION                          , \n");
            query.append("       V1.NU_MAX_DIAS_REPOSICION                          , \n");
            query.append("       V1.NU_DIAS_ROTACION_PROMEDIO                       , \n");
            query.append("       ''                           TRANSITO              , \n");
            query.append("       ''                           COMPRAS_PENDIENTES    , \n");
            query.append("       V1.VA_COSTO                  VA_COSTO                \n");
            query.append("FROM   (SELECT \n");
            query.append("               /*+ CHOOSE */ \n");
            query.append("               PROD_LOCAL.CO_COMPANIA   , \n");
            query.append("               PROD_LOCAL.CO_LOCAL      , \n");
            query.append("               PROD.CO_PRODUCTO CO_PRODUCTO, \n");
            query.append("               DECODE(NVL(PROD_LOCAL.NU_MIN_DIAS_REPOSICION,0), \n");
            query.append("                      0, DECODE(NVL(LABORATORIO.NU_MIN_DIAS_REPOSICION,0), \n");
            query.append("                                0, DECODE(NVL(LINEA_LOCAL.NU_MIN_DIAS_REPOSICION,0), \n");
            query.append("                                          0, NVL(LOCAL.NU_MIN_DIAS_REPOSICION,0), \n");
            query.append("                                          LINEA_LOCAL.NU_MIN_DIAS_REPOSICION), \n");
            query.append("                                LABORATORIO.NU_MIN_DIAS_REPOSICION), \n");
            query.append("                      PROD_LOCAL.NU_MIN_DIAS_REPOSICION) NU_MIN_DIAS_REPOSICION, \n");
            query.append("               DECODE(NVL(PROD_LOCAL.NU_MAX_DIAS_REPOSICION,0), \n");
            query.append("                      0, DECODE(NVL(LABORATORIO.NU_MAX_DIAS_REPOSICION,0), \n");
            query.append("                                0, DECODE(NVL(LINEA_LOCAL.NU_MAX_DIAS_REPOSICION,0), \n");
            query.append("                                          0, NVL(LOCAL.NU_MAX_DIAS_REPOSICION,0), \n");
            query.append("                                          LINEA_LOCAL.NU_MAX_DIAS_REPOSICION), \n");
            query.append("                                LABORATORIO.NU_MAX_DIAS_REPOSICION), \n");
            query.append("                      PROD_LOCAL.NU_MAX_DIAS_REPOSICION) NU_MAX_DIAS_REPOSICION, \n");
            query.append("               DECODE(NVL(PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0), \n");
            query.append("                      0, DECODE(NVL(LABORATORIO.NU_DIAS_ROTACION_PROMEDIO,0), \n");
            query.append("                                0, DECODE(NVL(LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0), \n");
            query.append("                                          0, NVL(LOCAL.NU_DIAS_ROTACION_PROMEDIO,0), \n");
            query.append("                                          LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO), \n");
            query.append("                                LABORATORIO.NU_DIAS_ROTACION_PROMEDIO), \n");
            query.append("                      PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO) NU_DIAS_ROTACION_PROMEDIO, \n");
            query.append("               NVL(CA_ULTIMO_PEDIDO_REP,0)                  ULTIMO, \n");
            query.append("               DECODE(PROD_LOCAL.IN_PROD_FRACCIONADO, 'S', (PROD_LOCAL.VA_COSTO_PRODUCTO* PROD_LOCAL.VA_FRACCION),PROD_LOCAL.VA_COSTO_PRODUCTO) VA_COSTO \n");
            query.append("       FROM    LGTR_PRODUCTO_LOCAL PROD_LOCAL, \n");
            query.append("               CMTR_LABORATORIO LABORATORIO  , \n");
            query.append("               VTTM_LOCAL LOCAL              , \n");
            query.append("               LGTM_PRODUCTO PROD            , \n");
            query.append("               LGTR_LINEA_LOCAL LINEA_LOCAL \n");
            query.append("       WHERE   PROD_LOCAL.CO_COMPANIA  = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("       AND     PROD_LOCAL.CO_LOCAL     = '").append(AtuxVariables.vCodigoLocal).append("'\n");
            query.append("       AND     PROD_LOCAL.CO_COMPANIA  = LOCAL.CO_COMPANIA \n");
            query.append("       AND     PROD_LOCAL.CO_LOCAL     = LOCAL.CO_LOCAL \n");
            query.append("       AND     PROD_LOCAL.CO_COMPANIA  = PROD.CO_COMPANIA \n");
            query.append("       AND     PROD_LOCAL.CO_PRODUCTO  = PROD.CO_PRODUCTO \n");
            query.append("       AND     PROD.CO_COMPANIA        = LINEA_LOCAL.CO_COMPANIA(+) \n");
            query.append("       AND     PROD.CO_LINEA_PRODUCTO  = LINEA_LOCAL.CO_LINEA_PRODUCTO(+) \n");
            query.append("       AND     PROD.CO_COMPANIA        = LABORATORIO.CO_COMPANIA (+) \n");
            query.append("       AND     PROD.CO_LABORATORIO     = LABORATORIO.CO_LABORATORIO(+) \n");
            query.append("       AND     LINEA_LOCAL.CO_LOCAL(+) = '").append(AtuxVariables.vCodigoLocal).append("'\n");
            query.append("       ) \n");
            query.append("       V1, \n");
            query.append("       (SELECT \n");
            query.append("               /*+ CHOOSE */ \n");
            query.append("               PRODUCTO_LOCAL.CO_COMPANIA                                    , \n");
            query.append("               PRODUCTO_LOCAL.CO_LOCAL                                       , \n");
            query.append("               NVL(PRODUCTO_LOCAL.CO_PRODUCTO,' ')         CO_PRODUCTO       , \n");
            query.append("               NVL(RTRIM(PRODUCTO.DE_PRODUCTO) ,' ')       DE_PRODUCTO       , \n");
            query.append("               NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') DE_UNIDAD_PRODUCTO, \n");
            query.append("               NVL(PRODUCTO_LOCAL.CA_STOCK_MINIMO ,0)      CA_STOCK_MINIMO   , \n");
            query.append("               NVL(PRODUCTO_LOCAL.CA_STOCK_MAXIMO ,0)      CA_STOCK_MAXIMO   , \n");
            query.append("               DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO, \n");
            query.append("                      'S', TO_CHAR(TRUNC((PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE) / PRODUCTO_LOCAL.VA_FRACCION),'99,990'), \n");
            query.append("                      TO_CHAR(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,'99,990') ) \n");
            query.append("                       || ' / ' \n");
            query.append("                       || DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO, \n");
            query.append("                                 'S', TO_CHAR(MOD(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE, PRODUCTO_LOCAL.VA_FRACCION),'99,990'), \n");
            query.append("                                 '   ')                        STOCK                   , \n");
            query.append("               PRODUCTO_LOCAL.IN_PRODUCTO_REPONER              IN_PRODUCTO_REPONER     , \n");
            query.append("               NVL(PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA, 0) CA_STOCK_REPONER_CALCULA, \n");
            query.append("               NVL(PRODUCTO_LOCAL.CA_STOCK_REPONER,0)          CA_STOCK_REPONER        , \n");
            query.append("               NVL(CA_MIN_PROD_EXHIBICION, 0)                  CA_MIN_PROD_EXHIBICION  , \n");
            query.append("               NVL(CA_PROD_NO_ATENDIDO, 0)                     CA_PROD_NO_ATENDIDO \n");
            query.append("       FROM    LGTM_PRODUCTO PRODUCTO, \n");
            query.append("               LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL \n");
            query.append("       WHERE   PRODUCTO_LOCAL.CO_COMPANIA         = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("       AND     PRODUCTO_LOCAL.CO_LOCAL            = '").append(AtuxVariables.vCodigoLocal).append("'\n");
            query.append("       AND     PRODUCTO_LOCAL.IN_PRODUCTO_REPONER = 'S' \n");
            query.append("       AND \n");
            query.append("               ( \n");
            query.append("                       PRODUCTO_LOCAL.ES_PROD_LOCAL       = 'A' \n");
            query.append("               OR      PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0 \n");
            query.append("               ) \n");
            query.append("       AND     PRODUCTO_LOCAL.CO_COMPANIA = PRODUCTO.CO_COMPANIA \n");
            query.append("       AND     PRODUCTO_LOCAL.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO \n");
            query.append("       AND     PRODUCTO_LOCAL.NU_REVISION_PRODUCTO = PRODUCTO.NU_REVISION_PRODUCTO \n");
            query.append("       AND     PRODUCTO.ES_PRODUCTO       = 'A' \n");
            query.append("       ) \n");
            query.append("       V2 \n");
            query.append("WHERE  V1.CO_COMPANIA = V2.CO_COMPANIA\n");
            query.append("AND  V1.CO_LOCAL    = V2.CO_LOCAL\n");
            query.append("AND  V1.CO_PRODUCTO = V2.CO_PRODUCTO");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                prodLocal = new ProductoLocal();
                prodLocal.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),"0"});
                prodLocal.setCoCompania(rs.getString(1));
                prodLocal.setCoLocal(rs.getString(2));
                prodLocal.setCoProducto(rs.getString(3));
                prodLocal.setDeProducto(rs.getString(4).trim());
                prodLocal.setDeUnidadProducto(rs.getString(5).trim());
                prodLocal.setCaStockMinimo(rs.getInt(6));
                prodLocal.setCaStockMaximo(rs.getInt(7));
                prodLocal.setStockEnCajas(rs.getString(8));
                prodLocal.setInProductoReponer(rs.getString(9).trim());
                prodLocal.setCaStockReponerCalcula(rs.getInt(10));
                prodLocal.setCaStockReponer(rs.getInt(11));
                prodLocal.setCaUltimoPedidoRep(rs.getInt(12));
                prodLocal.setCaMinProdExhibicion(rs.getInt(13));
                prodLocal.setCaProdNoAtendido(rs.getInt(14));
                prodLocal.setNuMinDiasReposicion(rs.getInt(15));
                prodLocal.setNuMaxDiasReposicion(rs.getInt(16));
                prodLocal.setNuDiasRotacionPromedio(rs.getInt(17));
                prodLocal.setCaTransito("");
                prodLocal.setCaComprasPendientes("");
                prodLocal.setVaCostoProducto(rs.getDouble(20));
                rgs.add(prodLocal);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public ArrayList<ProductoLocal> getRelacionProductosFaltaCero(String pFechaProceso)
    {
        ArrayList<ProductoLocal> rgs = new ArrayList<ProductoLocal>();
        try {
            StringBuffer query = new StringBuffer();
            query.append("SELECT A.CO_COMPANIA         , \n");
            query.append("       A.CO_LOCAL            , \n");
            query.append("       A.CO_PRODUCTO         , \n");
            query.append("       B.DE_PRODUCTO         , \n");
            query.append("       B.DE_UNIDAD_PRODUCTO  , \n");
            query.append("       A.CA_STOCK_NO_ATENDIDO, \n");
            query.append("       A.CA_STOCK_CALCULADO  , \n");
            query.append("       A.FE_CREA \n");
            query.append("FROM   LGTR_PRODUCTOS_FALTA_CERO_HIST A, \n");
            query.append("       LGTM_PRODUCTO B \n");
            query.append("WHERE  A.FE_CREA = TO_DATE('").append(pFechaProceso).append("','dd/MM/yyyy') \n");
            query.append("AND    A.CO_COMPANIA         = B.CO_COMPANIA \n");
            query.append("AND    A.CO_PRODUCTO         = B.CO_PRODUCTO \n");
            query.append("AND    A.NU_REVISION_PRODUCTO= B.NU_REVISION_PRODUCTO");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                prodLocal = new ProductoLocal();
                prodLocal.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),"0"});
                prodLocal.setCoCompania(rs.getString(1));
                prodLocal.setCoLocal(rs.getString(2));
                prodLocal.setCoProducto(rs.getString(3));
                prodLocal.setDeProducto(rs.getString(4).trim());
                prodLocal.setDeUnidadProducto(rs.getString(5).trim());
                prodLocal.setCaProdNoAtendido(rs.getInt(6));
                prodLocal.setCaStockReponerCalcula(rs.getInt(7));
                prodLocal.setFeCreaProdLocal(rs.getDate(8));
                rgs.add(prodLocal);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public ArrayList getFechasProReposicion()
    {
        ArrayList rgs = new ArrayList();
        String vFecha;
        try {
            StringBuffer query = new StringBuffer();
            query.append("SELECT DISTINCT TO_CHAR(FE_CREA,'DD/MM/YYYY') FROM LGTR_PRODUCTOS_FALTA_CERO_HIST \n");
            query.append(" ORDER BY 1 DESC \n");
            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                vFecha = new String();
                vFecha = rs.getString(1);
                rgs.add(vFecha);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public ArrayList<ListaPedidosReposicion> getRelacionPedidosReposicion(String fechaIni,String fechaFin)
    {
        ArrayList<ListaPedidosReposicion> rgs = new ArrayList<ListaPedidosReposicion>();
        try {
            StringBuffer query = new StringBuffer();
            query.append("SELECT CAB.CO_COMPANIA              , \n");
            query.append("       CAB.CO_LOCAL                 , \n");
            query.append("       CAB.TI_PEDIDO_PRODUCTO       , \n");
            query.append("       CAB.NU_PEDIDO_PRODUCTO       , \n");
            query.append("       CAB.FE_SOLICITA_PEDIDO       , \n");
            query.append("       CAB.CA_TOTAL_PRODUCTO        , \n");
            query.append("       CAB.NU_DIAS_ROTACION_PROMEDIO, \n");
            query.append("       CAB.NU_MINIMO_DIAS_REPOSICION, \n");
            query.append("       CAB.NU_MAXIMO_DIAS_REPOSICION \n");
            query.append("FROM   LGTC_PEDIDO_PRODUCTO CAB \n");
            query.append(" WHERE CAB.CO_COMPANIA  = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("  AND  CAB.CO_LOCAL     = '").append(AtuxVariables.vCodigoLocal).append("'\n");
            query.append("AND    CAB.FE_SOLICITA_PEDIDO BETWEEN TO_DATE ('").append(fechaIni).append("'\n");
            query.append("                  || ' 00:00:00','dd/MM/yyyy HH24:MI:SS') \n");
            query.append("                         AND      TO_DATE ('").append(fechaFin).append("'\n");
            query.append("                  || ' 23:59:59','dd/MM/yyyy HH24:MI:SS') \n");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                pedRep = new ListaPedidosReposicion();
                pedRep.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
                pedRep.setCoCompania(rs.getString(1));
                pedRep.setCoLocal(rs.getString(2));
                pedRep.setTiPedidoProducto(rs.getString(3));
                pedRep.setNuPedidoProducto(rs.getString(4));
                pedRep.setFeSolicitaPedido(rs.getDate(5));
                pedRep.setCaTotalProducto(rs.getInt(6));
                pedRep.setNuDiasRotacion(rs.getInt(7));
                pedRep.setNuMinDiasReposicion(rs.getInt(8));
                pedRep.setNuMaxDiasReposicion(rs.getInt(9));
                rgs.add(pedRep);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public ArrayList<ListaPedidosReposicion> getDetallePedidoReposicion(ListaPedidosReposicion pedido)
    {
        ArrayList<ListaPedidosReposicion> rgs = new ArrayList<ListaPedidosReposicion>();
        try {
            StringBuffer query = new StringBuffer();
            query.append(" SELECT CAB.NU_PEDIDO_PRODUCTO, \n");
            query.append("        DET.NU_ITEM           , \n");
            query.append("        DET.CO_PRODUCTO       , \n");
            query.append("        PRO.DE_PRODUCTO       , \n");
            query.append("        PRO.DE_UNIDAD_PRODUCTO, \n");
            query.append("        DET.CA_PEDIDA         , \n");
            query.append("        DET.CA_SUGERIDA_CALCULADA \n");
            query.append("     FROM   LGTC_PEDIDO_PRODUCTO CAB, \n");
            query.append("            LGTD_PEDIDO_PRODUCTO DET, \n");
            query.append("            LGTM_PRODUCTO PRO \n");
            query.append("  WHERE CAB.CO_COMPANIA       = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("  AND  CAB.CO_LOCAL           = '").append(AtuxVariables.vCodigoLocal).append("'\n");
            query.append("  AND  CAB.NU_PEDIDO_PRODUCTO = '").append(pedido.getNuPedidoProducto()).append("'\n");
            query.append("  AND  CAB.CO_COMPANIA        = DET.CO_COMPANIA \n");
            query.append("  AND  CAB.CO_LOCAL           = DET.CO_LOCAL \n");
            query.append("  AND  CAB.TI_PEDIDO_PRODUCTO = DET.TI_PEDIDO_PRODUCTO \n");
            query.append("  AND  CAB.NU_PEDIDO_PRODUCTO = DET.NU_PEDIDO_PRODUCTO \n");
            query.append("  AND  DET.CO_COMPANIA        = PRO.CO_COMPANIA \n");
            query.append("  AND  DET.CO_PRODUCTO        = PRO.CO_PRODUCTO \n");
            query.append("  AND  DET.NU_REVISION_PRODUCTO = PRO.NU_REVISION_PRODUCTO");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                pedido = new ListaPedidosReposicion();
                pedido.setNuPedidoProducto(rs.getString(1));
                pedido.setNuItem(rs.getInt(2));
                pedido.setCoProducto(rs.getString(3));
                pedido.setDeProducto(rs.getString(4));
                pedido.setDeUnidad(rs.getString(5));
                pedido.setCaPedida(rs.getInt(6));
                pedido.setCaSugerida(rs.getInt(7));
                rgs.add(pedido);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public ArrayList<FormaPagoPedidoCredito> get(String pFechaProceso)
    {
        FormaPagoPedidoCredito prodLocal;
        ArrayList<FormaPagoPedidoCredito> rgs = new ArrayList<FormaPagoPedidoCredito>();
        try {
            StringBuffer query = new StringBuffer();
            query.append("SELECT A.CO_COMPANIA         , \n");
            query.append("       A.CO_LOCAL            , \n");
            query.append("       A.CO_PRODUCTO         , \n");
            query.append("       B.DE_PRODUCTO         , \n");
            query.append("       B.DE_UNIDAD_PRODUCTO  , \n");
            query.append("       A.CA_STOCK_NO_ATENDIDO, \n");
            query.append("       A.CA_STOCK_CALCULADO  , \n");
            query.append("       A.FE_CREA \n");
            query.append("FROM   LGTR_PRODUCTOS_FALTA_CERO_HIST A, \n");
            query.append("       LGTM_PRODUCTO B \n");
            query.append("WHERE  A.FE_CREA = TO_DATE('").append(pFechaProceso).append("','dd/MM/yyyy') \n");
            query.append("AND    A.CO_COMPANIA         = B.CO_COMPANIA \n");
            query.append("AND    A.CO_PRODUCTO         = B.CO_PRODUCTO \n");
            query.append("AND    A.NU_REVISION_PRODUCTO= B.NU_REVISION_PRODUCTO");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                prodLocal = new FormaPagoPedidoCredito();
                prodLocal.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),"0"});
                prodLocal.setCoCompania(rs.getString(1));
                prodLocal.setCoLocal(rs.getString(2));

                rgs.add(prodLocal);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public ArrayList getRelacionDePedidosCredito(PedidoVenta pedido)
    {
        ArrayList<FormaPagoPedidoCredito> rgs = new ArrayList<FormaPagoPedidoCredito>();
        try {
            StringBuffer query = new StringBuffer();
            query.append("SELECT PED.CO_COMPANIA              , \n");
            query.append("       PED.CO_LOCAL                 , \n");
            query.append("       PED.NU_PEDIDO                , \n");
            query.append("       PED.NU_ITEM_FORMA_PAGO       , \n");
            query.append("       PED.CO_MONEDA                , \n");
            query.append("       PED.CO_FORMA_PAGO            , \n");
            query.append("       PED.VA_TASA_CAMBIO           , \n");
            query.append("       PED.IM_PAGO                  , \n");
            query.append("       PED.IM_TOTAL_PAGO            , \n");
            query.append("       PED.IN_PAGO_DIFERIDO         , \n");
            query.append("       PED.DE_VENTA_CREDITO         , \n");
            query.append("       PED.VA_VUELTO                , \n");
            query.append("       PED.VA_SALDO                 , \n");
            query.append("       PED.ES_FORMA_PAGO_PEDIDO     , \n");
            query.append("       PED.ID_CREA_FORMA_PAGO_PEDIDO, \n");
            query.append("       PED.FE_CREA_FORMA_PAGO_PEDIDO, \n");
            query.append("       PED.IN_ANULADO               , \n");
            query.append("       FORMA.DE_CORTA_FORMA_PAGO    , \n");
            query.append("       PED.FE_PEDIDO                , \n");
            query.append("       PED.FE_PAGO \n");
            query.append("FROM   VTTX_FORMA_PAGO_PEDIDO_CREDITO PED, \n");
            query.append("       VTTR_FORMA_PAGO FORMA             , \n");
            query.append("       VTTC_PEDIDO_VENTA VENTA \n");
            query.append("WHERE  PED.CO_COMPANIA= '").append(pedido.getCoCompania()).append("'\n");
            query.append("AND    PED.CO_LOCAL   = '").append(pedido.getCoLocal()).append("'\n");
            query.append("AND  \n");
            query.append("       (  \n");
            query.append("              PED.NU_PEDIDO         = '").append(pedido.getNuPedido()).append("'\n");
            query.append("       OR     VENTA.CO_CLIENTE_LOCAL= '").append(pedido.getCoClienteLocal()).append("'\n");
            query.append("       ) \n");
            query.append("AND    VENTA.CO_COMPANIA = PED.CO_COMPANIA \n");
            query.append("AND    VENTA.CO_LOCAL    = PED.CO_LOCAL \n");
            query.append("AND    VENTA.NU_PEDIDO   = PED.NU_PEDIDO \n");
            query.append("AND    PED.CO_COMPANIA   = FORMA.CO_COMPANIA \n");
            query.append("AND    PED.CO_FORMA_PAGO = FORMA.CO_FORMA_PAGO \n");
            query.append("ORDER BY PED.NU_PEDIDO,PED.NU_ITEM_FORMA_PAGO DESC");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                pedCredito = new FormaPagoPedidoCredito();
                pedCredito.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
                pedCredito.setCoCompania(rs.getString(1));
                pedCredito.setCoLocal(rs.getString(2));
                pedCredito.setNuPedido(rs.getString(3));
                pedCredito.setNuItemFormaPago(rs.getInt(4));
                pedCredito.setCoMoneda(rs.getString(5));
                pedCredito.setCoFormaPago(rs.getString(6));
                pedCredito.setVaTasaCambio(rs.getDouble(7));
                pedCredito.setImPago(rs.getDouble(8));
                pedCredito.setImTotalPago(rs.getDouble(9));
                pedCredito.setInPagoDiferido(rs.getString(10));
                pedCredito.setDeVentaCredito(rs.getString(11));
                pedCredito.setVaVuelto(rs.getDouble(12));
                pedCredito.setVaSaldo(rs.getDouble(13));
                pedCredito.setEsFormaPagoPedido(rs.getString(14));
                pedCredito.setIdCreaFormaPagoPedido(rs.getString(15));
                pedCredito.setFeCreaFormaPagoPedido(rs.getDate(16));
                pedCredito.setInAnulado(rs.getString(17));
                pedCredito.setDeFormaPago(rs.getString(18));
                pedCredito.setFePedido(rs.getDate(19));
                pedCredito.setFePago(rs.getDate(20));
                rgs.add(pedCredito);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }
}
