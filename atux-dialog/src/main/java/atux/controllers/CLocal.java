package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.managerbd.BaseConexion;
import atux.modelbd.Bitacora;
import atux.modelbd.Local;
import atux.util.common.AtuxVariables;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;


public class CLocal extends JAbstractController{
    private Local local;
    private Local.Compania compania;
    private InputStream itmp;

    public CLocal(){        
        
    }
    
    @Override
    public ArrayList<Local> getRegistros() {
        return this.getRegistros(new String[]{}, null,null);
    }

    @Override
    public ArrayList<Local> getRegistros(Object[] cvl) {
        return this.getRegistros(new String[]{}, cvl!=null?new String[]{Local.COLUMNA_ACTIVO}:null,cvl);
    }
    
    public ArrayList<Local> getRegistros(String[] columnas,Object[] cvl) {
        return this.getRegistros(new String[]{}, columnas,cvl);
    }       
    
    public Local getRegistrosCodigo(String codigo) {
        return getRegistros(Local.NO_FULL_NOM_CAMPOS, new String[]{"co_local"},new Object[]{codigo}).get(0);
    }        
    
    public ArrayList<Local> getRegistrosFecha(long valor) {
        return this.getRegistrosMn(new String[]{}, new String[]{"fec_registro"},new Object[]{valor});
    }
    
    public ArrayList<Local> getRegistrosFecha(long valor1,long valor) {
        return this.getRegistrosMn(new String[]{}, new String[]{"fec_registro","fec_registro"},new Object[]{valor1,valor});
    }

    @Override
    public Local getRegistro() {
        ArrayList<Local> registros = this.getRegistros();
        return registros.get(0);
    }
    
    public Local getRegitro(String id)
    {
        ArrayList<Local> registros = this.getRegistros(new String[]{"co_local"},new Object[]{id});
        return registros.get(0);
    }
    
