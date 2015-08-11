package com.aw.core.dao.bean.sql;

import com.aw.core.dao.bean.BeanSqlAbstractTest;
import com.aw.core.dao.bean.DAOHbmTableImpl;
import com.aw.core.dao.sql.SelectEntityParser;

import java.util.Date;

/**
 * User: Julio C. Macavilca
 * Date: 31/01/2008
 */
public class TestSelectEntityParser  extends BeanSqlAbstractTest {
    public void testFull(){
        SelectEntityParser entityParser = new SelectEntityParser();
        StringBuffer sql = entityParser.paserXX("select bean.*, FIELD_DATE as fecha, FIELD_DATE as fecha2, FIELD_DATE as \"fecha3\" from DAO_Hbm_Table bean where ab.ID_FIELD = 1", BNTest.class);
    }

    public static class BNTest{
        private DAOHbmTableImpl bean;
        private Date fecha;
        private Date fecha2;
        private Date fecha3;

        public DAOHbmTableImpl getBean() {
            return bean;
        }

        public void setBean(DAOHbmTableImpl bean) {
            this.bean = bean;
        }

        public Date getFecha() {
            return fecha;
        }

        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }

        public Date getFecha2() {
            return fecha2;
        }

        public void setFecha2(Date fecha2) {
            this.fecha2 = fecha2;
        }

        public Date getFecha3() {
            return fecha3;
        }

        public void setFecha3(Date fecha3) {
            this.fecha3 = fecha3;
        }
    }
}
