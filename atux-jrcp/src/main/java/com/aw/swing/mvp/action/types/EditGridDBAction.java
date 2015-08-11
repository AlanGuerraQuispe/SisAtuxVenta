package com.aw.swing.mvp.action.types;

//import com.aw.core.domain.security.GridRepository;2.0.1

/**
 * Copyright (c) 2008 AW <br>
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
public class EditGridDBAction<GridBean, EditBean> extends EditAction<GridBean, EditBean> {
//    GridRepository<GridBean, EditBean> rp;2.0.1
//    public EditGridDBAction(GridRepository<GridBean, EditBean> rp) {
//        this.rp = rp;
//    }
    protected EditBean getObjectToBeUpdated(GridBean obj) {
//        EditBean bean = rp.queryEditBean(obj);
//        return bean;
        return null;
    }
    protected GridBean getRowToBeDisplayed(EditBean  row) {
//        return rp.queryGridBean(row);
        return null;
    }

}