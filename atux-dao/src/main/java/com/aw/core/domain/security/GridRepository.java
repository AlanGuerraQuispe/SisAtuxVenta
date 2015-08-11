package com.aw.core.domain.security;

/**
 * <br>
 * Funcionalidad : <br>
 * <br>
 * Hist�rico de Creaci�n/Modificaci�n<br>
 * <br>
 * ID   PROGRAMADOR  FECHA/HORA  TIPO          OBSERVACI�N
 * 001   JCM          30/04/2009 Creaci�n      <br>
 * 002   JCM          06/10/2008  Modificaci�n  <br>
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
