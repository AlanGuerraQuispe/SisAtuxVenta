package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.Moneda;
import atux.modelbd.SimpleModelo;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;


public class CMoneda extends JAbstractController implements Serializable{
    
    private Moneda predeterminado;
    //@Override
    //public ArrayList getRegistros() {
         //return this.getRegistros(new String[]{},null,null);
    //}
    public ArrayList<SimpleModelo> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }
    @Override
    public ArrayList getRegistros(Object[] cvl) {
        return this.getRegistros(new String[]{}, cvl!=null?new String[]{Moneda.COLUMNA_ACTIVO}:null,cvl);
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
        Moneda sm = (Moneda)mdl;
        int gravado = 0;
        String campos = "nombre, simbolo, activo,isdefault";        
        Object[] valores = {sm.getNombre(),sm.getSimbolo(),sm.getActivo(),sm.getPredeterminado()};
       
         gravado = this.agregarRegistroPs(sm.getNombreTabla(), this.stringToArray(campos, ","), valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
         Moneda sm = (Moneda)mdl;
        int gravado = 0;
        String campos = "nombre, simbolo, activo,isdefault";        
        Object[] valores = {sm.getNombre(),sm.getSimbolo(),sm.getActivo(),sm.getPredeterminado(),sm.getPrimaryKey()};
        
        gravado = this.actualizarRegistroPs(sm.getNombreTabla(), this.adjuntarSimbolo(campos, ",", "?")+Ex.WHERE+mdl.getCampoClavePrimaria()+" = ? ", valores);
         System.out.println("grabadoo "+gravado); 
        return gravado;
    }
    
     public Moneda getRegistroPorPk(Object[] id)
    {
        Moneda sm = null;
        try {            
            rs = this.selectPorPk(Moneda.TABLA,null, Moneda.PK_COLUMNA, id);
            
            while(rs.next())
            {
                 sm = new Moneda();
                 sm.setPrimaryKey(new String[]{rs.getString(1)});                 
                 sm.setNombre(rs.getString(2));
                 sm.setSimbolo(rs.getString(3));
                 sm.setActivo(rs.getInt(4));
                 sm.setPredeterminado(rs.getInt(5));
                 if(sm.getPredeterminadoBool())
                 {
                     this.predeterminado = sm;
                 }
                 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sm;
    }

    @Override
    public ArrayList getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Moneda> rgs = new ArrayList<Moneda>();        
        try {
            
            if(id != null)
            { 
               this.numRegistros = this.getNumeroRegistros(Moneda.TABLA, Moneda.COLUMNA_ACTIVO, SimpleModelo.ACTIVO, id);
            }else
            {
               this.numRegistros = this.getNumeroRegistros(Moneda.TABLA, Moneda.COLUMNA_ACTIVO);               
               if(rs.isClosed())
               {
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(Moneda.TABLA,campos,columnaId,id,null);
            
            if(this.numRegistros<finalPag)
           {
              finalPag =  this.numRegistros;
           }
            if(finalPag>inicioPag)
            {
                inicioPag -= (finalPag-inicioPag)-1;
            }
            Moneda sm = null;
            while(rs.next())
            {
                 sm = new Moneda();
                 sm.setPrimaryKey(new String[]{rs.getString(1)});                 
                 sm.setNombre(rs.getString(2));
                 sm.setSimbolo(rs.getString(3));
                 sm.setActivo(rs.getInt(4));
                 sm.setPredeterminado(rs.getInt(5));
                 if(sm.getPredeterminadoBool())
                 {
                     this.predeterminado = sm;
                 }
                 rgs.add(sm);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }
    
    @Override
    public ArrayList getRegistros() {
        ArrayList<Moneda> rgs = new ArrayList<Moneda>();        
        try {
                        
            rs = this.getRegistros(Moneda.TABLA,Moneda.FULL_NOM_CAMPOS);
                       
            Moneda moneda = null;
            while(rs.next())
            {
                 moneda = new Moneda();
                 moneda.setPrimaryKey(new String[]{rs.getString(1)});                     
                 moneda.setCoMoneda(rs.getString(1));
                 moneda.setNombre(rs.getString(4));
                 moneda.setSimbolo(rs.getString(5));
                 //moneda.setActivo(rs.getInt(4));
                 moneda.setPredeterminado(rs.getInt(2));
                 if(moneda.getPredeterminadoBool())
                 {
                     this.predeterminado = moneda;
                 }
                 rgs.add(moneda);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public Moneda getPredeterminado() {
        return predeterminado;
    }

    public Moneda getMoneda() {
        if(predeterminado == null)
        {
            predeterminado = new Moneda();
        }
        return predeterminado;
    }


}
