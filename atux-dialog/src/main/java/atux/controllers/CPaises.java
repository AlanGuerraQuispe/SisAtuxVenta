package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.Paises;
import java.sql.SQLException;
import java.util.ArrayList;


public class CPaises extends JAbstractController{
    private Paises Pais;
   
    
    @Override
    public ArrayList<Paises> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{Paises.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<Paises> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<Paises> getRegistros() {                 
        ArrayList<Paises> rgs = new ArrayList<Paises>();
        try {                        
//            rs = this.getRegistros(TipoDeCambio.nt,TipoDeCambio.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(Paises.nt, Paises.FULL_NOM_CAMPOS, null, null, Paises.COLUMNA_ORDER);
            while(rs.next())
            {
                Pais = new Paises();
                Pais.setPrimaryKey(new String[]{rs.getString("CO_PAIS")});    
                Pais.setCoPais(rs.getString("CO_PAIS"));
                Pais.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                Pais.setDeCortaPais(rs.getString("DE_CORTA_PAIS"));
                Pais.setDePais(rs.getString("DE_PAIS"));
                Pais.setEsPais(rs.getString("ES_PAIS"));
                Pais.setIdCreaPais(rs.getString("ID_CREA_PAIS"));
                Pais.setFeCreaPais(rs.getDate("FE_CREA_PAIS"));
                Pais.setIdModPais(rs.getString("ID_MOD_PAIS"));
                Pais.setFeModPais(rs.getDate("FE_MOD_PAIS"));
                rgs.add(Pais);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public Paises getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(Paises.nt, Paises.FULL_NOM_CAMPOS, Paises.COLUMNA_PK , id);
            while(rs.next())
            {
                Pais = new Paises();
                Pais.setPrimaryKey(new String[]{rs.getString("CO_PAIS")});    
                Pais.setCoPais(rs.getString("CO_PAIS"));
                Pais.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                Pais.setDeCortaPais(rs.getString("DE_CORTA_PAIS"));
                Pais.setDePais(rs.getString("DE_PAIS"));
                Pais.setEsPais(rs.getString("ES_PAIS"));
                Pais.setIdCreaPais(rs.getString("ID_CREA_PAIS"));
                Pais.setFeCreaPais(rs.getDate("FE_CREA_PAIS"));
                Pais.setIdModPais(rs.getString("ID_MOD_PAIS"));
                Pais.setFeModPais(rs.getDate("FE_MOD_PAIS"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Pais;
    }
    
    
    public Paises getPaises() {
        if(Pais == null)
        {
            Pais = new Paises();
        }
        return Pais;
    }    
    
    @Override
    public ArrayList<Paises> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Paises> rgs = new ArrayList<Paises>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(Paises.nt, Paises.COLUMNA_ACTIVO, Paises.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(Paises.nt, Paises.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(Paises.nt, campos, columnaId, id, Paises.COLUMNA_ORDER);
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
                Pais = new Paises();
                Pais.setPrimaryKey(new String[]{rs.getString("CO_PAIS")});    
                Pais.setCoPais(rs.getString("CO_PAIS"));
                Pais.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                Pais.setDeCortaPais(rs.getString("DE_CORTA_PAIS"));
                Pais.setDePais(rs.getString("DE_PAIS"));
                Pais.setEsPais(rs.getString("ES_PAIS"));
                Pais.setIdCreaPais(rs.getString("ID_CREA_PAIS"));
                Pais.setFeCreaPais(rs.getDate("FE_CREA_PAIS"));
                Pais.setIdModPais(rs.getString("ID_MOD_PAIS"));
                Pais.setFeModPais(rs.getDate("FE_MOD_PAIS"));
                rgs.add(Pais);
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
        Pais = (Paises)mdl;
        int gravado = 0;
        String campos = Paises.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {Pais.getCoPais(),
                            Pais.getNuOrdenFila(),
                            Pais.getDeCortaPais(),
                            Pais.getDePais(),
                            Pais.getEsPais(),
                            Pais.getIdCreaPais(),
                            Pais.getFeCreaPais(),
                            Pais.getIdModPais(),
                            Pais.getFeModPais()
                           };
       
           gravado = this.agregarRegistroPs(Pais.getNombreTabla(),Paises.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        Pais = (Paises)mdl;
        int gravado = 0;        
        
        Object[] valores = {Pais.getNuOrdenFila(),
                            Pais.getDeCortaPais(),
                            Pais.getDePais(),
                            Pais.getEsPais(),
                            Pais.getIdCreaPais(),
                            Pais.getFeCreaPais(),
                            Pais.getIdModPais(),
                            Pais.getFeModPais()
                           };

        gravado = this.actualizarRegistroPs(Pais.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(Paises.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(Paises.COLUMNA_PK, Pais.getPrimaryKey()) , valores);
        return gravado;
    }

    public Paises getTipodeDocumento() {
        if(Pais == null)
        {
            Pais = new Paises();
        }
        return Pais;
    }

    public void setPaises(JAbstractModelBD prv) {
        this.Pais = (Paises)prv;
    }
}
