package atux.controllers;

import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.Ubigeo;
import java.sql.SQLException;
import java.util.ArrayList;

public class CUbigeo extends JAbstractController{
    private Ubigeo tUbigeo;

    public ArrayList getDepartamento(String Buscar)
     {
        ArrayList<Ubigeo> rgs = new ArrayList<Ubigeo>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT CO_DEPARTAMENTO, ");
            query.append("       DE_DEPARTAMENTO ");
            query.append("  FROM CMTR_DEPARTAMENTO ");
            if (Buscar != null){
                query.append("  WHERE DE_DEPARTAMENTO LIKE '%").append(Buscar).append("%' ");
            }
            query.append(" ORDER BY 2");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                tUbigeo = new Ubigeo();
                tUbigeo.setCoUbigeo(rs.getString("CO_DEPARTAMENTO"));
                tUbigeo.setDeUbigeo(rs.getString("DE_DEPARTAMENTO"));
                rgs.add(tUbigeo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }    

    public ArrayList getBuscarDepartamento(String Buscar)
     {
        ArrayList<Ubigeo> rgs = new ArrayList<Ubigeo>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT CO_DEPARTAMENTO, ");
            query.append("       DE_DEPARTAMENTO ");
            query.append("  FROM CMTR_DEPARTAMENTO ");
            if (Buscar != null){
                query.append("  WHERE CO_DEPARTAMENTO = '").append(Buscar).append("' ");
            }
            query.append(" ORDER BY 2");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                tUbigeo = new Ubigeo();
                tUbigeo.setCoUbigeo(rs.getString("CO_DEPARTAMENTO"));
                tUbigeo.setDeUbigeo(rs.getString("DE_DEPARTAMENTO"));
                rgs.add(tUbigeo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }    
    
    public ArrayList getProvincia(String Buscar, String CodigoDepartamento)
     {
        ArrayList<Ubigeo> rgs = new ArrayList<Ubigeo>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT CO_PROVINCIA, ");
            query.append("       DE_PROVINCIA ");
            query.append("  FROM CMTR_PROVINCIA ");
            query.append(" WHERE CO_DEPARTAMENTO = '").append(CodigoDepartamento).append("' ");
            if (Buscar != null){
                query.append("  AND DE_Provincia LIKE '%").append(Buscar).append("%' ");
            }            
            query.append(" ORDER BY 2");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                tUbigeo = new Ubigeo();
                tUbigeo.setCoUbigeo(rs.getString("CO_PROVINCIA"));
                tUbigeo.setDeUbigeo(rs.getString("DE_PROVINCIA"));
                rgs.add(tUbigeo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }    

    public ArrayList getBuscarProvincia(String CodigoDepartamento, String CodigoProvincia )
     {
        ArrayList<Ubigeo> rgs = new ArrayList<Ubigeo>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT CO_PROVINCIA, ");
            query.append("       DE_PROVINCIA ");
            query.append("  FROM CMTR_PROVINCIA ");
            query.append(" WHERE CO_DEPARTAMENTO = '").append(CodigoDepartamento).append("' ");
            query.append("  AND CO_Provincia = '").append(CodigoProvincia).append("' ");
            query.append(" ORDER BY 2");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                tUbigeo = new Ubigeo();
                tUbigeo.setCoUbigeo(rs.getString("CO_PROVINCIA"));
                tUbigeo.setDeUbigeo(rs.getString("DE_PROVINCIA"));
                rgs.add(tUbigeo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }    

    public ArrayList getDistrito(String Buscar, String CodigoDepartamento, String CodigoProvincia)
     {
        ArrayList<Ubigeo> rgs = new ArrayList<Ubigeo>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT CO_DISTRITO, ");
            query.append("       DE_DISTRITO ");
            query.append("  FROM CMTR_DISTRITO ");
            query.append(" WHERE CO_DEPARTAMENTO = '").append(CodigoDepartamento).append("' ");
            query.append("   AND CO_PROVINCIA = '").append(CodigoProvincia).append("' ");
            if (Buscar != null){
                query.append("  AND DE_DISTRITO LIKE '%").append(Buscar).append("%' ");
            }            
            query.append(" ORDER BY 2");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                tUbigeo = new Ubigeo();
                tUbigeo.setCoUbigeo(rs.getString("CO_DISTRITO"));
                tUbigeo.setDeUbigeo(rs.getString("DE_DISTRITO"));
                rgs.add(tUbigeo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }    

    public ArrayList getBuscarDistrito(String CodigoDepartamento, String CodigoProvincia, String CodigoDistrito)
     {
        ArrayList<Ubigeo> rgs = new ArrayList<Ubigeo>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT CO_DISTRITO, ");
            query.append("       DE_DISTRITO ");
            query.append("  FROM CMTR_DISTRITO ");
            query.append(" WHERE CO_DEPARTAMENTO = '").append(CodigoDepartamento).append("' ");
            query.append("   AND CO_PROVINCIA = '").append(CodigoProvincia).append("' ");
            query.append("  AND CO_DISTRITO = '").append(CodigoDistrito).append("' ");
            query.append(" ORDER BY 2");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                tUbigeo = new Ubigeo();
                tUbigeo.setCoUbigeo(rs.getString("CO_DISTRITO"));
                tUbigeo.setDeUbigeo(rs.getString("DE_DISTRITO"));
                rgs.add(tUbigeo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }
    
    public String BuscarUbigeo(String CodigoDepartamento, String CodigoProvincia, String CodigoDistrito){
        CUbigeo BG1 = new CUbigeo();
        if (CodigoDistrito != null){
            BG1.getBuscarDistrito(CodigoDepartamento,CodigoProvincia,CodigoDistrito);
            return BG1.getUbigeo().getDeUbigeo().trim();
        }else if (CodigoProvincia != null){
            BG1.getBuscarProvincia(CodigoDepartamento,CodigoProvincia);
            return BG1.getUbigeo().getDeUbigeo().trim();
        }else if (CodigoDepartamento != null){
            BG1.getBuscarDepartamento(CodigoDepartamento);
            return BG1.getUbigeo().getDeUbigeo().trim();
        }
        return "";
    }
    
    public Ubigeo getUbigeo() {
        if(tUbigeo == null)
        {
            tUbigeo = new Ubigeo();
        }
        return tUbigeo;
    }

    public void setUbigeo(JAbstractModelBD prv) {
        this.tUbigeo = (Ubigeo)prv;
    }
    
    @Override
    public ArrayList getRegistros() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList getRegistros(Object[] cvl) {
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

    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList getRegistros(String[] campos, String[] columnaId, Object[] id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