    /**
     * 
     * @param mdl modelo
     * @param opcion si true llenara el modelos con todos los datos, delocontrario solo obtendra la clave primaria.
     * @return 
     */
    @Override
    public JAbstractModelBD getRegistro(JAbstractModelBD mdl,boolean opcion) {
        local = (Local)mdl;
        sql = "select co_compania ,co_local ,ti_local ,in_tipo_caja,de_local from "+local.getNombreTabla()+" where co_local = '"+local.getCoLocal()+"'";
        try {
            rs =BaseConexion.getConexion().createStatement().executeQuery(sql);
            if(rs.next())
            {
                local.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2)});
                local.setCoCompania(rs.getString(1));
                local.setCoLocal(rs.getString(2));
                if(opcion) 
                {
                   local.setTiLocal(rs.getString(3));
                   local.setTiCaja(rs.getString(4));
                   local.setDeLocal(rs.getString(5));
                }
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return local;
    }


    public JAbstractModelBD getObtenerSuministrosReposicion() {
        local = new Local();
        sql = "select co_compania ,co_local, nvl(TS_DIAS,0) TS_DIAS, nvl(TR_DIAS,0) TR_DIAS from "+local.getNombreTabla()+" where co_compania = '" + AtuxVariables.vCodigoCompania + "' and co_local = '" + AtuxVariables.vCodigoLocal + "'";
        try {
            rs =BaseConexion.getConexion().createStatement().executeQuery(sql);
            
            if(rs.next()){
                local.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL")});
                local.setCoCompania(rs.getString("CO_COMPANIA"));
                local.setCoLocal(rs.getString("CO_LOCAL"));
                local.setTrDias(rs.getInt("TR_DIAS"));
                local.setTsDias(rs.getInt("TS_DIAS"));
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return local;
    }

    public Local getSuministroReposicion() {
        if(local == null)
        {
            local = new Local();
        }
        return local;
    }    
    @Override
    public JAbstractModelBD buscarRegistro(Object cvl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {      
        Local loc = (Local)mdl;
        int gravado = 0;
        
        String campos = Local.NO_FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {loc.getCoCompania(), loc.getCoLocal(),
                   loc.getTiLocal(),
                   loc.getTiCaja(),
                   loc.getDeLocal()};
       
           gravado = this.agregarRegistroPs(Local.nt, this.stringToArray(campos, ","), valores);
       
        if(gravado==1)
        {            
            try{
                this.grabarBitacora(this.getUltimoLocal());
            }catch(SQLException ex){ex.printStackTrace();}            
            return true;
        }
            
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
       Local loc = (Local)mdl;
       int gravado = 0;        
       String campos = Local.NO_FULL_NOM_CAMPOS.toString();
        
       Object[] valores = {loc.getCoCompania(), loc.getCoLocal(),
                   loc.getTiLocal(),
                   loc.getTiCaja(),
                   loc.getDeLocal(),loc.getPrimaryKey()};
        
       gravado = this.actualizarRegistroPs(Local.nt, this.adjuntarSimbolo(campos, ",", "?")+Ex.WHERE+Local.COLUMNA_PK+" = ? ", valores);
           
   
       return gravado;
    }        
    
    public int grabarEnBitacora(Local local)
    {
        try{
            return this.grabarBitacora(local);
        }catch(SQLException ex){ex.printStackTrace();}
        return -1;
    }
    private int grabarBitacora(Local local) throws SQLException 
    {        
            Calendar cr = Calendar.getInstance();
            Bitacora bt = getBitacora(local);
            sql = "INSERT INTO gv_bitacora ("+Bitacora.COLUMNAS_NO_ID+")"+
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
            this.ps = BaseConexion.getConexion().prepareStatement(sql);
            ps.setString(1,local.getCoCompania());  
            ps.setString(2,local.getCoLocal());
            ps.setString(3,AtuxVariables.vIdUsuario);
            ps.setString(2, System.getProperty("os.name"));
            ps.setString(3, System.getProperty("os.arch"));
            ps.setString(4, System.getProperty("os.version"));
            ps.setString(5, System.getProperty("user.name"));
            if(bt!=null)
            {
                ps.setLong(6, bt.getFechaActividad());
            }else{
                ps.setLong(6, cr.getTime().getTime());
            }
            ps.setLong(7, cr.getTime().getTime());
            return ps.executeUpdate();            
        
    }
    
    private Bitacora getBitacora(Local local) 
    {
        try {
            Bitacora bt = new Bitacora();
            sql = "select * from gv_bitacora where idusuario = "+local.getPrimaryKey()+" order by fecha_actividad desc limit 1";
            rs =BaseConexion.getConexion().createStatement().executeQuery(sql);
            if(rs.next())
            {
                bt.setIdbitacora(rs.getLong(1));
                bt.setIdusuario(rs.getInt(2));
                bt.setOs(rs.getString(3));
                bt.setArquitectura(rs.getString(4));
                bt.setVersion(rs.getString(5));
                bt.setUsuario(rs.getString(6));
                bt.setUltimaActividad(rs.getLong(7));
                bt.setFechaActividad(rs.getLong(8));
                return bt;
            }
                
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return null;
    }
    
       
    public Local.Compania getCompaniaPorPk(Object[] id)
    {
        try {
            rs =  this.selectPorPk(Local.Compania.nt,Local.Compania.NO_FULL_NOM_CAMPOS,Local.Compania.COLUMNA_PK, id);
            
            while(rs.next())
            {
               compania = new Local.Compania(); 
               compania.setCoCompania(rs.getString(1));
               compania.setCoTelCiudad(rs.getString(2));
               compania.setDeCompania(rs.getString(3));
               compania.setDeCortaCompania(rs.getString(4));  
               compania.setDeDireccion(rs.getString(5));
               compania.setDeDireccionWeb(rs.getString(6));
               compania.setNuRucCompania(rs.getString(7));
               compania.setNuTelNormal(rs.getString(8));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return compania;
    }

    @Override
    public ArrayList<Local> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Local> rgs = new ArrayList<Local>();        
        try {
            
            if(id != null)
            { 
               this.numRegistros = this.getNumeroRegistros(Local.nt, Local.COLUMNA_ACTIVO, Local.COLUMNA_ACTIVO, id);
            }else
            {
               this.numRegistros = this.getNumeroRegistros(Local.nt, Local.COLUMNA_ACTIVO);               
               if(rs.isClosed())
               {
                   System.out.println("resultset esta cerrado...");
               }
            }
            
            rs = this.getRegistros(Local.nt,campos,columnaId,id,null);
            
            if(this.numRegistros<finalPag)
            {
              finalPag =  this.numRegistros;
            }
            if(finalPag>inicioPag)
            {
              inicioPag -= (finalPag-inicioPag)-1;
            }
            
            Local loc = null;
            
            while(rs.next())
            {
                   loc = new Local();
                   loc.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2)});
                   loc.setCoCompania(rs.getString(1));
                   loc.setCompania(new CLocal().getCompaniaPorPk(new Object[]{rs.getString(1)}));                                                         
                   loc.setCoLocal(rs.getString(2));
                   loc.setTiLocal(rs.getString(3));
                   loc.setTiCaja(rs.getString(4));
                   loc.setDeLocal(rs.getString(5));  
                   loc.setDeCortaLocal(rs.getString(6));
                   loc.setDeMensajeTicket(rs.getString(7));
                   loc.setDeDireccionLocal(rs.getString(8));
                   loc.setInTicketBoleta(rs.getString(9));
                   loc.setInTicketFactura(rs.getString(10));
                 rgs.add(loc);
            }           
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }
    public Local getUltimoLocal() throws SQLException
    {
       Local loc = null;
       rs = this.getUltimoRegistro(Local.nt,"CO_LOCAL");
       try{
           
            while(rs.next())
            {
                   loc = new Local();
                   loc.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2)});
                   loc.setCoCompania(rs.getString(1));
                   loc.setCoLocal(rs.getString(2));
                   loc.setTiLocal(rs.getString(3));
                   loc.setTiCaja(rs.getString(4));
                   loc.setDeLocal(rs.getString(5));             
                 
            }
       }catch(SQLException ex){
           ex.printStackTrace();
       }
       return loc;
    }
    
     public ArrayList<Local> getRegistrosMn(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Local> rgs = new ArrayList<Local>();        
        try {
            
            if(id != null)
            { 
               this.numRegistros = this.getNumeroRegistros(Local.nt, Local.COLUMNA_ACTIVO, Local.COLUMNA_ACTIVO, id);
            }else
            {
               this.numRegistros = this.getNumeroRegistros(Local.nt, Local.COLUMNA_ACTIVO);               
               if(rs.isClosed())
               {
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistrosMn(Local.nt,campos,columnaId,id);
            if(this.numRegistros<finalPag)
           {
              finalPag =  this.numRegistros;
           }
            if(finalPag>inicioPag)
            {
                inicioPag -= (finalPag-inicioPag)-1;
            }
            Local loc = null;
            while(rs.next())
            {
                   local = new Local();
                   loc.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2)});
                   loc.setCoCompania(rs.getString(1));
                   loc.setCoLocal(rs.getString(2));
                   loc.setTiLocal(rs.getString(3));
                   loc.setTiCaja(rs.getString(4));
                   loc.setDeLocal(rs.getString(5)); 
                   
                 rgs.add(loc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }
     
    public Local getInfoLocal()
     {
        Local loc = null;        
        try {                                    
            
            StringBuffer query = new StringBuffer();
            query.append("SELECT LOC.CO_COMPANIA             , \n");
            query.append("       LOC.CO_LOCAL                , \n");
            query.append("       COM.DE_COMPANIA             , \n");
            query.append("       TRIM(LOC.DE_LOCAL)          , \n");
            query.append("       TRIM(LOC.DE_DIRECCION_LOCAL), \n");
            query.append("       TRIM(LOC.NU_TEL_NORMAL)     , \n");
            query.append("       COM.NU_RUC_COMPANIA         , \n");
            query.append("       TRIM(DIST.DE_DISTRITO)      , \n");
            query.append("       TRIM(PROV.DE_PROVINCIA)     , \n");
            query.append("       TRIM(DEP.DE_DEPARTAMENTO)     \n");
            query.append("FROM   VTTM_LOCAL LOC     , \n");
            query.append("       CMTM_COMPANIA COM  , \n");
            query.append("       CMTR_DISTRITO DIST , \n");
            query.append("       CMTR_PROVINCIA PROV, \n");
            query.append("       CMTR_DEPARTAMENTO DEP \n");
            query.append("WHERE  LOC.CO_COMPANIA      ='").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("AND    LOC.CO_LOCAL         ='").append(AtuxVariables.vCodigoLocal).append("'\n");
            query.append("AND    DIST.CO_DEPARTAMENTO = LOC.CO_DEPARTAMENTO \n");
            query.append("AND    DIST.CO_PROVINCIA    = LOC.CO_PROVINCIA \n");
            query.append("AND    DIST.CO_DISTRITO     = LOC.CO_DISTRITO \n");
            query.append("AND    LOC.CO_COMPANIA      = COM.CO_COMPANIA \n");
            query.append("AND    PROV.CO_PROVINCIA    = DIST. CO_PROVINCIA \n");
            query.append("AND    PROV.CO_DEPARTAMENTO = DIST.CO_DEPARTAMENTO \n");
            query.append("AND    DEP.CO_DEPARTAMENTO  = DIST.CO_DEPARTAMENTO \n");
            
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                   loc = new Local();
                   loc.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2)});
                   loc.setCoCompania(rs.getString(1));
                   loc.setCoLocal(rs.getString(2));     
                   loc.setCompania(new CLocal().getCompaniaPorPk(new Object[]{rs.getString(1)}));
                   loc.setDeLocal(rs.getString(4));                 
                   loc.setDeDireccionLocal(rs.getString(5));
                   loc.setNuTelNormal(rs.getString(6));
                   loc.setDeDistrito(rs.getString(8));
                   loc.setDeProvincia(rs.getString(9));
                   loc.setDeDepartamento(rs.getString(10));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return loc;
    } 
     
    public ArrayList getProductosComplementarios(String coProducto)
    {
        ArrayList<Local> rgs = new ArrayList<Local>();                
        Local      loc      = null;        
        StringBuffer  query;
        try {            
            query = new StringBuffer();
            query.append("SELECT T1.CO_LOCAL, ");
            query.append("       TRIM(T2.DE_LOCAL), ");
            query.append("       T1.VA_SALDO, ");            
            query.append("       TRIM(DEP.DE_DEPARTAMENTO), ");            
            query.append("       TRIM(PROV.DE_PROVINCIA), ");            
            query.append("       TRIM(DIS.DE_DISTRITO) ");
            query.append("       FROM LOGISTICA.LGTT_SALDO_PROD_LOCAL T1, ");
            query.append("                       (SELECT CO_LOCAL, DE_LOCAL, CO_DEPARTAMENTO, CO_PROVINCIA, CO_DISTRITO, ES_LOCAL, TI_LOCAL, 1 ORDEN ");
            query.append("                          FROM VTTM_LOCAL ");
            query.append("                         WHERE CO_COMPANIA ='").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                           AND CO_DEPARTAMENTO || CO_PROVINCIA || CO_DISTRITO IN (SELECT CO_DEPARTAMENTO || CO_PROVINCIA || CO_DISTRITO ");
            query.append("                                                                                  FROM VTTM_LOCAL ");
            query.append("                                                                                  WHERE CO_COMPANIA= '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                                                  AND CO_LOCAL   = '").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                                                                                  ) ");
            query.append("                        UNION ");
            query.append("                        SELECT CO_LOCAL, DE_LOCAL, CO_DEPARTAMENTO, CO_PROVINCIA, CO_DISTRITO, ES_LOCAL, TI_LOCAL, 2 ORDEN ");
            query.append("                          FROM VTTM_LOCAL ");
            query.append("                         WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                           AND CO_DEPARTAMENTO || CO_PROVINCIA IN (SELECT CO_DEPARTAMENTO || CO_PROVINCIA ");
            query.append("                                                                   FROM VTTM_LOCAL ");
            query.append("                                                                   WHERE CO_COMPANIA= '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                                   AND CO_LOCAL   = '").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                                                                         ) ");
            query.append("                           AND CO_LOCAL NOT IN (SELECT CO_LOCAL ");
            query.append("                                                  FROM VTTM_LOCAL ");
            query.append("                                                 WHERE CO_COMPANIA ='").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                   AND CO_DEPARTAMENTO || CO_PROVINCIA || CO_DISTRITO IN (SELECT CO_DEPARTAMENTO || CO_PROVINCIA || CO_DISTRITO ");
            query.append("                                                                                                          FROM VTTM_LOCAL ");
            query.append("                                                                                                          WHERE CO_COMPANIA= '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                                                                          AND CO_LOCAL   = '").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                                                                                                         )) ");
            query.append("                        UNION ");
            query.append("                        SELECT CO_LOCAL, DE_LOCAL, CO_DEPARTAMENTO, CO_PROVINCIA, CO_DISTRITO, ES_LOCAL, TI_LOCAL, 3 ORDEN ");
            query.append("                          FROM VTTM_LOCAL ");
            query.append("                         WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                           AND CO_DEPARTAMENTO IN (SELECT CO_DEPARTAMENTO ");
            query.append("                                                   FROM VTTM_LOCAL ");
            query.append("                                                   WHERE CO_COMPANIA= '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                   AND CO_LOCAL   = '").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                                                      ) ");
            query.append("                           AND CO_LOCAL NOT IN (SELECT CO_LOCAL ");
            query.append("                                                  FROM VTTM_LOCAL ");
            query.append("                                                 WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                   AND CO_DEPARTAMENTO || CO_PROVINCIA || CO_DISTRITO IN (SELECT CO_DEPARTAMENTO || CO_PROVINCIA || CO_DISTRITO ");
            query.append("                                                                                                          FROM VTTM_LOCAL ");
            query.append("                                                                                                          WHERE CO_COMPANIA= '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                                                                          AND CO_LOCAL   = '").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                                                                                                             ) ");
            query.append("                                                UNION ");
            query.append("                                                SELECT CO_LOCAL ");
            query.append("                                                  FROM VTTM_LOCAL ");
            query.append("                                                 WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                   AND CO_DEPARTAMENTO || CO_PROVINCIA IN (SELECT CO_DEPARTAMENTO || CO_PROVINCIA ");
            query.append("                                                                                           FROM VTTM_LOCAL ");
            query.append("                                                                                           WHERE CO_COMPANIA= '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                                                           AND CO_LOCAL   = '").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                                                                                              ) ");
            query.append("                                                   AND CO_LOCAL NOT IN (SELECT CO_LOCAL ");
            query.append("                                                                          FROM VTTM_LOCAL ");
            query.append("                                                                         WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                                           AND CO_DEPARTAMENTO || CO_PROVINCIA || CO_DISTRITO IN (SELECT CO_DEPARTAMENTO || CO_PROVINCIA || CO_DISTRITO ");
            query.append("                                                                                                                                  FROM VTTM_LOCAL ");
            query.append("                                                                                                                                  WHERE CO_COMPANIA= '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                                                                                                  AND CO_LOCAL   = '").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                                                                                                                                  ))) ");
            query.append("                        UNION ");
            query.append("                        SELECT CO_LOCAL, DE_LOCAL, CO_DEPARTAMENTO, CO_PROVINCIA, CO_DISTRITO, ES_LOCAL, TI_LOCAL, 4 ORDEN ");
            query.append("                          FROM VTTM_LOCAL ");
            query.append("                         WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                           AND CO_LOCAL NOT IN (SELECT CO_LOCAL ");
            query.append("                                                  FROM VTTM_LOCAL ");
            query.append("                                                 WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                   AND CO_DEPARTAMENTO || CO_PROVINCIA || CO_DISTRITO IN (SELECT CO_DEPARTAMENTO || CO_PROVINCIA || CO_DISTRITO ");
            query.append("                                                                                                          FROM VTTM_LOCAL ");
            query.append("                                                                                                          WHERE CO_COMPANIA= '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                                                                            AND CO_LOCAL   = '").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                                                                                                          ) ");
            query.append("                                                UNION ");
            query.append("                                                SELECT CO_LOCAL ");
            query.append("                                                  FROM VTTM_LOCAL ");
            query.append("                                                 WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                   AND CO_DEPARTAMENTO || CO_PROVINCIA IN (SELECT CO_DEPARTAMENTO || CO_PROVINCIA ");
            query.append("                                                                                             FROM VTTM_LOCAL ");
            query.append("                                                      WHERE CO_COMPANIA= '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                        AND CO_LOCAL   = '").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                                                                                              ) ");
            query.append("                                                        AND CO_LOCAL NOT IN (SELECT CO_LOCAL ");
            query.append("                                                                             FROM VTTM_LOCAL ");
            query.append("                                                                              WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                                                AND CO_DEPARTAMENTO || CO_PROVINCIA || CO_DISTRITO IN (SELECT CO_DEPARTAMENTO || CO_PROVINCIA || CO_DISTRITO ");
            query.append("                                                                                                                                  FROM VTTM_LOCAL ");
            query.append("                                                                                                                                  WHERE CO_COMPANIA= '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                                                                                                    AND CO_LOCAL   = '").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                                                                                                                                     )) ");
            query.append("                                                UNION ");
            query.append("                                                SELECT CO_LOCAL ");
            query.append("                                                  FROM VTTM_LOCAL ");
            query.append("                                                 WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                   AND CO_DEPARTAMENTO IN (SELECT CO_DEPARTAMENTO ");
            query.append("                                                                             FROM VTTM_LOCAL ");
            query.append("                                                      WHERE CO_COMPANIA= '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                        AND CO_LOCAL   = '").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                                                                              ) ");
            query.append("                                                   AND CO_LOCAL NOT IN (SELECT CO_LOCAL ");
            query.append("                                                                          FROM VTTM_LOCAL ");
            query.append("                                                                         WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                                           AND CO_DEPARTAMENTO || CO_PROVINCIA IN (SELECT CO_DEPARTAMENTO || CO_PROVINCIA ");
            query.append("                                                                                                                   FROM VTTM_LOCAL ");
            query.append("                                                                                                                   WHERE CO_COMPANIA= '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                                                                                     AND CO_LOCAL   = '").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                                                                                                                   ) ");
            query.append("                                                        AND CO_LOCAL NOT IN (SELECT CO_LOCAL ");
            query.append("                                                                             FROM VTTM_LOCAL ");
            query.append("                                                                             WHERE CO_COMPANIA ='").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                                               AND CO_DEPARTAMENTO || CO_PROVINCIA || CO_DISTRITO IN (SELECT CO_DEPARTAMENTO || CO_PROVINCIA || CO_DISTRITO ");
            query.append("                                                                                                                                      FROM VTTM_LOCAL ");
            query.append("                                                                                                                                      WHERE CO_COMPANIA= '").append(AtuxVariables.vCodigoCompania).append("'");
            query.append("                                                                                                                                        AND CO_LOCAL   = '").append(AtuxVariables.vCodigoLocal).append("'");
            query.append("                                                                                                                                        )))) ) T2, ");
            query.append("                                                      CMTR_DEPARTAMENTO DEP, ");
            query.append("                                                      CMTR_PROVINCIA         PROV, ");
            query.append("                                                      CMTR_DISTRITO          DIS ");
            query.append("                WHERE T1.CO_LOCAL        = T2.CO_LOCAL ");
            query.append("                  AND T1.CO_PRODUCTO     = '").append(coProducto).append("'");            
            query.append("                  AND DEP.CO_PAIS        = '001' ");
            query.append("                  AND T2.CO_DEPARTAMENTO = DEP.CO_DEPARTAMENTO ");
            query.append("                  AND PROV.CO_PAIS       = '001' ");
            query.append("                  AND DEP.CO_DEPARTAMENTO= PROV.CO_DEPARTAMENTO ");
            query.append("                  AND T2.CO_PROVINCIA = PROV.CO_PROVINCIA ");
            query.append("                  AND DIS.CO_PAIS        = '001' ");
            query.append("                  AND T2.CO_DEPARTAMENTO = DIS.CO_DEPARTAMENTO ");
            query.append("                  AND T2.CO_PROVINCIA    = DIS.CO_PROVINCIA ");
            query.append("                  AND T2.CO_DISTRITO     = DIS.CO_DISTRITO ");
            query.append("                  AND ES_LOCAL           = 'A' ");
            query.append("                  AND TI_LOCAL           = 'V' ");
            query.append("                ORDER BY ORDEN, T2.CO_DEPARTAMENTO, T2.CO_PROVINCIA, T2.CO_DISTRITO, T2.DE_LOCAL\n");
                          
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                   loc = new Local();
                   loc.setCoLocal(rs.getString(1));
                   loc.setDeLocal(rs.getString(2));
                   loc.setSaldo(rs.getDouble(3));
                   loc.setDeDepartamento(rs.getString(4));
                   loc.setDeProvincia(rs.getString(5));
                   loc.setDeDistrito(rs.getString(6));
                 rgs.add(loc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    } 
    
    public Local getInfoParametrosPedidoRep()
     {
        Local loc = null;        
        try {                                    
            
            StringBuffer query = new StringBuffer();
            query.append("SELECT CO_COMPANIA                , \n");
            query.append("       CO_LOCAL                   , \n");
            query.append("       NU_DIAS_ROTACION_PROMEDIO  , \n");
            query.append("       NU_MIN_DIAS_REPOSICION     , \n");
            query.append("       NU_MAX_DIAS_REPOSICION     , \n");
            query.append("       IN_IGNORAR_PROD_SIN_SALDO  , \n");
            query.append("       IN_SUMAR_TIEMPO_SUMINISTRO , \n");
            query.append("       IN_SUMAR_TRANSITO          , \n");
            query.append("       IN_SUMAR_MIN_EXHIBICION    , \n");
            query.append("       IN_SUMAR_COMPRAS_PENDIENTES, \n");
            query.append("       IN_TIPO_OPERACION          , \n");
            query.append("       IN_ORIGEN_PRODUCTOS        , \n");
            query.append("       IN_SOLO_PROD_ACTIVOS       , \n");
            query.append("       IN_PRODUCTOS_FRACCIONADOS  , \n");
            query.append("       IN_FALTA_CERO                \n");            
            query.append("FROM   VTTM_LOCAL a \n");            
            query.append("WHERE  CO_COMPANIA='").append(AtuxVariables.vCodigoCompania).append("'\n");
            query.append("AND    CO_LOCAL   ='").append(AtuxVariables.vCodigoLocal).append("'\n");

            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                   loc = new Local();
                   loc.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2)});
                   loc.setCoCompania(rs.getString(1));
                   loc.setCoLocal(rs.getString(2));     
                   loc.setNuDiasRotacionPromedio(rs.getInt(3));
                   loc.setNuMinDiasReposicion(rs.getInt(4));
                   loc.setNuMaxDiasReposicion(rs.getInt(5));
                   loc.setInIgnorarProdSinSaldo(rs.getString(6));
                   loc.setInSumarTiempoSuministro(rs.getString(7));
                   loc.setInSumarTransito(rs.getString(8));
                   loc.setInSumarMinExhibicion(rs.getString(9));
                   loc.setInSumarComprasPendientes(rs.getString(10));
                   loc.setInTipoOperacion(rs.getString(11));
                   loc.setInOrigenProductos(rs.getString(12));
                   loc.setInSoloProdActivos(rs.getString(13));
                   loc.setInProductosFraccionados(rs.getString(14));
                   loc.setInFaltaCero(rs.getString(15));                   
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return loc;
    }
    
    public boolean existeCodigo(String codigo)
    {
        return this.existe(Local.nt, "CO_LOCAL", codigo);
    }        
    
    public String getCodigoLocal()
    {
        return this.getCodigo(Local.nt, "CO_LOCAL");
    }        
    
    public int getNumeroRegistros()
    {
        return this.getNumeroRegistros(Local.nt, "CO_LOCAL");
    }
    
    public int activarLocal(Local local)
    {
        return this.eliminacionTemporal(Local.nt, Local.COLUMNA_ACTIVO, Local.COLUMNA_PK, local.getPrimaryKey(), 1);
    }
    
}
