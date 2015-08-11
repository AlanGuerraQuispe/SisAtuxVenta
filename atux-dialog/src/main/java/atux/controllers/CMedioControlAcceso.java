package atux.controllers;


import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.MedioControlAcceso;
import atux.util.common.AtuxDBUtility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CMedioControlAcceso extends JAbstractController{
    private MedioControlAcceso tMAC;
   
    
    @Override
    public ArrayList<MedioControlAcceso> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{MedioControlAcceso.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<MedioControlAcceso> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<MedioControlAcceso> getRegistros() {                 
        ArrayList<MedioControlAcceso> rgs = new ArrayList<MedioControlAcceso>();
        try {                        
//            rs = this.getRegistros(TipoDeCambio.nt,TipoDeCambio.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(MedioControlAcceso.nt, MedioControlAcceso.FULL_NOM_CAMPOS, null, null, MedioControlAcceso.COLUMNA_ORDER);
            while(rs.next())
            {
                tMAC = new MedioControlAcceso();
                tMAC.setPrimaryKey(new String[]{rs.getString("NU_PC")});
                tMAC.setNuPc(rs.getInt("NU_PC"));
                tMAC.setDeDireccionMAC(rs.getString("de_Direccion_MAC"));
                tMAC.setDeCodigoHDD(rs.getString("de_Codigo_HDD"));
                tMAC.setFeCreaReg(rs.getDate("FE_CREA_REG"));
                rgs.add(tMAC);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public MedioControlAcceso getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(MedioControlAcceso.nt, MedioControlAcceso.FULL_NOM_CAMPOS, MedioControlAcceso.COLUMNA_PK , id);
            while(rs.next())
            {
                tMAC = new MedioControlAcceso();
                tMAC.setPrimaryKey(new String[]{rs.getString("NU_PC")});
                tMAC.setNuPc(rs.getInt("NU_PC"));
                tMAC.setDeDireccionMAC(rs.getString("de_Direccion_MAC"));
                tMAC.setDeCodigoHDD(rs.getString("de_Codigo_HDD"));
                tMAC.setFeCreaReg(rs.getDate("FE_CREA_REG"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tMAC;
    }
    
    @Override
    public ArrayList<MedioControlAcceso> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<MedioControlAcceso> rgs = new ArrayList<MedioControlAcceso>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(MedioControlAcceso.nt, MedioControlAcceso.COLUMNA_ACTIVO, MedioControlAcceso.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(MedioControlAcceso.nt, MedioControlAcceso.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(MedioControlAcceso.nt, campos, columnaId, id, MedioControlAcceso.COLUMNA_ORDER);
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
                tMAC = new MedioControlAcceso();
                tMAC.setPrimaryKey(new String[]{rs.getString("NU_PC")});
                tMAC.setNuPc(rs.getInt("NU_PC"));
                tMAC.setDeDireccionMAC(rs.getString("de_Direccion_MAC"));
                tMAC.setDeCodigoHDD(rs.getString("de_Codigo_HDD"));
                tMAC.setFeCreaReg(rs.getDate("FE_CREA_REG"));
                rgs.add(tMAC);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
        
    }

    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        tMAC = (MedioControlAcceso)mdl;
        int gravado = 0;
        String campos = tMAC.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {tMAC.getNuPc(),
                            tMAC.getDeDireccionMAC(),
                            tMAC.getDeCodigoHDD(),
                            tMAC.getFeCreaReg()
                           };
       
           gravado = this.agregarRegistroPs(tMAC.getNombreTabla(),MedioControlAcceso.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    public MedioControlAcceso getMedioControlAcceso() {
        if(tMAC == null)
        {
            tMAC = new MedioControlAcceso();
        }
        return tMAC;
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
    public int actualizarRegistro(JAbstractModelBD mdl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public int getNuevoCodigo(){
        String Codigo="";
        try {
            Codigo= AtuxDBUtility.getValueAt(MedioControlAcceso.nt,"nvl(max(nu_pc),0)+1"," nu_pc is not null ");
        } catch (SQLException ex) {
            Logger.getLogger(CMedioControlAcceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.parseInt(Codigo);
    }    
}
