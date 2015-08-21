package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.TipoDeCambio;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CTipoDeCambio extends JAbstractController{
    private TipoDeCambio tCambio;
   
    
    @Override
    public ArrayList<TipoDeCambio> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{TipoDeCambio.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<TipoDeCambio> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<TipoDeCambio> getRegistros() {                 
        ArrayList<TipoDeCambio> rgs = new ArrayList<TipoDeCambio>();
        try {                        
//            rs = this.getRegistros(TipoDeCambio.nt,TipoDeCambio.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(TipoDeCambio.nt, TipoDeCambio.FULL_NOM_CAMPOS, null, null, TipoDeCambio.COLUMNA_ORDER);
            while(rs.next())
            {
                tCambio = new TipoDeCambio();
                tCambio.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("CO_MONEDA"), rs.getString("NU_SEC_TIPO_CAMBIO")});    
                tCambio.setCoCompania(rs.getString("CO_COMPANIA"));
                tCambio.setCoMoneda(rs.getString("CO_MONEDA"));
                tCambio.setNuSecTipoCambio(rs.getString("NU_SEC_TIPO_CAMBIO"));
                tCambio.setCoMonedaPais(rs.getString("CO_MONEDA_PAIS"));
                tCambio.setFeTipoCambio(rs.getDate("FE_TIPO_CAMBIO"));
                tCambio.setVaCambioCompraInka(rs.getDouble("VA_CAMBIO_COMPRA_INKA"));
                tCambio.setVaCambioVentaInka(rs.getDouble("VA_CAMBIO_VENTA_INKA"));
                tCambio.setEsTipoCambio(rs.getString("ES_TIPO_CAMBIO"));
                tCambio.setIdCreaTipoCambio(rs.getString("ID_CREA_TIPO_CAMBIO"));
                tCambio.setFeCreaTipoCambio(rs.getDate("FE_CREA_TIPO_CAMBIO"));
                tCambio.setIdModTipoCambio(rs.getString("ID_MOD_TIPO_CAMBIO"));
                tCambio.setFeModTipoCambio(rs.getDate("FE_MOD_TIPO_CAMBIO"));
                rgs.add(tCambio);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public TipoDeCambio getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(TipoDeCambio.nt, TipoDeCambio.FULL_NOM_CAMPOS, TipoDeCambio.COLUMNA_PK , id);
            while(rs.next())
            {
                tCambio.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("CO_MONEDA"), rs.getString("NU_SEC_TIPO_CAMBIO")});    
                tCambio.setCoCompania(rs.getString("CO_COMPANIA"));
                tCambio.setCoMoneda(rs.getString("CO_MONEDA"));
                tCambio.setNuSecTipoCambio(rs.getString("NU_SEC_TIPO_CAMBIO"));
                tCambio.setCoMonedaPais(rs.getString("CO_MONEDA_PAIS"));
                tCambio.setFeTipoCambio(rs.getDate("FE_TIPO_CAMBIO"));
                tCambio.setVaCambioCompraInka(rs.getDouble("VA_CAMBIO_COMPRA_INKA"));
                tCambio.setVaCambioVentaInka(rs.getDouble("VA_CAMBIO_VENTA_INKA"));
                tCambio.setEsTipoCambio(rs.getString("ES_TIPO_CAMBIO"));
                tCambio.setIdCreaTipoCambio(rs.getString("ID_CREA_TIPO_CAMBIO"));
                tCambio.setFeCreaTipoCambio(rs.getDate("FE_CREA_TIPO_CAMBIO"));
                tCambio.setIdModTipoCambio(rs.getString("ID_MOD_TIPO_CAMBIO"));
                tCambio.setFeModTipoCambio(rs.getDate("FE_MOD_TIPO_CAMBIO"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tCambio;
    }
    
    @Override
    public ArrayList<TipoDeCambio> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<TipoDeCambio> rgs = new ArrayList<TipoDeCambio>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(TipoDeCambio.nt, TipoDeCambio.COLUMNA_ACTIVO, TipoDeCambio.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(TipoDeCambio.nt, TipoDeCambio.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(TipoDeCambio.nt, campos, columnaId, id, TipoDeCambio.COLUMNA_ORDER);
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
                tCambio= new TipoDeCambio();
                tCambio.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("CO_MONEDA"), rs.getString("NU_SEC_TIPO_CAMBIO")});    
                tCambio.setCoCompania(rs.getString("CO_COMPANIA"));
                tCambio.setCoMoneda(rs.getString("CO_MONEDA"));
                tCambio.setNuSecTipoCambio(rs.getString("NU_SEC_TIPO_CAMBIO"));
                tCambio.setCoMonedaPais(rs.getString("CO_MONEDA_PAIS"));
                tCambio.setFeTipoCambio(rs.getDate("FE_TIPO_CAMBIO"));
                tCambio.setVaCambioCompraInka(rs.getDouble("VA_CAMBIO_COMPRA_INKA"));
                tCambio.setVaCambioVentaInka(rs.getDouble("VA_CAMBIO_VENTA_INKA"));
                tCambio.setEsTipoCambio(rs.getString("ES_TIPO_CAMBIO"));
                tCambio.setIdCreaTipoCambio(rs.getString("ID_CREA_TIPO_CAMBIO"));
                tCambio.setFeCreaTipoCambio(rs.getDate("FE_CREA_TIPO_CAMBIO"));
                tCambio.setIdModTipoCambio(rs.getString("ID_MOD_TIPO_CAMBIO"));
                tCambio.setFeModTipoCambio(rs.getDate("FE_MOD_TIPO_CAMBIO"));
                rgs.add(tCambio);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
        
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
        tCambio = (TipoDeCambio)mdl;
        int gravado = 0;
        String campos = TipoDeCambio.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {tCambio.getcoCompania(),
                            tCambio.getCoMoneda(),
                            tCambio.getNuSecTipoCambio(),
                            tCambio.getCoMonedaPais(),
                            tCambio.getFeTipoCambio(),
                            tCambio.getVaCambioCompraInka(),
                            tCambio.getVaCambioVentaInka(),
                            tCambio.getEsTipoCambio(),
                            tCambio.getIdCreaTipoCambio(),
                            tCambio.getFeCreaTipoCambio(),
                            tCambio.getIdModTipoCambio(),
                            tCambio.getFeModTipoCambio()
                           };
       
           gravado = this.agregarRegistroPs(tCambio.getNombreTabla(),TipoDeCambio.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        tCambio = (TipoDeCambio)mdl;
        int gravado = 0;        
        
        Object[] valores = {tCambio.getCoMonedaPais(),
                            tCambio.getFeTipoCambio(),
                            tCambio.getVaCambioCompraInka(),
                            tCambio.getVaCambioVentaInka(),
                            tCambio.getEsTipoCambio(),
                            tCambio.getIdCreaTipoCambio(),
                            tCambio.getFeCreaTipoCambio(),
                            tCambio.getIdModTipoCambio(),
                            tCambio.getFeModTipoCambio()
                           };

        gravado = this.actualizarRegistroPs(tCambio.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(TipoDeCambio.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(TipoDeCambio.COLUMNA_PK, tCambio.getPrimaryKey()) , valores);
        return gravado;
    }

    public TipoDeCambio getTipodeCambio() {
        if(tCambio == null)
        {
            tCambio = new TipoDeCambio();
        }
        return tCambio;
    }

    public void setTipodeCambio(JAbstractModelBD prv) {
        this.tCambio = (TipoDeCambio)prv;
    }

    public String getNuevoCodigo(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(TipoDeCambio.nt,"rtrim(ltrim(to_char(nvl(max(nu_sec_tipo_cambio),0) + 1,'00000000')))"," co_compania = '" + AtuxVariables.vCodigoCompania + "' and co_moneda ='02'");
        } catch (SQLException ex) {
            Logger.getLogger(CTipoDeCambio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
