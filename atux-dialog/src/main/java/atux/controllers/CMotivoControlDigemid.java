package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.MotivoControlDigemid;
import atux.util.common.AtuxDBUtility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CMotivoControlDigemid extends JAbstractController{
    private MotivoControlDigemid mControlDig;
   
    
    @Override
    public ArrayList<MotivoControlDigemid> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{MotivoControlDigemid.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<MotivoControlDigemid> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<MotivoControlDigemid> getRegistros() {                 
        ArrayList<MotivoControlDigemid> rgs = new ArrayList<MotivoControlDigemid>();
        try {                        
//            rs = this.getRegistros(TipoDeCambio.nt,TipoDeCambio.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(MotivoControlDigemid.nt, MotivoControlDigemid.FULL_NOM_CAMPOS, null, null, MotivoControlDigemid.COLUMNA_ORDER);
            while(rs.next())
            {
                mControlDig = new MotivoControlDigemid();
                mControlDig.setPrimaryKey(new String[]{rs.getString("CO_MOTIVO")});
                mControlDig.setCoMotivo(rs.getString("CO_MOTIVO"));
                mControlDig.setDeMotivo(rs.getString("DE_MOTIVO"));
                mControlDig.setDeMotivoCorta(rs.getString("DE_MOTIVO_CORTA"));
                rgs.add(mControlDig);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public MotivoControlDigemid getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(MotivoControlDigemid.nt, MotivoControlDigemid.FULL_NOM_CAMPOS, MotivoControlDigemid.COLUMNA_PK , id);
            while(rs.next())
            {
                mControlDig = new MotivoControlDigemid();
                mControlDig.setPrimaryKey(new String[]{rs.getString("CO_MOTIVO")});
                mControlDig.setCoMotivo(rs.getString("CO_MOTIVO"));
                mControlDig.setDeMotivo(rs.getString("DE_MOTIVO"));
                mControlDig.setDeMotivoCorta(rs.getString("DE_MOTIVO_CORTA"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mControlDig;
    }
    
    @Override
    public ArrayList<MotivoControlDigemid> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<MotivoControlDigemid> rgs = new ArrayList<MotivoControlDigemid>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(MotivoControlDigemid.nt, MotivoControlDigemid.COLUMNA_ACTIVO, MotivoControlDigemid.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(MotivoControlDigemid.nt, MotivoControlDigemid.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(MotivoControlDigemid.nt, campos, columnaId, id, MotivoControlDigemid.COLUMNA_ORDER);
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
                mControlDig = new MotivoControlDigemid();
                mControlDig.setPrimaryKey(new String[]{rs.getString("CO_MOTIVO")});
                mControlDig.setCoMotivo(rs.getString("CO_MOTIVO"));
                mControlDig.setDeMotivo(rs.getString("DE_MOTIVO"));
                mControlDig.setDeMotivoCorta(rs.getString("DE_MOTIVO_CORTA"));
                rgs.add(mControlDig);
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

    public MotivoControlDigemid getMotivoControlDig() {
        if(mControlDig == null)
        {
            mControlDig = new MotivoControlDigemid();
        }
        return mControlDig;
    }

    public void setMotivoControlDig(JAbstractModelBD prv) {
        this.mControlDig = (MotivoControlDigemid)prv;
    }

    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
