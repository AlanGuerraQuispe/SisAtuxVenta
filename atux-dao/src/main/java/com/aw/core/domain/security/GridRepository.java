package com.aw.core.domain.security;

/**
 * <br>
 * Funcionalidad : <br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * <br>
 * ID   PROGRAMADOR  FECHA/HORA  TIPO          OBSERVACIÓN
 * 001   JCM          30/04/2009 Creación      <br>
 * 002   JCM          06/10/2008  Modificación  <br>
 * <br>
 * <br>
 *
 * @author Julio C. Macavilca - AW<br>
 * @version 1.0<br>
 */
public interface GridRepository<GridBean, EditBean> {
    //public GridBean queryGridBean(GridBean gridBean);
    public GridBean queryGridBean(EditBean editBean);
    public EditBean queryEditBean(GridBean gridBean);
    public EditBean newEditBean();
}
