package com.aw.swing.mvp.action.types;

//import com.aw.core.domain.security.GridRepository;2.0.1

/**
 * Copyright (c) 2008 AW <br>
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
public class InsertGridDBAction<GridBean, EditBean> extends InsertAction<GridBean, EditBean> {
//    GridRepository<GridBean, EditBean> rp;2.0.1
//    public InsertGridDBAction(GridRepository<GridBean, EditBean> rp) {
//        this.rp = rp;
//    }

    protected EditBean getObjectToBeInserted() throws IllegalAccessException, InstantiationException {
//        return rp.newEditBean();  2.0.1
        return null;
    }
    protected GridBean getRowToBeAdded(EditBean row) {
//        return rp.queryGridBean(row);  2.0.1
        return null;
    }

}
